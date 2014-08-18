<%-- 
    Document   : error
    Created on : Jul 31, 2014, 1:05:43 AM
    Author     : Sergiusz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${path}/cssheet/poster.css">
        <link rel="shortcut icon" href="/testing/cssheet/ico/icon.ico" >
        <title>Error</title>
    </head>
    <body>          
        <table align="center">
            <tr>
                <td>
                    <h1 class="error">
                        Hello Error! ;(
                    </h1>    
                </td>
            </tr>
            <tr align="center">
                <td height="100px">
                    <h6>
                        ${errorMess}
                    </h6>
                </td>
            </tr>
            <tr align="center">
                <td height="100px">
                    <form method="post" action="${path}/enter">
                        <input type="hidden" name="command" value="login">
                        <input type="submit" value="Вернуться на главную">
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
