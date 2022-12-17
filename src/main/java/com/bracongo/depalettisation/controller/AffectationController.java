/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.Affectation;
import com.bracongo.depalettisation.service.impl.AffectationService;
import javax.validation.Valid;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author f.tshizubu
 */
@RestController
@CrossOrigin
@RequestMapping("/affectation")
@RequiredArgsConstructor
@Component
public class AffectationController {   
    @Autowired
    private  AffectationService affectationService;
    
    @PostMapping
    public ResponseEntity<Affectation> create(@Valid @RequestBody Affectation affectation){
        Affectation newAffectation =  affectationService.save(affectation);
        return new ResponseEntity<>(newAffectation,HttpStatus.CREATED);
    }
   
    @PatchMapping("{affectationId}") 
    public ResponseEntity<Affectation> update(@RequestBody Affectation affectation,@PathVariable("affectationId") long affectationId){
        Affectation updateAffectation = affectationService.updateAffectation(affectation,affectationId);
        return new ResponseEntity<>(updateAffectation,HttpStatus.OK);  
    }
    @PatchMapping("/changeAffectationState/{affectationId}") 
    public ResponseEntity<Affectation> changeAffectationState(@RequestBody Affectation affectation,@PathVariable("affectationId") long affectationId){
        Affectation updateAffectation = affectationService.updateAffectation(affectation,affectationId);
        return new ResponseEntity<>(updateAffectation,HttpStatus.OK);  
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
       
            if(affectationService.delete(id)>1)
            {
                return  new ResponseEntity<>(1,HttpStatus.OK);
            } 
            else{
                 return  new ResponseEntity<>(-1,HttpStatus.NOT_ACCEPTABLE); 
            }
               
            
    } 
    
    @GetMapping
    public ResponseEntity<List<Affectation>> findAll(){
        List<Affectation> affectations = affectationService.getAffectations();
        return new ResponseEntity<>(affectations,HttpStatus.OK);
    }
    
    @GetMapping("/current/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Affectation>> getCurrentAffectations(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber){
        
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Affectation> affectations = affectationService.getCurrentAffectations(page) ;
       return new ResponseEntity<>(affectations,HttpStatus.OK);
    }
     @GetMapping("/filter/current/{pageSize}/{pageNumber}/{name}")
    public ResponseEntity<Page<Affectation>> getCurrentAffectationsByName(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber,@PathVariable("name") String name){
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Affectation> affectations = affectationService.getCurrentAffectationsByName(name,page) ;
       return new ResponseEntity<>(affectations,HttpStatus.OK);
    }
    @GetMapping("/Notcurrent/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Affectation>> getNotCurrentAffectations(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber){
        
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Affectation> affectations = affectationService.getNotCurrentAffectations(page) ;
       return new ResponseEntity<>(affectations,HttpStatus.OK);
    }
    
    @GetMapping("/filter/Notcurrent/{pageSize}/{pageNumber}/{name}")
    public ResponseEntity<Page<Affectation>> getNotCurrentAffectationsByName(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber,@PathVariable("name") String name){
        
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Affectation> affectations = affectationService.getNotCurrentAffectationsByName(name,page) ;
       return new ResponseEntity<>(affectations,HttpStatus.OK);
    }
    @GetMapping("/current/{pageSize}/{pageNumber}/{centerId}")
    public ResponseEntity<Page<Affectation>> getCurrentAffectationsByCenterId(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber,@PathVariable("centerId") long centerId){
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Affectation> affectations = affectationService.getCurrentAffectationsByCenterId(centerId, page);
       return new ResponseEntity<>(affectations,HttpStatus.OK);
    }
    @GetMapping("/filter/current/{pageSize}/{pageNumber}/{centerId}/{name}")
    public ResponseEntity<Page<Affectation>> getCurrentAffectationsByCenterIdByName(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber,@PathVariable("centerId") long centerId,@PathVariable("name") String name){
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Affectation> affectations = affectationService.getCurrentAffectationsByCenterIdByName(name,centerId, page);
       return new ResponseEntity<>(affectations,HttpStatus.OK);
    }
     @GetMapping("/Notcurrent/{pageSize}/{pageNumber}/{centerId}")
    public ResponseEntity<Page<Affectation>> getNotCurrentAffectationsByCenterId(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber,@PathVariable("centerId") long centerId){
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Affectation> affectations = affectationService.getNotCurrentAffectationsByCenterId(centerId, page);
       return new ResponseEntity<>(affectations,HttpStatus.OK);
    }
     @GetMapping("/filter/Notcurrent/{pageSize}/{pageNumber}/{centerId}/{name}")
    public ResponseEntity<Page<Affectation>> getNotCurrentAffectationsByCenterIdByName(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber,@PathVariable("centerId") long centerId,@PathVariable("name") String name){
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Affectation> affectations = affectationService.getNoCurrentAffectationsByCenterIdByName(name,centerId, page);
       return new ResponseEntity<>(affectations,HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Affectation> findById(@PathVariable("id") Long id){
        Affectation affectation = affectationService.getAffectationById(id);
        return new ResponseEntity<>(affectation,HttpStatus.OK);
    }
    
    @GetMapping("/account/{acccountId}")
    public ResponseEntity<Affectation> getAffectationByAccountId(@PathVariable("acccountId") long accountId){
        Affectation affectation = affectationService.getAffectationByAccountId(accountId);
        return new ResponseEntity<>(affectation,HttpStatus.OK);
    }
    
}
