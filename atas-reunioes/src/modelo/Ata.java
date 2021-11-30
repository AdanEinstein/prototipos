package modelo;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import execucao.CrudFuncionario;

public class Ata {

	private Integer id;
	private Funcionario emissor;
	private String tituloReuniao;
	private Date dataEmissao;
	private List<Pessoa> participantes;
	private String pauta;
	private Date dataReuniao;
	private Time horaInicial;
	private Time horaFinal;
	private String setor;
	private String descricao;
	private List<String> palavrasChave;
	private Integer numeroParticipantes;
	private Visibilidade visibilidade;

	public Ata(Integer id, Funcionario emissor, String tituloReuniao, List<Pessoa> participantes, String pauta,
			Date dataReuniao, Time horaInicial, Time horaFinal, String setor, String descricao, List<String> palavrasChave,
			Visibilidade visibilidade) {
		this.id = id;
		this.emissor = emissor;
		this.tituloReuniao = tituloReuniao;
		this.participantes = participantes;
		this.pauta = pauta;
		this.dataReuniao = dataReuniao;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.setor = setor;
		this.descricao = descricao;
		this.palavrasChave = palavrasChave;
		this.visibilidade = visibilidade;
	}

	public Ata(Funcionario emissor, String tituloReuniao, List<Pessoa> participantes, String pauta, Date dataReuniao,

			Time horaInicial, Time horaFinal, String setor, String descricao, List<String> palavrasChave,
			Visibilidade visibilidade) {
		this.emissor = emissor;
		this.tituloReuniao = tituloReuniao;
		this.participantes = participantes;
		this.pauta = pauta;
		this.dataReuniao = dataReuniao;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.setor = setor;
		this.descricao = descricao;
		this.palavrasChave = palavrasChave;
		this.visibilidade = visibilidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Funcionario getEmissor() {
		return emissor;
	}

	public void setEmissor(Funcionario emissor) {
		this.emissor = emissor;
	}

	public String getTituloReuniao() {
		return tituloReuniao;
	}

	public void setTituloReuniao(String tituloReuniao) {
		this.tituloReuniao = tituloReuniao;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public List<Pessoa> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Pessoa> participantes) {
		this.participantes = participantes;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public Date getDataReuniao() {
		return dataReuniao;
	}

	public void setDataReuniao(Date dataReuniao) {
		this.dataReuniao = dataReuniao;
	}

	public Time getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(Time horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Time getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Time horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<String> getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(List<String> palavrasChave) {
		this.palavrasChave = palavrasChave;
	}

	public Integer getNumeroParticipantes() {
		return numeroParticipantes;
	}

	public void setNumeroParticipantes(Integer numeroParticipantes) {
		this.numeroParticipantes = numeroParticipantes;
	}

	public Visibilidade getVisibilidade() {
		return visibilidade;
	}
	
	public String getVisibilidadeString() {
		if(this.getVisibilidade() == Visibilidade.PRIVADA) {
			return "PRIVADA";
		}
		return "PUBLICA";
	}

	public void setVisibilidade(Visibilidade visibilidade) {
		this.visibilidade = visibilidade;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		return "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+ "\nTítulo: " + this.getTituloReuniao()
				+ "\nData de Emissão: " + sdf.format(this.getDataEmissao())
				+ "\nVisibilidade: " + this.getVisibilidadeString()
				+ "\nPauta: " + this.getPauta()
				+ "\nDescrição: " + this.getDescricao()
				+ "\nData Reunião: " + sdf.format(this.getDataReuniao())
				+ "\nInício: " + shf.format(this.getHoraInicial())
				+ "\nFim: " + shf.format(this.getHoraFinal())
				+ "\nSetor: " + this.getSetor()
				+ "\nEmissor: " + CrudFuncionario.nomeEmissorPorIdAta(this.getId())
				+ "\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%";
	}
	
}
