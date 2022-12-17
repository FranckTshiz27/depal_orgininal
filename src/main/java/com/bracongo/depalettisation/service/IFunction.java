/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;

import com.bracongo.depalettisation.entities.FunctionEntity;
import com.bracongo.depalettisation.entities.ServiceEntity;
import java.util.List;

/**
 *
 * @author J.LUTUNDULA
 */
public interface IFunction {
    public FunctionEntity saveOrUpdate(FunctionEntity function);

    public int delete(Long id);
    
    public FunctionEntity getFunctionById(Long id);
   
    public List<FunctionEntity> getFunctionsByCenterId(long centerId);
}
