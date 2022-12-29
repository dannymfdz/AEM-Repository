package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.List;

import com.adobe.aem.guides.wknd.core.models.ImagenesClientesModel;
import com.adobe.aem.guides.wknd.core.models.SeccionClientesModel;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import javax.inject.Inject;

@Model(
    adaptables = {Resource.class},
    adapters = {SeccionClientesModel.class}, // Nombre de la Interfaz
    resourceType = {SeccionClientesImpl.RESOURCE_TYPE}, //Nombre de la Clase Actual
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class SeccionClientesImpl implements SeccionClientesModel{

    protected static final String RESOURCE_TYPE = "wknd/components/clientes_imagenes";

    @Inject
    private String titulo;

    @Inject
    private List<ImagenesClientesModel>seccion;

    @Override
    public String getTitulo() {

        return titulo;
    }

    @Override
    public List<ImagenesClientesModel> getSeccion() {

        return seccion;
    }
}
