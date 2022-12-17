/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.FullDepalettizationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author f.tshizubu
 */
@Repository
public interface FullDepalettizationDetailDao extends JpaRepository<FullDepalettizationDetail, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<FullDepalettizationDetail> findFullDepalettizationDetailByFullDepalettizationDetailId(long id);

    int deleteFullDepalettizationByFullDepalettizationDetailId(long id);

    @Query("select  fd from FullDepalettizationDetail fd where fd.fullDepalettization.fullDepalettizationId =:fullDepalettizationId order by fd.product.game.name asc , fd.product.formatProduct.denomination asc")
    Page<FullDepalettizationDetail> getFullDepalettizationDetails(Pageable page,@Param("fullDepalettizationId") long fullDepalettizationId);
 
    
    @Query("select  fd from FullDepalettizationDetail fd where fd.fullDepalettization.fullDepalettizationId =:fullDepalettizationId order by fd.product.game.category, fd.product.game.name asc , fd.product.formatProduct.denomination asc")
    List<FullDepalettizationDetail> getFullDepalettizationDetails(@Param("fullDepalettizationId") long fullDepalettizationId);
 
}
