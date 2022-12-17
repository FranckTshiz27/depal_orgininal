/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.dao;

import com.bracongo.depalettisation.entities.Agent;
import com.bracongo.depalettisation.entities.Game;
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
public interface GameDao extends JpaRepository<Game, Integer>{
    
    /**
     *
     * @param id
     * @return
     */
    Optional<Game> findGameByGameId(int id);
    
    int deleteGametByGameId(int id);   
    
    @Query("select g from Game g order by g.name asc")
    public List<Game> getGamesOrderByNameAsc();
     
}
