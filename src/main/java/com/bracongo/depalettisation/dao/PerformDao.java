/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Perform;
import com.bracongo.depalettisation.enumerations._Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface PerformDao extends JpaRepository<Perform, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<Perform> findPerformByPerformId(long id);

    int deletePerformByPerformId(long id);

    @Query("select  p from Perform p")
     List<Perform> getPerforms();
     
      @Query("select  p from Perform p inner join Depalettization d "
              + " on d.depalettizationId = p.depalettization.depalettizationId "
              + " inner join FullDepalettization f "
              + " on d.depalettizationId = f.fullDepalettizationId "
              + " where f.fullDepalettizationId =:id")
      List<Perform> getPerformByFullDepalettization(@Param("id") long id);
      
      
     @Query("select  p from Perform p where p.depalettization.depalettizationId=:id and p.performAgent.account._role=:role")
     Perform getPerformByDepalettizationAndAgentRole(@Param("id") long id , @Param("role") _Role role);
     
     
 
}
