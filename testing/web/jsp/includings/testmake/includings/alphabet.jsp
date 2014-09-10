<%-- 
    Document   : alphabet
    Created on : Sep 10, 2014, 2:17:33 PM
    Author     : Sergiusz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>        
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
</body>
</html>
