/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.bracongo.depalettisation.enumerations.Sex;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author f.tshizubu
 */
@Entity
@Table(name = "T_AGENT")
@Configuration
@Data
public class Agent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AGENT_ID")
    private long agentId;

    @NotBlank(message = "Le nom d'un agent ne peut être nul vide")
    @Column(name = "NAME")
    private String name;

    @NotBlank(message = "Le post-nom d'un agent ne peut être nul ou vide")
    @Column(name = "POSTNAME")
    private String postname;

    @NotBlank(message = "Le prénom d'un agent ne peut être nul ou vide")
    @Column(name = "FIRSTNAME")
    private String firstname;

    @NotBlank(message = "Le numéro de téléphone d'un agent ne peut être nul ou vide")
    @Column(name = "PHONENUMBER", unique = true)
    private String phonenumber;

    @NotNull(message = "Le sexe de l'agent ne peut être nul ou vide")
    @Column(name = "SEX")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @NotNull(message = "Le compte d'un agent ne peut être nul")
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    private Account account;

    @Column(name = "AGENT_IMAGE", length = 100000)
    @Lob
    private byte[] agentImage;
    
    @JsonIgnore
    @OneToMany(mappedBy = "agent", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<Affectation> affectations;
    
    @JsonIgnore
    @OneToMany(mappedBy = "performAgent",cascade=CascadeType.PERSIST,fetch = FetchType.EAGER)
    List<Perform> performs;
    
    @JsonIgnore
    @OneToMany(mappedBy = "agent", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    List<ValidateNotification> validateNotifications;
    

    public Agent() {
        affectations = new ArrayList<>();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        Agent agent = new Agent();
        agent.setAgentId(agentId);
        agent.setAccount(account);
        agent.setFirstname(firstname);
        agent.setName(name);
        agent.setPhonenumber(phonenumber);
        agent.setPostname(postname);
        agent.setAffectations(affectations);
        agent.setSex(sex);

        return agent;
    }

}
