/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;
import com.bracongo.depalettisation.dto.AgentDto;
import com.bracongo.depalettisation.entities.Agent;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
public interface IAgentService{
    
    public Agent saveOrUpdate(AgentDto agentDto);

    public int delete(Long id);
    
    public Agent getAgentById(Long id);
    
    public List<Agent> getAgents();
}
