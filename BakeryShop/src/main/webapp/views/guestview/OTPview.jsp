<%-- 
    Document   : OTPview
    Created on : Feb 25, 2024, 9:02:00 PM
    Author     : HuynhLNCE171797
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <link rel="stylesheet" href="../../assets/css/otpview.css" />
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css" />
        <link rel="stylesheet" href="../../assets/css/style.css" />
    </head>
    <body>
        <div class="content-center">
            <div class="left">
                <h1>Indulge in the perfection of delightful cakes!</h1>
            </div>
            <div class="right">
                <form class="form" action="#" method="post">
                    <h1>VERIFICATION</h1>
                    <p class="title">Please enter the OTP send your mobile number</p>
                    <div class="input">
                        <div class="input-field">
                            <input type="number" />
                            <input type="number" />
                            <input type="number" />
                            <input type="number" />
                            <input type="number" />
                            <input type="number" />
                        </div>
                    </div>
                    <div class="title-1">
                        <p>Don't revive an OTP? <a href="#">Send OTP</a></p>
                    </div>
                    <button class="btn-otp active" type="submit">Validate</button>
                </form>
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
        <script src="../../assets/javascript/OTP.js"></script>
    </body>
</html>
