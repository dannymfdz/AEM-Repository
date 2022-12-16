package com.adobe.aem.guides.wknd.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
//import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name="AEM WKND - OSGi Configuration",
                        description = "OSGi Configuration")

public @interface OSGiConfig {

    @AttributeDefinition(

        description = "PokeapiEndpoint",
        name = "Pokeapi Endpoint",
        type = AttributeType.STRING
    )

    public String getPokeapiEndpoint() default "https://pokeapi.co/api/v2/";

    @AttributeDefinition(

        description = "Imagenes Endpoint",
        name = "Imagenes Endpoint",
        type = AttributeType.STRING
    )

    public String getImagenesEndpoint () default "https://api.unsplash.com/";

    @AttributeDefinition(

        description = "ID de Imagenes",
        name = "ID LLave",
        type = AttributeType.STRING
    )

    public String getIdEndpoint() default "";
/* 
    @AttributeDefinition(

        description = "Agregamos Idiomas",
        name = "Idiomas",
        type = AttributeType.STRING
    )

    public String[] getIdiomas() default {"en","es"};

    @AttributeDefinition(

        description = "Combo de Ambientes",
        name = "Ambiente",
        type = AttributeType.STRING,
        options = {
            @Option(value = "autor", label = "Autor"),
            @Option(value = "publish", label = "Publish"),
            @Option(value = "ambos", label = "Ambos")
        } 
    )

    public String getRunmodes() default "autor"; */
}
