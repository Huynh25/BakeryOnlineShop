<%-- 
    Document   : Header.jsp
    Created on : Mar 6, 2024, 9:30:43 AM
    Author     : Gia Huy <https://github.com/ThomasTran17>
--%>

<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
    User user = (User) session.getAttribute("user");
%>
<c:set var="role" value="${user.role}"></c:set>


<c:choose>
    <c:when test="${'customer'.equalsIgnoreCase(role)}">
        <%@include file="../homeviews/customer-header.jsp" %>
    </c:when>
    <c:when test="${'manager'.equalsIgnoreCase(role)}">
        <%@include file="../homeviews/manager-header.jsp" %>
    </c:when>
    <c:when test="${'staff'.equalsIgnoreCase(role)}">
        <%@include file="../homeviews/staff-header.jsp" %>
    </c:when>
    <c:otherwise>
        <%@include file="../homeviews/customer-header.jsp" %>
    </c:otherwise>
</c:choose>