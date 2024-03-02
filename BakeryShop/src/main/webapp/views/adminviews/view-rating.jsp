<%-- 
    Document   : view-rating
    Created on : Feb 20, 2024, 9:08:48 AM
    Author     : Tran Nguyen Nam Thuan CE171497
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Math" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Rating</title>
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/view-rating.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Inter&family=Unna:ital,wght@0,400;0,700;1,400;1,700&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    </head>

    <body>
        <%@include file="../homeviews/staff-header.jsp" %>
        <div id="content" class="row">
            <aside id="aside-left" class="col-sm-4">
                <div id="url">
                    <a id="home-link" href="#">Home</a>
                    <i id="next-btn" class="bi bi-chevron-compact-right"></i>
                    <span id="title">Rating Management</span>
                </div>
                <c:forEach var="cake" items="${cakeList}" >     
                    <div class="rating-cake-card rating-cake-card--short" id="${cake.cakeID}">
                        <div class="cake-card-title" onclick="toggleDetail('${cake.cakeID}')">
                            <h6 class="cake-card-name">${cake.cakeName}</h6>
                            <c:set var="ratingValueByCake" value="0" />
                           <c:set var="numberRating" value="0" />
                            <c:forEach var="rating" items="${ratingList}">
                                <c:if test="${rating.cake.cakeID==cake.cakeID}">
                                    <c:set var="ratingValueByCake" value="${ratingValueByCake +rating.ratingValue}" />
                                    <c:set var="numberRating" value="${numberRating + 1}" />
                                </c:if>
                            </c:forEach>
                            <c:set var="roundedAverage" value="${Math.round(ratingValueByCake / (numberRating))}" />
                            <div class="cake-card-icon">
                                <div class="cake-card-stars-icon">
                                    <c:forEach var="i" begin="1" end="${roundedAverage}">
                                        <i class="bi bi-star-fill"></i>
                                    </c:forEach>
                                    <c:forEach var="i" begin="${roundedAverage + 1}" end="5">
                                        <i class="bi bi-star"></i>
                                    </c:forEach>
                                </div>
                                <i id class="bi bi-chevron-double-down down-icon"></i>   
                            </div>
                        </div>
                        <div class="cake-card-detail-container">
                            <div class="cake-card-detail">
                                <img class="cake-card-image" src="../../${cake.cakeImg}" alt="${cake.cakeName}">
                                <div class="cake-card-info">
                                    <div class="cake-card-type-and-id">
                                        <div class="cake-card-type">${cake.cakeType}</div>
                                        <div class="cake-card-id">#${cake.cakeID}</div>
                                    </div>
                                    <div class="cake-card-price">
                                        ${cake.cakePrice} đ
                                    </div>
                                    <div class="cake-card-number-rating">
                                        ${numberRating} Ratings
                                    </div>
                                    <div class="cake-card-rating-value">
                                        <div class="cake-card-stars-icon">
                                            <c:forEach var="i" begin="1" end="${roundedAverage}">
                                                <i class="bi bi-star-fill"></i>
                                            </c:forEach>
                                            <c:forEach var="i" begin="${roundedAverage + 1}" end="5">
                                                <i class="bi bi-star"></i>
                                            </c:forEach>
                                        </div>
                                        <span class="cake-card-numberic-rating-value">${roundedAverage} Star</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="cake-card-more-detail-btn-container">
                            <div class="cake-card-more-detail-btn">
                               <button class="${cake.cakeID == 1 ? 'disable-icon' : ''}" ${cake.cakeID == 0 ? 'disabled' : ''} id="moreDetailButton-${cake.cakeID}" onclick="ShowRatingDetail(${cake.cakeID})">More Detail</button>
                            </div>
                        </div>        
                    </div> 
                </c:forEach>
            </aside>
            <aside id="aside-right" class="col-sm-8">
                <div id="rating-detail-1">
                    <h3 id="rating-title">Rating of ${cakeList.get(0).cakeName}</h3> 
                    <a href="../../views/adminviews/statistics-page.jsp">Statistics</a>
                    <div class="row" id="rating-detail-container">
                    <c:forEach var="rating" items="${ratingList}">
                        <c:if test="${rating.cake.cakeID==1}">
                            <div class="rating-comment-container col-sm-6 row">
                                <div class="col-sm-3">
                                    <img class="rating-comment-image " src="../../${rating.customer.userAvatar}" alt="${rating.customer.fullname}">
                                </div>                               
                                <div class="rating-comment-side-right col-sm-9">
                                    <div class="rating-comment-title">
                                        <div class="rating-comment-name">${rating.customer.fullname}</div>
                                        <div class="rating-comment-date">${rating.ratingDate}</div>
                                    </div>
                                      <div class="cake-card-stars-icon rating-comment-value">
                                            <c:forEach var="i" begin="1" end="${rating.ratingValue}">
                                                <i class="bi bi-star-fill"></i>
                                            </c:forEach>
                                            <c:forEach var="i" begin="${rating.ratingValue + 1}" end="5">
                                                <i class="bi bi-star"></i>
                                            </c:forEach>
                                        </div>
                                    <div class="rating-comment">
                                        ${rating.comment}
                                    </div>
                                </div>
                            </div>
                        </c:if>               
                    </c:forEach>
                </div> 
                       <c:if test="${numberRating > 8}">
                     <div class="show-more-button" onclick="ShowMoreRating()">
                                <div>Show more</div>
                                <i class="bi bi-chevron-double-down down-icon"></i>
                            </div>
                </c:if>
                </div>
              
            </aside>
        </div>
        <script  src="../../assets/javascript/view-rating.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
        crossorigin="anonymous"></script>
    </body>

</html>