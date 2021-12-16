"use strict";
let x, y, r;

function defineValues() {
    try {
        x = document.querySelector('input[id="X"]').value;
        y = document.querySelector('input[name="y"]:checked').value;
        r = document.querySelector('input[id="R"]').value;

        if(x.length >= 7){
            x = x.substring(0,6);
        }
        if(r.length >= 7){
            r = r.substring(0,6);
        }
        if(y.length >= 7){
            y = y.substring(0,6);
        }
    } catch (err) {
        createNotification(err.name + " Некоторые значения пустые");
    }
}
const svg = document.querySelector("svg");
document.addEventListener("DOMContentLoaded", () => {
    svg.addEventListener("click", (event) => {
        r = document.querySelector('input[id="R"]').value;
        if(r.length >= 7){
            r = r.substring(0,6);
        }
        if (validateR(r)) {
            const position = getMousePosition(svg, event);
            x = position.x;
            y = position.y;
            setPointer(x, y);
            x = position.x - 150;
            y = 150 - position.y;
            console.log(x + " " + y);
            const temp = 120/r;
            x = (x/temp).toFixed(1);
            y = (y/temp).toFixed(1);
            doGet(createRequest(x,y,r)).catch(err => createNotification("Ошибка HTTP. Повторите попытку позже. " + err));
        }
    });
});

function getMousePosition(svg, event) {
    const rect = svg.getBoundingClientRect();
    return {
        x: event.clientX - rect.left,
        y: event.clientY - rect.top
    };
}

function setPointer(x, y) {
    svg.insertAdjacentHTML("beforeend", `<circle r="5" cx="${x}" cy="${y}" fill="red"></circle>`);
}
document.getElementById("SendData").addEventListener("click", function (e) {
    defineValues();
        if (validateX(x) && validateR(r) && validateY(y)) {
            doGet(createRequest(x,y,r)).catch(err => createNotification("Ошибка HTTP. Повторите попытку позже. " + err));
        }
    }
)


function doGet(req){
    return  fetch(req).then(async response => {
        let message = await response.text();
        if (response.redirected) {
            window.location.href = response.url;
        }
        if (message === "Error:400") {
            document.getElementById("errorOut").innerHTML = message;
        } else {
            document.getElementById("results").innerHTML += message;
        }
    })
}

document.getElementById('clear').addEventListener('click',function (e) {
    fetch('clearHistory.php').then(r => {
        window.location.reload();
    });
})
// window.onload = function (){
//     fetch('loadTable.php').then(async response =>{
//         document.getElementById("results").innerHTML = await response.text();
//     })
// }

function createRequest(x,y,r) {
    let path = 'main?x='
        + x + '&y='
        + y + '&r='
        + r;
    let header = new Headers();
    header.append('Content-Type', 'application/x-www-form-url')
    let init = {method: 'GET', headers: header,redirect: "follow"};
    return new Request(path, init);
}

function createNotification(message) {
    alert("Something went wrong " + message);
}

function validateY(y) {
    if (isNumeric(y)) return true;
    else {
        createNotification("y не выбран");
        return false;
    }
}
function validateX(x) {
    if (!isNumeric(x)) {
        createNotification("x не число");
        return false;
    } else if (!((x > -5) && (x < 5))) {
        createNotification("x не входит в область допустимых значений");
        return false;
    } else {
        return true;
    }
}
function validateR(r) {
    if (!isNumeric(r)) {
        createNotification("r не число");
        return false;
    } else if (!((r > 1) && (r < 4))) {
        createNotification("R не входит в область допустимых значений");
        return false;
    } else {
        return true;
    }
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}
