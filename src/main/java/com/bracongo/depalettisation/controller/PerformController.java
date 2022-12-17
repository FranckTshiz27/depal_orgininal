/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.Perform;
import com.bracongo.depalettisation.service.impl.PerformService;
import com.bracongo.depalettisation.util.Converter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/perfoms")
@RequiredArgsConstructor
@Component
public class PerformController {

    @Autowired
    private PerformService performService;

    @GetMapping
    public ResponseEntity<List<Perform>> getPerforms() {  
       List<Perform> performs = performService.getPerforms();
       return new ResponseEntity<>(performs, HttpStatus.OK);
    }
    
     @GetMapping("/depalettization/{id}/{role}")
    public ResponseEntity<Perform> getPerformByDepalettizationAndRole(@PathVariable("id") long id,@PathVariable("role") String role) {  
       Perform perform = performService.getPerformByDepelettization(id,Converter.stringToRole(role));
       return new ResponseEntity<>(perform, HttpStatus.OK);
    }
}
