<%-- 
    Document   : deletetest
    Created on : Aug 18, 2014, 9:28:36 PM
    Author     : Sergiusz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div align="center">
            <h1 class="header" align="center">
                Hello ${nick}! Your id: ${id} :)
            </h1>   
        </div>
        <div id='menu-block'>
            <ul class='vertical-menu'>
                <c:forEach var="subj" items="${subjs}">
                    <li class='drop-link'>${subj.name}
                        <ul class='drop-block'>
                            <c:forEach var="theme" items="${themes}">
                                <c:if test="${subj.id == theme.subjectId}"> 
                                    <li class='drop-link'>${theme.name}
                                        <ul class='drop-block'>
                                            <li>
                                                <form method="post" id="form1" action="${path}/chootest">
                                                    <c:forEach var="test" items="${tests}">
                                                        <c:if test="${theme.id == test.themeId}"> 
                                                            <input type="submit" value="Тест ${test.themeId}-${test.id}" name="input"> 
                                                            <input type="hidden" value="deletechosentest" name="command">
                                                            <input type="hidden" value="${test.id}" name="data">
                                                        </c:if>    
                                                    </c:forEach> 
                                                </form>
                                            </li>
                                        </ul>
                                </c:if>    
                            </c:forEach>     
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div id='private'>
            <form method="post" id="form1" action="${path}/addtest">
                <input type="hidden" name="command" value="preparetest">
                <button>Добавить тест</button>
            </form>
            <form method="post" id="form1" action="${path}/enter">
                <input type="hidden" name="command" value="login">
                <button>Пройти тест</button>
            </form>            
            <form method="post" id="form1" action="${path}/chatest">
                <input type="hidden" name="command" value="changetest">
                <button>Изменить тест</button>
            </form>
            <form id="form1">
                <button type="button"><b>Удалить тест</b></button>
            </form>
            <font color="red"> ${result}
        </div>
        <div id='button' align="center">
            <form method="post" id="form1" action="${path}/enter">
                <input type="hidden" name="command" value="begin">
                <button>Бежим отсюда!</button>
            </form>
        </div>
    </body>
</html>
