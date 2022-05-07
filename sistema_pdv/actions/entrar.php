<?php
require_once '../banco/conexBanco.php';
session_start();

$login = $_POST["user"];
$senha = $_POST['password'];
if (empty($login)) {
    $_SESSION['resposta'] = "Preencha o campo USER!!!";
    header("Location: ../index.php");
} elseif (empty($senha)) {
    $_SESSION['resposta'] = "Preencha o campo PASSWORD!!!";
    header("Location: ../index.php");
} else {
    $sql = "SELECT * FROM pdv_usuarios WHERE login = :login AND senha = :senha";
    $conexao = new DAO();
    $dados = $conexao->select($sql, [":login" => $login, ":senha" => $senha]);

    if ($dados["login"] == $login and $dados["senha"] == $senha) {
        $usuario = array("login" => $dados['login'], "senha" => $dados['senha'], "perfil" => $dados['perfil']);
        $_SESSION['usuario'] = $usuario;
        header("Location: ../home.php");
    } else {
        $_SESSION['resposta'] = "Login ou senha inválidos";
        header("Location: ../index.php");
    }
}