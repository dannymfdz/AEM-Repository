package com.adobe.aem.guides.wknd.core.models.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.adobe.aem.guides.wknd.core.models.BylineCopy;
import com.adobe.aem.guides.wknd.core.services.PaginasService;
import com.day.cq.wcm.api.Page;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.SlingHttpServletRequest;
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
    private PaginasService paginasService;
    
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

        return currentPage.getTitle();
    }

    @Override
    public Iterator<Page> getPages() {

        return paginasService.getPages();
    }

}
