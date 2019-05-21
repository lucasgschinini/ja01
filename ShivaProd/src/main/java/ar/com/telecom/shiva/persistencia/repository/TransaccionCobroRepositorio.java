package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;


public interface TransaccionCobroRepositorio extends JpaRepository<ShvCobTransaccion, Integer> {
	
	@Modifying
	@Query("delete From ShvCobTransaccion where idTransaccion = ?1")
	int borrarTransaccionPorIdTransaccion(Integer idTransaccion);
}
