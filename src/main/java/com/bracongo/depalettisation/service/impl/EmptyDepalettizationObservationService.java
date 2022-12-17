/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.exception.CustomNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.dao.EmptyDepalettizationObservationDao;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservation;
import com.bracongo.depalettisation.service.IEmptyDepalettizationObservation;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class EmptyDepalettizationObservationService implements IEmptyDepalettizationObservation {

    @Autowired
    private final EmptyDepalettizationObservationDao depalettizationDao;

    @Override
    public int delete(long id) {

        Optional<EmptyDepalettizationObservation> deletingDepalettization = depalettizationDao.findEmptyDepalettizationObservationByEmptyDepalettizationObservationId(id);

        if (deletingDepalettization != null) {
            depalettizationDao.deleteEmptyDepalettizationObservationByEmptyDepalettizationObservationId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public EmptyDepalettizationObservation getEmptyDepalettizationById(long id) {
        return depalettizationDao.findEmptyDepalettizationObservationByEmptyDepalettizationObservationId(id).
                orElseThrow(() -> new CustomNotFoundException("La depalettization  vide le id " + id + " est introuvable"));
    }

  @Override
    public EmptyDepalettizationObservation save(EmptyDepalettizationObservation depalettization) {
       
        try {
            return depalettizationDao.save(depalettization);
            
        } catch (Exception e) {
        }

        return depalettization;
    }

    public EmptyDepalettizationObservation updateDepalettization(EmptyDepalettizationObservation depalettization ,long depalettizationId) {

        Optional<EmptyDepalettizationObservation> newDepalettization = depalettizationDao.findEmptyDepalettizationObservationByEmptyDepalettizationObservationId(depalettizationId);

        if (newDepalettization != null) {
           return  depalettizationDao.save(depalettization);
        }
       
        return null;
    }

   

   
    public List<EmptyDepalettizationObservation> getEmptyDepalettizations() {
        return depalettizationDao.getEmptyDepalettizationObservations();
    }

   

}
