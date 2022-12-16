package com.adobe.aem.guides.wknd.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.adobe.aem.guides.wknd.core.config.OSGiConfig;
import com.adobe.aem.guides.wknd.core.services.OSGiConfigModule;

@Component(service = OSGiConfigModule.class, immediate=true)
@Designate(ocd = OSGiConfig.class)

public class OSGiConfigImpl implements OSGiConfigModule {

    private String pokeapiEndpoint;
    private String imagenesEndpoint;
    private String idEndpoint;

    @Activate()
    protected void activate(final OSGiConfig cfg) {
    
        pokeapiEndpoint = cfg.getPokeapiEndpoint();
        imagenesEndpoint = cfg.getImagenesEndpoint();
        idEndpoint = cfg.getIdEndpoint();
    }

    @Override
    public String getPokeapiEndpoint() {
        return pokeapiEndpoint;
    }

    @Override
    public String getImagenesEndpoint() {
        return imagenesEndpoint;
    }

    @Override
    public String getIdEndpoint() {
        return idEndpoint;
    }   


}
