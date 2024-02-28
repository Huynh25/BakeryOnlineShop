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
            <form action="editProfile" method="post" class="form">
                <div class="col-md-2 right-avatar">
                    <img class="avatar" src="../../Image/Avatar/editAvatar.png" alt="Avatar" />
<!--                    <img src="${avt}" name="avt" alt="Avatar" class="img-fluid my-5" style="width: 80px; " />-->
                    <div class="file-input-wrapper">
                        <input  type="file" id="avt" accept=".png,.jpg" name="avt" required >
                    </div>
                </div>
                <div class="col-md-8 container left">
                    <div class="form-group">
                        <label for="fullname">Fullname</label>
                        <input
                            type="text"
                            id="fullname"
                            name="fullname"
                            placeholder="Enter your fullname"
                            value: ${fullname}
                            />
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input
                            type="email"
                            id="email"
                            name="email"
                            placeholder="Enter your email"
                            value: ${email}
                            />
                    </div>
                    <div class="form-group">
                        <label for="address">Address</label>
                        <input
                            type="text"
                            id="address"
                            name="address"
                            placeholder="Enter your address"
                            value: ${address}
                            />
                    </div>
                    <div class="form-group">
                        <label for="phone">Contact number</label>
                        <input
                            type="tel"
                            id="phone"
                            name="phone"
                            placeholder="Enter your contact number"
                            value: ${phone}
                            />
                    </div>
                    <div class="form-group">
                        <button class="cancel-btn">Cancel</button>
                        <button class="save">Save</button>
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
    </body>
</html>