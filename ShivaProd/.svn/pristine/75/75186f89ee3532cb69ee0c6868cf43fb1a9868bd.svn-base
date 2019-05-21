package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroSimplificadoCodigoExternoOperacion;

public interface DescobroSimplificadoCodigoOperacionExternaRepositorio extends JpaRepository<ShvCobDescobroSimplificadoCodigoExternoOperacion, Long> {

	@Query("FROM ShvCobDescobroSimplificadoCodigoExternoOperacion where operacion.idOperacion = ?1 ")
	public ShvCobDescobroSimplificadoCodigoExternoOperacion find(Long operacion);
}
