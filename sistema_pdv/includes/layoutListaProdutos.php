<h2 class="titulo" style="font-weight: bold">
    Lista de Produtos
</h2>
<hr>
<table class="table table-dark">
    <thead>
    <tr>
        <th class="d-md-table-cell d-none" scope="col">ID</th>
        <th scope="col">Descrição</th>
        <th class="d-md-table-cell d-none" scope="col">Preço</th>
        <th scope="col">Quantidade</th>
        <th scope="col">Ações</th>
    </tr>
    </thead>
    <tbody>
    <?php
    $sql = "SELECT * FROM pdv_produtos";
    try {
        /** @var TYPE_NAME $conexao */
        $dados = $conexao->select($sql, null, true);
    } catch (Exception $e) {
        header("Location: home.php");
    }
    if ($dados->rowCount() > 0):
        while ($row = $dados->fetch(PDO::FETCH_ASSOC)):
            ?>
            <tr>
                <th class="d-md-table-cell d-none" scope="row"><?php print($row["id"]) ?></th>
                <td><?php print($row["descricao"]) ?></td>
                <td class="d-md-table-cell d-none"><?php print("R$ " . str_replace(".", ",", $row["preco"])) ?></td>
                <td><?php print($row["quantidade"]) ?></td>
                <td>
                    <a class="btn btn-warning
                        btn-sm" href="<?php print("alterarProduto.php?id=" . $row["id"]) ?>">
                        <img src="images/pencil-square.svg" width="16" height="16" alt="edit">
                    </a>
                    <a class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#modal"
                       data-bs-whatever="<?php print($row["id"])?>" data-bs-whatever2="<?php print($row["descricao"])?>">
                        <img src="images/trash-fill.svg" width="16" height="16" alt="delete">
                    </a>
                </td>
            </tr>
        <?php
        endwhile;
    endif;
    ?>
    </tbody>
</table>