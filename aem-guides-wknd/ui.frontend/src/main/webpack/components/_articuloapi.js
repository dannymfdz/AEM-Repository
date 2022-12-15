
(function() {
    "use strict";

    var selectors = {
        self:      '[data-cmp-is="modelo"]',
        titulo: '[data-cmp-articulo-api-titulo="titulo"]',
        texto: '[data-cmp-articulo-api-texto="texto"]',
        img: '[data-cmp-articulo-api-img="img"]'
    };

    let mostrarService = async(tituloTxt) => {

        const response = await fetch("http://localhost:4502/bin/articulo/api/getimagen?query="+tituloTxt);
        return await response.json();
    }

    function ArticuloApi(config){

        function init(config){

            config.element.removeAttribute("data-cmp-is");

        }

        if(config && config.element) {

            init(config);
        }

        let titulo = config.element.querySelector(selectors.titulo);
        let img = config.element.querySelector(selectors.img);
        let tituloTxt = titulo.innerText;
        
        mostrarService(tituloTxt).then((resp) => {

            img.src = resp.results[0].urls.small;

        });
        
    }


    function onDocumentReady() {

        var elements = document.querySelectorAll(selectors.self);
        for (var i = 0; i < elements.length; i++) {
            new ArticuloApi({ element: elements[i] });
        }

        var MutationObserver = window.MutationObserver || window.WebKitMutationObserver || window.MozMutationObserver;
        var body             = document.querySelector("body");
        var observer         = new MutationObserver(function(mutations) {
            mutations.forEach(function(mutation) {
                // needed for IE
                var nodesArray = [].slice.call(mutation.addedNodes);
                if (nodesArray.length > 0) {
                    nodesArray.forEach(function(addedNode) {
                        if (addedNode.querySelectorAll) {
                            var elementsArray = [].slice.call(addedNode.querySelectorAll(selectors.self));
                            elementsArray.forEach(function(element) {
                                new ArticuloApi({ element: element });
                            });
                        }
                    });
                }
            });
        });

        observer.observe(body, {
            subtree: true,
            childList: true,
            characterData: true
        });
    }

    if (document.readyState !== "loading") {
        onDocumentReady();
    } else {
        document.addEventListener("DOMContentLoaded", onDocumentReady);
    }

}());
