<%-- 
    Document   : changetest
    Created on : Aug 18, 2014, 10:18:40 PM
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
                                                <textarea name="quest${dat.id}" required>${dat.text}</textarea>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <table>
                                        <c:forEach var="ans" varStatus="status" items="${dat.answers}">                                            
                                            <c:if test="${status.index+1 == 1}">
                                                <tr>
                                                    <td>
                                                        <input type="text" placeholder="${local["DFcorrect"]}" name="answer${ans.id}" value="${ans.text}" required>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input type="text" placeholder="${local["DFcorrect"]}" name="answer${ans.id}-1.5" value="${ans.textAdv}">
                                                    </td>
                                                </tr>    
                                            </c:if>
                                            <c:if test="${status.index+1 == 2}">
                                                <tr>
                                                    <td>
                                                        <input type="text" placeholder="${local["DEincorr"]}" name="answer${ans.id}" value="${ans.text}" required>
                                                    </td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${status.index+1 > 2}"> 
                                                <tr>
                                                    <td>
                                                        <input type="text" placeholder="${local["DEincorr"]}" name="answer${ans.id}" value="${ans.text}" >
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
                    <button>${local["DLchatest"]}</button>
                    <input type="hidden" value="${testid}" name="testid">
                    <input type="hidden" value=changechosentest name="command">
                    <input type="hidden" value=finally name="phase">
                    <input type="hidden" value="final" name="progress">
                    <input type="hidden" value="${questnum}" name="questnum">
                    <input type="hidden" value="${olddata}" name="olddata">
                </div>        
                <div id='help' align="justify">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    ${local["DGfirstadv"]}
                    <br/> <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    ${local["DHsecadv"]}
                </div>   
            </form>
        </div>
    </body>
</html>
