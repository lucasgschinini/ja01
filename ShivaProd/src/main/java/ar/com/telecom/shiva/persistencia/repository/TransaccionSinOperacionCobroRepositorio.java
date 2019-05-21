package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;


public interface TransaccionSinOperacionCobroRepositorio extends JpaRepository<ShvCobTransaccionSinOperacion, Integer> {
	
}
