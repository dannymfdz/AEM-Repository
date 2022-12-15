(function() {
    "use strict";

    var selectors = {
        self:      '[data-cmp-is="apimagen"]',
        boton:  '[data-cmp-hook-apimagen-boton="boton"]',
        imagen:  '[data-cmp-hook-apimagen-imagen="imagen"]',
        input:  '[data-cmp-hook-apimagen-input="input"]'
    };

    function Apimagen(config) {

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
            const response = await fetch('/bin/wknd/getimagen?query=' + query);
            const myJson = await response.json();
            console.log(myJson);
            imagen[0].src = myJson.results[0].urls.small;
        };
    }

    
    function onDocumentReady() {
        var elements = document.querySelectorAll(selectors.self);
        for (var i = 0; i < elements.length; i++) {
            new Apimagen({ element: elements[i] });
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
                                new Apimagen({ element: element });
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