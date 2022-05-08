<?php
session_start();
require_once '../banco/conexBanco.php';
$conexao = new DAO();
$id = $_POST['id'];

$sql = "DELETE FROM pdv_usuarios WHERE id = :id";
$params = [":id"=>$id];
if ($conexao->executeSQL($sql, $params)) {
    $_SESSION['resposta'] = 'Usuário deletado com sucesso!';
} else {
    $_SESSION['resposta'] = 'Não foi possível deletar este usuário!';
}
header("Location: ../listaUsers.php");
