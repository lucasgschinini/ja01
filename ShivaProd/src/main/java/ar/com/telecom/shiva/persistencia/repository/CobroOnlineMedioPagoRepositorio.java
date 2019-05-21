package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;

public interface CobroOnlineMedioPagoRepositorio  extends JpaRepository<ShvCobEdOtrosMedioPago, Integer>{

	@Modifying
	@Query("delete From ShvCobEdOtrosMedioPago where pk.cobro.idCobro = ?1")
	int borrarMediosPagoDelCobro(Long idCobro);
}
