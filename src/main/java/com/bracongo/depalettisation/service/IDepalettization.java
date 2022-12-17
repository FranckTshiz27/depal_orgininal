/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.Depalettization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
public interface IDepalettization {

    

    public int delete(long id);

    public Depalettization getDepalettizationById(long id);

    public Page<Depalettization> getDepalettizations(Pageable page,String request);
    
    public int visitedDepalettization(long id);
}
