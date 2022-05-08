<?php
require "../banco/conexBanco.php";
$conexao = new DAO();
session_start();
if(isset($_POST["datainicial"]) && !empty($_POST["datainicial"])){
    $dataInicial = $_POST["datainicial"];
    $_SESSION["relatorio"] = $dataInicial;
    header("Location: ../venderProdutos.php");
} else {
    $_SESSION["resposta"] = "Nenhuma data selecionada! Colocaremos a data atual.";
    $_SESSION["relatorio"] = date('Y-m-d');
    header("Location: ../venderProdutos.php");
}