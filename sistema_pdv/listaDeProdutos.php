<?php
include_once "includes/header.php";

require_once 'banco/conexBanco.php';
$conexao = new DAO();
session_start();
if (isset($_SESSION["resposta"])) {
    $resposta = $_SESSION["resposta"];
    echo '<div class="alert alert-info alert-dismissible fade show position-absolute" style="right: 10px; top: 10px;" role="alert">' .
        $resposta
        . '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>';
    unset($_SESSION["resposta"]);
}
?>
<?php if (isset($_SESSION["usuario"])):
    include_once "includes/nav.php";
    ?>
<main class="bg-light bg-opacity-75 p-md-3 p-2 m-md-3 my-3">
    <?php include_once "includes/layoutListaProdutos.php" ?>
</main>
<!--MODAL-->
<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Deletar</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="actions/actiondeletarproduto.php" method="post">
                <div class="modal-body">
                    <div class="mb-3">
                        <input class="d-none" type="text" id="idproduto" name="id" readonly>
                        Deseja mesmo deletar o produto: "<span id="descricaoProduto"></span>"
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-danger">Deletar</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="style/js/resources.js"></script>
<?php
else:
    include_once "includes/notfound.php";
endif;

include_once "includes/footer.php"
?>


