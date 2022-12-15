package com.adobe.aem.guides.wknd.core.servlets;

import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Component(service = { Servlet.class })
@SlingServletPaths(

    value = "/bin/articulo/api/getimagen"

)

public class ArticuloApiServlet extends SlingSafeMethodsServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) {
        
        try {
            
            String query = request.getParameter("query");

            URL url = new URL("https://api.unsplash.com/search/photos?query="+query+"&per_page=1&client_id=STR2ibg138WCGlFpE67dkqsJko4UQ3vcCRtumoDYH-4");
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

            StringBuilder imagen = new StringBuilder();

            while ((output = br.readLine()) != null) {
                imagen.append(output);
            }

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("imagen", imagen.toString());

            response.getWriter().write(jsonObject.get("imagen").getAsString());
    
            conn.disconnect();
    
          } catch (IOException e) {
    
            e.printStackTrace();
    
          }
        
    }

}

