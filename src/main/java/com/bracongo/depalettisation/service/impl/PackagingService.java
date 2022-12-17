/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.PackagingDao;
import com.bracongo.depalettisation.entities.Packaging;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.service.IPackaging;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author J.LUTUNDULA
 */
@Service
@Transactional
@AllArgsConstructor
public class PackagingService implements IPackaging {

    @Autowired
    private final PackagingDao packagingDao;

    @Override
    public int delete(int id) {

        Optional<Packaging> deletingPackaging = packagingDao.findPackagingByPackagingId(id);

        if (deletingPackaging != null) {
            packagingDao.deletePackagingByPackagingId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public Packaging getPackagingById(int id) {
        return packagingDao.findPackagingByPackagingId(id).
                orElseThrow(() -> new CustomNotFoundException("L'emballage dont le id " + id + " est introuvable"));
    }

    @Override
    public List<Packaging> getPackagings() {
        return packagingDao.findAll();
    }

    @Override
    public Packaging saveOrUpdate(Packaging packaging) {
        return packagingDao.save(packaging);
    }

    public Page<Packaging> getPackagings(Pageable page) {
        return packagingDao.getPackagingsOrderByContainerNameAsc(page);
    }

    public Page<Packaging> getPackagingByContainerName(String name, Pageable page) {
        return packagingDao.getPackagingsByContainerName(name, page);
    }
    
     public List<Packaging> getUnPackagings() {
        return packagingDao.getPackagings();
    }
}
