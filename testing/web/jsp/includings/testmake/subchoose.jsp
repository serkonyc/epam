<%-- 
    Document   : subchoose
    Created on : Aug 5, 2014, 2:28:06 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <button type="button">${local["subchoose"]}</button>            
            </form>
        </div>
        <div id="sublist">
            <form action="/testing/maketest" method="post" id="form1" autocomplete="off">
                <ul class='vertical-menu'>
                    <c:forEach var="subj" items="${subjs}">
                        <li>
                            <input type="submit" value="${subj.name}" name="input"> 
                            <input type="hidden" value="choosesubject" name="command">
                        </li>
                    </c:forEach>
                </ul>
            </form>
            <form action="/testing/maketest" method="post" id="form1" autocomplete="off">
                <ul class='vertical-menu' >
                    <li>
                        <input type="iftext" pattern="[A-я -]{2,16}" required placeholder="${local["chooseplaceholder"]}" name="input"> 
                        <input type="submit" value=${local["add"]}> 
                        <input type="hidden" value="choosesubject" name="command">
                        <input type="hidden" value="yes" name="newsubject">
                    </li>
                </ul>
            </form>
        </div>
        <br/>
    </body>
</html>
