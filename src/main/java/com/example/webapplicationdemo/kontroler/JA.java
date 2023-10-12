package com.example.webapplicationdemo.kontroler;

import com.example.webapplicationdemo.model.JAuzytkownik;
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

        HttpSession sesja = request.getSession();

        JAuzytkownik uzytkownik = (JAuzytkownik) sesja.getAttribute("uzytkownik");
        if(uzytkownik == null) {
            uzytkownik = new JAuzytkownik();
            sesja.setAttribute("uzytkownik", uzytkownik);
        }

        String strona = request.getParameter("strona");
        if(uzytkownik.getUprawnienia() > 0)
            strona = Narzedzia.parsujStrone(strona, "glowna;kwadratowe;trzecia;ustawienia");
        else
            strona = Narzedzia.parsujStrone(strona, "glowna;kwadratowe;trzecia");

        String szablon = Narzedzia.pobierzSzablon("index.html", context);
        szablon = Narzedzia.warunkoweSkrypty(strona, szablon);
        szablon = Narzedzia.uzupelnij(szablon, "NAGLOWEK", "naglowek.html", context);
        szablon = Narzedzia.uzupelnij(szablon, "TRESC", strona+".html", context);
        szablon = Narzedzia.uzupelnij(szablon, "STOPKA", "stopka.html", context);


        if(uzytkownik.isZalogowany()) {
            String login = sesja.getAttribute("login").toString();
            szablon = Narzedzia.uzupelnij(szablon, "MENU", "menuZalogowany.html", context);
            szablon = Narzedzia.uzupelnijUserInfo(szablon, login);
        }
        else
            szablon = Narzedzia.uzupelnij(szablon, "MENU", "menuNiezalogowany.html", context);



        out.println(szablon);
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        ServletContext context = getServletContext();
        PrintWriter out = response.getWriter();

        HttpSession sesja = request.getSession();

        String login = request.getParameter("login");
        String haslo = request.getParameter("haslo");

        sesja.setAttribute("login", login);

        JAuzytkownik uzytkownik = (JAuzytkownik) sesja.getAttribute("uzytkownik");
        if(uzytkownik == null) {
            uzytkownik = new JAuzytkownik();
            sesja.setAttribute("uzytkownik", uzytkownik);
        }

        String strona = request.getParameter("strona");
        if(uzytkownik.getUprawnienia() > 0)
            strona = Narzedzia.parsujStrone(strona, "glowna;kwadratowe;trzecia;ustawienia");
        else
            strona = Narzedzia.parsujStrone(strona, "glowna;kwadratowe;trzecia");

        String szablon = Narzedzia.pobierzSzablon("index.html", context);
        szablon = Narzedzia.warunkoweSkrypty(strona, szablon);
        szablon = Narzedzia.uzupelnij(szablon, "NAGLOWEK", "naglowek.html", context);
        szablon = Narzedzia.uzupelnij(szablon, "TRESC", strona+".html", context);
        szablon = Narzedzia.uzupelnij(szablon, "STOPKA", "stopka.html", context);

        if((login.equals("user1") && haslo.equals("user1")) || (login.equals("admin") && haslo.equals("admin"))) {
            uzytkownik.setZalogowany(true);
            szablon = Narzedzia.uzupelnij(szablon, "MENU", "menuZalogowany.html", context);
            szablon = Narzedzia.uzupelnijUserInfo(szablon, login);
        }
        else {
            uzytkownik.setZalogowany(false);
            szablon = Narzedzia.uzupelnij(szablon, "MENU", "menuNiezalogowany.html", context);
        }

        out.println(szablon);
        out.close();
    }
}
