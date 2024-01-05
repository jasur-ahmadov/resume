<%@page import="com.company.entity.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page Default</title>
    <script src="https://kit.fontawesome.com/ef7bf77584.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
%>
<div class="container" style="margin: 0 auto 10% 1%; width: 40%">
    <form action="userdetail" method="POST">
        <div class="form-group">
            <input type="hidden" name="id" value="<%=user.getId()%>"/>
            <label for="name" class="col-sm-2 col-form-label">name: &nbsp;&nbsp;&nbsp; </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="name"
                       value="<%=user.getName()%>"/>
            </div>
            <label for="surname" class="col-sm-2 col-form-label">surname: &nbsp;&nbsp;&nbsp; </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="surname" name="surname"
                       placeholder="surname" value="<%=user.getSurname()%>"/>
            </div>
            <label for="address" class="col-sm-2 col-form-label">address: &nbsp;&nbsp;&nbsp; </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="address" name="address"
                       placeholder="address" value="<%=user.getAddress()%>"/>
            </div>
            <label for="phone" class="col-sm-2 col-form-label">phone: &nbsp;&nbsp;&nbsp; </label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="phone" name="phone"
                       placeholder="phone" value="<%=user.getPhone()%>"/>
            </div>
            <label for="email" class="col-sm-2 col-form-label">email: &nbsp;&nbsp;&nbsp; </label>
            <div class="col-sm-10">
                <input type="email" class="form-control" name="email" id="email" value="<%=user.getEmail()%>">
            </div>
            <br>
            <div class="col-sm-10">
                <input type="submit" class="btn btn-primary" name="save" value="Save"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>