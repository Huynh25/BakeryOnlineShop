/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let activeWeakAllTopping = 0;
let currentPageAllTopping = 1;
let activeWeakOneTopping = 0;
let activeTopping = 0;
let activeOptionAllTopping = "Total Income";
let activeOptionOneTopping = "Total Income";
let weakList;
let myChart4;
let myChart3;
let toppingDetailList;
let toppingList;
(function ($) {
    "use strict";
    // Chart Global Color
    Chart.defaults.color = "#6C7293";
    Chart.defaults.borderColor = "#000000";
    var ctx4 = $("#bar-chart").get(0).getContext("2d");
    var ctx3 = $("#line-chart").get(0).getContext("2d");
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "../../topping-statistic", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            console.log(data);
            toppingDetailList = data.toppingInCakeList;
            toppingList = data.toppingList;
            const orderList = data.orderList;
            const orderListLength = orderList.length;
            let end = orderList[0].orderDate;
            let start = orderList[orderListLength - 1].orderDate;
            weakList = getWeeksBetweenDates(start, end);
            const weakListLength = weakList.length;
            const weakAllToppingOptionDropdown = document.getElementById('weak-all-topping-option-dropdown');
            activeWeakAllTopping = weakListLength - 1;
            for (var i = weakListLength - 1; i >= 0; i--) {
                if (activeWeakAllTopping === i) {
                    document.getElementById('weak-all-topping-option-dropdown-btn').innerHTML = `${weakList[i].start} To ${weakList[i].end}`;
                    weakAllToppingOptionDropdown.innerHTML += `<a class="dropdown-item active-all-topping" id="weak-bar-option-${i}" href="#">${weakList[i].start} To ${weakList[i].end}</a>`;
                } else {
                    weakAllToppingOptionDropdown.innerHTML += `<a class="dropdown-item" href="#" id="weak-bar-option-${i}">${weakList[i].start} To ${weakList[i].end}</a>`;
                }
            }
            const weakOptionDropdown = document.getElementById('weak-one-topping-option-dropdown');
            activeWeakOneTopping = weakListLength - 1;
            for (var i = weakListLength - 1; i >= 0; i--) {
                if (activeWeakOneTopping === i) {
                    document.getElementById('weak-one-topping-option-dropdown-btn').innerHTML = `${weakList[i].start} To ${weakList[i].end}`;
                    weakOptionDropdown.innerHTML += `<a class="dropdown-item active-one-topping" id="weak-line-option-${i}" href="#">${weakList[i].start} To ${weakList[i].end}</a>`;
                } else {
                    weakOptionDropdown.innerHTML += `<a class="dropdown-item" href="#" id="weak-line-option-${i}">${weakList[i].start} To ${weakList[i].end}</a>`;
                }
            }
            updateAllToppingChart();

            const totalPage = Math.ceil(toppingList.length / 5);
            document.getElementById('chartBar-Pagination').innerHTML = `Chart ${currentPageAllTopping} of ${totalPage}`;
            if (totalPage === 1) {
                document.getElementById('next-page-btn').classList.add('disable-icon');
            }

            const toppingListDropdown = document.getElementById('toppingList-one-topping-option-dropdown');
            const toppingListLength = toppingList.length;
            for (var i = 0; i < toppingListLength; i++) {
                if (activeTopping === i) {
                    document.getElementById('toppingList-one-topping-option-dropdown-btn').innerHTML = `${toppingList[i].toppingName}`;
                    toppingListDropdown.innerHTML += `<a class="dropdown-item active-one-topping" href="#" id="topping-line-option-${i}">${toppingList[i].toppingName}</a>`;
                } else {
                    toppingListDropdown.innerHTML += `<a class="dropdown-item" href="#" id="topping-line-option-${i}">${toppingList[i].toppingName}</a>`;
                }
            }
            updateOneToppingChart();
            const summarizeList = [];
            const toppingDetailListLength = toppingDetailList.length;
            const weakActiveList = startDateToWeak(weakListLength - 1);
            for (var j = 0, k = 0; j < toppingListLength; j++, k++) {
                summarizeList.push({"toppingName": toppingList[j].toppingName, "totalIncomeAll": 0, "numberOfOrderAll": 0, "totalIncomeWeak": 0, "numberOfOrderWeak": 0});
                for (var i = 0; i < toppingDetailListLength; i++) {
                    if (toppingDetailList[i].topping.toppingName === summarizeList[k].toppingName) {
                        summarizeList[k].totalIncomeAll += toppingDetailList[i].topping.toppingPrice * toppingDetailList[i].ticQuantity;
                        summarizeList[k].numberOfOrderAll+=toppingDetailList[i].ticQuantity;
                        if (weakActiveList.includes(convertDateFormat(toppingDetailList[i].cakeInOrder.order.orderDate))) {
                            summarizeList[k].totalIncomeWeak += toppingDetailList[i].topping.toppingPrice * toppingDetailList[i].ticQuantity;
                            summarizeList[k].numberOfOrderWeak+=toppingDetailList[i].ticQuantity;
                        }
                    }
                }
            }
            console.log(summarizeList);
            let totalIncomeAllMax = {"toppingName":"","data":0};
            let numberOfOrderAllMax = {"toppingName":"","data":0};
            let totalIncomeWeakMax = {"toppingName":"","data":0};
            let numberOfOrderWeakMax = {"toppingName":"","data":0};
            for (var i = 0; i < toppingListLength; i++) {
                if (totalIncomeAllMax.data < summarizeList[i].totalIncomeAll) {
                    totalIncomeAllMax.toppingName=summarizeList[i].toppingName;
                    totalIncomeAllMax.data = summarizeList[i].totalIncomeAll;
                }
                if (numberOfOrderAllMax.data < summarizeList[i].numberOfOrderAll) {
                    numberOfOrderAllMax.toppingName=summarizeList[i].toppingName;
                    numberOfOrderAllMax.data=summarizeList[i].numberOfOrderAll;
                }
                if (totalIncomeWeakMax.data < summarizeList[i].totalIncomeWeak) {
                    totalIncomeWeakMax.toppingName=summarizeList[i].toppingName;
                    totalIncomeWeakMax.data = summarizeList[i].totalIncomeWeak;
                }
                if (numberOfOrderWeakMax.data < summarizeList[i].numberOfOrderWeak) {
                    numberOfOrderWeakMax.toppingName = summarizeList[i].toppingName;
                    numberOfOrderWeakMax.data = summarizeList[i].numberOfOrderWeak;
                }
            }
            const summarizeContent = document.getElementById('summarize-content');
            summarizeContent.innerHTML += `<div>Toping has the highest total income: ${totalIncomeAllMax.toppingName} with ${totalIncomeAllMax.data.toLocaleString('vi-VN')}đ</div>`;
            summarizeContent.innerHTML += `<div>Toping has the highest number of order:  ${numberOfOrderAllMax.toppingName} with ${numberOfOrderAllMax.data} orders</div>`;
            summarizeContent.innerHTML += `<div>Toping has the highest total income in current week: ${totalIncomeWeakMax.toppingName} with ${totalIncomeWeakMax.data.toLocaleString('vi-VN')}đ</div>`;
            summarizeContent.innerHTML += `<div>Toping has the highest number of order in current week: ${numberOfOrderWeakMax.toppingName} with ${numberOfOrderWeakMax.data} orders</div>`;
        }
    };
    xhr.send();
    $(document).on("click", "#toppingList-one-topping-option-dropdown .dropdown-item", function () {
        activeTopping = this.id.split("topping-line-option-")[1];
        document.getElementById('toppingList-one-topping-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('toppingList-one-topping-option-dropdown').querySelector('.active-one-topping').classList.remove('active-one-topping');
        this.classList.add('active-one-topping');
        updateOneToppingChart();
    });
    $(document).on("click", "#weak-one-topping-option-dropdown .dropdown-item", function () {
        activeWeakOneTopping = this.id.split("weak-line-option-")[1];
        document.getElementById('weak-one-topping-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('weak-one-topping-option-dropdown').querySelector('.active-one-topping').classList.remove('active-one-topping');
        this.classList.add('active-one-topping');
        updateOneToppingChart();
    });
    $(document).on("click", "#one-topping-option-chart .dropdown-item", function () {
        activeOptionOneTopping = this.innerHTML;
        document.getElementById('one-topping-option-chart-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('one-topping-option-chart').querySelector('.active-one-topping').classList.remove('active-one-topping');
        this.classList.add('active-one-topping');
        updateOneToppingChart();
    });
    function updateOneToppingChart() {
        const weakActiveList = startDateToWeak(activeWeakOneTopping);
        const toppingDetailListLength = toppingDetailList.length;
        const oneToppingDataChart = [];
        let weakActiveListIndex = 0;
        if (activeOptionOneTopping === "Total Income") {
            for (var j = 0; j < 7; j++) {
                oneToppingDataChart.push({"orderDate": weakActiveList[weakActiveListIndex], "data": 0});
                for (var i = 0; i < toppingDetailListLength; i++) {
                    if (toppingDetailList[i].topping.toppingName === toppingList[activeTopping].toppingName && weakActiveList[weakActiveListIndex] === convertDateFormat(toppingDetailList[i].cakeInOrder.order.orderDate)) {
                        oneToppingDataChart[j].data += toppingDetailList[i].topping.toppingPrice * toppingDetailList[i].ticQuantity;
                    }
                }
                weakActiveListIndex++;
            }
        } else {
            for (var j = 0; j < 7; j++) {
                oneToppingDataChart.push({"orderDate": weakActiveList[weakActiveListIndex], "data": 0});
                for (var i = 0; i < toppingDetailListLength; i++) {
                    if (toppingDetailList[i].topping.toppingName === toppingList[activeTopping].toppingName && weakActiveList[weakActiveListIndex] === convertDateFormat(toppingDetailList[i].cakeInOrder.order.orderDate)) {
                        oneToppingDataChart[j].data+=toppingDetailList[i].ticQuantity;
                    }
                }
                weakActiveListIndex++;
            }
        }
        if (myChart3) {
            myChart3.destroy();
        }
        myChart3 = new Chart(ctx3, {
            type: "line",
            data: {
                labels: oneToppingDataChart.map(topping => topping.orderDate),
                datasets: [{
                        label: toppingList[activeTopping].toppingName,
                        fill: true,
                        backgroundColor: "rgba(235, 22, 22, .7)",
                        data: oneToppingDataChart.map(topping => topping.data)
                    }]
            },
            options: {
                responsive: true
            }
        });
    }
    $(document).on("click", "#bar-chart-option-dropdown .dropdown-item", function () {
        activeOptionAllTopping = this.innerHTML;
        document.getElementById('option-all-topping-chart-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('option-all-topping-chart').querySelector('.active-all-topping').classList.remove('active-all-topping');
        this.classList.add('active-all-topping');
        updateAllToppingChart();
    });
    $(document).on("click", "#weak-all-topping-option-dropdown .dropdown-item", function () {
        activeWeakAllTopping = this.id.split("weak-bar-option-")[1];
        document.getElementById('weak-all-topping-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('weak-all-topping-option-dropdown').querySelector('.active-all-topping').classList.remove('active-all-topping');
        this.classList.add('active-all-topping');
        updateAllToppingChart();
    });
    $(document).on("click", "#back-page-btn,#next-page-btn", function () {
        const totalPage = Math.ceil(toppingList.length / 5);
        if (this.id === 'back-page-btn') {
            if (currentPageAllTopping !== 1) {
                if (currentPageAllTopping === 2) {
                    document.getElementById('back-page-btn').classList.add('disable-icon');
                    document.getElementById('next-page-btn').classList.remove('disable-icon');
                }
                currentPageAllTopping--;
            }
        } else {
            if (totalPage >= currentPageAllTopping + 1) {
                if (currentPageAllTopping === 1) {
                    document.getElementById('back-page-btn').classList.remove('disable-icon');
                }
                if (totalPage === currentPageAllTopping + 1) {
                    document.getElementById('next-page-btn').classList.add('disable-icon');
                }
                currentPageAllTopping++;
            }
        }
        document.getElementById('chartBar-Pagination').innerHTML = `Chart ${currentPageAllTopping} of ${totalPage}`;
        updateAllToppingChart();
    });
    function updateAllToppingChart() {
        const weakActiveList = startDateToWeak(activeWeakAllTopping);
        const allToppingChartData = [];
        const allToppingPageLength = toppingList.length > (currentPageAllTopping - 1) * 5 + 5 ? (currentPageAllTopping - 1) * 5 + 5 : toppingList.length;
        const toppingDetailListLength = toppingDetailList.length;
        if (activeOptionAllTopping === 'Total Income') {
            for (var j = (currentPageAllTopping - 1) * 5, k = 0; j < allToppingPageLength; j++, k++) {
                allToppingChartData.push({"toppingName": toppingList[j].toppingName, "data": 0});
                for (var i = 0; i < toppingDetailListLength; i++) {
                    if (toppingDetailList[i].topping.toppingName === allToppingChartData[k].toppingName && weakActiveList.includes(convertDateFormat(toppingDetailList[i].cakeInOrder.order.orderDate))) {
                        allToppingChartData[k].data += toppingDetailList[i].topping.toppingPrice * toppingDetailList[i].ticQuantity;
                    }
                }
            }
        } else {
            for (var j = (currentPageAllTopping - 1) * 5, k = 0; j < allToppingPageLength; j++, k++) {
                allToppingChartData.push({"toppingName": toppingList[j].toppingName, "data": 0});
                for (var i = 0; i < toppingDetailListLength; i++) {
                    if (toppingDetailList[i].topping.toppingName === allToppingChartData[k].toppingName && weakActiveList.includes(convertDateFormat(toppingDetailList[i].cakeInOrder.order.orderDate))) {
                        allToppingChartData[k].data++;
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
                labels: allToppingChartData.map(topping => topping.toppingName),
                datasets: [{
                        label: activeOptionAllTopping,
                        backgroundColor: [
                            "rgba(235, 22, 22, .7)",
                            "rgba(235, 22, 22, .6)",
                            "rgba(235, 22, 22, .5)",
                            "rgba(235, 22, 22, .4)",
                            "rgba(235, 22, 22, .3)"
                        ],
                        data: allToppingChartData.map(topping => topping.data)
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
})(jQuery);
function getWeeksBetweenDates(startDate, endDate) {
    startDate = convertDateFormat(startDate);
    endDate = convertDateFormat(endDate);
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
function convertDateFormat(inputDate) {
    const date = new Date(inputDate);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    const formattedDate = (day < 10 ? '0' : '') + day + '-' + (month < 10 ? '0' : '') + month + '-' + year;
    return formattedDate;
}
function startDateToWeak(activeWeak) {
    const activeStartDay = weakList[activeWeak].start.split('-');
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
