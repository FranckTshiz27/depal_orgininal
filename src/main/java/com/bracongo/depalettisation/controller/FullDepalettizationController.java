/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.FullDepalettization;
import com.bracongo.depalettisation.service.impl.FullDepalettizationService;
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
@RequestMapping("/fullDepalettization")
@RequiredArgsConstructor
@Component
public class FullDepalettizationController {

    @Autowired
    private FullDepalettizationService fullFullDepalettizationService;

    @GetMapping
    public ResponseEntity<List<FullDepalettization>> getFullDepalettizationsByDepalettizationId() {  
        List<FullDepalettization> fullFullDepalettizations = fullFullDepalettizationService.getFullDepalettizations();
        return new ResponseEntity<>(fullFullDepalettizations, HttpStatus.OK);
    }
}
