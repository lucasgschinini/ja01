package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoletaSinValor;

public interface BoletaSinValorRepositorio  extends JpaRepository<ShvBolBoletaSinValor, Long>{
	
}
