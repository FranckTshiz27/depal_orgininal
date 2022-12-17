/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bracongo.depalettisation.service;
import com.bracongo.depalettisation.entities.Game;
import java.util.List;

/**
 *
 * @author f.tshizubu
 */
public interface IGame{
    
    public Game saveOrUpdate(Game  game);

    public int delete(int id);
    
    public Game getGameById(int id);
    
    public List<Game> getGames();
}
