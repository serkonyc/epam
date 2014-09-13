<%-- 
    Document   : statmake
    Created on : Aug 19, 2014, 8:33:31 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="usertag" uri="/WEB-INF/tld/custom.tld"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/testing/css/statistics.css">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <title>Statistics</title>
    </head>
    <body>
        <%@ include file="/jsp/includings/header.jsp"%>
        <div id='locale1'>
            <form method="post" id="form1" action="/testing/passtest">
                <button value="lookmakestat" name="command">RU</button>
                <input type="hidden" name="lang" value="label_ru">
                <input type="hidden" value="${currpage}" name="pagenum">
            </form>
        </div>
        <div id='locale2'>
            <form method="post" id="form1" action="/testing/passtest">
                <button value="lookmakestat" name="command">EN</button>
                <input type="hidden" name="lang" value="label_en">
                <input type="hidden" value="${currpage}" name="pagenum">
            </form>
        </div>
        <div id="statistics">      
            <usertag:statmake-table testdata="${posttestlist}" rows='10' local="${local}">
                &nbsp;
            </usertag:statmake-table>
        </div>
        <div id="statdetails">      
            <table class="byPages">
                <tr>
                    <c:forEach var="test" items="${allpagesnum}">
                        <td>
                            <form action="/testing/statistics" method="post" id="form1">
                                <c:choose>
                                    <c:when test="${currpage == test}">
                                        <button type="page" name="command" value="lookmakestat"><b>[${test}]</b></button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="page" name="command" value="lookmakestat">[${test}]</button>
                                    </c:otherwise>                                          
                                </c:choose> 
                                <input type="hidden" value="${test}" name="pagenum">
                            </form>
                        </td>
                    </c:forEach>
                </tr>
            </table>
        </div>
    </body>
</html>
