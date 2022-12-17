/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.DriverAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author f.tshizubu
 */
@Repository
public interface DriverAssignmentDao extends JpaRepository<DriverAssignment, Long>{
    
    /**
     *
     * @param id
     * @return
     */
    Optional<DriverAssignment> findDriverAssignmentByDriverAssignmentId(Long id);
    int deleteDriverAssignmentByDriverAssignmentId(Long id);
    
    @Query("select  d from DriverAssignment d  where d.isCurrent=true and d.driver.driverId=:driverId")
    DriverAssignment getCurrentDriverAssignmentByDriverId(@Param("driverId") long driverAssignmentId);
    
    
    @Modifying
     @Query("update  DriverAssignment d  set d.isCurrent = false where  d.truck.truckId=:truckId")
     int desactiveAllDriverAssignmentsByTruck(@Param("truckId") long truckId);
    
    
    @Modifying
     @Query("update  DriverAssignment d  set d.isCurrent = false where  d.circuit.circuitId=:circuitId and d.truck.truckId=:truckId")
     int desactiveAllDriverAssignmentsByCircuitAndTruck(@Param("circuitId") long circuitId , @Param("truckId") long truckId);
     
     @Modifying
     @Query("update  DriverAssignment d  set d.isCurrent = false where  d.driver.driverId=:driverId")
     int desactiveAllDriverAssignmentsByDriver(@Param("driverId") long driverId);
    
     @Modifying
     @Query("update  DriverAssignment d  set d.isCurrent =:state where  d.driverAssignmentId=:driverAssignmentId")
      int updateDriverAssignmentState(@Param("driverAssignmentId") long driverAssignmentId,@Param("state") boolean state);
     
    @Query("select  d from DriverAssignment d where d.isCurrent=true and d.driverAssignmentId=:driverAssignmentId")
    DriverAssignment getCurrentDriverAssignmentByDriverAssignmentId(@Param("driverAssignmentId") long driverAssignmentId);
    
    @Query("select  d from DriverAssignment d where d.isCurrent=true order by  d.circuit.code asc , d.truck.ub asc , d.driver.name asc, d.updatedAt desc , d.createdAt desc ")
    Page<DriverAssignment> getCurrentDriverAssignments(Pageable page);
    
    @Query("select  d from DriverAssignment d where d.isCurrent=false order by d.circuit.code asc , d.truck.ub asc , d.driver.name asc , d.updatedAt desc , d.createdAt desc ")
    Page<DriverAssignment> getNotCurrentDriverAssignments(Pageable page);
    
    @Query("select  d from DriverAssignment d where d.isCurrent=true and d.driver.name LIKE %:name% "
            + " order by  d.circuit.code asc , d.truck.ub asc , d.driver.name asc , d.updatedAt desc , d.createdAt desc ")
    Page<DriverAssignment> getCurrentDriverAssignmentsByName(@Param("name") String name,Pageable page);
    
    @Query("select  d from DriverAssignment d where d.isCurrent=false and d.driver.name LIKE %:name% "
            + " order by d.circuit.code asc , d.truck.ub asc , d.driver.name asc , d.updatedAt desc , d.createdAt desc ")
    Page<DriverAssignment> getNotCurrentDriverAssignmentsByName(@Param("name") String name,Pageable page);
    
    @Query("select  d from DriverAssignment d where d.isCurrent=true and d.circuit.center.centerId=:centerId "
            + " order by d.circuit.code asc , d.truck.ub asc , d.driver.name asc , d.updatedAt desc , d.createdAt desc  ")
    Page<DriverAssignment> getCurrentDriverAssignmentsByCenterId(@Param("centerId") long centerId,Pageable page);
    
    @Query("select  d from DriverAssignment d where d.isCurrent=false and d.circuit.center.centerId=:centerId "
            + " order by d.circuit.code asc , d.truck.ub asc , d.driver.name asc , d.updatedAt desc , d.createdAt desc")
    Page<DriverAssignment> getNotCurrentDriverAssignmentsByCenterId(@Param("centerId") long centerId,Pageable page);
    
    @Query("select  d from DriverAssignment d where d.isCurrent=true and d.circuit.center.centerId=:centerId "
            + " and d.driver.name LIKE %:name% "
            + " order by d.circuit.code asc , d.truck.ub asc , d.driver.name asc , d.updatedAt desc , d.createdAt desc ")
    Page<DriverAssignment> getCurrentDriverAssignmentsByCenterIdAndDriverName(@Param("centerId") long centerId,@Param("name") String name,Pageable page);
    
    @Query("select  d from DriverAssignment d where d.isCurrent=false and d.circuit.center.centerId=:centerId "
            + "and d.driver.name LIKE %:name% order by d.circuit.code asc , d.truck.ub asc , d.driver.name asc , d.updatedAt desc , d.createdAt desc ")
    Page<DriverAssignment> getNotCurrentDriverAssignmentsByCenterIdAndDriverName(@Param("centerId") long centerId,@Param("name") String name,Pageable page);
    
     @Query("select  d from DriverAssignment d where d.isCurrent=true and d.truck.ub=:ub")
    DriverAssignment getCurrentDriverAssignmentByTruckUb(@Param("ub") String ub);

}
