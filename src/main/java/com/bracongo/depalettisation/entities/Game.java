/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;


import com.bracongo.depalettisation.enumerations.GameCategory;
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
import javax.validation.constraints.NotBlank;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author f.tshizubu
 */
@Entity
@Table(name="T_GAME")
@Configuration
@Data
public class Game implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GAME_ID")
    private int gameId;
    
    @NotBlank(message = "Le nom d'une game ne peut être nul ou vide")
    @Column(name="NAME", unique = true)
    private String name;
      
    @NotNull(message = "La catégorie d'une game ne peut être nulle ou vide")
    @Column(name="GAMECATEGORY")
    @Enumerated(EnumType.STRING)
    private GameCategory category;
    
    @JsonIgnore
    @OneToMany(mappedBy = "game",cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    List<Product> products;
    
}
