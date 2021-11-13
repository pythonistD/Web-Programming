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

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("SendData").addEventListener("click", function (e) {
            defineValues();
            if (validateY()) {
                fetch(createRequest()).then(async response => {
                    document.getElementById("results").innerHTML += await response.text();
                }).catch(err => createNotification("Ошибка HTTP. Повторите попытку позже. " + err));
            }
        }
    )
});


function createRequest() {
    let path = 'index.php?x='
        + x + '&y='
        + y + '&R='
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
        } else if (!((y >= -5) && (y <= 3))) {
            createNotification("y не входит в область допустимых значений");
            return false;
        } else return true;
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}
