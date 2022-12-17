/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;
import com.bracongo.depalettisation.entities.Perform;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
public interface IPerform{
    
   public Perform save(Perform product);

    public int delete(long id);

    public Perform getPerformById(long id);

    public List<Perform> getPerforms();
}
