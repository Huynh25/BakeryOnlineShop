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


        <c:set var="toppings" value="${toppings}"/>
        <c:set var="cake" value="${cake}"/>
        <c:set var="cart" value="${cart}"/>
        <%
            Cake cake = (Cake) pageContext.getAttribute("cake");
            List<Topping> toppings = (ArrayList<Topping>) pageContext.getAttribute("toppings");
            Cart currentCart = (Cart) pageContext.getAttribute("cart");
            if (currentCart == null) {
                String text = "";
                Cookie[] cookies = new Cookie[4];
                cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("cart")) {
                            text += cookie.getValue();
                        }
                    }
                }

                if (!text.isEmpty()) {
                    text = URLDecoder.decode(text, "UTF-8");
                    CakeDAO cakeDAO = new CakeDAO();
                    List<Cake> cakeList = cakeDAO.readAll();

                    ToppingDAO toppingDAO = new ToppingDAO();
                    List<Topping> toppingList = toppingDAO.readAll();

                    currentCart = new Cart(text, cakeList, toppingList);
                } else {
                    currentCart = new Cart();
                }

            }
            int boughtQuantity = 0;
            List<Item> items = currentCart.getItems();
            for (Item item : items) {
                if (item.getCake().getCakeID() == cake.getCakeID()) {
                    boughtQuantity += item.getBuyQuantity();

                    int idx = 0;
                    for (Topping topping : toppings) {
                        idx = 0;
                        for (Topping toppingItem : item.getToppings()) {
                            if (topping.getToppingID() == toppingItem.getToppingID()) {
                                int updateQuantity = topping.getToppingQuantity() - item.getToppingsBuyQuantity().get(idx);
                                topping.setToppingQuantity(updateQuantity);
                            }
                        }
                    }
                }
            }
            cake.setCakeQuantity(cake.getCakeQuantity() - boughtQuantity);
        %>

        <div class="page">
            <!-- Header -->
            <%@include file="../homeviews/Header.jsp" %>

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
                                <form action="/addcaketocart" method="post" id="add-cake-to-cart">
                                    <input type="hidden" name="cakeID" value="${cake.cakeID}">
                                    <input type="hidden" name="cakeType" value="${cake.cakeType}">
                                </form>
                            </div>
                            <div class="des">
                                ${cake.cakeDescription}
                            </div>
                            <div class="rating row justify-content-start align-items-center">
                                <div class="col-8 rating-stars">
                                    <c:forEach var="star-fill" begin="1" end="${cakeRating}">
                                        <i class="bi bi-star-fill"></i>
                                    </c:forEach>
                                    <c:forEach var="star-fill" begin="${cakeRating + 1}" end="5">
                                        <i class="bi bi-star"></i>
                                    </c:forEach>
                                </div>
                                <div class="col-4 rating-value">
                                    ${cakeRating}
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
                                        <c:choose>
                                            <c:when test="${topping.toppingQuantity > 0}">
                                                <div class="topping-item">
                                                    <a data-value="${topping.toppingID}" data-price="${topping.toppingPrice}" 
                                                       data-quan="${topping.toppingQuantity}">${topping.toppingName}</a>
                                                </div>
                                            </c:when>
                                        </c:choose>

                                    </c:forEach>
                                </div>
                            </div>

                            <div class="row align-items-end">
                                <c:choose>
                                    <c:when test="${cake.cakeQuantity > 0}">
                                        <div class="col-12 price-quantity row justify-content-xxl-between justify-content-center">
                                            <div class="col-xxl-6 col-12 price">${cake.cakePrice}<span>đ</span>
                                            </div>
                                            <div class="col-xxl-6 col-12 quantity row justify-content-between align-items-center">
                                                <div class="col-3 quantity-btn">
                                                    <button value="decrease">-</button>
                                                </div>
                                                <div class="col-6 input">
                                                    <input form="add-cake-to-cart" type="text" name="buy-quantity" id="buy-quantity" 
                                                           value="${cake.cakeQuantity > 0 ? 1 : 0}">
                                                </div>
                                                <div class="col-3 quantity-btn">
                                                    <button value="increase">+</button>
                                                </div>
                                                <input type="hidden" name="cake-quantity" value="${cake.cakeQuantity}">
                                            </div>
                                        </div>
                                        <div class="col-12 add-to-cart-btn button">
                                            <button form="add-cake-to-cart" type="button">Add to cart</button>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <h2 class="sold-out">Sold out</h2>
                                    </c:otherwise>
                                </c:choose>
                            </div>
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
        <script src="../../assets/javascript/form-cake-detail.js"></script>
        <script src="../../assets/javascript/add-cake-to-cart.js"></script>
    </body>

</html>