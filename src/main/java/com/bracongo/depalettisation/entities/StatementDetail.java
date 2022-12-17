/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author F.TSHIZUBU
 */
@Entity
@Table(name ="T_STATEMENT_DETAIL")
@Configuration
@Data
public class StatementDetail extends BaseEntity implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATEMENT_DETAIL_ID")
    private long statementDetailId;
    
    @NotNull(message = "Un détail de déclaration doit toujours avoir sa déclaration ")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Statement statement;
    
    @NotNull(message = "Un détail de déclaration doit toujours avoir son affectation ")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    DriverAssignment driverAssignment;

    @Column(name = "NUMBER_OF_LOCKERS_FULL_CUSTOM",columnDefinition = "integer default 0")
    private int numberOfLockerFullCustom;
    
    @Column(name = "NUMBER_OF_LOCKERS_EMPTY_CUSTOM",columnDefinition = "integer default 0")
    private int numberOfLockerEmptyCustom;
    
    
    @Column(name = "NUMBER_OF_LOCKERS_FULL_SUNFLOWER",columnDefinition = "integer default 0")
    private int numberOfLockerFullSunFlower;
    
   
    @Column(name = "NUMBER_OF_LOCKERS_EMPTY_SUNFLOWER",columnDefinition = "integer default 0")
    private int numberOfLockerEmptySunFlower;
    
    
    @Column(name = "NUMBER_OF_LOCKERS_FULL_DEPALETTIZATION",columnDefinition = "integer default 0")
    private int numberOfLockerFullDepalettization;
    
   
    @Column(name = "NUMBER_OF_LOCKERS_EMPTY_DEPALETTIZATION",columnDefinition = "integer default 0")
    private int numberOfLockerEmptyDepalettization;
    
    @Column(name = "observation")
    private String observation;
    
    @Column(name = "CARGO",columnDefinition = "integer default 0")
    private int cargo;
   

}
