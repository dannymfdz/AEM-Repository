package com.adobe.aem.guides.wknd.core.helper;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;

public class Videojuegosmultifield {
    
    private String videojuegoNombre;
    private Date fechaPublicacion;
    private List<VideojuegosMultifieldHelper> ediciones;

    public Videojuegosmultifield(Resource resource){

        if(StringUtils.isNotBlank(resource.getValueMap().get("nombre", String.class))){

            this.videojuegoNombre= resource.getValueMap().get("nombre", String.class);
        }
        if(resource.getValueMap().get("fechapublicacion", String.class) != null){

            this.fechaPublicacion= resource.getValueMap().get("fechapublicacion", Date.class);
        }
    }

    public String getVideojuegoNombre() {
        return videojuegoNombre;
    }

    public Date getFechaPublicacion() {
        
        return fechaPublicacion;
    }

    public List<VideojuegosMultifieldHelper> getEdiciones() {
        
        return ediciones;
    }
    
    public void setEdiciones(List<VideojuegosMultifieldHelper> ediciones) {
        
        this.ediciones = ediciones;
    }

}
