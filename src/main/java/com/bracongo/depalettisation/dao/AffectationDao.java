/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Affectation;
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
public interface AffectationDao extends JpaRepository<Affectation, Long>{
    
    /**
     *
     * @param id
     * @return
     */
    Optional<Affectation> findAffectationByAffectationId(Long id);
    int deleteAffectationByAffectationId(Long id);
    
    @Query("select  a from Affectation a  where a.isCurrent=true and a.agent.agentId=:agentId")
    Affectation getCurrentAffectationByAgentId(@Param("agentId") long affectationId);
    
    @Modifying
     @Query("update  Affectation a  set a.isCurrent = false where  a.agent.agentId=:agentId")
     int desactiveAllAffectations(@Param("agentId") long agentId);
    
     @Modifying
     @Query("update  Affectation a  set a.isCurrent =:state where  a.affectationId=:affectationId")
      int updateAffectationState(@Param("affectationId") long affectationId,@Param("state") boolean state);
     
    @Query("select  a from Affectation a where a.isCurrent=true and a.affectationId=:affectationId")
    Affectation getCurrentAffectationByAffectationId(@Param("affectationId") long affectationId);
    
    @Query("select  a from Affectation a where a.isCurrent=true order by a.function.service.center.centerId asc , a.function.denomination asc , a.agent.name asc")
    Page<Affectation> getCurrentAffectations(Pageable page);
    
    @Query("select  a from Affectation a where a.isCurrent=false order by a.function.service.center.centerId asc , a.function.denomination asc , a.agent.name asc")
    Page<Affectation> getNotCurrentAffectations(Pageable page);
    
    @Query("select  a from Affectation a where a.isCurrent=true and a.agent.name LIKE %:name% "
            + "order by a.function.service.center.centerId asc , a.function.denomination asc , a.agent.name asc")
    Page<Affectation> getCurrentAffectationsByName(@Param("name") String name,Pageable page);
    
    @Query("select  a from Affectation a where a.isCurrent=false and a.agent.name LIKE %:name% "
            + "order by a.function.service.center.centerId asc , a.function.denomination asc , a.agent.name asc")
    Page<Affectation> getNotCurrentAffectationsByName(@Param("name") String name,Pageable page);
    
    @Query("select  a from Affectation a where a.isCurrent=true and a.function.service.center.centerId=:centerId "
            + "order by a.function.service.center.centerId asc , a.function.denomination asc , a.agent.name asc")
    Page<Affectation> getCurrentAffectationsByCenterId(@Param("centerId") long centerId,Pageable page);
    
    @Query("select  a from Affectation a where a.isCurrent=false and a.function.service.center.centerId=:centerId "
            + "order by a.function.service.center.centerId asc , a.function.denomination asc , a.agent.name asc")
    Page<Affectation> getNotCurrentAffectationsByCenterId(@Param("centerId") long centerId,Pageable page);
    
    @Query("select  a from Affectation a where a.isCurrent=true and a.function.service.center.centerId=:centerId "
            + "and a.agent.name LIKE %:name% order by a.function.service.center.centerId asc , a.function.denomination asc , a.agent.name asc")
    Page<Affectation> getCurrentAffectationsByCenterIdAndAgentName(@Param("centerId") long centerId,@Param("name") String name,Pageable page);
    
     @Query("select  a from Affectation a where a.isCurrent=false and a.function.service.center.centerId=:centerId "
            + "and a.agent.name LIKE %:name% order by a.function.service.center.centerId asc , a.function.denomination asc , a.agent.name asc")
    Page<Affectation> getNotCurrentAffectationsByCenterIdAndAgentName(@Param("centerId") long centerId,@Param("name") String name,Pageable page);
    
    @Query("select af from Affectation af where af.agent.account.accountId=:accountId")
    Affectation getAffectationByAccountId(@Param("accountId") long accountId);
    
    @Query("select af from Affectation af where af.agent.account.accountId=:accountId and af.isCurrent=true")
    Affectation getCurrentAffectationByAccountId(@Param("accountId") long accountId);
}
