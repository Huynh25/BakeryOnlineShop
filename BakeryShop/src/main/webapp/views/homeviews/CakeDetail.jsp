<%-- 
    Document   : cake detail
    Created on : Feb 22, 2024, ‏‎3:34:27 AM
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
        <title>Cake detail</title>

        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <!-- fonts & icons -->
        <link href='https://fonts.googleapis.com/css?family=Inter|Unna' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

        <!-- css -->
        <link rel="stylesheet" href="../../assets/css/reset.css">

        <link rel="stylesheet" href="../../assets/css/cakedetail.css">

        <link rel="stylesheet" href="../../assets/css/footer.css">

    </head>

    <body>
        <div class="page">
            <!-- Header -->
            <%@include file="../homeviews/customer-header.jsp" %>

            <!-- Bread-crumb -->
            <div class="bread-crumb container">
                <div class="bread-crumb-items">
                    <div class="bread-crumb-item">
                        <a href="/home">Main</a>
                    </div>
                    <div class="bread-crumb-arrow">
                        >
                    </div>
                    <div class="bread-crumb-item">
                        <a href="/catalog">Catalog</a>
                    </div>
                    <div class="bread-crumb-arrow">
                        >
                    </div>
                    <div class="bread-crumb-item">
                        <c:url value="/category" var="cakeTypeURL">
                            <c:param name="cakeType" value="${cake.cakeType}"/>
                        </c:url>
                        <a href="${cakeTypeURL}">${cake.cakeType}</a>
                    </div>
                    <div class="bread-crumb-arrow">
                        >
                    </div>
                    <div class="bread-crumb-item active">
                        <c:url value="/cakedetail" var="cakeDetailURL">
                            <c:param name="cakeID" value="${cake.cakeID}"/>
                        </c:url>
                        <a href="${cakeDetailURL}">${cake.cakeName}</a>
                    </div>
                </div>
            </div>

            <!-- Cake detail content -->
            <div class="cake-detail-content container">
                <div class="row justify-content-center">
                    <div class="row justify-content-xl-between justify-content-center">
                        <div class="col-xl-3 col-12 cake-info row align-items-between">
                            <div class="title">
                                <h2>${cake.cakeName}</h2>
                            </div>
                            <div class="des">
                                ${cake.cakeDescription}
                            </div>
                            <div class="rating row justify-content-start align-items-center">
                                <div class="col-8 rating-stars">
                                    <c:forEach var="star-fill" begin="1" end="5">
                                        <c:choose>
                                            <c:when test="${star-fill <= cakeRating}">
                                                <i class="bi bi-star-fill"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="bi bi-star"></i>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </div>
                                <div class="col-4 rating-value">
                                    ${String.format("%.2f", Float.parseFloat(cakeRating))}
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-12 img">
                            <img src="../../${cake.cakeImg}" alt="${cake.cakeName}">
                        </div>
                        <div class="col-4 cake-attribute row justify-content-xl-between justify-content-center">
                            <div class="col-12 toppings">
                                <div class="title">
                                    Choose toppings
                                </div>
                                <div class="topping-list">
                                    <c:forEach var="topping" items="${toppings}">
                                        <div class="topping-item">
                                            <a>${topping.toppingName}</a>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="row align-items-end">
                                <div class="col-12 price-quantity row justify-content-xxl-between justify-content-center">
                                    <div class="col-xxl-6 col-12 price">
                                        ${cake.cakePrice} <span>đ</span>
                                    </div>
                                    <div class="col-xxl-6 col-12 quantity row justify-content-between align-items-center">
                                        <div class="col-3 quantity-btn">
                                            <button>-</button>
                                        </div>
                                        <div class="col-6 input">
                                            <input type="text" name="buy-quantity" id="buy-quantity" value="0">
                                        </div>
                                        <div class="col-3 quantity-btn">
                                            <button>+</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 add-to-cart-btn button">
                                    <button>Add to cart</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <%@include file="../homeviews/Footer.jsp" %>

        </div>
        <div class="popup">

        </div>

    </body>

</html>