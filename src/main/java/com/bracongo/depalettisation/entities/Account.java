/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;


import com.bracongo.depalettisation.enumerations._Role;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author f.tshizubu
 */
@Entity
@Table(name="T_ACCOUNT")
@Data
@Configuration
public class Account implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOUNT_ID")
    private long accountId;
    
    @NotBlank(message = "Le nom d'utilisateur ne peut être nul")
    @Column(name="USERNAME", unique = true)
    private String username;
    
    @NotBlank(message = "Le mot de passe ne peut être nul ou vide")
    @Column(name="PASSWORD")
    private String password;
    
    @NotNull(message = "L'état d'un compte ne peut être nul")
    @Column(name="IS_ACTIVE")
    private boolean isActive;
  
    @NotNull(message = "Le rôle d'un compte ne peut être nul ou vide")
    @Column(name="_ROLE")
    @Enumerated(EnumType.STRING)
    private _Role _role;
    
    public Account() {
       
    }

    @Override
    protected Object clone() throws CloneNotSupportedException{
        super.clone(); 
        Account account = new Account();
        account.setAccountId(accountId);
        account.setActive(isActive);
        account.setPassword(password);
        account.setUsername(username);
        account.set_role(_role);
        return account;
    }
}
