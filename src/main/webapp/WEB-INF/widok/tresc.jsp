<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String jakaStrona = "/WEB-INF/widok/"+request.getParameter("jaka_strona")+".jsp"; %>
<jsp:include page="<%=jakaStrona %>" />
