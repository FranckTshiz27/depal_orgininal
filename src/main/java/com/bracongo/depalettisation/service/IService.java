/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;
import com.bracongo.depalettisation.entities.Center;
import com.bracongo.depalettisation.entities.ServiceEntity;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
public interface IService{
    
    public ServiceEntity saveOrUpdate(ServiceEntity service);

    public int delete(Long id);
    
    public ServiceEntity getServiceById(Long id);
   
    public List<ServiceEntity> getServicesByCenterId(long centerId);
}
