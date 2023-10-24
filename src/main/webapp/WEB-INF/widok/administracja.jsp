<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<jsp:useBean id="uzytkownik" class="com.example.webapplicationdemo.model.Uzytkownik" scope="session" />
<jsp:useBean id="uzytkownicy" class="java.util.HashMap" scope="application" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="mainController" method="post">
  Kolor tła: <input type="text" name="kolor" value="${applicationScope.kolorTla}"/><br/>
  <input type="submit" value="Zapisz" />

</form>
<br/>
<c:forEach items="${uzytkownicy}" var="entry">

  <form action="adminController" method="post">
    <fieldset name="fieldSet_${entry.key}">
          ${entry.key}:<br/>
        <input type="radio" id="user" name="typeOfUser" value="1###${entry.key}" ${entry.value.uprawnienia == 1 ? "checked" : ""} />
        <label for="user">Użytkownik   </label>
        <input type="radio" id="admin" name="typeOfUser" value="2###${entry.key}" ${entry.value.uprawnienia == 2 ? "checked" : ""} />
        <label for="admin">Administrator   </label><input type="submit" value="Nadaj uprawnienia"/>
        <br/>
    </fieldset>
  </form>

</c:forEach>



