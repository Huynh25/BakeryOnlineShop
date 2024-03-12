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
        const usernameInput = document.getElementById('username');
        const passwordInput = document.getElementById('password');
        const errorMessageDiv = document.getElementById('accountValid');
        usernameInput.addEventListener('input', function () {
            errorMessageDiv.innerText = '';
        });
        passwordInput.addEventListener('input', function () {
            errorMessageDiv.innerText = '';
        });
    });
