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
@Table(name ="T_EMPTY_DEPALETTIZATION_DETAIL")
@Configuration
@Data
public class EmptyDepalettizationDetail extends BaseEntity implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPTY_DEPALETTIZATION_DETAIL_ID")
    private long emptyDepalettizationDetailId;
    
    @NotNull(message = "Un détail de dépaléttisation doit toujours avoir sa depaléttisation ")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    EmptyDepalettization emptyDepalettization;
    
    @NotNull(message = "Un détail de dépaléttisation doit toujours avoir son emballage ")
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Packaging packaging;

    @NotNull(message = "Le nombre de casiers ne peut être nul")
    @Column(name = "NUMBER_OF_LOCKERS",columnDefinition = "integer default 0")
    private int numberOfLockers;
    
    @Column(name = "NUMBER_OF_BOTTLES", columnDefinition = "integer default 0")
    private int numberOfBottles;
    
    @Column(name = "NUMBER_OF_BROKEN_BOTTLES", columnDefinition = "integer default 0")
    private int numberOfBrokenBottles;
    
}
