package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.DiscografiaModel;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import javax.inject.Inject;

@Model(
        adaptables = {Resource.class},
        adapters = {DiscografiaModel.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class DiscografiaModelImpl implements DiscografiaModel {

    @Inject
    private String album;

    @Inject
    private Integer anio;

    @Override
    public String getAlbum() {

        return album;
    }

    @Override
    public Integer getAnio() {

        return anio;
    }
    
}
