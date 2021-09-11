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
// y.addEventListener('input', function (event) {
//   if(y<0){
//     alert("It works");
//   }
// });
form.addEventListener("submit",function (event){
  if(!checkY(y)){
    event.preventDefault;
  }
});
