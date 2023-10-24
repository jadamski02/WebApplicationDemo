package com.example.webapplicationdemo.model;

import java.util.Objects;

public class Uzytkownik {

    private String login = "";
    private int uprawnienia = -1;
    private boolean zalogowany = false;
    private String imie = "";
    private String nazwisko = "";
    private int wiek;
    private String haslo;

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(int uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    public boolean isZalogowany() {
        return zalogowany;
    }

    public void setZalogowany(boolean zalogowany) {
        this.zalogowany = zalogowany;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = filtruj(imie);
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = filtruj(nazwisko);
    }

    public int getWiek() {
        return wiek;
    }

    public String getWiekS() {
        if(wiek >= 0)
            return "" + wiek;
        else return "";
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    @Override
    public String toString() {
        return "JAuzytkownik{" +
                "login='" + login + '\'' +
                ", uprawnienia=" + uprawnienia +
                '}';
    }

    public String filtruj (String wejscie) {
        StringBuffer filtrowane = new StringBuffer();
        char c;

        for(int i = 0; i<wejscie.length(); i++) {
            c = wejscie.charAt(i);
            switch(c) {
                case '<': filtrowane.append("&lt;"); break;
                case '>': filtrowane.append("&gt;"); break;
                case '"': filtrowane.append("&quot;"); break;
                case '&': filtrowane.append("&amp;"); break;
                default: filtrowane.append(c);
            }
        }
        return filtrowane.toString();
    }

    public Uzytkownik(String login, String haslo, int uprawnienia) {
        this.login = login;
        this.haslo = haslo;
        this.uprawnienia = uprawnienia;
    }

    public Uzytkownik() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uzytkownik that = (Uzytkownik) o;
        return login.equals(that.login) && haslo.equals(that.haslo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, haslo);
    }
}
