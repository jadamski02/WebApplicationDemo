package com.example.webapplicationdemo.kontroler;

import com.example.webapplicationdemo.model.Narzedzia;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "JA", value = "/JA")
public class JA extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        ServletContext context = getServletContext();
        PrintWriter out = response.getWriter();

        String strona = request.getParameter("strona");
        strona = Narzedzia.parsujStrone(strona, "glowna;kwadratowe;trzecia");

        String szablon = Narzedzia.pobierzSzablon("index.html", context);
        szablon = Narzedzia.warunkoweSkrypty(strona, szablon);
        szablon = Narzedzia.uzupelnij(szablon, "NAGLOWEK", "naglowek.html", context);
        szablon = Narzedzia.uzupelnij(szablon, "MENU", "menu.html", context);
        szablon = Narzedzia.uzupelnij(szablon, "TRESC", strona+".html", context);
        szablon = Narzedzia.uzupelnij(szablon, "STOPKA", "stopka.html", context);

        out.println(szablon);
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
