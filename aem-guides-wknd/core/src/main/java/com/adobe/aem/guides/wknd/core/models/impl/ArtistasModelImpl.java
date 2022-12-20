package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.List;

import com.adobe.aem.guides.wknd.core.models.ArtistasListModel;
import com.adobe.aem.guides.wknd.core.models.ArtistasModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import javax.inject.Inject;

@Model(
        adaptables = {Resource.class},
        adapters = {ArtistasModel.class},
        resourceType = {ArtistasModelImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class ArtistasModelImpl implements ArtistasModel{

    protected static final String RESOURCE_TYPE = "wknd/components/artistas_musicales";
    
    @Inject
    private String titulo;

    @Inject
    private List<ArtistasListModel> artistas;
 
    @Override
    public List<ArtistasListModel> getArtistasList() {

        return artistas;
    }

    @Override
    public String getTitulo(){
        return titulo;
    }
}
