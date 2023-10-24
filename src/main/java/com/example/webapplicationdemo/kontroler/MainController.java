package com.example.webapplicationdemo.kontroler;

import com.example.webapplicationdemo.model.Uzytkownik;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "mainController", value = "/mainController")
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        ServletContext context = getServletContext();
        String kolorTla = request.getParameter("kolor");
        if(kolorTla == null) kolorTla = "";
        context.setAttribute("kolorTla", kolorTla);
        PrintWriter out = response.getWriter();
        HttpSession sesja = request.getSession();

        String login = request.getParameter("login");
        String haslo = request.getParameter("haslo");
        String imie = request.getParameter("imie");
        String nazwisko = request.getParameter("nazwisko");
        String wiek = request.getParameter("wiek");
        sesja.setAttribute("login", login);

        HashMap<String, Uzytkownik> uzytkownicy = (HashMap<String, Uzytkownik>) context.getAttribute("uzytkownicy");
        if(uzytkownicy == null) {
            uzytkownicy = new HashMap<>();
            uzytkownicy.put("user1", new Uzytkownik("user1", "user1", 1));
            uzytkownicy.put("user2", new Uzytkownik("user2", "user2", 1));
            uzytkownicy.put("user3", new Uzytkownik("user3", "user3", 2));
            uzytkownicy.put("admin", new Uzytkownik("admin", "admin", 2));
            context.setAttribute("uzytkownicy", uzytkownicy);
        }

        Uzytkownik uzytkownik = (Uzytkownik) sesja.getAttribute("uzytkownik");
        if(uzytkownik == null) {
            uzytkownik = new Uzytkownik();
            sesja.setAttribute("uzytkownik", uzytkownik);
        }

        if(login != null && haslo != null) {

            Uzytkownik potencjalnyUser = new Uzytkownik(login, haslo, -1);

            if(uzytkownicy.containsValue(potencjalnyUser)) {
                uzytkownik.setZalogowany(true);
                uzytkownik.setLogin(login);
                uzytkownik.setHaslo(haslo);
                uzytkownik.setUprawnienia(uzytkownicy.get(login).getUprawnienia());
            } else {
                uzytkownik.setZalogowany(false);
                uzytkownik.setUprawnienia(-1);
            }

        }

        if(imie != null) uzytkownik.setImie(imie);
        if(nazwisko != null) uzytkownik.setNazwisko(nazwisko);
        if(wiek != null) uzytkownik.setWiek(Integer.parseInt(wiek));

        response.sendRedirect(request.getContextPath() + "/index.jsp");

    }
}
