let selectedFile = "../../Image/Avatar/add_staff_avatar.jpg";
function openFilePicker() {
    document.getElementById('upload-avatar').click();
}
function handleAvatar(event) {
    selectedFile = event.target.files[0].name;
    selectedFile="../../Image/Avatar/" + selectedFile;
    document.getElementById('staff-avatar').src =selectedFile ;
    document.getElementById('avatar').value=selectedFile;
}
function validForm() {
    const staffName = document.getElementById("staff-name");
    const password = document.getElementById("password");
    const fullName = document.getElementById("fullname");
    const phoneNumber = document.getElementById("phoneNumber");
    const email = document.getElementById("email");
    const address = document.getElementById("address");
    var specialChar = /[!@#$%^&*(),.?":{}|<>]/; // Kiểm tra kí tự đặc biệt
    var upperCaseChar = /[A-Z]/; // Kiểm tra kí tự viết hoa
    const regex = /@.*\./;
    if (!staffName.value) {
        document.getElementById("staff-name-error").innerHTML = "Please fill in StaffName";
    } else if (!password.value) {
        document.getElementById("password-error").innerHTML = "Please fill in Password";
    } else if (!specialChar.test(password.value) || !upperCaseChar.test(password.value)) {
        document.getElementById("password-error").innerHTML = "Password must have at least 1 special character and 1 capital letter";
    } else if (!fullName.value) {
        document.getElementById("fullname-error").innerHTML = "Please fill in FullName";
    } else if (!fullName.value.trim().includes(" ")) {
        document.getElementById("fullname-error").innerHTML = "The full name must contain at least 1 space in between.";
    } else if (phoneNumber.value.length !== 10) {
        document.getElementById("phoneNumber-error").innerHTML = "Phone Number must have at least 10 numbers";
    } else if (!email.value) {
        document.getElementById("email-error").innerHTML = "Please fill in Email";
    } else if (!regex.test(email.value)) {
        document.getElementById("email-error").innerHTML = "Email is invalid";
    } else if (!address.value) {
        document.getElementById("address-error").innerHTML = "Please fill in Address";
    } else {
        return true;
    }
    return false;
}
document.getElementById('staff-form').addEventListener("submit", function (event) {
    if (!validForm()) {
        event.preventDefault();
    }
});

function resetError(errorElement) {
    document.getElementById(errorElement).innerHTML = "";
    document.getElementById('staff-message').innerHTML="";
}

document.getElementById("back-btn").addEventListener("click", function() {
    window.location.href = "../../staff-management";
});

