<?php

use DaanKoster\Rolstoel;

session_start();
if (! empty($_SESSION["name"])) {
    $name = $_SESSION["name"];
} else {
    session_unset();
    $url = "./index.php";
    header("Location: $url");

}

require_once __DIR__ . '/lib/Rolstoel.php';
$rolstoelClass = new Rolstoel();
session_write_close();


$rolstoelen = $rolstoelClass->getRolstoel();



?>
<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css"
	crossorigin="anonymous">
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<link href="vendor/fontawesome-free-5.15.3-web/css/all.css"
	rel="stylesheet">
<link href="assets/css/home.css" rel="stylesheet">
<style>
</style>
    <script src="assets/js/updater.js"></script>
</HEAD>

<BODY class="bg-image">
<nav class="navbar navbar-dark  bg-dark">
    <h1 class="mb-0 h1 mx-auto text-white">Nova College Rolstoelen</h1>
</nav>
<div class="m-header container">

	<div class="row">
		<div class="col-md-12 text-center mt-5">
			<h1>Welkom, <?php echo $name;?>!</h1>
		</div>

        <div class="pt-5 row row-cols-1 row-cols-md-4 text-center">

            <?php foreach ($rolstoelen as $rolstoel): ?>

                <div class="col">
                    <div class="card mb-4 file" style="">
                        <img class="card-img-top" src="assets/img/rolstoel.png" alt="Roelstoel">
                        <div class="card-header bg-dark text-white">Rolstoel van <?= $rolstoel["naam"] ?></div>
                        <div class="card-body text-black">
                            <h5 class="card-title text-black"><?= $rolstoel["naam"] ?></h5>
                            <p class="card-text text-black rolstoellocatie" data-id=<?= $rolstoel["id"] ?>>
                                Locatie: <?= $rolstoel["locatie"] ?>
                            </p>
                            <p class="card-text text-black">
                                Opmerking: <?= $rolstoel["eigenschap"]?>
                            </p>
                        </div>
                    </div>
                </div>

            <?php endforeach; ?>
        </div>
    </div>
</BODY>
</HTML>
