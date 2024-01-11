<%@ page import="com.company.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedInUser = (User) session.getAttribute("loggedInUser");
%>

<h5>
    <%="Welcome, " + loggedInUser.getName() + "!!!"%>
</h5>