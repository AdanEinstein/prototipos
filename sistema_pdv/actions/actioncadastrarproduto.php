<?php
session_start();
require_once '../banco/conexBanco.php';
$descricao = $_POST['descricao'];
$preco = $_POST['preco'];
$quantidade = $_POST['quantidade'];
$conexao = new DAO();

if (empty($descricao)) {
    $_SESSION['resposta'] = 'Preencha o campo descrição!';
    header("Location: ../cadastroProduto.php");
} elseif (empty($preco) || $preco <= 0) {
    $_SESSION['resposta'] = 'Preço inválido!';
    header("Location: ../cadastroProduto.php");
} elseif (empty($quantidade) || $quantidade <= 0) {
    $_SESSION['resposta'] = 'Quantidade inválida!';
    header("Location: ../cadastroProduto.php");
} else {
    $consultaSQL = "SELECT * FROM pdv_produtos WHERE descricao = '$descricao'";
    $result = $conexao->select($consultaSQL, null, true);
    if ($result->rowCount() == 0) {
        $sql = "INSERT INTO pdv_produtos VALUES (DEFAULT, :descricao, :preco, :quantidade)";
        $formatarQuantidade = str_replace(",", ".", $quantidade);
        $formatarPreco = str_replace(",", ".", $preco);
        $params = [":descricao" => $descricao, ":preco" => $formatarPreco, ":quantidade" => $formatarQuantidade];
        if ($conexao->executeSQL($sql, $params)) {
            $_SESSION['resposta'] = 'Produto cadastrado com sucesso!';
            header("Location: ../listaDeProdutos.php");
        } else {
            $_SESSION['resposta'] = 'Há algum valor inválido!';
            header("Location: ../cadastroProduto.php");
        }
    } else {
        $_SESSION['resposta'] = 'Produto já existe!';
        header("Location: ../cadastroProduto.php");
    }
}