package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.adobe.aem.guides.wknd.core.models.BulletModel;
import com.adobe.aem.guides.wknd.core.models.TitulosListModel;

@Model(
        adaptables = {Resource.class},
        adapters = {TitulosListModel.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class TitulosListModelImpl implements TitulosListModel{

    @Inject
    private String titulo;

    @Inject
    @Named("innerlist/.")
    private List<BulletModel> bullets;

    @Override
    public String getTitulo() {

        return titulo;
    }

    @Override
    public List<BulletModel> getBullets(){
        return bullets;
    }
    
}
