package com.adobe.aem.guides.wknd.core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public final class ResourceResolverUtil {
    
    private ResourceResolverUtil() {
    
    }

    public static ResourceResolver newResolver(ResourceResolverFactory factory) throws LoginException {

        final Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put(ResourceResolverFactory.USER,"admin");

        paramMap.put(ResourceResolverFactory.PASSWORD,"admin".toCharArray());

        ResourceResolver resolver = factory.getResourceResolver(paramMap);

        return resolver;
    }
}
