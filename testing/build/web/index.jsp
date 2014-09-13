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
        <link rel="stylesheet" type="text/css" href="/testing/css/primary.css">
        <link rel="stylesheet" type="text/css" href="${css}">
        <link rel="shortcut icon" href="/testing/css/ico/icon.ico" >
        <title>Welcome!</title>
    </head>
    <body>
        <div id='locale1'>
            <form method="post" id="form1" action="/testing/enter">
                <c:choose>
                    <c:when test="${logorreg == 'register'}">
                        <button value="needregister" name="command">RU</button>
                        <input type="hidden" name="lang" value="label_ru">
                    </c:when>
                    <c:otherwise>
                        <button value="needlogin" name="command">RU</button>
                        <input type="hidden" name="lang" value="label_ru">
                        <input type="hidden" name="progress" value="final">
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
        <div id='locale2'>
            <form method="post" id="form1" action="/testing/enter">
                <c:choose>
                    <c:when test="${logorreg == 'register'}">
                        <button value="needregister" name="command">EN</button>
                        <input type="hidden" name="lang" value="label_en">
                    </c:when>
                    <c:otherwise>
                        <button value="needlogin" name="command">EN</button>
                        <input type="hidden" name="lang" value="label_en">
                        <input type="hidden" name="progress" value="final">
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
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
                                <input type="text" pattern="[A-Za-z0-9_]{4,16}" required placeholder="${local["ACnickherror"]}" name="login">
                                <br>
                                <input type="password" required placeholder="${local["ABpassholder"]}" name="pass">
                                <br>
                                <button value="login" name="command"><b>${local["AEsignin"]}</b></button>   
                            </form>
                            <form method="post" id="form1" action="/testing/enter">
                                <button value="needregister" name="command">${local["AFsignup"]}</button>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${logorreg == 'logexfailure'}"> 
                        <td>                    
                            <form method="post" id="form1" type="err" action="/testing/enter" autocomplete="off">
                                <input type="text" pattern="[A-Za-z0-9_]{4,16}" required value="${nick}" name="login">
                                <br>
                                <input type="password" required placeholder="${local["ADpassherror"]}" name="pass">
                                <br>
                                <button value="login" name="command"><b>${local["AEsignin"]}</b></button>    
                            </form>
                            <form method="post" id="form1" action="/testing/enter">
                                <button value="needregister" name="command">${local["AFsignup"]}</button>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${logorreg == 'regexfailure'}"> 
                        <td>                    
                            <form method="post" id="form1" type="err" action="/testing/enter" autocomplete="off">
                                <input type="text" pattern="[A-Za-z0-9_]{4,16}" required placeholder="${local["BEnickherror"]}" name="login">
                                <input type="email" placeholder="${local["BBemail"]}" name="email">
                                <br>
                                <input type="password" required placeholder="${local["BCpassholder"]}" name="pass1">
                                <input type="password" required placeholder="${local["BDreppassholder"]}" name="pass2">
                                <br>
                                <button value="register" name="command"><b>${local["BGsignup"]}</b></button>
                            </form>
                            <form method="post" id="form1" action="/testing/enter">
                                <button value="needlogin" name="command">${local["BFsignin"]}</button>
                            </form>
                        </td>
                    </c:when>
                    <c:when test="${logorreg == 'register'}"> 
                        <td>                    
                            <form method="post" id="form1" action="/testing/enter" autocomplete="off">
                                <input type="text" pattern="[A-Za-z0-9_]{4,16}" required placeholder="${local["BAnickholder"]}" name="login">
                                <input type="email" placeholder="${local["BBemail"]}" name="email">
                                <br>
                                <input type="password" required placeholder="${local["BCpassholder"]}" name="pass1">
                                <input type="password" required placeholder="${local["BDreppassholder"]}" name="pass2">
                                <br>
                                <button value="register" name="command"><b>${local["BGsignup"]}</b></button>
                            </form>
                            <form method="post" id="form1" action="/testing/enter">
                                <button value="needlogin" name="command">${local["BFsignin"]}</button>
                            </form>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>                    
                            <form method="post" id="form1" action="/testing/enter" autocomplete="off">
                                <input type="text" pattern="[A-Za-z0-9_]{4,16}" required placeholder="${local["AAnickholder"]}" name="login">
                                <br>
                                <input type="password" required placeholder="${local["ABpassholder"]}" name="pass">
                                <br>
                                <button value="login" name="command"><b>${local["AEsignin"]}</b></button>     
                            </form>
                            <form method="post" id="form1" action="/testing/enter">
                                <button value="needregister" name="command">${local["AFsignup"]}</button>
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
