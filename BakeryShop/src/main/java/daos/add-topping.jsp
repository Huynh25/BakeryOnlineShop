<%-- 
    Document   : Topping-management
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
        <link rel="stylesheet" href="../../assets/css/topping-detail-management.css"/>
        <link
            href="https://fonts.googleapis.com/css2?family=Inter&family=Unna:ital,wght@0,400;0,700;1,400;1,700&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../homeviews/staff-header.jsp" %>
        <div class="content container-fluid">
            <div class="top-option">
                <div class="menu-and-history row">
                    <div class="histoty col-sm-6 row">
                        <h4 class="Home-text">Home</h4>
                        <h4>&gt</h4>
                        <h4><a class="pre-page" href="/ToppingManagement">Topping Management</a></h4>
                        <h4>&gt</h4>
                        <h4 id="topping-name-title" class="current-page">${topping.toppingName}</h4>
                    </div>
                </div>
            </div>

            <div class=" content-topping">
                <div class="d-flex justify-content-center">
                    <h1 class="title-update">Add Topping</h1>
                </div>
                <div class="row">
                    <div id="image-container" class="img-container position-relative">
                        <input name="topping-img-upload" type="file" id="topping-image-upload" style="display: none;" onchange="handleImageUpload(event)" />
                        <i class="bi bi-upload" id="upload-icon" onclick="openImageUploader()"></i>
                        <img class="topping-img" id="topping-image" src="../../${topping.toppingImg}" alt="Choose Topping Image" data-original-image="../../${topping.toppingImg}" />
                    </div>
                    <div class="col-sm-1"></div>

                    <form id="updateToppingForm" class="col-sm-6 row" action="ToppingDetailManagement" method="POST">
                        <div class="col-sm-2" id="form-row-1">
                            <label for="topping-name" id="topping-name-label">Topping ID</label>
                            <input style="text-align:center;" id="topping-id" name="topping-id" type="text" value="${toppingID}" placeholder="Enter Topping ID" oninput="resetError(`topping-id-error`)" readonly  />
                            <div id="topping-id-error"></div>
                        </div>
                        <div class="col-sm-5" id="form-row-1">
                            <label for="topping-name" id="topping-name-label" >Topping Name</label>
                            <input id="topping-name" name="topping-name" type="text" value="${topping.toppingName}" placeholder="Enter Topping Name" oninput="resetError(`topping-name-error`)"  />
                            <div class="text-danger" id="topping-name-error"></div>
                        </div>
                        
                        <div class=" detail-col-2 col-sm-12 row">
                            <div class="col-sm-2" id="form-row-2">
                                <label for="topping-quantity" id="topping-quantity-label">Quantity</label>
                                <div class="quantity-container">
                                    <input style="text-align:center;" id="topping-quantity" name="topping-quantity" type="number" min="0" value="${topping.toppingQuantity}" placeholder="Enter Quantity" oninput="resetError(`topping-quantity-error`)">
                                    <button type="button" class="plus-btn d-flex align-items-center" onclick="increaseQuantity()"><i class="bi bi-plus-square-fill"></i></button>
                                    <div class="text-danger"  id="topping-quantity-error"></div>
                                </div>
                            </div>
                            <div class="col-sm-4" id="form-row-2">
                                <label for="topping-price" id="topping-price-label" >Price</label>
                                <input id="topping-price" name="topping-price" type="number" value="${topping.toppingPrice}" placeholder="Enter Price" oninput="resetError(`topping-price-error`)"  />
                                <button type="button" class="plus-btn d-flex align-items-center" onclick="increasePrice()"><i class="bi bi-plus-square-fill"></i></button>
                                <div class="text-danger"  id="topping-price-error"></div>
                            </div>
                            
                        <div class="col-sm-12" id="form-row-3">
                            <label for="topping-description" id="topping-description-label" >Description</label>
                            <textarea id="topping-description" name="topping-description" type="text" placeholder="Enter Description" oninput="resetError(`topping-description-error`)" >${topping.toppingDescription}</textarea>
                            <div class="text-danger"  id="topping-description-error"></div>
                        </div>

                    </form>
                    <div class="container-fluid d-flex justify-content-center">
                        <button id="edit-btn" onclick="showPopup()" type="button">Add</button>
                    </div>
                </div>
            </div>
                            <div id="acceptOrderPopup" class="popup">
                <div class="popup-content">
                    <p>Are you sure add this topping?</p>
                    <button onclick="sendDataToAddToppingServlet()">Yes</button>
                    <button class="no-option" onclick="closePopup()">No</button>
                </div>
            </div>
                    
            <div id="contactPopup" class="popup">
                <div class="popup-content">
                    <p class="successfully-notice">Add Successfully</p>
                    <button class="no-option" onclick="closeContactPopup()">Close</button>
                </div>
            </div>
            <script src="../../assets/javascript/topping-detail-management.js"></script>
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