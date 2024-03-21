
var allConditionsMet = false;
var confirmPassword = false;

$("input[type=password]").keyup(function () {
    var ucase = new RegExp("[A-Z]+");
    var lcase = new RegExp("[a-z]+");
    var num = new RegExp("[0-9]+");

    if ($("#password1").val().length >= 6) {
        $("#8char").removeClass("glyphicon-remove");
        $("#8char").addClass("glyphicon-ok");
        $("#8char").css("color", "#00A41E");
    } else {
        $("#8char").removeClass("glyphicon-ok");
        $("#8char").addClass("glyphicon-remove");
        $("#8char").css("color", "#FF0004");
    }

    if (ucase.test($("#password1").val())) {
        $("#ucase").removeClass("glyphicon-remove");
        $("#ucase").addClass("glyphicon-ok");
        $("#ucase").css("color", "#00A41E");
    } else {
        $("#ucase").removeClass("glyphicon-ok");
        $("#ucase").addClass("glyphicon-remove");
        $("#ucase").css("color", "#FF0004");
    }

    if (lcase.test($("#password1").val())) {
        $("#lcase").removeClass("glyphicon-remove");
        $("#lcase").addClass("glyphicon-ok");
        $("#lcase").css("color", "#00A41E");
    } else {
        $("#lcase").removeClass("glyphicon-ok");
        $("#lcase").addClass("glyphicon-remove");
        $("#lcase").css("color", "#FF0004");
    }

    if (num.test($("#password1").val())) {
        $("#num").removeClass("glyphicon-remove");
        $("#num").addClass("glyphicon-ok");
        $("#num").css("color", "#00A41E");
    } else {
        $("#num").removeClass("glyphicon-ok");
        $("#num").addClass("glyphicon-remove");
        $("#num").css("color", "#FF0004");
    }

    if ($("#password1").val() === $("#password2").val()) {
        $("#pwmatch").removeClass("glyphicon-remove");
        $("#pwmatch").addClass("glyphicon-ok");
        $("#pwmatch").css("color", "#00A41E");
    } else {
        $("#pwmatch").removeClass("glyphicon-ok");
        $("#pwmatch").addClass("glyphicon-remove");
        $("#pwmatch").css("color", "#FF0004");
    }

    // Kiểm tra tất cả các điều kiện
    if ($("#password1").val().length >= 6 &&
            ucase.test($("#password1").val()) &&
            lcase.test($("#password1").val()) &&
            num.test($("#password1").val())) {
        // Nếu tất cả các điều kiện đều đúng, đặt biến cờ là true
        allConditionsMet = true;
    } else if ($("#password1").val() === $("#password2").val()) {
        confirmPassword = true;
    } else {
        allConditionsMet = false;
    }
});

function cancelChange() {
    window.location.href = "/home";
}
    function validatePasswordReset() {
        var newPassword = document.getElementById("password1").value;
        var confirmPassword = document.getElementById("password2").value;

        if (newPassword !== confirmPassword) {
            document.getElementById("passwordValid").innerHTML = "Confirmation password does not match";
            return false;
        }
        var uppercaseRegex = /[A-Z]/;
        var lowercaseRegex = /[a-z]/;
        var numberRegex = /[0-9]/;
        if (newPassword.length < 6 || !uppercaseRegex.test(newPassword) || !lowercaseRegex.test(newPassword) || !numberRegex.test(newPassword)) {
            document.getElementById("passwordValid").innerHTML = "New password is not valid";
            return false;
        }
        return true;
    }