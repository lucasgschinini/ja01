package ar.com.telecom.shiva.persistencia.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaBoletaDeposito;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoChequePagoDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorChequePagoDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorInterdeposito;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorPorReversion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorRetencion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorTransferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValoresVista;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimplificado;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaConValorFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public interface IValorDao {

	ShvValValorSimple buscarValorSimple(String id) throws PersistenciaExcepcion;
	
	ShvValValor insertarValor(ShvValValor valor) throws PersistenciaExcepcion;

	List<ShvValValor> listarTodasBoletas() throws PersistenciaExcepcion;

	ShvValValor actualizarValor(ShvValValor boleta) throws PersistenciaExcepcion;
	
	Long proximoNumeroValor() throws PersistenciaExcepcion;

	ShvValValor buscarValor(String idValor) throws PersistenciaExcepcion;
	
	ShvValValor buscarBoletaConValor(Long idValor)	throws PersistenciaExcepcion;
	
	List<ShvValValor> listarTodasBoletasConValor() throws PersistenciaExcepcion;
	
	ShvValValor buscarValorPadreAsociadoAlValorDelChequeAnulado(Long idValor) throws PersistenciaExcepcion;

	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
	 * 
	 * @param bancoOrigen
	 * @param numeroCheque
	 * @param importe
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvValBoletaDepositoCheque> buscarValoresBoletaChequeDuplicados(String bancoOrigen,String numeroCheque, String importe) throws PersistenciaExcepcion;

	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
	 * 
	 * @param bancoOrigen
	 * @param numeroCheque
	 * @param importe
	 * @param fechaVencimiento
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvValBoletaDepositoChequePagoDiferido> buscarValoresBoletaChequeDiferidoDuplicados(String bancoOrigen,String numeroCheque, String importe, String fechaVencimiento) throws PersistenciaExcepcion;

	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
	 * 
	 * @param bancoOrigen
	 * @param numeroCheque
	 * @param importe
	 * @param fechaDeposito
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvValValorCheque> buscarValoresChequeDuplicados(String bancoOrigen,String numeroCheque, String importe, String fechaDeposito) throws PersistenciaExcepcion;

	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
	 * 
	 * @param bancoOrigen
	 * @param numeroCheque
	 * @param importe
	 * @param fechaDeposito
	 * @param fechaVencimiento
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvValValorChequePagoDiferido> buscarValoresChequeDiferidoDuplicados(String bancoOrigen,String numeroCheque, String importe, String fechaDeposito, String fechaVencimiento) throws PersistenciaExcepcion;
	
	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
	 * 
	 * @param numeroReferencia
	 * @param fechaTransferencia
	 * @param importe
	 * @param es87
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvValValorTransferencia> buscarValoresTransferenciaDuplicados(String numeroReferencia,String fechaTransferencia, String importe, boolean es87, String acuerdo) throws PersistenciaExcepcion;
	
	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
	 * 
	 * @param tipoRetencion
	 * @param numeroConstancia
	 * @param CodigoCliente
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvValValorRetencion> buscarValoresRetencionImpuestoDuplicados(String tipoRetencion,String numeroConstancia, String CodigoCliente) throws PersistenciaExcepcion;

	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
	 * 
	 * @param codCliente
	 * @param numeroInterdeposito
	 * @param fechaInterdeposito
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvValValorInterdeposito> buscarValoresInterdepositoDuplicados(String codCliente,String numeroInterdeposito, String fechaInterdeposito) throws PersistenciaExcepcion;
	
	ArrayList<ShvValValor> buscarValores(UsuarioSesion userSesion,	BoletaConValorFiltro boletaFiltro) throws PersistenciaExcepcion;
	
	ArrayList<ShvValValor> buscarValoresParaAutorizar(UsuarioSesion userSesion,	BoletaConValorFiltro boletaFiltro) throws PersistenciaExcepcion;
	
	List <Map<String, Object>> consultaChequesReemplazar(String codigoCliente)throws PersistenciaExcepcion;
	
	List<ShvValValor> buscarValores(List<Long> idValores) throws PersistenciaExcepcion;

	List<ShvValValor> buscarValoresPorRecibo(String reciboPreImpreso, String atributoValor) throws PersistenciaExcepcion;

	Collection<ResultadoBusquedaBoletaDeposito> buscarValorBoleta() throws PersistenciaExcepcion;

	Long getIdValorAsociadoABoleta(String numeroBoleta, String tipoValor) throws PersistenciaExcepcion;
	
	Long obtenerIdValorAsociadoABoleta(Long idBoleta) throws PersistenciaExcepcion;

	ShvValValor buscarValorTipoDepositoPorIdBoleta(Long idBoleta) throws PersistenciaExcepcion;

	List<ShvValValorSimple> listarCodigosClienteLegadoBoleta(String usuarioLogueado)throws PersistenciaExcepcion;
	
	List<ShvValValorSimple> listarCodigosClienteLegadoAviso(String usuarioLogueado)throws PersistenciaExcepcion;
	
	List<ShvValValorSimple> listarCodigosClienteLegadoUser(String usuarioLogueado) throws PersistenciaExcepcion;

	/**
	 * Solo se usa para alta de valores por reversion
	 * @param valorDto
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	List<ShvValValor> validarUnicidadValor(ValorDto valorDto) throws PersistenciaExcepcion;

	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
	 * 
	 * @param valorDto
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	boolean reValidarUnicidadValor(ValorDto valorDto) throws PersistenciaExcepcion;


	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método es el que debe quedar cuando se habilite la mejora en el próximo Release, eliminando los anteriores.
	 * 
	 * @param valorDto
	 * @param incluirValorActualEnComparacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public boolean validarUnicidadValor(ValorDto valorDto, boolean incluirValorActualEnComparacion) throws PersistenciaExcepcion;
	
	ShvValValor buscarValorCreadoAPartirRegistroAvc(String idRegistro) throws PersistenciaExcepcion;

	ShvValValorPorReversion insertarValorAux(ShvValValorPorReversion valor) throws PersistenciaExcepcion;

// TODO este metodo se comento para volver atras 
//	List<ShvValValor> listarMorosidadValores(Filtro morosidadFiltro) throws PersistenciaExcepcion;
//	TODO este metodo se reemplaza para volver atras
	List<ShvValValoresVista> listarMorosidadValores(Filtro morosidadFiltro) throws PersistenciaExcepcion;
	
	List<ShvValValor> buscarRetencionesParaReporte(Filtro retencionFiltro) throws PersistenciaExcepcion;

	List<ShvValValorSimplificado> listarValoresSimplificados(List<Long> idValores) throws PersistenciaExcepcion;
	
	ShvValValorSimplificado buscarValorSimplificado(Long idValor) throws PersistenciaExcepcion;
	
	ShvValValorSimplificado actualizarValorSimplificado(ShvValValorSimplificado valor) throws PersistenciaExcepcion;

	Date buscarFechaModificacionXIdValor(Long idValor) throws PersistenciaExcepcion;
	
}

