/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Center;
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
public interface CenterDao extends JpaRepository<Center, Long>{
    
    /**
     *
     * @param id
     * @return
     */
    Optional<Center> findCenterByCenterId(Long id);
    int deleteCenterByCenterId(Long id);
    
    @Query("select c from Center c order by name asc")
    public List<Center> getCentersOrderByNameAsc();
     
}
