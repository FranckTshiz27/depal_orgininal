/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.bracongo.depalettisation.enumerations.DepalettizationType;
import com.bracongo.depalettisation.enumerations.NotificationType;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author F.TSHIZUBU
 */
@Entity
@Table(name="T_NOTIFICATION")
@Configuration
@Data
public class Notification implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="NOTIFICATION_ID")
    private long notificationId;    
     
    @NotNull(message = "Le type d'une dépalettisation ne peut être nul")
    @Column(name="DEPALETTIZATION_TYPE")
    @Enumerated(EnumType.STRING)
    private DepalettizationType depalettizationType;
     
    @NotNull(message = "Le type d'une notification ne peut être nul")
    @Column(name="NOTIFICATION_TYPE")
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;    

    @NotNull(message = "La réalisation d'une dépalettisation ne peut être nulle")
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PERFORM_ID", referencedColumnName = "PERFORM_ID")
    private Perform perform;
}
