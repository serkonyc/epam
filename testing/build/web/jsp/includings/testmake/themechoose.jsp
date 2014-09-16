<%-- 
    Document   : themechoose
    Created on : Aug 5, 2014, 2:31:36 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="label">
            <form id="form1">  
                <button type="button">${local["DCchtheme"]} &larr; ${oldinput}</button>
            </form>
        </div>
        <div id="choosepage"> 
            <form action="/testing/addtest" method="post" id="form2">
                <%@ include file="includings/alphabet.jsp" %>
                <input type="hidden" value="${subjid}" name="inputid">
                <input type="hidden" value="choosetheme" name="command">
                <input type="hidden" value="${oldinput}" name="oldinput">
            </form>
        </div>
        <div id="themelist">
            <form action="/testing/maketest" method="post" id="form1" autocomplete="off">                                                
                <ul class='vertical-menu'>
                    <c:if test="${fn:length(themes)!=0}">
                        <li>
                            <%@ include file="includings/numberlist.jsp" %>
                        </li>
                    </c:if>
                    <c:forEach var="theme" items="${themes}">
                        <li>
                            <input type="submit" value="${theme.name}" name="input">                            
                        </li>
                    </c:forEach>                    
                </ul>
                <input type="hidden" value="${subjid}" name="inputid">
                <input type="hidden" value="choosetheme" name="command">
            </form>
            <form action="/testing/maketest" method="post" id="form1" autocomplete="off">
                <ul class='vertical-menu'>
                    <li>
                        <%@ include file="includings/numberlist.jsp" %>
                        <input type="text" pattern="[A-я -]{2,24}" required placeholder="${local["DBinput"]}" name="input"> 
                        <input type="submit" value="${local["ZAadd"]}"> 
                        <input type="hidden" value="${subjid}" name="inputid">
                        <input type="hidden" value="choosetheme" name="command">
                        <input type="hidden" value="${oldinput}" name="oldinput">                    
                    </li>
                </ul>
            </form>
        </div>
        <br/>  
    </body>
</html>
