package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobTransaccionSimple;


public interface TransaccionSimpleCobroRepositorio extends JpaRepository<ShvCobTransaccionSimple, Integer> {
	
	@Query("FROM ShvCobTransaccionSimple where idTransaccion = ?1")
	ShvCobTransaccionSimple buscarTransaccionCobroPorTransaccion(Integer idTransaccion);
	
	@Query("FROM ShvCobTransaccionSimple where idOperacion = ?1 and numeroTransaccion = ?2")
	ShvCobTransaccionSimple buscarTransaccionCobroPorOperacionyNumero(Long idOperacion, Integer numeroTransaccion);

	@Query("FROM ShvCobTransaccionSimple where idOperacion = ?1 and idTransaccion = ?2 ")
	ShvCobTransaccionSimple buscarTransaccionCobroPorOperacionyTransaccion(Long idOperacion, Integer idTransaccion);
}
