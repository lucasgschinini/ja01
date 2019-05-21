package ar.com.telecom.shiva.negocio.servicios;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.enumeradores.ConfCampoTipoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.servicios.bean.TipoValorBean;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamJurisdiccion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoCobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoLegajo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoSuspension;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamProvincia;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoGestion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoLetraComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoRetencionImpuesto;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public interface ICombosServicio {

	/**
	 * 
	 * @param clase
	 * @return
	 * @throws NegocioExcepcion
	 */
	Collection<?> listar(Class<?> clase) throws NegocioExcepcion;

	/**
	 * 
	 * @param idAcuerdo
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamBancoCuenta buscarBancoCuentaPorIdAcuerdo(String idAcuerdo)	throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamTipoValor> buscarTipoValorBoletaConValor(ValorDto boletaDto)	throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamTipoValor> buscarTipoValorAvisoPago(ValorDto boletaDto) throws NegocioExcepcion;

	/**
	 * 
	 * @param idCuenta
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamBancoCuenta buscarBancoCuentaPorIdCuenta(String idCuenta) throws NegocioExcepcion;

	/**
	 * 
	 * @param idCuenta
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamBanco buscarBancoPorIdCuenta(String idCuenta) throws NegocioExcepcion;

	/**
	 * 
	 * @param idCuenta
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamAcuerdo buscarAcuerdoConciliablePorIdCuenta(String idCuenta) throws NegocioExcepcion;

	/**
	 * 
	 * @param idCuenta
	 * @param esConciliable
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamAcuerdo buscarAcuerdoNoConciliablesYconciliablesPorIdCuenta(String idCuenta, String esConciliable) throws NegocioExcepcion;

	/**
	 * 
	 * @param idBanco
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBancoCuenta> listarCuentaPorIdBanco(String idBanco,String esConciliable) throws NegocioExcepcion;

	/**
	 * 
	 * @param idBanco
	 * @param esConcialiable
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamAcuerdo> listarAcuerdoNoConciliablePorIdBanco(String idBanco, String esConcialiable)throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param tipoValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamOrigen> listarOrigenPorEmpresaSegmentoValor(String idEmpresa, String idSegmento, String tipoValor) throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamOrigen> listarOrigenAnalista() throws NegocioExcepcion;

	/**
	 * 
	 * @param origenId
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamOrigen buscarOrigenPorId(String origenId) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @param tipoValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamAcuerdo> actualizarAcuerdoPorIdOrigenValor(String idEmpresa,String idSegmento, String idOrigen, String tipoValor) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @param tipoValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBanco> actualizarBancoPorIdOrigenValor(String idEmpresa,String idSegmento, String idOrigen, String tipoValor)throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @param tipoValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBancoCuenta> actualizarCuentaPorIdOrigenValor(String idEmpresa, String idSegmento, String idOrigen,String tipoValor) throws NegocioExcepcion;

	/**
	 * 
	 * @param idAcuerdo
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBanco> actualizarBancoPorIdAcuerdo(String idAcuerdo)throws NegocioExcepcion;
	
	List<ShvParamBanco> listarBancosNoConciliables(List<ShvParamAcuerdo> listaAcuerdo)throws NegocioExcepcion;	

	/**
	 * 
	 * @param idAcuerdo
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBancoCuenta> actualizarCuentaPorAcuerdo(String idAcuerdo) throws NegocioExcepcion;
	
	List<ShvParamBancoCuenta> listarCuentaNoConciliables(List<ShvParamAcuerdo> listaAcuerdo) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBanco> listarBancoOrigen(String idEmpresa) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param userSesion
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamSegmento> listarSegmentoPorEmpresaYUsuario(String idEmpresa, UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamOrigen> listarOrigenPorIdSegmento(String idEmpresa, String idSegmento) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamAcuerdo> actualizarAcuerdoPorIdOrigen(String idEmpresa, String idSegmento, String idOrigen) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamAcuerdo> actualizarAcuerdoPorIdSegmento(String idEmpresa, String idSegmento) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param tipoValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamAcuerdo> actualizarAcuerdoNoConciliables(String idEmpresa, String idSegmento, String tipoValor) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBanco> actualizarBancoPorIdOrigen(String idEmpresa, String idSegmento, String idOrigen) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBanco> actualizarBancoPorIdSegmento(String idEmpresa, String idSegmento) throws NegocioExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBanco> listarBancoOrigen() throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBancoCuenta> actualizarCuentaPorIdOrigen(String idEmpresa, String idSegmento, String idOrigen) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBancoCuenta> actualizarCuentaPorIdSegmento(String idEmpresa, String idSegmento) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idMotivoSuspension
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamMotivoSuspension listarMotivoSuspensionPorId(String idMotivoSuspension) throws NegocioExcepcion;
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamMotivoSuspension> listarMotivoSuspension() throws NegocioExcepcion;

	/**
	 * 
	 * @param idAcuerdo
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamBancoCuenta buscarBancoCuentaPorAcuerdo(String idAcuerdo) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idCuenta
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamBancoCuenta buscarBancoCuentaPorCuenta(String idCuenta) throws NegocioExcepcion;

	/**
	 * 
	 * @param idCuenta
	 * @return
	 * @throws NegocioExcepcion
	 */
	ShvParamAcuerdo buscarAcuerdoPorIdCuenta(String idCuenta) throws NegocioExcepcion;

	/**
	 * 
	 * @param idBanco
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamAcuerdo> listarAcuerdoPorBanco(String idBanco) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamOrigen> listarOrigenPorSegmento(String idEmpresa, String idSegmento) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamAcuerdo> actualizarAcuerdoPorIdOrigenBoletaSinValor(String idEmpresa, String idSegmento, String idOrigen) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBanco> actualizarBancoPorIdOrigenBoletaSinValor(String idEmpresa, String idSegmento, String idOrigen) throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param idOrigen
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamBancoCuenta> actualizarCuentaPorIdOrigenBoletaSinValor(String idEmpresa, String idSegmento, String idOrigen) throws NegocioExcepcion;

	/**
	 * 
	 * @param userSesion
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamEmpresa> listarEmpresasPorUsuario(UsuarioSesion userSesion) throws NegocioExcepcion;

	/**
	 * Retorna la lista de copropietarios para una empresa y segmento dados,
	 * teniendo en cuenta no incluir un analista informado, y
	 * presentando siempre usuarios con perfil de Analista.
	 * Estos copropietarios serán usados en el alta de valores (aviso de pago / boleta sin valor). 
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param usuariosExcluidos
	 * @return
	 * @throws NegocioExcepcion
	 */
	Collection<UsuarioLdapDto> listarCopropietarioPorEmpresaYSegmento(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<Estado> listarEstadosTalonario() throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<Estado> listarEstadosCobro() throws NegocioExcepcion;
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamProvincia> listarProvincias() throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamJurisdiccion> listarProvinciasEnJurisdiccion() throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamTipoComprobante> listarTipoComprobante() throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamTipoLetraComprobante> listarLetraComprobante() throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamTipoRetencionImpuesto> listarTipoImpuesto() throws NegocioExcepcion;

	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param tipoValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamAcuerdo> actualizarAcuerdoPorValor(String idEmpresa, String idSegmento, String tipoValor) throws NegocioExcepcion;
	
	public ShvParamEmpresa buscarEmpresa(String idEmpresa) throws NegocioExcepcion;
	
	public ShvParamBanco buscarBanco(String idBanco) throws NegocioExcepcion;
	
	public <T> T buscarPorId(Class<T> clase, String campo, String id) throws NegocioExcepcion;
	public List<Estado> listarEstadosRegistroAVC() throws NegocioExcepcion;

	List<Estado> listarEstadosRegistroAVCYReversionesPendientes() throws NegocioExcepcion;
	
	
	/**
	 * Permite obtener la lista de motivos de cobro
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamMotivoCobro> listarMotivosConfiguracionCobro() throws NegocioExcepcion;
	
	/**
	 * Permite obtener la lista de motivos de operacion masiva
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamMotivoCobro> listarMotivosOperacionMasiva() throws NegocioExcepcion;
	
	/**
	 * Permite obtener la lista de motivos de operacion masiva
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamMotivoCobro> listarMotivosBusquedaCobros() throws NegocioExcepcion;
	
	/**
	 * Permite obtener la lista de motivos de legajo
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<ShvParamMotivoLegajo> listarMotivosConfiguracionLegajo() throws NegocioExcepcion;
	
	/**
	 * Retorna la lista de copropietarios para una empresa y segmento dados,
	 * teniendo en cuenta no incluir un analista informado, y
	 * presentando siempre usuarios con perfil de Analista de Cobranza.
	 * Estos copropietarios serán usados en la configuracion de un cobro
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param usuariosExcluidos
	 * @return
	 * @throws NegocioExcepcion
	 */
	Collection<UsuarioLdapDto> listarCopropietarioCobroPorEmpresaYSegmento(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws NegocioExcepcion;

	/**
	 * Retorna la lista de copropietarios para una empresa y segmento dados,
	 * teniendo en cuenta no incluir un analista informado, y
	 * presentando siempre usuarios con perfil de Analista de Cobranza.
	 * Estos copropietarios serán usados en la configuracion de una operacion masiva
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param usuariosExcluidos
	 * @return
	 * @throws NegocioExcepcion
	 */
	Collection<UsuarioLdapDto> listarCopropietarioOperacionMasivaPorEmpresaYSegmento(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws NegocioExcepcion;


	public List<ShvParamAcuerdo> listarAcuerdo(String idEmpresa) throws NegocioExcepcion;
	public List<ShvParamAcuerdo> listarAcuerdo(String idEmpresa, String idSegmento) throws NegocioExcepcion;
	
	public List<ShvParamBanco> listarBancoOrigenOrdenadoPorDescripcion(String idEmpresa) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idEmpresa
	 * @param idSegmento
	 * @param usuariosExcluidos
	 * @return
	 * @throws NegocioExcepcion
	 */
	public Collection<UsuarioLdapDto> listarCopropietarioLegajoChequeRechazadoPorEmpresaYSegmento(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws NegocioExcepcion;

	public List<Estado> listarEstadosLegajo() throws NegocioExcepcion;
	/**
	 * 
	 * @param idEmpresa
	 * @param idOrigen
	 * @param listaValores
	 * @param consiliable
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ShvParamTipoGestion> listarShvParamTipoGestion(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws NegocioExcepcion;
	/**
	 * 
	 * @param idEmpresa
	 * @param tipoTipoValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<TipoValorBean> buscarTiposValor(String idEmpresa, String tipoTipoValor) throws NegocioExcepcion;
	
	/**
	 * 
	 * @param idEmpresa
	 * @param idOrigen
	 * @param listaValores
	 * @param consiliable
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Map<String, Object>>  listarShvParamTipoGestionDb(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws NegocioExcepcion;

	/**
	 * 
	 * @param monedaOperacion
	 * @param tipoConfiguracion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Map<String, Object>> obtnerConfiguracionRegla(MonedaEnum monedaOperacion, ConfCampoTipoEnum tipoConfiguracion) throws NegocioExcepcion;

}
