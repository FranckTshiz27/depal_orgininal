/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.FunctionEntity;
import com.bracongo.depalettisation.service.impl.FunctionEntityService;
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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author J.LUTUNDULA
 */
@RestController
@RequestMapping("/function")
@RequiredArgsConstructor
@Component
public class FunctionController {
    
    @Autowired
    private  FunctionEntityService functionEntityService;
    
    
    @GetMapping
    public ResponseEntity<List<FunctionEntity>> findAll(){
        List<FunctionEntity> functions = functionEntityService.getFunctions();
        return new ResponseEntity<>(functions,HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<FunctionEntity> create(@Valid @RequestBody FunctionEntity function){
        FunctionEntity newFunctionEntity =  functionEntityService.saveOrUpdate(function);
        return new ResponseEntity<>(newFunctionEntity,HttpStatus.CREATED);
    }
   
    @PatchMapping 
    public ResponseEntity<FunctionEntity> update(@RequestBody FunctionEntity function){
        if(function.getFunctionId() == 0L) return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        FunctionEntity updateFunctionEntity = functionEntityService.saveOrUpdate(function);
        return new ResponseEntity<>(updateFunctionEntity,HttpStatus.OK);  
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
       
            if(functionEntityService.delete(id) >= 1)
            {
                return  new ResponseEntity<>(1,HttpStatus.OK);
            } 
            else{
                 return  new ResponseEntity<>(-1,HttpStatus.NOT_ACCEPTABLE); 
            }
               
            
    } 
    
    @GetMapping("/center/{centerId}")
    public ResponseEntity<List<FunctionEntity>> findAll(@PathVariable("centerId") long centerId){
        List<FunctionEntity> functions = functionEntityService.getFunctionsByCenterId(centerId);
        return new ResponseEntity<>(functions,HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<FunctionEntity> findById(@PathVariable("id") Long id){
        FunctionEntity function =functionEntityService.getFunctionById(id);
        return new ResponseEntity<>(function,HttpStatus.OK);
    }
    
    
}

