package com.adobe.aem.guides.wknd.core.services.impl;

import java.util.Iterator;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.wknd.core.services.PaginasService;
import com.adobe.aem.guides.wknd.core.util.ResourceResolverUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;


@Component(service=PaginasService.class, immediate = true)
public class PaginasServiceImpl implements PaginasService {

    @Reference
    private ResourceResolverFactory resolverFactory;
    

    @Activate
    public void activate(ComponentContext context) {
    
        System.out.println("Activating Paginas Service");
    }

    @Deactivate()
    public void deactivate() {
    
        System.out.println("Deactivating Paginas Service");
    }

    @Modified
    public void modified(ComponentContext context) {
        
        System.out.println("Modified Paginas Service");
    
    }


    @Override
    public Iterator<Page> getPages() {

        try {

            ResourceResolver resourceResolver = ResourceResolverUtil.newResolver(resolverFactory);
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage("/content/wknd/us/en");
            Iterator<Page> pages = page.listChildren();
            return pages;

        } catch (LoginException e) {
            
            e.printStackTrace();
        }
        
        return null;
    }
}
