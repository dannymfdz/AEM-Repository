package com.adobe.aem.guides.wknd.core.servlets;

import java.util.Iterator;
import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = { Servlet.class })
@SlingServletPaths(

    value = "/bin/wknd/pages"

)
public class SimpleServletPath extends SlingSafeMethodsServlet { //Solo get el safe
    
    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        
        ResourceResolver rs = request.getResourceResolver();
        Page page = rs.adaptTo(PageManager.class).getPage("/content/wknd/us/en");
        
        Iterator<Page> childPages = page.listChildren();
        
        while (childPages.hasNext()){

            Page childPage = childPages.next();
            try {
                
                response.getWriter().write(childPage.getPath());
                response.getWriter().write("\n");
                
            } catch (Exception e) {
                
                e.printStackTrace();
            }
        }
        
    }
    
}
