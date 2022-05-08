-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 08-Maio-2022 às 18:54
-- Versão do servidor: 10.4.21-MariaDB
-- versão do PHP: 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `crudphp`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `pdv_produtos`
--

CREATE TABLE `pdv_produtos` (
  `id` int(11) NOT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `preco` decimal(10,2) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `pdv_produtos`
--

INSERT INTO `pdv_produtos` (`id`, `descricao`, `preco`, `quantidade`) VALUES
(1, 'Livro JavaScript', '15.90', 500),
(2, 'Livro PHP 8.1', '32.55', 495),
(5, 'Livro de Física 1', '45.70', 650),
(6, 'Livro de Redes', '23.33', 560),
(8, 'iMac Pro', '10500.60', 800);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pdv_total`
--

CREATE TABLE `pdv_total` (
  `id` int(11) NOT NULL,
  `id_venda` int(11) NOT NULL,
  `data_venda` date DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pdv_usuarios`
--

CREATE TABLE `pdv_usuarios` (
  `id` int(11) NOT NULL,
  `login` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `perfil` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `pdv_usuarios`
--

INSERT INTO `pdv_usuarios` (`id`, `login`, `senha`, `perfil`) VALUES
(1, 'root', 'sistemapdv2022141592', 'adm'),
(2, 'adaneinstein@gmail.com', '141592', 'padrão');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pdv_vendas`
--

CREATE TABLE `pdv_vendas` (
  `id_venda` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `pdv_produtos`
--
ALTER TABLE `pdv_produtos`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `pdv_total`
--
ALTER TABLE `pdv_total`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `pdv_usuarios`
--
ALTER TABLE `pdv_usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `pdv_vendas`
--
ALTER TABLE `pdv_vendas`
  ADD KEY `fk_produto` (`id_produto`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `pdv_produtos`
--
ALTER TABLE `pdv_produtos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `pdv_total`
--
ALTER TABLE `pdv_total`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `pdv_usuarios`
--
ALTER TABLE `pdv_usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `pdv_vendas`
--
ALTER TABLE `pdv_vendas`
  ADD CONSTRAINT `fk_produto` FOREIGN KEY (`id_produto`) REFERENCES `pdv_produtos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
