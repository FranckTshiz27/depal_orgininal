/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.EmptyDepalettizationObservation;
import com.bracongo.depalettisation.service.impl.EmptyDepalettizationObservationService;
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

/**
 *
 * @author f.tshizubu
 */
@RestController
@CrossOrigin
@RequestMapping("/emptyObservationDepalettization")
@RequiredArgsConstructor
@Component
public class EmptyDepalettizationObservationController {

    @Autowired
    private EmptyDepalettizationObservationService emptyDepalettizationObservationService;

       @GetMapping
    public ResponseEntity<List<EmptyDepalettizationObservation>> getEmptyDepalettizations() {    
        List<EmptyDepalettizationObservation> emptyEmptyDepalettizations = emptyDepalettizationObservationService.getEmptyDepalettizations();
        return new ResponseEntity<>(emptyEmptyDepalettizations, HttpStatus.OK);
    }
}
