/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;


/**
 *
 * @author f.tshizubu
 */
@Repository
public interface DriverDao extends JpaRepository<Driver, Long>{
    
    /**
     *
     * @param id
     * @return
     */
    Optional<Driver> findDriverByDriverId(Long id);
    
    int deleteDrivertByDriverId(Long id);   
    
    @Query("select d from Driver d order by d.name asc")
    public List<Driver> getDriversOrderByNameAsc();
     
}
