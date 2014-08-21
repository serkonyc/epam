<%-- 
    Document   : error
    Created on : Jul 31, 2014, 1:05:43 AM
    Author     : Sergiusz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/testing/css/poster.css">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <title>Error</title>
    </head>
    <body>          
        <table align="center">
            <tr>
                <td>
                    <h1 class="error">
                        Hello Error! ;(
                    </h1>    
                </td>
            </tr>
            <tr align="center">
                <td height="100px">
                    <h6>
                        <c:if test="${errorMess == null}">
                            Page is not found. Maybe it was removed few seconds ago.
                        </c:if>
                        ${errorMess}
                    </h6>
                </td>
            </tr>
            <tr align="center">
                <td height="100px">
                    <form method="post" id="form1" action="/testing/enter">
                        <input type="hidden" name="command" value="login">
                        <input type="hidden" name="progress" value="final">
                        <button><b>Вернуться обратно</b></button>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
