/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.bracongo.depalettisation.enumerations.Trip;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author F.TSHIZUBU
 */
@Entity
@Table(name = "T_DEPALETTIZATION" , uniqueConstraints = @UniqueConstraint(name="uniqueTripDateDriverAssignment", columnNames = {"trip", "DEPALETTIZATION_DATE", "DRIVER_ASSIGNMENT_ID"}))
@Configuration
@Data
public  class Depalettization extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "DEPALETTIZATION_ID")
    private long depalettizationId;
    
    @NotNull(message = "Le voyage d'une depaléttisation ne peut être vide ou nul")
    @Column(name="TRIP")
    @Enumerated(EnumType.STRING)
    private Trip trip;
    
    @NotNull(message = "L'état d'une dépaléttisation ne peut être nul")
    @Column(name="IS_VALIDATED")
    private boolean isValidated;
    
    @NotNull(message = "L'état d'une dépaléttisation vide ne peut être nul")
    @Column(name="IS_EMPTY_VALIDATED")
    private boolean isEmptyValidated;
    
    @NotNull(message = "L'état d'une dépaléttisation pleine ne peut être nul")
    @Column(name="IS_FULL_VALIDATED")
    private boolean isFullValidated;
    
    
    @NotNull(message = "L'état de réalisation d'une dépaléttisation vide ne peut être nul")
    @Column(name="IS_EMPTY_PERFORMED")
    private boolean isEmptyPerformed;
    
    @NotNull(message = "L'état de réalisation d'une dépaléttisation pleine ne peut être nul")
    @Column(name="IS_FULL_PERFORMED")
    private boolean isFullPerformed;
    
    @NotNull(message = "L'état de visite d'une dépaléttisation ne peut être nul")
    @Column(name="IS_VISITED")
    private boolean isVisited;   
    
    @NotNull(message = "L'état de visite d'une dépaléttisation par un agent ne peut être nul")
    @Column(name="HAS_BEEN_VISITED_BY_AGENT")
    private boolean hasBeenVisitedByAgent; 
    
    @NotNull(message = "L'état de l'observation d'une dépaléttisation pleine ne peut être nul")
    @Column(name="IS_OBSERVATION_PERFORMED")
    private boolean isObsevationPerformed;
   
    @Column(name="DEPALETTIZATION_DATE")
    private String depalettizationDate;
    
    @NotNull(message = "Une dépaléttisation doit toujours avoir son affectation (chauffeur, camion et circuit) ")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="DRIVER_ASSIGNMENT_ID")
    DriverAssignment driverAssignment;
    
    @JsonIgnore
    @OneToMany(mappedBy = "depalettization",cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    List<Perform> performs;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "depalettization",cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    List<AddNotification> addNotifications;
    
    @JsonIgnore
    @OneToMany(mappedBy = "depalettization",cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    List<ValidateNotification> validateNotifications;
    
    
}
