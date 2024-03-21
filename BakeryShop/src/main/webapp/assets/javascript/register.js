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
    phoneInput.addEventListener('input', function () {
        const phoneNumber = phoneInput.value;
        const phoneRegex = /^[0-9]{10}$/;
        if (!phoneRegex.test(phoneNumber)) {
            showError("Invalid phone number. Please enter a 10-digit valid phone number.");
        }
    });
});

function checkUsernameValidity() {
    var username = document.getElementById("username").value;
    var usernameFeedback = document.getElementById("usernameFeedback");
    if (/\s/.test(username)) {
        usernameFeedback.style.display = "block";
        usernameFeedback.innerHTML = "Username cannot contain spaces!";
        return false;
    }
    var hasDiacritic = /[àáảãạâầấẩẫậăằắẳẵặèéẻẽẹêềếểễệđìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵÀÁẢÃẠÂẦẤẨẪẬĂẰẮẲẴẶÈÉẺẼẸÊỀẾỂỄỆĐÌÍỈĨỊÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢÙÚỦŨỤƯỪỨỬỮỰỲÝỶỸỴ]/.test(username);
    if (hasDiacritic) {
        usernameFeedback.style.display = "block";
        usernameFeedback.innerHTML = "Username cannot contain diacritics!";
        return false;
    }

    // Nếu username hợp lệ, ẩn thông báo lỗi
    usernameFeedback.style.display = "none";
    return true;
}

function validateForm() {
  var password = document.getElementById("password").value;
  var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,}$/;
  if (!passwordRegex.test(password)) {
    var errorElement = document.getElementById("passwordFeedback");
    errorElement.textContent = "Invalid password";

    // Xóa nội dung của ô mật khẩu
    document.getElementById("password").value = "";

    // Thêm lớp CSS "is-invalid" cho chữ báo lỗi
    errorElement.classList.add("is-invalid");

    return false;
  }

  return true;
}