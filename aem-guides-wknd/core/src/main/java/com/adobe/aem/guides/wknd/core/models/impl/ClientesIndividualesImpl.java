package com.adobe.aem.guides.wknd.core.models.impl;

import javax.inject.Inject;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.api.SlingHttpServletRequest;
import com.adobe.aem.guides.wknd.core.models.ClientesIndividuales;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {ClientesIndividuales.class},
        resourceType = {ClientesIndividualesImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)


public class ClientesIndividualesImpl implements ClientesIndividuales {

    protected static final String RESOURCE_TYPE = "wknd/components/boton_cliente";

    @Inject
    String nombrebtn;

    @Inject
    String id;

    @Override
    public String getNombrebtn() {
        return nombrebtn;
    }

    @Override
    public String getId() {
        return id;
    }
    
}
