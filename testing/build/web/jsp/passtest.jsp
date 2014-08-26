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
        <link rel="stylesheet" type="text/css" href="/testing/css/passingtest.css">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <title>Pass test</title>
    </head>
    <body>
        <div>
            <form action="/testing/passtest" method="post" id="form1" autocomplete="off">
                <c:if test="${result == null}">
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
                                                <c:if test="${ans.text != ''}">
                                                <tr>
                                                    <td>
                                                        <input type="radio" name="${ans.questId}" value="${ans.id}">${ans.text}
                                                    </td>
                                                </tr>
                                                </c:if>
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
                        <button>${local["DMpasstest"]}</button>
                        <input type="hidden" value="${testid}" name="testid">
                        <input type="hidden" value=passtest name="command">
                        <input type="hidden" value=final name="progress">
                    </div>
                    <div id='help' align="justify">
                        <br/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        ${local["DIpassadv"]}
                    </div>   
                </c:if>
                <c:if test="${result != null}">
                    ${result}
                    <div div id='result'>
                        <button>А я так и знал!</button>
                        <input type="hidden" value=passtest name="command">
                        <input type="hidden" value=final name="progress">
                    </div>        
                </c:if>
            </form>
        </div>
    </body>
</html>
