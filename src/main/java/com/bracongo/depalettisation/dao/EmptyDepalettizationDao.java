/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.EmptyDepalettization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface EmptyDepalettizationDao extends JpaRepository<EmptyDepalettization, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<EmptyDepalettization> findEmptyDepalettizationByEmptyDepalettizationId(long id);

    int deleteEmptyDepalettizationByEmptyDepalettizationId(long id);

    @Query("select  e from EmptyDepalettization e")
    List<EmptyDepalettization> getEmptyDepalettizations();
 
}
