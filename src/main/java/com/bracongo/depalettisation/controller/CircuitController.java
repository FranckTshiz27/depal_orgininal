/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.dao.CircuitDao;
import com.bracongo.depalettisation.entities.Circuit;
import com.bracongo.depalettisation.service.impl.CircuitService;
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
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author J.LUTUNDULA
 */
@RestController
@RequestMapping("/circuit")
@RequiredArgsConstructor
@Component
public class CircuitController {

    @Autowired
    private CircuitService circuitService;

    @Autowired
    private CircuitDao circuitDao;

    @GetMapping
    public ResponseEntity<List<Circuit>> findAll() {
        List<Circuit> circuit = circuitService.getCircuits();
        return new ResponseEntity<>(circuit, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Circuit> create(@Valid @RequestBody Circuit circuit) {
        Circuit newFunctionEntity = circuitService.saveOrUpdate(circuit);
        return new ResponseEntity<>(newFunctionEntity, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Circuit> update(@RequestBody Circuit circuit) {
        if (circuit.getCircuitId() == 0L) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Circuit updateCircuitEntity = circuitService.saveOrUpdate(circuit);
        return new ResponseEntity<>(updateCircuitEntity, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {

        if (circuitService.delete(id) >= 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping("allByCenter/{id}/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Circuit>> findAll(@PathVariable("id") long centerId,@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
          Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Circuit> circuits = circuitService.getCircuitsByCenterId(centerId,page);
        return new ResponseEntity<>(circuits, HttpStatus.OK);
    }

    @GetMapping("allByCenter/{id}/{name}")
    public ResponseEntity<List<Circuit>> findAll(@PathVariable("id") long centerId, @PathVariable("name") String circuitName) {
        List<Circuit> circuits = circuitDao.getCircuitsByCenterIdAndCiricuitName(centerId, circuitName);
        return new ResponseEntity<>(circuits, HttpStatus.OK);
    }
    
     @GetMapping("allByCenter/{id}")
    public ResponseEntity<List<Circuit>> findAllByCenter(@PathVariable("id") long centerId) {
        List<Circuit> circuits = circuitDao.getCircuitsByCenterId(centerId);
        return new ResponseEntity<>(circuits, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Circuit> findById(@PathVariable("id") Long id) {
        Circuit circuit = circuitService.getCircuitById(id);
        return new ResponseEntity<>(circuit, HttpStatus.OK);
    }

    @GetMapping("all/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Circuit>> getCircuits(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Circuit> circuits = circuitDao.getCircuits(page);
        return new ResponseEntity<>(circuits, HttpStatus.OK);
    }

    @GetMapping("allByName/{pageSize}/{pageNumber}/{name}")
    public ResponseEntity<Page<Circuit>> getCircuits(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("name") String name) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Circuit> circuits = circuitDao.getCircuitsByname(name, page);
        return new ResponseEntity<>(circuits, HttpStatus.OK);
    }
    
    @GetMapping("allByName/{centerId}/{pageSize}/{pageNumber}/{name}")
    public ResponseEntity<Page<Circuit>> getCircuitsByCenterId(@PathVariable("centerId") int centerId,@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("name") String name) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Circuit> circuits = circuitDao.getCircuitsBynameByCenter(centerId,name, page);
        return new ResponseEntity<>(circuits, HttpStatus.OK);
    }
}
