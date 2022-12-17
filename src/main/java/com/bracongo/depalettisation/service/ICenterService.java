/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;
import com.bracongo.depalettisation.entities.Center;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author f.tshizubu
 */
public interface ICenterService{
    
    public Center saveOrUpdate(Center center);

    public int delete(Long id);
    
    public Center getCenterById(Long id);
    
    public List<Center> getCenters();
}
