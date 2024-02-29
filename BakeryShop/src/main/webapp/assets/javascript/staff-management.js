document.getElementById("add-staff-btn").addEventListener("click", function () {
    window.location.href = "views/adminviews/add-staff.jsp";
});
function staffDetail(id) {
    document.getElementById('staffID').value = id;
    document.getElementById('staffDetailForm').submit();
}