/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.bracongo.depalettisation.enumerations.DepalettizationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name ="FULLDEPALETTIZATION")
@Configuration
@Data
public class FullDepalettization  implements Serializable {
     @Id
     @NotNull
     @Column(name="FULLDEPALETTIZATION_ID",unique = true)
     private long fullDepalettizationId;
    
    @NotNull(message = "Le type d'une dépalettisation ne peut être nul")
    @Column(name="FULL_DEPALETTIZATION_TYPE")
    @Enumerated(EnumType.STRING)
    private DepalettizationType depalettizationType;
    
    @JsonIgnore
    @OneToMany(mappedBy = "fullDepalettization", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<FullDepalettizationDetail> fullDepalettizationDetails;
}
