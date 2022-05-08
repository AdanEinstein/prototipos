<?php
session_start();
require "../banco/conexBanco.php";
$conexao = new DAO();
if (isset($_POST["email"]) && !empty($_POST["email"])) {
    $user = $_POST["email"];
    $sql = "SELECT * FROM pdv_usuarios WHERE login = '$user'";
    $result = $conexao->select($sql, null, true);
    if ($result->rowCount() > 0) {
        $loginid = $result->fetch(PDO::FETCH_ASSOC)["id"];
        $from = $result->fetch(PDO::FETCH_ASSOC)["login"];
        $message = rand();
        $headers = "From: " . $from;
        $_SESSION["resposta"] = "Enviamos uma senha nova para seu e-mail!";
        $envio = mail("pdv.sistema2022@gmail.com", "Senha gerada para você, redefinir sua senha!", $message, $headers);
        if (!$envio) {
            $sql = "UPDATE pdv_usuarios SET senha = :senha WHERE id = :id";
            $params = [":senha" => $message, ":id" => $loginid];
            if ($conexao->executeSQL($sql, $params)) {
                $_SESSION["loginid"] = $loginid;
                header("Location: ../redefinirSenha.php");
            } else {
                $_SESSION["resposta"] = "Erro para atualizar a senha para senha gerada";
                header("Location: ../recuperarSenha.php");
            }
        } else {
            $_SESSION["resposta"] = "Erro ao envia o e-mail";
            header("Location: ../recuperarSenha.php");
        }
    } else {
        $_SESSION["resposta"] = "E-mail não existe na nossa base de dados!";
        header("Location: ../recuperarSenha.php");
    }
} else {
    $_SESSION["resposta"] = "Preencha o campo e-mail!";
    header("Location: ../recuperarSenha.php");
}