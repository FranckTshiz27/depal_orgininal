/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import com.bracongo.depalettisation.entities.EmptyDepalettizationDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface EmptyDepalettizationDetailDao extends JpaRepository<EmptyDepalettizationDetail, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<EmptyDepalettizationDetail> findEmptyDepalettizationDetailByEmptyDepalettizationDetailId(long id);

    int deleteEmptyDepalettizationByEmptyDepalettizationDetailId(long id);

    @Query("select  ed from EmptyDepalettizationDetail ed where ed.emptyDepalettization.emptyDepalettizationId =:emptyDepalettizationId order by ed.packaging.container.name asc , ed.packaging.format.denomination asc ")
    Page<EmptyDepalettizationDetail> getEmptyDepalettizationDetails(Pageable page,@Param("emptyDepalettizationId") long emptyDepalettizationId);
    
      @Query("select  em from EmptyDepalettizationDetail em where em.emptyDepalettization.emptyDepalettizationId =:emptyDepalettizationId  order by em.packaging.container.category asc,em.packaging.container.name asc , em.packaging.format.denomination asc ")
    List<EmptyDepalettizationDetail> getEmptyDepalettizationDetails(@Param("emptyDepalettizationId") long emptyDepalettizationId);
}
