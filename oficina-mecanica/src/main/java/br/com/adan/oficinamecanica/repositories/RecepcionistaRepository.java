package br.com.adan.oficinamecanica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adan.oficinamecanica.modelo.Recepcionista;

@Repository
public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Integer>{

	public Recepcionista findRecepcionistaByid(Integer id);

}
