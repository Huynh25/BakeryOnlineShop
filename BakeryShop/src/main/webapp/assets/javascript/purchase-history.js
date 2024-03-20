let orderDateFilter = "";
let receivedDateFilter = "";
let currentPage = 1;
let currentActiveFilter = "all";
let searchInput = "";
let allowClick = true;
let ratingValue = 0;
let ratingInCake = null;
let cakeID;
let comment = "";
let indexI;
let indexJ;
function performFilter(filter) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "order-history", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            console.log(data);
            ratingInCake = data.ratingInCake;
            if (filter !== "search" && filter !== "order-date"
                    && filter !== "order-receive" && filter !== "Next-page"
                    && filter !== "Back-page") {
                let preActiveFilterButton = document.getElementsByClassName("nav-bar--active")[0];
                let nextActiveFilterButton = document.getElementById(filter + "-filter");
                preActiveFilterButton.querySelector(".nav-bar-items").disabled = false;
                nextActiveFilterButton.querySelector(".nav-bar-items").disabled = true;
                preActiveFilterButton.classList.remove('nav-bar--active');
                nextActiveFilterButton.classList.add('nav-bar--active');
            }
            document.getElementById("order-content-container").remove();
            let newOrderContainer = document.createElement("div");
            newOrderContainer.id = "order-content-container";
            var totalNumberPage = Math.ceil(data.numberCurrentOrder / 8);
            newOrderContainer.innerHTML = `
                <div id="all-order-info">                 
                    <h5 id="number-order">${data.numberCurrentOrder} Orders</h5>
                    <div id="page-info">
                        Page ${currentPage} of ${totalNumberPage === 0 ? "1" : totalNumberPage}
                        <i id="back-page-btn" ${currentPage === 1 ? "disabled" : `onclick="performFilter('Back-page')"`}  class="bi bi-chevron-left ${currentPage === 1 ? "disable-icon" : currentPage}" ></i>
                        <i id="next-page-btn" ${currentPage === totalNumberPage ? 'disabled' : `onclick="performFilter('Next-page')"`} class="bi bi-chevron-right ${currentPage === totalNumberPage ? 'disable-icon' : ''}"></i>
                    </div>
                </div>`;
            if (totalNumberPage === 0) {
                totalNumberPage = 1;
                newOrderContainer.innerHTML += `<h1 style="text-align:center">There aren't any Orders</h1>`;
            }
            const order = data.jsonOrderList;
            const orderLength = order.length;
            let cakeCardIndex = 0;
            for (var i = 0; i < orderLength; i++) {
                newOrderContainer.innerHTML += `  
                    <div class="order-content row" id="order-content-${order[i].orderID}">
                        <div class="col-sm-7 row order-content-total-info">
                            <div class="order-id col-sm-2">#${order[i].orderID}</div>
                            <div class="order-content-date col-sm-3"><i class="bi bi-cart"></i> ${convertDateFormat(order[i].orderDate)}</div>
                            <div class="order-receive-date col-sm-3"><i class="bi bi-truck"></i> ${convertDateFormat(order[i].receivedDate)}</div>        
                            <div class="order-total-price col-sm-4">Total Price: ${order[i].totalPrice.toLocaleString('vi-VN')}đ</div>
                        </div>                   
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4 row order-content-status-info">
                            <div class="order-staus col-sm-6">Status: ${order[i].status}</div>
                            <div class="order-staffID col-sm-6">Staff <i class="bi bi-person-check"></i> #${order[i].staffID === 0 ? 'Undefined' : order[i].staffID}</div>
                        </div>
                    </div>
                    `;
                const cakeInOrder = data.jsonCakeInOrder[i];
                var cakeInOrderLength = cakeInOrder.length;
                var orderDetailContent = document.createElement("div");
                if (cakeInOrderLength > 3) {
                    cakeInOrderLength = 3;
                }
                orderDetailContent.classList.add('orderdDetail-content', 'row');
                orderDetailContent.id = 'orderdDetail-content-' + order[i].orderID;
                orderDetailContent.innerHTML = ` 
                        <div class="col-sm-6 received-btn" ${order[i].status === "Delivering" ? "" : "hidden"}>
                                <button onclick="receivedOrder(${order[i].orderID})">Received</button>
                                    </div>
                        <div class="${order[i].status === "Delivering" ? "col-sm-6" : "col-sm-12"} col-sm-6 order-Description">
                            ${order[i].orderDescription}
                        </div>   `;
                for (let j = 0; j < cakeInOrderLength; j++) {
                    const ratingValue = ratingInCake[i][j].ratingValue;
                    orderDetailContent.innerHTML += `                               
                                <div class="orderDetail-card orderDetail-card--unshowTopping col-sm-3" id="orderDetail-card-${cakeInOrder[j].cioID}">
                                <div class="orderDetail-cakeName">${cakeInOrder[j].cake.cakeName}</div>
                                <div class="cake-info row">                          
                                    <img class="orderDetail-cakeImage col-sm-6" src="./../../${cakeInOrder[j].cake.cakeImg}"/>
                                    <div class="col-sm-6 row card-info">
                                        <div class="orderDetail-cakePrice col-sm-12">Price:${cakeInOrder[j].cake.cakePrice.toLocaleString('vi-VN')}đ
                                        </div>                                    
                                        <div class="orderDetail-cakeQuantity col-sm-12">Quantity: ${cakeInOrder[j].cioQuantity}</div>
                                            <div class="rate col-sm-12 cake-index-${cakeInOrder[j].cake.cakeID} index-array-${i}-${j}" id="${cakeCardIndex}">
                                                <input type="radio" id="star5-${cakeCardIndex}" name="rate-${cakeCardIndex}" value="5" ${ratingValue === 5 ? "checked" : ""} />
                                                <label for="star5-${cakeCardIndex}" title="text">5 stars</label>
                                                <input type="radio" id="star4-${cakeCardIndex}" name="rate-${cakeCardIndex}" value="4" ${ratingValue === 4 ? "checked" : ""}/>
                                                <label for="star4-${cakeCardIndex}" title="text">4 stars</label>
                                                <input type="radio" id="star3-${cakeCardIndex}" name="rate-${cakeCardIndex}" value="3" ${ratingValue === 3 ? "checked" : ""}/>
                                                <label for="star3-${cakeCardIndex}" title="text">3 stars</label>
                                                <input type="radio" id="star2-${cakeCardIndex}" name="rate-${cakeCardIndex}" value="2" ${ratingValue === 2 ? "checked" : ""}/>
                                                <label for="star2-${cakeCardIndex}" title="text">2 stars</label>
                                                <input type="radio" id="star1-${cakeCardIndex}" name="rate-${cakeCardIndex}" value="1" ${ratingValue === 1 ? "checked" : ""}/>
                                                <label for="star1-${cakeCardIndex}" title="text">1 stars</label>                   
                                            </div>
                                        <div class="showTopping-btn-cotainer col-sm-12">                                    
                                            <button class="showTopping-btn" onclick="ShowTopping(${cakeInOrder[j].cioID},${cakeInOrder[j].cioQuantity * cakeInOrder[j].cake.cakePrice})">Show Topping <i id class="bi bi-chevron-double-down down-icon"></i></button>                           
                                        </div>
                                    </div>
                                </div>
                                <div class="orderDetail-totalPrice">Total Cake Price: ${(cakeInOrder[j].cioQuantity * cakeInOrder[j].cake.cakePrice).toLocaleString('vi-VN')}đ
                                </div>
                            </div>`;
                    cakeCardIndex++;
                }
                if (cakeInOrder.length > 3) {
                    orderDetailContent.innerHTML += ` <div class="col-sm-12 showMore-btn-cotainer">                      
                            <button class="showMore-btn " onclick="showMore(${order[i].orderID},3)">Show More <i id class="bi bi-chevron-double-down down-icon"></i></button>
                        </div>`;
                }
                newOrderContainer.appendChild(orderDetailContent);
            }
            document.getElementById("content").appendChild(newOrderContainer);
        }
    };
    searchInput = document.getElementById('search-order-input').value;
    if (filter !== "search" && filter !== "order-date" && filter !== "order-receive") {
        if (filter === "Next-page") {
            currentPage += 1;
        } else if (filter === "Back-page") {
            currentPage -= 1;
        } else {
            currentActiveFilter = filter;
            currentPage = 1;
        }
    } else if (filter === 'order-date') {
        orderDateFilter = document.getElementById(filter).value;
    } else if (filter === 'order-receive') {
        receivedDateFilter = document.getElementById(filter).value;
    }

    xhr.send("filter=" + currentActiveFilter + "&numberPage=" + currentPage + "&search=" + searchInput + "&order-date=" + orderDateFilter + "&received-date=" + receivedDateFilter);

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
function ShowTopping(cioID, cioPrice) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "order-history", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            var cardShowTopping = document.getElementById("orderDetail-card-" + cioID);
            cardShowTopping.classList.remove('orderDetail-card--unshowTopping');
            cardShowTopping.querySelector('.showTopping-btn').remove();
            cardShowTopping.querySelector('.orderDetail-totalPrice').remove();
            cardShowTopping.querySelector('.showTopping-btn-cotainer').innerHTML = `<button class="showLess-btn" onclick="showLessTopping(${cioID},${cioPrice})">Show Less <i class="bi bi-chevron-double-up"></i></button> `;
            let toppingInfo = document.createElement("div");
            toppingInfo.classList.add("toppingInfo", "col-sm-12");
            const toppingInCakeLength = data.length;
            let toppingPrice = 0;
            for (let i = 0; i < toppingInCakeLength; i++) {
                toppingPrice += data[0].topping.toppingPrice * data[0].ticQuantity;
                toppingInfo.innerHTML += `<div>${data[0].topping.toppingName}:
                                            <div class="toppingDetail">
                                            <div class="toppingDetail-price">Price: ${data[0].topping.toppingPrice.toLocaleString('vi-VN')}đ</div>
                                            <div class="toppingDetail-quantity">Quantity: ${data[0].ticQuantity}</div>
                                            </div>              
                                          </div>`;
            }
            cardShowTopping.appendChild(toppingInfo);
            cardShowTopping.innerHTML += `<div class="orderDetail-totalPrice">Total Price: ${(cioPrice + toppingPrice).toLocaleString('vi-VN')}đ
                                </div>`;
        }
    };
    xhr.send("cioID=" + cioID);
}
function showLessTopping(cioID, cioPrice) {
    var cardShowTopping = document.getElementById("orderDetail-card-" + cioID);
    cardShowTopping.classList.add('orderDetail-card--unshowTopping');
    cardShowTopping.querySelector('.showLess-btn').remove();
    cardShowTopping.querySelector('.orderDetail-totalPrice').remove();
    cardShowTopping.querySelector('.showTopping-btn-cotainer').innerHTML = ` <button class="showTopping-btn" onclick="ShowTopping(${cioID},${cioPrice})">Show Topping <i id class="bi bi-chevron-double-down down-icon"></i></button>`;
    cardShowTopping.querySelector('.toppingInfo').remove();
    cardShowTopping.innerHTML += `<div class="orderDetail-totalPrice">Total Cake Price: ${(cioPrice).toLocaleString('vi-VN')}đ
                                </div>`;
}
function showMore(orderID, currentQuantity) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "order-history", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            let orderNeedShowMore = document.getElementById("orderdDetail-content-" + orderID);
            orderNeedShowMore.querySelector(".showMore-btn-cotainer").remove();
            const cioLength = data.length;
            if (cioLength > 3) {
                cioLength = 3;
            }
            for (var i = 0; i < cioLength; i++) {
                orderNeedShowMore.innerHTML += `<div class="orderDetail-card orderDetail-card--unshowTopping col-sm-3" id="orderDetail-card-${data[i].cioID}">
                                <div class="orderDetail-cakeName">${data[i].cake.cakeName}</div>
                                <div class="cake-info row">                          
                                    <img class="orderDetail-cakeImage col-sm-6" src="./../../${data[i].cake.cakeImg}"/>
                                    <div class="col-sm-6">
                                        <div class="orderDetail-cakePrice">Price:${data[i].cake.cakePrice.toLocaleString('vi-VN')}đ
                                        </div>
                                        <div class="orderDetail-cakeQuantity">Quantity: ${data[i].cioQuantity}</div>                                 
                                        <div class="showTopping-btn-cotainer">                                    
                                            <button class="showTopping-btn" onclick="ShowTopping(${data[i].cioID},${data[i].cioQuantity * data[i].cake.cakePrice})">Show Topping <i id class="bi bi-chevron-double-down down-icon"></i></button>                           
                                        </div>
                                    </div>
                                </div>
                                <div class="orderDetail-totalPrice">Total Cake Price: ${(data[i].cioQuantity * data[i].cake.cakePrice).toLocaleString('vi-VN')}đ
                                </div>
                            </div>`;
            }
            if (data.length > 3) {
                orderNeedShowMore.innerHTML += ` <div class="col-sm-12 showMore-btn-cotainer">                      
                            <button class="showMore-btn " onclick="showMore(${orderID},${currentQuantity + 3})">Show More <i id class="bi bi-chevron-double-down down-icon"></i></button>
                        </div>`;
            }
        }
    };
    xhr.send("orderID=" + orderID + "&currentQuantity=" + currentQuantity);
}

$(document).on("click", ".rate", function (event) {
    if (!allowClick) {
        event.preventDefault();
        event.stopPropagation();
    } else {
        const id = this.id;
        document.getElementById('ratingPopup').style.display = "block";
        for (var i = 1; i <= 5; i++) {
            const rating = this.querySelector(`#star` + i + "-" + id);
            if (rating.checked) {
                ratingValue = i;
            }
        }
        ;
        let ratingConvert;
        if (ratingValue === 5) {
            ratingConvert = 1;
        } else if (ratingValue === 4) {
            ratingConvert = 2;
        } else if (ratingValue === 3) {
            ratingConvert = 3;
        } else if (ratingValue === 2) {
            ratingConvert = 4;
        } else if (ratingValue === 1) {
            ratingConvert = 5;
        }
        if (ratingValue > 0) {
            cakeID = this.classList[2].split("cake-index-")[1];
            const listCakeRating = document.getElementsByClassName(this.classList[2]);
            const  splitIndex = this.classList[3].split("index-array-")[1];
            indexI = splitIndex.split("-")[0];
            indexJ = splitIndex.split("-")[1];
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "order-history", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    const data = JSON.parse(xhr.responseText);
                    ratingInCake = data.ratingInCake;
                    document.getElementById('comment').value = getComment();
                }
            };
            xhr.send("ratingValue=" + ratingValue);
            for (let i = 0; i < listCakeRating.length; i++) {
                const cakeRating = listCakeRating[i];
                const inputElements = cakeRating.querySelectorAll('input');
                inputElements[ratingConvert - 1].click();
            }
            setTimeout(function () {
                allowClick = false;
            }, 100);
        }
    }
});
function getComment() {
    const ratingInCakeLength = ratingInCake.length;
    for (var i = 0; i < ratingInCakeLength; i++) {
        const ratingListLength = ratingInCake[0].length;
        for (var j = 0; j < ratingListLength; j++) {
            if (parseInt(ratingInCake[i][j].cake.cakeID) === parseInt(cakeID)) {
                return ratingInCake[i][j].comment;
            }
        }
    }
}
function handleRating() {
    allowClick = true;
    document.getElementById('ratingPopup').style.display = "none";
    comment = document.getElementById('comment').value;
    console.log(comment);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "order-history", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            ratingInCake = data.ratingInCake;
        }
    };
    xhr.send("cakeID=" + cakeID + "&ratingValue=" + ratingValue + "&comment=" + comment);
}
function ClosePopup() {
    allowClick = true;
    document.getElementById('ratingPopup').style.display = "none";
}
function receivedOrder(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "order-history", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
          document.getElementById('order-content-'+id).remove();          
          document.getElementById('orderdDetail-content-'+id).remove();
        }
    };
    xhr.send("orderID=" + id);
}