/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.FullDepalettizationDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author f.tshizubu
 */
public interface IFullDepalettizationDetail {

    public FullDepalettizationDetail save(FullDepalettizationDetail depalettization);

    public int delete(long id);

    public FullDepalettizationDetail getFullDepalettizationDetailById(long id);

    public Page<FullDepalettizationDetail> getFullDepalettizationDetails(Pageable page, long depalettizationId);
}
