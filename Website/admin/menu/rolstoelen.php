<?php

use Phppot\Rolstoel;

session_start();
if (! empty($_SESSION["name"])) {
    $name = $_SESSION["name"];
} else {
    session_unset();
    $url = "../../index.php";
    header("Location: $url");
}

require_once __DIR__ . '/../../lib/Rolstoel.php';
$rolstoelClass = new Rolstoel();
session_write_close();


$rolstoelen = $rolstoelClass->getRolstoel();

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Bootstrap 5 Side Bar Navigation</title>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
        crossorigin="anonymous"
    />
    <link
        href="https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css"
        rel="stylesheet"
    />
    <link rel="stylesheet" href="../style.css" />
</head>

<body>
<div
    class="side-navbar active-nav d-flex justify-content-between flex-wrap flex-column"
    id="sidebar"
>
    <ul class="nav flex-column text-white w-100">
        <a href="#" class="nav-link h3 text-white my-2">
            Nova Rolstoelen <br>Admin
        </a>
        <li href="#" class="nav-link">
            <i class="bx bxs-dashboard"></i>
            <a class="mx-2" href="#">Home</a>
        </li>
        <li href="#" class="nav-link">
            <i class="bx bx-user-check"></i>
            <a class="mx-2" href="../menu/gebruikers.php">Gebruikers</a>
        </li>
        <li href="#" class="nav-link">
            <i class="bx bx-conversation"></i>
            <a class="mx-2" href="../menu/rolstoelen.php">Rolstoelen</a>
        </li>
    </ul>


</div>

<!-- Main Wrapper -->
<div class="p-1 my-container active-cont">

    <div class="m-header container">

        <div class="row">
            <div class="pt-5 row row-cols-1 row-cols-md-4 text-center">

                <?php foreach ($rolstoelen as $rolstoel): ?>

                    <div class="col">
                        <div class="card mb-4 file" style="">
                            <div class="card-header bg-dark text-white">Rolstoel van <?= $rolstoel["naam"] ?></div>
                            <div class="card-body text-black">
                                <h5 class="card-text text-black"><?= $rolstoel["naam"] ?></h5>
                                <p class="card-text text-black">
                                    Opmerking: <?= $rolstoel["eigenschap"]?>
                                </p>
                                <p class="card-text text-black">
                                    Tag: <?= $rolstoel["tag"]?>
                                </p>
                                <button class="btn btn-primary" data-id="<?= $rolstoel["id"]?>">Bewerk Rolstoel</button>
                            </div>
                        </div>
                    </div>

                <?php endforeach; ?>
            </div>
        </div>


    </div>

    <!-- bootstrap js -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"
    ></script>
    <!-- custom js -->
    <script>
        var menu_btn = document.querySelector("#menu-btn");
        var sidebar = document.querySelector("#sidebar");
        var container = document.querySelector(".my-container");
        menu_btn.addEventListener("click", () => {
            sidebar.classList.toggle("active-nav");
            container.classList.toggle("active-cont");
        });
    </script>
</body>
</html>

