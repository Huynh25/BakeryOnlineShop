<%-- 
    Document   : view-rating
    Created on : Feb 20, 2024, 9:08:48 AM
    Author     : Tran Nguyen Nam Thuan CE171497
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Math" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Staff Management</title>
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/staff-management.css">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Inter&family=Unna:ital,wght@0,400;0,700;1,400;1,700&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    </head>

    <body>
        <%@include file="../homeviews/manager-header.jsp" %>
        <div id="content">   
            <div id="url-and-add-staff-btn">
                <div id="url">
                    <a id="home-link" href="#">Home</a>
                    <i id="next-btn" class="bi bi-chevron-compact-right"></i>
                    <span id="title">Staff Management</span>
                </div>
                <div id="statistic-link-wrapper">                       
                    <a href="../../views/adminviews/staff-statistic-page.jsp" id="statistic-link">Statistics <i class="bi bi-bar-chart"></i></a>
                    <button id="add-staff-btn" >Add Staff<i class="bi bi-person-plus"></i></button>
                </div>
            </div>
            <div id="staff-content" class="row">
                <c:forEach var="staff" items="${staffList}">
                    <div class="staff-card col-sm-3" onclick="staffDetail('${staff.staffID}')">
                        <div class="staff-image-container">
                            <img class="staff-image" src="../../${staff.staffAvatar}" alt="alt"/>  
                        </div>
                        <div class="staff-name"><i class="bi bi-person-fill"></i> ${staff.fullname}</div>
                        <div class="staff-content-row-1">
                            <div><i class="bi bi-telephone"></i> ${staff.phoneNumber}</div>
                            <div><i class="bi bi-envelope"></i> ${staff.email}</div>
                        </div>
                        <div class="staff-address"><i class="bi bi-geo-alt"></i> ${staff.address}</div>                 
                    </div>
                </c:forEach>
                <form action="staff-management" method="GET" id="staffDetailForm">
                    <input type="text" name="staffID" id="staffID" hidden="">
                </form>

            </div>
        </div> 
        <script src="../../assets/javascript/staff-management.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    </body>

</html>