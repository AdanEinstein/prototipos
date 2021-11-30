package modelo;

import java.sql.Date;
import java.sql.Time;
//import java.text.SimpleDateFormat;
import java.util.List;

import infra.DAO;

public class Funcionario extends Pessoa {

	private Integer id;
	private String setor;
	private Integer numeroAtasEmitidas;

	public Funcionario(String nome, String email, String setor) {
		super(nome, email);
		this.setor = setor;
		this.numeroAtasEmitidas = 0;
	}

	public Funcionario(Integer id, String nome, String email, String setor) {
		super(nome, email);
		this.id = id;
		this.setor = setor;
		this.numeroAtasEmitidas = 0;
	}

	public Funcionario(Integer id, String nome, String email, String setor, Integer numero) {
		super(nome, email);
		this.id = id;
		this.setor = setor;
		this.numeroAtasEmitidas = numero;
	}

	public Integer getId() {
		return id;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Integer getNumeroAtasEmitidas() {
		return numeroAtasEmitidas;
	}

	public Ata emitirAta(String tituloReuniao, List<Pessoa> participantes, String pauta, Date dataReuniao,
			Time horaInicial, Time horaFinal, String setor, String descricao, List<String> palavrasChave,
			Visibilidade visibilidade) {
		DAO<Ata> dao = new DAO<>();

		Ata ata = new Ata(this, tituloReuniao, participantes, pauta, dataReuniao, horaInicial, horaFinal, setor,
				descricao, palavrasChave, visibilidade);

		this.numeroAtasEmitidas++;
		
		//INCREMENTANDO O numero de atas emitidas
		String sqlNumeroAtas = "UPDATE funcionarios SET numeroatasemitidas = (SELECT numeroatasemitidas + 1 FROM funcionarios WHERE id = ?) WHERE id = ?";
		new DAO<>().executarSQL(sqlNumeroAtas, this.getId(), this.getId());
		
		//PERSISTINDO ATA NA TABELA atas
		//Criação da Ata na tabela
		String sqlNovaAta = "INSERT INTO atas(tituloreuniao, pauta, datareuniao, horainicial, horafinal, setor, descricao, visibilidade, emissor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int id = dao.executarSQLId(sqlNovaAta, ata.getTituloReuniao(),
				ata.getPauta(),
				ata.getDataReuniao(),
				ata.getHoraInicial(),
				ata.getHoraFinal(),
				ata.getSetor(),
				ata.getDescricao(),
				ata.getVisibilidadeString(),
				ata.getEmissor().getId());
		
		//PERSISTINDO NAS TABELAS participantes_funcionarios, participantes_externas
		String sqlParticipantesF = "INSERT INTO participantes_funcionarios(id_funcionario, id_ata) VALUES (?, ?)";
		String sqlParticipantesE = "INSERT INTO participantes_externas(id_externa, id_ata) VALUES (?, ?)";
		participantes.forEach((p) -> {
			if (p instanceof Funcionario) {
				Funcionario f = (Funcionario) p;
				dao.executarSQL(sqlParticipantesF, f.getId(), id);
			} else if (p instanceof Externa) {
				Externa e = (Externa) p;
				dao.executarSQL(sqlParticipantesE, e.getId(), id);
			}
		});
		
		//PERSISTINDO NA TABELA palavras_chave
		String sqlPalavrasChave = "INSERT INTO palavras_chave(id_ata, palavra) VALUES (?, ?)";
		palavrasChave.stream().limit(5).forEach((p) -> {
			dao.executarSQL(sqlPalavrasChave, id, p);
		});
		dao.close();
		return ata;
	}

	public void imprimirID(Boolean id) {
		if (id == null || id == false) {
			System.out.println("Nome -> " + this.getNome());
			System.out.println("E-mail -> " + this.getEmail());
			System.out.println("Setor -> " + this.getSetor());
		} else {
			System.out.println(this.id + " - " + this.getNome() + " - " + this.getEmail() + " - " + this.getSetor());
		}
	}
}
