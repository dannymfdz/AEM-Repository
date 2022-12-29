package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.List;

import com.adobe.aem.guides.wknd.core.models.ImagenesClientesModel;
import com.adobe.aem.guides.wknd.core.models.ImagenesListModel;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import javax.inject.Inject;

@Model(
    adaptables = {Resource.class},
    adapters = {ImagenesClientesModel.class}, // Nombre de la Interfaz
    resourceType = {ImagenesClientesImpl.RESOURCE_TYPE}, //Nombre de la Clase Actual
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class ImagenesClientesImpl implements ImagenesClientesModel{

    protected static final String RESOURCE_TYPE = "wknd/components/clientes_imagenes";

    @Inject
    private String id;

    @Inject
    private List<ImagenesListModel>multilista;

    @Override
    public String getId() {

        return id;
    }

    @Override
    public List<ImagenesListModel> getMultilista() {

        return multilista;
    }
    
    
}
