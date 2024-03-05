/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
    var countDown = 60; // Số giây đếm ngược ban đầu
    var countdownElement = document.getElementById('countdown');

    function startCountdown() {
        var interval = setInterval(function () {
            countDown--; // Giảm số giây đếm ngược
            countdownElement.textContent = countDown + ' second'; // Cập nhật nội dung trong thẻ

            if (countDown <= 0) {
                clearInterval(interval); // Dừng đếm ngược khi hết thời gian
                countdownElement.textContent = 'Expired'; // Hiển thị thông báo hết thời gian
            }
        }, 1000); // Đếm ngược mỗi giây
    }

    startCountdown();