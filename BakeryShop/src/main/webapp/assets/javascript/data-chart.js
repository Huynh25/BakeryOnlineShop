let activeWeakLine = 0;
let activeTopBar = 0;
let data;
let weakList;
let myChart3;
let myChart4;
let myChart6;
let currentBarChart = 1;
(function ($) {
    "use strict";
    // Chart Global Color
    Chart.defaults.color = "#6C7293";
    Chart.defaults.borderColor = "#000000";
    var ctx3 = $("#line-chart").get(0).getContext("2d");
    var ctx4 = $("#bar-chart").get(0).getContext("2d");
    var ctx6 = $("#doughnut-chart").get(0).getContext("2d");
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "../../rating-management", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            data = JSON.parse(xhr.responseText);
            console.log(data);
            const ratingListLength = data.length;
            for (var i = 0; i < ratingListLength; i++) {
                data[i].ratingDate = convertDateFormat(data[i].ratingDate);
            }
            weakList = getWeeksBetweenDates(data[ratingListLength - 1].ratingDate, data[0].ratingDate);
            const weakListLength = weakList.length;
            const weakOptionDropdown = document.getElementById('weak-option-dropdown');
            activeWeakLine = weakListLength - 1;
            for (var i = weakListLength - 1; i >= 0; i--) {
                if (activeWeakLine === i) {
                    document.getElementById('weak-option-dropdown-btn').innerHTML = `${weakList[i].start} To ${weakList[i].end}`;
                    weakOptionDropdown.innerHTML += `<a class="dropdown-item active-weak-line" id="weak-line-option-${i}" href="#">${weakList[i].start} To ${weakList[i].end}</a>`;
                } else {
                    weakOptionDropdown.innerHTML += `<a class="dropdown-item" href="#" id="weak-line-option-${i}">${weakList[i].start} To ${weakList[i].end}</a>`;
                }
            }

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

            const cakeTypeList = [];
            for (var i = 0; i < ratingListLength; i++) {
                if (!cakeTypeList.includes(data[i].cake.cakeType)) {
                    cakeTypeList.push(data[i].cake.cakeType);
                }
            }
            const cakeTypeListLength = cakeTypeList.length;
            const cakeTypeOption = document.getElementById('cake-type-option-dropdown');
            for (var i = 0; i < cakeTypeListLength; i++) {
                cakeTypeOption.innerHTML += `<a class="dropdown-item" href="#">${cakeTypeList[i]}</a>`;
            }

            const singleLineChartData = [0, 0, 0, 0, 0, 0, 0];
            for (var i = 0; i < ratingListLength; i++) {
                for (var j = 0; j < 7; j++) {
                    if (data[i].ratingDate === weakActive[j]) {
                        singleLineChartData[j] += 1;
                    }
                }
            }
            // Single Line Chart


            myChart3 = new Chart(ctx3, {
                type: "line",
                data: {
                    labels: weakActive,
                    datasets: [{
                            label: "Number of ratings",
                            fill: true,
                            backgroundColor: "rgba(235, 22, 22, .7)",
                            data: singleLineChartData
                        }]
                },
                options: {
                    responsive: true
                }
            });
            const topWeakOptionDropdown = document.getElementById('top-weak-bar-chart-dropdown');
            activeTopBar = weakListLength - 1;
            for (var i = weakListLength - 1; i >= 0; i--) {
                topWeakOptionDropdown.innerHTML += `<a class="dropdown-item" href="#" id="top-bar-option-${i}">${weakList[i].start} To ${weakList[i].end}</a>`;
            }
            let cakeList = [];
            let topCakeRatingList = [];
            for (var i = 0; i < ratingListLength; i++) {
                if (!cakeList.includes(data[i].cake.cakeName)) {
                    cakeList.push(data[i].cake.cakeName);
                    topCakeRatingList.push({"cakeName": cakeList[cakeList.length - 1], "totalRating": 0, "numberRating": 0});
                }
            }
            document.getElementById('charBar-Pagination').innerHTML = `Chart ${currentBarChart} of ${Math.ceil(cakeList.length / 5)}`;
            if (Math.ceil(cakeList.length / 5) === 1) {
                document.getElementById('next-page-btn').disabled = true;
                document.getElementById('next-page-btn').classList.add('disable-icon');
            }
            const cakeListLength = cakeList.length;
            for (var i = 0; i < ratingListLength; i++) {
                for (var j = 0; j < cakeListLength; j++) {
                    if (data[i].cake.cakeName === cakeList[j]) {
                        topCakeRatingList[j].totalRating += data[i].ratingValue;
                        topCakeRatingList[j].numberRating += 1;
                    }
                }
            }
            let limitCakeList = cakeList.slice(0, 5);
            const limitTopCakeRatingList = topCakeRatingList.slice(0, 5);
            const barCharValue = [];
            for (var i = 0; i < 5; i++) {
                if (limitTopCakeRatingList[i].numberRating === 0) {
                    limitTopCakeRatingList[i].numberRating = 1;
                }
                barCharValue[i] = limitTopCakeRatingList[i].totalRating / limitTopCakeRatingList[i].numberRating;
            }
            // Single Bar Chart

            myChart4 = new Chart(ctx4, {
                type: "bar",
                data: {
                    labels: limitCakeList,
                    datasets: [{
                            label: "Average rating",
                            backgroundColor: [
                                "rgba(235, 22, 22, .7)",
                                "rgba(235, 22, 22, .6)",
                                "rgba(235, 22, 22, .5)",
                                "rgba(235, 22, 22, .4)",
                                "rgba(235, 22, 22, .3)"
                            ],
                            data: barCharValue
                        }]
                },
                options: {
                    responsive: true
                }
            });
            const numberEachValue = [0, 0, 0, 0, 0];
            for (var i = 0; i < ratingListLength; i++) {
                for (var j = 0; j < 5; j++) {
                    if (data[i].ratingValue === j + 1) {
                        numberEachValue[j] += 1;
                        break;
                    }
                }
            }
            // Doughnut Chart

            myChart6 = new Chart(ctx6, {
                type: "doughnut",
                data: {
                    labels: ["1 Star", "2 Star", "3 Star", "4 Star", "5 Star"],
                    datasets: [{
                            backgroundColor: [
                                "rgba(235, 22, 22, .7)",
                                "rgba(235, 22, 22, .6)",
                                "rgba(235, 22, 22, .5)",
                                "rgba(235, 22, 22, .4)",
                                "rgba(235, 22, 22, .3)"
                            ],
                            data: numberEachValue
                        }]
                },
                options: {
                    responsive: true
                }
            });
            let averageAllCake = {"allValue": 0, "allRating": 0};
            let highestCake = {"cakeName": "", "value": 0};
            let underratedCake = {"cakeName": "", "value": Number.MAX_SAFE_INTEGER};
            let mostReviewCake = {"cakeName": "", "rating": 0};
            let leastReviewCake = {"cakeName": "", "rating": Number.MAX_SAFE_INTEGER};
            let highestCakeType = {"cakeType": "", "value": 0};
            let underratedCakeType = {"cakeType": "", "value": Number.MAX_SAFE_INTEGER};
            let mostReviewCakeType = {"cakeType": "", "rating": 0};
            let leastReviewCakeType = {"cakeType": "", "rating": Number.MAX_SAFE_INTEGER};
            const cakeTypeListRating = [];
            console.log(topCakeRatingList);
            const topCakeRatingListlength = topCakeRatingList.length;
            for (var i = 0; i < topCakeRatingListlength; i++) {
                if (highestCake.value < topCakeRatingList[i].totalRating / topCakeRatingList[i].numberRating) {
                    highestCake.cakeName = topCakeRatingList[i].cakeName;
                    highestCake.value = topCakeRatingList[i].totalRating / topCakeRatingList[i].numberRating;
                }
                if (underratedCake.value > topCakeRatingList[i].totalRating / topCakeRatingList[i].numberRating) {
                    underratedCake.cakeName = topCakeRatingList[i].cakeName;
                    underratedCake.value = topCakeRatingList[i].totalRating / topCakeRatingList[i].numberRating;
                }
                if (mostReviewCake.rating < topCakeRatingList[i].numberRating) {
                    mostReviewCake.cakeName = topCakeRatingList[i].cakeName;
                    mostReviewCake.rating = topCakeRatingList[i].numberRating;
                }
                if (leastReviewCake.rating > topCakeRatingList[i].numberRating) {
                    leastReviewCake.cakeName = topCakeRatingList[i].cakeName;
                    leastReviewCake.rating = topCakeRatingList[i].numberRating;
                }
                averageAllCake.allValue += topCakeRatingList[i].totalRating;
                averageAllCake.allRating += topCakeRatingList[i].numberRating;
            }
            for (var j = 0; j < cakeTypeListLength; j++) {
                let isPush = false;
                for (var i = 0; i < ratingListLength; i++) {
                    if (data[i].cake.cakeType === cakeTypeList[j]) {
                        if (!isPush) {
                            cakeTypeListRating.push({"cakeType": cakeTypeList[j], "totalRating": 0, "numberRating": 0});
                            isPush = true;
                        }
                        cakeTypeListRating[j].totalRating += data[i].ratingValue;
                        cakeTypeListRating[j].numberRating += 1;
                    }
                }
            }
            const cakeTypeListRatingLenth = cakeTypeListRating.length;
            for (var i = 0; i < cakeTypeListRatingLenth; i++) {
                if (highestCakeType.value < cakeTypeListRating[i].totalRating / cakeTypeListRating[i].numberRating) {
                    highestCakeType.cakeType = cakeTypeListRating[i].cakeType;
                    highestCakeType.value = cakeTypeListRating[i].totalRating / cakeTypeListRating[i].numberRating;
                }
                if (underratedCakeType.value > cakeTypeListRating[i].totalRating / cakeTypeListRating[i].numberRating) {
                    underratedCakeType.cakeType = cakeTypeListRating[i].cakeType;
                    underratedCakeType.value = cakeTypeListRating[i].totalRating / cakeTypeListRating[i].numberRating;
                }
                if (mostReviewCakeType.rating < cakeTypeListRating[i].numberRating) {
                    mostReviewCakeType.cakeType = cakeTypeListRating[i].cakeType;
                    mostReviewCakeType.rating = cakeTypeListRating[i].numberRating;
                }
                if (leastReviewCakeType.rating > cakeTypeListRating[i].numberRating) {
                    leastReviewCakeType.cakeType = cakeTypeListRating[i].cakeType;
                    leastReviewCakeType.rating = cakeTypeListRating[i].numberRating;
                }
            }
            document.getElementById('averageAllCake').innerHTML=`Average rating of all cakes: ${(averageAllCake.allValue / averageAllCake.allRating).toFixed(2)} <i class="bi bi-star-fill"></i>`;
            document.getElementById('highestCake').innerHTML=`Highest rated cake: ${highestCake.cakeName}: ${highestCake.value.toFixed(2)} <i class="bi bi-star-fill"></i>`;
            document.getElementById('underratedCake').innerHTML=`Most underrated cake: ${underratedCake.cakeName}: ${underratedCake.value.toFixed(2)} <i class="bi bi-star-fill"></i>`;
            document.getElementById('mostReviewCake').innerHTML=`The cake has the most reviews: ${mostReviewCake.cakeName} ${mostReviewCake.rating} Ratings`;
            document.getElementById('leastReviewCake').innerHTML=`The cake received the least reviews: ${leastReviewCake.cakeName} ${leastReviewCake.rating} Ratings`;
            document.getElementById('highestCakeType').innerHTML=`Highest rated cake type: ${highestCakeType.cakeType} ${highestCakeType.value.toFixed(2)} <i class="bi bi-star-fill"></i>`;
            document.getElementById('underratedCakeType').innerHTML=`Most underrated cake type: ${underratedCakeType.cakeType} ${underratedCakeType.value.toFixed(2)} <i class="bi bi-star-fill"></i>`;
            document.getElementById('mostReviewCakeType').innerHTML=`The cake type has the most reviews: ${mostReviewCakeType.cakeType} ${mostReviewCakeType.rating} Ratings`;
            document.getElementById('leastReviewCakeType').innerHTML=`The cake type received the least reviews: ${leastReviewCakeType.cakeType} ${leastReviewCakeType.rating} Ratings`;
            console.log(cakeTypeListRating);
        }
    };


    xhr.send();
    $(document).on("click", ".pag-icon", function () {
        let cakeList = [];
        const ratingListLength = data.length;
        let limitCakeList;
        let barCharValue;
        let topCakeRatingList = [];
        const weakActive = [];
        activeTopBar = document.getElementById('weakBar-dropdown').querySelector('.active-top-bar').id.split('top-bar-option-')[1];
        if (activeTopBar !== "all") {
            const activeStartDay = weakList[activeTopBar].start.split('-');
            const start = new Date(activeStartDay[2], activeStartDay[1] - 1, activeStartDay[0]);
            const startDate = new Date(start);
            for (let i = 0; i < 7; i++) {
                const nextDay = new Date(startDate);
                nextDay.setDate(startDate.getDate() + i);
                const formattedDate = formatDate(nextDay);
                weakActive.push(formattedDate);
            }
            let weakActiveLenth = weakActive.length;
            for (var i = 0; i < ratingListLength; i++) {
                if (!cakeList.includes(data[i].cake.cakeName)) {
                    let isPush = false;
                    for (var j = 0; j < weakActiveLenth; j++) {
                        if (data[i].ratingDate === weakActive[j]) {
                            if (!isPush) {
                                cakeList.push(data[i].cake.cakeName);
                                isPush = true;
                            }
                            topCakeRatingList.push({"cakeName": cakeList[cakeList.length - 1], "totalRating": 0, "numberRating": 0});
                        }
                    }

                }
            }
        } else {
            for (var i = 0; i < ratingListLength; i++) {
                if (!cakeList.includes(data[i].cake.cakeName)) {
                    cakeList.push(data[i].cake.cakeName);
                    topCakeRatingList.push({"cakeName": cakeList[cakeList.length - 1], "totalRating": 0, "numberRating": 0});
                }
            }

        }

        if (!this.classList.contains('disable-icon')) {
            if (this.id === "next-page-btn") {
                currentBarChart++;
                document.getElementById('charBar-Pagination').innerHTML = `Chart ${currentBarChart} of ${Math.ceil(cakeList.length / 5)}`;
                if (currentBarChart === Math.ceil(cakeList.length / 5)) {
                    document.getElementById('next-page-btn').classList.add('disable-icon');
                }
                if (currentBarChart === 2) {
                    const backBarBtn = document.getElementById('back-page-btn');
                    backBarBtn.disabled = false;
                    backBarBtn.classList.remove('disable-icon');
                }
            } else {

                currentBarChart--;
                document.getElementById('charBar-Pagination').innerHTML = `Chart ${currentBarChart} of ${Math.ceil(cakeList.length / 5)}`;
                if (currentBarChart === 1) {
                    const backBarBtn = document.getElementById('back-page-btn').classList.add('disable-icon');
                }
                if (currentBarChart < Math.ceil(cakeList.length / 5)) {
                    const nextBarBtn = document.getElementById('next-page-btn');
                    nextBarBtn.disabled = false;
                    nextBarBtn.classList.remove('disable-icon');
                }
            }
            if (activeTopBar !== "all") {
                const weakActiveLenth = weakActive.length;
                const cakeListLength = cakeList.length;
                for (var i = 0; i < ratingListLength; i++) {
                    for (var j = 0; j < cakeListLength; j++) {
                        if (data[i].cake.cakeName === cakeList[j]) {
                            for (var k = 0; k < weakActiveLenth; k++) {
                                if (data[i].ratingDate === weakActive[k]) {
                                    topCakeRatingList[j].totalRating += data[i].ratingValue;
                                    topCakeRatingList[j].numberRating += 1;
                                }
                            }
                        }

                    }
                }
                limitCakeList = cakeList.slice((currentBarChart - 1) * 5, (currentBarChart - 1) * 5 + 5);
                const limitTopCakeRatingList = topCakeRatingList.slice((currentBarChart - 1) * 5, (currentBarChart - 1) * 5 + 5);
                barCharValue = [];
                const limitLength = limitCakeList.length;
                for (var i = 0; i < limitLength; i++) {
                    if (limitTopCakeRatingList[i].numberRating === 0) {
                        limitTopCakeRatingList[i].numberRating = 1;
                    }
                    barCharValue[i] = limitTopCakeRatingList[i].totalRating / limitTopCakeRatingList[i].numberRating;
                }
            } else {
                const cakeListLength = cakeList.length;
                for (var i = 0; i < ratingListLength; i++) {
                    for (var j = 0; j < cakeListLength; j++) {
                        if (data[i].cake.cakeName === cakeList[j]) {
                            topCakeRatingList[j].totalRating += data[i].ratingValue;
                            topCakeRatingList[j].numberRating += 1;
                        }
                    }
                }
                limitCakeList = cakeList.slice((currentBarChart - 1) * 5, (currentBarChart - 1) * 5 + 5);
                const limitTopCakeRatingList = topCakeRatingList.slice((currentBarChart - 1) * 5, (currentBarChart - 1) * 5 + 5);
                barCharValue = [];
                for (var i = 0; i < 5; i++) {
                    if (limitTopCakeRatingList[i].numberRating === 0) {
                        limitTopCakeRatingList[i].numberRating = 1;
                    }
                    barCharValue[i] = limitTopCakeRatingList[i].totalRating / limitTopCakeRatingList[i].numberRating;
                }
            }
            // Single Bar Chart
            myChart4.destroy();
            myChart4 = new Chart(ctx4, {
                type: "bar",
                data: {
                    labels: limitCakeList,
                    datasets: [{
                            label: "Average rating",
                            backgroundColor: [
                                "rgba(235, 22, 22, .7)",
                                "rgba(235, 22, 22, .6)",
                                "rgba(235, 22, 22, .5)",
                                "rgba(235, 22, 22, .4)",
                                "rgba(235, 22, 22, .3)"
                            ],
                            data: barCharValue
                        }]
                },
                options: {
                    responsive: true
                }
            });

        }
    });

    $(document).on("click", "#top-weak-bar-chart-dropdown .dropdown-item", function () {
        document.getElementById('top-weak-bar-chart-dropdown').querySelector('.active-top-bar').classList.remove('active-top-bar');
        this.classList.add('active-top-bar');
        document.getElementById('top-weak-bar-chart-btn').innerHTML = `${this.innerHTML}`;
        activeTopBar = this.id.split('top-bar-option-')[1];
        let limitCakeList;
        let barCharValue;
        currentBarChart = 1;
        document.getElementById('back-page-btn').disabled = true;
        document.getElementById('back-page-btn').classList.add('disable-icon');
        const ratingListLength = data.length;
        if (activeTopBar !== "all") {
            const activeStartDay = weakList[activeTopBar].start.split('-');
            const start = new Date(activeStartDay[2], activeStartDay[1] - 1, activeStartDay[0]);
            const startDate = new Date(start);
            const weakActive = [];
            for (let i = 0; i < 7; i++) {
                const nextDay = new Date(startDate);
                nextDay.setDate(startDate.getDate() + i);
                const formattedDate = formatDate(nextDay);
                weakActive.push(formattedDate);
            }
            let cakeList = [];
            let topCakeRatingList = [];
            let weakActiveLenth = weakActive.length;
            for (var i = 0; i < ratingListLength; i++) {
                if (!cakeList.includes(data[i].cake.cakeName)) {
                    let isPush = false;
                    for (var j = 0; j < weakActiveLenth; j++) {
                        if (data[i].ratingDate === weakActive[j]) {
                            if (!isPush) {
                                cakeList.push(data[i].cake.cakeName);
                                isPush = true;
                            }
                            topCakeRatingList.push({"cakeName": cakeList[cakeList.length - 1], "totalRating": 0, "numberRating": 0});
                        }
                    }

                }
            }
            document.getElementById('charBar-Pagination').innerHTML = `Chart ${currentBarChart} of ${Math.ceil(cakeList.length / 5)}`;
            if (Math.ceil(cakeList.length / 5) === 1) {
                document.getElementById('next-page-btn').disabled = true;
                document.getElementById('next-page-btn').classList.add('disable-icon');
            } else {
                document.getElementById('next-page-btn').disabled = false;
                document.getElementById('next-page-btn').classList.remove('disable-icon');
            }
            const cakeListLength = cakeList.length;
            for (var i = 0; i < ratingListLength; i++) {
                for (var j = 0; j < cakeListLength; j++) {
                    if (data[i].cake.cakeName === cakeList[j]) {
                        for (var k = 0; k < weakActiveLenth; k++) {
                            if (data[i].ratingDate === weakActive[k]) {
                                topCakeRatingList[j].totalRating += data[i].ratingValue;
                                topCakeRatingList[j].numberRating += 1;
                            }
                        }
                    }

                }
            }
            limitCakeList = cakeList.slice((currentBarChart - 1) * 5, (currentBarChart - 1) * 5 + 5);
            const limitTopCakeRatingList = topCakeRatingList.slice((currentBarChart - 1) * 5, (currentBarChart - 1) * 5 + 5);
            barCharValue = [];
            const limitLength = limitCakeList.length;
            for (var i = 0; i < limitLength; i++) {
                if (limitTopCakeRatingList[i].numberRating === 0) {
                    limitTopCakeRatingList[i].numberRating = 1;
                }
                barCharValue[i] = limitTopCakeRatingList[i].totalRating / limitTopCakeRatingList[i].numberRating;
            }
        } else {
            let cakeList = [];
            let topCakeRatingList = [];
            for (var i = 0; i < ratingListLength; i++) {
                if (!cakeList.includes(data[i].cake.cakeName)) {
                    cakeList.push(data[i].cake.cakeName);
                    topCakeRatingList.push({"cakeName": cakeList[cakeList.length - 1], "totalRating": 0, "numberRating": 0});
                }
            }
            document.getElementById('charBar-Pagination').innerHTML = `Chart ${currentBarChart} of ${Math.ceil(cakeList.length / 5)}`;
            if (Math.ceil(cakeList.length / 5) === 1) {
                document.getElementById('next-page-btn').disabled = true;
                document.getElementById('next-page-btn').classList.add('disable-icon');
            } else {
                document.getElementById('next-page-btn').disabled = false;
                document.getElementById('next-page-btn').classList.remove('disable-icon');
            }
            const cakeListLength = cakeList.length;
            for (var i = 0; i < ratingListLength; i++) {
                for (var j = 0; j < cakeListLength; j++) {
                    if (data[i].cake.cakeName === cakeList[j]) {
                        topCakeRatingList[j].totalRating += data[i].ratingValue;
                        topCakeRatingList[j].numberRating += 1;
                    }
                }
            }
            limitCakeList = cakeList.slice(0, 5);
            const limitTopCakeRatingList = topCakeRatingList.slice(0, 5);
            barCharValue = [];
            for (var i = 0; i < 5; i++) {
                if (limitTopCakeRatingList[i].numberRating === 0) {
                    limitTopCakeRatingList[i].numberRating = 1;
                }
                barCharValue[i] = limitTopCakeRatingList[i].totalRating / limitTopCakeRatingList[i].numberRating;
            }
        }
        // Single Bar Chart
        myChart4.destroy();
        myChart4 = new Chart(ctx4, {
            type: "bar",
            data: {
                labels: limitCakeList,
                datasets: [{
                        label: "Average rating",
                        backgroundColor: [
                            "rgba(235, 22, 22, .7)",
                            "rgba(235, 22, 22, .6)",
                            "rgba(235, 22, 22, .5)",
                            "rgba(235, 22, 22, .4)",
                            "rgba(235, 22, 22, .3)"
                        ],
                        data: barCharValue
                    }]
            },
            options: {
                responsive: true
            }
        });

    });


    $(document).on("click", "#line-chart-option-dropdown .dropdown-item", function () {
        document.getElementById('line-chart-option-dropdown').querySelector('.active-weak-line').classList.remove('active-weak-line');
        this.classList.add('active-weak-line');
        document.getElementById('action-weak-btn').innerHTML = `${this.innerHTML}`;
        activeWeakLine = document.getElementById('weakLine-dropdown').querySelector('.active-weak-line').id.split('weak-line-option-')[1];
        updateWeakChart();
    });

    $(document).on("click", "#cakeType-weak-dropdown .dropdown-item", function () {
        document.getElementById('cakeType-weak-dropdown').querySelector('.active-weak-line').classList.remove('active-weak-line');
        this.classList.add('active-weak-line');
        document.getElementById('cakeType-weak-button').innerHTML = `${this.innerHTML}`;
        activeWeakLine = document.getElementById('weakLine-dropdown').querySelector('.active-weak-line').id.split('weak-line-option-')[1];
        updateWeakChart();
    });

    $(document).on("click", "#weak-option-dropdown .dropdown-item", function () {
        document.getElementById('weak-line-option-' + activeWeakLine).classList.remove('active-weak-line');
        const activeOption = document.getElementById(this.id);
        activeOption.classList.add('active-weak-line');
        activeWeakLine = this.id.split('weak-line-option-')[1];
        document.getElementById('weak-option-dropdown-btn').innerHTML = `${weakList[activeWeakLine].start} To ${weakList[activeWeakLine].end}`;
        updateWeakChart();

    });
    function updateWeakChart() {
        const cakeType = document.getElementById('cakeType-weak-dropdown').querySelector('.active-weak-line').innerHTML;
        let ratingList = data.slice();
        if (cakeType !== 'All Cake Type') {
            for (let i = ratingList.length - 1; i >= 0; i--) {
                if (ratingList[i].cake.cakeType !== cakeType) {
                    ratingList.splice(i, 1);
                }
            }
        }
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
        const actionOption = document.getElementById('line-chart-option-dropdown').querySelector('.active-weak-line').innerHTML;
        let singleLineChartData = [0, 0, 0, 0, 0, 0, 0];
        let label = "";
        if (actionOption === "Number of Ratings") {
            const ratingListLength = ratingList.length;
            for (var i = 0; i < ratingListLength; i++) {
                for (var j = 0; j < 7; j++) {
                    if (ratingList[i].ratingDate === weakActive[j]) {
                        singleLineChartData[j] += 1;
                    }
                }
            }
            label = "Number of ratings";
        } else {
            const numberRating = [{number: 0, totalValue: 0}, {number: 0, totalValue: 0}, {number: 0, totalValue: 0},
                {number: 0, totalValue: 0}, {number: 0, totalValue: 0}, {number: 0, totalValue: 0}, {number: 0, totalValue: 0}];
            const ratingListLength = ratingList.length;
            for (var i = 0; i < ratingListLength; i++) {
                for (var j = 0; j < 7; j++) {
                    if (ratingList[i].ratingDate === weakActive[j]) {
                        numberRating[j].number += 1;
                        numberRating[j].totalValue += ratingList[i].ratingValue;
                    }
                }
            }
            for (var j = 0; j < 7; j++) {
                if (numberRating[j].number === 0) {
                    numberRating[j].number = 1;
                }
                singleLineChartData[j] = numberRating[j].totalValue / numberRating[j].number;
            }
            label = "Average Rating";
        }
        myChart3.destroy();
        myChart3 = new Chart(ctx3, {
            type: "line",
            data: {
                labels: weakActive,
                datasets: [{
                        label: label,
                        fill: true,
                        backgroundColor: "rgba(235, 22, 22, .7)",
                        data: singleLineChartData
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
