/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.Packaging;
import java.util.List;

/**
 *
 * @author J.LUTUNDULA
 */
public interface IPackaging {
    public Packaging saveOrUpdate(Packaging packaging);

    public int delete(int id);
    
    public Packaging getPackagingById(int id);
    
    public List<Packaging> getPackagings();
}
