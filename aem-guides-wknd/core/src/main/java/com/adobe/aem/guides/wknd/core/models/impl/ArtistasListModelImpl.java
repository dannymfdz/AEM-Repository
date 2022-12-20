package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.adobe.aem.guides.wknd.core.models.ArtistasListModel;
import com.adobe.aem.guides.wknd.core.models.DiscografiaModel;

@Model(
        adaptables = {Resource.class},
        adapters = {ArtistasListModel.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class ArtistasListModelImpl implements ArtistasListModel{

    @Inject
    private String nombre;

    @Inject
    private Date fechanacimiento;

    @Inject
    private List<DiscografiaModel> discografia;

    @Override
    public String getNombre() {

        return nombre;
    }

    @Override
    public Date getFechaNacimiento() {
        return fechanacimiento;
    }

    @Override
    public List<DiscografiaModel> getDiscografia() {
        return discografia;
    }
}
