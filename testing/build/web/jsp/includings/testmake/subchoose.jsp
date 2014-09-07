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
        <div id="choosepage"> 
            <form action="/testing/addtest" method="post" id="form2">
                <table>
                    <tr>
                        <td>
                            <c:forEach var="latin" items="${latAB}">
                                <button name="letter" value="${latin}">${latin}</button>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr type="wall">                        
                    </tr>
                    <tr>
                        <td>
                            <c:forEach var="kirill" items="${kirAB}">
                                <button name="letter" value="${kirill}">${kirill}</button>
                            </c:forEach> 
                        </td>
                    </tr>                
                </table>    
                <input type="hidden" value="yes" name="additParam">
                <input type="hidden" name="command" value="choosesubject">
            </form>
        </div>
        <div id="label">
            <form id="form1">            
                <button type="button">${local["DAchsubject"]}</button>            
            </form>
        </div>
        <div id="sublist">
            <form action="/testing/maketest" method="post" id="form1" autocomplete="off">
                <ul class='vertical-menu'>
                    <c:forEach var="subj" items="${subjs}">
                        <li>
                            <input type="submit" value="${subj.name}" name="input"> 
                        </li>
                    </c:forEach>
                </ul>
                <input type="hidden" value="choosesubject" name="command">
            </form>
            <form action="/testing/addtest" method="post" id="form1" autocomplete="off">
                <ul class='vertical-menu' >
                    <li>
                        <input type="iftext" pattern="[A-я -]{2,16}" required placeholder="${local["DBinput"]}" name="input"> 
                        <input type="submit" value=${local["ZAadd"]}> 
                        <input type="hidden" value="choosesubject" name="command">
                        <input type="hidden" value="yes" name="additParam">
                    </li>
                </ul>
            </form>
        </div>
        <br/>
    </body>
</html>
