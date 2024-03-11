<%-- 
    Document   : checkout
    Created on : Feb 23, 2024, ‏‎11:34:19 AM
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
        <title>Checkout</title>

        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <!-- fonts & icons -->
        <link href='https://fonts.googleapis.com/css?family=Inter|Unna' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

        <!-- css -->
        <link rel="stylesheet" href="../../assets/css/reset.css">

        <link rel="stylesheet" href="../../assets/css/checkout.css">

        <link rel="stylesheet" href="../../assets/css/footer.css">

    </head>

    <body>
        <div class="page">
            <!-- Header -->
            <%@include file="../homeviews/Header.jsp" %>

            <!-- Page info -->
            <div class="page-info container">
                <div class="title">
                    Checkout
                </div>
            </div>

            <<form action="make-purchase" method="post" id="make-purchase-form">
            </form>
            <!-- Checkout body -->
            <div class="checkout-body container">
                <div class="row justify-content-between align-items-start">
                    <div class="col-sm-7 checkout-content">
                        <div class="checkout-part contact-info row justify-content-between">
                            <div class="col-sm-12 title">
                                <h3>Contact information</h3>
                            </div>
                            <div class="col-sm-12 input">
                                <input form="make-purchase-form" type="text" name="fullname" id="fullname" placeholder="Enter your full name" value="${customer.fullname}">
                            </div>
                            <div class="col-sm-8 input">
                                <input form="make-purchase-form" type="text" name="email" id="email" placeholder="Your email" value="${customer.email}">
                            </div>
                            <div class="col-sm-4 input">
                                <input form="make-purchase-form" type="text" name="phone-number" id="phone-number" placeholder="Phone number" value="${customer.phoneNumber}">
                            </div>
                        </div>
                        <div class="checkout-part shipping-info row justify-content-between">
                            <div class="col-sm-12 title">
                                <h3>Shipping information</h3>
                            </div>
                            <div class="col-sm-12 input">
                                <textarea form="make-purchase-form" name="address" id="address" cols="30" rows="10"
                                          placeholder="Enter your address" value="${customer.address}"></textarea>
                            </div>
                        </div>
                            <div class="checkout-part description-info row justify-content-between">
                            <div class="col-sm-12 title">
                                <h3>Shipping information</h3>
                            </div>
                            <div class="col-sm-12 input">
                                <textarea form="make-purchase-form" name="description" id="description" cols="30" rows="10"
                                          placeholder="Enter your description about this order..." value=""></textarea>
                            </div>
                        </div>
                        <div class="checkout-part payment-info row justify-content-between">
                            <div class="col-sm-12 title">
                                <h3>Payment</h3>
                            </div>
                            <div class="col-sm-12 input">
                                <input form="make-purchase-form" type="radio" name="payment" id="cash" value="cash" checked>
                                <label for="cash">Cash</label>
                            </div>
                            <div class="col-sm-12 input">
                                <input form="make-purchase-form" type="radio" name="payment" id="e-wallet" value="e-wallet">
                                <label for="e-wallet">E-wallet</label>
                            </div>
                        </div>
                        <div class="checkout-part purchase-btn button">
                            <button form="make-purchase-form" type="submit">
                                PURCHASE
                            </button>
                        </div>
                    </div>
                    <div class="col-sm-5 order-content">
                        <div class="title">
                            Your order
                        </div>
                        <div class="order-list">
                            <c:forEach var="orderItem" items="${cart.items}">
                                <div class="order-item">
                                    <div class="cake-name">
                                        ${orderItem.cake.cakeName}
                                    </div>
                                    <c:set var="totalEachCake" value="${orderItem.cake.cakePrice}"></c:set>
                                        <div class="cake-toppings">
                                            Toppings: <c:forEach var="topping" items="${orderItem.toppings}" varStatus="status">
                                            ${topping.toppingName}${status.last ? '' : ', '}
                                            <c:set var="totalEachCake" value="${totalEachCake + topping.toppingPrice}"></c:set>
                                        </c:forEach>
                                    </div>
                                    <div class="price-quantity row justify-content-between">
                                        <div class="col-sm-8 price">
                                            ${totalEachCake} <span class="underline">đ</span>
                                        </div>
                                        <div class="col-sm-4 quantity">
                                            Quantity: ${orderItem.buyQuantity}
                                        </div>
                                    </div>
                                    <div class="total">
                                        <span class="bold">Total: </span>${orderItem.totalPrice} <span class="underline">đ</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="order-total">
                            <div class="total-product">
                                ${cart.totalQuantity} products
                            </div>
                            <div class="total-price">
                                Total of order: ${cart.totalPrice} <span class="underline">đ</span>
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

        <script src="../../assets/javascript/checkout.js""></script>
    </body>

</html>