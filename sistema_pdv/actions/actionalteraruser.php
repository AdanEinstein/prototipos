<?php
session_start();
require_once '../banco/conexBanco.php';
$id = $_POST['id'];
$login = $_POST['login'];
$perfil = $_POST['perfil'];
$conexao = new DAO();

if (empty($_POST["login"])) {
    $_SESSION['resposta'] = 'Login inválida!';
    header("Location: ../alterarUser.php?id=" . $id);
} elseif (empty($_POST["perfil"])) {
    $_SESSION['resposta'] = 'O perfil não foi selecionado!';
    header("Location: ../alterarUser.php?id=" . $id);
} else {
    $sql = "UPDATE pdv_usuarios SET perfil = :perfil WHERE id = :id";
    $params = [":perfil"=>$perfil, ":id"=>$id];
    if($conexao->executeSQL($sql, $params)){
        $_SESSION['resposta'] = 'Usuario atualizado com sucesso!';
    } else {
        $_SESSION['resposta'] = 'Erro de atualização!';
    }
    header("Location: ../listaUsers.php");
}