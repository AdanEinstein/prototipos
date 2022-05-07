<div class="input-group">
    <form class="d-flex flex-row w-100" action="actions/actioninserirproduto.php" method="get">
        <input type="hidden" value="<?php /** @var TYPE_NAME $venda */
        print($venda) ?>" name="idvenda">
        <select class="form-select w-100" id="inputProduto" name="idproduto">
            <option selected value="">Escolha....</option>
            <?php
            $sql = "SELECT * FROM pdv_produtos";
            try {
                /** @var TYPE_NAME $conexao */
                $dados = $conexao->select($sql, null, true);
                if ($dados->rowCount() > 0) :
                    while ($row = $dados->fetch(PDO::FETCH_ASSOC)) :
                        ?>
                        <option value="<?php print($row["id"]) ?>"><?php print($row["descricao"] . " -> R$ " . str_replace(".", ",", $row["preco"])) ?></option>
                    <?php
                    endwhile;
                endif;
            } catch (Exception $e) {
                header("Location: home.php");
            }
            ?>
        </select>
        <input class="form-control w-25 mx-2" type="number" min="1" placeholder="Quantidade"
               name="quant">
        <button class="btn btn-primary" type="submit">Adicionar</button>
    </form>
</div>
<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">Descrição</th>
        <th class="d-md-table-cell d-none" scope="col">Preço</th>
        <th scope="col">Quantidade</th>
        <th scope="col">Ações</th>
    </tr>
    </thead>
    <tbody>
    <?php
    if (!empty($venda)) :
    $sql = "SELECT prod.id, prod.descricao, prod.preco, vend.quantidade FROM pdv_produtos prod INNER JOIN pdv_vendas vend ON prod.id = vend.id_produto WHERE vend.id_venda = '$venda'";
    $dados = $conexao->select($sql, null, true);
    while ($row = $dados->fetch(PDO::FETCH_ASSOC)) :
        ?>
        <tr>
            <td><?php print($row["descricao"]) ?></td>
            <td class="d-md-table-cell d-none"><?php print("R$ " . str_replace(".", ",", $row["preco"])) ?></td>
            <td><?php print($row["quantidade"]) ?></td>
            <td>
                <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#modal"
                        data-bs-whatever="<?php print($row["id"]) ?>"
                        data-bs-whatever2="<?php print($row["descricao"]) ?>">
                    <img src="images/trash-fill.svg" width="16" height="16" alt="delete">
                </button>
            </td>
        </tr>
        <?php $total += (float)($row["preco"] * $row["quantidade"]); ?>
    <?php endwhile; ?>
    </tbody>
    <?php endif; ?>
</table>
<h3 class="d-flex justify-content-end">Total:
    <span style="margin-left: 10px"
          class="text-success"><?php print("R$ " . str_replace(".", ",", $total)) ?></span>
</h3>
<button class="btn btn-success btn-lg position-absolute" data-bs-toggle="modal" data-bs-target="#modal2"
        data-bs-whatever="<?php print($venda) ?>" data-bs-whatever2="<?php print($total) ?>" style="z-index: 10; bottom: 50px; right: 10px;">
    Vender!
</button>