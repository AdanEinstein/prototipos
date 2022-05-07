<?php
session_start();
require_once '../banco/conexBanco.php';
$id = $_POST['id'];
$descricao = $_POST['descricao'];
$preco = $_POST['preco'];
$quantidade = $_POST['quantidade'];
$conexao = new DAO();

if (empty($descricao)) {
    $_SESSION['resposta'] = 'Descrição inválida!';
    header("Location: ../alterarProduto.php?id=" . $id);
} elseif (empty($preco) || $preco <= 0) {
    $_SESSION['resposta'] = 'Preço inválido!';
    header("Location: ../alterarProduto.php?id=" . $id);
} elseif (empty($quantidade) || $quantidade <= 0) {
    $_SESSION['resposta'] = 'Quantidade inválida!';
    header("Location: ../alterarProduto.php?id=" . $id);
} else {
    $consultaSQL = "SELECT * FROM pdv_produtos WHERE descricao LIKE '$descricao' AND id <> '$id'";
    $result = $conexao->select($consultaSQL, null, true);
    if ($result->rowCount() == 0) {
        $sql = "UPDATE pdv_produtos SET descricao = :descricao, preco = :preco, quantidade = :quantidade WHERE id = :id";
        $formatarQuantidade = str_replace(",", ".", $quantidade);
        $formatarPreco = str_replace(",", ".", $preco);
        $params = [":descricao" => $descricao, ":preco" => $formatarPreco, ":quantidade" => $formatarQuantidade, ":id" => $id];
        if ($conexao->executeSQL($sql, $params)) {
            $_SESSION['resposta'] = 'Produto atualizado com sucesso!';
            header("Location: ../listaDeProdutos.php");
        } else {
            $_SESSION['resposta'] = 'Há algum valor inválido!';
            header("Location: ../alterarProduto.php?id=" . $id);
        }
    } else {
        $_SESSION['resposta'] = 'Descriçao pertence a outro produto!';
        header("Location: ../alterarProduto.php?id=" . $id);
    }
}