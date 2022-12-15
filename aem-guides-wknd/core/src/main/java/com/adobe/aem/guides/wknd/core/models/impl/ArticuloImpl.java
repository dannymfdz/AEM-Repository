package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.Articulo;
import com.adobe.cq.wcm.core.components.models.Image;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.models.annotations.injectorspecific.Self;
import javax.annotation.PostConstruct;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {Articulo.class},
        resourceType = {ArticuloImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)


public class ArticuloImpl  implements Articulo {

    @PostConstruct
    private void init() {
        image = modelFactory.getModelFromWrappedRequest(request,
                                                        request.getResource(),
                                                        Image.class);
    }

    private Image image;

    protected static final String RESOURCE_TYPE = "wknd/components/articulo";

    @Self
    private SlingHttpServletRequest request;

    @OSGiService
    private ModelFactory modelFactory;

    @ValueMapValue
    private String titulo;

    @ValueMapValue
    private String texto;

    @ValueMapValue
    private String link;

    
    @Override
    public String getTitulo() {
        
        return titulo;
    }

    @Override
    public String getTexto() {

        return texto;
    }

    @Override
    public String getLink() {

        return link;
    }

    @Override
    public boolean isEmpty() {

       final Image componentImage = getImage();
    
        if (StringUtils.isBlank(titulo)) {
            // Titulo is missing, but required
            return true;

        } else if (StringUtils.isBlank(texto)) {

            return true;
            
        } else if (StringUtils.isBlank(link)) {

            return true;

        } else if (componentImage == null || StringUtils.isBlank(componentImage.getSrc())) {
            // A valid image is required
            return true;
            
        } else {
            // Everything is populated, so this component is not considered empty
            return false;
        }
    }

    private Image getImage() {
        return image;
    }

}
