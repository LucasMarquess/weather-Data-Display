/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherdatadisplay.model;

/**
 *
 * @author Lucas Marques Fonseca
 */
public interface RecebeDado {
        public void notificar ();
        public void registrar (ModelObserver obj);
        
}
