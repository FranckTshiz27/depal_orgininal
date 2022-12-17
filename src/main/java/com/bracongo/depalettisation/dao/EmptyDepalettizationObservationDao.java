/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.EmptyDepalettizationObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface EmptyDepalettizationObservationDao extends JpaRepository<EmptyDepalettizationObservation, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<EmptyDepalettizationObservation> findEmptyDepalettizationObservationByEmptyDepalettizationObservationId(long id);

    int deleteEmptyDepalettizationObservationByEmptyDepalettizationObservationId(long id);

    @Query("select  e from EmptyDepalettizationObservation e")
    List<EmptyDepalettizationObservation> getEmptyDepalettizationObservations();
 
}
