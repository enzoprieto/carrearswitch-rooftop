package com.challenge.careerswtich.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
   
    private Properties propiedades;
    private String nombreArhivoConfig;

    public Configuracion(String nombreArchivoConfig) {
        this.nombreArhivoConfig = nombreArchivoConfig;
        
        propiedades = new Properties();
        InputStream entrada = null;
        try {
            entrada = Configuracion.class.getClassLoader().getResourceAsStream(nombreArchivoConfig);
            propiedades.load(entrada);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String obtenerValorPropiedad(String nombrePropiedad) {
            return propiedades.getProperty(nombrePropiedad);
    }
    
}