/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

//import com.bracongo.depalettisation.dto.Perform;
import com.bracongo.depalettisation.entities.Depalettization;
import com.bracongo.depalettisation.entities.Perform;
import com.bracongo.depalettisation.enumerations.Trip;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface DepalettizationDao extends JpaRepository<Depalettization, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<Depalettization> findDepalettizationByDepalettizationId(Long id);

    int deleteDepalettizationByDepalettizationId(Long id);

    @Transactional
    @Modifying
    @Query("update Depalettization d set d.isValidated = true where depalettizationId =:depalettizationId ")
    public int validateDepalettization(@Param("depalettizationId") long depalettizationId);

    @Query("select d from Depalettization d order by d.depalettizationDate desc , d.trip asc ")
    Page<Depalettization> getDepalettizationsOrderByDate(Pageable page);

    @Query("select d from Depalettization d where d.depalettizationId =:id")
    Depalettization getDepalettizationByDepalettizationId(@Param("id") long id);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsOrderByDateByCenterId(Pageable page, @Param("centerId") long centerId);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.trip=:trip order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsOrderByDateByCenterIdAndTrip(Pageable page, @Param("centerId") long centerId, @Param("trip") Trip trip);

    @Query("select d from Depalettization d  "
            + " where   d.trip=:trip order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsOrderByDateAndTrip(Pageable page, @Param("trip") Trip trip);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=true and  d.trip=:trip order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getValidatedDepalettizationsOrderByDateByCenterIdAndTrip(Pageable page, @Param("centerId") long centerId, @Param("trip") Trip trip);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.depalettizationDate=:date order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsOrderByDateByCenterIdAndDate(Pageable page, @Param("centerId") long centerId, @Param("date") String date);

    @Query("select d from Depalettization d  "
            + " where  d.depalettizationDate=:date order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsByDateOrderByDate(Pageable page, @Param("date") String date);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=true and d.depalettizationDate=:date order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getValidatedDepalettizationsOrderByDateByCenterIdAndDate(Pageable page, @Param("centerId") long centerId, @Param("date") String date);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=true and d.isVisited=:state order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getValidatedDepalettizationsOrderByDateByCenterIdAndVisited(Pageable page, @Param("centerId") long centerId, @Param("state") boolean state);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=:state order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsOrderByDateByCenterIdAndState(Pageable page, @Param("centerId") long centerId, @Param("state") boolean state);

    @Query("select d from Depalettization d  "
            + " where d.isValidated=:state order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsOrderByDateAndState(Pageable page, @Param("state") boolean state);

    @Query("select d from Depalettization d  "
            + " where   d.isValidated=:state and d.depalettizationDate=:date  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsByStateAndDateOrderByDate(Pageable page, @Param("state") boolean state, @Param("date") String date);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=:state and d.trip=:trip and d.depalettizationDate=:date  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsOrderByDateByCenterIdAndStateAndDateAndTrip(Pageable page, @Param("centerId") long centerId, @Param("state") boolean state, @Param("date") String date, @Param("trip") Trip trip);

    
    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=:state and d.depalettizationDate=:date  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsOrderByDateByCenterIdAndStateAndDate(Pageable page, @Param("centerId") long centerId, @Param("state") boolean state, @Param("date") String date);
    
    @Query("select d from Depalettization d  "
            + " where  d.isValidated=:state and d.trip=:trip and d.depalettizationDate=:date  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsByStateAndDateAndTripOrderByDate(Pageable page, @Param("state") boolean state, @Param("date") String date, @Param("trip") Trip trip);
    
    
    
    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=true and d.isVisited=:state  and d.trip=:trip and d.depalettizationDate=:date  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getValidatedDepalettizationsOrderByDateByCenterIdAndStateAndDateAndTrip(Pageable page, @Param("centerId") long centerId, @Param("state") boolean state, @Param("date") String date, @Param("trip") Trip trip);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=:state and d.trip=:trip  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsOrderByDateByCenterIdAndStateAndTrip(Pageable page, @Param("centerId") long centerId, @Param("state") boolean state, @Param("trip") Trip trip);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=:state and d.trip=:trip  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsByCenterIdAndStateAndTripOrderByDate(Pageable page, @Param("centerId") long centerId, @Param("state") boolean state, @Param("trip") Trip trip);
    
    
     @Query("select d from Depalettization d  "
            + " where  d.isValidated=:state and d.trip=:trip  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsByStateAndTripOrderByDate(Pageable page,  @Param("state") boolean state, @Param("trip") Trip trip);
    
    
    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.trip=:trip and d.depalettizationDate=:date  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsOrderByDateByCenterIdAndDateAndTrip(Pageable page, @Param("centerId") long centerId, @Param("trip") Trip trip, @Param("date") String date);

    
    
    @Query("select d from Depalettization d  "
            + " where  d.trip=:trip and d.depalettizationDate=:date  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getDepalettizationsByDateAndTripOrderByDate(Pageable page,  @Param("trip") Trip trip, @Param("date") String date);
    
    
    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated =true and d.trip=:trip and d.depalettizationDate=:date  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getValidatedDepalettizationsOrderByDateByCenterIdAndDateAndTrip(Pageable page, @Param("centerId") long centerId, @Param("trip") Trip trip, @Param("date") String date);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated =true and d.isVisited=:visited and d.depalettizationDate=:date  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getValidatedDepalettizationsOrderByDateByCenterIdAndDateAndVisited(Pageable page, @Param("centerId") long centerId, @Param("visited") boolean visited, @Param("date") String date);

    @Query("select d from Depalettization d  "
            + " where  d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated =true and d.isVisited=:visited and d.trip=:trip  order by d.depalettizationDate desc, d.createdAt desc ")
    Page<Depalettization> getValidatedDepalettizationsOrderByDateByCenterIdAndTripAndVisited(Pageable page, @Param("centerId") long centerId, @Param("visited") boolean visited, @Param("trip") Trip trip);

    @Query("select d from Depalettization d  "
            + " where d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=true order by d.depalettizationDate desc, d.createdAt desc")
    Page<Depalettization> getValidatedDepalettizationsOrderByDateByCenterId(Pageable page, @Param("centerId") long centerId);

    @Query("select d from Depalettization d where d.isValidated=true  order by d.depalettizationDate desc , d.trip asc")
    Page<Depalettization> getValidatedDepalettizationsOrderByDate(Pageable page);

    @Query("select d from Depalettization d where d.isValidated=false  order by d.depalettizationDate desc , d.trip asc ")
    Page<Depalettization> getUnValidatedDepalettizationsOrderByDate(Pageable page);

    @Query("select d from Depalettization d where d.driverAssignment.circuit.center.centerId =:centerId order by d.depalettizationDate desc  , d.trip asc ")
    Page<Depalettization> getDepalettizationsFromCenterOrderByDate(@Param("centerId") long centerId, Pageable page);

    @Query("select d from Depalettization d where d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=true order by d.depalettizationDate desc ")
    Page<Depalettization> getValidatedDepalettizationsFromCenterOrderByDate(@Param("centerId") long centerId, Pageable page);

    @Query("select d from Depalettization d where d.driverAssignment.circuit.center.centerId =:centerId and d.isValidated=false order by d.depalettizationDate desc , d.trip asc ")
    Page<Depalettization> getUnValidatedDepalettizationsFromCenterOrderByDate(@Param("centerId") long centerId, Pageable page);

    @Query("select d from Depalettization d where d.driverAssignment.driverAssignmentId=:driverAssignmentId and d.trip=:trip and depalettizationDate=:date ")
    Depalettization getDepalettizationByAssignmentAndTripAndDate(@Param("driverAssignmentId") long driverAssignmentId, @Param("trip") Trip trip, @Param("date") String date);

    @Query("select d from Depalettization d where d.driverAssignment.driverAssignmentId=:driverAssignmentId and d.trip=:trip and depalettizationDate=:date ")
    List<Depalettization> getDepalettizationByAssignmentAndTripAndDate2(@Param("driverAssignmentId") long driverAssignmentId, @Param("trip") Trip trip, @Param("date") String date);

    @Query("select d from Depalettization d where d.depalettizationDate=:date order by d.depalettizationDate desc , d.trip asc")
    Page<Depalettization> getDepalettizationsByDateOrderByDate(@Param("date") Date date, Pageable page);

    @Query("select d from Depalettization d where d.depalettizationDate=:date and d.isValidated = true order by d.depalettizationDate desc , d.trip asc ")
    Page<Depalettization> getValidatedDepalettizationsByDateOrderByDate(@Param("date") Date date, Pageable page);

    @Query("select d from Depalettization d where d.depalettizationDate=:date and d.isValidated = false order by d.depalettizationDate desc , d.trip asc ")
    Page<Depalettization> getUnValidatedDepalettizationsByDateOrderByDate(@Param("date") Date date, Pageable page);

    @Query("select d from Depalettization d where d.trip=:trip order by d.depalettizationDate desc , d.trip asc ")
    Page<Depalettization> getDepalettizationsByTripOrderByDate(@Param("trip") Trip trip, Pageable page);

    @Query("select d from Depalettization d where d.trip=:trip order by d.depalettizationDate desc , d.trip asc ")
    Page<Depalettization> getValidatedDepalettizationsByTripOrderByDate(@Param("trip") Trip trip, Pageable page);

    @Query("select d from Depalettization d where d.trip=:trip and d.isValidated=false order by d.depalettizationDate desc , d.trip asc ")
    Page<Depalettization> getUnValidatedDepalettizationsByTripOrderByDate(@Param("trip") Trip trip, Pageable page);

    @Query("select d from Depalettization d inner join Perform p on d.depalettizationId = p.depalettization.depalettizationId where d.depalettizationDate=:date and p.performAgent.agentId=:agentId order by d.depalettizationDate desc , d.createdAt ,d.trip asc ")
    List<Depalettization> getDepalettizationsByDateAndAgent(@Param("date") String date, @Param("agentId") long agentId);

//    @Query("update Depalettization d set d.isVisited='" + true + "' where d.depalettizationId=:depalettizationId")
//    public int visitedDepalettization(@Param("depalettizationId") long depalettizationId);
}
