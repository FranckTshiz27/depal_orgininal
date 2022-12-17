/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.exception.CustomNotFoundException;
import com.bracongo.depalettisation.dao.FunctionDao;
import com.bracongo.depalettisation.entities.FunctionEntity;
import com.bracongo.depalettisation.service.IFunction;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;

/**
 *
 * @author J.LUTUNDULA
 */
@Service
@Transactional
@AllArgsConstructor
public class FunctionEntityService implements IFunction {

    @Autowired
    private final FunctionDao functiondao;

    @Override
    public int delete(Long id) {

        Optional<FunctionEntity> deletingFunction = functiondao.findFunctionEntityByFunctionId(id);

        if (deletingFunction != null) {
            functiondao.deleteById(id);
            return 1;
        }

        return -1;

    }

    @Override
    public FunctionEntity getFunctionById(Long id) {
        return functiondao.findFunctionEntityByFunctionId(id).
                orElseThrow(() -> new CustomNotFoundException("La fonction dont l'id est : " + id + " est introuvable"));
    }

    @Override
    public FunctionEntity saveOrUpdate(FunctionEntity function) {
        return functiondao.save(function);
    }

    @Override
    public List<FunctionEntity> getFunctionsByCenterId(long centerId) {
        return functiondao.getFunctionsByCenterId(centerId);

    }

    public List<FunctionEntity> getFunctions() {
        return functiondao.findAll();
    }
}
