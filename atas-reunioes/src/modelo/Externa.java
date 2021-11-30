package modelo;

public class Externa extends Pessoa {

	private Integer id;
	private String empresa;

	public Externa(Integer id, String nome, String email, String empresa) {
		super(nome, email);
		this.id = id;
		this.empresa = empresa;
	}

	public Externa(String nome, String email, String empresa) {
		super(nome, email);
		this.empresa = empresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public void imprimirID(Boolean id) {
		if (id == null || id == false) {
			System.out.println("Nome -> " + this.getNome());
			System.out.println("E-mail -> " + this.getEmail());
			System.out.println("Empresa -> " + this.getEmpresa());
		} else {
			System.out.println(this.id + " - " + this.getNome() + " - " + this.getEmail() + " - " + this.getEmpresa());
		}
	}

}
