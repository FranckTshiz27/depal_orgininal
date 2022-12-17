/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dto;

import lombok.Data;

/**
 *
 * @author J.LUTUNDULA
 */
@Data
public class ChangePasswordDto {
    private String newPassword;
    private String repeatNewPassword;
}
