/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.ServiceEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author F.TSHIZUBU
 */
public interface ServiceDao extends JpaRepository<ServiceEntity, Long>{
     Optional<ServiceEntity> findServiceEntityByServiceId(Long id);
     
     @Query("select s from ServiceEntity s inner join Center c on c.centerId = s.center.centerId "
              + "where c.centerId =:centerId order by s.denomination asc")
      public List<ServiceEntity> getServicesByCenterId(@Param("centerId") long id);
  
}
