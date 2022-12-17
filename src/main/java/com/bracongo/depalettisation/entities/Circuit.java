/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

/**
 *
 * @author J.LUTUNDULA
 */
@Entity
@Table(name = "T_CIRCUIT")
@Data
public class Circuit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CIRUIT_ID")
    private long circuitId;

    @NotBlank(message = "Le code d'un circuit ne peut être nul ou vide")
    @Column(name = "CODE", unique = true)
    private String code;

    @NotBlank(message = "Le nom d'un circuit ne peut être nul ou vide")
    @Column(name = "NAME", unique = true)
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Center center;
    @JsonIgnore
    @OneToMany(mappedBy = "circuit",cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    List<DriverAssignment> driverAssignments;

    public Circuit() {

    }

    public Circuit(long circuitId, String code, String name, Center center) {
        this.circuitId = circuitId;
        this.code = code;
        this.name = name;
        this.center = center;
    }
}
