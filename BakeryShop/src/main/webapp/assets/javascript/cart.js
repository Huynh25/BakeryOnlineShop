let popup = document.querySelector(".popup");
let openCartClasses = document.querySelectorAll(".openCart");
let cartPopup = popup.querySelector(".cart-popup");
let increaseButtons = cartPopup.querySelectorAll(".inc-btn");
let decreaseButtons = cartPopup.querySelectorAll(".dec-btn");
let buyQuantityTexts = cartPopup.querySelectorAll(".buy-quan-text");
let removeButtons = cartPopup.querySelectorAll(".rem-btn");

const openCart = () => {
    popup.classList.add("open");
};

const closeCart = () => {
    popup.classList.remove("open");
};

openCartClasses.forEach((openCartClass) => {
    openCartClass.addEventListener("click", (e) => {
        e.preventDefault();
        openCart();
    });
});

popup.addEventListener("click", closeCart);
cartPopup.addEventListener("click", (e) => e.stopPropagation());

increaseButtons.forEach((increaseBtn) => {
    increaseBtn.addEventListener("click", (e) => {
        incFunc(e);
    });
});

decreaseButtons.forEach((decreaseBtn) => {
    decreaseBtn.addEventListener("click", (e) => {
        decFunc(e);
    });
});

buyQuantityTexts.forEach((buyQuanText) => {
    buyQuanText.addEventListener("change", (e) => {
        changeFunc(e);
    });
});

removeButtons.forEach((removeBtn) => {
    removeBtn.addEventListener("click", (e) => {
        remFunc(e);
    });
});

function rerender( {product, cartPrice, productPrice, quantity}, element, type = "update") {
    let cartInfoElement = document.querySelector(".cart-popup .cart-info");
    let productInCartElement = cartInfoElement.querySelector(".quantity");
    let priceInCartElement = cartInfoElement.querySelector(".price");
    let quanElement = element.querySelector(".buy-quan-text");
    let productPriceElement = element.querySelector(".cake-price");
    
    productInCartElement.childNodes[0].nodeValue = product + " products";
    priceInCartElement.childNodes[0].nodeValue = cartPrice;

    switch (type) {
        case "update":
            quanElement.setAttribute("value", quantity);
            quanElement.value = quantity;
            productPriceElement.childNodes[0].nodeValue = " " + productPrice + " ";
            break;
        case "delete":
            let cartItem = element.parentElement.parentElement.parentElement;
            let cartElement = cartItem.parentElement;
            cartElement.removeChild(cartItem);
            break;
}


}

function doAction(data, callback) {
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", "editcart", true);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            callback(xhttp.responseText);
        }
    };
    xhttp.send(data);
}

function getData(event, type) {
    let element = event.target.parentElement.parentElement.parentElement;
    if (type !== "remove") {
        element = element.parentElement;
    }
    let cakeIDValue = element.querySelector(".cart-item-id").getAttribute("value");
    let toppingsIDElement = element.querySelectorAll(".cart-item-topping");
    let toppingsIDValues = [];
    toppingsIDElement.forEach((toppingElement) => {
        toppingsIDValues.push("&toppingID=" + toppingElement.getAttribute("value"));
    });
    
    let value = event.target.parentElement.parentElement.querySelector(".buy-quan-text").getAttribute("value");
    value = (type === "change" ? event.target.value : value);
    
    let data = `type=${type}&cakeID=${cakeIDValue}&value=${value}${toppingsIDValues.join("")}`;
    return data;
}


function getIndex(event, type = "update") {
    let id = event.target.parentElement.parentElement;
    switch (type) {
        case "update":
            id = id.parentElement.childNodes[1].getAttribute("id");
            break;
        case "delete":
            id = id.childNodes[1].getAttribute("id");
            break;
    }

    id = id.substring(5, id.length);
    return id;
}

function incFunc(event) {
    let data = getData(event, "increase");
    let cartItem = event.target.parentElement.parentElement.parentElement.parentElement;
    doAction(data, (text) => {
        rerender(JSON.parse(text), cartItem);
    });
}

function decFunc(event) {
    let data = getData(event, "decrease");
    let cartItem = event.target.parentElement.parentElement.parentElement.parentElement;
    doAction(data, (text) => {
        rerender(JSON.parse(text), cartItem);
    });

}

function changeFunc(event) {
    let data = getData(event, "change");
    let cartItem = event.target.parentElement.parentElement.parentElement.parentElement;
    doAction(data, (text) => {
        rerender(JSON.parse(text), cartItem);
    });
}

function remFunc(event) {
    let data = getData(event, "remove");
    doAction(data, (text) => {
        rerender(JSON.parse(text), event.target, "delete");
    });

}