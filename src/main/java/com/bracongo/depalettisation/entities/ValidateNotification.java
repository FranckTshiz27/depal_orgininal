/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.bracongo.depalettisation.enumerations.ValidationType;
import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author F.TSHIZUBU
 */
@Entity
@Table(name = "T_VALIDATE_NOTIFICATION")
@Configuration
@Data
public class ValidateNotification extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VALIDATE_NOTIFICATION_ID")
    private long notificationId;

    @NotNull(message = "Le type d'une dépalettisation ne peut être nul")
    @Column(name = "DEPALETTIZATION_TYPE")
    @Enumerated(EnumType.STRING)
    private ValidationType validationType;

    @NotNull(message = "La réalisation d'une dépalettisation ne peut être nulle")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Perform perform;

    @NotNull(message = "L'agent validateur ne peut être nul")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Agent agent;

    @NotNull(message = "Une notifcation doit avoir sa dépalettisation")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    Depalettization depalettization;
}
