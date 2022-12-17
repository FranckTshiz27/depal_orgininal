/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author f.tshizubu
 */
@Entity
@Table(name="T_PRODUCT")
@Data
@Configuration
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRODUCT_ID")
    private int productId;
      
    @NotBlank(message = "Le code d'un produit ne peut être nul ou vide")
    @Column(name="CODE", unique = true)
    private String code;
      
  
    @Column(name="SECONDCODE", unique = true)
    private String secondCode;
      
    @Column(name="ABBREVIATION", unique = true)
    private String abbreviation;
    
    @NotNull(message = "Un produit doit toujours avoir son format")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Format formatProduct;
    
    @NotNull(message = "Un produit doit avoir sa game")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Game game;
    
    @Column(name = "PRODUCT_IMAGE", length = 100000)
    @Lob
    private byte[] productImage;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<FullDepalettizationDetail> fullDepalettizationDetails;
            
}
