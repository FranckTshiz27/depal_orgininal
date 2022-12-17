/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.FullDepalettizationDetail;
import com.bracongo.depalettisation.service.impl.FullDepalettizationDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/fullDepalettizationDetail")
@RequiredArgsConstructor
@Component
public class FullDepalettizationDetailController {

    @Autowired
    private FullDepalettizationDetailService fullFullDepalettizationDetailService;
    
    @GetMapping()
    public ResponseEntity<List<FullDepalettizationDetail>> getAllFullDepalettizationDetails() {    
        List<FullDepalettizationDetail> fullFullDepalettizationDetails = fullFullDepalettizationDetailService.getFullDepalettizationDetails();
        return new ResponseEntity<>(fullFullDepalettizationDetails, HttpStatus.OK);
    }

    @GetMapping("{depalettizationId}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<FullDepalettizationDetail>> getFullDepalettizationDetailsByDepalettizationId(@PathVariable("depalettizationId") long depalettizationId,@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {  
           Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<FullDepalettizationDetail> fullFullDepalettizationDetails = fullFullDepalettizationDetailService.getFullDepalettizationDetails(page, depalettizationId);
        return new ResponseEntity<>(fullFullDepalettizationDetails, HttpStatus.OK);
    }
    
    
     @GetMapping("{depalettizationId}")
    public ResponseEntity<List<FullDepalettizationDetail>> getAllFullDepalettizationDetailsByDepalettizationId(@PathVariable("depalettizationId") long depalettizationId) {    
        List<FullDepalettizationDetail> fullFullDepalettizationDetails = fullFullDepalettizationDetailService.getFullDepalettizationDetails(depalettizationId);
        return new ResponseEntity<>(fullFullDepalettizationDetails, HttpStatus.OK);
    }
}
