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
import com.bracongo.depalettisation.dao.FullDepalettizationDao;
import com.bracongo.depalettisation.entities.FullDepalettization;
import com.bracongo.depalettisation.service.IFullDepalettization;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class FullDepalettizationService implements IFullDepalettization {

    @Autowired
    private final FullDepalettizationDao depalettizationDao;

    @Override
    public int delete(long id) {

        Optional<FullDepalettization> deletingDepalettization = depalettizationDao.findFullDepalettizationByFullDepalettizationId(id);

        if (deletingDepalettization != null) {
            depalettizationDao.deleteFullDepalettizationByFullDepalettizationId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public FullDepalettization getFullDepalettizationById(long id) {
        return depalettizationDao.findFullDepalettizationByFullDepalettizationId(id).
                orElseThrow(() -> new CustomNotFoundException("La depalettization pleine dont le id " + id + " est introuvable"));
    }

  @Override
    public FullDepalettization save(FullDepalettization depalettization) {
       
        try {
            return depalettizationDao.save(depalettization);
            
        } catch (Exception e) {
        }

        return depalettization;
    }

    public FullDepalettization updateDepalettization(FullDepalettization depalettization ,long depalettizationId) {

        Optional<FullDepalettization> newDepalettization = depalettizationDao.findFullDepalettizationByFullDepalettizationId(depalettizationId);

        if (newDepalettization != null) {
           return  depalettizationDao.save(depalettization);
        }
       
        return null;
    }

   

    @Override
    public List<FullDepalettization> getFullDepalettizations() {
        return depalettizationDao.getFullDepalettizations();
    }

}
