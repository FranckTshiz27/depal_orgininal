/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dto;

import com.bracongo.depalettisation.entities.Container;
import com.bracongo.depalettisation.entities.Format;
import lombok.Data;

/**
 *
 * @author J.LUTUNDULA
 */
@Data
public class PackagingDto {    
    private int packagingId;
    Format format;
    Container container;
    private String code;
}
