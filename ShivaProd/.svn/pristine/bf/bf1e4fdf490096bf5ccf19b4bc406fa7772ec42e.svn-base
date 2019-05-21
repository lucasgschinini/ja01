package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.base.enumeradores.EstadoRegistroOperacionMasivaEnum;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;

public interface RegistroOperacionMasivaRepositorio extends JpaRepository<ShvMasRegistro, Long>{

	@Query("Select smr FROM ShvMasRegistro smr where smr.estado = ?1 and smr.idOperacion = ?2")
	public ShvMasRegistro buscarRegistroByEstadoAndIdOperacion(EstadoRegistroOperacionMasivaEnum estado, Long idOperacion);
	
	@Query("Select smr FROM ShvMasRegistro smr left join smr.operacionMasiva om where smr.estado = ?1 and om.idOperacionMasiva = ?2")
	public List<ShvMasRegistro> buscarRegistroByEstadoAndIdOperacionMasiva(EstadoRegistroOperacionMasivaEnum estado, Long idOperacionMasiva);
	
	@Query("Select smr FROM ShvMasRegistro smr left join smr.operacionMasiva om where smr.estado <> ?1 and om.idOperacionMasiva = ?2")
	public List<ShvMasRegistro> buscarRegistrosXOperacionMasivaDistintosAEstado(EstadoRegistroOperacionMasivaEnum estado, Long idOperacionMasiva);
}