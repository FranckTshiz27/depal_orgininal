/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.bracongo.depalettisation.enumerations.Trip;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author F.TSHIZUBU
 */
@Entity
@Table(name = "T_STATEMENT")
@Configuration
@Data
public class Statement extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATEMENT_ID")
    private long statementId;

    @NotNull(message = "Le voyage d'une déclaration ne peut être vide ou nul")
    @Column(name = "TRIP")
    @Enumerated(EnumType.STRING)
    private Trip trip;

    @NotNull(message = "La date d'une déclaration ne peut être nulle")
    @Column(name = "STATEMENT_DATE")
    private String statementDate;

    @NotNull(message = "Une déclaration doit toujours avoir son centre de distribution")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    Center center;
    
    @JsonIgnore
    @OneToMany(mappedBy = "statement", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<StatementDetail> statementDetails;
    
    

  
}
