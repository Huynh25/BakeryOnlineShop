document.addEventListener('DOMContentLoaded', function () {
    const xhr = new XMLHttpRequest();
    const url = 'MyConfirmedOrders';
    const method = 'POST';
    const contentType = 'application/json;charset=UTF-8';
    const orderListContainer = document.getElementById('orderList');
    const currentPageSpan = document.getElementById('currentPage');
    const totalPagesSpan = document.getElementById('totalPages');
    const searchTerm = document.getElementById('searchTerm');
    const searchOrderButton = document.querySelector('.search-order-btn'); // Thêm class cho nút search
    let currentPage = 1;
    let totalPages = 1;

    function fetchData(page) {
        xhr.open(method, url, true);
        xhr.setRequestHeader('Content-Type', contentType);

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const data = JSON.parse(xhr.responseText);
                totalPages = Math.ceil(data.length / 8);
                displayOrders(data.slice((page - 1) * 8, page * 8));
                totalPagesSpan.textContent = totalPages;
            }
        };

        xhr.send(JSON.stringify({ page: page }));
        console.log(searchTerm.value);
    }

    function displayOrders(orders) {
        const orderListHTML = orders.map(order => `
            <div class="col-sm-3">
                <div class="Order-card" onclick="redirectToOrderDetailPage(${order.orderID})">
                    <div class="Order-title d-flex align-items-center justify-content-center">
                        <p class="Order-id">Order ID: #${order.orderID}</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Order Date: ${convertDateFormat(order.orderDate)}</p>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">Status: ${order.status}</p>
                        <div class="rounded-circle circle ${order.status === 'Waiting' ? 'circle-blue' : (order.status === 'Delivering' ? 'circle-yellow' : 'circle-green')}"></div>
                    </div>
                    <div class="Order-content d-flex align-items-center justify-content-start">
                        <p class="Order-content-text">
                            ${order.wasPaid ? 'Already paid: Yes' : 'Already paid: No'}
                        </p>
                        <div class="rounded-circle circle ${order.wasPaid ? 'Paid' : 'NotPaid'}"></div>
                    </div>
                    <div class="Order-total-price d-flex align-items-center justify-content-end">
                        <p class="Total-price">Total Price: ${order.totalPrice.toLocaleString('vi-VN')}đ </p>
                    </div>
                </div>
            </div>
        `).join('');

        orderListContainer.innerHTML = orderListHTML;
    }

    const prevPageButton = document.querySelector('.prev-page');
    const nextPageButton = document.querySelector('.next-page');

    prevPageButton.addEventListener('click', function () {
        if (currentPage > 1) {
            currentPage--;
            fetchData(currentPage);
            currentPageSpan.textContent = currentPage;
        }
    });

    nextPageButton.addEventListener('click', function () {
        if (currentPage < totalPages) {
            currentPage++;
            fetchData(currentPage);
            currentPageSpan.textContent = currentPage;
        }
    });

    fetchData(currentPage);
});

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

function redirectToOrderDetailPage(orderID) {
    console.log("hello");
    var encodedPrePage = encodeURIComponent("My Confirmed Orders");
    var encodedURL = encodeURIComponent("MyConfirmedOrders");
    var encodedOrderID = encodeURIComponent(orderID);

    var url = 'OrderDetailManagement?orderID=' + encodedOrderID + '&prePage=' + encodedPrePage + '&preURL=' + encodedURL;
    console.log("Redirecting to URL:", url);
    window.location.href = url;
}

function getServletUrl() {
        return searchTerm ? 'SearchOrders' : 'MyConfirmedOrderController';
    }
    
    function convertDateFormat(dateString) {
    if (!dateString)
        return "Undefined";
    var date = new Date(dateString);
    var year = date.getFullYear();
    var month = ("0" + (date.getMonth() + 1)).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);
    var formattedDate = day + "-" + month + "-" + year;
    return formattedDate;
}