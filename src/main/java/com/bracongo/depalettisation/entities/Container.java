/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.bracongo.depalettisation.enumerations.PackagingCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author f.tshizubu
 */
@Entity
@Table(name = "T_CONTAINER")
@Data
public class Container implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTAINER_ID")
    private int containerId;

    @NotBlank(message = "Le nom d'un emballage ne peut être nul ou vide")
    @Column(name = "NAME", unique = true)
    private String name;

    @NotNull(message = "La catégorie d'un emballage ne peut être nulle")
    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private PackagingCategory category;

    @JsonIgnore
    @OneToMany(mappedBy = "container", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Packaging> packagings;

    public Container() {

    }

    public Container(int containerId, String name, PackagingCategory category) {
        this.containerId = containerId;
        this.name = name;
        this.category = category;
    }

}
