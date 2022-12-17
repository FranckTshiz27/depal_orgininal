/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.entities.DriverAssignment;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.dao.DriverAssignmentDao;
import com.bracongo.depalettisation.service.IDriverAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class DriverAssignmentService implements IDriverAssignment{

    @Autowired
    private DriverAssignmentDao driverAssignmentDao;
    
    @Override
    public int delete(Long id){
        
        Optional<DriverAssignment> deletingDriverAssignment = driverAssignmentDao.findDriverAssignmentByDriverAssignmentId(id);
        
        if (deletingDriverAssignment!=null) {
            driverAssignmentDao.deleteDriverAssignmentByDriverAssignmentId(id);
            return 1;
        }
            
        return -1;
        
    }

    @Override
    public DriverAssignment getDriverAssignmentById(Long id) {
        return  driverAssignmentDao.findDriverAssignmentByDriverAssignmentId(id).
                orElseThrow(()->new CustomNotFoundException("L'affection dont le id "+id+" est introuvable"));
    }
    
    public DriverAssignment getCurrentDriverAssignmentById(Long id) {
        return  driverAssignmentDao.getCurrentDriverAssignmentByDriverAssignmentId(id);
    }
    
    @Override
    public List<DriverAssignment> getDriverAssignments() { 
       return driverAssignmentDao.findAll();
    }

    @Override
    public DriverAssignment save(DriverAssignment driverAssignment) {
        try {
           driverAssignmentDao.desactiveAllDriverAssignmentsByCircuitAndTruck(driverAssignment.getCircuit().getCircuitId(),driverAssignment.getTruck().getTruckId());
           driverAssignmentDao.desactiveAllDriverAssignmentsByTruck(driverAssignment.getTruck().getTruckId());
           driverAssignmentDao.desactiveAllDriverAssignmentsByDriver(driverAssignment.getDriver().getDriverId());
            
        } catch (Exception e) {
        }
        
        driverAssignment.setCurrent(true);
        return driverAssignmentDao.save(driverAssignment);
    }
    
    
    public DriverAssignment updateDriverAssignment(DriverAssignment driverAssignment, long DriverAssignmentId) {
        
        DriverAssignment editingDriverAssignment = driverAssignmentDao.getById(DriverAssignmentId);
        if (editingDriverAssignment!=null) {
            driverAssignmentDao.desactiveAllDriverAssignmentsByCircuitAndTruck(driverAssignment.getCircuit().getCircuitId(),driverAssignment.getTruck().getTruckId());
            driverAssignmentDao.desactiveAllDriverAssignmentsByTruck(driverAssignment.getTruck().getTruckId());
            return driverAssignmentDao.save(editingDriverAssignment);
        }
        
        return null;
    }
    
    
     public DriverAssignment updateDriverAssignmentState(DriverAssignment driverAssignment, boolean state ) {
        
        DriverAssignment editingDriverAssignment = driverAssignmentDao.getById(driverAssignment.getDriverAssignmentId());
        if (editingDriverAssignment!=null) {
            if (state) 
            {
                driverAssignmentDao.desactiveAllDriverAssignmentsByCircuitAndTruck(driverAssignment.getCircuit().getCircuitId(),driverAssignment.getTruck().getTruckId());
                driverAssignmentDao.desactiveAllDriverAssignmentsByDriver(driverAssignment.getDriver().getDriverId());
            }
            
            editingDriverAssignment.setCurrent(state);
            return driverAssignmentDao.save(editingDriverAssignment);
        }
        
        return null;
    }
     public Page<DriverAssignment> getCurrentDriverAssignments(Pageable page) { 
       return driverAssignmentDao.getCurrentDriverAssignments(page);
    }
     public Page<DriverAssignment> getNotCurrentDriverAssignments(Pageable page) { 
       return driverAssignmentDao.getNotCurrentDriverAssignments(page);
    }
    public Page<DriverAssignment> getCurrentDriverAssignmentsByName(String name,Pageable page) { 
       return driverAssignmentDao.getCurrentDriverAssignmentsByName(name,page);
    }
    
     public Page<DriverAssignment> getNotCurrentDriverAssignmentsByName(String name,Pageable page) { 
       return driverAssignmentDao.getNotCurrentDriverAssignmentsByName(name,page);
    }
     
     public Page<DriverAssignment> getCurrentDriverAssignmentsByCenterId(long centerId,Pageable page) { 
       return driverAssignmentDao.getCurrentDriverAssignmentsByCenterId(centerId, page);
    }
    
     public Page<DriverAssignment> getNotCurrentDriverAssignmentsByCenterId(long centerId,Pageable page) { 
       return driverAssignmentDao.getNotCurrentDriverAssignmentsByCenterId(centerId, page);
    }
     
     public Page<DriverAssignment> getCurrentDriverAssignmentsByCenterIdByName(String name,long centerId,Pageable page) { 
       return driverAssignmentDao.getCurrentDriverAssignmentsByCenterIdAndDriverName(centerId,name,page);
    }
    
    public Page<DriverAssignment> getNoCurrentDriverAssignmentsByCenterIdByName(String name,long centerId,Pageable page) { 
       return driverAssignmentDao.getNotCurrentDriverAssignmentsByCenterIdAndDriverName(centerId,name,page);
    }
    
    public DriverAssignment geCurrentDriverAssignmentByTruckUb(String ub) { 
       return driverAssignmentDao.getCurrentDriverAssignmentByTruckUb(ub);
    }
}
