package com.adobe.aem.guides.wknd.core.services;

import java.util.Iterator;

import com.day.cq.wcm.api.Page;

public interface PaginasService {

    Iterator<Page> getPages();
    
}
