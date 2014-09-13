<%-- 
    Document   : deletetest
    Created on : Aug 18, 2014, 9:28:36 PM
    Author     : Sergiusz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id='menu-block'>
            <br><br><br>
            <div id='menu-subblock'
                 <ul class='vertical-menu'>                
                    <c:forEach var="subj" items="${subjs}">
                        <li class='drop-link'>${subj.name}
                            <ul class='drop-block'>
                                <c:forEach var="theme" items="${themes}">
                                    <c:if test="${subj.id == theme.subj.id}"> 
                                        <li class='drop-link'>${theme.name}
                                            <ul class='drop-block'>
                                                <li class='testlist'>
                                                    <form method="post" id="form1" action="${path}/chootest">
                                                        <c:forEach var="test" items="${tests}">
                                                            <c:if test="${theme.id == test.themeId}"> 
                                                                <button type="list" name="data" value="${test.id}">${local["CAtlabel"]} ${test.themeId}-${test.id}</button>
                                                                <input type="hidden" value="deletechosentest" name="command">
                                                                <input type="hidden" value="final" name="progress">
                                                            </c:if>    
                                                        </c:forEach> 
                                                    </form>
                                                </li>
                                            </ul>
                                    </c:if>    
                                </c:forEach>     
                            </ul>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div id='private'>
            <form method="post" id="form1" action="${path}/addtest">
                <input type="hidden" name="command" value="preparetest">
                <button>${local["CBadd"]}</button>
            </form>
            <form method="post" id="form1" action="${path}/enter">
                <input type="hidden" name="command" value="login">
                <input type="hidden" value="final" name="progress">
                <button>${local["CCpass"]}</button>
            </form>            
            <form method="post" id="form1" action="${path}/chatest">
                <input type="hidden" name="command" value="changetest">
                <button>${local["CDchange"]}</button>
            </form>
            <form id="form1">
                <button type="button">&larr; <b>${local["CEdelete"]}</b>&emsp;&emsp;&emsp;&emsp;</button>
            </form>
        </div>
        <div id='button' align="center">
            <form method="post" id="form1" action="${path}/enter">
                <input type="hidden" name="command" value="begin">
                <button>${local["CHgoaway"]}</button>
                <input type="hidden" name="progress" value="final">
            </form>
        </div>
    </body>
</html>
