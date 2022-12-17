/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.Format;
import java.util.List;

/**
 *
 * @author J.LUTUNDULA
 */
public interface IFormat {
    public Format saveOrUpdate(Format format);

    public int delete(int id);
    
    public Format getFormatById(int id);
    
    public List<Format> getFormats();
}
