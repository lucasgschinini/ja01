package ar.com.telecom.shiva.persistencia.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.base.enumeradores.EstadoNotificacionEnum;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjNotificacionSimplificado;

/**
 * @author u578936 M.A.Uehara
 *
 */
public interface LegajoChequeRechazadoNotificacionSimplificadoRepositorio extends JpaRepository<ShvLgjNotificacionSimplificado, Long> {
	@Query("From ShvLgjNotificacionSimplificado notificacion where notificacion.idLegajoChequeRechazado = ?1 AND notificacion.estado != 'ELIMINADA' ORDER BY notificacion.fechaContacto")
	public List<ShvLgjNotificacionSimplificado> listar(Long idLegajoChequeRechazado);

	@Query("UPDATE ShvLgjNotificacionSimplificado set estado = ?3, fechaModificacion = ?2 where idNotificacion = ?1")
	@Modifying
	public int cambiarEstado(Long idNotificacion, Date fechaModificacion, EstadoNotificacionEnum estado);
}
