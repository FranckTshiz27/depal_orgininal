/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.AccountDao;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.dao.AgentDao;
import com.bracongo.depalettisation.dto.AgentDto;
import com.bracongo.depalettisation.entities.Account;
import com.bracongo.depalettisation.service.IAgentService;
import com.bracongo.depalettisation.entities.Affectation;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.dao.AffectationDao;
import com.bracongo.depalettisation.entities.Agent;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class AgentService implements IAgentService {

    @Autowired
    private AgentDao agentDao;
    
    @Autowired
    private AccountDao accountDao;
    
    @Autowired
    private AffectationDao affectationDao;
    
    @Autowired
    private Agent agent;
    
    @Autowired
    private Affectation affectation;
    
    @Autowired
    private Account account;
 
    
    @Override
    public int delete(Long id){
        
        Optional<Agent> deletingAgent = agentDao.findAgentByAgentId(id);
        
        if (deletingAgent!=null) {
            agentDao.deleteAgentByAgentId(id);
            return 1;
        }
            
        return -1;
        
    }

    @Override
    public Agent getAgentById(Long id) {
        return  agentDao.findAgentByAgentId(id).
                orElseThrow(()->new CustomNotFoundException("Le agent dont le id "+id+" est introuvable"));
    }
    
    @Override
    public List<Agent> getAgents() { 
       return agentDao.getAgentsOrderByNameAsc();  
    }

    @Override
    public Agent saveOrUpdate(AgentDto agentDto) {
        
         try {
            this.agent =agentDto.getAgent();
            this.affectation =  agentDto.getAffectation();
        } catch (Exception e) {
        }
        this.account = accountDao.save(agentDto.getAgent().getAccount());
        if (account!=null)
            this.agent = agentDao.save(this.agent);
   
        this.affectation.setAgent(this.agent);
        affectationDao.save(this.affectation);
        
        return agent;
    }
 public Agent updateAgent(Agent agent) {
        
     Optional<Agent> newAgent = agentDao.findAgentByAgentId(agent.getAgentId());
     
     if (newAgent!=null) 
         return agentDao.save(agent);
        return null;
    }
 
  public Agent getAgentByAccount(long accountId ) {
        return  agentDao.getAgentByAccount(accountId);
               
    }
  
  
}
