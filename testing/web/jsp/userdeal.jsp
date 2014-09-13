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
        <link rel="stylesheet" type="text/css" href="/testing/css/userdeal.css">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>      
        <%@ include file="/jsp/includings/header.jsp"%>
        <div id='locale1'>
            <form method="post" action="/testing/passtest">
                <button value="userdeal" name="command">RU</button>
                <input type="hidden" name="lang" value="label_ru">
            </form>
        </div>
        <div id='locale2'>
            <form method="post" action="/testing/passtest">
                <button value="userdeal" name="command">EN</button>
                <input type="hidden" name="lang" value="label_en">
            </form>
        </div>
        <div id="usertable">
            <table>
                <c:forEach var="user" items="${users}">
                    <tr>                    
                        <td>
                            ${user.id} 
                        </td>
                        <td>
                            ${user.nick}  
                        </td>
                        <td>
                            ${user.email}
                        </td>
                        <td>
                            ${user.role}
                        </td>                        
                        <c:if test="${user.role != 'admin'}">                                                                          
                        <form action="/testing/userdeal" method="post" autocomplete="off">
                            <td>
                                <button name="command" value="upgradeuser">${local["GAupgrade"]}</button>
                            </td>
                            <td>
                                <button name="command" value="downgradeuser">${local["GBdowngrade"]}</button>
                            </td>
                            <td>
                                <button name="command" value="deleteuser">${local["GCdelete"]}</button>                            
                                <input type="hidden" value=final name="progress">
                                <input type="hidden" name="id" value="${user.id}">
                                <input type="hidden" name="nick" value="${user.nick}">
                                <input type="hidden" name="email" value="${user.email}">
                                <input type="hidden" name="role" value="${user.role}">
                            </td>
                        </form>
                    </c:if>
                    <c:if test="${user.role == 'admin'}">                                                                          
                        <form action="/testing/userdeal" method="post" autocomplete="off">
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                &nbsp;
                            </td>
                            <td>
                                &nbsp;                      
                            </td>
                        </form>
                    </c:if>                      
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
