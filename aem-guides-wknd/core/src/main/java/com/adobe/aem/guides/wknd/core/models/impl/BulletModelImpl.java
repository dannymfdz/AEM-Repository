package com.adobe.aem.guides.wknd.core.models.impl;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import com.adobe.aem.guides.wknd.core.models.BulletModel;

@Model(
        adaptables = {Resource.class},
        adapters = {BulletModel.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class BulletModelImpl implements BulletModel {

    @Inject
    private String bullet;

    @Override
    public String getBullet() {

        return bullet;
    }
    
    

}
