package com.company.resume.main;

import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import com.company.main.Context;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "MyFavoriteServletPage", urlPatterns = {"/MyFavoriteServletPage"})
public class MyFavoriteServletPage extends HttpServlet {

    private final SkillDaoInter skillDao = Context.instanceSkillDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyFavoriteServletPage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("I got GET request <br><br>");
            out.println(skillDao.getAll() + "<br>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String requestStr = getAllDataFromRequest(request);
            String name = String.valueOf(request.getParameter("name"));
            Skill s = new Skill(0, name);
            boolean b = skillDao.insertSkill(s);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet MyFavoriteServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("I got POST request <br><br>");
                out.println("skill inserted " + s + "<br>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        String name = String.valueOf(request.getParameter("name"));
        Skill skill = skillDao.getById(id);
        skill.setName(name);
        skillDao.updateSkill(skill);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public static String getAllDataFromRequest(HttpServletRequest request) throws Exception {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        return stringBuilder.toString();
    }
}