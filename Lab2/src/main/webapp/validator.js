"use strict";
let x, y, r;

function defineValues() {
    try {
        x = document.querySelector('input[name="x"]:checked').value;
        y = document.querySelector('input[id="Y"]').value;
        r = document.querySelector('select[id="R"]').value;
    } catch (err) {
        createNotification(err.name + " Некоторые значения пустые");
    }
}


document.getElementById("SendData").addEventListener("click", function (e) {
        defineValues();
        if (validateX() && validateR()) {
            fetch(createRequest(x,y,r)).then(async response => {
                let message = await response.text();
                if (message === "Error:400") {
                    document.getElementById("errorOut").innerHTML = message;
                } else {
                    document.getElementById("results").innerHTML += message;
                }
            }).catch(err => createNotification("Ошибка HTTP. Повторите попытку позже. " + err));
        }
    }
)

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
    let init = {method: 'GET', headers: header};
    return new Request(path, init);
}

function createNotification(message) {
    alert("Something went wrong " + message);
}

function validateY() {
    if (!isNumeric(y)) {
        createNotification("y не число");
        return false;
    } else if (!((y > -5) && (y <= 3))) {
        createNotification("y не входит в область допустимых значений");
        return false;
    } else return true;
}
function validateX() {
    if (isNumeric(x)) return true;
    else {
        createNotification("x не выбран");
        return false;
    }
}
function validateR() {
    if (isNumeric(x)) return true;
    else {
        createNotification("R не выбран");
        return false;
    }
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}
