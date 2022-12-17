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
import com.bracongo.depalettisation.dao.FullDepalettizationDetailDao;
import com.bracongo.depalettisation.entities.FullDepalettizationDetail;
import com.bracongo.depalettisation.service.IFullDepalettizationDetail;
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
public class FullDepalettizationDetailService implements IFullDepalettizationDetail {

    @Autowired
    private final FullDepalettizationDetailDao depalettizationDao;

    @Override
    public int delete(long id) {

        Optional<FullDepalettizationDetail> deletingDepalettization = depalettizationDao.findFullDepalettizationDetailByFullDepalettizationDetailId(id);

        if (deletingDepalettization != null) {
            depalettizationDao.deleteFullDepalettizationByFullDepalettizationDetailId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public FullDepalettizationDetail getFullDepalettizationDetailById(long id) {
        return depalettizationDao.findFullDepalettizationDetailByFullDepalettizationDetailId(id).
                orElseThrow(() -> new CustomNotFoundException("Le détail depalettization pleine dont le id " + id + " est introuvable"));
    }


    public FullDepalettizationDetail save(FullDepalettizationDetail depalettization) {
       
        try {
            return depalettizationDao.save(depalettization);
            
        } catch (Exception e) {
        }

        return null;
    }

    public FullDepalettizationDetail updateDepalettization(FullDepalettizationDetail depalettization ,long depalettizationId) {

        Optional<FullDepalettizationDetail> newDepalettization = depalettizationDao.findFullDepalettizationDetailByFullDepalettizationDetailId(depalettizationId);

        if (newDepalettization != null) {
           return  depalettizationDao.save(depalettization);
        }
       
        return null;
    }

   


    @Override
    public Page<FullDepalettizationDetail> getFullDepalettizationDetails(Pageable page, long depalettizationId) {
        return depalettizationDao.getFullDepalettizationDetails(page, depalettizationId);
    }

    public List<FullDepalettizationDetail> getFullDepalettizationDetails(long depalettizationId) {
        return depalettizationDao.getFullDepalettizationDetails(depalettizationId);
    }

    public List<FullDepalettizationDetail> getFullDepalettizationDetails() {
        return depalettizationDao.findAll();
    }
}
