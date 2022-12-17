/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Agent;
import com.bracongo.depalettisation.enumerations.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author f.tshizubu
 */
@Repository
public interface AgentDao extends JpaRepository<Agent, Long>{
    
    /**
     *
     * @param id
     * @return
     * name postname firstname phonenumber sex account
     */
    Optional<Agent> findAgentByAgentId(Long id);
    int deleteAgentByAgentId(Long id);   
    
    @Query("select a from Agent a order by a.name asc")
    public List<Agent> getAgentsOrderByNameAsc();
    
    @Query("select a from Agent a where a.account.accountId=:accountId")
    public Agent getAgentByAccount( @Param("accountId") long accountId);
    
    @Transactional
    @Modifying
    @Query("update Agent a set a.agentImage=:agentImage where a.agentId=:agentId")
    public void updateAgentImage(@Param("agentImage") byte[] agentImage, @Param("agentId") long agentId);
    
    @Transactional
    @Modifying
    @Query("update Agent a set a.name=:name, a.postname=:postname, a.firstname=:firstname , a.phonenumber=:phonenumber , a.sex =:sex  where a.agentId=:agentId")
    public int updateAgent(@Param("name") String name, @Param("postname") String postname, @Param("firstname") String firstname,@Param("phonenumber") String phonenumber , @Param("sex") Sex sex ,@Param("agentId") long agentId);
     
}
