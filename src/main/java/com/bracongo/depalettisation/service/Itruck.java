/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.Truck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
public interface Itruck {
    public Truck saveOrUpdate(Truck center);

    public int delete(Long id);
    
    public Truck getTruckById(Long id);
    
    public Page<Truck> getTrucks(Pageable page);
}
