package br.com.adan.oficinamecanica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.adan.oficinamecanica.modelo.Cliente;
import br.com.adan.oficinamecanica.repositories.ClienteRepository;

@Controller
@RequestMapping(path = "clientes")
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping(path = "/")
	public ModelAndView listarClientes() {
		ModelAndView modelAndView = new ModelAndView("lista_clientes");
		
		List<Cliente> clientes = clienteRepository.findAll();
		
		modelAndView.addObject("clientes", clientes);
		
		return modelAndView;
	}
	
	@GetMapping("/cadastra_clientes")
	public String telaCadastrar(Cliente cliente) {
		return "cadastra_clientes";
	}
	
	@PostMapping("/cadastra_clientes")
	public String cadastrarClientes(Cliente cliente) {
		ModelAndView modelAndView = new ModelAndView("cadastra_clientes");
		modelAndView.addObject("cliente", cliente);
		clienteRepository.save(cliente);
		return "redirect:cadastra_clientes/";
	}
	
	@GetMapping("/deletar/{id}")
	public String deletarCliente(@PathVariable(value = "id") Integer id) {
		
		Cliente cliente = clienteRepository.findClienteByid(id);
		clienteRepository.delete(cliente);
		
		return "redirect:/clientes/";
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView telaCliente(@PathVariable(value = "id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastra_clientes2");
		Cliente cliente = clienteRepository.findClienteByid(id);
		modelAndView.addObject("cliente", cliente);
		return modelAndView;
	}
	
	@PostMapping("/editar/{id}")
	public String editarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
		return "redirect:/clientes/";
	}
}












