<%-- 
    Document   : search cake
    Created on : Feb 22, 2024, ‏‎2:11:33 AM
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
        <title>Search cake</title>

        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <!-- fonts & icons -->
        <link href='https://fonts.googleapis.com/css?family=Inter|Unna' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

        <!-- css -->
        <link rel="stylesheet" href="./assets/css/reset.css">

        <link rel="stylesheet" href="./assets/css/searchcake.css">

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
                        <a href="/home">Search cake</a>
                    </div>
                    <div class="bread-crumb-arrow">
                        >
                    </div>
                    <div class="bread-crumb-item active">
                        <c:url value="/searchcake" var="cakeFindURL">
                            <c:param name="cakeSearch" value="${title}"/>
                        </c:url>
                        <a href="${cakeFindURL}">${title}</a>
                    </div>
                </div>
            </div>

            <c:if test="${cakeList == null || cakeList.size() == 0}">
                <h1 class='row justify-content-center'>Sorry, we couldn't find your cake</h1>
            </c:if>

            <!-- Card list -->
            <div class="card-list container">
                <div class="card-items row justify-content-xxl-start justify-content-center">
                    <c:forEach var="cake" items="${cakeList}">
                        <div class="col-xl-3 col-10 card-item row justify-content-center">
                            <div class="col-12 img">
                                <img src="../../${cake.cakeImg}" alt="${cake.cakeName}">
                            </div>
                            <div class="col-11 content">
                                <div class="title">
                                    <h3>
                                        ${cake.cakeName}
                                    </h3>
                                </div>
                                <div class="des">
                                    ${cake.cakeDescription}
                                </div>
                                <div class="link">
                                    <c:url value="/cakedetail" var="cakeURL">
                                        <c:param name="cakeID" value="${cake.cakeID}"/>
                                    </c:url>
                                    <a href="${cakeURL}">
                                        More detail
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Load-more & indicators -->
            <div class="load-more-indicators container">
                <div class="row justify-content-center">
                    <c:if test="${indicators.size() / 6 - currentPage > 0}">
                        <div class="col-10 load-more-btn button">
                            <button>
                                Load more
                            </button>
                        </div>
                    </c:if>
                    <div class="col-xl-6 col-sm-10 col-12 indicators row justify-content-center
                         align-items-center">
                        <c:forEach var="indicator" items="${indicators}">
                            <c:choose>
                                <c:when test="${indicator == currentPage}">
                                    <div class="col-1 indicator active">
                                        <a href="#">${indicator}</a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="col-1 indicator">
                                        <a href="#">${indicator}</a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>


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