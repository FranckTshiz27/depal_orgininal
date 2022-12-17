/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.dao.CenterDao;
import com.bracongo.depalettisation.service.ICenterService;
import com.bracongo.depalettisation.entities.Center;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class CenterService implements ICenterService {

    @Autowired
    private CenterDao centerDao;
    
    @Override
    public int delete(Long id){
        
        Optional<Center> deletingCenter = centerDao.findCenterByCenterId(id);
        
        if (deletingCenter!=null) {
            centerDao.deleteCenterByCenterId(id);
            return 1;
        }
            
        return -1;
        
    }

    @Override
    public Center getCenterById(Long id) {
        return  centerDao.findCenterByCenterId(id).
                orElseThrow(()->new CustomNotFoundException("Le centre dont le id "+id+" est introuvable"));
    }
    
    @Override
    public List<Center> getCenters() { 
       return centerDao.getCentersOrderByNameAsc();
    }

    @Override
    public Center saveOrUpdate(Center center) {
        return centerDao.save(center);
    }
 
}
