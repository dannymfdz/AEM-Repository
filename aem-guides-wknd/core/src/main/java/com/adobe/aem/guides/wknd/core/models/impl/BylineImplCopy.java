package com.adobe.aem.guides.wknd.core.models.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.adobe.aem.guides.wknd.core.helper.VideojuegosMultifieldHelper;
import com.adobe.aem.guides.wknd.core.helper.Videojuegosmultifield;
import com.adobe.aem.guides.wknd.core.models.BylineCopy;
import com.adobe.aem.guides.wknd.core.services.PagService;
import com.adobe.aem.guides.wknd.core.services.PaginasService;
import com.day.cq.wcm.api.Page;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {BylineCopy.class},
        resourceType = {BylineImplCopy.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class BylineImplCopy  implements BylineCopy {

    protected static final String RESOURCE_TYPE = "aem-commons/components/byline-copy";


    @ValueMapValue
    private String escuela;

    @ValueMapValue
    private List<String> hobbies;

    @ValueMapValue
    private String pageName;

    @ScriptVariable
    private Page currentPage;

    @OSGiService
    private PagService pagService;

    @OSGiService
    private PaginasService paginasService;

    @SlingObject
    private Resource currentResource;

    @Override
    public String getEscuela() {

        return escuela;
    }

    @Override
    public List<String> getHobbies() {

        if (hobbies != null) {
            Collections.sort(hobbies);
            return new ArrayList<String>(hobbies);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public String getPageName() {

        return pagService.getPag(currentPage);
    }

    @Override
    public Iterator<Page> getPages() {

        return paginasService.getPages();
    }

    @Override
    public List<Videojuegosmultifield> getVideojuegos() {
        
        List<Videojuegosmultifield> videojuegosList = new ArrayList<>();

        Resource videojuegoResource = currentResource.getChild("videojuegos");

        if(videojuegoResource!= null){
            for(Resource videojuego:videojuegoResource.getChildren()){
                Videojuegosmultifield videojuegos = new Videojuegosmultifield(videojuego);
                if(videojuego.hasChildren()){
                    List<VideojuegosMultifieldHelper> nestedList = new ArrayList<>();
                    Resource nestedResources = videojuego.getChild("videojuegoedicion");
                    for(Resource nestedResource: nestedResources.getChildren()){
                        nestedList.add(new VideojuegosMultifieldHelper(nestedResource));
                    }

                    videojuegos.setEdiciones(nestedList);
                }

                videojuegosList.add(videojuegos);
            }
        }
        return videojuegosList;
    }

}
