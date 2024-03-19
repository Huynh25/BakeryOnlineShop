/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let myChart5;
let myChart3;
let weekList;
let orderList;
let activeWeek;
let activeWeekPie = 1;
let activePieOption = "Status";
let activeOption = "Total Income";
(function ($) {
    "use strict";
    // Chart Global Color
    Chart.defaults.color = "#6C7293";
    Chart.defaults.borderColor = "#000000";
    var ctx5 = $("#pie-chart").get(0).getContext("2d");
    var ctx3 = $("#line-chart").get(0).getContext("2d");
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "../../order-statistic", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            orderList = data.orderList;
            console.log(orderList);
            const orderListLength = orderList.length;
            weekList = getWeeksBetweenDates(orderList[orderListLength - 1].orderDate, orderList[0].orderDate);
            console.log(weekList);
            const weekListLength = weekList.length;
            activeWeekPie = weekListLength - 1;
            const weekPieDropdown = document.getElementById('week-pie-dropdown');
            for (var i = weekListLength - 1; i >= 0; i--) {
                if (activeWeekPie === i) {
                    document.getElementById('week-pie-dropdown-btn').innerHTML = `${weekList[i].start} To ${weekList[i].end}`;
                    weekPieDropdown.innerHTML += `<a class="dropdown-item active-weak-line" id="weak-line-option-${i}" href="#">${weekList[i].start} To ${weekList[i].end}</a>`;
                } else {
                    weekPieDropdown.innerHTML += `<a class="dropdown-item" href="#" id="weak-line-option-${i}">${weekList[i].start} To ${weekList[i].end}</a>`;
                }
            }
            updatePieChart();
            const weekOptionDropdown = document.getElementById('week-option-dropdown');
            activeWeek = weekListLength - 1;
            for (var i = weekListLength - 1; i >= 0; i--) {
                if (activeWeek === i) {
                    document.getElementById('week-option-dropdown-btn').innerHTML = `${weekList[i].start} To ${weekList[i].end}`;
                    weekOptionDropdown.innerHTML += `<a class="dropdown-item active-weak-line" id="weak-line-option-${i}" href="#">${weekList[i].start} To ${weekList[i].end}</a>`;
                } else {
                    weekOptionDropdown.innerHTML += `<a class="dropdown-item" href="#" id="weak-line-option-${i}">${weekList[i].start} To ${weekList[i].end}</a>`;
                }
            }
            updateWeekChart();
            let totalIncomeCurrentWeek = 0;
            let totalIncome = 0;
            let unPaid = 0;
            let Waiting = 0;
            let Delivering = 0;
            let Done = 0;
            const currentWeek = startDateToWeak(weekListLength - 1);
            for (var i = 0; i < orderListLength; i++) {
                if (orderList[i].wasPaid === false) {
                    unPaid++;
                }
                if (orderList[i].status === "Waiting") {
                    Waiting++;
                }
                if (orderList[i].status === "Delivering") {
                    Delivering++;
                }
                 if (orderList[i].status === "Done") {
                    Done++;
                }
                if (currentWeek.includes(convertDateFormat(orderList[i].orderDate))) {
                    totalIncomeCurrentWeek += orderList[i].totalPrice;
                }
                totalIncome+= orderList[i].totalPrice;
            }
            const summarizeContent = document.getElementById('summarize-content-order');
            summarizeContent.innerHTML += `<div>Total Income: ${totalIncome.toLocaleString('vi-VN')}đ</div>`;
            summarizeContent.innerHTML += `<div>Total Income in Current Week: ${totalIncomeCurrentWeek.toLocaleString('vi-VN')}đ</div>`;
            summarizeContent.innerHTML += `<div>Orders UnPaid: ${unPaid} Orders</div>`;
            summarizeContent.innerHTML += `<div>Orders Waiting: ${Waiting} Orders</div>`;
            summarizeContent.innerHTML += `<div>Orders Delivering: ${Delivering} Orders</div>`;
            summarizeContent.innerHTML += `<div>Orders Done: ${Done} Orders</div>`;
        }
    };
    function updatePieChart() {
        const orderListLength = orderList.length;
        const weekListLength = weekList.length;
        const activeWeek = startDateToWeak(activeWeekPie);
        let pieChartData = {"Waiting": 0, "Delivering": 0, "Done": 0};
        let labels = ["Waiting", "Delivering", "Done"];
        if (activePieOption === "Status") {
            for (var i = 0; i < orderListLength; i++) {
                if (activeWeek.includes(convertDateFormat(orderList[i].orderDate))) {
                    if (orderList[i].status === "Waiting") {
                        pieChartData.Waiting++;
                    } else if (orderList[i].status === "Delivering") {
                        pieChartData.Delivering++;
                    } else {
                        pieChartData.Done++;
                    }
                }
            }
            pieChartData = [pieChartData.Waiting, pieChartData.Delivering, pieChartData.Done];
        } else {
            pieChartData = {"wasPaid": 0, "unPaid": 0};
            labels = ["Was Paid", "UnPaid"];
            for (var i = 0; i < orderListLength; i++) {
                if (activeWeek.includes(convertDateFormat(orderList[i].orderDate))) {
                    if (orderList[i].wasPaid === true) {
                        pieChartData.wasPaid++;
                    } else {
                        pieChartData.unPaid++;
                    }
                }
            }
            pieChartData = [pieChartData.wasPaid, pieChartData.unPaid];
        }
        if (myChart5) {
            myChart5.destroy();
        }
        myChart5 = new Chart(ctx5, {
            type: "pie",
            data: {
                labels: labels,
                datasets: [{
                        backgroundColor: [
                            "rgba(235, 22, 22, .7)",
                            "rgba(235, 22, 22, .6)",
                            "rgba(235, 22, 22, .5)",
                            "rgba(235, 22, 22, .4)",
                            "rgba(235, 22, 22, .3)"
                        ],
                        data: pieChartData
                    }]
            },
            options: {
                responsive: true
            }
        });
    }
    $(document).on("click", "#week-pie-dropdown .dropdown-item", function () {
        activeWeekPie = this.id.split('weak-line-option-')[1];
        document.getElementById('week-pie-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('week-pie-dropdown').querySelector('.active-weak-line').classList.remove('active-weak-line');
        this.classList.add('active-weak-line');
        updatePieChart();
    });
    $(document).on("click", "#pie-option-chart .dropdown-item", function () {
        activePieOption = this.innerHTML;
        document.getElementById('pie-option-chart-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('pie-option-chart').querySelector('.active-weak-line').classList.remove('active-weak-line');
        this.classList.add('active-weak-line');
        updatePieChart();
    });
    $(document).on("click", "#order-option-list-chart .dropdown-item", function () {
        activeOption = this.innerHTML;
        document.getElementById('order-option-list-chart-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('order-option-list-chart').querySelector('.active-weak-line').classList.remove('active-weak-line');
        this.classList.add('active-weak-line');
        updateWeekChart();
    });
    $(document).on("click", "#week-option-dropdown .dropdown-item", function () {
        activeWeek = this.id.split('weak-line-option-')[1];
        document.getElementById('week-option-dropdown-btn').innerHTML = `${this.innerHTML}`;
        document.getElementById('week-option-dropdown').querySelector('.active-weak-line').classList.remove('active-weak-line');
        this.classList.add('active-weak-line');
        updateWeekChart();
    });
    function updateWeekChart() {
        const weekChart = [];
        console.log(weekList);
        const orderListLength = orderList.length;
        const weakActiveList = startDateToWeak(activeWeek);
        let label = "Việt Nam Đồng";
        if (activeOption === "Total Income") {
            for (var i = 0; i < 7; i++) {
                weekChart.push({"date": weakActiveList[i], "data": 0});
                for (var j = 0; j < orderListLength; j++) {
                    if (convertDateFormat(orderList[j].orderDate) === weakActiveList[i]) {
                        weekChart[i].data += orderList[j].totalPrice;
                    }
                }
            }
        } else {
            label = "Orders";
            for (var i = 0; i < 7; i++) {
                weekChart.push({"date": weakActiveList[i], "data": 0});
                for (var j = 0; j < orderListLength; j++) {
                    if (convertDateFormat(orderList[j].orderDate) === weakActiveList[i]) {
                        weekChart[i].data++;
                    }
                }
            }
        }
        console.log(weekChart);
        if (myChart3) {
            myChart3.destroy();
        }
        myChart3 = new Chart(ctx3, {
            type: "line",
            data: {
                labels: weekChart.map(orderInDate => orderInDate.date),
                datasets: [{
                        label: label,
                        fill: false,
                        backgroundColor: "rgba(235, 22, 22, .7)",
                        data: weekChart.map(orderInDate => orderInDate.data)
                    }]
            },
            options: {
                responsive: true
            }
        });
    }
    xhr.send();
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
    const activeStartDay = weekList[activeWeak].start.split('-');
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
