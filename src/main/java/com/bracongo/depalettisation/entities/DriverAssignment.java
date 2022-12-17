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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author f.tshizubu
 */
@Entity
@Table(name="T_DRIVER_ASSIGNMENT")
@Data
@Configuration
public class DriverAssignment extends BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DRIVER_ASSIGNMENT_ID")
    private long driverAssignmentId;
    
    @NotNull(message = "L'état d'une affectation d'un chauffeur ne peut être nul")
    @Column(name="IS_CURRENT")
    private boolean isCurrent;
    
    @NotNull(message = "Une affectation doit toujours avoir son chauffeur")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Driver driver;
    
    @NotNull(message = "Une affectation doit toujours être liée à un circuit")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Circuit circuit;

    @NotNull(message = "Une affectation doit toujours être liée à un camion")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Truck truck;
    
    @JsonIgnore
    @OneToMany(mappedBy = "driverAssignment", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Depalettization> depalettizations;
    
    @JsonIgnore
    @OneToMany(mappedBy = "driverAssignment", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<StatementDetail> statementDetails;
    
    
    public DriverAssignment() {
    }  

    @Override
    public DriverAssignment clone() throws CloneNotSupportedException {
         super.clone(); 
         DriverAssignment driverAssignment = new DriverAssignment();
         driverAssignment.setDriverAssignmentId(driverAssignmentId);
         driverAssignment.setTruck(truck);
         driverAssignment.setCurrent(isCurrent);
         driverAssignment.setCircuit(circuit);
         driverAssignment.setDriver(driver);
         return driverAssignment;
    }
    
                
}
