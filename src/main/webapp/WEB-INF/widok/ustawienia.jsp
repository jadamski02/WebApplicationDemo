
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action = "mainController" method="post">
  Imię<input type="text" name="imie" value="${uzytkownik.imie}"/></br>
Nazwisko<input type="text" name="nazwisko" value="${uzytkownik.nazwisko}"/></br>
Wiek<input type="number" name="wiek" min="0" value="${uzytkownik.wiekS}"/></br>
  <input type="submit" value="Zapisz">
</form>
