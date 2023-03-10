package com.adobe.aem.guides.wknd.core.models;

import java.util.Date;
import java.util.List;

public interface ArtistasListModel {
    
    String getNombre();
    Date getFechaNacimiento();
    String getImagenArtista();
    String getDescripcion();
    List<DiscografiaModel> getDiscografia();

}
