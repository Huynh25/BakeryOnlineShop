<%-- 
    Document   : catalog
    Created on : Feb 20, 2024, ‏‎‏‎8:31:59 AM
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
        <title>Catalog</title>

        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <!-- fonts & icons -->
        <link href='https://fonts.googleapis.com/css?family=Inter|Unna' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

        <!-- css -->
        <link rel="stylesheet" href="../../assets/css/reset.css">

        <link rel="stylesheet" href="../../assets/css/catalog.css">

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
                    <div class="bread-crumb-item active">
                        <a href="/catalog">Catalog</a>
                    </div>
                </div>
            </div>

            <!-- Page info -->
            <div class="page-info container">
                <div class="title">
                    <h2>
                        Our catalog
                    </h2>
                </div>
                <div class="des">
                    We have a variety of cakes to give you more choices
                </div>
            </div>

            <!-- Card list -->
            <div class="card-list container">
                <div class="card-items row justify-content-xxl-start justify-content-center">
                    <c:forEach var="cake" items="${cakeList}">
                        <div class="col-xl-3 col-10 card-item row justify-content-center">
                            <div class="col-10 img">
                                <img src="../../${cake.cakeImg}" alt="${cake.cakeType}">
                            </div>
                            <div class="col-11 content">
                                <div class="title">
                                    <h3>
                                        ${cake.cakeType}
                                    </h3>
                                </div>
                                <div class="des">
                                    ${cake.cakeDescription}
                                </div>
                                <div class="link">
                                    <c:url value="/category" var="cakeTypeURL">
                                        <c:param name="cakeType" value="${cake.cakeType}"/>
                                    </c:url>
                                    <a href="${cakeTypeURL}">
                                        More detail
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

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
            </div>

            <!-- Footer -->
            <%@include file="../homeviews/Footer.jsp" %>

        </div>
        <!-- Cart -->



    </body>

</html>