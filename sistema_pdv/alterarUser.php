<?php
include_once "includes/header.php";
session_start();
require 'banco/conexBanco.php';
$dados = 0;
if (isset($_GET['id'])){
    $id = $_GET['id'];
    $sql = "SELECT * FROM pdv_usuarios WHERE id = :id";
    $param = [":id" => $id];
    $conexao = new DAO();
    $dados = $conexao->select($sql, $param);
}
?>
<?php if (isset($_SESSION["adm"])):
    include_once "includes/nav.php";
    ?>
    <main>
        <div class="container d-flex align-items-center mt-5 flex-column p-3"
             style="background-color: rgba(204,204,204,0.63); border-radius: 10px;">
            <h2 class="text-center text-white mb-3 fw-bolder">Altere o perfil aqui!</h2>
            <form action="actions/actionalteraruser.php" method="post" class="w-75">
                <div class="form-floating mb-3">
                    <input type="hidden" class="form-control" id="id" value="<?php print($dados["id"])?>" name="id" readonly>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="descricao" value="<?php print($dados["login"])?>" name="login" readonly>
                    <label for="descricao">Login</label>
                </div>
                <div class="form-floating my-3">
                    <select class="form-select" name="perfil" id="perfil">
                        <option value="" selected>Escolha o perfil</option>
                        <option value="adm">Administrador</option>
                        <option value="padrão">Padrão</option>
                    </select>
                    <label for="perfil">Perfil</label>
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
                    <a class="btn bg-danger text-white w-50 mx-2" href="listaUsers.php">Cancelar</a>
                    <button class="btn bg-warning text-black w-50 mx-2" type="submit">Atualizar usuário</button>
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
