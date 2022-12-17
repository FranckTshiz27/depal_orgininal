/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservationDetail;
import com.bracongo.depalettisation.entities.FullDepalettizationDetail;
import com.bracongo.depalettisation.service.impl.EmptyDepalettizationDetailObservationService;
import com.bracongo.depalettisation.service.impl.EmptyDepalettizationDetailService;
import com.bracongo.depalettisation.service.impl.EmptyDepalettizationObservationService;
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
@RequestMapping("/emptyDepalettizationObservationDetail")
@RequiredArgsConstructor
@Component
public class EmptyDepalettizationObservationDetailController {

 @Autowired
    private  EmptyDepalettizationDetailObservationService emptyDepalettizationDetailService;

    @GetMapping("{depalettizationId}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<EmptyDepalettizationObservationDetail>> getEmptyDepalettizationDetailsByDepalettizationId(@PathVariable("depalettizationId") long depalettizationId,@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {  
           Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<EmptyDepalettizationObservationDetail> emptyDepalettizationDetails = emptyDepalettizationDetailService.getEmptyDepalettizationDetails(page, depalettizationId);
        return new ResponseEntity<>(emptyDepalettizationDetails, HttpStatus.OK);
    }
    
     @GetMapping("{depalettizationId}")
    public ResponseEntity<List<EmptyDepalettizationObservationDetail>> getAllEmptyDepalettizationDetailsByDepalettizationId(@PathVariable("depalettizationId") long depalettizationId) {    
        List<EmptyDepalettizationObservationDetail> emptyDepalettizationDetails = emptyDepalettizationDetailService.getEmptyDepalettizationDetails(depalettizationId);
        return new ResponseEntity<>(emptyDepalettizationDetails, HttpStatus.OK);
    }
}
