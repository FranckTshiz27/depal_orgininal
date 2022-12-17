/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.FormatDao;
import com.bracongo.depalettisation.entities.Format;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.service.IFormat;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author J.LUTUNDULA
 */
@Service
@Transactional
@AllArgsConstructor
public class FormatService implements IFormat{

    @Autowired
    private final FormatDao formatDao;

    @Override
    public int delete(int id) {

        Optional<Format> deletingFormat = formatDao.findFormatByFormatId(id);

        if (deletingFormat != null) {
            formatDao.deleteById(id);
            return 1;
        }

        return -1;
    }

    @Override
    public Format getFormatById(int id) {
        return formatDao.findFormatByFormatId(id).
                orElseThrow(() -> new CustomNotFoundException("Le format dont l'id est : " + id + " est introuvable"));
    }

    @Override
    public Format saveOrUpdate(Format format) {
        return formatDao.save(format);
    }
  public Format updateFormat(Format format) {

        Optional<Format> newFormat = formatDao.findFormatByFormatId(format.getFormatId());
        if (newFormat != null) {
            return formatDao.save(format);
        }

        return null;
    }
    @Override
    public List<Format> getFormats() {
        return formatDao.getFormatsOrderByDenomination();
    }
}
