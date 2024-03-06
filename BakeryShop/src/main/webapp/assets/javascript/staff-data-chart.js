/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let activeWeakLine = 0;
let activeOption = "Total income";
let weakList;
let orderList;
let staffList;
let myChart4;
(function ($) {
    "use strict";
    // Chart Global Color
    Chart.defaults.color = "#6C7293";
    Chart.defaults.borderColor = "#000000";
    var ctx4 = $("#bar-chart").get(0).getContext("2d");
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "../../statistic-staff", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            staffList = data.staffList;
            orderList = data.orderList;
            const staffListLenth = staffList.length;
            const orderListLenth = orderList.length;
            const timeline = {start: orderList[orderListLenth - 1].orderDate, end: orderList[0].orderDate};
            weakList = getWeeksBetweenDates(timeline.start, timeline.end);
            const weakListLength = weakList.length;
            activeWeakLine = weakListLength - 1;
            const weakDropDown = document.getElementById('weakLine-dropdown');
            const weakOptionDropdown = weakDropDown.querySelector("#weak-option-dropdown");
            for (var i = weakListLength - 1; i >= 0; i--) {
                if (activeWeakLine === i) {
                    document.getElementById('weak-option-dropdown-btn').innerHTML = `${weakList[i].start} To ${weakList[i].end}`;
                    weakOptionDropdown.innerHTML += `<a class="dropdown-item active-weak-line" id="weak-line-option-${i}" href="#">${weakList[i].start} To ${weakList[i].end}</a>`;
                } else {
                    weakOptionDropdown.innerHTML += `<a class="dropdown-item" href="#" id="weak-line-option-${i}">${weakList[i].start} To ${weakList[i].end}</a>`;
                }
            }
            updateChart();
        }
    };
    xhr.send();
    function updateChart() {
        const weakActive = startDateToWeak();
        const staffListLength = staffList.length;
        const orderListLength = orderList.length;
        const salePerformance = [];
        for (var i = 0; i < staffListLength; i++) {
            salePerformance.push({"staffID": staffList[i].staffID, "staffName": staffList[i].fullname, "income": 0, "numberOrder": 0});
            for (var j = 0; j < orderListLength; j++) {
                if (orderList[j].staffID === staffList[i].staffID && weakActive.includes(convertDateFormat(orderList[j].orderDate))) {
                    salePerformance[i].income += orderList[j].totalPrice;
                    salePerformance[i].numberOrder += 1;
                }
            }
        }
        if (myChart4) {
            myChart4.destroy();
        }
        let dataChart = [];
        if (activeOption !== "Number of Orders") {
            dataChart = salePerformance.map(staff => staff.income);
        } else {
            dataChart = salePerformance.map(staff => staff.numberOrder);
        }

         myChart4 = new Chart(ctx4, {
            type: "bar",
            data: {
                labels: salePerformance.map(staff => staff.staffName),
                datasets: [{
                        label: activeOption,
                        backgroundColor: [
                            "rgba(235, 22, 22, .7)",
                            "rgba(235, 22, 22, .6)",
                            "rgba(235, 22, 22, .5)",
                            "rgba(235, 22, 22, .4)",
                            "rgba(235, 22, 22, .3)"
                        ],
                        data: dataChart
                    }]
            },
            options: {
                responsive: true
            }
        });
    }
    $(document).on("click", "#weak-option-dropdown .dropdown-item", function () {
        activeWeakLine = this.id.split("weak-line-option-")[1];
        document.getElementById("weak-option-dropdown").querySelector(".active-weak-line").classList.remove("active-weak-line");
        this.classList.add("active-weak-line");
        document.getElementById("weak-option-dropdown-btn").innerHTML = `${weakList[activeWeakLine].start} To ${weakList[activeWeakLine].end}`;
        updateChart();
    });
    $(document).on("click", "#option-chart .dropdown-item", function () {
        activeOption = this.innerHTML;
        document.getElementById("option-chart").querySelector(".active-weak-line").classList.remove("active-weak-line");
        document.getElementById("option-chat-btn").innerHTML = `${activeOption}`;
        this.classList.add("active-weak-line");
        updateChart();
    });
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
function startDateToWeak() {
    const activeStartDay = weakList[activeWeakLine].start.split('-');
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
