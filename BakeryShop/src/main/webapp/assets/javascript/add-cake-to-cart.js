let submitBtn = document.querySelector(".add-to-cart-btn button");


submitBtn.addEventListener("click", function () {
    let rightSide = document.querySelector(".cake-detail-content .cake-attribute");

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

    if (numberOfTopping === 0) {
        rightSide.prepend(alertNode);
        return;
    }
    document.querySelector("#add-cake-to-cart").submit();
});