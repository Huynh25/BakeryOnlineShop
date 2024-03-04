<%-- 
    Document   : register-view
    Created on : Feb 23, 2024, 10:51:45 AM
    Author     : acer
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
        <link rel="stylesheet" href="../../assets/css/register.css" />
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css" />
        <link rel="stylesheet" href="../../assets/css/style.css" />
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
                <form action="register" method="post" id="registerForm">
                    <div class="form-group fullname">
                        <div class="title"><label for="fname">Full Name</label></div>
                        <input class="fnbox" type="text" id="fullname" name="fullname" />
                        <div id="fullname-error"></div>
                    </div>
                    <div class="form-group box mailBox">
                        <div class="title"><label for="email">Email</label></div>
                        <input type="email" id="email" name="email" />
                    </div>
                    <div class="pandp">
                        <div class="form-group box phone">
                            <div class="title-phone"><label for="phone">Phone</label></div>
                            <input type="number" id="phoneNumer" name="phoneNumer" />
                        </div>
                        <div class="form-group box password">
                            <div class="title-password">
                                <label for="pwd">Password</label>
                            </div>
                            <input type="password" id="password" name="password" />
                        </div>
                    </div>
                    <div class="form-group box address">
                        <div class="title"><label for="address">Address</label></div>
                        <input type="text" id="address" name="address" />
                    </div>
                    <div>
                        <div class="overall-error" id="overall-error"></div>
                    </div>
                    <div class="form-group khac">
                        <input class="button" type="submit" value="CREATE ACCOUNT" />
                    </div>
                </form>
                <div class="form-group khac">
                    <p class="login">Already have an account? <a href="login">Login</a></p>
                    <p class="or">_________________<span>Or</span>_________________</p>
                    <div class="google">
                        <img src="../../Image/Login/google.jpg" alt="google" />
                        <a href="#"><p>Continue with Google</p></a>
                    </div>
                </div>

            </div>
            <div class="img-cake">
                <div class="candy-cake">
                    <img class="candy" src="../../Image/Login/candy-cane.png" alt="candy" />
                </div>
                <div class="cake">
                    <img class="cake-1" src="../../Image/Login/strawberry-cake (1).png" alt="cake" />
                </div>
                <div class="cake">
                    <img class="cake-2" src="../../Image/Login/strawberry-cake (2).png" alt="cake" />
                </div>
                <div class="cake">
                    <img class="cake-3" src="../../Image/Login/strawberry-cake.png" alt="cake" />
                </div>
                <div class="cake">
                    <img class="cake-4" src="../../Image/Login/strawberrymacaron.png" alt="cake" />
                </div>
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
