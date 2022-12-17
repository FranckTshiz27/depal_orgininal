/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.DriverAssignment;
import com.bracongo.depalettisation.service.impl.DriverAssignmentService;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author f.tshizubu
 */
@RestController
@CrossOrigin
@RequestMapping("/driverAssignment")
@RequiredArgsConstructor
@Component
public class DriverAssignmentController {

    @Autowired
    private DriverAssignmentService driverAssignmentService;

    @PostMapping
    public ResponseEntity<DriverAssignment> create(@Valid @RequestBody DriverAssignment driverAssignment) {
        DriverAssignment newDriverAssignment = driverAssignmentService.save(driverAssignment);
        return new ResponseEntity<>(newDriverAssignment, HttpStatus.CREATED);
    }

    @PatchMapping("{driverAssignmentId}")
    public ResponseEntity<DriverAssignment> update(@RequestBody DriverAssignment driverAssignment, @PathVariable("driverAssignmentId") long driverAssignmentId) {
        DriverAssignment updateDriverAssignment = driverAssignmentService.updateDriverAssignment(driverAssignment, driverAssignmentId);
        return new ResponseEntity<>(updateDriverAssignment, HttpStatus.OK);
    }

    @PatchMapping("/changeDriverAssignmentState/{driverAssignmentId}/{state}")
    public ResponseEntity<DriverAssignment> changeDriverAssignmentState(@RequestBody DriverAssignment driverAssignment, @PathVariable("driverAssignmentId") long driverAssignmentId, @PathVariable("state") boolean state) {
        DriverAssignment driver = driverAssignmentService.updateDriverAssignmentState(driverAssignment, state);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {

        if (driverAssignmentService.delete(id) > 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping
    public ResponseEntity<List<DriverAssignment>> findAll() {
        List<DriverAssignment> driverAssignments = driverAssignmentService.getDriverAssignments();
        return new ResponseEntity<>(driverAssignments, HttpStatus.OK);
    }

    @GetMapping("/current/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<DriverAssignment>> getCurrentDriverAssignments(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {

        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<DriverAssignment> driverAssignments = driverAssignmentService.getCurrentDriverAssignments(page);
        return new ResponseEntity<>(driverAssignments, HttpStatus.OK);
    }

    @GetMapping("/filter/current/{pageSize}/{pageNumber}/{name}")
    public ResponseEntity<Page<DriverAssignment>> getCurrentDriverAssignmentsByName(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("name") String name) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        if ("undefined".equals(name)) {
            name = "";
        }
        Page<DriverAssignment> driverAssignments = driverAssignmentService.getCurrentDriverAssignmentsByName(name, page);
        return new ResponseEntity<>(driverAssignments, HttpStatus.OK);
    }

    @GetMapping("/Notcurrent/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<DriverAssignment>> getNotCurrentDriverAssignments(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {

        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<DriverAssignment> driverAssignments = driverAssignmentService.getNotCurrentDriverAssignments(page);
        return new ResponseEntity<>(driverAssignments, HttpStatus.OK);
    }

    @GetMapping("/filter/Notcurrent/{pageSize}/{pageNumber}/{name}")
    public ResponseEntity<Page<DriverAssignment>> getNotCurrentDriverAssignmentsByName(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("name") String name) {
        if ("undefined".equals(name)) {
            name = "";
        }
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<DriverAssignment> driverAssignments = driverAssignmentService.getNotCurrentDriverAssignmentsByName(name, page);
        return new ResponseEntity<>(driverAssignments, HttpStatus.OK);
    }

    @GetMapping("/current/{pageSize}/{pageNumber}/{centerId}")
    public ResponseEntity<Page<DriverAssignment>> getCurrentDriverAssignmentsByCenterId(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("centerId") long centerId) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<DriverAssignment> driverAssignments = driverAssignmentService.getCurrentDriverAssignmentsByCenterId(centerId, page);
        return new ResponseEntity<>(driverAssignments, HttpStatus.OK);
    }

    @GetMapping("/filter/current/{pageSize}/{pageNumber}/{centerId}/{name}")
    public ResponseEntity<Page<DriverAssignment>> getCurrentDriverAssignmentsByCenterIdByName(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("centerId") long centerId, @PathVariable("name") String name) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        if ("undefined".equals(name)) {
            name = "";
        }
        Page<DriverAssignment> driverAssignments = driverAssignmentService.getCurrentDriverAssignmentsByCenterIdByName(name, centerId, page);
        return new ResponseEntity<>(driverAssignments, HttpStatus.OK);
    }

    @GetMapping("/Notcurrent/{pageSize}/{pageNumber}/{centerId}")
    public ResponseEntity<Page<DriverAssignment>> getNotCurrentDriverAssignmentsByCenterId(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("centerId") long centerId) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<DriverAssignment> driverAssignments = driverAssignmentService.getNotCurrentDriverAssignmentsByCenterId(centerId, page);
        return new ResponseEntity<>(driverAssignments, HttpStatus.OK);
    }

    @GetMapping("/filter/Notcurrent/{pageSize}/{pageNumber}/{centerId}/{name}")
    public ResponseEntity<Page<DriverAssignment>> getNotCurrentDriverAssignmentsByCenterIdByName(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("centerId") long centerId, @PathVariable("name") String name) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        if ("undefined".equals(name)) {
            name = "";
        }
        Page<DriverAssignment> driverAssignments = driverAssignmentService.getNoCurrentDriverAssignmentsByCenterIdByName(name, centerId, page);
        return new ResponseEntity<>(driverAssignments, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DriverAssignment> findById(@PathVariable("id") Long id) {
        DriverAssignment driverAssignment = driverAssignmentService.getDriverAssignmentById(id);
        return new ResponseEntity<>(driverAssignment, HttpStatus.OK);
    }

    @GetMapping("/truckUb/{ub}")
    public ResponseEntity<DriverAssignment> findById(@PathVariable("ub") String ub) {
        DriverAssignment driverAssignment = driverAssignmentService.geCurrentDriverAssignmentByTruckUb(ub);
        return new ResponseEntity<>(driverAssignment, HttpStatus.OK);
    }
}
