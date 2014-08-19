<%-- 
    Document   : welcome
    Created on : Jul 28, 2014, 6:14:20 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/testing/css/poster.css">
        <link rel="stylesheet" type="text/css" href="${css}">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <title>Welcome!</title>
    </head>
    <body>
        <table align="center">
            <tr>
                <td>
                    <h1 class="logo">
                        webTest
                    </h1>
                </td>
            </tr>
            <tr align="center">
                <c:choose>
                    <c:when test="${logorreg == 'logfailure'}"> 
                        <td>                    
                            <form method="post" id="form1" type="err" action="/testing/enter" autocomplete="off">
                                <input type="text" pattern="[A-Za-z0-9_]{4,16}" required placeholder="*Nick doesn't exist!" name="login">
                                <br>
                                <input type="password" required placeholder="*Password" name="pass">
                                <br>
                                <button value="login" name="command"><b>SIGN IN</b></button>                                
                            </form>
                            <form method="post" id="form1" action="/testing/enter">
                                <button value="needregister" name="command">Sign up</button>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${logorreg == 'logexfailure'}"> 
                        <td>                    
                            <form method="post" id="form1" type="err" action="/testing/enter" autocomplete="off">
                                <input type="text" pattern="[A-Za-z0-9_]{4,16}" required value="${nick}" name="login">
                                <br>
                                <input type="password" required placeholder="*Incorrect Password!" name="pass">
                                <br>
                                <button value="login" name="command"><b>SIGN IN</b></button>                                
                            </form>
                            <form method="post" id="form1" action="/testing/enter">
                                <button value="needregister" name="command">Sign up</button>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${logorreg == 'register'}"> 
                        <td>                    
                            <form method="post" id="form1" action="/testing/enter" autocomplete="off">
                                <input type="text" pattern="[A-Za-z0-9_]{4,16}" required placeholder="*Nickname" name="login">
                                <input type="email" placeholder="Email" name="email">
                                <br>
                                <input type="password" required placeholder="*Password" name="pass1">
                                <input type="password" required placeholder="*Repeat Password" name="pass2">
                                <br>
                                <button value="register" name="command"><b>SIGN UP</b></button>
                            </form>
                            <form method="post" id="form1" action="/testing/enter">
                                <button value="needlogin" name="command">Sign in</button>
                            </form>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>                    
                            <form method="post" id="form1" action="/testing/enter" autocomplete="off">
                                <input type="text" pattern="[A-Za-z0-9_]{4,16}" required placeholder="*Nickname" name="login">
                                <br>
                                <input type="password" required placeholder="*Password" name="pass">
                                <br>
                                <button value="login" name="command"><b>SIGN IN</b></button>                                
                            </form>
                            <form method="post" id="form1" action="/testing/enter">
                                <button value="needregister" name="command">Sign up</button>
                            </form>
                        </td>
                    </c:otherwise>
                </c:choose>
            </tr>
            <tr align="center">            
                <td>
                    <br>
                    <h6>
                        Sergiusz Koczanowski © 2014
                    </h6>
                </td>
            </tr>
        </table>        
    </body>    
</html>
