/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.dao.PackagingDao;
import com.bracongo.depalettisation.dto.PackagingDto;
import com.bracongo.depalettisation.entities.Packaging;
import com.bracongo.depalettisation.service.impl.PackagingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author J.LUTUNDULA
 */
@RestController
@RequestMapping("/packaging")
@RequiredArgsConstructor
@Component
public class PackagingController {

    @Autowired
    private PackagingService packagingService;
    
    @Autowired
    private PackagingDao packagingDao;

    @PostMapping
    public ResponseEntity<Packaging> create(@RequestParam("imageFile") MultipartFile file, @RequestParam("packaging") String packagingString) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        PackagingDto packagingDto = mapper.readValue(packagingString, PackagingDto.class);
        
        Packaging packaging = new Packaging();
        packaging.setCode(packagingDto.getCode());
        packaging.setContainer(packagingDto.getContainer());
        packaging.setFormat(packagingDto.getFormat());
        packaging.setPackagingImage(compressBytes(file.getBytes()));
        
        Packaging newPackaging = packagingService.saveOrUpdate(packaging);
        return new ResponseEntity<>(newPackaging, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity update(@RequestBody Packaging packaging) throws JsonProcessingException, IOException {       
        if (packaging.getPackagingId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        packagingDao.updatePackagingDataWithoutImage(packaging.getContainer(), packaging.getFormat(), packaging.getCode(), packaging.getPackagingId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @PatchMapping("image/{id}")
    public ResponseEntity update(@RequestParam("imageFile") MultipartFile file, @PathVariable("id") int id) throws JsonProcessingException, IOException {
        Packaging packaging = new Packaging();
        packaging.setPackagingId(id);
        packaging.setPackagingImage(compressBytes(file.getBytes()));
        
        if (packaging.getPackagingId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        packagingDao.updatePackagingImage(packaging.getPackagingImage(), packaging.getPackagingId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") int id) {

        if (packagingService.delete(id) >= 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }

    }
    
    @GetMapping("image/{id}")
    public byte[] getPackagingImage(@PathVariable("id") int id) throws Exception{
        Packaging packaging = packagingService.getPackagingById(id);
        return decompressBytes(packaging.getPackagingImage());
    }

    @GetMapping("/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Packaging>> findAll(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Packaging> packagings = packagingService.getPackagings(page);
        return new ResponseEntity<>(packagings, HttpStatus.OK);
    }

    @GetMapping("/{pageSize}/{pageNumber}/{name}")
    public ResponseEntity<Page<Packaging>> findAll(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("name") String name) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Packaging> packagings = packagingService.getPackagingByContainerName(name, page);
        return new ResponseEntity<>(packagings, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Packaging> findById(@PathVariable("id") int id) {
        Packaging packaging = packagingService.getPackagingById(id);
        return new ResponseEntity<>(packaging, HttpStatus.OK);
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
        }
        return outputStream.toByteArray();
    }

    
    @GetMapping
    public ResponseEntity<List<Packaging>> getPackagings() {
        List<Packaging> packaging = packagingService.getUnPackagings();
        return new ResponseEntity<>(packaging, HttpStatus.OK);
    }
}
