<?php
session_start();
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
ini_set('error_reporting', E_ALL);
if (!isset($_SESSION["tableRW"])) {
    $_SESSION["tableRW"] = array();
}
//запоминаем время начала работы скрипта
$start = microtime(true);
//получаем дату и время по москве
date_default_timezone_set('Europe/Moscow');
$now = date("d.m.y H:i");
//получаем параметры из main.html
if(isset($_GET["x"])){
    $x = $_GET["x"];
}else{
    echo "Error:400";
    http_response_code(400);
    exit(400);
}
if(isset($_GET["y"])){
    $y = $_GET["y"];
    $y = substr($y,0,6);
}else{
    echo "Error:400";
    http_response_code(400);
    exit(400);
}
if(isset($_GET["R"])){
    $r = $_GET["R"];
}else{
    echo "Error:400";
    http_response_code(400);
    exit(400);
}

//Hit check functions
global $message;
function checkCoordinates($x, $y, $r) {
    return (($x >= -$r) && ($x <= 0) && ($y <= 0) && ($y >= -$r / 2)) ||
        (($x >= 0) && ($y <= 0) && ($y >= 2 * $x - $r)) ||
        ((($x ** 2 + $y ** 2) <= ($r ** 2)) && ($x >= 0) && ($y >= 0));
}

 function hitInfo($x, $y, $r)
{
    if (checkCoordinates($x, $y, $r)) {
        $message = "Точка попала";
    } else {
        $message = "Точка не попала";
    }
    return $message;
}

function checkData($x, $y, $r) {
    return in_array($x, array(-4,-3,-2, -1, 0,1,2,3,4)) &&
        is_numeric($y) && ($y > -5 && $y < 3) &&
        in_array($r, array(1, 2, 3, 4, 5));
}
function isReqCorrect($bool){
    if(!$bool){
        echo "Error:400";
        http_response_code(400);
        exit(400);
    }

}
isReqCorrect(checkData($x,$y,$r));
$message = hitInfo($x, $y, $r);
//получаем время окончания работы скрипта
$finish = microtime(true);
//высчитываем время работы (разницу) и округляем
$timeWork = $finish - $start;
$timeWork = number_format(round($timeWork, 7),6);
$_SESSION["tableRW"][] = "<tr>
	  <td>$x</td>
	  <td>$y</td>
	  <td>$r</td>
	  <td>$message</td>
	  <td>$timeWork</td>
	  <td>$now</td>";
echo end($_SESSION['tableRW']);




