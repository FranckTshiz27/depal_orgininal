/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.dao.ContainerDao;
import com.bracongo.depalettisation.service.IContainer;
import com.bracongo.depalettisation.entities.Container;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class ContainerService implements IContainer {

    @Autowired
    private final ContainerDao containerDao;

    @Override
    public int delete(int id) {

        Optional<Container> deletingContainer = containerDao.findContainerByContainerId(id);

        if (deletingContainer != null) {
            containerDao.deleteContainerByContainerId(id);
            return 1;
        }

        return -1;

    }

    @Override
    public Container getContainerById(int id) {
        return containerDao.findContainerByContainerId(id).
                orElseThrow(() -> new CustomNotFoundException("L'emballage dont le id " + id + " est introuvable"));
    }

    @Override
    public List<Container> getContainers() {
        return containerDao.getContainersOrderByNameAsc();
    }

    @Override
    public Container saveOrUpdate(Container container) {
        return containerDao.save(container);
    }

}
