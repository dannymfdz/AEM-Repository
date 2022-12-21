package com.adobe.aem.guides.wknd.core.datasource;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import com.adobe.cq.sightly.WCMUsePojo;
import com.adobe.granite.ui.components.ds.DataSource;
import com.adobe.granite.ui.components.ds.SimpleDataSource;
import com.adobe.granite.ui.components.ds.ValueMapResource;

public class DatasourceColors extends WCMUsePojo{

    @Override
    public void activate() throws Exception {

        final ResourceResolver resolver = getResourceResolver();
    
        String dataPath = ResourceUtil.getValueMap(getResource().getChild("datasource")).get("data_path", String.class);

        System.out.println("Data path: " + dataPath);

        Resource resource = resolver.getResource("/apps/wknd/components/common/data/" + dataPath);

        System.out.println("Resource" + resource);

        Map<String, String> data = new LinkedHashMap<String, String>(); //Mantiene el orden

        Node currentNode = resource.adaptTo(Node.class);

        NodeIterator nodeIterator = currentNode.getNodes();

        while (nodeIterator.hasNext()) {
            
            Node node = nodeIterator.nextNode();

            data.put(node.getProperty("value").getValue().getString(), node.getProperty("name").getValue().getString());
        }

        /* Todo esto vas por tu servicio REST */
        
        @SuppressWarnings("unchecked")
        DataSource ds = new SimpleDataSource(new TransformIterator<>(data.keySet().iterator(), new Transformer() {

            @Override
            public Object transform(Object obj){
                
                String dropValue = (String) obj;

                ValueMap vm = new ValueMapDecorator(new HashMap<>());
                vm.put("value", dropValue);
                vm.put("text", data.get(dropValue));
            
                return new ValueMapResource(resolver, new ResourceMetadata(), "nt:unstructured", vm);
            }

        }));

        getRequest().setAttribute(DataSource.class.getName(), ds);
        

    }
}
