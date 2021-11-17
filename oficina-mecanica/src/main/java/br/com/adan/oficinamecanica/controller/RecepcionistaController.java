package br.com.adan.oficinamecanica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.adan.oficinamecanica.modelo.Recepcionista;
import br.com.adan.oficinamecanica.repositories.RecepcionistaRepository;

@Controller
@RequestMapping("/recepcionistas")
public class RecepcionistaController {

	@Autowired
	RecepcionistaRepository recepcionistaRepository;
	
	@GetMapping("/")
	public ModelAndView listaRecepcionistas() {
		ModelAndView modelAndView = new ModelAndView("lista_recepcionistas");
		
		List<Recepcionista> recepcionistas = recepcionistaRepository.findAll();
		modelAndView.addObject("recepcionistas", recepcionistas);
		return modelAndView;
	}
	
	@GetMapping("/cadastra_recepcionistas")
	public String telaCadastrar(Recepcionista recepcionista) {
		return "cadastra_recepcionistas";
	}
	
	@PostMapping("/cadastra_recepcionistas")
	public String cadastraRecepcionista(Recepcionista recepcionista){
		ModelAndView modelAndView = new ModelAndView("cadastra_recepcionistas");
		recepcionista.setSetor("Recepção");
		modelAndView.addObject("recepcionista", recepcionista);
		recepcionistaRepository.save(recepcionista);
		return "redirect:cadastra_recepcionistas/";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletarRecepcionista(@PathVariable(value = "id") Integer id) {
		
		Recepcionista recepcionista = recepcionistaRepository.findRecepcionistaByid(id);
		recepcionistaRepository.delete(recepcionista);
		
		return "redirect:/recepcionistas/";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView telaRecepcionista(@PathVariable(value = "id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastra_recepcionistas2");
		Recepcionista recepcionista = recepcionistaRepository.findRecepcionistaByid(id);
		modelAndView.addObject("recepcionista", recepcionista);
		return modelAndView;
	}
	
	@PostMapping("/editar/{id}")
	public String editarRecepcionista(Recepcionista recepcionista) {
		recepcionistaRepository.save(recepcionista);
		return "redirect:/recepcionistas/";
	}
	
}
