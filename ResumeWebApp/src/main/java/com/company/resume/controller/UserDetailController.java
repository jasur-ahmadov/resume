package com.company.resume.controller;

import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;
import com.company.entity.User;
import com.company.main.Context;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "UserDetailController", urlPatterns = {"/userdetail"})
public class UserDetailController extends HttpServlet {

    private final UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Id is not specified!");
            }
            Integer userId = Integer.parseInt(idStr);
            UserDaoInter userDao = Context.instanceUserDao();
            User user = userDao.getById(userId);
            if (user == null) {
                throw new IllegalArgumentException("There is no user with this id");
            }
            request.setAttribute("owner", true);
            request.setAttribute("user", user);
            request.getRequestDispatcher("userdetail.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");

        if (action.equals("update")) {
            String name = String.valueOf(request.getParameter("name"));
            String surname = String.valueOf(request.getParameter("surname"));
            String address = String.valueOf(request.getParameter("address"));
            String phone = String.valueOf(request.getParameter("phone"));
            String email = String.valueOf(request.getParameter("email"));
            User user = userDao.getById(id);
            user.setName(name);
            user.setSurname(surname);
            user.setAddress(address);
            user.setPhone(phone);
            user.setEmail(email);
            userDao.updateUser(user);
        } else if (action.equals("delete")) {
            userDao.removeUser(id);
        }
        response.sendRedirect("users");
    }
}