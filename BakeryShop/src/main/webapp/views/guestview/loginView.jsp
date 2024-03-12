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
        <link rel="stylesheet" href="../../assets/css/login.css" />
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css" />
        <title>Login</title>
    </head>
    <body style="overflow: hidden">
        <%@include file="../homeviews/customer-header.jsp" %>
        <div class="content-center">
            <div class="left">
                <h1>Indulge in the perfection of delightful cakes!</h1>
            </div>
            <div class="right">
                <form action="login" method="POST" id="loginForm" class="needs-validation" novalidate>
                    <h2>LOGIN</h2>
                    <div class="form-group fullname">
                        <div class="title">
                            <label for="username">Username</label>
                        </div>
                        <input class="form-control username" value="${sessionScope.rememeberusername}" type="text" id="username" name="username" required>
                        <div class="invalid-feedback">Please enter a username!</div>
                    </div>
                    <div class="form-group box password">
                        <div class="title">
                            <label for="password">Password</label>
                        </div>
                        <input class="form-control" type="password" id="password" name="password" required>
                        <div class="invalid-feedback">Please enter a password!</div>
                    </div>
                    <div class="container remember-me">
                        <div class="remember-check">
                            <input type="checkbox" id="remember-me" name="remember-me" />
                            <label for="remember-me">Remember me</label>
                        </div>
                        <div class="forgot-link">
                            <p class="forgotPassword">
                                <a href="../guestview/forgotpassword.jsp">Forgot Password?</a>
                            </p>
                        </div>
                    </div>
                    <div style="color: red" id="accountValid">${errorMessage}</div>
                    <div class="submit" style="text-align: center">
                        <button class="button" type="submit">LOGIN</button>
                    </div>
                </form>
                <div class="form-group khac">
                    <p class="or">_________________<span>Or</span>_________________</p>
                    <div class="google">
                        <img src="../../Image/Login/google.jpg" alt="google" />
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost:8080/BakeryShop/LoginGoogle&response_type=code&client_id=419792958495-cne67vhk6lh27eg32he4rltd8440sv7d.apps.googleusercontent.com&approval_prompt=force">
                            <p>Continue with Google</p></a>
                    </div>
                    <div class="register">
                        <p>Need an account ?<a href="register"> SIGN-UP</a></p>
                    </div>
                </div>

            </div>

            <div class="candy-cake">
                <img class="candy" src="../../Image/Login/candy-cane.png" alt="candy" />
            </div>
            <img class="cake-1" src="../../Image/Login/strawberry-cake (1).png" alt="cake" />

            <img class="cake-2" src="../../Image/Login/strawberry-cake (2).png" alt="cake" />

            <img class="cake-3" src="../../Image/Login/strawberry-cake.png" alt="cake" />

            <img class="cake-4" src="../../Image/Login/strawberrymacaron.png" alt="cake" />
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
        <script src="../../assets/javascript/login.js"></script>
    </body>
</html>
