<%-- 
    Document   : editProfile
    Created on : Feb 23, 2024, 10:52:25 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>

        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css" />
        <link rel="stylesheet" href="../../assets/css/changePassword.css" />

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
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    </head>
    <body>
        <%@include file="../homeviews/Header.jsp" %>
        <div class="form-edit">
            <form action="changePassword" method="post" class="form" onsubmit="return validatePasswords()" class="needs-validation" novalidate>
                <input type="text" name="userID" value="${customer.userID}" hidden=""/>
                <div class= "container">
                    <div class="form-group container">
                        <label for="current-password">Current password</label>
                        <input
                            type="password"
                            id="currentpassword"
                            name="currentpassword"
                            value: ${currentpassword}
                            required
                            oninput="clearErrorMessage()"
                            />
                    </div>
                    <div class= "container">
                        <div class="form-group">
                            <label for="email">New password</label>
                            <input
                                type="password"
                                id="newPassword"
                                name="newPassword"
                                value: ${newPassword}
                                required
                                oninput="clearErrorMessage()"
                                />
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <span id="8char" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> 6 Characters Long<br>
                                <span id="ucase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Uppercase Letter
                            </div>
                            <div class="col-sm-6">
                                <span id="lcase" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Lowercase Letter<br>
                                <span id="num" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> One Number
                            </div>
                        </div>
                        <div class="form-group confirm">
                            <label for="address">Confirm password</label>
                            <input
                                type="password"
                                id="password2"
                                name="password2"
                                value: ${password2}
                                required
                                oninput="clearErrorMessage()"
                                />
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <span id="pwmatch" class="glyphicon glyphicon-remove" style="color:#FF0004;"></span> Passwords Match
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="button" class="cancel-btn" onclick="cancelChange()">Cancel</button>
                        <button type="submit" class="save">Save</button>
                    </div>
                </div>
                <div style="color: red" id="passwordValid">${errorMessage}</div>
                ${successMessage}

            </form>
        </div>
        <!--Cart popup-->
        <%@include file="../homeviews/Cart.jsp" %>
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
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
        <script src="../../assets/javascript/changePassword.js"></script>
    </body>
</html>
