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
public class AtulizadorMediaObserver implements ModelObserver {
    
    private int count;
    private Double mediaTemperatura;
    private Double mediaPressao;
    private Double mediaUmidade;
    
    public AtulizadorMediaObserver() {
        this.mediaPressao = 0.0;
        this.mediaUmidade = 0.0;
        this.mediaTemperatura = 0.0;
    }
    
    
    @Override
    public void update(TempoModel dado) {
        this.mediaTemperatura = dado.getTemperatura();
        this.mediaPressao = dado.getPressao();
        this.mediaUmidade = dado.getUmidade();
    }
    
    //getter and setter
    public Double getMediaTemperatura() {
        return mediaTemperatura;
    }

    public void setMediaTemperatura(Double mediaTemperatura) {
        this.mediaTemperatura = mediaTemperatura;
    }

    public Double getMediaPressao() {
        return mediaPressao;
    }

    public void setMediaPressao(Double mediaPressao) {
        this.mediaPressao = mediaPressao;
    }

    public Double getMediaUmidade() {
        return mediaUmidade;
    }

    public void setMediaUmidade(Double mediaUmidade) {
        this.mediaUmidade = mediaUmidade;
    }   
    
}
