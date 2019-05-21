package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacion;

/**
 * @author u578936 M.A.Uehara
 *
 */
public interface LegajoChequeRechazadoNotificacionRepositorio extends JpaRepository<ShvLgjNotificacion, Long> {
}
