package com.challenge.careerswtich.utils;

import java.io.IOException;

public class ClienteWebService {

    private String url;
    private JsonHelper cliente;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JsonHelper getCliente() {
        return cliente;
    }

    public void setCliente(JsonHelper cliente) {
        this.cliente = cliente;
    }

    public ClienteWebService(String nombreUrl) {
        this(nombreUrl, Textos.URL_LABEL);
    }

    private ClienteWebService(String urlConfig, String archivoConfig) {
        try {
            url = (new Configuracion(archivoConfig)).obtenerValorPropiedad(urlConfig);
            cliente = new JsonHelper();
        } catch (Exception ex) {

        }
    }

    public <T> T getAndReceiveObjectTokenPathVariable(Class clase, String host, String token) throws IOException {
        return (T) cliente.getObjectInJsonAndReceiveObject(host + url, clase, token);
    }

    public <T> T postObjectInJsonAndReceiveObjectTokenPathVariable(Object parametro, String host, Class clase,
            String token) throws IOException {
        return (T) cliente.postObjectInJsonAndReceiveObject(parametro, host + url, clase, token);
    }

}