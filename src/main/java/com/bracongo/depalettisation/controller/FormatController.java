/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.Format;
import com.bracongo.depalettisation.service.impl.FormatService;
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
 * @author J.LUTUNDULA
 */
@RestController
@RequestMapping("/format")
@RequiredArgsConstructor
@Component
public class FormatController {

    @Autowired
    private FormatService formatService;

    @GetMapping
    public ResponseEntity<List<Format>> findAll() {
        List<Format> format = formatService.getFormats();
        return new ResponseEntity<>(format, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Format> create(@Valid @RequestBody Format format) {
        Format newFunctionEntity = formatService.saveOrUpdate(format);
        return new ResponseEntity<>(newFunctionEntity, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Format> update(@RequestBody Format format,@PathVariable("id") int id) {
       
        format.setFormatId(id);
        Format updateFormatEntity = formatService.updateFormat(format);
        
        return new ResponseEntity<>(updateFormatEntity, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") int id) {

        if (formatService.delete(id) >= 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }

    }


    @GetMapping("{id}")
    public ResponseEntity<Format> findById(@PathVariable("id") int id) {
        Format format = formatService.getFormatById(id);
        return new ResponseEntity<>(format, HttpStatus.OK);
    }
}
