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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author F.TSHIZUBU
 */
@Entity
@Table(name = "T_SECTION")
@Data
public class ServiceEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceId;

    @NotBlank(message = "L'intitulé d'un service ne peut être nul ou vide")
    @Column(name = "DENOMINATION")
    private String denomination;
    
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    Center center;
    
    @JsonIgnore
    @OneToMany(mappedBy = "service", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<FunctionEntity> functions;
 
    public ServiceEntity() {
    }

}
