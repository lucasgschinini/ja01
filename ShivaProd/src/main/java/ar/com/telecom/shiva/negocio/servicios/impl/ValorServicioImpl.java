package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.DatosWorkflowEnum;
import ar.com.telecom.shiva.base.enumeradores.EnviarMailBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoContabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoValorConstRecepEnum;
import ar.com.telecom.shiva.base.enumeradores.ImprimirBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoSuspensionEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenContableEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoAcuerdoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRetencionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.CampoMailExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.MailExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.GeneradorComprobantePago;
import ar.com.telecom.shiva.base.utils.GeneradorConstanciaRecepcionValores;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.ReporteMorosidadBatchRunner;
import ar.com.telecom.shiva.negocio.batch.bean.SaldoADescontarImputacionBatch;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;
import ar.com.telecom.shiva.negocio.mapeos.ValorBoletaDepositoChequeDiferidoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ValorBoletaDepositoChequeMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ValorBoletaDepositoEfectivoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ValorChequeDiferidoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ValorChequeMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ValorEfectivoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ValorInterdepositoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ValorRetencionMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ValorTransferenciaMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ValorVistaMapeador;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.IContabilidadServicio;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IReciboPreImpresoServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorConstanciaRecepcionServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorMedioPagoServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowBoletas;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowValores;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IBoletaDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivosSuspensionDao;
import ar.com.telecom.shiva.persistencia.dao.IOrganismoDao;
import ar.com.telecom.shiva.persistencia.dao.IReciboPreImpresoDao;
import ar.com.telecom.shiva.persistencia.dao.IReporteValoresPorEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.dao.ITareaDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.dao.IVistaSoporteDao;
import ar.com.telecom.shiva.persistencia.dao.IWorkflowDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoletaConValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvMigConstancia;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoChequePagoDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvValBoletaDepositoEfectivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcionValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcionValorPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorChequePagoDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorEfectivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorInterdeposito;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorPorReversion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorRetencion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorTransferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValoresVista;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstadoHist;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoSuspension;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamValorMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvBolBoletaSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaMicSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobFacturaUsuarioSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ActualizacionExitosaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ContabilidadDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TeamComercialDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValoresDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaConValorFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

public class ValorServicioImpl extends Servicio implements IValorServicio {
	
	@Autowired IVistaSoporteDao vistaSoporteDao;
	@Autowired ITareaDao tareaDao;
	@Autowired IValorDao valorDao;
	@Autowired IReporteValoresPorEmpresaDao reporteValoresPorEmpresaDao;
	@Autowired IBoletaDao boletaDao;
	@Autowired IBancoDao bancoDao;
	@Autowired IAcuerdoDao acuerdoDao;
	@Autowired IOrganismoDao organismoDao;
	@Autowired IWorkflowValores workflowValores;
	@Autowired IReciboPreImpresoDao reciboDao;
	@Autowired IReciboPreImpresoServicio reciboServicio;
	@Autowired IWorkflowBoletas workflowBoletas;
	@Autowired ValorEfectivoMapeador valorEfectivoMapeador;
	@Autowired ValorChequeMapeador valorChequeMapeador;
	@Autowired ValorChequeDiferidoMapeador valorChequeDiferidoMapeador;
	@Autowired ValorInterdepositoMapeador valorInterdepositoMapeador;
	@Autowired ValorTransferenciaMapeador valorTransferenciaMapeador;
	@Autowired MailServicio mailServicio;
	@Autowired ValorRetencionMapeador valorRetencionMapeador;
	@Autowired ValorBoletaDepositoEfectivoMapeador valorBoletaDepositoEfectivoMapeador;
	@Autowired ValorBoletaDepositoChequeMapeador valorBoletaDepositoChequeMapeador;
	@Autowired ValorBoletaDepositoChequeDiferidoMapeador valorBoletaDepositoChequeDiferidoMapeador;
	@Autowired IWorkflowDao workflowDao;
	@Autowired ILdapServicio ldapServicio;
	@Autowired IMotivosSuspensionDao motivoSuspensionDao;
	@Autowired private IParametroServicio parametroServicio;
	@Autowired IGenericoDao genericoDao;
	@Autowired IContabilidadServicio contabilidadServicio;
	@Autowired private IValorMedioPagoServicio valorMedioPagoServicio;
	@Autowired ITareaServicio tareaServicio;
	@Autowired IBoletaSinValorServicio boletaSinValorServicio;
	@Autowired ValorVistaMapeador valorVistaMapeador;
	@Autowired IVistaSoporteServicio vistaSoporteServicio;
	@Autowired MapeadorResultadoBusqueda resultadoBusquedaValorMapeador;
	@Autowired IRegistroAVCServicio registroAvcServicio;
	@Autowired private IEmpresaDao empresaDao;
	@Autowired private ISegmentoDao segmentoDao;
	@Autowired ITeamComercialServicio teamComercialServicio;
	@Autowired private ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio;
	@Autowired private ICobroDao cobroDao;
	@Autowired
	private IClienteServicio clienteServicio;
	@Autowired
	IValorConstanciaRecepcionServicio valorConstanciaRecepcionServicio;

	private static final String VALOR_PARA_BOLETA_CHEQUE = TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString();
	private static final String VALOR_PARA_BOLETA_CHEQUE_PD = TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString();
	private static final String VALOR_PARA_BOLETA_EFECTIVO = TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValorString();
	private static final String VALOR_CHEQUE = TipoValorEnum.CHEQUE.getIdTipoValorString();
	private static final String VALOR_CHEQUE_PD = TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValorString();
	private static final String VALOR_PARA_TRANSFERENCIA = TipoValorEnum.TRANSFERENCIA.getIdTipoValorString();
	private static final String VALOR_PARA_RETENCION = TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValorString();
	private static final String VALOR_PARA_INTERDEPOSITO = TipoValorEnum.INTERDEPÓSITO.getIdTipoValorString();
	private static final String VALOR_PARA_EFECTIVO = TipoValorEnum.EFECTIVO.getIdTipoValorString();
	
	public static final String ORIGEN_CAJERO_PAGADOR_ID = "1";
	public static final String ORIGEN_CLIENTE_ID = "2";
	public static final String ORIGEN_OFICINA_RECIBO_PREIMPRESO_ID = "3";
	public static final String ORIGEN_OFICINA_CONSTANCIA_AUTOMATICA_ID = "4";
	public static final String ORIGEN_REVERSION = "7";
	public static final String ORIGEN_BANCO_ID = "8";
	private static final String EXTENSION_ARCHIVO_ADJUNTO = "pdf";
	
	public static String NOMBRE_ARCHIVO_MOROSIDAD = "SHIVA.Morosidad.";
	public static String EXT_ARCH  = ".txt";
	public static String GUION_BAJO= "_"; 
	
	/**
	 * Método que crea el valor y retorna el Numero del valor que fue creado
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class,
			WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public Long crear(DTO dto) throws NegocioExcepcion {

		ValorDto valorDto = (ValorDto) dto;

		ShvValValor valor = mapFromValorDTOToValValor(valorDto);
		
		if(valorDto.isCheckEnviarMailBoleta() || valorDto.isCheckImprimirBoleta() || (valorDto.isMigracion() && valorDto.getNroConstancia() != null)){
			prepararConstancia(valorDto, valor);
		} 

		try {
			
			valor = valorDao.insertarValor(valor);
			
			//Actualizo el recibo
			actualizarEstadoRecibo(null, valor, true);
			
			// llamar contabilidad (interdeposito, boleta sin valor y registros AVC)
			if(!valor.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().equals(Estado.VAL_NO_DISPONIBLE)){
				contabilizarValor(valor, false);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		if (valorDto.isMigracion()){
			return valor.getIdValor();
		}
		Long valorRetorno =null;
		if( VALOR_PARA_BOLETA_CHEQUE.equals(String.valueOf(valor.getTipoValor().getIdTipoValor()))){
			
			valorRetorno = valor.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta();
		}else if(VALOR_PARA_BOLETA_CHEQUE_PD.equals(String.valueOf(valor.getTipoValor().getIdTipoValor()))){
			
			valorRetorno = valor.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta();
			
		}else if(VALOR_PARA_BOLETA_EFECTIVO.equals(String.valueOf(valor.getTipoValor().getIdTipoValor()))){
			valorRetorno = valor.getValorBoletaEfectivo().getBoleta().getNumeroBoleta();
		}else{
			valorRetorno=valor.getIdValor();
		}
		
		return valorRetorno;
	}

	/**
	 * Impacta en la base el ShvValValor. y si debe crear la tarea la crea. Retorna un ActualizacionExitosaDto
	 * con el valor insertado.
	 * @param valValor
	 * @param puedeCrearTareaPendiente
	 * @param user
	 * @return
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ActualizacionExitosaDto crearValValor(ShvValValor valValor, boolean puedeCrearTareaPendiente, UsuarioSesion user) throws NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		ActualizacionExitosaDto exitoDto = new ActualizacionExitosaDto();
		try {
			ShvValValor valor = valorDao.insertarValor(valValor);
			exitoDto.setValorExito(valor);
			
			if (puedeCrearTareaPendiente) {
				//Creo una tarea pendiente para el nuevo valor creado
				crearTareaPendiente(valor, user,false);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		Long valorRetorno =null;
		if( VALOR_PARA_BOLETA_CHEQUE.equals(String.valueOf(valValor.getTipoValor().getIdTipoValor()))){
			
			valorRetorno = valValor.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta();
		}else if(VALOR_PARA_BOLETA_CHEQUE_PD.equals(String.valueOf(valValor.getTipoValor().getIdTipoValor()))){
			
			valorRetorno = valValor.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta();
			
		}else if(VALOR_PARA_BOLETA_EFECTIVO.equals(String.valueOf(valValor.getTipoValor().getIdTipoValor()))){
			valorRetorno = valValor.getValorBoletaEfectivo().getBoleta().getNumeroBoleta();
		}else{
			valorRetorno=valValor.getIdValor();
		}
		exitoDto.setNumeroExito(valorRetorno);
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo crearValValor ", fechaHoraInicioNanoTime);
		
		return exitoDto;
	}

	/**
	 * Metodo generico que me permite crear tarea pendiente para el valor. Se agrega el booleano "enviarmail"
	 * para saber si al crear la tarea se debe enviar mail al dueño de la tarea.
	 * @param valor
	 * @throws NegocioExcepcion 
	 */
	private void crearTareaPendiente(ShvValValor valor, UsuarioSesion user, Boolean enviarMail) throws NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		String asuntoMail = mailServicio.armarAsuntoValor(valor);
		String cuerpoMail = mailServicio.armarLineaCuerpoValor(valor);
		if (Estado.VAL_AVISO_PAGO_PENDIENTE_CONFIRMAR.equals(valor.getWorkFlow().getEstado())) {
			TareaDto tarea = new TareaDto();
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setIdWorkflow(valor.getWorkFlow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.CONF_AVISO_PAGO);
			tarea.setFechaCreacion(valor.getWorkFlow().getFechaUltimaModificacion());
			tarea.setUsuarioCreacion(valor.getWorkFlow().getUsuarioAlta());
			tarea.setPerfilAsignacion(TipoPerfilEnum.ADMIN_VALOR.descripcion());
			tarea.setGestionaSobre(tarea.getTipoTareaGestionaPorIdTipoValor(
					valor.getTipoValor()!=null?valor.getTipoValor().getIdTipoValor():0));
			
			tarea.setAsuntoMail("- Aviso de Pago Pendiente de Confirmar - " + asuntoMail);
			
			if(!Validaciones.isNullOrEmpty(valor.getObservaciones())){
				cuerpoMail += "<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES, valor.getObservaciones());
			}
			tarea.setCuerpoMail(cuerpoMail);
			tarea.setIdValor(Long.valueOf(valor.getIdValor()));
			tarea.setEnviarMail(enviarMail);
			
			tareaServicio.crearTareaValor(tarea);
		}
		
		if (Estado.VAL_AVISO_PAGO_RECHAZADO.equals(valor.getWorkFlow().getEstado())) {
			
			TareaDto tarea = new TareaDto();
			tarea.setIdValor(Long.valueOf(valor.getIdValor()));
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setIdWorkflow(valor.getWorkFlow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.REV_AVISO_PAGO);
			tarea.setFechaCreacion(valor.getWorkFlow().getFechaUltimaModificacion());
			tarea.setUsuarioCreacion(user.getIdUsuario());
			tarea.setPerfilAsignacion(TipoPerfilEnum.ANALISTA.descripcion());
			tarea.setUsuarioAsignacion(valor.getIdAnalista());
			tarea.setGestionaSobre(tarea.getTipoTareaGestionaPorIdTipoValor(valor.getTipoValor() != null ? valor.getTipoValor().getIdTipoValor() : 0));
			tarea.setEnviarMail(enviarMail);
			tarea.setAsuntoMail(asuntoMail);
			tareaServicio.crearTareaValor(tarea);
		}
		
		if (Estado.VAL_BOLETA_PENDIENTE_AUTORIZAR.equals(valor.getWorkFlow().getEstado())) {
			//Creo la tarea y envio mail a los asignados
			TareaDto tarea = new TareaDto();
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setIdWorkflow(valor.getWorkFlow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.AUTR_VALOR);
			tarea.setFechaCreacion(valor.getWorkFlow().getFechaUltimaModificacion());
			tarea.setUsuarioCreacion(valor.getWorkFlow().getUsuarioAlta());
			tarea.setPerfilAsignacion(TipoPerfilEnum.SUPERVISOR.descripcion());
			tarea.setGestionaSobre(tarea.getTipoTareaGestionaPorIdTipoValor(
					valor.getTipoValor()!=null?valor.getTipoValor().getIdTipoValor():0));
			tarea.setIdValor(Long.valueOf(valor.getIdValor()));
			tarea.setEmpresa(valor.getEmpresa().getIdEmpresa());
			tarea.setSegmento(valor.getSegmento().getIdSegmento());
			tarea.setAsuntoMail(" - Boleta pendiente de autorizar - " + asuntoMail);
			if(!Validaciones.isNullOrEmpty(valor.getObservaciones())){
				cuerpoMail += "<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES, valor.getObservaciones());
			}
			tarea.setCuerpoMail(cuerpoMail);
			tarea.setEnviarMail(enviarMail);
			tareaServicio.crearTareaValor(tarea);
		}
				
		if (Estado.VAL_BOLETA_RECHAZADA.equals(valor.getWorkFlow().getEstado())) {
			TareaDto tarea = new TareaDto();
			tarea.setIdValor(Long.valueOf(valor.getIdValor()));
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setIdWorkflow(valor.getWorkFlow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.REV_VALOR);
			tarea.setFechaCreacion(valor.getWorkFlow().getFechaUltimaModificacion());
			tarea.setUsuarioCreacion(user.getIdUsuario());
			tarea.setPerfilAsignacion(TipoPerfilEnum.ANALISTA.descripcion());
			tarea.setUsuarioAsignacion(valor.getIdAnalista());
			tarea.setGestionaSobre(tarea.getTipoTareaGestionaPorIdTipoValor(valor.getTipoValor() != null ? valor.getTipoValor().getIdTipoValor() : 0));
			tarea.setEnviarMail(enviarMail);
			tarea.setAsuntoMail(asuntoMail);
			tareaServicio.crearTareaValor(tarea);
		}
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo crearTareaPendiente ", fechaHoraInicioNanoTime);
	}
	
	/**
	 * Mapeo la lista de DTO al valValor
	 * @param valoresDto
	 * @return
	 * @throws NegocioExcepcion
	 * @throws WorkflowExcepcion
	 */
	private List<ShvValValor> mapListFromValorDTOToValValor(List<ValorDto> valoresDto)	throws NegocioExcepcion, WorkflowExcepcion {
		
		List <ShvValValor> ValValores = new ArrayList<ShvValValor>();
		for (ValorDto valorDto : valoresDto) {
			ValValores.add(mapFromValorDTOToValValor(valorDto));
		}
		return ValValores;
	}

	/**
	 * Metodo que mapea de un valor dto a un ShvValValor y ademas crea el Workflow segun corresponda.
	 * @param valorDto
	 * @return
	 * @throws NegocioExcepcion
	 * @throws WorkflowExcepcion
	 */
	private ShvValValor mapFromValorDTOToValValor(ValorDto valorDto) throws NegocioExcepcion, WorkflowExcepcion {
		try {
			double fechaHoraInicioNanoTime = System.nanoTime();
			ShvValValor valor = (ShvValValor) defaultMapeador.map(valorDto,	null);
			
			valor = cargarDatosDeCliente(valor);
			
			valor.setEnviarMail(valorDto.isCheckEnviarMailBoleta());

			String datosOriginales;
			List<ComprobanteDto> listaComprobantes=valorDto.getListaComprobantes();
			String CampoDescripcion="";
			datosOriginales = Utilidad.datosOriginales(valorDto,getListaDatosOriginales());
			if(!Validaciones.isNullOrEmpty(String.valueOf(listaComprobantes) )){
				if(listaComprobantes.size()>0){
					datosOriginales=datosOriginales+"[Comprobantes]";
					int i=0;
					for(ComprobanteDto comprobante : listaComprobantes){				
						String paid= (i==0 )?"(Agregados: ":"|";			
						CampoDescripcion=CampoDescripcion+paid+" Nombre de Archivo: " + comprobante.getNombreArchivo() + " - Descripcion: "+comprobante.getDescripcionArchivo();
						i++;
					}
					datosOriginales+=CampoDescripcion+")";
				}
			}
			
			ShvWfWorkflow workflowAvisos = workflowValores.crearWorkflow(datosOriginales, valorDto.getUsuarioModificacion());
			
			// si el atributo isGenerarValorNoDispoblePorDefecto es true dejo el workflow en "no diponible"
			if(!valorDto.isGenerarValorNoDispoblePorDefecto()){
				if(valorDto.isGenerarValorDispoblePorDefecto()){
				
					ShvWfWorkflow wf =workflowValores.disponibilizarValor(workflowAvisos, "", valorDto.getUsuarioModificacion());
					valor.setFechaDisponible(wf.getFechaUltimaModificacion());
				}else{
					if(VALOR_PARA_BOLETA_CHEQUE.equals(valorDto.getIdTipoValor()) ||VALOR_PARA_BOLETA_CHEQUE_PD.equals(valorDto.getIdTipoValor())   || VALOR_PARA_BOLETA_EFECTIVO.equals(valorDto.getIdTipoValor()) ){
						
						//U562163 - Fusion - paquete 01 - se elimina la autorizacion dentro del alta de valores.
//						if((VALOR_PARA_BOLETA_CHEQUE_PD.equals(valorDto.getIdTipoValor()) || ORIGEN_OFICINA_RECIBO_PREIMPRESO_ID.equals(valorDto.getIdOrigen()) || ORIGEN_OFICINA_CONSTANCIA_AUTOMATICA_ID.equals(valorDto.getIdOrigen())) && !valorDto.isMigracion() ){									
//							
//							workflowValores.solicitarAutorizacionBoleta(workflowAvisos, "", valorDto.getUsuarioModificacion());
//							
//						}else{
							
							//distinto de cheque or origen=cliente						
							workflowValores.habilitarBoletaParaConciliar(workflowAvisos, "", valorDto.getUsuarioModificacion());
//						}
					}else{					
						workflowValores.solicitarConfirmacionAvisoDePago(workflowAvisos, "", valorDto.getUsuarioModificacion());
					}
				}
			}
			Date fechaValor = null;
			
			Date fechaAlta = new Date();
			Date fechaDeposito = null;
			Date fechaVencimiento = null;
			Date fechaRecibo = null; 
			Date fechaTransferencia = null;
//			Date fechaEmisionCheque = null;
			Date fechaNotificacionDisponibilidadRetiroValor = null;
			
			if (!Validaciones.isNullOrEmpty(valorDto.getFechaNotificacionDisponibilidadRetiroValor())){
				fechaNotificacionDisponibilidadRetiroValor = Utilidad.parseDatePickerString(valorDto.getFechaNotificacionDisponibilidadRetiroValor());
			}
			valor.setFechaNotificacionDisponibilidadRetiroValor(fechaNotificacionDisponibilidadRetiroValor);
			
			switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorDto.getIdTipoValor()))) {
				case EFECTIVO:
					ShvValValorEfectivo efectivo = (ShvValValorEfectivo) valorEfectivoMapeador.map(valorDto, null);
					if(!Validaciones.isObjectNull(efectivo.getNumeroBoleta())) {
						valor.setReferenciaValor(efectivo.getNumeroBoleta().toString());
					}
					if (!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())) {
						try {
							fechaDeposito = Utilidad.parseDatePickerString(valorDto.getFechaDeposito());
							fechaRecibo = Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo());
						} catch (java.text.ParseException e) {
							Traza.error(getClass(), "Error al parsear", e);
						}
						
						fechaValor = calcularFechaValor(TipoValorEnum.EFECTIVO, null,
								fechaAlta, fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia, fechaNotificacionDisponibilidadRetiroValor, null);
						valor.setFechaValor(fechaValor);
					}
					efectivo.setValor(valor);
					valor.setValValorEfectivo(efectivo);
	
					break;
				case CHEQUE:
					ShvValValorCheque valorCheque = (ShvValValorCheque) valorChequeMapeador.map(valorDto, null);
					
					valor.setReferenciaValor(valorCheque.getNumeroCheque().toString());
					if (!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())) {
						try {
							fechaDeposito = Utilidad.parseDatePickerString(valorDto.getFechaDeposito());
							fechaRecibo = Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo());
							//fechaEmisionCheque = Utilidad.parseDatePickerString(valorDto.getFechaEmision());
						} catch (java.text.ParseException e) {
							
							Traza.error(getClass(), "Error al parsear", e);
						}
						fechaValor = calcularFechaValor(TipoValorEnum.CHEQUE, null,  
								fechaAlta, fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia ,fechaNotificacionDisponibilidadRetiroValor, null);
						valor.setFechaValor(fechaValor);
						
					}
					//valorCheque.setFechaEmision(fechaEmisionCheque);
					valorCheque.setValor(valor);
					
					valor.setValValorCheque(valorCheque);
	
					break;
				case CHEQUE_DIFERIDO:
					ShvValValorChequePagoDiferido valorChequeDiferidoModelo = (ShvValValorChequePagoDiferido) valorChequeDiferidoMapeador
							.map(valorDto, null);
					
					valor.setReferenciaValor(valorChequeDiferidoModelo.getNumeroCheque().toString());
					
					if (!Validaciones.isNullOrEmpty(valorDto.getFechaVencimiento()) && !Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())) {
						try {
							fechaVencimiento = Utilidad.parseDatePickerString(valorDto.getFechaVencimiento());
							fechaDeposito = Utilidad.parseDatePickerString(valorDto.getFechaDeposito());
							fechaRecibo = Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo());
							//fechaEmisionCheque = Utilidad.parseDatePickerString(valorDto.getFechaEmision());
						} catch (java.text.ParseException e) {
							Traza.error(getClass(), "Error al parsear", e);
						}
						fechaValor = calcularFechaValor(TipoValorEnum.CHEQUE_DIFERIDO, null,  
								fechaAlta, fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia, fechaNotificacionDisponibilidadRetiroValor, null);
						valor.setFechaValor(fechaValor);
					}
					
					//valorChequeDiferidoModelo.setFechaEmision(fechaEmisionCheque);
					valorChequeDiferidoModelo.setValor(valor);
					valor.setValValorChequePD(valorChequeDiferidoModelo);
	
					break;
				case RETENCIÓN_IMPUESTO:
					ShvValValorRetencion retencion = (ShvValValorRetencion) valorRetencionMapeador
							.map(valorDto, null);
					
					valor.setReferenciaValor(retencion.getNroConstanciaRetencion());
					if (!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())) {
						try {
							fechaRecibo = Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo());
						} catch (java.text.ParseException e) {
							Traza.error(getClass(), "Error al parsear", e);
						}
						
						fechaValor = calcularFechaValor(TipoValorEnum.RETENCIÓN_IMPUESTO, null,  
								fechaAlta, fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia, fechaNotificacionDisponibilidadRetiroValor, null);
						valor.setFechaValor(fechaValor);
					}
					
					retencion.setValor(valor);
					valor.setValValorRetencion(retencion);
	
					break;
				case TRANSFERENCIA:
					ShvValValorTransferencia transferencia = (ShvValValorTransferencia) valorTransferenciaMapeador
							.map(valorDto, null);
					
					valor.setReferenciaValor(transferencia.getNumeroReferencia().toString());
					if (!Validaciones.isNullOrEmpty(valorDto.getFechaTransferencia())) {
						try {
							fechaTransferencia = Utilidad.parseDatePickerString(valorDto.getFechaTransferencia());
						} catch (java.text.ParseException e) {
							Traza.error(getClass(), "Error al parsear", e);
						}
						
						fechaValor = calcularFechaValor(TipoValorEnum.TRANSFERENCIA, null,  
								fechaAlta, fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia, fechaNotificacionDisponibilidadRetiroValor, null);
						valor.setFechaValor(fechaValor);
					}
					
					transferencia.setValor(valor);
					valor.setValValorTransferencia(transferencia);
	
					break;
				case BOLETA_DEPOSITO_EFECTIVO:
					ShvBolBoleta boletaEfectivo=  altaBoleta(valorDto, datosOriginales);
					ShvValBoletaDepositoEfectivo valorBolDepEfectivoModelo = (ShvValBoletaDepositoEfectivo) valorBoletaDepositoEfectivoMapeador.map(valorDto, null);
					try {
						if(!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())) {
							fechaDeposito = Utilidad.parseDatePickerString(valorDto.getFechaDeposito());
						}
						if(!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())) {
							fechaRecibo = Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo());
						}
					} catch (java.text.ParseException e) {
						Traza.error(getClass(), "Error al parsear", e);
					}
					fechaValor = calcularFechaValor(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO, new Integer(valorDto.getIdOrigen()),  
							fechaAlta, fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia, fechaNotificacionDisponibilidadRetiroValor, null);
					valor.setFechaValor(fechaValor);
					
					boletaEfectivo.setEmailObservaciones(valorDto.getObservaciones());
					valorBolDepEfectivoModelo.setBoleta(boletaEfectivo);
					valorBolDepEfectivoModelo.setValor(valor);
					valor.setValorBoletaEfectivo(valorBolDepEfectivoModelo);
					valor.setReferenciaValor(valorBolDepEfectivoModelo.getBoleta().getNumeroBoleta().toString());
									
					break;
				case BOLETA_DEPOSITO_CHEQUE:
					ShvBolBoleta boletaCheque=  altaBoleta(valorDto, datosOriginales);
					ShvValBoletaDepositoCheque valorBolDepChequeModelo = (ShvValBoletaDepositoCheque) valorBoletaDepositoChequeMapeador
							.map(valorDto, null);
					Date fechaEmision = null;
					valor.setReferenciaValor(valorBolDepChequeModelo.getNumeroCheque().toString());
					
					try {
						if(!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())) {
							fechaDeposito = Utilidad.parseDatePickerString(valorDto.getFechaDeposito());
						}
						if(!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())) {
							fechaRecibo = Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo());
						}
						if(!Validaciones.isNullOrEmpty(valorDto.getFechaEmision())) {
							fechaEmision = Utilidad.parseDatePickerString(valorDto.getFechaEmision());
						}
					} catch (java.text.ParseException e) {
						Traza.error(getClass(), "Error al parsear", e);
					}
					fechaValor = calcularFechaValor(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE, new Integer(valorDto.getIdOrigen()),  
							fechaAlta, fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia ,fechaNotificacionDisponibilidadRetiroValor, fechaEmision);
					valor.setFechaValor(fechaValor);
					
					boletaCheque.setEmailObservaciones(valorDto.getObservaciones());
					valorBolDepChequeModelo.setBoleta(boletaCheque);
					valorBolDepChequeModelo.setValor(valor);
					valor.setValorBoletaDepositoCheque(valorBolDepChequeModelo);
					break;
				case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
					ShvBolBoleta boletaChequeDiferido=  altaBoleta(valorDto, datosOriginales);
					ShvValBoletaDepositoChequePagoDiferido valorBolDepChequeDiferidoModelo = (ShvValBoletaDepositoChequePagoDiferido) valorBoletaDepositoChequeDiferidoMapeador
							.map(valorDto, null);
	
					valor.setReferenciaValor(valorBolDepChequeDiferidoModelo.getNumeroCheque().toString());
					
					try {
						if(!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())) {
							fechaDeposito = Utilidad.parseDatePickerString(valorDto.getFechaDeposito());
						}
						if(!Validaciones.isNullOrEmpty(valorDto.getFechaVencimiento())) {
							fechaVencimiento = Utilidad.parseDatePickerString(valorDto.getFechaVencimiento()); 
						}
						if(!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())) {
							fechaRecibo = Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo());
						}
					} catch (java.text.ParseException e) {
						Traza.error(getClass(), "Error al parsear", e);
					}
					fechaValor = calcularFechaValor(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO, new Integer(valorDto.getIdOrigen()),  
							fechaAlta, fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia, fechaNotificacionDisponibilidadRetiroValor, null);
					valor.setFechaValor(fechaValor);
					
					boletaChequeDiferido.setEmailObservaciones(valorDto.getObservaciones());
					valorBolDepChequeDiferidoModelo.setBoleta(boletaChequeDiferido);
					valorBolDepChequeDiferidoModelo.setValor(valor);
					valor.setValorBoletaDepositoChequePD(valorBolDepChequeDiferidoModelo);
					break;
				case INTERDEPÓSITO:
					ShvValValorInterdeposito interdeposito =  (ShvValValorInterdeposito) valorInterdepositoMapeador
							.map(valorDto, null);
					
					interdeposito.setFechaInterdeposito(Utilidad.parseDatePickerString(valorDto.getFechaInterdeposito()));
					interdeposito.setNumeroInterdeposito(Long.valueOf(valorDto.getNumeroInterdepositoCdif()));
					interdeposito.setCodigoOrganismo(valorDto.getCodOrganismo());
					
					valor.setReferenciaValor(interdeposito.getNumeroInterdeposito().toString());
					if(!Validaciones.isNullOrEmpty(valorDto.getFechaInterdeposito())){					
						try {
							fechaDeposito = Utilidad.parseDatePickerString(valorDto.getFechaInterdeposito());
						} catch (java.text.ParseException e) {
							Traza.error(getClass(), "Error al parsear", e);
						}
						
						fechaValor = calcularFechaValor(TipoValorEnum.INTERDEPÓSITO, null,
								fechaAlta, fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia, fechaNotificacionDisponibilidadRetiroValor, null);
						valor.setFechaValor(fechaValor);
					}
					
					interdeposito.setValor(valor);
					valor.setValValorInterdeposito(interdeposito);
					break;
				default:
					throw new NegocioExcepcion("No se encontro el tipo de Valor al mapear");
			}
			
			/**
			 * @author u573005, sprint 3, se corrige para reutilizar codigo
			 * Se setea el numero valor con el metodo correspondiente
			 */
			valor = setearNumeroValor(valor, valor);
			
			valor.setWorkFlow(workflowAvisos);

			if (!Validaciones.isObjectNull(valorDto.getFechaAlta())) {
				valor.setFechaAlta(valorDto.getFechaAlta());
			} else {
				valor.setFechaAlta(workflowAvisos.getFechaUltimaModificacion());
			}
			
			valor.setFechaUltimoEstado(valor.getFechaAlta());

			Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo mapFromValorDTOToValValor ", fechaHoraInicioNanoTime);
			return valor;

		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (ParseException e1) {
			throw new NegocioExcepcion(e1.getMessage(), e1);
		}
	}

	
	private ShvValValor cargarDatosDeCliente(ShvValValor valor) throws NegocioExcepcion {
		
		try {
			ClienteBean clienteBean = clienteServicio.consultarCliente(valor.getIdClienteLegado().toString());
			
			//CUIT
			if (!Validaciones.isNullOrEmpty(clienteBean.getCuit())){
				valor.setCuit(new Long(clienteBean.getCuit()));
			}
			//RAZÓN SOCIAL
			if (!Validaciones.isNullOrEmpty(clienteBean.getRazonSocial())){
				valor.setRazonSocial(clienteBean.getRazonSocial());
			}
			
			//ID HOLDING
			if (!Validaciones.isNullOrEmpty(clienteBean.getCodigoHolding())){
				valor.setIdHolding(new Long(clienteBean.getCodigoHolding()));
			}
			
			//DESCRIPCION_HOLDING 
			if (!Validaciones.isNullOrEmpty(clienteBean.getDescripcionHolding())){
				valor.setDescripcionHolding(clienteBean.getDescripcionHolding());
			}
			
			//ID_AGENCIA_NEGOCIO        
			if (!Validaciones.isNullOrEmpty(clienteBean.getAgenciaNegocio())){
				valor.setIdAgenciaNegocio(clienteBean.getAgenciaNegocio());
			}
			
			//DESCRIPCION_AGENCIA_NEGOCIO
			if (!Validaciones.isNullOrEmpty(clienteBean.getDescripcionAgenciaNegocio())){
				valor.setDescripcionAgenciaNegocio(clienteBean.getDescripcionAgenciaNegocio());
			}
			
			//SEGMENTO_AGENCIA_NEGOCIO     
			if (!Validaciones.isNullOrEmpty(clienteBean.getSegmentoAgencia())){
				valor.setSegmentoAgenciaNegocio(clienteBean.getSegmentoAgencia());
			}
			
			//ID_CLIENTE_PERFIL (esto es el perfil Siebel)
			if (!Validaciones.isNullOrEmpty(clienteBean.getIdClientePerfil())){
				valor.setIdClientePerfil(new Long(clienteBean.getIdClientePerfil()));
			}
			//RAZON_SOCIAL_CLIENTE_PERFIL
			if(!Validaciones.isNullOrEmpty(clienteBean.getRazonSocial())){
				valor.setRazonSocialClientePerfil(clienteBean.getRazonSocial());
			}
			
			if(!Validaciones.isNullOrEmpty(clienteBean.getOrigen())){
				valor.setOrigen(ClienteOrigenEnum.getEnumByCodigo(clienteBean.getOrigen()));
			}
			
			if(!Validaciones.isObjectNull(clienteBean.getPermiteUsoCV())){
				valor.setPermiteUsoCV(clienteBean.getPermiteUsoCV());
			}
			
			if(!Validaciones.isObjectNull(clienteBean.getPermiteUsoFT())){
				valor.setPermiteUsoFT(clienteBean.getPermiteUsoFT());
			}
			
			if(!Validaciones.isObjectNull(clienteBean.getPermiteUsoNX())){
				valor.setPermiteUsoNX(clienteBean.getPermiteUsoNX());
			}
			
			if(!Validaciones.isObjectNull(clienteBean.getPermiteUsoTP())){
				valor.setPermiteUsoTP(clienteBean.getPermiteUsoTP());
			}
			
			if(!Validaciones.isObjectNull(clienteBean.getPermiteUsoTA())){
				valor.setPermiteUsoTA(clienteBean.getPermiteUsoTA());
			}
			
			
			
			
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return valor;
	}

	/**
	 * 
	 * @param valorDto
	 */
	private void enviMailAvisoPago(ValorDto valorDto) {
		if (!Validaciones.isNullOrEmpty(valorDto.getUsuarioModificacion())) {
			StringBuffer cuerpo = new StringBuffer();
			cuerpo.append("Se ha generado una nueva tarea.");
			
			Mail mail = new Mail(valorDto.getUsuarioModificacion(), "Nueva tarea ", cuerpo);						
			mail.setDestinatarioCC(valorDto.getCopropietario());
			
			mailServicio.sendMail(mail);
		} else {
			Traza.advertencia(getClass(), "Metodo enviMailAvisoPago: Como el usuario Modificacion se encuentra vacio, no se enviara el correo");
		}
	}


	/**
	 * 
	 * @param valorDto
	 * @param datosOriginales
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvBolBoleta altaBoleta(ValorDto valorDto, String datosOriginales ) throws NegocioExcepcion {
		
		ShvWfWorkflow workflowBoleta;
		try {
			workflowBoleta = workflowBoletas.crearWorkflow(datosOriginales, valorDto.getUsuarioModificacion());
			ShvBolBoleta boleta = new ShvBolBoleta();
			boleta.setWorkFlow(workflowBoleta);
			boleta.setFechaAlta(workflowBoleta.getFechaAlta());
			boleta.setNumeroBoleta(boletaDao.proximoValorNumeroBoleta());
			valorDto.setNumeroBoleta(String.valueOf(boleta.getNumeroBoleta()));

			ShvBolBoletaConValor boletaConValor = new ShvBolBoletaConValor();

			boleta.setBoletaConValor(boletaConValor);
			boletaConValor.setBoleta(boleta);
			if (valorDto.isCheckEnviarMailBoleta()) {
				boleta.setImpresionEstado(valorDto.getBoletaImpresaEstado());
				boleta.setEmail(valorDto.getEmail());
				boleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.SI);
				boleta.setEmailEnvioUsuario(valorDto.isCheckEnviarMailBoleta() ? valorDto
						.getUsuarioModificacion() : "");
				boleta.setEmailEnvioObservaciones(valorDto.getObservacionMail());
				boleta.setEmailEnvioFecha(new Date());

			} else {
				boleta.setEmailEnvioEstado(valorDto
						.getBoletaEnviadaMailEstado());
				boleta.setImpresionEstado(ImprimirBoletaEstadoEnum.SI);
				boleta.setImpresionUsuario(valorDto.isCheckImprimirBoleta() ? valorDto
						.getUsuarioModificacion() : "");
				boleta.setImpresionFecha(new Date());
			}
			return boleta;
		} catch (WorkflowExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * @param valorDto
	 * @param valor
	 */
	private void prepararConstancia(ValorDto valorDto, ShvValValor valor) {
		
		ShvValConstanciaRecepcion constancia = new ShvValConstanciaRecepcion();
		
		constancia.setEstado(EstadoValorConstRecepEnum.ASIGNADA);
		constancia.setFechaAnulacion(null);
		constancia.setFechaCreacion(new Date());
		constancia.setIdConstanciaRecepcion(null);
		List<ShvValValor> listaValor = new ArrayList<ShvValValor>();
		listaValor.add(valor);
		constancia.setUsuarioAnulacion(null);
		constancia.setUsuaroCreacion(valorDto.getIdAnalista());
		
		ShvValConstanciaRecepcionValorPk constanciaRecepcionPk = new ShvValConstanciaRecepcionValorPk();
		constanciaRecepcionPk.setValor(valor);
		constanciaRecepcionPk.setConstanciaRecepcion(constancia);
		ShvValConstanciaRecepcionValor constanciaRecepcion = new ShvValConstanciaRecepcionValor();
		constanciaRecepcion.setShvValConstanciaRecepcionValorPk(constanciaRecepcionPk);
	}
	
	/**
	 * 
	 */
	public void migrarConstancia(Long id, String nroConstancia) throws NegocioExcepcion{
		try {
			ShvValValor valorModelo;
			valorModelo = valorDao.buscarValor(id.toString());
			ShvMigConstancia constanciaMigracion = new ShvMigConstancia();
			ShvValConstanciaRecepcionValor constancia = valorConstanciaRecepcionServicio.buscarConstanciaRecepcionAsignadaAValor(valorModelo.getIdValor());
			constanciaMigracion.setIdConstanciaRecepcion(Long.valueOf(constancia.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion().getIdConstanciaRecepcion()));
			constanciaMigracion.setIdConstanciaMigracion(Long.valueOf(nroConstancia));
			genericoDao.insertar(ShvMigConstancia.class,constanciaMigracion);
		} catch (PersistenciaExcepcion | IllegalArgumentException /*| IllegalAccessException*/ e) {
			throw new NegocioExcepcion(e);
		}
	}
	/**
	 * Metodo que genera la constancia, la asocia a todos los valores y la inserta en la base.
	 * @param ValorDto
	 * @param valor
	 */
	private ShvValConstanciaRecepcion prepararConstancia(List<ShvValValor> valoresList) throws NegocioExcepcion{
		ShvValConstanciaRecepcion constancia = new ShvValConstanciaRecepcion();
		List<ShvValValor> listaValor = new ArrayList<ShvValValor>();
		for (ShvValValor shvValValor : valoresList) {
			listaValor.add(shvValValor);
		}
		constancia.setUsuaroCreacion(valoresList.get(0).getIdAnalista());
		constancia.setEstado(EstadoValorConstRecepEnum.ASIGNADA);
		constancia.setFechaCreacion(new Date());
		/**
		 * @author u562163, IM de prod. 
		 */	
		List<ShvValConstanciaRecepcion> valorConstanciaRecepcion = new ArrayList<ShvValConstanciaRecepcion>();
		constancia = valorConstanciaRecepcionServicio.actualizarConstanciaRecepcion(constancia);
		valorConstanciaRecepcion.add(constancia);
		for (ShvValValor shvValValor : valoresList) {
			ShvValConstanciaRecepcionValorPk constanciaRecepcionPk = new ShvValConstanciaRecepcionValorPk();
			constanciaRecepcionPk.setValor(shvValValor);
			constanciaRecepcionPk.setConstanciaRecepcion(constancia);
			ShvValConstanciaRecepcionValor constanciaRecepcion = new ShvValConstanciaRecepcionValor();
			constanciaRecepcion.setShvValConstanciaRecepcionValorPk(constanciaRecepcionPk);
		}
		return constancia;
	}
	
	/**
	 * Arma ShvValConstanciaRecepcion y ShvValConstanciaRecepcionValor, ambos los guarda en la base.
	 * @param shvValValor
	 * @author u562163, IM de prod.
	 * @return
	 * @throws NegocioExcepcion
	 */
	private ShvValConstanciaRecepcionValor prepararConstancia(ShvValValor shvValValor) throws NegocioExcepcion{
		// Armo ShvValConstanciaRecepcion
		ShvValConstanciaRecepcion constanciaRecepcion = new ShvValConstanciaRecepcion();
		constanciaRecepcion.setEstado(EstadoValorConstRecepEnum.ASIGNADA);
		constanciaRecepcion.setFechaCreacion(new Date());
		constanciaRecepcion.setUsuaroCreacion(shvValValor.getIdAnalista());
		constanciaRecepcion = valorConstanciaRecepcionServicio.actualizarConstanciaRecepcion(constanciaRecepcion);
		
		
		// Armo ShvValConstanciaRecepcionValor
		ShvValConstanciaRecepcionValorPk constanciaRecepcionValorPk = new ShvValConstanciaRecepcionValorPk();
		constanciaRecepcionValorPk.setValor(shvValValor);
		constanciaRecepcionValorPk.setConstanciaRecepcion(constanciaRecepcion);
		ShvValConstanciaRecepcionValor constanciaRecepcionValor = new ShvValConstanciaRecepcionValor();
		constanciaRecepcionValor.setShvValConstanciaRecepcionValorPk(constanciaRecepcionValorPk);
		constanciaRecepcionValor = valorConstanciaRecepcionServicio.actualizarConstanciaRecepcionValor(constanciaRecepcionValor);
		
		return constanciaRecepcionValor;
	}

	/**
	 * Me permite anular las constancias asociadas a un valor
	 * @param shvValValor
	 * @param usuario
	 * @throws PersistenciaExcepcion
	 */
	private void anularConstancia(ShvValValor shvValValor, String usuario) throws NegocioExcepcion {
		ShvValConstanciaRecepcionValor constanciaValor = valorConstanciaRecepcionServicio.buscarConstanciaRecepcionAsignadaAValor(shvValValor.getIdValor());
		
		if(!Validaciones.isObjectNull(constanciaValor)){
			ShvValConstanciaRecepcion constancia = constanciaValor.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion();
			Date fechaConstancia = new Date();
			
			ShvValConstanciaRecepcion nuevaConstancia = new ShvValConstanciaRecepcion();
			nuevaConstancia.setEstado(EstadoValorConstRecepEnum.ASIGNADA);
			nuevaConstancia.setFechaCreacion(fechaConstancia);
			nuevaConstancia.setFechaAnulacion(null);
			nuevaConstancia.setUsuaroCreacion(usuario);
			//Actualizo en este momento para que no tire un error de persistencia
			nuevaConstancia = valorConstanciaRecepcionServicio.actualizarConstanciaRecepcion(nuevaConstancia);
		
			// le saco la primer constancia porque siempre va a tener una sola constancia
			constancia.setEstado(EstadoValorConstRecepEnum.ANULADA);
			constancia.setFechaAnulacion(fechaConstancia);
			constancia.setUsuarioAnulacion(usuario);
			
			// Asigno la nueva constancia como padre de la vieja
			nuevaConstancia.setIdConstanciaRecepcionPadre(constancia);
			
			// Por cada constancia que anulo recorro los otros valores que tenia asignados y les asigno la nueva constancia
			List<ShvValValor> listaValores = valorConstanciaRecepcionServicio.buscarValoresAsociadosAConstancia(constancia.getIdConstanciaRecepcion());
			listaValores = eliminarValor(listaValores, shvValValor.getIdValor());
			
			nuevaConstancia = valorConstanciaRecepcionServicio.actualizarConstanciaRecepcion(nuevaConstancia);
			for (ShvValValor valor : listaValores) {
				// Buscar la constanciaRecepcionValor
				valorConstanciaRecepcionServicio.actualizarRelacionConstanciaValor(valor,nuevaConstancia);
			}
		}
	}

	/**
	 * Metodo que arma una lista nueva exceptuando el valor a eliminar
	 * @param listaValores
	 * @param valorAEliminar
	 * @return
	 */
	private List<ShvValValor> eliminarValor(List<ShvValValor> listaValores, Long valorAEliminar){
		
		List<ShvValValor> listaValoresADevolver = new ArrayList<ShvValValor>();
		
		for (ShvValValor shvValValor : listaValores) {
			if(!shvValValor.getIdValor().equals(valorAEliminar)){
				listaValoresADevolver.add(shvValValor);	
			}
		}
		return listaValoresADevolver;
	}
	
	public String armarMensajeDuplicado(ValorDto valorDtoDuplicado) throws PersistenciaExcepcion {
		String mensajeDuplicado = "";
		if (valorDtoDuplicado.getIdBancoOrigen() != null) {
			ShvParamBanco bancoOrigen = bancoDao.buscarBanco(valorDtoDuplicado.getIdBancoOrigen());
			mensajeDuplicado = "Nro. Cheque " + valorDtoDuplicado.getNumeroCheque()	+ " para Banco Origen " + bancoOrigen.getDescripcion() + " con Importe " + valorDtoDuplicado.getImporte() + "";
			
			if (Validaciones.isNullOrEmpty(valorDtoDuplicado.getFechaVencimiento()) && !Validaciones.isNullOrEmpty(valorDtoDuplicado.getFechaDeposito())) {
				mensajeDuplicado += " y ";
			}
			
			if (!Validaciones.isNullOrEmpty(valorDtoDuplicado.getFechaDeposito())) {
				mensajeDuplicado += " Fecha Depósito " + valorDtoDuplicado.getFechaDeposito() + "";
			}
			
			if (!Validaciones.isNullOrEmpty(valorDtoDuplicado.getFechaVencimiento())) {
				mensajeDuplicado += " y Fecha Vencimiento " + valorDtoDuplicado.getFechaVencimiento() + "";
			}
		} else if(valorDtoDuplicado.getIdTipoImpuesto() != null) {
			mensajeDuplicado = "Nro. Constancia " + valorDtoDuplicado.getNroConstancia() + " para Tipo de Retención " + valorDtoDuplicado.getTipoImpuesto()	+ " para Cliente Legado " + valorDtoDuplicado.getCodCliente();
		} else if(valorDtoDuplicado.getNumeroReferencia() != null) {
			mensajeDuplicado = "Nro. Referencia " + valorDtoDuplicado.getNumeroReferencia()	+ " para Fecha de Transferencia " + valorDtoDuplicado.getFechaTransferencia() + " con Importe " + valorDtoDuplicado.getImporte() + "";
		} else if (valorDtoDuplicado.getNumeroInterdepositoCdif() != null){
			mensajeDuplicado = "Nro. Interdepósito " + valorDtoDuplicado.getNumeroInterdepositoCdif() + " para Fecha de Interdepósito " + valorDtoDuplicado.getFechaInterdeposito() + " con Código Cliente " + valorDtoDuplicado.getCodCliente();
		}
		return mensajeDuplicado;
	}
	
	public String armarMensajeDuplicadoZE(VistaSoporteResultadoBusquedaValor vistaSoporteResultadoBusquedaValor) throws PersistenciaExcepcion {
		String mensajeDuplicado = "";
		if (TipoValorEnum.CHEQUE.getDescripcion().equals(vistaSoporteResultadoBusquedaValor.getTipoValor()) || 
			TipoValorEnum.CHEQUE_DIFERIDO.getDescripcion().equals(vistaSoporteResultadoBusquedaValor.getTipoValor())) {
			ShvParamBanco bancoOrigen = bancoDao.buscarBanco(vistaSoporteResultadoBusquedaValor.getIdBancoOrigen());
			mensajeDuplicado += "Nro. Cheque " + vistaSoporteResultadoBusquedaValor.getReferenciaValor()	+ " para Banco Origen " + bancoOrigen.getDescripcion() + " con Importe " + vistaSoporteResultadoBusquedaValor.getImporte() + "";
			
			if (Validaciones.isObjectNull(vistaSoporteResultadoBusquedaValor.getFechaVencimiento()) && !Validaciones.isObjectNull(vistaSoporteResultadoBusquedaValor.getFechaDeposito())) {
				mensajeDuplicado += " y ";
			}
			
			if (!Validaciones.isObjectNull(vistaSoporteResultadoBusquedaValor.getFechaDeposito())) {
				mensajeDuplicado += " Fecha Depósito " + Utilidad.formatDatePicker(vistaSoporteResultadoBusquedaValor.getFechaDeposito()) + "";
			}
			
			if (!Validaciones.isObjectNull(vistaSoporteResultadoBusquedaValor.getFechaVencimiento())) {
				mensajeDuplicado += " y Fecha Vencimiento " + Utilidad.formatDatePicker(vistaSoporteResultadoBusquedaValor.getFechaVencimiento()) + "";
			}
		} else if(TipoValorEnum.TRANSFERENCIA.getDescripcion().equals(vistaSoporteResultadoBusquedaValor.getTipoValor())) {
			mensajeDuplicado += "Nro. Referencia " + vistaSoporteResultadoBusquedaValor.getReferenciaValor() + " para Fecha de Transferencia " + Utilidad.formatDatePicker(vistaSoporteResultadoBusquedaValor.getFechaTransferencia()) + " con Importe " + vistaSoporteResultadoBusquedaValor.getImporte() + "";
		} else if(TipoValorEnum.EFECTIVO.getDescripcion().equals(vistaSoporteResultadoBusquedaValor.getTipoValor())) {
			mensajeDuplicado += "Nro. Boleta " + vistaSoporteResultadoBusquedaValor.getNroBoleta()	+ " para Fecha Depósito " + Utilidad.formatDatePicker(vistaSoporteResultadoBusquedaValor.getFechaDeposito()) + " con Importe " + vistaSoporteResultadoBusquedaValor.getImporte() + "";
		}
		mensajeDuplicado  += " el cual contiene el ZE " + vistaSoporteResultadoBusquedaValor.getNumeroDocumentoContable() + " ya utilizado";
				
		return mensajeDuplicado;
	}

	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public List<String> autorizarBoleta(UsuarioSesion userSesion, String valoresSelected, String observaciones) throws NegocioExcepcion {
		List<String> mensajesMostrarResultados = new ArrayList<String>();
		
		try {
			Date date = new Date();
			String observacionesWf = (!Validaciones.isNullOrEmpty(observaciones)) ? 
					 "[" + Constantes.DATOS_MODIFICADOS_OBSERVACIONES + "](" + Constantes.DATOS_MODIFICADOS_VALOR + ": " + observaciones + ")" : "";
			
			if (!Validaciones.isNullOrEmpty(valoresSelected)) {
				
				//Ahi se verifica la concurrencia
				List<Long> listaId = new ArrayList<Long>();
				String listaIdInconcurrentes = "";
				String[] valoresArray = valoresSelected.split(",");
				List<ShvValValor> listaValores = new ArrayList<ShvValValor>();
				
				for (String idValor: valoresArray) {
					String[] split = idValor.split("_");
					String id = split[0];
					listaId.add(Long.valueOf(id));
					String timeStamp = split[1];
					try {
						verificarConcurrencia(id, Long.valueOf(timeStamp));
					} catch (ConcurrenciaExcepcion e) {
						//ShvValValor val = valorDao.buscarValor(id);
						VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
						filtro.setIdValor(id);
						List<VistaSoporteResultadoBusquedaValor>listaResultadoValores = vistaSoporteServicio.buscarValores(filtro);
						
						listaIdInconcurrentes += listaResultadoValores.get(0).getNroBoleta().toString() +",";
					}
				}
				if (!Validaciones.isNullOrEmpty(listaIdInconcurrentes)) {
					listaIdInconcurrentes = listaIdInconcurrentes.substring(0, listaIdInconcurrentes.length()-1); 
					throw new ConcurrenciaExcepcion(listaIdInconcurrentes);
				}	
				
				//De validar las concurrencias, puede procesar la info

				double fechaHoraInicioNanoTime = System.nanoTime();
				listaValores = valorDao.buscarValores(listaId);
				Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo valorDao.buscarValores  ", fechaHoraInicioNanoTime);
				
				//creo el MAP
				HashMap<String, List<String>> cuerpoMailMap = new HashMap<String, List<String>>();
				
				for (ShvValValor val : listaValores) {
					 fechaHoraInicioNanoTime = System.nanoTime();
							workflowValores.autorizarBoleta(val.getWorkFlow(), observacionesWf, userSesion.getIdUsuario());
							Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo workflowValores.autorizarBolet ", fechaHoraInicioNanoTime);
					date = val.getWorkFlow().getFechaUltimaModificacion();
					val.setFechaUltimoEstado(date);
					val.setUsuarioAutorizacion(userSesion.getIdUsuario());

				 fechaHoraInicioNanoTime = System.nanoTime();
					val = valorDao.actualizarValor(val);
					Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo valorDao.actualizarValor ", fechaHoraInicioNanoTime);
					
					 fechaHoraInicioNanoTime = System.nanoTime();
					//Contabilidad
					contabilizarValor(val, false);
					Utilidad.tracearTiempo(getClass(), "Tiempo en los metodos de contabilidad  ", fechaHoraInicioNanoTime);

					//Finalizo la tarea y notifico al generador de la tarea
					//Preparo el cuerpo y el key del mail y lo guardo en el Map
					UsuarioLdapDto	usuarioLdapAnalista = new UsuarioLdapDto();
					String para="";
					String cc="";
					String ccTcomercial="";

					try {
						usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(val.getIdAnalista());
					} catch (LdapExcepcion e) {
						Traza.error(getClass(), "Se ha producido error en el servicio de LDAP", e);
					}
					if(!Validaciones.isNullOrEmpty(String.valueOf(usuarioLdapAnalista)) && !Validaciones.isNullOrEmpty(usuarioLdapAnalista.getMail()) ){
						para = usuarioLdapAnalista.getMail();
					}
				
					UsuarioLdapDto usuarioLdapCopropietario = null;
					if (!Validaciones.isNullOrEmpty(val.getIdCopropietario())) {
						try {
							usuarioLdapCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(val.getIdCopropietario());
						} catch (LdapExcepcion e) {
							Traza.error(getClass(), "Se ha producido error en el servicio de LDAP", e);
						}
						if(!Validaciones.isNullOrEmpty(String.valueOf(usuarioLdapCopropietario)) && !Validaciones.isNullOrEmpty(usuarioLdapCopropietario.getMail())  ){
							cc = usuarioLdapCopropietario.getMail();
						}
					}
					
					TeamComercialDto teamComercial = teamComercialServicio.buscarTeamComercial(String.valueOf(val.getIdClienteLegado()));
					UsuarioLdapDto usuarioLdapTeamComercial = null;
					if(!Validaciones.isObjectNull(teamComercial) && !Validaciones.isNullOrEmpty(teamComercial.getUserAnalistaCobranzaDatos())) {
						try {
							usuarioLdapTeamComercial = ldapServicio.buscarUsuarioPorUidEnMemoria(teamComercial.getUserAnalistaCobranzaDatos());
						} catch (LdapExcepcion e) {
							Traza.error(getClass(), "Se ha producido error en el servicio de LDAP", e);
						}
						if(!Validaciones.isObjectNull(usuarioLdapTeamComercial) && !Validaciones.isNullOrEmpty(usuarioLdapTeamComercial.getMail())  ){
							ccTcomercial = usuarioLdapTeamComercial.getMail();
						}
					}										 
				
					StringBuffer key = new StringBuffer(mailServicio.formarKeyAgrupador(para,cc,ccTcomercial));
					
					/**
					 * @author u573005, Sprint 3, derivado del defecto 49
					 */
					String correccionObseraciones = "";
					if(!Validaciones.isNullOrEmpty(observaciones)){
						correccionObseraciones = observaciones + System.lineSeparator();
					}					
					prepararMail(cuerpoMailMap, val, key, correccionObseraciones, null);

					tareaServicio.finalizarTarea(TipoTareaEnum.AUTR_VALOR, val.getWorkFlow().getIdWorkflow(), date, userSesion.getIdUsuario(), null);
					
					List<ShvValValor> valValorMails = new ArrayList<ShvValValor>();
					
					//Verificar si puede enviar mail al autorizar el valor, BD Enviada Mail
					if (!Validaciones.isNullOrEmpty(String.valueOf(val.getValorBoletaDepositoCheque()))) {
						if (!Validaciones.isNullOrEmpty(String.valueOf(val.getValorBoletaDepositoCheque().getBoleta().getEmailEnvioEstado()))) {
							if (!EnviarMailBoletaEstadoEnum.NO.equals(val.getValorBoletaDepositoCheque().getBoleta().getEmailEnvioEstado())) {
								valValorMails.add(val);
							}
						}
					} else if (!Validaciones.isNullOrEmpty(String.valueOf(val.getValorBoletaDepositoChequePD()))) {
						if (!Validaciones.isNullOrEmpty(String.valueOf(val.getValorBoletaDepositoChequePD().getBoleta().getEmailEnvioEstado()))) {
							if (!EnviarMailBoletaEstadoEnum.NO.equals(val.getValorBoletaDepositoChequePD().getBoleta().getEmailEnvioEstado())) {
								valValorMails.add(val);
							}
						}
					} else if (!Validaciones.isNullOrEmpty(String.valueOf(val.getValorBoletaEfectivo()))) {
						if (!Validaciones.isNullOrEmpty(String.valueOf(val.getValorBoletaEfectivo().getBoleta().getEmailEnvioEstado()))) {
							if (!EnviarMailBoletaEstadoEnum.NO.equals(val.getValorBoletaEfectivo().getBoleta().getEmailEnvioEstado())) {
								valValorMails.add(val);
							}
						}
					}	
						
					if (Validaciones.isCollectionNotEmpty(valValorMails)){
						mensajesMostrarResultados = generarPdfBoletaMail(valValorMails, userSesion.getIdUsuario());
						generarDatosWFMail(valValorMails);
					}
				}
				mailServicio.enviarMailMasivo("Boletas autorizadas|Las siguientes boletas se encuentran autorizadas:|Boleta autorizada -", cuerpoMailMap);
			}
		
		} catch (PersistenciaExcepcion e) {			
			throw new NegocioExcepcion(e.getMessage(), e);			
		}
		
		return mensajesMostrarResultados;
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void rechazarAutorizacionBoleta(UsuarioSesion userSesion, String valoresSelected, String observaciones) throws NegocioExcepcion {
		
		try {
			Date date = new Date();
			String observacionesWf = "[" + Constantes.DATOS_MODIFICADOS_OBSERVACIONES + "](" + Constantes.DATOS_MODIFICADOS_VALOR + ": " + observaciones + ")";
			
			if (!Validaciones.isNullOrEmpty(valoresSelected)) {
				List<ShvValValor> listaValores = new ArrayList<ShvValValor>();
				
				//Ahi se verifica la concurrencia
				String listaIdInconcurrentes = "";
				String[] valorSelectedSplit = valoresSelected.split(",");
				for (String idValor: valorSelectedSplit) {
					String[] split = idValor.split("_");
					String id = split[0];
					String timeStamp = split[1];
			
					ShvValValor val = valorDao.buscarValor(id);
					try {
						verificarConcurrencia(id, Long.valueOf(timeStamp));
						
					} catch (ConcurrenciaExcepcion e) {
						listaIdInconcurrentes += val.getBoleta().getNumeroBoleta() +",";
						Traza.auditoria(ValorServicioImpl.class, listaIdInconcurrentes);
					}
				}
				if (!Validaciones.isNullOrEmpty(listaIdInconcurrentes)) {
					listaIdInconcurrentes = listaIdInconcurrentes.substring(0, listaIdInconcurrentes.length()-1); 
					throw new ConcurrenciaExcepcion(listaIdInconcurrentes);
					
				}
				HashMap<String, List<String>> cuerpoMailMap = new HashMap<String, List<String>>();
				
				//De validar las concurrencias, puede procesar la info
				List<Long> listaId = new ArrayList<Long>();
				for (String idValor: valorSelectedSplit) {
					String id = idValor.split("_")[0];
					listaId.add(Long.valueOf(id));
				}	
				listaValores = valorDao.buscarValores(listaId);
				
				for (ShvValValor val : listaValores) {
					Traza.auditoria(ValorServicioImpl.class, "valor11 : id:" + val.getIdValor() + ", estado: " + val.getWorkFlow().getEstado() + ", fechaultimoestado:" + val.getFechaUltimoEstado());
					
					ShvWfWorkflow wf = val.getWorkFlow();
					Traza.auditoria(ValorServicioImpl.class, "id: " + wf.getIdWorkflow() + ", wf fecha: "+ wf.getFechaUltimaModificacion());
					
					ShvWfWorkflow workflowActualizado = workflowValores.rechazarAutorizacionBoleta(wf, observacionesWf, userSesion.getIdUsuario());
					val.setWorkFlow(workflowActualizado);
					
					Traza.auditoria(ValorServicioImpl.class, "id: " + workflowActualizado.getIdWorkflow() + ", wf fecha: "+ workflowActualizado.getFechaUltimaModificacion());
					date = workflowActualizado.getFechaUltimaModificacion();
					val.setFechaUltimoEstado(date);
					Traza.auditoria(ValorServicioImpl.class, "valor2 : id:" + val.getIdValor() + ", estado: " + val.getWorkFlow().getEstado() + ", fechaultimoestado:" + val.getFechaUltimoEstado());
					
					valorDao.actualizarValor(val);
					Traza.auditoria(ValorServicioImpl.class, "valor3 : id:" + val.getIdValor() + ", estado: " + val.getWorkFlow().getEstado() + ", fechaultimoestado:" + val.getFechaUltimoEstado());
					
					//Creo una tarea pendiente para el valor rechazado
					crearTareaPendiente(val, userSesion,false);
					
					//Preparo el cuerpo y el key del mail y lo guardo en el Map
					UsuarioLdapDto	usuarioLdapAnalista = new UsuarioLdapDto();
					String para="";
					String cc="";
					String ccTcomercial="";

					try {
						usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(val.getIdAnalista());
					} catch (LdapExcepcion e) {
						Traza.error(getClass(), "Se ha producido error en el servicio de LDAP", e);
					}
					if(!Validaciones.isNullOrEmpty(String.valueOf(usuarioLdapAnalista)) && !Validaciones.isNullOrEmpty(usuarioLdapAnalista.getMail()) ){
						para = usuarioLdapAnalista.getMail();
					}
				
					UsuarioLdapDto usuarioLdapCopropietario = null;
					if (!Validaciones.isNullOrEmpty(val.getIdCopropietario())) {
						try {
							usuarioLdapCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(val.getIdCopropietario());
						} catch (LdapExcepcion e) {
							Traza.error(getClass(), "Se ha producido error en el servicio de LDAP", e);
						}
						if(!Validaciones.isNullOrEmpty(String.valueOf(usuarioLdapCopropietario)) && !Validaciones.isNullOrEmpty(usuarioLdapCopropietario.getMail())  ){
							cc = usuarioLdapCopropietario.getMail();
						}
					}
					
					TeamComercialDto teamComercial = teamComercialServicio.buscarTeamComercial(String.valueOf(val.getIdClienteLegado()));
					UsuarioLdapDto usuarioLdapTeamComercial = null;
					if (!Validaciones.isObjectNull(teamComercial) && !Validaciones.isNullOrEmpty(teamComercial.getUserAnalistaCobranzaDatos())) {
						try {
							usuarioLdapTeamComercial = ldapServicio.buscarUsuarioPorUidEnMemoria(teamComercial.getUserAnalistaCobranzaDatos());
						} catch (LdapExcepcion e) {
							Traza.error(getClass(), "Se ha producido error en el servicio de LDAP", e);
						}
						if(!Validaciones.isNullOrEmpty(String.valueOf(usuarioLdapTeamComercial)) && !Validaciones.isNullOrEmpty(usuarioLdapTeamComercial.getMail())  ){
							ccTcomercial = usuarioLdapTeamComercial.getMail();
						}
					}
					StringBuffer key = new StringBuffer(mailServicio.formarKeyAgrupador(para,cc,ccTcomercial));
					
					/**
					 * @author u573005, sprint 3, defecto 50
					 */
					String correccionObservaciones = "";
					if(!Validaciones.isNullOrEmpty(observaciones)){
						correccionObservaciones = observaciones + System.lineSeparator(); 
					}
					prepararMail(cuerpoMailMap, val, key, correccionObservaciones, " | Usuario responsable del rechazo: " + userSesion.getNombreCompleto());
					
					//Finalizo la tarea y notifico al generador de la tarea (no envia mail)
					tareaServicio.finalizarTarea(TipoTareaEnum.AUTR_VALOR, val.getWorkFlow().getIdWorkflow(), date, userSesion.getIdUsuario(), null);	
					
					val = null;
				}
				
				//Mail masivo
				mailServicio.enviarMailMasivo("Boletas rechazadas|Las siguientes boletas se encuentran rechazadas:|Boleta rechazada -", cuerpoMailMap);
			}
			
		} catch (PersistenciaExcepcion e) {			
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void actualizarEstado(BoletaConValorFiltro boletaConValorFiltro, UsuarioSesion userSesion) throws NegocioExcepcion {
				
		try {	
			
			if (!Validaciones.isNullOrEmpty(boletaConValorFiltro.getIdsParaModificar())) {
				
				//
				// Aqui se verifica la concurrencia
				//
				String listaIdInconcurrentes = "";
				String[] idsParaModificar = boletaConValorFiltro.getIdsParaModificar().split(",");
				for (String idValor: idsParaModificar) {
					String[] split = idValor.split("_");
					String id = split[0];
					String timeStamp = split[1];
			
					try {
						verificarConcurrencia(id, Long.valueOf(timeStamp));
					} catch (ConcurrenciaExcepcion e) {
						listaIdInconcurrentes += id +",";
					}
				}
				if (!Validaciones.isNullOrEmpty(listaIdInconcurrentes)) {
					listaIdInconcurrentes = listaIdInconcurrentes.substring(0, listaIdInconcurrentes.length()-1); 
					throw new ConcurrenciaExcepcion(listaIdInconcurrentes);
				}	
				
				//
				// Comienzo validaciones para anular un valor
				//
					String errorSaldoReversado = "";
					String errorBoletaSugerida = "";
					String errorBoletaConRecibo = "";
					StringBuilder errorValorSaldoAgotado = new StringBuilder();
					StringBuilder errorValorSaldoDisponible = new StringBuilder();
					StringBuilder errorValorSaldoNoTotal = new StringBuilder();
					StringBuilder errorValorChequeRechazado = new StringBuilder();

					Map<Long, String> mapIdValoresSuspendidos = new HashMap<Long, String>();

					for (String idValor: idsParaModificar) {
						String id = idValor.split("_")[0];
						ShvValValor val = valorDao.buscarValor(id);

						// Si el estado de Origen es suspendidio
						if (Estado.VAL_SUSPENDIDO.equals(val.getWorkFlow().getWorkflowEstado().getEstado())) {
							if (MotivoSuspensionEnum.CHEQUE_RECHAZADO.descripcion().equals(val.getMotivoSuspension().getDescripcion())) {
								mapIdValoresSuspendidos.put(val.getIdValor(), val.getNumeroValor());
								errorValorChequeRechazado.append(val.getNumeroValor()).append("<br>");
							}
							if (Estado.VAL_DISPONIBLE.descripcion().equals(boletaConValorFiltro.getSelectEstado())) {
								if (BigDecimal.ZERO.equals(val.getSaldoDisponible())) {
									errorValorSaldoAgotado.append(val.getNumeroValor()).append("<br>");
								}
							} else if (Estado.VAL_USADO.descripcion().equals(boletaConValorFiltro.getSelectEstado())) {
								if (!BigDecimal.ZERO.equals(val.getSaldoDisponible())) {
									errorValorSaldoDisponible.append(val.getNumeroValor() + "<br>");
								}
							} else if (Estado.VAL_ANULADO.descripcion().equals(boletaConValorFiltro.getSelectEstado())) {
								if (val.getImporte().compareTo(val.getSaldoDisponible()) != 0) {
									//errorSaldoReversado += val.getNumeroValor() + "<br>";
									errorValorSaldoNoTotal.append(val.getNumeroValor()).append("<br>");
								}
								
								if (!Validaciones.isObjectNull(val.getBoleta())){
									if(Estado.BOL_SUGERIDA.equals(val.getBoleta().getWorkFlow().getEstado())){
										errorBoletaSugerida += val.getNumeroValor() + "<br>";
									}
								}
								if (val.getParamOrigen()!=null) {
									if (OrigenEnum.OF_RECIBO_PREIMPRESO.descripcion().equals(val.getParamOrigen().getDescripcion())) {
										if (!Validaciones.isObjectNull(val.getValorBoletaDepositoCheque())) {
											if (!Validaciones.isObjectNull(val.getValorBoletaDepositoCheque().getReciboPreImpreso())) {
												errorBoletaConRecibo += val.getNumeroValor() + "<br>";
											}
										} else if (!Validaciones.isObjectNull(val.getValorBoletaDepositoChequePD())) {
											if (!Validaciones.isObjectNull(val.getValorBoletaDepositoChequePD().getReciboPreImpreso())) {
												errorBoletaConRecibo += val.getNumeroValor() + "<br>";
											}
										} else if (!Validaciones.isObjectNull(val.getValorBoletaEfectivo())) {
											if (!Validaciones.isObjectNull(val.getValorBoletaEfectivo().getReciboPreImpreso())) {
												errorBoletaConRecibo += val.getNumeroValor() + "<br>";
											}
										}
									}
								}
							}
						} else if (Estado.VAL_ANULADO.descripcion().equals(boletaConValorFiltro.getSelectEstado())) {
							if (val.getImporte().compareTo(val.getSaldoDisponible()) != 0) {
								errorSaldoReversado += val.getNumeroValor() + "<br>";
							}
							if (!Validaciones.isObjectNull(val.getBoleta())){
								if(Estado.BOL_SUGERIDA.equals(val.getBoleta().getWorkFlow().getEstado())){
									errorBoletaSugerida += val.getNumeroValor() + "<br>";
								}
							}
							if(val.getParamOrigen()!=null){
								if(OrigenEnum.OF_RECIBO_PREIMPRESO.descripcion().equals(val.getParamOrigen().getDescripcion())){
									if(!Validaciones.isObjectNull(val.getValorBoletaDepositoCheque())){
										if(!Validaciones.isObjectNull(val.getValorBoletaDepositoCheque().getReciboPreImpreso())){
											errorBoletaConRecibo += val.getNumeroValor() + "<br>";
										}
									}else
									if(!Validaciones.isObjectNull(val.getValorBoletaDepositoChequePD())){
										if(!Validaciones.isObjectNull(val.getValorBoletaDepositoChequePD().getReciboPreImpreso())){
											errorBoletaConRecibo += val.getNumeroValor() + "<br>";
									}
									}else
									if(!Validaciones.isObjectNull(val.getValorBoletaEfectivo())){
										if(!Validaciones.isObjectNull(val.getValorBoletaEfectivo().getReciboPreImpreso())){
											errorBoletaConRecibo += val.getNumeroValor() + "<br>";
										}
									}
								}
							}
						}
					}
					String mensaje ="";
					if (!Validaciones.isNullOrEmpty(errorSaldoReversado)) {
						mensaje= Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("valor.cambio.estado.anular.error.saldo.reversado"), 
								errorSaldoReversado);
					}
					if (!Validaciones.isNullOrEmpty(errorBoletaSugerida)) {
						if(!mensaje.equals("")){
							mensaje+="<br>";
						}
						mensaje+= Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("valor.cambio.estado.anular.error.boleta.sugerida"),
								errorBoletaSugerida);
					}
					if (!Validaciones.isNullOrEmpty(errorBoletaConRecibo)) {
						if(!mensaje.equals("")){
							mensaje+="<br>";
						}
						mensaje+= Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("valor.cambio.estado.anular.error.boleta.recibo.asociado"),
								errorBoletaConRecibo);
					}
					if (!Validaciones.isNullOrEmpty(errorValorChequeRechazado.toString())) {
						// Busco los legajo que corresponde al Valor
						List<Bean> lista = legajoChequeRechazadoServicio.listaBusqueda(mapIdValoresSuspendidos.keySet());
						StringBuilder str = new StringBuilder();
						for (Bean bean : lista) {
							VistaSoporteResultadoBusquedaLegajoChequeRechazado vistaSoporteResultadoBusquedaLegajoChequeRechazado = (VistaSoporteResultadoBusquedaLegajoChequeRechazado) bean;
							str.append(
								Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("valor.dupla.idLegajo.idValor"),
									vistaSoporteResultadoBusquedaLegajoChequeRechazado.getIdLegajo().toString(),
									mapIdValoresSuspendidos.get(vistaSoporteResultadoBusquedaLegajoChequeRechazado.getIdValor())
							));
							str.append(", ");
						}
						str.delete(str.toString().length() - 2, str.toString().length());
						
						if (!mensaje.equals("")) {
							mensaje+="<br>";
						}
						mensaje+= Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("valor.cambio.estado.suspendido.error.cheque.rechazado"),
								str.toString().replaceAll(", ", "<br>"));
					}
					if(!Validaciones.isNullOrEmpty(errorValorSaldoAgotado.toString())){
						if(!mensaje.equals("")){
							mensaje+="<br>";
						}
						mensaje+= Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("valor.cambio.estado.disponible.error.saldo.agotado"),
								errorValorSaldoAgotado.toString());
					}
					if(!Validaciones.isNullOrEmpty(errorValorSaldoDisponible.toString())){
						if(!mensaje.equals("")){
							mensaje+="<br>";
						}
						mensaje+= Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("valor.cambio.estado.usado.error.saldo.disponible"),
								errorValorSaldoDisponible.toString());
					}
					if(!Validaciones.isNullOrEmpty(errorValorSaldoNoTotal.toString())){
						if(!mensaje.equals("")){
							mensaje+="<br>";
						}
						mensaje+= Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("valor.cambio.estado.anulado.error.saldo.no.total"),
								errorValorSaldoNoTotal.toString());
					}
					if(!mensaje.equals("")){
						throw new ValidacionExcepcion(mensaje);
					}
				
				
				//
				// Fin validaciones para anular un valor
				//
				
				// De validar las concurrencias, puede procesar la info
				for (String idValor: idsParaModificar) {
					String id = idValor.split("_")[0];
					
					//
					// Voy a Suspender un valor
					//
					if (Estado.VAL_SUSPENDIDO.descripcion().equals(boletaConValorFiltro.getSelectEstado())) {
						
						suspenderValor(new Long(id), 
								boletaConValorFiltro.getObservaciones(), 
								boletaConValorFiltro.getUsuarioLogeado().getIdUsuario(),
								MotivoSuspensionEnum.getEnumByCodigo(boletaConValorFiltro.getIdMotivoSuspension()), null); 
						
					} else 
						//
						// Voy a Anular un valor
						//

						if (Estado.VAL_ANULADO.descripcion().equals(boletaConValorFiltro.getSelectEstado())) {
						
							anularValor(valorDao.buscarValor(id), 
										boletaConValorFiltro.getObservaciones(), 
										boletaConValorFiltro.getUsuarioLogeado().getIdUsuario());
							
					} else 
						//
						// Voy a Disponibilizar un valor
						//
						if (Estado.VAL_DISPONIBLE.descripcion().equals(boletaConValorFiltro.getSelectEstado())) {
						
							disponibilizarValor(valorDao.buscarValor(id), 
										boletaConValorFiltro.getObservaciones(), 
										boletaConValorFiltro.getUsuarioLogeado().getIdUsuario()); 
					} else if (Estado.VAL_USADO.descripcion().equals(boletaConValorFiltro.getSelectEstado())) {
						this.usarValorSuspendido(
							valorDao.buscarValor(id),
							boletaConValorFiltro.getObservaciones(),
							boletaConValorFiltro.getUsuarioLogeado().getIdUsuario()
						);
					}
					
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}		
	}
	

	/**
	 * 
	 * @param idValor
	 * @param observaciones
	 * @param idUsuario
	 * @param motivoSuspension
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor suspenderValor(Long idValor, String observaciones, String idUsuario, 
			MotivoSuspensionEnum motivoSuspension, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion {
	
		return suspenderValor(valorDao.buscarValor(idValor.toString()), observaciones, idUsuario, motivoSuspension, fechaRechazo);
	}

	/**
	 * 
	 * @param valorSimplificado
	 * @param observaciones
	 * @param idUsuario
	 * @param motivoSuspension
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor suspenderValor(ShvValValor valor, String observaciones, String idUsuario, 
			MotivoSuspensionEnum motivoSuspension, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion {

		StringBuffer observacionesSuspension = new StringBuffer();

		if (MotivoSuspensionEnum.CHEQUE_RECHAZADO.equals(motivoSuspension)) {
			
			observacionesSuspension.append(Constantes.OPEN_CORCHETES); 
			observacionesSuspension.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.valorSuspendido")); 
			observacionesSuspension.append(Constantes.CLOSE_CORCHETES); 
			
			observacionesSuspension.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.estado"));
			observacionesSuspension.append(Constantes.DOS_PUNTOS);
			observacionesSuspension.append(Constantes.WHITESPACE); 
			observacionesSuspension.append(Estado.VAL_SUSPENDIDO.descripcion());
			observacionesSuspension.append(Constantes.WHITESPACE); 

			observacionesSuspension.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.motivo"));
			observacionesSuspension.append(Constantes.DOS_PUNTOS);
			observacionesSuspension.append(Constantes.WHITESPACE); 
			observacionesSuspension.append(MotivoSuspensionEnum.CHEQUE_RECHAZADO.descripcion());
			observacionesSuspension.append(Constantes.WHITESPACE); 

			observacionesSuspension.append(observaciones);

			observacionesSuspension.append(Constantes.CLOSE_PARENTESIS); 
			observacionesSuspension.append(Constantes.WHITESPACE); 

		} else {
			
			observacionesSuspension.append(Constantes.OPEN_CORCHETES); 
			observacionesSuspension.append(Constantes.DATOS_MODIFICADOS_OBSERVACIONES); 
			observacionesSuspension.append(Constantes.CLOSE_CORCHETES); 
			
			observacionesSuspension.append(Constantes.OPEN_PARENTESIS); 
			observacionesSuspension.append(Constantes.DATOS_MODIFICADOS_VALOR); 
			observacionesSuspension.append(Constantes.DOS_PUNTOS);
			observacionesSuspension.append(Constantes.WHITESPACE); 
			observacionesSuspension.append(observaciones); 
			observacionesSuspension.append(Constantes.CLOSE_PARENTESIS); 
		}
		 
		ShvParamMotivoSuspension motivo = motivoSuspensionDao.buscarMotivoSuspensionPorID(motivoSuspension.codigo().toString());				
		valor.setMotivoSuspension(motivo);
		
		ShvWfWorkflow workflowActualizado = null;

		String estadoValor = valor.getWorkFlow().getEstado().name();
		if (Estado.VAL_DISPONIBLE.name().equals(estadoValor.toString())) {
			 workflowActualizado = suspenderValorDisponible(valor.getWorkFlow(), observacionesSuspension.toString(), idUsuario);

		} else if (Estado.VAL_USADO.name().equals(estadoValor.toString())) {
			 workflowActualizado = suspenderValorUsado(valor.getWorkFlow(), observacionesSuspension.toString(), idUsuario);
		}
		
		if (MotivoSuspensionEnum.CHEQUE_RECHAZADO.equals(motivoSuspension)) {
			
			// Contabilizamos la suspension realizada a travez del legajo.
			contabilizarValorSuspendidoMotivoChequeRechazado(valor.getIdValor().toString(), fechaRechazo);
		}
		
		valor.setWorkFlow(workflowActualizado);
		valor.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
		
		return valorDao.actualizarValor(valor);
		
	}
	
	/**
	 * 
	 * @param val
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor anularValor(Long idValor, String observaciones, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion {

		return anularValor(valorDao.buscarValor(idValor.toString()), observaciones, idUsuario);
	}
	
	/**
	 * 
	 * @param val
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor anularValor(Long idValor, String observaciones, String idUsuario, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion {

		return anularValor(valorDao.buscarValor(idValor.toString()), observaciones, idUsuario, fechaRechazo);
	}

	/**
	 * 
	 * @param val
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor anularValor(ShvValValor val, String observaciones, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion {
		
		return anularValor(val, observaciones, idUsuario, null);
	}

	/**
	 * 
	 * @param val
	 * @param observaciones
	 * @param idUsuario
	 * @param fechaRechazoLegajoChequeRechazado
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor anularValor(ShvValValor val, String observaciones, String idUsuario, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion {
		
		StringBuffer observacionesAnulacion = new StringBuffer();
		
		observacionesAnulacion.append(Constantes.OPEN_CORCHETES); 
		observacionesAnulacion.append(Constantes.DATOS_MODIFICADOS_OBSERVACIONES); 
		observacionesAnulacion.append(Constantes.CLOSE_CORCHETES); 
		
		observacionesAnulacion.append(Constantes.OPEN_PARENTESIS); 
		observacionesAnulacion.append(Constantes.DATOS_MODIFICADOS_VALOR); 
		observacionesAnulacion.append(Constantes.DOS_PUNTOS);
		observacionesAnulacion.append(Constantes.WHITESPACE); 
		observacionesAnulacion.append(observaciones); 
		observacionesAnulacion.append(Constantes.CLOSE_PARENTESIS); 

		ShvWfWorkflow workflowActualizado = null;
		
		HashMap<String,List<List<ShvValValor>>> listaValoresRecibo = new HashMap<String,List<List<ShvValValor>>>();

		if (Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.equals(val.getWorkFlow().getEstado())) {
			// Anular Boleta con valor
			workflowActualizado = anularValorBoletaPendienteConciliacion(val, observacionesAnulacion.toString(), idUsuario, listaValoresRecibo);
			
		} else if (Estado.VAL_SUSPENDIDO.equals(val.getWorkFlow().getEstado())) {
			
			MotivoSuspensionEnum motivoSuspension = null;
			
			if (!Validaciones.isObjectNull(val.getMotivoSuspension().getIdMotivoSuspension())) {
				motivoSuspension = MotivoSuspensionEnum.getEnumByCodigo(val.getMotivoSuspension().getIdMotivoSuspension());
			}
			
			if (MotivoSuspensionEnum.CHEQUE_RECHAZADO.equals(motivoSuspension)) {
				
				observacionesAnulacion = new StringBuffer();	

				observacionesAnulacion.append(Constantes.OPEN_CORCHETES); 
				observacionesAnulacion.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.valorAnulado")); 
				observacionesAnulacion.append(Constantes.CLOSE_CORCHETES); 
				
				observacionesAnulacion.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.estado"));
				observacionesAnulacion.append(Constantes.DOS_PUNTOS);
				observacionesAnulacion.append(Constantes.WHITESPACE); 
				observacionesAnulacion.append(Estado.VAL_ANULADO.descripcion());
				observacionesAnulacion.append(Constantes.WHITESPACE); 

				observacionesAnulacion.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.motivo"));
				observacionesAnulacion.append(Constantes.DOS_PUNTOS);
				observacionesAnulacion.append(Constantes.WHITESPACE); 
				observacionesAnulacion.append(MotivoSuspensionEnum.CHEQUE_RECHAZADO.descripcion());
				observacionesAnulacion.append(Constantes.WHITESPACE); 

				observacionesAnulacion.append(observaciones);

				observacionesAnulacion.append(Constantes.CLOSE_PARENTESIS); 
				observacionesAnulacion.append(Constantes.WHITESPACE);
			}
			
			// Anulo el valor
			workflowActualizado = anularValor(val.getWorkFlow(), observacionesAnulacion.toString(), idUsuario);						
			
			// Actualizo recibos asociados en caso de ser necesario
			actualizarEstadoRecibo(listaValoresRecibo, val, false);

			// Genero la contabilidad según corresponda
			if (MotivoSuspensionEnum.OTROS.equals(motivoSuspension) || MotivoSuspensionEnum.CHEQUE_RECHAZADO.equals(motivoSuspension)) {
				contabilidadServicio.anularContabilidad(val.getIdValor());
			}
			
			if (MotivoSuspensionEnum.CHEQUE_RECHAZADO.equals(motivoSuspension)) {
				contabilizarValorAnuladoMotivoChequeRechazado(val.getIdValor().toString(), fechaRechazo);
			}

		} else if (Estado.VAL_AVISO_PAGO_RECHAZADO.equals(val.getWorkFlow().getEstado())){
			workflowActualizado = anularAvisoDePagoRechazado(val.getWorkFlow(), observacionesAnulacion.toString(), idUsuario);	
			Date date = workflowActualizado.getFechaUltimaModificacion();
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_AVISO_PAGO, val.getWorkFlow().getIdWorkflow(), date, idUsuario, null);
			
		} else if (Estado.VAL_BOLETA_RECHAZADA.equals(val.getWorkFlow().getEstado())){
			workflowActualizado = anularValorBoletaRechazada(val.getWorkFlow(), observacionesAnulacion.toString(), idUsuario);
			Date date = workflowActualizado.getFechaUltimaModificacion();
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_VALOR, val.getWorkFlow().getIdWorkflow(), date, idUsuario, null);
		}
		
		anularConstancia(val, idUsuario);
		val.setWorkFlow(workflowActualizado);
		val.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
		
		return valorDao.actualizarValor(val);		
	}
	
	/**
	 * 
	 * @param idValor
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor disoniblizarValor(Long idValor, String observaciones, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion {
			
		return disponibilizarValor(valorDao.buscarValor(idValor.toString()), observaciones, idUsuario, null);
	}

	/**
	 * 
	 * @param idValor
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor disoniblizarValor(Long idValor, String observaciones, String idUsuario, Date fechaRehazo) throws NegocioExcepcion, PersistenciaExcepcion {
			
		return disponibilizarValor(valorDao.buscarValor(idValor.toString()), observaciones, idUsuario, fechaRehazo);
	}

	/**
	 * 
	 * @param val
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor disponibilizarValor(ShvValValor val, String observaciones, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion {
		
		return disponibilizarValor(val, observaciones, idUsuario, null);
	}
	/**
	 * 
	 * @param val
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor usarValorSuspendido(ShvValValor val, String observaciones, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion {
		if (Estado.VAL_SUSPENDIDO.equals(val.getWorkFlow().getEstado())) {
			StringBuffer observacionesStr = new StringBuffer();	

			observacionesStr.append(Constantes.OPEN_CORCHETES); 
			observacionesStr.append(Constantes.DATOS_MODIFICADOS_OBSERVACIONES); 
			observacionesStr.append(Constantes.CLOSE_CORCHETES); 

			observacionesStr.append(Constantes.OPEN_PARENTESIS); 
			observacionesStr.append(Constantes.DATOS_MODIFICADOS_VALOR); 
			observacionesStr.append(Constantes.DOS_PUNTOS);
			observacionesStr.append(Constantes.WHITESPACE); 
			observacionesStr.append(observaciones); 
			observacionesStr.append(Constantes.CLOSE_PARENTESIS);

			ShvWfWorkflow workflowActualizado = this.usarValorSuspendido(val.getWorkFlow(), observacionesStr.toString(), idUsuario);

			val.setWorkFlow(workflowActualizado);
			val.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
			val.setFechaDisponible(workflowActualizado.getFechaUltimaModificacion());
			val.setMotivoSuspension(null);
		}
		return val;
	}
	
	public ShvWfWorkflow usarValorSuspendido( ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion{	
		ShvWfWorkflow  workflowActualizado = workflowValores.usarValorSuspendido(wf, datosModificados, usuarioModificacion);
		return workflowActualizado;
	}
	/**
	 * 
	 * @param valor
	 * @param observaciones
	 * @param idUsuario
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor disponibilizarValor(ShvValValor valor, String observaciones, String idUsuario, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion {

		StringBuffer observacionesDisponibilizar = new StringBuffer();
		
		if (Estado.VAL_SUSPENDIDO.equals(valor.getWorkFlow().getEstado())) {
		
			if (MotivoSuspensionEnum.CHEQUE_RECHAZADO.equals(MotivoSuspensionEnum.getEnumByCodigo(valor.getMotivoSuspension().getIdMotivoSuspension()))) {
				
				// Anulo el movimiento contable 043 que generé cuando se suspendió el valor con motivo "cheque rechazado" a través de la gestión
				// de un legajo.
				anularContabilidadValorSuspendidoMotivoChequeRechazado(valor.getIdValor().toString(), fechaRechazo);
				
				// Armo la leyenda especial que irá a parar al historial del valor
				observacionesDisponibilizar.append(Constantes.OPEN_CORCHETES); 
				observacionesDisponibilizar.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.valorDisponible")); 
				observacionesDisponibilizar.append(Constantes.CLOSE_CORCHETES); 
				
				observacionesDisponibilizar.append(Constantes.OPEN_PARENTESIS); 
				observacionesDisponibilizar.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.estado"));
				observacionesDisponibilizar.append(Constantes.DOS_PUNTOS);
				observacionesDisponibilizar.append(Constantes.WHITESPACE); 

				if (BigDecimal.ZERO.compareTo(valor.getSaldoDisponible()) == 0) {
					observacionesDisponibilizar.append(Estado.VAL_USADO.descripcion());
				} else {
					observacionesDisponibilizar.append(Estado.VAL_DISPONIBLE.descripcion());
				}

				observacionesDisponibilizar.append(Constantes.WHITESPACE); 
	
				observacionesDisponibilizar.append(observaciones);
	
				observacionesDisponibilizar.append(Constantes.CLOSE_PARENTESIS); 
				observacionesDisponibilizar.append(Constantes.WHITESPACE); 
	
			} else {
				
				observacionesDisponibilizar.append(Constantes.OPEN_CORCHETES); 
				observacionesDisponibilizar.append(Constantes.DATOS_MODIFICADOS_OBSERVACIONES); 
				observacionesDisponibilizar.append(Constantes.CLOSE_CORCHETES); 
				
				observacionesDisponibilizar.append(Constantes.OPEN_PARENTESIS); 
				observacionesDisponibilizar.append(Constantes.DATOS_MODIFICADOS_VALOR); 
				observacionesDisponibilizar.append(Constantes.DOS_PUNTOS);
				observacionesDisponibilizar.append(Constantes.WHITESPACE); 
				observacionesDisponibilizar.append(observaciones); 
				observacionesDisponibilizar.append(Constantes.CLOSE_PARENTESIS);
			}
			
			ShvWfWorkflow workflowActualizado = null;
			
			if (BigDecimal.ZERO.compareTo(valor.getSaldoDisponible()) == 0) {
				workflowActualizado = revertirSuspensionValorUsado(valor.getWorkFlow(), observacionesDisponibilizar.toString(), idUsuario);
			} else {
				workflowActualizado = disponibilizarValorSuspendido(valor.getWorkFlow(), observacionesDisponibilizar.toString(), idUsuario);
				
				String asunto = "Valor Disponible - " +  mailServicio.armarAsuntoValor(valor);
				TeamComercialDto teamComercial = teamComercialServicio.buscarTeamComercial(String.valueOf(valor.getIdClienteLegado()));
				String usuarioAnalistaCobranzaDatos = "";
				if (!Validaciones.isObjectNull(teamComercial) && !Validaciones.isNullOrEmpty(teamComercial.getUserAnalistaCobranzaDatos())) {
				 usuarioAnalistaCobranzaDatos = teamComercial.getUserAnalistaCobranzaDatos();
				}
				this.enviarMailValor(valor.getIdAnalista(), valor.getIdCopropietario(), usuarioAnalistaCobranzaDatos, observaciones, asunto);		
			}
	
			valor.setWorkFlow(workflowActualizado);
			valor.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
			valor.setFechaDisponible(workflowActualizado.getFechaUltimaModificacion());
			valor.setMotivoSuspension(null);
			 
			valor = valorDao.actualizarValor(valor);
		}
		
		return valor;
	}
	

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow disponibilizarValorSuspendido( ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion{	
		ShvWfWorkflow  workflowActualizado = workflowValores.disponibilizarValorSuspendido(wf, datosModificados, usuarioModificacion);
		return workflowActualizado;
		
	}
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow revertirSuspensionValorUsado( ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion{	
		ShvWfWorkflow  workflowActualizado = workflowValores.revertirSuspensionValorUsado(wf, datosModificados, usuarioModificacion);
		return workflowActualizado;
		
	}

	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow anularValor( ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion{	
		ShvWfWorkflow  workflowActualizado = workflowValores.anular(wf, datosModificados, usuarioModificacion);
		return workflowActualizado;
		
	}

	/**
	 *
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow anularAvisoDePagoRechazado( ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion{	
		ShvWfWorkflow  workflowActualizado = workflowValores.anularAvisoDePagoRechazado(wf, datosModificados, usuarioModificacion);
		return workflowActualizado;
	}
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow anularValorBoletaPendienteConciliacion( ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion{
		return workflowValores.anularBoletaPendienteDeConciliar(wf, datosModificados, usuarioModificacion);
	}
	
	/**
	 * 
	 * @param wf
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @return
	 * @throws WorkflowExcepcion
	 */
	public ShvWfWorkflow anularValorBoletaRechazada( ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion{
		return workflowValores.anularBoletaRechazada(wf, datosModificados, usuarioModificacion);
	}
	
	/**
	 * Anula el valor y ademas la boleta con valor asociada a dicho valor.
	 */
	public ShvWfWorkflow anularValorBoletaPendienteConciliacion( ShvValValor valor, String datosModificados, String usuarioModificacion, HashMap<String,List<List<ShvValValor>>> listaValoresRecibo) throws WorkflowExcepcion{
		try {
			//Contabilidad
			if (!valor.getParamOrigen().getIdOrigen().equals(ORIGEN_CLIENTE_ID)){
				contabilidadServicio.anularContabilidad(valor.getIdValor());
			}
			//Anular valor
			ShvWfWorkflow  workflowActualizadoValor = null;
			if (valor.getWorkFlow().getEstado().equals(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION)){
				workflowActualizadoValor = anularValorBoletaPendienteConciliacion(valor.getWorkFlow(), datosModificados, usuarioModificacion);
			} else {
				if (valor.getWorkFlow().getEstado().equals(Estado.VAL_BOLETA_RECHAZADA)){
					workflowActualizadoValor = anularValorBoletaRechazada(valor.getWorkFlow(), datosModificados, usuarioModificacion);
				}
			}
			
			actualizarEstadoRecibo(listaValoresRecibo, valor,false);
			//Anular Boleta con valor
			ShvBolBoleta boleta = valor.getBoleta();
			ShvWfWorkflow wf = boleta.getWorkFlow();
						
			if(Estado.BOL_PENDIENTE.equals(wf.getEstado())){
				//Verifico concurrencia
				verificarConcurrenciaBoletaConValor(boleta.getIdBoleta(), Long.valueOf(wf.getFechaUltimaModificacion().getTime()));
				
				//Anula la boleta
				ShvWfWorkflow workflowActualizadoBoleta = workflowBoletas.solicitarBoletaAnulacion(wf, "", usuarioModificacion);
				
				boleta.setWorkFlow(workflowActualizadoBoleta);
				
				boletaDao.actualizarBoleta(boleta);
			}
			return workflowActualizadoValor;
		} catch (NegocioExcepcion e) {
			throw new WorkflowExcepcion(e.getMessage(), e);
		} catch (PersistenciaExcepcion e) {
			throw new WorkflowExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * Verifica la concurrencia para actualizar una boleta asociada a un valor.
	 * @param id
	 * @param timeStamp
	 * @throws NegocioExcepcion
	 */
	private void verificarConcurrenciaBoletaConValor(Serializable id, Long timeStamp) throws NegocioExcepcion {
		
		try {
			ShvBolBoleta boleta = boletaDao.buscarBoleta(Long.valueOf(id.toString()));
			if (Validaciones.isObjectNull(boleta)) {
				ShvWfWorkflow wf = boleta.getWorkFlow();
				
				//Si la ultima modificacion es distinta a la actual
				if (wf.getFechaUltimaModificacion().getTime() != timeStamp.longValue()) {
					throw new ConcurrenciaExcepcion(boleta.getNumeroBoleta());
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public ShvWfWorkflow suspenderValorDisponible( ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion{	
		ShvWfWorkflow  workflowActualizado = workflowValores.suspenderValorDisponible(wf, datosModificados, usuarioModificacion);
		return workflowActualizado;	
	}
	
	public ShvWfWorkflow suspenderValorUsado( ShvWfWorkflow wf, String datosModificados, String usuarioModificacion) throws WorkflowExcepcion{	
		ShvWfWorkflow  workflowActualizado = workflowValores.suspenderValorUsado(wf, datosModificados, usuarioModificacion);
		return workflowActualizado;
		
	}	
	
	public ArrayList<ValorDto> buscarParaCambioEstado(UsuarioSesion userSesion, List<Long> idValores) throws NegocioExcepcion {
		try {
			
			List<ShvValValor> valoresAmodificar = valorDao.buscarValores(idValores);
			ArrayList<ValorDto> listaValores = new ArrayList<ValorDto>();

			for (ShvValValor val : valoresAmodificar) {

				listaValores.add((ValorDto) defaultMapeador.map(val));
			}
			
		return listaValores;
		} catch (PersistenciaExcepcion e) {			
			throw new NegocioExcepcion(e.getMessage(), e);			
		}	
	}
	

	public ArrayList<ValorDto> buscarValoresEnVista(UsuarioSesion userSesion, List<Long> idValores) throws NegocioExcepcion {
		
		try {

			VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
			filtro.setIdValores(idValores);
			List<VistaSoporteResultadoBusquedaValor> listaVistaSoporteResultadoBusquedaValor;
			listaVistaSoporteResultadoBusquedaValor = vistaSoporteDao.buscarValores(filtro);
			ArrayList<ValorDto> listaValores = new ArrayList<ValorDto>();

			for (VistaSoporteResultadoBusquedaValor val : listaVistaSoporteResultadoBusquedaValor) {

				
				listaValores.add((ValorDto) resultadoBusquedaValorMapeador.map(val));
			}
			
		return listaValores;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e); 
		}
	}
	

	/**
	 * 
	 */
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		return null;
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvValValor crearValor(ValorDto valorDto) throws NegocioExcepcion {
		try {
			ShvValValor valor = mapFromValorDTOToValValor(valorDto);
			valor.setObservaciones((valorDto.getNuevaObservacion() != null) ? valorDto.getNuevaObservacion().trim() : "");
			valor = valorDao.insertarValor(valor);
			if(!Estado.VAL_NO_DISPONIBLE.equals(valor.getWorkFlow().getEstado())){
				contabilizarValor(valor, false);
			}
			return valor;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	/** @author fabio.giaquinta.ruiz*/
	@Override
	@Transactional 
	public ArrayList<ValorDto> buscarValores(BoletaConValorFiltro boletaFiltro) throws NegocioExcepcion {

		try {
			ArrayList<ValorDto> listaValores = new ArrayList<ValorDto>();

			List<VistaSoporteResultadoBusquedaValor> listaValoresVistaSoporte;
			VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro(boletaFiltro.getTextCriterio(), boletaFiltro.getiDestado());
			filtro.setIdEmpresa(boletaFiltro.getEmpresa());
			filtro.setIdSegmento(boletaFiltro.getSegmento());
			filtro.setFechaDesde(boletaFiltro.getFechaDesde());
			filtro.setFechaHasta(boletaFiltro.getFechaHasta());			
			filtro.setIdEstado(boletaFiltro.getSelectEstado());
			filtro.setImporteDesde(boletaFiltro.getImporteDesde());
			filtro.setImporteHasta(boletaFiltro.getImporteHasta());
			filtro.setNroBoletaFiltro(boletaFiltro.getNroBoletaFiltro());
			filtro.setReferenciaDelValorFiltro(boletaFiltro.getReferenciaDelValorFiltro());
			filtro.setIdValor(boletaFiltro.getIdValorFiltro());
			filtro.setIdCobroShivaFiltro(boletaFiltro.getIdCobroShivaFiltro());
			filtro.setAnalistaFiltro(boletaFiltro.getAnalistaFiltro());
			
			if (!Validaciones.isObjectNull(boletaFiltro.getListaClientesSegunFiltros())){
				for (ClienteDto cliente : boletaFiltro.getListaClientesSegunFiltros()) {
					filtro.getIdClientesLegado().add(cliente.getIdClienteLegado());
				}
			}
			
			listaValoresVistaSoporte = vistaSoporteServicio.buscarValores(filtro);

			ValorDto valorDto = new ValorDto();
			for (VistaSoporteResultadoBusquedaValor vistaSoporteResultadoBusquedaValor : listaValoresVistaSoporte) {
				valorDto = (ValorDto) resultadoBusquedaValorMapeador.map(vistaSoporteResultadoBusquedaValor);
				if(!Validaciones.isObjectNull(boletaFiltro.getUsuarioLogeado())){
					if(boletaFiltro.getUsuarioLogeado().esSupervisor()){
						Collection<String> perfiles = boletaFiltro.getUsuarioLogeado().getPerfilesCompletos();
						for (String perfil : perfiles) {
							String[] splitPerfilEmpresaSegmento = perfil.split("_");
							if(splitPerfilEmpresaSegmento[0].equalsIgnoreCase(Constantes.SUPERVISOR)){
								if(splitPerfilEmpresaSegmento[2].equalsIgnoreCase(valorDto.getIdSegmento()) && 
										splitPerfilEmpresaSegmento[1].equalsIgnoreCase(valorDto.getIdEmpresa())) {
									valorDto.setEsSupervisorEmpresaSegmento(true);
								}
							}
						}
					}
				}
				listaValores.add(valorDto);
			}
			return listaValores;
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	@Transactional 
	public ArrayList<ValorDto> buscarValores(UsuarioSesion userSesion,
			BoletaConValorFiltro boletaFiltro) throws NegocioExcepcion {

		try {
			List<ShvValValor> listaValoresoModelo;
			listaValoresoModelo = valorDao.buscarValores(userSesion, boletaFiltro);
			ArrayList<ValorDto> listaValores = new ArrayList<ValorDto>();

			for (ShvValValor val : listaValoresoModelo) {
				listaValores.add((ValorDto) defaultMapeador.map(val));
			}

			return listaValores;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
 
	@Override
	public List<ShvValValor> buscarValores(List<Long> idValores) throws NegocioExcepcion {

		try {
			return /*listaValoresoModelo = */valorDao.buscarValores(idValores);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValorSimplificado actualizarSaldoPorApropiacion(ShvValValorSimplificado valor, SaldoADescontarImputacionBatch saldo) throws NegocioExcepcion{
	    
		
		BigDecimal saldoDisponible = valor.getSaldoDisponible();


			if (saldoDisponible.compareTo(saldo.getSaldoADescontar()) > 0
					|| saldoDisponible.compareTo(saldo.getSaldoADescontar()) == 0){
				
				BigDecimal saldoDisponibleActualizado = saldoDisponible.subtract(saldo.getSaldoADescontar());
				valor.setSaldoDisponible(saldoDisponibleActualizado);
				
				String datosModificados = "[Asignación de Saldo]"
						+ " (Id Transaccion: " + saldo.getIdOperacionIdTransaccion() 
//						+ (String)(" Id Factura:" + (saldo.getIdFactura() != null ? saldo.getIdFactura():"-"))
//						+ (String)(" Origen de la Factura: " + (saldo.getOrigenFactura() != null ? saldo.getOrigenFactura():"-"))
						+ saldo.getDescFactura()
						+ " Saldo utilizado: " + saldo.getSaldoADescontar() 
						+ " Saldo Original: " + saldoDisponible 
						+ " Saldo Modificado: " + valor.getSaldoDisponible() + ")";
				
				if (saldoDisponibleActualizado.compareTo(BigDecimal.ZERO) == 0) {
					valor.setWorkFlow(workflowValores.usarValor(valor.getWorkFlow(), datosModificados, saldo.getUsuarioModificacion()));
					valor.setFechaUltimoEstado(valor.getWorkFlow().getWorkflowEstado().getFechaModificacion());
				}else{
					
					valor.setWorkFlow(workflowValores.actualizarWorkflow(valor.getWorkFlow(), datosModificados, saldo.getUsuarioModificacion()));
				}
				
				Traza.advertencia(getClass(), datosModificados);
				valor = actualizarValorSimplificado(valor);
			} else{
				throw new NegocioExcepcion(Propiedades.MENSAJES_PROPIEDADES.getString("error.cobros.apropiacion.shiva.sinSaldo"));
			}
				
			return valor;
		
	}


	
	@Override
	public ArrayList<ValorDto> buscarValoresParaAutorizacion(UsuarioSesion userSesion,
			BoletaConValorFiltro boletaFiltro) throws NegocioExcepcion {

		try {
			List<ShvValValor> listaValoresoModelo;
			listaValoresoModelo = valorDao.buscarValoresParaAutorizar(userSesion,boletaFiltro);
			ArrayList<ValorDto> listaValores = new ArrayList<ValorDto>();

			for (ShvValValor val : listaValoresoModelo) {

				listaValores.add((ValorDto) defaultMapeador.map(val));
			}
			return listaValores;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	public ValorDto buscarValorModificacion(String idValor) throws NegocioExcepcion {
		try {
			ShvValValor valorModelo;
			valorModelo = valorDao.buscarValor(idValor);
			return buscarValorModificacion(valorModelo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}

	}

	public ValorDto buscarValorModificacion(ShvValValor valorModelo) throws NegocioExcepcion {
		ValorDto valorDto = new ValorDto();

		valorDto = (ValorDto) defaultMapeador.map(valorModelo);						
		
		return correccionMapeo(valorDto);
	}

	private ValorDto correccionMapeo(ValorDto valorDto) {
		if(!Validaciones.isNullOrEmpty(valorDto.getNumeroValor())){
			String numeroValor = valorDto.getNumeroValor().replace("<br>", " ");
			valorDto.setNumeroValor(numeroValor);
		}		
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaEmision())){
			valorDto.setFechaEmision(Utilidad.formatDateDigitosYears(valorDto.getFechaEmision().replace("/", "-")));	
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())){
			valorDto.setFechaIngresoRecibo(Utilidad.formatDateDigitosYears(valorDto.getFechaIngresoRecibo().replace("/", "-")));	
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())){
			valorDto.setFechaDeposito(Utilidad.formatDateDigitosYears(valorDto.getFechaDeposito().replace("/", "-")));		
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaVencimiento())){
			valorDto.setFechaVencimiento(Utilidad.formatDateDigitosYears(valorDto.getFechaVencimiento().replace("/", "-")));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaInterdeposito())){
			valorDto.setFechaInterdeposito(Utilidad.formatDateDigitosYears(valorDto.getFechaInterdeposito().replace("/", "-")));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaTransferencia())){
			valorDto.setFechaTransferencia(Utilidad.formatDateDigitosYears(valorDto.getFechaTransferencia().replace("/", "-")));	
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaAltaValor())){
			valorDto.setFechaAltaValor(Utilidad.formatDateDigitosYears(valorDto.getFechaAltaValor().replace("/", "-")));	
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaUltimoEstado())){
			valorDto.setFechaUltimoEstado(Utilidad.formatDateDigitosYears(valorDto.getFechaUltimoEstado().replace("/", "-")));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaDisponible())){
			valorDto.setFechaDisponible(Utilidad.formatDateDigitosYears(valorDto.getFechaDisponible().replace("/", "-")));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaConstancia())){
			valorDto.setFechaConstancia(Utilidad.formatDateDigitosYears(valorDto.getFechaConstancia().replace("/", "-")));	
		}
		return valorDto;
	}
	
	/**
	 * @throws PersistenciaExcepcion 
	 * @throws NumberFormatException 
	 * 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public List<String> busquedaMailBD(List<Long> idValores, String idUsuarioEnvio) throws NegocioExcepcion, NumberFormatException, PersistenciaExcepcion{
		List<String> mensajesMostrarEnvioMail = new ArrayList<String>();
		List <VistaSoporteResultadoBusquedaValor> valValorMails = new ArrayList<VistaSoporteResultadoBusquedaValor>();
		VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
		ShvBolBoletaSimplificado boletaSimplificado = new ShvBolBoletaSimplificado();
		ShvWfWorkflow workflow = new ShvWfWorkflow();
		try {
				filtro.setIdValores(idValores);
				
				valValorMails = vistaSoporteDao.buscarValores(filtro);
			
			if(Validaciones.isCollectionNotEmpty(valValorMails)){
				mensajesMostrarEnvioMail = generarBoletaMailPdf(valValorMails, idUsuarioEnvio);
			}
			
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), "Error en busquedaMailBD", e);
			
			for (VistaSoporteResultadoBusquedaValor valor : valValorMails) {
				boletaSimplificado = boletaDao.buscarBoletaSimplificado(Long.valueOf(valor.getIdBoleta()));
				boletaSimplificado.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
				boletaSimplificado.setEmailEnvioFecha(new Date());
				boletaSimplificado.setEmailEnvioUsuario(idUsuarioEnvio);
				generarDatosMailWf(boletaSimplificado,workflow);
			try {
					boletaDao.actualizarBoleta(boletaSimplificado);
				} catch (PersistenciaExcepcion e1) {
					Traza.error(getClass(), "Error en busquedaMailBD", e1);
				}
			}
			}
		return mensajesMostrarEnvioMail;
		}
		
		
	
	
	private void generarDatosMailWf(ShvBolBoletaSimplificado boletaSimplificado, ShvWfWorkflow workflow) throws NegocioExcepcion {

			double fechaHoraInicioNanoTime = System.nanoTime();
					
				String fechaEnvio = "";
				String usuarioEnvio = "";
				String mailDestino = "";
				String estadoEnvio = "";
				String usuarioModificacion = boletaSimplificado.getEmailEnvioUsuario();
				if(!Validaciones.isNullOrEmpty(Utilidad.formatDateAndTimeFull(boletaSimplificado.getEmailEnvioFecha()))) {
					fechaEnvio = Utilidad.formatDateAndTimeFull(boletaSimplificado.getEmailEnvioFecha());
				}
				UsuarioLdapDto usuarioLdapMail = null;
				try {
					if(!Validaciones.isNullOrEmpty(boletaSimplificado.getEmailEnvioUsuario())) {
						usuarioLdapMail = ldapServicio.buscarUsuarioPorUidEnMemoria(boletaSimplificado.getEmailEnvioUsuario());
						usuarioEnvio = usuarioLdapMail.getNombreCompleto();
					}
				} catch (LdapExcepcion e) {
					Traza.error(getClass(), "Error de LDAP", e);
				}
				if(!Validaciones.isNullOrEmpty(boletaSimplificado.getEmailEnvioDestino())) {
					mailDestino = boletaSimplificado.getEmailEnvioDestino();
				}
				if(!Validaciones.isObjectNull(boletaSimplificado.getEmailEnvioEstado())) {
					estadoEnvio = boletaSimplificado.getEmailEnvioEstado().toString();
				}
				String datosModificados = "[Boleta Asociada Mail]" 
										+ "(Fecha de envío: " + fechaEnvio + ")" 
										+ "(Usuario que envió el mail: " + usuarioEnvio + ")" 
										+ "(Mail destino: " + mailDestino + ")" 
										+ "(Estado del envío: " + estadoEnvio + ")";
				guardarActualizadoWf(workflow, datosModificados, usuarioModificacion);
			
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarDatosWFMail ", fechaHoraInicioNanoTime);
	}
		
	


	/**
	 * @throws PersistenciaExcepcion 
	 * @throws NumberFormatException 
	 * 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ActualizacionExitosaDto busquedaImprimirBD(List<Long> idValores, String idUsuarioImpresion) throws NegocioExcepcion, NumberFormatException, PersistenciaExcepcion{
		ActualizacionExitosaDto actualizacionDto = new ActualizacionExitosaDto();
		List <VistaSoporteResultadoBusquedaValor> listaVistaSoporteResultadoBusquedaValor = new ArrayList<VistaSoporteResultadoBusquedaValor>();
		VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
		ShvBolBoletaSimplificado baseModeloSimplificado = new ShvBolBoletaSimplificado();
		ShvWfWorkflow workflow = new ShvWfWorkflow();
		try {
			filtro.setIdValores(idValores);
			listaVistaSoporteResultadoBusquedaValor = vistaSoporteDao.buscarValores(filtro);
			
			if(Validaciones.isCollectionNotEmpty(listaVistaSoporteResultadoBusquedaValor)){
			
				actualizacionDto.getArchivosImprimirBoleta().add(generarPdfImprimirBoleta(listaVistaSoporteResultadoBusquedaValor));
				for (VistaSoporteResultadoBusquedaValor valValor : listaVistaSoporteResultadoBusquedaValor) {
					
					actualizacionDto.getNumeroBoletaArchivo().add(valValor.getNroBoleta());
					workflow = workflowDao.buscarWorkflow(Integer.valueOf(valValor.getIdWorkflow().toString()));
					baseModeloSimplificado = boletaDao.buscarBoletaSimplificado(Long.valueOf(valValor.getIdBoleta()));
					baseModeloSimplificado.setImpresionEstado(ImprimirBoletaEstadoEnum.SI);
					baseModeloSimplificado.setImpresionFecha(new Date());
					baseModeloSimplificado.setImpresionUsuario(idUsuarioImpresion);
					boletaDao.actualizarBoleta(baseModeloSimplificado);
					generarDatosImpresionWf(baseModeloSimplificado, workflow);
				
				}
			}
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), "Error en busquedaImprimirBD", e);
			
			for (VistaSoporteResultadoBusquedaValor valValor : listaVistaSoporteResultadoBusquedaValor) {
				baseModeloSimplificado = boletaDao.buscarBoletaSimplificado(Long.valueOf(valValor.getIdBoleta()));
				baseModeloSimplificado.setImpresionEstado(ImprimirBoletaEstadoEnum.ERR);
				baseModeloSimplificado.setImpresionFecha(new Date());
				baseModeloSimplificado.setImpresionUsuario(idUsuarioImpresion);
				try {
					boletaDao.actualizarBoleta(baseModeloSimplificado);
					generarDatosImpresionWf(baseModeloSimplificado, workflow);
				} catch (PersistenciaExcepcion e1) {
					Traza.error(getClass(), "Error en busquedaImprimirBD", e1);
				}
			}
			
		}
	
		return actualizacionDto;
	}
	

	/**
	 * @throws NegocioExcepcion 
	 * 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ActualizacionExitosaDto busquedaImprimirConstancia(ArrayList<ValorDto> listaDeValores) throws NegocioExcepcion{
		ActualizacionExitosaDto actualizacionDto = new ActualizacionExitosaDto();
		List <ShvValValor> modelosOrigenConstancia = new ArrayList<ShvValValor>();
		try {
			for (ValorDto valorDto : listaDeValores) {
				ShvValValor valor = (ShvValValor) valorDao.buscarValor(valorDto.getIdValor().toString());
				modelosOrigenConstancia.add(valor);
			}
			
			List <ShvValValor> modelosContanciaAsociada = new ArrayList<ShvValValor>();
			for (ShvValValor valValor : modelosOrigenConstancia) {
				ShvValConstanciaRecepcionValor constancia = valorConstanciaRecepcionServicio.buscarConstanciaRecepcionAsignadaAValor(valValor.getIdValor());
				if(!Validaciones.isObjectNull(constancia)){
					modelosContanciaAsociada = valorConstanciaRecepcionServicio.buscarValoresAsociadosAConstancia(constancia.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion().getIdConstanciaRecepcion());
					modelosContanciaAsociada = setearConstanciaAValores(modelosContanciaAsociada,constancia.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion());
					actualizacionDto.getArchivosImprimirConstancia().add(crearArchivoConstancia(modelosContanciaAsociada));
					actualizacionDto.getNumeroConstanciaArchivo().add(String.valueOf(constancia.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion().getIdConstanciaRecepcion()));
				}
			}
			
		} catch (PersistenciaExcepcion | NegocioExcepcion e) {
			Traza.error(getClass(), "Error en busquedaImprimirConstancia", e);
			throw new NegocioExcepcion(e);
		}
		return actualizacionDto;
	}

	private List<ShvValValor> setearConstanciaAValores(List<ShvValValor> modelosContanciaAsociada, ShvValConstanciaRecepcion constanciaRecepcion) {
		for (ShvValValor shvValValor : modelosContanciaAsociada) {
			shvValValor.setConstanciaRecepcion(constanciaRecepcion);
		}
		return modelosContanciaAsociada;
	}

	/**
	 * 
	 * @param shvValValor
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @throws NegocioExcepcion
	 */
	private void guardarWFActualizado(ShvValValor shvValValor, String datosModificados, String usuarioModificacion) throws NegocioExcepcion {
		ShvWfWorkflow workflowActualizados = shvValValor.getWorkFlow();
		try {
			workflowActualizados = workflowValores.actualizarWorkflow(workflowActualizados, datosModificados, usuarioModificacion);
			workflowDao.actualizarWorkflow(workflowActualizados);
		} catch (WorkflowExcepcion | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}	
	/**
	 * 
	 * @param shvValValor
	 * @param datosModificados
	 * @param usuarioModificacion
	 * @throws NegocioExcepcion
	 */
	private void guardarActualizadoWf(ShvWfWorkflow workflow, String datosModificados, String usuarioModificacion) throws NegocioExcepcion {
		
		try {
			workflow = workflowValores.actualizarWorkflow(workflow, datosModificados, usuarioModificacion);
			workflowDao.actualizarWorkflow(workflow);
		} catch (WorkflowExcepcion | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}	

	/**
	 * Retorna la lista de atributos del Dto que debe comparar para la
	 * modificacion de Boletas Con Valor
	 * 
	 * @return
	 */
	private List<String> getListaModificarBoletaConValor() {
		List<String> lista = new ArrayList<String>();
		lista.add("empresa");
		lista.add("segmento");
		lista.add("codCliente");
		lista.add("email");
		lista.add("idCopropietario");
		lista.add("importe");
		lista.add("motivo");
		lista.add("operacionAsociada");
		lista.add("origen");
		lista.add("numeroDocumentoContable");
		lista.add("acuerdo");
		lista.add("bancoDeposito");
		lista.add("nroCuenta");
		lista.add("numeroBoleta");
		lista.add("nroBoleta");
		lista.add("fechaDeposito");
		lista.add("bancoOrigen");
		lista.add("numeroCheque");
		lista.add("fechaVencimiento");
		lista.add("numeroReferencia");
		lista.add("fechaTransferencia");
		lista.add("cuit");
		lista.add("tipoImpuesto");
		lista.add("nroConstancia");
		lista.add("fechaEmision");
		lista.add("nroChequeAReemplazar");
		lista.add("observaciones");
		lista.add("reciboPreImpreso");
		lista.add("fechaIngresoRecibo");
		lista.add("cuitIbb");
		lista.add("provincia");
		lista.add("tipoComprobante");
		lista.add("letraComprobante");
		lista.add("numeroLegalComprobante");
		//SECCION COMPROBANTES
		//CHECK DOC ORIG RECIB"
		return lista;
	}

	/**
	 * Retorna la lista de atributos del Dto que debe mostrar para el alta de
	 * Boletas Con Valor
	 * 
	 * @return
	 */
	private List<String> getListaDatosOriginales() {
		List<String> lista = new ArrayList<String>();
		lista.add("empresa");
		lista.add("segmento");
		lista.add("codCliente");
		lista.add("email");
		lista.add("razonSocialClienteLegado");
		lista.add("codClienteAgrupador");
		lista.add("razonSocialClienteAgrupador");
		lista.add("numeroHolding");
		lista.add("nombreHolding");		
		lista.add("nombreAnalista");
		lista.add("mailAnalista");
		lista.add("idAnalista");
		lista.add("idSupervisor");
		lista.add("nombreSupervisor");
		lista.add("copropietario");
		lista.add("tipoValor");
		lista.add("importe");
		lista.add("motivo");
		lista.add("operacionAsociada");
		lista.add("origen");
		lista.add("acuerdo");
		lista.add("bancoDeposito");
		lista.add("numeroCuenta");
		lista.add("bancoOrigen");
		lista.add("numeroCheque");
		lista.add("fechaVencimiento");
		lista.add("nroChequeAReemplazar");
		lista.add("reciboPreImpreso");
		lista.add("fechaIngresoRecibo");
		lista.add("observaciones");		
		lista.add("fechaDeposito");		
		lista.add("observacionMail");
		lista.add("numeroDocumentoContable");
		lista.add("nroBoleta");		
		lista.add("numeroReferencia");
		lista.add("cuit");
		lista.add("cuitIbb");
		lista.add("tipoImpuesto");
		lista.add("nroConstancia");
		lista.add("provincia");
		lista.add("tipoComprobante");
		lista.add("letraComprobante");
		lista.add("numeroLegalComprobante");
		lista.add("fechaTransferencia");
		lista.add("fechaEmision");
		lista.add("estadoValor");
		lista.add("saldoDisponible");
		lista.add("constancia");
		lista.add("fechaConstancia");
		lista.add("fechaInterdeposito");
		lista.add("numeroInterdepositoCdif");
		lista.add("codOrganismo");
		lista.add("partidaContable");
		lista.add("numeroTramite");
		lista.add("fechaTramiteCMS");
		lista.add("numeroValor");
		lista.add("numeroPartidaContable");
		lista.add("idBoleta");
		lista.add("numeroBoleta");
		lista.add("fechaValorVto");
		lista.add("fechaUltimoEstado");
		lista.add("fechaValor");
		lista.add("tipoAvisoPago");
		lista.add("tipo");
		lista.add("fechaAltaValor");
		lista.add("usuarioAutorizacion");
		lista.add("motivoSuspensionDao");
		lista.add("usuarioEjecutivo");
		lista.add("usuarioAsistente");
		lista.add("fechaDisponible");
		lista.add("valorPadre");
		lista.add("facturaRelacionada");
		lista.add("documentacionOriginalRecibida");
		lista.add("archivoValoresAconciliar");
		lista.add("idValor");
		lista.add("fechaNotificacionDisponibilidadRetiroValor");
		

		return lista;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ActualizacionExitosaDto administrarModificar(ValorDto dto, UsuarioSesion user) throws NegocioExcepcion {

		ValorDto valorDto = (ValorDto) dto;
		ActualizacionExitosaDto exitoDto = new ActualizacionExitosaDto();
		boolean nuevaConstanciaNecesaria = false;

		try {
			//Verifico concurrencia
			verificarConcurrencia(valorDto.getIdValor(), Long.valueOf(valorDto.getTimeStamp()));
			
			ShvValValor valValorViejo = valorDao.buscarValor(valorDto.getIdValor().toString());
			String clienteViejo = valValorViejo.getIdClienteLegado().toString();

			this.actualizarReciboViejo(valorDto, valValorViejo);
			Integer idMotivoViejo = (valValorViejo.getMotivo() != null)?valValorViejo.getMotivo().getIdMotivo():null;
			if (!Validaciones.isObjectNull(valValorViejo)) {
		
				//Datos Modificados
				ValorDto boletaConValorDtoViejo = buscarValorModificacion(valorDto.getIdValor().toString());
				valorDto.setEmpresa(empresaDao.buscar(valorDto.getIdEmpresa()).getDescripcion());
				valorDto.setSegmento(segmentoDao.buscarSegmento(valorDto.getIdSegmento()).getDescripcion());
				valorDto = correccionMapeo(valorDto);
				if(!valorDto.getOperation().equals(Constantes.CREAR_VALOR_REGISTRO_AVC)){
					boletaConValorDtoViejo.setImporte(Utilidad.formatCurrencySacarPesos(boletaConValorDtoViejo.getImporte()));
					boletaConValorDtoViejo.setSaldoDisponible(Utilidad.formatCurrencySacarPesos(boletaConValorDtoViejo.getSaldoDisponible()));
				}
				
				if(!Validaciones.isNullOrEmpty(boletaConValorDtoViejo.getCuitIbb())){
					boletaConValorDtoViejo.setCuitIbb(Utilidad.formatearCuit(boletaConValorDtoViejo.getCuitIbb()));
				}
				
				if(!Validaciones.isNullOrEmpty(valorDto.getNuevaObservacion())){
					valorDto.setObservaciones(((boletaConValorDtoViejo.getObservaciones() != null) ? boletaConValorDtoViejo.getObservaciones().trim() + Constantes.RETORNO_WIN : "" ) + 
							((valorDto.getNuevaObservacion() != null) ? valorDto.getNuevaObservacion().trim() : ""));
				}
				
				String datosModificados = Utilidad.generarDatosModificados(boletaConValorDtoViejo, valorDto, getListaModificarBoletaConValor());
				/**
				 * @author u573005, sprint 2, correccion mapeo
				 * las fechas se muestran con dos digitos en el año en el dto esta mal
				 * ademas el nro de cuenta tambien se genera de nuevo aunque es igual
				 */
				datosModificados += Utilidad.generarDatoModificado(boletaConValorDtoViejo.getNumeroCuenta(), valorDto.getNumeroCuenta(), 
						DatosWorkflowEnum.NROCUENTA, boletaConValorDtoViejo.getNumeroCuenta(), valorDto.getNumeroCuenta());
				
				agregarComprobanteModificadoHistorial(valorDto, boletaConValorDtoViejo, datosModificados);
		
				//Determino si puedo crear nueva constancia para reimprimir la constancia
				if (nuevaConstanciaNecesaria(valValorViejo, valorDto)){
					nuevaConstanciaNecesaria = true;
				}
				
				//Boleta Sin Valor Modificado mapeada
				ShvValValor valValorNuevo = (ShvValValor) defaultMapeador.map(valorDto, valValorViejo);
				
				ShvWfWorkflow workflowActualizados = valValorNuevo.getWorkFlow();
				TipoTareaEnum tipoTarea = null;
				if (Estado.VAL_BOLETA_RECHAZADA.equals(valValorNuevo.getWorkFlow().getEstado())) {
					tipoTarea = TipoTareaEnum.REV_VALOR;
					workflowActualizados = workflowValores.solicitarReautorizacionBoleta(workflowActualizados, datosModificados, valorDto.getUsuarioModificacion());
					valValorNuevo.setFechaUltimoEstado(workflowActualizados.getFechaUltimaModificacion());
				} else {
					if (Estado.VAL_AVISO_PAGO_RECHAZADO.equals(valValorNuevo.getWorkFlow().getEstado())) {
						tipoTarea = TipoTareaEnum.REV_AVISO_PAGO;
						workflowActualizados = workflowValores.solicitarReconfirmacionAvisoDePago(workflowActualizados, datosModificados, valorDto.getUsuarioModificacion());
						valValorNuevo.setFechaUltimoEstado(workflowActualizados.getFechaUltimaModificacion());
					} else {
						workflowActualizados = workflowValores.actualizarWorkflow(workflowActualizados, datosModificados, valorDto.getUsuarioModificacion());
						valValorNuevo.setFechaUltimoEstado(workflowActualizados.getFechaUltimaModificacion());
					}
				}

				ShvBolBoleta boleta = new ShvBolBoleta();

				valValorNuevo.setWorkFlow(workflowActualizados);
				valValorNuevo.setFechaAlta(workflowActualizados.getFechaAlta());
				valValorNuevo.setFechaUltimoEstado(workflowActualizados.getFechaUltimaModificacion());
				
				switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorDto.getIdTipoValor()))) {
					case EFECTIVO:
						ShvValValorEfectivo efectivo = (ShvValValorEfectivo) valorEfectivoMapeador.map(valorDto, null);
						efectivo.setValor(valValorNuevo);
						efectivo.setIdValor(valorDto.getIdValor());
						valValorNuevo.setValValorEfectivo(efectivo);
						// U578936
						// Actualizo la fecha valor
						actualizarEstadoRecibo(null, valValorNuevo, true);
						valValorNuevo.setFechaValor(calcularFechaValor(valValorNuevo, efectivo.getFechaDeposito()));
						break;
					case CHEQUE:
						ShvValValorCheque valorCheque = (ShvValValorCheque) valorChequeMapeador.map(valorDto, null);
						valorCheque.setValor(valValorNuevo);
						valorCheque.setIdValor(valorDto.getIdValor());
						valValorNuevo.setValValorCheque(valorCheque);
						// U578936
						// Actualizo la fecha valor
						actualizarEstadoRecibo(null, valValorNuevo, true);
						valValorNuevo.setFechaValor(calcularFechaValor(valValorNuevo, valorCheque.getFechaDeposito()));
						break;
					case CHEQUE_DIFERIDO:
						ShvValValorChequePagoDiferido valorChequeDiferidoModelo = (ShvValValorChequePagoDiferido) valorChequeDiferidoMapeador.map(valorDto, null);
						valorChequeDiferidoModelo.setValor(valValorNuevo);
						valorChequeDiferidoModelo.setIdValor(valorDto.getIdValor());
						valValorNuevo.setValValorChequePD(valorChequeDiferidoModelo);

						// U562163 - IM-----
						// Actualizo la fecha valor
						// U578936
						// Actualizo la fecha valor
						actualizarEstadoRecibo(null, valValorNuevo, true);
						valValorNuevo.setFechaValor(calcularFechaValor(valValorNuevo, valorChequeDiferidoModelo.getFechaDeposito()));
						break;
					case RETENCIÓN_IMPUESTO:
						ShvValValorRetencion retencion = (ShvValValorRetencion) valorRetencionMapeador.map(valorDto, null);
						retencion.setValor(valValorNuevo);
						retencion.setIdValor(valorDto.getIdValor());
						valValorNuevo.setValValorRetencion(retencion);
						valValorNuevo.setFechaValor(retencion.getFechaRecibo());
						actualizarEstadoRecibo(null, valValorNuevo, true);
						break;
					case INTERDEPÓSITO:
						break;
					case TRANSFERENCIA:
						ShvValValorTransferencia transferencia = (ShvValValorTransferencia) valorTransferenciaMapeador.map(valorDto, null);
						transferencia.setValor(valValorNuevo);
						transferencia.setIdValor(valorDto.getIdValor());
						valValorNuevo.setValValorTransferencia(transferencia);
						break;
					case BOLETA_DEPOSITO_EFECTIVO:
						ShvValBoletaDepositoEfectivo valorBolDepEfectivoModelo = (ShvValBoletaDepositoEfectivo) valorBoletaDepositoEfectivoMapeador.map(valorDto, null);
						
						//Tengo que setear la boleta del valor viejo al valor nuevo porque se pierde en el mapeo. Mejorar
						boleta = valValorViejo.getValorBoletaEfectivo().getBoleta();
						valorBolDepEfectivoModelo.setBoleta(boleta);
						
						valorBolDepEfectivoModelo.setValor(valValorNuevo);
						valorBolDepEfectivoModelo.setIdValor(valorDto.getIdValor());
						valValorNuevo.setValorBoletaEfectivo(valorBolDepEfectivoModelo);
						actualizarEstadoRecibo(null, valValorNuevo, true);
						
						// U562163 - IM-----
						// Actualizo la fecha valor
						valValorNuevo.setFechaValor(calcularFechaValor(valValorNuevo, valorBolDepEfectivoModelo.getFechaDeposito()));
						break;
					case BOLETA_DEPOSITO_CHEQUE:
						ShvValBoletaDepositoCheque valorBolDepChequeModelo = (ShvValBoletaDepositoCheque) valorBoletaDepositoChequeMapeador.map(valorDto, null);
						
						//Tengo que setear la boleta del valor viejo al valor nuevo porque se pierde en el mapeo. Mejorar
						boleta = valValorViejo.getValorBoletaDepositoCheque().getBoleta();
						valorBolDepChequeModelo.setBoleta(boleta);
						
						valorBolDepChequeModelo.setValor(valValorNuevo);
						valorBolDepChequeModelo.setIdValor(valorDto.getIdValor());
						valValorNuevo.setValorBoletaDepositoCheque(valorBolDepChequeModelo);
						actualizarEstadoRecibo(null, valValorNuevo, true);
						
						// U562163 - IM-----
						// Actualizo la fecha valor
						valValorNuevo.setFechaValor(calcularFechaValor(valValorNuevo, valorBolDepChequeModelo.getFechaDeposito()));
						break;
					case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
						ShvValBoletaDepositoChequePagoDiferido valorBolDepChequeDiferidoModelo = (ShvValBoletaDepositoChequePagoDiferido) valorBoletaDepositoChequeDiferidoMapeador.map(valorDto, null);
						
						//Tengo que setear la boleta del valor viejo al valor nuevo porque se pierde en el mapeo. Mejorar
						boleta = valValorViejo.getValorBoletaDepositoChequePD().getBoleta();
						valorBolDepChequeDiferidoModelo.setBoleta(boleta);
						
						valorBolDepChequeDiferidoModelo.setValor(valValorNuevo);
						valorBolDepChequeDiferidoModelo.setIdValor(valorDto.getIdValor());
						valValorNuevo.setValorBoletaDepositoChequePD(valorBolDepChequeDiferidoModelo);
						actualizarEstadoRecibo(null, valValorNuevo, true);
						
						// U562163 - IM-----
						// Actualizo la fecha valors
						valValorNuevo.setFechaValor(calcularFechaValor(valValorNuevo, valorBolDepChequeDiferidoModelo.getFechaDeposito()));
						break;
					default:
						throw new NegocioExcepcion("No se encontro el tipo de Valor al mapear");
				}
				
				/**
				 * @author u573005, sprint 3, se corrige para reutilizar codigo
				 * Se setea el numero valor con el metodo correspondiente
				 */
				valValorNuevo = setearNumeroValor(valValorNuevo, valValorNuevo);
				
				valValorNuevo = valorDao.actualizarValor(valValorNuevo);
				
				// Finalizo de ser necesario la tarea del Analista
				if (tipoTarea != null) {
					
					// Preparo e-mail
					String cc = "";
					if (!Validaciones.isNullOrEmpty(valValorNuevo.getIdCopropietario())) {						
						String mailCc = mailServicio.obtenerMailUsuario(valValorNuevo.getIdCopropietario());
						if(!Validaciones.isNullOrEmpty(mailCc)){
							cc = mailCc;
						}
					}
					TeamComercialDto teamComercialDto = teamComercialServicio.buscarTeamComercial(valValorNuevo.getIdClienteLegado().toString());
					if (!Validaciones.isObjectNull(teamComercialDto) && !Validaciones.isNullOrEmpty(teamComercialDto.getUserAnalistaCobranzaDatos())) {
						String mailCc = mailServicio.obtenerMailUsuario(teamComercialDto.getUserAnalistaCobranzaDatos());
						if(!Validaciones.isNullOrEmpty(mailCc)){
							cc = ((!Validaciones.isNullOrEmpty(cc)) ? cc + ";" : "") + mailCc;
						}
					}
					/**
					 * @author Guido.Settecerze u572487, Sprint 3, Defecto #78
					 * En este punto se crear el Mail para avisar al creador del "valor" que su "valor" fue modificado.
					 */
					String subject = "";
					if (TipoTareaEnum.REV_AVISO_PAGO.equals(tipoTarea)) {
						subject = "Aviso de pago rechazado - Alta de valor solicitada por otro usuario - ";
					} else {
						subject = "Boleta rechazada - Alta de valor solicitada por otro usuario - "; 
					}
					subject += mailServicio.armarAsuntoValor(valValorNuevo);
					
					StringBuffer body = new StringBuffer();
					body.append(mailServicio.armarLineaCuerpoValor(valValorNuevo));
					if (tipoTarea.equals(TipoTareaEnum.REV_AVISO_PAGO)) {
						body.append(Constantes.SEPARADOR_PIPE + "Usuario que tomó el valor por reversión: " + user.getNombreCompleto());
					} else {
						body.append(" " + Constantes.SEPARADOR_PIPE +  " " + "Usuario que tomó la boleta: " + user.getNombreCompleto());
					}
					if(!Validaciones.isNullOrEmpty(valValorNuevo.getObservaciones()))
					body.append("<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES, valValorNuevo.getObservaciones()));

					Mail mail = new Mail(null, cc, subject, body);
					// Fin preparación e-mail
					
					tareaServicio.finalizarTarea(tipoTarea, valValorNuevo.getWorkFlow().getIdWorkflow(), user.getIdUsuario(), mail);
				}
				//Creo una tarea pendiente para el nuevo valor creado				
				crearTareaPendiente(valValorNuevo, user, true);
				
				//Contabilidad
				if (!Estado.VAL_NO_DISPONIBLE.equals(valValorViejo.getWorkFlow().getEstado())){
					contabilizarModificacion(valValorViejo, clienteViejo,idMotivoViejo, valValorNuevo);
				}
				
				//Si es nueva constancia, puedo reimprimirla.
				if(nuevaConstanciaNecesaria){
					
					//Genero la nueva constancia y retorno una lista donde el primer id es de la vieja constancia y el segundo id es de la nueva constancia.
					List<ShvValConstanciaRecepcionValor> constanciaRecepcionValor = actualizarConstancia(valValorNuevo, user);
					
					byte[] nuevaConstancia = crearArchivoConstanciaValores(constanciaRecepcionValor);
					
					exitoDto = new ActualizacionExitosaDto();
					exitoDto.getArchivosImprimirConstancia().add(nuevaConstancia);
					exitoDto.getNumeroConstanciaArchivo().add(constanciaRecepcionValor.get(0).getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion().getIdConstanciaRecepcion().toString());
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return exitoDto;
	}

	/**
	 * Permite verificar si puede crear la nueva constancia
	 * @param valValor
	 * @param valorDto
	 * @return
	 */
	private boolean nuevaConstanciaNecesaria(ShvValValor valValor, ValorDto valorDto){
		boolean respuesta = false;
		
		if(valValor.getValorBoletaEfectivo() != null || valValor.getValorBoletaDepositoChequePD() != null || valValor.getValorBoletaDepositoCheque() != null){
			String tipoOrigen = valorDto.getIdOrigen();
			if (ORIGEN_OFICINA_CONSTANCIA_AUTOMATICA_ID.equals(tipoOrigen)) {
				if(valValor.getValorBoletaEfectivo() == null){
					if(valValor.getValorBoletaDepositoCheque() != null){
						if(!valValor.getValorBoletaDepositoCheque().getNumeroCheque().toString().equals(valorDto.getNumeroCheque())){
							respuesta = true;
						}
						if(!valValor.getValorBoletaDepositoCheque().getBancoOrigen().getIdBanco().equals(valorDto.getIdBancoOrigen())){
							respuesta = true;
						}
						
					}
					if(valValor.getValorBoletaDepositoChequePD() != null){
						if(!valValor.getValorBoletaDepositoChequePD().getNumeroCheque().toString().equals(valorDto.getNumeroCheque())){
							respuesta = true;
						}
						if(!valValor.getValorBoletaDepositoChequePD().getBancoOrigen().getIdBanco().equals(valorDto.getIdBancoOrigen())){
							respuesta = true;
						}
					}
				}
				if(valValor.getImporte() != null){
					String importeValorConComa = Utilidad.formatCurrency(valValor.getImporte(), false, false); /*nnnnnn,nn*/
					String importeDtoConPunto = valorDto.getImporte(); 										   /*nnn.nnn,nn*/
					String importeDtoConComa = Utilidad.formatCurrencySacarPunto(importeDtoConPunto);		   /*nnnnnn,nn o nnnnnn*/
					if (!importeDtoConComa.contains(",")) {													   /*nnnnnn*/
						BigDecimal importeDecimal = new BigDecimal(importeDtoConComa);
						importeDtoConComa = Utilidad.formatCurrency(importeDecimal, false, false);             /*nnnnnn,nn*/
					}
					
					if(!importeValorConComa.equals(importeDtoConComa)){ 
						respuesta = true;
					}
				}
			}
		}
		return respuesta;
	}
	
	private String agregarComprobanteModificadoHistorial(ValorDto valorDtoNuevo, ValorDto valorDtoViejo, String datosModificados){
		if(valorDtoNuevo.getListaComprobantes() != null || valorDtoViejo.getListaComprobantes() != null){
			if(valorDtoNuevo.getListaComprobantes().size() > 0 || valorDtoViejo.getListaComprobantes().size() > 0){
				for (ComprobanteDto comprobanteNuevo : valorDtoNuevo.getListaComprobantes()) {
					if(!valorDtoViejo.getListaComprobantes().contains(comprobanteNuevo)){
						datosModificados = datosModificados + "[" + "Constancia" +"]("+ "Comprobante Agregado Nombre" + ": " + comprobanteNuevo.getNombreArchivo() + ")("+ "Comprobante Agregado Descripcion" + ": "+ comprobanteNuevo.getDescripcionArchivo() +")";
					}
				}
				for (ComprobanteDto comprobanteViejo : valorDtoViejo.getListaComprobantes()) {
					if(!valorDtoNuevo.getListaComprobantes().contains(comprobanteViejo)){
						datosModificados = datosModificados + "[" + "Constancia" +"]("+ "Comprobante Eliminado Nombre" + ": " + comprobanteViejo.getNombreArchivo() + ")("+ "Comprobante Eliminado Descripcion" + ": "+ comprobanteViejo.getDescripcionArchivo() +")";
					}
				}
			}
		}
		return datosModificados;
	}
	
	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
	}

	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		ValorDto valorDtoActual = (ValorDto) dto;
		
		try {			
			ValorDto valorDtoViejo = buscarValorModificacion(valorDtoActual.getIdValor().toString());
			valorDtoViejo.setImporte(Utilidad.formatCurrencySacarPesos(valorDtoViejo.getImporte()));
			if(!Validaciones.isNullOrEmpty(valorDtoViejo.getCuitIbb())){
				valorDtoViejo.setCuitIbb(Utilidad.formatearCuit(valorDtoViejo.getCuitIbb()));
			}
			
			String datosModificados = Utilidad.generarDatosModificados(valorDtoViejo,valorDtoActual,getListaModificarBoletaConValor());
			
			return datosModificados;
		
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
	}
	
	public void arreglarFechasLargas(ValorDto valorDto) throws Exception {
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaConstancia())){
			valorDto.setFechaConstancia(Utilidad.cambiarFormatoFechaDDMMAAAABarra(valorDto.getFechaConstancia()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())){
			valorDto.setFechaDeposito(Utilidad.cambiarFormatoFechaDDMMAAAABarra(valorDto.getFechaDeposito()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaEmision())){
			valorDto.setFechaEmision(Utilidad.cambiarFormatoFechaDDMMAAAABarra(valorDto.getFechaEmision()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())){
			valorDto.setFechaIngresoRecibo(Utilidad.cambiarFormatoFechaDDMMAAAABarra(valorDto.getFechaIngresoRecibo()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaInterdeposito())){
			valorDto.setFechaInterdeposito(Utilidad.cambiarFormatoFechaDDMMAAAABarra(valorDto.getFechaInterdeposito()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaTransferencia())){
			valorDto.setFechaTransferencia(Utilidad.cambiarFormatoFechaDDMMAAAABarra(valorDto.getFechaTransferencia()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaVencimiento())){
			valorDto.setFechaVencimiento(Utilidad.cambiarFormatoFechaDDMMAAAABarra(valorDto.getFechaVencimiento()));
		}
	}
	
	public void arreglarFechasCortas(ValorDto valorDto) {
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaConstancia())){
			valorDto.setFechaConstancia(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaConstancia()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())){
			valorDto.setFechaDeposito(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaDeposito()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaEmision())){
			valorDto.setFechaEmision(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaEmision()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())){
			valorDto.setFechaIngresoRecibo(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaIngresoRecibo()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaInterdeposito())){
			valorDto.setFechaInterdeposito(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaInterdeposito()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaTransferencia())){
			valorDto.setFechaTransferencia(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaTransferencia()));
		}
		if(!Validaciones.isNullOrEmpty(valorDto.getFechaVencimiento())){
			valorDto.setFechaVencimiento(Utilidad.cambiarFormatoFechaDDMMAA(valorDto.getFechaVencimiento()));
		}
	}
	
	@Transactional(readOnly=true)
	public List<ValorHistoricoDto> obtenerHistorial(ValorDto valorDto) throws NegocioExcepcion{
		List<ValorHistoricoDto> listaHistoricoDto = new ArrayList<ValorHistoricoDto>();
		try {
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			String usuarioNombreBatch = parametroServicio.getValorTexto(Constantes.USUARIO_NOMBRE_BATCH);
			
			ShvValValor valor = valorDao.buscarValor(valorDto.getIdValor().toString());
			List<ShvWfWorkflowEstadoHist> historial = valor.getWorkFlow().getListaWorkflowEstadoHistOrdenadaPorFecha();
			
		
			Iterator<ShvWfWorkflowEstadoHist> it = historial.iterator();
			//Contador para el Primer Registro
			while (it.hasNext()) {
				ShvWfWorkflowEstadoHist hist = it.next();
				ValorHistoricoDto historicoDto = new ValorHistoricoDto();
				UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(hist.getUsuarioModificacion());
				
				if (usuarioLdapAnalista!=null) {
					historicoDto.setIdUsuarioModificacion(usuarioLdapAnalista.getTuid());
					historicoDto.setUsuarioModificacion(usuarioLdapAnalista.getNombreCompleto());
					historicoDto.setMailUsuarioModificacion(usuarioLdapAnalista.getMail());
				} else {
					if (usuarioBatch.equalsIgnoreCase(hist.getUsuarioModificacion())) {
						historicoDto.setIdUsuarioModificacion(hist.getUsuarioModificacion());
						historicoDto.setUsuarioModificacion(usuarioNombreBatch);
					} else {
						historicoDto.setIdUsuarioModificacion(hist.getUsuarioModificacion());
						historicoDto.setUsuarioModificacion(hist.getUsuarioModificacion());
					}
					historicoDto.setMailUsuarioModificacion("");
				}
				
				historicoDto.setEstadoModificacion(hist.getEstado().descripcion());
				historicoDto.setFechaModificacion(Utilidad.formatDateAndTimeFullMilliseconds(hist.getFechaModificacion()));
				
				String datos = Utilidad.mostrarDatosModificados(hist.getDatosModificados());
				String auxDatos = datos.replace(Constantes.RETORNO_WIN, Constantes.CARRIAGE_RETURN_R);
				StringTokenizer token = new StringTokenizer(auxDatos, Constantes.RETORNO_UNIX);
				String tituloRegistro = "";
				String datosRegistro = "";
				while(token.hasMoreTokens()){
					String campo = token.nextToken();

					if(!campo.contains(":")){
						if("".equals(tituloRegistro)){
							tituloRegistro = campo;
						} else {
							/**U576779 - Req 67475: Defecto pre pi #6696 se pregunta si es retencion/impuesto, para que muestre fecha emision o retencion,
							**segun corresponda
							**/
							if(tituloRegistro.equals("Fecha Emisión") && valorDto.getEsRetencionImpuesto()) {
								tituloRegistro = "Fecha de Retención";
							}
							historicoDto.getCamposModificados().add(tituloRegistro);
							historicoDto.getValoresOriginales().add(datosRegistro.replace(Constantes.CARRIAGE_RETURN_R, "</br>"));
							tituloRegistro = "";
							datosRegistro = "";
							tituloRegistro = campo;
						}
					} else {
						if("".equals(datosRegistro)){
							datosRegistro += campo;
						} else {
							datosRegistro += "</br>" + campo;
						}
					}
				}
				historicoDto.getCamposModificados().add(tituloRegistro);
				historicoDto.getValoresOriginales().add(datosRegistro.replace(Constantes.CARRIAGE_RETURN_R, "</br>")+"</br>");
				listaHistoricoDto.add(historicoDto);
			}
			
			if(!Validaciones.isObjectNull(valor.getWorkFlow().getShvWfWorkflowEstado())){
				if(valor.getWorkFlow().getShvWfWorkflowEstado().iterator().hasNext()){
					ShvWfWorkflowEstado wfEstado = valor.getWorkFlow().getShvWfWorkflowEstado().iterator().next();
					String datosEstado = Utilidad.mostrarDatosModificados(wfEstado.getDatosModificados());
					ValorHistoricoDto historicoDtoEstado = new ValorHistoricoDto();
					UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUid(wfEstado.getUsuarioModificacion());
					
					if (usuarioLdapAnalista != null) {
						historicoDtoEstado.setIdUsuarioModificacion(usuarioLdapAnalista.getTuid());
						historicoDtoEstado.setUsuarioModificacion(usuarioLdapAnalista.getNombreCompleto());
						historicoDtoEstado.setMailUsuarioModificacion(usuarioLdapAnalista.getMail());
					} else {
						if (usuarioBatch.equalsIgnoreCase(wfEstado.getUsuarioModificacion())) {
							historicoDtoEstado.setIdUsuarioModificacion(wfEstado.getUsuarioModificacion());
							historicoDtoEstado.setUsuarioModificacion(usuarioNombreBatch);
						} else {
							historicoDtoEstado.setIdUsuarioModificacion(wfEstado.getUsuarioModificacion());
							historicoDtoEstado.setUsuarioModificacion(wfEstado.getUsuarioModificacion());
						}
						historicoDtoEstado.setMailUsuarioModificacion("");
					}
					historicoDtoEstado.setEstadoModificacion(wfEstado.getEstado().descripcion());
					historicoDtoEstado.setFechaModificacion(Utilidad.formatDateAndTimeFullMilliseconds(wfEstado.getFechaModificacion()));

					String auxDatosEstado = datosEstado.replace(Constantes.RETORNO_WIN, Constantes.CARRIAGE_RETURN_R);
					StringTokenizer tokenEstado = new StringTokenizer(auxDatosEstado, Constantes.RETORNO_UNIX);
					String tituloRegistro = "";
					String datosRegistro = "";
					boolean formateable = false;
					while(tokenEstado.hasMoreTokens()){
						String campo = tokenEstado.nextToken();

						if(!campo.contains(":")){
							if(campo.equals("Importe") || campo.equals("Saldo Disponible")){
								formateable = true;
							} else {
								formateable = false;
							}
							if("".equals(tituloRegistro)){
								tituloRegistro = campo;
							} else {
								historicoDtoEstado.getCamposModificados().add(tituloRegistro);
								historicoDtoEstado.getValoresOriginales().add(datosRegistro.replace(Constantes.CARRIAGE_RETURN_R, "</br>"));
								tituloRegistro = "";
								datosRegistro = "";
								tituloRegistro = campo;
							}
						} else {
							if(formateable){
								String campoFormateado = campo.replace(": ", ": $");
								campo = campoFormateado;
							}
							if("".equals(datosRegistro)){
								datosRegistro += campo;
							} else {
								datosRegistro += "</br>" + campo;
							}
						}
					}
					historicoDtoEstado.getCamposModificados().add(tituloRegistro);
					historicoDtoEstado.getValoresOriginales().add(datosRegistro.replace(Constantes.CARRIAGE_RETURN_R, "</br>")+"</br>");
					listaHistoricoDto.add(historicoDtoEstado);
				}
			}
			
		} catch (PersistenciaExcepcion | LdapExcepcion e) {
			throw new NegocioExcepcion(e); 
		}
		
		Collections.reverse(listaHistoricoDto);
		return listaHistoricoDto;
	}
	
	/**
	 * Metodo que me permite crear y modificar constancias
	 * @param valValor
	 * @param user
	 * @return
	 * @throws NegocioExcepcion
	 */
	private List<ShvValConstanciaRecepcionValor> actualizarConstancia(ShvValValor valValor, UsuarioSesion user) throws NegocioExcepcion{
		
		Date fechaConstancia = new Date();
		List<ShvValConstanciaRecepcionValor> constanciaRecepcionValor = new ArrayList<ShvValConstanciaRecepcionValor>();
		
		ShvValConstanciaRecepcion nuevaConstancia = new ShvValConstanciaRecepcion();
		nuevaConstancia.setEstado(EstadoValorConstRecepEnum.ASIGNADA);
		nuevaConstancia.setFechaCreacion(fechaConstancia);
		nuevaConstancia.setUsuaroCreacion(user.getIdUsuario());
		nuevaConstancia.setFechaAnulacion(null);
		nuevaConstancia.setUsuarioAnulacion(null);
		nuevaConstancia = valorConstanciaRecepcionServicio.actualizarConstanciaRecepcion(nuevaConstancia);
		
		ShvValConstanciaRecepcionValor constanciaRecepcionBD = valorConstanciaRecepcionServicio.buscarConstanciaRecepcionAsignadaAValor(valValor.getIdValor());
		if(!Validaciones.isObjectNull(constanciaRecepcionBD)){
			ShvValConstanciaRecepcion shvValConstanciaRecepcion = constanciaRecepcionBD.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion();
			
			// le saco la primer constancia porque siempre va a tener una sola constancia
			shvValConstanciaRecepcion.setEstado(EstadoValorConstRecepEnum.ANULADA);
			shvValConstanciaRecepcion.setFechaAnulacion(fechaConstancia);
			shvValConstanciaRecepcion.setUsuarioAnulacion(user.getIdUsuario());
			
			// Asigno la nueva constancia como padre de la vieja
			nuevaConstancia.setIdConstanciaRecepcionPadre(shvValConstanciaRecepcion);
			nuevaConstancia = valorConstanciaRecepcionServicio.actualizarConstanciaRecepcion(nuevaConstancia);
			
			// A cada valor les asigno la nueva constancia
			List<ShvValValor> listaValores = valorConstanciaRecepcionServicio.buscarValoresAsociadosAConstancia(shvValConstanciaRecepcion.getIdConstanciaRecepcion());
			for (ShvValValor valor : listaValores) {
					
				constanciaRecepcionValor.add(valorConstanciaRecepcionServicio.actualizarRelacionConstanciaValor(valor,nuevaConstancia));
				valor.setConstanciaRecepcion(nuevaConstancia);
			}
			
		} else {
			// Sin padre
			nuevaConstancia.setIdConstanciaRecepcionPadre(null);

			// Al unico valor le asigno la nueva constancia
			constanciaRecepcionValor.add(valorConstanciaRecepcionServicio.actualizarRelacionConstanciaValor(valValor,nuevaConstancia));
		}
		
		return constanciaRecepcionValor;
	}
	
	/**
	 * Retorna la lista de atributos del Dto que debe comparar para la modificacion de Boletas Sin Valor
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<String> getListaModificarBoletaSinValor() {
		List<String> lista = new ArrayList<String>();
		lista.add("empresa");
		lista.add("segmento");
		lista.add("codCliente");
		lista.add("email");
		lista.add("idCopropietario");
		lista.add("importe");
		lista.add("idMotivo");
		lista.add("operacionAsociada");
		lista.add("idOrigen");
		lista.add("numeroDocumentoContable");
		lista.add("idAcuerdo");
		lista.add("idBancoDeposito");
		lista.add("idNroCuenta");
		lista.add("nroBoleta");
		lista.add("fechaDeposito");
		lista.add("idBancoOrigen");
		lista.add("numeroCheque");
		lista.add("fechaVencimiento");
		lista.add("numeroReferencia");
		lista.add("fechaTransferencia");
		lista.add("cuit");
		lista.add("idTipoImpuesto");
		lista.add("nroConstancia");
		lista.add("fechaEmision");
		lista.add("nroChequeReemplazar");
		lista.add("observaciones");
		lista.add("observacionMail");
		lista.add("reciboPreImpreso");
		lista.add("fechaIngresoRecibo");
		lista.add("cuitIbb");
		lista.add("idProvincia");
		lista.add("idTipoComprobante");
		lista.add("idLetraComprobante");
		lista.add("numeroLegalComprobante");
		//SECCION COMPROBANTES
		//CHECK DOC ORIG RECIB"
		
		return lista;
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void confirmarAvisoDePago(DTO dto) throws NegocioExcepcion {
		ValorDto valorDto = (ValorDto) dto;
		try {
			if(!Validaciones.isNullEmptyOrDash(valorDto.getNumeroDocumentoContable())){
				//Verifico la duplicidad del valor por medio del numero de documento contable
				List<VistaSoporteResultadoBusquedaValor> listaValoresVistaSoporte;
				VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
				filtro.setNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
				Set<String> estados = new HashSet<String>();
				estados.add(Estado.VAL_DISPONIBLE.name());
				estados.add(Estado.VAL_USADO.name());
				estados.add(Estado.VAL_SUSPENDIDO.name());
				filtro.setIdEstados(estados);
				if(VALOR_PARA_TRANSFERENCIA.equals(valorDto.getIdTipoValor())){
					filtro.setAcuerdo(valorDto.getIdAcuerdo());
				}
				
				listaValoresVistaSoporte = vistaSoporteServicio.buscarValores(filtro);
				
				//Verifico que no exista otro valor con el mismo ZE
				if(Validaciones.isCollectionNotEmpty(listaValoresVistaSoporte) && !VALOR_PARA_RETENCION.equals(valorDto.getIdTipoValor())){
					String mensajeDuplicado = "No se puede proceder al alta del registro debido a la existencia de duplicidades: ";
					mensajeDuplicado += armarMensajeDuplicadoZE(listaValoresVistaSoporte.get(0));
					if(VALOR_PARA_TRANSFERENCIA.equals(valorDto.getIdTipoValor())){
						mensajeDuplicado += " y Acuerdo " + listaValoresVistaSoporte.get(0).getIdAcuerdo();
					}
					throw new ValidacionExcepcion(mensajeDuplicado);
				}
			}
			//Verifico la concurrencia
			verificarConcurrencia(valorDto.getIdValor(), Long.valueOf(valorDto.getTimeStamp()));
			
			ShvValValor valorViejo = valorDao.buscarValor(String.valueOf(valorDto.getIdValor()));
			//Verifico la concurrencia
			if (!Estado.VAL_AVISO_PAGO_PENDIENTE_CONFIRMAR.equals(valorViejo.getWorkFlow().getEstado())) {
				throw new ConcurrenciaExcepcion();
			}
			
			ShvValValor valor = valorDao.buscarValor(String.valueOf(valorDto.getIdValor()));
			
			//Datos Modificados
			ValorDto boletaConValorDtoViejo = (ValorDto) defaultMapeador.map(valorViejo);
			boletaConValorDtoViejo.setNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
			SiNoEnum valorObtenido = this.obtenerDocumentacionOriginalRecibida(valorViejo);
			boletaConValorDtoViejo.setDocumentacionOriginalRecibida(valorObtenido!=null?valorObtenido.getDescripcion():"");
			
			Date fechaEjecucion = new Date();
			valor.setFechaDisponible(fechaEjecucion);
			mapearAvisosPago(valorDto, valor, boletaConValorDtoViejo);
			
			String datosModificados = Utilidad.generarDatosModificados(boletaConValorDtoViejo, valorDto, getListaConfirmarAvisoPago());
			agregarComprobanteModificadoHistorial(valorDto, boletaConValorDtoViejo, datosModificados);
			
			//Cambio de estado
			ShvWfWorkflow workflowActualizado = workflowValores.confirmarAvisoDePago(valor.getWorkFlow(), datosModificados, valorDto.getUsuarioModificacion());
			valor.setWorkFlow(workflowActualizado);
			valor.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
			valor.setUsuarioConfirmacion(valorDto.getUsuarioModificacion());
			valor.setNombreApellidoConfirmante(obtenerNombreApellidoAnalistaLdap(valorDto.getUsuarioModificacion()));
			valor = valorDao.actualizarValor(valor);
			
			//Contabilidad
			contabilizarValor(valor, false);
			
			//Actualizo el recibo
			actualizarEstadoRecibo(null, valor, true);
			
			//Finalizo la tarea Pendiente (Confirmar Aviso del pago)
			armarYEnviarMailConfirmacionValor(valor, valorDto.getObservacionConfirmacion());
			tareaServicio.finalizarTarea(TipoTareaEnum.CONF_AVISO_PAGO, valor.getWorkFlow().getIdWorkflow(), fechaEjecucion, valorDto.getUsuarioModificacion(), null);
			
		} catch (NumberFormatException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(String.valueOf(valorDto.getIdValor()),e);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(String.valueOf(valorDto.getIdValor()),e);
		}
	}
	
	private String obtenerNombreApellidoAnalistaLdap(String idAnalista) throws NegocioExcepcion {
		
		try {
		UsuarioLdapDto usuarioLdapAnalista;
		if(!Validaciones.isNullOrEmpty(idAnalista)){
				usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(idAnalista);
			if(!Validaciones.isNullOrEmpty(usuarioLdapAnalista.getNombreCompleto())){
				return usuarioLdapAnalista.getNombreCompleto();
			}
		}
		} catch (LdapExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return null;
	}

	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void rechazarAvisoDePago(DTO dto, UsuarioSesion usuarioSesion) throws NegocioExcepcion {
		ValorDto valorDto = (ValorDto) dto;
		try {
			//Verifico la concurrencia
			verificarConcurrencia(valorDto.getIdValor(), Long.valueOf(valorDto.getTimeStamp()));
			
			ShvValValor valorViejo = valorDao.buscarValor(String.valueOf(valorDto.getIdValor()));
			//Verifico la concurrencia
			if (!Estado.VAL_AVISO_PAGO_PENDIENTE_CONFIRMAR.equals(valorViejo.getWorkFlow().getEstado())) {
				throw new ConcurrenciaExcepcion();
			}
			
			ShvValValor valor = valorDao.buscarValor(String.valueOf(valorDto.getIdValor()));
			
			//Datos Modificados
			ValorDto boletaConValorDtoViejo = (ValorDto) defaultMapeador.map(valorViejo);
			boletaConValorDtoViejo.setNumeroDocumentoContable(valorDto.getNumeroDocumentoContable());
			SiNoEnum valorObtenido = this.obtenerDocumentacionOriginalRecibida(valorViejo);
			boletaConValorDtoViejo.setDocumentacionOriginalRecibida(valorObtenido!=null?valorObtenido.getDescripcion():"");
			
			mapearAvisosPago(valorDto, valor, boletaConValorDtoViejo);
			
			String datosModificados = Utilidad.generarDatosModificados(boletaConValorDtoViejo, valorDto, getListaConfirmarAvisoPago());
			agregarComprobanteModificadoHistorial(valorDto, boletaConValorDtoViejo, datosModificados);
			
			//Cambio de estado
			ShvWfWorkflow workflowActualizado = workflowValores.rechazarConfirmacionAvisoDePago(valor.getWorkFlow(), datosModificados, valorDto.getUsuarioModificacion());
			valor.setWorkFlow(workflowActualizado);
			valor.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
			
			valorDao.actualizarValor(valor);
			
			// Preparo e-mail
			String to = mailServicio.obtenerMailUsuario(valor.getIdAnalista());
			String cc = "";
			if (!Validaciones.isNullOrEmpty(valor.getIdCopropietario())) {
				String mailCc = mailServicio.obtenerMailUsuario(valor.getIdCopropietario());
				if(!Validaciones.isNullOrEmpty(mailCc)){
					cc = mailCc;
				}
			}
			TeamComercialDto teamComercialDto = teamComercialServicio.buscarTeamComercial(valor.getIdClienteLegado().toString());
			if (!Validaciones.isObjectNull(teamComercialDto) && !Validaciones.isNullOrEmpty(teamComercialDto.getUserAnalistaCobranzaDatos())) {
				String mailCc = mailServicio.obtenerMailUsuario(teamComercialDto.getUserAnalistaCobranzaDatos());
				if(!Validaciones.isNullOrEmpty(mailCc)){
					cc = ((!Validaciones.isNullOrEmpty(cc)) ? cc + ";" + mailCc : "");
				}
			}
			
			/**
			 * @author u573005, sprint3, correccion defecto 53
			 */
			String subject = "Aviso de pago rechazado - " + mailServicio.armarAsuntoValor(valor);
			
			StringBuffer body = new StringBuffer();
			body.append(mailServicio.armarLineaCuerpoValor(valor));			
			body.append(" " +  Constantes.SEPARADOR_PIPE  + " ");
			body.append("Usuario responsable del rechazo: " + usuarioSesion.getNombreCompleto());
			if (!Validaciones.isNullOrEmpty(valorDto.getObservacionConfirmacion())) {
				body.append("<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES, valorDto.getObservacionConfirmacion()));
			}
			
			Mail mail = new Mail();
			mail.setDestinatarioPara(to);
			mail.setDestinatarioCC(cc);
			mail.setAsunto(subject);
			mail.setCuerpo(body);
			
			// Fin preparación e-mail
			
			// Creo la tarea para el analista
			this.crearTareaPendiente(valor, usuarioSesion,false);
			
			//Finalizo la tarea Pendiente (Confirmar Aviso del pago)
			Date fechaEjecucion = new Date();
			tareaServicio.finalizarTarea(TipoTareaEnum.CONF_AVISO_PAGO, valor.getWorkFlow().getIdWorkflow(), 
					fechaEjecucion, valorDto.getUsuarioModificacion(), null);
			
			// Envio del correo
			mailServicio.sendMail(mail);
			
		} catch (NumberFormatException | PersistenciaExcepcion | ShivaExcepcion e) {
			throw new NegocioExcepcion(String.valueOf(valorDto.getIdValor()),e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void anularAvisoDePago(DTO dto) throws NegocioExcepcion {
		ValorDto valorDto = (ValorDto) dto;
		try {
			ShvValValor valor = valorDao.buscarValor(String.valueOf(valorDto.getIdValor()));
			ShvWfWorkflow workflowActualizado = workflowValores.anularAvisoDePagoRechazado(valor.getWorkFlow(), "", valorDto.getUsuarioModificacion());
			valor.setWorkFlow(workflowActualizado);
			valor.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
			valorDao.actualizarValor(valor);
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_AVISO_PAGO, valor.getWorkFlow().getIdWorkflow(), new Date(), valorDto.getUsuarioModificacion(), null);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(String.valueOf(valorDto.getIdValor()), e); 
		}
			
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void anularBoleta(DTO dto) throws NegocioExcepcion {
		ValorDto valorDto = (ValorDto) dto;
		try {
			ShvValValor valor = valorDao.buscarValor(String.valueOf(valorDto.getIdValor()));
			ShvWfWorkflow workflowActualizado = workflowValores.anularBoletaRechazada(valor.getWorkFlow(), "", valorDto.getUsuarioModificacion());
			valor.setWorkFlow(workflowActualizado);
			valor.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
			valorDao.actualizarValor(valor);
			
			ShvBolBoleta boleta = valor.getBoleta();
			ShvWfWorkflow wf = valor.getBoleta().getWorkFlow();
						
			if (Estado.BOL_PENDIENTE.equals(wf.getEstado())) {
				verificarConcurrenciaBoletaConValor(boleta.getIdBoleta(), Long.valueOf(wf.getFechaUltimaModificacion().getTime()));
				ShvWfWorkflow workflowActualizadoBoleta = workflowBoletas.solicitarBoletaAnulacion(wf, "", valorDto.getUsuarioModificacion());
				boleta.setWorkFlow(workflowActualizadoBoleta);
				boletaDao.actualizarBoleta(boleta);
			}
			
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_VALOR, valor.getWorkFlow().getIdWorkflow(), new Date(), valorDto.getUsuarioModificacion(), null);
			//tareaServicio.eliminarTarea(TipoTareaEnum.REV_VALOR, valor.getWorkFlow().getIdWorkflow(), valorDto.getUsuarioModificacion());
		
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(String.valueOf(valorDto.getIdValor()), e); 
		}
	}

	private List<String> getListaConfirmarAvisoPago() {
		List<String> lista = new ArrayList<String>();
		lista.add("numeroDocumentoContable");
		lista.add("documentacionOriginalRecibida");
		lista.add("observacionConfirmacion");
		
		return lista;
	}
	
	/**
	 * Me permite mapear algunos campos
	 * @param valorDto
	 * @param valValorNuevo
	 * @return
	 * @throws NegocioExcepcion
	 */
	private void mapearAvisosPago(ValorDto valorDto, ShvValValor valValorNuevo, ValorDto boletaConValorDtoViejo) throws NegocioExcepcion {
		
		 if (!Validaciones.isNullOrEmpty(valorDto.getNumeroDocumentoContable())) {
			valValorNuevo.setNumeroDocumentoContable(Long.valueOf(valorDto.getNumeroDocumentoContable()));
		 }		 
		 SiNoEnum valorDocumentacionOriginalRecibida = null;
		 
		 if(valorDto.getDocumentacionOriginalRecibidaBool() == null){
			 valorDocumentacionOriginalRecibida = SiNoEnum.NO;
			valorDto.setDocumentacionOriginalRecibida(valorDocumentacionOriginalRecibida.getDescripcion());
		 }else{
			 valorDocumentacionOriginalRecibida = SiNoEnum.SI;
			 valorDto.setDocumentacionOriginalRecibida(valorDocumentacionOriginalRecibida.getDescripcion());
		 }
		 
		 switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorDto.getIdTipoValor()))) {
			case EFECTIVO:
				valValorNuevo.getValValorEfectivo().setDocumentacionOriginalRecibida(valorDocumentacionOriginalRecibida);
				break;
			case CHEQUE:
				valValorNuevo.getValValorCheque().setDocumentacionOriginalRecibida(valorDocumentacionOriginalRecibida);
				break;
			case CHEQUE_DIFERIDO:
				valValorNuevo.getValValorChequePD().setDocumentacionOriginalRecibida(valorDocumentacionOriginalRecibida);
				break;
			case TRANSFERENCIA:
				valValorNuevo.getValValorTransferencia().setDocumentacionOriginalRecibida(valorDocumentacionOriginalRecibida);
				break;
			default:
				break;
		}
		
		if(Validaciones.isCollectionNotEmpty(valorDto.getListaComprobantes())){
			List<ShvDocDocumentoAdjunto> documentos = new ArrayList<ShvDocDocumentoAdjunto>();
			for (ComprobanteDto comprobante : valorDto.getListaComprobantes()) {
				ShvDocDocumentoAdjunto docAdjunto = new ShvDocDocumentoAdjunto();
				docAdjunto.setArchivoAdjunto(comprobante.getDocumento());
				docAdjunto.setDescripcion(comprobante.getDescripcionArchivo());
				docAdjunto.setFechaCreacion(new Date());
				docAdjunto.setNombreArchivo(comprobante.getNombreArchivo());
				docAdjunto.setUsuarioCreacion(valorDto.getIdAnalista());
				
				documentos.add(docAdjunto);
			}
			for (ShvDocDocumentoAdjunto shvValDocDocumentoAdjunto : documentos) {
				shvValDocDocumentoAdjunto.setIdValor(new ArrayList<ShvValValor>());
				shvValDocDocumentoAdjunto.getIdValor().add(valValorNuevo);
			}
			valValorNuevo.setValorDocAdjunto(documentos);
		}
	}
	
	/**
	 * Me permite retornar documentacionOriginalRecibida
	 * @param valor
	 * @return
	 */
	private SiNoEnum obtenerDocumentacionOriginalRecibida(ShvValValor valor) {
		switch (TipoValorEnum.getEnumByIdTipoValor(valor.getTipoValor().getIdTipoValor())) {
			case EFECTIVO:
				return valor.getValValorEfectivo().getDocumentacionOriginalRecibida();
			case CHEQUE:
				return valor.getValValorCheque().getDocumentacionOriginalRecibida();
			case CHEQUE_DIFERIDO:
				return valor.getValValorChequePD().getDocumentacionOriginalRecibida();
			case TRANSFERENCIA:
				return valor.getValValorTransferencia().getDocumentacionOriginalRecibida();
			default:
				return null;
		}
	}
	
	/**
	 * Generación y envío de mail para la confirmación de Avisos de Pago. 
	 * 
	 * @param ShvValValor shvValValor, String observaciones, String empresasAsociadas
	 * @author u591368 F.N. Quispe
	 * @throws NegocioExcepcion
	 */
	public void armarYEnviarMailConfirmacionValor(ShvValValor shvValValor, String observaciones) throws NegocioExcepcion {
		
		StringBuffer asunto = mailServicio.armarAsuntoValorStringBuffer(shvValValor);
		StringBuffer cuerpo = mailServicio.armarCuerpoValorStringBuffer(shvValValor);
		
		if (!Validaciones.isNullOrEmpty(observaciones)) {
			cuerpo.append(Constantes.RETORNO_HTML);
			cuerpo.append(Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES, observaciones));
			
		}
		
		String usuarioAnalistaCobranzaDatos = Constantes.EMPTY_STRING;
		try {
			TeamComercialDto teamComercial = teamComercialServicio.buscarTeamComercial(String.valueOf(shvValValor.getIdClienteLegado()));
			if (!Validaciones.isObjectNull(teamComercial) && !Validaciones.isNullOrEmpty(teamComercial.getUserAnalistaCobranzaDatos())) {
				usuarioAnalistaCobranzaDatos = teamComercial.getUserAnalistaCobranzaDatos();
			}
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e);
		}
		this.enviarMailValor(shvValValor.getIdAnalista(), shvValValor.getIdCopropietario(), usuarioAnalistaCobranzaDatos, cuerpo.toString(), asunto.toString());
	}
	
	
	/**
	 * Sprint3, se utiliza este metodo tanto para valores
	 * como para registros avc
	 */
	public void mailValorConciliacion(ShvValValor shvValValor, HashMap<String, List<String>> cuerpoMailMap) throws NegocioExcepcion{
		try {
			String cuerpo = mailServicio.armarCuerpoValorStringBuffer(shvValValor).toString();
			
			if(!Validaciones.isObjectNull(shvValValor.getOperacionAsociada())){

				cuerpo += " " + Constantes.SEPARADOR_PIPE + " Operacion Asociada: " + shvValValor.getOperacionAsociada();

				}

			TeamComercialDto teamComercial = teamComercialServicio.buscarTeamComercial(String.valueOf(shvValValor.getIdClienteLegado()));
			
			String analista = "";
			if(!Validaciones.isObjectNull(shvValValor)){
				analista = shvValValor.getIdAnalista();
			}
			String copropietario = "";
			if(!Validaciones.isObjectNull(shvValValor)){
				copropietario = shvValValor.getIdCopropietario();
			}
			String analistaTeamComercial = "";
			if(!Validaciones.isObjectNull(teamComercial)){
				analistaTeamComercial = teamComercial.getUserAnalistaCobranzaDatos();
			}
			String key = mailServicio.formarKeyAgrupador(analista, copropietario, analistaTeamComercial);
			
			if(cuerpoMailMap.containsKey(key)){
				cuerpoMailMap.get(key).add(cuerpo);
			} else {
				ArrayList<String> nuevaLista = new ArrayList<String>();
				nuevaLista.add(cuerpo);
				cuerpoMailMap.put(key,nuevaLista);
			}
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/***************************************************************************
	 * MANEJO DE ARCHIVOS
	 * ************************************************************************/
	public byte[] crearArchivoConstancia(List<ShvValValor> listaValValor) throws NegocioExcepcion {
		
		Document documento = new Document();
		ByteArrayOutputStream baos = null;
		try {
			GeneradorConstanciaRecepcionValores generador = new GeneradorConstanciaRecepcionValores(listaValValor);
			 
			baos = new ByteArrayOutputStream();
			@SuppressWarnings("unused")
			PdfWriter pw = PdfWriter.getInstance(documento, baos);
			documento.open();
			
			documento = generador.crearArchivoConstanciaRecepcionValores(documento);
			documento.close();
			
		} catch (DocumentException e) {
			Traza.error(getClass(), "Error al crearArchivoConstancia", e);
		} catch (IOException e) {
			Traza.error(getClass(), "Error al crearArchivoConstancia", e);
		}
		return baos.toByteArray();
	}
	
	/**
	 * Imprime la constancia en PDF
	 * @param valValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	public byte[] crearArchivoConstancia(ShvValValor valValor) throws NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		Document documento = new Document();
		ByteArrayOutputStream baos = null;
		try {
			GeneradorConstanciaRecepcionValores generador = new GeneradorConstanciaRecepcionValores(valValor);
			 
			baos = new ByteArrayOutputStream();
			@SuppressWarnings("unused")
			PdfWriter pw = PdfWriter.getInstance(documento, baos);
			documento.open();
			
			documento = generador.crearArchivoConstanciaRecepcionValores(documento);
			documento.close();
			
		} catch (DocumentException e) {
			Traza.error(getClass(), "Error al crearArchivoConstancia (1)", e);
		} catch (IOException e) {
			Traza.error(getClass(), "Error al crearArchivoConstancia (1)", e);
		}
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo crearArchivoConstancia ", fechaHoraInicioNanoTime);
		return baos.toByteArray();
	}
	
	/**
	 * Imprime la constancia en PDF
	 * @param valValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	public byte[] crearArchivoConstanciaValores(List<ShvValConstanciaRecepcionValor> constanciaRecepcionValor) throws NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		Document documento = new Document();
		ByteArrayOutputStream baos = null;
		try {
			GeneradorConstanciaRecepcionValores generador = new GeneradorConstanciaRecepcionValores(valorConstanciaRecepcionServicio.retornarListaValores(constanciaRecepcionValor));
			 
			baos = new ByteArrayOutputStream();
			@SuppressWarnings("unused")
			PdfWriter pw = PdfWriter.getInstance(documento, baos);
			documento.open();
			
			documento = generador.crearArchivoConstanciaRecepcionValores(documento,constanciaRecepcionValor);
			documento.close();
			
		} catch (DocumentException e) {
			Traza.error(getClass(), "Error al crearArchivoConstancia (1)", e);
		} catch (IOException e) {
			Traza.error(getClass(), "Error al crearArchivoConstancia (1)", e);
		}
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo crearArchivoConstanciaValores ", fechaHoraInicioNanoTime);
		return baos.toByteArray();
	}
	
	public Document unificarArchivoImpresion(List<Document> listaDocumentos) throws NegocioExcepcion {
		
		Document documentoUnico = new Document();
		for (int i = 0; i < listaDocumentos.size(); i++) {
			documentoUnico.newPage();
		}
		return null;
	}
	
	
	public void mailDocumento(byte[] documento, ShvValValor shvValValor, String idUsuarioEnvio) throws NegocioExcepcion {

		ShvBolBoleta bolBoleta = null;		
		bolBoleta = shvValValor.getBoleta();

		bolBoleta.setEmailEnvioUsuario(idUsuarioEnvio);
		
		if(Validaciones.isNullOrEmpty(shvValValor.getEmail())){
			bolBoleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			shvValValor.setBoleta(bolBoleta);
			throw new CampoMailExcepcion(Mensajes.MAIL_ERROR_DESTINATARIO_PARA);
		}
		
		StringBuffer cuerpo = new StringBuffer("");
		StringBuffer cuerpoParaGuardarEnBD = new StringBuffer("");
		if (!EnviarMailBoletaEstadoEnum.NO.equals(bolBoleta.getEmailEnvioEstado()) 
				&& !Validaciones.isNullOrEmpty(bolBoleta.getEmailEnvioObservaciones())) {
			cuerpo.append(bolBoleta.getEmailEnvioObservaciones());
			cuerpoParaGuardarEnBD.append(bolBoleta.getEmailEnvioObservaciones());
		} else {
			if (!Validaciones.isNullOrEmpty(bolBoleta.getEmailObservaciones())) {
				cuerpo.append(bolBoleta.getEmailObservaciones());
				cuerpoParaGuardarEnBD.append(bolBoleta.getEmailObservaciones());
			}
		}
		
		String asunto = shvValValor.getEmpresa().getDescripcion() + " - " + Mensajes.MAIL_ASUNTO + String.valueOf(bolBoleta.getNumeroBoleta()
				+ " - Cliente: " + shvValValor.getIdClienteLegado() + " - " + shvValValor.getRazonSocialClientePerfil() );
		
		String cc = "";
		UsuarioLdapDto usuarioLdapAnalista;
		if(!Validaciones.isNullOrEmpty(shvValValor.getIdAnalista())){
			usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(shvValValor.getIdAnalista());
			if(!Validaciones.isNullOrEmpty(usuarioLdapAnalista.getMail())){
				cc = usuarioLdapAnalista.getMail() + ";";
			}
		}
		UsuarioLdapDto usuarioLdapCopropietario;
		if(!Validaciones.isNullOrEmpty(shvValValor.getIdCopropietario())){
			usuarioLdapCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(shvValValor.getIdCopropietario());
			if(!Validaciones.isNullOrEmpty(usuarioLdapCopropietario.getMail())){
				cc += usuarioLdapCopropietario.getMail() + ";";
			}
		}

		UsuarioLdapDto usuarioLdapTeamComercial = null;
		TeamComercialDto teamComercial = teamComercialServicio.buscarTeamComercial(String.valueOf(shvValValor.getIdClienteLegado()));
		if (!Validaciones.isObjectNull(teamComercial) && !Validaciones.isNullOrEmpty(teamComercial.getUserAnalistaCobranzaDatos())) {
			try {
				usuarioLdapTeamComercial = ldapServicio.buscarUsuarioPorUid(teamComercial.getUserAnalistaCobranzaDatos());
			} catch (LdapExcepcion e) {
				Traza.error(getClass(), "Se ha producido error en el servicio de LDAP", e);
			}
			if(!Validaciones.isNullOrEmpty(String.valueOf(usuarioLdapTeamComercial)) && !Validaciones.isNullOrEmpty(usuarioLdapTeamComercial.getMail())  ){
				cc += usuarioLdapTeamComercial.getMail() + ";";
			}
		}	
		
		Mail mail = new Mail(shvValValor.getEmail(), asunto, cuerpo);
		List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
		listaAdjuntos.add(new Adjunto(documento, asunto, EXTENSION_ARCHIVO_ADJUNTO));
		mail.setAdjuntos(listaAdjuntos);
		mail.setDestinatarioCC(cc);
		bolBoleta.setEmail(shvValValor.getEmail());
		bolBoleta.setEmailEnvioFecha(new Date());
		bolBoleta.setEmailEnvioDestino(shvValValor.getEmail());
		if(!Validaciones.isNullOrEmpty(cuerpoParaGuardarEnBD.toString())){
			bolBoleta.setEmailEnvioObservaciones(cuerpoParaGuardarEnBD.toString());
		}

		try {
			mailServicio.enviarMail(mail);
			bolBoleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.SI);
		} catch (MailExcepcion e) {
			bolBoleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			throw new MailExcepcion(Mensajes.MAIL_ERROR);
		} finally {
			shvValValor.setBoleta(bolBoleta);
		}

	}

	//* Se duplica el metodo ya que se cambia como parametro ShvValValor por VistaSoporteResultadoBusquedaValor
	public void mailDocumento(byte[] documento, VistaSoporteResultadoBusquedaValor valor, String idUsuarioEnvio, ShvBolBoletaSimplificado bolBoleta) throws NegocioExcepcion {
		

		bolBoleta.setEmailEnvioUsuario(idUsuarioEnvio);
		
		if(Validaciones.isNullOrEmpty(valor.getEmail())){
			bolBoleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			throw new CampoMailExcepcion(Mensajes.MAIL_ERROR_DESTINATARIO_PARA);
		}
		
		StringBuffer cuerpo = new StringBuffer("");
		StringBuffer cuerpoParaGuardarEnBD = new StringBuffer("");
		if (!EnviarMailBoletaEstadoEnum.NO.equals(bolBoleta.getEmailEnvioEstado()) 
				&& !Validaciones.isNullOrEmpty(bolBoleta.getEmailEnvioObservaciones())) {
			cuerpo.append(bolBoleta.getEmailEnvioObservaciones());
			cuerpoParaGuardarEnBD.append(bolBoleta.getEmailEnvioObservaciones());
		} else {
			if (!Validaciones.isNullOrEmpty(bolBoleta.getEmailObservaciones())) {
				cuerpo.append(bolBoleta.getEmailObservaciones());
				cuerpoParaGuardarEnBD.append(bolBoleta.getEmailObservaciones());
			}
		}
		
		String asunto = valor.getEmpresa() + " - " + Mensajes.MAIL_ASUNTO + String.valueOf(bolBoleta.getNumeroBoleta()
				+ " - Cliente: " + valor.getIdClienteLegado() + " - " + valor.getRazonSocialClienteAgrupador());
		
		String cc = "";
		UsuarioLdapDto usuarioLdapAnalista;
		if(!Validaciones.isNullOrEmpty(valor.getIdAnalista())){
			usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(valor.getIdAnalista());
			if(!Validaciones.isNullOrEmpty(usuarioLdapAnalista.getMail())){
				cc = usuarioLdapAnalista.getMail() + ";";
			}
		}
		UsuarioLdapDto usuarioLdapCopropietario;
		if(!Validaciones.isNullOrEmpty(valor.getIdCopropietario())){
			usuarioLdapCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(valor.getIdCopropietario());
			if(!Validaciones.isNullOrEmpty(usuarioLdapCopropietario.getMail())){
				cc += usuarioLdapCopropietario.getMail() + ";";
			}
		}

		UsuarioLdapDto usuarioLdapTeamComercial = null;
		TeamComercialDto teamComercial = teamComercialServicio.buscarTeamComercial(String.valueOf(valor.getIdClienteLegado()));
		if (!Validaciones.isObjectNull(teamComercial) && !Validaciones.isNullOrEmpty(teamComercial.getUserAnalistaCobranzaDatos())) {
			try {
				usuarioLdapTeamComercial = ldapServicio.buscarUsuarioPorUid(teamComercial.getUserAnalistaCobranzaDatos());
			} catch (LdapExcepcion e) {
				Traza.error(getClass(), "Se ha producido error en el servicio de LDAP", e);
			}
			if(!Validaciones.isNullOrEmpty(String.valueOf(usuarioLdapTeamComercial)) && !Validaciones.isNullOrEmpty(usuarioLdapTeamComercial.getMail())  ){
				cc += usuarioLdapTeamComercial.getMail() + ";";
			}
		}	
		
		Mail mail = new Mail(valor.getEmail(), asunto, cuerpo);
		List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
		listaAdjuntos.add(new Adjunto(documento, asunto, EXTENSION_ARCHIVO_ADJUNTO));
		mail.setAdjuntos(listaAdjuntos);
		mail.setDestinatarioCC(cc);
		bolBoleta.setEmail(valor.getEmail());
		bolBoleta.setEmailEnvioFecha(new Date());
		bolBoleta.setEmailEnvioDestino(valor.getEmail());
		if(!Validaciones.isNullOrEmpty(cuerpoParaGuardarEnBD.toString())){
			bolBoleta.setEmailEnvioObservaciones(cuerpoParaGuardarEnBD.toString());
		}

		try {
			mailServicio.enviarMail(mail);
			bolBoleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.SI);
		} catch (MailExcepcion e) {
			bolBoleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			throw new MailExcepcion(Mensajes.MAIL_ERROR);
		} 

	}
	
	
	public List<byte[]> imprimirDocumentos(List<Document> listaDocumentos) throws NegocioExcepcion {
		List<byte[]> listaByteArray = new ArrayList<byte[]>();
		for (int i = 0; i < listaDocumentos.size(); i++) {
			Document documento = listaDocumentos.get(i);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				@SuppressWarnings("unused")
				PdfWriter pw = PdfWriter.getInstance(documento, baos);
				documento.open();
			} catch (DocumentException e) {
				Traza.error(getClass(), "Error al imprimirDocumentos", e);
			}
			listaByteArray.add(baos.toByteArray().clone());
		}
		return listaByteArray;
	}

	/**
	 * Genero de la siguiente manera Por ejemplo:
	 * "[1401097870|1401022360|1401202724|1401227300]"
	 */
	@Override
	public String listar10CodigosClienteLegadoUsuario(String usuarioLogueado) throws NegocioExcepcion {
		String cadena = "";
		try {
			List<ShvValValorSimple> lista = valorDao.listarCodigosClienteLegadoUser(usuarioLogueado);
			cadena = formato10CodigosClienteLegado(cadena, lista);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return cadena;
	}
	@Override
	public String listar10CodigosClienteLegadoBoleta(String usuarioLogueado) throws NegocioExcepcion {
		String cadena = "";
		try {
			List<ShvValValorSimple> lista = valorDao.listarCodigosClienteLegadoBoleta(usuarioLogueado);
			cadena = formato10CodigosClienteLegado(cadena, lista);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return cadena;
	}
	@Override
	public String listar10CodigosClienteLegadoAviso(String usuarioLogueado) throws NegocioExcepcion {
		String cadena = "";
		try {
			List<ShvValValorSimple> lista = valorDao.listarCodigosClienteLegadoAviso(usuarioLogueado);
			cadena = formato10CodigosClienteLegado(cadena, lista);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return cadena;
	}
	/**
	 * @param cadena
	 * @param lista
	 * @return
	 */
	private String formato10CodigosClienteLegado(String cadena, List<ShvValValorSimple> lista) {
		Map<String, String> unsortMap = new HashMap<String, String>();
		for (ShvValValorSimple val : lista) {
			if (unsortMap.size() >= 10) {
				break;
			} else {
				unsortMap.put(val.getIdClienteLegado().toString(), val.getCodigoClienteSiebelFormula());
			}
		}
		Map<String, String> listaOrdenada = new TreeMap<String, String>(unsortMap);
		for (Map.Entry<String, String> entry : listaOrdenada.entrySet()) {
			cadena += entry.getValue() + Constantes.SEPARADOR_PIPE;
		}
		// Saco la ultima ","
		if (!Validaciones.isNullOrEmpty(cadena)) {
			cadena = cadena.substring(0, cadena.length() - 1);
		}
		return cadena;
	}
	
	private void prepararMail(Map<String,List<String>> mapMail,ShvValValor valorModelo,StringBuffer usuarios, String observaciones,String cadenaExtra){
		double fechaHoraInicioNanoTime = System.nanoTime();
		String key = "";
		StringBuffer cuerpo = new StringBuffer();
		String informacionAdicional = mailServicio.armarLineaCuerpoValor(valorModelo);
		if(!Validaciones.isNullOrEmpty(cadenaExtra)){
			cuerpo.append(informacionAdicional+cadenaExtra);
		}else{
			cuerpo.append(informacionAdicional);
		}
		if (!Validaciones.isNullOrEmpty(observaciones)){
			cuerpo.append("<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES, observaciones));
		}
		try {
			if(usuarios == null){
				String perfilAsignacion = "";
				if (Estado.VAL_AVISO_PAGO_PENDIENTE_CONFIRMAR.equals(valorModelo.getWorkFlow().getEstado())) {
					perfilAsignacion = TipoPerfilEnum.ADMIN_VALOR.descripcion(); 
				} else {
					perfilAsignacion = TipoPerfilEnum.SUPERVISOR.descripcion()+"_"+valorModelo.getEmpresa().getIdEmpresa()+"_"+valorModelo.getSegmento().getIdSegmento(); 
				}
				Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorPerfilEnMemoria(perfilAsignacion);
				usuarios = new StringBuffer("");
				for (UsuarioLdapDto usuario: usuariosLdap) {
					if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
						usuarios.append(usuario.getMail()+";");  
					}
				}
				usuarios.setLength(Math.max(usuarios.length() - 1, 0));
			}
			key = usuarios.toString();
			if (mapMail.containsKey(key)){
				mapMail.get(key).add(cuerpo.toString());
			} else {
				ArrayList<String> nuevaLista = new ArrayList<String>();
				nuevaLista.add(cuerpo.toString());
				mapMail.put(key, nuevaLista);
			}
		} catch (LdapExcepcion e) {
			Traza.advertencia(getClass(), "Se ha producido un error del servicio LDAP y no se pudo enviar el correo" , e);
		}
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo prepararMail ", fechaHoraInicioNanoTime);
	}
	
	/**
	 * Metodo para dar de alta una boleta con valor o un aviso de pago.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ActualizacionExitosaDto administrarValores(ValoresDto valoresDTO, UsuarioSesion userSesion) throws PersistenciaExcepcion, NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		
		ActualizacionExitosaDto exitosaDto = new ActualizacionExitosaDto();
		List<Long> numerosBoleta = new ArrayList<Long>();
		List<String> mensajesBoleta = new ArrayList<String>();
		List<ShvValValor> modelosMail = new ArrayList<ShvValValor>();
		List<ShvValValor> modelosBoleta = new ArrayList<ShvValValor>();
		List<ShvValValor> modelosContancia = new ArrayList<ShvValValor>();
		List<ShvValConstanciaRecepcionValor> modelosContanciaValor = new ArrayList<ShvValConstanciaRecepcionValor>();
		List<String> numeroConstanciaArchivo = new ArrayList<String>();
		List<String> numeroBoletaArchivo = new ArrayList<String>();
		HashMap<String,List<List<ShvValValor>>> listaValoresRecibo = new HashMap<String,List<List<ShvValValor>>>();
		HashMap<String,List<String>> mapMail = new HashMap<String,List<String>>();
		
		for (ValorDto valorDto : valoresDTO.getListaValoresDto()) {
			valorDto.setObservacionMail(valoresDTO.getObservacionMail());
		}
		
		//Tomo, Mapeo, Creo [BoletaOrigenConstancia]
		List<ValorDto> listaValoresOrigenConstancia = this.getValoresOrigenConstancia(valoresDTO.getListaValoresDto());
		//Mapeo de valorDto a ShvValValor
		List<ShvValValor> listaValValorOrigenConstancia = mapListFromValorDTOToValValor(listaValoresOrigenConstancia);
		if(Validaciones.isCollectionNotEmpty(listaValValorOrigenConstancia)){
			if(valoresDTO.isGenerarConstancia()){
				//Constancias Separadas
				for (ShvValValor shvValValor : listaValValorOrigenConstancia) {
					crearConstanciaRecepcion(userSesion, numerosBoleta,
							modelosMail, modelosBoleta, modelosContancia,
							modelosContanciaValor, numeroConstanciaArchivo,
							numeroBoletaArchivo, shvValValor);
				}
				
			} else {
				//Constancia Conjunta
				
				/**
				 * @author u562163, IM de prod. 
				 */	
				if(listaValValorOrigenConstancia.size()==1){
					ShvValValor shvValValor = listaValValorOrigenConstancia.get(0);
					crearConstanciaRecepcion(userSesion, numerosBoleta,
							modelosMail, modelosBoleta, modelosContancia,
							modelosContanciaValor, numeroConstanciaArchivo,
							numeroBoletaArchivo, shvValValor);
				} else {
				
					if(listaValValorOrigenConstancia.size()>1){
						
						//Creo la constancia para todos los valores
						ShvValConstanciaRecepcion constancia = prepararConstancia(listaValValorOrigenConstancia);
						
						for (ShvValValor shvValValor : listaValValorOrigenConstancia) {
							
							//Creo el valor
							crearValValor(shvValValor, false, userSesion);
							
							//Creo una tarea pendiente por cada valor creado
							crearTareaPendiente(shvValValor, userSesion,false);
		
							numerosBoleta.add(shvValValor.getBoleta().getNumeroBoleta());
							if (shvValValor.getEnviarMail()){
								//Si la boleta se encuentra pendiente de autorizar no lo envio
								if (!Estado.VAL_BOLETA_PENDIENTE_AUTORIZAR.equals(shvValValor.getWorkFlow().getEstado())) {
									modelosMail.add(shvValValor);
								}
							} else {
								numeroBoletaArchivo.add(shvValValor.getBoleta().getNumeroBoleta().toString());
								modelosBoleta.add(shvValValor);
							}
							
							ShvValConstanciaRecepcionValorPk constanciaRecepcionPk = new ShvValConstanciaRecepcionValorPk();
							constanciaRecepcionPk.setValor(shvValValor);
							constanciaRecepcionPk.setConstanciaRecepcion(constancia);
							ShvValConstanciaRecepcionValor constanciaRecValor = new ShvValConstanciaRecepcionValor();
							constanciaRecValor.setShvValConstanciaRecepcionValorPk(constanciaRecepcionPk);
							shvValValor.setConstanciaRecepcion(constancia);
							
							modelosContancia.add(shvValValor);
							numeroConstanciaArchivo.add(constancia.getIdConstanciaRecepcion().toString());
							valorConstanciaRecepcionServicio.actualizarConstanciaRecepcionValor(constanciaRecValor);
							modelosContanciaValor.add(constanciaRecValor);
						}
					}
				}
			}
		}
		
		
		//Tomo, Mapeo, Creo [BoletaSinOrigenConstancia]
		List<ValorDto> listaValoresSinOrigenConstancia = this.getValoresSinOrigenConstancia(valoresDTO.getListaValoresDto());
		List<ShvValValor> listaValValorSinOrigenConstancia = mapListFromValorDTOToValValor(listaValoresSinOrigenConstancia);

		if(Validaciones.isCollectionNotEmpty(listaValValorSinOrigenConstancia)){
			for (ShvValValor shvValValor : listaValValorSinOrigenConstancia) {
				ActualizacionExitosaDto exitoDto = crearValValor(shvValValor, true, userSesion);
				numerosBoleta.add(exitoDto.getNumeroExito());
				if(exitoDto.getValorExito().getEnviarMail()){
					//Si la boleta se encuentra pendiente de autorizar no lo envio
					if (!Estado.VAL_BOLETA_PENDIENTE_AUTORIZAR.equals(shvValValor.getWorkFlow().getEstado())) {
						modelosMail.add(exitoDto.getValorExito());
					}
				} else {
					numeroBoletaArchivo.add(exitoDto.getValorExito().getBoleta().getNumeroBoleta().toString());
					modelosBoleta.add(shvValValor);
				}

				actualizarEstadoRecibo(listaValoresRecibo, shvValValor, true);
//				if (shvValValor.getParamOrigen().getIdOrigen() == Integer.valueOf(ORIGEN_CAJERO_PAGADOR_ID)
//						&& shvValValor.getValorBoletaDepositoChequePD() == null){
//					double fechaHoraInicioNanoTimeContabilidad = System.nanoTime();
//					contabilizarValor(shvValValor, false);
//
//					Utilidad.tracearTiempo(getClass(), "Tiempo en los metodos de contabilidad ", fechaHoraInicioNanoTimeContabilidad);
//				}
			}
		}
		for (ShvValValor shvValValor : listaValValorSinOrigenConstancia) {
            contabilizarValor(shvValValor, false);
        }
		for (ShvValValor shvValValor : listaValValorOrigenConstancia) {
			contabilizarValor(shvValValor, false);
		}		
//		Envio mail de Boletas
		/**
		 * @author u562163 - Sprint 3 - Defecto 49
		 */
		if(Validaciones.isCollectionNotEmpty(listaValValorSinOrigenConstancia) || Validaciones.isCollectionNotEmpty(listaValValorOrigenConstancia)){
			for (ShvValValor valor : listaValValorSinOrigenConstancia) {
				//Si es boleta cheque o boleta efectivo y el origen es cliente no se debe enviar mail de tarea porque no se genera ninguna tarea
				if(!  ((VALOR_PARA_BOLETA_CHEQUE.equals(String.valueOf(valor.getTipoValor().getIdTipoValor())) 
						|| VALOR_PARA_BOLETA_EFECTIVO.equals(String.valueOf(valor.getTipoValor().getIdTipoValor()))) 
						&& 
						(
						ORIGEN_CLIENTE_ID.equals(String.valueOf(valor.getParamOrigen().getIdOrigen()))
						||
						/**
						 * @author u573005, defecto 80, se prevee no mandar mail para tarea de autorizacion ya que
						 * cuando se es cajero pagador y se da de alta cheque o efectivo va a conciliacion
						 * directo
						 */
						ORIGEN_CAJERO_PAGADOR_ID.equals(String.valueOf(valor.getParamOrigen().getIdOrigen()))
						)
						)){
					prepararMail(mapMail, valor, null, valor.getBoleta().getEmailObservaciones() + System.lineSeparator(), null);
				}
			}
			for (ShvValValor valor : listaValValorOrigenConstancia) {
				//Si es boleta cheque o boleta efectivo y el orign es cliente no se debe enviar mail de tarea porque no se genera ninguna tarea
				if(!  ((VALOR_PARA_BOLETA_CHEQUE.equals(String.valueOf(valor.getTipoValor().getIdTipoValor())) 
						|| VALOR_PARA_BOLETA_EFECTIVO.equals(String.valueOf(valor.getTipoValor().getIdTipoValor()))) 
						&& ORIGEN_CLIENTE_ID.equals(String.valueOf(valor.getParamOrigen().getIdOrigen())))){
					prepararMail(mapMail, valor, null, valor.getBoleta().getEmailObservaciones() + System.lineSeparator(), null);
				}
			}
			/**
			 * @author u573005, sprint 3, correccion index out of bounds con constancia automatica
			 * y asunto con informacion duplicada
			 */
			/**
			 * U562163 - Se comenta porque se elimino la generacion de la tarea de autorizacion de valores
			 */
//			String asuntoUnaBoleta = "Nueva tarea - Boleta pendiente de autorizar - ";
//			if(listaValValorSinOrigenConstancia.size() == 1){
//				asuntoUnaBoleta += mailServicio.armarAsuntoValor(listaValValorSinOrigenConstancia.get(0));
//			}
//			if(listaValValorOrigenConstancia.size() == 1){
//				asuntoUnaBoleta += mailServicio.armarAsuntoValor(listaValValorOrigenConstancia.get(0));
//			}			
//			mailServicio.enviarMailMasivo("Nuevas tareas - Boletas pendientes de autorizar|Las siguientes boletas requieren autorización:|" + asuntoUnaBoleta, mapMail);
		}
		
		//Tomo, Mapeo, Creo [Aviso]
		List<ValorDto> listaValoresAvisos = this.getValoresAviso(valoresDTO.getListaValoresDto());
		List<ShvValValor> listaValValorAviso = mapListFromValorDTOToValValor(listaValoresAvisos);
		if(Validaciones.isCollectionNotEmpty(listaValValorAviso)){
			for (ShvValValor shvValValor : listaValValorAviso) {
				ActualizacionExitosaDto exitoDto = crearValValor(shvValValor, true, userSesion);
				String correccionObseraciones = "";
				if(!Validaciones.isNullOrEmpty(shvValValor.getObservaciones())){
					correccionObseraciones = shvValValor.getObservaciones() + System.lineSeparator();
				}				
				prepararMail(mapMail,shvValValor,null, correccionObseraciones,null);
				numerosBoleta.add(exitoDto.getNumeroExito());
				actualizarEstadoRecibo(listaValoresRecibo, shvValValor, true);
			}
			/**
			 * @author u573005, Sprint3, defecto 53
			 */			
			String asuntoUnsoloAvisoPago = "Nueva tarea - " + Constantes.AVISO_DE_PAGO + " pendiente de confirmar - ";
			if(Validaciones.isCollectionNotEmpty(listaValValorAviso)){
				if(listaValValorAviso.size() == 1){
					asuntoUnsoloAvisoPago += mailServicio.armarAsuntoValor(listaValValorAviso.get(0));
				}				
			}
			
			//Envio mail Aviso de pago
			mailServicio.enviarMailMasivo("Nuevas tareas - Avisos de Pago pendientes de confirmar|Los siguientes avisos de pago requieren confirmación:|"
			+ asuntoUnsoloAvisoPago, mapMail);
		}
		
		/*Manejo de PDF*/
		List<byte[]> byteARetornarBoleta = new ArrayList<byte[]>();
		List<byte[]> byteARetornarConstancia = new ArrayList<byte[]>();
		
		//Creo, Mando PDF Boleta a Mail
		List<String> mensajesMostrarEnvioMail = new ArrayList<String>();
		if(Validaciones.isCollectionNotEmpty(modelosMail)){
			mensajesMostrarEnvioMail = generarPdfBoletaMail(modelosMail, userSesion.getIdUsuario());
			generarDatosWFMail(modelosMail);
		}
		
		//Creo, Retengo PDF Boleta a Imprimir
		if(Validaciones.isCollectionNotEmpty(modelosBoleta)){
			byteARetornarBoleta.add(generarPdfBoletaImprimir(modelosBoleta));
			generarDatosWFImpresion(modelosBoleta);
		}
		
		//Creo, Retengo <De Ser Necesario> PDF Constancia Imprimir para el mensaje
		if(Validaciones.isCollectionNotEmpty(modelosContancia)){
			if(valoresDTO.isGenerarConstancia() && modelosContancia.size() > 1){
				for (ShvValValor valor : modelosContancia) {
					byteARetornarConstancia.add(crearArchivoConstancia(valor));
				}
			} else {
				byteARetornarConstancia.add(crearArchivoConstanciaValores(modelosContanciaValor));
			}
		}
		ValorDto valoDT = valoresDTO.getValorDto();
		String mensajeAenviarOK="";
		if(!Validaciones.isCollectionNotEmpty(listaValoresAvisos)){
			if(numerosBoleta.size()>1){
				mensajeAenviarOK=Propiedades.MENSAJES_PROPIEDADES.getString("boleta.alta.mensaje.crear.ok.concatena.numeros");
				String numeroValorConcatenado="";
				String coma="";
				int i=0;
				for (Long numeroValor : numerosBoleta) {				
					coma= (i==0)?"":", ";
					numeroValorConcatenado+=coma+String.valueOf(numeroValor);
					i++;
				}
				mensajesBoleta.add(Utilidad.reemplazarMensajes(mensajeAenviarOK, numeroValorConcatenado));
			} else {
				mensajeAenviarOK=Propiedades.MENSAJES_PROPIEDADES.getString("boleta.alta.mensaje.crear.ok");
				mensajesBoleta.add(Utilidad.reemplazarMensajes(mensajeAenviarOK, numerosBoleta.get(0).toString()));
			}
		}else{
			mensajeAenviarOK=Propiedades.MENSAJES_PROPIEDADES.getString("valor.alta.mensaje.crear.ok");
			mensajesBoleta.add(mensajeAenviarOK);
			enviMailAvisoPago(valoDT);
		}
		
		exitosaDto.setNumeroBoletaArchivo(numeroBoletaArchivo);
		exitosaDto.setNumeroConstanciaArchivo(numeroConstanciaArchivo);
		exitosaDto.setMensajesMostrarEnvioMail(mensajesMostrarEnvioMail);
		exitosaDto.setNumerosBoletasAlta(mensajesBoleta);
		exitosaDto.setArchivosImprimirBoleta(byteARetornarBoleta);
		exitosaDto.setArchivosImprimirConstancia(byteARetornarConstancia);
		
		Utilidad.tracearTiempo(getClass(), "Tiempo en administrar un valor: ", fechaHoraInicioNanoTime);
		
		return exitosaDto;
	}

	private void crearConstanciaRecepcion(UsuarioSesion userSesion,
			List<Long> numerosBoleta, List<ShvValValor> modelosMail,
			List<ShvValValor> modelosBoleta,
			List<ShvValValor> modelosContancia,
			List<ShvValConstanciaRecepcionValor> modelosContanciaValor,
			List<String> numeroConstanciaArchivo,
			List<String> numeroBoletaArchivo, ShvValValor shvValValor) throws NegocioExcepcion {
		
		double fechaHoraInicioNanoTime = System.nanoTime();
		ActualizacionExitosaDto exitoDto = crearValValor(shvValValor, true, userSesion);
		ShvValConstanciaRecepcionValor constancia = prepararConstancia(shvValValor);
		
		exitoDto.getValorExito().setConstanciaRecepcion(constancia.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion());
		numerosBoleta.add(exitoDto.getNumeroExito());
		//Pregunto si debo enviar mail
		if(exitoDto.getValorExito().getEnviarMail()){
			
			//Si la boleta se encuentra pendiente de autorizar no lo envio
			if (!Estado.VAL_BOLETA_PENDIENTE_AUTORIZAR.equals(exitoDto.getValorExito().getWorkFlow().getEstado())) {
				modelosMail.add(exitoDto.getValorExito());
			}
		} else {
			numeroBoletaArchivo.add(exitoDto.getValorExito().getBoleta().getNumeroBoleta().toString());
			modelosBoleta.add(shvValValor);
		}
		
		numeroConstanciaArchivo.add(constancia.getShvValConstanciaRecepcionValorPk().getConstanciaRecepcion().getIdConstanciaRecepcion().toString());
		modelosContancia.add(exitoDto.getValorExito());
		modelosContanciaValor.add(constancia);
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo crearConstanciaRecepcion ", fechaHoraInicioNanoTime);
	}
	
	/**
	 * Orden de las listas: 0. Retencion - 1. Boleta Cheque - 2. Boleta Cheque Diferido - 3. Boleta Efectivo
	 * @param numeroRecibo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<List<ShvValValor>> valoresDeUnRecibo(String numeroRecibo) throws NegocioExcepcion{
		List<List<ShvValValor>> resultado = new ArrayList<List<ShvValValor>>();
		List<ShvValValor> listaRetencion=new ArrayList<ShvValValor>();
		List<ShvValValor> listaBCheque = new ArrayList<ShvValValor>();
		List<ShvValValor> listaBCDiferido = new ArrayList<ShvValValor>();
		List<ShvValValor> listaBEfectivo = new ArrayList<ShvValValor>();
		List<ShvValValor> listaCheque = new ArrayList<ShvValValor>();
		List<ShvValValor> listaChequeDif = new ArrayList<ShvValValor>();
		List<ShvValValor> listaEfectivo = new ArrayList<ShvValValor>();
		try {
			listaRetencion   = valorDao.buscarValoresPorRecibo(numeroRecibo, "ShvValValorRetencion");
			listaBCheque     = valorDao.buscarValoresPorRecibo(numeroRecibo, "ShvValBoletaDepositoCheque");
			listaBCDiferido  = valorDao.buscarValoresPorRecibo(numeroRecibo, "ShvValBoletaDepositoChequePagoDiferido");
			listaBEfectivo   = valorDao.buscarValoresPorRecibo(numeroRecibo, "ShvValBoletaDepositoEfectivo");
			listaCheque      = valorDao.buscarValoresPorRecibo(numeroRecibo, "ShvValValorCheque");
			listaChequeDif   = valorDao.buscarValoresPorRecibo(numeroRecibo, "ShvValValorChequePagoDiferido");
			listaEfectivo    = valorDao.buscarValoresPorRecibo(numeroRecibo, "ShvValValorEfectivo");
			
			if (listaRetencion != null){
				resultado.add(listaRetencion);
			}
			if (listaBCheque != null){
				resultado.add(listaBCheque);
			}
			if (listaBCDiferido != null){
				resultado.add(listaBCDiferido);
			}
			if (listaBEfectivo != null){
				resultado.add(listaBEfectivo);
			}
			if (listaCheque != null){
				resultado.add(listaCheque);
			}
			if (listaChequeDif != null){
				resultado.add(listaChequeDif);
			}
			if (listaEfectivo != null){
				resultado.add(listaEfectivo);
			}
			
			return resultado;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e);
		}
		
	}
	
	private List<ValorDto> getValoresAviso(List<ValorDto> listaValoresDto){	
		List<ValorDto> resultado = new ArrayList<ValorDto>();
		for (ValorDto valorDto : listaValoresDto) {
			String tipoValor = valorDto.getIdTipoValor();
			if(!(VALOR_PARA_BOLETA_CHEQUE.equals(tipoValor) || VALOR_PARA_BOLETA_CHEQUE_PD.equals(tipoValor) || VALOR_PARA_BOLETA_EFECTIVO.equals(tipoValor))){
				resultado.add(valorDto);
			}
		}
		return resultado;
	}
	
	/**
	 * Obtiene una lista de boletas con valor con origen Constancia automatica
	 * @param listaValoresDto
	 * @return
	 */
	private List<ValorDto> getValoresOrigenConstancia(List<ValorDto> listaValoresDto){		
		List<ValorDto> resultado = new ArrayList<ValorDto>();
		for (ValorDto valorDto : listaValoresDto) {
			String tipoValor = valorDto.getIdTipoValor();
			String tipoOrigen = valorDto.getIdOrigen();
			if(VALOR_PARA_BOLETA_CHEQUE.equals(tipoValor) || VALOR_PARA_BOLETA_CHEQUE_PD.equals(tipoValor) || VALOR_PARA_BOLETA_EFECTIVO.equals(tipoValor)){
				if(ORIGEN_OFICINA_CONSTANCIA_AUTOMATICA_ID.equals(tipoOrigen)){
					resultado.add(valorDto);
				}
			}
		}
		return resultado;	
	}
	
	/**
	 * Obtiene una lista de boletas con valor sin origen Constancia automatica
	 * @param listaValoresDto
	 * @return
	 */
	private List<ValorDto> getValoresSinOrigenConstancia(List<ValorDto> listaValoresDto){		
		List<ValorDto> resultado = new ArrayList<ValorDto>();
		for (ValorDto valorDto : listaValoresDto) {
			String tipoValor = valorDto.getIdTipoValor();
			String tipoOrigen = valorDto.getIdOrigen();
			if(VALOR_PARA_BOLETA_CHEQUE.equals(tipoValor) || VALOR_PARA_BOLETA_CHEQUE_PD.equals(tipoValor) || VALOR_PARA_BOLETA_EFECTIVO.equals(tipoValor)){
				if(!ORIGEN_OFICINA_CONSTANCIA_AUTOMATICA_ID.equals(tipoOrigen)){
					resultado.add(valorDto);
				}
			}
		}
		return resultado;
	}
	
	/**
	 * Actualiza el workflow del valor a disponible y setea las observaciones de la confirmacion.
	 */
	public ShvValValor actualizarWorkflowDeValorADisponible(ValorDto valorDto, String usuarioModificacion) throws NegocioExcepcion
	{
		try {
			ShvValValor shvValor = valorDao.buscarValor(String.valueOf(valorDto.getIdValor()));
			valorDto.setObservaciones(((shvValor.getObservaciones() != null) ? shvValor.getObservaciones().trim() : "") + 
					((valorDto.getObservacionesConfirmarAlta() != null) ? Constantes.RETORNO_WIN + valorDto.getObservacionesConfirmarAlta().trim() : ""));
			String datosModificados = Utilidad.generarDatoModificado(shvValor.getObservaciones(), valorDto.getObservaciones(), 
					DatosWorkflowEnum.OBSERVACIONES, shvValor.getObservaciones(), valorDto.getObservaciones());
			
			ShvWfWorkflow wf = shvValor.getWorkFlow();
			ShvWfWorkflow wfActualizado = workflowValores.disponibilizarValor(wf, datosModificados, usuarioModificacion);
			
			shvValor.setWorkFlow(wfActualizado);
			shvValor.setFechaUltimoEstado(wfActualizado.getFechaUltimaModificacion());
			shvValor.setFechaDisponible(wfActualizado.getFechaUltimaModificacion());
			// Actualizo las observaciones - Agrego la observacion del confirmación
			shvValor.setObservaciones(((shvValor.getObservaciones() != null) ? shvValor.getObservaciones().trim() : "") + 
					((valorDto.getObservacionesConfirmarAlta() != null) ? Constantes.RETORNO_WIN + valorDto.getObservacionesConfirmarAlta().trim() : ""));
			// grabo las observaciones de la confirmacion en ObservacionesConfirmarAlta
			shvValor.setObservacionesConfirmarAlta(valorDto.getObservacionesConfirmarAlta());
			
			shvValor.setUsuarioConfirmacion(usuarioModificacion);
			shvValor.setNombreApellidoConfirmante(obtenerNombreApellidoAnalistaLdap(usuarioModificacion));
			
			shvValor = valorDao.actualizarValor(shvValor);
			
			//contabilizar
			if (Validaciones.isNullOrEmpty(valorDto.getValorPorReversion())){
				contabilizarValor(shvValor, false);
			} else {
				contabilizarValor(shvValor, true);
			}
			
			// Actualizo el recibo
			actualizarEstadoRecibo(null, shvValor, true);
			
			return shvValor;
			
		} catch (PersistenciaExcepcion pe) {
			throw new NegocioExcepcion(pe);
		}
	}

	/**
	 * 
	 * @param shvValValores
	 * @param idUsuarioEnvio
	 * @return
	 * @throws PersistenciaExcepcion 
	 */
	private List<String> generarPdfBoletaMail(List<ShvValValor> shvValValores, String idUsuarioEnvio) throws PersistenciaExcepcion{
		double fechaHoraInicioNanoTime = System.nanoTime();
		List<Long> boletasExito = new ArrayList<Long>();
		List<Long> boletasErrorPara = new ArrayList<Long>();
		List<Long> boletasErrorMail = new ArrayList<Long>();
		List<Long> boletasErrorDocumento = new ArrayList<Long>();
		List<String> boletasADevolver = new ArrayList<String>();
		
		for (ShvValValor modeloMail : shvValValores) {
			Document documento = new Document();
			ByteArrayOutputStream baos = null;
			try {
				GeneradorComprobantePago generador = new GeneradorComprobantePago(modeloMail);
				baos = new ByteArrayOutputStream();
				PdfWriter pw = PdfWriter.getInstance(documento, baos);
				documento.open();

				generador.crearContenidoPDF(documento, pw);
				//documento = generador.getDocumento();
				documento.close();
				
				mailDocumento(baos.toByteArray(), modeloMail, idUsuarioEnvio);
				boletasExito.add(modeloMail.getBoleta().getNumeroBoleta());

			} catch (DocumentException e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorDocumento.add(modeloMail.getBoleta().getNumeroBoleta());
			} catch (IOException e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorDocumento.add(modeloMail.getBoleta().getNumeroBoleta());
			} catch (SQLException e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorDocumento.add(modeloMail.getBoleta().getNumeroBoleta());
			} catch (CampoMailExcepcion e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorPara.add(modeloMail.getBoleta().getNumeroBoleta());
			} catch (MailExcepcion e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorMail.add(modeloMail.getBoleta().getNumeroBoleta());
			} catch (NegocioExcepcion e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorMail.add(modeloMail.getBoleta().getNumeroBoleta());
			}
			try {
				boletaDao.actualizarBoleta(modeloMail.getBoleta());
			} catch (PersistenciaExcepcion e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
			}
		}

		if(boletasErrorDocumento.size() == shvValValores.size()){
			if(boletasErrorDocumento.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_DOCUMENTO_SINGULAR);
			} else {
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_DOCUMENTO_PLURAL);
			}
		} else if(boletasErrorMail.size() == shvValValores.size()){
			if(boletasErrorMail.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_ENVIO_SINGULAR);
			} else {
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_ENVIO_PLURAL);
			}
		} else if(boletasErrorPara.size() == shvValValores.size()){
			if(boletasErrorPara.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_PARA_SINGULAR);
			} else {
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_PARA_PLURAL);
			}
		} else if(boletasExito.size() == shvValValores.size()){
			if(boletasExito.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_EXITO_SINGULAR);
			} else {
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_EXITO_PLURAL);
			}
		} else {

			if (!boletasErrorDocumento.isEmpty()) {
				if(boletasErrorDocumento.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_DOCUMENTO_SINGULAR, boletasErrorDocumento.toString()));
				} else {
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_DOCUMENTO_PLURAL, boletasErrorDocumento.toString()));
				}
			}

			if (!boletasErrorMail.isEmpty()) {
				if(boletasErrorMail.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_ENVIO_SINGULAR, boletasErrorMail.toString()));
				} else {
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_ENVIO_PLURAL, boletasErrorMail.toString()));
				}
			}
			
			if (!boletasErrorPara.isEmpty()) {
				if(boletasErrorPara.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_PARA_SINGULAR, boletasErrorPara.toString()));
				} else {
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_PARA_PLURAL, boletasErrorPara.toString()));
				}
			}
			
			if (!boletasExito.isEmpty()) {
				if(boletasExito.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_EXITO_SINGULAR, boletasExito.toString()));
				} else {
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_EXITO_PLURAL, boletasExito.toString()));
				}
			}
		}
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarPdfBoletaMail ", fechaHoraInicioNanoTime);
		return boletasADevolver;

	}
	/**
	 * Se copia metodo para la mejora de performance
	 * @param shvValValores
	 * @param idUsuarioEnvio
	 * @return
	 * @throws PersistenciaExcepcion 
	 */
	private List<String> generarBoletaMailPdf(List<VistaSoporteResultadoBusquedaValor> valores, String idUsuarioEnvio) throws PersistenciaExcepcion{
		double fechaHoraInicioNanoTime = System.nanoTime();
		List<Long> boletasExito = new ArrayList<Long>();
		List<Long> boletasErrorPara = new ArrayList<Long>();
		List<Long> boletasErrorMail = new ArrayList<Long>();
		List<Long> boletasErrorDocumento = new ArrayList<Long>();
		List<String> boletasADevolver = new ArrayList<String>();
		ShvBolBoletaSimplificado shvBolBoleta =  new ShvBolBoletaSimplificado();
		for (VistaSoporteResultadoBusquedaValor modeloMail : valores) {
			Document documento = new Document();
			ByteArrayOutputStream baos = null;
			try {
				GeneradorComprobantePago generador = new GeneradorComprobantePago(modeloMail, acuerdoDao);
				shvBolBoleta = boletaDao.buscarBoletaSimplificado(Long.valueOf(modeloMail.getIdBoleta()));
				baos = new ByteArrayOutputStream();
				PdfWriter pw = PdfWriter.getInstance(documento, baos);
				documento.open();

				generador.crearContenidoPDF(documento, pw);

				documento.close();
				
				mailDocumento(baos.toByteArray(), modeloMail, idUsuarioEnvio,shvBolBoleta);
				boletasExito.add(Long.valueOf(modeloMail.getNroBoleta()));

			} catch (DocumentException e) {
				Traza.error(getClass(), "Error al generarBoletaMailPdf", e);
				boletasErrorDocumento.add(Long.valueOf(modeloMail.getNroBoleta()));
			} catch (IOException e) {
				Traza.error(getClass(), "Error al generarBoletaMailPdf", e);
				boletasErrorDocumento.add(Long.valueOf(modeloMail.getNroBoleta()));
			} catch (SQLException e) {
				Traza.error(getClass(), "Error al generarBoletaMailPdf", e);
				boletasErrorDocumento.add(Long.valueOf(modeloMail.getNroBoleta()));
			} catch (CampoMailExcepcion e) {
				Traza.error(getClass(), "Error al generarBoletaMailPdf", e);
				boletasErrorPara.add(Long.valueOf(modeloMail.getNroBoleta()));
			} catch (MailExcepcion e) {
				Traza.error(getClass(), "Error al generarBoletaMailPdf", e);
				boletasErrorMail.add(Long.valueOf(modeloMail.getNroBoleta()));
			} catch (NegocioExcepcion e) {
				Traza.error(getClass(), "Error al generarBoletaMailPdf", e);
				boletasErrorMail.add(Long.valueOf(modeloMail.getNroBoleta()));
			}
			try {
				boletaDao.actualizarBoleta(shvBolBoleta);
			} catch (PersistenciaExcepcion e) {
				Traza.error(getClass(), "Error al generarBoletaMailPdf", e);
			}
		}

		if(boletasErrorDocumento.size() == valores.size()){
			if(boletasErrorDocumento.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_DOCUMENTO_SINGULAR);
			} else {
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_DOCUMENTO_PLURAL);
			}
		} else if(boletasErrorMail.size() == valores.size()){
			if(boletasErrorMail.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_ENVIO_SINGULAR);
			} else {
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_ENVIO_PLURAL);
			}
		} else if(boletasErrorPara.size() == valores.size()){
			if(boletasErrorPara.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_PARA_SINGULAR);
			} else {
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_PARA_PLURAL);
			}
		} else if(boletasExito.size() == valores.size()){
			if(boletasExito.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_EXITO_SINGULAR);
			} else {
				boletasADevolver.add(Mensajes.MAIL_BOLETAS_GENERAL_EXITO_PLURAL);
			}
		} else {

			if (!boletasErrorDocumento.isEmpty()) {
				if(boletasErrorDocumento.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_DOCUMENTO_SINGULAR, boletasErrorDocumento.toString()));
				} else {
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_DOCUMENTO_PLURAL, boletasErrorDocumento.toString()));
				}
			}

			if (!boletasErrorMail.isEmpty()) {
				if(boletasErrorMail.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_ENVIO_SINGULAR, boletasErrorMail.toString()));
				} else {
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_ENVIO_PLURAL, boletasErrorMail.toString()));
				}
			}
			
			if (!boletasErrorPara.isEmpty()) {
				if(boletasErrorPara.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_PARA_SINGULAR, boletasErrorPara.toString()));
				} else {
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_PARA_PLURAL, boletasErrorPara.toString()));
				}
			}
			
			if (!boletasExito.isEmpty()) {
				if(boletasExito.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_EXITO_SINGULAR, boletasExito.toString()));
				} else {
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ESPECIFICO_EXITO_PLURAL, boletasExito.toString()));
				}
			}
		}
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarPdfBoletaMail ", fechaHoraInicioNanoTime);
		return boletasADevolver;

	}

	/**
	 * @param shvValValores
	 * @throws NegocioExcepcion 
	 */
	private void generarDatosWFMail(List<ShvValValor> shvValValores) throws NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		if(Validaciones.isCollectionNotEmpty(shvValValores)){
			for (ShvValValor shvValValor : shvValValores) {
				String fechaEnvio = "";
				String usuarioEnvio = "";
				String mailDestino = "";
				String estadoEnvio = "";
				String usuarioModificacion = shvValValor.getBoleta().getEmailEnvioUsuario();
				if(!Validaciones.isNullOrEmpty(Utilidad.formatDateAndTimeFull(shvValValor.getBoleta().getEmailEnvioFecha()))) {
					fechaEnvio = Utilidad.formatDateAndTimeFull(shvValValor.getBoleta().getEmailEnvioFecha());
				}
				UsuarioLdapDto usuarioLdapMail = null;
				try {
					if(!Validaciones.isNullOrEmpty(shvValValor.getBoleta().getEmailEnvioUsuario())) {
						usuarioLdapMail = ldapServicio.buscarUsuarioPorUidEnMemoria(shvValValor.getBoleta().getEmailEnvioUsuario());
						usuarioEnvio = usuarioLdapMail.getNombreCompleto();
					}
				} catch (LdapExcepcion e) {
					Traza.error(getClass(), "Error de LDAP", e);
				}
				if(!Validaciones.isNullOrEmpty(shvValValor.getBoleta().getEmailEnvioDestino())) {
					mailDestino = shvValValor.getBoleta().getEmailEnvioDestino();
				}
				if(!Validaciones.isObjectNull(shvValValor.getBoleta().getEmailEnvioEstado())) {
					estadoEnvio = shvValValor.getBoleta().getEmailEnvioEstado().toString();
				}
				String datosModificados = "[Boleta Asociada Mail]" 
										+ "(Fecha de envío: " + fechaEnvio + ")" 
										+ "(Usuario que envió el mail: " + usuarioEnvio + ")" 
										+ "(Mail destino: " + mailDestino + ")" 
										+ "(Estado del envío: " + estadoEnvio + ")";
				guardarWFActualizado(shvValValor, datosModificados, usuarioModificacion);
			}
		}
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarDatosWFMail ", fechaHoraInicioNanoTime);
	}
	
	/**
	 * 
	 * @param ShvValValores
	 * @return
	 * @throws NegocioExcepcion 
	 * @throws PersistenciaExcepcion 
	 */
	private byte[] generarPdfBoletaImprimir(List<ShvValValor> ShvValValores) throws NegocioExcepcion, PersistenciaExcepcion{
		
		try {
			double fechaHoraInicioNanoTime = System.nanoTime();
			Traza.auditoria(getClass(), "path java.io.tmpdir: " + System.getProperty("java.io.tmpdir") 
					+ " path jboss.server.temp.dir: " + System.getProperty("jboss.server.temp.dir")
					+ " path user.home: " + System.getProperty("user.home")
					+ " path user.dir: " + System.getProperty("user.dir")
					);
			
			System.setProperty("java.io.tmpdir", System.getProperty("jboss.server.temp.dir"));
			
			Traza.auditoria(getClass(), "path java.io.tmpdir: " + System.getProperty("java.io.tmpdir"));
			
			Document documento = new Document();
			ByteArrayOutputStream baos = null;
		
			GeneradorComprobantePago generador = new GeneradorComprobantePago(ShvValValores);
			Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarPdfBoletaImprimir - GeneradorComprobantePago ", fechaHoraInicioNanoTime);
			Traza.auditoria(getClass(), "se cargo GeneradorComprobantePago");
			
			baos = new ByteArrayOutputStream();
			PdfWriter pw = PdfWriter.getInstance(documento, baos);
			documento.open();
			generador.crearContenidoPDF(documento, pw);
			Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarPdfBoletaImprimir - crearContenido", fechaHoraInicioNanoTime);
			Traza.auditoria(getClass(), "se disparo generador.crearContenidoPDF(documento, pw);");
			
			documento.close();
			Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarPdfBoletaImprimir ", fechaHoraInicioNanoTime);
			
			return baos.toByteArray();
			
		} catch (DocumentException e) {
			Traza.error(getClass(), "Error al generarPdfBoletaImprimir DocumentException", e);
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (IOException e) {
			Traza.error(getClass(), "Error al generarPdfBoletaImprimir IOException", e);
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (SQLException e) {
			Traza.error(getClass(), "Error al generarPdfBoletaImprimir SQLException", e);
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}

	/**
	 * 
	 * @param ShvValValores
	 * @param acuerdoDao 
	 * @return
	 * @throws NegocioExcepcion 
	 * @throws PersistenciaExcepcion 
	 */
	private byte[] generarPdfImprimirBoleta(List<VistaSoporteResultadoBusquedaValor> ShvValValores) throws NegocioExcepcion, PersistenciaExcepcion{
		
		try {
			double fechaHoraInicioNanoTime = System.nanoTime();
			Traza.auditoria(getClass(), "path java.io.tmpdir: " + System.getProperty("java.io.tmpdir") 
					+ " path jboss.server.temp.dir: " + System.getProperty("jboss.server.temp.dir")
					+ " path user.home: " + System.getProperty("user.home")
					+ " path user.dir: " + System.getProperty("user.dir")
					);
			
			System.setProperty("java.io.tmpdir", System.getProperty("jboss.server.temp.dir"));
			
			Traza.auditoria(getClass(), "path java.io.tmpdir: " + System.getProperty("java.io.tmpdir"));
			
			Document documento = new Document();
			ByteArrayOutputStream baos = null;
		
			GeneradorComprobantePago generador = new GeneradorComprobantePago(ShvValValores, acuerdoDao);
			Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarPdfBoletaImprimir - GeneradorComprobantePago ", fechaHoraInicioNanoTime);
			Traza.auditoria(getClass(), "se cargo GeneradorComprobantePago");
			
			baos = new ByteArrayOutputStream();
			PdfWriter pw = PdfWriter.getInstance(documento, baos);
			documento.open();
			generador.crearContenidoPDF(documento, pw);
			Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarPdfBoletaImprimir - crearContenido", fechaHoraInicioNanoTime);
			Traza.auditoria(getClass(), "se disparo generador.crearContenidoPDF(documento, pw);");
			
			documento.close();
			Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarPdfBoletaImprimir ", fechaHoraInicioNanoTime);
			
			return baos.toByteArray();
			
		} catch (DocumentException e) {
			Traza.error(getClass(), "Error al generarPdfBoletaImprimir DocumentException", e);
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (IOException e) {
			Traza.error(getClass(), "Error al generarPdfBoletaImprimir IOException", e);
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (SQLException e) {
			Traza.error(getClass(), "Error al generarPdfBoletaImprimir SQLException", e);
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}
	

	/**
	 * @param ShvValValores
	 * @throws NegocioExcepcion 
	 */
	private void generarDatosWFImpresion(List<ShvValValor> ShvValValores) throws NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		if(Validaciones.isCollectionNotEmpty(ShvValValores)){
			for (ShvValValor shvValValor : ShvValValores) {
				String fechaImpresion = "";
				String usuarioImpresion = "";
				String estadoImpresion = "";

				if(!Validaciones.isNullOrEmpty(Utilidad.formatDateAndTimeFull(shvValValor.getBoleta().getImpresionFecha()))) {
					fechaImpresion = Utilidad.formatDateAndTimeFull(shvValValor.getBoleta().getImpresionFecha());
				}
				if(!Validaciones.isObjectNull(shvValValor.getBoleta().getImpresionEstado())) {
					estadoImpresion = shvValValor.getBoleta().getImpresionEstado().toString();
				}
				String usuarioModificacion = shvValValor.getBoleta().getImpresionUsuario();
				UsuarioLdapDto usuarioLdapImpresion = null;
				try {
					if(!Validaciones.isNullOrEmpty(shvValValor.getBoleta().getImpresionUsuario())){
						usuarioLdapImpresion = ldapServicio.buscarUsuarioPorUidEnMemoria(shvValValor.getBoleta().getImpresionUsuario());
						usuarioImpresion = usuarioLdapImpresion.getNombreCompleto();
					}
				} catch (LdapExcepcion e) {
					Traza.error(getClass(), "Error al pasar", e);
				}
				String datosModificados = "[Boleta Asociada Impresa]" 
										+ "(Fecha de impresión: " + fechaImpresion + ")" 
										+ "(Usuario que la imprimió: " + usuarioImpresion + ")" 
										+ "(Estado de la impresión: " + estadoImpresion + ")";
				guardarWFActualizado(shvValValor, datosModificados, usuarioModificacion);
			}
		}
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarDatosWFImpresion ", fechaHoraInicioNanoTime);
	}
	
	/**
	 * @param workflow 
	 * @param ShvValValores
	 * @throws NegocioExcepcion 
	 * @throws PersistenciaExcepcion 
	 * @throws NumberFormatException 
	 */
	private void generarDatosImpresionWf(ShvBolBoletaSimplificado shvBolBoleta, ShvWfWorkflow workflow) throws NegocioExcepcion, NumberFormatException, PersistenciaExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();

				String fechaImpresion = "";
				String usuarioImpresion = "";
				String estadoImpresion = "";
				
				if(!Validaciones.isNullOrEmpty(Utilidad.formatDateAndTimeFull(shvBolBoleta.getImpresionFecha()))) {
					fechaImpresion = Utilidad.formatDateAndTimeFull(shvBolBoleta.getImpresionFecha());
				}
				if(!Validaciones.isObjectNull(shvBolBoleta.getImpresionEstado())) {
					estadoImpresion = shvBolBoleta.getImpresionEstado().toString();
				}
				String usuarioModificacion = shvBolBoleta.getImpresionUsuario();
				UsuarioLdapDto usuarioLdapImpresion = null;
				try {
					if(!Validaciones.isNullOrEmpty(shvBolBoleta.getImpresionUsuario())){
						usuarioLdapImpresion = ldapServicio.buscarUsuarioPorUidEnMemoria(shvBolBoleta.getImpresionUsuario());
						usuarioImpresion = usuarioLdapImpresion.getNombreCompleto();
					}
				} catch (LdapExcepcion e) {
					Traza.error(getClass(), "Error al pasar", e);
				}
				String datosModificados = "[Boleta Asociada Impresa]" 
										+ "(Fecha de impresión: " + fechaImpresion + ")" 
										+ "(Usuario que la imprimió: " + usuarioImpresion + ")" 
										+ "(Estado de la impresión: " + estadoImpresion + ")";
			guardarActualizadoWf(workflow, datosModificados, usuarioModificacion);
			Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo generarDatosWFImpresion ", fechaHoraInicioNanoTime);
			}

	
	/**
	 * 
	 */
	public List<ValorDto> buscarChequesReemplazar(String codigoCliente) throws NegocioExcepcion {
		try {

			List<ValorDto> listaChequesReemplazar = new ArrayList<ValorDto>();
			List<Map<String, Object>> list;
			list = valorDao.consultaChequesReemplazar(codigoCliente);
			
			
			Iterator<Map<String, Object>> it = list.iterator();
		    while(it.hasNext()){
		    	Map<?, ?> o = it.next();
		    	
		    	ValorDto valor = new ValorDto();
		    	valor.setChequeReemplazarNumero(o.get("NUMERO_CHEQUE").toString());
		    	valor.setChequeReemplazarId(o.get("ID_VALOR").toString());
		    	listaChequesReemplazar.add(valor);
		    	
		    	
		    }
			return listaChequesReemplazar;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}



	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp) throws NegocioExcepcion {
		Long idValor = new Long(id.toString()) ;
		try {				
			//busca en la base la fecha_modificacion del workflow del valor. buscando por id_valor=id

				if (valorDao.buscarFechaModificacionXIdValor(idValor).getTime() != timeStamp.longValue()) {
					throw new ConcurrenciaExcepcion();
				}

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
				
	}
	
	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		try {
			ShvValValor valorModelo = valorDao.buscarValor(String.valueOf(id));
			if (!Validaciones.isObjectNull(valorModelo)) {
				ValorDto valorDto = new ValorDto();
				valorDto = (ValorDto) defaultMapeador.map(valorModelo);
				return valorDto;
			}
			return null;

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}

	}
	
	/**
	    * Modifica el estado del cobro a VAL_DISPONIBLE si esta en esta VAL_USADO. Ademas ingresa una descripcion en los datos moficiados del workflow.
	    * Ademas le reintegro el saldo del medio de pago al saldo disponible al valor.
	    */
		@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
		@Override
		public ShvValValorSimplificado revertirValoresPertenecientesAGrupo(ShvValValorSimplificado valor, ShvCobTransaccionSinOperacion transaccion, BigDecimal importe, Integer numeroTransaccion, boolean desapropiacion) throws NegocioExcepcion {

				String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
	    		
	    		if (!Validaciones.isObjectNull(valor)) {
	    			BigDecimal saldoOriginal = valor.getSaldoDisponible();
	    			valor.setSaldoDisponible(saldoOriginal.add(importe));
		                 
		    		if(valor.getWorkFlow().getEstado().equals(Estado.VAL_USADO)){
		    			String mensaje = " Reversion de valor por " + (desapropiacion?"desapropiacion de ":"")+ "transaccion ";
		    			ShvWfWorkflow workflowActualizado = workflowValores.disponibilizarValorUsado(valor.getWorkFlow(), 
		    					mensaje + transaccion.getOperacionTransaccionFormateado(), usuarioBatch);
		    			valor.setWorkFlow(workflowActualizado);
		    			valor.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
		    		}
		
		    		ShvCobFacturaSinOperacion factura = transaccion.getFactura();
		    		
		    		String datosModificados = generarDetalleValor(factura, transaccion, importe,valor, saldoOriginal);
		    		
		            ShvWfWorkflow workflowActualizado = workflowValores.actualizarWorkflow(valor.getWorkFlow(), datosModificados.toString(), usuarioBatch);
		            valor.setWorkFlow(workflowActualizado);
		            valor.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
		            /**
					 * @author u562163, IM de prod. 
					 */	
		            Traza.advertencia(this.getClass(), datosModificados.toString());
	    		}
	    		return valor;
	                 
	    }
		

   /**
    * Modifica el estado del cobro a VAL_DISPONIBLE si esta en esta VAL_USADO. Ademas ingresa una descripcion en los datos moficiados del workflow.
    * Ademas le reintegro el saldo del medio de pago al saldo disponible al valor.
    */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValorSimplificado revertirValoresPertenecientesATransaccion(ShvValValorSimplificado valor, BigDecimal importe, ShvCobCobro cobro, Integer numeroTransaccion, boolean desapropiacion) throws NegocioExcepcion {

		try {
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			ShvCobTransaccion transaccion = cobro.getTransaccionPorNumeroTransaccion(numeroTransaccion);
    		
    		if (!Validaciones.isObjectNull(valor)) {
    			BigDecimal saldoOriginal = valor.getSaldoDisponible();
    			valor.setSaldoDisponible(saldoOriginal.add(importe));
	                 
	    		if(valor.getWorkFlow().getEstado().equals(Estado.VAL_USADO)){
	    			String mensaje = " Reversion de valor por " + (desapropiacion?"desapropiacion de ":"")+ "transaccion ";
	    			ShvWfWorkflow workflowActualizado = workflowValores.disponibilizarValorUsado(valor.getWorkFlow(), 
	    					mensaje + transaccion.getOperacionTransaccionFicticioFormateado(), usuarioBatch);
	    			valor.setWorkFlow(workflowActualizado);
	    			valor.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
	    		}
	
	    		ShvCobTransaccionSinOperacion transaccionSinOperacion;
				
				transaccionSinOperacion = cobroDao.buscarTransaccionSinOperacionPorIdTransaccion(Integer.valueOf(transaccion.getIdTransaccion()));
				
	    		
	    		ShvCobFacturaSinOperacion factura = transaccionSinOperacion.getFactura();
	                
	    		String datosModificados = generarDetalleValor(factura, transaccionSinOperacion, importe, valor, saldoOriginal);
	            
	            ShvWfWorkflow workflowActualizado = workflowValores.actualizarWorkflow(valor.getWorkFlow(), datosModificados.toString(), usuarioBatch);
	            valor.setWorkFlow(workflowActualizado);
	            valor.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
	            /**
				 * @author u562163, IM de prod. 
				 */	
	            Traza.advertencia(this.getClass(), datosModificados.toString());
    		}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return valor;
                 
    }
	
	/**
	 * A partir de un idValor obtiene el estado. 
	 * Si el estado es val_boleta_pendiente_conciliacion lo pasa a val_disponible.
	 * Si el estado es val_no_disponible lo pasa a val_disponible.
	 */
//	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void disponibilizarValorAsociadoBoleta(Long idValor, Date fechaDepositoRegistroAvc,HashMap<String, List<String>> cuerpoMailMap, Mail asunto) throws NegocioExcepcion {
		try {
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			
			
			ShvValValor valorModelo = valorDao.buscarValor(String.valueOf(idValor));
			if(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.equals(valorModelo.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado())){
				ShvWfWorkflow  workflowActualizado = workflowValores.disponibilizarBoleta(valorModelo.getWorkFlow(), "Disponibilizar valor por Conciliacion", usuarioBatch);
				valorModelo.setWorkFlow(workflowActualizado);
				valorModelo.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
				
			}else{
				if(Estado.VAL_NO_DISPONIBLE.equals(valorModelo.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado())){
					ShvWfWorkflow  workflowActualizado = workflowValores.disponibilizarValor(valorModelo.getWorkFlow(), "Disponibilizar valor por Conciliacion", usuarioBatch);
					valorModelo.setWorkFlow(workflowActualizado);
					valorModelo.setFechaUltimoEstado(workflowActualizado.getFechaUltimaModificacion());
				}
			}
			valorModelo.setFechaDisponible(new Date());
			
			//seteo la fecha Deposito
			if (!Validaciones.isObjectNull(valorModelo.getValorBoletaDepositoCheque())) {
				valorModelo.getValorBoletaDepositoCheque().setFechaDeposito(fechaDepositoRegistroAvc);
			} else {
				if (!Validaciones.isObjectNull(valorModelo.getValorBoletaDepositoChequePD())) {
					valorModelo.getValorBoletaDepositoChequePD().setFechaDeposito(fechaDepositoRegistroAvc);
				} else { 
					if (!Validaciones.isObjectNull(valorModelo.getValorBoletaEfectivo())) {
						valorModelo.getValorBoletaEfectivo().setFechaDeposito(fechaDepositoRegistroAvc);
					}
				}
			}
			
			
			
			Date fechaValor = calcularFechaValor(valorModelo, fechaDepositoRegistroAvc);
			valorModelo.setFechaValor(fechaValor);
			
			//seteo la boleta al valor para que persista el cambio de workflow de la boleta
			valorModelo = valorDao.actualizarValor(valorModelo);
			Traza.advertencia(ValorServicioImpl.class, "Se actualiza el valor id: "+valorModelo.getIdValor()+ " con estado: "+valorModelo.getWorkFlow().getEstado());
			
			//Actualizo el recibo
			actualizarEstadoRecibo(null, valorModelo, true);
			contabilizarValor(valorModelo, false);
			//guardo los datos para el mail al analista avisando que se disponibilizo el valor
			mailValorConciliacion(valorModelo,cuerpoMailMap);
			
			asunto.setAsunto("Conciliacion" + mailServicio.armarAsuntoValorStringBuffer(valorModelo).toString());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}

	/**
	 * Asocia un valor con la boleta que coincida con el idBoleta.
	 * @param valorModelo
	 * @param idBoleta
	 * @throws NegocioExcepcion
	 */
	public void asociarValorConBoleta(ShvValValor valorModelo, Long idBoleta) throws NegocioExcepcion {
		try {
			ShvBolBoleta boleta = boletaDao.buscarBoleta(idBoleta);
			valorModelo.setBoleta(boleta);
			valorDao.actualizarValor(valorModelo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Arma un ContabilidadDto con los datos del ShvValValor excepto el importe
	 * @param valorModelo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ContabilidadDto contabilizarValor(ShvValValor valorModelo) throws NegocioExcepcion{
		ContabilidadDto contabilidadDto = new ContabilidadDto();
			
		contabilidadDto.setIdValor(valorModelo.getIdValor());
		contabilidadDto.setTransaccion(String.valueOf(valorModelo.getIdValor()));
		contabilidadDto.setCodigoClienteLegado(valorModelo.getIdClienteLegado());
		contabilidadDto.setEstado(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE);
		if (valorModelo.getAcuerdo()!=null){
			contabilidadDto.setIdAcuerdo(valorModelo.getAcuerdo());
		}
		contabilidadDto.setIdAnalista(valorModelo.getIdAnalista());
		contabilidadDto.setMoneda(valorModelo.getMoneda());
		contabilidadDto.setNumeroDocumentoContable((valorModelo.getNumeroDocumentoContable()!=null)?valorModelo.getNumeroDocumentoContable():null);
		return contabilidadDto;
	}
	
	/**
	 * Metodo generico que prepara el mail del valor modificado
	 * @param shvValValor
	 * @param observaciones
	 * @param asunto
	 */
	public void enviarMailValor(String idAnalista, String idCopropietario, String teamComercial, String cuerpo, String asunto){
		
		StringBuffer cuerpo1= new StringBuffer("");
		if(!Validaciones.isNullOrEmpty(cuerpo)){
			cuerpo1.append(cuerpo);	
		}
		
		String cc = "";
		String para = "";
		/**
		 * @author u573005, sprint3, normalizacion armado mails
		 */
		if (!Validaciones.isNullOrEmpty(idAnalista)) {
			String mailPara = mailServicio.obtenerMailUsuario(idAnalista);
			if(!Validaciones.isNullOrEmpty(mailPara)){
				para = mailPara;
			}
		}
		if (!Validaciones.isNullOrEmpty(idCopropietario)) {
			String mailCc = mailServicio.obtenerMailUsuario(idCopropietario);
			if(!Validaciones.isNullOrEmpty(mailCc)){
				cc = mailCc;
			}
		}
		if (!Validaciones.isNullOrEmpty(teamComercial)) {
			String mailCc = mailServicio.obtenerMailUsuario(teamComercial);
			if(!Validaciones.isNullOrEmpty(mailCc)){
				if(!Validaciones.isNullOrEmpty(cc)){
					cc +=";" + mailCc;
				}else{
					cc = mailCc;
				}
			}
		}		
	
		Mail mail = new Mail(para, asunto, cuerpo1);
		
		if (!Validaciones.isNullOrEmpty(cc)) {
			mail.setDestinatarioCC(cc);
		}

		mailServicio.sendMail(mail); 
	}
	
	/**
	 * Concilia la boleta y disponibiliza el valor asociado
	 */
	public ShvValValor establecerValorConciliadoConDiferencia(BoletaSinValorDto boletaDto, String usuarioModificacion, DepositoDto registroAvc) throws NegocioExcepcion {
		try{
			ShvValValor valor = valorDao.buscarValorTipoDepositoPorIdBoleta(Long.valueOf(boletaDto.getIdBoleta().toString()));
			
			if (!Validaciones.isObjectNull(valor)) {
				valor = igualarDatosDelValorConRegistroAVC(registroAvc, valor);
				
				// Disponibilizo el valor
				ShvWfWorkflow wfValor = valor.getWorkFlow();
				ShvWfWorkflow workflowValorActualizado = workflowValores.disponibilizarBoleta(wfValor, " ",	usuarioModificacion);
				valor.setWorkFlow(workflowValorActualizado);
				valor.setFechaUltimoEstado(workflowValorActualizado.getFechaUltimaModificacion());
				valor.setFechaDisponible(workflowValorActualizado.getFechaUltimaModificacion());
				
				//seteo la fecha deposito
				if (!Validaciones.isObjectNull(valor.getValorBoletaDepositoCheque())) {
					valor.getValorBoletaDepositoCheque().setFechaDeposito(registroAvc.getFechaPago());
				} else {
					if (!Validaciones.isObjectNull(valor.getValorBoletaDepositoChequePD())) {
						valor.getValorBoletaDepositoChequePD().setFechaDeposito(registroAvc.getFechaPago());
					} else { 
						if (!Validaciones.isObjectNull(valor.getValorBoletaEfectivo())) {
							valor.getValorBoletaEfectivo().setFechaDeposito(registroAvc.getFechaPago());
						}
					}
				}
				
				valor.setFechaValor(calcularFechaValor(valor,registroAvc.getFechaPago()));
				valor = setearNumeroValor(valor, valor);
				
				// Obtengo la boleta asociada al valor
				ShvBolBoleta boleta = null;
				if(!Validaciones.isObjectNull(valor.getValorBoletaDepositoCheque())){
					boleta = valor.getValorBoletaDepositoCheque().getBoleta();
				}else if(!Validaciones.isObjectNull(valor.getValorBoletaDepositoChequePD())) {
					boleta = valor.getValorBoletaDepositoChequePD().getBoleta();
					} else if (!Validaciones.isObjectNull(valor.getValorBoletaEfectivo())) {
						boleta = valor.getValorBoletaEfectivo().getBoleta();
				}
				
				//Confirmo conciliacion a la boleta
				if(!Validaciones.isObjectNull(boleta)){
					ShvWfWorkflow wfBoleta = boleta.getWorkFlow();
					ShvWfWorkflow workflowBoletaActualizado = workflowBoletas.conciliarBoletaConDiferencias(wfBoleta, " ", usuarioModificacion);
					boleta.setWorkFlow(workflowBoletaActualizado);
					boletaDao.actualizarBoleta(boleta);
					valor = valorDao.actualizarValor(valor);
					//llamar contabilidad (b.cheque,b.chequePD, b.efectivo, cheque, chequeDP, efectivo)
					contabilizarValor(valor, false);
				}
				
				//Actualizo el recibo
				actualizarEstadoRecibo(null, valor, true);
			}
			return valor;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}

	}
	
	/**
	 * Actualiza los estados del Registro AVC y la Boleta. Para esto: en caso de diferir el “Nro. Cliente”,
	 * se actualizará del lado del Registro AVC con el dato que se indique en la Boleta.
	 * De lo contrario se debe actualizar del lado de la Boleta, con los datos que se indiquen en el Registro AVC. 
	 * @param registroAvc
	 * @param valor
	 */
	private ShvValValor igualarDatosDelValorConRegistroAVC(DepositoDto registroAvc, ShvValValor valor) throws NegocioExcepcion{
		try {
			
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			
			String datosModificados = "";

			if(!registroAvc.getIdAcuerdo().equals(valor.getAcuerdo().getIdAcuerdo())){
				ShvParamAcuerdo acuerdo = acuerdoDao.buscarAcuerdo(registroAvc.getIdAcuerdo());
				
				datosModificados += "[Acuerdo](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+valor.getAcuerdo().getDescripcion()+")(" 
						+ Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+registroAvc.getAcuerdo()+")";
				valor.setAcuerdo(acuerdo);
			}
			
			if(!Validaciones.isObjectNull(registroAvc.getImporte())){
				if(!registroAvc.getImporte().equals(valor.getImporte())){
					datosModificados += "[Importe](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+valor.getImporte()+")(" 
										+ Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+registroAvc.getImporteParaComparar()+")";
					datosModificados += "[Saldo Disponible](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+valor.getSaldoDisponible()+")(" 
										+ Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+registroAvc.getImporteParaComparar()+")";
					
					valor.setImporte(registroAvc.getImporteParaComparar());
					valor.setSaldoDisponible(registroAvc.getImporteParaComparar());
				}
			}
			
			if (!Validaciones.isObjectNull(valor.getValorBoletaDepositoCheque())) {
				if(!Validaciones.isObjectNull(registroAvc.getNroBoleta())){
					if(!registroAvc.getNroBoleta().equals(valor.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta())){
						datosModificados += "[Número Boleta](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+valor.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta()+")(" 
											+ Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+registroAvc.getNroBoleta()+")";
						valor.getValorBoletaDepositoCheque().getBoleta().setNumeroBoleta(registroAvc.getNroBoleta());
					}
				}
				if(!Validaciones.isObjectNull(registroAvc.getNumeroCheque())){
					if(!registroAvc.getNumeroCheque().equals(valor.getValorBoletaDepositoCheque().getNumeroCheque())){
						datosModificados += "[Número Cheque](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+valor.getValorBoletaDepositoCheque().getNumeroCheque()+")(" 
											+ Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+registroAvc.getNumeroCheque()+")";
						valor.getValorBoletaDepositoCheque().setNumeroCheque(registroAvc.getNumeroCheque());
					}
				}
			} else {
				if (!Validaciones.isObjectNull(valor.getValorBoletaDepositoChequePD())) {
					if(!Validaciones.isObjectNull(registroAvc.getNroBoleta())){
						if(!registroAvc.getNroBoleta().equals(valor.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta())){
							datosModificados += "[Número Boleta](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+valor.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta()+")(" 
									+ Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+registroAvc.getNroBoleta()+")";
							valor.getValorBoletaDepositoChequePD().getBoleta().setNumeroBoleta(registroAvc.getNroBoleta());
						}
					}
					if(!Validaciones.isObjectNull(registroAvc.getNumeroCheque())){
						if(!registroAvc.getNumeroCheque().equals(valor.getValorBoletaDepositoChequePD().getNumeroCheque())){
							datosModificados += "[Número Cheque](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+valor.getValorBoletaDepositoChequePD().getNumeroCheque()+")(" 
									+ Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+registroAvc.getNumeroCheque()+")";
							valor.getValorBoletaDepositoChequePD().setNumeroCheque(registroAvc.getNumeroCheque());
						}
					}
				} else { 
					if (!Validaciones.isObjectNull(valor.getValorBoletaEfectivo())) {
						if(!Validaciones.isObjectNull(registroAvc.getNroBoleta())){
							if(!registroAvc.getNroBoleta().equals(valor.getValorBoletaEfectivo().getBoleta().getNumeroBoleta())){
								datosModificados += "[Número Boleta](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+valor.getValorBoletaEfectivo().getBoleta().getNumeroBoleta()+")(" 
										+ Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+registroAvc.getNroBoleta()+")";
								valor.getValorBoletaEfectivo().getBoleta().setNumeroBoleta(registroAvc.getNroBoleta());
							}
						}
					}
				}
			}
			
			// guardo en workflow Historial el cambio de los datos
			ShvWfWorkflow workflowActualizado = workflowValores.actualizarWorkflow(valor.getWorkFlow(), datosModificados, usuarioBatch);
			valor.setWorkFlow(workflowActualizado);
			return valor;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	/**
	 * Recibe un valor y genera un alta en contabilidad. 
	 * @param valor
	 * @param garantia
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	private void contabilizarValor(ShvValValor valor, boolean valorPorReversion) throws NegocioExcepcion {
		
		ContabilidadDto contabilidadDto = contabilizarValor(valor);
		contabilidadDto.setImporte(valor.getImporte());
		ContabilidadDto contabilidadGarantia = new ContabilidadDto();
		
		if (valor.getValValorCheque()!= null){
			contabilidadDto.setNumeroComprobante(valor.getValValorCheque().getNumeroCheque().toString());
			contabilidadDto.setNumeroBoleta(valor.getValValorCheque().getNumeroBoleta());
			contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.CHEQUE.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadDto.setFechaValor(valor.getValValorCheque().getFechaDeposito());
			if(valorPorReversion){
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
			} else {
				if ((valor.getParamOrigen() !=null)?valor.getParamOrigen().getIdOrigen()== Integer.valueOf(ORIGEN_CLIENTE_ID):false){
					contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
				} else {
					contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
				}
			}
		} else if (valor.getValValorChequePD() != null){
			contabilidadDto.setNumeroComprobante(valor.getValValorChequePD().getNumeroCheque().toString());
			contabilidadDto.setFechaVencimiento(valor.getValValorChequePD().getFechaVencimiento());
			contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadDto.setNumeroBoleta(valor.getValValorChequePD().getNumeroBoleta());
			contabilidadDto.setFechaValor(valor.getValValorChequePD().getFechaDeposito());
			if(valorPorReversion){
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
			} else {
				if ((valor.getParamOrigen() !=null)?valor.getParamOrigen().getIdOrigen()== Integer.valueOf(ORIGEN_CLIENTE_ID):false){
					contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
				} else {
					contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
				}
			}
		} else if (valor.getValValorEfectivo() != null){
			contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.EFECTIVO.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadDto.setNumeroBoleta(valor.getValValorEfectivo().getNumeroBoleta());
			contabilidadDto.setFechaValor(valor.getValValorEfectivo().getFechaDeposito());
			if(valorPorReversion){
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
			} else {
				if ((valor.getParamOrigen() !=null)?valor.getParamOrigen().getIdOrigen()== Integer.valueOf(ORIGEN_CLIENTE_ID):false){
					contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
				} else {
					contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
				}
			}
		} else if (valor.getValorBoletaDepositoCheque() != null){
			contabilidadDto.setNumeroComprobante(valor.getValorBoletaDepositoCheque().getNumeroCheque().toString());
			contabilidadDto.setNumeroBoleta(valor.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta());
			contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadDto.setFechaValor(valor.getValorBoletaDepositoCheque().getFechaDeposito());
			if(valorPorReversion){
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
			} else {
				if (valor.getWorkFlow().getEstado().equals(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION)){
					if((valor.getParamOrigen() !=null)?valor.getParamOrigen().getIdOrigen()!= Integer.valueOf(ORIGEN_CLIENTE_ID):false){
						contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_001);
					}
				} else {
				 	if ((valor.getParamOrigen() !=null)?valor.getParamOrigen().getIdOrigen()== Integer.valueOf(ORIGEN_CLIENTE_ID):false){
						contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
					} else {
						contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_010);
					}
				}
			}
		} else if (valor.getValorBoletaDepositoChequePD() != null){
			contabilidadDto.setNumeroComprobante(valor.getValorBoletaDepositoChequePD().getNumeroCheque().toString());
			contabilidadDto.setNumeroBoleta(valor.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta());
			contabilidadDto.setFechaVencimiento(valor.getValorBoletaDepositoChequePD().getFechaVencimiento());
			contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadDto.setFechaValor(valor.getValorBoletaDepositoChequePD().getFechaDeposito());
			if(valorPorReversion){
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
			} else {
				if (valor.getWorkFlow().getEstado().equals(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION)){
					if((valor.getParamOrigen() !=null)?valor.getParamOrigen().getIdOrigen()!= Integer.valueOf(ORIGEN_CLIENTE_ID):false){
						contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_001);
					}
				} else {
					if ((valor.getParamOrigen() !=null)?valor.getParamOrigen().getIdOrigen()== Integer.valueOf(ORIGEN_CLIENTE_ID):false
								|| valor.getValorBoletaDepositoChequePD() == null){
						contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
					} else {
						contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_010);
					}
				}
			}
		} else if (valor.getValorBoletaEfectivo() != null){
			contabilidadDto.setNumeroBoleta(valor.getValorBoletaEfectivo().getBoleta().getNumeroBoleta());
			contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadDto.setFechaValor(valor.getValorBoletaEfectivo().getFechaDeposito());
			if(valorPorReversion){
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
			} else {
				if (valor.getWorkFlow().getEstado().equals(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION)){
					if((valor.getParamOrigen() !=null)?valor.getParamOrigen().getIdOrigen()!= Integer.valueOf(ORIGEN_CLIENTE_ID):false){
						contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_001);
					}
				} else { 
					if ((valor.getParamOrigen() !=null)?valor.getParamOrigen().getIdOrigen()== Integer.valueOf(ORIGEN_CLIENTE_ID):false){
						contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
					} else {
						contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_010);
					}
				}
			}
		} else if (valor.getValValorRetencion() != null) {
			contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_002);
			contabilidadDto.setCuit(valor.getValValorRetencion().getCuit());
			if(valor.getValValorRetencion().getNroConstanciaRetencion().length() < 16){
			contabilidadDto.setNumeroComprobante(valor.getValValorRetencion().getNroConstanciaRetencion());
			} else {
				contabilidadDto.setNumeroComprobante(valor.getValValorRetencion().getNroConstanciaRetencion().substring(1,valor.getValValorRetencion().getNroConstanciaRetencion().length()));
			}
			contabilidadDto.setIdJurisdiccion((valor.getValValorRetencion().getParamJurisdiccion() != null)?valor.getValValorRetencion().getParamJurisdiccion().getProvincia():null);
			contabilidadDto.setFechaValor(valor.getFechaValor());
			List<ShvParamValorMedioPago> listaValorMP = valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValor()));
			for (ShvParamValorMedioPago valorMedioPago : listaValorMP) {
				if(valorMedioPago.getIdSubTipoValor().equals(valor.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto())){
					contabilidadDto.setIdTipoMedioPago(valorMedioPago.getTipoMedioPago());
				}
			}
		} else if (valor.getValValorTransferencia()!= null){
			contabilidadDto.setNumeroComprobante(valor.getValValorTransferencia().getNumeroReferencia().toString());
			contabilidadDto.setCuit(valor.getValValorTransferencia().getCuit());
			contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.TRANSFERENCIA.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadDto.setFechaValor(valor.getFechaValor());
			if(valorPorReversion){
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
			} else {
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
			}
		} else if (valor.getValValorInterdeposito()!= null){
			contabilidadDto.setNumeroComprobante(valor.getValValorInterdeposito().getNumeroInterdeposito().toString());
			contabilidadDto.setIdOrganismo(valor.getValValorInterdeposito().getCodigoOrganismo());
			contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadDto.setFechaValor(valor.getFechaValor());
			if(valorPorReversion){
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
			} else {
				contabilidadDto.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
			}
		}
		boolean booleanGarantia = (((valor.getMotivo() != null)?valor.getMotivo().getIdMotivo()==ContabilidadServicioImpl.VALOR_GARANTIA_ID:false)
									&& valor.getValValorRetencion() == null
									&& valor.getWorkFlow().getEstado().equals(Estado.VAL_DISPONIBLE));
		if (booleanGarantia){
			BeanUtils.copyProperties(contabilidadDto, contabilidadGarantia);
			contabilidadGarantia.setImporte(valor.getImporte());
			contabilidadGarantia.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_030);
			contabilidadGarantia.setFechaValor(null);
			contabilidadGarantia.setFechaVencimiento(null);
		}
		if (contabilidadDto.getDescripcionTipoOrigenContable() != null){
			contabilidadServicio.contabilizar(contabilidadDto);
		}
		if (contabilidadGarantia.getDescripcionTipoOrigenContable() != null){
			contabilidadServicio.contabilizar(contabilidadGarantia);
		}
	}
	
	/**
	 * 
	 * @param valValorViejo
	 * @param clienteViejo
	 * @param idMotivoViejo
	 * @param valValorNuevo
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	private void contabilizarModificacion(ShvValValor valValorViejo,String clienteViejo, Integer idMotivoViejo,ShvValValor valValorNuevo) throws NegocioExcepcion, PersistenciaExcepcion {
		ContabilidadDto contabilidadNuevo = contabilizarValor(valValorNuevo);
		ContabilidadDto contabilidadViejo = contabilizarValor(valValorViejo);
		ContabilidadDto contabilidadGarantia = new ContabilidadDto();
		ContabilidadDto contabilidadGarantiaViejo = new ContabilidadDto();
		
		contabilidadNuevo.setImporte(valValorNuevo.getImporte());
		contabilidadViejo.setCodigoClienteLegado(Long.valueOf(clienteViejo));
		contabilidadViejo.setImporte(valValorViejo.getImporte().negate());
		contabilidadViejo.setEstadoInactividad(ContabilidadServicioImpl.ESTADO_INACTIVIDAD);
		
		boolean valorGarantia = false;
		boolean bajaGarantia = false;
		boolean ambasGarantia = false;
		int idMotivo = (valValorNuevo.getMotivo() != null)?valValorNuevo.getMotivo().getIdMotivo():0;
		boolean motivoViejo = (idMotivoViejo != null)?idMotivoViejo==8:false;
		if (idMotivo == 8 || motivoViejo ){
			valorGarantia = true;
			if (idMotivo != ((idMotivoViejo != null)?idMotivoViejo.intValue():0)){
				if ((idMotivoViejo != null)?idMotivoViejo==8:false){
					bajaGarantia = true;
				}
			} else {
				ambasGarantia = true;
			}
		}
		String origen = (valValorNuevo.getParamOrigen() != null)?valValorNuevo.getParamOrigen().getIdOrigen().toString():"";
		if (valValorNuevo.getValValorRetencion() != null){
			if (!valValorNuevo.getIdClienteLegado().toString().equals(clienteViejo)){
				List<ShvParamValorMedioPago> listaValorMP = valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValor()));
				for (ShvParamValorMedioPago valorMedioPago : listaValorMP) {
					if(valorMedioPago.getIdSubTipoValor().equals(valValorNuevo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto())){
						contabilidadNuevo.setIdTipoMedioPago(valorMedioPago.getTipoMedioPago());
					}
				}
				contabilidadNuevo.setCuit(valValorNuevo.getValValorRetencion().getCuit());
				if(valValorNuevo.getValValorRetencion().getNroConstanciaRetencion().length() < 16){
					contabilidadNuevo.setNumeroComprobante(valValorNuevo.getValValorRetencion().getNroConstanciaRetencion());
				} else {
					contabilidadNuevo.setNumeroComprobante(valValorNuevo.getValValorRetencion().getNroConstanciaRetencion().substring(1,valValorNuevo.getValValorRetencion().getNroConstanciaRetencion().length()));
				}
				contabilidadNuevo.setIdJurisdiccion((valValorNuevo.getValValorRetencion().getParamJurisdiccion() != null)?valValorNuevo.getValValorRetencion().getParamJurisdiccion().getProvincia():null);
				contabilidadNuevo.setFechaValor(valValorNuevo.getFechaValor());
				contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_002);
				contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_002);
				contabilidadViejo.setCuit(valValorViejo.getValValorRetencion().getCuit());
				contabilidadViejo.setNumeroComprobante(contabilidadNuevo.getNumeroComprobante());
				contabilidadViejo.setIdJurisdiccion((valValorViejo.getValValorRetencion().getParamJurisdiccion() != null)?valValorViejo.getValValorRetencion().getParamJurisdiccion().getProvincia():null);
				contabilidadViejo.setIdTipoMedioPago(contabilidadNuevo.getIdTipoMedioPago());
				contabilidadViejo.setFechaValor(valValorViejo.getFechaValor());
			}
		} else if (valValorNuevo.getValValorCheque()!= null){
			contabilidadNuevo.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.CHEQUE.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadNuevo.setNumeroComprobante(String.valueOf(valValorNuevo.getValValorCheque().getNumeroCheque()));
			contabilidadNuevo.setNumeroBoleta(valValorNuevo.getValValorCheque().getNumeroBoleta());
			contabilidadNuevo.setFechaValor(valValorNuevo.getValValorCheque().getFechaDeposito());
			if (!valValorNuevo.getIdClienteLegado().toString().equals(clienteViejo)){
				if(origen.equals(ORIGEN_REVERSION)){
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
				} else {
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
				}
				contabilidadViejo.setIdTipoMedioPago(contabilidadNuevo.getIdTipoMedioPago());
				contabilidadViejo.setNumeroComprobante(String.valueOf(valValorViejo.getValValorCheque().getNumeroCheque()));
				contabilidadViejo.setNumeroBoleta(valValorViejo.getValValorCheque().getNumeroBoleta());
				contabilidadViejo.setFechaValor(valValorViejo.getValValorCheque().getFechaDeposito());
			}
		} else if (valValorNuevo.getValValorChequePD() != null){
			contabilidadNuevo.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadNuevo.setNumeroComprobante(String.valueOf(valValorNuevo.getValValorChequePD().getNumeroCheque()));
			contabilidadNuevo.setFechaVencimiento(valValorNuevo.getValValorChequePD().getFechaVencimiento());
			contabilidadNuevo.setNumeroBoleta(valValorNuevo.getValValorChequePD().getNumeroBoleta());
			contabilidadNuevo.setFechaValor(valValorNuevo.getValValorChequePD().getFechaDeposito());
			if (!valValorNuevo.getIdClienteLegado().toString().equals(clienteViejo)){
				if(origen.equals(ORIGEN_REVERSION)){
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
				} else {
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
				}
				contabilidadViejo.setIdTipoMedioPago(contabilidadNuevo.getIdTipoMedioPago());
				contabilidadViejo.setFechaVencimiento(valValorViejo.getValValorChequePD().getFechaVencimiento());
				contabilidadViejo.setNumeroComprobante(String.valueOf(valValorViejo.getValValorChequePD().getNumeroCheque()));
				contabilidadViejo.setNumeroBoleta(valValorViejo.getValValorChequePD().getNumeroBoleta());
				contabilidadViejo.setFechaValor(valValorViejo.getValValorChequePD().getFechaDeposito());
			}
		} else if (valValorNuevo.getValValorEfectivo() != null){
			contabilidadNuevo.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.EFECTIVO.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadNuevo.setNumeroBoleta(contabilidadNuevo.getNumeroBoleta());
			contabilidadNuevo.setFechaValor(valValorNuevo.getValValorEfectivo().getFechaDeposito());
			if (!valValorNuevo.getIdClienteLegado().toString().equals(clienteViejo)){
				if(origen.equals(ORIGEN_REVERSION)){
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
				} else {
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
				}
				contabilidadViejo.setIdTipoMedioPago(contabilidadNuevo.getIdTipoMedioPago());
				contabilidadViejo.setNumeroBoleta(valValorViejo.getValValorEfectivo().getNumeroBoleta());
				contabilidadViejo.setFechaValor(valValorViejo.getValValorEfectivo().getFechaDeposito());
			}
		} else if (valValorNuevo.getValValorTransferencia()!= null){
			contabilidadNuevo.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.TRANSFERENCIA.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadNuevo.setCuit(valValorNuevo.getValValorTransferencia().getCuit());
			contabilidadNuevo.setNumeroComprobante(String.valueOf(valValorNuevo.getValValorTransferencia().getNumeroReferencia()));
			contabilidadNuevo.setFechaValor(valValorNuevo.getFechaValor());
			if (!valValorNuevo.getIdClienteLegado().toString().equals(clienteViejo)){
				if(origen.equals(ORIGEN_REVERSION)){
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
				} else {
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
				}
				contabilidadViejo.setIdTipoMedioPago(contabilidadNuevo.getIdTipoMedioPago());
				if(valValorViejo.getValValorTransferencia().getBancoOrigen()!=null) {
					contabilidadViejo.setCuit(valValorViejo.getValValorTransferencia().getBancoOrigen().getCuit());
				}
				contabilidadViejo.setNumeroComprobante(String.valueOf(valValorViejo.getValValorTransferencia().getNumeroReferencia()));
				contabilidadViejo.setFechaValor(valValorViejo.getFechaValor());
			}
		} else if (valValorNuevo.getValValorInterdeposito()!= null){
			contabilidadNuevo.setIdOrganismo(valValorNuevo.getValValorInterdeposito().getCodigoOrganismo());
			contabilidadNuevo.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadNuevo.setNumeroComprobante(String.valueOf(valValorNuevo.getValValorInterdeposito().getNumeroInterdeposito()));
			contabilidadNuevo.setFechaValor(valValorNuevo.getFechaValor());
			if (!valValorNuevo.getIdClienteLegado().toString().equals(clienteViejo)){
				if(origen.equals(ORIGEN_REVERSION)){
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_020);
				} else {
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_011);
				}
				contabilidadViejo.setIdTipoMedioPago(contabilidadNuevo.getIdTipoMedioPago());
				contabilidadViejo.setIdOrganismo(contabilidadNuevo.getIdOrganismo());
				contabilidadViejo.setNumeroComprobante(String.valueOf(valValorViejo.getValValorInterdeposito().getNumeroInterdeposito()));
				contabilidadViejo.setFechaValor(valValorViejo.getFechaValor());
			}
		} else if (valValorNuevo.getValorBoletaDepositoCheque() != null){
			contabilidadNuevo.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadNuevo.setNumeroBoleta(valValorNuevo.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta());
			contabilidadNuevo.setNumeroComprobante(String.valueOf(valValorNuevo.getValorBoletaDepositoCheque().getNumeroCheque()));
			contabilidadNuevo.setFechaValor(valValorNuevo.getValorBoletaDepositoCheque().getFechaDeposito());
			if (!valValorNuevo.getIdClienteLegado().toString().equals(clienteViejo)){
				if (origen.equals(ORIGEN_CLIENTE_ID)) {
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
				} else {
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_010);
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_010);
				}
				contabilidadViejo.setIdTipoMedioPago(contabilidadNuevo.getIdTipoMedioPago());
				contabilidadViejo.setNumeroBoleta(valValorViejo.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta());
				contabilidadViejo.setNumeroComprobante(String.valueOf(valValorViejo.getValorBoletaDepositoCheque().getNumeroCheque()));
				contabilidadViejo.setFechaValor(valValorViejo.getValorBoletaDepositoCheque().getFechaDeposito());
			}
		} else if (valValorNuevo.getValorBoletaDepositoChequePD() != null){
			contabilidadNuevo.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadNuevo.setNumeroBoleta(valValorNuevo.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta());
			contabilidadNuevo.setNumeroComprobante(String.valueOf(valValorNuevo.getValorBoletaDepositoChequePD().getNumeroCheque()));
			contabilidadNuevo.setFechaVencimiento(valValorNuevo.getValorBoletaDepositoChequePD().getFechaVencimiento());
			contabilidadNuevo.setFechaValor(valValorNuevo.getValorBoletaDepositoChequePD().getFechaDeposito());
			if (!valValorNuevo.getIdClienteLegado().toString().equals(clienteViejo)){
				if (origen.equals(ORIGEN_CLIENTE_ID)) {
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
				} else {
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_010);
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_010);
				}
				contabilidadViejo.setIdTipoMedioPago(contabilidadNuevo.getIdTipoMedioPago());
				contabilidadViejo.setNumeroBoleta(valValorViejo.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta());
				contabilidadViejo.setNumeroComprobante(String.valueOf(valValorViejo.getValorBoletaDepositoChequePD().getNumeroCheque()));
				contabilidadViejo.setFechaValor(valValorViejo.getValorBoletaDepositoChequePD().getFechaDeposito());
			}
		} else if (valValorNuevo.getValorBoletaEfectivo() != null){
			contabilidadNuevo.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor())).get(0).getTipoMedioPago());
			contabilidadNuevo.setNumeroBoleta(valValorNuevo.getValorBoletaEfectivo().getBoleta().getNumeroBoleta());
			contabilidadNuevo.setFechaValor(valValorNuevo.getValorBoletaEfectivo().getFechaDeposito());
			if (!valValorNuevo.getIdClienteLegado().toString().equals(clienteViejo)){
				if (origen.equals(ORIGEN_CLIENTE_ID)) {
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_013);
				} else {
					contabilidadNuevo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_010);
					contabilidadViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_010);
				}
				contabilidadViejo.setIdTipoMedioPago(contabilidadNuevo.getIdTipoMedioPago());
				contabilidadViejo.setNumeroBoleta(valValorViejo.getValorBoletaEfectivo().getBoleta().getNumeroBoleta());
				contabilidadViejo.setFechaValor(valValorViejo.getValorBoletaEfectivo().getFechaDeposito());
			}
		}
		if (valorGarantia && valValorNuevo.getValValorRetencion() == null){
			BeanUtils.copyProperties(contabilidadNuevo, contabilidadGarantia);
			if (ambasGarantia){
				contabilidadGarantia.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_030);
				BeanUtils.copyProperties(contabilidadViejo, contabilidadGarantiaViejo);
				contabilidadGarantiaViejo.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_031);
				contabilidadGarantiaViejo.setImporte(valValorViejo.getImporte());
			} else if (!bajaGarantia){
				contabilidadGarantia.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_030);
			} else {
				contabilidadGarantia.setDescripcionTipoOrigenContable(ContabilidadServicioImpl.ORIGEN_CONT_031);
			}
			contabilidadGarantia.setEstadoInactividad(ContabilidadServicioImpl.ESTADO_INACTIVIDAD);
			contabilidadGarantia.setFechaValor(null);
			contabilidadGarantia.setFechaVencimiento(null);
			contabilidadGarantiaViejo.setEstadoInactividad(ContabilidadServicioImpl.ESTADO_INACTIVIDAD);
			contabilidadGarantiaViejo.setFechaValor(null);
			contabilidadGarantiaViejo.setFechaVencimiento(null);
		}
		if (contabilidadViejo.getDescripcionTipoOrigenContable() != null){
			contabilidadServicio.desactivarDetalle(valValorViejo.getIdValor(),false);
			contabilidadServicio.contabilizar(contabilidadViejo);
		} else if (bajaGarantia) {
			contabilidadServicio.desactivarDetalle(valValorViejo.getIdValor(),true);
		}
		if (contabilidadNuevo.getDescripcionTipoOrigenContable() != null){
			contabilidadServicio.contabilizar(contabilidadNuevo);
		}
		if (contabilidadGarantia.getDescripcionTipoOrigenContable() != null){
			contabilidadServicio.contabilizar(contabilidadGarantia);
		}
		if (contabilidadGarantiaViejo.getDescripcionTipoOrigenContable() != null){
			contabilidadServicio.contabilizar(contabilidadGarantiaViejo);
		}
	}
	
	/**
	 * Al momento de confirmar el alta de un legajo de cheque rechazado, si el cheque asociado es de Shiva 
	 * lo suspendemos con motivo cheque rechazado, y generamos movimiento “043 - Suspensión de Cheque por Rechazo”
	 * 
	 * @param idValor
	 * @throws NegocioExcepcion
	 */
	private void contabilizarValorSuspendidoMotivoChequeRechazado(String idValor, Date fechaRechazo) throws NegocioExcepcion {
		
		VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro(); 
		filtro.setIdValor(idValor);
		
		VistaSoporteResultadoBusquedaValor valor = vistaSoporteServicio.buscarValores(filtro).iterator().next();
		 
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		
		contabilidadDto.setIdValor(valor.getIdValor());
		contabilidadDto.setImporte(valor.getImporte());
		contabilidadDto.setMoneda(valor.getMoneda().name());
		contabilidadDto.setCodigoClienteLegado(new Long(valor.getIdClienteLegado()));
		contabilidadDto.setIdAnalista(valor.getIdAnalista());
		contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(valor.getIdTipoValor()).get(0).getTipoMedioPago());
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$043.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());
		contabilidadDto.setFechaValor(fechaRechazo);
		contabilidadDto.setTransaccion(valor.getIdValor().toString());
		
		if (!Validaciones.isObjectNull(valor.getNroBoleta())) {
			contabilidadDto.setNumeroBoleta(new Long(valor.getNroBoleta()));	
		}
		
		contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());

		contabilidadServicio.contabilizar(contabilidadDto);
	}
	
	/**
	 * Al momento de anular el alta de un legajo de cheque rechazado, si el cheque asociado es de Shiva 
	 * lo pasamos a disponible o usado según corresponda, y generamos movimiento “043 - Suspensión de Cheque por Rechazo” con importe negado
	 * 
	 * @param idValor
	 * @throws NegocioExcepcion
	 */
	private void anularContabilidadValorSuspendidoMotivoChequeRechazado(String idValor, Date fechaRechazo) throws NegocioExcepcion {
		
		VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro(); 
		filtro.setIdValor(idValor);
		
		VistaSoporteResultadoBusquedaValor valor = vistaSoporteServicio.buscarValores(filtro).iterator().next();
		 
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		
		contabilidadDto.setIdValor(valor.getIdValor());
		contabilidadDto.setImporte(valor.getImporte().negate());
		contabilidadDto.setMoneda(valor.getMoneda().name());
		contabilidadDto.setCodigoClienteLegado(new Long(valor.getIdClienteLegado()));
		contabilidadDto.setIdAnalista(valor.getIdAnalista());
		contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(valor.getIdTipoValor()).get(0).getTipoMedioPago());
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$043.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());
		contabilidadDto.setFechaValor(fechaRechazo);
		contabilidadDto.setTransaccion(valor.getIdValor().toString());
		
		if (!Validaciones.isObjectNull(valor.getNroBoleta())) {
			contabilidadDto.setNumeroBoleta(new Long(valor.getNroBoleta()));	
		}
		
		contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());

		contabilidadServicio.contabilizar(contabilidadDto);
	}
	
	/**
	 * Al momento de confirmar el alta de un legajo de cheque rechazado, si el cheque asociado es de Shiva 
	 * y corresponde anularlo, se debe generar el movimiento contable "015 - Baja Cheque Rechazado"
	 * 
	 * @param idValor
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion 
	 */
	private void contabilizarValorAnuladoMotivoChequeRechazado(String idValor, Date fechaRechazo) throws NegocioExcepcion, PersistenciaExcepcion {

		VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro(); 
		filtro.setIdValor(idValor);
		
		VistaSoporteResultadoBusquedaValor valor = vistaSoporteServicio.buscarValores(filtro).iterator().next();
		 
		ContabilidadDto contabilidadDto = new ContabilidadDto();
		
		contabilidadDto.setIdValor(valor.getIdValor());
		contabilidadDto.setImporte(valor.getImporte());
		contabilidadDto.setMoneda(valor.getMoneda().name());
		contabilidadDto.setCodigoClienteLegado(new Long(valor.getIdClienteLegado()));
		contabilidadDto.setIdAnalista(valor.getIdAnalista());
		contabilidadDto.setIdTipoMedioPago(valorMedioPagoServicio.buscarPorTipoValor(valor.getIdTipoValor()).get(0).getTipoMedioPago());
		contabilidadDto.setDescripcionTipoOrigenContable(OrigenContableEnum.$015.codigo());
		contabilidadDto.setEstado(EstadoContabilidadEnum.PENDIENTE.codigo());
		contabilidadDto.setFechaValor(fechaRechazo);
		contabilidadDto.setTransaccion(valor.getIdValor().toString());
		
		ShvParamAcuerdo acuerdo = acuerdoDao.buscarAcuerdo(valor.getIdAcuerdo());
		
		if (!Validaciones.isObjectNull(acuerdo)){
			contabilidadDto.setIdAcuerdo(acuerdo);
		}
			
		if (!Validaciones.isObjectNull(valor.getNroBoleta())) {
			contabilidadDto.setNumeroBoleta(new Long(valor.getNroBoleta()));	
		}
		
		contabilidadDto.setNumeroComprobante(valor.getReferenciaValor());

		contabilidadServicio.contabilizar(contabilidadDto);
	}
	

	public ShvValValor buscarValValorPorIdValor(Integer idValor)  throws NegocioExcepcion {
		try {
			return valorDao.buscarValor(String.valueOf(idValor));
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Busca si existe un valor creado anteriormente para ese id de registro y lo retorna
	 */
	public ValorDto buscarValorCreadoAPartirRegistroAvc(String idRegistro)throws NegocioExcepcion {
		try {
			ShvValValor valor = valorDao.buscarValorCreadoAPartirRegistroAvc(idRegistro);
			if(!Validaciones.isObjectNull(valor)){
				return (ValorDto) defaultMapeador.map(valor);
			}
			return null;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Crea un ShvValValorAux. Este metodo se usa para el caso de reversion de cobros.
	 * Ya que los datos que vienen por los archivos no son suficientes para dar de alta
	 * un ShvValValor.
	 */
	public Long crearValorAux(DTO dto) throws NegocioExcepcion {

		ValorDto valorDto = (ValorDto) dto;
		try {
			//Mapear
			ShvValValorPorReversion valor = new ShvValValorPorReversion();
			valor.setImporte(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			valor.setSaldoDisponible(Utilidad.stringToBigDecimal(valorDto.getSaldoDisponible()));
			valor.setIdTipoValor(Long.valueOf(valorDto.getIdTipoValor()));
	    	valor.setIdAcuerdo(Long.valueOf(valorDto.getIdAcuerdo()));
	    	
	    	if(!Validaciones.isNullOrEmpty(valorDto.getBancoOrigen())){
	    		valor.setIdBancoOrigen(valorDto.getBancoOrigen());
	    	}
	    	if(!Validaciones.isNullOrEmpty(valorDto.getNumeroCheque())){
	    		valor.setNumeroCheque(Long.valueOf(valorDto.getNumeroCheque()));
	    	}
	    	try {
	    		if(!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())){
	    			valor.setFechaDeposito(Utilidad.deserializeAndFormatDate(valorDto.getFechaDeposito(),Utilidad.DATE_FORMAT_PICKER));
	    		}
	    		if(!Validaciones.isNullOrEmpty(valorDto.getFechaVencimiento())){
	    			valor.setFechaVencimiento(Utilidad.deserializeAndFormatDate(valorDto.getFechaVencimiento(),Utilidad.DATE_FORMAT_PICKER));
	    		}
	    	} catch (ParseException e) {
	    		throw new NegocioExcepcion(e.getMessage(),e);
	    	}
	    	if(!Validaciones.isNullOrEmpty(valorDto.getNumeroBoleta())){
	    		valor.setNumeroBoleta(Long.valueOf(valorDto.getNumeroBoleta()));
	    	}
	    	if(!Validaciones.isNullOrEmpty(valorDto.getNumeroReferencia())){
	    		valor.setNumeroReferencia(Long.valueOf(valorDto.getNumeroReferencia()));
	    	}
	    	if(!Validaciones.isNullOrEmpty(valorDto.getCodOrganismo())){
	    		valor.setCodigoOrganismo(valorDto.getCodOrganismo());
	    	}
	    	
			valorDao.insertarValorAux(valor);
			return valor.getIdValorPorReversion();
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public ShvValValor buscarValorPorIdBoleta(Long idBoleta) throws NegocioExcepcion {
		try {
			return valorDao.buscarValorTipoDepositoPorIdBoleta(idBoleta);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	public List<ShvValValor> listarRetencionesParaReporte(Filtro retencionFiltro) throws NegocioExcepcion {
		try {
			return valorDao.buscarRetencionesParaReporte(retencionFiltro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public void generarReporteMorosidad(String fechaHasta) throws NegocioExcepcion {
		try {

			Integer cantReg = 0;
			Filtro morosidadFiltro = new Filtro();
			morosidadFiltro.setFechaHasta(fechaHasta);
//			List<ShvValValor> reporteMorosidad = valorDao.listarMorosidadValores(morosidadFiltro);
			List<ShvValValoresVista> reporteMorosidad = valorDao.listarMorosidadValores(morosidadFiltro);

			Traza.auditoria(ReporteMorosidadBatchRunner.class, "----Recuperando " + reporteMorosidad.size() + " Valores.");
			
			List<StringBuffer> listaTxtArchivoMorosidad = new ArrayList<StringBuffer>();
//			for (ShvValValor valor : reporteMorosidad) {
			for (ShvValValoresVista valor : reporteMorosidad) {
//				TODO se comento para volver a la version anterior
//				listaTxtArchivoMorosidad.add(valorToStringBuffer(valor));
				listaTxtArchivoMorosidad.add(getValorVistaStringBuffer(valor));				
				cantReg++;
			}
			String pie = "00|" + cantReg;
			crearArchivoReporteMorosidad(listaTxtArchivoMorosidad, pie,fechaHasta);
			
			Traza.auditoria(ReporteMorosidadBatchRunner.class, "----Se ha creado el archivo con " + cantReg + " registros.");

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public void crearArchivoReporteMorosidad(List<StringBuffer> listaArchivoMorosidad, String pie, String fechaHasta)
			throws NegocioExcepcion {
		try {

			String carpetaDestino = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.morosidad");
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
			
			Date fechaDelDia = null;
			try {

				fechaDelDia = formatoDelTexto.parse(fechaHasta);

			} catch (ParseException e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
			
			ControlArchivo.escribirArchivos(listaArchivoMorosidad,carpetaDestino,NOMBRE_ARCHIVO_MOROSIDAD + Utilidad.formatDateAAAAMMDD(fechaDelDia) + EXT_ARCH,pie);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Calcula la fecha valor segun el origen del valor. Metodo utilizado al 
	 * conciliar boletas con valor.
	 * @param valor
	 * @return fecha valor
	 */
	@Override
	public Date calcularFechaValor(ShvValValor valor, Date fechaDepositoRegistroAvc) {
		
		Integer idOrigen = null;
		
		Date fechaAlta = valor.getFechaAlta(); 
		Date fechaDeposito = fechaDepositoRegistroAvc;
		Date fechaRecibo = null;
		Date fechaVencimiento = null;
		Date fechaTransferencia = null;
		Date fechaValor = null;

		if (!Validaciones.isObjectNull(valor.getValorBoletaDepositoCheque())) {
			idOrigen = valor.getParamOrigen().getIdOrigen();
			fechaRecibo = valor.getValorBoletaDepositoCheque().getFechaRecibo();
			
			fechaValor = this.calcularFechaValor(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE, idOrigen, fechaAlta,
					fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia, valor.getFechaNotificacionDisponibilidadRetiroValor(), valor.getValorBoletaDepositoCheque().getFechaEmision());
		} else {
			if(!Validaciones.isObjectNull(valor.getValorBoletaEfectivo())){
				idOrigen = valor.getParamOrigen().getIdOrigen();
				fechaRecibo = valor.getValorBoletaEfectivo().getFechaRecibo();
				
				fechaValor = this.calcularFechaValor(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO, idOrigen, fechaAlta, 
						fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia, valor.getFechaNotificacionDisponibilidadRetiroValor(), null);
			} else {
				if(!Validaciones.isObjectNull(valor.getValorBoletaDepositoChequePD())){
					idOrigen = valor.getParamOrigen().getIdOrigen();
					fechaDeposito = valor.getValorBoletaDepositoChequePD().getFechaDeposito();
					fechaVencimiento = valor.getValorBoletaDepositoChequePD().getFechaVencimiento();
					fechaRecibo = valor.getValorBoletaDepositoChequePD().getFechaRecibo();
		
					fechaValor = this.calcularFechaValor(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO, idOrigen, fechaAlta, 
							fechaDeposito, fechaVencimiento, fechaRecibo, fechaTransferencia, valor.getFechaNotificacionDisponibilidadRetiroValor(), null);
				}
			}
		}
		if (!Validaciones.isObjectNull(valor.getValValorEfectivo())) {
			fechaDeposito = valor.getValValorEfectivo().getFechaDeposito();
			fechaRecibo = valor.getValValorEfectivo().getFechaRecibo();
			fechaValor = this.calcularFechaValor(
				TipoValorEnum.EFECTIVO,
				null,
				null,
				fechaDeposito,
				null,
				fechaRecibo,
				null,
				null,
				null
			);
		} else if (!Validaciones.isObjectNull(valor.getValValorCheque())) {
			fechaDeposito = valor.getValValorCheque().getFechaDeposito();
			fechaRecibo = valor.getValValorCheque().getFechaRecibo();
			fechaValor = this.calcularFechaValor(
				TipoValorEnum.CHEQUE,
				null,
				null,
				fechaDeposito,
				null,
				fechaRecibo,
				null,
				null,
				null
			);
		} else if (!Validaciones.isObjectNull(valor.getValValorChequePD())) {
			fechaDeposito = valor.getValValorChequePD().getFechaDeposito();
			fechaRecibo = valor.getValValorChequePD().getFechaRecibo();
			fechaVencimiento = valor.getValValorChequePD().getFechaVencimiento();
			fechaValor = this.calcularFechaValor(
				TipoValorEnum.CHEQUE_DIFERIDO,
				null,
				null,
				fechaDeposito,
				fechaVencimiento,
				fechaRecibo,
				null,
				null,
				null
			);
		} else if (!Validaciones.isObjectNull(valor.getValValorRetencion())) {
			fechaRecibo = valor.getValValorRetencion().getFechaRecibo();
			fechaValor = this.calcularFechaValor(
				TipoValorEnum.RETENCIÓN_IMPUESTO,
				null,
				null,
				null,
				null,
				fechaRecibo,
				null,
				null,
				null
			);
		}
		return fechaValor;
	}
	
	/**
	 * Calcula la fecha valor segun el origen del valor. Metodo utilizado al 
	 * conciliar boletas con valor.
	 * @param tipoValor
	 * @param idOrigen
	 * @param fechaAlta
	 * @param fechaDeposito
	 * @param fechaVencimiento
	 * @param fechaRecibo
	 * @param fechaTransferencia
	 * @parma fechaNotificacionDisponibilidadRetiroValor
	 * @param fechaEmision
	 * @return
	 */
	@Override
	public Date calcularFechaValor(TipoValorEnum tipoValor, Integer idOrigen,  
			Date fechaAlta, Date fechaDeposito, Date fechaVencimiento, 
			Date fechaRecibo, Date fechaTransferencia, Date fechaNotificacionDisponibilidadRetiroValor, Date fechaEmision) {

		Date retorno = null;
		switch (tipoValor) {
			case EFECTIVO: // Aviso de pago
				if (!Validaciones.isObjectNull(fechaNotificacionDisponibilidadRetiroValor)) {
					retorno = fechaNotificacionDisponibilidadRetiroValor;
				} else {
					if (Validaciones.isObjectNull(fechaRecibo)) {
						retorno = fechaDeposito;	
					} else {
						retorno = fechaRecibo;
					}
					break;
				}				
			case CHEQUE: // Aviso de pago
				if (!Validaciones.isObjectNull(fechaNotificacionDisponibilidadRetiroValor)) {
					retorno = fechaNotificacionDisponibilidadRetiroValor;
				} else {
					if (Validaciones.isObjectNull(fechaRecibo)) {
						retorno = fechaDeposito;	
					} else {
						retorno = fechaRecibo;
					}
				}
				break;
			case CHEQUE_DIFERIDO:// Aviso de pago
				Date fecha = null;
				if (!Validaciones.isObjectNull(fechaNotificacionDisponibilidadRetiroValor)) {
					fecha = fechaNotificacionDisponibilidadRetiroValor;
				} else {
					if (Validaciones.isObjectNull(fechaRecibo)) {
						fecha = fechaDeposito;	
					} else {
						fecha = fechaRecibo;
					}
				}
				if (fechaVencimiento.after(fecha)) {
					retorno = fechaVencimiento;
				}else{
					retorno = fecha;
				}
				break;
				
			case RETENCIÓN_IMPUESTO:// Aviso de pago
				if (!Validaciones.isObjectNull(fechaNotificacionDisponibilidadRetiroValor)) {
					retorno = fechaNotificacionDisponibilidadRetiroValor;
				} else {
					retorno = fechaRecibo;
				}
				break;
				
			case TRANSFERENCIA:// Aviso de pago
				retorno = fechaTransferencia;
				break;
				
			case INTERDEPÓSITO:// Aviso de pago
				retorno = fechaDeposito;
				break;
				
			case BOLETA_DEPOSITO_EFECTIVO:
				if (ORIGEN_CLIENTE_ID.equals(idOrigen.toString())) {
					retorno = fechaDeposito;
				} else if (ORIGEN_OFICINA_CONSTANCIA_AUTOMATICA_ID.equalsIgnoreCase(idOrigen.toString())) {
					retorno = fechaAlta;
				} else if (
					ORIGEN_CAJERO_PAGADOR_ID.equalsIgnoreCase(idOrigen.toString()) ||
					ORIGEN_OFICINA_RECIBO_PREIMPRESO_ID.equalsIgnoreCase(idOrigen.toString())
				) {
					if (Validaciones.isObjectNull(fechaNotificacionDisponibilidadRetiroValor)) {
						retorno = fechaRecibo;
					} else {
						retorno = fechaNotificacionDisponibilidadRetiroValor;
					}
				}
				break;

			case BOLETA_DEPOSITO_CHEQUE:
				if (ORIGEN_CLIENTE_ID.equals(idOrigen.toString())) {
					retorno = fechaDeposito;
				} else if (ORIGEN_OFICINA_CONSTANCIA_AUTOMATICA_ID.equals(idOrigen.toString())) {
					retorno = fechaAlta;
				} else if (
					ORIGEN_CAJERO_PAGADOR_ID.equals(idOrigen.toString()) ||
					ORIGEN_OFICINA_RECIBO_PREIMPRESO_ID.equals(idOrigen.toString())
				) {
					if (Validaciones.isObjectNull(fechaNotificacionDisponibilidadRetiroValor)) {
						retorno = fechaRecibo;
					} else {
						retorno = fechaNotificacionDisponibilidadRetiroValor;
					}
				} else  if (ORIGEN_BANCO_ID.equals(idOrigen.toString())) {
					retorno = fechaEmision;
				}
				break;

			case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
				if (ORIGEN_CLIENTE_ID.equals(idOrigen.toString())) {
					if (Validaciones.isObjectNull(fechaDeposito)) {
						retorno = fechaVencimiento;
					} else {
						if (fechaVencimiento.after(fechaDeposito)) {
							retorno = fechaVencimiento;
						} else {
							retorno = fechaDeposito;
						}
					}
				} else if (
					ORIGEN_CAJERO_PAGADOR_ID.equals(idOrigen.toString()) ||
					ORIGEN_OFICINA_RECIBO_PREIMPRESO_ID.equals(idOrigen.toString())
				) {
					if (Validaciones.isObjectNull(fechaNotificacionDisponibilidadRetiroValor)) {
						retorno = fechaRecibo;
					} else {
						retorno = fechaNotificacionDisponibilidadRetiroValor;
					}
					if (fechaVencimiento.after(retorno)) {
						retorno = fechaVencimiento;
					}
				} else if (ORIGEN_OFICINA_CONSTANCIA_AUTOMATICA_ID.equals(idOrigen.toString())) {
					if (Validaciones.isObjectNull(fechaAlta)) {
						retorno = fechaVencimiento;
					} else if (fechaVencimiento.after(fechaAlta)) {
						retorno = fechaVencimiento;
					} else {
						retorno = fechaAlta;
					}
				} else  if (ORIGEN_BANCO_ID.equals(idOrigen.toString())) {
					retorno = fechaVencimiento;
				}
				break;
			default:
				break;
		}
		return retorno;
	}
	
	/**
	 * Setea el numero Valor segun los datos del valor
	 * @param valorViejo, valorNuevo 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvValValor setearNumeroValor(ShvValValor valorViejo, ShvValValor valorNuevo) throws NegocioExcepcion{
		StringBuffer strBuffer = null;
		switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorViejo.getTipoValor().getIdTipoValor()))) {
			
			case EFECTIVO:
				valorNuevo.setNumeroValor(Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_BOLETA, valorViejo.getValValorEfectivo().getNumeroBoleta().toString()));
				break;

			case CHEQUE:
				valorNuevo.setNumeroValor(Utilidad.reemplazarMensajes(Mensajes.CAMPO_BANCO_DESC, valorViejo.getValValorCheque().getBancoOrigen().getDescripcion()) 
						+ " " + Constantes.SEPARADOR_PIPE + " "
						+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CHEQUE, valorViejo.getValValorCheque().getNumeroCheque().toString())
						+ " " + Constantes.SEPARADOR_PIPE + " "
						+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_FECHA_EMISION,
								(!Validaciones.isObjectNull(valorViejo.getValValorCheque().getFechaEmision()))? Utilidad.formatDatePicker(valorViejo.getValValorCheque().getFechaEmision()) :""));
				break;
	
			case CHEQUE_DIFERIDO:
				valorNuevo.setNumeroValor(Utilidad.reemplazarMensajes(Mensajes.CAMPO_BANCO_DESC, valorViejo.getValValorChequePD().getBancoOrigen().getDescripcion()) 
						+ " " + Constantes.SEPARADOR_PIPE + " " 
						+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CHEQUE, valorViejo.getValValorChequePD().getNumeroCheque().toString())
						+ " " + Constantes.SEPARADOR_PIPE + " "
						+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_FECHA_EMISION, 
								(!Validaciones.isObjectNull(valorViejo.getValValorChequePD().getFechaEmision()))? Utilidad.formatDatePicker(valorViejo.getValValorChequePD().getFechaEmision()) :"")
						+ " "+ Constantes.SEPARADOR_PIPE + " "
						+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_FECHA_VTO, Utilidad.formatDatePicker(valorViejo.getValValorChequePD().getFechaVencimiento()))
						);
				break;
				
			case RETENCIÓN_IMPUESTO:
				String numeroValor = Utilidad.reemplazarMensajes(Mensajes.CAMPO_TIPO, valorViejo.getValValorRetencion().getParamTipoRetencionImpuesto().getDescripcion())
					+" "+ Constantes.SEPARADOR_PIPE + " " 
					+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CONSTANCIA, valorViejo.getValValorRetencion().getNroConstanciaRetencion())
					+" "+ Constantes.SEPARADOR_PIPE + " " 
					+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_FECHA_RET, Utilidad.formatDatePicker(valorViejo.getValValorRetencion().getFechaEmision()));
				if (Constantes.RETENCION_IIBB.equalsIgnoreCase(String.valueOf(valorViejo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto()))) {
					if(!Validaciones.isNullOrEmpty(valorViejo.getValValorRetencion().getCuit())){
						numeroValor += " " + Constantes.SEPARADOR_PIPE + " " 
						+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CUIT, valorViejo.getValValorRetencion().getCuit());
					}
					//TODO CONSULTAR QUE ONDA - MERGE
					if (!Validaciones.isNullOrEmpty(valorViejo.getValValorRetencion().getParamJurisdiccion().getProvincia())) {
						numeroValor += " " +  Constantes.SEPARADOR_PIPE + " "
							+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_PROVINCIA, valorViejo.getValValorRetencion().getParamJurisdiccion().getDescripcion());
					}
				}				
				valorNuevo.setNumeroValor(numeroValor);
				break;
				
			case TRANSFERENCIA:
				valorNuevo.setNumeroValor(Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_REFERENCIA, valorViejo.getValValorTransferencia().getNumeroReferencia().toString()));
				break;
				
			case BOLETA_DEPOSITO_EFECTIVO:
				valorNuevo.setNumeroValor(Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_BOLETA, valorViejo.getValorBoletaEfectivo().getBoleta().getNumeroBoleta().toString()));
				break;
				
			case BOLETA_DEPOSITO_CHEQUE:
				strBuffer = new StringBuffer();
				strBuffer.append(Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_BOLETA, valorViejo.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta().toString()));
				strBuffer.append(" ").append(Constantes.SEPARADOR_PIPE).append(" ");
				strBuffer.append(Utilidad.reemplazarMensajes(Mensajes.CAMPO_BANCO_DESC, valorViejo.getValorBoletaDepositoCheque().getBancoOrigen().getDescripcion()));
				strBuffer.append(" ").append(Constantes.SEPARADOR_PIPE).append(" ");
				strBuffer.append(Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CHEQUE, valorViejo.getValorBoletaDepositoCheque().getNumeroCheque().toString()));
				strBuffer.append(" ").append(Constantes.SEPARADOR_PIPE).append(" ");
				strBuffer.append(Utilidad.reemplazarMensajes(
					Mensajes.CAMPO_FECHA_EMISION,
					(!Validaciones.isObjectNull(valorViejo.getValorBoletaDepositoCheque().getFechaEmision()))?Utilidad.formatDatePicker(valorViejo.getValorBoletaDepositoCheque().getFechaEmision()):"")
				);
				valorNuevo.setNumeroValor(strBuffer.toString());
				break;
				
			case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
				strBuffer = new StringBuffer();
				strBuffer.append(Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_BOLETA, valorViejo.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta().toString()));
				strBuffer.append(" ").append(Constantes.SEPARADOR_PIPE).append(" ");
				strBuffer.append(Utilidad.reemplazarMensajes(Mensajes.CAMPO_BANCO_DESC, valorViejo.getValorBoletaDepositoChequePD().getBancoOrigen().getDescripcion()));
				strBuffer.append(" ").append(Constantes.SEPARADOR_PIPE).append(" ");
				strBuffer.append(Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_CHEQUE, valorViejo.getValorBoletaDepositoChequePD().getNumeroCheque().toString()));
				strBuffer.append(" ").append(Constantes.SEPARADOR_PIPE).append(" ");
				strBuffer.append(Utilidad.reemplazarMensajes(
						Mensajes.CAMPO_FECHA_EMISION,
						(!Validaciones.isObjectNull(valorViejo.getValorBoletaDepositoChequePD().getFechaEmision()))?Utilidad.formatDatePicker(valorViejo.getValorBoletaDepositoChequePD().getFechaEmision()):"")
						);
				strBuffer.append(" ").append(Constantes.SEPARADOR_PIPE).append(" ");
				strBuffer.append(Utilidad.reemplazarMensajes(Mensajes.CAMPO_FECHA_VTO, Utilidad.formatDatePicker(valorViejo.getValorBoletaDepositoChequePD().getFechaVencimiento())));
				valorNuevo.setNumeroValor(strBuffer.toString());
				break;
				
			case INTERDEPÓSITO:
				valorNuevo.setNumeroValor(
						Utilidad.reemplazarMensajes(Mensajes.CAMPO_NRO_INTERDEPOSITO, valorViejo.getValValorInterdeposito().getNumeroInterdeposito().toString())
						+ " " + Constantes.SEPARADOR_PIPE + " " 
						+ Utilidad.reemplazarMensajes(Mensajes.CAMPO_CODIGO_ORGANISMO, valorViejo.getValValorInterdeposito().getCodigoOrganismo())
						);
				break;
				
			default:
				throw new NegocioExcepcion("No se encontro el tipo de Valor al mapear");
		}
		
		return valorNuevo;
		
	}

	/**
	 * Metodo que compara los recibos preimpresos entre el valor de la base y el valor del online.
	 * 
	 * @param valorDto
	 * @param valValorViejo
	 * @throws NegocioExcepcion
	 */
	private void actualizarReciboViejo(ValorDto valorDto,ShvValValor valValorViejo) throws NegocioExcepcion {
		

		boolean actualizarReciboViejo = false;
		HashMap<String,List<List<ShvValValor>>> listaValoresRecibo = new HashMap<String,List<List<ShvValValor>>>();
		
		if(valValorViejo.getValValorRetencion() != null){
			if (valValorViejo.getValValorRetencion().getReciboPreImpreso()!= null){
				if (!valValorViejo.getValValorRetencion().getReciboPreImpreso().getNumeroRecibo().equals(valorDto.getReciboPreImpreso())){
					actualizarReciboViejo = true;
				}
			}
		} else if(valValorViejo.getValorBoletaDepositoCheque() != null){
			if (valValorViejo.getValorBoletaDepositoCheque().getReciboPreImpreso()!= null){
				if (!valValorViejo.getValorBoletaDepositoCheque().getReciboPreImpreso().getNumeroRecibo().equals(valorDto.getReciboPreImpreso())){
					actualizarReciboViejo = true;
				}
			}
		} else if(valValorViejo.getValorBoletaDepositoChequePD() != null){
			if (valValorViejo.getValorBoletaDepositoChequePD().getReciboPreImpreso()!= null){
				if (!valValorViejo.getValorBoletaDepositoChequePD().getReciboPreImpreso().getNumeroRecibo().equals(valorDto.getReciboPreImpreso())){
					actualizarReciboViejo = true;
				}
			}
		} else if(valValorViejo.getValorBoletaEfectivo() != null){
			if (valValorViejo.getValorBoletaEfectivo().getReciboPreImpreso()!= null){
				if (!valValorViejo.getValorBoletaEfectivo().getReciboPreImpreso().getNumeroRecibo().equals(valorDto.getReciboPreImpreso())){
					actualizarReciboViejo = true;
				}
			}
		} else if(valValorViejo.getValValorCheque() != null){
			if (valValorViejo.getValValorCheque().getReciboPreImpreso()!= null){
				if (!valValorViejo.getValValorCheque().getReciboPreImpreso().getNumeroRecibo().equals(valorDto.getReciboPreImpreso())){
					actualizarReciboViejo = true;
				}
			}
		} else if(valValorViejo.getValValorChequePD() != null){
			if (valValorViejo.getValValorChequePD().getReciboPreImpreso()!= null){
				if (!valValorViejo.getValValorChequePD().getReciboPreImpreso().getNumeroRecibo().equals(valorDto.getReciboPreImpreso())){
					actualizarReciboViejo = true;
				}
			}
		} else if(valValorViejo.getValValorEfectivo() != null){
			if (valValorViejo.getValValorEfectivo().getReciboPreImpreso()!= null){
				if (!valValorViejo.getValValorEfectivo().getReciboPreImpreso().getNumeroRecibo().equals(valorDto.getReciboPreImpreso())){
					actualizarReciboViejo = true;
				}
			}
		}
		if (actualizarReciboViejo){
			actualizarEstadoRecibo(listaValoresRecibo, valValorViejo, false);
		}
	
	}
	
	/**
	 * 
	 * @param valValorViejo
	 * @param agregar
	 * @throws NegocioExcepcion
	 */
	public void actualizarEstadoRecibo(HashMap<String, List<List<ShvValValor>>> listaValoresRecibo, ShvValValor valValorViejo, boolean agregar) throws NegocioExcepcion {
		
		String numeroRecibo ="";
		int indiceMatriz=0;
		boolean actualizar = true;
		
		String usuarioModificacion = valValorViejo.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getUsuarioModificacion();
		
		//
		// Se determina si el valor ha sido modificado, y como parte de los cambios si se ha modificado el recibo.
		// Para ello se busca el recibo asociado en el valor, en su version original, antes de modificar el valor
		// La variable "actualizar" indica si el recibo debe o no ser actualizado
		//
		if (valValorViejo.getValValorRetencion() != null){
			if (valValorViejo.getValValorRetencion().getReciboPreImpreso() != null){
				numeroRecibo = valValorViejo.getValValorRetencion().getReciboPreImpreso().getNumeroRecibo();
			} else {
				actualizar = false;
			}
		} else if (valValorViejo.getValorBoletaDepositoCheque() != null){
			if (valValorViejo.getValorBoletaDepositoCheque().getReciboPreImpreso() != null){
				numeroRecibo = valValorViejo.getValorBoletaDepositoCheque().getReciboPreImpreso().getNumeroRecibo();
				indiceMatriz = 1;
			} else {
				actualizar = false;
			}
		} else if (valValorViejo.getValorBoletaDepositoChequePD() != null){
			if (valValorViejo.getValorBoletaDepositoChequePD().getReciboPreImpreso() != null){
				numeroRecibo = valValorViejo.getValorBoletaDepositoChequePD().getReciboPreImpreso().getNumeroRecibo();
				indiceMatriz = 2;
			} else {
				actualizar = false;
			}
		} else if (valValorViejo.getValorBoletaEfectivo() != null){
			if (valValorViejo.getValorBoletaEfectivo().getReciboPreImpreso() != null){
				numeroRecibo = valValorViejo.getValorBoletaEfectivo().getReciboPreImpreso().getNumeroRecibo();
				indiceMatriz =3;
			} else {
				actualizar = false;
			}
		} else if (valValorViejo.getValValorCheque() != null){
			if (valValorViejo.getValValorCheque().getReciboPreImpreso() != null){
				numeroRecibo = valValorViejo.getValValorCheque().getReciboPreImpreso().getNumeroRecibo();
				indiceMatriz =4;
			} else {
				actualizar = false;
			}
		} else if (valValorViejo.getValValorChequePD() != null){
			if (valValorViejo.getValValorChequePD().getReciboPreImpreso() != null){
				numeroRecibo = valValorViejo.getValValorChequePD().getReciboPreImpreso().getNumeroRecibo();
				indiceMatriz =5;
			} else {
				actualizar = false;
			}
		} else if (valValorViejo.getValValorEfectivo() != null){
			if (valValorViejo.getValValorEfectivo().getReciboPreImpreso() != null){
				numeroRecibo = valValorViejo.getValValorEfectivo().getReciboPreImpreso().getNumeroRecibo();
				indiceMatriz =6;
			} else {
				actualizar = false;
			}
		}
		
		//
		// Si el valor original tenia un numero de recibo...
		//
		if (!Validaciones.isNullOrEmpty(numeroRecibo)){
			List<List<ShvValValor>> matrizValores = new ArrayList<List<ShvValValor>>();

			if (!Validaciones.isObjectNull(listaValoresRecibo)){
				if (listaValoresRecibo.containsKey(numeroRecibo)){
					matrizValores = listaValoresRecibo.get(numeroRecibo);
				} else {
					matrizValores = valoresDeUnRecibo(numeroRecibo);
				}
				listaValoresRecibo.put(numeroRecibo, matrizValores);
			} else {
					matrizValores = valoresDeUnRecibo(numeroRecibo);
			}
			
			Iterator<ShvValValor> iteradorValor = matrizValores.get(indiceMatriz).iterator();
			while (iteradorValor.hasNext()){
				ShvValValor val = iteradorValor.next();
				if (val.getIdValor().equals(valValorViejo.getIdValor())) {
					matrizValores.get(indiceMatriz).remove(val);
					break;
				}
			}
			if (agregar) {
				matrizValores.get(indiceMatriz).add(valValorViejo);
			}
			if (actualizar) {
				reciboServicio.actualizacionEstado(matrizValores, numeroRecibo, usuarioModificacion);
			}
		}		

	}
	
	/**
	 * 
	 */
	public ShvValValor actualizarValor(ShvValValor valorModelo) throws NegocioExcepcion{
		try {
			return valorDao.actualizarValor(valorModelo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

//	TODO se comento para volver a la version anterior hasta que se corrija
//	public StringBuffer valorToStringBuffer(ShvValValor valorModelo) throws NegocioExcepcion {
//
//		StringBuffer retorno = new StringBuffer();
//		
//		List<ShvParamValorMedioPago> listaMedioPago = valorMedioPagoServicio.buscarPorTipoValor("");
//
//		switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorModelo.getTipoValor().getIdTipoValor()))) {
//	    case CHEQUE:
//	    	
//	    	retorno.append(buscarIdMedioPago(valorModelo.getTipoValor().getIdTipoValor(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getParamOrigen() != null)?valorModelo.getParamOrigen().getIdOrigen():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getAcuerdo() != null)?valorModelo.getAcuerdo().getIdAcuerdo():null) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getValValorCheque().getBancoOrigen() != null)?valorModelo.getValValorCheque().getBancoOrigen().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorCheque().getNumeroBoleta()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorCheque().getNumeroCheque()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorCheque().getFechaDeposito())) +  Constantes.SEPARADOR_PIPE  );
////			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorCheque().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//			
//	    	break;
//	    	
//	    case BOLETA_DEPOSITO_CHEQUE:
//
//	    	retorno.append(buscarIdMedioPago(valorModelo.getTipoValor().getIdTipoValor(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getParamOrigen() != null)?valorModelo.getParamOrigen().getIdOrigen():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getAcuerdo() != null)?valorModelo.getAcuerdo().getIdAcuerdo():null) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getValorBoletaDepositoCheque().getBancoOrigen() != null)?valorModelo.getValorBoletaDepositoCheque().getBancoOrigen().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValorBoletaDepositoCheque().getNumeroCheque()) +  Constantes.SEPARADOR_PIPE  );
//			
//			if(valorModelo.getFechaAlta() != null){
//				retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//			}else{
//				retorno.append( Constantes.SEPARADOR_PIPE );
//			}
//			
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValorBoletaDepositoCheque().getFechaDeposito())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValorBoletaDepositoCheque().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//    		
//    		break;	
//	    case CHEQUE_DIFERIDO:
//	    	
//	    	retorno.append(buscarIdMedioPago(valorModelo.getTipoValor().getIdTipoValor(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getParamOrigen() != null)?valorModelo.getParamOrigen().getIdOrigen():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getAcuerdo() != null)?valorModelo.getAcuerdo().getIdAcuerdo():null) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getValValorChequePD().getBancoOrigen() != null)?valorModelo.getValValorChequePD().getBancoOrigen().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorChequePD().getNumeroBoleta()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorChequePD().getNumeroCheque()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorChequePD().getFechaDeposito())) +  Constantes.SEPARADOR_PIPE  );
////			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorChequePD().getFechaVencimiento())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
// 
//	    	break;
//	    	
//	    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
//
//	    	retorno.append(buscarIdMedioPago(valorModelo.getTipoValor().getIdTipoValor(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getParamOrigen() != null)?valorModelo.getParamOrigen().getIdOrigen():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getAcuerdo() != null)?valorModelo.getAcuerdo().getIdAcuerdo():null) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getValorBoletaDepositoChequePD().getBancoOrigen() != null)?valorModelo.getValorBoletaDepositoChequePD().getBancoOrigen().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValorBoletaDepositoChequePD().getNumeroCheque()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValorBoletaDepositoChequePD().getFechaDeposito())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValorBoletaDepositoChequePD().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );			
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValorBoletaDepositoChequePD().getFechaVencimiento())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
// 
//    		break;
//	    case BOLETA_DEPOSITO_EFECTIVO:
//	    	
//	    	retorno.append(buscarIdMedioPago(valorModelo.getTipoValor().getIdTipoValor(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getParamOrigen() != null)?valorModelo.getParamOrigen().getIdOrigen():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getAcuerdo() != null)?valorModelo.getAcuerdo().getIdAcuerdo():null) +  Constantes.SEPARADOR_PIPE  );
////			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValorBoletaEfectivo().getBancoOrigen().getDescripcion()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValorBoletaEfectivo().getBoleta().getNumeroBoleta()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValorBoletaEfectivo().getFechaDeposito())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValorBoletaEfectivo().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//			
//    		break;
//	    case TRANSFERENCIA:
//	    	
//	    	retorno.append(buscarIdMedioPago(valorModelo.getTipoValor().getIdTipoValor(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getAcuerdo() != null)?valorModelo.getAcuerdo().getIdAcuerdo():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getValValorTransferencia().getBancoOrigen() != null)?valorModelo.getValValorTransferencia().getBancoOrigen().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorTransferencia().getNumeroReferencia()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.desformatearCuit(valorModelo.getValValorTransferencia().getCuit())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorTransferencia().getFechaTransferencia())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//	    	
//	    	break;
//	    case INTERDEPÓSITO:
//
//	    	retorno.append(buscarIdMedioPago(valorModelo.getTipoValor().getIdTipoValor(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getAcuerdo() != null)?valorModelo.getAcuerdo().getIdAcuerdo():null) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorInterdeposito().getNumeroInterdeposito()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorInterdeposito().getCodigoOrganismo()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorInterdeposito().getFechaInterdeposito())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//			
//	    	break;
//	    case EFECTIVO:
//
//	    	retorno.append(buscarIdMedioPago(valorModelo.getTipoValor().getIdTipoValor(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getParamOrigen() != null)?valorModelo.getParamOrigen().getIdOrigen():null) +  Constantes.SEPARADOR_PIPE  );
//	    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getAcuerdo() != null)?valorModelo.getAcuerdo().getIdAcuerdo():null) +  Constantes.SEPARADOR_PIPE  );
////			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getBancoOrigen().getDescripcion()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorEfectivo().getNumeroBoleta()) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorEfectivo().getFechaDeposito())) +  Constantes.SEPARADOR_PIPE  );
////			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorEfectivo().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//			retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
// 	
//    		break;
//    	
//	    case RETENCIÓN_IMPUESTO:
//	    	switch (TipoRetencionEnum.getEnumByIdTipoRetencion(Long.valueOf(valorModelo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto()))) {
//		        case RETENCION_IIBB:
//
//		        	retorno.append(buscarIdMedioPagoConRetencion(valorModelo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getNroConstanciaRetencion()) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getFechaEmision()) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.desformatearCuit(valorModelo.getValValorRetencion().getCuit())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getValValorRetencion().getParamJurisdiccion() != null)?valorModelo.getValValorRetencion().getParamJurisdiccion().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );		
//					retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getValValorRetencion().getParamTipoComprobante() != null)?valorModelo.getValValorRetencion().getParamTipoComprobante().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );	
//					retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getValValorRetencion().getParamTipoLetraComprobante() != null)?valorModelo.getValValorRetencion().getParamTipoLetraComprobante().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getNumeroComprobante()) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorRetencion().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));	
//	        		
//		        	break;
//		        case RETENCION_IVA:
//
//		        	retorno.append(buscarIdMedioPagoConRetencion(valorModelo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getNroConstanciaRetencion()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getFechaEmision()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorRetencion().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//
//	        		break;
//		        case RETENCION_GANANCIA:
//
//		        	retorno.append(buscarIdMedioPagoConRetencion(valorModelo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getNroConstanciaRetencion()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getFechaEmision()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorRetencion().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//
//		        	break;
//		        case RETENCION_SEGURIDAD_SOCIAL:
//
//		        	retorno.append(buscarIdMedioPagoConRetencion(valorModelo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getNroConstanciaRetencion()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getFechaEmision()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorRetencion().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//					
//		        	break;
//		        case IMPUESTO_AL_SELLO:
//
//		        	retorno.append(buscarIdMedioPagoConRetencion(valorModelo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getNroConstanciaRetencion()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getFechaEmision()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorRetencion().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//	        		
//		        	break;
//		        case IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE:
//		        	
//		        	retorno.append(buscarIdMedioPagoConRetencion(valorModelo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getNroConstanciaRetencion()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getFechaEmision()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorRetencion().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//			    	
//	        		break;
//		        case IMPUESTO_TASAS_MUNICIPALES:
//
//		        		retorno.append(buscarIdMedioPagoConRetencion(valorModelo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getNroConstanciaRetencion()) +  Constantes.SEPARADOR_PIPE  );
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getFechaEmision()) +  Constantes.SEPARADOR_PIPE  );
//				    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//						retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorRetencion().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//						retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//				    	
//		        	break;
//		        case RETENCION_IVA_RG3349:
//
//		        	retorno.append(buscarIdMedioPagoConRetencion(valorModelo.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto(),listaMedioPago) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdValor() +  Constantes.SEPARADOR_PIPE  ));
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getImporte(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorModelo.getSaldoDisponible(), false, false, 4)) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaValor())) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio((valorModelo.getMotivo() != null)?valorModelo.getMotivo().getDescripcion():null) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getOperacionAsociada()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getObservaciones()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getIdClienteLegado()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getNroConstanciaRetencion()) +  Constantes.SEPARADOR_PIPE );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getValValorRetencion().getFechaEmision()) +  Constantes.SEPARADOR_PIPE  );
//			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getFechaAlta())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorModelo.getValValorRetencion().getFechaRecibo())) +  Constantes.SEPARADOR_PIPE  );
//					retorno.append(Utilidad.generarSalidaConValorOVacio(valorModelo.getNumeroValor().replace( Constantes.SEPARADOR_PIPE , "")));
//			    	
//	        		break;
//		        default:
//		    };
//	    	break;
//	    	default:
//		};
// 	
//		
//		return retorno;
//
//	}

	private String buscarIdMedioPago(Long idTipoValor, List<ShvParamValorMedioPago> listaMedioPago) {
	
	 	for (ShvParamValorMedioPago shvParamValorMedioPago : listaMedioPago) {
			if(idTipoValor.equals(shvParamValorMedioPago.getTipoValor().getIdTipoValor())){
				return shvParamValorMedioPago.getTipoMedioPago().getIdTipoMedioPago();
			}
		}
		return "";
	}
	
	private String buscarIdMedioPagoConRetencion(Long idTipoRetencion, List<ShvParamValorMedioPago> listaMedioPago) {
		
		for (ShvParamValorMedioPago shvParamValorMedioPago : listaMedioPago) {
			if(idTipoRetencion.equals(shvParamValorMedioPago.getIdSubTipoValor())){
				return shvParamValorMedioPago.getTipoMedioPago().getIdTipoMedioPago();
			}
		}
		return "";
	}
	/**
	 * Genera el detalle del debito.
	 * @param factura
	 * @param transaccion
	 * @param importe
	 * @param valor
	 * @param saldoOriginal
	 * @return
	 * @throws NegocioExcepcion
	 */
	private String generarDetalleValor(ShvCobFacturaSinOperacion factura, ShvCobTransaccionSinOperacion transaccion, BigDecimal importe, ShvValValorSimplificado valor, BigDecimal saldoOriginal) throws NegocioExcepcion{
		
		StringBuffer datosModificados = new StringBuffer("[Devolución de Saldo] (Id Transaccion: ").append(transaccion.getOperacionTransaccionFicticioFormateado());
        
		if (!Validaciones.isObjectNull(factura)) {
    		datosModificados.append(" Id Factura: ").append(generarIdFacturaParaMostrar(factura));
    		datosModificados.append(" Origen de la Factura: ").append(factura.getSociedad().getApocope()).append(" - ");
    		if(!SistemaEnum.NEGOCIO_NET.equals(factura.getSistemaOrigen())){
    			datosModificados.append((factura.getSistemaOrigen() != null ? factura.getSistemaOrigen().name():"-"));
    		} else {
    			datosModificados.append((factura.getSistemaOrigen() != null ? factura.getSistemaOrigen().name().replace("_","."):"-"));
    		}

		} else {
			datosModificados.append(" Id Tratamiento: ").append(transaccion.getTratamientoDiferencia().getIdTratamientoDiferencia());
		}
             
        
        datosModificados.append(" Saldo devuelto: ").append(importe);
        datosModificados.append(" Saldo Original: ").append(saldoOriginal);
        datosModificados.append(" Saldo Modificado: ").append(valor.getSaldoDisponible()).append(")");
        
        return datosModificados.toString();
	}
	/**
	 * Genera la id de Factura a mostrar
	 * @param factura
	 * @return
	 * @throws NegocioExcepcion
	 */
	@Override
	public String generarIdFacturaParaMostrar(ShvCobFacturaSinOperacion factura) throws NegocioExcepcion{
		StringBuffer idFacturaParaMostrar = new StringBuffer();
		
		
			if (TipoComprobanteEnum.C_C.equals(factura.getTipoComprobante())){
				idFacturaParaMostrar.append(Constantes.CUENTA_CORRIENTE).append(" ");
				if(factura instanceof ShvCobFacturaUsuarioSinOperacion){
					if (!Validaciones.isNullOrEmpty(((ShvCobFacturaUsuarioSinOperacion) factura).getReferencia())){
						idFacturaParaMostrar.append(((ShvCobFacturaUsuarioSinOperacion) factura).getReferencia()).append(" ");
					} else {
						idFacturaParaMostrar.append(factura.getIdClienteLegado()).append(" ");
					}
				}
			} else if (TipoComprobanteEnum.CDD.equals(factura.getTipoComprobante())){
				idFacturaParaMostrar.append(Constantes.CONJUNTO_DEBITOS).append(" ");
			} else {
				if (TipoComprobanteEnum.DE2.equals(factura.getTipoComprobante())) {
					// Para el caso del tipo de comprobante 'DE2', hacemos un tratamiento especial para poder eliminar el '2' de la descripción
					// que se está utilizando.
					idFacturaParaMostrar.append(factura.getTipoComprobante().getValor().replace("2", "")).append(" ");
				} else {
					idFacturaParaMostrar.append(factura.getTipoComprobante().getValor()).append(" ");
				}
				
				// Se arma el numero legal
				 // Si el comprobante es "D" o "S", el mismo no se envía ya que para el usuario es un blanco.
				if (!Validaciones.isObjectNull(factura.getClaseComprobante()) 
						&& !TipoClaseComprobanteEnum.D.equals(factura.getClaseComprobante()) 
						&& !TipoClaseComprobanteEnum.S.equals(factura.getClaseComprobante()) ) { 
				
						idFacturaParaMostrar.append(factura.getClaseComprobante().name()).append("-");
				}
			
				idFacturaParaMostrar.append(Utilidad.rellenarCerosIzquierda(factura.getSucursalComprobante(), 4)).append("-");
				idFacturaParaMostrar.append(Utilidad.rellenarCerosIzquierda(factura.getNumeroComprobante(), 8));
				
				if(factura instanceof ShvCobFacturaMicSinOperacion){
					if (!TipoComprobanteEnum.DUC.equals(factura.getTipoComprobante())){
						idFacturaParaMostrar.append(" | ").append(((ShvCobFacturaMicSinOperacion) factura).getIdReferenciaFactura()).append(" ");
					}
				}
			}
	
	    return idFacturaParaMostrar.toString();
	}
	
	//	TODO version anterior del metodo 
	private final String PIPE = "|";
	public StringBuffer getValorVistaStringBuffer(Modelo vo) throws NegocioExcepcion {

		ShvValValoresVista valorVista = (ShvValValoresVista) vo;

		StringBuffer retorno = new StringBuffer();
		
		List<ShvParamValorMedioPago> listaMedioPago = valorMedioPagoServicio.buscarPorTipoValor("");

		switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorVista.getIdTipoValor()))) {
	    case CHEQUE:
	    	
	    	retorno.append(buscarIdMedioPago(valorVista.getIdTipoValor(),listaMedioPago) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOrigen()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdAcuerdo()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getDescripcionBancoOrigen()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroBoleta()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroCheque()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaDeposito())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroCheque()));
			
	    	break;
	    	
	    case BOLETA_DEPOSITO_CHEQUE:

	    	retorno.append(buscarIdMedioPago(valorVista.getIdTipoValor(),listaMedioPago) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOrigen()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdAcuerdo()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getDescripcionBancoOrigen()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroBoleta()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroCheque()) + PIPE );
			
			if(Validaciones.isNullOrEmpty(valorVista.getFechaAlta())){
				retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
			}else{
				retorno.append(PIPE);
			}
			
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaDeposito())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroCheque()));
    		
    		break;	
	    case CHEQUE_DIFERIDO:
	    	
	    	retorno.append(buscarIdMedioPago(valorVista.getIdTipoValor(),listaMedioPago) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOrigen()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdAcuerdo()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getDescripcionBancoOrigen()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroBoleta()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroCheque()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaDeposito())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaVencimiento())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroCheque()));
 
	    	break;
	    	
	    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:

	    	retorno.append(buscarIdMedioPago(valorVista.getIdTipoValor(),listaMedioPago) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOrigen()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdAcuerdo()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getDescripcionBancoOrigen()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroBoleta()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroCheque()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaDeposito())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaVencimiento())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroCheque()));
 
    		break;
	    case BOLETA_DEPOSITO_EFECTIVO:
	    	
	    	retorno.append(buscarIdMedioPago(valorVista.getIdTipoValor(),listaMedioPago) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOrigen()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdAcuerdo()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getDescripcionBancoOrigen()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroBoleta()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaDeposito())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroBoleta()));
			
    		break;
	    case TRANSFERENCIA:
	    	
	    	retorno.append(buscarIdMedioPago(valorVista.getIdTipoValor(),listaMedioPago) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdAcuerdo()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getDescripcionBancoOrigen()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroReferencia()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.desformatearCuit(valorVista.getCuit())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaTransferencia())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroReferencia()));
	    	
	    	break;
	    case INTERDEPÓSITO:

	    	retorno.append(buscarIdMedioPago(valorVista.getIdTipoValor(),listaMedioPago) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdAcuerdo()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroInterdeposito()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoOrganismo()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaDeposito())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroInterdeposito()));
			
	    	break;
	    case EFECTIVO:

	    	retorno.append(buscarIdMedioPago(valorVista.getIdTipoValor(),listaMedioPago) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOrigen()) + PIPE );
	    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdAcuerdo()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getDescripcionBancoOrigen()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroBoleta()) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaDeposito())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
			retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroBoleta()));
 	
    		break;
    	
	    case RETENCIÓN_IMPUESTO:
	    	switch (TipoRetencionEnum.getEnumByIdTipoRetencion(Long.valueOf(valorVista.getIdTipoRentencionImpuesto()))) {
		        case RETENCION_IIBB:

		        	retorno.append(buscarIdMedioPagoConRetencion(valorVista.getIdTipoRentencionImpuesto(),listaMedioPago) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getFechaEmision()) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.desformatearCuit(valorVista.getCuit())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getDescripcionProvincia()) + PIPE );		
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getTipoComprobante()) + PIPE );	
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getLetraComprobante()) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroLegalComprobante()) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()));
	        		
		        	break;
		        case RETENCION_IVA:

		        	retorno.append(buscarIdMedioPagoConRetencion(valorVista.getIdTipoRentencionImpuesto(),listaMedioPago) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getFechaEmision()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()));
 

	        		break;
		        case RETENCION_GANANCIA:

		        	retorno.append(buscarIdMedioPagoConRetencion(valorVista.getIdTipoRentencionImpuesto(),listaMedioPago) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getFechaEmision()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()));

		        	break;
		        case RETENCION_SEGURIDAD_SOCIAL:

		        	retorno.append(buscarIdMedioPagoConRetencion(valorVista.getIdTipoRentencionImpuesto(),listaMedioPago) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getFechaEmision()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()));
					
		        	break;
		        case IMPUESTO_AL_SELLO:

		        	retorno.append(buscarIdMedioPagoConRetencion(valorVista.getIdTipoRentencionImpuesto(),listaMedioPago) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getFechaEmision()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()));
	        		
		        	break;
		        case IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE:
		        	
		        	retorno.append(buscarIdMedioPagoConRetencion(valorVista.getIdTipoRentencionImpuesto(),listaMedioPago) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getFechaEmision()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()));
			    	
	        		break;
		        case IMPUESTO_TASAS_MUNICIPALES:

	        		retorno.append(buscarIdMedioPagoConRetencion(valorVista.getIdTipoRentencionImpuesto(),listaMedioPago) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getFechaEmision()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()));
				    	
		        	break;
		        case RETENCION_IVA_RG3349:

		        	retorno.append(buscarIdMedioPagoConRetencion(valorVista.getIdTipoRentencionImpuesto(),listaMedioPago) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getIdValor() + PIPE ));
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getImporte(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(valorVista.getSaldoDisponible(), false, false, 4)) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaValor())) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getMotivo()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getOperacionAsociada()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getObservaciones()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getCodigoClienteLegado()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()) + PIPE);
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getFechaEmision()) + PIPE );
			    	retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.cambiarFormatoFechaAAAAMMDD(valorVista.getFechaAlta())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateAAAAMMDDconBarra(valorVista.getFechaIngresoRecibo())) + PIPE );
					retorno.append(Utilidad.generarSalidaConValorOVacio(valorVista.getNumeroConstanciaDeRetencion()));
			    	
	        		break;
		        default:
		    };
	    	break;
	    	default:
		};
 	
		
		return retorno;

	}
	
	@Override
	public List<ShvValValorSimplificado> listarValoresSimplificados(List<Long> idValores) throws NegocioExcepcion {
	
		try{
			return valorDao.listarValoresSimplificados(idValores);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

	}
	
	@Override
	public ShvValValorSimplificado buscarValorSimplificado(Long idValor) throws NegocioExcepcion {
		
		try{
			return valorDao.buscarValorSimplificado(idValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public ShvValValorSimplificado actualizarValorSimplificado(ShvValValorSimplificado valor) throws NegocioExcepcion {
		try{
			return valorDao.actualizarValorSimplificado(valor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/***************************************************************************
	 * Validaciones duplicidad - valores y recibos
	 * ************************************************************************/

	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método se debe eliminar cuando se habilite la mejora en el próximo Release
	 * 
	 * Metodo que se utiliza para validar la existencia de un valor.
	 * 
	 * @param usuarioModificacion
	 * @param valorDTO
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ValorDto validarDuplicados(String usuarioModificacion, ValorDto valorDTO) throws PersistenciaExcepcion {
		ValorDto valorDtoDuplicado = new ValorDto();
		String idTipoValor = valorDTO.getIdTipoValor();
		valorDTO.setUsuarioModificacion(usuarioModificacion);
		
		if(!Validaciones.isNullEmptyOrDash(valorDTO.getNumeroDocumentoContable()) &&
			(VALOR_PARA_TRANSFERENCIA.equals(idTipoValor) || VALOR_CHEQUE.equals(idTipoValor) || VALOR_CHEQUE_PD.equals(idTipoValor) || VALOR_PARA_EFECTIVO.equals(idTipoValor) )){
			List<VistaSoporteResultadoBusquedaValor> listaValoresVistaSoporte;
			VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
			filtro.setNumeroDocumentoContable(valorDTO.getNumeroDocumentoContable());
			Set<String> estados = new HashSet<String>();
			estados.add(Estado.VAL_DISPONIBLE.name());
			estados.add(Estado.VAL_USADO.name());
			estados.add(Estado.VAL_SUSPENDIDO.name());
			filtro.setIdEstados(estados);
			if(VALOR_PARA_TRANSFERENCIA.equals(idTipoValor)){
				filtro.setAcuerdo(valorDTO.getIdAcuerdo());
			}
			try {
				listaValoresVistaSoporte = vistaSoporteServicio.buscarValores(filtro);
			} catch (NegocioExcepcion e) {
				throw new PersistenciaExcepcion(e.getMessage());
			}
			//Verifico que no exista otro valor con el mismo ZE
			if ( Validaciones.isCollectionNotEmpty(listaValoresVistaSoporte)) {
				valorDtoDuplicado.setMensajeDuplicadoError(armarMensajeDuplicadoZE(listaValoresVistaSoporte.get(0)));
				if(VALOR_PARA_TRANSFERENCIA.equals(idTipoValor)){
					valorDtoDuplicado.setMensajeDuplicadoError(valorDtoDuplicado.getMensajeDuplicadoError() + " y Acuerdo " + listaValoresVistaSoporte.get(0).getIdAcuerdo());
				}
			}
		} else if(VALOR_PARA_BOLETA_CHEQUE.equals(idTipoValor) || VALOR_PARA_BOLETA_CHEQUE_PD.equals(idTipoValor) || VALOR_CHEQUE.equals(idTipoValor) || VALOR_CHEQUE_PD.equals(idTipoValor)){
			List<ShvValBoletaDepositoCheque> listaBoletaCheque = valorDao.buscarValoresBoletaChequeDuplicados(valorDTO.getIdBancoOrigen(), valorDTO.getNumeroCheque(), valorDTO.getImporte());
			List<ShvValBoletaDepositoChequePagoDiferido> listaBoletaDiferido = valorDao.buscarValoresBoletaChequeDiferidoDuplicados(valorDTO.getIdBancoOrigen(), valorDTO.getNumeroCheque(), valorDTO.getImporte(), valorDTO.getFechaVencimiento());
			List<ShvValValorCheque> listaCheque = valorDao.buscarValoresChequeDuplicados(valorDTO.getIdBancoOrigen(),	valorDTO.getNumeroCheque(),	valorDTO.getImporte(), valorDTO.getFechaDeposito());
			List<ShvValValorChequePagoDiferido> listaDiferido = valorDao.buscarValoresChequeDiferidoDuplicados(valorDTO.getIdBancoOrigen(),	valorDTO.getNumeroCheque(),	valorDTO.getImporte(), valorDTO.getFechaDeposito(), valorDTO.getFechaVencimiento());
			
			if(Validaciones.isCollectionNotEmpty(listaBoletaCheque) || Validaciones.isCollectionNotEmpty(listaBoletaDiferido) || Validaciones.isCollectionNotEmpty(listaCheque) || Validaciones.isCollectionNotEmpty(listaDiferido)){
				valorDtoDuplicado.setIdBancoOrigen(valorDTO.getIdBancoOrigen());
				valorDtoDuplicado.setNumeroCheque(valorDTO.getNumeroCheque());
				valorDtoDuplicado.setImporte(valorDTO.getImporte());
				valorDtoDuplicado.setFechaDeposito(valorDTO.getFechaDeposito());
				valorDtoDuplicado.setFechaVencimiento(valorDTO.getFechaVencimiento());
				valorDtoDuplicado.setFechaTransferencia(valorDTO.getFechaTransferencia());
				valorDtoDuplicado.setExisteDuplicado(true);
			}
		} else if(VALOR_PARA_TRANSFERENCIA.equals(idTipoValor)){
			boolean es87 = false;
			if (TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion().equals(valorDTO.getIdAcuerdo())){
				es87 = true;
			}
			List<ShvValValorTransferencia> listaTransferencia = valorDao.buscarValoresTransferenciaDuplicados(valorDTO.getNumeroReferencia(), valorDTO.getFechaTransferencia(), valorDTO.getImporte(), es87, valorDTO.getIdAcuerdo());
			if(Validaciones.isCollectionNotEmpty(listaTransferencia)){
				valorDtoDuplicado.setNumeroReferencia(valorDTO.getNumeroReferencia());
				if(!es87){
					valorDtoDuplicado.setFechaTransferencia(valorDTO.getFechaTransferencia());
					valorDtoDuplicado.setImporte(valorDTO.getImporte());
				}
				valorDtoDuplicado.setExisteDuplicado(true);
			}
		} else if(VALOR_PARA_RETENCION.equals(idTipoValor)){
			List<ShvValValorRetencion> listaRetencion = valorDao.buscarValoresRetencionImpuestoDuplicados(valorDTO.getIdTipoImpuesto(), valorDTO.getNroConstancia(),valorDTO.getCliente().getIdClienteLegado());
			if(Validaciones.isCollectionNotEmpty(listaRetencion)){
				valorDtoDuplicado.setIdTipoImpuesto(valorDTO.getIdTipoImpuesto());
				valorDtoDuplicado.setTipoImpuesto(valorDTO.getTipoImpuesto());
				valorDtoDuplicado.setNroConstancia(valorDTO.getNroConstancia());
				valorDtoDuplicado.setCodCliente(valorDTO.getCliente().getIdClienteLegado());
				valorDtoDuplicado.setExisteDuplicado(true);
			}
		} else if (VALOR_PARA_INTERDEPOSITO.equals(idTipoValor)){
			List<ShvValValorInterdeposito> listaInterdeposito = valorDao.buscarValoresInterdepositoDuplicados(valorDTO.getCliente().getIdClienteLegado(), valorDTO.getNumeroInterdepositoCdif(), valorDTO.getFechaInterdeposito());
			if(Validaciones.isCollectionNotEmpty(listaInterdeposito)){
				valorDtoDuplicado.setCodCliente(valorDTO.getCliente().getIdClienteLegado());
				valorDtoDuplicado.setNumeroInterdepositoCdif(valorDTO.getNumeroInterdepositoCdif());
				valorDtoDuplicado.setFechaInterdeposito(valorDTO.getFechaInterdeposito());
				valorDtoDuplicado.setExisteDuplicado(true);
			}
		}
		return valorDtoDuplicado;
	}
	
	/**
	 * Req 67878 Cablevision Release 1 - Mejoras de performance en Valores - consultas para validacion de unicidad. 
	 * Este método es el que debe quedar cuando se habilite la mejora en el próximo Release, eliminando los anteriores
	 * (hay que sacar el "Pablo" del final del nombre del metodo)
	 * 
	 * Metodo que se utiliza para validar la existencia de un valor.
	 * 
	 * metodo nuevo
	 * @param usuarioModificacion
	 * @param valorDTO
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ValorDto validarDuplicadosPablo(String usuarioModificacion, ValorDto valorDTO) throws NegocioExcepcion {

		ValorDto valorDtoDuplicado = new ValorDto();
		valorDTO.setUsuarioModificacion(usuarioModificacion);
		
		if (!validarUnicidadValor(valorDTO, false)) {
			valorDtoDuplicado.setIdBancoOrigen(valorDTO.getIdBancoOrigen());
			valorDtoDuplicado.setNumeroCheque(valorDTO.getNumeroCheque());
			valorDtoDuplicado.setImporte(valorDTO.getImporte());
			valorDtoDuplicado.setFechaDeposito(valorDTO.getFechaDeposito());
			valorDtoDuplicado.setFechaVencimiento(valorDTO.getFechaVencimiento());
			valorDtoDuplicado.setFechaTransferencia(valorDTO.getFechaTransferencia());
			
			valorDtoDuplicado.setIdTipoImpuesto(valorDTO.getIdTipoImpuesto());
			valorDtoDuplicado.setTipoImpuesto(valorDTO.getTipoImpuesto());
			valorDtoDuplicado.setNroConstancia(valorDTO.getNroConstancia());
			valorDtoDuplicado.setCodCliente(valorDTO.getCliente().getIdClienteLegado());
			valorDtoDuplicado.setExisteDuplicado(true);
		}

		return valorDtoDuplicado;
	}
	
	/**
	 * Modificado por 
	 * @author fabio.giaquinta.ruiz
	 * fecha 24/09/2014
	 * @Funcionalidad
	 * Aplica la RN035 del CU414, SF002B 
	 * Valida si ya existe un valor con los datos del valorDto, segun su tipo.
	 * Se usa unicamente en valores por reversion
	 */
	public List<ShvValValor> validarUnicidadValor(ValorDto valorDto) throws NegocioExcepcion {
		List<ShvValValor> listaValorBd = null;
		try {
			listaValorBd = valorDao.validarUnicidadValor(valorDto);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return listaValorBd;
	}
	
	/**
	 * Valida si existe otro valor con los datos del valorDto, segun su tipo.
	 */
	public boolean reValidarUnicidadValor(ValorDto valorDto) throws NegocioExcepcion {
		try {
			return valorDao.reValidarUnicidadValor(valorDto);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
		
	/**
	 * Valida si existe otro valor con los datos del valorDto, segun su tipo.
	 * 
	 * @param valorDto
	 * @param incluirValorActualEnComparacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarUnicidadValor(ValorDto valorDto, boolean incluirValorActualEnComparacion) throws NegocioExcepcion {

		boolean evaluacionEsValorUnico = false;
		
		try {
			
			Traza.auditoria(getClass(), " ");			
			Traza.auditoria(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(getClass(), "~ Voy a validar unicidad del valor ");
			Traza.auditoria(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			evaluacionEsValorUnico = valorDao.validarUnicidadValor(valorDto, incluirValorActualEnComparacion);

			Traza.auditoria(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(getClass(), "~ Fin de validación de unicidad ");
			Traza.auditoria(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			Traza.auditoria(getClass(), " ");
			
			return evaluacionEsValorUnico;

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Metodo que verifica si hay duplicidad en el alta
	 */
	public boolean validarDuplicidadAlta(ValoresDto valoresDTO,	UsuarioSesion userSesion) throws PersistenciaExcepcion,	NegocioExcepcion {

		double fechaHoraInicioNanoTime = System.nanoTime();
		boolean existenDuplicados = false;
		List<String> numerosDuplicados = new ArrayList<String>();
		
		for (int i = 0; i < valoresDTO.getListaValoresDto().size(); i++) {
			ValorDto valorDtoDuplicado = validarDuplicados(userSesion.getIdUsuario(), valoresDTO.getListaValoresDto().get(i));
			
			if (valorDtoDuplicado.getExisteDuplicado()) {				
				String mensajeDuplicado = armarMensajeDuplicado(valorDtoDuplicado);
				
				numerosDuplicados.add(mensajeDuplicado);
				existenDuplicados = true;
			} else if (!Validaciones.isNullEmptyOrDash(valorDtoDuplicado.getMensajeDuplicadoError())){
				numerosDuplicados.add(valorDtoDuplicado.getMensajeDuplicadoError());
				existenDuplicados = true;
			}
		}
		if (existenDuplicados){
			String mensajeDuplicidad = "No se puede proceder al alta del registro debido a la existencia de duplicidades: ";
			valoresDTO.getErrorValorDuplicadoMensaje().add(mensajeDuplicidad);
			for (String numero : numerosDuplicados) {
				valoresDTO.getErrorValorDuplicadoMensaje().add(numero);
			}
			valoresDTO.setErrorValorDuplicado(true);
		}
		
		Utilidad.tracearTiempo(getClass(), "Tiempo en validar duplicidad un valor ", fechaHoraInicioNanoTime);
		return existenDuplicados;
	}
	
	/**
	 * Metodo que valida duplicidad al querer dar de alta dos valores a la vez.
	 * @param valoresDto
	 * @return
	 */
	public ValoresDto validarAltaValoresSimultaneo(ValoresDto valoresDto)  throws NegocioExcepcion{
		List<ValorDto> listaDuplicadosValorDto = valoresDto.getListaValoresDto();
		ValorDto valorDto = valoresDto.getValorDto();
		
		String errorMensajeDuplicadoTablaBoletas = Mensajes.VALOR_ALTA_DUPLICADO_BOLETA;
		String errorMensajeDuplicadoTablaAvisos = Mensajes.VALOR_ALTA_DUPLICADO_AVISO;
		
		boolean duplicadoBoleta = false;
		boolean duplicado = false;
		
		if (VALOR_PARA_BOLETA_CHEQUE.equals(valorDto.getIdTipoValor())) {

			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());
			
			for (ValorDto valorLista : listaDuplicadosValorDto) {

				if (valorLista.getIdBancoOrigen().equals(valorDto.getIdBancoOrigen())
						&& valorLista.getNumeroCheque().equals(valorDto.getNumeroCheque())
						&& valorLista.getImporte().equals(Utilidad.formatCurrency(bigDecimal, 2))) {
					
					errorMensajeDuplicadoTablaBoletas += " Nro. Cheque " + valorLista.getNumeroCheque()	+ " para Banco Origen " + valorLista.getBancoOrigen() + " con Importe " + valorLista.getImporte() + "";
					duplicado = true;
					duplicadoBoleta = true;
					break;
				}
			}
		} else if(VALOR_PARA_BOLETA_CHEQUE_PD.equals(valorDto.getIdTipoValor())){

			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());
			
			for (ValorDto valorLista : listaDuplicadosValorDto) {

				if (valorLista.getIdBancoOrigen().equals(valorDto.getIdBancoOrigen())
						&& valorLista.getNumeroCheque().equals(valorDto.getNumeroCheque())
						&& valorLista.getImporte().equals(Utilidad.formatCurrency(bigDecimal, 2))
						&& valorLista.getFechaVencimiento().equals(valorDto.getFechaVencimiento())) {
					
					errorMensajeDuplicadoTablaBoletas += " Nro. Cheque " + valorLista.getNumeroCheque()	
							+ " para Banco Origen " + valorLista.getBancoOrigen() 
							+ " con Importe " + valorLista.getImporte() 
							+ " y Fecha Vencimiento " + valorLista.getFechaVencimiento();
					duplicado = true;
					duplicadoBoleta = true;
					break;
				}
			}
		} else if (VALOR_CHEQUE.equals(valorDto.getIdTipoValor())) {

			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());
			
			for (ValorDto valorLista : listaDuplicadosValorDto) {

				if (valorLista.getIdBancoOrigen().equals(valorDto.getIdBancoOrigen())
						&& valorLista.getNumeroCheque().equals(valorDto.getNumeroCheque())
						&& valorLista.getImporte().equals(Utilidad.formatCurrency(bigDecimal, 2))
						&& valorLista.getFechaDeposito().equals(valorDto.getFechaDeposito())) {
					
					errorMensajeDuplicadoTablaAvisos += " Nro. Cheque " + valorLista.getNumeroCheque()	+ " para Banco Origen " + valorLista.getBancoOrigen() + " con Importe " + valorLista.getImporte() + " y Fecha Depósito " + valorLista.getFechaDeposito() + "";
					duplicado = true;
					break;
				}
			}
		} else if (VALOR_CHEQUE_PD.equals(valorDto.getIdTipoValor())) {

			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());

			for (ValorDto valorLista : listaDuplicadosValorDto) {

				if (valorLista.getIdBancoOrigen().equals(valorDto.getIdBancoOrigen())
						&& valorLista.getNumeroCheque().equals(valorDto.getNumeroCheque())
						&& valorLista.getImporte().equals(Utilidad.formatCurrency(bigDecimal, 2))
						&& valorLista.getFechaDeposito().equals(valorDto.getFechaDeposito())
						&& valorLista.getFechaVencimiento().equals(valorDto.getFechaVencimiento())) {
					errorMensajeDuplicadoTablaAvisos += " Nro. Cheque " + valorLista.getNumeroCheque()	
													  + " para Banco Origen " + valorLista.getBancoOrigen() 
													  + " con Importe " + valorLista.getImporte() 
													  + " con Fecha Depósito " + valorLista.getFechaDeposito()
													  + " y Fecha Vencimiento " + valorLista.getFechaVencimiento();
					duplicado = true;
					break;
				}
			}
		} else if(VALOR_PARA_TRANSFERENCIA.equals(valorDto.getIdTipoValor())){
			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());

			for (ValorDto valorLista : listaDuplicadosValorDto) {
				if (valorLista.getNumeroReferencia().equals(valorDto.getNumeroReferencia())
						&& valorLista.getFechaTransferencia().equals(valorDto.getFechaTransferencia())
						&& valorLista.getImporte().equals(Utilidad.formatCurrency(bigDecimal, 2))
						&& valorLista.getIdAcuerdo().equals(valorDto.getIdAcuerdo())) {
					
					errorMensajeDuplicadoTablaAvisos += "Nro. Referencia " + valorLista.getNumeroReferencia()	+ " para Fecha de Transferencia " + valorLista.getFechaTransferencia() + " con Importe " + valorLista.getImporte() + "y Acuerdo " + valorLista.getIdAcuerdo() + "";
					duplicado = true;
					break;
				}
			}
		} else if(VALOR_PARA_RETENCION.equals(valorDto.getIdTipoValor())){
			for (ValorDto valorLista : listaDuplicadosValorDto) {
				if (valorLista.getTipoImpuesto().equals(valorDto.getTipoImpuesto())
						&& valorLista.getNroConstancia().equals(valorDto.getNroConstancia())
						&& valorLista.getCodCliente().equals(valorDto.getCliente().getIdClienteLegado())) {
					
					errorMensajeDuplicadoTablaAvisos += "Nro. Constancia " + valorLista.getNroConstancia() + " para Tipo de Retención " + valorLista.getTipoImpuesto()	+ " para el Cliente " + valorLista.getCodCliente();
					duplicado = true;
					break;
				}
			}
		}

		
		if (duplicado == false) {
			List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
			BigDecimal bigDecimal;	
			bigDecimal = (BigDecimal) Utilidad.stringToBigDecimal(valorDto.getImporte());
			valorDto.setImporte(Utilidad.formatCurrency(bigDecimal, 2) );
			valoresDto.getListaValoresDto().add(valorDto);
			int i = 0;

			Iterator<ValorDto> it = valoresDto.getListaValoresDto().iterator();
			while (it.hasNext()) {
				ValorDto val = it.next();
				val.setId(i);
				i++;
			}

			boolean checkMail = valorDto.isCheckEnviarMailBoleta();
			boolean checkImprimir = valorDto.isCheckImprimirBoleta();
			String empresa = valorDto.getEmpresa();
			String idEmpresa = valorDto.getIdEmpresa();
			String segmento = valorDto.getSegmento();
			String idSegmento = valorDto.getIdSegmento();
			String codCliente = valorDto.getCliente().getIdClienteLegado();
			String razonSocial = valorDto.getRazonSocialClientePerfil();
			String codClienteA = valorDto.getCodClienteAgrupador();
			String razonSocialA = valorDto.getRazonSocialClienteAgrupador();
			String numeroHolding = valorDto.getNumeroHolding();
			String nombreHolding = valorDto.getNombreHolding();
			String email = valorDto.getEmail();
			String idAnalista = valorDto.getIdAnalista();
			String idCopropietario = valorDto.getIdCopropietario();
			String idTipoValor = valorDto.getIdTipoValor();
			String chequeAreemplarnumero=valorDto.getChequeReemplazarNumero();
			String chequeAreemplazarId= valorDto.getChequeReemplazarId();
			valorDto = new ValorDto();
			if(valoresDto.isMantenerComprobantesAdjuntos()){
				listaComprobantes.addAll(valoresDto.getValorDto().getListaComprobantes());
				valorDto.setComboComprobante(false);
			}
			
			valorDto.setListaComprobantes(listaComprobantes);
			valorDto.setCheckEnviarMailBoleta(checkMail);
			valorDto.setCheckImprimirBoleta(checkImprimir);
			valorDto.setEmpresa(empresa);
			valorDto.setIdEmpresa(idEmpresa);
			valorDto.setSegmento(segmento);
			valorDto.setIdSegmento(idSegmento);
			valorDto.setCodCliente(codCliente);
			valorDto.setRazonSocialClientePerfil(razonSocial);
			valorDto.setCodClienteAgrupador(codClienteA);
			valorDto.setRazonSocialClienteAgrupador(razonSocialA);
			valorDto.setNumeroHolding(numeroHolding);
			valorDto.setNombreHolding(nombreHolding);
			valorDto.setEmail(email);
			valorDto.setIdAnalista(idAnalista);
			valorDto.setIdCopropietario(idCopropietario);
			valorDto.setIdTipoValor(idTipoValor);
			valorDto.setChequeReemplazarNumero(chequeAreemplarnumero);
			valorDto.setChequeReemplazarId(chequeAreemplazarId);
			valoresDto.setValorDto(valorDto);

		} else {
			valoresDto.setDuplicado(true);
			valoresDto.setValorDto(valorDto);
			if(duplicadoBoleta){
				valoresDto.setErrorMensajeDuplicadoTabla(errorMensajeDuplicadoTablaBoletas);				
			} else {
				valoresDto.setErrorMensajeDuplicadoTabla(errorMensajeDuplicadoTablaAvisos);
			}
		}

		return valoresDto;
	}

	
	public ValorDto validarRecibo(ValorDto valorDto) throws NegocioExcepcion {
		String mensajeError = null;
		if (!Validaciones.isNullOrEmpty(valorDto.getReciboPreImpreso())) {
			mensajeError = reciboServicio.validarRecibo(valorDto);
		}
		if (!Validaciones.isNullOrEmpty(mensajeError)) {
			valorDto.setExisteReciboError(true);
			valorDto.setMensajeReciboError(mensajeError);
		}
		return valorDto;
	}
	
	public boolean validarUnicidadRecibo(ValoresDto valoresDTO,	UsuarioSesion userSesion) throws PersistenciaExcepcion,	NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		Iterator<ValorDto> it = valoresDTO.getListaValoresDto().iterator();
		boolean duplicado = false;

		while (it.hasNext()) {
			ValorDto valorDTO = it.next();
			valorDTO.setUsuarioModificacion(userSesion.getIdUsuario());
			valorDTO = validarRecibo(valorDTO);
			if (valorDTO.isExisteReciboError()) {
				duplicado = true;
			}
		}
		
		Utilidad.tracearTiempo(getClass(), "Tiempo en validar unicidad del recibo ", fechaHoraInicioNanoTime);
		return duplicado;
	}
	
//	
	/***********************************************************************************
	 * Metodos privados que valida campos
	 **********************************************************************************/

	public void validarComprobantes(MultipartFile file, String descripcion) throws ValidacionExcepcion, NegocioExcepcion {

		if (!Validaciones.esFormatoTexto(descripcion)) {
			throw new ValidacionExcepcion("", "error.formatoTexto", "#errorDescripcionComp");
		} 
		if (file.getOriginalFilename().length() > Constantes.CANT_MAXIMA_CARACTERES_NOMBRE_ARCHIVO) {
			throw new ValidacionExcepcion("","error.nombreArchivoMuyLargo", "#errorArchComprobante");
		} else {
			try {
				if (ControlArchivo.isMultipartFileEmpty(file)) {
					throw new ValidacionExcepcion("","valor.error.archivoVacio", "#errorArchComprobante");
				} else { 
					if (ControlArchivo.esArchivoProhibido(file.getOriginalFilename())) {
						throw new ValidacionExcepcion("","error.archivoProhibido", "#errorArchComprobante");
					} else { 
						if (ControlArchivo.archivoTieneExtensionProhibida(file.getOriginalFilename())) {
							throw new ValidacionExcepcion("","error.extensionProhibida", "#errorArchComprobante");
						} else { 
							if (ControlArchivo.superaPesoMaximoPermitido(file)) {
								throw new ValidacionExcepcion("","error.pesoMaximoSuperado", "#errorArchComprobante");
							} 
						}
					}
				}
			} catch (IOException e) {
				throw new NegocioExcepcion(e);
			}
		}
	}
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/

	public IValorDao getValorDao() {
		return valorDao;
	}

	public void setValorDao(IValorDao valorDao) {
		this.valorDao = valorDao;
	}

	public IWorkflowValores getWorkflowValores() {
		return workflowValores;
	}

	public void setWorkflowValores(IWorkflowValores workflowValores) {
		this.workflowValores = workflowValores;
	}

	public ValorEfectivoMapeador getValorEfectivoMapeador() {
		return valorEfectivoMapeador;
	}

	public void setValorEfectivoMapeador(
			ValorEfectivoMapeador valorEfectivoMapeador) {
		this.valorEfectivoMapeador = valorEfectivoMapeador;
	}

	public ValorChequeMapeador getValorChequeMapeador() {
		return valorChequeMapeador;
	}

	public void setValorChequeMapeador(ValorChequeMapeador valorChequeMapeador) {
		this.valorChequeMapeador = valorChequeMapeador;
	}

	public ValorChequeDiferidoMapeador getValorChequeDiferidoMapeador() {
		return valorChequeDiferidoMapeador;
	}

	public void setValorChequeDiferidoMapeador(
			ValorChequeDiferidoMapeador valorChequeDiferidoMapeador) {
		this.valorChequeDiferidoMapeador = valorChequeDiferidoMapeador;
	}

	public ValorInterdepositoMapeador getValorInterdepositoMapeador() {
		return valorInterdepositoMapeador;
	}

	public void setValorInterdepositoMapeador(
			ValorInterdepositoMapeador valorInterdepositoMapeador) {
		this.valorInterdepositoMapeador = valorInterdepositoMapeador;
	}

	public ValorTransferenciaMapeador getValorTransferenciaMapeador() {
		return valorTransferenciaMapeador;
	}

	public void setValorTransferenciaMapeador(
			ValorTransferenciaMapeador valorTransferenciaMapeador) {
		this.valorTransferenciaMapeador = valorTransferenciaMapeador;
	}

	public ValorRetencionMapeador getValorRetencionMapeador() {
		return valorRetencionMapeador;
	}

	public void setValorRetencionMapeador(
			ValorRetencionMapeador valorRetencionMapeador) {
		this.valorRetencionMapeador = valorRetencionMapeador;
	}

	public ValorBoletaDepositoEfectivoMapeador getValorBoletaDepositoEfectivoMapeador() {
		return valorBoletaDepositoEfectivoMapeador;
	}

	public void setValorBoletaDepositoEfectivoMapeador(
			ValorBoletaDepositoEfectivoMapeador valorBoletaDepositoEfectivoMapeador) {
		this.valorBoletaDepositoEfectivoMapeador = valorBoletaDepositoEfectivoMapeador;
	}

	public ValorBoletaDepositoChequeMapeador getValorBoletaDepositoChequeMapeador() {
		return valorBoletaDepositoChequeMapeador;
	}

	public void setValorBoletaDepositoChequeMapeador(
			ValorBoletaDepositoChequeMapeador valorBoletaDepositoChequeMapeador) {
		this.valorBoletaDepositoChequeMapeador = valorBoletaDepositoChequeMapeador;
	}

	public ValorBoletaDepositoChequeDiferidoMapeador getValorBoletaDepositoChequeDiferidoMapeador() {
		return valorBoletaDepositoChequeDiferidoMapeador;
	}

	public void setValorBoletaDepositoChequeDiferidoMapeador(
			ValorBoletaDepositoChequeDiferidoMapeador valorBoletaDepositoChequeDiferidoMapeador) {
		this.valorBoletaDepositoChequeDiferidoMapeador = valorBoletaDepositoChequeDiferidoMapeador;
	}
	public ValorVistaMapeador getValorVistaMapeador() {
		return valorVistaMapeador;
	}

	public void setValorVistaMapeador(ValorVistaMapeador valorVistaMapeador) {
		this.valorVistaMapeador = valorVistaMapeador;
	}

	public IBoletaSinValorServicio getBoletaSinValorServicio() {
		return boletaSinValorServicio;
	}

	public void setBoletaSinValorServicio(
			IBoletaSinValorServicio boletaSinValorServicio) {
		this.boletaSinValorServicio = boletaSinValorServicio;
	}

	/**
	 * @return the resultadoBusquedaValorMapeador
	 */
	public MapeadorResultadoBusqueda getResultadoBusquedaValorMapeador() {
		return resultadoBusquedaValorMapeador;
	}

	/**
	 * @param resultadoBusquedaValorMapeador the resultadoBusquedaValorMapeador to set
	 */
	public void setResultadoBusquedaValorMapeador(
			MapeadorResultadoBusqueda resultadoBusquedaValorMapeador) {
		this.resultadoBusquedaValorMapeador = resultadoBusquedaValorMapeador;
	}

	public IAcuerdoDao getAcuerdoDao() {
		return acuerdoDao;
	}

	public void setAcuerdoDao(IAcuerdoDao acuerdoDao) {
		this.acuerdoDao = acuerdoDao;
	}

	public IRegistroAVCServicio getRegistroAvcServicio() {
		return registroAvcServicio;
	}

	public void setRegistroAvcServicio(IRegistroAVCServicio registroAvcServicio) {
		this.registroAvcServicio = registroAvcServicio;
	}

	public IEmpresaDao getEmpresaDao() {
		return empresaDao;
	}

	public void setEmpresaDao(IEmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}

	public ISegmentoDao getSegmentoDao() {
		return segmentoDao;
	}

	public void setSegmentoDao(ISegmentoDao segmentoDao) {
		this.segmentoDao = segmentoDao;
	}

	public ITeamComercialServicio getTeamComercialServicio() {
		return teamComercialServicio;
	}

	public void setTeamComercialServicio(ITeamComercialServicio teamComercialServicio) {
		this.teamComercialServicio = teamComercialServicio;
	}

	public IValorConstanciaRecepcionServicio getValorConstanciaRecepcionServicio() {
		return valorConstanciaRecepcionServicio;
	}

	public void setValorConstanciaRecepcionServicio(
			IValorConstanciaRecepcionServicio valorConstanciaRecepcionServicio) {
		this.valorConstanciaRecepcionServicio = valorConstanciaRecepcionServicio;
	}

	/**
	 * @return the boletaDao
	 */
	public IBoletaDao getBoletaDao() {
		return boletaDao;
	}

	/**
	 * @param boletaDao the boletaDao to set
	 */
	public void setBoletaDao(IBoletaDao boletaDao) {
		this.boletaDao = boletaDao;
	}

	/**
	 * @return the workflowDao
	 */
	public IWorkflowDao getWorkflowDao() {
		return workflowDao;
	}

	/**
	 * @param workflowDao the workflowDao to set
	 */
	public void setWorkflowDao(IWorkflowDao workflowDao) {
		this.workflowDao = workflowDao;
	}

}
