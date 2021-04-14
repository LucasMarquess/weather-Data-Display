/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherdatadisplay.presenter;

import com.mycompany.weatherdatadisplay.model.AtulizadorMediaObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import com.mycompany.weatherdatadisplay.model.Dados;
import com.mycompany.weatherdatadisplay.model.Log;
import com.mycompany.weatherdatadisplay.model.TempoModel;
import org.json.simple.JSONObject;
import com.mycompany.weatherdatadisplay.view.TelaInicial;
import org.json.XML;

/**
 *
 * @author Lucas Marques
 */
public class TempoPresenter {

    private final TelaInicial iTela;
    private AtulizadorMediaObserver mediaDiaria;
    private AtulizadorMediaObserver mediaSemanal;
    private AtulizadorMediaObserver mediaMensal;
    private final Dados dados;
    
    private int countRegistros = 0;
    
    public TempoPresenter( ) {
        this.iTela = new TelaInicial(this);
        this.iTela.setVisible(true);
        this.dados = new Dados();
        this.mediaDiaria = new AtulizadorMediaObserver();
        this.mediaSemanal = new AtulizadorMediaObserver();
        this.mediaMensal = new AtulizadorMediaObserver();
    }
    
    public void incluirDados (LocalDate data, Double temperatura, Double umidade, Double pressao){
        TempoModel tempo = new TempoModel(data, temperatura, umidade, pressao);
        this.dados.getDados().add(tempo);
        this.insertLog(tempo);
    }
    
    public void deletarDados(int linha){
        this.dados.getDados().remove(linha);
    }
    
    public void calculaMedia(int opcao){
        
        LocalDate dataHoje = LocalDate.now();
        Double tempCount = 0.0;
        Double umidadeCount = 0.0;
        Double pressaoCount = 0.0;
        Double mediaTemperatura = 0.0;
        Double mediaUmidade = 0.0;
        Double mediaPressao = 0.0;
        this.countRegistros = 0;

        
        int i = 0;
        int tamanho = this.dados.getDados().size();
        
        //calculo de media diaria
        if (opcao == 0){
            for (i=0; i < tamanho; i++){
                if (this.dados.getDados().get(i).getData().isEqual(dataHoje)){
                    countRegistros++;
                    tempCount += this.dados.getDados().get(i).getTemperatura();
                    umidadeCount += this.dados.getDados().get(i).getUmidade();
                    pressaoCount += this.dados.getDados().get(i).getPressao();
                }
            }
                mediaTemperatura =  (tempCount / countRegistros);
                mediaUmidade = (umidadeCount/countRegistros);
                mediaPressao = (pressaoCount/countRegistros);

                mediaDiaria.update(new TempoModel(LocalDate.now(), mediaTemperatura, mediaUmidade, mediaPressao));
        }
        
        //calculo de media semanal = 7 dias anteriores
        if (opcao == 1){
            var sevenDaysBefore = LocalDate.now();
            sevenDaysBefore = sevenDaysBefore.minusDays(7);
            for (i=0; i < tamanho; i++){
                if ((this.dados.getDados().get(i).getData().isAfter(sevenDaysBefore)) && (this.dados.getDados().get(i).getData().isBefore(dataHoje.plusDays(1)))){
                    countRegistros++;
                    tempCount += this.dados.getDados().get(i).getTemperatura();
                    umidadeCount += this.dados.getDados().get(i).getUmidade();
                    pressaoCount += this.dados.getDados().get(i).getPressao();
                }
            }
                mediaTemperatura =  (tempCount / countRegistros);
                mediaUmidade = (umidadeCount/countRegistros);
                mediaPressao = (pressaoCount/countRegistros);

                mediaSemanal.update(new TempoModel(LocalDate.now(), mediaTemperatura, mediaUmidade, mediaPressao));
        }
        
        //calculo de media mensal = 31 dias anteriores
        if (opcao == 2){
            var thirtyOneDaysBefore = LocalDate.now();
            thirtyOneDaysBefore = thirtyOneDaysBefore.minusDays(31);
            for (i=0; i < tamanho; i++){
                if ((this.dados.getDados().get(i).getData().isAfter(thirtyOneDaysBefore)) && (this.dados.getDados().get(i).getData().isBefore(dataHoje.plusDays(1)))){
                    countRegistros++;
                    tempCount += this.dados.getDados().get(i).getTemperatura();
                    umidadeCount += this.dados.getDados().get(i).getUmidade();
                    pressaoCount += this.dados.getDados().get(i).getPressao();
                }
            }
                mediaTemperatura =  (tempCount / countRegistros);
                mediaUmidade = (umidadeCount/countRegistros);
                mediaPressao = (pressaoCount/countRegistros);

                mediaMensal.update(new TempoModel(LocalDate.now(), mediaTemperatura, mediaUmidade, mediaPressao));
        }
    
        
    }
    
    
    public void insertLog (TempoModel tempo){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Data", tempo.getData().toString());
        jsonObject.put("Temperatura", tempo.getTemperatura().toString());
        jsonObject.put("Umidade", tempo.getUmidade().toString());
        jsonObject.put("Pressão", tempo.getPressao().toString());
        jsonObject.put("Tipo", "Inclusão" );
        String xml = XML.toString(jsonObject);
        Log.getJsonArray().add(jsonObject);
        Log.getXMLArray().append("<log>").append(xml).append("</log>");
    }
    
    public void deleteLog (int linha){
        
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        
        jsonObject.put("Data", this.getDados().getDados().get(linha).getData().toString());
        jsonObject.put("Temperatura", this.getDados().getDados().get(linha).getTemperatura().toString() );
        jsonObject.put("Umidade",  this.getDados().getDados().get(linha).getUmidade().toString());
        jsonObject.put("Pressão",  this.getDados().getDados().get(linha).getPressao().toString());
        jsonObject.put("Tipo", "Remoção" );
        String xml = XML.toString( jsonObject);
        Log.getXMLArray().append("<log>").append(xml).append("</log>");
        Log.getJsonArray().add(jsonObject);
    }
    
    public void salvarLog (int opcao){
        
        try {
            
            if(opcao == 0){
            FileWriter file = new FileWriter("outputjson.json");
            file.write(Log.getJsonArray().toJSONString());
            file.close();
            }
            if(opcao == 1){
            FileWriter file = new FileWriter("outputxml.xml");
            file.write(Log.getXMLArray().toString());
            file.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public TempoModel getUltimoRegistro (){
        
        var tamanho = this.getDados().getDados().size();
        
        return this.getDados().getDados().get(tamanho - 1);
    }
    
    
    //getters and setters
    public Dados getDados() {
        return dados;
    }

    public int getCountRegistros() {
        return countRegistros;
    }

    public AtulizadorMediaObserver getMediaDiaria() {
        return mediaDiaria;
    }

    public AtulizadorMediaObserver getMediaSemanal() {
        return mediaSemanal;
    }

    public AtulizadorMediaObserver getMediaMensal() {
        return mediaMensal;
    }
    
    
    
}
