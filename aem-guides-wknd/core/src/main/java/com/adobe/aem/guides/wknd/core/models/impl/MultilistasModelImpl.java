package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.List;
import com.adobe.aem.guides.wknd.core.models.MultilistaModel;
import com.adobe.aem.guides.wknd.core.models.TitulosListModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import javax.inject.Inject;
import javax.inject.Named;

@Model(
        adaptables = {Resource.class},
        adapters = {MultilistaModel.class},
        resourceType = {MultilistasModelImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class MultilistasModelImpl implements MultilistaModel{

    protected static final String RESOURCE_TYPE = "wknd/components/multilista";
    
    @Inject
    @Named("listatitulos/.")
    private List<TitulosListModel> titulosList;
 
    public List<TitulosListModel> getLista() {

        System.out.println(titulosList);
        return titulosList;
    }
}
