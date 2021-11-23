package execucao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import infra.DAO;
import modelo.Funcionario;

public class CrudFuncionario {

	private static Scanner entrada = new Scanner(System.in);
	
	private static void novoFuncionario(String nome, String email, String setor) {
		DAO<Funcionario> dao = new DAO<>();
		String sql = "INSERT INTO funcionarios(nome, email, setor) VALUES (?, ?, ?)";
		dao.executarSQL(sql, nome, email, setor);
		System.out.println("\n Funcionário cadastrada com sucesso!! \n");
		dao.close();
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
				funcionarios.forEach((f) -> f.imprimirFuncionarioID(true));
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
	
	private static String capturarEntrada(String texto) {
		System.out.print(texto);
		String valor = entrada.nextLine();
		return valor;
	}
}







