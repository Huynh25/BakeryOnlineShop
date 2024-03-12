/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function cancelEdit() {
    window.location.href = "/home";
}
function handleAvatar(event) {
    const avatarInput = "../../Image/Avatar/" + event.target.files[0].name;
    document.getElementById('show-avatar').src = avatarInput;
    document.getElementById('inputAvatar').value = avatarInput;
}