(function() {
    "use strict";

    var selectors = {

        self:      '[data-cmp-is="clientes-individuales"]',
        btn:  '[data-cmp-clientes-individuales-btn="btn-ci"]'
    };

    function ClientesIndividuales(config) {

        function init(config) {

            config.element.removeAttribute("data-cmp-is");
            
        }

        if (config && config.element) {
            init(config);
        }

        /*----------------------------------------------------------------*/

        const secciones = document.querySelectorAll('.cmp-clientes-container');
        secciones.forEach(seccion => {

            seccion.setAttribute('hidden', 'true');
        })

        secciones[0].removeAttribute('hidden');

        let btn = config.element.querySelector(selectors.btn);

        btn.addEventListener('click', function(){

            secciones.forEach(seccion => {

                if(seccion.id === btn.dataset.section){

                    seccion.removeAttribute('hidden');

                }else{

                    seccion.setAttribute('hidden',true);
                }
            })
        });

    }

    
    
    function onDocumentReady() {
        var elements = document.querySelectorAll(selectors.self);
        for (var i = 0; i < elements.length; i++) {
            new ClientesIndividuales({ element: elements[i] });
        }

        var MutationObserver = window.MutationObserver || window.WebKitMutationObserver || window.MozMutationObserver;
        var body             = document.querySelector("body");
        var observer         = new MutationObserver(function(mutations) {
            mutations.forEach(function(mutation) {
                // needed for IE
                let nodesArray = [].slice.call(mutation.addedNodes);
                if (nodesArray.length > 0) {
                    nodesArray.forEach(function(addedNode) {
                        if (addedNode.querySelectorAll) {
                            var elementsArray = [].slice.call(addedNode.querySelectorAll(selectors.self));
                            elementsArray.forEach(function(element) {
                                new ClientesIndividuales({ element: element });
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