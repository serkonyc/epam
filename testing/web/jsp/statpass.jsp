<%-- 
    Document   : passstat
    Created on : Aug 19, 2014, 8:32:30 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/testing/css/addingtest.css">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <title>Statistics</title>
    </head>
    <body>
        <div id="statistics">
            <form id="form1">
                <ul class='vertical-menu'>
                    <c:forEach var="pass" items="${history}">
                        <li>
                            Тест номер ${pass.testId} <br>
                            Результат ${pass.result} %
                        </li>
                    </c:forEach>                         
                </ul>
            </form>
        </div>
        <div id="statdetails">
            <form action="/testing/enter" method="post" id="form1">
                <ul class='vertical-menu'>
                    <c:forEach var="test" items="${posttestlist}">
                        <li>
                            Предмет:&nbsp;&nbsp;${test.theme.subj.name} <br>
                            Тема:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${test.theme.name}
                        </li>
                    </c:forEach>                         
                </ul>
                <br/>
                <br/>
                <div id="statbutt">
                    <button>Вернуться</button>   
                    <input type="hidden" value="final" name="progress">
                    <input type="hidden" value=login name="command">
                </div>
            </form>
        </div>
    </body>
</html>
