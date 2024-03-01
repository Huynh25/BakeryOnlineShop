let submitBtn = document.querySelector(".add-to-cart-btn button");
submitBtn.addEventListener("click", function () {
    toppings.forEach(function (topping) {
        if (!topping.classList.contains("active")) {
            return;
        }
        
        const node = document.createElement("input");
        node.setAttribute("type", "hidden");
        node.setAttribute("name", "topping");
        node.setAttribute("value", topping.querySelector("a").getAttribute("data-value"));
        node.setAttribute("form", "add-cake-to-cart");
        topping.appendChild(node);
    });

    document.querySelector("#add-cake-to-cart").submit();
});