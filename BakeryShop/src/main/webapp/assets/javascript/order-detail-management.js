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

