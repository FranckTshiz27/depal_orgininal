/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.EmptyDepalettization;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
public interface IEmptyDepalettization {

    public EmptyDepalettization save(EmptyDepalettization depalettization);

    public int delete(long id);

    public EmptyDepalettization getEmptyDepalettizationById(long id);

    public List<EmptyDepalettization> getEmptyDepalettizations();
}
