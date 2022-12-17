/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bracongo.depalettisation.controller;

import com.bracongo.depalettisation.entities.Game;
import com.bracongo.depalettisation.service.impl.GameService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author f.tshizubu
 */
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
@Component
public class GameController {
    
    @Autowired
    private  GameService gameService;
    
    
    @GetMapping
    public ResponseEntity<List<Game>> findAll(){
        List<Game> games = gameService.getGames();
        return new ResponseEntity<>(games,HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Game> create(@Valid @RequestBody Game game){
        Game newGame =  gameService.saveOrUpdate(game);
        return new ResponseEntity<>(newGame,HttpStatus.CREATED);
    }
   
    @PatchMapping("{id}")
    public ResponseEntity<Game> update(@RequestBody Game game,@PathVariable("id") int id) {
       
        game.setGameId(id);
        Game updateGameEntity = gameService.updateGame(game);
        
        return new ResponseEntity<>(updateGameEntity, HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") int id){
       
            if(gameService.delete(id)>=1)
            {
                return  new ResponseEntity<>(1,HttpStatus.OK);
            } 
            else{
                 return  new ResponseEntity<>(-1,HttpStatus.NOT_ACCEPTABLE); 
            }
               
            
    } 
    
    
    
    @GetMapping("{id}")
    public ResponseEntity<Game> findById(@PathVariable("id") int id){
        Game game = gameService.getGameById(id);
        return new ResponseEntity<>(game,HttpStatus.OK);
    }
    
    
}
