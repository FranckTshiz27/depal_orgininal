/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Truck;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *
 * @author J.LUTUNDULA
 */
@Repository
public interface TruckDao  extends JpaRepository<Truck, Long> {

    Optional<Truck> findTruckByTruckId(Long id);

    @Query("select t from Truck t order by t.ub asc")
    public Page<Truck> getTrucksOrderByUb(Pageable page);
    
    @Query("select t from Truck t where t.ub LIKE %:ub%")
    Page<Truck> getTrucksByUb(@Param("ub") String ub,Pageable page);
    
     @Query("select t from Truck t order by t.ub asc")
    public List<Truck> getUnPagedTrucksOrderByUb();
}
