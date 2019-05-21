/**
 * 
 */
package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoChequeRechazadoDetalleCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoConsultaSaldoChequeRechazadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoContabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoSuspensionEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenContableEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturacionIceEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.enumeradores.UbicacionChequeEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.NoSePuedeRecuperarDetalleCobrosChequeIceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionUnicidadLegajoChequeRechazadoExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionFactura;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionPaginadoDebito;
import ar.com.telecom.shiva.base.jms.util.runnable.ConsultaSaldoACobradoresChequeRechazadoThread;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaIceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.Cliente;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.InformacionParaPaginadoDebito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaIceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ice.ClienteCheque;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ice.IceCheques;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteIceServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.bean.NroDocumentoLegal;
import ar.com.telecom.shiva.negocio.bean.ValidacionesAuxiliar;
import ar.com.telecom.shiva.negocio.mapeos.LegajoChequeRechazadoCobroRelacionadoDocumentoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.LegajoChequeRechazadoCobroRelacionadoMapeador;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.IContabilidadServicio;
import ar.com.telecom.shiva.negocio.servicios.IExcelLegajoServicio;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoReversionIceServicio;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.bean.ConsultaSoporteResultadoBusquedaChequeRechazado;
import ar.com.telecom.shiva.negocio.servicios.paginado.IPaginadorServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.ILegajoChequeRechazadoValidacionServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.EdicionTipoEnum;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowLegajosChequesRechazados;
import ar.com.telecom.shiva.negocio.workflow.servicios.util.IObservacionesWorkflowServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoDetalleDocumento;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoAdjuntoDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.ILegajoChequeRechazadoDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjAdjuntoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleDocumento;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjChequeRechazadoDetalleCobroSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjChequeRechazadoDetalleDocumentoSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjLegajoChequeRechazadoSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjLegajoChequeRechazadoSimplificadoWorkFlow;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ContabilidadDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConsultaSoporteBusquedaChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.ChequeRechazadoControlPaginacion;

/**
 * @author u564030
 *
 */
public class LegajoChequeRechazadoServicioImpl extends Servicio implements ILegajoChequeRechazadoServicio {

	@Autowired
	private IWorkflowLegajosChequesRechazados workflowLegajoChequesRechazadosServicio;
	@Autowired
	private ILegajoChequeRechazadoDao legajoChequeRechazadoDao;
	@Autowired
	private IValorDao valorDao;
	@Autowired
	private IClienteIceServicio clienteIceServicio;
	@Autowired
	private IValorServicio valorServicio;
	@Autowired
	private IGenericoDao genericoDao;
	@Autowired 
	private IDocumentoAdjuntoDao documentoAdjuntoDao;
	@Autowired
	private IVistaSoporteServicio vistaSoporteServicio;
	@Autowired
	private IContabilidadServicio contabilidadServicio;
	@Autowired
	private IClienteSiebelServicio clienteConsultarSiebelServicio;
	@Autowired
	private IPaginadorServicio chequeRechazadoPaginadoServicio;
	@Autowired
	private ILegajoChequeRechazadoValidacionServicio legajoChequeRechazadoValidacionServicio;
	@Autowired
	private LegajoChequeRechazadoCobroRelacionadoMapeador legajoChequeRechazadoCobroRelacionadoMapeador;
	@Autowired
	private LegajoChequeRechazadoCobroRelacionadoDocumentoMapeador legajoChequeRechazadoCobroRelacionadoDocumentoMapeador;
	@Autowired
	private IExcelLegajoServicio exportacionDetalleLegajo;
	@Autowired
	private IClienteServicio clienteServicio;
	@Autowired
	private ILegajoChequeRechazadoReversionIceServicio legajoChequeRechazadoReversionIceServicio;
	
	
	
	
	@Autowired private IObservacionesWorkflowServicio observacionesWorkflowServicio;
	
	public List<ShvLgjChequeRechazadoDetalleDocumentoSimplificado> listaDocumentos = new ArrayList<ShvLgjChequeRechazadoDetalleDocumentoSimplificado>();
	public List<ShvLgjChequeRechazadoDetalleDocumentoSimplificado> listaDocumentosRepetidos = new ArrayList<ShvLgjChequeRechazadoDetalleDocumentoSimplificado>();
	public List<ShvLgjChequeRechazadoDetalleDocumentoSimplificado> listaDocMic = new ArrayList<ShvLgjChequeRechazadoDetalleDocumentoSimplificado>();
	public List<ShvLgjChequeRechazadoDetalleDocumentoSimplificado> listaDocCalipso = new ArrayList<ShvLgjChequeRechazadoDetalleDocumentoSimplificado>();
	public List<ConsultaSaldoACobradoresChequeRechazadoThread> listaThreads = new ArrayList<ConsultaSaldoACobradoresChequeRechazadoThread>();
	public List<String> listaNumDocumentoCLP = new ArrayList<String>();
	public List<String> listaNumReferenciaMIC = new ArrayList<String>();
	public final int LIMITE_MIC=30;
	public final int LIMITE_CALIPSO = 15;

	@Override
	public List<Bean> listarCheques(Filtro filtro, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion {
		ConsultaSoporteBusquedaChequeRechazadoFiltro chequeRechazadoFiltro = (ConsultaSoporteBusquedaChequeRechazadoFiltro) filtro;

		legajoChequeRechazadoValidacionServicio.validarFiltroCheques(chequeRechazadoFiltro);

		
		chequeRechazadoFiltro.setIdClientesLegado(this.completarFiltroConClientes(chequeRechazadoFiltro.obtenerClienteSiebelFiltro()));
		

		ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion = new ChequeRechazadoControlPaginacion();
		chequeRechazadoControlPaginacion.setFiltro(chequeRechazadoFiltro);
		chequeRechazadoFiltro.setChequeRechazadoControlPaginacion(chequeRechazadoControlPaginacion);
		return chequeRechazadoPaginadoServicio.buscar(chequeRechazadoControlPaginacion, validacionesAuxiliar);
	}

	@Override
	public List<Bean> paginarCheques(Filtro filtro, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion {
		ConsultaSoporteBusquedaChequeRechazadoFiltro chequeRechazadoFiltro = (ConsultaSoporteBusquedaChequeRechazadoFiltro) filtro;

		return chequeRechazadoPaginadoServicio.paginado(
			chequeRechazadoFiltro.getChequeRechazadoControlPaginacion(),
			chequeRechazadoFiltro.getPaginadorAccion(),
			validacionesAuxiliar
		);
	}

	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	protected List<ConsultaSoporteResultadoBusquedaChequeRechazado> listarChequesShiva(Filtro filtro) throws NegocioExcepcion {
		VistaSoporteBusquedaValoresFiltro vistaSoporteBusquedaValoresFiltro = (ConsultaSoporteBusquedaChequeRechazadoFiltro) filtro;
		List<ConsultaSoporteResultadoBusquedaChequeRechazado> lista = new ArrayList<ConsultaSoporteResultadoBusquedaChequeRechazado>();
		
		if (
			Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getSistema()) ||
			SistemaEnum.SHIVA.getDescripcionCorta().equals(vistaSoporteBusquedaValoresFiltro.getSistema())
		) {
			try {
				vistaSoporteBusquedaValoresFiltro.setCobroOnlineCreditosShiva(false);
				vistaSoporteBusquedaValoresFiltro.getListaTipoValor().add(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor()));
				vistaSoporteBusquedaValoresFiltro.getListaTipoValor().add(String.valueOf(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor()));
				if (Validaciones.isNullEmptyOrDash(vistaSoporteBusquedaValoresFiltro.getFechaVencimiento())) {
					vistaSoporteBusquedaValoresFiltro.getListaTipoValor().add(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor()));
					vistaSoporteBusquedaValoresFiltro.getListaTipoValor().add(String.valueOf(TipoValorEnum.CHEQUE.getIdTipoValor()));
				}
				vistaSoporteBusquedaValoresFiltro.setSqlOrder(" ORDER By id_valor DESC, ID_BANCO_ORIGEN, REFERENCIA_VALOR ");
				
				vistaSoporteBusquedaValoresFiltro.getIdEstados().add(Estado.VAL_DISPONIBLE.name());
				vistaSoporteBusquedaValoresFiltro.getIdEstados().add(Estado.VAL_USADO.name());

				List<VistaSoporteResultadoBusquedaValor> listaCheques = vistaSoporteServicio.buscarValores(vistaSoporteBusquedaValoresFiltro);
	
				for (VistaSoporteResultadoBusquedaValor vistaSoporteResultadoBusquedaValor: listaCheques) {
					ConsultaSoporteResultadoBusquedaChequeRechazado chequeRechazado = new ConsultaSoporteResultadoBusquedaChequeRechazado();
					
					chequeRechazado.setSistemaOrigen(SistemaEnum.SHIVA);
					chequeRechazado.setTipoCheque(vistaSoporteResultadoBusquedaValor.getTipoValor());
					chequeRechazado.setIdTipoCheque(vistaSoporteResultadoBusquedaValor.getIdTipoValor());
					chequeRechazado.setCodBancoOrigen(vistaSoporteResultadoBusquedaValor.getIdBancoOrigen());
					chequeRechazado.setDescripcionBancoOrigen(vistaSoporteResultadoBusquedaValor.getDescripcionBancoOrigen());
					chequeRechazado.setNroCheque(vistaSoporteResultadoBusquedaValor.getReferenciaValor());
					chequeRechazado.setFechaDeposito(vistaSoporteResultadoBusquedaValor.getFechaDeposito());
					//chequeRechazado.setFechaRecepcion(vistaSoporteResultadoBusquedaValor.getFe());
					chequeRechazado.setFechaVenc(vistaSoporteResultadoBusquedaValor.getFechaVencimiento());
					chequeRechazado.setMoneda(MonedaEnum.PES);
					chequeRechazado.setImporte(vistaSoporteResultadoBusquedaValor.getImporte());
					chequeRechazado.setAcuerdo(vistaSoporteResultadoBusquedaValor.getIdAcuerdo());
					chequeRechazado.setBancoDeposito(vistaSoporteResultadoBusquedaValor.getBancoDeposito());
					chequeRechazado.setCuentaDeposito(vistaSoporteResultadoBusquedaValor.getCuentaDeposito());
					chequeRechazado.setEstado(vistaSoporteResultadoBusquedaValor.getEstadoValor());
					chequeRechazado.setEmpresa(vistaSoporteResultadoBusquedaValor.getEmpresa());
					chequeRechazado.setSegmento(vistaSoporteResultadoBusquedaValor.getSegmento());
					chequeRechazado.setAnalista(vistaSoporteResultadoBusquedaValor.getIdAnalista());
					chequeRechazado.setAnalistaTeamComercial(vistaSoporteResultadoBusquedaValor.getIdAnalistaTeamComercial());
					chequeRechazado.setIdInternoValor(vistaSoporteResultadoBusquedaValor.getIdValor());
					
					chequeRechazado.setSaldoDisponible(vistaSoporteResultadoBusquedaValor.getSaldoDisponible());
					lista.add(chequeRechazado);
				}
			} catch (Exception e) {
				throw new NegocioExcepcion(e);
			}
		}
		return lista;
	}
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws NegocioExcepcion
	 */
	protected List<ConsultaSoporteResultadoBusquedaChequeRechazado> listarChequesIce(Filtro filtro) throws NegocioExcepcion {
		return new ArrayList<ConsultaSoporteResultadoBusquedaChequeRechazado>();
	}


	/**
	 * 
	 * @param legajoChequeRechazado
	 * @throws NegocioExcepcion
	 * @throws ValidacionUnicidadLegajoChequeRechazadoExcepcion
	 */
	private void validarUnicidad(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws NegocioExcepcion, ValidacionUnicidadLegajoChequeRechazadoExcepcion {
		
		// <<VAL>> Validar que no exista un legajo de cheque rechazado para los mismos datos del cheque usuario
		validarUnicidadDatosModificadosNoExisteEnOtroLegajo(legajoChequeRechazado);

		// <<VAL>> Validar que no exista un legajo de cheque rechazado para asociado al mismo valor de Shiva
		validarUnicidadChequeAsociadoShivaNoExistaEnOtroLegajo(legajoChequeRechazado);
		
		//<<VAL>> Validar que los datos del cheque que se ingresan manualmente no exista como valor en Shiva 
		validarUnicidadDatosModificadosNoExistaComoChequeShiva(legajoChequeRechazado);

		// <<VAL>> Validar que no exista un legajo de cheque rechazado para asociado al mismo valor de Ice
		validarUnicidadChequeAsociadoIceNoExistaEnOtroLegajo(legajoChequeRechazado);
		
		//<<VAL>> Validar que los datos del cheque que se ingresan manualmente no exista como valor en Ice 
		validarUnicidadDatosModificadosNoExistaComoChequeIce(legajoChequeRechazado);
	}

	/**
	 * 
	 * @param legajoChequeRechazado
	 * @throws ValidacionUnicidadLegajoChequeRechazadoExcepcion
	 * @throws NegocioExcepcion
	 */
	public void validarUnicidadDatosModificadosNoExisteEnOtroLegajo(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws ValidacionUnicidadLegajoChequeRechazadoExcepcion, NegocioExcepcion{
		
		try {
			Long idLegajoDuplicado = legajoChequeRechazadoDao.validarUnicidadDatosModificadosNoExisteEnOtroLegajo(legajoChequeRechazado);

			if (!Validaciones.isObjectNull(idLegajoDuplicado)) {
				throw new ValidacionUnicidadLegajoChequeRechazadoExcepcion(
					Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.validarUnicidad.datosModificadosNoExisteEnOtroLegajo"),  
							idLegajoDuplicado.toString())
				);
			}

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @throws ValidacionUnicidadLegajoChequeRechazadoExcepcion
	 * @throws NegocioExcepcion
	 */
	public void validarUnicidadChequeAsociadoShivaNoExistaEnOtroLegajo(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws ValidacionUnicidadLegajoChequeRechazadoExcepcion, NegocioExcepcion{
		
		try {
			
			if (SistemaEnum.SHIVA.equals(legajoChequeRechazado.getSistemaOrigen())) {

				Long idLegajoDuplicado = legajoChequeRechazadoDao.validarUnicidadChequeAsociadoShivaNoExistaEnOtroLegajo(legajoChequeRechazado);

				if (!Validaciones.isObjectNull(idLegajoDuplicado)) {
					throw new ValidacionUnicidadLegajoChequeRechazadoExcepcion(
						Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.validarUnicidad.chequeAsociadoShivaNoExistaEnOtroLegajo"),idLegajoDuplicado.toString())
					);
				}
			}

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}	

	/**
	 * Verifica si el cheque esta existe en Shiva
	 * @param legajoChequeRechazado
	 * @throws NegocioExcepcion
	 * @throws ValidacionUnicidadLegajoChequeRechazadoExcepcion
	 */
	private void validarUnicidadDatosModificadosNoExistaComoChequeShiva(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws NegocioExcepcion, ValidacionUnicidadLegajoChequeRechazadoExcepcion{

		ValorDto valorBuscadoComoLegajoChequeRechazado = new ValorDto();
		
		List<ShvValValor> listaValores;
		valorBuscadoComoLegajoChequeRechazado.setIdBancoOrigen(legajoChequeRechazado.getBancoOrigen().getIdBanco());
		valorBuscadoComoLegajoChequeRechazado.setNumeroCheque(legajoChequeRechazado.getNumeroCheque().toString());
		valorBuscadoComoLegajoChequeRechazado.setFechaDeposito(Utilidad.formatDatePicker(legajoChequeRechazado.getFechaDeposito()));
		valorBuscadoComoLegajoChequeRechazado.setImporte(Utilidad.formatCurrency(legajoChequeRechazado.getImporte(), false, false));

		
		
		if ( !Validaciones.isObjectNull( legajoChequeRechazado.getFechaVencimiento() ) ) {
			valorBuscadoComoLegajoChequeRechazado.setFechaVencimiento( Utilidad.formatDatePicker(legajoChequeRechazado.getFechaVencimiento()) );
			valorBuscadoComoLegajoChequeRechazado.setIdTipoValor(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValorString());
		} else {
			valorBuscadoComoLegajoChequeRechazado.setIdTipoValor(TipoValorEnum.CHEQUE.getIdTipoValorString());
		}

		try {			
			// Validar si el valor existe en shiva
			listaValores = valorDao.validarUnicidadValor(valorBuscadoComoLegajoChequeRechazado);
			if (!listaValores.isEmpty() ) {
				if (
					Validaciones.isObjectNull(legajoChequeRechazado.getChequeRechazado()) ||
					!listaValores.get(0).getIdValor().equals(legajoChequeRechazado.getChequeRechazado().getIdValor())
				) {
					throw new ValidacionUnicidadLegajoChequeRechazadoExcepcion(
							Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.validarUnicidad.datosModificadosNoExistaComoChequeShiva"),  
									listaValores.get(0).getIdValor().toString())
					);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}

	/**
	 * 
	 * @param legajoChequeRechazado
	 * @throws NegocioExcepcion
	 * @throws ValidacionUnicidadLegajoChequeRechazadoExcepcion
	 */
	private void validarUnicidadChequeAsociadoIceNoExistaEnOtroLegajo(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws NegocioExcepcion, ValidacionUnicidadLegajoChequeRechazadoExcepcion{
		
		try {
			if (SistemaEnum.ICE.equals(legajoChequeRechazado.getSistemaOrigen())) {

				Long idLegajoDuplicado = legajoChequeRechazadoDao.validarUnicidadChequeAsociadoIceNoExistaEnOtroLegajo(legajoChequeRechazado);
	
				if (!Validaciones.isObjectNull(idLegajoDuplicado)) {
					throw new ValidacionUnicidadLegajoChequeRechazadoExcepcion(
							Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.validarUnicidad.chequeAsociadoIceNoExistaEnOtroLegajo"),  
									idLegajoDuplicado.toString())
						);
				}
			}

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}		
	}
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @throws NegocioExcepcion
	 * @throws ValidacionUnicidadLegajoChequeRechazadoExcepcion
	 */
	private void validarUnicidadDatosModificadosNoExistaComoChequeIce(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws NegocioExcepcion, ValidacionUnicidadLegajoChequeRechazadoExcepcion{

		EntradaIceConsultaChequesWS entrada = new EntradaIceConsultaChequesWS();
		
		entrada.getBancos().add(legajoChequeRechazado.getBancoOrigen().getIdBanco());
		entrada.setNroCheque(Long.toString(legajoChequeRechazado.getNumeroCheque()));
		entrada.setFechaCobroDesde(legajoChequeRechazado.getFechaDeposito());
		entrada.setFechaCobroHasta(legajoChequeRechazado.getFechaDeposito());
		entrada.setImporteDesde(legajoChequeRechazado.getImporte());
		entrada.setImporteHasta(legajoChequeRechazado.getImporte());
	
		SalidaIceConsultaChequesWS salida = clienteIceServicio.consultarChequesIce(entrada);
		
		if (!Validaciones.isObjectNull(salida) && !Validaciones.isObjectNull(salida.getListaCheques())) {
			
			//
			// Si la busqueda en ICE retorna mas de un cheque, directamente lanzo la excepcion ya que asumo que existe mas de un cheque 
			// con los datos modificados de mi cheque de ingreso manual, independientemente de que el legajo se encuentre ya asociado a un cheque de ICE
			//
			
			if (salida.getListaCheques().size() > 1) {
				throw new ValidacionUnicidadLegajoChequeRechazadoExcepcion(
						Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.validarUnicidad.datosModificadosNoExistaComoChequeIce"));
			}
			
			//
			// Si la busqueda en ICE retorna un cheque y el origen del cheque en el legajo es diferente de ICE,
			// entonces entiendo que los datos manuales de mi cheque en el legajo, existen como cheque en ICE
			//
			
			if (salida.getListaCheques().size() == 1 && !SistemaEnum.ICE.equals(legajoChequeRechazado.getSistemaOrigen())) {
				throw new ValidacionUnicidadLegajoChequeRechazadoExcepcion(
						Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.validarUnicidad.datosModificadosNoExistaComoChequeIce"));
			}

			//
			// Si la busqueda en ICE retorna un cheque y el origen del cheque en el legajo es ICE,
			// entonces debiera verificar si el usuario ha modificado los datos de ingreso manual.
			// Si los datos del cheque asociado al legajo y los datos modificados del legajo son los mismos, y al consultar en ICE por datos modificados 
			// está retornando un registro, entonces estoy ante la presencia del mismo cheque, NO genero excepcion
			// Si los datos del cheque asociado al legajo y los datos modificados del legajo son diferentes, y al consultar en ICE por datos modificados 
			// está retornando un registro, entonces estoy ante la presencia de un cheque diferente, SI genero excepcion
			//
			if (salida.getListaCheques().size() == 1 && SistemaEnum.ICE.equals(legajoChequeRechazado.getSistemaOrigen())) {
				
				StringBuffer keyChequeDatosModificados = new StringBuffer();
				keyChequeDatosModificados.append(legajoChequeRechazado.getBancoOrigen().getIdBanco());
				keyChequeDatosModificados.append(Constantes.SEPARADOR_PIPE);
				keyChequeDatosModificados.append(legajoChequeRechazado.getNumeroCheque());
				keyChequeDatosModificados.append(Constantes.SEPARADOR_PIPE);
				keyChequeDatosModificados.append(Utilidad.formatDatePicker(legajoChequeRechazado.getFechaDeposito()));
				keyChequeDatosModificados.append(Constantes.SEPARADOR_PIPE);
				keyChequeDatosModificados.append(legajoChequeRechazado.getImporte());
				
				StringBuffer keyChequeIceAsociadosAlLegajo = new StringBuffer();
				keyChequeIceAsociadosAlLegajo.append(legajoChequeRechazado.getChequeRechazado().getBancoOrigen().getIdBanco());
				keyChequeDatosModificados.append(Constantes.SEPARADOR_PIPE);
				keyChequeIceAsociadosAlLegajo.append(legajoChequeRechazado.getChequeRechazado().getNumeroCheque());
				keyChequeDatosModificados.append(Constantes.SEPARADOR_PIPE);
				keyChequeIceAsociadosAlLegajo.append(Utilidad.formatDatePicker(legajoChequeRechazado.getChequeRechazado().getFechaDeposito()));
				keyChequeDatosModificados.append(Constantes.SEPARADOR_PIPE);
				keyChequeIceAsociadosAlLegajo.append(legajoChequeRechazado.getChequeRechazado().getImporte());

				StringBuffer keyChequeResultadoBusquedaIce = new StringBuffer();
				IceCheques chequeIce = salida.getListaCheques().iterator().next();
				
				keyChequeResultadoBusquedaIce.append(chequeIce.getBancoEmisorCheque());
				keyChequeDatosModificados.append(Constantes.SEPARADOR_PIPE);
				keyChequeResultadoBusquedaIce.append(chequeIce.getNumeroCheque());
				keyChequeDatosModificados.append(Constantes.SEPARADOR_PIPE);
				keyChequeResultadoBusquedaIce.append(Utilidad.formatDatePicker(chequeIce.getFechaDeCobro()));
				keyChequeDatosModificados.append(Constantes.SEPARADOR_PIPE);
				keyChequeResultadoBusquedaIce.append(chequeIce.getImporteCheque());

				if (keyChequeDatosModificados.equals(keyChequeIceAsociadosAlLegajo) && keyChequeDatosModificados.equals(keyChequeResultadoBusquedaIce)) {
					throw new ValidacionUnicidadLegajoChequeRechazadoExcepcion(
							Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.validarUnicidad.datosModificadosNoExistaComoChequeIce"));
				}
			}
		}
	}	
	
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public Modelo crear(Modelo modelo) throws NegocioExcepcion {

		// TODO: Solo para pruebas durante el desarrollo
		//ControlVariablesSingleton.permitirTraceoSQL();
		
		ShvLgjLegajoChequeRechazado legajoChequeRechazado = (ShvLgjLegajoChequeRechazado) modelo;
		try {
			this.validarUnicidad(legajoChequeRechazado);
			// Crear el WF
			ShvWfWorkflow workflowLegajoChequesRechazados = workflowLegajoChequesRechazadosServicio.crearWorkflow(
																"", legajoChequeRechazado.getIdAnalista());
	
			workflowLegajoChequesRechazados = workflowLegajoChequesRechazadosServicio.solicitarAperturaDeLegajo(
																workflowLegajoChequesRechazados, null, legajoChequeRechazado.getIdAnalista()); 
			
			legajoChequeRechazado.setWorkflow(workflowLegajoChequesRechazados);
			legajoChequeRechazado.setFechaAlta(workflowLegajoChequesRechazados.getFechaUltimaModificacion());
			 
			legajoChequeRechazadoDao.crear(legajoChequeRechazado);
			
			//Guardado de la observacion en el historial
			if(!Validaciones.isNullOrEmpty(legajoChequeRechazado.getObservaciones())){
				observacionesWorkflowServicio.insertarHistorialObservacion(legajoChequeRechazado.getWorkflow().getWorkflowEstado(), legajoChequeRechazado.getObservaciones());
			} else {
				observacionesWorkflowServicio.borrarObservacionCorrienteBy(legajoChequeRechazado.getWorkflow().getWorkflowEstado());
			}
			
			
			if (SistemaEnum.SHIVA.equals(legajoChequeRechazado.getSistemaOrigen())) {
				
				// Al momento de confirmar el alta del legajo, si el cheque asociado es de Shiva lo suspendemos con motivo
				// cheque rechazado, y generamos movimiento “043 - Suspensión de Cheque por Rechazo”
				
				StringBuffer observacionesSuspension = new StringBuffer();
				observacionesSuspension.append(Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.legajo.fecha.rechazo"));
				observacionesSuspension.append(Constantes.DOS_PUNTOS);
				observacionesSuspension.append(Constantes.WHITESPACE); 
				observacionesSuspension.append(Utilidad.formatDatePicker(legajoChequeRechazado.getFechaRechazo()));
				observacionesSuspension.append(Constantes.WHITESPACE); 

				observacionesSuspension.append(Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.legajo.fecha.notificacion.rechazo"));
				observacionesSuspension.append(Constantes.DOS_PUNTOS);
				observacionesSuspension.append(Constantes.WHITESPACE); 
				observacionesSuspension.append(Utilidad.formatDatePicker(legajoChequeRechazado.getFechaNotificacion()));
				observacionesSuspension.append(Constantes.WHITESPACE); 

				observacionesSuspension.append(Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.leyenda.idLegajoChequeRechazado"));
				observacionesSuspension.append(Constantes.DOS_PUNTOS);
				observacionesSuspension.append(Constantes.WHITESPACE); 
				observacionesSuspension.append(legajoChequeRechazado.getIdLegajoChequeRechazado());
				
				ShvValValor valorAsociado = 
						valorServicio.suspenderValor(legajoChequeRechazado.getChequeRechazado().getIdValor(), 
											 observacionesSuspension.toString(), 
											 legajoChequeRechazado.getIdAnalista(), 
											 MotivoSuspensionEnum.CHEQUE_RECHAZADO, 
											 legajoChequeRechazado.getFechaRechazo());	

				// Si el cheque del cual se recibió el rechazo es administrado por el sistema Shiva y el mismo tiene todo su saldo suspendido, 
				// entonces se deberá proceder a la anulación del mismo.
				
				if (valorAsociado.getImporte().compareTo(valorAsociado.getSaldoDisponible()) == 0) {

					StringBuffer observacionesAnulacion = new StringBuffer();
					observacionesAnulacion.append(Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.leyenda.idLegajoChequeRechazado"));
					observacionesAnulacion.append(Constantes.DOS_PUNTOS);
					observacionesAnulacion.append(Constantes.WHITESPACE); 
					observacionesAnulacion.append(legajoChequeRechazado.getIdLegajoChequeRechazado());
					
					valorAsociado =
						valorServicio.anularValor(valorAsociado, 
											 observacionesAnulacion.toString(), 
											 legajoChequeRechazado.getIdAnalista(),
											 legajoChequeRechazado.getFechaRechazo());
					
					
					// Si el valor queda anulado, quiere decir que al no tener cobros relacionados, 
					// puedo avanzar el WF del Legajo
					
					if (Estado.LGJ_ABIERTO.equals(workflowLegajoChequesRechazados.getEstado())) {
						workflowLegajoChequesRechazados = workflowLegajoChequesRechazadosServicio.solicitarOmitirProcesoDeReembolsoParaLegajoConChequeRechazadoAbierto(
																		workflowLegajoChequesRechazados, null, legajoChequeRechazado.getIdAnalista());
					}
				}
				
			} else if (SistemaEnum.ICE.equals(legajoChequeRechazado.getSistemaOrigen())) {
				
				// Si el cheque asociado es de ICE, debemos buscar los cobros asociados a ese cheque
				// para poder almacenarlos en la base
				cargarDetalleCobros(legajoChequeRechazado);

				// Al momento de confirmar el alta del legajo, si el cheque asociado es de ICE, 
				// y los cobros del mismo se encuentran completamente revertidos, generamos un 
				// movimiento “040 - Reversión por Ch. Rechazado (circuito cobranza bancaria)” con origen ICE
				
				if (legajoChequeRechazado.getChequeRechazado().isCobrosRevertidos()) {
					contabilizarReversionChequeRechazadoCircuitoCobranzaBancariaIce(legajoChequeRechazado, legajoChequeRechazado.getIdAnalista());
					
					// Si el valor queda anulado, quiere decir que al no tener cobros relacionados, 
					// puedo avanzar el WF del Legajo
					
					if (Estado.LGJ_ABIERTO.equals(workflowLegajoChequesRechazados.getEstado())) {
						workflowLegajoChequesRechazados = workflowLegajoChequesRechazadosServicio.solicitarOmitirProcesoDeReembolsoParaLegajoConChequeRechazadoAbierto(
																		workflowLegajoChequesRechazados, null, legajoChequeRechazado.getIdAnalista());
					}
				}
				
				
			} else if (SistemaEnum.USUARIO.equals(legajoChequeRechazado.getSistemaOrigen())) {
				
				// Al momento de confirmar el alta de un legajo de cheque rechazado, si el cheque es de origen usuario, 
				// generamos el movimiento “045 - Alta de legajo de cheque rechazado (circuito manual)"

				contabilizarAltaLegajoChequeRechazadoCircuitoManual(legajoChequeRechazado);
			}

			if (
					SistemaEnum.ICE.equals(legajoChequeRechazado.getSistemaOrigen()) ||
					SistemaEnum.SHIVA.equals(legajoChequeRechazado.getSistemaOrigen())
			) {
				
				
				List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> lista = vistaSoporteServicio.obtenerChequeRechazadoDetalleDocumentoBy(
					legajoChequeRechazado.getIdLegajoChequeRechazado()
				);

				for (VistaSoporteResultadoBusquedaLegajoDetalleDocumento vistaSoporteResultadoBusquedaLegajoDetalleDocumento : lista) {
					ShvLgjChequeRechazadoDetalleDocumento shvLgjChequeRechazadoDetalleDocumento =
					(ShvLgjChequeRechazadoDetalleDocumento) legajoChequeRechazadoCobroRelacionadoDocumentoMapeador.map(
						vistaSoporteResultadoBusquedaLegajoDetalleDocumento,
						null
					);
					shvLgjChequeRechazadoDetalleDocumento.setChequeRechazado(legajoChequeRechazado.getChequeRechazado());
					legajoChequeRechazado.getChequeRechazado().getDocumentos().add(shvLgjChequeRechazadoDetalleDocumento);
				}
				
			}
	
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ValidacionUnicidadLegajoChequeRechazadoExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}

		return legajoChequeRechazado;
	}

	/**
	 * 
	 * @param legajoChequeRechazado
	 * @throws NegocioExcepcion 
	 */
	private void cargarDetalleCobros(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws NegocioExcepcion {
		
		EntradaIceConsultaChequesWS entrada = new EntradaIceConsultaChequesWS();		
		
		entrada.getBancos().add(legajoChequeRechazado.getChequeRechazado().getBancoOrigen().getIdBanco());
		entrada.setNroCheque(legajoChequeRechazado.getChequeRechazado().getNumeroCheque());
		entrada.setFechaCobroDesde(legajoChequeRechazado.getChequeRechazado().getFechaDeposito());
		entrada.setFechaCobroHasta(legajoChequeRechazado.getChequeRechazado().getFechaDeposito());
		entrada.setImporteDesde(legajoChequeRechazado.getChequeRechazado().getImporte());
		entrada.setImporteHasta(legajoChequeRechazado.getChequeRechazado().getImporte());
		entrada.setCodigoBancoCobro(legajoChequeRechazado.getChequeRechazado().getCodigoBancoDeCobro());
		
		SalidaIceConsultaChequesWS salida = clienteIceServicio.consultarDetalleCobranzaChequeIce(entrada);
		
		if (salida.getListaCheques().isEmpty()) {
			throw new NoSePuedeRecuperarDetalleCobrosChequeIceExcepcion(
						Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.validarCargaDetalleCobrosIce"));
		}
		
		for (IceCheques cobroChequeIce : salida.getListaCheques()) {
			
			ShvLgjChequeRechazadoDetalleCobro detalleCobro = new ShvLgjChequeRechazadoDetalleCobro(); 
			
			detalleCobro.setCodigoBancoEmisorCheque(cobroChequeIce.getBancoEmisorCheque());
			
			if (!Validaciones.isObjectNull(cobroChequeIce.getClientes()) && !cobroChequeIce.getClientes().isEmpty()) {
				ClienteCheque clienteCheque = (ClienteCheque) cobroChequeIce.getClientes().iterator().next();
			
				detalleCobro.setIdClienteLegado(clienteCheque.getIdClienteLegado());
				detalleCobro.setRazonSocial(clienteCheque.getRazonSocial());
			}
			
			detalleCobro.setCobroRevertido(cobroChequeIce.isCobroRevertido() ? SiNoEnum.SI : SiNoEnum.NO);
			if(SiNoEnum.SI.equals(detalleCobro.getCobroRevertido())) {
				detalleCobro.setEstado(EstadoChequeRechazadoDetalleCobroEnum.REVERTIDO);
			} else {
				detalleCobro.setEstado(EstadoChequeRechazadoDetalleCobroEnum.COBRADO);
			}
			detalleCobro.setCodigoBancoCobro(cobroChequeIce.getCodigoBancoDeCobro());
			detalleCobro.setCodigoDeTarea(cobroChequeIce.getCodigoDeTarea());
			detalleCobro.setCodigoEmpresaReceptora(cobroChequeIce.getCodigoEmpresaReceptora());
			detalleCobro.setCodigoEntidadGestora(cobroChequeIce.getCodigoEntidadGestora());
			detalleCobro.setCodigoOperacion(cobroChequeIce.getCodigoOperacion());
			detalleCobro.setCodigoPostalSucursal(cobroChequeIce.getCodigoPostalSucursal());
			detalleCobro.setComision(cobroChequeIce.getComision());
			detalleCobro.setCpClienteCodigoBarras(cobroChequeIce.getCpClienteCodigoBarras());
			detalleCobro.setDescripcionBanco(cobroChequeIce.getDescripcionBanco());
			detalleCobro.setFechaDeAcreditacion(cobroChequeIce.getFechaDeAcreditacion());
			detalleCobro.setFechaDeCobro(cobroChequeIce.getFechaDeCobro());
			detalleCobro.setFechaDeVctoOriginal(cobroChequeIce.getFechaDeVctoOriginal());
			detalleCobro.setFechaPeticionReversion(cobroChequeIce.getFechaPeticionReversion());
			detalleCobro.setFormaDePagoDelCliente(cobroChequeIce.getFormaDePagoDelCliente());
			detalleCobro.setIdentificadorDePago(cobroChequeIce.getIdentificadorDePago());
			detalleCobro.setImporteAbsoluto(cobroChequeIce.getImporteAbsoluto());
			detalleCobro.setImporteBono(cobroChequeIce.getImporteBono());
			detalleCobro.setImporteCheque(cobroChequeIce.getImporteCheque());
			detalleCobro.setImporteCobradoOReversar(cobroChequeIce.getImporteCobradoOReversar());
			detalleCobro.setImporteComprobante(cobroChequeIce.getImporteComprobante());
			detalleCobro.setImporteEfectivo(cobroChequeIce.getImporteEfectivo());
			detalleCobro.setImporteOriginal(cobroChequeIce.getImporteOriginal());
			detalleCobro.setImporteOtrasMonedas(cobroChequeIce.getImporteOtrasMonedas());
			detalleCobro.setMarcaDeAjuste(cobroChequeIce.getMarcaDeAjuste());
			detalleCobro.setMarcaDePago(cobroChequeIce.getMarcaDePago()); 
			detalleCobro.setMoneda(cobroChequeIce.getMoneda());
			detalleCobro.setNombreBancoDeCobro(cobroChequeIce.getNombreBancoDeCobro());
			detalleCobro.setNombreEntidadGestora(cobroChequeIce.getNombreEntidadGestora());
			detalleCobro.setNumeroCheque(cobroChequeIce.getNumeroCheque());
			detalleCobro.setNumeroChequeCompleto(cobroChequeIce.getNumeroChequeCompleto());
			detalleCobro.setNumeroConvenio(cobroChequeIce.getNumeroConvenio());
			detalleCobro.setNumeroCuota(cobroChequeIce.getNumeroCuota());
			detalleCobro.setNumeroDeAgencia(cobroChequeIce.getNumeroDeAgencia());
			detalleCobro.setNumeroDeComprobante(cobroChequeIce.getNumeroDeComprobante());
			detalleCobro.setNumeroDeSucursalCompleto(cobroChequeIce.getNumeroDeSucursalCompleto());
			detalleCobro.setNumeroDeTarjeta(cobroChequeIce.getNumeroDeTarjeta());
			detalleCobro.setNumeroLegal(cobroChequeIce.getNumeroLegal());
			detalleCobro.setNumeroReferenciaMic(cobroChequeIce.getNumeroReferenciaMic());
			detalleCobro.setProvinciaDelCodBarras(cobroChequeIce.getProvinciaDelCodBarras());
			detalleCobro.setRecibo(cobroChequeIce.getRecibo());
			detalleCobro.setReferenciaDelComprobante(cobroChequeIce.getReferenciaDelComprobante());
			detalleCobro.setSucursalBanco(cobroChequeIce.getSucursalBanco());
			detalleCobro.setTipoBono(cobroChequeIce.getTipoBono());
			detalleCobro.setTipoClienteCodigoBarras(cobroChequeIce.getTipoClienteCodigoBarras());
			detalleCobro.setTipoComprobante(cobroChequeIce.getTipoComprobante());
			detalleCobro.setTipoDeCambio(cobroChequeIce.getTipoDeCambio());
			detalleCobro.setTipoDeFacturacion(cobroChequeIce.getTipoDeFacturacion());
			detalleCobro.setTipoDeLectura(cobroChequeIce.getTipoDeLectura());
			detalleCobro.setTipoDeOperacion(cobroChequeIce.getTipoDeOperacion());
			detalleCobro.setTipoDocumentoRelac(cobroChequeIce.getTipoDocumentoRelacionado());
			detalleCobro.setTipoDocumentoRelacTotfa(cobroChequeIce.getTipoDocumentoRelacionadoTotfa());
			detalleCobro.setTipoOtrasMonedas(cobroChequeIce.getTipoOtrasMonedas()); 
			
			detalleCobro.setChequeRechazado(legajoChequeRechazado.getChequeRechazado());
			
			legajoChequeRechazado.getChequeRechazado().getCobros().add(detalleCobro);
		}
	}

	@Override
	public Modelo modificar(Modelo modelo) throws NegocioExcepcion {
		ShvLgjLegajoChequeRechazadoSimplificado legajoSimplificado = (ShvLgjLegajoChequeRechazadoSimplificado) modelo;
		
		try {
			legajoSimplificado = (ShvLgjLegajoChequeRechazadoSimplificado) legajoChequeRechazadoDao.modificar(legajoSimplificado);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return legajoSimplificado;
	}

	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	@Override
	public void entregarCheque(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
		try {
			entregarCheque(legajoChequeRechazadoDao.buscar(idLegajoChequeRechazado), idUsuario);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	private void entregarCheque(ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion {

		try {
			legajoChequeRechazado.setUbicacion(UbicacionChequeEnum.CLIENTE);
			legajoChequeRechazado = legajoChequeRechazadoDao.modificar(legajoChequeRechazado);

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		if (Estado.LGJ_ABIERTO.equals(legajoChequeRechazado.getWorkflow().getEstado())) {

			workflowLegajoChequesRechazadosServicio.
						solicitarEntregaDeChequeRechazadoParaLegajoAbierto(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);
												
		} else if (Estado.LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA.equals(legajoChequeRechazado.getWorkflow().getEstado())) {

			workflowLegajoChequesRechazadosServicio.
						solicitarEntregaDeChequeRechazadoParaLegajoEnProcesoDeReembolso(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);
												
		} else if (Estado.LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA.equals(legajoChequeRechazado.getWorkflow().getEstado())) {

			workflowLegajoChequesRechazadosServicio.
						solicitarEntregaDeChequeRechazadoParaLegajoReembolsado(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);

		} else if (Estado.LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA.equals(legajoChequeRechazado.getWorkflow().getEstado())) {

			workflowLegajoChequesRechazadosServicio.
						solicitarEntregaDeChequeRechazadoParaLegajoCerrado(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);
		}
	}
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvLgjLegajoChequeRechazado reembolsarCheque(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
		ShvLgjLegajoChequeRechazado legajoChequeRechazado = null;
		try {
			legajoChequeRechazado = legajoChequeRechazadoDao.buscar(idLegajoChequeRechazado);
			reembolsarCheque(legajoChequeRechazado, idUsuario);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return legajoChequeRechazado;
	}	
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	private void reembolsarCheque(ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
	
		if (Estado.LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA.equals(legajoChequeRechazado.getWorkflow().getEstado())) {

			workflowLegajoChequesRechazadosServicio.
						solicitarFinalizarProcesodeReembolsoParaLegajoConChequeRechazadoPendienteDeEntrega(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);
												
		} else if (Estado.LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_ENTREGADO.equals(legajoChequeRechazado.getWorkflow().getEstado())) {

			workflowLegajoChequesRechazadosServicio.
						solicitarFinalizarProcesoDeReembolsoParaLegajoConChequeRechazadoEntregado(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);
		}
	}
	

	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvLgjLegajoChequeRechazado cerrar(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
		
		ShvLgjLegajoChequeRechazado legajo;
		try {
			legajo = legajoChequeRechazadoDao.buscar(idLegajoChequeRechazado);
			cerrar(legajo, idUsuario);
			
			legajo.setFechaCierre(legajo.getWorkflow().getWorkflowEstado().getFechaModificacion());
			
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return legajo;
	}

	/**
	 * 
	 * @param legajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	private void cerrar(ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
	
		if (Estado.LGJ_ABIERTO.equals(legajoChequeRechazado.getWorkflow().getEstado())) {

			workflowLegajoChequesRechazadosServicio.
					solicitarCierreConChequeRechazadoPendienteDeEntregaParaLegajoAbierto(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);

		} if (Estado.LGJ_ABIERTO_CON_CHEQUE_ENTREGADO.equals(legajoChequeRechazado.getWorkflow().getEstado())) {

			workflowLegajoChequesRechazadosServicio.
					solicitarCierreDeLegajoParaChequeRechazadoEntregado(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);

		} if (Estado.LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA.equals(legajoChequeRechazado.getWorkflow().getEstado())) {

			workflowLegajoChequesRechazadosServicio.
					solicitarCierreConChequeRechazadoPendienteDeEntregaParaLegajoReembolsado(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);

		} if (Estado.LGJ_REEMBOLSADO_CON_CHEQUE_ENTREGADO.equals(legajoChequeRechazado.getWorkflow().getEstado())) {

			workflowLegajoChequesRechazadosServicio.
					solicitarCierreParaLegajoConChequeRechazadoEntregado(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);
		}			
	}
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvLgjLegajoChequeRechazado desistir(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
		
		ShvLgjLegajoChequeRechazado legajo;
		try {
			legajo = legajoChequeRechazadoDao.buscar(idLegajoChequeRechazado);
			desistir(legajo, idUsuario);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return legajo;
	}

	/**
	 * 
	 * @param legajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	private void desistir(ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion {

		// Avanzo el WF al estado "Desitido"
		workflowLegajoChequesRechazadosServicio.solicitarDesistir(
									legajoChequeRechazado.getWorkflow(), 
									null, idUsuario);
		
		// Si el legajo se encuentra en Legales, el botón “Cerrar” aparece como “Desistir”, si el usuario presiona este
		// botón más allá del cambio de estado, se generará el movimiento contable “047 - Desistimiento de cheque rechazado (circuito manual)”. 
		// Este movimiento se envía solo si el origen del cheque es Usuario.
		if (SistemaEnum.USUARIO.equals(legajoChequeRechazado.getSistemaOrigen())) {
		
			// Genero el movimiento contable 
			contabilizarDesistirLegajoChequeRechazadoCircuitoManual(legajoChequeRechazado, idUsuario);
		}
	}

	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvLgjLegajoChequeRechazado enviarALegales(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
		ShvLgjLegajoChequeRechazado legajoChequeRechazado = null;
		try {
			legajoChequeRechazado = legajoChequeRechazadoDao.buscar(idLegajoChequeRechazado);
			enviarALegales(legajoChequeRechazado, idUsuario);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return legajoChequeRechazado;
	}
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	private void enviarALegales(ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
	
		try {
			legajoChequeRechazado.setUbicacion(UbicacionChequeEnum.LEGALES);
			legajoChequeRechazado = legajoChequeRechazadoDao.modificar(legajoChequeRechazado);

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		if (SistemaEnum.USUARIO.equals(legajoChequeRechazado.getSistemaOrigen())) {
			
			// Avanzo el WF al estado "Enviado a Legales" para el caso de Legajos con cheque Usuario
			workflowLegajoChequesRechazadosServicio.solicitarEnvioALegalesParaLegajoAbierto(
										legajoChequeRechazado.getWorkflow(), 
										null, idUsuario);

			// Si se presiona el botón “Enviar a Legales”, más allá del cambio de estado, se genera el movimiento contable
			// “046 - Envío a legales de cheque rechazado (circuito manual)”
			// Este movimiento se envía solo si el origen del cheque es Usuario.
			contabilizarEnvioALegaglesLegajoChequeRechazadoCircuitoManual(legajoChequeRechazado, idUsuario);

		} else {
			
			// Avanzo el WF al estado "Enviado a Legales" para el caso de Legajos con cheque ICE o Shiva
			workflowLegajoChequesRechazadosServicio.solicitarEnvioALegalesParaLegajoEnProcesoDeReembolso(
										legajoChequeRechazado.getWorkflow(), 
										null, idUsuario);
		}
	}	
	
	/**
	 * 
	 */
	public boolean validarChequeAplicadoEnOperacion(Long idValor) throws NegocioExcepcion {
		
		VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
	
		filtro.setIdValor(idValor.toString());

		List<VistaSoporteResultadoBusquedaValor> listaValores = vistaSoporteServicio.buscarValores(filtro);
		
		if (listaValores.isEmpty()) {
			throw new NegocioExcepcion("El ID Valor solicitado no posee un Valor asociado");
		}
	
		return listaValores.get(0).getImporte().compareTo(listaValores.get(0).getSaldoDisponible()) == 0;
	}

	@Override
	public Map<String, BigDecimal> obtenerMontos(ShvLgjChequeRechazado modelo) throws NegocioExcepcion {
		Map<String, BigDecimal> montos = new HashMap<String, BigDecimal>();
		BigDecimal montoARevertir = BigDecimal.ZERO;
		BigDecimal montoAReenvolsar = BigDecimal.ZERO;

		if (SistemaEnum.SHIVA.equals(modelo.getSistemaOrigen())) {
			VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
			filtro.setIdValor(modelo.getIdValor().toString());
			List<VistaSoporteResultadoBusquedaValor> listaValores = vistaSoporteServicio.buscarValores(filtro);

			if (listaValores.isEmpty()) {
				throw new NegocioExcepcion("El ID Valor solicitado no posee un Valor asociado");
			}
			montoARevertir = listaValores.get(0).getImporte().subtract(listaValores.get(0).getSaldoDisponible());
			// Tengo sumar las reversiones!!!
			montoAReenvolsar = listaValores.get(0).getSaldoDisponible();
		} else if (SistemaEnum.ICE.equals(modelo.getSistemaOrigen())) {
			for (ShvLgjChequeRechazadoDetalleCobro detalle : modelo.getCobros()) {
				if (EstadoChequeRechazadoDetalleCobroEnum.COBRADO.equals(detalle.getEstado())) {
					montoARevertir = montoARevertir.add(detalle.getImporteAbsoluto());	
				} else if (EstadoChequeRechazadoDetalleCobroEnum.REVERTIDO.equals(detalle.getEstado())) {
					montoAReenvolsar = montoAReenvolsar.add(detalle.getImporteAbsoluto());
				}
			}
		}
		montos.put("montoARevertir", montoARevertir);
		montos.put("montoAReenvolsar", montoAReenvolsar);
		return montos;
	}
	
	@Override
	public Map<String, String> obtenerMontos(Long idLegajo, SistemaEnum sistema,Estado estado) throws NegocioExcepcion {
		Map<String, String> montos = new HashMap<String, String>();
		if (
			Estado.LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA.name().equals(estado.name()) ||
			Estado.LGJ_REEMBOLSADO_CON_CHEQUE_ENTREGADO.name().equals(estado.name()) ||
			Estado.LGJ_CERRADO_CON_CHEQUE_ENTREGADO.name().equals(estado.name()) ||
			Estado.LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA.name().equals(estado.name())
		){
			montos.put("montoARevertir", Utilidad.formatCurrency(BigDecimal.ZERO, false, false));
			montos.put("montoAReenvolsar", Utilidad.formatCurrency(BigDecimal.ZERO, false, false));
		} else {
			montos = obtenerMontos(idLegajo, sistema);
		}
		
		return montos;
	}
	
	@Override
	public Map<String, String> obtenerMontos(Long idLegajo, SistemaEnum sistema) throws NegocioExcepcion {
		Map<String, String> montos = new HashMap<String, String>();
		BigDecimal montoARevertir = BigDecimal.ZERO;
		BigDecimal montoAReenvolsar = BigDecimal.ZERO;

		if (!SistemaEnum.USUARIO.equals(sistema)) {
			VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro filtro = new VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro();
			filtro.setIdLegajoChequeRechazado(idLegajo);

			List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listaDetalleCobro;
			try {
				listaDetalleCobro = this.listarCobrosRelacionados(filtro);
			} catch (ShivaExcepcion e) {
				Traza.error(getClass(), e.getMessage());
				throw new NegocioExcepcion(e.getMessage(), e);
			}
			boolean descobrado = false;
			for (VistaSoporteResultadoBusquedaLegajoCobrosRelacionado detalleCobro : listaDetalleCobro) {
				descobrado = false; 
				if (SistemaEnum.SHIVA.equals(detalleCobro.getSistemaOrigen())) {
					if (Estado.DES_DESCOBRADO.name().equals(detalleCobro.getEstadoReversionShiva())) {
						descobrado = true;
					} else {
						descobrado = false;
					}
				} else if (SistemaEnum.ICE.equals(detalleCobro.getSistemaOrigen())) {
					if (EstadoChequeRechazadoDetalleCobroEnum.REVERTIDO.name().equals(detalleCobro.getEstadoReversionIce())) {
						descobrado = true;
					} else {
						descobrado = false;
					}
				}
				if (descobrado) {
					montoAReenvolsar = montoAReenvolsar.add(new BigDecimal(detalleCobro.getImporteTotalCheque()));
				} else {
					montoARevertir = montoARevertir.add(new BigDecimal(detalleCobro.getImporteTotalCheque()));
				}
			}
		}
		montos.put("montoARevertir", Utilidad.formatCurrency(montoARevertir, false, false));
		montos.put("montoAReenvolsar", Utilidad.formatCurrency(montoAReenvolsar, false, false));
		return montos;
	}
	/**
	 * Al momento de confirmar el alta del legajo, si el cheque asociado es de ICE, y los cobros del mismo se 
	 * encuentran completamente revertidos, generamos un movimiento “040 - Reversión por Ch. Rechazado (circuito cobranza bancaria)” con origen ICE
	 *  
	 * @param legajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	private void contabilizarReversionChequeRechazadoCircuitoCobranzaBancariaIce(
			ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
		
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$040.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());		
		
		if (legajoChequeRechazado.getChequeRechazado().getClientes().size() == 1) { 
			contabilidadDto.setCodigoClienteLegado(legajoChequeRechazado.getChequeRechazado().getClientes().iterator().next().getIdClienteLegado());
		}
		
		contabilidadDto.setIdAnalista(idUsuario);
		contabilidadDto.setImporte(legajoChequeRechazado.getImporte());
		contabilidadDto.setMoneda(legajoChequeRechazado.getMoneda().name());
		contabilidadDto.setFechaValor(legajoChequeRechazado.getFechaRechazo());
		contabilidadDto.setTransaccion(legajoChequeRechazado.getIdLegajoChequeRechazado().toString());
		contabilidadDto.setNumeroComprobante(legajoChequeRechazado.getNumeroCheque().toString());
		contabilidadDto.setIdCaja(legajoChequeRechazado.getChequeRechazado().getBancoOrigen().getIdBanco());

		contabilidadServicio.contabilizar(contabilidadDto);
	}
	
	
	/**
	 * Al momento de confirmar el alta de un legajo de cheque rechazado, si el cheque es de origen usuario, 
	 * generamos el movimiento “045 - Alta de legajo de cheque rechazado (circuito manual)"
	 *  
	 * @param legajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	private void contabilizarAltaLegajoChequeRechazadoCircuitoManual(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws NegocioExcepcion {
		
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$045.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());
		
		if (Validaciones.isCollectionNotEmpty(legajoChequeRechazado.getChequeRechazado().getClientes())) {
			contabilidadDto.setCodigoClienteLegado(legajoChequeRechazado.getChequeRechazado().getClientes().iterator().next().getIdClienteLegado());
		}
		
		contabilidadDto.setIdAnalista(legajoChequeRechazado.getIdAnalista());
		contabilidadDto.setImporte(legajoChequeRechazado.getImporte());
		contabilidadDto.setMoneda(legajoChequeRechazado.getMoneda().name());
		contabilidadDto.setFechaValor(legajoChequeRechazado.getFechaRechazo());
		contabilidadDto.setTransaccion(legajoChequeRechazado.getIdLegajoChequeRechazado().toString());
		contabilidadDto.setNumeroComprobante(legajoChequeRechazado.getNumeroCheque().toString());
		contabilidadDto.setIdCaja(legajoChequeRechazado.getBancoOrigen().getIdBanco());
		contabilidadDto.setFechaValor(legajoChequeRechazado.getFechaRechazo());

		contabilidadServicio.contabilizar(contabilidadDto);
	}
	
	/**
	 * Al momento de anular el alta de un legajo de cheque rechazado, si el cheque es de origen usuario, 
	 * generamos el movimiento “045 - Alta de legajo de cheque rechazado (circuito manual)" con importe negado
	 *  
	 * @param legajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	private void anularContabilidadAltaLegajoChequeRechazadoCircuitoManual(ShvLgjLegajoChequeRechazado legajoChequeRechazado) throws NegocioExcepcion {
		
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$045.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());		

		if (Validaciones.isCollectionNotEmpty(legajoChequeRechazado.getChequeRechazado().getClientes())) {
			contabilidadDto.setCodigoClienteLegado(legajoChequeRechazado.getChequeRechazado().getClientes().iterator().next().getIdClienteLegado());
		}

		contabilidadDto.setIdAnalista(legajoChequeRechazado.getIdAnalista());
		contabilidadDto.setImporte(legajoChequeRechazado.getImporte().negate());
		contabilidadDto.setMoneda(legajoChequeRechazado.getMoneda().name());
		contabilidadDto.setTransaccion(legajoChequeRechazado.getIdLegajoChequeRechazado().toString());
		contabilidadDto.setNumeroComprobante(legajoChequeRechazado.getNumeroCheque().toString());
		contabilidadDto.setIdCaja(legajoChequeRechazado.getBancoOrigen().getIdBanco());
		contabilidadDto.setFechaValor(legajoChequeRechazado.getFechaRechazo());

		contabilidadServicio.contabilizar(contabilidadDto);
	}
	
	/**
	 * Sprint 04 - Gestionar el legajo de cheque Rechazados - Cobros relacionados
	 * 
	 * - Contabilizar en lo que respecta al hito de reversiones del legajo de cheque rechazados.  
	 *   La idea es que, al momento de cerrar el hito, se envíen los siguientes movimientos contables para el caso que se 
	 *   hayan revertido cobros de ICE (ya sea automáticos por ICE o manuales por el usuario).  
	 *
	 * Origen 041 – aplicación Mic x Reversión de Ch. Rech. (el monto que corresponde reversar de Mic) – datos N° de cliente y N° identificación (idem anterior)
	 * Origen 042 – aplicación Calipso x Reversión de Ch. Rech (el monto que corresponde reversar de Calipso) – datos idem Mic
	 * Origen 044 - Aplicación DEIMOs x Reversión de Ch. Rech (circuito cobranza bancaria)
	 * 
	 * Aquí una duda, es una pareja de movimientos 040/041, 040/042 o 040/044 con cada cobro de ese cheque que se
	 * revierte, o es un movimiento 041, 042 o 044 por cada documento, y un 040 por el cheque con el total del importe?
	 * Los movimientos 041, 042 y 044 son por cada cobro que se mandó a revertir. Si el cheque se aplicó en varios
	 * documentos, se envían varios registros según el sistema donde se imputó. En el caso del 040, el movimiento es
	 * único por el total del cheque teniendo en cuenta los datos cargados por el usuario.
	 *
	 * Y otra duda, los movimientos se van generando a medida que los cobros se van revirtiendo, o cuando el usuario
	 * da al botón “Confirmar fin de reversión”, que se habilita cuando ya se encuentra todo revertido?
	 * Cuando se confirma la reversión por parte del usuario se envían todos los movimientos juntos.
	 * 
	 * 
	 * @param legajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	public void contabilizarReversionCobranzasLegajoChequeRechazadoCircuitoCobranzaBancariaIce(
						ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
		
		if (SistemaEnum.ICE.equals(legajoChequeRechazado.getSistemaOrigen())) {
			
			for (ShvLgjChequeRechazadoDetalleCobro detalleCobro : legajoChequeRechazado.getChequeRechazado().getCobros()) {
				
				if (TipoFacturacionIceEnum.esTipoFacturacionMic(detalleCobro.getTipoDeFacturacion())) { 
					
					// Origen 041 – aplicación Mic x Reversión de Ch. Rech. (el monto que corresponde reversar de Mic)
					contabilizarReversionDocumentoMicLegajoChequeRechazadoCircuitoCobranzaBancariaIce(
																					detalleCobro, idUsuario, 
																					legajoChequeRechazado.getIdLegajoChequeRechazado());

				} else if (TipoFacturacionIceEnum.esTipoFacturacionCalipso(detalleCobro.getTipoDeFacturacion())) {
					
					// Origen 042 – aplicación Calipso x Reversión de Ch. Rech (el monto que corresponde reversar de Calipso)
					contabilizarReversionDocumentoCalipsoLegajoChequeRechazadoCircuitoCobranzaBancariaIce(
																					detalleCobro, idUsuario, 
																					legajoChequeRechazado.getIdLegajoChequeRechazado());

				} else if (TipoFacturacionIceEnum.esTipoFacturacionDeimos(detalleCobro.getTipoDeFacturacion())) {
					
					// Origen 044 - Aplicación DEIMOs x Reversión de Ch. Rech (circuito cobranza bancaria)
					contabilizarReversionDocumentoDeimosLegajoChequeRechazadoCircuitoCobranzaBancariaIce(
																					detalleCobro, idUsuario, 
																					legajoChequeRechazado.getIdLegajoChequeRechazado());
				}
			}			

			// Al momento de confirmar el alta del legajo, si el cheque asociado es de ICE, 
			// y los cobros del mismo se encuentran completamente revertidos, generamos un 
			// movimiento “040 - Reversión por Ch. Rechazado (circuito cobranza bancaria)” con origen ICE
			
			contabilizarReversionChequeRechazadoCircuitoCobranzaBancariaIce(legajoChequeRechazado, idUsuario);
		}
	}
	
	
	/** 
	 * Sprint 04 - Gestionar el legajo de cheque Rechazados - Cobros relacionados
	 *
	 * Si el cheque es de ICE, una vez revertidos los cobros
	 * o Si el documento cobrado es de MIC, se envía “041 - Aplicación Mic x Reversión de Ch. Rech (circuito cobranza
	 *   bancaria)
	 */
	private void contabilizarReversionDocumentoMicLegajoChequeRechazadoCircuitoCobranzaBancariaIce(
					ShvLgjChequeRechazadoDetalleCobro detalleCobro, String idUsuario, Long idLegajoChequeRechazado) throws NegocioExcepcion {
		
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$041.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());	
		
		contabilidadDto.setCodigoClienteLegado(detalleCobro.getIdClienteLegado());
		contabilidadDto.setIdAnalista(idUsuario);
		contabilidadDto.setImporte(detalleCobro.getImporteAbsoluto());
		contabilidadDto.setMoneda(detalleCobro.getMoneda().name());
		contabilidadDto.setTransaccion(idLegajoChequeRechazado.toString());
		contabilidadDto.setNumeroComprobante(detalleCobro.getNumeroReferenciaMic());
		contabilidadDto.setIdCaja(detalleCobro.getCodigoBancoCobro());
		
		contabilidadServicio.contabilizar(contabilidadDto);
	}
	
	/**
	 * Sprint 04 - Gestionar el legajo de cheque Rechazados - Cobros relacionados
	 * 
	 * Si el cheque es de ICE, una vez revertidos los cobros
	 * o Si el documento cobrado es de CLP, se envía “042 - Aplicación Calipso x Reversión de Ch. Rech (circuito
	 *   cobranza bancaria)”
	 */
	private void contabilizarReversionDocumentoCalipsoLegajoChequeRechazadoCircuitoCobranzaBancariaIce(
			ShvLgjChequeRechazadoDetalleCobro detalleCobro, String idUsuario, Long idLegajoChequeRechazado) throws NegocioExcepcion {
		
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$042.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());		
		
		contabilidadDto.setCodigoClienteLegado(detalleCobro.getIdClienteLegado());
		contabilidadDto.setIdAnalista(idUsuario);
		contabilidadDto.setImporte(detalleCobro.getImporteAbsoluto());
		contabilidadDto.setMoneda(detalleCobro.getMoneda().name());
		contabilidadDto.setTransaccion(idLegajoChequeRechazado.toString());
		if(!Validaciones.isNullEmptyOrDash(detalleCobro.getNumeroLegal())) {
			contabilidadDto.setNumeroComprobante(detalleCobro.getNumeroLegal().replace("-", ""));
		}
		contabilidadDto.setIdCaja(detalleCobro.getCodigoBancoCobro());
		
		contabilidadServicio.contabilizar(contabilidadDto);
	}
	
	/**
	 * Sprint 04 - Gestionar el legajo de cheque Rechazados - Cobros relacionados
	 * 
	 * Si el cheque es de ICE, una vez revertidos los cobros
	 * o Si el documento cobrado es de DEIMOS, se envía “044 Aplicación DEIMOs x Reversión de Ch. Rech (circuito
	 *   cobranza bancaria)”
	 */ 
	private void contabilizarReversionDocumentoDeimosLegajoChequeRechazadoCircuitoCobranzaBancariaIce(
			ShvLgjChequeRechazadoDetalleCobro detalleCobro, String idUsuario, Long idLegajoChequeRechazado) throws NegocioExcepcion {
		
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$044.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());		

		contabilidadDto.setIdAnalista(idUsuario);
		contabilidadDto.setImporte(detalleCobro.getImporteAbsoluto());
		contabilidadDto.setMoneda(detalleCobro.getMoneda().name());
		contabilidadDto.setTransaccion(idLegajoChequeRechazado.toString());
		
		if (!Validaciones.isObjectNull(detalleCobro.getNumeroConvenio()) && !Validaciones.isObjectNull(detalleCobro.getNumeroCuota())) {
			contabilidadDto.setNumeroComprobante(detalleCobro.getNumeroConvenio().toString() + detalleCobro.getNumeroCuota().toString());
		}

		contabilidadDto.setIdCaja(detalleCobro.getCodigoBancoCobro());

		contabilidadServicio.contabilizar(contabilidadDto);
	}
	
	/**
	 * Sprint 07 - Gestionar el legajo de cheque Rechazados - Reembolso
	 * Si se presiona el botón “Enviar a Legales”, más allá del cambio de estado, se genera el movimiento contable
	 * “046 - Envío a legales de cheque rechazado (circuito manual)”
	 * Este movimiento se envía solo si el origen del cheque es Usuario.
	 * 
	 * @param legajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	private void contabilizarEnvioALegaglesLegajoChequeRechazadoCircuitoManual(
			ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
		
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$046.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());		
		
		if (Validaciones.isCollectionNotEmpty(legajoChequeRechazado.getChequeRechazado().getClientes())) {
			contabilidadDto.setCodigoClienteLegado(legajoChequeRechazado.getChequeRechazado().getClientes().iterator().next().getIdClienteLegado());
		}
		
		contabilidadDto.setIdAnalista(idUsuario);
		contabilidadDto.setImporte(legajoChequeRechazado.getImporte());
		contabilidadDto.setMoneda(legajoChequeRechazado.getMoneda().name());
		contabilidadDto.setTransaccion(legajoChequeRechazado.getIdLegajoChequeRechazado().toString());
		contabilidadDto.setNumeroComprobante(legajoChequeRechazado.getNumeroCheque().toString());
		contabilidadDto.setIdCaja(legajoChequeRechazado.getBancoOrigen().getIdBanco());
		contabilidadDto.setFechaValor(legajoChequeRechazado.getFechaRechazo());
		
		contabilidadServicio.contabilizar(contabilidadDto);
	}
	
	/**
	 * Sprint 09 - Gestionar el legajo de cheque Rechazados – Resumen
	 * Si el legajo se encuentra en Legales, el botón “Cerrar” aparece como “Desistir”, si el usuario presiona este
	 * botón más allá del cambio de estado, se generará el movimiento contable “047 - Desistimiento de cheque
	 * rechazado (circuito manual)”. 
	 * Este movimiento se envía solo si el origen del cheque es Usuario.
	 * 
	 * @param legajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	private void contabilizarDesistirLegajoChequeRechazadoCircuitoManual(
			ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
		
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$047.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());		
		
		if (Validaciones.isCollectionNotEmpty(legajoChequeRechazado.getChequeRechazado().getClientes())) {
			contabilidadDto.setCodigoClienteLegado(legajoChequeRechazado.getChequeRechazado().getClientes().iterator().next().getIdClienteLegado());
		}
		
		contabilidadDto.setIdAnalista(idUsuario);
		contabilidadDto.setImporte(legajoChequeRechazado.getImporte());
		contabilidadDto.setMoneda(legajoChequeRechazado.getMoneda().name());
		contabilidadDto.setTransaccion(legajoChequeRechazado.getIdLegajoChequeRechazado().toString());
		contabilidadDto.setNumeroComprobante(legajoChequeRechazado.getNumeroCheque().toString());
		contabilidadDto.setIdCaja(legajoChequeRechazado.getBancoOrigen().getIdBanco());
		contabilidadDto.setFechaValor(legajoChequeRechazado.getFechaRechazo());
		
		contabilidadServicio.contabilizar(contabilidadDto);
	}
	/**
	 * Buscar el Legajo y levanta todas sus relacilaciones
	 */
	@Override
	public Modelo buscar(Long idLegajoChequeRechazado) throws NegocioExcepcion {
		if (Validaciones.isObjectNull(idLegajoChequeRechazado)) {
			throw new NegocioExcepcion("buscar idLegajoChequeRechazado no puede ser nulo");
		}
		try {
			//ControlVariablesSingleton.permitirTraceoSQL();
			ShvLgjLegajoChequeRechazado shvLgjLegajoChequeRechazado = legajoChequeRechazadoDao.buscar(idLegajoChequeRechazado);
			//List<E> observacionesWorkflowServicio.listarObservacionesActualesByIdWorkflowOrdenadoPorFecha(shvLgjLegajoChequeRechazado.getWorkflow().getIdWorkflow());
			return shvLgjLegajoChequeRechazado;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public Modelo buscarSimplificadoConWorkFlow(Long idLegajoChequeRechazado) throws NegocioExcepcion {
		if (Validaciones.isObjectNull(idLegajoChequeRechazado)) {
			throw new NegocioExcepcion("buscar idLegajoChequeRechazado no puede ser nulo");
		}
		try {
			ShvLgjLegajoChequeRechazadoSimplificadoWorkFlow shvLgjLegajoChequeRechazado = legajoChequeRechazadoDao.buscarSimplificadoConWorkFlow(idLegajoChequeRechazado);
			//List<E> observacionesWorkflowServicio.listarObservacionesActualesByIdWorkflowOrdenadoPorFecha(shvLgjLegajoChequeRechazado.getWorkflow().getIdWorkflow());
			return shvLgjLegajoChequeRechazado;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public List<Estado> listarComboEstados() throws NegocioExcepcion {
		try {
			List<Estado> lista = Estado.listarEstados("LGJ");
			return lista;
		} catch (Exception e){
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	public List<Bean> listar(Filtro filtro) throws NegocioExcepcion {
		return null; // TODO LISTAR Legajos
	}
	
	/**
	 * Busca en la vista SHV_SOP_LGJ_BUSQUEDA mediante le filtro que recibe.
	 */
	@Override
	public List<Bean> listarBusqueda(Filtro filtro) throws NegocioExcepcion {
		VistaSoporteLegajoChequeRechazadoFiltro vistaSoporteLegajoChequeRechazadoFiltro = (VistaSoporteLegajoChequeRechazadoFiltro) filtro;
		//Se valida el filtro de la busqueda. 
		//Si se valida OK se busca en la vista. 
		//Si se valida NOK se lanza una excepcion.
		legajoChequeRechazadoValidacionServicio.validarFiltroBusquedaLegajos(vistaSoporteLegajoChequeRechazadoFiltro);

		vistaSoporteLegajoChequeRechazadoFiltro.getIdClientesLegado().addAll(
			this.completarFiltroConClientes(vistaSoporteLegajoChequeRechazadoFiltro.obtenerClienteSiebelFiltro()
		));

		return vistaSoporteServicio.listarLegajosChequeRechazado(vistaSoporteLegajoChequeRechazadoFiltro);
	}
	@Override
	public EdicionTipoEnum validarEdicionSegunPerfilEstado(String idAnalista, String idCopropietario, Estado estado, UsuarioSesion userSesion) throws NegocioExcepcion {
		EdicionTipoEnum edicion = null;

		if (userSesion.getUsuario().equals(idAnalista) || userSesion.getUsuario().equals(idCopropietario)) {
			switch (estado) {
			case LGJ_NO_DISPONIBLE:
				edicion = EdicionTipoEnum.SIN_EDICION;
				break;
			case LGJ_ABIERTO:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_2;
				break;
			case LGJ_ABIERTO_CON_CHEQUE_ENTREGADO:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_3;
				break;
			case LGJ_EN_PROCESO_REVERSION_COBROS_RELACIONADOS:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_2;
				break;
			case LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_4_1;
				break;
			case LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_ENTREGADO:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_4;
				break;
			case LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_5_1;
				break;
			case LGJ_REEMBOLSADO_CON_CHEQUE_ENTREGADO:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_5;
				break;
			case LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_6;
				break;
			case LGJ_CERRADO_CON_CHEQUE_ENTREGADO:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_1;
				break;
			case LGJ_ENVIADO_A_LEGALES:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_7;
				break;
			case LGJ_DESISTIDO:
				edicion = EdicionTipoEnum.EDICION_PARCIAL_1;
				break;
			case LGJ_ANULADO:
				edicion = EdicionTipoEnum.SIN_EDICION;
				break;
			default:
				edicion = EdicionTipoEnum.SIN_EDICION;
				break;
			}
		} else {
			edicion = EdicionTipoEnum.SIN_EDICION;
		}
		return edicion;
	}
	@Override
	public Set<String> completarFiltroConClientes(ClienteFiltro filtro) throws NegocioExcepcion {
		Set<String> clientesLegados = new HashSet<String>();

		if (!Validaciones.isNullEmptyOrDash(filtro.getCriterio())) {
			try {
				List<ClienteBean> clienteBeanList = this.clienteServicio.consultarClientes(filtro);

				if (clienteBeanList.isEmpty()) {
					throw new ValidacionExcepcion("","error.codClienteSiebelInexistente");
				}

				for (ClienteBean clienteBean : clienteBeanList) {
					clientesLegados.add(clienteBean.getIdClienteLegadoString());
				}
			} catch (NegocioExcepcion e) {
				if (e.getCause() instanceof ValidacionExcepcion) {
					ValidacionExcepcion ve = null;
					ve = (ValidacionExcepcion) e.getCause();

					List<String> campos = new ArrayList<String>();
					List<String> leyendas = new ArrayList<String>();
					campos.add("#errorTextCriterio");

					leyendas.add(Propiedades.MENSAJES_PROPIEDADES.getString(ve.getLeyenda()));
					throw new ValidacionExcepcion(campos,leyendas);
				} else if(e instanceof ValidacionExcepcion) {
					List<String> campos = new ArrayList<String>();
					List<String> leyendas = new ArrayList<String>();
					campos.add("#errorTextCriterio");
					leyendas.add(Propiedades.MENSAJES_PROPIEDADES.getString(((ValidacionExcepcion) e).getLeyenda()));
					throw new ValidacionExcepcion(campos,leyendas);
				} else {
					NegocioExcepcion ex = null;
					throw new NegocioExcepcion(e.getMensajeAuxiliar(), (NegocioExcepcion)ex);
				}
			}
		}
		return clientesLegados;
	}

	/**
	 * @throws ShivaExcepcion 
	 * 
	 */
	@Override
	public List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listarCobrosRelacionados(VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro filtro) throws NegocioExcepcion, ShivaExcepcion {
		return vistaSoporteServicio.listarCobrosRelacionados(filtro);
	}

	
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void revertirCobrosRelacionadosIce(List<Long> listaIdDetCobro, Estado estadoWorkflow, Integer idWorkflow, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		try {
			
			List<ShvLgjChequeRechazadoDetalleCobroSimplificado> listaDetCobroSimplificado = new ArrayList<ShvLgjChequeRechazadoDetalleCobroSimplificado>();
			
			listaDetCobroSimplificado = legajoChequeRechazadoDao.listarDetCobroSimplificado(listaIdDetCobro);
			
			for (ShvLgjChequeRechazadoDetalleCobroSimplificado detCobroSimplificado : listaDetCobroSimplificado) {
				
				//Si el tipo de facturacion es FACTURACION_GENESIS, CAMBIO EL ESTADO A PENDIENTE_ENVIAR_ICE, SI ES CALIPSO o DEIMOS o Cobranza integrada, DIRECTAMENTE PASA A REVERTIDO
				if(CodigoTareaIceEnum.esCobranzaIntegrada(detCobroSimplificado.getCodigoDeTarea())
						|| TipoFacturacionIceEnum.esTipoFacturacionCalipso(detCobroSimplificado.getTipoDeFacturacion())
						|| TipoFacturacionIceEnum.esTipoFacturacionDeimos(detCobroSimplificado.getTipoDeFacturacion())
						|| (TipoFacturacionIceEnum.esTipoFacturacionMic(detCobroSimplificado.getTipoDeFacturacion())
								&& !TipoFacturacionIceEnum.FACTURACION_GENESIS.equals(TipoFacturacionIceEnum.getEnumByCodigo(detCobroSimplificado.getTipoDeFacturacion())))) {
					
					detCobroSimplificado.setEstado(EstadoChequeRechazadoDetalleCobroEnum.REVERTIDO);
					
				} else if (TipoFacturacionIceEnum.FACTURACION_GENESIS.equals(TipoFacturacionIceEnum.getEnumByCodigo(detCobroSimplificado.getTipoDeFacturacion()))){
					
					detCobroSimplificado.setEstado(EstadoChequeRechazadoDetalleCobroEnum.PEND_ENVIAR_ICE);
				}
				//Guardo el detalle cobro
				legajoChequeRechazadoDao.modificarCobrosRelacionados(detCobroSimplificado);
			}
			
			solicitarReversionDeCobrosrelacionados(estadoWorkflow,idWorkflow,userSesion);
			
		}catch(PersistenciaExcepcion e){
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	
	/**
	 * 
	 * @param idLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void anular(Long idLegajoChequeRechazado, String idUsuario) throws NegocioExcepcion {
		try {
			anular(legajoChequeRechazadoDao.buscar(idLegajoChequeRechazado), idUsuario);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param legajoChequeRechazado
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	private void anular(ShvLgjLegajoChequeRechazado legajoChequeRechazado, String idUsuario) throws NegocioExcepcion {

		String datosModificados = "";
		
//		TODO: Aqui debiera agregar la invocacion al metodo que valida si el cobro se puede anular, asi como se hace en el online				

		
		if (SistemaEnum.SHIVA.equals(legajoChequeRechazado.getSistemaOrigen())) {
			
			StringBuffer observacionesDisponibilizar = new StringBuffer();
			
			observacionesDisponibilizar.append(Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.leyenda.idLegajoChequeRechazadoAnulado"));
			observacionesDisponibilizar.append(Constantes.DOS_PUNTOS);
			observacionesDisponibilizar.append(Constantes.WHITESPACE); 
			observacionesDisponibilizar.append(legajoChequeRechazado.getIdLegajoChequeRechazado());
			
			try {
				valorServicio.disoniblizarValor(legajoChequeRechazado.getChequeRechazado().getIdValor(), 
												observacionesDisponibilizar.toString(), 
												idUsuario,
												legajoChequeRechazado.getFechaRechazo());

			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		
		} else if (SistemaEnum.USUARIO.equals(legajoChequeRechazado.getSistemaOrigen())) {

			anularContabilidadAltaLegajoChequeRechazadoCircuitoManual(legajoChequeRechazado);
		}
		
		// Anulo el Legajo avanzando el WF según corresponda
		
		if (Estado.LGJ_ABIERTO.equals(legajoChequeRechazado.getWorkflow().getEstado())) {
			workflowLegajoChequesRechazadosServicio.anularLegajoAbierto(legajoChequeRechazado.getWorkflow(), datosModificados, idUsuario);

		} else if (Estado.LGJ_EN_PROCESO_REVERSION_COBROS_RELACIONADOS.equals(legajoChequeRechazado.getWorkflow().getEstado())
//						&& SistemaEnum.ICE.equals(legajoChequeRechazado.getSistemaOrigen())
//						&& legajoChequeRechazado.getChequeRechazado().isAlMenosUnCobroRevertido()
						) {

			workflowLegajoChequesRechazadosServicio.anularLegajoEnProcesoDeReversion(legajoChequeRechazado.getWorkflow(), datosModificados, idUsuario);
		}
	}
	@Override
	public ComprobanteDto insertarDocumentoAdjunto(Long idLegajo, ComprobanteDto comprobante) throws NegocioExcepcion {
		try {
			ShvDocDocumentoAdjunto docAdjunto = new ShvDocDocumentoAdjunto();
			docAdjunto.setArchivoAdjunto(comprobante.getDocumento());
			docAdjunto.setDescripcion(comprobante.getDescripcionArchivo());
			docAdjunto.setFechaCreacion(new Date());
			docAdjunto.setNombreArchivo(comprobante.getNombreArchivo());
			docAdjunto.setUsuarioCreacion(comprobante.getUsuarioCreacion());
			docAdjunto.setIdValor(new ArrayList<ShvValValor>());
			
			docAdjunto = documentoAdjuntoDao.crear(docAdjunto);
			
			ShvLgjAdjuntoPk legajoAdjuntoPK = new ShvLgjAdjuntoPk();
			legajoAdjuntoPK.setDocumentoAdjunto(docAdjunto);
			legajoAdjuntoPK.setIdLegajo(idLegajo);
			
			ShvLgjAdjunto legajoAdjunto = new ShvLgjAdjunto();
			legajoAdjunto.setPk(legajoAdjuntoPK);
			
			legajoAdjunto = legajoChequeRechazadoDao.insertarDocumentoAdjunto(legajoAdjunto);
			
			comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());
			
			return comprobante;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}
	@Override
	public void eliminarDocumentoAdjuntoDelLegajo(Long idComprobante) throws NegocioExcepcion {
		try {
			ShvDocDocumentoAdjunto docAdjunto = documentoAdjuntoDao.buscarDocumentoAdjunto(idComprobante);
			legajoChequeRechazadoDao.eliminarDocumentoAdjuntoDelLegajo(docAdjunto);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}
	
	public List<ComprobanteDto> listarComprobantes(Long idLegajo) throws NegocioExcepcion {
		List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
		List<ShvDocDocumentoAdjunto> listaAdjuntos = null;

		try {
			listaAdjuntos = legajoChequeRechazadoDao.buscarAdjuntosDelLegajo(idLegajo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		if (Validaciones.isCollectionNotEmpty(listaAdjuntos)) {
			for (ShvDocDocumentoAdjunto docAdjunto : listaAdjuntos) {
				ComprobanteDto comprobante = new ComprobanteDto();
				comprobante.setId(docAdjunto.getIdValorAdjunto());
				comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());
				comprobante.setNombreArchivo(docAdjunto.getNombreArchivo());
				comprobante.setDescripcionArchivo(docAdjunto.getDescripcion());
				comprobante.setDocumento(docAdjunto.getArchivoAdjunto());
				comprobante.setUsuarioCreacion(docAdjunto.getUsuarioCreacion());
				comprobante.setFechaAlta(docAdjunto.getFechaCreacion());
				listaComprobantes.add(comprobante);
			}
		}
		return listaComprobantes;
	}
	@Override
	public ComprobanteDto buscarAdjuntoLegajo(Long idAdjunto) throws NegocioExcepcion {
		try {
			ShvDocDocumentoAdjunto docAdjunto = documentoAdjuntoDao.buscarDocumentoAdjunto(idAdjunto);
			
			if (docAdjunto!=null) {
				ComprobanteDto comprobante = new ComprobanteDto();
				comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());
				comprobante.setNombreArchivo(docAdjunto.getNombreArchivo());
				comprobante.setDescripcionArchivo(docAdjunto.getDescripcion());
				comprobante.setDocumento(docAdjunto.getArchivoAdjunto());
				comprobante.setUsuarioCreacion(docAdjunto.getUsuarioCreacion());
				comprobante.setFechaAlta(docAdjunto.getFechaCreacion());
				
				return comprobante;
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}

	@Override
	public VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado obtenerLegajoChequeRechazadoCobrosRelacionadosEstadoReversionShivaBy(Long idOperacion) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerLegajoChequeRechazadoCobrosRelacionadosEstadoReversionShivaBy(idOperacion);
	}

	@Override
	public void confirmarFinReversion(Long idLegajo, List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listaCobrosRelacionados, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		try {
				
			ShvLgjLegajoChequeRechazado legajoChequeRechazado = legajoChequeRechazadoDao.buscar(idLegajo);

			if(SistemaEnum.SHIVA.equals(legajoChequeRechazado.getSistemaOrigen())){
				if(cobrosRelacionadosSoloConReintegros(listaCobrosRelacionados)){
					//Transicionamos cuando se presiona el botón “Confirmar fin de reversión” en la solapa de Cobros Relacionados, y los cobros sobre los cuales aplica el
					//cheque no poseen documentos, solo reintegros o similares
					ShvWfWorkflow wf = buscarWorkflowActual(legajoChequeRechazado.getWorkflow().getIdWorkflow());
					workflowLegajoChequesRechazadosServicio.solicitarOmitirProcesoDeReembolsoParaLegajoConChequeRechazadoPendienteDeEntrega(wf, "", userSesion.getIdUsuario());
				} else {
					//Solicitar Inicio Proceso de Reembolso: Transicionamos cuando en la solapa de cobros relacionados el usuario da al botón “Confirmar fin de reversión”
					ShvWfWorkflow wf = buscarWorkflowActual(legajoChequeRechazado.getWorkflow().getIdWorkflow());
					workflowLegajoChequesRechazadosServicio.solicitarInicioProcesodeReembolso(wf, "", userSesion.getIdUsuario());
				}
				
				
				// Si el cheque del cual se recibió el rechazo es administrado por el sistema Shiva y el mismo tiene todo su saldo suspendido, 
				// entonces se deberá proceder a la anulación del mismo.
				
				StringBuffer observacionesAnulacion = new StringBuffer();
				observacionesAnulacion.append(Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.leyenda.idLegajoChequeRechazado"));
				observacionesAnulacion.append(Constantes.DOS_PUNTOS);
				observacionesAnulacion.append(Constantes.WHITESPACE); 
				observacionesAnulacion.append(idLegajo);
				
				valorServicio.anularValor(legajoChequeRechazado.getChequeRechazado().getIdValor(), 
										 observacionesAnulacion.toString(), 
										 userSesion.getIdUsuario(),
										 legajoChequeRechazado.getFechaRechazo());
				
			} else if(SistemaEnum.ICE.equals(legajoChequeRechazado.getSistemaOrigen())){
				/** Modificación de cheques rechazados Needit 66608
				 * u576779
				 * Se agrega el nuevo estado ENVIADO_ICE en la transición de detalle cobro
				 * */
				
				List<ShvLgjChequeRechazadoDetalleCobro> listaRegistrosDetalleCobro = legajoChequeRechazadoDao.listarDetalleCobrosEnviadoAIcePorIdChequeRechazado(legajoChequeRechazado.getChequeRechazado().getIdChequeRechazado());
				listaRegistrosDetalleCobro = legajoChequeRechazadoReversionIceServicio.actualizarEstadoDetalleCobroARevertido(listaRegistrosDetalleCobro);
				
				legajoChequeRechazadoDao.actualizarRegistrosDetalleCobroPorEnvioIce(listaRegistrosDetalleCobro);
				//Solicitar Inicio Proceso de Reembolso: Transicionamos cuando en la solapa de cobros relacionados el usuario da al botón “Confirmar fin de reversión”
				ShvWfWorkflow wf = buscarWorkflowActual(legajoChequeRechazado.getWorkflow().getIdWorkflow());
				workflowLegajoChequesRechazadosServicio.solicitarInicioProcesodeReembolso(wf, "", userSesion.getIdUsuario());
				contabilizarReversionCobranzasLegajoChequeRechazadoCircuitoCobranzaBancariaIce(legajoChequeRechazado,legajoChequeRechazado.getIdAnalista());
			}
		
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Retorna false si al menos hay un documento de tipo FAC,DEB,DUC. Sino retorna true
	 * @param listaCobrosRelacionados
	 * @return
	 */
	private boolean cobrosRelacionadosSoloConReintegros(List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listaCobrosRelacionados) {
		
		//Me fijo en los cobros relacionados, si los documentos son facturas,debitos o duc
		for (VistaSoporteResultadoBusquedaLegajoCobrosRelacionado cob : listaCobrosRelacionados) {
			if (TipoComprobanteEnum.FAC.name().equals(cob.getTipoDocumento())
				||	TipoComprobanteEnum.DEB.name().equals(cob.getTipoDocumento())
				||	TipoComprobanteEnum.DUC.name().equals(cob.getTipoDocumento())){
				
				return false;
			}
		}
		return true;
		
	}
	
	/**
	 * Solicitar Reversión de cobros relacionados Transicionamos cuando se presiona el revertir al primer cobro, independientemente de que sea ICE
	 * o Shiva
	 * @param estado
	 * @param idWorkflow
	 * @param userSesion
	 * @throws NegocioExcepcion
	 */
	@Override
	public void solicitarReversionDeCobrosrelacionados(Estado estado, Integer idWorkflow, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		if (Estado.LGJ_ABIERTO.equals(estado)){
			ShvWfWorkflow wf = buscarWorkflowActual(idWorkflow);
			workflowLegajoChequesRechazadosServicio.solicitarReversionDeCobrosrelacionados(wf, "", userSesion.getIdUsuario());
		}
	}
	
	@Override
	public ShvWfWorkflow buscarWorkflowActual(Integer idWorkflow) throws NegocioExcepcion {
		
		ShvWfWorkflow wf = workflowLegajoChequesRechazadosServicio.buscarWorkflow(idWorkflow);
		return wf;
	}
	
	@Override
	public boolean legajoEsAnulable(List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listaCobrosRelacionados, Estado estado) throws NegocioExcepcion{
		
		
		if (Estado.LGJ_ABIERTO.equals(estado)){
			
			return true;

		} else if (Estado.LGJ_EN_PROCESO_REVERSION_COBROS_RELACIONADOS.equals(estado)){
			//Si hay cobros relacionados pendiente de revertir, entonces el legajo no es anulable.
			SistemaEnum sistema = listaCobrosRelacionados.get(0).getSistemaOrigen();
			return !hayCobrosRelacionadosPendienteRevertir(listaCobrosRelacionados, sistema);
		} 
		
		return false;
	}
	
	private boolean hayCobrosRelacionadosPendienteRevertir(List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listaCobrosRelacionados, SistemaEnum sistema) {
		
		
		for (VistaSoporteResultadoBusquedaLegajoCobrosRelacionado cob : listaCobrosRelacionados) {
			if(SistemaEnum.SHIVA.equals(sistema)){
				if (!Estado.DES_DESCOBRADO.toString().equals(cob.getEstadoReversionShiva())
						&& !Estado.DES_ANULADO.toString().equals(cob.getEstadoReversionShiva())){
					return true;
				}
			} else {
				if(SistemaEnum.ICE.equals(sistema)){
					if (EstadoChequeRechazadoDetalleCobroEnum.PEND_ENVIAR_ICE.toString().equals(cob.getEstadoReversionIce())){
						return true;
					}
				}
			}
		}
		
		return false;
		
	}
	@Override	//ShvLgjChequeRechazadoDetalleDocumentoS
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> listarCobroRelacionadoDocumentosBy(Long idLegajoChequeRechazado) throws NegocioExcepcion {
		return vistaSoporteServicio.listarChequeRechazadoDetalleDocumento(idLegajoChequeRechazado);
	}

	@Override
	public void actualizarSaldosDocumentosReembolso() throws NegocioExcepcion {
		
		try {
			listaDocumentos = legajoChequeRechazadoDao.listarDetalleDocumentoSimplificados();
		
			for (ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDocumento : listaDocumentos) {
				
				if (SistemaEnum.CALIPSO.equals(detDocumento.getSistemaOrigen()) && LIMITE_CALIPSO > listaDocCalipso.size()){
	
					String numDocumentoClp = detDocumento.getNumeroLegal();
					
					if(!listaNumDocumentoCLP.contains(numDocumentoClp)){
						detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.PENDIENTE);
						listaDocCalipso.add(detDocumento);
						listaNumDocumentoCLP.add(numDocumentoClp);
					} else {
						listaDocumentosRepetidos.add(detDocumento);
					}
					
				} else if (SistemaEnum.MIC.equals(detDocumento.getSistemaOrigen()) && LIMITE_MIC > listaDocMic.size()){
					
					String numReferenciaMic = detDocumento.getNumeroReferenciaMic();
					
					if(!listaNumReferenciaMIC.contains(numReferenciaMic)){
						detDocumento.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.PENDIENTE);
						listaDocMic.add(detDocumento);
						listaNumReferenciaMIC.add(numReferenciaMic);
					} else {
						listaDocumentosRepetidos.add(detDocumento);
					}
				}
			}
			
			if (LIMITE_CALIPSO >= listaDocCalipso.size() && Validaciones.isCollectionNotEmpty(listaDocCalipso)) {
				
				for (ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDocumento : listaDocCalipso) {
					ConsultaSaldoACobradoresChequeRechazadoThread consultarCobradores = null; 
					cargarDetDocumentoYEjecutarThreads(detDocumento,consultarCobradores);
				}
			}
			
			if (LIMITE_MIC >= listaDocMic.size() && Validaciones.isCollectionNotEmpty(listaDocMic)) {
				
				for (ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDocumento : listaDocMic) {
					ConsultaSaldoACobradoresChequeRechazadoThread consultarCobradores=null;
					cargarDetDocumentoYEjecutarThreads(detDocumento,consultarCobradores);
				}
			}
			
			/**
			 * Queda en un loop, preguntando por el estado de todos los documentos, hasta que todos se encuentren en estado "Finalizado".
			 * 
			 */
			
			int finalizados = 0;
			int enProceso = 0;
			while (finalizados < listaThreads.size()){
				
				for (ConsultaSaldoACobradoresChequeRechazadoThread hilo: listaThreads) {
						
					if (EstadoConsultaSaldoChequeRechazadoEnum.EN_PROCESO.equals(hilo.getDetDocumento().getEstado())){
						try {
							enProceso++;
							Thread.sleep(500);
						} catch (InterruptedException e) {
							throw new NegocioExcepcion(e.getMessage(),e);
						}
					} else {
						finalizados++;
					}
					
				}
				if (finalizados >=listaThreads.size()){
					Traza.auditoria(LegajoChequeRechazadoServicioImpl.class, "FINALIZADOS [ " + finalizados + " de " + listaThreads.size()  + " ]");
					break;
				}else{
					Traza.auditoria(LegajoChequeRechazadoServicioImpl.class, "FINALIZADOS [ " + finalizados + " de " + listaThreads.size()  + " ]" + " [ EN PROCESO:  "+ enProceso + " de " + listaThreads.size() + " ]");
					finalizados=0;
					enProceso=0;
				}
			}
			
			/**
			 * Controla que todos los hilos hayan finalizado.
			 */
			
			int hilosFinalizados = 0;
			while (hilosFinalizados < listaThreads.size()){
				for (ConsultaSaldoACobradoresChequeRechazadoThread hilo : listaThreads) {
					if(!hilo.isAlive()){
						hilosFinalizados++;
					}
				}
				if (hilosFinalizados < listaThreads.size()){
					hilosFinalizados = 0;
				}
			}
			
			Traza.auditoria(LegajoChequeRechazadoServicioImpl.class, "HILOS FINALIZADOS[" + hilosFinalizados +"]");
			Traza.auditoria(LegajoChequeRechazadoServicioImpl.class, "No hay más débitos para consultar...");
			
			
			for (ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDocRepetido : listaDocumentosRepetidos) {
				
				for (ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDoc : listaDocumentos) {
					
					if (EstadoConsultaSaldoChequeRechazadoEnum.FINALIZADO_OK.equals(detDoc.getEstado())){
						
						if (SistemaEnum.CALIPSO.equals(detDoc.getSistemaOrigen())
								&& SistemaEnum.CALIPSO.equals(detDocRepetido.getSistemaOrigen())){
							if(detDocRepetido.getNumeroLegal().equals(detDoc.getNumeroLegal())){
								
								detDocRepetido.setSaldoDocumento(detDoc.getSaldoDocumento());
								break;
							}
							
						} else if (SistemaEnum.MIC.equals(detDoc.getSistemaOrigen())
								&& SistemaEnum.MIC.equals(detDocRepetido.getSistemaOrigen())){
							if (detDocRepetido.getNumeroReferenciaMic().equals(detDoc.getNumeroReferenciaMic())){
								detDocRepetido.setSaldoDocumento(detDoc.getSaldoDocumento());
								break;
							}
						}
					}
				}
			}
			
			for (ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDocRepetido : listaDocumentosRepetidos) {
				
				for (ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDoc : listaDocumentos) {
						
					if (detDoc.getIdChequeRechazadoDocumento().equals(detDocRepetido.getIdChequeRechazadoDocumento())){
						detDoc = detDocRepetido;
					}
				}
			}
		
			for (ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDoc : listaDocumentos){
				detDoc.setFechaActualizacion(new Date());
				legajoChequeRechazadoDao.actualizarChequeRechazadoDetalleDocumentoSimplificado(detDoc);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}		
		
		
	}
	
	private void cargarDetDocumentoYEjecutarThreads(ShvLgjChequeRechazadoDetalleDocumentoSimplificado shvLgjChequeRechazadoDetalleDocumentoSimplificado,
			ConsultaSaldoACobradoresChequeRechazadoThread consultarCobradores) {
		
		//Nuevo Thread
		consultarCobradores = new ConsultaSaldoACobradoresChequeRechazadoThread();
		
		consultarCobradores.setName("Thread [ID" + shvLgjChequeRechazadoDetalleDocumentoSimplificado.getIdChequeRechazadoDocumento() + "]" );
		setearDatosParaConsulta(shvLgjChequeRechazadoDetalleDocumentoSimplificado,consultarCobradores);
		consultarCobradores.setLegajoChequeRechazadoServicio(this);
		consultarCobradores.start();
		listaThreads.add(consultarCobradores);
		
	}

	private void setearDatosParaConsulta(ShvLgjChequeRechazadoDetalleDocumentoSimplificado shvLgjChequeRechazadoDetalleDocumentoSimplificado,
			ConsultaSaldoACobradoresChequeRechazadoThread consultarCobradores) {

		if(SistemaEnum.CALIPSO.equals(shvLgjChequeRechazadoDetalleDocumentoSimplificado.getSistemaOrigen())){
			
			IdDocumento documento = new IdDocumento();
			NroDocumentoLegal nroDocLegal = new NroDocumentoLegal();
			nroDocLegal = Utilidad.obtenerNroDocumentoLegal(shvLgjChequeRechazadoDetalleDocumentoSimplificado.getNumeroLegal());
			if (!Validaciones.isObjectNull(shvLgjChequeRechazadoDetalleDocumentoSimplificado.getTipoComprobante())){
				documento.setTipoComprobante(shvLgjChequeRechazadoDetalleDocumentoSimplificado.getTipoComprobante());
			}
			documento.setNumeroComprobante(nroDocLegal.getNumero());
			documento.setSucursalComprobante(nroDocLegal.getSucursal());
			
			if(!Validaciones.isObjectNull(nroDocLegal.getClase())){
				documento.setClaseComprobante(nroDocLegal.getClase());
			}
			
			EntradaCalipsoDeudaWS entradaCalipsoDeudaWs= new EntradaCalipsoDeudaWS();
			entradaCalipsoDeudaWs.setIdDocumento(documento);
			entradaCalipsoDeudaWs.setMoneda(MonedaEnum.TOD);
			consultarCobradores.setCobrador(SistemaEnum.CALIPSO);
			
			if (!Validaciones.isObjectNull(shvLgjChequeRechazadoDetalleDocumentoSimplificado.getIdClienteLegado())){
				//Creo el cliente y lo agrego a la lista de clientes.
				List<Cliente> listaCliente = new ArrayList<Cliente>();
				Cliente cliente = new Cliente();
				cliente.setIdClienteLegado(BigInteger.valueOf(shvLgjChequeRechazadoDetalleDocumentoSimplificado.getIdClienteLegado()));
				cliente.setIdDocumento(documento);
				listaCliente.add(cliente);
				
				entradaCalipsoDeudaWs.setListaClientes(listaCliente);
				
			}
			
			InformacionParaPaginadoDebito informacionParaPaginado = new InformacionParaPaginadoDebito();
			informacionParaPaginado.setCantidadDeRegistrosARetornar(BigInteger.ONE);
			entradaCalipsoDeudaWs.setInformacionParaPaginado(informacionParaPaginado);
			
			consultarCobradores.setEntradaCalipsoDeudaWs(entradaCalipsoDeudaWs);
			
		}else{
			if (SistemaEnum.MIC.equals(shvLgjChequeRechazadoDetalleDocumentoSimplificado.getSistemaOrigen())){
				
				MicConsultaDeudaEntrada micConsultaDeudaEntrada = new MicConsultaDeudaEntrada();
				MicInformacionFactura informacionFactura = new MicInformacionFactura();
				
				informacionFactura.setNumeroReferenciaMIC(shvLgjChequeRechazadoDetalleDocumentoSimplificado.getNumeroReferenciaMic());
				informacionFactura.setTipoComprobante(TipoComprobanteEnum.TOD);
				micConsultaDeudaEntrada.setInformacionFactura(informacionFactura);
				
				if (!Validaciones.isObjectNull(shvLgjChequeRechazadoDetalleDocumentoSimplificado.getIdClienteLegado())){
					List<BigInteger> listaClientes = new ArrayList<BigInteger>();
					listaClientes.add(BigInteger.valueOf(shvLgjChequeRechazadoDetalleDocumentoSimplificado.getIdClienteLegado()));
					micConsultaDeudaEntrada.setListaClientes(listaClientes);
				}
				MicInformacionPaginadoDebito paginado = new MicInformacionPaginadoDebito();
				paginado.setCantidadRegistrosARetornar(1);
				micConsultaDeudaEntrada.setInformacionPaginado(paginado);
				consultarCobradores.setCobrador(SistemaEnum.MIC);
				consultarCobradores.setMicConsultaDeudaEntrada(micConsultaDeudaEntrada);
				
			}
		}
		
		shvLgjChequeRechazadoDetalleDocumentoSimplificado.setEstado(EstadoConsultaSaldoChequeRechazadoEnum.EN_PROCESO);
		consultarCobradores.setDetDocumento(shvLgjChequeRechazadoDetalleDocumentoSimplificado);
		
	}

	public synchronized void obtenerDetDocumentoActualizado(ShvLgjChequeRechazadoDetalleDocumentoSimplificado detDocumento,ConsultaSaldoACobradoresChequeRechazadoThread consultarCobradores){
		int index=0;
		int indexLista=0;
		if(listaDocumentos.indexOf(detDocumento) >= 0){
			
			index = listaDocumentos.indexOf(detDocumento);

			Traza.auditoria(LegajoChequeRechazadoServicioImpl.class, "" + "]");
			listaDocumentos.set(index, detDocumento);
			
			if (SistemaEnum.CALIPSO.equals(detDocumento.getSistemaOrigen())){
				indexLista = listaDocCalipso.indexOf(detDocumento);
				listaDocCalipso.remove(indexLista);
			} else { 
				indexLista =listaDocMic.indexOf(detDocumento);
				listaDocMic.remove(indexLista);
			}
			
			if(!listaDocumentos.isEmpty()){
				
				boolean siguiente=false;
				
				while(!siguiente && index < listaDocumentos.size()){
					
					if(EstadoConsultaSaldoChequeRechazadoEnum.PENDIENTE.equals(detDocumento.getEstado())){
						
						if(SistemaEnum.CALIPSO.equals(listaDocumentos.get(index).getSistemaOrigen())){
							
							listaDocCalipso.add(listaDocumentos.get(index));
							Traza.auditoria(CobroOnlineServicioImpl.class, "THREAD ANTERIOR [ID");
							cargarDetDocumentoYEjecutarThreads(listaDocCalipso.get(listaDocCalipso.size()-1),consultarCobradores);
							
						} else if(SistemaEnum.MIC.equals(listaDocumentos.get(index).getSistemaOrigen())){
							
							listaDocMic.add(listaDocumentos.get(index));
							Traza.auditoria(LegajoChequeRechazadoServicioImpl.class,"[");
							cargarDetDocumentoYEjecutarThreads(listaDocMic.get(listaDocMic.size()-1),consultarCobradores);
							
						}
						siguiente=true;
					}else{
						
						if(index < listaDocumentos.size()){
							index++;
						}
					}
				}
			}
		}
		
	}
	
	public HSSFWorkbook exportarDetalleLegajo(LegajoChequeRechazadoDto legajoChequeRechazadoDto) throws NegocioExcepcion {
		
		Map<String, String> map = this.obtenerMontos(
				legajoChequeRechazadoDto.getIdLegajoChequeRechazado(),SistemaEnum.getEnumByDescripcion(
					legajoChequeRechazadoDto.getSistemaOrigen()
				),
				legajoChequeRechazadoDto.getEstado()
			);
			
			legajoChequeRechazadoDto.setMontoARevertir(map.get("montoARevertir"));
			legajoChequeRechazadoDto.setMontoAReenvolsar(map.get("montoAReenvolsar"));
		HSSFWorkbook workbook = exportacionDetalleLegajo.generarExcelDetalleLegajo(legajoChequeRechazadoDto);
		
		return workbook;
	}
	
	@Override
	public List<Bean> listaBusqueda(Set<Long> idValores) throws NegocioExcepcion {
		VistaSoporteLegajoChequeRechazadoFiltro vistaSoporteLegajoChequeRechazadoFiltro = new VistaSoporteLegajoChequeRechazadoFiltro();

		vistaSoporteLegajoChequeRechazadoFiltro.setNotInEstados(
			Arrays.asList(new Estado[] {
				Estado.LGJ_ANULADO
			})
		);
		vistaSoporteLegajoChequeRechazadoFiltro.setIdValores(idValores);
		return this.listarBusqueda(vistaSoporteLegajoChequeRechazadoFiltro);
	}
}