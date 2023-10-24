<%@ page import="com.example.webapplicationdemo.model.Uzytkownik" %>
<%@ page import="com.example.webapplicationdemo.model.Narzedzia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="uzytkownik" class="com.example.webapplicationdemo.model.Uzytkownik" scope="session" />
<ul>
  <li><a href="?strona=glowna">Strona główna</a></li>
  <li><a href="?strona=kwadratowe">ax<sup>2</sup>+bx+c</a></li>
  <li><a href="?strona=trzecia">Link3</a></li>
  ${ (uzytkownik.uprawnienia > 0) ? '<li><a href="?strona=ustawienia">Ustawienia</a></li>' : ''}
  <%
    if(uzytkownik.getUprawnienia() == 2) { %>
  <li><a href="?strona=administracja">Administracja</a></li>
  <% } %>
</ul>
<div id="newsy">
  <% if(uzytkownik.getUprawnienia() < 0) { %>
  <form action="mainController" method="post">
    Login: <input type="text" name="login" style="width: 90%;" />
    Hasło: <input type="password" name="haslo" style="width: 90%;" />
    <input type="submit" value="Zaloguj">
  </form>
  <% } else { %>
  <form action = "logout-servlet" method="post">
    Jesteś zalogowany jako <b><%=uzytkownik.getLogin()%></b>
    <input type="submit" value="Wyloguj"></br>
  </form>
  <% } %>
  <p id="news1"></p>
  <p id="news2"></p>
</div>
<div id="logowanie">

</div>
