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
import com.bracongo.depalettisation.dao.EmptyDepalettizationDao;
import com.bracongo.depalettisation.entities.EmptyDepalettization;
import com.bracongo.depalettisation.service.IEmptyDepalettization;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class EmptyDepalettizationService implements IEmptyDepalettization {

    @Autowired
    private final EmptyDepalettizationDao depalettizationDao;

    @Override
    public int delete(long id) {

        Optional<EmptyDepalettization> deletingDepalettization = depalettizationDao.findEmptyDepalettizationByEmptyDepalettizationId(id);

        if (deletingDepalettization != null) {
            depalettizationDao.deleteEmptyDepalettizationByEmptyDepalettizationId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public EmptyDepalettization getEmptyDepalettizationById(long id) {
        return depalettizationDao.findEmptyDepalettizationByEmptyDepalettizationId(id).
                orElseThrow(() -> new CustomNotFoundException("La depalettization  vide le id " + id + " est introuvable"));
    }

  @Override
    public EmptyDepalettization save(EmptyDepalettization depalettization) {
       
        try {
            return depalettizationDao.save(depalettization);
            
        } catch (Exception e) {
        }

        return depalettization;
    }

    public EmptyDepalettization updateDepalettization(EmptyDepalettization depalettization ,long depalettizationId) {

        Optional<EmptyDepalettization> newDepalettization = depalettizationDao.findEmptyDepalettizationByEmptyDepalettizationId(depalettizationId);

        if (newDepalettization != null) {
           return  depalettizationDao.save(depalettization);
        }
       
        return null;
    }

   

   
    public List<EmptyDepalettization> getEmptyDepalettizations() {
        return depalettizationDao.getEmptyDepalettizations();
    }

}
