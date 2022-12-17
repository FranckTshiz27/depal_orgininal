/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.dto.DriverDto;
import com.bracongo.depalettisation.service.impl.DriverService;
import com.bracongo.depalettisation.entities.Driver;
import com.bracongo.depalettisation.entities.DriverAssignment;
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
@RequestMapping("/driver")
@RequiredArgsConstructor
@Component
public class DriverController {
    
    @Autowired
    private DriverService driverService;
    
    @PostMapping
    public ResponseEntity<DriverAssignment> create(@Valid @RequestBody DriverDto driver) {
        DriverAssignment newDriver =driverService.saveOrUpdate(driver);
        return new ResponseEntity<>(newDriver, HttpStatus.CREATED);
    }
    
    @PatchMapping("{id}")    
    public ResponseEntity<DriverAssignment> update(@RequestBody Driver driver, @PathVariable("id") long id) {
        driver.setDriverId(id);
        DriverAssignment updateDriver = driverService.updateDriver(driver);
        return new ResponseEntity<>(updateDriver, HttpStatus.OK);        
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        if (driverService.delete(id) > 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);            
        }
        
    }    
    
    @GetMapping
    public ResponseEntity<List<Driver>> findAll() {
        List<Driver>drivers =driverService.getDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Driver> findById(@PathVariable("id") Long id) {
        Driver driver =driverService.getDriverById(id);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }
}
