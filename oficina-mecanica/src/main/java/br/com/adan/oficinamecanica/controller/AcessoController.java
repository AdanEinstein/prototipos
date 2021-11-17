package br.com.adan.oficinamecanica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.adan.oficinamecanica.modelo.Mecanico;
import br.com.adan.oficinamecanica.modelo.Recepcionista;
import br.com.adan.oficinamecanica.modelo.acesso.Acesso;
import br.com.adan.oficinamecanica.repositories.AcessoRepository;
import br.com.adan.oficinamecanica.repositories.MecanicoRepository;
import br.com.adan.oficinamecanica.repositories.RecepcionistaRepository;

@Controller
@RequestMapping("/login")
public class AcessoController {
	
	@Autowired
	AcessoRepository acessoRepository;
	
	@Autowired
	RecepcionistaRepository recepcionistaRepository;
	
	@Autowired
	MecanicoRepository mecanicoRepository;
	
	@GetMapping("/")
	public String telaLogin(Acesso acesso) {
		return "login";
	}
	
//	@GetMapping("/entrar/{matricula}/{senha}")
//	public String entrarLogin(@PathVariable(value = "matricula") String matricula,
//			@PathVariable(value = "senha") String senha) {
//		
//		boolean matriculaVazia = matricula.isEmpty() || matricula == null ? true: false;
//		boolean senhaVazia = senha.isEmpty() || senha == null ? true : false;
//		
//		if(matriculaVazia || senhaVazia) {
//			return "redirect:/login/";
//		} else {
//			
//			if(matricula.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("123")) {
//				return "adm_tela";
//			} else if(acessoRepository.encontrarAcesso(matricula, senha) != null) {
//				if(acessoRepository.consultarMatriculaRecepcionista(matricula) != null) {
//					return "recepcionista_tela";
//				} else if(acessoRepository.consultarMatriculaMecanico(matricula) != null) {
//					return "mecanico_tela";
//				}
//			}
//			
//			return "redirect:/login/";
//		}
//	}
//	
//	@PostMapping("/entrar/{matricula}/{senha}")
//	public String checarFuncionario(@PathVariable(value = "matricula") String matricula,
//			@PathVariable(value = "senha") String senha) {
//		
//		boolean matriculaVazia = matricula.isEmpty() || matricula == null ? true: false;
//		boolean senhaVazia = senha.isEmpty() || senha == null ? true : false;
//		
//		if(matriculaVazia || senhaVazia) {
//			return "redirect:/login/";
//		} else {
//			
//			if(acessoRepository.consultarMatriculaRecepcionista(matricula) != null) {
//				
//				Recepcionista recepcionista = acessoRepository.consultarMatriculaRecepcionista(matricula);
//				recepcionista = recepcionistaRepository.findRecepcionistaByid(recepcionista.getId());
//				if(recepcionista != null && acessoRepository.encontrarAcesso(matricula, senha) == null) {
//					Acesso acesso = new Acesso(matricula, senha);
//					acessoRepository.save(acesso);
//					return "redirect:/login/";
//				} else if(acessoRepository.consultarMatriculaMecanico(matricula) != null) {
//					
//					Mecanico mecanico = acessoRepository.consultarMatriculaMecanico(matricula);
//					mecanico = mecanicoRepository.findMecanicoByid(mecanico.getId());
//					if(mecanico != null && acessoRepository.encontrarAcesso(matricula, senha) == null) {
//						Acesso acesso = new Acesso(matricula, senha);
//						acessoRepository.save(acesso);
//						return "redirect:/login/";
//					} else {
//						return "redirect:/login/";
//					}	
//				} else {
//					return "redirect:/login/";
//				}
//				
//			} else {
//				return "redirect:/login/";
//			}
//			
//		}
//	}
	
	@GetMapping("/entrar")
	public String entrarLogin(@RequestParam(value = "matricula") String matricula,
			@RequestParam(value = "senha") String senha) {
		
		boolean matriculaVazia = matricula.isEmpty() || matricula == null ? true: false;
		boolean senhaVazia = senha.isEmpty() || senha == null ? true : false;
		
		if(matriculaVazia || senhaVazia) {
			return "redirect:/login/";
		} else {
			
			if(matricula.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("123")) {
				return "adm_tela";
			} else if(acessoRepository.encontrarAcesso(matricula, senha) != null) {
				List<Acesso> acessos = acessoRepository.findMatricula(matricula);
				if(acessoRepository.encontrarAcesso(matricula, senha).getId() == acessos.get(0).getId()) {
					return "recepcionista_tela";
				} else {
					return "mecanico_tela";
				}
			}
			
			return "redirect:/login/";
		}
	}
	
	@PostMapping("/entrar")
	public String checarFuncionario(@RequestParam(value = "matricula") String matricula,
			@RequestParam(value = "senha") String senha) {
		
		boolean matriculaVazia = matricula.isEmpty() || matricula == null ? true: false;
		boolean senhaVazia = senha.isEmpty() || senha == null ? true : false;
		
		if(matriculaVazia || senhaVazia) {
			return "redirect:/login/";
		} else {
			
			if(acessoRepository.consultarMatriculaRecepcionista(matricula) != null) {
				
				Recepcionista recepcionista = acessoRepository.consultarMatriculaRecepcionista(matricula);
				recepcionista = recepcionistaRepository.findRecepcionistaByid(recepcionista.getId());
				if(recepcionista != null && acessoRepository.consultarMatriculaRecepcionista(matricula) != null) {
					if (acessoRepository.encontrarAcesso(matricula) < 1) {
						Acesso acesso = new Acesso(matricula, senha);
						acessoRepository.save(acesso);
						
					} else if(acessoRepository.consultarMatriculaMecanico(matricula) != null) {
						
						Mecanico mecanico = acessoRepository.consultarMatriculaMecanico(matricula);
						mecanico = mecanicoRepository.findMecanicoByid(mecanico.getId());
						if(mecanico != null && acessoRepository.consultarMatriculaMecanico(matricula) != null) {
							if (acessoRepository.encontrarAcesso(matricula) < 2) {
								Acesso acesso = new Acesso(matricula, senha);
								acessoRepository.save(acesso);
							}
						
							return "redirect:/login/";
						
						}
						
					} else {
						return "redirect:/login/";
					}	
				} else {
					return "redirect:/login/";
				}
				
			} else {
				return "redirect:/login/";
			}
			
		}
		
		return "redirect:/login/";
	}

}








