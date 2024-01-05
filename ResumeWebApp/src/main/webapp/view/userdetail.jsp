<%@page import="com.company.entity.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page Default</title>
</head>
<body>
<%
    if (request.getAttribute("owner") == null) {
        response.sendRedirect("error.jsp?msg=not found");
        return;
    }
    User user = (User) request.getAttribute("user");
%>
<form action="UserController" method="POST">
    <div>
        <input type="hidden" name="id" value="<%=user.getId()%>"/>

        <label for="name">name: &nbsp;&nbsp;&nbsp; </label>
        <input type="text" id="name" name="name" value="<%=user.getName()%>"/> <br><br>

        <label for="surname">surname: </label>
        <input type="text" id="surname" name="surname" value="<%=user.getSurname()%>"/> <br><br>

        <label for="address">address: </label>
        <input type="text" id="address" name="address" value="<%=user.getAddress()%>"/> <br><br>

        <label for="phone">phone: </label>
        <input type="text" id="phone" name="phone" value="<%=user.getPhone()%>"/> <br><br>

        <label for="email">email: </label>
        <input type="email" id="email" name="email" value="<%=user.getEmail()%>"/> <br><br>

        <label for="birthdate">birthdate: </label>
        <input type="text" id="birthdate" name="birthdate" value="<%=user.getBirthDate()%>"/>

        <input type="submit" name="save" value="Save"/>
    </div>
</form>
</body>
</html>