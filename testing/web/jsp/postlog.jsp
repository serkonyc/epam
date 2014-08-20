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
                <button>Статистика прохождений</button>
                <input type="hidden" value="lookstat" name="command">
            </form>
            <form method="post" id="form1" action="${path}/statistics">
                <button>Статистика добавлений</button>
                <input type="hidden" value="lookmakestat" name="command">
            </form>
        </div>
    </body>
</html>
