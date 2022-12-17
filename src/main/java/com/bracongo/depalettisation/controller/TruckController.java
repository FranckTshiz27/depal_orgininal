/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.Truck;
import com.bracongo.depalettisation.service.impl.TruckService;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

/**
 *
 * @author J.LUTUNDULA
 */
@RestController
@RequestMapping("/truck")
@RequiredArgsConstructor
@Component
public class TruckController {

    @Autowired
    private TruckService truckService;

     @GetMapping
    public ResponseEntity<List<Truck>> findAll() {
       
        List<Truck>trucks = truckService.getUnPagedTrucks();
        
       return new ResponseEntity<>(trucks,HttpStatus.OK);
    }
    
    @GetMapping("/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Truck>> findAll(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
         Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Truck>trucks = truckService.getTrucks(page) ;
       return new ResponseEntity<>(trucks,HttpStatus.OK);
    }

    
    @GetMapping("/filter/{pageSize}/{pageNumber}/{ub}")
    public ResponseEntity<Page<Truck>> findAll(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber,@PathVariable("ub") String ub) {
         Pageable page = PageRequest.of(pageNumber,pageSize);
         if("undefined".equals(ub))
            ub="";
        Page<Truck>trucks = truckService.getTrucksByUb(ub,page) ;
       return new ResponseEntity<>(trucks,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Truck> create(@Valid @RequestBody Truck truck) {
        Truck newFunctionEntity = truckService.saveOrUpdate(truck);
        return new ResponseEntity<>(newFunctionEntity, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Truck> update(@RequestBody Truck truck,@PathVariable("id") long id) {
       
        truck.setTruckId(id);
        Truck updateTruckEntity = truckService.updateTruck(truck);
        
        return new ResponseEntity<>(updateTruckEntity, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {

        if (truckService.delete(id) >= 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }

    }

   

    @GetMapping("{id}")
    public ResponseEntity<Truck> findById(@PathVariable("id") Long id) {
        Truck truck = truckService.getTruckById(id);
        return new ResponseEntity<>(truck, HttpStatus.OK);
    }
}
