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
        <%@ include file="/jsp/includings/header.jsp"%>
        <c:if test="${result == null}">
            <div id='locale1'>
                <form method="post" id="form1" action="/testing/passtest">
                    <button type="auto" value="choosetest" name="command">RU</button>
                    <input type="hidden" name="lang" value="label_ru">
                    <input type="hidden" value="${testid}" name="data">
                </form>
            </div>
            <div id='locale2'>
                <form method="post" id="form1" action="/testing/passtest">
                    <button type="auto" value="choosetest" name="command">EN</button>
                    <input type="hidden" name="lang" value="label_en">
                    <input type="hidden" value="${testid}" name="data">
                </form>
            </div>
        </c:if>
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
                                            <c:set var="ifCheck" scope="page" value="no"/>   
                                            <c:forEach var="ans" items="${dat.answers}">                                                                                             
                                                <c:forEach var="secans" items="${dat.answers}">
                                                    <c:if test="${ifCheck == 'no'}">
                                                        <c:if test="${secans.ifCheckBox}">
                                                            <c:set var="ifCheck" scope="page" value="yes"/>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>     
                                                <c:choose>
                                                    <c:when test="${ifCheck == 'yes'}">
                                                        <c:if test="${ans.text != ''}">
                                                            <tr>
                                                                <td>
                                                                    <input type="checkbox" name="${ans.questId}" value="${ans.id}">${ans.text}
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                        <c:if test="${ans.textAdv != '' && ans.textAdv!=null}">
                                                            <tr>
                                                                <td>
                                                                    <input type="checkbox" name="${ans.questId}Adv" value="${ans.id}">${ans.textAdv}
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:if test="${ans.text != ''}">
                                                            <tr>
                                                                <td>
                                                                    <input type="radio" name="${ans.questId}" value="${ans.id}">${ans.text}
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:otherwise>
                                                </c:choose>
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
                    ${local["DNresult"]} ${result}
                    <div div id='result'>
                        <button>${local["DOknowit"]}</button>
                        <input type="hidden" value=passtest name="command">
                        <input type="hidden" value=final name="progress">
                    </div>        
                </c:if>
            </form>
        </div>
    </body>
</html>
