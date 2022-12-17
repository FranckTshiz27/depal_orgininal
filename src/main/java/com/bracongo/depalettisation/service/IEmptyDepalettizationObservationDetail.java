/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import com.bracongo.depalettisation.entities.EmptyDepalettizationObservationDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
public interface IEmptyDepalettizationObservationDetail {

    public EmptyDepalettizationObservationDetail save(EmptyDepalettizationObservationDetail depalettization);

    public int delete(long id);

    public EmptyDepalettizationObservationDetail getEmptyDepalettizationDetailById(long id);

    public Page<EmptyDepalettizationObservationDetail> getEmptyDepalettizationDetails(Pageable page, long depalettizationId);
}
