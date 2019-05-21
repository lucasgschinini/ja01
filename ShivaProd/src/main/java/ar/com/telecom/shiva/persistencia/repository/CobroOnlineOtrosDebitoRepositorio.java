package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosDebitoPk;
/**
 * @author u578936 M.A.Uehara
 *
 */
public interface CobroOnlineOtrosDebitoRepositorio  extends JpaRepository<ShvCobEdOtrosDebito, ShvCobEdOtrosDebitoPk>{

	@Modifying
	@Query("delete From ShvCobEdOtrosDebito where pk.cobro.idCobro = ?1")
	int borrarOtrosDebitos(Long idCobro);
	
}
