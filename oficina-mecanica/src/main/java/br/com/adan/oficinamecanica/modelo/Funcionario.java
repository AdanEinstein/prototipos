//package br.com.adan.oficinamecanica.modelo;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//
//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//public abstract class Funcionario {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	private String nome;
//	private String matricula;
//	private String setor;
//	
//	public Funcionario() {	}
//
//	public Funcionario(Integer id,String nome, String matricula, String setor) {
//		this.id = id;
//		this.nome = nome;
//		this.matricula = matricula;
//		this.setor = setor;
//	}
//	
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getMatricula() {
//		return matricula;
//	}
//
//	public void setMatricula(String matricula) {
//		this.matricula = matricula;
//	}
//
//	public String getSetor() {
//		return setor;
//	}
//
//	public void setSetor(String setor) {
//		this.setor = setor;
//	}
//
//}
