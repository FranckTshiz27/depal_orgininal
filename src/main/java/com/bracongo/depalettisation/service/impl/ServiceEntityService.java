/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.dao.ServiceDao;
import com.bracongo.depalettisation.entities.ServiceEntity;
import com.bracongo.depalettisation.service.IService;
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
public class ServiceEntityService implements IService {

    @Autowired
    private ServiceDao servicedao;

    @Override
    public int delete(Long id){
        
        Optional<ServiceEntity> deletingService = servicedao.findServiceEntityByServiceId(id);
        
        if (deletingService!=null) {
            servicedao.deleteById(id);
            return 1;
        }
            
        return -1;
    }

    @Override
    public ServiceEntity getServiceById(Long id) {
        return  servicedao.findServiceEntityByServiceId(id).
                orElseThrow(()->new CustomNotFoundException("Le service dont l'id est : "+id+" est introuvable"));
    }

    @Override
    public ServiceEntity saveOrUpdate(ServiceEntity service) {
        return servicedao.save(service);
    }

    @Override
    public List<ServiceEntity> getServicesByCenterId(long centerId) {
        return servicedao.getServicesByCenterId(centerId);
    }

    public List<ServiceEntity> getServices() {
        return servicedao.findAll();
    }
    
}
