package com.adobe.aem.guides.wknd.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.adobe.aem.guides.wknd.core.services.PagService;
import com.day.cq.wcm.api.Page;


@Component(service=PagService.class, immediate = true)
public class PagServiceImpl implements PagService {

    @Override
    public String getPag(Page page) {

        return page.getTitle();
    }
}
