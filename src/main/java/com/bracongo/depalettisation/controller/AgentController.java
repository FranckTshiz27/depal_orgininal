/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;


import com.bracongo.depalettisation.dao.AgentDao;
import com.bracongo.depalettisation.dto.AgentDto;
import com.bracongo.depalettisation.service.impl.AgentService;
import com.bracongo.depalettisation.entities.Agent;
import static com.bracongo.depalettisation.util.Image.compressBytes;
import static com.bracongo.depalettisation.util.Image.decompressBytes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author f.tshizubu
 */
@RestController
@RequestMapping("/agent")
@RequiredArgsConstructor
@Component
public class AgentController {

    @Autowired
    private AgentService agentService;
    
    @Autowired
    private AgentDao agentDao;

    @PostMapping
    public ResponseEntity<Agent> create(@RequestParam("imageFile") MultipartFile file, @RequestParam("agent") String agent) {
        
        AgentDto agentDto = null;

        ObjectMapper mapper = new ObjectMapper();

        try {
            agentDto = mapper.readValue(agent, AgentDto.class);
            agentDto.getAgent().setAgentImage(compressBytes(file.getBytes()));
        } catch (Exception e) {
            
            System.out.println(" erreur lors de l'insertion de l'agent "+e);
        }
        
        Agent newAgent = agentService.saveOrUpdate(agentDto);
        
        return new ResponseEntity<>(newAgent, HttpStatus.CREATED);
    }
    
    @PatchMapping("image/{id}")
    public ResponseEntity update(@RequestParam("imageFile") MultipartFile file, @PathVariable("id") long id) throws JsonProcessingException, IOException {
        Agent agent = new Agent();
        agent.setAgentId(id);
        agent.setAgentImage(compressBytes(file.getBytes()));
        
        if (agent.getAgentId()== 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
       agentDao.updateAgentImage(agent.getAgentImage(), agent.getAgentId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @GetMapping("image/{id}")
    public byte[] getAgentImage(@PathVariable("id") long id) throws Exception{
        Agent agent = agentService.getAgentById(id);
        if (decompressBytes(agent.getAgentImage())!=null) 
            return decompressBytes(agent.getAgentImage());
        return null;
    }

    @PatchMapping("{id}")
    public ResponseEntity<Integer> update(@RequestBody Agent agent, @PathVariable("id") long id) {
        agent.setAgentId(id);
        Agent newAgent = agentService.getAgentById(id);
        int resp = agentDao.updateAgent(agent.getName(),agent.getPostname(), agent.getFirstname(), agent.getPhonenumber(), agent.getSex(), agent.getAgentId());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {

        if (agentService.delete(id) > 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping
    public ResponseEntity<List<Agent>> findAll() {
        List<Agent> agents = agentService.getAgents();
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Agent> findById(@PathVariable("id") Long id) {
        Agent agent = agentService.getAgentById(id);
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }
    
     @GetMapping("account/{id}")
    public ResponseEntity<Agent> findAgentByAccount(@PathVariable("id") Long id) {
        Agent agent = agentService.getAgentByAccount(id);
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }
}
