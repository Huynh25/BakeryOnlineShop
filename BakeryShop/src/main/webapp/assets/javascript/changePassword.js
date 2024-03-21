/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
$("input[type=password]").keyup(function(){
    var ucase = new RegExp("[A-Z]+");
	var lcase = new RegExp("[a-z]+");
	var num = new RegExp("[0-9]+");
	
	if($("#newPassword").val().length >= 6){
		$("#8char").removeClass("glyphicon-remove");
		$("#8char").addClass("glyphicon-ok");
		$("#8char").css("color","#00A41E");
	}else{
		$("#8char").removeClass("glyphicon-ok");
		$("#8char").addClass("glyphicon-remove");
		$("#8char").css("color","#FF0004");
	}
	
	if(ucase.test($("#newPassword").val())){
		$("#ucase").removeClass("glyphicon-remove");
		$("#ucase").addClass("glyphicon-ok");
		$("#ucase").css("color","#00A41E");
	}else{
		$("#ucase").removeClass("glyphicon-ok");
		$("#ucase").addClass("glyphicon-remove");
		$("#ucase").css("color","#FF0004");
	}
	
	if(lcase.test($("#newPassword").val())){
		$("#lcase").removeClass("glyphicon-remove");
		$("#lcase").addClass("glyphicon-ok");
		$("#lcase").css("color","#00A41E");
	}else{
		$("#lcase").removeClass("glyphicon-ok");
		$("#lcase").addClass("glyphicon-remove");
		$("#lcase").css("color","#FF0004");
	}
	
	if(num.test($("#newPassword").val())){
		$("#num").removeClass("glyphicon-remove");
		$("#num").addClass("glyphicon-ok");
		$("#num").css("color","#00A41E");
	}else{
		$("#num").removeClass("glyphicon-ok");
		$("#num").addClass("glyphicon-remove");
		$("#num").css("color","#FF0004");
	}
	
	if($("#newPassword").val() === $("#password2").val()){
		$("#pwmatch").removeClass("glyphicon-remove");
		$("#pwmatch").addClass("glyphicon-ok");
		$("#pwmatch").css("color","#00A41E");
	}else{
		$("#pwmatch").removeClass("glyphicon-ok");
		$("#pwmatch").addClass("glyphicon-remove");
		$("#pwmatch").css("color","#FF0004");
	}
});

function cancelChange() {
    window.location.href = "/home";
}
    
    function validatePasswords() {
        var currentPassword = document.getElementById("currentpassword").value;
        var newPassword = document.getElementById("newPassword").value;
        var confirmPassword = document.getElementById("password2").value;

        if (currentPassword.trim() === '' || newPassword.trim() === '' || confirmPassword.trim() === '') {
            document.getElementById("passwordValid").innerHTML = "Please complete all information";
            return false;
        }
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

    function clearErrorMessage() {
        document.getElementById("passwordValid").innerHTML = "";
    }
    
    