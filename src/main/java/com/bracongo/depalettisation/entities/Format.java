/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author J.LUTUNDULA
 */
@Entity
@Table(name = "T_FORMAT")
@Data
public class Format implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORMAT_ID")
    private int formatId;

    @NotBlank(message = "La dénomination d'un format ne peut être nulle")
    @Column(name = "DENOMINATION", unique = true)
    private String denomination;

    @NotNull(message = "Le nombre de bouteille d'un format ne peut être nul")
    @Min(value = 12, message = "Le nombre de bouteilles ne peut être inférieur à 12 ")
    @Column(name = "NUMBEROFBOTTLE")
    private int numberOfBottle;

    @JsonIgnore
    @OneToMany(mappedBy = "format", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Packaging> packagings;
    
    @JsonIgnore
    @OneToMany(mappedBy = "formatProduct",cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    List<Product> products;
   
    public Format() {

    }

}
