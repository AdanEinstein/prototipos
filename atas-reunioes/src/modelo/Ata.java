package modelo;

import java.sql.Date;
import java.util.List;

public class Ata {

	private Funcionario emissor;
	private String tituloReuniao;
	private Date dataEmissao;
	private List<Pessoa> participantes;
	private String pauta;
	private Date dataReuniao;
	private Date horaInicial;
	private Date horaFinal;
	private String setor;
	private String descricao;
	private String[] palavrasChave = new String[5];
	private Integer numeroParticipantes;
	private Visibilidade visibilidade;
	
	

	public Ata(Funcionario emissor, String tituloReuniao, List<Pessoa> participantes, String pauta, Date dataReuniao,
			Date horaInicial, Date horaFinal, String setor, String descricao, String[] palavrasChave,
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

	public Date getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Date getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
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

	public String[] getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(String[] palavrasChave) {
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

	public void setVisibilidade(Visibilidade visibilidade) {
		this.visibilidade = visibilidade;
	}

}
