<%-- 
    Document   : postlog
    Created on : Jul 31, 2014, 7:29:31 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${path}/css/work.css">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <title>Work Room</title>
    </head>
    <body>
        <div align="center">
            <h1 class="header" align="center">
                Hello ${nick}! Your id: ${id}.
            </h1>   
        </div>
        <div id='locale1'>
            <form method="post" id="form1" action="/testing/enter">
                <button type="local" value="changelocale" name="command">RU</button>
                <input type="hidden" name="lang" value="label_ru">
                <input type="hidden" name="progress" value="final">
            </form>
        </div>
        <div id='locale2'>
            <form method="post" id="form1" action="/testing/enter">
                <button type="local" value="changelocale" name="command">EN</button>
                <input type="hidden" name="lang" value="label_en">
                <input type="hidden" name="progress" value="final">
            </form>
        </div>
        <c:choose>
            <c:when test="${wascommand == 'delete'}">
                <%@ include file="includings/testdeal/deletetest.jsp" %>
            </c:when>
            <c:when test="${wascommand == 'change'}">
                <%@ include file="includings/testdeal/changetest.jsp" %>
            </c:when>
            <c:otherwise>
                <%@ include file="includings/testdeal/passtest.jsp" %>
            </c:otherwise>
        </c:choose>
        <div id="statistics">
            <form method="post" id="form1" action="${path}/statistics">
                <button>${local["CFpassst"]}</button>
                <input type="hidden" value="lookstat" name="command">
            </form>
            <c:if test="${role == 'tutor' || role == 'admin'}">
                <form method="post" id="form1" action="${path}/statistics">
                    <button>${local["CGmakest"]}</button>
                    <input type="hidden" value="lookmakestat" name="command">
                </form>
            </c:if>
            <c:if test="${role == 'admin'}">
                <form method="post" id="form1" action="${path}/userdeal">
                    <button>${local["CIdeal"]}</button>
                    <input type="hidden" value="userdeal" name="command">
                    <input type="hidden" value="final" name="progress">
                </form>
            </c:if>
        </div>
    </body>
</html>
