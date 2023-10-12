package com.example.webapplicationdemo.kontroler;

import com.example.webapplicationdemo.model.JAuzytkownik;
import com.example.webapplicationdemo.model.Narzedzia;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "logoutServlet", value = "/logout-servlet")
public class Logout extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        ServletContext context = getServletContext();
        PrintWriter out = response.getWriter();

        HttpSession sesja = request.getSession();

        sesja.removeAttribute("login");
        sesja.removeAttribute("haslo");
        sesja.removeAttribute("userName");

        JAuzytkownik uzytkownik = (JAuzytkownik) sesja.getAttribute("uzytkownik");
        if(uzytkownik == null) {
            uzytkownik = new JAuzytkownik();
            sesja.setAttribute("uzytkownik", uzytkownik);
        }

        uzytkownik.setZalogowany(false);
        uzytkownik.setUprawnienia(-1);

        String strona = request.getParameter("strona");
        strona = Narzedzia.parsujStrone(strona, "glowna;kwadratowe;trzecia");
        String szablon = Narzedzia.pobierzSzablon("index.html", context);
        szablon = Narzedzia.warunkoweSkrypty(strona, szablon);
        szablon = Narzedzia.uzupelnij(szablon, "NAGLOWEK", "naglowek.html", context);
        szablon = Narzedzia.uzupelnij(szablon, "TRESC", strona+".html", context);
        szablon = Narzedzia.uzupelnij(szablon, "STOPKA", "stopka.html", context);
        szablon = Narzedzia.uzupelnij(szablon, "MENU", "menuNiezalogowany.html", context);

        out.println(szablon);
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
