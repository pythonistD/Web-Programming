<?php
session_start();
if (isset($_SESSION['tableRW'])) {
    foreach ($_SESSION["tableRW"] as $tableRow) {
        echo $tableRow;
    }
}