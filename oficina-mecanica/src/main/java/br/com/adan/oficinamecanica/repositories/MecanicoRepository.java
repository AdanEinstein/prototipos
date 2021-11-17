package br.com.adan.oficinamecanica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adan.oficinamecanica.modelo.Mecanico;

@Repository
public interface MecanicoRepository extends JpaRepository<Mecanico, Integer>{

	public Mecanico findMecanicoByid(Integer id);

}
