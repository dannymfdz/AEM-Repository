package com.adobe.aem.guides.wknd.core.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.osgi.service.component.annotations.Component;

import com.adobe.aem.guides.wknd.core.services.RestApiService;

@Component(service =RestApiService.class, immediate =true)
public class RestApiServiceImpl implements RestApiService{

    @Override
    public String getJson(String urlParam) {

        URL url;
        HttpURLConnection conn = null;
        
        try{

            url = new URL(urlParam);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }


            BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

            String output;
            StringBuilder json = new StringBuilder();

            while ((output = br.readLine()) != null) {
                json.append(output);
            }

            return json.toString();

        }catch(Exception e){

            e.printStackTrace();
            
        }finally{

            if (conn!= null) {
                conn.disconnect();
            }
        }

        return null;
    }
    
}
