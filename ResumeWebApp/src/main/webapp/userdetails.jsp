<%@page import="com.company.entity.User"%>
<%@page import="com.company.main.Context"%>
<%@page import="com.company.dao.inter.UserDaoInter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page Default</title>
    </head>
    <body>
        <%
            UserDaoInter userDao = Context.instanceUserDao();
            User user = userDao.getById(1);
        %>

        <form action="UserController" method="POST">
            <div>
                <input type="hidden" name="id" value="<%=user.getId()%>" />

                <label for="name">name: &nbsp;&nbsp;&nbsp; </label>
                <input type="text" name="name" value="<%=user.getName()%>"/> <br><br>

                <label for="surname">surname: </label>
                <input type="text" name="surname" value="<%=user.getSurname()%>"/> <br><br>

                <label for="address">address: </label>
                <input type="text" name="address" value="<%=user.getAddress()%>"/> <br><br>

                <label for="phone">phone: </label>
                <input type="text" name="phone" value="<%=user.getPhone()%>"/> <br><br>

                <label for="email">email: </label>
                <input type="email" name="email" value="<%=user.getEmail()%>"/> <br><br>

                <label for="birthdate">birthdate: </label>
                <input type="text" name="birthdate" value="<%=user.getBirthDate()%>"/>

                <input type="submit" name="save" value="Save"/>
            </div>
        </form>
    </body>
</html>