<%-- 
    Document   : numberlist
    Created on : Sep 10, 2014, 2:54:03 PM
    Author     : Sergiusz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        ${local["DDquestnum"]}&nbsp&nbsp&nbsp&nbsp<select name="questnum">
                        <option value="3">${local["E3"]}</option>
                        <option value="4">${local["E4"]}</option>
                        <option selected value="5">${local["E5"]}</option>
                        <option value="6">${local["E6"]}</option>
                        <option value="7">${local["E7"]}</option>
                        <option value="8">${local["E8"]}</option>
                        <option value="9">${local["E9"]}</option>
                        <option value="10">${local["E10"]}</option>
                    </select>
    </body>
</html>
