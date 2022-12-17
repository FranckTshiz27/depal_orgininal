/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dto;

import com.bracongo.depalettisation.entities.Agent;
import com.bracongo.depalettisation.entities.Depalettization;
import com.bracongo.depalettisation.entities.EmptyDepalettization;
import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import com.bracongo.depalettisation.enumerations.Shift;
import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author F.TSHIZUBU
 */
@Data
public class AddEmptyDepalettizationDto {
    
    Depalettization depalettization;
    EmptyDepalettization emptyDepalettization;
    ArrayList<EmptyDepalettizationDetail> emptyDepalettizationDetails;
    Agent agent;
    Shift shift;
    
}
