<?php
session_start();
require "../banco/conexBanco.php";
$conexao = new DAO();
if (empty($_GET["idproduto"])) {
    $_SESSION["resposta"] = "Por favor! Selecione algum produto";
    header("Location: ../venderProdutos.php");
} elseif (empty($_GET["quant"])) {
    $_SESSION["resposta"] = "Por favor! Selecione a quantidade";
    header("Location: ../venderProdutos.php");
} else {
    $id_venda = $_GET["idvenda"];
    $id_produto = $_GET["idproduto"];
    $quant = $_GET["quant"];
    $consultaQuantidade = "SELECT * FROM pdv_produtos WHERE id = '$id_produto'";
    $quantidade = $conexao->select($consultaQuantidade, null, true)->fetch(PDO::FETCH_ASSOC);
    if ($quant > $quantidade["quantidade"]) {
        $_SESSION["resposta"] = "Quantidade maior do que o estoque de " . $quantidade['quantidade'] . " unidades!";
        $_SESSION["vendaid"] = $id_venda;
        header("Location: ../venderProdutos.php");
    } else {
        $consultaSQL = "SELECT * FROM pdv_vendas WHERE id_venda = '$id_venda' AND id_produto = '$id_produto'";
        $result = $conexao->select($consultaSQL, null, true);
        if ($result->rowCount() == 0) {
            $sql = "INSERT INTO pdv_vendas(id_venda, id_produto, quantidade) VALUES (:idVenda, :idProduto, :quantidade)";
            $params = [":idVenda" => $id_venda, ":idProduto" => $id_produto, ":quantidade"=>$quant];
            $conexao->executeSQL($sql, $params);
            $_SESSION["vendaid"] = $id_venda;
            $quantidadeRestante = $quantidade["quantidade"] - $quant;
            $sql = "UPDATE pdv_produtos SET quantidade = :quantidadeRestante WHERE id = :idProduto";
            $params = [":quantidadeRestante"=>$quantidadeRestante, ":idProduto"=>$id_produto];
            $conexao->executeSQL($sql, $params);
            header("Location: ../venderProdutos.php");
        } else {
            $_SESSION["resposta"] = "Produto já foi selecionado!";
            $_SESSION["vendaid"] = $id_venda;
            header("Location: ../venderProdutos.php");
        }
    }
}
