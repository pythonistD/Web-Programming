<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 16.12.2021
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table</title>
</head>
<body>
<div id="outContainer">
    <table id="res_table">
        <thead>
        <tr>
            <td>X</td>
            <td>Y</td>
            <td>R</td>
            <td>Result</td>
            <td>TimeWork</td>
            <td>CurrentTime</td>
        </tr>
        </thead>
        <tbody id="results">
        <jsp:useBean id="table" scope="application" class="model.Table" />
        <c:forEach var="point" items="${table.pointList}">
            <tr>
                <td>${point.x}</td>
                <td>${point.y}</td>
                <td>${point.r}</td>
                <td>${point.hit}</td>
                <td>${point.timeOfWork}</td>
                <td>${point.curTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="back">
        <button id="Back">back</button>
    </div>
</div>
</body>
<script type="text/javascript" src="redirect.js"></script>
</html>
