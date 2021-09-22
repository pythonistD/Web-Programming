"use strict"

const form  = document.getElementsByClassName('main-form')[0];
const x = document.getElementsByIdName('X');
const y = document.getElementsByIdName('Y');
const r = document.getElementsByIdName('R');
function checkY(y) {
  if(y<0){
    return false;
  }else {
    return true;
  }
}
function showMessage() {
  alert( 'Всем привет!' );
}

showMessage();
document.getElementById("SendData").onclick = function () {
  showMessage();
   document.getElementById("out").innerHTML = 'Всем привет!';
  // if (true) {
  //     fetch("script.php", {
  //         method: "GET",
  //         headers: {"Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"},
  //         body: "x=" + encodeURIComponent(x) + "&y=" + encodeURIComponent(y) + "&r=" + encodeURIComponent(r))
  //     }).then(response => response.text()).then(function (serverAnswer) {
  //         document.getElementById("out").innerHTML = serverAnswer;
  //     }).catch(err => createNotification("Ошибка HTTP. Повторите попытку позже. " + err));
  // }
};
