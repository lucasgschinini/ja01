package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;

public interface CobroOnlineRepositorio  extends JpaRepository<ShvCobEdCobro, Integer>{

	@Query("FROM ShvCobEdCobro where idCobro = ?1")
	ShvCobEdCobro buscarCobroOnline(Long idCobro);
	
	@Query("FROM ShvCobEdCobro where idOperacion = ?1")
	ShvCobEdCobro buscarCobroPorIdOperacion(Long idOperacion);

}
