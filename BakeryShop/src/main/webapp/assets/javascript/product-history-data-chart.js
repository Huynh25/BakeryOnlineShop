/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let productHistoryList;
let cakeList;
let toppingList;
let activeWeekAll;
let activeWeekOne;
let activeProduct = 0;
let curentPageAll = 1;
let weekList;
let activeTypeAll = "Cake";
let activeTypeOne = "Cake";
let statusAll = "Update";
let statusOne = "Update";
let myChart4;
let myChart3;
(function ($) {
    "use strict";
    // Chart Global Color
    Chart.defaults.color = "#6C7293";
    Chart.defaults.borderColor = "#000000";
    var ctx4 = $("#bar-chart").get(0).getContext("2d");
    var ctx3 = $("#line-chart").get(0).getContext("2d");
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "../../product-history-statistic", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var data = JSON.parse(xhr.responseText);
            productHistoryList = data.productHistoryList;
            cakeList = data.cakeList;
            toppingList = data.toppingList;
            console.log(productHistoryList);
            const productHistoryLength = productHistoryList.length;
            weekList = getWeeksBetweenDates(convertDateFormat(productHistoryList[productHistoryLength - 1].hasOwnProperty("updatedDate") ?
                    productHistoryList[productHistoryLength - 1].updatedDate :
                    productHistoryList[productHistoryLength - 1].createDate),
                    convertDateFormat(productHistoryList[0].hasOwnProperty("updatedDate") ?
                            productHistoryList[0].updatedDate :
                            productHistoryList[0].createDate));
            const weekListLength = weekList.length;
            const weekAllOptionDropdown = document.getElementById('weak-all-option-dropdown');
            activeWeekAll = weekListLength - 1;
            for (var i = weekListLength - 1; i >= 0; i--) {
                if (activeWeekAll === i) {
                    document.getElementById('weak-all-option-dropdown-btn').innerHTML = `${weekList[i].start} To ${weekList[i].end}`;
                    weekAllOptionDropdown.innerHTML += `<a class="dropdown-item active-all-product" id="week-bar-option-${i}" href="#">${weekList[i].start} To ${weekList[i].end}</a>`;
                } else {
                    weekAllOptionDropdown.innerHTML += `<a class="dropdown-item" href="#" id="week-bar-option-${i}">${weekList[i].start} To ${weekList[i].end}</a>`;
                }
            }
            const totalPage = Math.ceil(cakeList.length / 5);
            document.getElementById('chartBar-Pagination').innerHTML = `Chart ${curentPageAll} of ${totalPage}`;
            if (totalPage === 1) {
                document.getElementById('next-page-btn').classList.add('disable-icon');
            }
            updateAllChart();
            const weekOneOptionDropdown = document.getElementById('weak-one-option-dropdown');
            activeWeekOne = weekListLength - 1;
            for (var i = weekListLength - 1; i >= 0; i--) {
                if (activeWeekOne === i) {
                    document.getElementById('weak-one-option-dropdown-btn').innerHTML = `${weekList[i].start} To ${weekList[i].end}`;
                    weekOneOptionDropdown.innerHTML += `<a class="dropdown-item active-one-product" id="week-line-option-${i}" href="#">${weekList[i].start} To ${weekList[i].end}</a>`;
                } else {
                    weekOneOptionDropdown.innerHTML += `<a class="dropdown-item" href="#" id="week-line-option-${i}">${weekList[i].start} To ${weekList[i].end}</a>`;
                }
            }
            const productList = cakeList.map(product => product.cakeName);
            const productOptionDropdown = document.getElementById('productList-one-option-dropdown');
            const productListLength = productList.length;
            for (var i = 0; i < productListLength; i++) {
                if (i === activeProduct) {
                    document.getElementById('productList-one-option-dropdown-btn').innerHTML = productList[i];
                    productOptionDropdown.innerHTML += `<a class="dropdown-item active-one-product" id="product-line-option-${i}" href="#">${productList[i]}</a>`;
                } else {
                    productOptionDropdown.innerHTML += `<a class="dropdown-item" id="product-line-option-${i}" href="#">${productList[i]}</a>`;
                }
            }
            updateOneChart();
            const summarizeToppingList = [];
            const summarizeCakeList = [];
            const createdCakeList = [];
            const createdToppingList = [];
            const weakActiveList = startDateToWeak(weekListLength - 1);
            const cakeListLength = cakeList.length;
            const toppingListLength = toppingList.length;
            for (var i = 0; i < toppingListLength; i++) {
                summarizeToppingList.push({"productName": toppingList[i].toppingName, "quantityCreate": 0, "quantityUpdate": 0, "quantityUpdateWeak": 0});
                for (var j = 0; j < productHistoryLength; j++) {
                    if (productHistoryList[j].hasOwnProperty("topping") &&
                            productHistoryList[j].topping.toppingName === toppingList[i].toppingName) {
                        if (productHistoryList[j].hasOwnProperty("updatedDate")) {
                            summarizeToppingList[i].quantityUpdate += productHistoryList[j].phQuantity;
                            if (weakActiveList.includes(convertDateFormat(productHistoryList[j].updatedDate))) {
                                summarizeToppingList[i].quantityUpdateWeak += productHistoryList[j].phQuantity;
                            }
                        } else {
                            summarizeToppingList[i].quantityCreate += productHistoryList[j].phQuantity;
                            if (weakActiveList.includes(convertDateFormat(productHistoryList[j].createDate))) {
                                createdToppingList.push(toppingList[i].toppingName);
                            }
                        }

                    }
                }
            }
            for (var i = 0; i < toppingListLength; i++) {
                summarizeCakeList.push({"productName": cakeList[i].cakeName, "quantityCreate": 0, "quantityUpdate": 0, "quantityUpdateWeak": 0});
                for (var j = 0; j < productHistoryLength; j++) {
                    if (productHistoryList[j].hasOwnProperty("cake") &&
                            productHistoryList[j].cake.cakeName === cakeList[i].cakeName) {
                        if (productHistoryList[j].hasOwnProperty("updatedDate")) {
                            summarizeCakeList[i].quantityUpdate += productHistoryList[j].phQuantity;
                            if (weakActiveList.includes(convertDateFormat(productHistoryList[j].updatedDate))) {
                                summarizeCakeList[i].quantityUpdateWeak += productHistoryList[j].phQuantity;
                            }
                        } else {
                            summarizeCakeList[i].quantityCreate += productHistoryList[j].phQuantity;
                            if (weakActiveList.includes(convertDateFormat(productHistoryList[j].createDate))) {
                                createdCakeList.push(cakeList[i].cakeName);
                            }
                        }

                    }
                }
            }
            let maxToppingWeek = {"productName":"","quantityCreate": 0, "quantityUpdate": 0, "quantityUpdateWeak": 0};
            let maxTopping = {"productName":"","quantityCreate": 0, "quantityUpdate": 0, "quantityUpdateWeak": 0};
            let maxCakeWeek ={"productName":"","quantityCreate": 0, "quantityUpdate": 0, "quantityUpdateWeak": 0};
            let maxCake = {"productName":"","quantityCreate": 0, "quantityUpdate": 0, "quantityUpdateWeak": 0};
            const summarizeToppingListLength = summarizeToppingList.length;
            const summarizeCakeListLength = summarizeCakeList.length;
            for (var i = 0; i < summarizeToppingListLength; i++) {
                if (maxTopping.quantityUpdate < summarizeToppingList[i].quantityUpdate) {
                    maxTopping = summarizeToppingList[i];
                }
                if (maxToppingWeek.quantityUpdateWeak < summarizeToppingList[i].quantityUpdateWeak) {
                    maxToppingWeek = summarizeToppingList[i];
                }
            }
            for (var i = 0; i < summarizeCakeListLength; i++) {
                if (maxCake.quantityUpdate < summarizeCakeList[i].quantityUpdate) {
                    maxCake = summarizeCakeList[i];
                }
                if (maxCakeWeek.quantityUpdateWeak < summarizeCakeList[i].quantityUpdateWeak) {
                    maxCakeWeek = summarizeCakeList[i];
                }
            }
            
            console.log(maxToppingWeek);
            console.log(maxTopping);
            console.log(maxCakeWeek);
            console.log(maxCake);
            console.log(createdCakeList);
            console.log(createdToppingList);
            const summarizeContent = document.getElementById('summarize-content');
            summarizeContent.innerHTML += `<div>Cake has the largest number of updates: ${maxCake.productName} with quantity is ${maxCake.quantityUpdate}</div>`;
            summarizeContent.innerHTML += `<div>Cake has the largest number of updates this week:  ${maxCakeWeek.productName} with quantity is ${maxCakeWeek.quantityUpdateWeak}</div>`;
            summarizeContent.innerHTML += `<div>Topping has the largest number of updates: ${maxTopping.productName} with quantity is ${maxTopping.quantityUpdate}</div>`;
            summarizeContent.innerHTML += `<div>Topping has the largest number of updates this week: ${maxToppingWeek.productName} with quantity is ${maxToppingWeek.quantityUpdateWeak}</div>`;
            const createProduct=document.createElement('div');
            createProduct.innerHTML='The cakes created this week are: ';
            const createdCakeListLength=createdCakeList.length;
            for (var i = 0; i < createdCakeListLength; i++) {
                if(i===0){
                    createProduct.innerHTML+=`${createdCakeList[i]}`;
                }else{
                   createProduct.innerHTML+=`, ${createdCakeList[i]}`; 
                }               
            }
            createProduct.innerHTML+=' and the toppings created this week are: ';
            const createdToppingListLength=createdToppingList.length;
            for (var i = 0; i < createdToppingListLength; i++) {
                if(i===0){
                    createProduct.innerHTML+=`${createdToppingList[i]}`;
                }else{
                   createProduct.innerHTML+=`, ${createdToppingList[i]}`; 
                }               
            }
            summarizeContent.appendChild(createProduct);
        }
    }
    ;
    xhr.send();
    $(document).on("click", "#productList-one-option-dropdown .dropdown-item", function () {
        activeProduct = this.id.split("product-line-option-")[1];
        document.getElementById('productList-one-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('productList-one-option-dropdown').querySelector('.active-one-product').classList.remove('active-one-product');
        this.classList.add('active-one-product');
        updateOneChart();
    });
    $(document).on("click", "#type-one-option-dropdown .dropdown-item", function () {
        activeTypeOne = this.innerHTML;
        document.getElementById('type-one-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('type-one-option-dropdown').querySelector('.active-one-product').classList.remove('active-one-product');
        this.classList.add('active-one-product');
        let productList;
        let productOptionDropdown = document.getElementById('productList-one-option-dropdown');
        productOptionDropdown.innerHTML = "";
        if (activeTypeOne === "Cake") {
            productList = cakeList.map(product => product.cakeName);
        } else {
            productList = toppingList.map(product => product.toppingName);
        }
        console.log(productList);
        activeProduct = 0;
        const productListLength = productList.length;
        for (var i = 0; i < productListLength; i++) {
            if (i === activeProduct) {
                document.getElementById('productList-one-option-dropdown-btn').innerHTML = productList[i];
                productOptionDropdown.innerHTML += `<a class="dropdown-item active-one-product" id="product-line-option-${i}" href="#">${productList[i]}</a>`;
            } else {
                productOptionDropdown.innerHTML += `<a class="dropdown-item" id="product-line-option-${i}" href="#">${productList[i]}</a>`;
            }
        }

    });
    $(document).on("click", "#weak-one-option-dropdown .dropdown-item", function () {
        activeWeekOne = this.id.split("week-line-option-")[1];
        document.getElementById('weak-one-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('weak-one-option-dropdown').querySelector('.active-one-product').classList.remove('active-one-product');
        this.classList.add('active-one-product');
        updateOneChart();
    });
    $(document).on("click", "#option-one-satus-chart .dropdown-item", function () {
        statusOne = this.innerHTML;
        document.getElementById('option-one-status-chart-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('option-one-satus-chart').querySelector('.active-one-product').classList.remove('active-one-product');
        this.classList.add('active-one-product');
        updateOneChart();
    });
    function updateOneChart() {
        const productHistoryLength = productHistoryList.length;
        const chartOneData = [];
        const weekActive = startDateToWeak(activeWeekOne);
        for (var i = 0; i < 7; i++) {
            if (activeTypeOne === "Cake") {
                chartOneData.push({"date": weekActive[i], "data": 0});
                if (statusOne === "Update") {
                    for (var j = 0; j < productHistoryLength; j++) {
                        if (productHistoryList[j].hasOwnProperty("cake") &&
                                productHistoryList[j].cake.cakeName === cakeList[activeProduct].cakeName
                                && productHistoryList[j].hasOwnProperty("updatedDate")
                                && convertDateFormat(productHistoryList[j].updatedDate) === weekActive[i]) {
                            chartOneData[i].data += productHistoryList[j].phQuantity;
                        }
                    }
                } else {
                    for (var j = 0; j < productHistoryLength; j++) {
                        if (productHistoryList[j].hasOwnProperty("cake") &&
                                productHistoryList[j].cake.cakeName === cakeList[activeProduct].cakeName
                                && productHistoryList[j].hasOwnProperty("createDate")
                                && convertDateFormat(productHistoryList[j].createDate) === weekActive[i]) {
                            chartOneData[i].data += productHistoryList[j].phQuantity;
                        }
                    }
                }
            } else {
                chartOneData.push({"date": weekActive[i], "data": 0});
                if (statusOne === "Update") {
                    for (var j = 0; j < productHistoryLength; j++) {
                        if (productHistoryList[j].hasOwnProperty("topping") &&
                                productHistoryList[j].topping.toppingName === toppingList[activeProduct].toppingName
                                && productHistoryList[j].hasOwnProperty("updatedDate")
                                && convertDateFormat(productHistoryList[j].updatedDate) === weekActive[i]) {
                            chartOneData[i].data += productHistoryList[j].phQuantity;
                        }
                    }
                } else {
                    for (var j = 0; j < productHistoryLength; j++) {
                        if (productHistoryList[j].hasOwnProperty("topping") &&
                                productHistoryList[j].topping.toppingName === toppingList[activeProduct].toppingName
                                && productHistoryList[j].hasOwnProperty("createDate")
                                && convertDateFormat(productHistoryList[j].createDate) === weekActive[i]) {
                            chartOneData[i].data += productHistoryList[j].phQuantity;
                        }
                    }
                }
            }
        }
        if (myChart3) {
            myChart3.destroy();
        }
        myChart3 = new Chart(ctx3, {
            type: "line",
            data: {
                labels: chartOneData.map(product => product.date),
                datasets: [{
                        label: cakeList[activeProduct].cakeName,
                        fill: true,
                        backgroundColor: "rgba(235, 22, 22, .7)",
                        data: chartOneData.map(product => product.data)
                    }]
            },
            options: {
                responsive: true
            }
        });
    }
    $(document).on("click", "#back-page-btn,#next-page-btn", function () {
        let totalPage = 1;
        if (activeTypeAll === "Cake") {
            totalPage = Math.ceil(cakeList.length / 5);
        } else {
            totalPage = Math.ceil(toppingList.length / 5);
        }
        if (this.id === 'back-page-btn') {
            if (curentPageAll !== 1) {
                if (curentPageAll === 2) {
                    document.getElementById('back-page-btn').classList.add('disable-icon');
                    document.getElementById('next-page-btn').classList.remove('disable-icon');
                }
                curentPageAll--;
            }
        } else {
            if (totalPage >= curentPageAll + 1) {
                if (curentPageAll === 1) {
                    document.getElementById('back-page-btn').classList.remove('disable-icon');
                }
                if (totalPage === curentPageAll + 1) {
                    document.getElementById('next-page-btn').classList.add('disable-icon');
                }
                curentPageAll++;
            }
        }
        document.getElementById('chartBar-Pagination').innerHTML = `Chart ${curentPageAll} of ${totalPage}`;
        updateAllChart();
    });
    $(document).on("click", "#weak-all-option-dropdown .dropdown-item", function () {
        activeWeekAll = this.id.split('week-bar-option-')[1];
        document.getElementById('weak-all-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('weak-all-option-dropdown').querySelector('.active-all-product').classList.remove('active-all-product');
        this.classList.add('active-all-product');
        updateAllChart();
    });
    $(document).on("click", "#option-all-type-chart .dropdown-item", function () {
        activeType = this.innerHTML;
        let totalPage = 1;
        if (activeTypeAll === "Cake") {
            totalPage = Math.ceil(cakeList.length / 5);
        } else {
            totalPage = Math.ceil(toppingList.length / 5);
        }
        if (totalPage === 1) {
            document.getElementById('next-page-btn').classList.add('disable-icon');
        } else {
            document.getElementById('next-page-btn').classList.remove('disable-icon');
        }
        document.getElementById('back-page-btn').classList.add('disable-icon');
        curentPageAll = 1;
        document.getElementById('chartBar-Pagination').innerHTML = `Chart ${curentPageAll} of ${totalPage}`;
        document.getElementById('option-all-type-chart-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('option-all-type-chart').querySelector('.active-all-product').classList.remove('active-all-product');
        this.classList.add('active-all-product');
        updateAllChart();
    });
    $(document).on("click", "#option-all-satus-chart .dropdown-item", function () {
        statusAll = this.innerHTML;
        document.getElementById('option-all-status-chart-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('option-all-satus-chart').querySelector('.active-all-product').classList.remove('active-all-product');
        this.classList.add('active-all-product');
        updateAllChart();
    });
    function updateAllChart() {
        const productHistoryLength = productHistoryList.length;
        const chartAllData = [];
        if (activeTypeAll === "Cake") {
            const cakeListLength = cakeList.length;
            const currentOnePageLength = (curentPageAll - 1) * 5 + 5 > cakeListLength ? cakeListLength : (curentPageAll - 1) * 5 + 5;
            for (var i = (curentPageAll - 1) * 5; i < currentOnePageLength; i++) {
                chartAllData.push({"productName": cakeList[i].cakeName, "quantity": 0});
            }
            const weekActiveList = startDateToWeak(activeWeekAll);
            const chartAllDataLength = chartAllData.length;
            if (statusAll === "Update") {
                for (var i = 0; i < chartAllDataLength; i++) {
                    for (var j = 0; j < productHistoryLength; j++) {
                        if (productHistoryList[j].hasOwnProperty("cake") &&
                                productHistoryList[j].cake.cakeName === chartAllData[i].productName &&
                                productHistoryList[j].hasOwnProperty("updateBy") &&
                                weekActiveList.includes(convertDateFormat(productHistoryList[j].updatedDate))) {
                            chartAllData[i].quantity += productHistoryList[j].phQuantity;
                        }
                    }
                }
            } else {
                for (var i = 0; i < chartAllDataLength; i++) {
                    for (var j = 0; j < productHistoryLength; j++) {
                        if (productHistoryList[j].hasOwnProperty("cake") &&
                                productHistoryList[j].cake.cakeName === chartAllData[i].productName &&
                                productHistoryList[j].hasOwnProperty("createBy") &&
                                weekActiveList.includes(convertDateFormat(productHistoryList[j].createDate))) {
                            chartAllData[i].quantity += productHistoryList[j].phQuantity;
                        }
                    }
                }
            }
        } else {
            const toppingListLength = toppingList.length;
            const currentOnePageLength = (curentPageAll - 1) * 5 + 5 > toppingListLength ? toppingListLength : (curentPageAll - 1) * 5 + 5;
            for (var i = (curentPageAll - 1) * 5; i < currentOnePageLength; i++) {
                chartAllData.push({"productName": toppingList[i].toppingName, "quantity": 0});
            }
            const weekActiveList = startDateToWeak(activeWeekAll);
            const chartAllDataLength = chartAllData.length;
            if (statusAll === "Update") {
                for (var i = 0; i < chartAllDataLength; i++) {
                    for (var j = 0; j < productHistoryLength; j++) {
                        if (productHistoryList[j].hasOwnProperty("topping") &&
                                productHistoryList[j].topping.toppingName === chartAllData[i].productName &&
                                productHistoryList[j].hasOwnProperty("updateBy") &&
                                weekActiveList.includes(convertDateFormat(productHistoryList[j].updatedDate))) {
                            chartAllData[i].quantity += productHistoryList[j].phQuantity;
                        }
                    }
                }
            } else {
                for (var i = 0; i < chartAllDataLength; i++) {
                    for (var j = 0; j < productHistoryLength; j++) {
                        if (productHistoryList[j].hasOwnProperty("topping") &&
                                productHistoryList[j].topping.toppingName === chartAllData[i].productName &&
                                productHistoryList[j].hasOwnProperty("createBy") &&
                                weekActiveList.includes(convertDateFormat(productHistoryList[j].createDate))) {
                            chartAllData[i].quantity += productHistoryList[j].phQuantity;
                        }
                    }
                }
            }
        }
        if (myChart4) {
            myChart4.destroy();
        }
        myChart4 = new Chart(ctx4, {
            type: "bar",
            data: {
                labels: chartAllData.map(product => product.productName),
                datasets: [{
                        label: "Quantity",
                        backgroundColor: [
                            "rgba(235, 22, 22, .7)",
                            "rgba(235, 22, 22, .6)",
                            "rgba(235, 22, 22, .5)",
                            "rgba(235, 22, 22, .4)",
                            "rgba(235, 22, 22, .3)"
                        ],
                        data: chartAllData.map(product => product.quantity)
                    }]
            },
            options: {
                responsive: true
            }
        });
    }

// Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
// Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });
// Sidebar Toggler
    $('.sidebar-toggler').click(function () {
        $('.sidebar, .content').toggleClass("open");
        return false;
    });
// Progress Bar
    $('.pg-bar').waypoint(function () {
        $('.progress .progress-bar').each(function () {
            $(this).css("width", $(this).attr("aria-valuenow") + '%');
        });
    }, {offset: '80%'});
// Calender
    $('#calender').datetimepicker({
        inline: true,
        format: 'L'
    });
// Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        items: 1,
        dots: true,
        loop: true,
        nav: false
    });
}
)(jQuery);
function convertDateFormat(inputDate) {
    const date = new Date(inputDate);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    const formattedDate = (day < 10 ? '0' : '') + day + '-' + (month < 10 ? '0' : '') + month + '-' + year;
    return formattedDate;
}
function getWeeksBetweenDates(startDate, endDate) {
    const weeksArray = [];
    const startDateParts = startDate.split('-');
    const endDateParts = endDate.split('-');
    const start = new Date(startDateParts[2], startDateParts[1] - 1, startDateParts[0]);
    const end = new Date(endDateParts[2], endDateParts[1] - 1, endDateParts[0]);
    while (start <= end) {
        const weekStart = new Date(start); // Lấy bản sao của ngày bắt đầu tuần
        const weekEnd = new Date(start.getTime() + 6 * 24 * 60 * 60 * 1000); // Ngày kết thúc tuần
        const formattedStart = formatDate(weekStart);
        const formattedEnd = formatDate(weekEnd);
        weeksArray.push({
            start: formattedStart,
            end: formattedEnd
        });
        start.setDate(start.getDate() + 7);
    }

    return weeksArray;
}

function formatDate(date) {
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    return (day < 10 ? '0' : '') + day + '-' + (month < 10 ? '0' : '') + month + '-' + year;
}
function startDateToWeak(activeWeek) {
    const activeStartDay = weekList[activeWeek].start.split('-');
    const start = new Date(activeStartDay[2], activeStartDay[1] - 1, activeStartDay[0]);
    const startDate = new Date(start);
    const weakActive = [];
    for (let i = 0; i < 7; i++) {
        const nextDay = new Date(startDate);
        nextDay.setDate(startDate.getDate() + i);
        const formattedDate = formatDate(nextDay);
        weakActive.push(formattedDate);
    }
    return weakActive;
}
