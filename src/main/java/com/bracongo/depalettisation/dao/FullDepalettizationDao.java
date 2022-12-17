/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.FullDepalettization;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface FullDepalettizationDao extends JpaRepository<FullDepalettization, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<FullDepalettization> findFullDepalettizationByFullDepalettizationId(long id);

    int deleteFullDepalettizationByFullDepalettizationId(long id);

    @Query("select  f from FullDepalettization f")
    List<FullDepalettization> getFullDepalettizations();
 
}
