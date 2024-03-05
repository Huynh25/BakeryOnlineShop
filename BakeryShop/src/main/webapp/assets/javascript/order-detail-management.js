document.addEventListener('DOMContentLoaded', function () {
    const unconfirmedOrderButton = document.querySelector('.unconfirmed-order-btn');

    unconfirmedOrderButton.addEventListener('click', function () {
        window.location.href = 'UnconfirmedOrders'; 
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const myConfirmedOrder = document.querySelector('.my-confirmed-order-btn');

    myConfirmedOrder.addEventListener('click', function () {
        window.location.href = 'MyConfirmedOrders'; 
    });
});

function showTopping(cardIndex, CakeID) {
    var toppingSectionID = `topping-section-${cardIndex}-${CakeID}`;
    var toppingSection = document.querySelector(`#${toppingSectionID}`);
    var icon = document.getElementById("icon-" + CakeID);
        console.log(toppingSection);
        toppingSection.style.display = (toppingSection.style.display === "none") ? "block" : "none";
        icon.className = (toppingSection.style.display === "none") ? "bi bi-chevron-double-down down-icon" : "bi bi-chevron-double-up up-icon";

}

function showPopup() {
    document.getElementById("acceptOrderPopup").style.display = "block";
}

function closePopup() {
    document.getElementById("acceptOrderPopup").style.display = "none";
}

function confirmAccept(orderID) {
    console.log("hello");

    var encodedOrderID = encodeURIComponent(orderID);

    var url = 'AcceptOrder?orderID=' + encodedOrderID;
    console.log("Redirecting to URL:", url);
    window.location.href = url;
    closePopup();
}

function showContactPopup() {
    document.getElementById("contactPopup").style.display = "block";
}

function closeContactPopup() {
    document.getElementById("contactPopup").style.display = "none";
}

