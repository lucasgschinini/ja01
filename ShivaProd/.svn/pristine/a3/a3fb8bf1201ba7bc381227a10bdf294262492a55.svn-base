package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.comparador.ComparatorDocumentoCapDto;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenHistoricoObservaciones;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenModificacionCobroTransaccionDto;
import ar.com.telecom.shiva.base.comparador.ComparatorShvCobEdOtrosMedioPagoWrapper;
import ar.com.telecom.shiva.base.comparador.ComparatorShvWfWorkflowMarca;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.AccionesReglaCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoActivoInactivoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDocSapEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTramiteDeimosEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.QueryMarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.SemaforoGestionabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.SubTipoCompensacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoAccionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoReglaCobroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.SimulacionCobroExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionFactura;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionPaginadoDebito;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.jms.util.runnable.ConsultaDebitosACobradoresThread;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.SapConsultaPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.SapS4ConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.Cliente;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.InformacionParaPaginadoDebito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaDeimosConsultaEstadoDocumentoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeimosServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.bean.BuilderConsultaSap;
import ar.com.telecom.shiva.negocio.bean.ImportacionDebitosAuxiliar;
import ar.com.telecom.shiva.negocio.bean.TotalAcumuladoresTransacciones;
import ar.com.telecom.shiva.negocio.dto.CuitHoldingAgenciaNegocioDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineClienteMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineCreditoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineDebitoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineDocumentoAdjuntoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineHistorialMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineOtrosDebitoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineTransaccionesMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobrosOnlineVistaMapeador;
import ar.com.telecom.shiva.negocio.semaforo.SemaforoGestionabilidadCredito;
import ar.com.telecom.shiva.negocio.semaforo.SemaforoGestionabilidadDebito;
import ar.com.telecom.shiva.negocio.semaforo.SemaforoGestionabilidadDocumentoCap;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionCalipsoServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.ICombosServicio;
import ar.com.telecom.shiva.negocio.servicios.IExcelServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParamRespWfTareaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IReglaCobroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.validacion.ICobroOnlineValidacionServicio;
import ar.com.telecom.shiva.negocio.simulacionCoherencia.ShvCobEdCobroDatosSerializados;
import ar.com.telecom.shiva.negocio.simulacionCoherencia.ShvCobEdCreditoWrapper;
import ar.com.telecom.shiva.negocio.simulacionCoherencia.ShvCobEdDebitoWrapper;
import ar.com.telecom.shiva.negocio.simulacionCoherencia.ShvCobEdOtroDebitoWrapper;
import ar.com.telecom.shiva.negocio.simulacionCoherencia.ShvCobEdOtrosMedioPagoWrapper;
import ar.com.telecom.shiva.negocio.simulacionCoherencia.ShvCobEdTratamientoDiferenciaWrapper;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteCobrosOnline;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaCobroHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaCobroTransaccion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoCobroCreditoDebito;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineClienteDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineCreditoDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDebitoDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineMedioPagoDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineOtrosDebitoDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoAdjuntoDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IMedioPagoDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoDao;
import ar.com.telecom.shiva.persistencia.dao.IParamRespWfTareaDao;
import ar.com.telecom.shiva.persistencia.dao.ITareaDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoComprobanteDao;
import ar.com.telecom.shiva.persistencia.dao.ITransaccionCobroDao;
import ar.com.telecom.shiva.persistencia.dao.IWorkflowDao;
import ar.com.telecom.shiva.persistencia.dao.impl.CobroOnlineDaoImpl;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobroAdjuntoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCodigoOperacionExterna;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionCesionCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionIIBB;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionIntercompany;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionLiquidoProducto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionOtras;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionProveedor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoPlanDePago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobOperacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaArchivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTarea;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstadoHist;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowMarca;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamReglaCobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoComprobante;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimple;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdClienteSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificadoConWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroMedioDePagoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroOtrosDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTratamientoDiferenciaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoGestionableDTO;
import ar.com.telecom.shiva.presentacion.bean.dto.GestionabilidadDto;
import ar.com.telecom.shiva.presentacion.bean.dto.HistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CobroJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DeimosJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ImportarDebitoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.TransaccionesJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.PerfilFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ReglaCobroFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaCobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroTransaccionFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.CreditosControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.salida.ShivaConsultaCreditoSalida;
import ar.com.telecom.shiva.presentacion.bean.paginacion.salida.ShivaControlPaginado;

public class CobroOnlineServicioImpl extends Servicio implements ICobroOnlineServicio {
	
	@Autowired ICobroOnlineDao cobroOnlineDao;
	@Autowired ICobroOnlineClienteDao cobroClienteDao;
	@Autowired ICobroOnlineDebitoDao cobroDebitoDao;
	@Autowired ICobroOnlineOtrosDebitoDao cobroOtrosDebitoDao;
	@Autowired ICobroOnlineCreditoDao cobroCreditoDao;
	@Autowired ICobroOnlineMedioPagoDao cobroMedioPagoDao;
	@Autowired IWorkflowCobros workflowCobros;
	@Autowired IWorkflowService workflowService;
	@Autowired IWorkflowDao workflowDao;
	@Autowired IClienteSiebelServicio clienteSiebelServicio;
	@Autowired IGenericoDao genericoDao;
	@Autowired IDocumentoAdjuntoDao documentoAdjuntoDao;
	@Autowired MailServicio mailServicio;
	@Autowired ITareaServicio tareaServicio;
	@Autowired ITareaDao tareaDao;
	@Autowired IClienteDeimosServicio clienteDeimosServicio;
	@Autowired ICobroOnlineValidacionServicio cobroOnlineValidacionServicio;
	@Autowired IExcelServicio exportacionDetalleCobro;
	@Autowired ICombosServicio combosServicio;	
	@Autowired ILdapServicio ldapServicio;
	@Autowired IMotivoDao motivoDao;	
	@Autowired ICobroDao cobroDao;
	@Autowired IMedioPagoDao medioPagoDao;
	@Autowired IVistaSoporteServicio vistaSoporteServicio;	
	@Autowired ICobroBatchSimulacionServicio cobroBatchSimulacionServicio;
	@Autowired ICobroBatchServicio cobroBatchServicio;
	@Autowired IParametroServicio parametroServicio;
	@Autowired CobrosOnlineVistaMapeador cobroOnlineVistaMapeo;	
	@Autowired CobroOnlineDebitoMapeador debitoMapeador;
	@Autowired CobroOnlineOtrosDebitoMapeador otrosDebitoMapeador;
	@Autowired CobroOnlineCreditoMapeador creditoMapeador;
	@Autowired CobroOnlineClienteMapeador clienteMapeador;
	@Autowired CobroOnlineHistorialMapeador cobroOnlineHistorialMapeo;
	@Autowired CobroOnlineTransaccionesMapeador cobroOnlineTransaccionesMapeador;
	@Autowired CobroOnlineDocumentoAdjuntoMapeador adjuntoMapeador;
	@Autowired IDescobroDao descobroDao;
	@Autowired ITeamComercialServicio teamComercialServicio;	
	@Autowired IOperacionMasivaServicio operacionMasivaServicio;
	@Autowired IClienteCalipsoServicio clienteCalipsoServicio;
	@Autowired IMicJmsSyncServicio  micJmsSyncServicio;
	@Autowired IReglaCobroServicio reglaCobroServicio;
	@Autowired ITipoComprobanteDao tipoComprobanteDao;
	@Autowired IParamRespWfTareaDao paramRespWfTareaDao;
	@Autowired IParamRespWfTareaServicio paramRespWfTareaServicio;
		
	@Autowired ICobroBatchSoporteServicio cobroBatchSoporteServicio;
	@Autowired ICobroBatchSoporteImputacionServicio cobroBatchSoporteImputacionServicio;
	@Autowired SapS4ConsultaProveedoresWS sapS4ConsultaProveedoresWS;
	@Autowired SapConsultaPartidasWS sapConsultaPartidasWS;
	@Autowired private IClienteServicio clienteServicio;
	@Autowired private CobroOnlineDaoImpl cobroDaoImpl;
	@Autowired ITransaccionCobroDao transaccionCobroDao;
	@Autowired ICobroBatchSoporteImputacionCalipsoServicio cobroBatchSoporteImputacionCalipsoServicio;
	
	private String xls = ".xls";
	public static final String XLS_CONTENT_TYPE = "application/vnd.ms-excel";
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String ATTACHMENT_FILENAME = "attachment; filename=";
	
	//LISTAS PARA CONSULTAR A LOS COBRADORES MIC, CALIPSO
	public final int LIMITE_MIC=30;
	public final int LIMITE_CALIPSO = 15;
	public List<ShvCobEdDebito> listaDebitosOriginal;
	public List<ShvCobEdDebito> listaDebitosMic = new ArrayList<ShvCobEdDebito>();
	public List<ShvCobEdDebito> listaDebitosCalipso = new ArrayList<ShvCobEdDebito>();
	public List<ConsultaDebitosACobradoresThread> listaThreads = new ArrayList<ConsultaDebitosACobradoresThread>();

	
	/****************************************************************************************************
	 * Servicios Comunes
	 ***************************************************************************************************/
	@Override
	public CobroDto buscarCobro(Long idCobro) throws NegocioExcepcion {
 		try {
  			CobroDto cobroDto = null;
			ShvCobEdCobro cobro = cobroOnlineDao.buscarCobro(idCobro);
			
			cobroDto = (CobroDto) defaultMapeador.map(cobro);
			return cobroDto;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	public CobroDto buscarCobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion {
 		try {
  			CobroDto cobroDto = null;
			ShvCobEdCobro cobro = cobroOnlineDao.buscarCobroPorIdOperacion(idOperacion);
			
			if(!Validaciones.isObjectNull(cobro)) {
				cobroDto = (CobroDto) defaultMapeador.map(cobro);
			}
			return cobroDto;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public CobroDebitoDto mapearDebito(ShvCobEdDebito debito) throws NegocioExcepcion {
		
		CobroDebitoDto debitoDto = null;
		
		if(!Validaciones.isObjectNull(debito)) {
				debitoDto = (CobroDebitoDto) debitoMapeador.map(debito);
		}
		return debitoDto;
	}
	
	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		return null; 
	}

	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		return null;
	}


	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public Long crear(DTO dto) throws NegocioExcepcion {
		CobroDto cobroDto = (CobroDto) dto;
		
		// Cobro
		ShvCobEdCobro cobro = (ShvCobEdCobro) defaultMapeador.map(cobroDto, null);
		cobro.setIdAnalistaTeamComercial(cobroDto.getIdAnalistaTeamComercial());
		cobro.setNombreApellidoAnalistaTeamComercial(cobroDto.getNombreAnalistaTeamComercial());

//		Set<ComprobanteDto> listaComprobantes = cobroDto.getListaComprobantes();
//		Set<ComprobanteDto> listaComprobanteAplicacionManual = cobroDto.getListaComprobanteAplicacionManual();
		// Adjuntos
//		if(!Validaciones.isObjectNull(cobroDto.getListaComprobantes())) {
//		@SuppressWarnings("unchecked")
//		Set<ShvCobEdCobroAdjunto> comprobantes = (HashSet<ShvCobEdCobroAdjunto>) adjuntoMapeador.map(cobroDto.getListaComprobantes());
//		cobro.setComprobantes(comprobantes);
//		}
		
		
		cobro = crear(cobro);
		
		
//		if (cobroDto.getListaComprobantes()!= null) {
//			for (ComprobanteDto comprobanteDto : cobroDto.getListaComprobantes()) {
//				ShvCobEdCobroAdjunto documentoAdjunto = (ShvCobEdCobroAdjunto) adjuntoMapeador.map(comprobanteDto, null);
//				documentoAdjunto.getPk().setIdCobro(cobro.getIdCobro());
//				
//				guardarAdjuntos(documentoAdjunto);
//			}
//		}
		return cobro.getIdCobro();
	}

	
//	@Override
//	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
//	public ShvCobEdCobro crear(ShvCobEdCobro cobro) throws NegocioExcepcion {
//		return crear(cobro, null);
//	}

	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvCobEdCobro crear(ShvCobEdCobro cobro) throws NegocioExcepcion {
		
		try {
			//Mapeos
			cobro.setIdOperacion(genericoDao.proximoValorSecuencia("SEQ_SHV_COB_OPERACION"));
			cobro.setIdCobro(genericoDao.proximoValorSecuencia("SEQ_SHV_COB_COBRO"));
			
			if (Validaciones.isObjectNull(cobro.getIdCobroPadre())) {
				cobro.setIdCobroPadre(cobro.getIdCobro());
			}
			//Fin-Mapeos
			
			ShvWfWorkflow wf = workflowCobros.crearWorkflow("", cobro.getUsuarioUltimaModificacion());
			
			wf = workflowCobros.guardarCobroEnEdicion(wf, "", cobro.getUsuarioUltimaModificacion());
			cobro.setWorkflow(wf);
			
			// Actualizo el importe total
			cobro.setImporteTotal(calcularImporteTotal(cobro));


//			if (!Validaciones.isObjectNull(cobro.getComprobantes())) {
//				for (ShvCobEdCobroAdjunto adjunto : cobro.getComprobantes()) {
//					guardarAdjuntos(cobro);
//				}
//			}

//			guardarClientes(cobro);
//			guardarDebitos(cobro);
//			guardarCreditos(cobro);
//			guardarMediosPago(cobro);
			
			cobro = cobroOnlineDao.guardarCobro(cobro);
			
			return cobro;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void modificar(DTO dto) throws NegocioExcepcion {
		try {
			CobroDto cobroDto = (CobroDto) dto;
			
			//Limpio las listas
			cobroClienteDao.borrarClientesDelCobro(cobroDto.getIdCobro());
			cobroDebitoDao.borrarDebitosDelCobro(cobroDto.getIdCobro());
			cobroOtrosDebitoDao.borrarOtrosDebitosDelCobro(cobroDto.getIdCobro());
			cobroCreditoDao.borrarCreditosDelCobro(cobroDto.getIdCobro());
			cobroMedioPagoDao.borrarMediosPagoDelCobro(cobroDto.getIdCobro());
			
			ShvCobEdCobro cobro = cobroOnlineDao.buscarCobro(cobroDto.getIdCobro());
			
			//Mapeando
			cobro = (ShvCobEdCobro) defaultMapeador.map(cobroDto, cobro);
			cobro.setNombreApellidoAnalistaTeamComercial(cobroDto.getNombreAnalistaTeamComercial());
			cobro.setIdAnalistaTeamComercial(cobroDto.getIdAnalistaTeamComercial());
			
			//Workflow
			if (this.verificarDebitos(cobro)) {
				if (Estado.COB_EN_EDICION.equals(cobro.getWorkflow().getEstado())) {
					ShvWfWorkflow wf = workflowCobros.solicitarVerificacionDebitos(cobro.getWorkflow(), "", cobroDto.getUsuarioModificacion());
					cobro.setWorkflow(wf);
				} else if (Estado.COB_RECHAZADO.equals(cobro.getWorkflow().getEstado())) {
					ShvWfWorkflow wf = workflowCobros.solicitarVerificacionDebitosCobroRechazado(cobro.getWorkflow(), "", cobroDto.getUsuarioModificacion());
					cobro.setWorkflow(wf);
				}
			}
			if (
				this.esPersistirObservacionEnEstado(cobro.getWorkflow().getWorkflowEstado().getEstado()) &&
				!Validaciones.isNullEmptyOrDash(cobro.getObservacion())
			) {
				cobro = this.pasarObservacionEnEstado(cobro);
			}
			// Actualizo el importe total
			cobro.setImporteTotal(calcularImporteTotal(cobro));
			
			//Guardo el cobro
			cobro = cobroOnlineDao.guardarCobro(cobro);
			if (
				!Validaciones.isObjectNull(cobroDto.isBorrarAdjuntoAplicacionManal()) &&
				cobroDto.isBorrarAdjuntoAplicacionManal() &&
				!Validaciones.isNullEmptyOrDash(cobroDto.getIdAdjuntoApliacionManualPrev())
			) {
				Traza.auditoria(getClass(), "Se borra el adjunto de apliacion Manual Previsualizar");
				this.eliminarAdjuntoCobro(new Long(cobroDto.getIdAdjuntoApliacionManualPrev()));
			}
			
			if (!Validaciones.isObjectNull(cobroDto.isEliminarTodosAdjuntosOtrosDeb())) {
				if(cobroDto.isEliminarTodosAdjuntosOtrosDeb()) {
					cobroOtrosDebitoDao.eliminarAdjuntosDeOtrosDeb(cobroDto.getListaIdsAdjuntosOtrosDebitos());
				}
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
	}
	
	/**
	 * El importe total del cobro será la suma de los medios de pago que se hayan seleccionado para configurar el cobro
	 * mas el tratamiento de la diferencia cuando la misma es tratada como Debito a Proxima Factura (que termina siendo
	 * un medio de pago)
	 *  
	 * @param edCobro
	 * @return
	 */
	private BigDecimal calcularImporteTotal(ShvCobEdCobro edCobro) {
		
		BigDecimal importeTotal = BigDecimal.ZERO;
		
		for (ShvCobEdCredito credito : edCobro.getCreditos()) {
			importeTotal = importeTotal.add(credito.getImporteAUtilizar());
		}
		
		for (ShvCobEdOtrosMedioPago otroMedioPago : edCobro.getMediosPagos()) {
			importeTotal = importeTotal.add(otroMedioPago.getImporte());
		}

		if (!Validaciones.isObjectNull(edCobro.getTratamientoDiferencia())) {
			if (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.equals(edCobro.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
				importeTotal = importeTotal.add(edCobro.getTratamientoDiferencia().getImporte());
			}
		}
		
		return importeTotal;
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void anular(DTO dto) throws NegocioExcepcion {
		
		CobroDto cobroDto = (CobroDto) dto;
		String usuarioModificacion = cobroDto.getUsuarioModificacion();
		
		try {
			ShvCobEdCobro cobroModelo = cobroOnlineDao.buscarCobro(cobroDto.getIdCobro());
			
			ShvWfWorkflow workflow = cobroModelo.getWorkflow();

			// 
			// Si el cobro 
			//
			List<ShvWfTarea> tarea = tareaDao.buscarTareasPendientes(workflow.getIdWorkflow());
			for (ShvWfTarea shvWfTarea : tarea) {
				switch (shvWfTarea.getTipoTarea()) {
				
					case COB_REV_RECH: 
						tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_RECH, workflow.getIdWorkflow(), new Date(), usuarioModificacion,null);
						break;
					case COB_REV_COB_ERR: 
						tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_COB_ERR, workflow.getIdWorkflow(), new Date(), usuarioModificacion,null);
						break;
					case COB_REV_COB_APR:
						tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_COB_APR, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
						break;
					case COB_ERR_CONF_APLIC_MANUAL:
						tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
						break;
					default:
						break;
				}
			}				

			//
			// Anulo el cobro, verificando previamente en que estado estoy
			//
			switch (cobroModelo.getWorkflow().getEstado()) {
				case COB_EN_EDICION:
					workflowCobros.anularCobroEnEdicion(workflow, "", usuarioModificacion);
				break;
				case COB_RECHAZADO:
					workflowCobros.anularCobroRechazado(workflow, "", usuarioModificacion);
				break;
				case COB_ERROR_COBRO: 
					workflowCobros.anularCobroErrorEnCobro(workflow, "", usuarioModificacion);
				break;
				case COB_ERROR_APROPIACION: 
					workflowCobros.anularCobroErrorEnApropiacion(workflow, "", usuarioModificacion);
				break;
				default: break;
			}
			
	    } catch (NumberFormatException | PersistenciaExcepcion e) {
	        throw new NegocioExcepcion(e);
	    }
	}

	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}

	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {

	}
	
	/****************************************************************************************************
	 * Privados
	 ***************************************************************************************************/
	
//	/**
//	 * Guardo los clientes
//	 * @param cobro
//	 * @throws NegocioExcepcion
//	 * @throws PersistenciaExcepcion
//	 */
//	private void guardarClientes(ShvCobEdCobro cobro) throws NegocioExcepcion, PersistenciaExcepcion {
//		
//		if(Validaciones.isCollectionNotEmpty(cobro.getClientes())){
//
//			long contador = 0;
//			for (ShvCobEdCliente cliente : cobro.getClientes()) {
//
//				contador ++;
//				ShvCobEdClientePk pk = new ShvCobEdClientePk();
//				pk.setCobro(cobro);
//				pk.setIdClienteCobro(contador);
//				cliente.setPk(pk);
//				
////				cobroClienteDao.guardarCliente(cliente);
//			}
//		}
//	}
	
//	/**
//	 * Guardo los debitos
//	 * @param cobro
//	 * @throws NegocioExcepcion
//	 * @throws PersistenciaExcepcion
//	 */
//	private void guardarDebitos(ShvCobEdCobro cobro) throws NegocioExcepcion, PersistenciaExcepcion {
//		
//		if (Validaciones.isCollectionNotEmpty(cobro.getDebitos())) {
//
//			long contador = 0;
//			for (ShvCobEdDebito debito : cobro.getDebitos()) {
//
//				contador ++;
//				ShvCobEdDebitoPk pk = new ShvCobEdDebitoPk();
//				pk.setCobro(cobro);
//				pk.setIdDebito(contador);
//				debito.setPk(pk);
//				
////				cobroDebitoDao.guardarDebito(debito);
//			}
//		}
//	}
	
//	/**
//	 * Guardo los creditos
//	 * @param cobro
//	 * @throws NegocioExcepcion
//	 * @throws PersistenciaExcepcion
//	 */
//	private void guardarCreditos(ShvCobEdCobro cobro) throws NegocioExcepcion, PersistenciaExcepcion {
//		
//		if (Validaciones.isCollectionNotEmpty(cobro.getCreditos())) {
//			
//			long contador = 0;
//			for (ShvCobEdCredito credito : cobro.getCreditos()) {
//
//				contador ++;
//				ShvCobEdCreditoPk pk = new ShvCobEdCreditoPk();
//				pk.setCobro(cobro);
//				pk.setIdCredito(contador);
//				credito.setPk(pk);
//				
////				cobroCreditoDao.guardarCredito(credito);
//			}
//		}
//	}
	
//	/**
//	 * Guardo los medios de pago
//	 * @param cobro
//	 * @throws NegocioExcepcion
//	 * @throws PersistenciaExcepcion
//	 */
//	private void guardarMediosPago(ShvCobEdCobro cobro) throws NegocioExcepcion, PersistenciaExcepcion {
//		
//		if (Validaciones.isCollectionNotEmpty(cobro.getMediosPagos())) {
//
////			long contador = 0;
//			for (ShvCobEdOtrosMedioPago otroMedioPago : cobro.getMediosPagos()) {
//
////				contador++;
////				ShvCobEdOtrosMedioPagoPk pk = new ShvCobEdOtrosMedioPagoPk();
////				pk.setCobro(cobro);
////				pk.setIdMedioPago(contador);
////				otroMedioPago.setPk(pk);
//
////				cobroMedioPagoDao.guardarMedioPago(otroMedioPago);
//			}
//		}
//	}
	
//	/**
//	 * 
//	 * @param cobroAdjunto
//	 * @throws NegocioExcepcion
//	 */
//	private void guardarAdjuntos(ShvCobEdCobro cobro) throws NegocioExcepcion {
//		try {
////			ShvDocDocumentoAdjunto docAdjunto = new ShvDocDocumentoAdjunto();
////			docAdjunto.setArchivoAdjunto(comprobante.getDocumento());
////			docAdjunto.setDescripcion(comprobante.getDescripcionArchivo());
////			docAdjunto.setFechaCreacion(new Date());
////			docAdjunto.setNombreArchivo(comprobante.getNombreArchivo());
////			docAdjunto.setUsuarioCreacion(comprobante.getUsuarioCreacion());
////			docAdjunto.setIdValor(new ArrayList<ShvValValor>());
//			
////			documentoAdjuntoDao.crear(cobroAdjunto.getPk().getDocumentoAdjunto());
//			
////			ShvCobEdCobroAdjuntoPk cobroAdjuntoPK = new ShvCobEdCobroAdjuntoPk();
////			cobroAdjuntoPK.setDocumentoAdjunto(docAdjunto);
////			cobroAdjuntoPK.setIdCobro(idCobro);
////			cobroAdjuntoPK.setMotivoAdjunto(MotivoAdjuntoEnum.getEnumByName(comprobante.getMotivoAdjunto()));
////			ShvCobEdCobroAdjunto cobroAdjunto = new ShvCobEdCobroAdjunto();
////			cobroAdjunto.setPk(cobroAdjuntoPK);
//			
//			if (!Validaciones.isObjectNull(cobro.getComprobantes())) {
//				for (ShvCobEdCobroAdjunto cobroAdjunto : cobro.getComprobantes()) {
////					cobroAdjunto.getPk().setIdCobro(cobro.getIdCobro());
//					
//					cobroOnlineDao.insertarDocumentoAjunto(cobroAdjunto);
//				}
//			}
//			
////			cobroAdjunto = 
//			
////			comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());
////			
////			return comprobante;
//		} catch (PersistenciaExcepcion e) {
//			throw new NegocioExcepcion (e.getMessage(), e);
//		}
//	}

	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint5 
	 * @param cobro
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void modificarTransaccionesConIntereses(CobroDto cobroDto) throws NegocioExcepcion {
		
		cobroBatchServicio.modificarTransaccionesConIntereses(cobroDto);
		
	}
	
	@Override
	public ComprobanteDto buscarAdjuntoCobro(Long idAdjunto)
			throws NegocioExcepcion {
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
	public CobroOtrosDebitoDto buscarAdjunto(Long idAdjunto)
			throws NegocioExcepcion {
		try {
			ShvDocDocumentoAdjunto adjunto = documentoAdjuntoDao.buscarDocumentoAdjunto(idAdjunto);
			
			if (adjunto!=null) {
				CobroOtrosDebitoDto archivoAdjunto = new CobroOtrosDebitoDto();
				archivoAdjunto.setIdAdjunto(adjunto.getIdValorAdjunto());
				archivoAdjunto.setNombreAdjunto(adjunto.getNombreArchivo());
				archivoAdjunto.setAdjunto(adjunto.getArchivoAdjunto());
				archivoAdjunto.setUsuarioModificacion(adjunto.getUsuarioCreacion());
				archivoAdjunto.setFechaAlta(adjunto.getFechaCreacion());
				
				return archivoAdjunto;
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}

	@Override
	public ComprobanteDto crearAdjuntoCobro(Long idCobro, ComprobanteDto comprobante)
			throws NegocioExcepcion {
		try {
			ShvDocDocumentoAdjunto docAdjunto = new ShvDocDocumentoAdjunto();
			docAdjunto.setArchivoAdjunto(comprobante.getDocumento());
			docAdjunto.setDescripcion(comprobante.getDescripcionArchivo());
			docAdjunto.setFechaCreacion(new Date());
			docAdjunto.setNombreArchivo(comprobante.getNombreArchivo());
			docAdjunto.setUsuarioCreacion(comprobante.getUsuarioCreacion());
			docAdjunto.setIdValor(new ArrayList<ShvValValor>());
			
			docAdjunto = documentoAdjuntoDao.crear(docAdjunto);
			
			ShvCobEdCobroAdjuntoPk cobroAdjuntoPK = new ShvCobEdCobroAdjuntoPk();
			cobroAdjuntoPK.setDocumentoAdjunto(docAdjunto);
			cobroAdjuntoPK.setIdCobro(idCobro);
			cobroAdjuntoPK.setMotivoAdjunto(MotivoAdjuntoEnum.getEnumByName(comprobante.getMotivoAdjunto()));
			
			ShvCobEdCobroAdjunto cobroAdjunto = new ShvCobEdCobroAdjunto();
			cobroAdjunto.setPk(cobroAdjuntoPK);
			
			cobroAdjunto = cobroOnlineDao.insertarDocumentoAjunto(cobroAdjunto);
			
			comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());
			
			return comprobante;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}
	
	@Override
	public void eliminarAdjuntoCobro(Long idAdjunto) throws NegocioExcepcion {
		try {
			Traza.auditoria(getClass(), "Se borra el adjunto de apliacion Manual Previsualizar");
			ShvDocDocumentoAdjunto docAdjunto = documentoAdjuntoDao.buscarDocumentoAdjunto(idAdjunto);
			cobroOnlineDao.eliminarDocumentoAdjuntoDelCobro(docAdjunto);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}
	
	@Override
	public List<ComprobanteDto> buscarAdjuntoPorIdAdjunto(Long idAdjunto) throws NegocioExcepcion {
		
		List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
		List<ShvDocDocumentoAdjunto> listaAdjuntos;
		try {
			listaAdjuntos = cobroDaoImpl.buscarPorIdAdjuntoCobrosOnline(idAdjunto);

			if(Validaciones.isCollectionNotEmpty(listaAdjuntos)){
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
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return listaComprobantes;
	}
	
	/**
	 * Verifico que si hay un pendiente de verificar debitos
	 * @param cobro
	 * @throws NegocioExcepcion
	 * @throws PersistenciaExcepcion
	 */
	private boolean verificarDebitos(ShvCobEdCobro cobro) 
			throws NegocioExcepcion, PersistenciaExcepcion {
		
		if(Validaciones.isCollectionNotEmpty(cobro.getDebitos())){
			for (ShvCobEdDebito debito : cobro.getDebitos()) {
				if (EstadoDebitoImportadoEnum.PENDIENTE.equals(debito.getResultadoValidacionEstado())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/****************************************************************************************************
	 * Tratamientos online
	 ***************************************************************************************************/
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param  multipartFile
	 * @throws NegocioExcepcion 
	 */
	public ImportarDebitoJsonResponse resultadoValidaciones(MultipartFile multipartFile, String cantDebitosGrilla, Long idCobro) throws NegocioExcepcion {
		String[] registros = null;
		File file = ControlArchivo.multipartToFile(multipartFile);
		ImportarDebitoJsonResponse importarDebitoDtoResultFinal = new ImportarDebitoJsonResponse();
		importarDebitoDtoResultFinal = resultadoValidacionesErrores(multipartFile, cantDebitosGrilla, idCobro);
		if (!Validaciones.isNullOrEmpty(importarDebitoDtoResultFinal.getErrors())) {
			return importarDebitoDtoResultFinal;
		} else {
			importarDebitoDtoResultFinal.setSuccess(true);
			try {
				registros = ControlArchivo.leerArchivo(file.getPath(), Constantes.RETORNO_WIN);
				if (registros.length == 1) {
					registros[0] = registros[0].replaceAll(Constantes.RETORNO_UNIX, Constantes.RETORNO_WIN); 
					registros = registros[0].split(Constantes.RETORNO_WIN);
				}
			} catch (ShivaExcepcion e1) {
				throw new NegocioExcepcion(e1.getMessage(), e1);
			}

			List<ClienteDto> listaClientes = new ArrayList<ClienteDto>(); 
			List<CobroDebitoDto> listaDebitos = new ArrayList<CobroDebitoDto>(); 
			Set<String> listaIdClienteLegado = new HashSet<String>();
	
			int registroNumero = 1;
			ClienteBean clienteBean = null;
		
			for (String reg : registros) {
				String[] campos;
				CobroDebitoDto debitoDto = new CobroDebitoDto();
				if (reg.split(Constantes.SEMICOLON).length == 10 ) {
					campos = reg.split(Constantes.SEMICOLON);
				} else {
					String[] aux = reg.concat("ÑXZ").split(Constantes.SEMICOLON);//Se hace esto raro porque el split no splitea como corresponde al tener ;; en el final.
					String str = "";
					for(int i=0; i<=aux.length-1; i++) {
						if (i!=aux.length-1) {
							str += aux[i]+";";
						} else {
							str += aux[i];
						}
					}
					 campos = str.replace("ÑXZ", " ").split(Constantes.SEMICOLON);
				}
				 
				String codigoCliente = "";
				StringBuffer erroCliente = new StringBuffer();
				if (campos[ConstantesCobro.CLIENTE-1].trim().length() <= 10 && Validaciones.isNumeric(campos[ConstantesCobro.CLIENTE-1].trim())) {
					codigoCliente = Utilidad.addStartingZeros(campos[ConstantesCobro.CLIENTE-1].trim(), 10);

					if (!listaIdClienteLegado.contains(codigoCliente)) {
						listaIdClienteLegado.add(codigoCliente);
						
						try{
							clienteBean = this.clienteServicio.consultarCliente(codigoCliente);
						} catch(Throwable ex){
							Traza.error(getClass(), ex.getMessage(), ex);
							importarDebitoDtoResultFinal.setSuccess(false);
							erroCliente.append(ConstantesCobro.LINEA_NRO).append(registroNumero).append(ConstantesCobro.CAUSA);
							if (ex.getCause()!=null && ex.getCause() instanceof RemoteAccessException) {
								erroCliente.append(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.caida"));
							} else {
								erroCliente.append(Propiedades.MENSAJES_PROPIEDADES.getString("conf.cobro.mensaje.siebel.ws.error"));
							}
							erroCliente.append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS).append(codigoCliente);
							erroCliente.append(Constantes.CLOSE_PARENTESIS);
							erroCliente.append(Constantes.PUNTO);
							erroCliente.append(Constantes.CARRIAGE_RETURN);
							
							if (importarDebitoDtoResultFinal.getErrors()!="" && importarDebitoDtoResultFinal.getErrors()!=null) {
								StringBuilder str = new StringBuilder(importarDebitoDtoResultFinal.getErrors());
								str.append(erroCliente.toString());
								importarDebitoDtoResultFinal.setErrors(str.toString());
							}else{
								importarDebitoDtoResultFinal.setErrors(erroCliente.toString());
							}
						}

						if (!Validaciones.isObjectNull(clienteBean)) {
							ClienteDto clienteDto = new ClienteDto();
							clienteDto.setIdClienteLegado(codigoCliente);
							clienteDto.setDescripcionHolding(clienteBean.getDescripcionHolding());
							clienteDto.setRazonSocial(clienteBean.getRazonSocialClienteAgrupador());
							clienteDto.setSegmentoAgencia(clienteBean.getSegmentoAgencia());
							clienteDto.setAgenciaNegocio(clienteBean.getAgenciaNegocio());
							clienteDto.setCuit(clienteBean.getCuit());
							listaClientes.add(clienteDto);
						} else {
							importarDebitoDtoResultFinal.setSuccess(false);
							if(importarDebitoDtoResultFinal.getErrors()!="" && importarDebitoDtoResultFinal.getErrors()!=null){
								importarDebitoDtoResultFinal.setErrors(importarDebitoDtoResultFinal.getErrors() + Constantes.CARRIAGE_RETURN + ConstantesCobro.ERROR_EL_REGISTRO_NRO + registroNumero + ConstantesCobro.ERROR_POSEE_CLIENTE_NO_VALIDO );
							}else{
								importarDebitoDtoResultFinal.setErrors(ConstantesCobro.ERROR_EL_REGISTRO_NRO + registroNumero + ConstantesCobro.ERROR_POSEE_CLIENTE_NO_VALIDO );
							}
						}
					}
				}
				
				debitoDto = debitoMapeador.mapeoDebitoDto(campos, debitoDto);
				
				debitoDto.setCliente(Utilidad.removeStartingZeros(codigoCliente, 1));
				// El servicio de gestionabilidadDeDeuda asigna un semanforo en verde a todos los
				// Debitos que tenga RESULTADO_DE_VALDIDACION = PENDIENTE
				debitoDto.setSemaforo(determinarGestionabilidadDeDeuda(debitoDto));
				//obtenerSaldoMaximoDebito(debitoDto);
				
				try {
					
					String motivo = (Validaciones.isObjectNull(cobroOnlineDao.buscarCobro(idCobro).getMotivo().getIdMotivoCobro()) ? "" : cobroOnlineDao.buscarCobro(idCobro).getMotivo().getIdMotivoCobro().toString());
					obtenerImporteAUtilizarDebito(debitoDto, motivo);
				} catch (PersistenciaExcepcion e2) {
					throw new NegocioExcepcion(e2.getMessage(), e2);
				}
				
				listaDebitos.add(debitoDto);
				
				registroNumero++;
			}
			if (!listaDebitos.isEmpty()) {
				importarDebitoDtoResultFinal.setDebitos(listaDebitos);
			}
			if (!listaClientes.isEmpty()) {
				importarDebitoDtoResultFinal.setClientes(listaClientes);
			}
			return importarDebitoDtoResultFinal;
		}
	}

	

	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param file
	 * @return
	 * @throws NegocioExcepcion 
	 */
	public ImportarDebitoJsonResponse resultadoValidacionesErrores(MultipartFile file, String cantDebitosGrilla, Long idCobro) throws NegocioExcepcion {
		ImportarDebitoJsonResponse importarDebitoDtoResultFinal = new ImportarDebitoJsonResponse();
		importarDebitoDtoResultFinal = validarRestriccionesArchivo(file);
		if(!Validaciones.isNullOrEmpty(importarDebitoDtoResultFinal.getErrors())) {
			return importarDebitoDtoResultFinal;
		}else{
			importarDebitoDtoResultFinal = procesarRegistrosImportarDebitos(file, cantDebitosGrilla, idCobro);
		}
		return importarDebitoDtoResultFinal;
	} 

	/**
	 * Procesa un archivo de Debitos. Si es correcto retorna Lista con Clientes, sino Lista con Errores.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param registros
	 */
	public ImportarDebitoJsonResponse procesarRegistrosImportarDebitos(MultipartFile multipartFile, String cantDebitosGrilla, Long idCobro) throws NegocioExcepcion {
		File file = ControlArchivo.multipartToFile(multipartFile);
		String[] registros = null;
		boolean exedeLimiteCantRegistros = false;
		try {
			registros = ControlArchivo.leerArchivo(file.getPath(), Constantes.RETORNO_WIN);
			if (registros.length == 1) {
				registros[0] = registros[0].replaceAll(Constantes.RETORNO_UNIX, Constantes.RETORNO_WIN);
				registros = registros[0].split(Constantes.RETORNO_WIN);
			}
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		int nroRegistro = 1;
		int nroRegistroAux = 0;
		boolean	errorEnCampos = false;
		boolean	errorDuplicidad = false;
		boolean	errorFormato = false;
		boolean errorDuplicidadDebitosPersistidos = false;
		boolean errorDuc = false;
		boolean errorFinal = false;
		ImportarDebitoJsonResponse importarDebitoDto = new ImportarDebitoJsonResponse();

		CobroDto cobroDto = buscarCobro(idCobro);
		ImportacionDebitosAuxiliar importarDebitosAuxiliar = new ImportacionDebitosAuxiliar(cobroDto, false);
		
		for (@SuppressWarnings("unused") String reg : registros) {
			nroRegistroAux++;
		}
		exedeLimiteCantRegistros = cobroOnlineValidacionServicio.validacionCantDebitosMaximos(nroRegistroAux, cantDebitosGrilla, importarDebitosAuxiliar);
		
		for (String reg : registros) {
			String[] campos = reg.concat("ÑXZ").split(Constantes.SEMICOLON);//Se hace esto raro porque el split no splitea como corresponde al tener ;; en el final.
			String str = "";
			for(int i=0; i<=campos.length-1; i++){
				if(i!=campos.length-1){
					str += campos[i]+";";
				}else{
					str += campos[i];
				}
			}
			String[] camposATR = str.replace("ÑXZ", " ").split(Constantes.SEMICOLON);
			errorFormato = cobroOnlineValidacionServicio.validarFormato(camposATR, nroRegistro, importarDebitosAuxiliar);
			if(errorFormato){
//				importarDebitoDto.setErrors("Error en Formato.");
				errorFinal = true;
			}
			if(!errorFormato){
				errorDuplicidad = cobroOnlineValidacionServicio.validarDuplicidad(camposATR, nroRegistro, importarDebitosAuxiliar);
				if(errorDuplicidad){
					errorFinal = true;
				}
			}
			if((!errorFormato)){
				if((!errorDuplicidad)){
					errorEnCampos = cobroOnlineValidacionServicio.validarCamposArchivoImportarDebitos(camposATR, nroRegistro, importarDebitosAuxiliar);
					if(errorEnCampos){
						errorFinal = true;
					}
				}
			}
			if (!errorFormato && !errorEnCampos) {
				importarDebitosAuxiliar.setPrimerComprobanteEsDuc(camposATR[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
			}
			if(!errorFormato && !errorEnCampos){
				errorDuplicidadDebitosPersistidos = cobroOnlineValidacionServicio.validarDuplicidadEnCobro(camposATR, nroRegistro, importarDebitosAuxiliar);
				if (errorDuplicidadDebitosPersistidos) {
					errorFinal = true;
				}
			}
			if (!errorFormato && !errorEnCampos) {
				errorDuc = cobroOnlineValidacionServicio.validarRegistroDuc(camposATR, nroRegistro, importarDebitosAuxiliar);
				if (errorDuc) {
					errorFinal = true;
				}
			}
			
			
			nroRegistro++;
		}
		if (!errorFinal) {
			if (errorFormato || errorDuplicidad || exedeLimiteCantRegistros || errorEnCampos || errorDuplicidadDebitosPersistidos || errorDuc) {
				errorFinal = true;
			}
		}
		if (errorFinal) {
			importarDebitoDto = cobroOnlineValidacionServicio.setErrorsImportarDebitoDto(importarDebitoDto, importarDebitosAuxiliar);
		}
		//cobroOnlineValidacionServicio.clinearListaDebitos();
		//cobroOnlineValidacionServicio.clinearListaErrores();
		return importarDebitoDto;
	}

	/**
	 * Valida las restricciones de los archivos.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	@Override
	public ImportarDebitoJsonResponse validarRestriccionesArchivo(
			MultipartFile file) throws NegocioExcepcion {
		
		ImportarDebitoJsonResponse importarDebitoDto = new ImportarDebitoJsonResponse();
		
		StringBuffer resultadoValidaciones = new StringBuffer();
		resultadoValidaciones = cobroOnlineValidacionServicio.validarRestriccionesArchivo(file);
		importarDebitoDto.setErrors(resultadoValidaciones.toString());
		importarDebitoDto.setSuccess(false);
		return importarDebitoDto;
	}
	
	/**
	 * Retorna el saldo maximo que se puede pagar para una factura
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param debitoDto
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal obtenerSaldoMaximoDebito(CobroDebitoDto debitoDto, String motivo){
		//
		// @author Dante.Romero u575899
		// Consideraciones:
		// Se modifican los valores de los campos saldoMaxCaso01, saldoMaxCaso02, saldoMaxCaso03,... y saldoMaxDefecto
		// Los mismos se utilizan para el control en pantalla de la edicion del importe a cobrar ingresado por el usuario
		//
		BigDecimal resultadoFinal = null;
		
		if (
			OrigenDebitoEnum.IMPORTACION.equals(debitoDto.getOrigen()) &&
			EstadoDebitoImportadoEnum.PENDIENTE.equals(debitoDto.getResultadoValidacionEstado())
		) {
			return resultadoFinal;
		}
		
		if (SistemaEnum.MIC.equals(debitoDto.getSistemaOrigen()) &&
				(TipoComprobanteEnum.FAC.name().equalsIgnoreCase(debitoDto.getTipoComprobanteEnum().name()) 
						|| TipoComprobanteEnum.DEB.name().equalsIgnoreCase(debitoDto.getTipoComprobanteEnum().name())))
		{
			debitoDto.setSaldoMaxCaso01(debitoDto.getSaldo1erVencMonOrigen());

			debitoDto.setSaldoMaxCaso02(Utilidad.formatCurrency(debitoDto.getImportePriVencTerceros(), false, true));
			debitoDto.setSaldoMaxCaso03(debitoDto.getImp2doVenc());
			debitoDto.setSaldoMaxCaso04(Utilidad.formatCurrency(debitoDto.getImporteSegVencTerceros(), false, true));
			
			if (!debitoDto.isCobrarAl2doVenc()){
				if (!debitoDto.isDestransferir3ros()){
					resultadoFinal = Utilidad.stringToBigDecimal(debitoDto.getSaldo1erVencMonOrigen());
				} else {
					resultadoFinal = debitoDto.getImportePriVencTerceros();
				}
			} else {
				if (debitoDto.isCobrarAl2doVenc()) {
					if (!debitoDto.isDestransferir3ros()) {
						resultadoFinal = Utilidad.stringToBigDecimal(debitoDto.getImp2doVenc());
					} else {
						resultadoFinal = debitoDto.getImporteSegVencTerceros();
					}
				}
			}
		} else { 
			if (SistemaEnum.MIC.equals(debitoDto.getSistemaOrigen()) && 
				(TipoComprobanteEnum.DUC.name().equalsIgnoreCase(debitoDto.getTipoComprobanteEnum().name())))
			{
				resultadoFinal = Utilidad.stringToBigDecimal(debitoDto.getSaldo1erVencMonOrigen());
			} else { 
				if (SistemaEnum.CALIPSO.equals(debitoDto.getSistemaOrigen()) && 
						(TipoComprobanteEnum.FAC.name().equalsIgnoreCase(debitoDto.getTipoComprobanteEnum().name()) 
						|| TipoComprobanteEnum.DEB.name().equalsIgnoreCase(debitoDto.getTipoComprobanteEnum().name())))
				{
					if (debitoDto.getMonedaImporteCobrarEnum().getSigno2().equalsIgnoreCase(debitoDto.getMoneda())) {
						resultadoFinal = Utilidad.stringToBigDecimal(debitoDto.getSaldo1erVencMonOrigen());
						debitoDto.setSaldoMaxCaso05(debitoDto.getSaldo1erVencMonOrigen());
					} else {
						if(ConstantesCobro.ID_MOTIVO_COBRO_COMPENSACION.equals(motivo)){
							debitoDto.setSaldoMaxCaso06(debitoDto.getSaldoPesificadoFechaCotizacion());
							resultadoFinal = Utilidad.stringToBigDecimal(debitoDto.getSaldoPesificadoFechaCotizacion());
						}
						
						debitoDto.setSaldoMaxCaso05(debitoDto.getSaldoPesificado());
						
						if (debitoDto.isSinDifDeCambio()) {
							resultadoFinal = Utilidad.stringToBigDecimal(debitoDto.getSaldoPesificado());
						}
					}
				}
			}
		}
		
		debitoDto.setSaldoMaxCaso01(Validaciones.isNullEmptyOrDash(debitoDto.getSaldoMaxCaso01()) ? "-" : debitoDto.getSaldoMaxCaso01());
		debitoDto.setSaldoMaxCaso02(Validaciones.isNullEmptyOrDash(debitoDto.getSaldoMaxCaso02()) ? "-" : debitoDto.getSaldoMaxCaso02());
		debitoDto.setSaldoMaxCaso03(Validaciones.isNullEmptyOrDash(debitoDto.getSaldoMaxCaso03()) ? "-" : debitoDto.getSaldoMaxCaso03());
		debitoDto.setSaldoMaxCaso04(Validaciones.isNullEmptyOrDash(debitoDto.getSaldoMaxCaso04()) ? "-" : debitoDto.getSaldoMaxCaso04());
		debitoDto.setSaldoMaxCaso05(Validaciones.isNullEmptyOrDash(debitoDto.getSaldoMaxCaso05()) ? "-" : debitoDto.getSaldoMaxCaso05());
		debitoDto.setSaldoMaxCaso06(Validaciones.isNullEmptyOrDash(debitoDto.getSaldoMaxCaso06()) ? "-" : debitoDto.getSaldoMaxCaso06());

		return resultadoFinal;
	}
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param debitoDto
	 * @return
	 */
	public BigDecimal obtenerImporteAUtilizarDebito(CobroDebitoDto debitoDto, String motivo){

		String resultadoFinal = null;

		if (
			OrigenDebitoEnum.IMPORTACION.equals(debitoDto.getOrigen())&&
			EstadoDebitoImportadoEnum.PENDIENTE.equals(debitoDto.getResultadoValidacionEstado())
		) {
			return null;
		}
		if (SistemaEnum.CALIPSO.equals(debitoDto.getSistemaOrigen()) && 
				(TipoComprobanteEnum.FAC.descripcion().equalsIgnoreCase(debitoDto.getTipoDoc()) 
						|| TipoComprobanteEnum.DEB.descripcion().equalsIgnoreCase(debitoDto.getTipoDoc()))) {

			if (debitoDto.getMonedaImporteCobrarEnum().getSigno2().equalsIgnoreCase(debitoDto.getMoneda())) {
				resultadoFinal = debitoDto.getSaldo1erVencMonOrigen();
			} else if(ConstantesCobro.ID_MOTIVO_COBRO_COMPENSACION.equals(motivo)){
//					resultadoFinal = debitoDto.getImporte1erVencimientoPesificadoFechaCotizacion();
					resultadoFinal = debitoDto.getSaldoPesificadoFechaCotizacion();
				} else{
					resultadoFinal = debitoDto.getSaldoPesificado();
				}			
		} else if(SistemaEnum.MIC.equals(debitoDto.getSistemaOrigen())) { 
			if (TipoComprobanteEnum.FAC.descripcion().equalsIgnoreCase(debitoDto.getTipoDoc()) 
					|| TipoComprobanteEnum.DEB.descripcion().equalsIgnoreCase(debitoDto.getTipoDoc()) 
					|| TipoComprobanteEnum.DUC.descripcion().equalsIgnoreCase(debitoDto.getTipoDoc())) {
				resultadoFinal = debitoDto.getSaldo1erVencMonOrigen();
			}
		}
		
		//
		// @author Dante.Romero u575899
		// Consideraciones:
		// Se modifica el valor de saldoMax
		// El mismo se utiliza para el control en pantalla de la edicion del importe a cobrar ingresado por el usuario
		//
		BigDecimal saldoMax = this.obtenerSaldoMaximoDebito(debitoDto, motivo);
		String saldoMaxDefault = (resultadoFinal != null) ? resultadoFinal : "0,0000";
		debitoDto.setSaldoMaxDefault(saldoMaxDefault);
		// Este saldo seria el saldo por de defalut sin la accion de los check
		debitoDto.setSaldoMaxDefaultCalculado(saldoMaxDefault);
		if (Validaciones.isNullEmptyOrDash(debitoDto.getImpACobrar())) {
			debitoDto.setImpACobrar(saldoMaxDefault);
		} else {
			debitoDto.setSaldoMaxDefault(Utilidad.formatCurrency(saldoMax, false, false));
		}
		BigDecimal resultadoFinalBd = Utilidad.stringToBigDecimal(saldoMaxDefault);
		return resultadoFinalBd;
	}

	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param listaDebitoDto
	 * @return
	 * @Muehara No veo que se use en algun lado este metodo. 
	 * Por tema de importacion tube que modificar "obtenerImporteAUtilizarDebito(dto)" para que el caso de 
	 * debito importado no se calcula el importeAUtilizar. por lo que retornaria null
	 */
	public List<BigDecimal> obtenerImporteAUtilizarDebito(List<CobroDebitoDto> listaDebitoDto, String motivo){
		List<BigDecimal> resultadoFinalLista = new ArrayList<BigDecimal>();
		
		for (CobroDebitoDto cobroDebitoDto : listaDebitoDto) {
			resultadoFinalLista.add(obtenerImporteAUtilizarDebito(cobroDebitoDto, motivo));
		}
		return resultadoFinalLista;
	}

	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param creditoDto
	 * @return
	 */
	public BigDecimal obtenerImporteAUtilizarCredito(CobroCreditoDto creditoDto){

		String resultadoFinal = null;
		 
		// Dolares - u578936 M Uehara
		//Correccion - u573005 F Giaquinta
		if (SistemaEnum.CALIPSO.equals(creditoDto.getSistemaOrigen()) &&
			(TipoComprobanteEnum.CTA.descripcion().equalsIgnoreCase(creditoDto.getTipoCredito()) 
			|| TipoComprobanteEnum.CRE.descripcion().equalsIgnoreCase(creditoDto.getTipoCredito()))) {
		
			if (!creditoDto.getMonedaImporteUtilizarEnum().getSigno2().equalsIgnoreCase(creditoDto.getMoneda())){
				resultadoFinal = creditoDto.getSaldoPesificado();
			} else {
				resultadoFinal = creditoDto.getSaldoMonOrigen();
			}		
			
		} else if (SistemaEnum.MIC.equals(creditoDto.getSistemaOrigen()) 
				&& (TipoComprobanteEnum.CRE.descripcion().equalsIgnoreCase(creditoDto.getTipoCredito()) 
						|| TipoComprobanteEnum.REM.descripcion().equalsIgnoreCase(creditoDto.getTipoCredito()))) {
			resultadoFinal = creditoDto.getSaldoMonOrigen();

		} else if (SistemaEnum.SHIVA.equals(creditoDto.getSistemaOrigen())) {
			resultadoFinal = creditoDto.getSaldoMonOrigen();
		}
		// Mantengo compatibildiad con  obtenerImporteAUtilizarDebito

		String importeMaxDefault = (resultadoFinal != null) ? resultadoFinal.toString() : "0,00";
		creditoDto.setImporteMaxDefault(importeMaxDefault);
		if (Validaciones.isNullEmptyOrDash(creditoDto.getImporteAUtilizar())) {
			creditoDto.setImporteAUtilizar(importeMaxDefault);
		}
		BigDecimal resultadoFinalBd = Utilidad.stringToBigDecimal(importeMaxDefault);
		return resultadoFinalBd;
	}
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param listaCreditoDto
	 * @return
	 */
	public List<BigDecimal> obtenerImporteAUtilizarCredito(List<CobroCreditoDto> listaCreditoDto){
		List<BigDecimal> resultadoFinalLista = new ArrayList<BigDecimal>();

		for (CobroCreditoDto cobroCreditoDto : listaCreditoDto) {
			resultadoFinalLista.add(obtenerImporteAUtilizarCredito(cobroCreditoDto));
		}
		return resultadoFinalLista;
	}


	/**
	 * 
	 * @return
	 */
	@Override
	public List<ShvCobEdDebito> listarDebitosEnEdicionPendienteValidacion() {

		List<ShvCobEdDebito> listaDebitos = null;
		try {
			listaDebitos = cobroDebitoDao.listarDebitosPendienteValidacion();
		} catch (PersistenciaExcepcion e) {
		}
		
		return listaDebitos;
	}
	
	/**
	 * Valida los débitos importados con los cobradores Mic/Calipso
	 */
	@Override
	public boolean procesarDebitosAValidar() throws NegocioExcepcion {

		listaDebitosOriginal = listarDebitosEnEdicionPendienteValidacion();
		if (Validaciones.isCollectionNotEmpty(listaDebitosOriginal)) {
			
			//
			// Carga en las listas de Mic y Calipso un máximo de débitos permitidos en cada una (LIMITE_CALIPSO,LIMITE_MIC).
			//
			
			for (ShvCobEdDebito deb : listaDebitosOriginal) {
				if (SistemaEnum.CALIPSO.equals(deb.getSistemaOrigen()) && listaDebitosCalipso.size() < LIMITE_CALIPSO) {
					listaDebitosCalipso.add(deb);
				} else {
					if (SistemaEnum.MIC.equals(deb.getSistemaOrigen()) && listaDebitosMic.size() < LIMITE_MIC) {
						listaDebitosMic.add(deb);
					}
				}
			}
			
			//
			// Va tomando los débitos de cada lista, y crea el objeto Thread que generará la consulta con los cobradores.
			//
			
			if (listaDebitosCalipso.size() <= LIMITE_CALIPSO && Validaciones.isCollectionNotEmpty(listaDebitosCalipso)) {
				for (int i = 0; i < listaDebitosCalipso.size(); i++){
					ConsultaDebitosACobradoresThread consultarCobradores = null; 
					cargarDebitosEjecutarThreads(listaDebitosCalipso.get(i),consultarCobradores);
				}
			}
			
			if (listaDebitosMic.size() <= LIMITE_MIC && Validaciones.isCollectionNotEmpty(listaDebitosMic)) {
				for (int i = 0; i < listaDebitosMic.size(); i++) {
					ConsultaDebitosACobradoresThread consultarCobradores=null;
					cargarDebitosEjecutarThreads(listaDebitosMic.get(i),consultarCobradores);
				}
			}
			
			/**
			 * Queda en un loop, preguntando por el estado de todos los débitos, hasta que todos se encuentren en estado "Finalizado".
			 * 
			 */
			
			int finalizados=0;
			int enProceso=0;
			while (finalizados < listaDebitosOriginal.size()){
				
				for (int i = 0; i < listaDebitosOriginal.size(); i++){
					if (EstadoDebitoImportadoEnum.VALIDACION_OK.equals(listaDebitosOriginal.get(i).getResultadoValidacionEstado())
							|| EstadoDebitoImportadoEnum.VALIDACION_ERROR.equals(listaDebitosOriginal.get(i).getResultadoValidacionEstado())){
						
						finalizados++;
					}else if (EstadoDebitoImportadoEnum.EN_PROCESO.equals(listaDebitosOriginal.get(i).getResultadoValidacionEstado())){
						try {
							enProceso++;
							Thread.sleep(500);
						} catch (InterruptedException e) {
							throw new NegocioExcepcion(e.getMessage(),e);
						}
					}
					
				}
				if (finalizados >=listaDebitosOriginal.size()){
					Traza.auditoria(CobroOnlineServicioImpl.class, "FINALIZADOS [ " + finalizados + " de " + listaDebitosOriginal.size()  + " ]");
					break;
				}else{
					Traza.auditoria(CobroOnlineServicioImpl.class, "FINALIZADOS [ " + finalizados + " de " + listaDebitosOriginal.size()  + " ]" + " [ EN PROCESO:  "+ enProceso + " de " + listaDebitosOriginal.size() + " ]");
					finalizados=0;
					enProceso=0;
				}
			}
			
			/**
			 * Controla que todos los hilos hayan finalizado.
			 */
			
			int hilosFinalizados=0;
			while (hilosFinalizados < listaThreads.size()){
				for (int i = 0; i < listaThreads.size(); i++){
					if(!listaThreads.get(i).isAlive()){
						hilosFinalizados++;
					}
				}
				if (hilosFinalizados < listaDebitosOriginal.size()){
					hilosFinalizados=0;
				}
			}
			
			Traza.auditoria(CobroOnlineServicioImpl.class, "HILOS FINALIZADOS[" + hilosFinalizados +"]");
			Traza.auditoria(CobroOnlineServicioImpl.class, "No hay más débitos para consultar...");
			/**
			 * Una vez que todos los débitos fueron validados, recorre la lista Original de débitos; para cambiar el estado 
			 * de los respectivos cobros
			 * y actualizar los estados de cada débito validado.
			 */
			Long idCobro = listaDebitosOriginal.get(0).getPk().getCobro().getIdCobro();
			ShvCobEdDebito debitoActualizar=null;
			BigDecimal totalImporteDebito = new BigDecimal(0);
			
			for (ShvCobEdDebito debito: listaDebitosOriginal){
				if (idCobro != debito.getPk().getCobro().getIdCobro()){
					cambiarEstadoCobro(debitoActualizar.getPk().getCobro());
					crearTareaPendiente(debitoActualizar.getPk().getCobro(), false, totalImporteDebito);
					idCobro = debito.getPk().getCobro().getIdCobro();
					totalImporteDebito = new BigDecimal (0);
				}
				try {
					cambiarEstadoDebitosValidados(debito);
					totalImporteDebito= totalImporteDebito.add(debito.getImporteACobrar());
					debitoActualizar = debito;
				} catch (PersistenciaExcepcion e) {
					throw new NegocioExcepcion(e.getMessage(),e);
				}
			}
			cambiarEstadoCobro(debitoActualizar.getPk().getCobro());
			crearTareaPendiente(debitoActualizar.getPk().getCobro(), false, totalImporteDebito);
		} else {
			Traza.auditoria(CobroOnlineServicioImpl.class, "No hay débitos para validar");
			return false;
		}

		return true;
	}	
	
	/**
	 * Metodo que imputa con aprobacion generando tarea y enviando mail.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobroDto
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void validarAprobacionCobroEnviarMail(CobroDto cobroDto, UsuarioSesion userSesion) throws NegocioExcepcion {
		CobroDto cobroDtoFinal = buscarCobro(cobroDto.getIdCobro());
		crearTareaPendienteAprobacionCobro(cobroDtoFinal, userSesion);
		cobroDto.setEstado(cobroDtoFinal.getEstado());
	}
	
		/**
	 * Metodo que imputa sin requerir aprobacion.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobroDto
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void imputarSinAprobacion(CobroDto cobroDto, UsuarioSesion userSesion) throws NegocioExcepcion {
	
		ShvCobEdCobro cobro = imputarSinAprobacion(cobroDto.getIdCobro(), userSesion.getIdUsuario());
		cobroDto.setEstado(cobro.getWorkflow().getEstado());
	}

	/**
	 * Metodo que imputa sin requerir aprobacion.
	 * 
	 * @param cobroDto
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvCobEdCobro imputarSinAprobacion(Long idCobro, String idUsuario) throws NegocioExcepcion {

		try {
			ShvCobEdCobro cobroModelo = cobroOnlineDao.buscarCobro(idCobro);
			
			cobroModelo.setUsuarioDerivacion(idUsuario);
			cobroModelo.setFechaDerivacion(new Date());
			cobroModelo.setUsuarioUltimaModificacion(idUsuario);
			cobroModelo.setFechaUltimaModificacion(new Date());
			
			ShvWfWorkflow workflow = cobroModelo.getWorkflow();
			// En esta transicion las observaciones presentes se persisten en el estado corriente
			workflow = workflowCobros.solicitarImputacionCobro(workflow, cobroModelo.getObservacion(), idUsuario);
			cobroModelo.setWorkflow(workflow);
			cobroModelo.setObservacion(Utilidad.EMPTY_STRING);
			cobroModelo = cobroOnlineDao.guardarCobro(cobroModelo);

			// Seteo la fecha de ultimo procesamiento en el cobro
			ShvCobCobroSimple cob = cobroDao.buscarCobroSimplePorIdOperacion(cobroModelo.getIdOperacion());
			cob.setFechaUltProcesamiento(workflow.getFechaUltimaModificacion());
			cob.setWorkflow(workflow);
			cob = cobroDao.modificar(cob);

			return cobroModelo;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo que valida con que campos se puede interactuar en la edicion segun Estado y Marca.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobroDto
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public String validarEdicionSegunEstadoMarca(CobroDto cobroDto, UsuarioSesion userSesion) throws NegocioExcepcion{
		
		ShvCobEdCobro cobroModelo;
		String resultadoFinal = "";
		Boolean edicionTotal = false;
		Boolean edicionParcial = false;
		Boolean edicionTotalMenosDebitos = false;
		Boolean sinEdicion = false;
		MarcaEnum marca = null;
		List<MarcaEnum> marcasSimulacionOk = new ArrayList<MarcaEnum>();
		marcasSimulacionOk.add(MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO);
		marcasSimulacionOk.add(MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_EXITO);
		try {
			cobroModelo = cobroOnlineDao.buscarCobro(cobroDto.getIdCobro());

			ShvWfWorkflow workflow = cobroModelo.getWorkflow();
			ShvWfWorkflowEstado workflowEstado = workflow.getWorkflowEstado();
			Estado estado = workflowEstado.getEstado();
			// Recupero solo la ultima marca
			List<MarcaEnum> listaMarcas = this.obtenerUltimasMarcas(cobroDto.getIdCobro(), cobroModelo, true);
			if (!listaMarcas.isEmpty()) {
				marca = listaMarcas.get(0);
			}
			
			switch (estado) {
			case COB_EN_EDICION:
				if (Validaciones.isObjectNull(marca)) {
					edicionTotal = true;
				} else if (marcasSimulacionOk.contains(marca)) {
					edicionTotal = true;
				} else if (MarcaEnum.SIMULACION_BATCH_EN_PROCESO.equals(marca)) {
					edicionParcial = true;
				}
				break;
			case COB_EN_EDICION_VERIFCANDO_DEBITOS:
					edicionTotalMenosDebitos = true;
				break;
			case COB_PENDIENTE:
				edicionParcial = true;
				break;
			case COB_PROCESO:
				edicionParcial = true;
			case COB_PEND_REFERENTE_COBRANZA:
				sinEdicion = true;
				break;
			case COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES:
				sinEdicion = true;
				break;
			case COB_RECHAZADO:
				if (MarcaEnum.RECHAZADO_POR_REFERENTE.equals(marca) || MarcaEnum.RECHAZADO_POR_SUPERVISOR_OP_ESPECIALES.equals(marca)) {
					edicionTotal = true;
				}else if (MarcaEnum.RECHAZADO_POR_SUPERVISOR_OP_ESPECIALES.equals(marca)) {
					edicionTotal = true;
				} else if (marcasSimulacionOk.contains(marca)) {
					edicionTotal = true;
				} else if (MarcaEnum.SIMULACION_BATCH_EN_PROCESO.equals(marca)) {
					edicionParcial = true;
				}
				break;
			case COB_COBRADO:
				edicionParcial = true;
				break;
			case COB_CONFIRMADO_CON_ERROR:
				edicionParcial = true;
				break;
			case COB_ERROR_APROPIACION:
				edicionParcial = true;
				break;
			case COB_ERROR_COBRO:
				edicionParcial = true;
				break;
			case COB_ERROR_CONFIRMACION:
				edicionParcial = true;
				break;
			case COB_ERROR_DESAPROPIACION:
				edicionParcial = true;
				break;
			case COB_REEDITADO:
				edicionParcial = true;
				break;
			case COB_REEDITADO_PARCIAL:
				edicionParcial = true;
				break;
			case COB_ANULADO:
				sinEdicion = true;
				break;
				//U562163
			case COB_PENDIENTE_CONFIRMACION_MANUAL:
				edicionParcial = true;
				break;
				//Mar
			case COB_COBRADO_PARCIALMENTE:
				edicionParcial = true;
				break;
			case COB_ERROR_DESAPROPIACION_PARCIAL:
				edicionParcial = true;
				break;
			case COB_ERROR_CONFIRMACION_PARCIAL:
				edicionParcial = true;
				break;
			case COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC:
				edicionParcial = true;
				break;
			case COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_PROCESAR_MIC:
				edicionParcial = true;
				break;
			case COB_PROCESO_APLICACION_EXTERNA:
				edicionParcial = true;
				break;
			case COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA:
				edicionParcial = true;
				break;
			case COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC:
				edicionParcial = true;
				break;
			case COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC:
				edicionParcial = true;
				break;
			case COB_CONFIRMADO_PARCIALMENTE_CON_ERROR:
				edicionParcial = true;
				break;
			default:
				break;
			}
			if (edicionTotal) {
				if(Constantes.TRUE.equalsIgnoreCase(cobroDto.getVengoReedicion())){
					resultadoFinal = "reedicion";
				} else {
					resultadoFinal = "";
				}
				
			} else if(edicionParcial) {
				resultadoFinal = "edicionParcial";
			} else if(edicionTotalMenosDebitos){
				resultadoFinal = "edicionTotalMenosDebitos";
			} else if(sinEdicion){
				resultadoFinal = "sinEdicion";
			}

			if (!sinEdicion) {
				if (userSesion.getPerfiles().contains(TipoPerfilEnum.SUPERVISOR_COBRANZA.nombreLdap())) {
					resultadoFinal = "edicionParcial";
				}
			}
		}catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return resultadoFinal;
	}
	
	/**
	 * Metodo que anula tarea rechazada desde la bandeja de entrada del Analista Cobranza.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param idCobro
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void anularTarea(Long idCobro, UsuarioSesion userSesion) throws NegocioExcepcion{
		String datosModificados = "";
		ShvCobEdCobro cobroModelo;
		try {
			String idUsuario = userSesion.getIdUsuario();
			
			cobroModelo = cobroOnlineDao.buscarCobro(idCobro);
			
			cobroModelo.setUsuarioUltimaModificacion(idUsuario);
			cobroModelo.setFechaUltimaModificacion(new Date());
			
			ShvWfWorkflow workflow = cobroModelo.getWorkflow();
			
			if (!Estado.COB_REEDITADO.equals(workflow.getEstado())){
				workflow = workflowCobros.anularCobroRechazado(workflow, datosModificados, idUsuario);
				cobroModelo.setWorkflow(workflow);
				
				cobroModelo = cobroOnlineDao.guardarCobro(cobroModelo);
			}
			
			tareaServicio.finalizarTarea(TipoTareaEnum.COB_REV_RECH, workflow.getIdWorkflow(), cobroModelo.getFechaUltimaModificacion(), cobroModelo.getIdAnalista(), null);
			
		}catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo que elimina la tarea de error desapropiacion.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param idCobro
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void anularTareaErrorDesapropiacion(Long idCobro, UsuarioSesion userSesion) throws NegocioExcepcion{
		ShvCobEdCobro cobroModelo;
		try {
			String idUsuario = userSesion.getIdUsuario();
			
			cobroModelo = cobroOnlineDao.buscarCobro(idCobro);
			
			cobroModelo.setUsuarioUltimaModificacion(idUsuario);
			cobroModelo.setFechaUltimaModificacion(new Date());
			
			ShvWfWorkflow workflow = cobroModelo.getWorkflow();
			
			tareaServicio.finalizarTarea(TipoTareaEnum.COB_REV_COB_DES, workflow.getIdWorkflow(), cobroModelo.getFechaUltimaModificacion(), cobroModelo.getIdAnalista(), null);
			
		}catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo que elimina la tarea de error confirmacion.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param idCobro
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void anularTareaErrorConfirmacion(Long idCobro, UsuarioSesion userSesion) throws NegocioExcepcion{
		ShvCobEdCobro cobroModelo;
		try {
			String idUsuario = userSesion.getIdUsuario();
			
			cobroModelo = cobroOnlineDao.buscarCobro(idCobro);
			
			cobroModelo.setUsuarioUltimaModificacion(idUsuario);
			cobroModelo.setFechaUltimaModificacion(new Date());
			
			ShvWfWorkflow workflow = cobroModelo.getWorkflow();
			
			tareaServicio.finalizarTarea(TipoTareaEnum.COB_REV_COB_CON, workflow.getIdWorkflow(), cobroModelo.getFechaUltimaModificacion(), cobroModelo.getIdAnalista(), null);
			
		}catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param debito
	 * @throws PersistenciaExcepcion
	 */
	private void cambiarEstadoDebitosValidados(ShvCobEdDebito debito) throws PersistenciaExcepcion {
		try {
			cobroDebitoDao.guardarDebito(debito);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * 
	 * @param cobro
	 */
	private void cambiarEstadoCobro (ShvCobEdCobro cobro) throws NegocioExcepcion{
		try {
			//obtener workflow
			ShvWfWorkflow workflow = cobro.getWorkflow();// BORRAR cobroOnlineServicio.getWorkflowByIdCobro(idCobro);
	
			String usuarioBatch;
				usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			
			//avanzar workflow
			ShvWfWorkflow workflowActualizado = null;
			List<ShvWfWorkflowEstadoHist> shvWfWorkflowEstadoHist = workflow.getListaWorkflowEstadoHistOrdenadaPorFecha();
			ShvWfWorkflowEstadoHist estadoAnterior = shvWfWorkflowEstadoHist.get(shvWfWorkflowEstadoHist.size() - 1);
			if (Estado.COB_EN_EDICION.equals(estadoAnterior.getEstado())) {
				workflowActualizado = workflowCobros.solicitarEdicion(workflow, "", usuarioBatch);
			} else {
				workflowActualizado = workflowCobros.solicitarEdicionCobroRechazado(workflow, "", usuarioBatch);
			}
			//guardar workflow
			workflowDao.actualizarWorkflow(workflowActualizado);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (WorkflowExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param cobro
	 * @param enviarMail
	 * @param importe
	 * @throws NegocioExcepcion
	 */
	private void crearTareaPendiente(ShvCobEdCobro cobro, Boolean enviarMail, BigDecimal importe) throws NegocioExcepcion {
		TareaDto tarea = new TareaDto();
		
		// Busco el cliente
		ShvCobEdCliente clienteFinal = null;
		for (ShvCobEdCliente cliente : cobro.getClientes()) {
			clienteFinal = cliente;
		}
		if (!Validaciones.isObjectNull(clienteFinal)) {
			tarea.setNroCliente(clienteFinal.getIdClienteLegado().toString()); //ver de pasar nombre completo si corresponde
			tarea.setRazonSocial(clienteFinal.getRazonSocial());
		}
		// Fin Cliente
		
		String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
		
		String asuntoMail= "Nueva Tarea - Revisar resultado validación débitos importados - ID Operación Cobro: ";
		asuntoMail += cobro.getIdOperacion();
		if (!Validaciones.isObjectNull(clienteFinal)) {
			asuntoMail += " - " + clienteFinal.getIdClienteLegado().toString() + " / " + clienteFinal.getRazonSocial();
		}
		
		String cuerpoMail = "Ha finalizado el proceso de validación de los débitos importados para la Operación de Cobro ";
		cuerpoMail += cobro.getIdOperacion();
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setIdWorkflow(cobro.getWorkflow().getIdWorkflow());
		tarea.setTipoTarea(TipoTareaEnum.COB_REV_DEB_IMP);
		tarea.setFechaCreacion(cobro.getWorkflow().getFechaUltimaModificacion());
		tarea.setUsuarioCreacion(usuarioBatch);
		tarea.setUsuarioAsignacion(cobro.getIdAnalista());
		
		tarea.setPerfilAsignacion(TipoPerfilEnum.ANALISTA_COBRANZA.descripcion());
		tarea.setGestionaSobre(TipoTareaGestionaEnum.COBRO);
		//
		tarea.setMonedaImporte(cobro.getMonedaOperacion().getSigno2());
		tarea.setImporte(Utilidad.formatCurrency(importe, false, true));
		
		
		tarea.setAsuntoMail(asuntoMail);
		tarea.setCuerpoMail(cuerpoMail);
		tarea.setIdCobro(Long.valueOf(cobro.getIdCobro()));
		tarea.setIdOperacion(Long.valueOf(cobro.getIdOperacion()));
		tarea.setEnviarMail(enviarMail);
		//Moneda Operacion
		
		
		tareaServicio.crearTareaResultadoValidacionDebitos(tarea);
		
		//ENVIA EL MAIL
		String para ="";
		String cc ="";
		UsuarioLdapDto usuarioLdapAnalista;
		if(!Validaciones.isNullOrEmpty(cobro.getIdAnalista())){
			usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(cobro.getIdAnalista());
			if(!Validaciones.isNullOrEmpty(usuarioLdapAnalista.getMail())){
				para = usuarioLdapAnalista.getMail() + ";";
			}
		}
		
		UsuarioLdapDto usuarioLdapCopropietario;
		if(!Validaciones.isNullOrEmpty(cobro.getIdCopropietario())){
			usuarioLdapCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(cobro.getIdCopropietario());
			if(!Validaciones.isNullOrEmpty(usuarioLdapCopropietario.getMail())){
				cc = usuarioLdapCopropietario.getMail();
			}
		}
		Mail mail = new Mail(para,cc,asuntoMail,new StringBuffer(cuerpoMail));
		mailServicio.sendMail(mail);
	}
	
	// comentamos esta linea porque está trayendo problemas en prod para aprobar las compensaciones
	//@Transactional (readOnly = false, rollbackFor = { Exception.class, NegocioExcepcion.class }, propagation = Propagation.REQUIRED)
	public void aprobarCobroCambiarEstadoWorkFlow(CobroDto cobroDto,  UsuarioSesion usuarioModificacion) throws NegocioExcepcion {
		aprobarCobroCambiarEstadoWorkFlow(cobroDto, usuarioModificacion, null, null,null);
	}
	
	/**
	 * Metodo que cambia el estado del workflow, envia el mail y genera la tarea
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobroDto
	 * @param usuarioModificacion
	 */
	
	
	private void aprobarCobroCambiarEstadoWorkFlow(CobroDto cobroDto,  UsuarioSesion usuarioModificacion, SociedadEnum sociedad, SistemaEnum sistema, Set<CodigoOperacionExternaDto> listadoCodigoOperacionesExternas) throws NegocioExcepcion {
		try {
			String idUsuario = usuarioModificacion.getIdUsuario();
			Set<ClienteDto> clientes = cobroDto.getClientes();
			ShvCobEdCobro cobroModelo = cobroOnlineDao.buscarCobro(cobroDto.getIdCobro());
			boolean esPendienteDeConfManual = false;
			ShvWfWorkflow workflow = cobroModelo.getWorkflow();
			
//			String asunto = "Cobro autorizado por Referente - ";
			String asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.autorizacion.cobros.asunto.autorizado.por.referente"); 
			
			if(hayCompensacionesSap(cobroModelo) && Estado.COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES.equals(workflow.getEstado())){
				workflow = workflowCobros.aprobarCobroSegunSupervisorOperacionesEspecialesSolicitandoImputacion(workflow, cobroDto.getObservacion(), idUsuario);
//				asunto = "Cobro autorizado por Supervisor Operaciones Especiales - ";
				asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.autorizacion.cobros.asunto.autorizado.por.supervisor.op.especiales");
				cobroDto.setUsuarioAprobacionSupervisorOperacionesEspeciales(usuarioModificacion.getIdUsuario());
				cobroDto.setFechaAprobacionOperacionesEspeciales(new Date());
				cobroDto.setNombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales(ldapServicio.buscarUsuarioPorUidEnMemoria(usuarioModificacion.getIdUsuario()).getNombreCompleto());
				//ver si en cobroDto traigo los tratamientos de diferencia, sino usar CobroModelo
				this.modificar(cobroDto);	
			} else if ((Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(workflow.getEstado()))&&(esperaAprobarPorReferenteCaja(cobroDto))){
//				asunto = "Cobro con aplicación externa confirmado por Referente Caja - ";
				asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.autorizacion.cobros.asunto.autorizado.por.referente.caja");
//				cobroModelo.setIdReferenteCaja(usuarioModificacion.getIdUsuario());
//				cobroModelo.setNombreApellidoReferenteCaja(ldapServicio.buscarUsuarioPorUidEnMemoria(usuarioModificacion.getIdUsuario()).getNombreCompleto());
//				cobroModelo.setFechaReferenteCaja(new Date());
				workflow = workflowCobros.registrarCobroPendienteDeConfirmacionManualAEnProceso(workflow, cobroDto.getObservacion(), idUsuario);
				//U562163 - Fusion Cobros
				workflow = workflowService.agregarWorkflowMarcaConObservaciones(workflow, idUsuario, MarcaEnum.CONFIRMADO_POR_REFERENTE_CAJA,"("+sociedad.getDescripcion()+"/"+sistema.getDescripcion()+")");

				Set<CodigoOperacionExternaDto> codigosOperacionesExternas = listadoCodigoOperacionesExternas;
				if(Validaciones.isCollectionNotEmpty(codigosOperacionesExternas)){
					cobroModelo = crearCodigoOperacionExterna(cobroModelo, codigosOperacionesExternas,usuarioModificacion);
//					usuarioModificacion.setCodigosOperacionesExternasAGuardar(new ArrayList<CodigoOperacionExternaDto>());
				}else{
					cobroOnlineDao.guardarCobro(cobroModelo);
				}
				
			} else if ((Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(workflow.getEstado()))&&(esperaAprobarPorReferenteOperacionesExternas(cobroDto))){
//				asunto = "Cobro con aplicación externa confirmado por Referente Operaciones Externas - ";
				asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.autorizacion.cobros.asunto.autorizado.por.referente.op.externas");
//				cobroModelo.setIdReferenteoperacionexterna(usuarioModificacion.getIdUsuario());
//				cobroModelo.setNombreApellidoReferenteOperacionExterna(ldapServicio.buscarUsuarioPorUidEnMemoria(usuarioModificacion.getIdUsuario()).getNombreCompleto());
//				cobroModelo.setFechaReferenteOperacionExterna(new Date());

				workflow = workflowCobros.registrarCobroPendienteDeConfirmacionManualAEnProceso(workflow, cobroDto.getObservacion(), idUsuario);
				//U562163 - Fusion Cobros
				workflow = workflowService.agregarWorkflowMarcaConObservaciones(workflow, idUsuario, MarcaEnum.CONFIRMADO_POR_REFERENTE_OPERACIONES_EXTERNAS,"("+sociedad.getDescripcion()+"/"+sistema.getDescripcion()+")");
				
				Set<CodigoOperacionExternaDto> codigosOperacionesExternas = listadoCodigoOperacionesExternas;
				if(Validaciones.isCollectionNotEmpty(codigosOperacionesExternas)){
					cobroModelo = crearCodigoOperacionExterna(cobroModelo, codigosOperacionesExternas,usuarioModificacion);
//					usuarioModificacion.setCodigosOperacionesExternasAGuardar(new ArrayList<CodigoOperacionExternaDto>());
				}else{
					cobroOnlineDao.guardarCobro(cobroModelo);
				}
				
			} else if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(workflow.getEstado()) || Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())
						|| Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC.equals(workflow.getEstado()) || Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(workflow.getEstado())
						|| Estado.COB_ERROR_DESAPROPIACION.equals(workflow.getEstado())
					){
				
				esPendienteDeConfManual = true;
				if(usuarioModificacion.esReferenteCaja()){
					asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.autorizacion.cobros.asunto.autorizado.por.referente.caja");
//					cobroModelo.setIdReferenteCaja(usuarioModificacion.getIdUsuario());
//					cobroModelo.setNombreApellidoReferenteCaja(ldapServicio.buscarUsuarioPorUidEnMemoria(usuarioModificacion.getIdUsuario()).getNombreCompleto());
//					cobroModelo.setFechaReferenteCaja(new Date());
					workflow = actualizarEstadoWorkflow("",workflow, idUsuario,sociedad, sistema,false);
					workflow = workflowService.agregarWorkflowMarcaConObservaciones(workflow, idUsuario, MarcaEnum.CONFIRMADO_POR_REFERENTE_CAJA,"("+sociedad.getDescripcion()+"/"+sistema.getDescripcion()+")");
					
				} else if (usuarioModificacion.esReferenteOperacionesExternas()){
					asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.autorizacion.cobros.asunto.autorizado.por.referente.op.externas");
//					cobroModelo.setIdReferenteoperacionexterna(usuarioModificacion.getIdUsuario());
//					cobroModelo.setNombreApellidoReferenteOperacionExterna(ldapServicio.buscarUsuarioPorUidEnMemoria(usuarioModificacion.getIdUsuario()).getNombreCompleto());
//					cobroModelo.setFechaReferenteOperacionExterna(new Date());
					workflow = actualizarEstadoWorkflow("",workflow, idUsuario,sociedad, sistema,false);
					workflow = workflowService.agregarWorkflowMarcaConObservaciones(workflow, idUsuario, MarcaEnum.CONFIRMADO_POR_REFERENTE_OPERACIONES_EXTERNAS,"("+sociedad.getDescripcion()+"/"+sistema.getDescripcion()+")");
				}
				asunto += Constantes.OPEN_PARENTESIS + sociedad.getDescripcion() + Constantes.BARRA + sistema.getDescripcion() + Constantes.CLOSE_PARENTESIS + Constantes.DOS_PUNTOS + " ";
				actualizarEstadoTransacciones(cobroModelo.getIdOperacion(),sistema,sociedad,hayCompensacionesSap(cobroModelo));
				Set<CodigoOperacionExternaDto> codigosOperacionesExternas = listadoCodigoOperacionesExternas;
				if(Validaciones.isCollectionNotEmpty(codigosOperacionesExternas)){
					cobroModelo = crearCodigoOperacionExterna(cobroModelo, codigosOperacionesExternas,usuarioModificacion);
//					usuarioModificacion.setCodigosOperacionesExternasAGuardar(new ArrayList<CodigoOperacionExternaDto>());
				}else{
					cobroOnlineDao.guardarCobro(cobroModelo);
				}
				
			} else {
				cobroDto.setUsuarioAprobacionReferenteCobranza(usuarioModificacion.getIdUsuario());
				cobroDto.setNombreApellidoUsuarioAprobacionReferenteCobranza(ldapServicio.buscarUsuarioPorUidEnMemoria(usuarioModificacion.getIdUsuario()).getNombreCompleto());
				cobroDto.setFechaAprobacionReferenteCobranza(new Date());
				workflow = workflowCobros.aprobarCobroSegunReferenteSolicitandoImputacion(workflow, cobroDto.getObservacion(), idUsuario);
			
				this.modificar(cobroDto);	
			}
			
			
			Date date = workflow.getFechaUltimaModificacion();
			//Seteo la fecha de ultimo procesamiento en el cobro
			ShvCobCobroSimple cob = cobroDao.buscarCobroSimplePorIdOperacion(cobroDto.getIdOperacion());
			cob.setFechaUltProcesamiento(date);
			cob.setWorkflow(workflow);
			cob = cobroDao.modificar(cob);
			

			asunto += cobroDto.getIdOperacion().toString();
			asunto += " - ";
			asunto += Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.empresa") + cobroDto.getEmpresa();
			asunto += " / ";
			Iterator<ClienteDto> iterator = clientes.iterator();
			if(iterator.hasNext()){
				ClienteDto cliente = iterator.next();
				asunto += Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuit") + cliente.getCuit();
				asunto += " / ";
				asunto += Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cliente") + cliente.getIdClienteLegado();
				if(cliente.getRazonSocial()!=null){
					asunto += " / ";
					asunto += cliente.getRazonSocial();
				}
			}
			
			StringBuffer cuerpoMail = new StringBuffer();
			cuerpoMail.append(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuerpo.listado.clientes"));
			Iterator<ClienteDto> iteratorClientes = clientes.iterator();
			while (iteratorClientes.hasNext()){
				cuerpoMail.append(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.empresa"));
				cuerpoMail.append(cobroDto.getEmpresa() + " / ");
//				cuerpoMail.append("Id Operación Cobro: ");
//				cuerpoMail.append(cobroDto.getIdOperacion().toString() + " / ");
				ClienteDto cliente = iteratorClientes.next();
				cuerpoMail.append(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuit"));
				cuerpoMail.append(cliente.getCuit() + " / ");
				cuerpoMail.append(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cliente"));
				cuerpoMail.append(cliente.getIdClienteLegado());
				if(cliente.getRazonSocial()!=null){
					cuerpoMail.append(" / ");
					cuerpoMail.append(cliente.getRazonSocial());
				}
				cuerpoMail.append("<br>");
			}
			String observacion = this.obtenerObservacionHistorialYObservacionTarea(cobroDto);
			if(!Validaciones.isNullOrEmpty(observacion)){
				cuerpoMail.append("<br><br>");
				cuerpoMail.append( "Observaciones:");
				cuerpoMail.append("<br>");
				cuerpoMail.append(Utilidad.formateadoMail(
					observacion
				));
			}
			
			Mail mail = new Mail(cobroDto.getMailAnalista(), cobroDto.getMailCopropietario(), null, asunto, cuerpoMail);
			if (hayCompensacionesSap(cobroModelo) && !esPendienteDeConfManual) {
				tareaServicio.finalizarTarea(TipoTareaEnum.COB_AUTR_COB_SUP_OP_ESP, workflow.getIdWorkflow(), date, cobroDto.getIdAnalista(), mail);
			} else if (esperaAprobarPorReferenteCaja(cobroDto)
					|| esperaAprobarPorReferenteOperacionesExternas(cobroDto)) {
				
				TipoTareaEnum tipoTarea = TipoTareaEnum.COB_CONF_APLIC_MANUAL;
				tareaServicio.finalizarTareaCorrecto(tipoTarea,sociedad,sistema, workflow.getIdWorkflow(), date, usuarioModificacion.getIdUsuario(), mail, TipoAccionEnum.CONFIRMADA, cobroDto.getObservacion());
			} else if (esPendienteDeConfManual) {
				tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_CONF_APLIC_MANUAL,sociedad,sistema, workflow.getIdWorkflow(), date, usuarioModificacion.getIdUsuario(), mail, TipoAccionEnum.CONFIRMADA, cobroDto.getObservacion());
			} else {
				tareaServicio.finalizarTarea(TipoTareaEnum.COB_AUTR_COB, workflow.getIdWorkflow(), date, cobroDto.getIdAnalista(), mail);
			}
			
			cobroDto.setObservacion(Utilidad.EMPTY_STRING);
			
				
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Método que valida si corresponde ser aprobado por Referente Caja.
	 * 
	 * @author u587496 Guido.Settecerze
	 * 
	 * @param cobroDto
	 * @return esperaAprobarForReferenteCaja
	 */
	private boolean esperaAprobarPorReferenteCaja (CobroDto cobroDto) {
		
		boolean esperaAprobarForReferenteCaja = false;
		CobroTratamientoDiferenciaDto tratamientoDiferencia = cobroDto.getTratamientoDiferencia();
		if(!Validaciones.isObjectNull(tratamientoDiferencia)) {
			if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name().equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {
				if (SistemaEnum.SAP.getDescripcionCorta().equals(tratamientoDiferencia.getSistemaDestino()) 
						|| SistemaEnum.NEGOCIO_NET.getDescripcionCorta().equals(tratamientoDiferencia.getSistemaDestino())) {
					esperaAprobarForReferenteCaja = true;
				}
			}
		}
		return esperaAprobarForReferenteCaja;
	}
	
	/**
	 * Método que valida si corresponde ser aprobado por Referente de Operaciones Externas.
	 * 
	 * @author u587496 Guido.Settecerze
	 * 
	 * @param cobroDto
	 * @return esperaAprobarporReferenteOperacionesExternas
	 */
	private boolean esperaAprobarPorReferenteOperacionesExternas (CobroDto cobroDto) {
		
		boolean esperaAprobarporReferenteOperacionesExternas = false;
		CobroTratamientoDiferenciaDto tratamientoDiferencia = cobroDto.getTratamientoDiferencia();
		if(!Validaciones.isObjectNull(tratamientoDiferencia)) {
			if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name().equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {
				if (SistemaEnum.FIBERTEL.getDescripcionCorta().equals(tratamientoDiferencia.getSistemaDestino()) 
						|| SistemaEnum.NEXTEL.getDescripcionCorta().equals(tratamientoDiferencia.getSistemaDestino())
						|| SistemaEnum.CABLEVISION.getDescripcionCorta().equals(tratamientoDiferencia.getSistemaDestino())) {
					esperaAprobarporReferenteOperacionesExternas = true;
				}
			}
		}
		return esperaAprobarporReferenteOperacionesExternas;
	}
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param idCobro
	 * @param cobroModelo
	 * @param usuarioModificacion
	 */
	public void eliminarTarea(DTO dto, Long idTarea) throws NegocioExcepcion {

		CobroDto cobroDto = (CobroDto) dto;
		TareaDto tareaDto;
		SociedadEnum sociedad = null;
		SistemaEnum sistema = null;
		String usuarioModificacion = cobroDto.getUsuarioModificacion();
		
		try {
			ShvCobEdCobro cobroModelo = cobroOnlineDao.buscarCobro(cobroDto.getIdCobro());
			
			ShvWfWorkflow workflow = cobroModelo.getWorkflow();
			
			if (!Validaciones.isObjectNull(idTarea)){
				 tareaDto = (TareaDto) tareaServicio.buscarTarea(idTarea);
				 sociedad = tareaDto.getSociedad();
				 sistema = tareaDto.getSistema();
			}
			
			//TODO: Verificar si para todos los cobros en estado COB_REV_COB_ERR,COB_REV_COB_APR, COB_ERR_CONF_APLIC_MANUALno deberia anularse el cobro.
			
			List<ShvWfTarea> tarea = tareaDao.buscarTareasPendientes(workflow.getIdWorkflow());
			for (ShvWfTarea shvWfTarea : tarea) {
				switch (shvWfTarea.getTipoTarea()) {
				
				case COB_REV_DEB_IMP:
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_DEB_IMP, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
					break;
				case COB_RES_SIM_OK: 
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_RES_SIM_OK, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
					break;
				case COB_RES_SIM_ERR:
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_RES_SIM_ERR, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
					break;
				case COB_REV_RECH: 
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_RECH, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
					workflowCobros.anularCobroRechazado(workflow, "", usuarioModificacion);
					break;
				case COB_REV_COB_ERR: 
					if(Validaciones.isObjectNull(sociedad) && Validaciones.isObjectNull(sistema)) {
						tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_COB_ERR, null, null, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null, TipoAccionEnum.ANULADA, null);
					} else {
						tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_COB_ERR, sociedad, sistema, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null, TipoAccionEnum.ANULADA, null);
					}
					// TODO: Agregar la llamada al metodo que avanza el WF según el estado de las tareas
					cobroBatchServicio.cambiarEstadoSegunEstadoDeTareas(cobroDto.getIdCobro(), usuarioModificacion);
					break;
				case COB_REV_COB_APR:
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_COB_APR, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
//					workflowCobros.anularCobroErrorEnApropiacion(workflow, "", usuarioModificacion);
					break;
				case COB_ERR_CONF_APLIC_MANUAL:
					tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), sociedad, sistema, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null, TipoAccionEnum.ANULADA, null);

					cobroBatchServicio.cambiarEstadoSegunEstadoDeTareas(cobroDto.getIdCobro(), usuarioModificacion);
					break;
				case COB_DESAPRO_APLI_MANUAL:
					tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
					break;
				case COB_REV_ERR_APLIC_MANUAL:
					tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), sociedad, sistema, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null, TipoAccionEnum.ANULADA, null);

					// TODO: Agregar la llamada al metodo que avanza el WF según el estado de las tareas
					cobroBatchServicio.cambiarEstadoSegunEstadoDeTareas(cobroDto.getIdCobro(), usuarioModificacion);
					break;
				default:
					break;
				}
			}

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	/**
	 * 
	 * @param idCobroPadre
	 * @param idTarea
	 * @throws NegocioExcepcion
	 */
	public void eliminarTarea(long idCobroPadre, Long idTarea, String usuarioModificacion) throws NegocioExcepcion {
		TareaDto tareaDto;
		SociedadEnum sociedad = null;
		SistemaEnum sistema = null;
		
		try {
			ShvCobEdCobro cobroModelo = cobroOnlineDao.buscarCobro(idCobroPadre);
			
			ShvWfWorkflow workflow = cobroModelo.getWorkflow();
			
			if (!Validaciones.isObjectNull(idTarea)){
				 tareaDto = (TareaDto) tareaServicio.buscarTarea(idTarea);
				 sociedad = tareaDto.getSociedad();
				 sistema = tareaDto.getSistema();
			}
			
			//TODO: Verificar si para todos los cobros en estado COB_REV_COB_ERR,COB_REV_COB_APR, COB_ERR_CONF_APLIC_MANUALno deberia anularse el cobro.
			
			List<ShvWfTarea> tarea = tareaDao.buscarTareasPendientes(workflow.getIdWorkflow());
			for (ShvWfTarea shvWfTarea : tarea) {
				switch (shvWfTarea.getTipoTarea()) {
				
				case COB_REV_DEB_IMP:
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_DEB_IMP, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
					break;
				case COB_RES_SIM_OK: 
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_RES_SIM_OK, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
					break;
				case COB_RES_SIM_ERR:
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_RES_SIM_ERR, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
					break;
				case COB_REV_RECH: 
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_RECH, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
					workflowCobros.anularCobroRechazado(workflow, "", usuarioModificacion);
					break;
				case COB_REV_COB_ERR: 
					if(Validaciones.isObjectNull(sociedad) && Validaciones.isObjectNull(sistema)) {
						tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_COB_ERR, null, null, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null, TipoAccionEnum.ANULADA, null);
					} else {
						tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_COB_ERR, sociedad, sistema, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null, TipoAccionEnum.ANULADA, null);
					}
					// TODO: Agregar la llamada al metodo que avanza el WF según el estado de las tareas
					cobroBatchServicio.cambiarEstadoSegunEstadoDeTareas(idCobroPadre, usuarioModificacion);
					break;
				case COB_REV_COB_APR:
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_COB_APR, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
//					workflowCobros.anularCobroErrorEnApropiacion(workflow, "", usuarioModificacion);
					break;
				case COB_ERR_CONF_APLIC_MANUAL:
					tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), sociedad, sistema, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null, TipoAccionEnum.ANULADA, null);

					cobroBatchServicio.cambiarEstadoSegunEstadoDeTareas(idCobroPadre, usuarioModificacion);
					break;
				case COB_DESAPRO_APLI_MANUAL:
					tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), workflow.getIdWorkflow(), new Date(), usuarioModificacion, null);
					break;
				case COB_REV_ERR_APLIC_MANUAL:
					tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), sociedad, sistema, workflow.getIdWorkflow(), new Date(), usuarioModificacion, null, TipoAccionEnum.ANULADA, null);

					// TODO: Agregar la llamada al metodo que avanza el WF según el estado de las tareas
					cobroBatchServicio.cambiarEstadoSegunEstadoDeTareas(idCobroPadre, usuarioModificacion);
					break;
				default:
					break;
				}
			}

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param idCobro
	 * @param cobroModelo
	 * @param usuarioModificacion
	 */
	public void eliminarTareasAlImputar(Long idCobro, ShvCobEdCobro cobroModelo, UsuarioSesion usuarioModificacion) throws NegocioExcepcion {
		try {
			ShvWfWorkflow workflow = new ShvWfWorkflow();
			String idUsuario = usuarioModificacion.getIdUsuario();
			if(idCobro!=null){
				cobroModelo = cobroOnlineDao.buscarCobro(idCobro);
				workflow = cobroModelo.getWorkflow();
			}else{
				workflow = cobroModelo.getWorkflow();
			}
			
			List<ShvWfTarea> tarea = tareaDao.buscarTareasPendientes(workflow.getIdWorkflow());
			for (ShvWfTarea shvWfTarea : tarea) {
				switch (shvWfTarea.getTipoTarea()) {
				
				case COB_REV_RECH: 
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_RECH, workflow.getIdWorkflow(), new Date(), idUsuario, null);
					workflow = workflowCobros.anularCobroRechazado(workflow, "", idUsuario);
					cobroModelo.setWorkflow(workflow);
					cobroModelo = cobroOnlineDao.guardarCobro(cobroModelo);
					break;
				default:
					break;
				}
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param idCobro
	 * @param cobroModelo
	 * @param usuarioModificacion
	 */
	public void eliminarTareasAlEditar(Long idCobro, ShvCobEdCobro cobroModelo, UsuarioSesion usuarioModificacion) throws NegocioExcepcion {
		try {
			ShvWfWorkflow workflow = new ShvWfWorkflow();
			String idUsuario = usuarioModificacion.getIdUsuario();
			if(idCobro!=null){
				cobroModelo = cobroOnlineDao.buscarCobro(idCobro);
				workflow = cobroModelo.getWorkflow();
			}else{
				workflow = cobroModelo.getWorkflow();
			}
			
			List<ShvWfTarea> tarea = tareaDao.buscarTareasPendientes(workflow.getIdWorkflow());
			for (ShvWfTarea shvWfTarea : tarea) {
				switch (shvWfTarea.getTipoTarea()) {
				
				case COB_REV_DEB_IMP:
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_DEB_IMP, workflow.getIdWorkflow(), new Date(), idUsuario, null);
					break;
				case COB_RES_SIM_OK: 
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_RES_SIM_OK, workflow.getIdWorkflow(), new Date(), idUsuario, null);
					break;
				case COB_RES_SIM_ERR:
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_RES_SIM_ERR, workflow.getIdWorkflow(), new Date(), idUsuario, null);
					break;
				default:
					break;
				}
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
//	/**
//	 * Sobre cargo el servicio para que utilizarlo en aplicaciones manuales. En estas nesesito el sistema Destion
//	 * @param idCobro
//	 * @param usuarioModificacion
//	 * @param observaciones
//	 * 
//	 */
//	public void rechazarTareaDeAprobacionCobroOConfirmacionAplicacionManual(Long idCobro,  UsuarioSesion usuarioModificacion, String observaciones, SistemaEnum sistemaDestino, SociedadEnum sociedad) throws NegocioExcepcion {
//
//		this.rechazarTareaDeAprobacionCobroOConfirmacionAplicacionManual(idCobro, usuarioModificacion, observaciones, sistemaDestino, sociedad);
//	}
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobroDto
	 * @throws PersistenciaExcepcion 
	 * @throws IOException 
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, NegocioExcepcion.class }, propagation = Propagation.REQUIRED)
	public void rechazarTareaDeAprobacionCobroOConfirmacionAplicacionManual(Long idCobro,  UsuarioSesion usuarioModificacion, String observaciones, SistemaEnum sistemaDestino, SociedadEnum sociedad) throws NegocioExcepcion, PersistenciaExcepcion, IOException {

		String idUsuario = usuarioModificacion.getIdUsuario();
		String nombreApellidoUsuarioRechazoAplManual = "";
		ShvCobEdCobroSimplificadoConWorkflow cobro = null;
		boolean rechazoConfirmacionAplicacionManual = false;
		boolean hayCompensacionesSap = false;
		
		try {
			cobro = cobroOnlineDao.buscarCobroSimplificadoConWorkflow(idCobro);

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
		
		ShvWfWorkflow workflow = workflowService.buscarWorkflow(cobro.getIdWorkflow());
		

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Preparo variables comunes
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		String asunto = "Cobro rechazado por Referente - ";
		TipoTareaEnum tipoTareaParaFinalizar = TipoTareaEnum.COB_AUTR_COB;
		
		if (Estado.COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES.equals(workflow.getEstado())) {
			asunto ="Cobro rechazado por Supervisor Operaciones especiales - ";
			tipoTareaParaFinalizar = TipoTareaEnum.COB_AUTR_COB_SUP_OP_ESP;
			
		} else if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(workflow.getEstado()) || Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())
				|| Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC.equals(workflow.getEstado()) || Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(workflow.getEstado())
				|| Estado.COB_ERROR_DESAPROPIACION.equals(workflow.getEstado())) {
			
			
			tipoTareaParaFinalizar = retornaTareaAplicacionManual(sistemaDestino, workflow.getEstado());
			nombreApellidoUsuarioRechazoAplManual = ldapServicio.buscarUsuarioPorUidEnMemoria(usuarioModificacion.getIdUsuario()).getNombreCompleto();
			// Seteo usuario y fecha de rechazo en el cobro
//			cobro.setUsuarioRechazoAplicacionManual(usuarioModificacion.getIdUsuario());
//			cobro.setReferenteRechazoAplicacionManual(nombreApellidoUsuarioRechazoAplManual);
//			cobro.setFechaRechazoAplicacionManual(new Date());
//			cobro.setObservacionesAplicacionesManual(observaciones);
		}

		
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Avanzo el workflow por el camino de rechazo según corresponda
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			

		if (Estado.COB_PEND_SUPERVISOR_OPERACIONES_ESPECIALES.equals(workflow.getEstado())) {
			crearTareaRechazoDeAprobacionCobroOConfirmacionAplicacionManual(cobro, usuarioModificacion, observaciones);
			workflow = workflowCobros.rechazarAutorizacionSolicitadaAlSupOperacionesEspeciales(workflow, observaciones, idUsuario);
			workflow = workflowService.agregarWorkflowMarca(workflow, usuarioModificacion.getIdUsuario(), MarcaEnum.RECHAZADO_POR_SUPERVISOR_OP_ESPECIALES);
	
		} else if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(workflow.getEstado()) || Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())
				|| Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC.equals(workflow.getEstado()) || Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(workflow.getEstado())
				|| Estado.COB_ERROR_DESAPROPIACION.equals(workflow.getEstado())) {
			
			rechazoConfirmacionAplicacionManual=true;
			
			try {
				List<ShvCobTransaccionSinOperacion> listaTransaccionesConTratamientoAplManual = cobroDao.buscarTransaccionesPorIdOperacionParaRechazoConfirmAplicacionManual(cobro.getIdOperacion());
				
				// Si es un cobro con tratamiento de aplicacion manual viejo va a realizar lo siguiente
				
				if(Validaciones.isCollectionNotEmpty(listaTransaccionesConTratamientoAplManual)){
					for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesConTratamientoAplManual) {
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.ERROR_TRATAMIENTO);
						transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
						transaccion.setMensajeEstado("Aplicación Manual rechazada por el usuario: " + nombreApellidoUsuarioRechazoAplManual);
						cobroDao.guardarTransaccionSinOperacion(transaccion);
					}
					
					workflow = workflowCobros.registrarCobroPendienteDeConfirmacionManualAEnProceso(workflow, observaciones, idUsuario);
				
				} else {
					
					// si es un cobro nuevo con otros debitos.
					List<ShvCobTransaccionSinOperacion> listaTransacciones = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYSistemaYSociedad(cobro.getIdOperacion(), sistemaDestino, sociedad);
					
					for (ShvCobTransaccionSinOperacion transaccion : listaTransacciones) {
						
						transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.EN_PROCESO_DESAPROPIACION_APL_MANUAL_RECHAZADA );
						transaccion.setTipoMensajeEstado(TipoMensajeEstadoEnum.ERR);
						transaccion.setMensajeEstado("Aplicación Manual rechazada por el usuario: " + nombreApellidoUsuarioRechazoAplManual);
						cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(transaccion.getIdOperacion(), transaccion, false);
						cobroDao.guardarTransaccionSinOperacion(transaccion);
						cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(transaccion.getIdOperacion(), transaccion, false);
						
						ShvCobTransaccionSinOperacion trAux = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYNumeroTransaccion(
								transaccion.getIdOperacion(), transaccion.getNumeroTransaccion());
						Traza.auditoria(this.getClass(), 
								"+++++++++++++++++++++busco la misma transaccion para ver si el cambio impactó en la base+++++++++++++++++++++");
						cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(trAux.getIdOperacion(), trAux, false);
						
						if(cobroBatchSoporteImputacionServicio.hayMedioPagoComProveedoresOLiquidoProducto(transaccion)){
							hayCompensacionesSap = true;
						}
						
						
					}
					
					List<ShvCobTransaccionSinOperacion> listaTransaccionesDependencia = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYSistemaDependenciaYSociedadDependencia(cobro.getIdOperacion(), sistemaDestino, sociedad);
					
					if(Validaciones.isCollectionNotEmpty(listaTransaccionesDependencia)){
						for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesDependencia){
							if(!hayCompensacionesSap){
							transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE);
							} else {
								transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.SIN_PROCESAR_POR_ERROR_EN_GRUPO);
							}
							cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(transaccion.getIdOperacion(), transaccion, false);
							cobroDao.guardarTransaccionSinOperacion(transaccion);
							cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(transaccion.getIdOperacion(), transaccion, false);
							
							ShvCobTransaccionSinOperacion trAux = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYNumeroTransaccion(
									transaccion.getIdOperacion(), transaccion.getNumeroTransaccion());
							Traza.auditoria(this.getClass(), 
									"+++++++++++++++++++++busco la misma transaccion para ver si el cambio impactó en la base+++++++++++++++++++++");
							cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(trAux.getIdOperacion(), trAux, false);
						}
					}
					
					if(hayCompensacionesSap){
						List<ShvCobTransaccionSinOperacion> listaTransaccionesCobro = cobroDao.buscarTransaccionPorIdOperacionYEstado(cobro.getIdOperacion(), EstadoTransaccionEnum.PENDIENTE);
						if(Validaciones.isCollectionNotEmpty(listaTransaccionesCobro)){
							for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesCobro){
							transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.SIN_PROCESAR_POR_ERROR_EN_GRUPO);
							cobroDao.guardarTransaccionSinOperacion(transaccion);	
							}
						}
					}
					workflow = actualizarEstadoWorkflow("",workflow, idUsuario,sociedad, sistemaDestino,rechazoConfirmacionAplicacionManual);
			
				}
				
				
				if (usuarioModificacion.esReferenteCaja()) {
					workflow = workflowService.agregarWorkflowMarcaConObservaciones(workflow, idUsuario, MarcaEnum.RECHAZADO_POR_REFERENTE_CAJA,"("+sociedad.getDescripcion()+"/"+sistemaDestino.getDescripcion()+")");
		
				} else if (usuarioModificacion.esReferenteOperacionesExternas()) {
					workflow = workflowService.agregarWorkflowMarcaConObservaciones(workflow, idUsuario, MarcaEnum.RECHAZADO_POR_REFERENTE_OPERACIONES_EXTERNAS,"("+sociedad.getDescripcion()+"/"+sistemaDestino.getDescripcion()+")");
				}
							
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e);
			}
	
		} else {
			crearTareaRechazoDeAprobacionCobroOConfirmacionAplicacionManual(cobro, usuarioModificacion, observaciones);
			workflow = workflowCobros.rechazarAutorizacionSolicitadaAlReferente(workflow, observaciones, idUsuario);
			workflow = workflowService.agregarWorkflowMarca(workflow, idUsuario, MarcaEnum.RECHAZADO_POR_REFERENTE);
		}		
		
		//setear el usuario y fecha de ultima modificacion en el cobro edicion
		cobro.setUsuarioUltimaModificacion(usuarioModificacion.getIdUsuario());
		cobro.setFechaUltimaModificacion(new Date());
		
		try {
			cobroOnlineDao.guardarCobroSimplificadoConWorkflow(cobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
		
		
		if (rechazoConfirmacionAplicacionManual) {
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			// Cierro la tarea que se acaba de rechazar
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			tareaServicio.finalizarTareaCorrecto(
					tipoTareaParaFinalizar,
					sociedad,
					sistemaDestino,
					workflow.getIdWorkflow(),
					workflow.getFechaUltimaModificacion(), 
					usuarioModificacion.getIdUsuario(), 
					null,
					TipoAccionEnum.RECHAZADA,
					observaciones);
		} else {
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			// Envío el mail de tarea rechazada
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			Mail mail = crearMailRechazarTareaDeAprobacionCobroOConfirmacionAplicacionManual(asunto, cobro, usuarioModificacion, observaciones);
	
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			// Cierro la tarea que se acaba de rechazar
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

			tareaServicio.finalizarTareaCorrecto(
						tipoTareaParaFinalizar, 
						workflow.getIdWorkflow(), 
						workflow.getFechaUltimaModificacion(), 
						usuarioModificacion.getIdUsuario(), 
						mail);
		}
		
		
	}

	/**
	 * retorno la tarea aplicacion manual segun el sistemas destino y el estado
	 * @param sistemaDestino
	 * @param estado
	 * @return
	 */
	private TipoTareaEnum retornaTareaAplicacionManual(SistemaEnum sistemaDestino, Estado estado) {
		TipoTareaEnum tipoTarea = null;
		
		if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado) || Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(estado)
				|| Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC.equals(estado) || Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(estado)
				|| Estado.COB_ERROR_DESAPROPIACION.equals(estado)) {
			tipoTarea = TipoTareaEnum.COB_CONF_APLIC_MANUAL;
		} else if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
			tipoTarea = TipoTareaEnum.DES_CONFIRMA_APL_MAN;
		} else if (Estado.COB_ERROR_COBRO.equals(estado)) {
			tipoTarea = TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL;
		}

		return tipoTarea;
	}
	/**
	 * 
	 * @param asunto
	 * @param cobro
	 * @param usuarioModificacion
	 * @return
	 * @throws PersistenciaExcepcion 
	 * @throws IOException 
	 */
	private Mail crearMailRechazarTareaDeAprobacionCobroOConfirmacionAplicacionManual (String asunto, 
			ShvCobEdCobroSimplificadoConWorkflow cobro, UsuarioSesion usuarioModificacion, String observaciones) throws NegocioExcepcion {
		
		
		asunto += cobro.getIdOperacion().toString();
		asunto += " - ";
		asunto += Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.empresa") + cobro.getEmpresa().getDescripcion();
		asunto += " / ";

		// Busco el cliente de manera exclusiva, para no sobrecargar el objeto de cobro
		List<ShvCobEdClienteSimplificado> clientes;
		try {
			clientes = cobroClienteDao.listarClientesSimplificadosPorIdCobro(cobro.getIdCobro());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
		
		Iterator<ShvCobEdClienteSimplificado> iterator = clientes.iterator();
		if (iterator.hasNext()) {
			ShvCobEdClienteSimplificado cliente = iterator.next();
			asunto += Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cuit") + cliente.getCuit();
			asunto += " / ";
			asunto += Propiedades.MENSAJES_PROPIEDADES.getString("mail.cobros.cliente") + cliente.getIdClienteLegado();
			if (cliente.getRazonSocial() != null) {
				asunto += " / ";
				asunto += cliente.getRazonSocial();
			}
		}
		
		StringBuffer cuerpoMail = new StringBuffer();
		cuerpoMail.append(Propiedades.MENSAJES_PROPIEDADES.getString("cobro.mail.cuerpo.cabecera"));

		Iterator<ShvCobEdClienteSimplificado> iteratorClientes = clientes.iterator();
		while (iteratorClientes.hasNext()){
			ShvCobEdClienteSimplificado cliente = iteratorClientes.next();
			cuerpoMail.append(
					Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("cobro.mail.cuerpo.detalle.imputacion"),
					cobro.getEmpresa().getDescripcion(),
					Validaciones.isNullEmptyOrDash(cliente.getCuit()) ? "" : cliente.getCuit(),
					cliente.getIdClienteLegado().toString(),
					Validaciones.isNullEmptyOrDash(cliente.getRazonSocial()) ? "" : cliente.getRazonSocial()
				));
		}
		
		cuerpoMail.append("<br><br>");
		cuerpoMail.append("Usuario que rechazó el pedido: ");
		cuerpoMail.append(usuarioModificacion.getNombreCompleto());

		// TODO: Esto está bien? No debieran ser las observaciones que se cargan en el Online?
		// String observacion = obtenerObseHistorial(cobroDto, null);
		if (!Validaciones.isNullOrEmpty(observaciones)) {
			cuerpoMail.append("<br><br>");
			cuerpoMail.append( "Observaciones:");
			cuerpoMail.append("<br>");
			cuerpoMail.append(Utilidad.formateadoMail(observaciones));
		}

		UsuarioLdapDto usuarioAnalistaLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(cobro.getIdAnalista());
		String mailAnalista = usuarioAnalistaLdap != null ? usuarioAnalistaLdap.getMail() : "";
		
		UsuarioLdapDto usuarioCopropietarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(cobro.getIdCopropietario());
		String mailCopropietario = usuarioCopropietarioLdap != null ? usuarioCopropietarioLdap.getMail() : "";
		
		Mail mail = new Mail(
					mailAnalista, 
					mailCopropietario, 
					null, 
					asunto, 
					cuerpoMail);
		
		return mail;
	}
	
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void crearTareaRechazoDeAprobacionCobroOConfirmacionAplicacionManual(ShvCobEdCobroSimplificadoConWorkflow cobro, 
								UsuarioSesion usuarioModificacion, String observaciones) throws NegocioExcepcion {

		try {
			ShvWfWorkflow workflow = workflowService.buscarWorkflow(cobro.getIdWorkflow());
			
			if (!Estado.COB_RECHAZADO.equals(workflow.getEstado())) {
				
				TareaDto tarea = new TareaDto();
				tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
				
//				if (rechazoConfirmacionAplicacionManual) {
//					tarea.setTipoTarea(TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL);
//				} else {
					tarea.setTipoTarea(TipoTareaEnum.COB_REV_RECH);
//				}
				
				if (Validaciones.isNullOrEmpty(observaciones)) {
					observaciones = Utilidad.EMPTY_STRING;
				}				
				
				tarea.setIdWorkflow(workflow.getIdWorkflow());
				tarea.setFechaCreacion(new Date());
				tarea.setUsuarioCreacion(usuarioModificacion.getIdUsuario());
				tarea.setUsuarioAsignacion(workflow.getUsuarioAlta());
				tarea.setGestionaSobre(TipoTareaGestionaEnum.COBRO);
				tarea.setPerfilAsignacion(TipoPerfilEnum.ANALISTA_COBRANZA.descripcion());

				//Moneda Operacion
				tarea.setMonedaImporte(cobro.getMonedaOperacion().getSigno2());
				if (!Validaciones.isObjectNull(cobro.getImporteTotal())) {
					tarea.setImporte(Utilidad.formatCurrency(cobro.getImporteTotal(), false, true));
				}
				tarea.setIdCobro(Long.valueOf(cobro.getIdCobro()));
				tarea.setIdOperacion(cobro.getIdOperacion());
				
				// Seteo si la tarea debe o no enviar mail
				tarea.setEnviarMail(false);
				
				// Busco el cliente de manera exclusiva, para no sobrecargar el objeto de cobro
				List <ShvCobEdCliente> clientes = cobroClienteDao.listarClientesPorIdCobro(cobro.getIdCobro());
				
				// Según lógica original, recorro todos los clientes y me quedo con el último para la tarea.
				for (ShvCobEdCliente cliente : clientes) {
					tarea.setNroCliente(cliente.getIdClienteLegado().toString());
					tarea.setRazonSocial(cliente.getRazonSocial());
				}
				// Fin Cliente

				// TODO: porque no usamos el crear tarea????? se que esto viene de milenios atras...
				tareaServicio.crearTareaRechazoAprobacionCobro(tarea);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	

	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobro
	 * @throws NegocioExcepcion
	 */
	private void crearTareaPendienteAprobacionCobro(CobroDto cobro, UsuarioSesion usuarioModificacion) throws NegocioExcepcion {
		try {
			ShvCobEdCobro cobroModelo = cobroOnlineDao.buscarCobro(cobro.getIdCobro());
			ShvWfWorkflow workflow = cobroModelo.getWorkflow();
			Date now = new Date();
			ClienteDto clienteFinal = new ClienteDto();
			String idUsuario = usuarioModificacion.getIdUsuario();

			TareaDto tarea = new TareaDto();
			Set<ClienteDto> clientes = cobro.getClientes();
			String asuntoMail= " - Cobro pendiente de autorizar - ";
			asuntoMail += cobro.getIdOperacion().toString();
			asuntoMail += " / ";
			Iterator<ClienteDto> iterator = clientes.iterator();
			if(iterator.hasNext()){
				ClienteDto cliente = iterator.next();
				clienteFinal = cliente;
				asuntoMail += cliente.getIdClienteLegado();
				if(cliente.getRazonSocial()!=null){
					asuntoMail += " / ";
					asuntoMail += cliente.getRazonSocial();
				}
			}

			String cuerpoMail = "Detalle de los clientes asociados al cobro pendiente de autorizar: ";
			cuerpoMail += "<br>";
			cuerpoMail += "Número de operación de cobro: ";
			cuerpoMail += cobro.getIdOperacion().toString();
			Iterator<ClienteDto> iteratorClientes = clientes.iterator();
			while (iteratorClientes.hasNext()){
				cuerpoMail += "<br>";
				ClienteDto cliente = iteratorClientes.next();
				cuerpoMail += cliente.getIdClienteLegado();
				if(cliente.getRazonSocial()!=null){
					cuerpoMail += " / ";
					cuerpoMail += cliente.getRazonSocial();
				}
			}
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			if(Estado.COB_EN_EDICION.equals(workflow.getEstado())){
				cobroModelo.setUsuarioDerivacion(idUsuario);
				cobroModelo.setFechaDerivacion(now);
				cobroModelo.setUsuarioUltimaModificacion(idUsuario);
				cobroModelo.setFechaUltimaModificacion(now);
				// En esta transicion las observaciones presentes se persisten en el estado corriente
				
				//Me fijo si hay compensaciones sap. Si hay, se pasa al estado "Pend. Sup Operaciones Especiales"
				// sino
				// avanza de “En Edicion” a "Pend. Referente Cobranza"
				
				if (hayCompensacionesSap(cobroModelo)){
					workflow = workflowCobros.solicitarImputacionCobroConPedidoAutorizacionSupOperacionesEspeciales(workflow, cobroModelo.getObservacion(), idUsuario);
					tarea.setPerfilAsignacion(TipoPerfilEnum.SUPERVISOR_OPERACIONES_ESPECIALES.descripcion());
					tarea.setTipoTarea(TipoTareaEnum.COB_AUTR_COB_SUP_OP_ESP);
				} else {
					workflow = workflowCobros.solicitarImputacionCobroConPedidoAutorizacionReferente(workflow, cobroModelo.getObservacion(), idUsuario);
					tarea.setPerfilAsignacion(TipoPerfilEnum.REFERENTE_COBRANZA.descripcion());
					tarea.setTipoTarea(TipoTareaEnum.COB_AUTR_COB);
				}
				cobroModelo.setWorkflow(workflow);
				// la observacion se eliminan del modelo cobro
				cobroModelo.setObservacion(Utilidad.EMPTY_STRING);
				cobroModelo = cobroOnlineDao.guardarCobro(cobroModelo);
			}else if(Estado.COB_RECHAZADO.equals(workflow.getEstado())){
				
				cobroModelo.setUsuarioDerivacion(idUsuario);
				cobroModelo.setFechaDerivacion(now);
				cobroModelo.setUsuarioUltimaModificacion(idUsuario);
				cobroModelo.setFechaUltimaModificacion(now);
				// En esta transicion las observaciones presentes se persisten en el estado corriente
				// avanza de “Rechazado” a "Pend. Referente Cobranza"
				
				if (hayCompensacionesSap(cobroModelo)){
					workflow = workflowCobros.solicitarAutorizacionCobroRechazadoAlSupervisor(workflow, cobroModelo.getObservacion(), idUsuario);
					tarea.setPerfilAsignacion(TipoPerfilEnum.SUPERVISOR_OPERACIONES_ESPECIALES.descripcion());
					tarea.setTipoTarea(TipoTareaEnum.COB_AUTR_COB_SUP_OP_ESP);
				} else {
					workflow = workflowCobros.solicitarAutorizacionCobroRechazadoAlReferente(workflow, cobroModelo.getObservacion(), idUsuario);
					tarea.setPerfilAsignacion(TipoPerfilEnum.REFERENTE_COBRANZA.descripcion());
					tarea.setTipoTarea(TipoTareaEnum.COB_AUTR_COB);
				}
				cobroModelo.setWorkflow(workflow);
				// la observacion se eliminan del modelo cobro
				cobroModelo.setObservacion(Utilidad.EMPTY_STRING);
				tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_REV_RECH, workflow.getIdWorkflow(), now, idUsuario, null);
				cobroModelo = cobroOnlineDao.guardarCobro(cobroModelo);
			}
			
			tarea.setIdWorkflow(workflow.getIdWorkflow());
			tarea.setFechaCreacion(now);
			tarea.setUsuarioCreacion(workflow.getUsuarioAlta());

			tarea.setGestionaSobre(TipoTareaGestionaEnum.COBRO);
			//Moneda Operacion
			tarea.setMonedaImporte(cobro.getMonedaOperacion());
			if(!Validaciones.isObjectNull(cobroModelo.getImporteTotal())){
				tarea.setImporte(Utilidad.formatCurrency(cobroModelo.getImporteTotal(), false, true));
			}

			tarea.setIdCobro(Long.valueOf(cobro.getIdCobro()));
			tarea.setIdOperacion(Long.valueOf(cobro.getIdOperacion()));
			
			tarea.setNroCliente(clienteFinal.getIdClienteLegado()); //ver de pasar nombre completo si corresponde
			tarea.setRazonSocial(clienteFinal.getRazonSocial());
			
			//MAIL
			String observacion = this.obtenerObservacionHistorialYObservacionTarea(cobro);
			if(!Validaciones.isNullOrEmpty(observacion)){
				cuerpoMail += "<br><br>";
				cuerpoMail += "Observaciones:";
				cuerpoMail += "<br>";
				cuerpoMail += Utilidad.formateadoMail(
					observacion
				);
			}
			tarea.setAsuntoMail(asuntoMail);
			tarea.setCuerpoMail(cuerpoMail);
			tarea.setEnviarMail(true);
			tareaServicio.crearTareaAprobacionCobro(tarea);
			// Actulizo el estado en el dto
			cobro.setEstado(workflow.getEstado());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * Setea los datos de los cobradores mic, calipso
	 * @param entradaCalipsoDeudaWs
	 * @param consultaCobradores
	 */
	public void setDatosCobradores(ShvCobEdDebito debitoAConsultar, ConsultaDebitosACobradoresThread consultaCobradores){
		
		if(SistemaEnum.CALIPSO.equals(debitoAConsultar.getSistemaOrigen())){
				
			IdDocumento documento = new IdDocumento();
			documento.setTipoComprobante(debitoAConsultar.getTipoComprobante());
			documento.setNumeroComprobante(debitoAConsultar.getNumeroComprobante());
			documento.setSucursalComprobante(debitoAConsultar.getSucursalComprobante());
			
			if(!Validaciones.isObjectNull(debitoAConsultar.getClaseComprobante())){
				documento.setClaseComprobante(debitoAConsultar.getClaseComprobante());
			}
			
			EntradaCalipsoDeudaWS entradaCalipsoDeudaWs= new EntradaCalipsoDeudaWS();
			entradaCalipsoDeudaWs.setIdDocumento(documento);
			consultaCobradores.setCobrador(SistemaEnum.CALIPSO);
			entradaCalipsoDeudaWs.setMoneda(debitoAConsultar.getMoneda());
			if(ConstantesCobro.ID_MOTIVO_COBRO_COMPENSACION.equalsIgnoreCase(debitoAConsultar.getPk().getCobro().getMotivo().getIdMotivoCobro().toString())) {
				entradaCalipsoDeudaWs.setFechaCotizacion(debitoAConsultar.getPk().getCobro().getFechaTipoCambio());
			}
			
			//Creo el cliente y lo agrego a la lista de clientes.
			List<Cliente> listaCliente = new ArrayList<Cliente>();
			Cliente cliente = new Cliente();
			cliente.setIdClienteLegado(BigInteger.valueOf(debitoAConsultar.getIdClienteLegado()));
			cliente.setIdDocumento(documento);
			listaCliente.add(cliente);
			
			entradaCalipsoDeudaWs.setListaClientes(listaCliente);
			
			InformacionParaPaginadoDebito informacionParaPaginado = new InformacionParaPaginadoDebito();
			informacionParaPaginado.setCantidadDeRegistrosARetornar(BigInteger.ONE);
			entradaCalipsoDeudaWs.setInformacionParaPaginado(informacionParaPaginado);
			
			consultaCobradores.setEntradaCalipsoDeudaWs(entradaCalipsoDeudaWs);
			
		}else{
			if (SistemaEnum.MIC.equals(debitoAConsultar.getSistemaOrigen())){
				
				MicConsultaDeudaEntrada micConsultaDeudaEntrada = new MicConsultaDeudaEntrada();
				MicInformacionFactura informacionFactura = new MicInformacionFactura();
				
				if (!Validaciones.isObjectNull(debitoAConsultar.getTipoComprobante())){
				
					if(TipoComprobanteEnum.DUC.equals(debitoAConsultar.getTipoComprobante())){
						String numReferenciaMic = debitoAConsultar.getSucursalComprobante() + "-";
						numReferenciaMic += debitoAConsultar.getNumeroComprobante(); 
						informacionFactura.setNumeroReferenciaMIC(numReferenciaMic);
						informacionFactura.setTipoComprobante(TipoComprobanteEnum.DUC);
					} else{
						informacionFactura.setTipoComprobante(debitoAConsultar.getTipoComprobante());
						informacionFactura.setSucursalComprobante(debitoAConsultar.getSucursalComprobante());
						informacionFactura.setNumeroComprobante(debitoAConsultar.getNumeroComprobante());
						
						if (!Validaciones.isObjectNull(debitoAConsultar.getClaseComprobante())){
							informacionFactura.setClaseComprobante(debitoAConsultar.getClaseComprobante());
						}
					}
				} else {
					informacionFactura.setTipoComprobante(TipoComprobanteEnum.TOD);
				}
				
				if(!Validaciones.isObjectNull(debitoAConsultar.getTipoComprobante())
						&& Validaciones.isObjectNull(debitoAConsultar.getSucursalComprobante())
						&& Validaciones.isObjectNull(debitoAConsultar.getNumeroComprobante())
						&& !Validaciones.isObjectNull(debitoAConsultar.getNumeroReferenciaMic())){
					
					informacionFactura.setNumeroReferenciaMIC(debitoAConsultar.getNumeroReferenciaMic().toString());
				}
				
				micConsultaDeudaEntrada.setInformacionFactura(informacionFactura);
				
				List<BigInteger> listaClientes = new ArrayList<BigInteger>();
				listaClientes.add(BigInteger.valueOf(debitoAConsultar.getIdClienteLegado()));
				micConsultaDeudaEntrada.setListaClientes(listaClientes);
				
				MicInformacionPaginadoDebito paginado = new MicInformacionPaginadoDebito();
				paginado.setCantidadRegistrosARetornar(1);
				micConsultaDeudaEntrada.setInformacionPaginado(paginado);
				consultaCobradores.setCobrador(SistemaEnum.MIC);
				consultaCobradores.setMicConsultaDeudaEntrada(micConsultaDeudaEntrada);
				
			}
		}
		debitoAConsultar.setResultadoValidacionEstado(EstadoDebitoImportadoEnum.EN_PROCESO);
		consultaCobradores.setDebito(debitoAConsultar);

	}
	
	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	public synchronized void cambiarEstadoDebitoValidado(ShvCobEdDebito debito,ConsultaDebitosACobradoresThread consultarCobradores){
		int index=0;
		int indexLista=0;
		if(listaDebitosOriginal.indexOf(debito)>=0){
			
			index =listaDebitosOriginal.indexOf(debito);

			Traza.auditoria(CobroOnlineServicioImpl.class, "Cambio de estado Debito Thread [ID" + debito.getPk().getIdDebito() +"] a -->[" + debito.getResultadoValidacionEstado() + "]");
			listaDebitosOriginal.set(index, debito);
			
			if (SistemaEnum.CALIPSO.equals(debito.getSistemaOrigen())){
				indexLista =listaDebitosCalipso.indexOf(debito);
				listaDebitosCalipso.remove(indexLista);
			} else { 
				indexLista =listaDebitosMic.indexOf(debito);
				listaDebitosMic.remove(indexLista);
			}
			
			if(!listaDebitosOriginal.isEmpty()){
				
				boolean siguiente=false;
				
				while(!siguiente && index < listaDebitosOriginal.size()){
					
					if(EstadoDebitoImportadoEnum.PENDIENTE.equals(listaDebitosOriginal.get(index).getResultadoValidacionEstado())){
						
						if(SistemaEnum.CALIPSO.equals(listaDebitosOriginal.get(index).getSistemaOrigen())){
							
							listaDebitosCalipso.add(listaDebitosOriginal.get(index));
							Traza.auditoria(CobroOnlineServicioImpl.class, "THREAD ANTERIOR [ID"+debito.getPk().getIdDebito() + "] ---> Nueva Consulta : Thread [ID" + listaDebitosOriginal.get(index).getPk().getIdDebito() +"]");
							cargarDebitosEjecutarThreads(listaDebitosCalipso.get(listaDebitosCalipso.size()-1),consultarCobradores);
							
						} else if(SistemaEnum.MIC.equals(listaDebitosOriginal.get(index).getSistemaOrigen())){
							
							listaDebitosMic.add(listaDebitosOriginal.get(index));
							Traza.auditoria(CobroOnlineServicioImpl.class,"[" + new Date().getHours()+":" + new Date().getMinutes()+ ":" + new Date().getSeconds() + "]" + "THREAD ANTERIOR [ID"+debito.getPk().getIdDebito() + "] ---> Nueva Consulta : Thread [ID" + listaDebitosOriginal.get(index).getPk().getIdDebito() +"]");
							cargarDebitosEjecutarThreads(listaDebitosMic.get(listaDebitosMic.size()-1),consultarCobradores);
							
						}
						siguiente=true;
					}else{
						
						if(index < listaDebitosOriginal.size()){
							index++;
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * 
	 * @param debito
	 */
	public void cargarDebitosEjecutarThreads(ShvCobEdDebito debito, ConsultaDebitosACobradoresThread consultarCobradores){
		//Nuevo Thread
		consultarCobradores = new ConsultaDebitosACobradoresThread();
		
		consultarCobradores.setName("Thread [ID" + debito.getPk().getIdDebito() + "]" );
		setDatosCobradores(debito,consultarCobradores);
		consultarCobradores.setCobroOnlineServicio(this);
		consultarCobradores.start();
		listaThreads.add(consultarCobradores);
	}
	
	/**
	 * 
	 */
	public GestionabilidadDto determinarGestionabilidadDeDeuda(CobroDebitoDto debito) throws NegocioExcepcion {
		SemaforoGestionabilidadDebito semaforo = new SemaforoGestionabilidadDebito();
		return semaforo.determinarGetionabilidad(debito);
	}
	
	/**
	 * 
	 */
	public GestionabilidadDto determinarGestionabilidadDeCredito(CobroCreditoDto credito) throws NegocioExcepcion {
		SemaforoGestionabilidadCredito semaforo = new SemaforoGestionabilidadCredito();
		return semaforo.determinarGetionabilidad(credito);
	}
	
	/**
	 * 
	 */
	@Override
	public DeimosJsonResponse validarEstadoDeimos(List<IDatosComunesEntrada> listaDebitosCreditos, EmpresaEnum empresa, List<IDatosComunesEntrada> listaDebitosCreditosYaSeleccionados) throws NegocioExcepcion {
		DeimosJsonResponse respuesta = new DeimosJsonResponse();
		List<IDatosComunesEntrada> listaDebitosCreditosActualizada = new ArrayList<IDatosComunesEntrada>();
		List<IDatosComunesEntrada> listaDebitosCreditosSeleccionados = new ArrayList<IDatosComunesEntrada>();
		try {
			for(IDatosComunesEntrada datosDeimos: listaDebitosCreditos) {
	
				CobroDebitoDto debito = null;
				CobroCreditoDto credito = null;
				SalidaDeimosConsultaEstadoDocumentoWS salidaDeimos = null;
				if(CobroDebitoDto.class.getSimpleName().equals(datosDeimos.getClaseString())){
					
					debito = (CobroDebitoDto) datosDeimos;
					
					if (SemaforoGestionabilidadEnum.AMARILLO.getDescripcion().equals(debito.getSemaforo().getSemaforo())
							&& SiNoEnum.SI.equals(debito.getMarcaMigradaOrigenEnum())) {
						salidaDeimos = clienteDeimosServicio.consultarDeimos(datosDeimos, empresa);
						if (!Validaciones.isObjectNull(salidaDeimos.getInformacionGeneralApropiacion())) {
							if (!Validaciones.isObjectNull(salidaDeimos.getInformacionGeneralApropiacion().getCantidadCuotasPagas())) {
								debito.setDmosCantidadDeCuotasPagas(salidaDeimos.getInformacionGeneralApropiacion().getCantidadCuotasPagas().longValue());
							}
							if (!Validaciones.isNullOrEmpty(salidaDeimos.getInformacionGeneralApropiacion().getCodigoEstadoTramite())) {
								debito.setDmosEstadoTramite(EstadoTramiteDeimosEnum.getEnumtByCodigo(salidaDeimos.getInformacionGeneralApropiacion().getCodigoEstadoTramite()));
							}
							if (!Validaciones.isObjectNull(salidaDeimos.getInformacionGeneralApropiacion().getCantidadCuotas())) {
								debito.setDmosCantidadDeCuotas(salidaDeimos.getInformacionGeneralApropiacion().getCantidadCuotas().longValue());
							}
							if (!Validaciones.isObjectNull(salidaDeimos.getInformacionGeneralApropiacion().getConvenio())) {
								debito.setDmosNroConvenio(salidaDeimos.getInformacionGeneralApropiacion().getConvenio().longValue());
							}
							if (!Validaciones.isObjectNull(salidaDeimos.getInformacionGeneralApropiacion().getApropiada())) {
								debito.setDmosApropiacionEnDeimos(salidaDeimos.getInformacionGeneralApropiacion().getApropiada());
							}
							if(!Validaciones.isNullOrEmpty(salidaDeimos.getInformacionGeneralApropiacion().getDescripcionEstadoTramite())){
								debito.setEstadoDeimos(EstadoTramiteDeimosEnum.getEnumtByCodigo(salidaDeimos.getInformacionGeneralApropiacion().getCodigoEstadoTramite()).descripcion());
							}
						}
					}
				}else{
					credito = (CobroCreditoDto) datosDeimos;
					
					if (SemaforoGestionabilidadEnum.AMARILLO.getDescripcion().equals(credito.getSemaforo().getSemaforo())
							&& SiNoEnum.SI.equals(credito.getMarcaMigradaOrigenEnum())) {
						salidaDeimos = clienteDeimosServicio.consultarDeimos(datosDeimos, empresa);
						if (!Validaciones.isObjectNull(salidaDeimos.getInformacionGeneralApropiacion())) {
							if (!Validaciones.isObjectNull(salidaDeimos.getInformacionGeneralApropiacion().getCantidadCuotasPagas())) {
								credito.setDmosCantidadDeCuotasPagas(salidaDeimos.getInformacionGeneralApropiacion().getCantidadCuotasPagas().longValue());
							}
							if (!Validaciones.isNullOrEmpty(salidaDeimos.getInformacionGeneralApropiacion().getCodigoEstadoTramite())) {
								credito.setDmosEstadoTramite(EstadoTramiteDeimosEnum.getEnumtByCodigo(salidaDeimos.getInformacionGeneralApropiacion().getCodigoEstadoTramite()));
							}
							if (!Validaciones.isObjectNull(salidaDeimos.getInformacionGeneralApropiacion().getCantidadCuotas())) {
								credito.setDmosCantidadDeCuotas(salidaDeimos.getInformacionGeneralApropiacion().getCantidadCuotas().longValue());
							}
							if (!Validaciones.isObjectNull(salidaDeimos.getInformacionGeneralApropiacion().getConvenio())) {
								credito.setDmosNroConvenio(salidaDeimos.getInformacionGeneralApropiacion().getConvenio().longValue());
							}
							if (!Validaciones.isObjectNull(salidaDeimos.getInformacionGeneralApropiacion().getApropiada())) {
								credito.setDmosApropiacionEnDeimos(salidaDeimos.getInformacionGeneralApropiacion().getApropiada());
							}
							if (!Validaciones.isNullOrEmpty(salidaDeimos.getInformacionGeneralApropiacion().getDescripcionEstadoTramite())) {
								credito.setEstadoDeimos(EstadoTramiteDeimosEnum.getEnumtByCodigo(salidaDeimos.getInformacionGeneralApropiacion().getCodigoEstadoTramite()).descripcion());
							}
						}
					}
				}
				
				if(!Validaciones.isObjectNull(debito)){
					GestionabilidadDto semaforo = determinarGestionabilidadDeDeuda(debito);
					debito.setSemaforo(semaforo);
					listaDebitosCreditosActualizada.add(debito);
					
					//evaluar cuales pasan a seleccionados
					if(!SemaforoGestionabilidadEnum.ROJO.getDescripcion().equals(debito.getSemaforo().getSemaforo())){
						// verifico si ya se encuentra seleccionado. Si no se encuentra lo selecciono
						if (!verificarSiDocumentoSeEncuentraEnElVector(debito, listaDebitosCreditosYaSeleccionados)) {
							listaDebitosCreditosSeleccionados.add(debito);
						}
					}
				}else{
					GestionabilidadDto semaforo = determinarGestionabilidadDeCredito(credito);
					credito.setSemaforo(semaforo);
					listaDebitosCreditosActualizada.add(credito);
					
					//evaluar cuales pasan a seleccionados
					if(!SemaforoGestionabilidadEnum.ROJO.getDescripcion().equals(credito.getSemaforo().getSemaforo())){
						if (!verificarSiDocumentoSeEncuentraEnElVector(credito, listaDebitosCreditosYaSeleccionados)) {
							listaDebitosCreditosSeleccionados.add(credito);
						}
					}
				}
			}
			respuesta.setListaDebitosCreditos(listaDebitosCreditosActualizada);
			respuesta.setListaDebitosCreditosSeleccionados(listaDebitosCreditosSeleccionados);
			
		} catch (NegocioExcepcion e) {
			if(e instanceof WebServiceExcepcion){
				respuesta.setErrorMensaje(e.getMensajeAuxiliar());
			}else{
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		}
		return respuesta;
	}

	protected boolean verificarSiDocumentoSeEncuentraEnElVector(IDatosComunesEntrada documento, List<IDatosComunesEntrada> listaDebitosCreditosYaSeleccionados) {
		for (IDatosComunesEntrada corriente : listaDebitosCreditosYaSeleccionados) {
			if (corriente.getIdPantalla().equals(documento.getIdPantalla())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public ShivaConsultaCreditoSalida buscarCreditosShiva(VistaSoporteBusquedaValoresFiltro shivaEntrada) throws NegocioExcepcion {
		try {
			shivaEntrada.setCobroOnlineCreditosShiva(true);
			List<VistaSoporteResultadoBusquedaValor> listaCreditos = vistaSoporteServicio.buscarValores(shivaEntrada);
			
			ShivaConsultaCreditoSalida salida = new ShivaConsultaCreditoSalida();
			salida.setListaCreditos(listaCreditos);
			
			ShivaControlPaginado controlPaginado = new ShivaControlPaginado();
			controlPaginado.setCantidadRegistrosRetornados(Long.valueOf(listaCreditos.size()));
			controlPaginado.setCantidadRegistrosTotales(Long.valueOf(listaCreditos.size()));
			salida.setControlPaginado(controlPaginado);
			
			if(Validaciones.isCollectionNotEmpty(listaCreditos)){
				ResultadoProcesamiento resultado = new ResultadoProcesamiento();
				resultado.setResultadoImputacion("OK");
				resultado.setCodigoError("");
				resultado.setDescripcionError("");
				salida.setResultadoProcesamiento(resultado);
			}
			
			return salida;
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}	
	
	@Override
	public List<Estado> listarComboEstados() throws NegocioExcepcion {
		try {
			List<Estado> lista = Estado.listarEstados("COB");
			return lista;
		} catch (Exception e){
			throw new NegocioExcepcion(e);
		}
	}

	@Override
	public List<CobroDto> listarCobrosOnline(VistaSoporteCobroOnlineFiltro cobroFiltro)
			throws NegocioExcepcion, NumberFormatException, PersistenciaExcepcion {

		List<CobroDto> listaCobrosDto = new ArrayList<CobroDto>();
		try {
			
			List<VistaSoporteCobrosOnline> listaCobros = vistaSoporteServicio.listarCobrosOnline(cobroFiltro);
			
			for (VistaSoporteCobrosOnline cobro : listaCobros) {
				listaCobrosDto.add((CobroDto) cobroOnlineVistaMapeo.map(cobro)); 
			}
			
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		return listaCobrosDto;
	}

	/**
	 * Valida si un cobro requiere Aprobacion.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param debito
	 */
	@Override
	public boolean validarSiRequiereAprobacion(CobroDto cobroDto) {
		boolean requiereAprobacion = false;
		if(!Validaciones.isObjectNull(cobroDto)){
			if(!Validaciones.isObjectNull(cobroDto.getMedios())){
				Set<CobroMedioDePagoDto> medios = cobroDto.getMedios();
				for (CobroMedioDePagoDto cobroMedioDePagoDto : medios) {
					if(TipoCreditoEnum.DESISTIMIENTO.equals(cobroMedioDePagoDto.getMpTipoCredito())
							|| TipoCreditoEnum.OTRAS_COMPENSACIONES.equals(cobroMedioDePagoDto.getMpTipoCredito())  
							|| TipoCreditoEnum.INTERCOMPANY.equals(cobroMedioDePagoDto.getMpTipoCredito())
							|| TipoCreditoEnum.LIQUIDOPRODUCTO.equals(cobroMedioDePagoDto.getMpTipoCredito())
							|| TipoCreditoEnum.IIBB.equals(cobroMedioDePagoDto.getMpTipoCredito())
							|| TipoCreditoEnum.CESION_CREDITOS.equals(cobroMedioDePagoDto.getMpTipoCredito())
							|| TipoCreditoEnum.PROVEEDORES.equals(cobroMedioDePagoDto.getMpTipoCredito())
							){
						requiereAprobacion= true;
					}
				}
			}
		}
		return requiereAprobacion;
	}
	
	/**
	 * Valida si es obligatorio el campo Observaciones al Imputar.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param debito
	 */
	@Override
	public boolean validarTransacciones(String idCobro) throws NegocioExcepcion {
		ShvCobCobro cobro;
		try {
			cobro = cobroDao.buscarCobro(new Long(idCobro));
			
			if(!Validaciones.isObjectNull(cobro)){
				if(!Validaciones.isObjectNull(cobro.getOperacion())){
					ShvCobOperacion operacion = cobro.getOperacion();
					Set<ShvCobTransaccion> transacciones = operacion.getTransacciones();

					ShvCobFactura factura = new ShvCobFactura();
					ShvCobTratamientoDiferencia tratamientoDiferencia = new ShvCobTratamientoDiferencia();
					Set<ShvCobMedioPago> mediosPago = new HashSet<ShvCobMedioPago>();
					List<ShvCobEdCliente> listarClientesDelCobroOnline = cobroOnlineDao.listarClientesDelCobroOnline(new Long(idCobro));

					if (listarClientesDelCobroOnline.size() == 1) {
						return false;
					}
					for (ShvCobTransaccion shvCobTransaccion : transacciones) {
						List<CuitHoldingAgenciaNegocioDto> listCuitHoldingAgendiaNegocio = new ArrayList<CuitHoldingAgenciaNegocioDto>();
						Set<String> clientesABuscar = new HashSet<String>();
						factura = shvCobTransaccion.getFactura();
						tratamientoDiferencia = shvCobTransaccion.getTratamientoDiferencia();
						mediosPago = shvCobTransaccion.getMediosPago();

						for (ShvCobMedioPago shvCobMedioPago : mediosPago) {
							if (shvCobMedioPago.getIdClienteLegado() != null ) {
								clientesABuscar.add(shvCobMedioPago.getIdClienteLegado().toString());
							} else if (
									shvCobMedioPago instanceof ShvCobMedioPagoCompensacionOtras ||
									shvCobMedioPago instanceof ShvCobMedioPagoCompensacionIntercompany ||
									shvCobMedioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto ||
									shvCobMedioPago instanceof ShvCobMedioPagoCompensacionCesionCredito ||
									shvCobMedioPago instanceof ShvCobMedioPagoCompensacionIIBB||
									shvCobMedioPago instanceof ShvCobMedioPagoCompensacionProveedor ||
									shvCobMedioPago instanceof ShvCobMedioPagoDesistimiento ||
									shvCobMedioPago instanceof ShvCobMedioPagoPlanDePago
							) {
								for (ShvCobMedioPagoCliente cliente : ((ShvCobMedioPagoUsuario)shvCobMedioPago).getListaMedioPagoClientes()) {
									clientesABuscar.add(cliente.getIdClienteLegado().toString());
								}
							}
						}

						if (!Validaciones.isObjectNull(factura) && factura.getIdClienteLegado() != null)
							clientesABuscar.add(factura.getIdClienteLegado().toString());
						if (tratamientoDiferencia!=null && tratamientoDiferencia != new ShvCobTratamientoDiferencia()) {
							if (tratamientoDiferencia.getIdClienteLegadoAcuerdoTrasladoCargo() != null)
								clientesABuscar.add(tratamientoDiferencia.getIdClienteLegadoAcuerdoTrasladoCargo().toString());
						}

						for (String idCliente : clientesABuscar) {
							CuitHoldingAgenciaNegocioDto cuitHoldingAgendiaNegocio = new CuitHoldingAgenciaNegocioDto();
							for (ShvCobEdCliente shvCobEdCliente : listarClientesDelCobroOnline) {
								if (idCliente.equals(shvCobEdCliente.getIdClienteLegado().toString())) {
									if (shvCobEdCliente.getCuit() != null) {
										cuitHoldingAgendiaNegocio.setCuit(shvCobEdCliente.getCuit());
									}
									if (shvCobEdCliente.getIdHolding() != null) {
										cuitHoldingAgendiaNegocio.setHolding(shvCobEdCliente.getIdHolding().toString());
									}
									if (shvCobEdCliente.getIdAgenciaNegocio() != null) {
										cuitHoldingAgendiaNegocio.setAgenciaNegocios(shvCobEdCliente.getIdAgenciaNegocio().toString());
									}
									listCuitHoldingAgendiaNegocio.add(cuitHoldingAgendiaNegocio);
								}
							}
						}
						Set<CuitHoldingAgenciaNegocioDto> setCuitHoldingAgenciaNegocio =  new HashSet<CuitHoldingAgenciaNegocioDto>(listCuitHoldingAgendiaNegocio);
						if (listCuitHoldingAgendiaNegocio.size() > 1) {
							boolean clientesNoRelacionados = false;
							for (CuitHoldingAgenciaNegocioDto dto : setCuitHoldingAgenciaNegocio) {
								if (Collections.frequency(listCuitHoldingAgendiaNegocio, dto) <= 1) {
									clientesNoRelacionados = true;
								}
							}
							if (clientesNoRelacionados) {
								return true;
							}
						}
						
					}
				}
			}
			return false;
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	/**
	 * Valida si es obligatorio el comprobante al Imputar.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param debito
	 */
	@Override
	public String validarComprobanteObligatorio(CobroDto cobroDto) throws NegocioExcepcion, ValidacionExcepcion {
		String comprobanteObligatorio="";
		try {
			//CobroDto cobroDto = buscarCobro(Long.parseLong(idCobro));
			Set<CobroMedioDePagoDto> medios = cobroDto.getMedios();
			CobroTratamientoDiferenciaDto tratamientoDiferencia = cobroDto.getTratamientoDiferencia();
			//cobroDto.setListaComprobantes(this.listarComprobantes(cobroDto.getIdCobro()));
			
			List<ComprobanteDto> lista = new ArrayList<ComprobanteDto>();
			Set<ComprobanteDto> listaComprobantes = new HashSet<ComprobanteDto>();
			Set<ComprobanteDto> listaComprobantesAplManual = new HashSet<ComprobanteDto>();
			Set<ComprobanteDto> listaComprobantesOtrosDebito = new HashSet<ComprobanteDto>();
			lista = this.listarComprobantes(cobroDto.getIdCobro());
			
			for (ComprobanteDto comprobanteDto : lista) {
				if (MotivoAdjuntoEnum.COMPROBANTE_COBRO.equals(MotivoAdjuntoEnum.getEnumByName(comprobanteDto.getMotivoAdjunto()))){
					listaComprobantes.add(comprobanteDto);
				} else if (MotivoAdjuntoEnum.APLICACION_MANUAL.equals(MotivoAdjuntoEnum.getEnumByName(comprobanteDto.getMotivoAdjunto()))){
					listaComprobantesAplManual.add(comprobanteDto);
				} else if (MotivoAdjuntoEnum.OTROS_DEBITO.equals(MotivoAdjuntoEnum.getEnumByName(comprobanteDto.getMotivoAdjunto()))){
					listaComprobantesOtrosDebito.add(comprobanteDto);
				}
			}
			
			cobroDto.setListaComprobanteAplicacionManual(listaComprobantesAplManual);
//			cobroDto.setListaComprobanteOtrosDebito(listaComprobantesOtrosDebito);
			cobroDto.setListaComprobantes(listaComprobantes);

			if (cobroDto.getListaComprobantes().isEmpty()) {
				for (CobroMedioDePagoDto cobroMedioDePagoDto : medios) {
					if (TipoMedioPagoEnum.DESISTIMIENTO.getIdTipoMedioPago().equals(cobroMedioDePagoDto.getIdMpTipoCredito()) ||
					    TipoMedioPagoEnum.COMPENSACION_OTRAS.getIdTipoMedioPago().equals(cobroMedioDePagoDto.getIdMpTipoCredito()) ||
					    TipoMedioPagoEnum.COMPENSACION_IIBB.getIdTipoMedioPago().equals(cobroMedioDePagoDto.getIdMpTipoCredito()) ||
					    TipoMedioPagoEnum.COMPENSACION_CESION_CREDITOS.getIdTipoMedioPago().equals(cobroMedioDePagoDto.getIdMpTipoCredito()) ||
					    TipoMedioPagoEnum.COMPENSACION_PROVEEDORES.getIdTipoMedioPago().equals(cobroMedioDePagoDto.getIdMpTipoCredito()) ||
					    TipoMedioPagoEnum.COMPENSACION_INTERCOMPANY.getIdTipoMedioPago().equals(cobroMedioDePagoDto.getIdMpTipoCredito()) ||
					    TipoMedioPagoEnum.COMPENSACION_LIQUIDO_PROD.getIdTipoMedioPago().equals(cobroMedioDePagoDto.getIdMpTipoCredito())) {
						comprobanteObligatorio = TipoMedioPagoEnum.getEnumByIdTipoMedioPago(cobroMedioDePagoDto.getIdMpTipoCredito()).name();
						break;
					}
					
				}
				if (Validaciones.isObjectNull(comprobanteObligatorio)){
					if (TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.name().equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {
						ReglaCobroFiltro reglaCobroFiltro = new ReglaCobroFiltro();
						TreeSet<CobroTransaccionDto> transaccionesDtoOrdenadas = new TreeSet<CobroTransaccionDto>(new ComparatorOrdenModificacionCobroTransaccionDto());
						transaccionesDtoOrdenadas.addAll(cobroDto.getTransacciones());
						
						List<ShvParamReglaCobro> reglasCobro = null;
						
						for (CobroTransaccionDto transaccionDto : transaccionesDtoOrdenadas) {
							if (TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO.name().equals(transaccionDto.getTipoMedioPagoNombre()) ||
									TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC.name().equals(transaccionDto.getTipoMedioPagoNombre())) {
								reglaCobroFiltro.setTipoRegla(TipoReglaCobroEnum.COMPROBANTE.name());
								reglaCobroFiltro.setEmpresa(cobroDto.getIdEmpresa());
								reglaCobroFiltro.setSegmento(cobroDto.getIdSegmento());
								reglaCobroFiltro.setMoneda(MonedaEnum.getEnumBySigno2(cobroDto.getMonedaOperacion()).name());
								reglaCobroFiltro.setMonto(Utilidad.stringToBigDecimal(transaccionDto.getImporteMedioPago()));
								reglaCobroFiltro.setPorcentaje(BigDecimal.ZERO);
								reglasCobro = reglaCobroServicio.buscar(reglaCobroFiltro);
								
								if (reglasCobro.isEmpty()) {
									String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.imputacion.validacion.noExisteReglaParametrica");
									Traza.error(getClass(), 
											Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.reglaParametrica.validacion"), 
													"'validarComprobanteObligatorio'",reglaCobroFiltro.toString(),mensajeError));
									throw new ValidacionExcepcion(mensajeError);
									} else
										if (reglasCobro.size() > 1 ) {
											String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.imputacion.validacion.masDeUnaReglaParametrica");
											Traza.error(getClass(), 
													Utilidad.reemplazarMensajes(
															Propiedades.MENSAJES_PROPIEDADES.getString("cobros.error.reglaParametrica.validacion"), 
															"'validarComprobanteObligatorio'",reglaCobroFiltro.toString(),mensajeError));
											throw new ValidacionExcepcion(mensajeError);
											} else
												if (AccionesReglaCobroEnum.CON_COMPROB.equals(reglasCobro.get(0).getAccion())) {
													comprobanteObligatorio = TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.name();
													}
								}
							}
						} else
							if (TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.name().equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {
								comprobanteObligatorio = TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.name();
								} else
									if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name().equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {
										if (cobroDto.getListaComprobanteAplicacionManual().isEmpty()){
											comprobanteObligatorio = TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name();
											}
										} 
					}
				}  else 
					if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name().equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {
						if (cobroDto.getListaComprobanteAplicacionManual().isEmpty()){
							comprobanteObligatorio = TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.name();
							}
						}
			return comprobanteObligatorio;
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public void validarDisponibilidadSaldoValoresEnProceso(CobroDto cobro) throws NegocioExcepcion {
		
		if (!Validaciones.isObjectNull(cobro.getCreditos())) {
			for (CobroCreditoDto cobroCreditoDto : cobro.getCreditos()) {
				if (!Validaciones.isObjectNull(cobroCreditoDto) && !Validaciones.isObjectNull(cobroCreditoDto.getIdValor())) {
					
					try {
						cobroBatchSoporteServicio.validarSaldoYDisponibilidad(cobroCreditoDto.getIdValor(), Utilidad.stringToBigDecimal(cobroCreditoDto.getImporteAUtilizar()));
					} catch (SimulacionCobroExcepcion e) {
						throw new ValidacionExcepcion(e.getMensajeAuxiliar(), e);
					} catch (NegocioExcepcion e) {
						throw new NegocioExcepcion(e.getMessage(), e);
					}
				}
			}
		}
		
	}

	@Transactional(readOnly=false)
	public List<CobroHistoricoDto> obtenerHistorialCobro (VistaSoporteBusquedaCobroHistorialFiltro filtro) throws NegocioExcepcion{
		
		List<CobroHistoricoDto> listaHistoricoDto = new ArrayList<CobroHistoricoDto>();
		
		try {
			List<VistaSoporteResultadoBusquedaCobroHistorial> historialCobros = vistaSoporteServicio
					.obtenerHistorialCobro(filtro);

			for (VistaSoporteResultadoBusquedaCobroHistorial hist : historialCobros) {
				listaHistoricoDto.add((CobroHistoricoDto) cobroOnlineHistorialMapeo.map(hist)); 
			}
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return listaHistoricoDto;
		
	}
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param idOperacion
	 */
	public boolean validarSimulacionBatchEnProceso(Long idCobro) throws NegocioExcepcion{
		boolean flag = false;
		
		List<MarcaEnum> marcas = this.obtenerUltimasMarcas(idCobro, null, true);
		if(Validaciones.isCollectionNotEmpty(marcas)){
			if(marcas.contains(MarcaEnum.SIMULACION_BATCH_EN_PROCESO)){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param idCobro
	 */
	public ShvCobEdCobro obtenerModeloCobro(Long idCobro) throws NegocioExcepcion{
		ShvCobEdCobro cobroModelo = new ShvCobEdCobro();
		try {
			cobroModelo = cobroOnlineDao.buscarCobro(idCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return cobroModelo;
	}
	/**
	 * Método que trabaja con el cobro ERROR y cobro ERROR en APROPIACION al guardar.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param idOperacion
	 */
	// Se comenta ya que el transactional al evaluar la tarea al momento de cambiar el estado del cobro hacia que la misma venga en estado PENDIENTE
//	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public CobroDto logicaAlGuardarErrorCobroErrorApropiacion(CobroDto cobroDto, UsuarioSesion usuario, boolean esReedicionParcial) throws NegocioExcepcion {
		String idUsuario = usuario.getIdUsuario();
		try {
			ShvCobEdCobro cobroModelo = obtenerModeloCobro(cobroDto.getIdCobro());
			CobroDto cobroDtoFinal = null;
			TareaDto tareaDto = null ;
			SociedadEnum sociedad = null;
			SistemaEnum sistema = null; 
			ShvWfWorkflow workflow = cobroModelo.getWorkflow();
			
			if (!Validaciones.isObjectNull(cobroDto.getIdTarea())) {
				 tareaDto = (TareaDto) tareaServicio.buscarTarea(cobroDto.getIdTarea());
				 sociedad = tareaDto.getSociedad();
				 sistema = tareaDto.getSistema();
			}
			
			if (esReedicionParcial) {
				//Lógica de Reedición Parcial
				cobroDtoFinal = reeditarCobroParcial(cobroDto, tareaDto);
			} 
			
			//TODO Hay que ver si el estado del cobro al reeditar un cobro nuevo parcialmente en algun momento pasa a COB_REEDITADO
			if (Estado.COB_ERROR_APROPIACION.equals(cobroDto.getEstado())) {
				
				//Se aplica Reedición Total a COB_ERROR_APROPIACION
				cobroDtoFinal = cobroDto;
			} 

			cobroDtoFinal.setListaComprobantes(cobroDto.getListaComprobantes());
			cobroDtoFinal.setListaComprobanteAplicacionManual(cobroDto.getListaComprobanteAplicacionManual());
			return cobroDtoFinal;
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}

	@Override
	public void simularCobro(Long idCobro) throws NegocioExcepcion {
		cobroBatchSimulacionServicio.simularCobroOnline(idCobro);
	}

	/**
	 * @author u573005, fabio.giaquinta, sprint 5
	 * @throws PersistenciaExcepcion 
	 */
	@Override
	public TransaccionesJsonResponse buscarTransacciones(Long idCobro, boolean simulacionBatchEnProceso) throws NegocioExcepcion, PersistenciaExcepcion {
		boolean simulacionOnlineOK = true;
		TransaccionesJsonResponse rta = new TransaccionesJsonResponse();
		boolean soloLecturaPantalla = false;

		CobroDto cobroAux = buscarCobro(idCobro);

		if (!Validaciones.isObjectNull(idCobro)) {
			VistaSoporteCobroTransaccionFiltro filtro = new VistaSoporteCobroTransaccionFiltro();
			filtro.setIdCobro(idCobro);

			List<VistaSoporteResultadoBusquedaCobroTransaccion> listaTransaccionesModelo = vistaSoporteServicio.obtenerTransaccionesCobro(filtro);
			List<CobroTransaccionDto> listaTransacciones = new ArrayList<CobroTransaccionDto>();

			if (Validaciones.isCollectionNotEmpty(listaTransaccionesModelo)) {
				// Obtengo el maximo de transacciones de la tabla de parametros
				Long maximoTransacciones = parametroServicio.getValorNumerico(Mensajes.COBROS_VALIDACION_CANTIDAD_TRANSACCIONES_MAXIMO_ONLINE_PERMITIDO);
				String segmentosTransladarIntereses = parametroServicio.getValorTexto(ConstantesCobro.COBROS_VALIDACION_SEGMENTO_OBLIGATORIO_TRASLADO_INTERESES_PROXIMA_FACTURA);

				// Busco el ultimo registro que ya viene ordenado de menor a mayor y obtengo en el ultimo numero transaccion la cantidad de transacciones 
				Long cantidadTransacciones = new Long(listaTransaccionesModelo.get(listaTransaccionesModelo.size()-1).getNumeroTransaccion());

				// Comparo con el maximo y mapeo el solo lectura por registro para que despues la grilla habilite por registro
				if (cantidadTransacciones > maximoTransacciones && simulacionBatchEnProceso) {
					rta.setDescripcionError(Mensajes.COBROS_AVISO_SIMULACION_BATCH);
					soloLecturaPantalla = true;
					rta.setSimulacionEnBatch(true);
				} else {
					rta.setSimulacionEnBatch(false);
					soloLecturaPantalla = false;
				}

				if (!Validaciones.isNullEmptyOrDash(segmentosTransladarIntereses) && Validaciones.isCollectionNotEmpty(listaTransaccionesModelo)){
					for (int i = 0; i < listaTransaccionesModelo.size();i++) {

						SistemaEnum sistemaDoc = listaTransaccionesModelo.get(i).getSistemaOrigenDocumento();
						SociedadEnum sociedadDoc = listaTransaccionesModelo.get(i).getSociedad();
						TipoComprobanteEnum tipoComprobante = listaTransaccionesModelo.get(i).getTipoComprobante();

						if(!Validaciones.isObjectNull(sistemaDoc) && !Validaciones.isObjectNull(sociedadDoc)){
							if (!SistemaEnum.MIC.equals(sistemaDoc) && !SistemaEnum.CALIPSO.equals(sistemaDoc)) {
									listaTransaccionesModelo.get(i).setQueHacerConIntereses(TratamientoInteresesEnum.TM);
									listaTransaccionesModelo.get(i).setPorcentajeABonificar(0);
									listaTransaccionesModelo.get(i).setImporteABonificar(BigDecimal.ZERO);
								
							}
						} else if (TipoMedioPagoEnum.DESISTIMIENTO.equals(listaTransaccionesModelo.get(i).getTipoMedioPago())){
							listaTransaccionesModelo.get(i-1).setQueHacerConIntereses(TratamientoInteresesEnum.SC);
							listaTransaccionesModelo.get(i-1).setPorcentajeABonificar(0);	
							listaTransaccionesModelo.get(i-1).setImporteABonificar(BigDecimal.ZERO);
							listaTransaccionesModelo.get(i-1).setEsTrasladarInteresesObligatorio(false);
						} else {
							for (String segmentoTransadarIntereses : segmentosTransladarIntereses.split(Constantes.SEMICOLON)) {
								if(segmentoTransadarIntereses.equals(listaTransaccionesModelo.get(i).getSegmentoAgenciaNegocio())){
									if(TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getDescripcion().equals(listaTransaccionesModelo.get(i-1).getDescripcionSubTipoDocumento())){
										listaTransaccionesModelo.get(i-1).setTrasladarIntereses(SiNoEnum.SI);
										listaTransaccionesModelo.get(i-1).setQueHacerConIntereses(TratamientoInteresesEnum.TM);
										listaTransaccionesModelo.get(i-1).setPorcentajeABonificar(0);
										listaTransaccionesModelo.get(i-1).setImporteABonificar(BigDecimal.ZERO);
										listaTransaccionesModelo.get(i-1).setEsTrasladarInteresesObligatorio(true);
									}
								}
							}
						}
					}
				}
				
				VistaSoporteResultadoBusquedaCobroTransaccion transAComparar = null;
				
				for (VistaSoporteResultadoBusquedaCobroTransaccion transaccion : listaTransaccionesModelo) {
					//se fija si hay error en la transaccion por el campo tipo mensaje estado, si alguna transaccion
					//tiene error deshabilito el boton imputar
					if(TipoMensajeEstadoEnum.ERR.equals(transaccion.getTipoMensajeTransaccion())
						|| TipoMensajeEstadoEnum.ERR.equals(transaccion.getTipoMensajeCredito())
						|| TipoMensajeEstadoEnum.ERR.equals(transaccion.getTipoMensajeDebito())) {

						simulacionOnlineOK = false;
					}
					
					CobroTransaccionDto transaccionDto = (CobroTransaccionDto) cobroOnlineTransaccionesMapeador.map(transaccion);
					// Si hay error de Simulacion o es simulacion bacth es de solo lectura			(0)
					transaccionDto.setSoloLecturaPantalla(soloLecturaPantalla);

					// Habilita o no habilita el check segun la fecha del cobro y fecha vto			(1)
					transaccionDto = this.calcularSiEsTransaccionTraladarInteresesFechas(transaccionDto);

					if (Validaciones.isObjectNull(transAComparar)) {
						transAComparar = transaccion;
						if (Validaciones.isObjectNull(transaccionDto.getApocopeGrupo())){
						transaccionDto.setApocopeGrupo(apocopeGrupo(transaccion, cobroAux.getIdSegmento(), cobroAux.getIdEmpresa()));
						}
					} else if (!transAComparar.getNumeroTransaccionFormateado().equals(transaccion.getNumeroTransaccionFormateado())) {
						transAComparar = transaccion;
						if (!Validaciones.isObjectNull(transaccion.getTipoComprobante())) {
							if (Validaciones.isObjectNull(transaccionDto.getApocopeGrupo())){
								transaccionDto.setApocopeGrupo(apocopeGrupo(transaccion, cobroAux.getIdSegmento(), cobroAux.getIdEmpresa()));
								} 
						}
					} else {
						if (Validaciones.isObjectNull(transaccionDto.getApocopeGrupo())){
							transaccionDto.setApocopeGrupo(apocopeGrupo(transAComparar, cobroAux.getIdSegmento(), cobroAux.getIdEmpresa()));
							}
						transaccionDto.setNumeroGrupo(transAComparar.getGrupo());
					}
					
					// Si es la componete factura
					if (!Validaciones.isObjectNull(transaccion.getTipoComprobante())) {
						// Si la transaccion de la factura tiene medios de pago que no trasladan intereses
						// Se anula la trasalacion de la factura
						transaccionDto = this.calcularSiEsTransaccionTraladarIntereses(transaccionDto, transaccion, listaTransaccionesModelo);
						if (
							!Validaciones.isNullEmptyOrDash(transaccionDto.getIdFactura()) &&
							!SistemaEnum.MIC.getDescripcion().equals(transaccionDto.getSistemaDoc()) &&
							!SistemaEnum.CALIPSO.getDescripcion().equals(transaccionDto.getSistemaDoc())
							
						) {
							transaccionDto.setMostrarAsteriscos(transaccionDto.isGeneraInteresesParamUso() ? SiNoEnum.SI: SiNoEnum.NO);
						} else {
							transaccionDto.setMostrarAsteriscos(SiNoEnum.NO);
						}
					} else if (
							!TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO.equals(transaccion.getTipoMedioPago()) &&
							!TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC.equals(transaccion.getTipoMedioPago())
						) {
							// Se desabilitan los campos de trasladar interes para tdos
							transaccionDto.setGeneraInteresesParamUso(false);
						}
					listaTransacciones.add(transaccionDto);
				}
			}

			try {
				Utilidad.guionesNull(listaTransacciones);
			} catch (ShivaExcepcion e) {
				Traza.error(getClass(), e.getMessage());
			}
			rta.setTransaccionesOK(simulacionOnlineOK);
			rta.setAaData(listaTransacciones);
		}
		rta.setImputable(this.validarEsImputable(idCobro));
		rta.setSimulablePorEstado(rta.isImputable());
		CobroJsonResponse rta2 = this.obtenerEstados(idCobro, false, "", "|");
		rta.getEstado().setEstadoDescripcion(rta2.getEstado().getEstadoDescripcion());
		rta.getEstado().setMarcaDescripcion(rta2.getEstado().getMarcaDescripcion());
		
		StringBuffer observacionesDocumentosCap = new StringBuffer();
		List<String> listaResultadoVerifacionDocumentosCap = vistaSoporteServicio.consultarResultadoVerificacionDocumentosCap(idCobro);
		for (String resultadoDocumentoCap : listaResultadoVerifacionDocumentosCap) {
			observacionesDocumentosCap.append(resultadoDocumentoCap);
			observacionesDocumentosCap.append(Constantes.CARRIAGE_RETURN);
		}
		rta.setObservacionesDocCap(observacionesDocumentosCap.toString());
		
		if (
			MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR.descripcion().equals(rta2.getEstado().getMarcaDescripcion()) ||
			MarcaEnum.SIMULACION_ONLINE_FINALIZADA_CON_ERROR.descripcion().equals(rta2.getEstado().getMarcaDescripcion())
		) {
			simulacionOnlineOK = false;
			rta.setTransaccionesOK(simulacionOnlineOK);
		}
		rta.setSuccess(true);
		return rta;
	}

	private boolean componetesMedioPagoTrasladaIntereses(VistaSoporteResultadoBusquedaCobroTransaccion transaccion, List<VistaSoporteResultadoBusquedaCobroTransaccion> transacciones) {
		for (int i = 0; i < transacciones.size(); i++) {
			if (
				transaccion.getNumeroTransaccionFormateado().equals(transacciones.get(i).getNumeroTransaccionFormateado()) &&
				transaccion.getTipoComprobante().equals(transacciones.get(i).getTipoComprobante())
			) {
				if (SiNoEnum.SI.equals(transacciones.get(i + 1).getGeneraInteresesParamUso())) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	private CobroTransaccionDto calcularSiEsTransaccionTraladarIntereses(
		CobroTransaccionDto transaccion,
		VistaSoporteResultadoBusquedaCobroTransaccion transaccionVista,
		List<VistaSoporteResultadoBusquedaCobroTransaccion> vistas
	) {
		if (transaccion.isGeneraInteresesParamUso()) {
			// Si la transaccion genera intereses, esto esta seteado en la base
			if (!Validaciones.isNullEmptyOrDash(transaccion.getTipoDocumento())) {
				// si es una factura
				if (!Validaciones.isObjectNull(transaccion.isFechaCobroMenorIgualFechaVto()) && !transaccion.isFechaCobroMenorIgualFechaVto()) {
					if (!Validaciones.isObjectNull(transaccion.getNoHabilitadoTrasladarIntereses()) && transaccion.getNoHabilitadoTrasladarIntereses()) {
						transaccion.setGeneraInteresesParamUso(false);
					}
				} else {
					transaccion.setGeneraInteresesParamUso(false);
				}
				if (!Validaciones.isObjectNull(transaccion.isGeneraInteresesParamUso()) && transaccion.isGeneraInteresesParamUso()) {
					if (!componetesMedioPagoTrasladaIntereses(transaccionVista, vistas)) {
						transaccion.setGeneraInteresesParamUso(false);
					}
				}
			}
		}
		return transaccion;
	}
	
	private String apocopeGrupo(VistaSoporteResultadoBusquedaCobroTransaccion vista, String segmento, String empresa) throws NegocioExcepcion, PersistenciaExcepcion {
		PerfilFiltro filtro = new PerfilFiltro();
		String apocopeGrupo = Constantes.EMPTY_STRING;
		
		if (!Validaciones.isObjectNull(vista.getSociedad()) && !Validaciones.isObjectNull(vista.getSistemaOrigenDocumento()) && !ConstantesCobro.GRUPO_INTERNO_SHIVA.equals(vista.getGrupo())) {
			filtro.setSociedad(vista.getSociedad().name());
			filtro.setSistema(vista.getSistemaOrigenDocumento().name());
			filtro.setSegmento(segmento);
			filtro.setEmpresa(empresa);
			filtro.setTipoTarea(TipoTareaEnum.COB_CONF_APLIC_MANUAL.name());
			apocopeGrupo = vista.getSociedad().getApocope();
			apocopeGrupo += Constantes.SIGNO_MENOS;
			apocopeGrupo += TipoImputacionEnum.MANUAL.getApocope();
			apocopeGrupo += Constantes.SIGNO_MENOS;
			
			TipoPerfilEnum perfil = paramRespWfTareaServicio.buscarPerfil(filtro);; 
			
			apocopeGrupo += TipoPerfilEnum.getApocopeResponsableApliManual(perfil);
			
			
		} else {
			apocopeGrupo = SociedadEnum.TELECOM.getApocope();
			apocopeGrupo += Constantes.SIGNO_MENOS;
			apocopeGrupo += TipoImputacionEnum.AUTOMATICA.getApocope();
			
		}
		return apocopeGrupo;
	}
	
	@Override
	public TransaccionesJsonResponse buscarTransaccionesApliManual(CobroDto cobro) throws PersistenciaExcepcion, NegocioExcepcion {
		VistaSoporteCobroTransaccionFiltro filtro = new VistaSoporteCobroTransaccionFiltro();
		filtro.setIdCobro(cobro.getIdCobro());
		filtro.setSistema(cobro.getSistemaDestinoDescripcion());
		filtro.setSociedad(cobro.getSociedad());
		
		VistaSoporteResultadoBusquedaCobroTransaccion transAComparar = null;
		
		List<VistaSoporteResultadoBusquedaCobroTransaccion> listaTransaccionesModelo = vistaSoporteServicio.obtenerTransaccionesCobro(filtro);
		TransaccionesJsonResponse rta = new TransaccionesJsonResponse();
		List<CobroTransaccionDto> transaccionesApliManual = new ArrayList<CobroTransaccionDto>();

		int i = 0;
		for (VistaSoporteResultadoBusquedaCobroTransaccion transaccion : listaTransaccionesModelo) {
			if (TipoMedioPagoEnum.DESISTIMIENTO.equals(listaTransaccionesModelo.get(i).getTipoMedioPago())){
				listaTransaccionesModelo.get(i-1).setQueHacerConIntereses(TratamientoInteresesEnum.SC);
				listaTransaccionesModelo.get(i-1).setPorcentajeABonificar(0);	
				listaTransaccionesModelo.get(i-1).setImporteABonificar(BigDecimal.ZERO);
				listaTransaccionesModelo.get(i-1).setEsTrasladarInteresesObligatorio(false);
			}
			i++;
			CobroTransaccionDto transaccionDto = (CobroTransaccionDto) cobroOnlineTransaccionesMapeador.map(transaccion);
			if (Validaciones.isObjectNull(transAComparar)) {
				transAComparar = transaccion;
				if (Validaciones.isObjectNull(transaccionDto.getApocopeGrupo())){
					transaccionDto.setApocopeGrupo(apocopeGrupo(transaccion, cobro.getSegmento(), cobro.getEmpresa()));
				} 
			} else if (!transAComparar.getNumeroTransaccionFormateado().equals(transaccion.getNumeroTransaccionFormateado())) {
				transAComparar = transaccion;
				if (!Validaciones.isObjectNull(transaccion.getTipoComprobante())) {
					if (Validaciones.isObjectNull(transaccionDto.getApocopeGrupo())){
						transaccionDto.setApocopeGrupo(apocopeGrupo(transaccion, cobro.getSegmento(), cobro.getEmpresa()));
					} 
				}
			} else {
				if (Validaciones.isObjectNull(transaccionDto.getApocopeGrupo())){
					transaccionDto.setApocopeGrupo(apocopeGrupo(transAComparar, cobro.getSegmento(), cobro.getEmpresa()));
					} 
				transaccionDto.setNumeroGrupo(transAComparar.getGrupo());
			}
			
			// Habilita o no habilita el check segun la fecha del cobro y fecha vto			(1)
			transaccionDto = this.calcularSiEsTransaccionTraladarInteresesFechas(transaccionDto);
			// Si es la componete factura
			if (!Validaciones.isObjectNull(transaccion.getTipoComprobante())) {
				// Si la transaccion de la factura tiene medios de pago que no trasladan intereses
				// Se anula la trasalacion de la factura
				transaccionDto = this.calcularSiEsTransaccionTraladarIntereses(transaccionDto, transaccion, listaTransaccionesModelo);
				if (
					!Validaciones.isNullEmptyOrDash(transaccionDto.getIdFactura()) &&
					!SistemaEnum.MIC.getDescripcion().equals(transaccionDto.getSistemaDoc()) &&
					!SistemaEnum.CALIPSO.getDescripcion().equals(transaccionDto.getSistemaDoc())
					
				) {
					transaccionDto.setMostrarAsteriscos(transaccionDto.isGeneraInteresesParamUso() ? SiNoEnum.SI: SiNoEnum.NO);
				} else {
					transaccionDto.setMostrarAsteriscos(SiNoEnum.NO);
				}
			} else if (
					!TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_CALIPSO.equals(transaccion.getTipoMedioPago()) &&
					!TipoMedioPagoEnum.DEBITO_PROXIMA_FACTURA_MIC.equals(transaccion.getTipoMedioPago())
				) {
					// Se desabilitan los campos de trasladar interes para tdos
					transaccionDto.setGeneraInteresesParamUso(false);
				}
			transaccionesApliManual.add(transaccionDto);
			
		}
		try {
			Utilidad.guionesNull(transaccionesApliManual);
		} catch (ShivaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
		}
		rta.setAaData(transaccionesApliManual);
		return rta;
	}
	
	public void exportarCobro(HttpServletResponse response, Long idCobro) throws NegocioExcepcion {
		try {
			ByteArrayOutputStream salida = new ByteArrayOutputStream();
			HSSFWorkbook workbook = exportarCobro(idCobro);

			workbook.write(salida);

			response.setContentType(XLS_CONTENT_TYPE);
			response.setContentLength(salida.size());
			response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + "ID Operación Cobro " + workbook.getSheetName(0) + xls);

			FileCopyUtils.copy(salida.toByteArray(), response.getOutputStream());

		} catch (IOException | PersistenciaExcepcion e) {
			new NegocioExcepcion(e.getMessage(),e);	
		}
	}
	
	public HSSFWorkbook exportarCobro(Long idCobro,Estado estadoCobro) throws NegocioExcepcion, PersistenciaExcepcion {
		
		boolean simulacionBatch = false;
		CobroDto cobroDto = buscarCobro(idCobro);
		String idCobroStr = idCobro.toString();
		
		if (!Validaciones.isObjectNull(estadoCobro)){
			cobroDto.setEstado(estadoCobro);
		}
		
		for (CobroDebitoDto debito : cobroDto.getDebitos()) {
			// Completo los atributos 'opeAsocAnalista' y 'marcaPagoCompensacionEnProcesoShiva
			this.setearAtributosPorConsulta(debito, idCobroStr);
		}
		for (CobroCreditoDto credito : cobroDto.getCreditos()) {
			// Completo los atributos 'opeAsocAnalista' y 'marcaPagoCompensacionEnProcesoShiva`
			this.setearAtributosPorConsulta(credito, idCobroStr);
		}
		
		List<MarcaEnum> ultimaMarca = this.obtenerUltimasMarcas(cobroDto.getIdCobro(), null, true);
		
		if (!ultimaMarca.isEmpty()) {
			//Como obtengo la ultima marca
			cobroDto.setMarcas(ultimaMarca);
			if (MarcaEnum.SIMULACION_BATCH_EN_PROCESO.equals(ultimaMarca.get(0))) {
				simulacionBatch = true;
			}
		}
		
		// Si el cobro tiene una operacion masiva, busco el nombre del archivo. Brian
		if(!Validaciones.isNullOrEmpty(cobroDto.getIdOperacionMasiva())) {
			ShvMasOperacionMasivaArchivo archivo = operacionMasivaServicio.buscarArchivoOperacionMasivaModelo(new Long(cobroDto.getIdOperacionMasiva()));
			cobroDto.setNombreArchivo(archivo.getNombreArchivo());
		}

		TransaccionesJsonResponse transacciones = this.buscarTransacciones(cobroDto.getIdCobro(), simulacionBatch);
		transacciones.setAaData(this.formatearImportesTransacciones(transacciones.getAaData(), cobroDto.getMonedaOperacion()));
		
		Set <CobroTransaccionDto> setTransacciones = new LinkedHashSet<CobroTransaccionDto>(transacciones.getAaData());
		cobroDto.setTransacciones(setTransacciones);
		cobroDto.setObservacionAnterior(this.obtenerObservacionHistorialYObservacionTarea(cobroDto));
		
		// Ordeno los documentos caps y seteo los colores de renglon
		this.prepararDocumentosCapParaSuVisualizacion(cobroDto);
				
		HSSFWorkbook workbook = exportacionDetalleCobro.generarExcelDetalleCobro(cobroDto);
	
		return workbook;
	}

	/**
	 * Genera Excel de un cobro
	 * @author u579607
	 * @throws PersistenciaExcepcion 
	 */
	public HSSFWorkbook exportarCobro(Long idCobro) throws NegocioExcepcion, PersistenciaExcepcion {
				
		HSSFWorkbook workbook = exportarCobro(idCobro,null);
	
		return workbook;
	}
	/**
	  * Valida si el cobro es imputable en base al estado del cobro
	  * @author u578936 Max Uehara
	  * @param idCobro
	  * @return 
	  * @throws NegocioExcepcion
	  */
	public boolean validarEsImputable(Long idCobro) throws NegocioExcepcion {
		CobroDto cobro = this.buscarCobro(idCobro);
		if (cobro == null) {
			throw new NegocioExcepcion("No existe el cobro cob el id");
		}
		List<Estado> estados = Arrays.asList(
			new Estado[] {
				Estado.COB_EN_EDICION,
				Estado.COB_RECHAZADO
			}
		);
		if (estados.contains(cobro.getEstado())) {
			return true;
		}
		return false;
	}
	/**
	  * Genero el checkSum de un cobro. Se utiliza el SH1-512 
	  * Los datos tenidos en cuenta son los Debitos, Creditos, MediosDePago y TratamientoDeLaDiferencia 
	  * @author u578936 Max Uehara
	  * @param cobroModelo
	  * @return String de 128 caracteres
	 * @throws Exception 
	  */
	public String generarCheckSum(Long idCobro) throws NegocioExcepcion {
		ShvCobEdCobro cobroModelo = null;

		cobroModelo = obtenerModeloCobro(idCobro);
		ShvCobEdCobroDatosSerializados cobroSerializado = null;
		try {
			cobroSerializado = new ShvCobEdCobroDatosSerializados();
			// genero los wrapper de los debitos
			// Elimino datos variables como las PK y las relaciones bidireccional
			for (ShvCobEdDebito debito : cobroModelo.getDebitos()) {
				CobroDebitoDto debitoDto = (CobroDebitoDto)debitoMapeador.map(debito);
				cobroSerializado.getDebitos().add(
					new ShvCobEdDebitoWrapper(
						(ShvCobEdDebito)Utilidad.clonarObjeto(debito),
						debitoDto.getIdPantalla()
					)
				);
			}
			// genero los wrapper de los otros debitos
			// Elimino datos variables como las PK y las relaciones bidireccional
			for (ShvCobEdOtrosDebito otrosDebito : cobroModelo.getOtrosDebitos()) {
				CobroOtrosDebitoDto otrosDebitoDto = (CobroOtrosDebitoDto)otrosDebitoMapeador.map(otrosDebito);
				cobroSerializado.getOtrosDebitos().add(
					new ShvCobEdOtroDebitoWrapper(
						(ShvCobEdOtrosDebito)Utilidad.clonarObjeto(otrosDebito),
						otrosDebitoDto.getIdOtrosDebitoPantalla()
					)
				);
			}
			// genero los wrapper de los creditos
			// Elimino datos variables como las PK y las relaciones bidireccional
			for (ShvCobEdCredito credito : cobroModelo.getCreditos()) {
				CobroCreditoDto creditoDto = (CobroCreditoDto)creditoMapeador.map(credito);
				cobroSerializado.getCreditos().add(
					new ShvCobEdCreditoWrapper(
						(ShvCobEdCredito) Utilidad.clonarObjeto(credito),
						creditoDto.getIdPantalla()
					)
				);
			}
			// genero los wrapper de los medios de pago;
			for (ShvCobEdOtrosMedioPago medio: cobroModelo.getMediosPagos()) {
				cobroSerializado.getMedios().add(
					new ShvCobEdOtrosMedioPagoWrapper(
						(ShvCobEdOtrosMedioPago)Utilidad.clonarObjeto(medio)
					)
				);
			}
			Collections.sort(cobroSerializado.getMedios(), new ComparatorShvCobEdOtrosMedioPagoWrapper());
			if (cobroModelo.getTratamientoDiferencia() != null) {
				cobroSerializado.setDiferencia(
					new ShvCobEdTratamientoDiferenciaWrapper(
						(ShvCobEdTratamientoDiferencia)Utilidad.clonarObjeto(cobroModelo.getTratamientoDiferencia())
					)
				);
			}
			cobroSerializado.setSegmento(cobroModelo.getSegmento().getIdSegmento());
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		
		String hash = "";
		try {
			hash = Utilidad.checkSumSHA512(Utilidad.serializarObjeto(cobroSerializado));
		} catch (IOException e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		return hash;
	}
	/**
	 * Persisto el checkSum en el cobro
	 * @param idCobro
	 * @return void
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void persistirCheckSum(Long idCobro, String hash, String usuarioModificacion, Date fechaModificacion) throws NegocioExcepcion {
		try {
			ShvCobEdCobro cobroModelo = obtenerModeloCobro(idCobro);
			cobroModelo.setCheckSumAlSimular(hash);
			cobroModelo.setUsuarioUltimaModificacion(usuarioModificacion);
			cobroModelo.setFechaUltimaModificacion(fechaModificacion);
			cobroOnlineDao.guardarCobro(cobroModelo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage());
		}
	}

	/**
	 * Valida si la simulacion realizada sobre el cobro. representa a los datos actuales del cobro
	 * retorna false. si los datos actuales del cobro fueron modificados en relacion a los del momento de
	 * la simulacion. Los datos tomados son debitos, creditos, medios de pago y tratamientodeladiferencia
	 * @param idCobro
	 * @return boolean.
	 * @throws NegocioExcepcion
	 */
	public boolean validarSimulacion(long idCobro, String userSession) throws NegocioExcepcion {
		ShvCobEdCobro cobroModelo = null;
		boolean valido = false;

		cobroModelo = obtenerModeloCobro(idCobro);
		if (Validaciones.isNullOrEmpty(cobroModelo.getCheckSumAlSimular())) {
			valido = true;
		} else {
			// Calculo el valor del checkSum del cobro con los datos actuales
			String checkSum = this.generarCheckSum(idCobro);
			// Comparo con el checkSum ak momento de Simular
			valido = checkSum.equals(cobroModelo.getCheckSumAlSimular());
			if (!valido) {
				 workflowService.agregarWorkflowMarca(
					cobroModelo.getWorkflow(),
					userSession,
					MarcaEnum.SIMULACION_VACIA
				);
			}
		}
		return valido;
	}

	public List<MarcaEnum> obtenerUltimasMarcas(long idCobro, ShvCobEdCobro cobroModelo, boolean soloUltima) throws NegocioExcepcion {
		if (Validaciones.isObjectNull(cobroModelo)) {
			cobroModelo = obtenerModeloCobro(idCobro);
		}
		Set<ShvWfWorkflowMarca> marcas = cobroModelo.getWorkflow().getWorkflowEstado().getWorkflowMarcas();
		TreeSet<ShvWfWorkflowMarca> workflowMarcas = new TreeSet<ShvWfWorkflowMarca>(new ComparatorShvWfWorkflowMarca());
		workflowMarcas.addAll(marcas);

		List<MarcaEnum> listaMarcas = new ArrayList<MarcaEnum>();

		if (soloUltima) {
			if (!workflowMarcas.isEmpty()) {
				listaMarcas.add(workflowMarcas.first().getMarca());
			}
		} else {
			boolean primerMarcaSimulacion = false;
			boolean primerMarcaRechazado = false;

			for (ShvWfWorkflowMarca marca : workflowMarcas) {
				if (marca.getMarca().name().startsWith("SIMULACION") && !primerMarcaSimulacion) {
					listaMarcas.add(marca.getMarca());
					primerMarcaSimulacion = true;
				}
				if (marca.getMarca().name().startsWith("RECHAZADO") && !primerMarcaRechazado) {
					listaMarcas.add(marca.getMarca());
					primerMarcaRechazado = true;
				}
				if (primerMarcaRechazado && primerMarcaSimulacion) {
					break;
				}
			}
		}
		return listaMarcas;
	}
	public CobroJsonResponse obtenerEstados(long idCobro, boolean soloUltima, String prefijo, String separador) throws NegocioExcepcion {
		ShvCobEdCobro cobroModelo = obtenerModeloCobro(idCobro);
		List<MarcaEnum> marcas = this.obtenerUltimasMarcas(idCobro, cobroModelo, true);
		StringBuffer str = new StringBuffer();
		for (MarcaEnum marca : marcas) {
			if (!Estado.COB_PROCESO_APLICACION_EXTERNA.equals(cobroModelo.getWorkflow().getEstado())
					&& !Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(cobroModelo.getWorkflow().getEstado())
					&& !Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(cobroModelo.getWorkflow().getEstado())){
				
				str.append(prefijo);
				str.append(marca.descripcion());
				str.append(separador);
			}
		}
		if (str.length() > 0) {
			str.deleteCharAt(str.length() - 1);
		}
		CobroJsonResponse rta = new CobroJsonResponse();
		rta.getEstado().setEstadoDescripcion(cobroModelo.getWorkflow().getWorkflowEstado().getEstado().descripcion());
		rta.getEstado().setMarcaDescripcion(str.toString());
		return rta;
	}

	@Override
	public CobroTransaccionDto calcularSiEsTransaccionTraladarInteresesFechas(CobroTransaccionDto transaccion) throws NegocioExcepcion {
		Date fechaCobro = null;
		Date fechaVenc = null;
		Date fecha2doVenc = null;
		Boolean salida = false;

		if (!Validaciones.isObjectNull(transaccion.getSistemaDoc())) {
			if (!Validaciones.isNullEmptyOrDash(transaccion.getFechaCobro()) &&
				SistemaEnum.MIC.equals(transaccion.getSistemaDoc())
			) {
				try {
					if (Validaciones.isObjectNull(transaccion.isCobrarAl2doVenc()) || SiNoEnum.NO.equals(transaccion.isCobrarAl2doVenc())) {
						//salida = false;
						salida = false;
					} else {
						fechaCobro = Utilidad.parseDatePickerString(transaccion.getFechaCobro());
						if (!Validaciones.isNullEmptyOrDash(transaccion.getFechaVenc())) {
							fechaVenc = Utilidad.parseDatePickerString(transaccion.getFechaVenc());
						}
						if (!Validaciones.isNullEmptyOrDash(transaccion.getFecha2doVenc())) {
							fecha2doVenc = Utilidad.parseDatePickerString(transaccion.getFecha2doVenc());
						}
						if (
							Validaciones.isObjectNull(fechaCobro) ||
							Validaciones.isObjectNull(fechaVenc) ||
							Validaciones.isObjectNull(fecha2doVenc)
						) {
							salida = false;
						} else {
							if (fechaCobro.after(fechaVenc) && fechaCobro.before(fecha2doVenc)) {
								salida = true;
							} else if (fechaCobro.after(fechaVenc) && fechaCobro.equals(fecha2doVenc)) {
								salida = true;
							}
						}
					}
				} catch (ParseException e) {
					throw new NegocioExcepcion(e.getMessage());
				}
			}
	
			if (transaccion.getEsTrasladarInteresesObligatorio()) { 
				salida = false;
			}
			transaccion.setFechaCobroMenorIgualFechaVto(
				Utilidad.verificarSiFech1EsMenosIgualFecha2(transaccion.getFechaCobro(), transaccion.getFechaVenc())
			);
			transaccion.setNoHabilitadoTrasladarIntereses(salida);
		}
		
		return transaccion;
	}
	/**
	 * 
	 * @param cobro
	 * @return
	 */
	@Override
	public TotalAcumuladoresTransacciones calculaTotalInteresesTransacciones(CobroDto cobro) {
		TotalAcumuladoresTransacciones acu = new TotalAcumuladoresTransacciones();

		for (CobroTransaccionDto transaccion: cobro.getTransacciones()) {
			BigDecimal importeABonificar = BigDecimal.ZERO;
			MonedaEnum moneda = null;

			if (!Validaciones.isNullEmptyOrDash(transaccion.getImporteABonificar())) {
				importeABonificar = Utilidad.stringToBigDecimal(transaccion.getImporteABonificar());
				
				if (!Validaciones.isNullEmptyOrDash(transaccion.getSistemaDoc())) {
					moneda = MonedaEnum.getEnumBySigno2(transaccion.getMoneda());
				} else {
					moneda = MonedaEnum.getEnumBySigno2(transaccion.getMonedaMedioPago());
				}
				BigDecimal intereses = null;
				if (!Validaciones.isNullEmptyOrDash(transaccion.getIntereses())) {
					intereses = Utilidad.stringToBigDecimal(transaccion.getIntereses().replace(moneda.getSigno2(), ""));
				} else {
					intereses = new BigDecimal(0);
				}
				if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento())) {
					acu.totalReintegro(moneda, intereses);
				} else if (transaccion.getTrasladarIntereses()) {
					acu.totalTraslados(moneda, intereses);
				} else if (
					!TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) &&
					!TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) &&
					!TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) &&
					!TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) &&
					!TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.getDescripcion().equals(transaccion.getTipoDocumento())
				) {
					acu.totalBonificados(moneda, importeABonificar);
					acu.totalTraslados(moneda, intereses, importeABonificar);
				}
			}
		}
		return acu;
	}
	
	/**
	 * Busca el cobro en la tabla SHV_COB_ED_COBRO asociado al idCobro y setea la fecha y usuario de imputacion si el cobro esta cobrado.
	 * Ademas, independiente del estado setea la fecha y usuario ultima modificacion y lo guarda en la base.
	 * @param idOperacion
	 * @param FechaImputacionYUltimaMdoficiacion
	 * @throws NegocioExcepcion
	 */
	public void setearUsuarioYFechaImputacionYUltimaMdoficiacion(Long idOperacion, Date fechaImputacionYUltimaMdoficiacion, boolean cobroCobrado) throws NegocioExcepcion {
		try {
			ShvCobEdCobroSimplificado cobroEdicion = cobroOnlineDao.buscarCobroSimplificado(idOperacion);
			
			//Cuidado con los cobros viejos
			if (cobroEdicion!=null) {
				
				String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
				if(cobroCobrado){
					cobroEdicion.setFechaImputacion(fechaImputacionYUltimaMdoficiacion);
					cobroEdicion.setUsuarioImputacion(usuarioBatch);
				}
				
				cobroEdicion.setFechaUltimaModificacion(fechaImputacionYUltimaMdoficiacion);
				cobroEdicion.setUsuarioUltimaModificacion(usuarioBatch);
				cobroOnlineDao.guardarCobroSimplificado(cobroEdicion);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		
	}
	/**
	 * Setea a blanco los mensajes de las transacciones de un cobro
	 * @param idCobro
	 * @throws NegocioExcepcion 
	 */
	public void blanquearMensajesTransacciones(Long idCobro) throws NegocioExcepcion {
		ShvCobCobro cobroModelo = null;
		try {
			cobroModelo = cobroDao.buscarCobro(idCobro);
			for (ShvCobTransaccion transaccion : cobroModelo.getOperacion().getTransacciones()) {
				if (!Validaciones.isObjectNull(transaccion.getFactura())) {
					ShvCobFactura factura = transaccion.getFactura();
					factura.setTipoMensajeEstado(null);
					factura.setMensajeEstado(null);
				}
				if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {
					ShvCobTratamientoDiferencia tratamientoDiferencia = transaccion.getTratamientoDiferencia();
					tratamientoDiferencia.setTipoMensajeEstado(null);
					tratamientoDiferencia.setMensajeEstado(null);
				}
				for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {
					medioPago.setTipoMensajeEstado(null);
					medioPago.setMensajeEstado(null);
				}
				transaccion.setMensajeEstado(null);
				transaccion.setTipoMensajeEstado(null);
			}
			cobroDao.modificar(cobroModelo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	/**
	 * Este metodo se utiliza para mostra habilitado o no un campo checkBox de la transacciones
	 * @param transaccion
	 * @return
	 */
	
//	public int validarHabilitacionCampo(CobroTransaccionDto transaccion) {
//		BigDecimal intereses = null;
//		if (!Validaciones.isNullEmptyOrDash(transaccion.getIntereses())) {
//			intereses = Utilidad.stringToBigDecimal(transaccion.getIntereses());	
//		} else {
//			intereses = Utilidad.stringToBigDecimal("0");
//		}
//		if (transaccion.getSoloLecturaPantalla()) {
//			return ConstantesCobro.TRANSACCION_SOLO_LECTURA;//=4;
//		} else {
//			if (
//				TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
//				TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
//				TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
//				TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
//				TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.getDescripcion().equals(transaccion.getTipoDocumento()) ||
//				TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.getDescripcion().equals(transaccion.getTipoDocumento())	||
//				TipoTratamientoDiferenciaEnum.SALDO_A_COBRAR.getDescripcion().equals(transaccion.getTipoDocumento())
//			) {
//				//es reintegro NO proxima factura
//				return ConstantesCobro.TRANSACCION_NO_PROXIMA_FAC; //=0;
//			} else if (
//				TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
//				TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.getDescripcion().equals(transaccion.getTipoMedioPago())
//			) {
//				//es reintegro proxima factura
//				if (intereses.compareTo(new BigDecimal(0)) > 0) {
//					return ConstantesCobro.TRANSACCION_PROXIMA_FAC_CON_INTERESES;//=1;
//				}else{
//					return ConstantesCobro.TRANSACCION_DISABLED; //=3;
//				}
//			} else if(intereses.compareTo(new BigDecimal(0)) > 0){	
//				if ("0".equals(transaccion.getColorLetraRegistro())) {
//		//			interes mayor a cero y no es diferencia de cambio
//					return ConstantesCobro.TRANSACCION_CON_INTERESES_NO_DIFERENCIA_CAMBIO; //2
//				} else {
//		//			interes mayor a cero y moneda en dolares, o sea es el registro informativo de calipso
//					return ConstantesCobro.TRANSACCION_DISABLED; //=3;
//				}
//			} else {
//		//		interes menor a cero y no es reintegro
//				return ConstantesCobro.TRANSACCION_DISABLED; //=3;
//			}
//		}
//	}
	public int validarHabilitacionCampo(CobroTransaccionDto transaccion) {
		BigDecimal intereses = null;
		int salida = ConstantesCobro.TRANSACCION_NO_PROXIMA_FAC;

		if (!Validaciones.isNullEmptyOrDash(transaccion.getIntereses())) {
			intereses = Utilidad.stringToBigDecimal(transaccion.getIntereses());	
		} else {
			intereses = Utilidad.stringToBigDecimal("0");
		}
		
		
		if (transaccion.getSoloLecturaPantalla()) {
			salida = ConstantesCobro.TRANSACCION_SOLO_LECTURA;//=4;
		} else {
			if (transaccion.isGeneraInteresesParamUso()) {

				if (
					TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
					TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.getDescripcion().equals(transaccion.getTipoMedioPago())
				) {
					//es reintegro proxima factura
					if (intereses.compareTo(new BigDecimal(0)) > 0) {
						salida = ConstantesCobro.TRANSACCION_PROXIMA_FAC_CON_INTERESES;//=1;
					} else {
						salida = ConstantesCobro.TRANSACCION_DISABLED; //=3;
					}
				} else if(intereses.compareTo(new BigDecimal(0)) > 0){	
					if ("0".equals(transaccion.getColorLetraRegistro())) {
			//			interes mayor a cero y no es diferencia de cambio
						salida = ConstantesCobro.TRANSACCION_CON_INTERESES_NO_DIFERENCIA_CAMBIO; //2
						if (SiNoEnum.SI.equals(transaccion.getMostrarAsteriscos())) {
							salida = ConstantesCobro.TRANSACCION_ASTERISCOS;
						}
					} else {
			//			interes mayor a cero y moneda en dolares, o sea es el registro informativo de calipso
						salida = ConstantesCobro.TRANSACCION_DISABLED; //=3;
					}
				} else {
			//		interes menor a cero y no es reintegro
					salida =  ConstantesCobro.TRANSACCION_DISABLED; //=3;
					if (SiNoEnum.SI.equals(transaccion.getMostrarAsteriscos())) {
						salida = ConstantesCobro.TRANSACCION_ASTERISCOS;
					}
				}
			}
		}
			return salida;
	}
	/**
	 * u579607 Brian.Keller
	 */
	public boolean puedeRevertir (Long idCobro) throws PersistenciaExcepcion {

		boolean rta = true;
		
		ShvCobDescobro descobro = descobroDao.buscarDescobroPorIdCobro(idCobro);
		if(!Validaciones.isObjectNull(descobro)){
			rta = false;
		}
		return rta;
	}
	
	@Override
	public List<ClienteDto> listarClientesCobro (Long idCobro) throws NegocioExcepcion {
		
		List<ClienteDto> listaClientesCobro = new ArrayList <ClienteDto>();
		List<ShvCobEdCliente> listaEdClientes;
		ClienteDto cobroCliente = null;
		
		try {
			
			listaEdClientes = cobroOnlineDao.listarClientesDelCobroOnline(idCobro);
			
			for (ShvCobEdCliente lista : listaEdClientes) {
				cobroCliente = (ClienteDto) clienteMapeador.map(lista);
				listaClientesCobro.add(cobroCliente);
			}
			
			return listaClientesCobro;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/


	public List<ShvCobEdDebito> getListaDebitosOriginal() {
		return listaDebitosOriginal;
	}

	public void setListaDebitosOriginal(List<ShvCobEdDebito> listaDebitosOriginal) {
		this.listaDebitosOriginal = listaDebitosOriginal;
	}

	public ICobroOnlineDebitoDao getCobroDebitoDao() {
		return cobroDebitoDao;
	}

	public void setCobroDebitoDao(ICobroOnlineDebitoDao cobroDebitoDao) {
		this.cobroDebitoDao = cobroDebitoDao;
	}

	public CobroOnlineDebitoMapeador getDebitoMapeador() {
		return debitoMapeador;
	}

	public void setDebitoMapeador(
			CobroOnlineDebitoMapeador debitoMapeador) {
		this.debitoMapeador = debitoMapeador;
	}

	public IVistaSoporteServicio getVistaSoporteServicio() {
		return vistaSoporteServicio;
	}

	public void setVistaSoporteServicio(IVistaSoporteServicio vistaSoporteServicio) {
		this.vistaSoporteServicio = vistaSoporteServicio;
	}

	public ICobroBatchSimulacionServicio getCobroBatchSimulacionServicio() {
		return cobroBatchSimulacionServicio;
	}

	public void setCobroBatchSimulacionServicio(
			ICobroBatchSimulacionServicio cobroBatchSimulacionServicio) {
		this.cobroBatchSimulacionServicio = cobroBatchSimulacionServicio;
	}

	public CobroOnlineTransaccionesMapeador getCobroOnlineTransaccionesMapeador() {
		return cobroOnlineTransaccionesMapeador;
	}

	public void setCobroOnlineTransaccionesMapeador(
			CobroOnlineTransaccionesMapeador cobroOnlineTransaccionesMapeador) {
		this.cobroOnlineTransaccionesMapeador = cobroOnlineTransaccionesMapeador;
	}

	public ICobroBatchServicio getCobroBatchServicio() {
		return cobroBatchServicio;
	}

	public void setCobroBatchServicio(ICobroBatchServicio cobroBatchServicio) {
		this.cobroBatchServicio = cobroBatchServicio;
	}

	/**
	 * @return the creditoMapeador
	 */
	public CobroOnlineCreditoMapeador getCreditoMapeador() {
		return creditoMapeador;
	}

	/**
	 * @param creditoMapeador the creditoMapeador to set
	 */
	public void setCreditoMapeador(CobroOnlineCreditoMapeador creditoMapeador) {
		this.creditoMapeador = creditoMapeador;
	}

	@Override
	public String getAnalistaCobroDebito(String idDebitoCobro, String idCobro)
			throws NegocioExcepcion {
		
		List<VistaSoporteResultadoCobroCreditoDebito> resultado = 
				vistaSoporteServicio.obtenerDebitosPorReferencia(idDebitoCobro, idCobro);
		
		if (Validaciones.isCollectionNotEmpty(resultado)) {
			String campoOperacionAnalista = resultado.get(0).getOperacionAnalista();
			if (!Validaciones.isNullOrEmpty(campoOperacionAnalista)) {
				campoOperacionAnalista = campoOperacionAnalista.replaceAll(",", ",<br>"); 
			}
			return campoOperacionAnalista;
		}
		return null;
	}

	@Override
	public String getAnalistaCobroCredito(String idCreditoCobro, String idCobro)
			throws NegocioExcepcion {
		
		List<VistaSoporteResultadoCobroCreditoDebito> resultado = 
				vistaSoporteServicio.obtenerCreditosPorReferencia(idCreditoCobro, idCobro);
		
		if (Validaciones.isCollectionNotEmpty(resultado)) {
			String campoOperacionAnalista = resultado.get(0).getOperacionAnalista();
			if (!Validaciones.isNullOrEmpty(campoOperacionAnalista)) {
				campoOperacionAnalista = campoOperacionAnalista.replaceAll(",", ",<br>"); 
			}
			return campoOperacionAnalista;
		}
		return null;
	}
	@Override
	public String getAnalistaCobroDocumentoCap(String idCapCobro, String idCobro) throws NegocioExcepcion {
		List<VistaSoporteResultadoCobroCreditoDebito> resultado = vistaSoporteServicio.obtenerDocumentoCapPorReferencia(idCapCobro, idCobro);

		if (Validaciones.isCollectionNotEmpty(resultado)) {
			String campoOperacionAnalista = resultado.get(0).getOperacionAnalista();
			if (!Validaciones.isNullOrEmpty(campoOperacionAnalista)) {
				campoOperacionAnalista = campoOperacionAnalista.replaceAll(",", ",<br>"); 
			}
			return campoOperacionAnalista;
		}
		return null;
	}
	public boolean esPersistirObservacionEnEstado(Estado estado) {
		List<Estado> estadoObservacion = Arrays.asList(
			new Estado[] {
				Estado.COB_PENDIENTE,
					Estado.COB_PROCESO,
					Estado.COB_COBRADO,
					Estado.COB_CONFIRMADO_CON_ERROR,
					Estado.COB_ERROR_CONFIRMACION,
					Estado.COB_ERROR_APROPIACION,
					Estado.COB_ERROR_COBRO,
					Estado.COB_ERROR_CONFIRMACION,
					Estado.COB_ERROR_DESAPROPIACION
			});
		
		return estadoObservacion.contains(estado);
	}
	

	public boolean esPersistirObservacionEnEstado(String estadoDesc) {
		Estado estado = null;
		estado = Estado.getEnumByDescripcion(estadoDesc);
		if (Validaciones.isObjectNull(estado)) {
			return false;
		}
		return esPersistirObservacionEnEstado(estado);
	}
	
	public ShvCobEdCobro pasarObservacionEnEstado(ShvCobEdCobro cobro) throws NegocioExcepcion {
		ShvWfWorkflowEstado shvWorkflowEstado = cobro.getWorkflow().getWorkflowEstado();
		StringBuffer str = new StringBuffer();
		UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUid(cobro.getUsuarioUltimaModificacion());
		if (Validaciones.isNullOrEmpty(shvWorkflowEstado.getDatosModificados())) {
			str.append(Utilidad.EMPTY_STRING);
		} else {
			str.append(shvWorkflowEstado.getDatosModificados());
		}
		if (str.length() > 0) {
			str.append(Constantes.RETORNO_HTML);
		}
		str.append(Utilidad.formatDateAndTimeFull(new Date()));
		str.append(" (");
		
		str.append(usuarioLdapAnalista.getTuid());
		str.append(") ");
		str.append(usuarioLdapAnalista.getNombreCompleto());
		str.append(Constantes.RETORNO_HTML);
		str.append(cobro.getObservacion());
		shvWorkflowEstado.setDatosModificados(
			 str.toString()
		);
		
		cobro.setObservacion(Utilidad.EMPTY_STRING);
		return cobro;
	}
	
	public String obtenerObseHistorial(CobroDto cobroDto, ShvCobEdCobro cobroModelo) throws NegocioExcepcion {
		StringBuffer str = new StringBuffer();
		try {
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			String usuarioNombreBatch = parametroServicio.getValorTexto(Constantes.USUARIO_NOMBRE_BATCH);

			if (Validaciones.isObjectNull(cobroModelo)) {
				cobroModelo = this.obtenerModeloCobro(cobroDto.getIdCobro());
			}
			List<ShvWfWorkflowEstadoHist> historial = cobroModelo.getWorkflow().getListaWorkflowEstadoHistOrdenadaPorFecha();

			//creo esta variable para no procesar el estado 'COB_PROCESO' varias veces
			boolean cobroProceso = false;
			for (ShvWfWorkflowEstadoHist historico : historial) {
				if(Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(historico.getEstado())){
					cobroProceso = false;
				}
				
				if (!Estado.COB_PEND_PROCESAR_MIC.equals(historico.getEstado())
					&& !Estado.COB_PENDIENTE_MIC.equals(historico.getEstado())){
					
					if (!Estado.COB_PROCESO.equals(historico.getEstado())){
						str.append(this.returnObservacion(historico, usuarioBatch, usuarioNombreBatch));
						
					} else
					if (Estado.COB_PROCESO.equals(historico.getEstado()) && !cobroProceso){
						str.append(this.returnObservacion(historico, usuarioBatch, usuarioNombreBatch));
						cobroProceso = true;
					}
					
				}
			}

			if (!Validaciones.isObjectNull(cobroModelo.getWorkflow().getShvWfWorkflowEstado())) {
				if (cobroModelo.getWorkflow().getShvWfWorkflowEstado().iterator().hasNext()) {
//					if (str.length() > 0) {
//						str.append(Constantes.RETORNO_HTML);
//					}
					str.append(
						this.returnObservacion(
							cobroModelo.getWorkflow().getShvWfWorkflowEstado().iterator().next(),
							usuarioBatch,
							usuarioNombreBatch
					));
				}
			}
			
		} catch (LdapExcepcion e) {
			throw new NegocioExcepcion(e); 
		}
		return str.toString();
	}
	/**
	 * Metodo encargado de obtener las observaciones del cobro tanto del historial como de las tareas (si contiene aplicacion manual)
	 * @param cobroDto
	 * @param cobroModelo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String obtenerObservacionHistorialYObservacionTarea(CobroDto cobroDto) throws NegocioExcepcion {
		
		StringBuffer str = new StringBuffer();
		ShvCobEdCobro cobroModelo = this.obtenerModeloCobro(cobroDto.getIdCobro());
		
		try {
			
			List<ShvWfWorkflowEstadoHist> historial = cobroModelo.getWorkflow().getListaWorkflowEstadoHistOrdenadaPorFecha();
			
			List<ShvWfTarea> listaTareas = new ArrayList<ShvWfTarea>();
			
			List<HistoricoDto> listaObservaciones = new ArrayList<HistoricoDto>();
			
			listaTareas = tareaDao.buscarTareaAPLManualParaObservaciones(cobroModelo.getWorkflow().getIdWorkflow());
			
			//creo esta variable para no procesar el estado 'COB_PROCESO' varias veces
			boolean cobroProceso = false;
			for (ShvWfWorkflowEstadoHist historico : historial) {
				
				
				if(Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(historico.getEstado())){
					cobroProceso = false;
				}
				
				if (!Estado.COB_PEND_PROCESAR_MIC.equals(historico.getEstado())
					&& !Estado.COB_PENDIENTE_MIC.equals(historico.getEstado())
					&& !Estado.COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(historico.getEstado())
					&& !Estado.COB_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_PROCESAR_MIC.equals(historico.getEstado())
					&& !Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(historico.getEstado())
					&& !Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC.equals(historico.getEstado())){
					
					
					if (!Estado.COB_PROCESO.equals(historico.getEstado())){
						String observaciones = returnObservacion(historico.getDatosModificados(), historico.getFechaModificacion(), historico.getUsuarioModificacion(), true);
						
						if (!Validaciones.isNullEmptyOrDash(observaciones)) {
							listaObservaciones.add(
									new HistoricoDto(observaciones, historico.getDatosModificados(), historico.getFechaModificacion(), historico.getUsuarioModificacion()));
							
						}	
					} else
					if (Estado.COB_PROCESO.equals(historico.getEstado()) && !cobroProceso){
						String observaciones = returnObservacion(historico.getDatosModificados(), historico.getFechaModificacion(), historico.getUsuarioModificacion(), true);
						
						if (!Validaciones.isNullEmptyOrDash(observaciones)) {
							listaObservaciones.add(
									new HistoricoDto(observaciones, historico.getDatosModificados(), historico.getFechaModificacion(), historico.getUsuarioModificacion()));
							
						}
						cobroProceso = true;
					}
					
				}
			
			}
			for (ShvWfTarea tarea : listaTareas) {
				String observaciones = returnObservacion(tarea.getObservaciones(), tarea.getFechaEjecucion(), tarea.getUsuarioEjecucion(), false);
				
				if (!Validaciones.isNullEmptyOrDash(observaciones)) {
					listaObservaciones.add(
						new HistoricoDto(observaciones, null, tarea.getFechaEjecucion(), tarea.getUsuarioEjecucion()));
				}
				
			}

			Collections.sort(listaObservaciones, new ComparatorOrdenHistoricoObservaciones());
			for(HistoricoDto historico: listaObservaciones){
				str.append(historico.getObservacion());
				
			}
		} catch (LdapExcepcion e) {
			throw new NegocioExcepcion(e); 
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
		
		return str.toString();
		
	}

	private String returnObservacion(
		String datosModificados,
		Date fecha,
		String usuarioModificacion,
		String usuarioBatch,
		String usuarioNombreBatch
	) throws LdapExcepcion {
		StringBuffer str = new StringBuffer();
		datosModificados = Utilidad.mostrarDatosModificados(
			datosModificados,
			Utilidad.DATOS_MODIFICADOS_SOLO_DATOS
		);

		if (!Validaciones.isNullOrEmpty(datosModificados)) {
			str.append(Utilidad.formatDateAndTimeFull(fecha));
			str.append(" (");
			if (usuarioBatch.equalsIgnoreCase(usuarioModificacion)) {
				str.append(usuarioBatch);
				str.append(") ");
				str.append(usuarioNombreBatch);
			} else {
				UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUid(usuarioModificacion);
				if (usuarioLdapAnalista != null) {
					str.append(usuarioLdapAnalista.getTuid());
					str.append(") ");
					str.append(usuarioLdapAnalista.getNombreCompleto());
				}
			}
			str.append(Constantes.RETORNO_HTML);
			if (!Validaciones.isNullOrEmpty(datosModificados)) {
				str.append(datosModificados);
			}
			str.append(Constantes.RETORNO_HTML);
		}
		return str.toString();
	}
	/**
	 * 
	 * @param datosModificados
	 * @param fecha
	 * @param usuarioModificacio
	 * @return
	 * @throws LdapExcepcion
	 */
	private String returnObservacion(String datosModificados, Date fecha, String usuarioModificacion, boolean eliminarInformacionWorkflow) 
			throws LdapExcepcion, NegocioExcepcion {
		
		StringBuffer str = new StringBuffer();
		
		if (eliminarInformacionWorkflow) {
			datosModificados = Utilidad.mostrarDatosModificados(datosModificados, Utilidad.DATOS_MODIFICADOS_SOLO_DATOS);
		}

		String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
		String usuarioImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION);
		String usuarioRecepcion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_RECEPCION);
		String usuarioSap = parametroServicio.getValorTexto(Constantes.USUARIO_SAP);
		
		String usuarioNombreBatch = parametroServicio.getValorTexto(Constantes.USUARIO_NOMBRE_BATCH);
		String usuarioNombreImputacion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_IMPUTACION_NOMBRE);;
		String usuarioNombreRecepcion = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH_COBROS_RECEPCION_NOMBRE);;
		String usuarioNombreSap = parametroServicio.getValorTexto(Constantes.USUARIO_SAP_NOMBRE);;
			
		if (!Validaciones.isNullOrEmpty(datosModificados)) {
			str.append(Utilidad.formatDateAndTimeFull(fecha));
			str.append(" (");
			if (usuarioBatch.equalsIgnoreCase(usuarioModificacion)) {
				str.append(usuarioBatch);
				str.append(") ");
				str.append(usuarioNombreBatch);
			}else if (usuarioImputacion.equalsIgnoreCase(usuarioModificacion)){
				str.append(usuarioImputacion);
				str.append(") ");
				str.append(usuarioNombreImputacion);
			}else if(usuarioRecepcion.equalsIgnoreCase(usuarioModificacion)){
				str.append(usuarioRecepcion);
				str.append(") ");
				str.append(usuarioNombreRecepcion);
			}else if(usuarioSap.equalsIgnoreCase(usuarioModificacion)){
				str.append(usuarioSap);
				str.append(") ");
				str.append(usuarioNombreSap);
			}else {
				UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUid(usuarioModificacion);
				if (usuarioLdapAnalista != null) {
					str.append(usuarioLdapAnalista.getTuid());
					str.append(") ");
					str.append(usuarioLdapAnalista.getNombreCompleto());
				}
			}
			str.append(Constantes.RETORNO_HTML);
			if (!Validaciones.isNullOrEmpty(datosModificados)) {
				str.append(datosModificados);
			}
			str.append(Constantes.RETORNO_HTML);
		}
		return str.toString();
	}
	
	private String returnObservacion(ShvWfWorkflowEstadoHist wfEstado,String usuarioBatch, String usuarioNombreBatch) throws LdapExcepcion {
		return this.returnObservacion(
			wfEstado.getDatosModificados(),
			wfEstado.getFechaModificacion(),
			wfEstado.getUsuarioModificacion(),
			usuarioBatch,
			usuarioNombreBatch
		);
	}
	private String returnObservacion(ShvWfWorkflowEstado wfEstado,String usuarioBatch, String usuarioNombreBatch) throws LdapExcepcion {
		return this.returnObservacion(
			wfEstado.getDatosModificados(),
			wfEstado.getFechaModificacion(),
			wfEstado.getUsuarioModificacion(),
			usuarioBatch,
			usuarioNombreBatch
		);
	}
	/**
	 * Busca un ShvCobEdCliente por IdCobro y IdCreditoOrigen.
	 */
	public ShvCobEdCliente buscarClientePorIdCobroYIdClienteLegado(Long idCobro, Long idClienteLegado) throws NegocioExcepcion {
		try {
			return cobroClienteDao.buscarClientePorIdCobroYIdClienteLegado(idCobro,idClienteLegado);
			
		}catch(PersistenciaExcepcion e){
			throw new NegocioExcepcion(e.getMessage(),e);
		}	
	}
	/**
	 * Agrega el $ al importe a cobrar
	 * @param debitos
	 * @return
	 */
	@Override
	public Set<CobroDebitoDto> formatearImporteACobrar(Set<CobroDebitoDto> debitos) {
		
		for (CobroDebitoDto debito : debitos) {
			
			debito.setImpACobrar(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(debito.getImpACobrar())),2,MonedaEnum.getEnumByName(debito.getMonedaImporteCobrar()).getSigno2()));
			
			if(!Validaciones.isNullEmptyOrDash(debito.getSaldo1erVencMonOrigen())){
				debito.setSaldo1erVencMonOrigen(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(debito.getSaldo1erVencMonOrigen())),2, debito.getMoneda()));
			}
			
			if(!Validaciones.isNullEmptyOrDash(debito.getImp1erVenc())){
				//chequear q viene en este monedaimportaacobrar
				debito.setImp1erVenc(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(debito.getImp1erVenc())),2, debito.getMoneda()));
			}
			
			if (!Validaciones.isNullEmptyOrDash(debito.getImp2doVenc())){
				debito.setImp2doVenc(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(debito.getImp2doVenc())),2,debito.getMoneda()));
			}
			
			if (!Validaciones.isNullEmptyOrDash(debito.getSaldoPesificado())){
				debito.setSaldoPesificado(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(debito.getSaldoPesificado())),2));
			}
			
			if (!Validaciones.isNullEmptyOrDash(debito.getImp3rosTransf())){
				debito.setImp3rosTransf(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(debito.getImp3rosTransf())),2));
			}
			
		}
		
		return debitos;
	}
	
	/**
	 * Agrega la moneda correspondiente en cada importe.
	 * @param creditos
	 * @return
	 */
	@Override
	public Set<CobroCreditoDto> formatearImportesCredito(Set<CobroCreditoDto> creditos) {
		
		for (CobroCreditoDto credito : creditos) {
			
			credito.setImporteAUtilizar(Utilidad.formatCurrency(Utilidad.stringToBigDecimal(credito.getImporteAUtilizar()),2,MonedaEnum.getEnumByName(credito.getMonedaImporteUtilizar()).getSigno2()));
			
			if(!Validaciones.isNullEmptyOrDash(credito.getSaldoMonOrigen())){
				credito.setSaldoMonOrigen(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(credito.getSaldoMonOrigen())),2, credito.getMoneda()));
			}
			
			if(!Validaciones.isNullEmptyOrDash(credito.getImpMonOrigen())){
				//chequear q viene en este monedaimportaacobrar
				credito.setImpMonOrigen(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(credito.getImpMonOrigen())),2, credito.getMoneda()));
			}
			
			if (!Validaciones.isNullEmptyOrDash(credito.getImpPesificado())){
				credito.setImpPesificado(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(credito.getImpPesificado())),2));
			}
			
			if (!Validaciones.isNullEmptyOrDash(credito.getSaldoPesificado())){
				credito.setSaldoPesificado(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(credito.getSaldoPesificado())),2));
			}
			
		}
		
		return creditos;
	}
	
	/**
	 * Agrega la moneda correspondiente en cada importe.
	 * @param creditos
	 * @return
	 */
	@Override
	public List<CobroTransaccionDto> formatearImportesTransacciones(List<CobroTransaccionDto> transacciones, String monedaOperacion) {
		
		for (CobroTransaccionDto transaccion : transacciones) {
			
			//factura
			
			if(!Validaciones.isNullEmptyOrDash(transaccion.getImporte())){
				transaccion.setImporte(Utilidad.formatCurrency(Utilidad.stringToBigDecimal(transaccion.getImporte()),2,monedaOperacion));
			}
			
			if (!Validaciones.isNullEmptyOrDash(transaccion.getImporteAplicadoFechaEmisionMonedaOrigen())){
				transaccion.setImporteAplicadoFechaEmisionMonedaOrigen(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(transaccion.getImporteAplicadoFechaEmisionMonedaOrigen())),2,transaccion.getMoneda()));
			}
			
			//medio de pago
			
			if(!Validaciones.isNullEmptyOrDash(transaccion.getImporteMedioPago())){
				transaccion.setImporteMedioPago(Utilidad.formatCurrency(Utilidad.stringToBigDecimal(transaccion.getImporteMedioPago()),2,monedaOperacion));
			}
			
			if (!Validaciones.isNullEmptyOrDash(transaccion.getImporteAplicadoFechaEmisionMonedaOrigenMedioPago())){
				transaccion.setImporteAplicadoFechaEmisionMonedaOrigenMedioPago(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(transaccion.getImporteAplicadoFechaEmisionMonedaOrigenMedioPago())),2,transaccion.getMonedaMedioPago()));
			}
			
			//comun
			//logica hasta q este desarrollada la moneda del medio de pago
			//if (!Validaciones.isNullEmptyOrDash(transaccion.getIntereses())){
			//	if(!Validaciones.isNullEmptyOrDash(transaccion.getTipoDocumento())) {
			//		transaccion.setIntereses(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(transaccion.getIntereses())),2,transaccion.getMoneda()));
			//	}else {
			//		transaccion.setIntereses(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(transaccion.getIntereses())),2, transaccion.getMonedaMedioPago()));
			//	}
			//}
			
			if (!Validaciones.isNullEmptyOrDash(transaccion.getImporteABonificar())){
				if(!Validaciones.isNullEmptyOrDash(transaccion.getTipoDocumento())) {
					transaccion.setImporteABonificar(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(transaccion.getImporteABonificar())),2,transaccion.getMoneda()));
				}else {
					transaccion.setImporteABonificar(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(transaccion.getImporteABonificar())),2,transaccion.getMonedaMedioPago()));
				}
			}
		}
		
		return transacciones;
	}
	
	public Set<CobroDebitoDto> formatearReclamoYMigradoEnOrigen(Set<CobroDebitoDto> debitos) {
		for (CobroDebitoDto debito : debitos) {
			
			if (Validaciones.isObjectNull(debito.getMarcaReclamoDescripcion())){
				debito.setMarcaReclamoDescripcion(SiNoEnum.NO.getDescripcion());
			}
			
			if (Validaciones.isNullOrEmpty(debito.getMigradoOrigen())){
				debito.setMigradoOrigen(SiNoEnum.NO.getDescripcion());
				
			}
			
		}
		
		return debitos;
	}
	public boolean obtenerMarcaReversionDeCobroEdicion(DocumentoGestionableDTO dto) throws NegocioExcepcion {
		QueryMarcaEnum queryEnum = null;
		boolean salida = false;
		if (dto instanceof CobroDebitoDto) {
			CobroDebitoDto debito = (CobroDebitoDto) dto;
			queryEnum = QueryMarcaEnum.DESCOBRO_EDICION_ERROR_ERROR_PRIMER_DESCOBRO_DEBITO_QUERY;
			salida = vistaSoporteServicio.obtenerMarca(debito.getIdPantalla(), queryEnum);
		} else if (dto instanceof CobroCreditoDto) {
			CobroCreditoDto credito = (CobroCreditoDto) dto;
			queryEnum = QueryMarcaEnum.DESCOBRO_EDICION_ERROR_ERROR_PRIMER_DESCOBRO_CREDITO_QUERY;
			salida = vistaSoporteServicio.obtenerMarca(credito.getIdPantalla(), queryEnum);
		} else if (dto instanceof DocumentoCapDto) {
			DocumentoCapDto cap = (DocumentoCapDto) dto;
			queryEnum = QueryMarcaEnum.DESCOBRO_EDICION_ERROR_ERROR_PRIMER_DESCOBRO_CREDITO_QUERY;
			salida = vistaSoporteServicio.obtenerMarca(cap.getIdPantalla(), queryEnum);
		}
		return salida;
	}
	@Override
	public boolean obtenerMarcaCreditoEnCobrosPendienteProceso(String idCreditoCobro) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerMarcaCreditoEnCobrosPendienteProceso(idCreditoCobro);
	}
	@Override
	public boolean obtenerMarcaDebitoEnCobrosPendienteProceso(String idDebitoCobro) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerMarcaDebitoEnCobrosPendienteProceso(idDebitoCobro);
	}
	@Override
	public boolean obtenerMarcaCreditoEnDescobrosPendienteProceso(String idCreditoCobro) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerMarca(idCreditoCobro, QueryMarcaEnum.DESCOBRO_PENDIENTE_PROCESO_CREDITO_QUERY);
	}
	@Override
	public boolean obtenerMarcaDebitoEnDescobrosPendienteProceso(String idDebitoCobro) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerMarca(idDebitoCobro, QueryMarcaEnum.DESCOBRO_PENDIENTE_PROCESO_DEBITO_QUERY);
	}
	
	@Override
	public CobroDebitoDto setearAtributosPorConsulta(CobroDebitoDto debitoDto, String idCobro) throws NegocioExcepcion {
		// verifico si el documento esta en otra Operacion Shiva
		String operacionAnalista = this.getAnalistaCobroDebito(debitoDto.getIdDebitoPantalla(), idCobro);
		debitoDto.setOpeAsocAnalista(operacionAnalista);
		if (!Validaciones.isNullOrEmpty(operacionAnalista)) {
			debitoDto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.SI);
		} else {
			debitoDto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.NO);
		}
		//
		boolean marcaCobroPendienteProceso = this.obtenerMarcaDebitoEnCobrosPendienteProceso(debitoDto.getIdDebitoPantalla());
		if (marcaCobroPendienteProceso) {
			debitoDto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.SI);
		} else {
			debitoDto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.NO);
		}
		//                                           
		boolean marcaDesCobroPendienteProceso = this.obtenerMarcaDebitoEnDescobrosPendienteProceso(debitoDto.getIdDebitoPantalla());
		if (marcaDesCobroPendienteProceso) {
			debitoDto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.SI);
		} else {
			debitoDto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.NO);
		}
		
		boolean marcaReversionDeCobroEdicion = this.obtenerMarcaReversionDeCobroEdicion(debitoDto);
		if (marcaReversionDeCobroEdicion) {
			debitoDto.setMarcaReversionDeCobroEdicion(SiNoEnum.SI);
		} else {
			debitoDto.setMarcaReversionDeCobroEdicion(SiNoEnum.NO);
		}
		return debitoDto;
	}

	@Override
	public boolean obtenerMarcaCreditoEnCobrosPendienteProcesoByNumeroRefMic(Long nroReferenciaMic) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerMarca(nroReferenciaMic, QueryMarcaEnum.COBRO_PENDIENTE_PROCESO_CREDITO_QUERY_MIC_BY_NUMERO_REF_MIC);
	}
	@Override
	public boolean obtenerMarcaDebitoEnCobrosPendienteProcesoByNumeroRefMic(Long nroReferenciaMic) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerMarca(nroReferenciaMic, QueryMarcaEnum.COBRO_PENDIENTE_PROCESO_DEBITO_QUERY_MIC_BY_NUMERO_REF_MIC);
	}
	@Override
	public boolean obtenerMarcaCreditoEnDescobrosPendienteProcesoByNumeroRefMic(Long nroReferenciaMic) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerMarca(nroReferenciaMic, QueryMarcaEnum.DESCOBRO_PENDIENTE_PROCESO_CREDITO_QUERY_MIC_BY_NUMERO_REF_MIC);
	}
	@Override
	public boolean obtenerMarcaDebitoEnDescobrosPendienteProcesoByNumeroRefMic(Long nroReferenciaMic) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerMarca(nroReferenciaMic, QueryMarcaEnum.DESCOBRO_PENDIENTE_PROCESO_DEBITO_QUERY_MIC_BY_NUMERO_REF_MIC);
	}

	@Override
	public CobroCreditoDto setearAtributosPorConsulta(CobroCreditoDto creditoDto, String idCobro) throws NegocioExcepcion {
		String operacionAnalista = this.getAnalistaCobroCredito(creditoDto.getIdCreditoPantalla(), idCobro);
		creditoDto.setOpeAsocAnalista(operacionAnalista);
		if (!Validaciones.isNullOrEmpty(operacionAnalista)) {
			creditoDto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.SI);
		} else {
			creditoDto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.NO);
		}
		boolean marcaCobroPendienteProceso = this.obtenerMarcaCreditoEnCobrosPendienteProceso(creditoDto.getIdCreditoPantalla());
		if (marcaCobroPendienteProceso) {
			creditoDto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.SI);
		} else {
			creditoDto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.NO);
		}
		boolean marcaDesCobroPendienteProceso = this.obtenerMarcaCreditoEnDescobrosPendienteProceso(creditoDto.getIdCreditoPantalla());
		if (marcaDesCobroPendienteProceso) {
			creditoDto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.SI);
		} else {
			creditoDto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.NO);
		}
		boolean marcaReversionDeCobroEdicion = this.obtenerMarcaReversionDeCobroEdicion(creditoDto);
		if (marcaReversionDeCobroEdicion) {
			creditoDto.setMarcaReversionDeCobroEdicion(SiNoEnum.SI);
		} else {
			creditoDto.setMarcaReversionDeCobroEdicion(SiNoEnum.NO);
		}
		return creditoDto;
	}
	
	public SalidaCalipsoCargosWS actualizarInteresesDebitoAProxCalipso(Integer idMedioPago, String acuerdoFacturacion) throws NegocioExcepcion{
		
		List<ShvCobMedioPagoDebitoProximaFacturaCalipso> debitoProximaFacturaCalipso;
		SalidaCalipsoCargosWS respuesta = null;
		
		try {
			debitoProximaFacturaCalipso = medioPagoDao.buscarMedioPagoDebProxCalipsoPorIdMedioPago(idMedioPago);
			EntradaCalipsoCargosWS entrada = cobroBatchSimulacionServicio.prepararSimulacionGeneracionCargosDebitoCalipso(debitoProximaFacturaCalipso.get(0));
			
			entrada.getDetalleCargo().setAcuerdoFacturacion(acuerdoFacturacion);
			entrada.getDetalleCargo().setImporteBonificacionIntereses(null);
			
			
			respuesta = clienteCalipsoServicio.generarCargoCalipso(entrada, TipoProcesoEnum.COBRO);
			
			
			return respuesta;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public MicRespuestaGeneracionCargoSalida actualizarInteresesDebitoAProxMic(Integer idMedioPago, String acuerdoFacturacion) throws NegocioExcepcion{
		
		List<ShvCobMedioPagoDebitoProximaFacturaMic> debitoProximaFacturaMic;
		MicRespuestaGeneracionCargoSalida respuesta = null;
		
		try {
			debitoProximaFacturaMic = medioPagoDao.buscarMedioPagoDebProxMicPorIdMedioPago(idMedioPago);
			MicTransaccionGeneracionCargosDto entradaMicGeneracionCargoJms = cobroBatchSimulacionServicio.prepararSimulacionGeneracionCargosDebitoMic(debitoProximaFacturaMic.get(0));
			
			entradaMicGeneracionCargoJms.getDetalleGeneracionCargos().setAcuerdoFacturacion(acuerdoFacturacion);
			
			
			respuesta = micJmsSyncServicio.simularCargoCredito(entradaMicGeneracionCargoJms);
			
			
			return respuesta;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public List<ComprobanteDto> listarComprobantes(Long idCobro) throws NegocioExcepcion {
		List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
		List<ShvCobEdCobroAdjunto> listaAdjuntos = new ArrayList<ShvCobEdCobroAdjunto>();

		try {
			listaAdjuntos = cobroOnlineDao.buscarAdjuntosDelCobroOnline(idCobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		if( Validaciones.isCollectionNotEmpty(listaAdjuntos)){
			for (ShvCobEdCobroAdjunto cobroAdjunto : listaAdjuntos) {
				ComprobanteDto comprobante = new ComprobanteDto();
				comprobante.setId(cobroAdjunto.getPk().getDocumentoAdjunto().getIdValorAdjunto());
				comprobante.setIdComprobante(cobroAdjunto.getPk().getDocumentoAdjunto().getIdValorAdjunto());
				comprobante.setNombreArchivo(cobroAdjunto.getPk().getDocumentoAdjunto().getNombreArchivo());
				comprobante.setDescripcionArchivo(cobroAdjunto.getPk().getDocumentoAdjunto().getDescripcion());
				comprobante.setDocumento(cobroAdjunto.getPk().getDocumentoAdjunto().getArchivoAdjunto());
				comprobante.setUsuarioCreacion(cobroAdjunto.getPk().getDocumentoAdjunto().getUsuarioCreacion());
				comprobante.setFechaAlta(cobroAdjunto.getPk().getDocumentoAdjunto().getFechaCreacion());
				comprobante.setMotivoAdjunto(cobroAdjunto.getPk().getMotivoAdjunto().name());
				listaComprobantes.add(comprobante);
			}
		}
		
//		if (Validaciones.isCollectionNotEmpty(listaAdjuntos)) {
//			for (ShvDocDocumentoAdjunto docAdjunto : listaAdjuntos) {
//				ComprobanteDto comprobante = new ComprobanteDto();
//				comprobante.setId(docAdjunto.getIdValorAdjunto());
//				comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());
//				comprobante.setNombreArchivo(docAdjunto.getNombreArchivo());
//				comprobante.setDescripcionArchivo(docAdjunto.getDescripcion());
//				comprobante.setDocumento(docAdjunto.getArchivoAdjunto());
//				comprobante.setUsuarioCreacion(docAdjunto.getUsuarioCreacion());
//				comprobante.setFechaAlta(docAdjunto.getFechaCreacion());
//				listaComprobantes.add(comprobante);
//			}
//		}
		return listaComprobantes;
	}
	
	public BuilderConsultaSap consultaDocumentosCap(BuilderConsultaSap builder) throws NegocioExcepcion, ParseException {
		builder.setearSalidaSapConsultaPartidasWS(sapConsultaPartidasWS.consultarPartidas(builder.creearConsultaPartidasWS()));
		return builder;
	}
	
		
	public BuilderConsultaSap consultaProveedoresSap(BuilderConsultaSap builder) throws NegocioExcepcion {
		builder.setearSalidaConsultaProveedores(sapS4ConsultaProveedoresWS.consultarProveedores(builder.creearConsultaProveedores()));
		return builder;
	}
	public BuilderConsultaSap determinarGestiobabilidadSap(BuilderConsultaSap builder, String idCobro) throws NegocioExcepcion {
		
		List<DocumentoCapDto> listaPadres = builder.getListaDosCap();
		List<DocumentoCapDto> listaHijos = builder.getListaVinculados();
		
		for (DocumentoCapDto dto : listaPadres) {
			dto = this.setearAtributosPorConsulta(dto, idCobro);
			dto.setSemaforo(this.determinarGestionabilidadDeSap(dto));
		}
		
		for (DocumentoCapDto dto : listaHijos) {
			dto = this.setearAtributosPorConsulta(dto, idCobro);
			dto.setSemaforo(this.determinarGestionabilidadDeSap(dto));
		}
		
		for (DocumentoCapDto dtoPadre : listaPadres) {
			
			String numPadre = dtoPadre.getCodigoSociedad() + "-" + dtoPadre.getNroDocumentoSap() + "-" + dtoPadre.getEjercicioFiscalDocSAP() + "-" + dtoPadre.getPosicionDocSAP();
			
			for (DocumentoCapDto dtoHijos : listaHijos) {
			
				String numHijo = dtoHijos.getCodigoSociedad() + "-" + dtoHijos.getNumeroDocSAPVinculado() + "-" 
				+ dtoHijos.getEjercicioFiscalDocSAPVinculado() + "-" + dtoHijos.getPosicionDocSAPVinculado();
			
				if (!Validaciones.isNullOrEmpty(numPadre) && !Validaciones.isNullOrEmpty(numHijo)
						&& numPadre.equals(numHijo)){
					
					if (SemaforoGestionabilidadEnum.ROJO.getDescripcion().equals(dtoPadre.getSemaforo().getSemaforo())) {
						dtoHijos.setSemaforo(dtoPadre.getSemaforo());
						
					} else if (SemaforoGestionabilidadEnum.ROJO.getDescripcion().equals(dtoHijos.getSemaforo().getSemaforo())) {
						dtoPadre.setSemaforo(dtoHijos.getSemaforo());
						
					} else if (SemaforoGestionabilidadEnum.NARANJA.getDescripcion().equals(dtoPadre.getSemaforo().getSemaforo())) {
						dtoHijos.setSemaforo(dtoPadre.getSemaforo());
						
					} else if (SemaforoGestionabilidadEnum.NARANJA.getDescripcion().equals(dtoHijos.getSemaforo().getSemaforo())) {
						dtoPadre.setSemaforo(dtoHijos.getSemaforo());
						
					} else if (SemaforoGestionabilidadEnum.AMARILLO.getDescripcion().equals(dtoPadre.getSemaforo().getSemaforo())) {
						dtoHijos.setSemaforo(dtoPadre.getSemaforo());
						
					} else if (SemaforoGestionabilidadEnum.AMARILLO.getDescripcion().equals(dtoHijos.getSemaforo().getSemaforo())) {
						dtoPadre.setSemaforo(dtoHijos.getSemaforo());
					}
				}
			}
		}
		
		builder.setListaDosCap(listaPadres);
		builder.getListaDosCap().addAll(listaHijos);
		return builder;
	}
	
	@Override
	public GestionabilidadDto determinarGestionabilidadDeSap(DocumentoCapDto documentoCaDto) throws NegocioExcepcion {
//		GestionabilidadDto dto;
		
//		if (documentoCaDto.getPosicionDocSAP() == 2) { 
//			dto = new GestionabilidadDto("NO", SemaforoGestionabilidadEnum.ROJO.getDescripcion(), "Testeo");
//		} else {
//			dto = new GestionabilidadDto("Si", SemaforoGestionabilidadEnum.VERDE.getDescripcion(), "Testeo");
//			
//		}
		
		SemaforoGestionabilidadDocumentoCap semaforo = new SemaforoGestionabilidadDocumentoCap();

		return semaforo.determinarGetionabilidad(documentoCaDto);
	}
	@Override
	public DocumentoCapDto setearAtributosPorConsulta(DocumentoCapDto documentoCapDto, String idCobro) throws NegocioExcepcion {
		String operacionAnalista = this.getAnalistaCobroDocumentoCap(documentoCapDto.getIdDocumentoCapReferencia(), idCobro);

		//documetoCapDto.setOpeAsocAnalista(operacionAnalista);
		if (!Validaciones.isNullOrEmpty(operacionAnalista)) {
			documentoCapDto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.SI);
		} else {
			documentoCapDto.setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(SiNoEnum.NO);
		}
		boolean marcaCobroPendienteProceso = this.obtenerMarcaDocumentoEnCobrosPendienteProceso(documentoCapDto.getIdDocumentoCapReferencia());
		if (marcaCobroPendienteProceso) {
			documentoCapDto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.SI);
		} else {
			documentoCapDto.setMarcaPagoCompensacionEnProcesoShiva(SiNoEnum.NO);
		}
		boolean marcaDesCobroPendienteProceso = this.obtenerMarcaDocumentoEnDescobrosPendienteProceso(documentoCapDto.getIdDocumentoCapReferencia());
		if (marcaDesCobroPendienteProceso) {
			documentoCapDto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.SI);
		} else {
			documentoCapDto.setMarcaReversionDeCobroProcesoPendiente(SiNoEnum.NO);
		}
		boolean marcaReversionDeCobroEdicion = this.obtenerMarcaReversionDeCobroEdicion(documentoCapDto);
		if (marcaReversionDeCobroEdicion) {
			documentoCapDto.setMarcaReversionDeCobroEdicion(SiNoEnum.SI);
		} else {
			documentoCapDto.setMarcaReversionDeCobroEdicion(SiNoEnum.NO);
		}
		
		if (EstadoDocSapEnum.V.equals(documentoCapDto.getEstadoDocSAP())){
			documentoCapDto.setEsDocumentoPreliminar(SiNoEnum.SI);
		} else {
			documentoCapDto.setEsDocumentoPreliminar(SiNoEnum.NO);
		}
		return documentoCapDto;
	}
	@Override
	public boolean obtenerMarcaDocumentoEnCobrosPendienteProceso(String idCapCobro) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerMarcaCapEnCobrosPendienteProceso(idCapCobro);
	}
	@Override
	public boolean obtenerMarcaDocumentoEnDescobrosPendienteProceso(String idCapCobro) throws NegocioExcepcion {
		return vistaSoporteServicio.obtenerMarca(idCapCobro, QueryMarcaEnum.DESCOBRO_PENDIENTE_PROCESO_CAP_QUERY);
	}
	@Override
	public ArchivoByteArray generarPdfCartaCompensacion(Long idCobro) throws NegocioExcepcion {
		return cobroBatchServicio.generarPdfCartaCompensacion(idCobro);
	}
	@Override
	public ArchivoByteArray generarPdfResumenCompensacion(Long idCobro) throws NegocioExcepcion {
		return cobroBatchServicio.generarPdfResumenCompensacion(idCobro);
	}
	@Override
	public CobroDto prepararDocumentosCapParaSuVisualizacion(CobroDto cobroDto) {
		if ("9".equals(cobroDto.getIdMotivoCobro())) {
			if (!Validaciones.isObjectNull(cobroDto.getMedios()) && !cobroDto.getMedios().isEmpty()) {
				for (CobroMedioDePagoDto medio : cobroDto.getMedios()) {
					if (!Validaciones.isObjectNull(medio.getListaDocumentoCap()) && !medio.getListaDocumentoCap().isEmpty()) {
						Collections.sort(medio.getListaDocumentoCap(), new ComparatorDocumentoCapDto());
						BuilderConsultaSap builder = new BuilderConsultaSap();
						builder.setListaDosCap(medio.getListaDocumentoCap());
						int cantidad = builder.setValoresVisuales();
						medio.setListaDocumentoCap(builder.getListaDosCap());
						
						
						cobroDto.setControlPaginacion(new CreditosControlPaginacion());
						cobroDto.getControlPaginacion().setCantPorPagina(1);
						cobroDto.getControlPaginacion().setCantRegistrosMostrados(cantidad);
						cobroDto.getControlPaginacion().setTotalRegistros(cantidad);
						cobroDto.getControlPaginacion().setPaginaActual(1);
					}
					break;
				}
			}
		}
		return cobroDto;
	}
	
	public boolean hayCompensacionesSap(ShvCobEdCobro cobro){
		
		for (ShvCobEdOtrosMedioPago mp : cobro.getMediosPagos() ){
			
			if ("35".equals(mp.getTipoMedioPago().getIdTipoMedioPago()) //COMPENSACION LIQUIDO PRODUCTO
					|| "41".equals(mp.getTipoMedioPago().getIdTipoMedioPago()) //COMPENSACION PROVEEDORES
					|| "43".equals(mp.getTipoMedioPago().getIdTipoMedioPago())){ //SALDO A COBRAR
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean mostrarBtnGenerarCartayResumen (CobroDto cobroDto) {
		if(Estado.COB_COBRADO.equals(cobroDto.getEstado()) && ConstantesCobro.ID_MOTIVO_COBRO_COMPENSACION.equals(cobroDto.getIdMotivoCobro())
				&&	cobroDto.getMedios().iterator().hasNext()) {
			
			CobroMedioDePagoDto  medioPago = cobroDto.getMedios().iterator().next();
			
			if(TipoCreditoEnum.LIQUIDOPRODUCTO.getIdTipoMedioPago().equals(medioPago.getIdMpTipoCredito()) 
					&& 	( SubTipoCompensacionEnum.LIQ_PROD.getCodigo().equals(medioPago.getSubTipo()) || SubTipoCompensacionEnum.RET_LIQ_PROD.getCodigo().equals(medioPago.getSubTipo()))) {
				
				return true;
			}
		}
		return false;
		
	}
	
	public boolean mostrarBtnGenerarResumen (CobroDto cobroDto) {
		if(Estado.COB_COBRADO.equals(cobroDto.getEstado()) && ConstantesCobro.ID_MOTIVO_COBRO_COMPENSACION.equals(cobroDto.getIdMotivoCobro())
				&&	cobroDto.getMedios().iterator().hasNext()) {
			
			CobroMedioDePagoDto  medioPago = cobroDto.getMedios().iterator().next();
			
			if(
				TipoCreditoEnum.PROVEEDORES.getIdTipoMedioPago().equals(medioPago.getIdMpTipoCredito()) ||
				(
					TipoCreditoEnum.LIQUIDOPRODUCTO.getIdTipoMedioPago().equals(medioPago.getIdMpTipoCredito()) &&
					!(
						SubTipoCompensacionEnum.LIQ_PROD.getCodigo().equals(medioPago.getSubTipo()) ||
						SubTipoCompensacionEnum.RET_LIQ_PROD.getCodigo().equals(medioPago.getSubTipo())
					)
				)
			) {
				return true;
			}
		}
		return false;
	}
	
	public ShvWfWorkflow actualizarEstadoWorkflow(String observacion, ShvWfWorkflow workflow, String idUsuario, SociedadEnum sociedad, SistemaEnum sistema, boolean rechazoConfirmacionAplicacionManual) throws PersistenciaExcepcion, WorkflowExcepcion{
		
		if(Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(workflow.getEstado())){
			if(Validaciones.isObjectNull(tareaDao.buscarTareaPendienteConfManual(TipoTareaEnum.COB_CONF_APLIC_MANUAL, sociedad, sistema, workflow.getIdWorkflow()))){
				workflow = workflowCobros.registrarPendienteConfirmacionManualAEnProcesoDeAplicacionExterna(workflow, observacion, idUsuario);	
			}
			else {
				if(rechazoConfirmacionAplicacionManual){
					workflow = workflowCobros.registrarPendienteConfirmacionManualAEnPendienteConfirmacionManualEnProcesoApliExterna(workflow,observacion, idUsuario);
				} else {
					workflow = workflowCobros.registrarPendienteConfirmacionManualAPendienteConfirmacionManual(workflow, observacion, idUsuario);
				}
			}
		} else if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(workflow.getEstado())){
			if(Validaciones.isObjectNull(tareaDao.buscarTareaPendienteConfManual(TipoTareaEnum.COB_CONF_APLIC_MANUAL, sociedad, sistema, workflow.getIdWorkflow()))){
				workflow = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaAEnProcesoDeAplicacionExterna(workflow, observacion, idUsuario);
			} else{
				workflow = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaAPendienteConfirmacionManualEnProcesoDeAplicacionExterna(workflow,observacion, idUsuario);
				}
			
		} else if (Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PEND_PROC_MIC.equals(workflow.getEstado())){
			if(Validaciones.isObjectNull(tareaDao.buscarTareaPendienteConfManual(TipoTareaEnum.COB_CONF_APLIC_MANUAL, sociedad, sistema, workflow.getIdWorkflow()))){
				workflow = workflowCobros.registrarConfirmacionManualEnProcesoApliExternaYPendienteProcesarMICAEnProcesoApliExternaYPendienteProcesarMIC(workflow, observacion, idUsuario);
			} else{
				workflow = workflowCobros.registrarConfirmacionManualEnProcesoApliExternaYPendienteProcesarMICAConfirmacionManualEnProcesoApliExternaYPendienteProcesarMIC(workflow, observacion, idUsuario);
			}
		} else if(Estado.COB_PEND_CONF_MANUAL_Y_PROCESO_APLICACION_EXTERNA_Y_PENDIENTE_MIC.equals(workflow.getEstado())){
			if(Validaciones.isObjectNull(tareaDao.buscarTareaPendienteConfManual(TipoTareaEnum.COB_CONF_APLIC_MANUAL, sociedad, sistema, workflow.getIdWorkflow()))){
				workflow = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaPendienteMICAEnProcesoDeAplicacionExternaPendienteMIC(workflow, observacion, idUsuario);
			} else{
				workflow = workflowCobros.registrarPendienteConfirmacionManualEnProcesoApliExternaPendienteMICAPendienteConfirmacionManualEnProcesoDeAplicacionExternaPendienteMIC(workflow, observacion, idUsuario);
			}
		} else if(Estado.COB_ERROR_DESAPROPIACION.equals(workflow.getEstado())){
			workflow = workflowCobros.registrarErrorDesapropiacionAErrorEnDesapropiacion(workflow, observacion, idUsuario);
		}
			return workflow;
	}
	public void actualizarEstadoTransacciones(Long idOperacion, SistemaEnum sistema,SociedadEnum sociedad,boolean hayCompensaciones) throws PersistenciaExcepcion{

		List<ShvCobTransaccionSinOperacion> listaTransacciones = new ArrayList<ShvCobTransaccionSinOperacion>();
		List<ShvCobTransaccionSinOperacion> listaTransaccionesDependencia = new ArrayList<ShvCobTransaccionSinOperacion>();
		
		listaTransacciones = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYSistemaYSociedad(idOperacion, sistema, sociedad);
		for (ShvCobTransaccionSinOperacion transaccion : listaTransacciones){
			if(hayCompensaciones){
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.APL_MANUAL_APROBADA_SIN_COMP_SAP);
			} else{
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.APL_MANUAL_APROBADA);
			}
			cobroDao.guardarTransaccionSinOperacion(transaccion);
		}
		
		listaTransaccionesDependencia = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYSistemaDependenciaYSociedadDependencia(idOperacion, sistema, sociedad);
		if(Validaciones.isCollectionNotEmpty(listaTransaccionesDependencia)){
			for (ShvCobTransaccionSinOperacion transaccion : listaTransaccionesDependencia){
				transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE);
				cobroDao.guardarTransaccionSinOperacion(transaccion);
			}
		}
	}
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, NegocioExcepcion.class }, propagation = Propagation.REQUIRED)
	public void confirmaAplicacionManual(Long idCobro, UsuarioSesion userSesion, String observacion, SociedadEnum sociedad, SistemaEnum sistema, Set<CodigoOperacionExternaDto> listaCodigoOperacionesExternas) throws NegocioExcepcion{
		List<ComprobanteDto> comprobantes = userSesion.getComprobantesAGuardar();
		Set<CodigoOperacionExternaDto> codigosOperacionesExternas = listaCodigoOperacionesExternas;
		CobroDto cobro = buscarCobro(idCobro);
				
		if(Validaciones.isCollectionNotEmpty(comprobantes)){
			for (ComprobanteDto  comprobante : comprobantes) {
				crearAdjuntoCobro(idCobro, comprobante);
			}
			userSesion.setComprobantesAGuardar(new ArrayList<ComprobanteDto>());
		}

		cobro.setObservacion(observacion);
		
		if(Validaciones.isCollectionNotEmpty(comprobantes) || Validaciones.isCollectionNotEmpty(codigosOperacionesExternas)){
			aprobarCobroCambiarEstadoWorkFlow(cobro, userSesion,sociedad,sistema, listaCodigoOperacionesExternas);
		} else {
			throw new NegocioExcepcion(Mensajes.COBROS_ERROR_CONFIRMACION_APLICACION_MANUAL);
		}
	}
	/*
	 * (non-Javadoc)
	 * @see ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio#informaDesapropiacionAplicacionManual(java.lang.Long, ar.com.telecom.shiva.presentacion.bean.UsuarioSesion, java.lang.String, ar.com.telecom.shiva.base.enumeradores.SociedadEnum, ar.com.telecom.shiva.base.enumeradores.SistemaEnum)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, NegocioExcepcion.class }, propagation = Propagation.REQUIRED)
	public void informaDesapropiacionAplicacionManual(Long idCobro, UsuarioSesion userSesion, String observacion, SociedadEnum sociedad, SistemaEnum sistema) throws NegocioExcepcion{
		//CobroDto cobro = buscarCobro(idCobro);
		
		try {
			
			ShvCobEdCobro cobroModelo = cobroOnlineDao.buscarCobro(idCobro);
			//cobro.setObservacion(observacion);
			// Obtengo la lista de taras pendientes.
			List<ShvWfTarea> tareas = tareaDao.buscarTareasPendientes(cobroModelo.getWorkflow().getIdWorkflow());
			
			tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.COB_DESAPRO_APLI_MANUAL,sociedad,sistema, cobroModelo.getWorkflow().getIdWorkflow(), new Date(), userSesion.getIdUsuario(), null, null, observacion);
			
			
			
			if (tareas.size() <= 1) {
				ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroPendienteDesapropiacionManualExternaEnProceso(cobroModelo.getWorkflow(), observacion, userSesion.getIdUsuario());
				cobroModelo.setWorkflow(workflowActualizado);
				//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
				this.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobroModelo.getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),true);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion("Error al buscar las tarea " + TipoTareaEnum.COB_DESAPRO_APLI_MANUAL.descripcion());
		}
	}
	/**
	 * 
	 * @param cobro
	 * @param codigosOperacionesExternasDto
	 */
	private ShvCobEdCobro crearCodigoOperacionExterna(ShvCobEdCobro cobro, Set<CodigoOperacionExternaDto> codigosOperacionesExternasDto, UsuarioSesion usuario) throws NegocioExcepcion{
		
		List<ShvCobEdCodigoOperacionExterna> listaOperacionesExternas = new ArrayList<ShvCobEdCodigoOperacionExterna>();
		try {
			for (CodigoOperacionExternaDto codigoOperacionExternaDto : codigosOperacionesExternasDto) {
				ShvCobEdCodigoOperacionExterna operacionExterna = new ShvCobEdCodigoOperacionExterna();
				operacionExterna.setCodigoOperacionExterna(codigoOperacionExternaDto.getCodigoOperacionExterna());
				operacionExterna.setNumeroTransaccionFormateado(codigoOperacionExternaDto.getNroTransaccion());
				operacionExterna.setNroDocumento((codigoOperacionExternaDto.getFactura().equals(Constantes.SIGNO_MENOS)) ? null : codigoOperacionExternaDto.getFactura());
				operacionExterna.setImporte(new BigDecimal(codigoOperacionExternaDto.getImporte()));
				operacionExterna.setReferente((codigoOperacionExternaDto.getReferencia().equals(Constantes.SIGNO_MENOS)) ? null : codigoOperacionExternaDto.getReferencia());
				operacionExterna.setCobro(cobro);
				operacionExterna.setEstado(EstadoActivoInactivoEnum.ACTIVO);
				operacionExterna.setMonedaImporte(MonedaEnum.getEnumBySigno2(codigoOperacionExternaDto.getMonedaImporte()));
				operacionExterna.setSistema(codigoOperacionExternaDto.getSistema());
				operacionExterna.setIdTransaccion(Integer.parseInt(codigoOperacionExternaDto.getIdTransaccion()));
				operacionExterna.setGrupo(codigoOperacionExternaDto.getGrupo());
				operacionExterna.setNombreApellidoReferenteAprobador(usuario.getNombreCompleto());
				operacionExterna.setIdReferenteAprobador(usuario.getIdUsuario());
				operacionExterna.setFechaAprobacion(new Date());
				String[] nroTransaccionFormateado = codigoOperacionExternaDto.getNroTransaccion().split("\\.");
				String idOperacion = nroTransaccionFormateado[0];
				String nroTransaccion = nroTransaccionFormateado[1];
				SiNoEnum esNroTransaccionFicticio = null;
				if(transaccionCobroDao.verificarNroTransaccionFicticio(Long.valueOf(idOperacion), Integer.valueOf(nroTransaccion))>1){
					esNroTransaccionFicticio = SiNoEnum.SI;
				} else {
					esNroTransaccionFicticio = SiNoEnum.NO;
				}
				operacionExterna.setEsNroTransaccionFicticio(esNroTransaccionFicticio);
				listaOperacionesExternas.add(operacionExterna);	
			}
			cobro.getCodigosOperacionesExternas().addAll(listaOperacionesExternas);
			
			cobro = cobroOnlineDao.guardarCobro(cobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return cobro;
	}

	@Override
	public String buscarCuentaContablePorDefault(Set<ClienteDto> clientes) throws NegocioExcepcion {

		List<String> listaIdClienteLegado = new ArrayList<String>();
		for (ClienteDto clienteDto : clientes) {
			listaIdClienteLegado.add(clienteDto.getIdClienteLegado());
		}
		
		String cuentaContable ="";
		try {
			cuentaContable = cobroDao.buscarCuentaContablePorDefault(listaIdClienteLegado);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return cuentaContable;
	}

	/**
	 * Método que se encarga de la Reedición Parcial.
	 * 
	 * @author U587496 Guido.Settecerze
	 * @param idCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public CobroDto reeditarCobroParcial(CobroDto cobroDto, TareaDto tareaDto) throws NegocioExcepcion {
		SociedadEnum sociedad = tareaDto.getSociedad();
		SistemaEnum sistema = tareaDto.getSistema();

		ShvCobCobro cobroModelo;
		ShvCobFactura facturasAReeditar;
		Set<CobroDebitoDto> debitosReedicion = new HashSet<CobroDebitoDto>();
		Set<CobroOtrosDebitoDto> otrosDebitosReedicion = new HashSet<CobroOtrosDebitoDto>();
		Set<CobroCreditoDto> creditosReedicion = new HashSet<CobroCreditoDto>();
		Set<CobroMedioDePagoDto> mediosReedicion = new HashSet<CobroMedioDePagoDto>();
		Set<Long> idClientesReedicion = new HashSet<Long>();
		Set<ClienteDto> clientesReedicion = new HashSet<ClienteDto>();
		CobroTratamientoDiferenciaDto tratamientoDiferenciaReedicion = null;
		SociedadEnum sociedadFactura = null;
		SistemaEnum sistemaTransaccion = null;
		Set<ShvCobTransaccion> transaccionesGrupoCero = new HashSet<ShvCobTransaccion>();
		boolean reediteUnGrupo = false;

		try {
			cobroModelo = cobroDao.buscarCobro(cobroDto.getIdCobro());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		Set<ShvCobTransaccion> transacciones = cobroModelo.getOperacion().getTransacciones();

		for (ShvCobTransaccion cobroTransaccionModelo : transacciones) {
			//Integer grupoTransaccion = cobroTransaccionModelo.getGrupo();
			sistemaTransaccion = cobroTransaccionModelo.getSistema();
			facturasAReeditar = cobroTransaccionModelo.getFactura();
			String grupoTransaccion = cobroTransaccionModelo.getGrupo().toString();

			if (Utilidad.ZERO_CHARACTER.equals(grupoTransaccion)) {
				transaccionesGrupoCero.add(cobroTransaccionModelo);
			}

			if (!Validaciones.isObjectNull(facturasAReeditar)) {
				sociedadFactura = facturasAReeditar.getSociedad();
			}

			if (
				(!Validaciones.isObjectNull(sociedadFactura) && sociedadFactura.equals(sociedad)) &&
				(!Validaciones.isObjectNull(sistemaTransaccion) && sistemaTransaccion.equals(sistema))
			) {
				reediteUnGrupo = true;

				logicaDebOtrosDebCredMedPagCliente(cobroDto,
						debitosReedicion, otrosDebitosReedicion,
						creditosReedicion, mediosReedicion, tratamientoDiferenciaReedicion,
						idClientesReedicion, cobroTransaccionModelo );
			}
		}

		//Si no reedito ningun grupo, se espera entonces reeditar el grupoCero
		if(!reediteUnGrupo && !transaccionesGrupoCero.isEmpty()) {
			for (ShvCobTransaccion shvCobTransaccionGrupoCero : transaccionesGrupoCero) {

				logicaDebOtrosDebCredMedPagCliente(cobroDto,
						debitosReedicion, otrosDebitosReedicion, creditosReedicion, mediosReedicion,
						tratamientoDiferenciaReedicion, idClientesReedicion, shvCobTransaccionGrupoCero );
			}
		}
		for (Long idCliente : idClientesReedicion) {
			ClienteBean consultarCliente = clienteServicio.consultarCliente(String.valueOf(idCliente));
			ClienteDto clienteDto = clienteServicio.mapear(consultarCliente);
			clientesReedicion.add(clienteDto);
		}

		cobroDto.setClientes(clientesReedicion);
		cobroDto.setDebitos(debitosReedicion);
		cobroDto.setOtrosDebitos(otrosDebitosReedicion);
		cobroDto.setCreditos(creditosReedicion);
		cobroDto.setMedios(mediosReedicion);
		cobroDto.setTratamientoDiferencia(tratamientoDiferenciaReedicion);

		return cobroDto;

	}
	
	/**
	 * 
	 * Busca y setea todos los elementos a impactar en un nuevo cobro.
	 * 
	 * @author U587496 Guido.Settecerze
	 * @param cobroDto
	 * @param listaDebitoReeditado
	 * @param listaOtrosDebitoReeditado
	 * @param listaCreditosReeditado
	 * @param listaOtrosMedioPagoReeditado
	 * @param idClientesReedicion
	 * @param transaccion
	 * @param tratamientoDiferenciaReeditado
	 * 
	 */
	private void logicaDebOtrosDebCredMedPagCliente (
			CobroDto cobroDto,
			Set<CobroDebitoDto> listaDebitoReeditado,
			Set<CobroOtrosDebitoDto> listaOtrosDebitoReeditado,
			Set<CobroCreditoDto> listaCreditosReeditado,
			Set<CobroMedioDePagoDto> listaOtrosMedioPagoReeditado,
			CobroTratamientoDiferenciaDto tratamientoDiferenciaReeditado,
			Set<Long> idClientesReedicion,
			ShvCobTransaccion transaccion
		) {

		ShvCobFactura facturasAReeditar = transaccion.getFactura();
		Set<CobroDebitoDto> listaDebitoOriginal = cobroDto.getDebitos(); 
		Set<CobroOtrosDebitoDto> otrosDebitos = cobroDto.getOtrosDebitos();
		Set<CobroCreditoDto> listaCreditoOriginal = cobroDto.getCreditos();
		Set<CobroMedioDePagoDto> listaOtrosMedioPagoOriginal = cobroDto.getMedios();
		Set<ShvCobMedioPago> listaMediosPagoAReeditar;
		

		//Manejo Tratamiento de la Diferencia
		if(!Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia())) {
			tratamientoDiferenciaReeditado = cobroDto.getTratamientoDiferencia();
		}

		if(!Validaciones.isObjectNull(facturasAReeditar)) {
			if(!Validaciones.isObjectNull(facturasAReeditar.getIdClienteLegado())) {
				idClientesReedicion.add(facturasAReeditar.getIdClienteLegado());
			}

			//Manejo de Otros Débitos
			if(facturasAReeditar instanceof ShvCobFacturaUsuario){
				for (CobroOtrosDebitoDto otrosDebitoOriginal : otrosDebitos) {
					if(otrosDebitoOriginal.getIdOtrosDebito().equals(facturasAReeditar.getIdDebitoOrigen())) {
						if(!listaOtrosDebitoReeditado.contains(otrosDebitoOriginal)){
							otrosDebitoOriginal.setImporte(facturasAReeditar.getImporteCobrar().toString());
							listaOtrosDebitoReeditado.add(otrosDebitoOriginal);
						}else {
							for (CobroOtrosDebitoDto otrosDebitoAnalisis : listaOtrosDebitoReeditado) {
								if(otrosDebitoAnalisis.getIdOtrosDebito().equals(otrosDebitoOriginal.getIdOtrosDebito())){ 
									otrosDebitoAnalisis.setImporte(Utilidad.cambiarPuntoDecimalPorComa(((Utilidad.stringToBigDecimal(otrosDebitoAnalisis.getImporte())).add(Utilidad.stringToBigDecimal(facturasAReeditar.getImporteCobrar().toString()))).toString()));
								}
							}
						}
					}
				}
			} else {

				//Manejo de Débitos
				for (CobroDebitoDto debitoOriginal : listaDebitoOriginal) {
					if((debitoOriginal.getIdDebito().equals(String.valueOf(facturasAReeditar.getIdDebitoOrigen())))) {
						if(!listaDebitoReeditado.contains(debitoOriginal)){
							listaDebitoReeditado.add(debitoOriginal);
						}else {
							for (CobroDebitoDto debitoAnalisis : listaDebitoReeditado) {
								if(debitoAnalisis.getIdDebito().equals(debitoOriginal.getIdDebito())){ 
									debitoAnalisis.setImpACobrar(Utilidad.cambiarPuntoDecimalPorComa(((Utilidad.stringToBigDecimal(debitoAnalisis.getImpACobrar())).add(Utilidad.stringToBigDecimal(debitoOriginal.getImpACobrar()))).toString()));
								}
							}
						}
					}
				}
			}
		}
		
		//Manejo de Medios de Pago
		listaMediosPagoAReeditar = transaccion.getMediosPago();
		if (!Validaciones.isObjectNull(listaMediosPagoAReeditar)){
			for (ShvCobMedioPago medioPago : listaMediosPagoAReeditar) {
				if(medioPago instanceof ShvCobMedioPagoUsuario) {
					for (CobroMedioDePagoDto medioPagoOriginalDto : listaOtrosMedioPagoOriginal) {
						if(!Validaciones.isObjectNull(medioPagoOriginalDto.getClientesLegados())) {
							String[] clientes = medioPagoOriginalDto.getClientesLegados().split("-");
							int contador = 0;
							while (contador <= clientes.length-1) {
								idClientesReedicion.add(Long.valueOf(clientes[contador].trim()));
								contador++;
							}
						}
						if(medioPagoOriginalDto.getIdMedioPago().equals(medioPago.getIdCreditoOrigen())) {
							if(!listaOtrosMedioPagoReeditado.contains(medioPagoOriginalDto)){
								medioPagoOriginalDto.setImporte(medioPago.getImporte().toString());
								listaOtrosMedioPagoReeditado.add(medioPagoOriginalDto);
							}else {
								for (CobroMedioDePagoDto medioAAnalizar : listaOtrosMedioPagoReeditado) {
									if(medioAAnalizar.getIdMedioPago().equals(medioPagoOriginalDto.getIdMedioPago())){
										medioAAnalizar.setImporte(Utilidad.cambiarPuntoDecimalPorComa(((Utilidad.stringToBigDecimal(medioAAnalizar.getImporte())).add(Utilidad.stringToBigDecimal((medioPago.getImporte().toString())))).toString()));
									}
								}
							}
						}
					}
				} else {
					//Manejo de Créditos
					for (CobroCreditoDto creditoOriginalDto : listaCreditoOriginal) {
						if(!Validaciones.isObjectNull(creditoOriginalDto.getIdClienteLegado())) {
							idClientesReedicion.add(Long.valueOf(creditoOriginalDto.getIdClienteLegado()));
						}
						if(creditoOriginalDto.getIdCredito().equals(medioPago.getIdCreditoOrigen().toString())) {
							if(!listaCreditosReeditado.contains(creditoOriginalDto)){
								creditoOriginalDto.setImporteAUtilizar(medioPago.getImporte().toString());
								if (medioPago instanceof ShvCobMedioPagoCTA) {
									creditoOriginalDto.setClaseComprobante(((ShvCobMedioPagoCTA) medioPago).getClaseComprobante());
									creditoOriginalDto.setSucursalComprobante(((ShvCobMedioPagoCTA) medioPago).getSucursalComprobante());
									creditoOriginalDto.setNumeroComprobante(((ShvCobMedioPagoCTA) medioPago).getNroComprobante());
								}
								listaCreditosReeditado.add(creditoOriginalDto);
							} else {
								for (CobroCreditoDto creditoAnalisis : listaCreditosReeditado) {
									if(creditoAnalisis.getIdCredito().equals(creditoOriginalDto.getIdCredito())){
										creditoAnalisis.setImporteAUtilizar(Utilidad.cambiarPuntoDecimalPorComa(((Utilidad.stringToBigDecimal(creditoAnalisis.getImporteAUtilizar())).add(Utilidad.stringToBigDecimal((medioPago.getImporte().toString()))).toString())));
									}
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * 
	 */
	@Override
	public ShvParamTipoComprobante buscarComprobante(TipoComprobanteEnum comprobante) throws NegocioExcepcion{
		
		try{
			
			ShvParamTipoComprobante shvParamTipoComprobante = tipoComprobanteDao.buscarComprobante(comprobante);
			return shvParamTipoComprobante;
		
		} catch (PersistenceException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * @return the cobroOtrosDebitoDao
	 */
	public ICobroOnlineOtrosDebitoDao getCobroOtrosDebitoDao() {
		return cobroOtrosDebitoDao;
	}

	/**
	 * @param cobroOtrosDebitoDao the cobroOtrosDebitoDao to set
	 */
	public void setCobroOtrosDebitoDao(
			ICobroOnlineOtrosDebitoDao cobroOtrosDebitoDao) {
		this.cobroOtrosDebitoDao = cobroOtrosDebitoDao;
	}

	/**
	 * @return the otrosDebitoMapeador
	 */
	public CobroOnlineOtrosDebitoMapeador getOtrosDebitoMapeador() {
		return otrosDebitoMapeador;
	}

	/**
	 * @param otrosDebitoMapeador the otrosDebitoMapeador to set
	 */
	public void setOtrosDebitoMapeador(CobroOnlineOtrosDebitoMapeador otrosDebitoMapeador) {
		this.otrosDebitoMapeador = otrosDebitoMapeador;
	}

}
