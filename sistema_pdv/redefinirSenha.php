<?php
include_once "includes/header.php";
session_start();
require 'banco/conexBanco.php';
$dados = 0;
if (isset($_SESSION['loginid'])):
    $id = $_SESSION['loginid'];
    $sql = "SELECT * FROM pdv_usuarios WHERE id = :user";
    $param = [":user" => $id];
    $conexao = new DAO();
    $dados = $conexao->select($sql, $param);
?>
    <main>
        <div class="container d-flex align-items-center mt-5 flex-column p-3"
             style="background-color: rgba(204,204,204,0.63); border-radius: 10px;">
            <h2 class="text-center text-white mb-3 fw-bolder">Redefina sua senha aqui!</h2>
            <form action="actions/actionaltualizarsenha.php" method="post" class="w-75">
                <div class="form-floating mb-3">
                    <input type="hidden" class="form-control" id="id" value="<?php print($dados["id"])?>" name="id" readonly>
                </div>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="descricao" value="<?php print($dados["login"])?>" name="login" readonly>
                    <label for="login">User</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="senhagerada" name="senhagerada">
                    <label for="senhagerada">Senha gerada:</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="password" name="password">
                    <label for="password">Nova senha:</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="confirmpassword" name="confirmpassword">
                    <label for="confirmpassword">Confirmar senha:</label>
                </div>
                <?php
                if (isset($_SESSION["resposta"])) {
                    $resposta = $_SESSION["resposta"];
                    echo '<br><div class="alert alert-info" role="alert">'.
                        $resposta
                        .'</div><br>';
                    unset($_SESSION["resposta"]);
                }
                ?>

                <div class="d-flex justify-content-end mb-3">
                    <button class="btn bg-warning text-black w-100" type="submit">Redefinir senha</button>
                </div>
            </form>
        </div>
    </main>

<?php
else:
    include_once "includes/notfound.php";
endif;

include_once "includes/footer.php"
?>
