package execucao.teste;

import java.sql.Connection;
import java.sql.SQLException;

import infra.FabricaConexao;

public class TesteFabricaConexao {

	public static void main(String[] args) throws SQLException {
		
		Connection conexao = FabricaConexao.getConexao();
		
		System.out.println("Conexão efetuada com sucesso!!");
		
		conexao.close();
	}
}
