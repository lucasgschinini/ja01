package ar.com.telecom.shiva.persistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvParamTeamComercial;

public interface TeamComercialRepositorio extends JpaRepository<ShvParamTeamComercial, Long>{
	
	@Modifying
	@Query("DELETE ShvParamTeamComercial where empresa=?1 and origen=?2")
	public void borrar(EmpresaEnum empresa, ClienteOrigenEnum origen) throws PersistenciaExcepcion;
	
}
