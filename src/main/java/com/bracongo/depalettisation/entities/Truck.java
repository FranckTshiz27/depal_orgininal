/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.bracongo.depalettisation.enumerations.TruckState;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author J.LUTUNDULA
 */
@Entity
@Table(name = "T_TRUCK")
@Data
public class Truck implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRUCK_ID")
    private long truckId;

    @NotBlank(message = "L'UB d'un camion ne peut être nul ou vide")
    @Column(name = "UB", unique = true)
    private String ub;
    
    @NotNull(message = "L'état d'un camion doit toujours être renseigné!!")
    @Column(name="TRUCKSTATE")
    @Enumerated(EnumType.STRING)
    private TruckState state;
    
    @JsonIgnore
    @OneToMany(mappedBy = "truck",cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    List<DriverAssignment> driverAssignments;
   
            
}
