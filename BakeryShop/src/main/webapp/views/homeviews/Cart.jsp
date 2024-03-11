<%-- 
    Document   : cart
    Created on : Feb 23, 2024, ‏‎2:29:23 PM
    Author     : Tran Gia Huy CE170732
--%>
<%@page import="daos.ToppingDAO"%>
<%@page import="daos.CakeDAO"%>
<%@page import="java.net.URLDecoder"%>

<%@page import="models.Topping"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="models.Cake"%>
<%@page import="models.Item"%>
<%@page import="models.Cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.lang.Math" %>


<link rel="stylesheet" href="../../assets/css/cart.css"/>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
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

            cart = new Cart(text, cakeList, toppingList);
        } else {
            cart = new Cart();
        }

        session.setAttribute("cart", cart);
    }
%>

<div class="popup">
    <div class="cart-popup">
        <div class="title">
            Shopping Cart
        </div>
        <div class="cart-info row justify-content-between">
            <div class="col-sm-6 product-in-cart">
                <div class="title">
                    Order
                </div>
                <div class="quantity">${cart.totalQuantity} products
                </div>
            </div>
            <div class="col-sm-6 price-in-cart">
                <div class="title">
                    Total
                </div>
                <div class="price">${cart.totalPrice}<span class="underline">đ</span>
                </div>
            </div>
            <div class="col-sm-12 button">
                <form action="checkout" method="post" id="go-to-checkout"></form>
                <button type="submit" form="go-to-checkout">BUY</button>
            </div>
        </div>
        <form action="/editcart" method="post" id="edit-cart">
        </form>
        <div class="cart-items">
            <c:forEach var="item" items="${cart.items}" varStatus="status">
                <div class="cart-item">
                    <input type="hidden" class="cart-item-id" value="${item.cake.cakeID}">
                    <div class="img">
                        <img src="../../${item.cake.cakeImg}" alt="${item.cake.cakeName}">
                    </div>
                    <div class="cake-name">
                        ${item.cake.cakeName}
                    </div>
                    <div class="cake-price">
                        ${item.totalPrice} <span class="underline">đ</span>
                    </div>
                    <div class="cake-toppings">
                        Toppings: <c:forEach var="topping" items="${item.toppings}" varStatus="status">
                            ${topping.toppingName}${status.last ? '' : ', '}
                            <input type="hidden" class="cart-item-topping" value="${topping.toppingID}">
                        </c:forEach>
                    </div>
                    <div class="cake-edit row justify-content-between">
                        <div class="col-sm-6 cake-quantity row justify-content-between align-items-center">
                            <div class="col-sm-3 quantity-btn">
                                <button type="button" form="edit-cart" class="dec-btn">-</button>
                            </div>
                            <div class="col-sm-6 input">
                                <input form="edit-cart" type="text" name="buy-quantity" class="buy-quan-text" value="${item.buyQuantity}">
                            </div>
                            <div class="col-sm-3 quantity-btn">
                                <button type="button" form="edit-cart" class="inc-btn">+</button>
                            </div>
                        </div>
                        <div class="col-sm-6 cake-remove">
                            <button type="button" form="edit-cart" class="rem-btn">Remove</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script src="../../assets/javascript/cart.js""></script>