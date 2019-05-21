package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;

@Repository
public interface MensajeriaTransaccionRepositorio extends JpaRepository<ShvCobTransaccionMensajeriaDetalle, Integer> {

	@Modifying
	@Query("delete From ShvCobTransaccionMensajeriaDetalle where idOperacion = ?1")
	int borrarMensajesPorIdOperacion(Long idOperacion);

	@Modifying
	@Query("delete From ShvCobTransaccionMensajeriaDetalle where idOperacion = ?1 and servicio IN (?2, ?3, ?4)")
	int borrarMensajesPorIdOperacionServicios(Long idOperacion, MensajeServicioEnum servio1, MensajeServicioEnum servio2, MensajeServicioEnum servio3);
	
	@Modifying
	@Query("delete From ShvCobTransaccionMensajeriaDetalle where idOperacion = ?1 and servicio IN (?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17)")
	int borrarMensajesPorIdOperacionServiciosVarios(Long idOperacion, MensajeServicioEnum servio1, MensajeServicioEnum servio2, MensajeServicioEnum servio3, MensajeServicioEnum servio4, MensajeServicioEnum servio5, MensajeServicioEnum servio6, MensajeServicioEnum servio7, MensajeServicioEnum servio8, MensajeServicioEnum servio9, MensajeServicioEnum servio10, MensajeServicioEnum servio11, MensajeServicioEnum servio12, MensajeServicioEnum servio13, MensajeServicioEnum servio14, MensajeServicioEnum servio15, MensajeServicioEnum servio16);
	
	@Modifying
	@Query("delete From ShvCobTransaccionMensajeriaDetalle where idTransaccionMensajeria = ?1")
	int borrarMensajesPorIdTransaccionMensajeria(Integer idTransaccionMensajeria);
}
