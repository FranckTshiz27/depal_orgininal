/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.Center;
import com.bracongo.depalettisation.service.impl.ServiceEntityService;
import com.bracongo.depalettisation.entities.ServiceEntity;
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
 * @author f.tshizubu
 */
@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
@Component
public class ServiceController {
    
    @Autowired
    private  ServiceEntityService serviceEntityService;
    
    
    @GetMapping
    public ResponseEntity<List<ServiceEntity>> findAll(){
        List<ServiceEntity> services = serviceEntityService.getServices();
        return new ResponseEntity<>(services,HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<ServiceEntity> create(@Valid @RequestBody ServiceEntity service){
        ServiceEntity newServiceEntity =  serviceEntityService.saveOrUpdate(service);
        return new ResponseEntity<>(newServiceEntity,HttpStatus.CREATED);
    }
   
    @PatchMapping 
    public ResponseEntity<ServiceEntity> update(@RequestBody ServiceEntity service){
        ServiceEntity updateServiceEntity = serviceEntityService.saveOrUpdate(service);
        return new ResponseEntity<>(updateServiceEntity,HttpStatus.OK);  
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
       
            if(serviceEntityService.delete(id)>=1)
            {
                return  new ResponseEntity<>(1,HttpStatus.OK);
            } 
            else{
                 return  new ResponseEntity<>(-1,HttpStatus.NOT_ACCEPTABLE); 
            }
               
            
    } 
    
    @GetMapping("all/{centerId}")
    public ResponseEntity<List<ServiceEntity>> findAll(@PathVariable("centerId") long centerId){
        List<ServiceEntity> services = serviceEntityService.getServicesByCenterId(centerId);
        return new ResponseEntity<>(services,HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ServiceEntity> findById(@PathVariable("id") Long id){
        ServiceEntity service = serviceEntityService.getServiceById(id);
        return new ResponseEntity<>(service,HttpStatus.OK);
    }
    
    
}
