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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author J.LUTUNDULA
 */
@Entity
@Table(name = "T_PACKAGING")
@Data
public class Packaging implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PACKAGING_ID")
    private int packagingId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    Format format;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    Container container;

    @NotBlank(message = "Le code d'emballage ne peut être nul ou vide")
    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "PACKAGING_IMAGE", length = 100000)
    @Lob
    private byte[] packagingImage;
    
    @JsonIgnore
    @OneToMany(mappedBy = "packaging", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<EmptyDepalettizationDetail> details;

    public Packaging() {
    }
}
