<%-- 
    Document   : Order-detail-management
    Created on : Feb 26, 2024, 2:46:59 PM
    Author     : Nguyen Truong An CE170984
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/order-detail-management.css"/>
        <link
            href="https://fonts.googleapis.com/css2?family=Inter&family=Unna:ital,wght@0,400;0,700;1,400;1,700&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../homeviews/Header.jsp" %>
        <div class="container-fluid content-top">
            <div class="top-option">
                <div class="menu-and-history row">
                    <div class="histoty col-sm-4 row">
                        <h4 class="Home-text"><a class="pre-page" href="/home">Home</a></h4>
                        <h4>&gt</h4>
                        <h4><a class="pre-page current-page" href="${preURL}">${prePage}</a></h4>
                        <h4>&gt</h4>
                        <h4 class="current-page">#${order.orderID}</a></h4>
                    </div>
                    <div class="col-sm-4"></div>
                    <div class="menu col-sm-4 row">
                        <c:set var="myConfirmedOrderBtnClass" value="menu-default" />
                        <c:set var="unconfirmedOrderBtnClass" value="menu-variant" />

                        <c:if test="${prePage eq 'My Confirmed Orders'}">
                            <c:set var="myConfirmedOrderBtnClass" value="menu-variant" />
                            <c:set var="unconfirmedOrderBtnClass" value="menu-default" />
                        </c:if>

                        <button class="menu-item my-confirmed-order-btn ${myConfirmedOrderBtnClass} col-sm-6">
                            My Confirmed Orders
                        </button>
                        <div class="col-sm-1"></div>
                        <button class="menu-item unconfirmed-order-btn ${unconfirmedOrderBtnClass} col-sm-5">
                            Unconfirmed Orders
                        </button>
                    </div>
                </div>
                <div class="search-and-filter d-flex align-items-center row">
                    <div class="searchbar col-sm-4">
                        <form class="search-order" action="UnconfirmedOrders" method="get">
                            <input
                                type="text"
                                name="searchTerm"
                                class="search-order-input"
                                placeholder="Search Order"
                                />
                            <button type="submit" class="search-order-btn">
                                <i class="bi bi-search"></i>
                            </button>
                        </form>
                    </div>
                    <div class="col-sm-2"></div>
                    <div class="col-sm-3"></div>
                    <div class="swap-page col-sm-3 d-flex align-items-center justify-content-end">
                        <p class="swap-page-item page-number">Page <span id="currentPage">1</span> of <span id="totalPages">1</span></p>
                        <button class="swap-page-item prev-page swap-page-button">&lt</button>
                        <button class="swap-page-item next-page swap-page-button">&gt</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid">
            <div class="order-information d-flex align-items-center">
                <div class="container-fluid row">
                    <div class="col-sm-1 order-detail-inf order-inf-id">Order #${order.getOrderID()}</div>
                    <div class="col-sm-2 order-detail-inf">Order Date: ${order.getOrderDate()}</div>
                    <c:if test="${not empty order.getReceivedDate()}">
                        <div class="col-sm-3 order-detail-inf order-inf-receivedDate">Received Date: ${order.getReceivedDate()}</div>
                    </c:if>
                    <c:if test="${empty order.getReceivedDate()}">
                        <div class="col-sm-3 order-detail-inf order-inf-receivedDate">Received Date: Undefined</div>
                    </c:if>
                    <div class="col-sm-2 order-detail-inf">Status: ${order.getStatus()}</div>
                    <c:if test="${not empty order.getReceivedDate()}">
                        <div class="col-sm-2 order-detail-inf">Person in charge: #${order.getStaffID()}</div>
                    </c:if>
                    <c:if test="${empty order.getReceivedDate()}">
                        <div class="col-sm-2 order-detail-inf order-inf-receivedDate">Accept By: Undefined</div>
                    </c:if>

                    <div class="col-sm-2 order-detail-inf">Total Price: ${order.getTotalPrice()}đ</div>

                </div>
            </div>
            <div class="cake-in-order d-flex align-items-center row" id="cakeList">
                <c:set var="CardIndex" value="0" />
                <c:forEach var="toppingInCake" items="${toppingInCakeList}">
                    <c:set var="CakeID" value="${toppingInCake.cakeInOrder.cake.cakeID}"/>

                    <div class="col-sm-4 cake-in-order-cover">
                        <div class="cake-in-order-detai">
                            <div class="d-flex justify-content-center">
                                <p class="cake-name">${toppingInCake.cakeInOrder.cake.cakeName}</p>
                            </div>
                            <div class="row">
                                <div class="col-sm-1"></div>
                                <img class="cake-img col-sm-5" src="../../${toppingInCake.cakeInOrder.cake.cakeImg}""/>
                                <div class="cake-detail col-sm-5">
                                    <div class="d-flex justify-content-center">
                                        <p class="cake-inf-text">Price: <fmt:formatNumber value="${toppingInCake.cakeInOrder.cake.cakePrice}" type="currency" currencySymbol="" maxFractionDigits="0" />đ</p>
                                    </div>
                                    <div class="d-flex justify-content-center">
                                        <p class="cake-inf-text">Quantity:  ${toppingInCake.cakeInOrder.cioQuantity}</p>
                                    </div>
                                    <p class="cake-inf-text">Rating:</p>
                                    <div class="d-flex justify-content-center">
                                        <div class="rating-stars">
                                            <c:set var="hasRating" value="false" />
                                            <c:forEach var="rating" items="${ratingList}">

                                                <c:if test="${toppingInCake.cakeInOrder.cake.cakeID eq rating.cake.cakeID}">
                                                    <c:forEach begin="1" end="${rating.ratingValue}" varStatus="ratedStar">
                                                        <i class="bi bi-star-fill"></i>
                                                    </c:forEach>
                                                    <c:forEach begin="${rating.ratingValue + 1}" end="5">
                                                        <i class="bi bi-star"></i>
                                                    </c:forEach>
                                                    <c:set var="hasRating" value="true" />
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${not hasRating}">
                                                <c:forEach begin="1" end="5">
                                                    <i class="bi bi-star"></i>
                                                </c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="topping-section-${CardIndex}-${CakeID}" class="topping-section-${CakeID}" style="display: none;">
                                <div class="d-flex justify-content-center">
                                    <p class="topping-in-cake-txt">Topping</p>
                                </div>


                                <div class="d-flex justify-content-start topping-name-div">
                                    <p class="topping-name topping-txt">${toppingInCake.topping.toppingName}</p>
                                </div>
                                <div class="d-flex justify-content-around container">
                                    <p class="topping-price topping-txt">Price: ${toppingInCake.topping.toppingPrice}</p>
                                    <p class="topping-quantity topping-txt">Quantity: ${toppingInCake.getTicQuantity()}</p>
                                </div>
                            </div>

                            <div class="d-flex justify-content-center" id="show-topping-btn">
                                <button onclick="showTopping(${CardIndex},${CakeID})">Show Topping <i id="icon-${CakeID}" class="bi bi-chevron-double-down down-icon"></i></button>
                            </div>
                            <div class="d-flex justify-content-center">
                                <p class="cake-total-price">Total Price: <fmt:formatNumber value="${toppingInCake.cakeInOrder.cake.cakePrice + toppingInCake.topping.toppingPrice * toppingInCake.getTicQuantity()}" type="currency" currencySymbol="" maxFractionDigits="0" />đ</p>
                            </div>

                        </div>
                    </div>  

                    <c:set var="CardIndex" value="${CardIndex+1}" />
                </c:forEach>
            </div>
            <div id="fixed-bottom">
                <div class="container btn-contain d-flex justify-content-around align-items-center">
                    <c:set var="status" value="${order.status}"/>
                    <c:choose>
                        <c:when test="${status eq 'Waiting'}">
                            <button onclick="showContactPopup()"> <i class="bi bi-telephone-outbound-fill"></i>Contact Customer</button>
                            <button onclick="showPopup()"> <i class="bi bi-truck"></i>Accept This Order</button>
                        </c:when>
                        <c:otherwise>
                            <button onclick="showContactPopup()"> <i class="bi bi-telephone-outbound-fill"></i>Contact Customer</button>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>

            <div id="acceptOrderPopup" class="popup">
                <div class="popup-content">
                    <p>Are you sure you want to accept this order?</p>
                    <button onclick="confirmAccept(${order.orderID})">Yes</button>
                    <button class="no-option" onclick="closePopup()">No</button>
                </div>
            </div>
                    
            <div id="contactPopup" class="popup">
                <div class="popup-content">
                    <p>Customer Phone Number: ${user.phoneNumber}</p>
                    <p>Customer Email: ${user.email}</p>
                    <p>Customer Address: ${user.address}</p>
                    <button class="no-option" onclick="closeContactPopup()">Close</button>
                </div>
            </div>

            <script src="../../assets/javascript/order-detail-management.js"></script>
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