<?php
include_once "includes/header.php"
?>

<div class="row my-4 mx-2">
    <div class="col"></div>
    <div class="col-md-6 col-12">
        <div class="alert alert-danger" role="alert">
            <h4 class="alert-heading">Permissão negada!</h4>
            <p>Infelizmente você não tem permissão para acessar esta URL!</p>
            <hr>
            <p class="mb-0">Para prosseguir efetue o login corretamente, obrigado!</p>
        </div>
    </div>
    <div class="col"></div>
</div>
<div class="row">
    <div class="col"></div>
    <div class="m-2 col-lg-8 col-12 d-flex justify-content-center align-items-center">
        <a href="index.php" class="btn btn-warning btn-lg">
            Página Inicial
        </a>
    </div>
    <div class="col"></div>
</div>

<?php
include_once "includes/footer.php"
?>
