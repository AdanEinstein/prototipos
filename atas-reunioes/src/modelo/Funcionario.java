package modelo;

import java.sql.Date;
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
			Date horaInicial, Date horaFinal, String setor, String descricao, String[] palavrasChave,
			Visibilidade visibilidade) {
		
		Ata ata = new Ata(this, tituloReuniao, participantes, pauta, dataReuniao, horaInicial, horaFinal, setor,
				descricao, palavrasChave, visibilidade);

		this.numeroAtasEmitidas++;
		String sqlNumeroAtas = "UPDATE funcionarios SET numeroatasemitidas = (SELECT numeroatasemitidas + 1 FROM funcionarios WHERE id = ?) WHERE id = ?";
		new DAO<>().executarSQL(sqlNumeroAtas, this.getId(), this.getId());
		return ata;
	}

	public void imprimirFuncionarioID(Boolean id) {
		if (id == null || id == false) {
			System.out.println("Nome -> " + this.getNome());
			System.out.println("E-mail -> " + this.getEmail());
			System.out.println("Setor -> " + this.getSetor());
		} else {
			System.out.println(this.id + " - " + this.getNome() + " - " + this.getEmail() + " - " + this.getSetor());
		}
	}
}
