package com.adobe.aem.guides.wknd.core.models;

import java.util.Iterator;
import java.util.List;

import com.day.cq.wcm.api.Page;

public interface BylineCopy {

    String getEscuela();

    List<String> getHobbies();

    String getPageName();

    Iterator<Page> getPages();
    
}
