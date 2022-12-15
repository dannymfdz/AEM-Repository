package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.Apimagenmodelo;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {Apimagenmodelo.class},
        resourceType = {ApimagenmodeloImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class ApimagenmodeloImpl implements Apimagenmodelo {

    protected static final String RESOURCE_TYPE = "wknd/components/imagenapi"; //nombre de carpeta en ui.apps

    @ValueMapValue
    String title;

    @ValueMapValue
    String text;

    @ValueMapValue
    @Default(values = "Get Image")
    String boton;

    @Override
    public String getTitle() {

        return title;
    }

    @Override
    public String getText() {

        return text;
    }

    @Override
    public String getBoton() {

        return boton;
    }
    
    
}
