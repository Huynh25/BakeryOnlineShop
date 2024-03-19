/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
    var countDown = 60; // Số giây đếm ngược ban đầu
    var countdownElement = document.getElementById('countdown');

    function startCountdown() {
        var interval = setInterval(function () {
            countDown--; 
            countdownElement.textContent = countDown + ' second';

            if (countDown <= 0) {
                clearInterval(interval); 
                countdownElement.textContent = 'Expired'; 
            }
        }, 1000); 
    }

    startCountdown();
     function postDataToServlet() {
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = 'forgotpassword'; 
        var input = document.createElement('input');
        input.type = 'hidden';
        form.appendChild(input);    
        document.body.appendChild(form);
        form.submit();
    }