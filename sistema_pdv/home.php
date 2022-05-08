<?php
include_once "includes/header.php";
session_start();
?>
<?php if (isset($_SESSION["usuario"])):
    include_once "includes/nav.php";
    ?>
    <main class="bg-light bg-opacity-75 p-3 m-5">
        <h2>Bem vindo ao seu sistema de Vendas!</h2>
        <p>Para começar a vender você deve cadastrar os seus produtos clique em <span>Cadastrar Produtos</span></p>
    </main>
<?php elseif (isset($_SESSION["adm"])):
    include_once "includes/nav.php";
    ?>
    <main class="bg-light bg-opacity-75 p-3 m-5">
        <h2>Bem vindo ao seu sistema Administrador!</h2>
        <p>Gerencie a lista de usuários atribuindo os perfis correspondentes ou excluindo!</p>
    </main>
<?php
else:
    include_once "includes/notfound.php";
endif;

include_once "includes/footer.php"
?>