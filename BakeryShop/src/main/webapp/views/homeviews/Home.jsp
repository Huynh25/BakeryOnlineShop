<%-- 
    Document   : home
    Created on : Feb 19, 2024, ‏‎6:09:23 PM
    Author     : Tran Gia Huy CE170732
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Math" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>

        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <!-- fonts & icons -->
        <link href='https://fonts.googleapis.com/css?family=Inter|Unna' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

        <!-- css -->
        <link rel="stylesheet" href="../../assets/css/reset.css">

        <link rel="stylesheet" href="../../assets/css/footer.css">


        <link rel="stylesheet" href="../../assets/css/home.css">


    </head>

    <body>
        <div class="page">
            <!-- Header -->
            <c:choose>
                <c:when test="${'customer'.equalsIgnoreCase(role)}">
                    <%@include file="../homeviews/customer-header.jsp" %>
                </c:when>
                <c:when test="${'manager'.equalsIgnoreCase(role)}">
                    <%@include file="../homeviews/manager-header.jsp" %>
                </c:when>
                <c:when test="${'staff'.equalsIgnoreCase(role)}">
                    <%@include file="../homeviews/staff-header.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@include file="../homeviews/customer-header.jsp" %>
                </c:otherwise>
            </c:choose>

            <!-- Banner -->
            <div class="banner container-fluid ">
                <div class="banner-content container row
                     justify-content-between">
                    <div class="banner-left col-xl-6 col-sm-12 row align-items-end">
                        <div class="col-12">
                            <h1>Delicious cakes to order !</h1>
                        </div>
                        <div class="col-12 row justify-content-between">
                            <div class="img col-sm-4">
                                <img src="../../Image/Cake/banner-img-1.jpg" alt="banner-img-1">
                            </div>
                            <div class="row new-collection col-sm-7">
                                <h3>NEW COLLECTION</h3>
                                <div class="new-collection-desc">
                                    Welcome to our bakery specializing in fresh, high-quality pastries made from natural
                                    ingredients.
                                </div>
                                <div class="button">
                                    <form action="/catalog" method="POST">
                                        <button type="submit">SEE OUR CATALOG</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="banner-right col-xl-5 col-sm-12 row justify-content-end">
                        <div class="col-xl-10 col-12 img">
                            <img src="../../Image/Cake/banner-img-2.jpg" alt="banner-img-2">
                        </div>
                    </div>
                </div>
            </div>

            <!-- Ribbon -->
            <div class="ribbon">
                <div class="ribbon-content">
                    Welcome to our bakery Welcome to our bakery Welcome to our bakery Welcome to our bakery Welcome to our
                    bakery
                </div>
            </div>

            <!-- Best service -->
            <div class="best-service container">
                <div class="title">
                    <h2>Best service</h2>
                </div>
                <div class="best-service-items row justify-content-between">
                    <div class="col-sm-3 col-12 row align-items-between best-service-item">
                        <div class="col-sm-12 icon">
                            <i class="bi bi-star-fill"></i>
                        </div>
                        <div class="col-sm-12 title">
                            Fresh and Natural Ingredients
                        </div>
                        <div class="col-sm-12 content">
                            Quality fresh and natural ingredients for health.
                        </div>
                    </div>
                    <div class="col-sm-3 col-12 row align-items-between best-service-item">
                        <div class="col-sm-12 icon">
                            <i class="bi bi-star-fill"></i>
                        </div>
                        <div class="col-sm-12 title">
                            Wide Selection of Desserts
                        </div>
                        <div class="col-sm-12 content">
                            A wide selection of desserts, from classic to modern, which will decorate any celebration.
                        </div>
                    </div>
                    <div class="col-sm-3 col-12 row align-items-between best-service-item">
                        <div class="col-sm-12 icon">
                            <i class="bi bi-star-fill"></i>
                        </div>
                        <div class="col-sm-12 title">
                            Customizable Options
                        </div>
                        <div class="col-sm-12 content">
                            Quality fresh and natural ingredients for health.
                        </div>
                    </div>
                    <div class="col-sm-3 col-12 row align-items-between best-service-item">
                        <div class="col-sm-12 icon">
                            <i class="bi bi-star-fill"></i>
                        </div>
                        <div class="col-sm-12 title">
                            Services for Decorating Desserts
                        </div>
                        <div class="col-sm-12 content">
                            We offer services for decorating desserts and cakes to make them more attractive and original.
                        </div>
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <%@include file="../homeviews/Footer.jsp" %>

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
    </body>

</html>