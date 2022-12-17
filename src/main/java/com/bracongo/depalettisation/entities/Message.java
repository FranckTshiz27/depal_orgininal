/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.entities;

import com.bracongo.depalettisation.enumerations._Role;
import lombok.Data;
import org.apache.tomcat.util.json.Token;

/**
 *
 * @author F.TSHIZUBU
 */
@Data
public class Message {
    private boolean status;
    private String message;
    private long accountId;
    private Agent agent;
    private Affectation currentAffectation;
    private _Role role;
    private Token token;
    
}
