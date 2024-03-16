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
let maxCake = cakeQuantity;
const alertNode = document.createElement("div");
alertNode.setAttribute("class", ["row align-items-center justify-content-start alert"]);
alertNode.textContent = "You must choose one of these toppings !";

const orgPrice = parseInt(priceTag.nodeValue);

function updatePrice() {
    priceTag.nodeValue = buyQuanCurrent * (orgPrice + toppingsPrice);
}

function updateMaxCake() {
    if (cakeQuantity === 0) {
        maxCake = 0;
        return;
    }

    maxCake = cakeQuantity;
    let toppingsActive = cakeDetailContent.querySelectorAll(".topping-list .topping-item.active a");
    let minQuanToppingActive = 1;
    for (let idx = 0; idx < toppingsActive.length; ++idx) {
        let quan = parseInt(toppingsActive[idx].getAttribute("data-quan"));
        if (idx === 0) {
            minQuanToppingActive = quan;
        } else if (quan < minQuanToppingActive) {
            minQuanToppingActive = quan;
        }
    }

    if (maxCake > minQuanToppingActive) {
        maxCake = minQuanToppingActive;
    }
}

function updateBuyQuantity() {
    buyQuantity = cakeDetailContent.querySelector("input[name='buy-quantity']");

    if (buyQuanCurrent > maxCake) {
        buyQuanCurrent = maxCake;
    }

    buyQuantity.setAttribute("value", buyQuanCurrent);
    buyQuantity.value = buyQuanCurrent;
}

toppings.forEach(function (topping) {
    topping.addEventListener("click", function (event) {
        const toppingPrice = parseInt(topping.querySelector("a").getAttribute("data-price"));
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

        } else {
            --numberOfTopping;
            toppingsPrice -= toppingPrice;

        }

        updateMaxCake();
        updateBuyQuantity();
        updatePrice();
    });
});

buyQuantity.addEventListener("change", function (event) {
    if (numberOfTopping === 0) {
        let rightSide = document.querySelector(".cake-detail-content .cake-attribute");
        rightSide.prepend(alertNode);
        return;
    }

    if (isNaN(event.target.value)) {
        event.target.value = 1;
        return;
    }

    updateMaxCake();
    if (cakeQuantity === 0) {
        event.target.value = 0;
    } else if (event.target.value > maxCake) {
        event.target.value = maxCake;
    } else if (event.target.value < 1) {
        event.target.value = 1;

    }
    buyQuanCurrent = parseInt(event.target.value);
    buyQuantity.setAttribute("value", buyQuanCurrent);

    updatePrice();
});

decreaseBtn.addEventListener("click", function () {
    if (numberOfTopping === 0) {
        let rightSide = document.querySelector(".cake-detail-content .cake-attribute");
        rightSide.prepend(alertNode);
        return;
    }

    buyQuanCurrent = parseInt(buyQuantity.getAttribute("value"));
    if (buyQuanCurrent > 1) {
        --buyQuanCurrent;
    } else if (cakeQuantity === 0) {
        buyQuanCurrent = 0;
    } else {
        buyQuanCurrent = 1;
    }

    buyQuantity.setAttribute("value", buyQuanCurrent);
    buyQuantity.value = buyQuanCurrent;
    updatePrice();
});

increaseBtn.addEventListener("click", function () {
    if (numberOfTopping === 0) {
        let rightSide = document.querySelector(".cake-detail-content .cake-attribute");
        rightSide.prepend(alertNode);
        return;
    }

    buyQuanCurrent = parseInt(buyQuantity.getAttribute("value"));
    updateMaxCake();
    if (cakeQuantity === 0) {
        buyQuanCurrent = 0;
    } else if (buyQuanCurrent < maxCake) {
        ++buyQuanCurrent;
    } else {
        buyQuanCurrent = maxCake;
    }
    buyQuantity.setAttribute("value", buyQuanCurrent);
    buyQuantity.value = buyQuanCurrent;
    updatePrice();
});
