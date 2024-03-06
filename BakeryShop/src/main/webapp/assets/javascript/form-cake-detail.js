let cakeDetailContent = document.querySelector(".cake-detail-content");
let toppings = cakeDetailContent.querySelectorAll(".topping-list .topping-item");
let toppingsPrice = 0;
let buyQuanCurrent = 1;
let numberOfTopping = 0;
let buyQuantity = cakeDetailContent.querySelector("input[name='buy-quantity']");
let decreaseBtn = cakeDetailContent.querySelector("button[value='decrease']");
let increaseBtn = cakeDetailContent.querySelector("button[value='increase']");
let priceTag = cakeDetailContent.querySelector(".price-quantity .price").childNodes[0];
let cakeQuantity = parseInt(cakeDetailContent.querySelector("input[name='cake-quantity']").getAttribute("value"));
const alertNode = document.createElement("div");
alertNode.setAttribute("class", ["row align-items-center justify-content-start alert"]);
alertNode.textContent = "You must choose one of these toppings !";

const orgPrice = parseInt(priceTag.nodeValue);

function updatePrice() {
    priceTag.nodeValue = buyQuanCurrent * (orgPrice + toppingsPrice);
}

toppings.forEach(function (topping) {
    topping.addEventListener("click", function (event) {
        const spanToppingQuantity = topping.querySelector("span");
        const toppingPrice = parseInt(topping.querySelector("a").getAttribute("data-price"));
        if (event.target === spanToppingQuantity) {
            let toppingBuyQuantityInput = spanToppingQuantity.querySelector("input");
            let toppingBuyValue = parseInt(toppingBuyQuantityInput.getAttribute("value"));
            let maxQuantity = parseInt(topping.querySelector("a").getAttribute("data-quan"));
            if (toppingBuyValue >= maxQuantity) {
                return;
            }

            ++toppingBuyValue;
            toppingsPrice += toppingPrice;

            spanToppingQuantity.childNodes[0].textContent = toppingBuyValue;

            toppingBuyQuantityInput.setAttribute("value", toppingBuyValue);

        } else {
            topping.classList.toggle("active");
            if (topping.classList.contains("active")) {
                if (numberOfTopping === 0) {
                    let rightSide = document.querySelector(".cake-detail-content .cake-attribute");
                    let currentAlertNode = rightSide.querySelector(".alert");
                    if (currentAlertNode) {
                        rightSide.removeChild(currentAlertNode);
                    }
                }

                ++numberOfTopping;

                toppingsPrice += toppingPrice;

                const spanToppingQuantity = document.createElement("span");
                spanToppingQuantity.textContent = 1;

                const toppingBuyQuantityInput = document.createElement("input");
                toppingBuyQuantityInput.setAttribute("type", "hidden");
                toppingBuyQuantityInput.setAttribute("name", "toppingQuantity");
                toppingBuyQuantityInput.setAttribute("value", 1);
                toppingBuyQuantityInput.setAttribute("form", "add-cake-to-cart");
                spanToppingQuantity.appendChild(toppingBuyQuantityInput);

                topping.appendChild(spanToppingQuantity);
            } else {
                --numberOfTopping;

                let toppingBuyQuantityInput = spanToppingQuantity.querySelector("input");
                let toppingBuyValue = parseInt(toppingBuyQuantityInput.getAttribute("value"));
                let removePrice = toppingPrice * toppingBuyValue;

                toppingsPrice -= removePrice;
                topping.removeChild(spanToppingQuantity);
            }
        }



        updatePrice();
    });
});


buyQuantity.addEventListener("change", function (event) {

    if (isNaN(event.target.value)) {
        event.target.value = 1;
        return;
    }

    let cakeQuantity = parseInt(cakeDetailContent.querySelector("input[name='cake-quantity']").getAttribute("value"));
    if (event.target.value > cakeQuantity) {
        event.target.value = cakeQuantity;
    } else if (event.target.value < 1) {
        event.target.value = 1;
    }
    buyQuanCurrent = parseInt(event.target.value);
    buyQuantity.setAttribute("value", buyQuanCurrent);

    updatePrice();
});

decreaseBtn.addEventListener("click", function () {
    buyQuanCurrent = parseInt(buyQuantity.getAttribute("value"));
    if (buyQuanCurrent > 1) {
        --buyQuanCurrent;
    } else {
        buyQuanCurrent = 1;
    }


    buyQuantity.setAttribute("value", buyQuanCurrent);
    buyQuantity.value = buyQuanCurrent;
    updatePrice();
});
increaseBtn.addEventListener("click", function () {
    buyQuanCurrent = parseInt(buyQuantity.getAttribute("value"));
    if (buyQuanCurrent < cakeQuantity) {
        ++buyQuanCurrent;
    } else {
        buyQuanCurrent = cakeQuantity;
    }
    buyQuantity.setAttribute("value", buyQuanCurrent);
    buyQuantity.value = buyQuanCurrent;
    updatePrice();
});
