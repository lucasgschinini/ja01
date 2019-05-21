package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdMedioPagoCliente;

public interface CobroOnlineMedioPagoClienteRepositorio  extends JpaRepository<ShvCobEdMedioPagoCliente, Integer>{

	@Modifying
	@Query("delete From ShvCobEdMedioPagoCliente where otrosMedioPago.pk.cobro.idCobro = ?1")
	int borrarMediosPagoClienteDelCobro(Long idCobro);
}
