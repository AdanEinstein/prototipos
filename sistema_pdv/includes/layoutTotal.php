<?php print(isset($_SESSION["relatorio"]) ? "" : "<h5 class='fw-bolder'>Lista das vendas de hoje...</h5><hr>")?>
<form class="d-flex flex-row mb-2" action="actions/actiondatarelatorio.php" method="post">
    <div class="input-group mx-2">
        <span class="input-group-text">Data inicial:</span>
        <input type="date" class="form-control" name="datainicial" value="<?php print(isset($_SESSION["relatorio"]) ? $_SESSION["relatorio"] : date('Y-m-d'))?>">
    </div>
    <button type="submit" class="btn btn-primary w-25">Buscar</button>
</form>
<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">Data</th>
        <?php print(isset($_SESSION["relatorio"]) ? "<th scope=\"col\">Valor Total</th>" : "<th scope=\"col\">Valor</th>")?>

    </tr>
    </thead>
    <tbody>
    <?php
    if (isset($_SESSION["relatorio"])):
        $dataInicial = $_SESSION["relatorio"];
        $sql = "SELECT tot.data_venda, SUM(tot.total) as total FROM pdv_total tot WHERE tot.data_venda >= '$dataInicial' GROUP BY tot.data_venda ORDER BY tot.data_venda DESC";
        /** @var TYPE_NAME $conexao */
        $relatorio = $conexao->select($sql, null, true);
        while ($row = $relatorio->fetch(PDO::FETCH_ASSOC)):
            ?>
            <tr>
                <td><?php print(date_format(date_create($row["data_venda"]), 'd/m/Y')) ?></td>
                <td><?php print("R$ " . str_replace(".", ",", $row["total"])) ?></td>
            </tr>
        <?php
        endwhile;
        unset($_SESSION["relatorio"]);
    else:
        $sql = "SELECT * FROM pdv_total tot WHERE tot.data_venda = CURRENT_DATE() GROUP BY tot.id_venda ORDER BY tot.data_venda DESC";
        /** @var TYPE_NAME $conexao */
        $dados = $conexao->select($sql, null, true);
        while ($row = $dados->fetch(PDO::FETCH_ASSOC)):
            ?>
            <tr>
                <td><?php print(date_format(date_create($row["data_venda"]), 'd/m/Y')) ?></td>
                <td><?php print("R$ " . str_replace(".", ",", $row["total"])) ?></td>
            </tr>
        <?php
        endwhile;
    endif;
    ?>
    </tbody>
</table>