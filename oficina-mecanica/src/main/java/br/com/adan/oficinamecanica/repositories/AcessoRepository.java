package br.com.adan.oficinamecanica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.adan.oficinamecanica.modelo.Mecanico;
import br.com.adan.oficinamecanica.modelo.Recepcionista;
import br.com.adan.oficinamecanica.modelo.acesso.Acesso;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Integer>{
	
	@Query("SELECT m FROM Mecanico m WHERE matricula = :matricula")
	public Mecanico consultarMatriculaMecanico(@Param(value = "matricula") String matricula);
	
	@Query("SELECT r FROM Recepcionista r WHERE matricula = :matricula")
	public Recepcionista consultarMatriculaRecepcionista(@Param(value = "matricula") String matricula);
	
	public Acesso findAcessoByid(Integer id);
	
	@Query("SELECT a FROM Acesso a WHERE matricula LIKE :matricula")
	public List<Acesso> findMatricula(@Param(value = "matricula") String matricula);
	
	@Query("SELECT a FROM Acesso a WHERE matricula LIKE :matricula AND senha LIKE :senha")
	public Acesso encontrarAcesso(@Param(value = "matricula") String matricula, 
			@Param(value = "senha") String senha);

	@Query("SELECT count(a) FROM Acesso a WHERE matricula LIKE :matricula")
	public Integer encontrarAcesso(@Param(value = "matricula") String matricula);
	
	
}
