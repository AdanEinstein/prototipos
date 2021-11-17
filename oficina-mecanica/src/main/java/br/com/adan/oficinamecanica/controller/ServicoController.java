package br.com.adan.oficinamecanica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.adan.oficinamecanica.modelo.Servico;
import br.com.adan.oficinamecanica.repositories.ServicoRepository;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

	@Autowired
	ServicoRepository servicoRepository;
	
	@GetMapping("/")
	public ModelAndView listaServicos() {
		ModelAndView modelAndView = new ModelAndView("lista_servicos");
		
		List<Servico> servicos = servicoRepository.findAll();
		modelAndView.addObject("servicos", servicos);
//		
//		for (Servico servico : servicos) {
//			String acabado = servico.getTerminado() ? "SIM" : "NÃO";
//			modelAndView.addObject("servico.terminado", acabado);
//		}
		
		return modelAndView;
	}
	
	@GetMapping("/cadastra_servicos")
	public String telaCadastrar(Servico servico) {
		return "cadastra_servicos";
	}
	
	@PostMapping("/cadastra_servicos")
	public String cadastraServico(Servico servico){
		ModelAndView modelAndView = new ModelAndView("cadastra_servicos");
//		servico.setTerminado(false);
		modelAndView.addObject("servico", servico);
		servicoRepository.save(servico);
		return "redirect:cadastra_servicos/";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletarServico(@PathVariable(value = "id") Integer id) {
		
		Servico servico = servicoRepository.findServicoByid(id);
		servicoRepository.delete(servico);
		
		return "redirect:/servicos/";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView telaServico(@PathVariable(value = "id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastra_servicos2");
		Servico servico = servicoRepository.findServicoByid(id);
		modelAndView.addObject("servico", servico);
		return modelAndView;
	}
	
	@PostMapping("/editar/{id}")
	public String editarServico(Servico servico) {
		servicoRepository.save(servico);
		return "redirect:/servicos/";
	}
	
}
