/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.Circuit;
import java.util.List;

/**
 *
 * @author J.LUTUNDULA
 */
public interface ICiruit {
    public Circuit saveOrUpdate(Circuit center);

    public int delete(Long id);
    
    public Circuit getCircuitById(Long id);
    
    public List<Circuit> getCircuits();
}
