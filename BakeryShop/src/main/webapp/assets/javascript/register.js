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
document.addEventListener('DOMContentLoaded', function () {
    const fullnameInput = document.getElementById('fullname');
    const emailInput = document.getElementById('email');
    const phoneInput = document.getElementById('phoneNumber');
    const passwordInput = document.getElementById('password');
    const password2Input = document.getElementById('password2');
    const errorMessageDiv = document.getElementById('accountValid');
    function showError(errorMessage) {
        errorMessageDiv.innerText = errorMessage;
    }
    function clearError() {
        errorMessageDiv.innerText = '';
    }
    fullnameInput.addEventListener('input', clearError);
    emailInput.addEventListener('input', clearError);
    phoneInput.addEventListener('input', clearError);
    passwordInput.addEventListener('input', clearError);
    password2Input.addEventListener('input', clearError);
    phoneInput.addEventListener('input', function () {
        const phoneNumber = phoneInput.value;
        const phoneRegex = /^[0-9]{10}$/;
        if (!phoneRegex.test(phoneNumber)) {
            showError("Invalid phone number. Please enter a 10-digit valid phone number.");
        }
    });
    passwordInput.addEventListener('input', function () {
        const password = passwordInput.value;
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
        if (!passwordRegex.test(password)) {
            showError("Invalid password. Password must contain at least 6 characters, including uppercase, lowercase, and special characters.");
        }
    });
    password2Input.addEventListener('input', function () {
        const password = passwordInput.value;
        const password2 = password2Input.value;

        if (password2 !== password) {
            showError("Passwords do not match");
        }
    });
});

