package com.adobe.aem.guides.wknd.core.helper;


//import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;


public class VideojuegosMultifieldHelper {
    
    private String consola;
    private String nombreEdicion;

    public VideojuegosMultifieldHelper(Resource resource){

        if(StringUtils.isNotBlank(resource.getValueMap().get("consola", String.class))){

            this.consola= resource.getValueMap().get("consola", String.class);
        }
        if(resource.getValueMap().get("nombreedicion", String.class) != null){

            this.nombreEdicion= resource.getValueMap().get("nombreedicion", String.class);
        }
    }

    public String getConsola() {
        return consola;
    }

    public String getNombreEdicion() {
        return nombreEdicion;
    }

}
