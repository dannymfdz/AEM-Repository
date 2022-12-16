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

    value = "/bin/api/getpokemon"

)

public class PokemonServlet extends SlingSafeMethodsServlet {
    
    private static final long serialVersionUID = 1L;

    @Reference
    private RestApiService restApiService;

    @Reference
    private OSGiConfigModule osgiConfig;

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        
        try {

            String id = request.getParameter("id");
            String url = osgiConfig.getPokeapiEndpoint() + "pokemon/" + id;
            String pokemon = restApiService.getJson(url);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("pokemon", pokemon.toString());

            response.getWriter().write(jsonObject.get("pokemon").getAsString());
    
          } catch (IOException e) {
    
            e.printStackTrace();
    
          }
        
    }

}
