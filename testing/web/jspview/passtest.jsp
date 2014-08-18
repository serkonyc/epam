<%-- 
    Document   : passtest
    Created on : Aug 18, 2014, 8:27:09 AM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/testing/cssheet/passingtest.css">
        <link rel="shortcut icon" href="/testing/cssheet/ico/icon.ico" >
        <title>Pass test</title>
    </head>
    <body>
        <div>
            <form action="/testing/passtest" method="post" id="form1" autocomplete="off">
                <div id='questlist'>
                    <c:forEach var="dat" items="${data}">           
                        <table>
                            <tr>
                                <td>
                                    <table>
                                        <tr class="trquestion">
                                            <td class="tdquestion">
                                                <textarea disabled>${dat.text}</textarea>
                                            </td>
                                        </tr>
                                    </table>
                                <td>
                                <td>
                                    <table>
                                        <c:forEach var="ans" items="${dat.answers}">
                                            <tr>
                                                <td>
                                                    <input type="radio" name="${ans.questId}" value="${ans.id}">${ans.text}
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
                    <button>Закончить тест</button>
                    <input type="hidden" value="${testid}" name="testid">
                    <input type="hidden" value=passtest name="command">
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
        </div>
    </body>
</html>
