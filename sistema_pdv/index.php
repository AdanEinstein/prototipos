<?php
include_once "includes/header.php";
?>
<div class="container-fluid">
    <div class="row">
        <div class="col"></div>
        <div class="col-md-6 col-12 d-flex flex-column my-4 py-3 align-items-center"  style="background: rgba(204,204,204,0.63); border-radius: 10px; ">
            <h2 class="text-center text-white fw-bolder mb-5">Faça o seu login!</h2>
            <form action="actions/entrar.php" method="post" class="w-75">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="user" name="user">
                    <label for="user">User</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="password" name="password">
                    <label for="password">Password</label>
                </div>
                <?php
                session_start();
                if (isset($_SESSION["resposta"])) {
                    $resposta = $_SESSION["resposta"];
                    echo '<div class="alert alert-primary alert-dismissible fade show" role="alert">' .
                        $resposta
                        . '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>';
                    unset($_SESSION["resposta"]);
                }
                session_unset();
                ?>
                <div class="d-flex justify-content-between">
                    <a href="cadastroUser.php" class="btn btn-outline-light bg-opacity-25 me-2 w-25 d-lg-block d-none">Faça
                        seu cadastro!</a>
                    <a href="cadastroUser.php" class="btn btn-outline-light bg-opacity-25 me-2 w-50 d-lg-none d-block">Cadastre-se</a>
                    <button class="btn bg-primary text-white w-25" type="submit">Entrar</button>
                </div>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<?php
include_once "includes/footer.php"
?>
