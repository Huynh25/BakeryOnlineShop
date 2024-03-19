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
    String role = (user != null) ? user.getRole() : "guest";
    request.setAttribute("role", role);
%>


<c:if test="${role eq 'customer'}">
    <%@include file="../homeviews/customer-header.jsp" %>
</c:if>
<c:if test="${role eq 'manager'}">
    <%@include file="../homeviews/manager-header.jsp" %>
</c:if>
<c:if test="${role eq 'staff'}">
    <%@include file="../homeviews/staff-header.jsp" %>
</c:if>
<c:if test="${role eq 'guest'}">
    <%@include file="../homeviews/customer-header.jsp" %>
</c:if>
