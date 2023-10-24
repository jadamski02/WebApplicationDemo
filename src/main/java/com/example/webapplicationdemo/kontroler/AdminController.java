package com.example.webapplicationdemo.kontroler;

import com.example.webapplicationdemo.model.Uzytkownik;
import com.sun.net.httpserver.HttpContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

@WebServlet(name = "adminController", value = "/adminController")
public class AdminController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();
        HttpSession session = req.getSession();
        HashMap<String, Uzytkownik> uzytkownicy = (HashMap<String, Uzytkownik>) context.getAttribute("uzytkownicy");

        String reqValue = req.getParameter("typeOfUser");
        String[] splitted = reqValue.split("###");
        int uprawnienia = Integer.parseInt(splitted[0]);
        String userName = splitted[1];

        String login = uzytkownicy.get(userName).getLogin();
        String haslo = uzytkownicy.get(userName).getHaslo();
        Uzytkownik updatedUser = new Uzytkownik(login, haslo, uprawnienia);
        uzytkownicy.replace(userName, updatedUser);
        context.setAttribute("uzytkownicy", uzytkownicy);

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
