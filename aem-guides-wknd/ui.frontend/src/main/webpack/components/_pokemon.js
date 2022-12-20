
(function() {
    "use strict";

    var selectors = {
        self:      '[data-cmp-is="pokemon"]',
        button: '[data-cmp-hook-pokemon-button="button"]',
        name: '[data-cmp-hook-pokemon-name="name"]'
    };

    function Pokemon(config) {

        function init(config) {

            config.element.removeAttribute("data-cmp-is");

        }

        if (config && config.element) {
            init(config);
        }

        let button = config.element.querySelector(selectors.button);
        let name = config.element.querySelector(selectors.name);

        button.onclick = async() => {

            alert("Pokemon Obtenido");

            let x = Math.floor((Math.random() * 150) + 1);

            const response = await fetch("http://localhost:4502/bin/api/getpokemon?id=" + x);
            const myJson = await response.json();
            console.log(myJson);
            name.innerText = "Atrapaste a: " + myJson.forms[0].name;
        };

    }
    
    function onDocumentReady() {

        console.log("onDocumentReady");

        var elements = document.querySelectorAll(selectors.self);
        for (var i = 0; i < elements.length; i++) {
            new Pokemon({ element: elements[i] });
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
                                new Pokemon({ element: element });
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
