/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherdatadisplay.model;

import java.util.ArrayList;

/**
 *
 * @author Lucas Marques Fonseca
 */
public class Dados {
    private ArrayList<TempoModel> dados;

    public Dados() {
        this.dados = new ArrayList<TempoModel>();
    }
    
   //getters and setters

    public ArrayList<TempoModel> getDados() {
        return dados;
    }

    public void setDados(ArrayList<TempoModel> dados) {
        this.dados = dados;
    }
    
}
