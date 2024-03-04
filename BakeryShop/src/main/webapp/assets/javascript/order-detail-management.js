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
    // Tạo ID duy nhất cho topping section bằng cách kết hợp cardIndex và CakeID
    var toppingSectionID = `topping-section-${cardIndex}-${CakeID}`;
    var toppingSection = document.querySelector(`#${toppingSectionID}`);
    var icon = document.getElementById("icon-" + CakeID);
        console.log(toppingSection);
        toppingSection.style.display = (toppingSection.style.display === "none") ? "block" : "none";
        icon.className = (toppingSection.style.display === "none") ? "bi bi-chevron-double-down down-icon" : "bi bi-chevron-double-up up-icon";

    // TODO: Sử dụng AJAX hoặc các phương pháp khác để động cơ hóa và điền nội dung topping
    // Đối với đơn giản, bạn có thể trực tiếp bao gồm nội dung topping trong HTML hoặc lấy nó từ máy chủ.
}

function showPopup() {
    document.getElementById("acceptOrderPopup").style.display = "block";
}

function closePopup() {
    document.getElementById("acceptOrderPopup").style.display = "none";
}

function confirmAccept() {
    // Thực hiện xác nhận và đóng popup
    // ...
    closePopup();
}

function showContactPopup() {
    document.getElementById("contactPopup").style.display = "block";
}

function closeContactPopup() {
    document.getElementById("contactPopup").style.display = "none";
}

