package com.adobe.aem.guides.wknd.core.servlets;

import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.wknd.core.services.OSGiConfigModule;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Component(service = { Servlet.class })
@SlingServletPaths(

    value = "/bin/api/getpokemon"

)

public class PokemonServlet extends SlingSafeMethodsServlet {
    
    private static final long serialVersionUID = 1L;

    @Reference
    private OSGiConfigModule osgiConfig;

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        
        try {

            String id = request.getParameter("id");
            //URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + id);
            URL url = new URL(osgiConfig.getPokeapiEndpoint() + "pokemon/" + id); // ctrl+k+c / +u
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
    
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
    
            BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));
    
            String output;
            StringBuilder pokemon = new StringBuilder();

            while ((output = br.readLine()) != null) {
                pokemon.append(output);
            }

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("pokemon", pokemon.toString());

            response.getWriter().write(jsonObject.get("pokemon").getAsString());
    
            conn.disconnect();
    
          } catch (IOException e) {
    
            e.printStackTrace();
    
          }
        
    }

}
