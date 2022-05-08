<?php
include_once "includes/header.php";
session_start();
?>
    <div class="container-fluid">
        <div class="row">
            <div class="col"></div>
            <div class="col-md-6 col-12 d-flex flex-column my-4 py-3 align-items-center"
                 style="background: rgba(204,204,204,0.38); border-radius: 10px; ">
                <h2 class="text-center text-white mb-5 fw-bolder">Recupere sua senha!</h2>
                <p class="text-white fw-normal fs-5 d-xxl-block d-none">Enviaremos uma senha nova para seu e-mail para que você possa redefini-la.</p>
                <p class="text-white fw-normal fs-5 d-xxl-none d-block">Enviaremos uma senha nova para seu e-mail.</p>
                <form action="actions/actionredefinirsenha.php" method="post" class="w-75">
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="user" name="email">
                        <label for="user">E-mail</label>
                    </div>
                    <?php
                    if (isset($_SESSION["resposta"])) {
                        $resposta = $_SESSION["resposta"];
                        echo '<br><div class="alert alert-primary" role="alert">'.
                            $resposta
                            . '</div><br>';
                        unset($_SESSION["resposta"]);
                    }
                    ?>
                    <div class="d-flex justify-content-end">
                        <a style="width: 70%" href="index.php" class="btn bg-danger me-2 text-white">Voltar</a>
                        <button style="width: 70%" class="btn bg-success text-white" type="submit">Enviar</button>
                    </div>
                </form>
            </div>
            <div class="col"></div>
        </div>
    </div>
<?php
session_unset();
include_once "includes/footer.php"
?>