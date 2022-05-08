<?php
session_start();
require "../banco/conexBanco.php";
$conexao = new DAO();
if (!isset($_POST["senhagerada"]) || empty($_POST["senhagerada"])){
    $_SESSION["resposta"] = "A senha gerada não foi indicada!";
    header("Location: ../redefinirSenha.php");
} elseif (!isset($_POST["password"]) || empty($_POST["password"])){
    $_SESSION["resposta"] = "A nova senha não foi indicada!";
    header("Location: ../redefinirSenha.php");
} elseif (!isset($_POST["confirmpassword"]) || empty($_POST["confirmpassword"])){
    $_SESSION["resposta"] = "A confirmação da nova senha não foi indicada!";
    header("Location: ../redefinirSenha.php");
} elseif ($_POST["password"] != $_POST["confirmpassword"]){
    $_SESSION["resposta"] = "As senhas não são correspondentes!";
    header("Location: ../redefinirSenha.php");
} else {
    $id = $_POST["id"];
    $sql = "SELECT * FROM pdv_usuarios WHERE id = '$id'";
    $result = $conexao->select($sql, null, true);
    $senhaGerada = $result->fetch(PDO::FETCH_ASSOC)["senha"];
    if($_POST["senhagerada"] == $senhaGerada){
        $novaSenha = $_POST["password"];
        $sql = "UPDATE pdv_usuarios SET senha = :senha WHERE id = :id";
        $params = [":senha"=>$novaSenha, ":id"=>$id];
        if($conexao->executeSQL($sql, $params)){
            $_SESSION["resposta"] = "Senha atualizada com sucesso!";
            header("Location: ../index.php");
        } else {
            $_SESSION["resposta"] = "Erro para cadastrar a nova senha!";
            header("Location: ../index.php");
        }
    } else {
        $_SESSION["resposta"] = "Senha gerada inválida!";
        header("Location: ../redefinirSenha.php");
    }
}