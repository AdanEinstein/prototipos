package br.com.adan.oficinamecanica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adan.oficinamecanica.modelo.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{

	public Servico findServicoByid(Integer id);
}
