
(function() {
    "use strict";

    var selectors = {
        self:      '[data-cmp-is="imagenapi"]',
        boton:  '[data-cmp-hook-imagenapi-boton="boton"]',
        imagen:  '[data-cmp-hook-imagenapi-imagen="imagen"]',
        input:  '[data-cmp-hook-imagenapi-input="input"]'
    };

    function Imagenapi(config) {

        function init(config) {

            config.element.removeAttribute("data-cmp-is");

        }

        if (config && config.element) {
            init(config);
        }

        let boton = config.element.querySelectorAll(selectors.boton);
        let imagen = config.element.querySelectorAll(selectors.imagen);
        let input = config.element.querySelectorAll(selectors.input);

        boton[0].onclick = async() => {
            let query = input[0].value;
            const response = await fetch('/bin/api/getimagenes?query=' + query);
            const myJson = await response.json();
            console.log(myJson);
            imagen[0].src = myJson.results[0].urls.small;
        };

    }

    
    function onDocumentReady() {
        var elements = document.querySelectorAll(selectors.self);
        for (var i = 0; i < elements.length; i++) {
            new Imagenapi({ element: elements[i] });
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
                                new Imagenapi({ element: element });
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
