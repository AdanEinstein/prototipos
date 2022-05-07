<?php
session_start();
require "../banco/conexBanco.php";
$conexao = new DAO();

$id_venda = $_POST["idvenda"];
$id_produto = $_POST["id"];

$consultaQuantidade = "SELECT * FROM pdv_produtos WHERE id = '$id_produto'";
$quantidadeRestante = $conexao->select($consultaQuantidade, null, true)->fetch(PDO::FETCH_ASSOC);

$sql = "SELECT * FROM pdv_vendas WHERE id_venda = '$id_venda' AND id_produto = '$id_produto'";
$quantidadeVendida = $conexao->select($sql, null, true)->fetch(PDO::FETCH_ASSOC);

$quantidadeRestaurada = $quantidadeVendida["quantidade"] + $quantidadeRestante["quantidade"];
$sql = "UPDATE pdv_produtos SET quantidade = :quantidadeRestaurada WHERE id = :idProduto";
$params = [":quantidadeRestaurada"=>$quantidadeRestaurada, ":idProduto"=>$id_produto];
$conexao->executeSQL($sql, $params);

$sql = "DELETE FROM pdv_vendas WHERE id_venda = :idvenda AND id_produto = :idproduto";
$params = [":idvenda"=>$id_venda, ":idproduto"=>$id_produto];

if($conexao->executeSQL($sql, $params)){
    $_SESSION["resposta"] = "Produto excluído!";
    header("Location: ../venderProdutos.php");
} else {
    $_SESSION["resposta"] = "Erro de exclusão do produto!";
    header("Location: ../venderProdutos.php");
}