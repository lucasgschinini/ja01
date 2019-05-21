package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamConfCampo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamParametro;

/**
 * @author u578936 M.A.Uehara
 *
 */
public interface ParametroConfReglaCampoRepositorio extends JpaRepository<ShvParamParametro, Long> {

	@Query("select imp FROM ShvParamConfCampo as imp where imp.campoImportacion = ?1 and imp.activo = ?2 and imp.ordenCampos IS NOT NULL order by imp.ordenCampos")
	List<ShvParamConfCampo> listarCamposImportacion(SiNoEnum si, SiNoEnum activo);
	
	@Query("select imp FROM ShvParamConfCampo as imp where imp.ordenCampos IS NOT NULL order by imp.ordenCampos")
	List<ShvParamConfCampo> listarCamposOrden();
	
}
