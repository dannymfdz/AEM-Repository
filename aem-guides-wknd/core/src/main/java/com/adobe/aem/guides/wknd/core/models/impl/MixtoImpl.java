package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.Mixto;
import com.adobe.cq.wcm.core.components.models.Image;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {Mixto.class},
        resourceType = {MixtoImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)


public class MixtoImpl  implements Mixto {

    private Image image;

    @PostConstruct
    private void init() {
        image = modelFactory.getModelFromWrappedRequest(request,
                                                        request.getResource(),
                                                        Image.class);
    }

    protected static final String RESOURCE_TYPE = "wknd/components/mixto";

    @Self
    private SlingHttpServletRequest request;

    @OSGiService
    private ModelFactory modelFactory;

    @ValueMapValue
    private String titulo;

    @ValueMapValue
    private String texto;

    @ValueMapValue
    private String descImagen;

    @ValueMapValue
    private String linkImp;

    @ValueMapValue
    private String linkVen;

    
    @Override
    public String getTitulo() {
        
        return titulo;
    }

    @Override
    public String getTexto() {

        return texto;
    }

    @Override
    public String getDescImagen() {

        return descImagen;
    }

    @Override
    public String getLinkImp() {

        return linkImp;
    }

    @Override
    public String getLinkVen() {

        return linkVen;
    }

    private Image getImage() {
        return image;
    }

    @Override
    public boolean isEmpty() {

        final Image componentImage = getImage();
    
        if (StringUtils.isBlank(titulo)) {

            return true;

        } else if (StringUtils.isBlank(texto)) {

            return true;
            
        } else if (StringUtils.isBlank(linkImp)) {

            return true;

        } else if (StringUtils.isBlank(linkVen)) {

            return true;

        } else if (StringUtils.isBlank(descImagen)) {

            return true;

        } else if (componentImage == null || StringUtils.isBlank(componentImage.getSrc())) {
            // A valid image is required
            return true;
            
        } else {

            return false;
        }
    }

}