/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.TruckDao;
import com.bracongo.depalettisation.entities.Truck;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import com.bracongo.depalettisation.service.Itruck;

/**
 *
 * @author J.LUTUNDULA
 */
@Service
@Transactional
@AllArgsConstructor
public class TruckService implements Itruck {

    @Autowired
    private final TruckDao truckDao;

    @Override
    public int delete(Long id) {

        Optional<Truck> deletingTruck = truckDao.findTruckByTruckId(id);

        if (deletingTruck != null) {
            truckDao.deleteById(id);
            return 1;
        }

        return -1;

    }

    @Override
    public Truck getTruckById(Long id) {
        return truckDao.findTruckByTruckId(id).
                orElseThrow(() -> new CustomNotFoundException("Le camion dont l'id est : " + id + " est introuvable"));
    }

    @Override
    public Truck saveOrUpdate(Truck Truck) {
        return truckDao.save(Truck);
    }

    public Truck updateTruck(Truck truck) {

        Optional<Truck> newTruck = truckDao.findTruckByTruckId(truck.getTruckId());
        if (newTruck != null) {
            return truckDao.save(truck);
        }

        return null;
    }

    @Override
    public Page<Truck> getTrucks(Pageable page) {
        return truckDao.getTrucksOrderByUb(page);
    }

    public List<Truck> getUnPagedTrucks() {
        return truckDao.getUnPagedTrucksOrderByUb();
    }
    
    public Page<Truck> getTrucksByUb(String ub,Pageable page) {
        return truckDao.getTrucksByUb(ub,page);
    }

}
