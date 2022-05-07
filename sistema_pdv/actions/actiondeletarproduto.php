<?php
session_start();
require_once '../banco/conexBanco.php';
$conexao = new DAO();
$id = $_POST['id'];

$sql = "DELETE FROM pdv_produtos WHERE id = :id";
$params = [":id"=>$id];
if ($conexao->executeSQL($sql, $params)) {
    $_SESSION['resposta'] = 'Produto deletado com sucesso!';
    header("Location: ../listaDeProdutos.php");
} else {
    $_SESSION['resposta'] = 'Não foi possível deletar este produto!';
    header("Location: ../listaDeProdutos.php");
}
