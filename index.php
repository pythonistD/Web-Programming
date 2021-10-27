<?php

use JetBrains\PhpStorm\Pure;

session_start();
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
#[Pure] function checkHit($x, $y, $r): bool
{
    return checkTriangle($x, $y, $r) ||
        checkSq($x, $y, $r) || checkSector($x, $y, $r);
}

#[Pure] function checkTriangle($x, $y, $r): bool
{
    $S1 = squareArea(0, 0, $x, $y, $r / 2, 0);
    $S2 = squareArea(0, $r / 2, $x, $y, $r / 2, 0);
    $S3 = squareArea(0, 0, $x, $y, $r / 2, 0);
    $S = squareArea(0, 0, 0, $r / 2, $r / 2, 0);
    $Sum = $S1 + $S2 + $S3;
    return !($Sum > $S);

}

#[Pure] function squareArea($x1, $y1, $x2, $y2, $x3, $y3): float
{
    $a = module($x1, $y1, $x2, $y2);
    $b = module($x2, $y2, $x3, $y3);
    $c = module($x3, $y3, $x1, $y1);
    $p = $a + $b + $c;
    return sqrt($p * ($p - $a) * ($p - $b) * ($p - $c));
}

function module($x1, $y1, $x2, $y2): float
{
    return sqrt(($x2 - $x1) ^ 2 + ($y2 - $y1) ^ 2);
}

function checkSq($x, $y, $r): bool
{
    return $x >= 0 && $x <= $r &&
        $y <= 0 && $y >= -$r;
}

function checkSector($x, $y, $r): bool
{
    return $x <= 0 && $y >= 0 &&
        sqrt($x ^ 2 + $y ^ 2) <= $r;
}

#[Pure] function hitInfo($x, $y, $r): string
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

//получаем время окончания работы скрипта
$finish = microtime(true);
//высчитываем время работы (разницу) и округляем
$timeWork = $finish - $start;
$timeWork = round($timeWork, 7);
//заполняем переменную сессии для отображения всей таблицы
sessionNumber();
$message = hitInfo($x, $y, $r);
$sessionNumber = $_SESSION['count'];
$_SESSION['tableRw' . $sessionNumber] =
    "<tr>
	  <td>$x</td>
	  <td>$y</td>
	  <td>$r</td>
	  <td>$message</td>
	  <td>$timeWork</td>
	  <td>$now</td>
  </tr>";
echo "<table>
      <tr>
    	  <td>X</td>
    	  <td>Y</td>
    	  <td>R</td>
    	  <td>Result</td>
    	  <td>Work time</td>
    	  <td>Current time</td>
      </tr>
    </table>";
foreach ($_SESSION as $value) {
    echo $value;
}
