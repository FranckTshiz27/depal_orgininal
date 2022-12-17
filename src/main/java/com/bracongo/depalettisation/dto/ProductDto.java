/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dto;

import com.bracongo.depalettisation.entities.Format;
import com.bracongo.depalettisation.entities.Game;
import lombok.Data;

/**
 *
 * @author J.LUTUNDULA
 */
@Data
public class ProductDto {    
    private long productId;
    Format formatProduct;
    Game game ;
    private String code;
    private String abbreviation;
    private String secondCode;
}
