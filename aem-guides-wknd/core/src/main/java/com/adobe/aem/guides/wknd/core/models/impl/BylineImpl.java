package com.adobe.aem.guides.wknd.core.models.impl;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import com.adobe.aem.guides.wknd.core.models.Byline;
import com.adobe.cq.wcm.core.components.models.Image;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.models.annotations.injectorspecific.Self;
import javax.annotation.PostConstruct;


// A Sling Model annotation that defines the adaptable, adapter, resource type, and default injection
// strategy of the model.
@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {Byline.class},
        resourceType = {BylineImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

/**
 * The BylineImpl class is a Sling Model that implements the Byline interface
 */
public class BylineImpl  implements Byline {

    /**
     * The function takes the request and the resource and creates an image object
     */
    @PostConstruct
    private void init() {
        image = modelFactory.getModelFromWrappedRequest(request,
                                                        request.getResource(),
                                                        Image.class);
    }

    // Creating an image object.
    private Image image;

    // This is a constant that defines the resource type of the component.
    protected static final String RESOURCE_TYPE = "wknd/components/byline";

    // Injecting the current request into the model.
    @Self
    private SlingHttpServletRequest request;

    // Injecting the ModelFactory service into the model.
    @OSGiService
    private ModelFactory modelFactory;

    // Injecting the value of the property into the field.
    @ValueMapValue
    private String name;

    // A Sling Model annotation that injects the value of the property into the field.
    @ValueMapValue
    private List<String> occupations;

    /**
     * This function returns the name of the object
     * 
     * @return The name of the person.
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    /**
     * It returns a sorted list of occupations
     * 
     * @return A list of occupations.
     */
    @Override
    public List<String> getOccupations() {
        if (occupations != null) {
            Collections.sort(occupations);
            return new ArrayList<String>(occupations);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * If the name is blank, or the occupations list is empty, or the image is blank, then the
     * component is considered empty
     */
    @Override
    public boolean isEmpty() {
       final Image componentImage = getImage();
    
        if (StringUtils.isBlank(name)) {
            // Name is missing, but required
            return true;
        } else if (occupations == null || occupations.isEmpty()) {
            // At least one occupation is required
            return true;
        } else if (componentImage == null || StringUtils.isBlank(componentImage.getSrc())) {
            // A valid image is required
            return true;
        } else {
            // Everything is populated, so this component is not considered empty
            return false;
        }
    }
    /**
    * @return the Image Sling Model of this resource, or null if the resource cannot create a valid Image Sling Model.
    */
    private Image getImage() {
        return image;
    }

}
