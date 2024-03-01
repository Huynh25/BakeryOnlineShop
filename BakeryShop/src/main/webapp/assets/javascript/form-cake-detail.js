let cakeDetailContent = document.querySelector(".cake-detail-content");
let toppings = cakeDetailContent.querySelectorAll(".topping-list .topping-item");
let toppingsPrice = 0;
let buyQuanCurrent = 1;
let buyQuantity = cakeDetailContent.querySelector("input[name='buy-quantity']");
let decreaseBtn = cakeDetailContent.querySelector("button[value='decrease']");
let increaseBtn = cakeDetailContent.querySelector("button[value='increase']");
let priceTag = cakeDetailContent.querySelector(".price-quantity .price").childNodes[0];
let cakeQuantity = parseInt(cakeDetailContent.querySelector("input[name='cake-quantity']").getAttribute("value"));

const orgPrice = parseInt(priceTag.nodeValue);

function updatePrice() {
    priceTag.nodeValue = buyQuanCurrent * (orgPrice + toppingsPrice);
}

toppings.forEach(function (topping) {
    topping.addEventListener("click", function (event) {
        topping.classList.toggle("active");        
        if (topping.classList.contains("active")) {
            toppingsPrice += parseInt(topping.querySelector("a").getAttribute("data-price"));
        } else {
            toppingsPrice -= parseInt(topping.querySelector("a").getAttribute("data-price"));
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
