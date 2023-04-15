package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//have url /login.
//check session attribute "user".
//for GET request if the session attribute "user" does not exist, redirect to the /login.jsp page, else redirect to the /user/hello.jsp.
//for POST request check the request parameters. "login" should exist in Users and the request parameter "password" shouldn't be empty.
// If parameters are correct set session attribute "user" and redirect to /user/hello.jsp, else forward to the /login.jsp.

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
            req.getRequestDispatcher("user/hello.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");

        if(userPassword != null && userPassword.length() > 0 && Users.getInstance().getUsers().stream().anyMatch( u -> u.equals(userLogin))){
            req.getSession().setAttribute("user", userLogin);
            System.out.println("UserMatched");
        }
        doGet(req, resp);
    }
}
