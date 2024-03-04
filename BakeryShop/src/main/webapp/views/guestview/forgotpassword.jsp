<%-- 
    Document   : forgotpassword
    Created on : Feb 25, 2024, 9:01:52 PM
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
        <link rel="stylesheet" href="../../assets/css/forgotpassword.css" />
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css" />
        <link rel="stylesheet" href="../../assets/css/style.css" />
        <title>Forgot Password</title>
    </head>
    <body>
        <div id="header" class="row header">
            <div class="row col-sm-5">
                <div class="col-sm-5" id="catalog-container">
                    <a id="catalog-link" href="#">Catalog</a>
                </div>
                <div class="col-sm-7">
                    <form id="search-cake">
                        <input
                            type="text"
                            name="search-cake-input"
                            id="search-cake-input"
                            placeholder="Search Cake"
                            />
                        <button type="submit" id="search-cake-btn">
                            <i class="bi bi-search"></i>
                        </button>
                    </form>
                </div>
            </div>
            <div class="col-sm-4">
                <a href="#" id="backhome-link">
                    <h3 id="bakery-name">ELEGANTO</h3>
                </a>
            </div>
            <div class="col-sm-3">
                <ul id="navbar-icon">
                    <li>
                        <a href="#"><i class="bi bi-basket3"></i></a>
                    </li>
                    <li>
                        <a href="#"><i class="bi bi-clock-history"></i></a>
                    </li>
                    <li>
                        <a href=""><i class="bi bi-person-circle"></i></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="content-center">
            <div class="left">
                <h1>Indulge in the perfection of delightful cakes!</h1>
            </div>
            <div class="right">
                <form class="form" action="#" method="post">
                    <h2>FORGOT PASSWORD</h2>
                    <p class="title">Enter your phone number to receive the OTP code</p>
                    <div class="input">
                        <div class="phone">
                            <input type="text" placeholder="  Phone or Email" />
                        </div>
                        <div class="username">
                            <input type="text" placeholder="  Username" />
                        </div>
                    </div>
                    <div class="title-1">
                        <p>The code expires in <span>5 minute</span>s</p>
                    </div>

                    <button class="btn-otp" type="submit">Verify Code</button>
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
        <script src="./js/login.js"></script>
    </body>
</html>
