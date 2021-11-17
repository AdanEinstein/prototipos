package br.com.adan.oficinamecanica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.adan.oficinamecanica.modelo.Mecanico;
import br.com.adan.oficinamecanica.repositories.MecanicoRepository;

@Controller
@RequestMapping("/mecanicos")
public class MecanicoController {
	
	@Autowired
	MecanicoRepository mecanicoRepository;
	
	@GetMapping("/")
	public ModelAndView listarMecanicos() {
		ModelAndView modelAndView = new ModelAndView("lista_mecanicos");
		
		List<Mecanico> mecanicos = mecanicoRepository.findAll();
		modelAndView.addObject("mecanicos", mecanicos);
		
		return modelAndView;
	}
	
	@GetMapping("/cadastra_mecanicos")
	public String telaCadastrar(Mecanico mecanico) {
		return "cadastra_mecanicos";
	}
	
	@PostMapping("/cadastra_mecanicos")
	public String cadastrarMecanicos(Mecanico mecanico) {
		ModelAndView modelAndView = new ModelAndView("cadastra_mecanicos");
		mecanico.setSetor("Oficina");
		modelAndView.addObject("mecanico", mecanico);
		mecanicoRepository.save(mecanico);
		return "redirect:cadastra_mecanicos/";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletarMecanico(@PathVariable(value = "id") Integer id) {
		
		Mecanico mecanico = mecanicoRepository.findMecanicoByid(id);
		mecanicoRepository.delete(mecanico);
		
		return "redirect:/mecanicos/";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView telaMecanico(@PathVariable(value = "id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastra_mecanicos2");
		Mecanico mecanico = mecanicoRepository.findMecanicoByid(id);
		modelAndView.addObject("mecanico", mecanico);
		return modelAndView;
	}
	
	@PostMapping("/editar/{id}")
	public String editarMecanico(Mecanico mecanico) {
		mecanicoRepository.save(mecanico);
		return "redirect:/mecanicos/";
	}
	
}
