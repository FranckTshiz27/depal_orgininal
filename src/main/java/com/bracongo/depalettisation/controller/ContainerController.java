/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.Container;
import com.bracongo.depalettisation.service.impl.ContainerService;
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
import javax.servlet.ServletContext;
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
@RequestMapping("/container")
@RequiredArgsConstructor
@Component

public class ContainerController {

    @Autowired
    private ContainerService containerService;
    @Autowired
    private ServletContext context;

    @PostMapping
    public ResponseEntity<Container> create(@Valid @RequestBody Container container) {
        Container newContainer = containerService.saveOrUpdate(container);
        return new ResponseEntity<>(newContainer, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Container> update(@RequestBody Container container) {
        if (container.getContainerId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Container updateContainer = containerService.saveOrUpdate(container);
        return new ResponseEntity<>(updateContainer, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") int id) {

        if (containerService.delete(id) >= 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping
    public ResponseEntity<List<Container>> findAll() {
        List<Container> containers = containerService.getContainers();
        return new ResponseEntity<>(containers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Container> findById(@PathVariable("id") int id) {
        Container container = containerService.getContainerById(id);
        return new ResponseEntity<>(container, HttpStatus.OK);
    }
}
