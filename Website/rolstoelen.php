<?php

use DaanKoster\Rolstoel;

require_once __DIR__ . '/lib/Rolstoel.php';
$rolstoelClass = new Rolstoel();
session_write_close();


$rolstoelen = $rolstoelClass->getRolstoel();

header('Content-Type: application/json; charset=utf-8');
echo json_encode($rolstoelen);
