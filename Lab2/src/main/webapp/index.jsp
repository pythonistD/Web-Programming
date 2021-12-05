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
        <svg xmlns="http://www.w3.org/2000/svg">
            <line x1="0" y1="150" x2="300" y2="150" stroke="#000720"></line>
            <line x1="150" y1="0" x2="150" y2="300" stroke="#000720"></line>
            <line x1="270" y1="148" x2="270" y2="152" stroke="#000720"></line>
            <line x1="270" y1="148" x2="270" y2="200" stroke="#000720"></line>
            <text x="265" y="140">R</text>
            <text x="200" y="140">R/2</text>
            <text x="75" y="140">-R/2</text>
            <text x="20" y="140">-R</text>
            <text x="156" y="35">R</text>
            <text x="156" y="95">R/2</text>
            <text x="156" y="215">-R/2</text>
            <text x="156" y="275">-R</text>
            <polygon points="300,150 295,155 295, 145" fill="#000720" stroke="#000720"></polygon>
            <polygon points="150,0 145,5 155,5" fill="#000720" stroke="#000720"></polygon>
            <rect x="150" y="90" width="120" height="60" fill-opacity="0.4" stroke="navy" fill="blue"></rect>
            <polygon points="150,150 270,150 150,210" fill-opacity="0.4" stroke="navy" fill="blue"></polygon>
            <path d="M90 150 A 75 75, 0, 0, 0, 150 210 L 150 150 Z" fill-opacity="0.4" stroke="navy" fill="blue"></path>
        </svg>
    </div>
    <div id="right_bar">
        <div class="main-form">
            <div class="wrapper">
                <div class="row">
                    X:
                    <input id="1" type="radio" name="x" value="-4"><label for="1">-4</label>
                    <input id="2" type="radio" name="x" value="-3"><label for="2">-3</label>
                    <input id="3" type="radio" name="x" value="-2"><label for="3">-2</label>
                    <input id="4" type="radio" name="x" value="-1"><label for="4">-1</label>
                    <input id="5" type="radio" name="x" value="0"><label for="5">0</label>
                    <input id="6" type="radio" name="x" value="1"><label for="6">1</label>
                    <input id="7" type="radio" name="x" value="2"><label for="7">2</label>
                    <input id="8" type="radio" name="x" value="3"><label for="8">3</label>
                    <input id="9" type="radio" name="x" value="4"><label for="9">4</label>
                    <label for="Y">Y:</label>
                    <input id="Y" type="text" name="y" placeholder="-5 до 3" >

                    <label for="R">R</label><select name="R" id="R">
                    <option value="1">R:1</option>
                    <option value="2">R:2</option>
                    <option value="3">R:3</option>
                    <option value="4">R:4</option>
                    <option value="5">R:5</option>
                </select>
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
        <tbody id="results"></tbody>


    </table>
</div>

<script type="text/javascript" src="validator.js"></script>
</body>
</html>
