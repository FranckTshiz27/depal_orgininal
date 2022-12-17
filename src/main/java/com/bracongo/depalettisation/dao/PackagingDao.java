/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Container;
import com.bracongo.depalettisation.entities.Format;
import com.bracongo.depalettisation.entities.Packaging;
import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author J.LUTUNDULA
 */
public interface PackagingDao extends JpaRepository<Packaging, Long> {

    /**
     *
     * @param id
     * @return
     */
    Optional<Packaging> findPackagingByPackagingId(int id);

    int deletePackagingByPackagingId(int id);

    @Query("select p from Packaging p order by p.container.name asc")
    public Page<Packaging> getPackagingsOrderByContainerNameAsc(Pageable page);

     @Query("select p from Packaging p order by p.container.name asc , p.format.denomination asc")
    public List<Packaging> getPackagings();
    
    @Query("select p from Packaging p where p.container.name LIKE %:name% order by p.container.name asc")
    public Page<Packaging> getPackagingsByContainerName(@Param("name") String containerName, Pageable page);

    @Query("select p from Packaging p where p.packagingId=:id  order by p.container.name asc")
    public Packaging getPackagingByPackagingId(@Param("id") int packagingId);

    @Transactional
    @Modifying
    @Query("update Packaging p set p.container=:container, p.format=:format, p.code=:code where p.packagingId=:packagingId")
    public void updatePackagingDataWithoutImage(@Param("container") Container container, @Param("format") Format format, @Param("code") String code, @Param("packagingId") int packagingId);

    @Transactional
    @Modifying
    @Query("update Packaging p set p.packagingImage=:packagingImage where p.packagingId=:packagingId")
    public void updatePackagingImage(@Param("packagingImage") byte[] packagingImage, @Param("packagingId") int packagingId);
}
