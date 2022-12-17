/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.EmptyDepalettization;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservation;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
public interface IEmptyDepalettizationObservation {

    public EmptyDepalettizationObservation save(EmptyDepalettizationObservation depalettization);

    public int delete(long id);

    public EmptyDepalettizationObservation getEmptyDepalettizationById(long id);

    public List<EmptyDepalettizationObservation> getEmptyDepalettizations();
}
