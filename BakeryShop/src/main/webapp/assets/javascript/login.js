/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
$(document).ready(function () {
    $('#loginForm').submit(function (event) {
        var username = $('#username').val();
        var password = $('#password').val();
        if (username.trim() === '' && password.trim() === '') {
            $('#accountValid').text('Please enter username and password');
            event.preventDefault();
        } else if (username.trim() === '') {
            $('#userValid').text('Please enter username!');
            event.preventDefault();
        } else if (password.trim() === '') {
            $('#passValid').text('Please enter password!');
            event.preventDefault();
        }
    });        
});



