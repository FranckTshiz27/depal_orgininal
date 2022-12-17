/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Circuit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author J.LUTUNDULA
 */
@Repository
public interface CircuitDao  extends JpaRepository<Circuit, Long> {

    Optional<Circuit> findCircuitByCircuitId(Long id);

    @Query("select ct from Circuit ct inner join Center c on c.centerId = ct.center.centerId "
            + "where c.centerId =:centerId order by ct.name asc")
    public Page<Circuit> getCircuitsByCenterId(@Param("centerId") long id,Pageable  page);

    @Query("select ct from Circuit ct inner join Center c on c.centerId = ct.center.centerId "
            + "where c.centerId =:centerId order by ct.name asc")
    public List<Circuit> getCircuitsByCenterId(@Param("centerId") long id);
    
    @Query("select ct from Circuit ct inner join Center c on c.centerId = ct.center.centerId "
            + "where c.centerId=:centerId and ct.name=:name order by ct.name asc")
    public List<Circuit> getCircuitsByCenterIdAndCiricuitName(@Param("centerId") long id, @Param("name") String name);
    
    @Query("select c from Circuit c order by c.name asc")
    public Page<Circuit> getCircuits(Pageable page);
    
    @Query("select c from Circuit c where c.name LIKE %:name% order by c.name asc")
    public Page<Circuit> getCircuitsByname(@Param("name") String name,Pageable page);
    
     @Query("select c from Circuit c where c.name LIKE %:name% and c.center.centerId =:centerId order by c.name asc")
    public Page<Circuit> getCircuitsBynameByCenter(@Param("centerId") long centerId,@Param("name") String name,Pageable page);
     
}
