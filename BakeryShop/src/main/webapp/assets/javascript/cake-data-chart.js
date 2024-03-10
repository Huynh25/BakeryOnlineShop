/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let activeWeakAllCake = 0;
let currentPageAllCake = 1;
let activeWeakOneCake = 0;
let activeCake = 0;
let activeOptionAllCake = "Total Income";
let activeOptionOneCake = "Total Income";
let weakList;
let myChart4;
let myChart3;
let cakeDetailList;
let cakeList;
(function ($) {
    "use strict";
    // Chart Global Color
    Chart.defaults.color = "#6C7293";
    Chart.defaults.borderColor = "#000000";
    var ctx4 = $("#bar-chart").get(0).getContext("2d");
    var ctx3 = $("#line-chart").get(0).getContext("2d");
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "../../cake-statistic", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            console.log(data);
            cakeDetailList = data.cakeDetailList;
            cakeList = data.cakeList;
            const orderList = data.orderList;
            const orderListLength = orderList.length;
            let end = orderList[0].orderDate;
            let start = orderList[orderListLength - 1].orderDate;
            weakList = getWeeksBetweenDates(start, end);
            const weakListLength = weakList.length;

            const weakAllCakeOptionDropdown = document.getElementById('weak-all-cake-option-dropdown');
            activeWeakAllCake = weakListLength - 1;
            for (var i = weakListLength - 1; i >= 0; i--) {
                if (activeWeakAllCake === i) {
                    document.getElementById('weak-all-cake-option-dropdown-btn').innerHTML = `${weakList[i].start} To ${weakList[i].end}`;
                    weakAllCakeOptionDropdown.innerHTML += `<a class="dropdown-item active-all-cake" id="weak-bar-option-${i}" href="#">${weakList[i].start} To ${weakList[i].end}</a>`;
                } else {
                    weakAllCakeOptionDropdown.innerHTML += `<a class="dropdown-item" href="#" id="weak-bar-option-${i}">${weakList[i].start} To ${weakList[i].end}</a>`;
                }
            }
            const weakOptionDropdown = document.getElementById('weak-one-cake-option-dropdown');
            activeWeakOneCake = weakListLength - 1;
            for (var i = weakListLength - 1; i >= 0; i--) {
                if (activeWeakOneCake === i) {
                    document.getElementById('weak-one-cake-option-dropdown-btn').innerHTML = `${weakList[i].start} To ${weakList[i].end}`;
                    weakOptionDropdown.innerHTML += `<a class="dropdown-item active-one-cake" id="weak-line-option-${i}" href="#">${weakList[i].start} To ${weakList[i].end}</a>`;
                } else {
                    weakOptionDropdown.innerHTML += `<a class="dropdown-item" href="#" id="weak-line-option-${i}">${weakList[i].start} To ${weakList[i].end}</a>`;
                }
            }
            updateAllCakeChart();

            const cakeListDropdown = document.getElementById('cakelist-one-cake-option-dropdown');
            const cakeListLength = cakeList.length;
            for (var i = 0; i < cakeListLength; i++) {
                if (activeCake === i) {
                    document.getElementById('cakelist-one-cake-option-dropdown-btn').innerHTML = `${cakeList[i].cakeName}`;
                    cakeListDropdown.innerHTML += `<a class="dropdown-item active-one-cake" href="#" id="cake-line-option-${i}">${cakeList[i].cakeName}</a>`;
                } else {
                    cakeListDropdown.innerHTML += `<a class="dropdown-item" href="#" id="cake-line-option-${i}">${cakeList[i].cakeName}</a>`;
                }
            }
            updateOneCakeChart();

            const totalPage = Math.ceil(cakeList.length / 5);
            document.getElementById('chartBar-Pagination').innerHTML = `Chart ${currentPageAllCake} of ${totalPage}`;
            if (totalPage === 1) {
                document.getElementById('next-page-btn').classList.add('disable-icon');
            }
            const summarizeList = [];
            const cakeDetailListLength = cakeDetailList.length;
            const weakActiveList = startDateToWeak(weakListLength - 1);
            for (var j = 0, k = 0; j < cakeListLength; j++, k++) {
                summarizeList.push({"cakeName": cakeList[j].cakeName, "totalIncomeAll": 0, "numberOfOrderAll": 0, "totalIncomeWeak": 0, "numberOfOrderWeak": 0});
                for (var i = 0; i < cakeDetailListLength; i++) {
                    if (cakeDetailList[i].cake.cakeName === summarizeList[k].cakeName) {
                        summarizeList[k].totalIncomeAll += cakeDetailList[i].cake.cakePrice * cakeDetailList[i].cioQuantity;
                        summarizeList[k].numberOfOrderAll+=cakeDetailList[i].cioQuantity;
                        if (weakActiveList.includes(convertDateFormat(cakeDetailList[i].order.orderDate))) {
                            summarizeList[k].totalIncomeWeak += cakeDetailList[i].cake.cakePrice * cakeDetailList[i].cioQuantity;
                            summarizeList[k].numberOfOrderWeak+=cakeDetailList[i].cioQuantity;
                        }
                    }
                }
            }
            let totalIncomeAllMax = {"cakeName":"","data":0};
            let numberOfOrderAllMax = {"cakeName":"","data":0};
            let totalIncomeWeakMax = {"cakeName":"","data":0};
            let numberOfOrderWeakMax = {"cakeName":"","data":0};
            for (var i = 0; i < cakeListLength; i++) {
                if (totalIncomeAllMax.data < summarizeList[i].totalIncomeAll) {
                    totalIncomeAllMax.cakeName=summarizeList[i].cakeName;
                    totalIncomeAllMax.data = summarizeList[i].totalIncomeAll;
                }
                if (numberOfOrderAllMax.data < summarizeList[i].numberOfOrderAll) {
                    numberOfOrderAllMax.cakeName=summarizeList[i].cakeName;
                    numberOfOrderAllMax.data=summarizeList[i].numberOfOrderAll;
                }
                if (totalIncomeWeakMax.data < summarizeList[i].totalIncomeWeak) {
                    totalIncomeWeakMax.cakeName=summarizeList[i].cakeName;
                    totalIncomeWeakMax.data = summarizeList[i].totalIncomeWeak;
                }
                if (numberOfOrderWeakMax.data < summarizeList[i].numberOfOrderWeak) {
                    numberOfOrderWeakMax.cakeName = summarizeList[i].cakeName;
                    numberOfOrderWeakMax.data = summarizeList[i].numberOfOrderWeak;
                }
            }
            const summarizeContent = document.getElementById('summarize-content');
            summarizeContent.innerHTML += `<div>Cake has the highest total income: ${totalIncomeAllMax.cakeName} with ${totalIncomeAllMax.data.toLocaleString('vi-VN')}đ</div>`;
            summarizeContent.innerHTML += `<div>Cake has the highest number of order:  ${numberOfOrderAllMax.cakeName} with ${numberOfOrderAllMax.data} orders</div>`;
            summarizeContent.innerHTML += `<div>Cake has the highest total income in current week: ${totalIncomeWeakMax.cakeName} with ${totalIncomeWeakMax.data.toLocaleString('vi-VN')}đ</div>`;
            summarizeContent.innerHTML += `<div>Cake has the highest number of order in current week: ${numberOfOrderWeakMax.cakeName} with ${numberOfOrderWeakMax.data} orders</div>`;
        }
    };
    xhr.send();
    $(document).on("click", "#cakelist-one-cake-option-dropdown .dropdown-item", function () {
        activeCake = this.id.split("cake-line-option-")[1];
        document.getElementById('cakelist-one-cake-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('cakelist-one-cake-option-dropdown').querySelector('.active-one-cake').classList.remove('active-one-cake');
        this.classList.add('active-one-cake');
        updateOneCakeChart();
    });
    $(document).on("click", "#weak-one-cake-option-dropdown .dropdown-item", function () {
        activeWeakOneCake = this.id.split("weak-line-option-")[1];
        document.getElementById('weak-one-cake-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('weak-one-cake-option-dropdown').querySelector('.active-one-cake').classList.remove('active-one-cake');
        this.classList.add('active-one-cake');
        updateOneCakeChart();
    });
    $(document).on("click", "#one-cake-option-chart .dropdown-item", function () {
        activeOptionOneCake = this.innerHTML;
        console.log(activeOptionOneCake);
        document.getElementById('one-cake-option-chart-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('one-cake-option-chart').querySelector('.active-one-cake').classList.remove('active-one-cake');
        this.classList.add('active-one-cake');
        updateOneCakeChart();
    });
    $(document).on("click", "#bar-chart-option-dropdown .dropdown-item", function () {
        activeOptionAllCake = this.innerHTML;
        console.log(activeOptionAllCake);
        document.getElementById('option-all-cake-chart-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('option-all-cake-chart').querySelector('.active-all-cake').classList.remove('active-all-cake');
        this.classList.add('active-all-cake');
        updateAllCakeChart();
    });
    $(document).on("click", "#weak-all-cake-option-dropdown .dropdown-item", function () {
        activeWeakAllCake = this.id.split("weak-bar-option-")[1];
        document.getElementById('weak-all-cake-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('weak-all-cake-option-dropdown').querySelector('.active-all-cake').classList.remove('active-all-cake');
        this.classList.add('active-all-cake');
        updateAllCakeChart();
    });
    $(document).on("click", "#back-page-btn,#next-page-btn", function () {
        const totalPage = Math.ceil(cakeList.length / 5);
        if (this.id === 'back-page-btn') {
            if (currentPageAllCake !== 1) {
                if (currentPageAllCake === 2) {
                    document.getElementById('back-page-btn').classList.add('disable-icon');
                    document.getElementById('next-page-btn').classList.remove('disable-icon');
                }
                currentPageAllCake--;
            }
        } else {
            if (totalPage >= currentPageAllCake + 1) {
                if (currentPageAllCake === 1) {
                    document.getElementById('back-page-btn').classList.remove('disable-icon');
                }
                if (totalPage === currentPageAllCake + 1) {
                    document.getElementById('next-page-btn').classList.add('disable-icon');
                }
                currentPageAllCake++;
            }
        }
        document.getElementById('chartBar-Pagination').innerHTML = `Chart ${currentPageAllCake} of ${totalPage}`;
        updateAllCakeChart();
    });

    function updateAllCakeChart() {
        const weakActiveList = startDateToWeak(activeWeakAllCake);
        const allCakeChartData = [];
        const allCakePageLength = cakeList.length > (currentPageAllCake - 1) * 5 + 5 ? (currentPageAllCake - 1) * 5 + 5 : cakeList.length;
        const cakeDetailListLength = cakeDetailList.length;
        if (activeOptionAllCake === 'Total Income') {
            for (var j = (currentPageAllCake - 1) * 5, k = 0; j < allCakePageLength; j++, k++) {
                allCakeChartData.push({"cakeName": cakeList[j].cakeName, "data": 0});
                for (var i = 0; i < cakeDetailListLength; i++) {
                    if (cakeDetailList[i].cake.cakeName === allCakeChartData[k].cakeName && weakActiveList.includes(convertDateFormat(cakeDetailList[i].order.orderDate))) {
                        allCakeChartData[k].data += cakeDetailList[i].cake.cakePrice * cakeDetailList[i].cioQuantity;
                    }
                }
            }
        } else {
            for (var j = (currentPageAllCake - 1) * 5, k = 0; j < allCakePageLength; j++, k++) {
                allCakeChartData.push({"cakeName": cakeList[j].cakeName, "data": 0});
                for (var i = 0; i < cakeDetailListLength; i++) {
                    if (cakeDetailList[i].cake.cakeName === allCakeChartData[k].cakeName && weakActiveList.includes(convertDateFormat(cakeDetailList[i].order.orderDate))) {
                        allCakeChartData[k].data+=cakeDetailList[i].cioQuantity;
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
                labels: allCakeChartData.map(cake => cake.cakeName),
                datasets: [{
                        label: activeOptionAllCake,
                        backgroundColor: [
                            "rgba(235, 22, 22, .7)",
                            "rgba(235, 22, 22, .6)",
                            "rgba(235, 22, 22, .5)",
                            "rgba(235, 22, 22, .4)",
                            "rgba(235, 22, 22, .3)"
                        ],
                        data: allCakeChartData.map(cake => cake.data)
                    }]
            },
            options: {
                responsive: true
            }
        });
    }
    function updateOneCakeChart() {
        const weakActiveList = startDateToWeak(activeWeakOneCake);
        const cakeDetailListLength = cakeDetailList.length;
        const oneCakeDataChart = [];
        let weakActiveListIndex = 0;
        if (activeOptionOneCake === "Total Income") {
            for (var j = 0; j < 7; j++) {
                oneCakeDataChart.push({"orderDate": weakActiveList[weakActiveListIndex], "data": 0});
                for (var i = 0; i < cakeDetailListLength; i++) {
                    if (cakeDetailList[i].cake.cakeName === cakeList[activeCake].cakeName && weakActiveList[weakActiveListIndex] === convertDateFormat(cakeDetailList[i].order.orderDate)) {
                        oneCakeDataChart[j].data += cakeDetailList[i].cake.cakePrice * cakeDetailList[i].cioQuantity;
                    }
                }
                weakActiveListIndex++;
            }
        } else {
            for (var j = 0; j < 7; j++) {
                oneCakeDataChart.push({"orderDate": weakActiveList[weakActiveListIndex], "data": 0});
                for (var i = 0; i < cakeDetailListLength; i++) {
                    if (cakeDetailList[i].cake.cakeName === cakeList[activeCake].cakeName && weakActiveList[weakActiveListIndex] === convertDateFormat(cakeDetailList[i].order.orderDate)) {
                        oneCakeDataChart[j].data+=cakeDetailList[i].cioQuantity;
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
                labels: oneCakeDataChart.map(cake => cake.orderDate),
                datasets: [{
                        label: cakeList[activeCake].cakeName,
                        fill: true,
                        backgroundColor: "rgba(235, 22, 22, .7)",
                        data: oneCakeDataChart.map(cake => cake.data)
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
