<%@ page import="com.example.webapplicationdemo.model.Uzytkownik" %>
<%@ page import="com.example.webapplicationdemo.model.Narzedzia" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><!DOCTYPE html>
<jsp:useBean id="uzytkownik" class="com.example.webapplicationdemo.model.Uzytkownik" scope="session" />
<%
    //uzytkownik.setUprawnienia(2);
    String strona = request.getParameter("strona");
    String podstrony = "glowna;kwadratowe;trzecia";
    if(uzytkownik.getUprawnienia() > 0) podstrony+=";ustawienia";
    if(uzytkownik.getUprawnienia() == 2) podstrony+=";administracja";
    strona = Narzedzia.parsujStrone(strona, podstrony);
%>
<html style="background-color: ${(empty applicationScope.kolorTla) ? '#333333' : applicationScope.kolorTla}">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Fullstack-demo</title>
    <link rel="stylesheet" type="text/css" href="styl.css"/>
    <script type ="text/javascript" src="skrypt.js"></script>
</head>
<body onload="funkcje(); zegarek(); setInterval(zegarek, 1000);">
<div id="kontener">
    <div id="naglowek">
        <jsp:include page="/WEB-INF/widok/naglowek.jsp" />
    </div>
    <div id="srodek">
        <div id="menu">
            <jsp:include page="/WEB-INF/widok/menu.jsp" />
        </div>
        <div id="tresc">
            <jsp:include page="/WEB-INF/widok/tresc.jsp" >
                <jsp:param name="jaka_strona" value="<%=strona%>" />
            </jsp:include>
        </div>
    </div>
    <div id="stopka">
        <jsp:include page="/WEB-INF/widok/stopka.jsp" />
    </div>
</div>
</body>
</html>