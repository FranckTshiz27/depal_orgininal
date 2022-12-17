/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.EmptyDepalettizationObservationDetail;
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
public interface EmptyDepalettizationObservationDetailDao extends JpaRepository<EmptyDepalettizationObservationDetail, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<EmptyDepalettizationObservationDetail> findEmptyDepalettizationObservationDetailByEmptyDepalettizationObservationDetailId(long id);

    int deleteEmptyDepalettizationByEmptyDepalettizationObservationDetailId(long id);

    @Query("select  ed from EmptyDepalettizationObservationDetail ed where ed.emptyDepalettizationObservation.emptyDepalettizationObservationId =:emptyDepalettizationId order by ed.packaging.container.name asc , ed.packaging.format.denomination asc ")
    Page<EmptyDepalettizationObservationDetail> getEmptyDepalettizationObservationDetails(Pageable page,@Param("emptyDepalettizationId") long emptyDepalettizationId);
    
      @Query("select  em from EmptyDepalettizationObservationDetail em where em.emptyDepalettizationObservation.emptyDepalettizationObservationId =:emptyDepalettizationId and (numberOfLockers!=0 or numberOfBottles!=0 or numberOfBrokenBottles!=0 ) order by em.packaging.container.category asc,em.packaging.container.name asc , em.packaging.format.denomination asc ")
    List<EmptyDepalettizationObservationDetail> getEmptyDepalettizationObservationDetails(@Param("emptyDepalettizationId") long emptyDepalettizationId);
}
