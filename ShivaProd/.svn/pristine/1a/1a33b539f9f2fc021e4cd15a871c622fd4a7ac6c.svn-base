package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;

public interface CobroOnlineClienteRepositorio  extends JpaRepository<ShvCobEdCliente, Integer>{
	
	
	@Query("from ShvCobEdCliente where pk.cobro.idCobro = ?1")
	List<ShvCobEdCliente> listarClientesPorIdCobro(Long idCobro);

	@Query("from ShvCobEdCliente where pk.cobro.idOperacion = ?1")
	List<ShvCobEdCliente> listarClientesPorIdOperacionCobro(Long idOperacion);
	
	@Modifying
	@Query("delete From ShvCobEdCliente where pk.cobro.idCobro = ?1")
	int borrarClientesDelCobro(Long idCobro);
	
	@Query("from ShvCobEdCliente where pk.cobro.idCobro = ?1 and idClienteLegado =?2")
	ShvCobEdCliente buscarClientePorIdCobroYIdClienteLegado(Long idCobro, Long idClienteLegado);

	@Query("from ShvCobEdCliente where pk.cobro.idOperacion = ?1 and idClienteLegado =?2")
	ShvCobEdCliente buscarClientePorIdOperacionCobroYIdClienteLegado(Long idOperacion, Long idClienteLegado);
	
	@Query("from ShvCobEdCliente where pk.cobro.idCobro in (select descobro.idCobro from ShvCobDescobro descobro where descobro.operacion.idOperacion = ?) and idClienteLegado =?2")
	ShvCobEdCliente buscarClientePorIdOperacionDesCobroYIdClienteLegado(Long idOperacion, Long idClienteLegado);
}
