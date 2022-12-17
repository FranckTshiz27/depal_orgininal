/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.bracongo.depalettisation.enumerations.CenterZone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author f.tshizubu
 */
@Entity
@Table(name = "T_DISTRIBUTION_CENTER")
@Data
public class Center implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISTRIBUTION_CENTER_ID")
    private long centerId;

    @NotBlank(message = "Le code d'un centre de distribution ne peut être nul ou vide")
    @Column(name = "CODE", unique = true)
    private String code;

    @NotBlank(message = "Le nom d'un centre de distribution ne peut être nul ou vide")
    @Column(name = "NAME", unique = true)
    private String name;

    @NotNull(message = "La zone d'un centre de distribution ne peut être nulle")
    @Column(name = "ZONE")
    @Enumerated(EnumType.STRING)
    private CenterZone zone;

    @NotBlank(message = "L'adresse d'un centre de distribution ne peut être nulle ou vide")
    @Column(name = "ADRESS", unique = true)
    private String adress;

    @JsonIgnore
    @OneToMany(mappedBy = "center", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<ServiceEntity> services;
    
    @JsonIgnore
    @OneToMany(mappedBy = "center", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Statement> statements;

    public Center() {

    }

    public Center(long centerId, String code, String name, CenterZone zone, String adress) {
        this.centerId = centerId;
        this.code = code;
        this.name = name;
        this.zone = zone;
        this.adress = adress;
    }

}
