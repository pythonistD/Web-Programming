<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style.css">
    <link rel="shortcut icon" type="image/jpg" href="img/favicon.ico"/>
    <title>Lab#1</title>
</head>
<body>
<header>
    <div class="text">
        <p>Cheboksarov Yaroslav Maksimovich</p>
        <p>group:P3211</p>
        <p>variant:11021</p>
    </div>
</header>
<div id="center">
    <div id="img">
        <svg xmlns="http://www.w3.org/2000/svg" id="img">
            <line x1="0" y1="150" x2="300" y2="150" stroke="#000720"></line>
            <line x1="150" y1="0" x2="150" y2="300" stroke="#000720"></line>
            <text x="265" y="140">R</text>
            <text x="200" y="140">R/2</text>
            <text x="65" y="140">-R/2</text>
            <text x="20" y="140">-R</text>
            <text x="156" y="35">R</text>
            <text x="156" y="95">R/2</text>
            <text x="156" y="215">-R/2</text>
            <text x="156" y="275">-R</text>
            <polygon points="300,150 295,155 295, 145" fill="#000720" stroke="#000720"></polygon>
            <polygon points="150,0 145,5 155,5" fill="#000720" stroke="#000720"></polygon>
            <rect x="20" y="150" width="130" height="65" fill-opacity="0.4" stroke="navy" fill="blue"></rect>
            <polygon points="150,150 200,150 150,280" fill-opacity="0.4" stroke="navy" fill="blue"></polygon>
            <path d="M150 40 A 150 150, 0, 0, 1, 260 150 L 150 150 Z" fill-opacity="0.4" stroke="red" fill="red"></path>
        </svg>
    </div>
    <div id="right_bar">
        <div class="main-form">
            <div class="wrapper">
                <div class="row">
                    Y:
                    <input id="1" type="radio" name="y" value="-2"><label for="1">-2</label>
                    <input id="2" type="radio" name="y" value="-1.5"><label for="2">-1.5</label>
                    <input id="3" type="radio" name="y" value="-1"><label for="3">-1</label>
                    <input id="4" type="radio" name="y" value="-0.5"><label for="4">-0.5</label>
                    <input id="5" type="radio" name="y" value="0"><label for="5">0</label>
                    <input id="6" type="radio" name="y" value="0.5"><label for="6">0.5</label>
                    <input id="7" type="radio" name="y" value="1"><label for="7">1</label>
                    <input id="8" type="radio" name="y" value="1.5"><label for="8">1.5</label>
                    <input id="9" type="radio" name="y" value="2"><label for="9">2</label>
                    <label for="X">X:</label>
                    <input id="X" type="text" name="x" placeholder="от -5 до 5" >

                    <label for="R">R</label>
                    <input id="R" type="text" name="r" placeholder="от 1 до 4" >

                </div>
                <div class="submit">
                    <button id="SendData">Check data</button>
                </div>
                <div class="clear_session">
                    <button id="clear">Clear session data</button>
                </div>
            </div>
        </div>
        <div id="errorOut">
            <p>Error output</p>

        </div>
    </div>
</div>
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
        <%! %>
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
</div>

<script type="text/javascript" src="validator.js"></script>
</body>
</html>
