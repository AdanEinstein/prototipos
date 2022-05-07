<?php
include_once "includes/header.php";
require "banco/conexBanco.php";
session_start();
$conexao = new DAO();
if (isset($_SESSION["resposta"])) {
    $resposta = $_SESSION["resposta"];
    echo '<div class="alert alert-info alert-dismissible fade show position-absolute" style="right: 10px; top: 10px;" role="alert">' .
        $resposta
        . '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>';
    unset($_SESSION["resposta"]);
}
$venda = 0;
$total = 0;
if (!isset($_SESSION["vendaid"])) {
    $sql = "SELECT MAX(id_venda) as id FROM pdv_vendas";
    $venda = $conexao->select($sql, null, true)->fetch(PDO::FETCH_ASSOC)["id"];
    if (empty($venda)) {
        $venda = 1;
    } else {
        $sql = "SELECT * FROM pdv_total WHERE id_venda = '$venda'";
        $existe = $conexao->select($sql, null, true);
        if ($existe->rowCount() > 0) {
            $venda += 1;
        }
    }
} else {
    $venda = $_SESSION["vendaid"];
    unset($_SESSION["vendaid"]);
}
?>
<?php if (isset($_SESSION["usuario"])):
    include_once "includes/nav.php";
    ?>
    <main class="bg-light bg-opacity-75 p-md-3 p-2 m-md-3 my-3">
        <h2 class="titulo" style="font-weight: bold">
            Pronto para vender? Vamos lá!
        </h2>
        <hr>
        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link <?php print(isset($_SESSION["relatorio"]) ? "" : "active") ?>"
                        id="pill-lista-vendas" data-bs-toggle="pill"
                        data-bs-target="#pills-lista" type="button" role="tab" aria-controls="pills-lista"
                >Lista de Compras
                </button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link <?php print(isset($_SESSION["relatorio"]) ? "active" : "") ?>"
                        id="pill-total-vendas" data-bs-toggle="pill" data-bs-target="#pills-total"
                        type="button" role="tab" aria-controls="pills-total">Total de Vendas
                </button>
            </li>
        </ul>
        <div class="tab-content bg-dark text-white p-3" id="pills-tabContent">
            <div class="tab-pane fade <?php print(isset($_SESSION["relatorio"]) ? "" : "show active") ?>"
                 id="pills-lista" role="tabpanel" aria-labelledby="pill-lista-vendas">
                <?php include_once "includes/layoutVender.php" ?>
            </div>
            <div class="tab-pane fade <?php print(isset($_SESSION["relatorio"]) ? "show active" : "") ?>"
                 id="pills-total" role="tabpanel" aria-labelledby="pill-total-vendas">
                <?php include_once "includes/layoutTotal.php" ?>
            </div>
        </div>
    </main>
    <!--Modal-->
    <div class="modal fade" id="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Deletar</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="actions/actiondeletaritem.php" method="post">
                    <div class="modal-body">
                        <div class="mb-3">
                            <input type="hidden" name="idvenda" value="<?php print($venda) ?>">
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
    <!--Modal2-->
    <div class="modal fade" id="modal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Parabéns pela venda!</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="actions/actionvender.php" method="post">
                    <div class="modal-body">
                        <div class="mb-3">
                            <input class="d-none" type="text" id="vendaid" name="vendaid" readonly>
                            <input class="d-none" type="text" name="total" value="<?php print($total)?>" readonly>
                            <h6 class="d-flex justify-content-center">Total:
                                <span id="total" style="margin-left: 10px"
                                      class="text-success"></span>
                            </h6>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-success">Confirmar Venda</button>
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

include_once "includes/footer.php";
?>
