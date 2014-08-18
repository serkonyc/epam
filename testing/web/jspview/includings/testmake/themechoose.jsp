<%-- 
    Document   : themechoose
    Created on : Aug 5, 2014, 2:31:36 PM
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
            <button type="reset">Количество вопросов</button>
            <br/>
            <button type="button">
                <select name="questnum">
                    <option value="3">Три</option>
                    <option value="4">Четыре</option>
                    <option selected value="5">Пять</option>
                    <option value="6">Шесть</option>
                    <option value="7">Семь</option>
                    <option value="8">Восемь</option>
                    <option value="9">Девять</option>
                    <option value="10">Десять</option>
                </select>
            </button>
            <br/>
            <button type="reset">Выберите тему</button>
        </div>
        <div id="themelist">
        <ul class='vertical-menu'>
            <c:forEach var="theme" items="${themes}">
                <li>
                    <input type="submit" value="${theme.name}" name="input"> 
                    <input type="hidden" value="${subjid}" name="inputid">
                    <input type="hidden" value="choosetheme" name="command">
                </li>
            </c:forEach>
            <li>
                <input type="text" pattern="[A-я ]{4,24}" optional placeholder="Создать новую..." name="input"> 
                <input type="submit" value="Добавить"> 
                <input type="hidden" value="${subjid}" name="inputid">
                <input type="hidden" value="choosetheme" name="command">
            </li>
        </ul>
        </div>
        <br/>  
    </body>
</html>
