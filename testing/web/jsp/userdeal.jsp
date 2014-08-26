<%-- 
    Document   : userdeal
    Created on : Aug 25, 2014, 6:43:30 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/testing/css/addingtest.css">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
            <table>
                <c:forEach var="user" items="${users}">
                    <form action="/testing/userdeal" method="post" id="form1" autocomplete="off">
                        <tr>
                            <td>
                                &nbsp;&nbsp;&nbsp;${user.id} 
                            </td>
                            <td>
                                &nbsp;&nbsp;&nbsp;${user.nick}  
                            </td>
                            <td>
                                &nbsp;&nbsp;&nbsp;${user.email}
                            </td>
                            <td>
                                &nbsp;&nbsp;&nbsp;${user.role}
                            </td>
                            <td>
                                <button name="command" value="upgradeuser">Повысить</button>
                            </td>
                            <td>
                                <button name="command" value="downgradeuser">Понизить</button>
                            </td>
                            <td>
                                <button name="command" value="deleteuser">Удалить</button>
                            </td>
                        <input type="hidden" value=final name="progress">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="nick" value="${user.nick}">
                        <input type="hidden" name="email" value="${user.email}">
                        <input type="hidden" name="role" value="${user.role}">
                        </tr>
                    </form>
                </c:forEach>
            </table>
        </ul>
    </body>
</html>
