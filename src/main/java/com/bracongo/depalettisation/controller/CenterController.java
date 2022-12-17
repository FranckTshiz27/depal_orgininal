/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.service.impl.CenterService;
import com.bracongo.depalettisation.entities.Center;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author f.tshizubu
 */
@RestController
@CrossOrigin
@RequestMapping("/center")
@RequiredArgsConstructor
@Component

public class CenterController {   
    @Autowired
    private  CenterService centerService;
    
    @PostMapping
    public ResponseEntity<Center> create(@Valid @RequestBody Center center){
        Center newCenter =  centerService.saveOrUpdate(center);
        return new ResponseEntity<>(newCenter,HttpStatus.CREATED);
    }
   
    @PatchMapping 
    public ResponseEntity<Center> update(@RequestBody Center center){
        Center updateCenter = centerService.saveOrUpdate(center);
        return new ResponseEntity<>(updateCenter,HttpStatus.OK);  
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
       
            if(centerService.delete(id)>=1)
            {
                return  new ResponseEntity<>(1,HttpStatus.OK);
            } 
            else{
                 return  new ResponseEntity<>(-1,HttpStatus.NOT_ACCEPTABLE); 
            }
               
            
    } 
    
    @GetMapping
    public ResponseEntity<List<Center>> findAll(){
        List<Center> centers = centerService.getCenters();
        return new ResponseEntity<>(centers,HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Center> findById(@PathVariable("id") Long id){
        Center center = centerService.getCenterById(id);
        return new ResponseEntity<>(center,HttpStatus.OK);
    }
    
    @GetMapping("user/{id}")
    public ResponseEntity<List<Center>> findById(@PathVariable("id") long id){
        List<Center> centers = new ArrayList<>();
        centers.add(centerService.getCenterById(id));
        return new ResponseEntity<>(centers,HttpStatus.OK);
    }
}
