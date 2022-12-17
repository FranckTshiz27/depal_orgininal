/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.FullDepalettization;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
public interface IFullDepalettization {

    public FullDepalettization save(FullDepalettization depalettization);

    public int delete(long id);

    public FullDepalettization getFullDepalettizationById(long id);

    public List<FullDepalettization> getFullDepalettizations();
}
