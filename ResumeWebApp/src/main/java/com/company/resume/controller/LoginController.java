package com.company.resume.controller;

import java.io.IOException;

import com.company.entity.User;
import com.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.dao.inter.UserDaoInter;
import com.company.resume.util.ControllerUtil;

import at.favre.lib.crypto.bcrypt.BCrypt;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private final UserDaoInter userDao = Context.instanceUserDao();
    BCrypt.Verifyer myVerifyer = BCrypt.verifyer();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("username");
            String password = request.getParameter("password");
            User user = userDao.findByEmail(email);
            if (user == null) {
                throw new IllegalArgumentException("user doesn't exist!");
            }
            BCrypt.Result rs = myVerifyer.verify(password.toCharArray(), user.getPassword().toCharArray());
            if (!rs.verified) {
                throw new IllegalArgumentException("password is incorrect!");
            }
            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect("users");
        } catch (Exception ex) {
            ControllerUtil.errorPage(response, ex);
        }
    }
}