/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.GameDao;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.entities.Product;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.dao.ProductDao;
import com.bracongo.depalettisation.service.IProduct;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class ProductService implements IProduct {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private GameDao gameDao;

    @Override
    public int delete(int id) {

        Optional<Product> deletingProduct = productDao.findProductByProductId(id);

        if (deletingProduct != null) {
            productDao.deleteProductByProductId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public Product getProductById(int id) {
        return productDao.findProductByProductId(id).
                orElseThrow(() -> new CustomNotFoundException("Le produit dont l'id " + id + " est introuvable"));
    }

    @Override
    public Page<Product> getProducts(Pageable page) {
        return productDao.getProductsOrderByGameName(page);
    }

     public List<Product> getUnPagedProducts() {
        return productDao.getUnPagedProductsOrderByGameName();
    }
    @Override
    public Product save(Product product) {
      return productDao.save(product);
    }

    public Product updateProduct(Product product, int ProductId) {

        Optional<Product> editingProduct = productDao.findProductByProductId(ProductId);
        if (editingProduct != null) {
            return productDao.save(product);
        }

        return null;
    }

    public Page<Product> getProductsByGameName(String name, Pageable page) {
        return productDao.getProductsByGameName(name, page);
    }

}
