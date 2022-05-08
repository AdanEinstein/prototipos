<?php
use PHPMailer\PHPMailer\PHPMailer;
require '../vendor/autoload.php';
session_start();
require "../banco/conexBanco.php";
$conexao = new DAO();
if (isset($_POST["email"]) && !empty($_POST["email"])) {
    $user = $_POST["email"];
    $sql = "SELECT * FROM pdv_usuarios WHERE login = '$user'";
    $result = $conexao->select($sql, null, true);
    if ($result->rowCount() > 0) {
        $loginid = $result->fetch(PDO::FETCH_ASSOC)["id"];
        $message = rand();
        $_SESSION["resposta"] = "Enviamos uma senha nova para seu e-mail!";
        $mail = new PHPMailer;
        $mail->isSMTP();
        $mail->Host = 'smtp.hostinger.com';
        $mail->Port = 587;
        $mail->SMTPAuth = true;
        $mail->Username = 'adm@sistema-pdv.correctsoftware.net';
        $mail->Password = 'c=299792458C';
        $mail->setFrom('adm@sistema-pdv.correctsoftware.net', 'Sistema PDV');
        $mail->addAddress($user, 'Destinatário');
        if ($mail->addReplyTo($_POST['email'])) {
            $mail->CharSet = 'UTF-8';
            $mail->Encoding = 'base64';
            $mail->Subject = 'Redefinição da senha!';
            $mail->isHTML(false);
            $mail->Body = "Senha gerada: \"$message\" \nAtt. Sistema PDV";
            if (!$mail->send()) {
                $_SESSION["resposta"] = "Erro ao envia o e-mail";
                header("Location: ../recuperarSenha.php");
            } else {
                $sql = "UPDATE pdv_usuarios SET senha = :senha WHERE id = :id";
                $params = [":senha" => $message, ":id" => $loginid];
                if ($conexao->executeSQL($sql, $params)) {
                    $_SESSION["loginid"] = $loginid;
                    header("Location: ../redefinirSenha.php");
                } else {
                    $_SESSION["resposta"] = "Erro para atualizar a senha para senha gerada";
                    header("Location: ../recuperarSenha.php");
                }
            }
        } else {
            $msg = 'Share it with us!';
        }
    } else {
        $_SESSION["resposta"] = "E-mail não existe na nossa base de dados!";
        header("Location: ../recuperarSenha.php");
    }
} else {
    $_SESSION["resposta"] = "Preencha o campo e-mail!";
    header("Location: ../recuperarSenha.php");
}