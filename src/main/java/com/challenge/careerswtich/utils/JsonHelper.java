package com.challenge.careerswtich.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
    
    private ObjectMapper jsonMapper;
    
    public JsonHelper() {
        this.jsonMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        jsonMapper.setDateFormat(df);
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public Object stringToObject(String sConsulta, Class cClaseASerializar) throws IOException{
        return jsonMapper.readValue(sConsulta, cClaseASerializar);
    }
    
    public String objectToString(Object oClaseSerializable) throws JsonProcessingException{
        jsonMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        return jsonMapper.writeValueAsString(oClaseSerializable);
    }
    
    public Object convertValue(Object objAConvertir, Class claseRespuesta) throws IllegalArgumentException {
        return jsonMapper.convertValue(objAConvertir, claseRespuesta);
    }

    private InputStream getJson(String uri) throws MalformedURLException, IOException {
        URL url = new URL(uri);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(Textos.GET_LABEL);
        conn.setRequestProperty(Textos.CONTENT_TYPE_LABEL, Textos.APP_JSON_LABEL);
        conn.setDoOutput(true);

        InputStream input = conn.getInputStream();
        return input;
    }

    public InputStream postJson(String postData, String uri) throws MalformedURLException, ProtocolException, UnsupportedEncodingException, IOException
    {
        URL url = new URL(uri);
        
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(Textos.POST_LABEL);
        conn.setRequestProperty(Textos.CONTENT_TYPE_LABEL, Textos.APP_JSON_LABEL);
        byte[] postDataBytes = postData.getBytes();
        conn.setRequestProperty(Textos.CONTENT_LENGTH_SIZE, String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        
        InputStream input = conn.getInputStream();
        
        return input;
    }
    
    public Object streamToObject(InputStream input, Class claseADeserializar) throws IOException{
        return jsonMapper.readValue(input, claseADeserializar);
    }
    
    public Object getObjectInJsonAndReceiveObject(String url, Class claseRespuesta, String token) throws ProtocolException, IOException {
        if(token != null && !token.isEmpty()){
            HashMap<String,String> parameters = null;
            parameters = addToMap(Textos.TOKEN_LABEL, token, parameters);
            url = addPathVariable(url, parameters);  
        } 
        InputStream input = this.getJson(url);
        return streamToObject(input, claseRespuesta);
    }
    
    public Object postObjectInJsonAndReceiveObject(Object objetoPostear, String url, Class claseRespuesta, String token) throws ProtocolException, IOException {
        if(token != null && !token.isEmpty()){
            HashMap<String,String> parameters = null;
            parameters = addToMap(Textos.TOKEN_LABEL, token, parameters);
            url = addPathVariable(url, parameters);  
        } 
        String postData = objectToString(objetoPostear);
        InputStream input = this.postJson(postData, url);
        return streamToObject(input, claseRespuesta);
    }
    
    public String addPathVariable(String url , HashMap<String,String> parameters) {
        int i = 0;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(i == 0){
                url = url.concat("?" + key + "=" + value);
            }else{
                url = url.concat("&" + key + "=" + value);
            } 
            i++;
        }
        
        return url;
    }

    private HashMap addToMap(String key, String value, HashMap<String, String> response){
        if(response == null) response = new HashMap<>();
        response.put(key, value);
        return response;
    }

}
