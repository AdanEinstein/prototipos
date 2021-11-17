package br.com.adan.oficinamecanica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adan.oficinamecanica.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	public Cliente findClienteByid(Integer id);
}
