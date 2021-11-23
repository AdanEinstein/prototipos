package infra;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaConexao {

	public static Connection getConexao() {
		try {
			
			String url = FabricaConexao.getProperties()
					.getProperty("url");
			String user = FabricaConexao.getProperties()
					.getProperty("user");
			String senha = FabricaConexao.getProperties()
					.getProperty("senha");
			
			Connection conexao = DriverManager
					.getConnection(url, user, senha);
			
			return conexao;
			
		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Properties getProperties() throws IOException {
		Properties prop = new Properties();
		prop.load(FabricaConexao.class.getResourceAsStream(
				"conexao.properties"));
		return prop;
	}
}
