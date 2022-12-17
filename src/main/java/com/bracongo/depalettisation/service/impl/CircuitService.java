/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.dao.CircuitDao;
import com.bracongo.depalettisation.entities.Circuit;
import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.service.ICiruit;
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
public class CircuitService implements ICiruit {

    @Autowired
    private final CircuitDao circuitDao;

    @Override
    public int delete(Long id) {

        Optional<Circuit> deletingCircuit = circuitDao.findCircuitByCircuitId(id);

        if (deletingCircuit != null) {
            circuitDao.deleteById(id);
            return 1;
        }

        return -1;

    }

    @Override
    public Circuit getCircuitById(Long id) {
        return circuitDao.findCircuitByCircuitId(id).
                orElseThrow(() -> new CustomNotFoundException("Le circuit dont l'id est : " + id + " est introuvable"));
    }

    @Override
    public Circuit saveOrUpdate(Circuit circuit) {
        return circuitDao.save(circuit);
    }

    public Page<Circuit> getCircuitsByCenterId(long centerId,Pageable page) {
        return circuitDao.getCircuitsByCenterId(centerId,page);
    }

    @Override
    public List<Circuit> getCircuits() {
        return circuitDao.findAll();
    }
}
