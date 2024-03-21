/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
(function () {
    'use strict';
    var forms = document.querySelectorAll('.needs-validation');
    Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated');
                }, false);
            });
})();
function cancelEdit() {
    window.location.href = "/home";
}
function handleAvatar(event) {
    const avatarInput = "../../Image/Avatar/" + event.target.files[0].name;
    document.getElementById('show-avatar').src = avatarInput;
    document.getElementById('inputAvatar').value = avatarInput;
}

    function validateForm() {
        var fullname = document.getElementById("fullname").value;
        var email = document.getElementById("email").value;
        var address = document.getElementById("address").value;
        var phoneNumber = document.getElementById("phoneNumber").value;

        // Kiểm tra xem các trường có được điền vào không
        if (fullname === '' || email === '' || address === '' || phoneNumber === '') {
            alert("Please fill out all fields.");
            return false;
        }

        // Kiểm tra định dạng email
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert("Please enter a valid email address.");
            return false;
        }

        // Kiểm tra định dạng số điện thoại
        var phoneRegex = /^\d{10}$/; // Điện thoại gồm 10 chữ số
        if (!phoneRegex.test(phoneNumber)) {
            alert("Please enter a valid phone number.");
            return false;
        }

        return true;
    }