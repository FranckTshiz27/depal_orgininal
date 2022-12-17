/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;


import com.bracongo.depalettisation.enumerations.Shift;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author f.tshizubu
 */
@Entity
@Table(name="T_PERFORM")
@Data
@Configuration
public class Perform implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PERFORM_ID")
    private long performId;
    
    @NotNull(message = "Une réalisation de dépalettisation doit toujours avoir son agent")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Agent performAgent;
    
    @NotNull(message = "Une réalisation de dépalettisation doit toujours avoir sa depaléttisation")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Depalettization depalettization;
    
    @NotNull(message = "Veuillez préciser le shift")
    @Column(name="SHIFT")
    @Enumerated(EnumType.STRING)
    private Shift shift;
    
    @JsonIgnore
    @OneToMany(mappedBy = "perform", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<ValidateNotification> validateNotifications;
             
}
