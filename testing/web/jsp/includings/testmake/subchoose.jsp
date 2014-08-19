<%-- 
    Document   : subchoose
    Created on : Aug 5, 2014, 2:28:06 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="button">
            <button type="reset">Выберите предмет</button>
        </div>
        <div id="sublist">
            <ul class='vertical-menu'>
                <c:forEach var="subj" items="${subjs}">
                    <li>
                        <input type="submit" value="${subj.name}" name="input"> 
                        <input type="hidden" value="choosesubject" name="command">
                    </li>
                </c:forEach>
                <li>
                    <input type="iftext" pattern="[A-я ]{4,16}" optional placeholder="Введите название..." name="input"> 
                    <input type="submit" value="Добавить"> 
                    <input type="hidden" value="choosesubject" name="command">
                </li>
            </ul>
        </div>
        <br/>
    </body>
</html>
