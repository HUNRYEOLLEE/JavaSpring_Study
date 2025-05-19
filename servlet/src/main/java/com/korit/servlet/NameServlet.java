package com.korit.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/name")
public class NameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        System.out.println(req.getParameter("name"));
        System.out.println(req.getParameter("age"));
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        int intAge= 0;
        try {
            intAge = Integer.parseInt(age);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(intAge + 10);

        resp.getWriter().println("이훈렬");
    }
}
