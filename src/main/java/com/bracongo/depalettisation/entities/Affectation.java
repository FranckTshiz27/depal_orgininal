/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;


import java.io.Serializable;
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
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author f.tshizubu
 */
@Entity
@Table(name="T_AFFECTATION")
@Data
@Configuration
public class Affectation implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AFFECTATION_ID")
    private long affectationId;
    
    @NotNull(message = "L'état d'une affectation ne peut être nul")
    @Column(name="IS_CURRENT")
    private boolean isCurrent;
    
    @NotNull(message = "Une affectation doit toujours avoir son agent")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Agent agent;
    
    @NotNull(message = "La fonction d'une affectation ne peut être nulle")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    FunctionEntity function;

    public Affectation() {
    }  

    @Override
    public Affectation clone() throws CloneNotSupportedException {
         super.clone(); 
         Affectation affectation = new Affectation();
         affectation.setAffectationId(affectationId);
         affectation.setAgent(agent);
         affectation.setCurrent(isCurrent);
         affectation.setFunction(function);
         return affectation;
    }
    
                
}
