/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dto;

import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservationDetail;
import com.bracongo.depalettisation.entities.FullDepalettizationDetail;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p.tezoadm
 */
@Data
public class ReportDto {
    
    private List<FullDepalettizationDetail> fullDepalettizationDetails;
    private List<EmptyDepalettizationDetail> emptyDepalettizationDetails;
    private List<EmptyDepalettizationObservationDetail> emptyDepalettizationObservationDetails;
    
}
