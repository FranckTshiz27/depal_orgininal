/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.EmptyDepalettizationObservationDetailDao;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservationDetail;
import com.bracongo.depalettisation.service.IEmptyDepalettizationObservationDetail;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class EmptyDepalettizationDetailObservationService implements IEmptyDepalettizationObservationDetail {

    @Autowired
    private final EmptyDepalettizationObservationDetailDao depalettizationDao;

    @Override
    public int delete(long id) {

        Optional<EmptyDepalettizationObservationDetail> deletingDepalettization = depalettizationDao.findEmptyDepalettizationObservationDetailByEmptyDepalettizationObservationDetailId(id);

        if (deletingDepalettization != null) {
            depalettizationDao.deleteEmptyDepalettizationByEmptyDepalettizationObservationDetailId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public EmptyDepalettizationObservationDetail getEmptyDepalettizationDetailById(long id) {
        return depalettizationDao.findEmptyDepalettizationObservationDetailByEmptyDepalettizationObservationDetailId(id).
                orElseThrow(() -> new CustomNotFoundException("Le détail depalettization vide dont le id " + id + " est introuvable"));
    }


    public EmptyDepalettizationObservationDetail save(EmptyDepalettizationObservationDetail depalettization) {
       
        try {
            return depalettizationDao.save(depalettization);
            
        } catch (Exception e) {
        }

        return null;
    }

    public EmptyDepalettizationObservationDetail updateDepalettization(EmptyDepalettizationObservationDetail depalettization ,long depalettizationId) {

        Optional<EmptyDepalettizationObservationDetail> newDepalettization = depalettizationDao.findEmptyDepalettizationObservationDetailByEmptyDepalettizationObservationDetailId(depalettizationId);

        if (newDepalettization != null) {
           return  depalettizationDao.save(depalettization);
        }
       
        return null;
    }

     @Override
    public Page<EmptyDepalettizationObservationDetail> getEmptyDepalettizationDetails(Pageable page, long depalettizationId) {
        return depalettizationDao.getEmptyDepalettizationObservationDetails(page, depalettizationId);
    }
    
    public List<EmptyDepalettizationObservationDetail> getEmptyDepalettizationDetails(long depalettizationId) {
        return depalettizationDao.getEmptyDepalettizationObservationDetails(depalettizationId);
    }

}
