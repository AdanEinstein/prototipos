package execucao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import infra.DAO;
import modelo.Ata;
import modelo.Externa;
import modelo.Funcionario;
import modelo.Pessoa;
import modelo.Visibilidade;

public class CrudAta {

	private static Scanner entrada = new Scanner(System.in);
	
	private static Funcionario selecionarFuncionario() {
		DAO<Ata> dao = new DAO<>();
		String sqlSenha = "SELECT * FROM senhas WHERE id_funcionario = ?";
		String sqlIdFuncionario = "SELECT * FROM funcionarios WHERE id = ?";
		System.out.println("==============================");
		System.out.println("     SELECIONANDO EMISSOR");
		System.out.println("==============================");
		boolean continuar = true;
		while (continuar) {
			try {
				Integer id = Integer.parseInt(capturarEntrada("Digite o seu ID: "));
				ResultSet resultado = dao.executarSQL(sqlIdFuncionario, id);
				if (resultado.next()) {
					String nome = resultado.getString("nome");
					ResultSet resultado2 = dao.executarSQL(sqlSenha, id);
					if (resultado2.next()) {
						String senha = capturarEntrada("Senha de " + nome + ": ");
						boolean iguais = resultado2.getString("senha").equals(senha);
						if (iguais) {
							Integer idFinal = resultado.getInt("id");
							String nomeFinal = resultado.getString("nome");
							String emailFinal = resultado.getString("email");
							String setorFinal = resultado.getString("setor");
							Integer numeroAtas = resultado.getInt("numeroatasemitidas");
							return new Funcionario(idFinal, nomeFinal, emailFinal, setorFinal, numeroAtas);
						} else {
							System.out.println("\nSenha Inválida\n");
						}
					} else {
						boolean cadastrar = capturarEntrada("Deseja cadastrar login? [S]/[n]").equalsIgnoreCase("n")
								? false
								: true;
						if (cadastrar) {
							String senha = capturarEntrada("Cadastre sua senha " + nome + ": ");
							CrudFuncionario.cadastrarSenha(id, senha);
						} else {
							return null;
						}
					}

				} else {
					System.out.println("\nFuncionário não cadastrado\n");

				}
			} catch (NumberFormatException e) {
				System.err.println("\nID Inválido!!!\n");
			} catch (SQLException e) {
			}

		}

		dao.close();
		return null;
	}

	private static String capturarEntrada(String texto) {
		System.out.print(texto);
		String valor = entrada.nextLine();
		return valor;
	}

	private static List<String> palavrasChave() {
		List<String> palavras = new ArrayList<>();
		boolean continuar = true;
		while (continuar) {
			if (palavras.size() <= 5) {
				String palavra = capturarEntrada("Digite a palavra chave: ");
				palavras.add(palavra);
			}
			continuar = capturarEntrada("Deseja adicionar mais uma palavra? [S]/[n]").equalsIgnoreCase("n") ? false
					: true;
		}
		return palavras;
	}

	private static List<Pessoa> participantes() {
		String sqlFunc = "SELECT * FROM funcionarios WHERE id = ?";
		String sqlExt = "SELECT * FROM externas WHERE id = ?";
		List<Pessoa> participantes = new ArrayList<>();
		DAO<Pessoa> dao = new DAO<>();
		boolean continuar = true;
		while (continuar) {
			System.out.println("Participantes: ");
			String opcao = capturarEntrada("\n[1] - Funcionário\n[2] - Pessoa Externa\n\nOpção: ");
			switch (opcao) {
			case "1":
				System.out.println("\nFuncionários:");
				CrudFuncionario.listarFuncionarios();
				try {
					Integer id = Integer.parseInt(capturarEntrada("Digite o ID do participante: "));
					ResultSet resultado = dao.executarSQL(sqlFunc, id);
					if (resultado.next()) {
						Integer idPart = resultado.getInt("id");
						String nomePart = resultado.getString("nome");
						String emailPart = resultado.getString("email");
						String setorPart = resultado.getString("setor");
						Integer numeroPart = resultado.getInt("numeroatasemitidas");
						participantes.add(new Funcionario(idPart, nomePart, emailPart, setorPart, numeroPart));
					}
				} catch (NumberFormatException e) {
					System.err.println("\nID Inválido!!\n");
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
				break;
			case "2":
				System.out.println("\nPessoas Externas:");
				CrudFuncionario.listarExternas();
				try {
					Integer id = Integer.parseInt(capturarEntrada("Digite o ID do participante: "));
					ResultSet resultado = dao.executarSQL(sqlExt, id);
					if (resultado.next()) {
						Integer idPart = resultado.getInt("id");
						String nomePart = resultado.getString("nome");
						String emailPart = resultado.getString("email");
						String empresaPart = resultado.getString("empresa");
						participantes.add(new Externa(idPart, nomePart, emailPart, empresaPart));
					} else {
						novaExterna(resultado);
					}
				} catch (NumberFormatException e) {
					System.err.println("\nID Inválido!!\n");
				} catch (SQLException e) {
					System.err.println("\nErro de banco!!\n");
				}

				break;
			default:
				System.err.println("\nOpção Inválida\n");
			}
			if (participantes.size() >= 2) {
				continuar = capturarEntrada("Deseja adicionar mais um participante? [S]/[n]").equalsIgnoreCase("n")
						? false
						: true;
			}
		}
		dao.close();
		return participantes;
	}

	private static ResultSet novaExterna(ResultSet resultSet) throws SQLException {

		if (resultSet.next()) {
			return resultSet;
		} else {
			String nome = capturarEntrada("\nCadastre o nome da pessoa: ");
			String email = capturarEntrada("\nCadastre o email da pessoa: ");
			String empresa = capturarEntrada("\nCadastre nome da empresa a que pertence: ");
			CrudFuncionario.cadastrarExterna(nome, email, empresa);
			return resultSet;
		}
	}
	
	public static void listarAtas() {
		try {
			String sql = "SELECT id, tituloreuniao, setor, visibilidade FROM atas";
			DAO<Ata> dao = new DAO<>();
			ResultSet rs = dao.executarSQL(sql);
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String titulo = rs.getString(2);
				String setor = rs.getString(3);
				String visibilidade = rs.getString(4);
				System.out.println(id + " - " + titulo + " - " + setor + " - " + visibilidade + "\n");
			}
			
			dao.close();
		} catch (Exception e) {}
	}

	private static Ata novaAta(){
		SimpleDateFormat formatoHora = new SimpleDateFormat("HHmm");
		SimpleDateFormat formatoData = new SimpleDateFormat("ddMMyyyy");
		System.out.println("-----------------------------------------");
		System.out.println("             EMISSÃO DA ATA              ");
		System.out.println("-----------------------------------------");
		boolean continuar = true;
			
		while (continuar) {
			try {
				String tituloAta = capturarEntrada("\nDigite o título da Ata: ");
				List<Pessoa> participantes = participantes();
				String pauta = capturarEntrada("\nDigite a pauta: ");
				String setor = capturarEntrada("\nDigite o setor: ");
				List<String> palavrasChave = palavrasChave();
				String dataReuniaoString = capturarEntrada("Digite a data da reunião (dd/mm/aaaa): ")
						.replaceAll("\\D", "");
				String horaInicialString = capturarEntrada("Digite a hora inicial (hh:mm) ").replaceAll("\\D", "");
				String horaFinalString = capturarEntrada("Digite a hora final (hh:mm) ").replaceAll("\\D", "");
				java.util.Date dt = formatoData.parse(dataReuniaoString);
				java.util.Date hri = formatoHora.parse(horaInicialString);
				java.util.Date hrf = formatoHora.parse(horaFinalString);
				Date dataReuniao = new Date(dt.getTime());
				Time horaInicial = new Time(hri.getTime());
				Time horaFinal = new Time(hrf.getTime());
				String descricao = capturarEntrada("\nDescrição da reunião: ");
				System.out.print("\nDeseja definir ata como PRIVADA? [S]/[n]");
				Visibilidade visibilidade = entrada.nextLine().trim().equalsIgnoreCase("n")
						? Visibilidade.PUBLICA : Visibilidade.PRIVADA;
				Ata ata = new Ata(null, tituloAta, participantes, pauta, dataReuniao, horaInicial, horaFinal, setor,
						descricao, palavrasChave, visibilidade);
				System.out.println("\n Ata emitida com sucesso !!!!\n");
				return ata;
			} catch (Exception e) {
				System.err.println(
						"\n Você informou algum valor inválido!!\n\n Por favor digite novamente os dados!!!\n");
			} 
		}
		return null;
			
	}

	public static void novaAtaRun(){
		DAO<Ata> dao = new DAO<>();
		Funcionario emissor = selecionarFuncionario();
		Ata ata = novaAta();
		ata.setEmissor(emissor);
		emissor.emitirAta(ata.getTituloReuniao(), ata.getParticipantes(), ata.getPauta(), ata.getDataReuniao(),
				ata.getHoraInicial(), ata.getHoraFinal(), ata.getSetor(), ata.getDescricao(), ata.getPalavrasChave(),
				ata.getVisibilidade());
		dao.close();
	}
	
	//TODO implementar esse método
	public static void alterarAtaRun() {
		DAO<Ata> dao = new DAO<>();
		String selectAtas = "SELECT * FROM atas WHERE id = ?";
		System.out.println("-----------------------------------------");
		System.out.println("             ALTERAÇÃO DE ATA            ");
		System.out.println("-----------------------------------------");
		boolean continuar = true;
		while (continuar) {
			try {
				listarAtas();
				Integer idAta = Integer.parseInt(capturarEntrada("\nDigite o ID da Ata: "));
				ResultSet rs = dao.executarSQL(selectAtas, idAta);
				if (rs.next()) {
					Integer id = rs.getInt(1);
					String titulo = rs.getString(2);
					String pauta = rs.getString(3);
					Date dataR = rs.getDate(4);
					Time horaI = rs.getTime(5);
					Time horaF = rs.getTime(6);
					String setor = rs.getString(7);
					String descricao = rs.getString(8);
					Visibilidade visib = rs.getString(9).equals("PUBLICA") ? Visibilidade.PUBLICA : Visibilidade.PRIVADA;
					Funcionario emissor = CrudFuncionario.funcionarioPorId(rs.getInt(10));
//					Ata ata = new Ata(id, emissor, titulo, null, pauta, dataR, horaI, horaF, setor, descricao, null, visib);
					System.out.println("\n" + new Ata(id, emissor, titulo, null, pauta, dataR, horaI, horaF, setor, descricao, null, visib));
				}
			} catch (Exception e) {
				System.err.println("\nValor Inválido!\n" + e.getMessage());
			}
		}
		dao.close();
	}
	
	
}
