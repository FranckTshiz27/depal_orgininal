/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.entities.Affectation;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.dao.AffectationDao;
import com.bracongo.depalettisation.service.IAffectation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class AffectationService implements IAffectation{

    @Autowired
    private AffectationDao affectationDao;
    
    @Override
    public int delete(Long id){
        
        Optional<Affectation> deletingAffectation = affectationDao.findAffectationByAffectationId(id);
        
        if (deletingAffectation!=null) {
            affectationDao.deleteAffectationByAffectationId(id);
            return 1;
        }
            
        return -1;
        
    }

    @Override
    public Affectation getAffectationById(Long id) {
        return  affectationDao.findAffectationByAffectationId(id).
                orElseThrow(()->new CustomNotFoundException("L'affectation dont le id "+id+" est introuvable"));
    }
    
    public Affectation getCurrentAffectationById(Long id) {
        return  affectationDao.getCurrentAffectationByAffectationId(id);
    }
    @Override
    public List<Affectation> getAffectations() { 
       return affectationDao.findAll();
    }

    @Override
    public Affectation save(Affectation affectation) {
        try {
          
           affectationDao.desactiveAllAffectations(affectation.getAgent().getAgentId());
            
        } catch (Exception e) {
        }
        return affectationDao.save(affectation);
    }
    
    
    public Affectation updateAffectation(Affectation affectation, long AffectationId) {
        
        Affectation editingAffectation = affectationDao.getById(AffectationId);
        if (editingAffectation!=null) {
            affectationDao.desactiveAllAffectations(affectation.getAgent().getAgentId());
            return affectationDao.save(editingAffectation);
        }
        
        return null;
    }
    
     public Page<Affectation> getCurrentAffectations(Pageable page) { 
       return affectationDao.getCurrentAffectations(page);
    }
     public Page<Affectation> getNotCurrentAffectations(Pageable page) { 
       return affectationDao.getNotCurrentAffectations(page);
    }
    public Page<Affectation> getCurrentAffectationsByName(String name,Pageable page) { 
       return affectationDao.getCurrentAffectationsByName(name,page);
    }
    
     public Page<Affectation> getNotCurrentAffectationsByName(String name,Pageable page) { 
       return affectationDao.getNotCurrentAffectationsByName(name,page);
    }
     
     public Page<Affectation> getCurrentAffectationsByCenterId(long centerId,Pageable page) { 
       return affectationDao.getCurrentAffectationsByCenterId(centerId, page);
    }
    
     public Page<Affectation> getNotCurrentAffectationsByCenterId(long centerId,Pageable page) { 
       return affectationDao.getNotCurrentAffectationsByCenterId(centerId, page);
    }
     
     public Page<Affectation> getCurrentAffectationsByCenterIdByName(String name,long centerId,Pageable page) { 
       return affectationDao.getCurrentAffectationsByCenterIdAndAgentName(centerId,name,page);
    }
    
    public Page<Affectation> getNoCurrentAffectationsByCenterIdByName(String name,long centerId,Pageable page) { 
       return affectationDao.getNotCurrentAffectationsByCenterIdAndAgentName(centerId,name,page);
    }
    
    @Override
    public Affectation getAffectationByAccountId(long accountId){
       return affectationDao.getAffectationByAccountId(accountId);
    }
    
      public Affectation getCurrentAffectationByAccountId(long accountId) { 
       return affectationDao.getCurrentAffectationByAccountId(accountId);
    }
}
