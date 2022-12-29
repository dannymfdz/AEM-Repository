package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.ImagenesListModel;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(
        adaptables = {Resource.class},
        adapters = {ImagenesListModel.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class ImagenesListModelImpl implements ImagenesListModel {

    @Inject
    private String imagenPath;


    @Override
    public String getImagenPath() {
        
        return imagenPath;
    }
    
}
