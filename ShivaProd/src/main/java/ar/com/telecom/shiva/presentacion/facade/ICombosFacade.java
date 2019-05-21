package ar.com.telecom.shiva.presentacion.facade;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.dto.BancosParametroDto;
import ar.com.telecom.shiva.negocio.dto.ComboAcuerdoBancoCuentaDto;
import ar.com.telecom.shiva.negocio.dto.ComboAcuerdoBancoCuentaGralDto;
import ar.com.telecom.shiva.negocio.dto.ParamConfReglaCampo;
import ar.com.telecom.shiva.negocio.dto.ProvinciaParametroDto;
import ar.com.telecom.shiva.negocio.dto.TipoValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.EstadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.SelectOptionJsonResponse;


public interface ICombosFacade extends IFacade {
	/**
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ComboAcuerdoBancoCuentaDto listarAcuerdo(String idEmpresa) throws NegocioExcepcion;
	/**
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws NegocioExcepcion
	 */
	public BancosParametroDto listaBancosAgrupadosPorDescipcion(String idEmpresa) throws NegocioExcepcion;
	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param usuariosExcluidos
	 * @return
	 * @throws Exception
	 */
	public @ResponseBody List<SelectOptionJsonResponse> listarCopropietarioLegajoChequeRechazadoPorEmpresaYSegmento(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws Exception;
	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param usuariosExcluidos
	 * @return
	 * @throws Exception
	 */
	public @ResponseBody List<SelectOptionJsonResponse> listarCopropietarioPorEmpresaYSegmento(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws Exception;
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<EstadoDto> listarEstadosLegajo() throws NegocioExcepcion;
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ProvinciaParametroDto> listarProvincias() throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idEmpresa
	 * @param idOrigen
	 * @param listaValores
	 * @param consiliable
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ComboAcuerdoBancoCuentaGralDto listarShvParamTipoGestion(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws NegocioExcepcion;
		
	
	/**
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ComboAcuerdoBancoCuentaGralDto listar(String idEmpresa) throws NegocioExcepcion;
	
	
	public List<TipoValorDto> buscarTiposValor(String idEmpresa, String tipoTipoValor) throws NegocioExcepcion;
	
	public ComboAcuerdoBancoCuentaGralDto listarShvParamTipoGestionDb(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param monedaOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Map<MonedaEnum, Map<SociedadEnum, Map<SistemaEnum, Map<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>>>>> listarCombosCamposOtrosDebito(MonedaEnum monedaOperacion) throws NegocioExcepcion;
	
	public List<TipoTratamientoDiferenciaEnum> listarComboTratamientoDiferenciaCred();
}
