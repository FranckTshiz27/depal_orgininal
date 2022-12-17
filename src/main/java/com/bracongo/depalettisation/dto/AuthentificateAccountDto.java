/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author F.TSHIZUBU
 */
@Data
public class AuthentificateAccountDto {
    
    @NotNull(message = "Le nom d'utilisateur ne peut être nul")
    private String username;
    @NotNull(message = "Le mot de passe ne peut être nul")
    private String password;
    
}
