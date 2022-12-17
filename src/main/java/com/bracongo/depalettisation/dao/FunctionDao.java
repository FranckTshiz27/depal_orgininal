/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.FunctionEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author J.LUTUNDULA
 */
public interface FunctionDao extends JpaRepository<FunctionEntity, Long> {

    Optional<FunctionEntity> findFunctionEntityByFunctionId(Long id);

    @Query("select f from FunctionEntity f inner join ServiceEntity s on s.serviceId = f.service.serviceId "
            + "where s.serviceId =: serviceId order by f.denomination asc")
    public List<FunctionEntity> getFunctionByServiceId(@Param("serviceId") long id);

    @Query("select f from FunctionEntity f inner join ServiceEntity s on s.serviceId = f.service.serviceId inner join Center c on c.centerId = s.center.centerId where c.centerId =:centerId order by f.denomination asc")
    public List<FunctionEntity> getFunctionsByCenterId(@Param("centerId") long centerId);
}
