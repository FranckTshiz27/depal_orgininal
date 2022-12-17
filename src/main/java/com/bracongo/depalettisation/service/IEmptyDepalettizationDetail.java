/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
public interface IEmptyDepalettizationDetail {

    public EmptyDepalettizationDetail save(EmptyDepalettizationDetail depalettization);

    public int delete(long id);

    public EmptyDepalettizationDetail getEmptyDepalettizationDetailById(long id);

    public Page<EmptyDepalettizationDetail> getEmptyDepalettizationDetails(Pageable page, long depalettizationId);
}
