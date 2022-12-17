/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Format;
import com.bracongo.depalettisation.entities.Game;
import com.bracongo.depalettisation.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    /**
     *
     * @param id
     * @return
     */
    Optional<Product> findProductByProductId(int id);

    int deleteProductByProductId(int id);

    @Query("select  p from Product p  order by p.game.name asc")
    Page<Product> getProductsOrderByGameName(Pageable page);
    
     @Query("select  p from Product p  order by p.game.name asc")
    List<Product> getUnPagedProductsOrderByGameName();

    @Query("select  p from Product p  where "
            + " p.game.name LIKE %:name% "
            + " order  by p.game.name asc")
    Page<Product> getProductsByGameName(@Param("name") String name, Pageable page);
    
    @Transactional
    @Modifying
    @Query("update Product p set p.productImage=:productImage where p.productId=:productId")
    public void updateProductImage(@Param("productImage") byte[] productImage, @Param("productId") int productId);
    
    @Transactional
    @Modifying
    @Query("update Product p set p.formatProduct=:formatProduct, p.game=:game, p.code=:code , p.secondCode=:secondCode , p.abbreviation=:abbreviation where p.productId=:productId")
    public int updateProduct(@Param("formatProduct") Format format, @Param("game") Game game, @Param("code") String code,@Param("secondCode") String secondCode, @Param("abbreviation") String abbreviation,@Param("productId") int productId);

    
    
}
