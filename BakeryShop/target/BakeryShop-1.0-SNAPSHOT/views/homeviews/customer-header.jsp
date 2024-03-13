<%-- 
    Document   : customer-header
    Created on : Feb 20, 2024, 9:17:13 AM
    Author     : Tran Nguyen Nam Thuan CE171497
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="header" class="row">
    <div class="row col-sm-5">
        <div class="col-sm-5" id="catalog-container">
            <a id="catalog-link" href="/catalog">Catalog</a>
        </div>
        <div class="col-sm-7">
            <form action="/searchcake" method="get" id="search-cake">
                <input type="text" name="cakeSearch" id="search-cake-input" placeholder="Search Cake">
                <button type="submit" id="search-cake-btn"><i class="bi bi-search"></i></button>
            </form>
        </div>
    </div>
    <div class="col-sm-4">
        <a href="/home" id="backhome-link">
            <h3 id="bakery-name">ELEGANTO</h3>
        </a>
    </div>
    <div class="col-sm-3">
        <ul id="navbar-icon">
            <li><a class="openCart" href="#"><i class="bi bi-basket3"></i></a></li>
            <li><a href="order-history"><i class="bi bi-clock-history"></i></a></li>

            <c:choose>
                <c:when test="${'customer'.equalsIgnoreCase(role)}">
                    <li class="nav-item dropdown" id="dropdown-container">
                        <a class="nav-link dropdown-toggle" id="dropdown-management" href="#" role="button"
                           data-toggle="dropdown" aria-expanded="false">
                            <i class="bi bi-person-circle h4">
                            </i>
                        </a>
                        <div class="dropdown-menu" id="dropdown-management-list">
                            <a class="dropdown-item" href="editProfile">Edit profile</a>
                            <a class="dropdown-item" href="changePassword">Change password</a>
                            <a class="dropdown-item" href="logout">Log out</a>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="/login">
                            <i class="bi bi-person-circle">
                            </i>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
