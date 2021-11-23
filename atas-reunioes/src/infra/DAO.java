package infra;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO<E> {

	private Connection conexao;
	
	public Connection getConexao() {
		try {
			if (conexao != null && !conexao.isClosed()) {
				return conexao;
			}
		} catch (SQLException e) {}
		
		conexao = FabricaConexao.getConexao();
		return conexao;
	}
	
	public ResultSet executarSQL(String sql, Object... params) {
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			int indice = 1;
			for (Object param : params) {
				if (param instanceof String) {
					if (sql.startsWith("SELECT")) {
						stmt.setString(indice, "%" + (String) param + "%");
					} else {
						stmt.setString(indice,(String) param);
					}
				} else if(param instanceof Integer) {
					stmt.setInt(indice,(Integer) param);
				} else if(param instanceof Date) {
					stmt.setDate(indice,(Date) param);
				} else {
					System.err.println("Algo está errado!!");
				}
				indice++;
			}
			
			if (stmt.execute()) {
				return stmt.executeQuery();
			}
			
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void close() {
		try {
			if (conexao != null) {
				conexao.close();
			}
		} catch (SQLException e) {}
	}
}
