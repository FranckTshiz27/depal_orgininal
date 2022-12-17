/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.StatementDetail;
import com.bracongo.depalettisation.service.impl.StatementDetailService;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author f.tshizubu
 */
@RestController
@CrossOrigin
@RequestMapping("/statementDetail")
@RequiredArgsConstructor
@Component
public class StatementDetailController {

    @Autowired
    private StatementDetailService statementDetailService;

    @GetMapping("{statementId}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<StatementDetail>> getStatementDetailsByDepalettizationId(@PathVariable("statementId") long statementId,@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {  
           Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<StatementDetail> statementDetails = statementDetailService.getStatementDetails(page, statementId);
        return new ResponseEntity<>(statementDetails, HttpStatus.OK);
    }
    
    
     @GetMapping("{statementId}")
    public ResponseEntity<List<StatementDetail>> getAllStatementDetailsByDepalettizationId(@PathVariable("statementId") long statementId) {    
        List<StatementDetail> statementDetails = statementDetailService.getStatementDetails(statementId);
        return new ResponseEntity<>(statementDetails, HttpStatus.OK);
    }
    
     @PatchMapping("/all")
    public ResponseEntity<List<StatementDetail>> create(@RequestBody List<StatementDetail> statementDetails) {    
        List<StatementDetail> details= statementDetailService.saveAllDetails(statementDetails);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
