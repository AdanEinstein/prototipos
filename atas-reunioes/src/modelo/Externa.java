package modelo;

public class Externa extends Pessoa{

	private String empresa;

	public Externa(String nome, String email, String empresa) {
		super(nome, email);
		this.empresa = empresa;
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
}
