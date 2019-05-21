package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;

public interface CobroOnlineCreditoRepositorio  extends JpaRepository<ShvCobEdCredito, Integer>{

	@Modifying
	@Query("delete From ShvCobEdCredito where pk.cobro.idCobro = ?1")
	int borrarCreditosDelCobro(Long idCobro);
}
