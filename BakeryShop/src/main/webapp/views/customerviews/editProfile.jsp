<%-- 
    Document   : editProfile
    Created on : Feb 23, 2024, 10:52:25 AM
    Author     : HuynhLNCE171797
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <link rel="stylesheet" href="../../assets/css/editProfile.css" />
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Inter&family=Unna:ital,wght@0,400;0,700;1,400;1,700&display=swap"
            rel="stylesheet"
            />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
            integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
            crossorigin="anonymous"
            />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
            />
    </head>
    <body>
        <%@include file="../homeviews/customer-header.jsp" %>
        <div class="form-edit">
            <form class="form" action="editProfile" method="POST">
                <div class="col-md-2 right-avatar file-input-wrapper ">
                    <input type="text" name="userID" value="${customer.userID}" hidden=""/>
                    <label for="file" class="avatar-wrapper">
                        <image src="${customer.userAvatar}" alt="Avatar" id="show-avatar" class="userAvatar"/>
                        <input type="file" id="file" onchange="handleAvatar(event)"/>
                        <input type="text" id="inputAvatar" name="avatar" value="${customer.userAvatar}" hidden="">
                    </label>
                    <span id="fileLabel">Change avatar ${successMessage} ${errorMessage}</span>
                </div>
                <div class="col-md-8 container left">
                    <div class="form-group">
                        <label for="fullname">Fullname</label>
                        <input
                            type="text"
                            id="fullname"
                            name="fullname"
                            value="${customer.fullname}"
                            placeholder="Enter your fullname"
                            />
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            value="${customer.email}"
                            placeholder="Enter your email"
                            />
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input

                            type="text"
                            id="address"
                            name="address"
                            value="${customer.address}"
                            placeholder="Enter your address"
                            />
                    </div>
                    <div class="form-group">
                        <label for="phone">Contact number</label>
                        <input

                            type="tel"
                            id="phoneNumber"
                            name="phoneNumber"
                            value="${customer.phoneNumber}"
                            placeholder="Enter your contact number"
                            />
                    </div>
                    <div class="form-group">
                        <button type="button" class="cancel-btn" onclick="cancelEdit()">Cancel</button>
                        <button type="submit" class="save">Save</button>
                    </div>
                </div>
            </form>
        </div>
        <script
            src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"
        ></script>
        <script src="../../assets/javascript/editProfile.js"></script>
    </body>
</html>
