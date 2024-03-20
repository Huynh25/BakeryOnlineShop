<%-- 
    Document   : Cake-management
    Created on : Mar 1, 2024, 1:43:02 PM
    Author     : ACER PC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../../assets/css/GlobalStyle.css">
        <link rel="stylesheet" href="../../assets/css/cake-detail-management.css"/>
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
        <div class="content container-fluid">
            <div class="top-option">
                <div class="menu-and-history row">
                    <div class="histoty col-sm-6 row">
                        <h4 class="Home-text"><a class="pre-page" href="/home">Home</a></h4>
                        <h4>&gt</h4>
                        <h4><a class="pre-page" href="/CakeManagement">Cake Management</a></h4>
                        <h4>&gt</h4>
                        <c:set var="isFirst" value="true"/>
                        <c:if test="${isFirst}">
                            <h4 id="cake-name-title" class="current-page">Add Cake</h4>
                            <c:set var="isFirst" value="false"/>
                        </c:if>
                        <h4 id="cake-name-title" class="current-page">${cake.cakeName}</h4>
                    </div>
                </div>
            </div>

            <div class=" content-cake">
                <div class="d-flex justify-content-center">
                    <h1 class="title-update">Add Cake</h1>
                </div>
                <div class="row">
                    <div id="image-container" class="img-container position-relative">
                        <input name="cake-img-upload" type="file" id="cake-image-upload" style="display: none;" onchange="handleImageUpload(event)" />
                        <i class="bi bi-upload" id="upload-icon" onclick="openImageUploader()"></i>
                        <img class="cake-img" id="cake-image" alt="Choose Cake Image" data-original-image="../../${cake.cakeImg}" />
                        <div class="text-danger" id="cake-img-error"></div>
                    </div>
                    <div class="col-sm-1"></div>

                    <form id="updateCakeForm" class="col-sm-6 row" action="CakeDetailManagement" method="POST">
                        <div class="col-sm-2" id="form-row-1">
                            <label for="cake-name" id="cake-name-label">Cake ID</label>
                            <input style="text-align:center;" id="cake-id" name="cake-id" type="text" value="${cakeID}" placeholder="Enter Cake ID" oninput="resetError(`cake-id-error`)" readonly  />
                            <div id="cake-id-error"></div>
                        </div>
                        <div class="col-sm-5" id="form-row-1">
                            <label for="cake-name" id="cake-name-label" >Cake Name</label>
                            <input id="cake-name" name="cake-name" type="text" value="${cake.cakeName}" placeholder="Enter Cake Name" oninput="resetError(`cake-name-error`)"  />
                            <div class="text-danger" id="cake-name-error"></div>
                        </div>
                        <div class="col-sm-5" id="form-row-1">
                            <label for="cake-type" id="cake-type-label" >Cake Type</label>
                            <input id="cake-type" name="cake-type" type="text" value="${cake.cakeType}" placeholder="Enter Cake Type" oninput="resetError(`cake-type-error`)"  />
                            <div class="text-danger"  id="cake-type-error"></div>
                        </div>
                        <div class=" detail-col-2 col-sm-12 row">
                            <div class="col-sm-2" id="form-row-2">
                                <label for="cake-quantity" id="cake-quantity-label">Quantity</label>
                                <div class="quantity-container">
                                    <input style="text-align:center;" id="cake-quantity" name="cake-quantity" type="number" min="0" value="${cake.cakeQuantity}" placeholder="Enter Quantity" oninput="resetError(`cake-quantity-error`)">
                                    <button type="button" class="plus-btn d-flex align-items-center" onclick="increaseQuantity()"><i class="bi bi-plus-square-fill"></i></button>
                                    <div class="text-danger"  id="cake-quantity-error"></div>
                                </div>
                            </div>
                            <div class="col-sm-4" id="form-row-2">
                                <label for="cake-price" id="cake-price-label" >Price</label>
                                <input id="cake-price" name="cake-price" type="number" value="${cake.cakePrice}" placeholder="Enter Price" oninput="resetError(`cake-price-error`)"  />
                                <button type="button" class="plus-btn d-flex align-items-center" onclick="increasePrice()"><i class="bi bi-plus-square-fill"></i></button>
                                <div class="text-danger"  id="cake-price-error"></div>
                            </div>
                            <p class="col-sm-2 add-topping-txt">Add Topping</p>
                            <div class="col-sm-4 d-flex" id="form-row-2">
                                <select class="form-select" id="add-topping-select" aria-label="Default select example">
                                    <c:forEach var="topping" items="${toppingList}">
                                        <option value="${topping.toppingName}">${topping.toppingName}</option>
                                    </c:forEach>
                                </select>
                                <button onclick="addTopping()" type="button" class="add-btn d-flex align-items-center justify-content-center"><i class="bi bi-plus-square-fill"></i></button>
                            </div>
                        </div>
                        <div class="col-sm-12 d-flex justify-content-center">
                            <p class="topping-go-with-txt">Topping go with</p>
                        </div>
                        <div id="topping-container" class=" topping-go-with-content col-sm-12 row d-flex justify-content-start">

                            <c:forEach var="thisTopping" items="${thisToppingList}">
                                <div class="topping-container col-sm-4">
                                    <p class="topping-in-go-with-txt">${thisTopping}</p>
                                    <button onclick="removeTopping('${thisTopping}')" type="button" class="cancel-btn close" aria-label="Close">
                                        <span onmousedown='return false;' onselectstart='return false;' aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-sm-12" id="form-row-2">
                            <label for="cake-quatity" id="cake-quantity-label" >Description</label>
                            <textarea id="cake-description" name="cake-description" type="text" placeholder="Enter Description" oninput="resetError(`cake-description-error`)" >${cake.cakeDescription}</textarea>
                            <div class="text-danger"  id="cake-description-error"></div>
                        </div>

                    </form>
                    <div class="container-fluid d-flex justify-content-center">
                        <button id="edit-btn" onclick="showPopup()" type="button">Add Cake</button>
                    </div>
                </div>
            </div>
            <div id="acceptOrderPopup" class="popup">
                <div class="popup-content">
                    <p>Are you sure create this cake?</p>
                    <button onclick="sendDataToAddCakeServlet()">Yes</button>
                    <button class="no-option" onclick="closePopup()">No</button>
                </div>
            </div>

            <div id="contactPopup" class="popup">
                <div class="popup-content">
                    <p class="successfully-notice">Create Successfully</p>
                    <button class="no-option" onclick="closeContactPopup()">Close</button>
                </div>
            </div>
            <script src="../../assets/javascript/cake-detail-management.js"></script>
            <script>

                        var thisToppingList = [
                <c:forEach var="thisTopping" items="${thisToppingList}" varStatus="loopStatus">
                        '<c:out value="${thisTopping}" />'<c:if test="${!loopStatus.last}">,</c:if>
                </c:forEach>
                        ];
            </script>
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