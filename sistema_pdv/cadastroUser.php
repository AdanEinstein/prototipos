<?php
include_once "includes/header.php";
session_start();
?>
    <div class="container-fluid">
        <div class="row">
            <div class="col"></div>
            <div class="col-md-6 col-12 d-flex flex-column my-4 py-3 align-items-center"
                 style="background: rgba(204,204,204,0.38); border-radius: 10px; ">
                <h2 class="text-center text-white mb-5 fw-bolder">Cadastre-se aqui!</h2>
                <form action="actions/actioncadastraruser.php" method="post" class="w-75">
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="user" name="user" aria-describedby="aviso">
                        <label for="user">User</label>
                        <div id="aviso" class="form-text text-white">Dica: Digite o seu e-mail para que seja possível recuperar sua senha caso esqueça!</div>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="password" name="password">
                        <label for="password">Password</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="confirPassword" name="confirmPassword">
                        <label for="confirPassword">Confirm Password</label>
                    </div>
                    <?php
                    if (isset($_SESSION["resposta"])) {
                        $resposta = $_SESSION["resposta"];
                        echo '<br><div class="alert alert-primary" role="alert">' .
                            $resposta
                            . '</div><br>';
                        unset($_SESSION["resposta"]);
                    }
                    ?>
                    <div class="d-flex justify-content-end">
                        <a style="width: 70%" href="index.php" class="btn bg-danger me-2 text-white">Voltar</a>
                        <button style="width: 70%" class="btn bg-success text-white" type="submit">Cadastrar</button>
                    </div>
                </form>
            </div>
            <div class="col"></div>
        </div>
    </div>
<?php
include_once "includes/footer.php"
?>