/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.service.impl;

import com.bracongo.depalettisation.exception.CustomNotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import lombok.AllArgsConstructor;
import com.bracongo.depalettisation.dao.GameDao;
import com.bracongo.depalettisation.entities.Game;
import com.bracongo.depalettisation.entities.Truck;
import com.bracongo.depalettisation.service.IGame;

/**
 *
 * @author f.tshizubu
 */
@Service
@Transactional
@AllArgsConstructor
public class GameService implements IGame {

    @Autowired
    private GameDao gameDao;
    
    
    
    
    @Override
    public int delete(int id){
        
        Optional<Game> deletingGame = gameDao.findGameByGameId(id);
        
        if (deletingGame!=null) {
            gameDao.deleteGametByGameId(id);
            return 1;
        }
            
        return -1;
        
    }

    @Override
    public Game getGameById(int id) {
        return  gameDao.findGameByGameId(id).
                orElseThrow(()->new CustomNotFoundException("La game dont le id "+id+" est introuvable"));
    }
    
    @Override
    public List<Game> getGames() { 
       return gameDao.getGamesOrderByNameAsc();  
    }

    @Override
    public Game saveOrUpdate(Game game) {
        
        return gameDao.save(game);
    }
    
     public Game updateGame(Game game) {

        Optional<Game> newGame = gameDao.findGameByGameId(game.getGameId());
        if (newGame!= null) {
            return gameDao.save(game);
        }

        return null;
    }
}
