/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.dao.ProductDao;
import com.bracongo.depalettisation.dto.ProductDto;
import com.bracongo.depalettisation.entities.Product;
import com.bracongo.depalettisation.service.impl.ProductService;
import static com.bracongo.depalettisation.util.Image.compressBytes;
import static com.bracongo.depalettisation.util.Image.decompressBytes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author f.tshizubu
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
@RequiredArgsConstructor
@Component
public class ProductController {

    @Autowired
    private ProductService productService;
     @Autowired
    private ProductDao productDao;

    @PostMapping
    public ResponseEntity<Product> create(@RequestParam("imageFile") MultipartFile file, @RequestParam("product") String product) throws JsonProcessingException, IOException {        
        
        ObjectMapper mapper = new ObjectMapper();
        ProductDto productDto = mapper.readValue(product, ProductDto.class);
        
        Product newProduct = new Product();
        newProduct.setCode(productDto.getCode());
        newProduct.setFormatProduct(productDto.getFormatProduct());
        newProduct.setGame(productDto.getGame());
        newProduct.setAbbreviation(productDto.getAbbreviation());
        newProduct.setSecondCode(productDto.getSecondCode());
        newProduct.setProductImage(compressBytes(file.getBytes()));
        
        Product savedProduct = productService.save(newProduct);
        
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PatchMapping("{productId}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable("productId") int productId) {
        
        Optional<Product> savedProduct = productDao.findProductByProductId(productId);
         Product updateProduct = null;
         int resp = -1;
        if (savedProduct!=null) {
           
              resp = productDao.updateProduct(product.getFormatProduct(),product.getGame(), product.getCode(),product.getSecondCode(),product.getAbbreviation(),product.getProductId());
              updateProduct = productDao.getById(productId);
        }
       
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PatchMapping("image/{id}")
    public ResponseEntity update(@RequestParam("imageFile") MultipartFile file, @PathVariable("id") int id) throws JsonProcessingException, IOException {
        Product product = new Product();
        product.setProductId(id);
        product.setProductImage(compressBytes(file.getBytes()));
        
        if (product.getProductId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        productDao.updateProductImage(product.getProductImage(), product.getProductId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @GetMapping("image/{id}")
    public byte[] getProductImage(@PathVariable("id") int id) throws Exception{
        Product product = productService.getProductById(id);
        if (decompressBytes(product.getProductImage())!=null)
          return decompressBytes(product.getProductImage());
        
        return null;
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") int id) {

        if (productService.delete(id) >= 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{pageSize}/{pageNumber}")
    public ResponseEntity<Page<Product>> findAll(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber) {

        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<Product> products = productService.getProducts(page);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/filter/{pageSize}/{pageNumber}/{name}")
    public ResponseEntity<Page<Product>> getProductsByGameName(@PathVariable("pageSize") int pageSize, @PathVariable("pageNumber") int pageNumber, @PathVariable("name") String name) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
     
        if("undefined".equals(name))
            name="";
        Page<Product> products = productService.getProductsByGameName(name, page);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") int id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.getUnPagedProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
