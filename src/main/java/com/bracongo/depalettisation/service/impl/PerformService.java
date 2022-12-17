/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.PerformDao;
import com.bracongo.depalettisation.entities.Perform;
import com.bracongo.depalettisation.enumerations._Role;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.service.IPerform;
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
public class PerformService implements IPerform {

    @Autowired
    private PerformDao performDao;

    @Override
    public int delete(long id){
        
        Optional<Perform> deletingPerform = performDao.findPerformByPerformId(id);
        
        if (deletingPerform!=null) {
            performDao.deleteById(id);
            return 1;
        }
            
        return -1;
    }

    @Override
    public Perform getPerformById(long id) {
        return  performDao.findPerformByPerformId(id).
                orElseThrow(()->new CustomNotFoundException("L'affectation dont l'id est : "+id+" est introuvable"));
    }

    @Override
    public Perform save(Perform perform) {
        return performDao.save(perform);
    }

   @Override
    public List<Perform> getPerforms() {
        return performDao.getPerforms();
    }



   public List<Perform> getPerformByFullDePalettization(long id) {
        return performDao.getPerformByFullDepalettization(id);
    } 

 public Perform getPerformByDepelettization(long id, _Role role) {
        return performDao.getPerformByDepalettizationAndAgentRole(id,role);
    }    
}
