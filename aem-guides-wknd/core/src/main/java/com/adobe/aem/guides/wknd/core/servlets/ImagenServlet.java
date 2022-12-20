package com.adobe.aem.guides.wknd.core.servlets;

import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.wknd.core.services.OSGiConfigModule;
import com.adobe.aem.guides.wknd.core.services.RestApiService;
import com.google.gson.JsonObject;
import java.io.IOException;


@Component(service = { Servlet.class })
@SlingServletPaths(

    value = "/bin/api/getimagenes"

)

public class ImagenServlet extends SlingSafeMethodsServlet {
    
    private static final long serialVersionUID = 1L;

    @Reference
    private OSGiConfigModule osgiConfig;

    @Reference
    private RestApiService restApiService;

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        
        try {
            
            String query = request.getParameter("query");
            String url = osgiConfig.getImagenesEndpoint()+"search/photos?query="+query+"&per_page=1&client_id="+osgiConfig.getIdEndpoint();
            String imagen = restApiService.getJson(url);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("imagen", imagen.toString());

            response.getWriter().write(jsonObject.get("imagen").getAsString());
    
          } catch (IOException e) {
    
            e.printStackTrace();
    
          }
        
    }

}