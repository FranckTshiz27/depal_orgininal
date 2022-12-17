/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;
import com.bracongo.depalettisation.entities.Affectation;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author f.tshizubu
 */
public interface IAffectation{
    
    public Affectation save(Affectation center);

    public int delete(Long id);
    
    public Affectation getAffectationById(Long id);
    
    public List<Affectation> getAffectations();
    
    public Affectation getAffectationByAccountId(long accountId);
}
