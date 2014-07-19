<%-- 
    Document   : choice
    Created on : Jul 13, 2014, 5:45:42 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main page</title>
    </head>
    <body>
        <h1>Please, make choice.</h1>
        <form method="post" action="servlet/choice">
            <input type="hidden" name="command" value="SAX">
            <input type="submit" value=" SAX  ">
        </form>

        <form method="post" action="servlet/choice">
            <input type="hidden" name="command" value="DOM">
            <input type="submit" value=" DOM ">
        </form>

        <form method="post" action="servlet/choice">
            <input type="hidden" name="command" value="STAX">
            <input type="submit" value="STAX">
        </form>
    </body>
</html>
