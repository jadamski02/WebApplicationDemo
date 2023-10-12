package com.example.webapplicationdemo.model;

public class JAuzytkownik {

    private String login = "";
    private int uprawnienia = -1;
    private boolean zalogowany = false;

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

    @Override
    public String toString() {
        return "JAuzytkownik{" +
                "login='" + login + '\'' +
                ", uprawnienia=" + uprawnienia +
                '}';
    }
}
