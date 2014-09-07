<%-- 
    Document   : addquest
    Created on : Aug 5, 2014, 2:37:14 AM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/testing/css/addingtest.css">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <title>Add Test</title>
    </head>
    <body>     
        <%@ include file="/jsp/includings/header.jsp"%>
        <div>            
                <c:if test="${wascommand == 'prepare'}"> 
                    <%@ include file="includings/testmake/subchoose.jsp" %>
                </c:if>    
                <c:if test="${wascommand == 'themes'}"> 
                    <%@ include file="includings/testmake/themechoose.jsp" %>
                </c:if>
                <c:if test="${wascommand == 'questions'}"> 
                    <%@ include file="includings/testmake/testmake.jsp" %>             
                </c:if>                
        </div>
    </body>
</html>
