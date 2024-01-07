package com.company.resume.util;

import javax.servlet.http.HttpServletResponse;

public class ControllerUtil {

    public static void errorPage(HttpServletResponse response, Exception e) {
        try {
            e.printStackTrace();
            response.sendRedirect("error?msg=" + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}