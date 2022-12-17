/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Format;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author J.LUTUNDULA
 */
@Repository
public interface FormatDao  extends JpaRepository<Format, Integer> {

    Optional<Format> findFormatByFormatId(int id);

    @Query("select f from Format f order by f.denomination asc")
    public List<Format> getFormatsOrderByDenomination();
}
