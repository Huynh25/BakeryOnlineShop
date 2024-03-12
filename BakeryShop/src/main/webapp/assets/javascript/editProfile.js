/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function uploadAvatar() {
    var avatarInput = document.getElementById("avatarUpload");
    var avatarImage = document.getElementById("avatarImage");
    if (avatarInput.files && avatarInput.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            avatarImage.src = e.target.result;
        };
        reader.readAsDataURL(avatarInput.files[0]);
    }
}
function cancelEdit() {
    window.location.href = "/home";
}
document.addEventListener('DOMContentLoaded', function () {
        const avatarWrapper = document.querySelector('.avatar-wrapper');
        const fileInput = document.getElementById('file');

        // Khi hình ảnh được nhấp, kích hoạt sự kiện click cho input file
        avatarWrapper.addEventListener('click', function () {
            fileInput.click();
        });
    });
