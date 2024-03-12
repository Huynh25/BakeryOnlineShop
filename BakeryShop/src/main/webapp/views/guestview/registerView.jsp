<%-- 
    Document   : register-view
    Created on : Feb 23, 2024, 10:51:45 AM
    Author     : HuynhLNCE171797
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous"
            />
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
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
            />
        <link rel="stylesheet" href="../../assets/css/register.css" />
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css" />
        <title>Register</title>
    </head>
    <body>
        <%@include file="../homeviews/customer-header.jsp" %>
        <div class="content-center">
            <div class="left">
                <h1>Indulge in the perfection of delightful cakes!</h1>
            </div>
            <div class="right">
                <h2>CREATE ACCOUNT</h2>
                <form action="register" method="post" id="registerForm" onsubmit="return validateForm()" class="needs-validation" novalidate>
                    <div class="form-group">
                        <div class="title"><label for="fname">Full Name</label></div>
                        <input class="form-control" type="text" id="fullname" name="fullname" required/>
                        <div class="invalid-feedback">Please enter Full name!</div>
                    </div>
                    <div class="form-group">
                        <div class="title"><label for="email">Email</label></div>
                        <input class="form-control" type="email" id="email" name="email" required/>
                        <div class="invalid-feedback">Please enter Email!</div>
                    </div>
                    <div class="form-group">
                        <div class="title"><label for="phone">Phone Number</label></div>
                        <input class="form-control" type="number" id="phoneNumber" name="phoneNumber" required/>
                        <div class="invalid-feedback">Please enter a Phone number!</div>
                    </div>
                    <div class="form-group">
                        <div class="title"><label for="password">Password</label></div>
                        <input class="form-control" type="password" id="password" name="password" required/>
                        <div class="invalid-feedback" >Please enter Password!</div>
                    </div>
                    <div class="form-group box">
                        <div class="title"><label for="password">Confirm Password</label></div>
                        <input class="form-control" type="password" id="password2" name="password2" required/>
                        <div class="invalid-feedback">Please enter Confirm Password!</div>
                    </div>
                    <div style="color: red" id="accountValid">${error}</div>
                    <div class="form-group">
                        <input class="button" type="submit" value="CREATE ACCOUNT" />
                    </div>
                </form>
                <div class="form-group">
                    <p class="login">Already have an account? <a href="login">Login</a></p>
                </div>
            </div>
            <div class="img-cake cake">
                <img class="candy" src="../../Image/Login/candy-cane.png" alt="candy" />
                <img class="cake-1" src="../../Image/Login/strawberry-cake (1).png" alt="cake" />
                <img class="cake-2" src="../../Image/Login/strawberry-cake (2).png" alt="cake" />
                <img class="cake-3" src="../../Image/Login/strawberry-cake.png" alt="cake" />
                <img class="cake-4" src="../../Image/Login/strawberrymacaron.png" alt="cake" />
            </div>
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
        <script src="../../assets/javascript/register.js"></script>
    </body>
</html>
