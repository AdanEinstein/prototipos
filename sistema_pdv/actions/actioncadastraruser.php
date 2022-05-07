<?php
session_start();
require_once '../banco/conexBanco.php';
$user = $_POST['user'];
$password = $_POST['password'];
$confirmPassword = $_POST['confirmPassword'];

if (empty($user)) {
    $_SESSION['resposta'] = 'Preencha o campo user!';
    header("Location: ../cadastroUser.php");
} elseif (empty($password)) {
    $_SESSION['resposta'] = 'Preencha o campo password!';
    header("Location: ../cadastroUser.php");
} elseif ($password != $confirmPassword) {
    $_SESSION['resposta'] = 'Senhas não correspondentes!';
    header("Location: ../cadastroUser.php");
} else {
    $sql = "INSERT INTO pdv_usuarios VALUES (DEFAULT, :user, :password, :perfil)";
    $params = [":user"=>$user, ":password"=>$password, ":perfil"=>'padrão'];
    $conexao = new DAO();
    if ($conexao->executeSQL($sql, $params)) {
        $_SESSION['resposta'] = 'Usuário cadastrado com sucesso!';
        header("Location: ../index.php");
    }
}