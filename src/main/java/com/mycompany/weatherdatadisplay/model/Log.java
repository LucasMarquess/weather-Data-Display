/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weatherdatadisplay.model;

import org.json.simple.JSONArray;

/**
 *
 * @author Davidson
 */
public abstract class Log {
    private static JSONArray jsonArray = new JSONArray();
    private static StringBuilder XMLArray = new StringBuilder();

    public static StringBuilder getXMLArray() {
        return XMLArray;
    }

    public static void setXMLArray(StringBuilder XMLArray) {
        Log.XMLArray = XMLArray;
    }

    public static JSONArray getJsonArray() {
        return jsonArray;
    }

    public static void setJsonArray(JSONArray jsonArray) {
        Log.jsonArray = jsonArray;
    }
}
