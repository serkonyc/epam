<%-- 
    Document   : themechoose
    Created on : Aug 5, 2014, 2:31:36 PM
    Author     : Sergiusz
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="label">
            <form id="form1">  
                <button type="button">${local["DCchtheme"]}</button>
            </form>
        </div>
        <div id="choosepage"> 
            <form action="/testing/addtest" method="post" id="form2">
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
                <input type="hidden" value="${subjid}" name="inputid">
                <input type="hidden" value="choosetheme" name="command">
                <input type="hidden" value="${oldinput}" name="oldinput">
            </form>
        </div>
        <div id="themelist">
            <form action="/testing/maketest" method="post" id="form1" autocomplete="off">                                                
                <ul class='vertical-menu'>
                    <c:forEach var="theme" items="${themes}">
                        <li>
                            <input type="submit" value="${theme.name}" name="input">                            
                        </li>
                    </c:forEach>
                    <c:if test="${fn:length(themes)!=0}">
                          <li>
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
                          </li>
                </c:if>
            </ul>
            <input type="hidden" value="${subjid}" name="inputid">
            <input type="hidden" value="choosetheme" name="command">
        </form>
        <form action="/testing/maketest" method="post" id="form1" autocomplete="off">
            <ul class='vertical-menu'>
                <li>
                    <input type="text" pattern="[A-я -]{4,24}" required placeholder="${local["DBinput"]}" name="input"> 
                    <input type="submit" value="${local["ZAadd"]}"> 
                    <input type="hidden" value="${subjid}" name="inputid">
                    <input type="hidden" value="choosetheme" name="command">
                    <input type="hidden" value="${oldinput}" name="oldinput">
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
                </li>
            </ul>
        </form>
    </div>
    <br/>  
</body>
</html>
