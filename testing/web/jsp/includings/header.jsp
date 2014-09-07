<%-- 
    Document   : header
    Created on : Sep 7, 2014, 4:27:00 PM
    Author     : Sergiusz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/testing/css/header.css">
    </head>
    <body>
        <div id="homebutt">
            <form action="/testing" method="post" id="form3">
                <input type="image" src="css/png/home.png" name="image">
                <input type="hidden" value=login name="command">
                <input type="hidden" value=final name="progress">
            </form>
        </div>
        <div id="exitbutt">
            <form action="/testing/enter" method="post" id="form3">
                <input type="image" src="css/png/exit.png" name="image">
                <input type="hidden" name="command" value="begin">
                <input type="hidden" name="progress" value="final">
            </form>
        </div>
    </body>
</html>
