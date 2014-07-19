<%-- 
    Document   : output
    Created on : Jul 13, 2014, 5:59:12 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>      
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> ${type} Page</title>
        <LINK rel="stylesheet" href="${path}/css/tablestyle.css">
    </head>
    <body>   
        <table class="features-table">
            <thead>
                <tr>            
                    <td></td>       
                    <td>id: </td>       
                    <td>name: </td>      
                    <td>type: </td>      
                    <td>country: </td>      
                    <td>rate: </td>      
                    <td>compl: </td>      
                    <td>uncmp: </td>  
                    <td>arcgr: </td>    
                    <td>mass: </td>            
                </tr>
            </thead>
            <c:forEach var="weapon" items="${weapons}" varStatus="status">   
                <tr> 
                    <td width="30">${status.index+1}</td>
                    <td>${weapon.id}</td>
                    <td>${weapon.name}</td>
                    <td>${weapon.type}</td>
                    <td>${weapon.country}</td>
                    <td width="50">${weapon.rate}</td>
                    <c:if test="${weapon.TYPE == 'gun'}">                                   
                        <td>${weapon.mass.complected}</td>  
                        <td>${weapon.mass.uncomplected}</td>   
                        <td></td>     
                        <td></td>    
                    </c:if>         
                    <c:if test="${weapon.TYPE == 'equip'}">     
                        <td></td>      
                        <td></td>   
                        <td>${weapon.arcgrad}</td>   
                        <td>${weapon.mass}</td>  
                    </c:if>         
                </tr>
            </c:forEach>
        </table>
    </body>
</html>