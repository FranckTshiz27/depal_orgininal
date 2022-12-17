/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.bracongo.depalettisation.enumerations;

/**
 *
 * @author f.tshizubu
 */
public enum GameCategory {
     BIERE("BIERE"), BG("BOISSON GAZEUSE"), EAU("EAU");
     private String category;
     
        private GameCategory (String category) {
            this.category = category;
        }
       
        @Override
        public String toString(){
            return category ;
        }
}
