<%-- 
    Document   : passstat
    Created on : Aug 19, 2014, 8:32:30 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="usertag" uri="/WEB-INF/tld/custom.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/testing/css/addingtest.css">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <title>Statistics</title>
    </head>
    <body>
        <%@ include file="/jsp/includings/header.jsp"%>
        <div id="statistics">      
            <usertag:statpass-table passdata="${history}" testdata="${posttestlist}" rows='10' local="${local}">
                &nbsp;
            </usertag:statpass-table>
        </div>
        <div id="statdetails">      
            <table>
                <tr>
                    <c:forEach var="test" items="${allpagesnum}">
                        <td>
                            <form action="/testing/statistics" method="post" id="form2">
                                <c:choose>
                                    <c:when test="${currpage == test}">
                                        <button type="page" name="command" value="lookstat"><b>[${test}]</b></button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="page" name="command" value="lookstat">[${test}]</button>
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
