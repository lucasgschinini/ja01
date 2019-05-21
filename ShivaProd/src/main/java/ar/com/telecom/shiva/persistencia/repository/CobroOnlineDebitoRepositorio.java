package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebitoPk;

public interface CobroOnlineDebitoRepositorio  extends JpaRepository<ShvCobEdDebito, ShvCobEdDebitoPk>{
	
	
	@Query("FROM ShvCobEdDebito where idDebito = ?1")
	ShvCobEdDebito buscarDebito(Long idDebito);
	
	@Query("select deb FROM ShvCobEdDebito as deb join deb.pk.cobro as c join c.workflow.shvWfWorkflowEstado as we where deb.resultadoValidacionEstado = 'PENDIENTE' and we.estado = 'COB_EN_EDICION_VERIFCANDO_DEBITOS' order by c.idCobro")
	List<ShvCobEdDebito> listarDebitosPendienteValidacion();

	@Modifying
	@Query("delete From ShvCobEdDebito where pk.cobro.idCobro = ?1")
	int borrarDebitosDelCobro(Long idCobro);
	
}
