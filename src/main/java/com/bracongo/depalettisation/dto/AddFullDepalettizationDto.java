/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dto;

import com.bracongo.depalettisation.entities.Agent;
import com.bracongo.depalettisation.entities.Depalettization;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservation;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservationDetail;
import com.bracongo.depalettisation.entities.FullDepalettization;
import com.bracongo.depalettisation.entities.FullDepalettizationDetail;
import com.bracongo.depalettisation.enumerations.Shift;
import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author F.TSHIZUBU
 */
@Data
public class AddFullDepalettizationDto {
    
    Depalettization depalettization;
    FullDepalettization fullDepalettization;
    EmptyDepalettizationObservation emptyDepalettizationObservation;
    ArrayList<FullDepalettizationDetail> fullDepalettizationDetails;
    ArrayList<EmptyDepalettizationObservationDetail> emptyDepalettizationObservationDetails;
    Agent agent;
    Shift shift;
    
}
