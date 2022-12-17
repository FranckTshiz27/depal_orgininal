/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.EmptyDepalettizationDetailDao;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import com.bracongo.depalettisation.service.IEmptyDepalettizationDetail;
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
public class EmptyDepalettizationDetailService implements IEmptyDepalettizationDetail {

    @Autowired
    private final EmptyDepalettizationDetailDao depalettizationDao;

    @Override
    public int delete(long id) {

        Optional<EmptyDepalettizationDetail> deletingDepalettization = depalettizationDao.findEmptyDepalettizationDetailByEmptyDepalettizationDetailId(id);

        if (deletingDepalettization != null) {
            depalettizationDao.deleteEmptyDepalettizationByEmptyDepalettizationDetailId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public EmptyDepalettizationDetail getEmptyDepalettizationDetailById(long id) {
        return depalettizationDao.findEmptyDepalettizationDetailByEmptyDepalettizationDetailId(id).
                orElseThrow(() -> new CustomNotFoundException("Le détail depalettization vide dont le id " + id + " est introuvable"));
    }


    public EmptyDepalettizationDetail save(EmptyDepalettizationDetail depalettization) {
       
        try {
            return depalettizationDao.save(depalettization);
            
        } catch (Exception e) {
        }

        return null;
    }
    
    

    public EmptyDepalettizationDetail updateDepalettization(EmptyDepalettizationDetail depalettization ,long depalettizationId) {

        Optional<EmptyDepalettizationDetail> newDepalettization = depalettizationDao.findEmptyDepalettizationDetailByEmptyDepalettizationDetailId(depalettizationId);

        if (newDepalettization != null) {
           return  depalettizationDao.save(depalettization);
        }
       
        return null;
    }

     @Override
    public Page<EmptyDepalettizationDetail> getEmptyDepalettizationDetails(Pageable page, long depalettizationId) {
        return depalettizationDao.getEmptyDepalettizationDetails(page, depalettizationId);
    }
    
    public List<EmptyDepalettizationDetail> getEmptyDepalettizationDetails(long depalettizationId) {
        return depalettizationDao.getEmptyDepalettizationDetails(depalettizationId);
    }
    
    public List<EmptyDepalettizationDetail> getEmptyDepalettizationDetails() {
        return depalettizationDao.findAll();
    }

}
