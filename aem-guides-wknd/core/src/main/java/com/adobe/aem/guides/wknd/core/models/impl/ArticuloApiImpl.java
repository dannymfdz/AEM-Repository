package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.ArticuloApi;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {ArticuloApi.class},
        resourceType = {ArticuloApiImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)


public class ArticuloApiImpl  implements ArticuloApi {

    protected static final String RESOURCE_TYPE = "wknd/components/articuloapi"; /* DUDAAAA */

    @Self
    private SlingHttpServletRequest request;

    @OSGiService
    private ModelFactory modelFactory;

    @ValueMapValue
    private String titulo;

    @ValueMapValue
    private String texto;

    @ValueMapValue
    private String img;

    
    @Override
    public String getTitulo() {
        
        return titulo;
    }

    @Override
    public String getTexto() {

        return texto;
    }

    @Override
    public String getImg() {

        return img;
    }

    @Override
    public boolean isEmpty() {
    
        if (StringUtils.isBlank(titulo)) {

            return true;

        } else if (StringUtils.isBlank(texto)) {

            return true;
            
        } else if (StringUtils.isBlank(img)) {

            return true;

        } else {

            return false;
        }
    }

}