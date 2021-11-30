package execucao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import infra.DAO;
import modelo.Ata;
import modelo.Externa;
import modelo.Funcionario;

public class CrudFuncionario {

	private static Scanner entrada = new Scanner(System.in);
	
	private static void novoFuncionario(String nome, String email, String setor) {
		try {
			DAO<Funcionario> dao = new DAO<>();
			String sql = "INSERT INTO funcionarios(nome, email, setor) VALUES (?, ?, ?)";
			dao.executarSQL(sql, nome, email, setor);
			System.out.println("\n Funcionário cadastrada com sucesso!! \n");
			dao.close();
		} catch (Exception e) {
			System.err.println("\nAlgum dado violou a integridade do banco!!!\n");
		}
	}
	
	public static void alterarFuncionario(String atributo, String valor, Integer id) {
		DAO<Funcionario> dao = new DAO<>();
		String sqlNome = "UPDATE funcionarios SET nome = ? WHERE id = ?";
		String sqlEmail = "UPDATE funcionarios SET email = ? WHERE id = ?";
		String sqlSetor = "UPDATE funcionarios SET setor = ? WHERE id = ?";
		switch(atributo) {
			case "nome":
				dao.executarSQL(sqlNome, valor, id);
				break;
			case "email":
				dao.executarSQL(sqlEmail, valor, id);
				break;
			case "setor":
				dao.executarSQL(sqlSetor, valor, id);
				break;
			default:
				System.err.println("\nNão foi possível alterar!\n");
 		}
		dao.close();
	}
	
	public static void novoFuncionarioRun() {
		DAO<Funcionario> dao = new DAO<>();
		boolean continuar = true;
		while(continuar) {
			System.out.println("\n------------------------------------------");
			System.out.println("               NOVO FUNCIONÁRIO:            ");
			System.out.println("------------------------------------------\n");
			String nome = capturarEntrada("Nome: ");
			String email = capturarEntrada("E-mail: ");
			String setor = capturarEntrada("Setor: ");
			novoFuncionario(nome, email, setor);
			continuar = capturarEntrada("Cadastrar novo? [S/n] ").equalsIgnoreCase("n") ? 
					false : true;
		}
		dao.close();
	}
	
	public static void alterarFuncionarioRun() {
		DAO<Funcionario> dao = new DAO<>();
		boolean continuar = true;
		while (continuar) {
			try {
				String sql = "SELECT * FROM funcionarios ORDER BY id ASC";
				List<Funcionario> funcionarios = new ArrayList<>();
				System.out.println("\n------------------------------------------");
				System.out.println("             ALTERAR FUNCIONÁRIO:           ");
				System.out.println("------------------------------------------\n");
				ResultSet resultado = dao.executarSQL(sql);
				while (resultado.next()) {
					Integer id = resultado.getInt(1);
					String nome = resultado.getString(2);
					String email = resultado.getString(3);
					String setor = resultado.getString(4);
					funcionarios.add(new Funcionario(id, nome, email, setor));
				}
				funcionarios.forEach((f) -> f.imprimirID(true));
				String id = capturarEntrada("Digite o ID do funcionário: ");
				String atributo = capturarEntrada("[1] - Nome\n[2] - E-mail\n[3] - Setor\nDigite opção: ");
				String valor = capturarEntrada("Digite o novo valor: ");
				switch (atributo) {
				case "1":
					alterarFuncionario("nome", valor, Integer.parseInt(id));
					break;
				case "2":
					alterarFuncionario("email", valor, Integer.parseInt(id));
					break;
				case "3":
					alterarFuncionario("setor", valor, Integer.parseInt(id));
					break;
				default:
					System.err.println("\nValor Inválido\n");
					break;
				}
				continuar = capturarEntrada("Deseja continuar? [S]/[n] ").equalsIgnoreCase("n")
						? false : true;
				
			} catch (SQLException e) {
				System.err.println("\nAlgo ocorreu errada!!!\n");
			}
		}
		dao.close();
	}
	
	public static void deletarFuncionario() {
		DAO<Funcionario> dao = new DAO<>();
		boolean continuar = true;
		while (continuar) {
			try {
				String sql = "SELECT * FROM funcionarios ORDER BY id ASC";
				String sqlDeletar = "DELETE FROM funcionarios WHERE id = ?";
				List<Funcionario> funcionarios = new ArrayList<>();
				System.out.println("\n------------------------------------------");
				System.out.println("             DELETAR FUNCIONÁRIO:           ");
				System.out.println("------------------------------------------\n");
				ResultSet resultado = dao.executarSQL(sql);
				while (resultado.next()) {
					Integer id = resultado.getInt(1);
					String nome = resultado.getString(2);
					String email = resultado.getString(3);
					String setor = resultado.getString(4);
					funcionarios.add(new Funcionario(id, nome, email, setor));
				}
				funcionarios.forEach((f) -> f.imprimirID(true));
				String id = capturarEntrada("Digite o ID do funcionário: ");
				try {
					dao.executarSQL(sqlDeletar, Integer.parseInt(id));
					System.out.println("\nPessoa deletada com sucesso!\n");
				} catch (NumberFormatException e) {
					System.err.println("\nValor Inválido\n");
				}
				continuar = capturarEntrada("Deseja continuar? [S]/[n] ").equalsIgnoreCase("n")
						? false : true;
				
			} catch (SQLException e) {
				System.err.println("\nAlgo ocorreu errada!!!\n");
			}
		}
		dao.close();
	}
	
	public static void cadastrarSenha(Integer id, String senha) {
		try {
			String sql = "INSERT INTO senhas(id_funcionario, senha) VALUES (?, ?)";
			new DAO<>().executarSQL(sql, id, senha);
			System.out.println("\nSenha cadastrada com sucesso!!\n");
		} catch (Exception e) {
			System.err.println("\nErro ao cadastrar senha!!\n");
		}
		
	}
	
	public static void cadastrarExterna(String nome, String email, String empresa) {
		String sql = "INSERT INTO externas(nome, email, empresa) VALUES (?, ?, ?)";
		new DAO<>().executarSQL(sql, nome, email, empresa);
		System.out.println("\nPessoa cadastrada com sucesso!!\n");
		
	}
	
	public static ResultSet selecionarExterna(String email) {
		String sql = "SELECT * FROM externas WHERE email ILIKE ?";
		return new DAO<>().executarSQL(sql, email);
	}
	
	public static void listarFuncionarios() {
		try {
			String sql = "SELECT * FROM funcionarios ORDER BY id ASC";
			DAO<Funcionario> dao = new DAO<>();
			List<Funcionario> funcionarios = new ArrayList<>();
			ResultSet resultado = dao.executarSQL(sql);
			while (resultado.next()) {
				Integer id = resultado.getInt(1);
				String nome = resultado.getString(2);
				String email = resultado.getString(3);
				String setor = resultado.getString(4);
				funcionarios.add(new Funcionario(id, nome, email, setor));
			}
			funcionarios.forEach((f) -> f.imprimirID(true));
			dao.close();
		} catch (SQLException e) {}
	}

	public static void listarExternas() {
		try {
			String sql = "SELECT * FROM externas ORDER BY id ASC";
			DAO<Externa> dao = new DAO<>();
			List<Externa> externas = new ArrayList<>();
			ResultSet resultado = dao.executarSQL(sql);
			while (resultado.next()) {
				Integer id = resultado.getInt(1);
				String nome = resultado.getString(2);
				String email = resultado.getString(3);
				String empresa = resultado.getString(4);
				externas.add(new Externa(id, nome, email, empresa));
			}
			externas.forEach((f) -> f.imprimirID(true));
			dao.close();
		} catch (SQLException e) {}
	}
	
	private static String capturarEntrada(String texto) {
		System.out.print(texto);
		String valor = entrada.nextLine();
		return valor;
	}
	
	public static String nomeEmissorPorIdAta(Integer id) {

		DAO<Ata> dao;
		try {
			dao = new DAO<>();
			String sql = "SELECT nome FROM funcionarios f, atas a WHERE f.id = a.emissor AND a.id = ?";
			ResultSet resultado = dao.executarSQL(sql, id);
			if (resultado.next()) {
				String nome = resultado.getString(1);
				dao.close();
				return nome;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Funcionario funcionarioPorId(Integer id) {
		
		DAO<Ata> dao;
		try {
			dao = new DAO<>();
			String sql = "SELECT * FROM funcionarios WHERE id = ?";
			ResultSet rs = dao.executarSQL(sql, id);
			if (rs.next()) {
				Integer idF = rs.getInt(1);
				String nomeF = rs.getString(2);
				String emailF = rs.getString(3);
				String setorF = rs.getString(4);
				return new Funcionario(idF, nomeF, emailF, setorF);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}







