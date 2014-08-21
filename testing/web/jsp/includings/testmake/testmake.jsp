<%-- 
    Document   : testmake
    Created on : Aug 11, 2014, 2:10:51 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Maketest page</title>
    </head>
    <body>
        <form action="/testing/maketest" method="post" id="form1" autocomplete="off">
            <div id='questlist'>
                <c:forEach var="quest" begin="1" end="${questnum}">
                    <table>
                        <tr>
                            <td>
                                <table>
                                    <tr class="trquestion">
                                        <td class="tdquestion">
                                            <textarea name="quest${quest}" required placeholder="Почему метеорологические камни столь жестоки к пользователям и не очень честно показывают прогноз?"></textarea>
                                        </td>
                                    </tr>
                                </table>
                            <td>
                            <td>
                                <table>
                                    <tr>
                                        <td>
                                            <input type="text" placeholder="Правильный вариант ответа" required name="${quest}-1">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="text" placeholder="Неверная альтернатива" required name="${quest}-2">
                                        </td>
                                    </tr>
                                    <c:forEach var="test" begin="3" end="5">
                                        <tr>
                                            <td>
                                                <input type="text" placeholder="Неверная альтернатива" name="${quest}-${test}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </table>
                </c:forEach>
                <br/>        
                <br/>     
                <br/>     
                <br/>  
            </div>
            <div div id='bottom'>
                <button>Добавить тест </button>
                <input type="hidden" value="${questnum}" name="questnum">
                <input type="hidden" value="${questarr}" name="questarr">
                <input type="hidden" value="${themeid}" name="inputid">
                <input type="hidden" value="final" name="progress">
                <input type="hidden" value=maketest name="command">
            </div>        
            <div id='help' align="justify">
                <br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                Заполните все поля для создания теста согласно
                <br/> шаблонам:
                <br/> левая часть отводится для вопроса и составляет 
                <br/> эшульме-мешульме символов,
                <br/> правая часть включает в себя пять вариантов ответа,
                <br/> верхний из которых является единственно правильным.
                <br/> <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                Все поля обязательны к заполнению, 
                <br/> а не то как дам щас.
            </div>     
        </form>
    </body>
</html>  