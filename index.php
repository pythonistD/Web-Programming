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
$x = $_GET["x"];
$y = $_GET["y"];
$r = $_GET["R"];
//Hit check functions
global $message;
function checkHit($x, $y, $r)
{
    return checkTriangle($x, $y, $r) ||
        checkSq($x, $y, $r) || checkSector($x, $y, $r);
}

function checkTriangle($x, $y, $r)
{
    $S1 = squareArea(0, 0, $x, $y, $r / 2, 0);
    $S2 = squareArea(0, $r / 2, $x, $y, $r / 2, 0);
    $S3 = squareArea(0, 0, $x, $y, $r / 2, 0);
    $S = squareArea(0, 0, 0, $r / 2, $r / 2, 0);
    $Sum = $S1 + $S2 + $S3;
    return !($Sum > $S);
}

function squareArea($x1, $y1, $x2, $y2, $x3, $y3)
{
    $a = module($x1, $y1, $x2, $y2);
    $b = module($x2, $y2, $x3, $y3);
    $c = module($x3, $y3, $x1, $y1);
    $p = $a + $b + $c;
    return sqrt($p * ($p - $a) * ($p - $b) * ($p - $c));
}

function module($x1, $y1, $x2, $y2)
{
    return sqrt(($x2 - $x1) ^ 2 + ($y2 - $y1) ^ 2);
}

function checkSq($x, $y, $r)
{
    return $x >= 0 && $x <= $r &&
        $y <= 0 && $y >= -$r;
}

function checkSector($x, $y, $r)
{
    return $x <= 0 && $y >= 0 &&
        sqrt($x ^ 2 + $y ^ 2) <= $r;
}

 function hitInfo($x, $y, $r)
{
    if (checkHit($x, $y, $r)) {
        $message = "Точка попала";
    } else {
        $message = "Точка не попала";
    }
    return $message;
}

function sessionNumber()
{
    if (!isset($_SESSION['count'])) {
        $_SESSION['count'] = 1;
    } else {
        ++$_SESSION['count'];
    }

}
function checkData($x, $y, $r) {
    return in_array($x, array(-4,-3,-2, -1, 0,1,2,3,4)) &&
        is_numeric($y) && ($y >= -5 && $y <= 3) &&
        in_array($r, array(1, 2, 3, 4, 5));
}
function isReqCorrect($bool){
    if(!$bool){
        echo "Error:400";
        exit(400);
    }

}
isReqCorrect(checkData($x,$y,$r));
//получаем время окончания работы скрипта
$finish = microtime(true);
//высчитываем время работы (разницу) и округляем
$timeWork = $finish - $start;
$timeWork = round($timeWork, 7);
$message = hitInfo($x, $y, $r);
$_SESSION["tableRW"][] = "<tr>
	  <td>$x</td>
	  <td>$y</td>
	  <td>$r</td>
	  <td>$message</td>
	  <td>$timeWork</td>
	  <td>$now</td>";
echo end($_SESSION['tableRW']);




