"use strict";

let x, y, r;

function defineValues(){
    x = document.querySelector('input[name="x"]:checked').value;
    y = document.querySelector('input[id="Y"]').value;
    r = document.querySelector('select[id="R"]').value;
}
document.getElementById("SendData").onclick = function () {
    defineValues();
    if (validateX() && validateY()) {
      fetch(createRequest()).then(response => response.text()).then(serverAnswer => {
          document.getElementById("out").innerHTML = serverAnswer;
      }).catch(err => createNotification("Ошибка HTTP. Повторите попытку позже. " + err));
  }
};
function createRequest(){
    let path = 'index.php?x='
    + x + '&y='
    + y + '&R='
    + r;
    let header = new Headers();
    header.append('Content-Type', 'application/x-www-form-url')
    let init = {method:'GET', headers:header};
    return new Request(path, init);
}
function createNotification(message) {
    alert("Something went wrong " + message);
}

function validateX() {
    if(typeof x === 'undefined'){
        createNotification("x не выбран");
        return false;
    }
    else {
        return true;
    }
}

function validateY() {
    if (y === null) {
        createNotification("y не введён");
        return false;
    } else if (!isNumeric(y)) {
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
