package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoShivaEnum;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRetencionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionCobroRto;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IImputacionAutomaticaValoresClientesPuroServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.servicios.ITipoMedioPagoServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoValoresClientesPuros;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IImputacionValoresClientesPurosDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoCobroDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdClientePk;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCreditoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobProcHilosCobros;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTratamientoDiferenciaSinOperacion;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteImputacionAutomaticaValoresClientesPurosFiltro;

/**
 * 
 * @author 
 *
 */
public class ImputacionAutomaticaValoresClientesPuroServicioImpl extends Servicio implements IImputacionAutomaticaValoresClientesPuroServicio {
	
	@Autowired private IImputacionValoresClientesPurosDao imputacionValoresClientesPurosDao;
	@Autowired private IParametroServicio parametroServicio;
	@Autowired private IEmpresaDao empresaDao;
	@Autowired private ISegmentoDao segmentoDao;
	@Autowired private IMotivoCobroDao motivoCobroDao;
	@Autowired private ITeamComercialServicio teamComercialServicio;
	@Autowired private ILdapServicio ldapServicio;
	@Autowired private ICobroOnlineServicio cobroOnlineServicio;
	@Autowired private ICobroImputacionServicio cobroServicio;
	@Autowired private ITipoMedioPagoDao tipoMedioPagoDao;
	@Autowired private ICobroBatchSimulacionServicio cobroBatchSimulacionServicio;
	@Autowired private ITipoMedioPagoServicio tipoMedioPagoServicio;
	@Autowired private ICobroOnlineDao cobroOnlineDao;
	@Autowired private MailServicio mailServicio;
	@Autowired private IWorkflowCobros workflowCobros;
	@Autowired private ICobroDao cobroDao;
	
	@Override
	public void generarImputacionAutomaticaValor() throws NegocioExcepcion {
		try {
			
			Traza.auditoria(getClass(), "Comienzo del metodo generarImputacionPorValor");
			
			String empresasString = parametroServicio.getValorTexto(Constantes.LISTA_EMPRESAS_BATCH_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS);
			List<EmpresaEnum> empresas = EmpresaEnum.obtenerListaDeEmpresas(empresasString);
			
			// Logica para el armado de cobros por empresa
			for (EmpresaEnum empresa : empresas) {
				
				//Armado de filtro
				List<Estado> listaEstados = new ArrayList<Estado>();
				listaEstados.add(Estado.VAL_DISPONIBLE);
				
				VistaSoporteImputacionAutomaticaValoresClientesPurosFiltro filtro = new VistaSoporteImputacionAutomaticaValoresClientesPurosFiltro(empresa,listaEstados);
				
				//Busco todos los datos necesarios para generar el cobro de aplicacion manual.				
				List<VistaSoporteResultadoValoresClientesPuros> valoresDisponiblesClientesPuros = imputacionValoresClientesPurosDao.buscarValores(filtro);
				
				List<VistaSoporteResultadoValoresClientesPuros> listaValoresClientesPuros = new ArrayList<VistaSoporteResultadoValoresClientesPuros>();
				
				Traza.auditoria(getClass(), "~");
				Traza.auditoria(getClass(), "~ Para la empresa: " + empresa + " se obtuvieron " + valoresDisponiblesClientesPuros.size() + " valores para generar cobros");
				Traza.auditoria(getClass(), "~");
				
				if (Validaciones.isCollectionNotEmpty(valoresDisponiblesClientesPuros)){
					
					for (VistaSoporteResultadoValoresClientesPuros valorDisponible: valoresDisponiblesClientesPuros){
						
						Traza.auditoria(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						Traza.auditoria(getClass(), "~ Voy a procesar el ID Valor: " + valorDisponible.getIdValor());
						Traza.auditoria(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						
						valorDisponible.setIdEmpresaCliente(empresa.name());
						listaValoresClientesPuros.add(generarCobro(valorDisponible));

						Traza.auditoria(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						Traza.auditoria(getClass(), "~ Fin de procesamiento para el ID Valor: " + valorDisponible.getIdValor());
						Traza.auditoria(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						Traza.auditoria(getClass(), " ");
					}
					
					enviarMailConDetalleDeProcesamiento(listaValoresClientesPuros, empresa);
				}			
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}
	
	/**
	 * 
	 * @param valor
	 * @return
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public VistaSoporteResultadoValoresClientesPuros generarCobro(VistaSoporteResultadoValoresClientesPuros valor) throws NegocioExcepcion {
		try {
			
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			
			ShvCobEdCobro cobro = new ShvCobEdCobro();
			
			cobro.setEmpresa(empresaDao.buscar(valor.getIdEmpresa()));
			cobro.setSegmento(segmentoDao.buscarSegmento(valor.getIdSegmentoCliente()));
			cobro.setMotivo(motivoCobroDao.buscarMotivoCobro(Constantes.MOTIVO_COBRO_SALIDA_AUTOMATICA_VALORES));
			
			String analistaTeamComercial = obtenerAnalistaTeamComercial(valor.getIdClienteLegado(),valor.getIdEmpresaCliente());
			
			if (!Validaciones.isObjectNull(analistaTeamComercial)){
				
				cobro.setIdAnalista(analistaTeamComercial);
				String nombreApellidoAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(cobro.getIdAnalista()).getNombreCompleto();
				
				if (!Validaciones.isObjectNull(nombreApellidoAnalista)){
					cobro.setNombreApellidoAnalista(nombreApellidoAnalista);
				} else {
					valor.setResultadoProcesamiento(OkNokEnum.NOK);
					valor.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.se.definio.analista.inexistente"));
				}
			} else {
				valor.setResultadoProcesamiento(OkNokEnum.NOK);
				valor.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.no.se.definio.analista.generico"));
			}
	
			cobro.setUsuarioAlta(cobro.getIdAnalista());
			cobro.setIdAnalistaTeamComercial(cobro.getIdAnalista());
			cobro.setNombreApellidoAnalistaTeamComercial(cobro.getNombreApellidoAnalista());
			
			cobro.setFechaAlta(new Date());
			cobro.setFechaUltimaModificacion(new Date());
			cobro.setMonedaOperacion(MonedaEnum.PES);
			cobro.setUsuarioUltimaModificacion(cobro.getIdAnalista());
			
			// Armar Cliente
			HashSet<ShvCobEdCliente> listaClientes = new HashSet<ShvCobEdCliente>();
			listaClientes.add(armarCliente(valor, cobro));
			cobro.setClientes(listaClientes);
			
			// Armar Credito
			HashSet<ShvCobEdCredito> listaCreditos = new HashSet<ShvCobEdCredito>();
			listaCreditos.add(armarCredito(valor, cobro));
			cobro.setCreditos(listaCreditos);
			
			//Armar Tratamiento de la Diferencia
			cobro.setTratamientoDiferencia(armarTratamientoDiferencia(valor,cobro));
			
			if (Validaciones.isObjectNull(valor.getResultadoProcesamiento())){

				//
				// Creo el cobro con su estructura "Online"
				//
				cobro = cobroOnlineServicio.crear(cobro);
				Traza.auditoria(getClass(), "Para el valor: " + valor.getIdValor() + " se creo el cobro 'En Edición': Id Cobro: " + cobro.getIdCobro() + " - Id Operacion: " + cobro.getIdOperacion());

				//
				// Simulacion Online del Cobro Generado
				//
				cobroBatchSimulacionServicio.simularCobroOnline(cobro);
				
				List<MarcaEnum> marcasSimulacionNOK = new ArrayList<MarcaEnum>();
				marcasSimulacionNOK.add(MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR);
				marcasSimulacionNOK.add(MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_ERROR);
				
				//
				// Levanto el cobro para obtener el WF actualizado
				//
				cobro = cobroOnlineDao.buscarCobro(cobro.getIdCobro());
				List<MarcaEnum> listaMarcas = cobroOnlineServicio.obtenerUltimasMarcas(cobro.getIdCobro(), cobro, true); 
				if (!listaMarcas.isEmpty()) {
					if (marcasSimulacionNOK.contains(listaMarcas.get(0))){
						
						//ShvCobEdCobro cobroModelo = cobroOnlineDao.buscarCobro(cobro.getIdCobro());
						cobro = anularCobro(cobro, usuarioBatch);
						valor.setResultadoProcesamiento(OkNokEnum.NOK);
						valor.setDescripcionError(obtenerMensajeriaDeLaTransaccion(cobro.getIdOperacion()));
					}
				}
			}
			
			if (Validaciones.isObjectNull(valor.getResultadoProcesamiento())) {
								
				// Envio a imputar el cobro a fin de que quede pendiente de proceso
				cobroOnlineServicio.imputarSinAprobacion(cobro.getIdCobro(), cobro.getIdAnalista());
				
				// Con el cobro pendiente de proceso, disparo el proceso de imputación para poder procesar el cobro.
				ShvCobProcHilosCobros cobroPendiente = new ShvCobProcHilosCobros();
				cobroPendiente.setIdCobro(cobro.getIdCobro());
				cobroPendiente.setIdOperacion(cobro.getIdOperacion());
				cobroPendiente.setCantTransacciones(Integer.valueOf(1));
				ImputacionCobroRto datosEntrada = new ImputacionCobroRto(cobroPendiente);
				
				cobroServicio.imputarCobro(datosEntrada);
				
				ShvCobEdCobro cobroModelo = cobroOnlineDao.buscarCobro(cobro.getIdCobro());
				ShvWfWorkflow workflow = cobroModelo.getWorkflow();
				ShvWfWorkflowEstado workflowEstado = workflow.getWorkflowEstado();
				Estado estado = workflowEstado.getEstado();
				
				if (Estado.COB_ERROR_COBRO.equals(estado) || Estado.COB_ERROR_APROPIACION.equals(estado)) {
					anularCobro(cobroModelo, usuarioBatch);
					valor.setResultadoProcesamiento(OkNokEnum.NOK);
					valor.setDescripcionError(obtenerMensajeriaDeLaTransaccion(cobro.getIdOperacion()));

				} else {
					valor.setResultadoProcesamiento(OkNokEnum.OK);
				}
			}
			
			return valor;
		
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion 
	 */
	private String obtenerAnalistaTeamComercial(Long idClienteLegado, String idEmpresa) throws NegocioExcepcion {
		
		String analistaTeamComercial = "";
		ClienteDto clientes = new ClienteDto();
		clientes.setIdClienteLegado(idClienteLegado.toString());
		
		HashSet<ClienteDto> listaClientes = new HashSet<ClienteDto>();
		listaClientes.add(clientes);
		
		UsuarioLdapDto usuarioTeamComercial = teamComercialServicio.obtenerAnalistaTeamComercial(listaClientes);
		if (!Validaciones.isObjectNull(usuarioTeamComercial)) {
			
			analistaTeamComercial = usuarioTeamComercial.getTuid();
			
		} else {
			
			String analistasTeamComercialGenericos = parametroServicio.getValorTexto(Constantes.LISTA_ANALISTA_BATCH_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS);
			analistaTeamComercial = obtenerAnalistaTeamComercialGenerico(analistasTeamComercialGenericos, idEmpresa);
		}
		return analistaTeamComercial;
	}
	
	/**
	 * 
	 * @param analistasTeamComercialGenericos
	 * @param idEmpresa
	 * @return
	 */
	private String obtenerAnalistaTeamComercialGenerico(String analistasTeamComercialGenericos, String idEmpresa) {
			
		String analista = "";
		HashMap<String, String> listaAnalistaTeamComercial = new HashMap<String, String>();
		String[] analistasTeamComercialString = analistasTeamComercialGenericos.split(";");
		
		for (String analistaTeamComercialString : analistasTeamComercialString) {
			String[] analistaPorEmpresa = analistaTeamComercialString.split("-");
			listaAnalistaTeamComercial.put(analistaPorEmpresa[0], analistaPorEmpresa[1]);
		}
		
		if (listaAnalistaTeamComercial.containsKey(idEmpresa)){
			analista = listaAnalistaTeamComercial.get(idEmpresa).toString();
		}
		return analista;
	}
	
	/**
	 * 
	 * @param valor
	 * @param cobro
	 * @return
	 * @throws PersistenciaExcepcion 
	 */
	private ShvCobEdCliente armarCliente(VistaSoporteResultadoValoresClientesPuros valor, ShvCobEdCobro cobro) throws PersistenciaExcepcion {
		
		ShvCobEdCliente cliente = new ShvCobEdCliente();
		ShvCobEdClientePk pk = new ShvCobEdClientePk();
		pk.setIdClienteCobro(1l);
		pk.setCobro(cobro);
		cliente.setPk(pk);
		
		cliente.setIdClienteLegado(valor.getIdClienteLegado());
		cliente.setRazonSocial(valor.getRazonSocialCliente());
		cliente.setCuit(valor.getCuitCliente());
		cliente.setIdHolding(valor.getNumHoldingCliente());
		cliente.setDescripcionHolding(valor.getDescripcionHoldingCliente());
		cliente.setIdAgenciaNegocio(valor.getIdAgenciaNegocioCliente());
		cliente.setDescripcionAgenciaNegocio(valor.getDescripcionAgenciaNegocioCliente());
		cliente.setIdClientePerfil(valor.getIdClienteLegado());
		ShvParamSegmento segmento = segmentoDao.buscarSegmento(valor.getIdSegmentoCliente());
		if(!Validaciones.isObjectNull(segmento)){
			cliente.setSegmentoAgenciaNegocio(segmento.getDescripcion());
		}
		cliente.setIdProvincia(valor.getIdProvinciaCliente());
		
		
		return cliente;
	}
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion 
	 * @throws PersistenciaExcepcion 
	 */
	private ShvCobEdCredito armarCredito(VistaSoporteResultadoValoresClientesPuros valor, ShvCobEdCobro cobro) throws NegocioExcepcion, PersistenciaExcepcion {
		
		ShvCobEdCredito credito = new ShvCobEdCredito();		
		ShvCobEdCreditoPk creditoPk = new ShvCobEdCreditoPk();
		creditoPk.setIdCredito(new Long(1));
		creditoPk.setCobro(cobro);
		credito.setPk(creditoPk);
		
		credito.setIdValor(valor.getIdValor());
		credito.setIdClienteLegado(valor.getIdClienteLegado());
		credito.setSistemaOrigen(SistemaEnum.SHIVA);
		credito.setTipoCredito(TipoCreditoEnum.getEnumByIdTipoValor(valor.getIdTipoValor().toString()));
		credito.setMoneda(MonedaEnum.PES);
		credito.setImporteMonedaOrigen(valor.getImporte());
		credito.setImporteAUtilizar(valor.getSaldoDisponible());
		credito.setSaldoMonedaOrigen(valor.getSaldoDisponible());
		credito.setFechaValor(valor.getFechaValor());
		
		if (!Validaciones.isObjectNull(valor.getFechaAlta())){
			credito.setFechaAlta(formatearFecha(valor.getFechaAlta()));
		}
		
		if (!Validaciones.isObjectNull(valor.getFechaEmision())){
			credito.setFechaEmision(valor.getFechaEmision());
		}
		
		credito.setFechaVencimiento(valor.getFechaVencimiento());
		credito.setFechaUltimoMovimiento(valor.getFechaUltimoEstado());
		credito.setFechaIngresoRecibo(valor.getFechaIngresoRecibo());
		credito.setFechaDeposito(valor.getFechaDeposito());
		credito.setFechaTransferencia(valor.getFechaTransferencia());
		credito.setProvincia(valor.getProvinciaRetencion());
		credito.setCuit(Utilidad.formatearCuit(valor.getNroCuitRetencion()));
		credito.setSubtipo(valor.getIdTipoRetencion()!=null?valor.getIdTipoRetencion().toString():null);
		credito.setReferenciaValor(valor.getReferenciaValor());
		
		if (!Validaciones.isObjectNull(valor.getIdTipoRetencion())){
			credito.setTipoRetencion(TipoRetencionEnum.getEnumByIdTipoRetencion(valor.getIdTipoRetencion()));
		}
		
		credito.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.VALIDACION_OK);
		credito.setEstadoOrigen(EstadoOrigenEnum.DISPONIBLE);
		
		if (!Validaciones.isObjectNull(valor.getIdMotivo())){
			credito.setMotivo(MotivoShivaEnum.getEnumByIdMotivo(valor.getIdMotivo().toString()));
		}
		
		String idTipoMedioPago = "";
		if (!Validaciones.isNullEmptyOrDash(credito.getSubtipo())) {
			TipoRetencionEnum tipoRetencionEnum = TipoRetencionEnum.getEnumByIdTipoRetencion(Long.valueOf(credito.getSubtipo()));
			credito.setTipoRetencion(tipoRetencionEnum);
			if (tipoRetencionEnum != null) {
				idTipoMedioPago = tipoRetencionEnum.getIdTipoMedioPago();
			}
		} else {
			idTipoMedioPago = credito.getTipoCredito().getIdTipoMedioPago();
		}
		
		Modelo tipoMedPagoModelo = tipoMedioPagoDao.buscar(idTipoMedioPago);
		credito.setTipoMedioPago(tipoMedPagoModelo != null ? (ShvParamTipoMedioPago)tipoMedPagoModelo : null);
		
		String idCreditoReferencia = valor.getIdValor() + "_" + valor.getIdTipoValor()
				+ "_" + valor.getReferenciaValor() + "_" + valor.getIdClienteLegado();
		
		credito.setIdCreditoReferencia(idCreditoReferencia);
		credito.setMonedaImporteAUtilizar(MonedaEnum.PES);
		
		return credito;

	}
	
	/**
	 * 
	 * @param fecha
	 * @return
	 * @throws NegocioExcepcion
	 */
    private Date formatearFecha(String fecha) throws NegocioExcepcion{
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaNueva = null;
		
		try {
			fechaNueva = formatoDelTexto.parse(fecha);
		} catch (ParseException e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return fechaNueva;
	}

    /**
     * 
     * @param valor
     * @param cobro
     * @return
     * @throws NegocioExcepcion
     */
    private ShvCobEdTratamientoDiferencia armarTratamientoDiferencia(VistaSoporteResultadoValoresClientesPuros valor, ShvCobEdCobro cobro) throws NegocioExcepcion {
		
		ShvCobEdTratamientoDiferencia tratamientoDiferencia = new ShvCobEdTratamientoDiferencia();
		 
		tratamientoDiferencia.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL);
		tratamientoDiferencia.setSistemaDestino(SistemaEnum.getEnumByDescripcionCorta(valor.getIdEmpresaCliente()));
		tratamientoDiferencia.setImporte(valor.getSaldoDisponible());
		tratamientoDiferencia.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(TipoMedioPagoEnum.APLICACION_MANUAL));		
		tratamientoDiferencia.setMoneda(cobro.getMonedaOperacion());
		tratamientoDiferencia.setCobro(cobro);
		
		return tratamientoDiferencia;
	}

	/**
	 * 
	 * @return
	 */
	public IImputacionValoresClientesPurosDao getImputacionValoresClientesPurosDao() {
		return imputacionValoresClientesPurosDao;
	}

	/**
	 * 
	 * @param imputacionValoresClientesPurosDao
	 */
	public void setImputacionValoresClientesPurosDao(IImputacionValoresClientesPurosDao imputacionValoresClientesPurosDao) {
		this.imputacionValoresClientesPurosDao = imputacionValoresClientesPurosDao;
	}
	
	/**
	 * 
	 * @param cobro
	 * @param workflowActualizado
	 * @throws PersistenciaExcepcion
	 * @throws NegocioExcepcion
	 */
	private ShvCobEdCobro anularCobro(ShvCobEdCobro cobro, String idUsuarioModificacion) throws PersistenciaExcepcion, NegocioExcepcion {
		
		ShvWfWorkflow workflowActualizado = null;
		
		if (Estado.COB_EN_EDICION.equals(cobro.getWorkflow().getEstado())) {
			workflowActualizado = workflowCobros.anularCobroEnEdicion(cobro.getWorkflow(), "", idUsuarioModificacion);
			
		} else if (Estado.COB_ERROR_COBRO.equals(cobro.getWorkflow().getEstado())) {
			workflowActualizado = workflowCobros.anularCobroErrorEnCobro(cobro.getWorkflow(), "", idUsuarioModificacion);
			
		} else if (Estado.COB_ERROR_APROPIACION.equals(cobro.getWorkflow().getEstado())) {
			workflowActualizado = workflowCobros.anularCobroErrorEnApropiacion(cobro.getWorkflow(), "", idUsuarioModificacion);
		}
		
		cobro.setWorkflow(workflowActualizado);
		cobro.setFechaUltimaModificacion( workflowActualizado.getFechaUltimaModificacion());
		cobro.setUsuarioUltimaModificacion(workflowActualizado.getUsuarioModificacion());
		
		return cobro;
		
	}
	
	/**
	 * 
	 * @param idOperacion
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	private String obtenerMensajeriaDeLaTransaccion(Long idOperacion) throws PersistenciaExcepcion {
		
		StringBuffer mensajeError = new StringBuffer(); 
		
		List<ShvCobTransaccionSinOperacion> listaDeTransacciones = cobroDao.buscarTransaccionPorIdOperacion(idOperacion);
		
		for (ShvCobTransaccionSinOperacion transaccion: listaDeTransacciones) {
			
			ShvCobTratamientoDiferenciaSinOperacion tratamientoDiferencia = transaccion.getTratamientoDiferencia(); 
			if (TipoMensajeEstadoEnum.ERR.equals(tratamientoDiferencia.getTipoMensajeEstado())) {
				mensajeError.append(tratamientoDiferencia.getMensajeEstado());
			} 
			
			ShvCobMedioPagoSinOperacion medioPago = transaccion.getMediosPago().iterator().next();
			if (TipoMensajeEstadoEnum.ERR.equals(medioPago.getTipoMensajeEstado())) {
				if (!Constantes.EMPTY_STRING.equals(mensajeError.toString().trim())) {
					mensajeError.append("<br>");
				}
				mensajeError.append(medioPago.getMensajeEstado());
			}
		}
		
		return mensajeError.toString();
	}
	
	/**
	 * 
	 * @param listaValoresClientesPuros
	 * @param idEmpresa
	 * @throws NegocioExcepcion
	 */
	private void enviarMailConDetalleDeProcesamiento(List<VistaSoporteResultadoValoresClientesPuros> listaValoresClientesPuros, EmpresaEnum idEmpresa) throws NegocioExcepcion{
		try{
			
			Traza.auditoria(getClass(), "~ Comienza la lógica de envío de mail con detalle de procesamiento...");
			
			// Destinatarios 'para'
			//
			
			TipoPerfilEnum perfilBusqueda = TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS;
			
			if (!EmpresaEnum.CV.equals(idEmpresa) && !EmpresaEnum.NX.equals(idEmpresa) && !EmpresaEnum.FT.equals(idEmpresa)) {
				perfilBusqueda = TipoPerfilEnum.REFERENTE_CAJA;
			}
			
			StringBuffer destinatariosPara = new StringBuffer();
			Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorPerfilEnMemoria(perfilBusqueda.descripcion());
			for (UsuarioLdapDto usuario: usuariosLdap) {
				if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
					destinatariosPara.append(usuario.getMail());
					destinatariosPara.append(";");
				}
			}
			
			// Destinatarios 'en copia'
			//
			String listaDestinatarioCorreoPorEmpresa = parametroServicio.getValorTexto(Constantes.LISTA_GRUPO_MAIL_BATCH_IMPUTACION_AUTOMATICA_VALORES_CLIENTES_PUROS);
			String destinatariosCC = obtenerGrupoDeMails(listaDestinatarioCorreoPorEmpresa, idEmpresa.toString());
			
			// Si tengo destinatarios, armo mail y lo envío
			
			if (!Validaciones.isNullOrEmpty(destinatariosPara.toString()) && !Validaciones.isNullOrEmpty(destinatariosCC)) {
				
				Mail mailDetalleProcesamiento = new Mail(destinatariosPara.toString(), 
									 destinatariosCC.replace(",", ";") + ";", 
									 generarAsunto(listaValoresClientesPuros, idEmpresa),
									 generarCuerpoMail(listaValoresClientesPuros));
				
				mailServicio.sendMail(mailDetalleProcesamiento);
				
			} else {
				Traza.auditoria(getClass(), "~ Error: no existen usuarios con perfil '" 
											+ TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS.descripcion() 
											+ "' ni destinatarios configurados para la empresa '" 
											+ idEmpresa.descripcion() 
											+ " (" + idEmpresa.name() + ")"
											+ "' para poder enviar el detalle del procesamiento.");
			}
			
			Traza.auditoria(getClass(), "~ Fin de envío de mail.");
			Traza.auditoria(getClass(), "~");
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param grupoDeMails
	 * @param idEmpresa
	 * @return
	 */
	private String obtenerGrupoDeMails(String grupoDeMails, String idEmpresa) {
			
		String grupoDeMail = "";
		HashMap<String, String> listaGrupoDeMails = new HashMap<String, String>();
		String[] grupoDeMailsString = grupoDeMails.split(";");
		
		for (String grupoDeMailString : grupoDeMailsString) {
			String[] grupoDeMailPorEmpresa = grupoDeMailString.split("-");
			listaGrupoDeMails.put(grupoDeMailPorEmpresa[0], grupoDeMailPorEmpresa[1]);
		}
		
		if (listaGrupoDeMails.containsKey(idEmpresa)){
			grupoDeMail = listaGrupoDeMails.get(idEmpresa).toString();
		}
		
		return grupoDeMail;
	}
	
	/**
	 * 
	 * @param listaValoresClientesPuros
	 * @param idEmpresa
	 * @return
	 */
	private String generarAsunto(List<VistaSoporteResultadoValoresClientesPuros> listaValoresClientesPuros, EmpresaEnum idEmpresa) {
		
		int cantidadRegistroConError = calcularCantidadDeRegistro(listaValoresClientesPuros,OkNokEnum.NOK);
		
		String asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.imputacion.automatica.valores.clientes.puros");
		asunto += " " + idEmpresa.descripcion();
		
		if (cantidadRegistroConError == 0){
			asunto += " - FINALIZADO CON EXITO";
		} else {
			asunto += " - FINALIZADO CON ERRORES";
		}
		
		return asunto;
	}
	
	/**
	 * 
	 * @param listaValoresClientesPuros
	 * @param ResultadoProcesamiento
	 * @return
	 */
	private int calcularCantidadDeRegistro(List<VistaSoporteResultadoValoresClientesPuros> listaValoresClientesPuros, OkNokEnum resultadoProcesamiento) {
		
		int cantidad=0;
		
		for (VistaSoporteResultadoValoresClientesPuros valor: listaValoresClientesPuros){
			if (valor.getResultadoProcesamiento().equals(resultadoProcesamiento)){
				cantidad++;
			}
		}
		
		return cantidad;
	}
	
	/**
	 * 
	 * @param listaValoresClientesPuros
	 * @return
	 * @throws NegocioExcepcion
	 */
	private StringBuffer generarCuerpoMail(List<VistaSoporteResultadoValoresClientesPuros> listaValoresClientesPuros) throws NegocioExcepcion {
		
		int cantidadRegistroOK = calcularCantidadDeRegistro(listaValoresClientesPuros,OkNokEnum.OK);
		int cantidadRegistroConError = calcularCantidadDeRegistro(listaValoresClientesPuros,OkNokEnum.NOK);
		String importeTotalRegistroOK = Utilidad.formatCurrency(calcularImporteTotalDeRegistro(listaValoresClientesPuros,OkNokEnum.OK),true,true);
		String importeTotalRegistroConError = Utilidad.formatCurrency(calcularImporteTotalDeRegistro(listaValoresClientesPuros,OkNokEnum.NOK),true,true);
		
		String cuerpo = "Cantidad de Valores Procesados: " + listaValoresClientesPuros.size() + "<br>";
		
		if (!(cantidadRegistroOK==0)){
			cuerpo += "Cantidad de Valores Procesados OK: " + cantidadRegistroOK + " - Importe Total: " + importeTotalRegistroOK + "<br>";
		}
		
		if (!(cantidadRegistroConError==0)){
			cuerpo += "Cantidad de Valores Procesados con Error: " + cantidadRegistroConError + " - Importe Total: " + importeTotalRegistroConError + "<br><br>";
			cuerpo += "Detalle de los registros no procesados: <br><br>" + detalleRegistrosNoProcesados(listaValoresClientesPuros);
		}
		
		return new StringBuffer(cuerpo);
	}
	
	
	/**
	 * 
	 * @param listaValoresClientesPuros
	 * @param ResultadoProcesamiento
	 * @return
	 */
	private BigDecimal calcularImporteTotalDeRegistro(List<VistaSoporteResultadoValoresClientesPuros> listaValoresClientesPuros, OkNokEnum resultadoProcesamiento) {
		
		BigDecimal importe= new BigDecimal(0);
		
		for (VistaSoporteResultadoValoresClientesPuros valor: listaValoresClientesPuros){
			if (valor.getResultadoProcesamiento().equals(resultadoProcesamiento)){
				importe = importe.add(valor.getSaldoDisponible());
			}
		}
		
		return importe;
	}
	
	/**
	 * 
	 * @param listaValoresClientesPuros
	 * @return
	 * @throws NegocioExcepcion
	 */
	private StringBuffer detalleRegistrosNoProcesados(List<VistaSoporteResultadoValoresClientesPuros> listaValoresClientesPuros) throws NegocioExcepcion {
		
		StringBuffer detalle = new StringBuffer();
		
		for (VistaSoporteResultadoValoresClientesPuros valor: listaValoresClientesPuros){
			
			if (OkNokEnum.NOK.equals(valor.getResultadoProcesamiento())){
				
				detalle.append("CUIT: " + Utilidad.formatearCuit(valor.getCuitCliente()));
				detalle.append("/Cliente: " + valor.getIdClienteLegado());
				detalle.append("/Razon Social: " + valor.getRazonSocialCliente());
				detalle.append("/Analista Team Comercial: " + obtenerAnalistaTeamComercial(valor.getIdClienteLegado(),valor.getIdEmpresaCliente()));
				detalle.append("/" + valor.getNroValor());
				detalle.append("/Importe: " + Utilidad.formatCurrency(valor.getSaldoDisponible(),true,true));
				detalle.append("/Descripcion del Error: " + valor.getDescripcionError() + "<br>");
				
			}
		}
		return detalle;
	}
}