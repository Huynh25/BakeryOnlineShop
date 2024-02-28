<%-- 
    Document   : view-rating
    Created on : Feb 20, 2024, 9:08:48 AM
    Author     : Tran Nguyen Nam Thuan CE171497
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Staff</title>
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/add-staff.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
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
        <div id="content" class="row">
            <div id="back-btn" class="col-sm-1"><button>Back</button></div>
            <div id="avatar-container" class="col-sm-3">
                <input type="image" id="staff-avatar" src="../../Image/Avatar/add_staff_avatar.jpg" onclick="openFilePicker()" /> 
                <input type="file" onchange="handleAvatar(event)" id="upload-avatar" />             
                <i class="bi bi-upload" id="upload-status"></i>
                <div id="strip">
                    <img id="cake-1" src="../../Image/Cake/add_staff_cake2.jpg" alt="alt"/>
                    <img id="cake-2" src="../../Image/Cake/add_staff_cake1.jpg" alt="alt"/>
                </div>

            </div>
            <div id="form-container" class="col-sm-8">
                <form id="staff-form" class="row" action="../../staff-management" method="POST">
                    <h2 class="col-sm-12" id="add-staff-title">Add Staff</h2>
                    <div class="col-sm-12" id="staff-message">${message}</div>
                    <div class="col-sm-6" id="form-row-1">
                        <label for="staff-name" id="staff-name-label" >Staff Name</label>
                        <input id="staff-name" name="staff-name" type="text" placeholder="Enter StaffName" oninput="resetError(`staff-name-error`)"  />
                        <div id="staff-name-error"></div>
                    </div>
                    <div class="col-sm-6" id="form-row-2">
                        <label for="password" id="password-label">Password</label>
                        <input id="password" name="password" type="password" placeholder="Enter Password" oninput="resetError(`password-error`)" />
                        <div id="password-error"></div>
                    </div>
                    <div class="col-sm-6" id="form-row-3">                       
                        <label for="fullname" id="fullname-label">Full name</label>
                        <input id="fullname" name="fullname" type="text" placeholder="Enter FullName" oninput="resetError(`fullname-error`)" />
                        <div id="fullname-error"></div>
                    </div>
                    <div class="col-sm-6" id="form-row-4">                      
                        <label for="phoneNumber" id="phoneNumber-label">PhoneNumber</label>
                        <input id="phoneNumber" name="phoneNumber" type="number" placeholder="Enter PhoneNumber" oninput="resetError(`phoneNumber-error`)" />
                        <div id="phoneNumber-error"></div>
                    </div>
                    <div class="col-sm-12" id="form-row-5">                        
                        <label for="email" id="email-label">Email</label>
                        <input id="email" name="email" type="email" placeholder="Enter Email" oninput="resetError(`email-error`)" />
                        <div id="email-error"></div>
                    </div>
                    <div class="col-sm-12" id="form-row-6">                       
                        <label for="address" id="address-label">Address</label>
                        <textarea id="address" name="address" type="text" placeholder="Enter Address" rows="4" oninput="resetError(`address-error`)"></textarea>
                        <div id="address-error"></div>
                    </div>
                    <input type="text" hidden="" name="avatar" id="avatar" value="../../Image/Avatar/add_staff_avatar.jpg">
                    <div class="col-sm-12" id="form-row-7">                        
                        <input  type="submit"  value="Submit" id="submit"/>
                    </div>
                </form>
            </div>
        </div>
        <script src="../../assets/javascript/add-staff.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    </body>

</html>