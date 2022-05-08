<nav class="navbar navbar-expand-lg navbar-dark"
     style="background: linear-gradient(to right, rgb(170, 7, 107), rgb(97, 4, 95)); box-shadow: 5px 5px 15px -3px #000000;">
    <div class="container-fluid">
        <a class="navbar-brand" href="home.php">
            <img src="./images/basket-fill.svg" alt="" width="30" height="24"
                 class="d-inline-block align-text-top">
            Home
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <?php if(isset($_SESSION["usuario"])):?>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="listaDeProdutos.php" class="nav-link my-1">Listar produtos</a>
                </li>
                <li class="nav-item">
                    <a href="cadastroProduto.php" class="nav-link my-1">Cadastrar Produto</a>
                </li>
                <li class="nav-item">
                    <a href="venderProdutos.php" class="nav-link my-1">Vender</a>
                </li>
                <li class="nav-item d-lg-none">
                    <a href="index.php" class="btn btn-danger fw-lighter w-100 my-1">SAIR</a>
                </li>
            </ul>
        </div>
        <?php elseif (isset($_SESSION["adm"])):?>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="listaUsers.php" class="nav-link my-1">Lista de usuários</a>
                </li>
                <li class="nav-item d-lg-none">
                    <a href="index.php" class="btn btn-danger fw-lighter w-100 my-1">SAIR</a>
                </li>
            </ul>
        </div>
        <?php endif;?>
        <div class="d-lg-block d-none">
            <a class="btn btn-danger fw-lighter" style="width: 150px" href="index.php">SAIR</a>
        </div>
    </div>
</nav>