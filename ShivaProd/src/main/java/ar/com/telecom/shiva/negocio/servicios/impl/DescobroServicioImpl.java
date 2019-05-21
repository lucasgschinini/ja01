package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenModificacionDescobroTransaccionDto;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenModificacionShvCobTransaccion;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenVistaTransaccionInvertido;
import ar.com.telecom.shiva.base.comparador.ComparatorShvWfWorkflowMarca;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoDescobroEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoAccionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaClienteSalida;
import ar.com.telecom.shiva.base.jms.servicios.IFacJmsSyncServicio;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.AcuerdoLegado;
import ar.com.telecom.shiva.negocio.mapeos.DescobroHistorialMapeador;
import ar.com.telecom.shiva.negocio.mapeos.DescobroOperacionesRelacionadasMapeador;
import ar.com.telecom.shiva.negocio.mapeos.DescobroTransaccionesMapeador;
import ar.com.telecom.shiva.negocio.mapeos.DescobroVistaMapeador;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionContabilidadServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IExcelDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParamRespWfTareaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IScardServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorPorReversionServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowDescobros;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowValores;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobro;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroHistorial;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroTransaccion;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroCodigoOperacionExternaDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoAdjuntoDao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoCapDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.ITareaDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.dao.IWorkflowDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroAdjuntoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroCodigoOperacionExterna;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroOperacionRelacionada;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
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
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCapDetalle;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoPlanDePago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoRemanente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoSaldoACobrar;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobOperacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTarea;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstadoHist;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowMarca;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoDescobro;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimple;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTratamientoDiferenciaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroHistoricoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroOperacionesRelacionadasDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DescobrosJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.PerfilFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaDescobroHistorialFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroOperacionRelacionadaFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroTransaccionFiltro;

public class DescobroServicioImpl extends Servicio implements IDescobroServicio{

	@Autowired 
	IWorkflowDescobros workflowDescobros;
	
	@Autowired 
	IDescobroDao descobroDao;
	
	@Autowired 
	ICobroDao cobroDao;

	ICobroImputacionServicio cobroServicio;
	
	@Autowired
	ICobroBatchSoporteImputacionContabilidadServicio cobroBatchSoporteImputacionContabilidadServicio;
	
	@Autowired
	IValorServicio valorServicio;
	
	@Autowired 
	IValorDao valorDao;
	
	@Autowired
	IValorPorReversionServicio valorPorReversionServicio;
	
	@Autowired 
	IScardServicio scardServicio;
	
	@Autowired
	private IMensajeriaTransaccionServicio mensajeriaTransaccionServicio;
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired
	private MailServicio mailServicio;
	
	@Autowired 
	ICobroOnlineDao cobroOnlineDao;
	
	@Autowired 
	IDocumentoAdjuntoDao documentoAdjuntoDao;
	
	@Autowired 
	IGenericoDao genericoDao;
	
	@Autowired 
	IVistaSoporteServicio vistaSoporteServicio;
	
	@Autowired
	DescobroVistaMapeador descobroVistaMapeo;
	
	@Autowired
	DescobroOperacionesRelacionadasMapeador descobroOperacionesRelacionadasMapeo;
	
	@Autowired 
	ITareaServicio tareaServicio;
	
	@Autowired 
	DescobroHistorialMapeador descobroHistorialMapeo;
	
	@Autowired 
	IDescobroSimulacionServicio descobroBatchSimulacionServicio;

	@Autowired
	DescobroTransaccionesMapeador descobroTransaccionesMapeador;
	
	@Autowired
	ICobroOnlineServicio cobroOnlineServicio;
	
	@Autowired 
	IExcelDescobroServicio excelDescobroServicio;
	
	@Autowired
	IFacJmsSyncServicio facJmsSyncServicio;
	
	@Autowired
	IClienteCalipsoServicio clienteCalipsoServicio;
	
	@Autowired 
	ILdapServicio ldapServicio;
	
	@Autowired
	IWorkflowValores workflowValores;

	@Autowired
	IWorkflowService workflowService;
	
	@Autowired
	IWorkflowDao workflowDao;
	
	@Autowired
	IDocumentoCapDao documentoCapDao;
	
	@Autowired
	ILegajoChequeRechazadoServicio legajoServicio;
	
	@Autowired
	IDescobroCodigoOperacionExternaDao codigoOperacionExternaDao;

	@Autowired
	IDescobroImputacionServicio descobroImputacionServicio;
	
	@Autowired
	IParamRespWfTareaServicio paramRespWfTareaServicio;
	
	@Autowired
	ITareaDao tareaDao;
	
	private String xls = ".xls";
	public static final String XLS_CONTENT_TYPE = "application/vnd.ms-excel";
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String ATTACHMENT_FILENAME = "attachment; filename=";
	


	/**
	 * U562163
	 * setea a todas las transacciones, Facturas y Medios de Pago de la operacion a "PENDIENTE_DESCOBRO".
	 * @param operacion
	 * @throws NegocioExcepcion 
	 */
	@Override
	public ShvCobOperacion inicializarOperacionDeDescobro(ShvCobOperacion operacionVieja) throws NegocioExcepcion{
		ShvCobOperacion operacionNueva = new ShvCobOperacion();
		BeanUtils.copyProperties(operacionVieja, operacionNueva);

		Set<ShvCobTransaccion> transaccionesAux = new HashSet<ShvCobTransaccion>();
		
		for(ShvCobTransaccion transaccionVieja : operacionVieja.getTransacciones()){
			ShvCobTransaccion transaccionNueva = new ShvCobTransaccion();
			BeanUtils.copyProperties(transaccionVieja, transaccionNueva);
			
			// Medio de Pago
			Set<ShvCobMedioPago> mediosDePagosAux = new HashSet<ShvCobMedioPago>();
			for(ShvCobMedioPago medioPagoVieja : transaccionVieja.getShvCobMedioPago()){

				if(medioPagoVieja instanceof ShvCobMedioPagoCTA){
					//CTA
					ShvCobMedioPagoCTA medioPagoNueva = new ShvCobMedioPagoCTA();
					BeanUtils.copyProperties((ShvCobMedioPagoCTA)medioPagoVieja, medioPagoNueva);
					medioPagoNueva.setIdMedioPago(null);
					medioPagoNueva.setId(null);
					
					if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
						if(!Utilidad.esDiferenciaCambio(medioPagoVieja)
								&& !EstadoFacturaMedioPagoEnum.NUEVO.equals(medioPagoVieja.getEstado())){
							medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
						}
						
						medioPagoNueva.setTipoMensajeEstado(null);
						medioPagoNueva.setMensajeEstado(null);
					}
					
					medioPagoNueva.setTransaccion(transaccionNueva);
					mediosDePagosAux.add(medioPagoNueva);
				}else{
					//REMANENTE
					if(medioPagoVieja instanceof ShvCobMedioPagoRemanente){
						ShvCobMedioPagoRemanente medioPagoNueva = new ShvCobMedioPagoRemanente();
						BeanUtils.copyProperties((ShvCobMedioPagoRemanente)medioPagoVieja,medioPagoNueva);
						medioPagoNueva.setIdMedioPago(null);
						medioPagoNueva.setId(null);
						
						if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
							if(!Utilidad.esDiferenciaCambio(medioPagoVieja)){
								medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
							}
							medioPagoNueva.setTipoMensajeEstado(null);
							medioPagoNueva.setMensajeEstado(null);
						}
						
						medioPagoNueva.setTransaccion(transaccionNueva);
						mediosDePagosAux.add(medioPagoNueva);
					}else{
						//NOTA DE CREDITO MIC
						if(medioPagoVieja instanceof ShvCobMedioPagoNotaCreditoMic){
							ShvCobMedioPagoNotaCreditoMic medioPagoNueva = new ShvCobMedioPagoNotaCreditoMic();
							BeanUtils.copyProperties((ShvCobMedioPagoNotaCreditoMic)medioPagoVieja,medioPagoNueva);
							medioPagoNueva.setIdMedioPago(null);
							medioPagoNueva.setId(null);
							
							if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
								if(!Utilidad.esDiferenciaCambio(medioPagoVieja)){
									medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
								}
								medioPagoNueva.setTipoMensajeEstado(null);
								medioPagoNueva.setMensajeEstado(null);
							}
							
							medioPagoNueva.setTransaccion(transaccionNueva);
							mediosDePagosAux.add(medioPagoNueva);
						}else{
							//NOTA DE CREDITO CALIPSO
							if(medioPagoVieja instanceof ShvCobMedioPagoNotaCreditoCalipso){
								
								ShvCobMedioPagoNotaCreditoCalipso medioPagoNueva = new ShvCobMedioPagoNotaCreditoCalipso();
								BeanUtils.copyProperties((ShvCobMedioPagoNotaCreditoCalipso)medioPagoVieja,medioPagoNueva);
								medioPagoNueva.setIdMedioPago(null);
								medioPagoNueva.setId(null);
								
								if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
									if(!Utilidad.esDiferenciaCambio(medioPagoVieja)){
										medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
									}
									medioPagoNueva.setTipoMensajeEstado(null);
									medioPagoNueva.setMensajeEstado(null);
								}
								
								//Lo dejo en null o No para que no lo levante SCARD
								medioPagoNueva.setGeneradoPorCobro(SiNoEnum.NO);
								
								medioPagoNueva.setTransaccion(transaccionNueva);
								mediosDePagosAux.add(medioPagoNueva);
							}else{
								if(medioPagoVieja instanceof ShvCobMedioPagoShiva){
									//Shiva
									ShvCobMedioPagoShiva medioPagoNueva = new ShvCobMedioPagoShiva();
									BeanUtils.copyProperties((ShvCobMedioPagoShiva)medioPagoVieja,medioPagoNueva);
									medioPagoNueva.setIdMedioPago(null);
									medioPagoNueva.setId(null);
									medioPagoNueva.setIdValor(((ShvCobMedioPagoShiva) medioPagoVieja).getIdValor());
									
									if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
										if(!Utilidad.esDiferenciaCambio(medioPagoVieja)){
											medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
										}
										medioPagoNueva.setTipoMensajeEstado(null);
										medioPagoNueva.setMensajeEstado(null);
									}
									
									medioPagoNueva.setTransaccion(transaccionNueva);
									mediosDePagosAux.add(medioPagoNueva);
								}else{
									if(medioPagoVieja instanceof ShvCobMedioPagoDesistimiento){
										//DESISTIMIENTO
										ShvCobMedioPagoDesistimiento medioPagoNueva = new ShvCobMedioPagoDesistimiento();
										BeanUtils.copyProperties((ShvCobMedioPagoDesistimiento)medioPagoVieja,medioPagoNueva);
										medioPagoNueva.setIdMedioPago(null);
										medioPagoNueva.setId(null);
										
										if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
											if(!Utilidad.esDiferenciaCambio(medioPagoVieja)){
												medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
											}
											medioPagoNueva.setTipoMensajeEstado(null);
											medioPagoNueva.setMensajeEstado(null);
										}
										
										medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoDesistimiento) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
										medioPagoNueva.setTransaccion(transaccionNueva);
										mediosDePagosAux.add(medioPagoNueva);
									}else{
										if(medioPagoVieja instanceof ShvCobMedioPagoPlanDePago){
											//PLAN DE PAGO
											ShvCobMedioPagoPlanDePago medioPagoNueva = new ShvCobMedioPagoPlanDePago();
											BeanUtils.copyProperties((ShvCobMedioPagoPlanDePago)medioPagoVieja,medioPagoNueva);
											medioPagoNueva.setIdMedioPago(null);
											medioPagoNueva.setId(null);
											
											if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
												if(!Utilidad.esDiferenciaCambio(medioPagoVieja)){
													medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
												}
												medioPagoNueva.setTipoMensajeEstado(null);
												medioPagoNueva.setMensajeEstado(null);
											}
											
											medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoPlanDePago) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
											medioPagoNueva.setTransaccion(transaccionNueva);
											mediosDePagosAux.add(medioPagoNueva);
										}else{
											if(medioPagoVieja instanceof ShvCobMedioPagoCompensacionOtras){
												//COMPENSACION
												ShvCobMedioPagoCompensacionOtras medioPagoNueva = new ShvCobMedioPagoCompensacionOtras();
												BeanUtils.copyProperties((ShvCobMedioPagoCompensacionOtras)medioPagoVieja,medioPagoNueva);
												medioPagoNueva.setIdMedioPago(null);
												medioPagoNueva.setId(null);
												
												if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
													if(!Utilidad.esDiferenciaCambio(medioPagoVieja)){
														medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
													}
													medioPagoNueva.setTipoMensajeEstado(null);
													medioPagoNueva.setMensajeEstado(null);
												}
												
												medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionOtras) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
												medioPagoNueva.setTransaccion(transaccionNueva);
												mediosDePagosAux.add(medioPagoNueva);
											} else {
												if (medioPagoVieja instanceof ShvCobMedioPagoCompensacionIIBB) {
													// Compensación IIBB
													ShvCobMedioPagoCompensacionIIBB medioPagoNueva = new ShvCobMedioPagoCompensacionIIBB();
													BeanUtils.copyProperties((ShvCobMedioPagoCompensacionIIBB)medioPagoVieja, medioPagoNueva);
													medioPagoNueva.setIdMedioPago(null);
													medioPagoNueva.setId(null);
													
													if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
														if (!Utilidad.esDiferenciaCambio(medioPagoVieja)) {
															medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
														}
														medioPagoNueva.setTipoMensajeEstado(null);
														medioPagoNueva.setMensajeEstado(null);
													}
													
													medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionIIBB) medioPagoVieja).getListaMedioPagoClientes(), medioPagoNueva));
													medioPagoNueva.setTransaccion(transaccionNueva);
													mediosDePagosAux.add(medioPagoNueva);
												} else {
													if (medioPagoVieja instanceof ShvCobMedioPagoCompensacionCesionCredito) {
														// Compensación Cesión Crédito
														ShvCobMedioPagoCompensacionCesionCredito medioPagoNueva = new ShvCobMedioPagoCompensacionCesionCredito();
														BeanUtils.copyProperties((ShvCobMedioPagoCompensacionCesionCredito)medioPagoVieja, medioPagoNueva);
														medioPagoNueva.setIdMedioPago(null);
														medioPagoNueva.setId(null);
														
														if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
															if (!Utilidad.esDiferenciaCambio(medioPagoVieja)) {
																medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
															}
															medioPagoNueva.setTipoMensajeEstado(null);
															medioPagoNueva.setMensajeEstado(null);
														}
														
														medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionCesionCredito) medioPagoVieja).getListaMedioPagoClientes(), medioPagoNueva));
														medioPagoNueva.setTransaccion(transaccionNueva);
														mediosDePagosAux.add(medioPagoNueva);
													} else {
														if (medioPagoVieja instanceof ShvCobMedioPagoCompensacionProveedor) {
															// Compensación Proveedor
															ShvCobMedioPagoCompensacionProveedor medioPagoNueva = new ShvCobMedioPagoCompensacionProveedor();
															BeanUtils.copyProperties((ShvCobMedioPagoCompensacionProveedor)medioPagoVieja, medioPagoNueva);
															medioPagoNueva.setIdMedioPago(null);
															medioPagoNueva.setId(null);

															medioPagoNueva.setDocumentoCap(generarDocumentoCap(medioPagoNueva.getDocumentoCap()));
															
															if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
																if (!Utilidad.esDiferenciaCambio(medioPagoVieja)) {
																	medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
																}
																medioPagoNueva.setTipoMensajeEstado(null);
																medioPagoNueva.setMensajeEstado(null);
															}
															
															medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionProveedor) medioPagoVieja).getListaMedioPagoClientes(), medioPagoNueva));
															medioPagoNueva.setTransaccion(transaccionNueva);
															mediosDePagosAux.add(medioPagoNueva);
														}else{
															if(medioPagoVieja instanceof ShvCobMedioPagoCompensacionIntercompany){
																//COMPENSACION INTERCOMPANY
																ShvCobMedioPagoCompensacionIntercompany medioPagoNueva = new ShvCobMedioPagoCompensacionIntercompany();
																BeanUtils.copyProperties((ShvCobMedioPagoCompensacionIntercompany)medioPagoVieja,medioPagoNueva);
																medioPagoNueva.setIdMedioPago(null);
																medioPagoNueva.setId(null);
																
																if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
																	if(!Utilidad.esDiferenciaCambio(medioPagoVieja)){
																		medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
																	}
																	medioPagoNueva.setTipoMensajeEstado(null);
																	medioPagoNueva.setMensajeEstado(null);
																}
																
																medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionIntercompany) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
																medioPagoNueva.setTransaccion(transaccionNueva);
																mediosDePagosAux.add(medioPagoNueva);
															}else{
																if(medioPagoVieja instanceof ShvCobMedioPagoCompensacionLiquidoProducto){
																	//COMPENSACION LIQUIDO
																	ShvCobMedioPagoCompensacionLiquidoProducto medioPagoNueva = new ShvCobMedioPagoCompensacionLiquidoProducto();
																	BeanUtils.copyProperties((ShvCobMedioPagoCompensacionLiquidoProducto)medioPagoVieja,medioPagoNueva);
																	medioPagoNueva.setIdMedioPago(null);
																	medioPagoNueva.setId(null);
																	medioPagoNueva.setDocumentoCap(generarDocumentoCap(medioPagoNueva.getDocumentoCap()));
																	
																	if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
																		if(!Utilidad.esDiferenciaCambio(medioPagoVieja)){
																			medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
																		}
																		medioPagoNueva.setTipoMensajeEstado(null);
																		medioPagoNueva.setMensajeEstado(null);
																	}
																	
																	medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionLiquidoProducto) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
																	medioPagoNueva.setTransaccion(transaccionNueva);
																	mediosDePagosAux.add(medioPagoNueva);
																}else{
																	if(medioPagoVieja instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso){
																		//DEBITO PROXIMA FACTURA CALIPSO
																		ShvCobMedioPagoDebitoProximaFacturaCalipso medioPagoNueva = new ShvCobMedioPagoDebitoProximaFacturaCalipso();
																		BeanUtils.copyProperties((ShvCobMedioPagoDebitoProximaFacturaCalipso)medioPagoVieja,medioPagoNueva);
																		medioPagoNueva.setIdMedioPagoCobro(medioPagoNueva.getIdMedioPago());
																		medioPagoNueva.setIdMedioPago(null);
																		medioPagoNueva.setId(null);
																		
																		if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
																			medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
																			medioPagoNueva.setTipoMensajeEstado(null);
																			medioPagoNueva.setMensajeEstado(null);
																		}
																		
																		medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
																		medioPagoNueva.setTransaccion(transaccionNueva);
																		mediosDePagosAux.add(medioPagoNueva);
																	}else{
																		if(medioPagoVieja instanceof ShvCobMedioPagoDebitoProximaFacturaMic){
																			//DEBITO PROXIMA FACTURA MIC
																			ShvCobMedioPagoDebitoProximaFacturaMic medioPagoNueva = new ShvCobMedioPagoDebitoProximaFacturaMic();
																			BeanUtils.copyProperties((ShvCobMedioPagoDebitoProximaFacturaMic)medioPagoVieja,medioPagoNueva);
																			medioPagoNueva.setIdMedioPagoCobro(medioPagoNueva.getIdMedioPago());
																			medioPagoNueva.setIdMedioPago(null);
																			medioPagoNueva.setId(null);
																			
																			if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
																				medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
																				medioPagoNueva.setTipoMensajeEstado(null);
																				medioPagoNueva.setMensajeEstado(null);
																			}
																			
																			medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoDebitoProximaFacturaMic) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
																			medioPagoNueva.setTransaccion(transaccionNueva);
																			mediosDePagosAux.add(medioPagoNueva);
																		} else {
																			if(medioPagoVieja instanceof ShvCobMedioPagoSaldoACobrar){
																				//SALDO A COBRAR
																				ShvCobMedioPagoSaldoACobrar medioPagoNueva = new ShvCobMedioPagoSaldoACobrar();
																				BeanUtils.copyProperties((ShvCobMedioPagoSaldoACobrar)medioPagoVieja,medioPagoNueva);
																				medioPagoNueva.setSistemaOrigen(SistemaEnum.USUARIO);
																				medioPagoNueva.setIdMedioPago(null);
																				medioPagoNueva.setId(null);
																				
																				if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
																					if(!Utilidad.esDiferenciaCambio(medioPagoVieja)){
																						medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
																					}
																					medioPagoNueva.setTipoMensajeEstado(null);
																					medioPagoNueva.setMensajeEstado(null);
																				}
																				
																				medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoSaldoACobrar) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
																				medioPagoNueva.setTransaccion(transaccionNueva);
																				mediosDePagosAux.add(medioPagoNueva);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			transaccionNueva.setShvCobMedioPago(mediosDePagosAux);

			if(Validaciones.isCollectionNotEmpty(transaccionVieja.getShvCobFactura())
					&& !Validaciones.isObjectNull(transaccionVieja.getShvCobFactura().iterator().next())){
				
				// factura
				Set<ShvCobFactura> facturasAux = new HashSet<ShvCobFactura>();
				ShvCobFactura facturaVieja = transaccionVieja.getShvCobFactura().iterator().next();
				
				if(facturaVieja instanceof ShvCobFacturaMic){
					
					ShvCobFacturaMic facturaNueva = new ShvCobFacturaMic();
					BeanUtils.copyProperties((ShvCobFacturaMic) facturaVieja, facturaNueva);
					facturaNueva.setIdFacturaCobro(facturaNueva.getIdFactura());
					facturaNueva.setIdFactura(null);
					facturaNueva.setId(null);
					
					if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
						facturaNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
						facturaNueva.setTipoMensajeEstado(null);
						facturaNueva.setMensajeEstado(null);
					}
					
					//Lo dejo en null o No para que no lo levante SCARD
					facturaNueva.setSaldoActual(null);
					facturaNueva.setGeneradoPorCobro(SiNoEnum.NO);
					
					facturaNueva.setTransaccion(transaccionNueva);
					facturasAux.add(facturaNueva);
					
				} else if(facturaVieja instanceof ShvCobFacturaCalipso){
					
					ShvCobFacturaCalipso facturaNueva = new ShvCobFacturaCalipso();
					BeanUtils.copyProperties((ShvCobFacturaCalipso) facturaVieja, facturaNueva);
					facturaNueva.setIdFacturaCobro(facturaNueva.getIdFactura());
					facturaNueva.setIdFactura(null);
					facturaNueva.setId(null);
					
					if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
						if(!Utilidad.esDiferenciaCambio(facturaVieja)){
							facturaNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
						}
						facturaNueva.setTipoMensajeEstado(null);
						facturaNueva.setMensajeEstado(null);
					}
					
					//Lo dejo en null o No para que no lo levante SCARD
					facturaNueva.setSaldoActual(null);
					facturaNueva.setSaldoActualMonedaOrigen(null);
					facturaNueva.setGeneradoPorCobro(SiNoEnum.NO);
					
					facturaNueva.setTransaccion(transaccionNueva);
					facturasAux.add(facturaNueva);
					
				} else if(facturaVieja instanceof ShvCobFacturaUsuario){
					
					ShvCobFacturaUsuario facturaNueva = new ShvCobFacturaUsuario();
					BeanUtils.copyProperties((ShvCobFacturaUsuario) facturaVieja, facturaNueva);
					facturaNueva.setIdFacturaCobro(facturaNueva.getIdFactura());
					facturaNueva.setIdFactura(null);
					facturaNueva.setId(null);
					
					if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
						if(!Utilidad.esDiferenciaCambio(facturaVieja)){
							facturaNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
						}
						facturaNueva.setTipoMensajeEstado(null);
						facturaNueva.setMensajeEstado(null);
					}
					
					//Lo dejo en null o No para que no lo levante SCARD
					facturaNueva.setSaldoActual(null);
					facturaNueva.setGeneradoPorCobro(SiNoEnum.NO);
					
					facturaNueva.setTransaccion(transaccionNueva);
					facturasAux.add(facturaNueva);
				}
				
				transaccionNueva.setShvCobFactura(facturasAux);
				
			} else if(!Validaciones.isObjectNull(transaccionVieja.getTratamientoDiferencia())){
				//Tratamiento de diferencia
				Set<ShvCobTratamientoDiferencia> tratamientoAux = new HashSet<ShvCobTratamientoDiferencia>();
				ShvCobTratamientoDiferencia tratamientoViejo = transaccionVieja.getTratamientoDiferencia();

				ShvCobTratamientoDiferencia tratamientoNuevo = new ShvCobTratamientoDiferencia();
				BeanUtils.copyProperties((ShvCobTratamientoDiferencia)tratamientoViejo, tratamientoNuevo);
				tratamientoNuevo.setIdTratamientoDiferenciaCobro(tratamientoNuevo.getIdTratamientoDiferencia());
				tratamientoNuevo.setIdTratamientoDiferencia(null);
				tratamientoNuevo.setId(null);
				
				if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
					tratamientoNuevo.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
					tratamientoNuevo.setTipoMensajeEstado(null);
					tratamientoNuevo.setMensajeEstado(null);
				}
				tratamientoNuevo.setTransaccion(transaccionNueva);
				
				tratamientoAux.add(tratamientoNuevo);
				transaccionNueva.setListaTratamientosDiferencias(tratamientoAux);
			}

			transaccionNueva.setId(null);
			transaccionNueva.setIdTransaccion(null);
			
			if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccionVieja.getEstadoProcesamiento())){
				if(!Utilidad.esDiferenciaCambio(transaccionVieja)){
					transaccionNueva.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE_DESCOBRO);
				}
				transaccionNueva.setTipoMensajeEstado(null);
				transaccionNueva.setMensajeEstado(null);
			}
			
			transaccionNueva.setShvCobMedioPago(mediosDePagosAux);
			transaccionNueva.setOperacion(operacionNueva);
			transaccionesAux.add(transaccionNueva);
			
		}

		// Operacion
		operacionNueva.setTipoOperacion(TipoOperacionEnum.DESCOBRO);
		operacionNueva.setIdOperacion(null);
		operacionNueva.setId(null);
		operacionNueva.setTransacciones(transaccionesAux);
		operacionNueva.setIdOperacionOriginal(operacionVieja.getIdOperacion());

		return operacionNueva;
	}

	/**
	 * Duplica la lista de ShvCobMedioPagoCliente y la retorna.
	 * @param mediosPagoClientes
	 * @param medioPagoUsuario 
	 * @return
	 */
	private Set<ShvCobMedioPagoCliente> generarListaMedioPagoClientes(Set<ShvCobMedioPagoCliente> mediosPagoClientes, ShvCobMedioPagoUsuario medioPagoUsuario) {
		Set<ShvCobMedioPagoCliente> mediosDePagoClientesAux = new HashSet<ShvCobMedioPagoCliente>();

		for(ShvCobMedioPagoCliente clienteViejo : mediosPagoClientes){
			//DEBITO PROXIMA FACTURA CALIPSO
			ShvCobMedioPagoCliente clienteNuevo = new ShvCobMedioPagoCliente();
			BeanUtils.copyProperties(clienteViejo,clienteNuevo);
			clienteNuevo.setIdMedioPagoCliente(null);
			clienteNuevo.setId(null);
			clienteNuevo.setMedioPagoUsuario(medioPagoUsuario);
			
			mediosDePagoClientesAux.add(clienteNuevo);
		}
		
		return mediosDePagoClientesAux;
	}

	/**
	 * Duplica la lista de ShvCobMedioPagoDocumentoCap y la retorna.
	 * @param documentosCap
	 * @return Set<ShvCobMedioPagoDocumentoCap>
	 */
	private ShvCobMedioPagoDocumentoCap generarDocumentoCap(ShvCobMedioPagoDocumentoCap documentosCap) {

			documentosCap.setId(null);
			documentosCap.setIdCobro(null);
			documentosCap.setIdDescobro(null);
			documentosCap.setIdMedioPagoDocumentoCap(null);
			
			Set<ShvCobMedioPagoDocumentoCapDetalle> documentoCapDetalleAux = new HashSet<ShvCobMedioPagoDocumentoCapDetalle>();
			
			for (ShvCobMedioPagoDocumentoCapDetalle documentoCapDetalleViejo : documentosCap.getDetalle()) {
				ShvCobMedioPagoDocumentoCapDetalle documentoCapDetalleNuevo = new ShvCobMedioPagoDocumentoCapDetalle();
				BeanUtils.copyProperties(documentoCapDetalleViejo,documentoCapDetalleNuevo);
				documentoCapDetalleNuevo.setId(null);
				documentoCapDetalleNuevo.setIdMedioPagoDocumentoCapDetalle(null);
				
				documentoCapDetalleAux.add(documentoCapDetalleNuevo);
				
			}
			
			documentosCap.setDetalle(documentoCapDetalleAux);
		
		return documentosCap;
	}
	

	public ShvCobDescobro buscarDescobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion{
		try {
			return descobroDao.buscarDescobroPorIdOperacion(idOperacion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	
	@Override
	public Set<TipoCreditoEnum> listarMediosPagoBusquedaDescobros() {

		Set <TipoCreditoEnum> listaMediosDePagoSinRepetidosFinal = new HashSet<TipoCreditoEnum>();
		Set<String> descripcionEnums = new HashSet<String>();
		List<TipoCreditoEnum> listaMediosDePago = Arrays
				.asList(TipoCreditoEnum.values());
		for (TipoCreditoEnum tipoCreditoEnum : listaMediosDePago) {
			if(!TipoCreditoEnum.SALDO_A_COBRAR.equals(tipoCreditoEnum)) {
				descripcionEnums.add(tipoCreditoEnum.getDescripcion());
			}
		}		
		for (String descripcionEnum : descripcionEnums) {
			listaMediosDePagoSinRepetidosFinal.add(TipoCreditoEnum.getEnumByDescripcion(descripcionEnum));
		}
		
		return listaMediosDePagoSinRepetidosFinal;
	}
	
	@Override
	public List<DescobroDto> listarDescobros(
			VistaSoporteDescobroFiltro descobroFiltro) throws NegocioExcepcion {

		List<DescobroDto> listaDescobros = new ArrayList<DescobroDto>();
		
		List<VistaSoporteResultadoBusquedaDescobro> listaCobros = vistaSoporteServicio.listarDescobros(descobroFiltro);
		
		for (VistaSoporteResultadoBusquedaDescobro descobro : listaCobros) {
			
			listaDescobros.add((DescobroDto) descobroVistaMapeo.map(descobro));
		}
		
		return listaDescobros;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param dto
	 * @return
	 * @throws NegocioExcepcion
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvCobDescobro crearDescobroDto(DTO dto, Long idCobro, UsuarioSesion userSesion) throws NegocioExcepcion {
		DescobroDto descobroDto = (DescobroDto) dto;
		
		try {
			ShvCobCobro cobro = cobroDao.buscarCobro(idCobro);
			ShvCobEdCobro buscarCobro = cobroOnlineDao.buscarCobro(idCobro);
			
			//Mapeos
			ShvCobDescobro descobroModeloConOperacion = copioOperacionDeCobroADescobro(cobro);
			ShvCobDescobro desCobro = (ShvCobDescobro) defaultMapeador.map(descobroDto, null);
			desCobro.setOperacion(descobroModeloConOperacion.getOperacion());
			desCobro.getOperacion().setIdOperacion(genericoDao.proximoValorSecuencia("SEQ_SHV_COB_OPERACION"));
			desCobro.setImporteTotal(buscarCobro.getImporteTotal());
			desCobro.setOrigenDescobro(SistemaEnum.SHIVA);
			desCobro.setMonedaOperacion(cobro.getMonedaOperacion());
			
			ShvWfWorkflow workflowDescobro = workflowDescobros.crearWorkflow("", descobroDto.getUsuarioModificacion());
			workflowDescobro = workflowDescobros.guardarDescobroEnEdicion(workflowDescobro, "", descobroDto.getUsuarioModificacion());
			desCobro.setWorkflow(workflowDescobro);
			
			desCobro = descobroDao.insertarDescobro(desCobro);
			
//			Avanzo el workflow del legajo en caso que corresponda
			
			if(!Validaciones.isObjectNull(descobroDto.getIdLegajo())) {
				ShvLgjLegajoChequeRechazado shvLgjLegajoChequeRechazado = (ShvLgjLegajoChequeRechazado) legajoServicio.buscar(descobroDto.getIdLegajo());
				
				legajoServicio.solicitarReversionDeCobrosrelacionados(shvLgjLegajoChequeRechazado.getWorkflow().getEstado(), shvLgjLegajoChequeRechazado.getWorkflow().getIdWorkflow(), userSesion);
			}
			
			
			//Seteo el ID Descobro en el documento CAP
			ShvCobMedioPago medioPago = desCobro.getOperacion().getTransacciones().iterator().next().getMediosPago().iterator().next();
			
			if(medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto){
				ShvCobMedioPagoDocumentoCap documentoCap = ((ShvCobMedioPagoCompensacionLiquidoProducto) medioPago).getDocumentoCap();
				
				documentoCap.setIdDescobro(desCobro.getIdDescobro());
				documentoCapDao.actualizarDocumentoCap(documentoCap);
			}else if(medioPago instanceof ShvCobMedioPagoCompensacionProveedor){
				ShvCobMedioPagoDocumentoCap documentoCap = ((ShvCobMedioPagoCompensacionProveedor) medioPago).getDocumentoCap();
				
				documentoCap.setIdDescobro(desCobro.getIdDescobro());
				documentoCapDao.actualizarDocumentoCap(documentoCap);
				
			}
			
			return desCobro;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 */
	@Override
	public List<ComprobanteDto> buscarAdjuntoPorIdAdjunto(Long idAdjunto) throws NegocioExcepcion {
		
		List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
		List<ShvDocDocumentoAdjunto> listaAdjuntos;
		try {
			listaAdjuntos = descobroDao.buscarPorIdAdjuntoDescobrosOnline(idAdjunto);

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
	
	@Override
	public ComprobanteDto crearAdjuntoDescobro(Long idDescobro, ComprobanteDto comprobante)
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
			
			ShvCobDescobroAdjuntoPk cobroAdjuntoPK = new ShvCobDescobroAdjuntoPk();
			cobroAdjuntoPK.setDocumentoAdjunto(docAdjunto);
			cobroAdjuntoPK.setIdDescobro(idDescobro);
			
			ShvCobDescobroAdjunto cobroAdjunto = new ShvCobDescobroAdjunto();
			cobroAdjunto.setPk(cobroAdjuntoPK);
			cobroAdjunto.setMotivoAdjunto(MotivoAdjuntoEnum.getEnumByName(comprobante.getMotivoAdjunto()));
			
			cobroAdjunto = descobroDao.insertarDocumentoAdjunto(cobroAdjunto);
			
			comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());
			
			return comprobante;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}
	
	@Override
	public ComprobanteDto buscarAdjuntoDescobro(Long idAdjunto)
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
	public DescobroDto buscarDescobroPorIdDescobro(Long idDescobro) throws NegocioExcepcion {
		try {
			DescobroDto descobroDto = new DescobroDto();

			ShvCobDescobro descobro = descobroDao.buscarDescobro(idDescobro);
			//Mapeando
			descobroDto = (DescobroDto) defaultMapeador.map(descobro);
			
			return descobroDto;

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}
	
	@Override
	public void iniciarDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion {

		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
			wf = workflowDescobros.iniciarDescobro(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public void descobrarCobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
			wf = workflowDescobros.descobrarCobro(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}		
	}

	@Override
	public void guardarDescobroEnEdicion(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
			wf = workflowDescobros.guardarDescobroEnEdicion(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}		
	}
	
	/**
	 * Cambio el estado del wf del descobro a PENDIENTE, al igual que sus transacciones, medios de pago, facturas y tratamientos.
	 * 
	 * @author u572487 Guido.Settecerze
	 */
	@Override
	public void revertirDescobrosCheckeados(List<String> ids, UsuarioSesion userSesion) throws NegocioExcepcion {

		try {
			for (String id : ids) {
				if (!Validaciones.isNullEmptyOrDash(id)) {
					ShvCobDescobro descobroModelo = descobroDao.buscarDescobro(new Long(id));

					descobroModelo = revivirDescobroConErrores(descobroModelo, userSesion);

					revertirDescobrosCheckeadosGuardarDescobro(descobroModelo);
				}
			}

		} catch (NegocioExcepcion | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	private void revertirDescobrosCheckeadosGuardarDescobro(ShvCobDescobro descobroModelo) throws NegocioExcepcion {

		try {

			descobroDao.modificar(descobroModelo);

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	private ShvCobDescobro revivirDescobroConErrores(ShvCobDescobro descobroModelo, UsuarioSesion userSesion) throws NegocioExcepcion {
			
		ShvWfWorkflow wf = descobroModelo.getWorkflow();
		
		if(Estado.DES_ERROR_EN_PRIMER_DESCOBRO.equals(wf.getEstado())){
			wf = workflowDescobros.revertirDescobroEnErrorEnPrimerDescobro(wf, "", userSesion.getIdUsuario());
		}else {
			wf = workflowDescobros.reintentarDescobro(wf, "", userSesion.getIdUsuario());
			eliminarTareasEnErrorDescobro(null, descobroModelo, userSesion.getIdUsuario());
		}
		
		
		ShvCobTransaccion transaccionEnError = descobroModelo.getTransaccionEnError();

		if(!Validaciones.isObjectNull(transaccionEnError)){
			// MEDIOS DE PAGO
			for (ShvCobMedioPago medioPago : transaccionEnError.getMediosPago()) {
				if(!Validaciones.isObjectNull(medioPago.getEstado())){
					if(medioPago.getEstado().equals(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO) ||
							medioPago.getEstado().equals(EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO)){
						medioPago.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
						medioPago.setTipoMensajeEstado(null);
						medioPago.setMensajeEstado(null);
					}
				}
			}
			
			// FACTURAS
			ShvCobFactura factura = transaccionEnError.getFactura();
			if(!Validaciones.isObjectNull(factura) && !Validaciones.isObjectNull(factura.getEstado())){
				if(factura.getEstado().equals(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO) ||
						factura.getEstado().equals(EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO)){
					factura.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
					factura.setTipoMensajeEstado(null);
					factura.setMensajeEstado(null);
				}
			}
			
			// TRATAMIENTO DE DIFERENCIA
			ShvCobTratamientoDiferencia tratamiento = transaccionEnError.getTratamientoDiferencia();
			if (!Validaciones.isObjectNull(tratamiento) && !Validaciones.isObjectNull(tratamiento.getEstado())) {
				if(tratamiento.getEstado().equals(EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO) ||
						tratamiento.getEstado().equals(EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO)){
					tratamiento.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
					tratamiento.setTipoMensajeEstado(null);
					tratamiento.setMensajeEstado(null);
				}
			}
			
			
			// Las transacciones del descobro --> PENDIENTE_DESCOBRO
			if(!Validaciones.isObjectNull(transaccionEnError.getEstadoProcesamiento())){
				if(transaccionEnError.getEstadoProcesamiento().equals(EstadoTransaccionEnum.ERROR_DESCOBRO_DEUDA) ||
						transaccionEnError.getEstadoProcesamiento().equals(EstadoTransaccionEnum.ERROR_DESCOBRO_MEDIO_PAGO) ||
						transaccionEnError.getEstadoProcesamiento().equals(EstadoTransaccionEnum.ERROR_DESCOBRO_MEDIO_PAGO_DEUDA) ||
						transaccionEnError.getEstadoProcesamiento().equals(EstadoTransaccionEnum.ERROR_DESCOBRO_CARGO)){
					
					transaccionEnError.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE_DESCOBRO);
					transaccionEnError.setTipoMensajeEstado(null);
					transaccionEnError.setMensajeEstado(null);
				}
			}
			
			List<MensajeServicioEnum> listaMensajes = new ArrayList<MensajeServicioEnum>();
			
			listaMensajes.addAll(MensajeServicioEnum.getEnumByTipoMensajeria("MSJ_DESCOBRO_IMPUTACION_CLP"));
			listaMensajes.addAll(MensajeServicioEnum.getEnumByTipoMensajeria("MSJ_DESCOBRO_IMPUTACION_MIC"));
			
			try {
				mensajeriaTransaccionServicio.borrarMensajesPorIdOperacionServiciosVarios(
						descobroModelo.getOperacion().getIdOperacion(), transaccionEnError.getIdTransaccion(), listaMensajes
						);
			} catch (PersistenciaExcepcion e) {
				Traza.error(getClass(), e.getMessage());
				throw new NegocioExcepcion(e.getMessage(), e);
			}
			
		}
		
		return descobroModelo;
		
	}
	
	
	/**
	 * @author u572487 Guido.Settecerze
	 */
	@Override
	public List<String> buscarIdsOperacionDescobroConIdsDescobro(List<String> ids) throws NegocioExcepcion {
		List<String> idsOperacionDescobro = new ArrayList<String>();
		ShvCobDescobro descobroModelo;
		for (String id : ids) {
			try {
				if(!Validaciones.isNullEmptyOrDash(id)){
					descobroModelo = descobroDao.buscarDescobro(new Long(id));
					Long idOperacion = descobroModelo.getOperacion().getIdOperacion();
					if(!Validaciones.isObjectNull(idOperacion)){
						idsOperacionDescobro.add(idOperacion.toString());
					}
				}

			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(),e);
			}
		}
		return idsOperacionDescobro;
	}

	@Override
	public void solicitarDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion {

		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
			wf = workflowDescobros.solicitarDescobro(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public void solicitarAnulacionDeDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion {

		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
//			tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.,wf.getIdWorkflow(), new Date(), userSesion.getIdUsuario(), null);
			this.eliminarTareasSimulacion( null, descobroModelo, userSesion.getIdUsuario());
			wf = workflowDescobros.solicitarAnulacionDeDescobro(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public void aplicarDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion {

		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
			wf = workflowDescobros.aplicarDescobro(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public void registrarDescobroEnError(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion {

		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
			wf = workflowDescobros.registrarDescobroEnError(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public void reintentarDescobroEnError(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion {

		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
			wf = workflowDescobros.reintentarDescobroEnError(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public void registrarErrorEnPrimerDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion {

		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
			wf = workflowDescobros.registrarErrorEnPrimerDescobro(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@Override
	public void revertirDescobroEnErrorEnPrimerDescobro(Long idDescobro, UsuarioSesion userSesion)
			throws NegocioExcepcion {

		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
			wf = workflowDescobros.revertirDescobroEnErrorEnPrimerDescobro(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	public DescobrosJsonResponse obtenerEstados(long idDescobro, boolean soloUltima, String prefijo, String separador) throws NegocioExcepcion {
		ShvCobDescobro descobroModelo;
		DescobrosJsonResponse rta = new DescobrosJsonResponse();
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			List<MarcaEnum> marcas = this.obtenerUltimasMarcas(idDescobro, descobroModelo, true);
			StringBuffer str = new StringBuffer();
			for (MarcaEnum marca : marcas) {
				str.append(prefijo);
				str.append(marca.descripcion());
				str.append(separador);
			}
			if (str.length() > 0) {
				str.deleteCharAt(str.length() - 1);
			}
			rta.getEstado().setEstadoDescripcion(descobroModelo.getWorkflow().getWorkflowEstado().getEstado().descripcion());
			rta.getEstado().setMarcaDescripcion(str.toString());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return rta;
	}
	
	@Override
	public void anularDescobroEnErrorEnPrimerDescobro(Long idDescobro, UsuarioSesion userSesion) throws NegocioExcepcion {

		ShvCobDescobro descobroModelo;
		
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
//			tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.DES_IMP_1ER_ERR,wf.getIdWorkflow(), new Date(), userSesion.getIdUsuario(), null);
			eliminarTareasErrorPrimerDescobro(null, descobroModelo, userSesion.getIdUsuario());
			wf = workflowDescobros.anularDescobroEnErrorEnPrimerDescobro(wf, "", userSesion.getIdUsuario());
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * @author u579607 Brian.Keller
	 */
	public boolean validaAnular(DescobroDto descobroDto) {
		
		boolean result = false;
		
		if (descobroDto.getUsuarioModificacion().equals(descobroDto.getIdAnalistaTeamComercial()) || descobroDto.getUsuarioModificacion().equals(descobroDto.getIdAnalista()) || descobroDto.getUsuarioModificacion().equals(descobroDto.getIdCopropietario())){

			if(Estado.DES_ERROR_EN_PRIMER_DESCOBRO.equals(descobroDto.getEstado()) || Estado.DES_EN_EDICION.equals(descobroDto.getEstado())){
				result = true;
				if(MarcaEnum.SIMULACION_BATCH_EN_PROCESO.equals(descobroDto.getMarca())) {
					result = false;
				}
			}
		}
		return result;
	}
	
	/**
	 * @author u579607 Brian.Keller
	 * @throws LdapExcepcion 
	 */
	public boolean validaModificar(DescobroDto descobroDto, UsuarioSesion userSesion) throws LdapExcepcion {
		
		boolean result = false;
		
		
		//chequeo q sea supervisor del segmento
		Collection<String> segmentos = userSesion.getIdSegmentos("SUPERVISORCOBRANZA");

		for (String seg : segmentos) {
			if(seg.equals(descobroDto.getIdSegmento())) {
				result = true;
			}
		}
		//chequeo que sea analista, analista del team o coprop 
		if(!result) {
			if (descobroDto.getUsuarioModificacion().equals(descobroDto.getIdAnalistaTeamComercial()) || 
					descobroDto.getUsuarioModificacion().equals(descobroDto.getIdAnalista()) || 
						descobroDto.getUsuarioModificacion().equals(descobroDto.getIdCopropietario())){
				result = true;
			}
		}
		
		if(result && Estado.DES_ANULADO.equals(descobroDto.getEstado())) {
			result = false;
		}
		
		
		return result;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @throws PersistenciaExcepcion 
	 * @throws NegocioExcepcion 
	 */
	public ShvCobDescobro copioOperacionDeCobroADescobro(ShvCobCobro cobro) throws NegocioExcepcion {
		ShvCobDescobro descobro = new ShvCobDescobro();
		
		ShvCobOperacion operacion = inicializarOperacionDeDescobro(cobro.getOperacion());
		descobro.setOperacion(operacion);
		
		return descobro;
	}
	
	@Transactional(readOnly=false)
	public List<DescobroHistoricoDto> obtenerHistorialDescobro (VistaSoporteBusquedaDescobroHistorialFiltro filtro) throws NegocioExcepcion{
		
		List<DescobroHistoricoDto> listaHistoricoDto = new ArrayList<DescobroHistoricoDto>();
		
		try {
			List<VistaSoporteResultadoBusquedaDescobroHistorial> historialCobros = vistaSoporteServicio.obtenerHistorialDescobro(filtro);

			for (VistaSoporteResultadoBusquedaDescobroHistorial hist : historialCobros) {
				listaHistoricoDto.add((DescobroHistoricoDto) descobroHistorialMapeo.map(hist)); 
			}
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return listaHistoricoDto;
		
	}
	
	public List<String> listarEstadosBusquedaDescobro(){
		
		
		List<String> lista = new ArrayList<String>();
		
		lista.add("En Edición");
		lista.add("En Proceso");
		lista.add("Con Error");
		lista.add("Anulada");
		lista.add("Descobrada");
		
		return lista;
	}
	
	@Override
	public List<DescobroTransaccionDto> buscarTransacciones(Long idDescobro) throws NegocioExcepcion, PersistenciaExcepcion {

		List<DescobroTransaccionDto> listaTransacciones = new ArrayList<DescobroTransaccionDto>();
		
		if(!Validaciones.isObjectNull(idDescobro)){
			VistaSoporteDescobroTransaccionFiltro filtro = new VistaSoporteDescobroTransaccionFiltro();
			filtro.setIdDescobro(idDescobro);
			
			ShvCobDescobro descobroAux = descobroDao.buscarDescobro(idDescobro);
			
			List<VistaSoporteResultadoBusquedaDescobroTransaccion> listaTransaccionesModelo = vistaSoporteServicio.obtenerTransaccionesDescobro(filtro);
			Collections.sort(listaTransaccionesModelo, new ComparatorOrdenVistaTransaccionInvertido());

			if(Validaciones.isCollectionNotEmpty(listaTransaccionesModelo)){

				VistaSoporteResultadoBusquedaDescobroTransaccion transAComparar = null;
				
				for (VistaSoporteResultadoBusquedaDescobroTransaccion transaccion : listaTransaccionesModelo) {
					
					DescobroTransaccionDto transaccionDto = (DescobroTransaccionDto) descobroTransaccionesMapeador.map(transaccion);
					
					if (Validaciones.isObjectNull(transAComparar)) {
						transAComparar = transaccion;
						transaccionDto.setApocopeGrupo(apocopeGrupo(transaccion, descobroAux.getSegmento().getIdSegmento(), descobroAux.getEmpresa().getIdEmpresa()));
					} else if (!transAComparar.getNumeroTransaccionFormateado().equals(transaccion.getNumeroTransaccionFormateado())) {
						transAComparar = transaccion;
						if (!Validaciones.isObjectNull(transaccion.getTipoComprobante())) {
							transaccionDto.setApocopeGrupo(apocopeGrupo(transaccion, descobroAux.getSegmento().getIdSegmento(), descobroAux.getEmpresa().getIdEmpresa()));
						}
					} else {
						transaccionDto.setApocopeGrupo(apocopeGrupo(transAComparar, descobroAux.getSegmento().getIdSegmento(), descobroAux.getEmpresa().getIdEmpresa()));
						transaccionDto.setNumeroGrupo(transAComparar.getGrupo());
					}
					
					if(!Validaciones.isNullEmptyOrDash(transaccionDto.getSistemaAcuerdo())
							&& !Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoDestinoCargos())){
						if(Validaciones.isNullEmptyOrDash(transaccionDto.getSistemaAcuerdoFactDestinoContracargos())
								|| Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoFactDestinoContracargos())){
							transaccionDto.setHabilitarBtnSimular(false);
						}else{
							transaccionDto.setHabilitarBtnSimular(true);
						}
					}else if(Validaciones.isNullEmptyOrDash(transaccionDto.getSistemaAcuerdo())
							&& Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoDestinoCargos()) &&
							Validaciones.isNullEmptyOrDash(transaccionDto.getSistemaAcuerdoFactDestinoContracargos())
							&& Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoFactDestinoContracargos())){
						transaccionDto.setHabilitarBtnSimular(true);
					}
					else if(!Validaciones.isNullEmptyOrDash(transaccionDto.getSistemaAcuerdo())
							&& !Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoDestinoCargos()) &&
							!Validaciones.isNullEmptyOrDash(transaccionDto.getSistemaAcuerdoFactDestinoContracargos())
							&& !Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoFactDestinoContracargos())){
						transaccionDto.setHabilitarBtnSimular(true);
					}else{
						transaccionDto.setHabilitarBtnSimular(false);
					}
					listaTransacciones.add(transaccionDto); 

				}
			}
			try {
				Utilidad.guionesNull(listaTransacciones);
			} catch (ShivaExcepcion e) {
				Traza.error(getClass(), e.getMessage());
			}
		}
		return listaTransacciones;
	}	
	
	private String apocopeGrupo(VistaSoporteResultadoBusquedaDescobroTransaccion vista, String segmento, String empresa) throws NegocioExcepcion, PersistenciaExcepcion {
		PerfilFiltro filtro = new PerfilFiltro();
		String apocopeGrupo = Constantes.EMPTY_STRING;
		
		if (!Validaciones.isObjectNull(vista.getSociedad()) && !Validaciones.isObjectNull(vista.getSistemaOrigenDocumento()) && !ConstantesCobro.GRUPO_INTERNO_SHIVA.equals(vista.getGrupo())) {
			filtro.setSociedad(vista.getSociedad().name());
			filtro.setSistema(vista.getSistemaOrigenDocumento().name());
			filtro.setSegmento(segmento);
			filtro.setEmpresa(empresa);
			filtro.setTipoTarea(TipoTareaEnum.DES_CONFIRMA_APL_MAN.name());
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
	public List<DescobroOperacionesRelacionadasDto> buscarOperacionesRelacionadas(Long idDescobro) throws NegocioExcepcion {

		List<DescobroOperacionesRelacionadasDto> listaOperacionesRelacionadas = new ArrayList<DescobroOperacionesRelacionadasDto>();
		
		VistaSoporteDescobroOperacionRelacionadaFiltro filtro = new VistaSoporteDescobroOperacionRelacionadaFiltro();
		filtro.setIdDescobro(idDescobro);
			
		List<VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas> listaOperacionesRelacionadasModelo = vistaSoporteServicio.obtenerOperacionesRelacionadasDescobro(filtro);
			

			if(Validaciones.isCollectionNotEmpty(listaOperacionesRelacionadasModelo)){

				for (VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas operacionRelacionada : listaOperacionesRelacionadasModelo) {
					listaOperacionesRelacionadas.add((DescobroOperacionesRelacionadasDto) descobroOperacionesRelacionadasMapeo.map(operacionRelacionada)); 
				}
			}
			try {
				Utilidad.guionesNull(listaOperacionesRelacionadas);
			} catch (ShivaExcepcion e) {
				Traza.error(getClass(), e.getMessage());
			}
		
		return listaOperacionesRelacionadas;
	}	
	
	/**
	 * Genera Excel de un descobro
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 */
	public HSSFWorkbook exportarDescobro(Long idDescobro, Long idCobro) throws NegocioExcepcion {
		
		DescobroDto descobroDto = buscarDescobroPorIdDescobro(idDescobro);
		CobroDto cobroDto = cobroOnlineServicio.buscarCobro(idCobro);
//		List<DescobroTransaccionDto> transacciones = this.buscarTransacciones(idDescobro);
//		
//		Set<DescobroTransaccionDto> setTransacciones = new LinkedHashSet<DescobroTransaccionDto>(transacciones);
//		descobroDto.setTransacciones(setTransacciones);
		
		List<MarcaEnum> ultimaMarca = this.obtenerUltimasMarcas(descobroDto.getIdDescobro(), null, true);
		
		if (!ultimaMarca.isEmpty()) {
			//obtengo la ultima marca
			descobroDto.setMarcas(ultimaMarca);
		}
		HSSFWorkbook workbook = excelDescobroServicio.generarExcelDetalleDescobro(descobroDto, cobroDto);
	
		return workbook;
	}
	
	public List<MarcaEnum> obtenerUltimasMarcas(Long idDescobro, ShvCobDescobro descobroModelo, boolean soloUltima) throws NegocioExcepcion {
		
		if (Validaciones.isObjectNull(descobroModelo)) {
			descobroModelo = obtenerModeloDescobro(idDescobro);
		}
		
		Set<ShvWfWorkflowMarca> marcas = descobroModelo.getWorkflow().getWorkflowEstado().getWorkflowMarcas();
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
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param idCobro
	 */
	public ShvCobDescobro obtenerModeloDescobro(Long idDescobro) throws NegocioExcepcion{
		ShvCobDescobro descobroModelo = new ShvCobDescobro();
		try {
			descobroModelo = descobroDao.buscarDescobro(idDescobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return descobroModelo;
	}
	
	@Override
	public void exportarDescobro(DescobroDto descobroDto, List<ComprobanteDto> comprobantesAGuardar, UsuarioSesion userSesion, HttpServletResponse response)
			throws NegocioExcepcion {
		
		try {

			//empiezo a generar el excel
			ByteArrayOutputStream salida = new ByteArrayOutputStream();
			HSSFWorkbook workbook = exportarDescobro(descobroDto.getIdDescobro(), descobroDto.getIdCobro());
		
			workbook.write(salida);
			
			response.setContentType(XLS_CONTENT_TYPE);
	    	response.setContentLength(salida.size());
	    	response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + "ID Operación Reversión Cobro " + workbook.getSheetName(0) + xls);
	    	
	    	response.getOutputStream().write(salida.toByteArray());
	    	
		} catch (IOException e) {
				new NegocioExcepcion(e.getMessage(),e);	
		}
	}
	
//	@Override
//	public List<Object[]> listarDescobrosParaSubdiario() throws NegocioExcepcion {
//		try {
//			return descobroDao.buscarCobrosParaSubdiario();
//		} catch (PersistenciaExcepcion e) {
//			throw new NegocioExcepcion(e.getMessage(),e);
//		}
//	}
	

	/**
	 * @author u573005, fabio.giaquinta.ruiz
	 */
	@Override
	public List<ShvCobDescobro> buscarDescobrosPendienteSimulacionBatch()
			throws NegocioExcepcion {
		try {
			
			List<ShvCobDescobro> listaDescobros = descobroDao.buscarDescobrosSimulacionEnProceso();
			return listaDescobros;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Este metodo se utiliza para mostra habilitado o no un campo checkBox de la transacciones
	 * @param transaccion
	 * @return
	 */
	public int validarHabilitacionCampo(DescobroTransaccionDto transaccion){
		BigDecimal intereses = null;
		if (!Validaciones.isNullEmptyOrDash(transaccion.getIntereses())) {
			intereses = Utilidad.stringToBigDecimal(transaccion.getIntereses());	
		} else {
			intereses = Utilidad.stringToBigDecimal("0");
		}
		if (transaccion.isSoloLecturaPantalla()) {
			return 4;
		} else {
			if (
				TipoTratamientoDiferenciaEnum.REINTEGRO_POR_CHEQUE.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
				TipoTratamientoDiferenciaEnum.REINTEGRO_PAGO_CUENTA_TERCEROS.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
				TipoTratamientoDiferenciaEnum.REINTEGRO_CREDITO_RED_INTEL.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
				TipoTratamientoDiferenciaEnum.REINTEGRO_TRANSFERENCIA_BAN.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
				TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.getDescripcion().equals(transaccion.getTipoDocumento())
			) {
				//es reintegro NO proxima factura
				return 0;
			} else if (
				TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC.getSubTipoDocumento().equals(transaccion.getSubtipoDocumento()) ||
				TipoTratamientoDiferenciaEnum.DEBITO_PROX_FAC.getDescripcion().equals(transaccion.getTipoMedioPago())
			) {
				//es reintegro proxima factura
				if (intereses.compareTo(new BigDecimal(0)) > 0) {
					return 1;
				}else{
					return 3;
				}
			} else if(intereses.compareTo(new BigDecimal(0)) > 0){	
				if ("0".equals(transaccion.getColorLetraRegistro())) {
		//			interes mayor a cero y no es diferencia de cambio
					return ConstantesCobro.TRANSACCION_CON_INTERESES_NO_DIFERENCIA_CAMBIO; // =2
				} else {
		//			interes mayor a cero y moneda en dolares, o sea es el registro informativo de calipso
					return ConstantesCobro.TRANSACCION_DISABLED; //=3
				}
			} else {
		//		interes menor a cero y no es reintegro
				return ConstantesCobro.TRANSACCION_DISABLED; //=3
			}
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 */
	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
		try {
			DescobroDto descobroDto = (DescobroDto) dto;
			
			ShvCobDescobro descobro = descobroDao.buscarDescobro(descobroDto.getIdDescobro());
			
			//Mapeando
			descobro = (ShvCobDescobro) defaultMapeador.map(descobroDto, descobro);
			
			descobro = this.pasarObservacionEnEstado(descobro);
			
			//Guardo el descobro
			descobro = descobroDao.modificar(descobro);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvCobDescobro modificarTransaccion(DescobroDto descobroDto, ShvCobDescobro descobro) throws NegocioExcepcion {

		try {
			//si el modelo es nulo, voy a buscarlo
			if(Validaciones.isObjectNull(descobro)){
				descobro = descobroDao.buscarDescobro(descobroDto.getIdDescobro());
			}
			
			//pregunta si tiene una marca de simulacion, ya que solo se pueden hacer modificaciones si fue simulado
			if(!Validaciones.isObjectNull(descobro.getWorkflow().getWorkflowEstado().getWorkflowMarcas())){
				
				Set<ShvCobTransaccion> setDeTransaccionesDescobro = descobro.getOperacion().getTransaccionesSinDifCambio();
				Set<DescobroTransaccionDto> setDeTransaccionesDto = descobroDto.getTransaccionesSinDifCambio();
				
				//paso las colecciones a un treeset seteandole un comparator para que las ordene
				//de manera que sea mas facil recorrerlas y actualizarlas
				Set<ShvCobTransaccion> setOrdenadoTransaccionesDescobro = new TreeSet<ShvCobTransaccion>(new ComparatorOrdenModificacionShvCobTransaccion());
				Set<DescobroTransaccionDto> setOrdenadoTransaccionesDto = new TreeSet<DescobroTransaccionDto>(new ComparatorOrdenModificacionDescobroTransaccionDto());
				setOrdenadoTransaccionesDescobro.addAll(setDeTransaccionesDescobro);
				setOrdenadoTransaccionesDto.addAll(setDeTransaccionesDto);
				
				if(Validaciones.isCollectionNotEmpty(setOrdenadoTransaccionesDescobro) 
						&& Validaciones.isCollectionNotEmpty(setOrdenadoTransaccionesDto)){
					Iterator<ShvCobTransaccion> itCobro = setOrdenadoTransaccionesDescobro.iterator();
					
					//creo un set nuevo para actualizarlo y pisar el viejo
					Set<ShvCobTransaccion> listaTransaccionesCobroActualizado = new HashSet<ShvCobTransaccion>();
					
					//recorro todas las transacciones como vienen ordendas de la base
					//aunque solo voy a actualizar los debitos que no sean "reintegros No a proxima factura"
					//y envio a ganancias y el medio de pago Debito a proxima factura
					while(itCobro.hasNext()){
						ShvCobTransaccion transaccionDescobro = itCobro.next();
						ShvCobTransaccion modeloActualizado = transaccionDescobro;
						
						Iterator<DescobroTransaccionDto> itCobroDto = setOrdenadoTransaccionesDto.iterator();
						
						//recorro las transacciones de la grilla en pantalla mientras que quede alguna
						while(itCobroDto.hasNext()){
							DescobroTransaccionDto transaccionDto = itCobroDto.next();
							
							if(!transaccionDescobro.getNumeroTransaccion().toString().equals(transaccionDto.getNumeroTransaccion())){
								break;
							}
							//verifico que el acuerdo contracargos no este vacio y sea diferente al de cargos
							if(!Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoFactDestinoContracargos())    ){
//									&& !transaccionDto.getAcuerdoFactDestinoContracargos().equals(transaccionDto.getAcuerdoDestinoCargos())){
								modeloActualizado = descobroTransaccionesMapeador.map(transaccionDto, transaccionDescobro);
							}
							
							//borro el registro de la transaccion de la pantalla, para no recorrerla de nuevo
							itCobroDto.remove();
						}
						
						//me guardo el modelo actualizado en la lista nueva
						listaTransaccionesCobroActualizado.add(modeloActualizado);
					}
					
					//actualizo el modelo
					descobro.getOperacion().getTransacciones().removeAll(setDeTransaccionesDescobro);
					descobro.getOperacion().getTransacciones().addAll(listaTransaccionesCobroActualizado);
					
					//lo impacto en la base
					descobroDao.modificar(descobro);
					return descobro;
				}
				return null;
			}
			 return null;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}

	/**
	 * @author u579607
	 */
	@Override
	public AcuerdoLegado validarAcuerdoContraCliente(String sistemaIngresado, String acuerdoIngresado, List<String> listaIdClientesLegado) throws NegocioExcepcion {

		
		AcuerdoLegado acuerdoLegado = new AcuerdoLegado();
		String numeroCliente = "";
		boolean contains = false;
		
		try {
		
		if(SistemaEnum.MIC.equals(SistemaEnum.getEnumByName(sistemaIngresado))) {
			
			if(!Validaciones.isNullOrEmpty(acuerdoIngresado)){
				
				acuerdoLegado = validarEstadoAcuerdoMic(new Long(acuerdoIngresado));
				
				if(EstadoAcuerdoFacturacionEnum.ACTIVO.equals(acuerdoLegado.getEstado()) ||
						EstadoAcuerdoFacturacionEnum.INCOMUNICADO.equals(acuerdoLegado.getEstado())){
					
					FacConsultaClienteSalida datos = facJmsSyncServicio.consultarClientexAcuerdo(acuerdoIngresado);
					if (!Validaciones.isObjectNull(datos) && !Validaciones.isObjectNull(datos.getNumeroCliente())) {
						numeroCliente = datos.getNumeroCliente().toString();
					}
				}
			}
			
			if(!Validaciones.isNullOrEmpty(numeroCliente) && !listaIdClientesLegado.isEmpty()){
				contains = listaIdClientesLegado.contains(Utilidad.rellenarCerosIzquierda(numeroCliente,10)) || listaIdClientesLegado.contains(numeroCliente);
				acuerdoLegado.setIdClienteLegado(new Long(numeroCliente));
			}
			
			//acuerdoLegado.setMensaje solo estara seteado en caso de errores
			//ver por "else if" el tema de los mensajes si no anda bien
			if(contains){
				return acuerdoLegado;
			}else if(Validaciones.isNullOrEmpty(acuerdoLegado.getMensaje())){
				acuerdoLegado.setMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoNoRelacionado"));
				return acuerdoLegado;
			}else {
				return acuerdoLegado;
			}
			
		}else if(SistemaEnum.CALIPSO.equals(SistemaEnum.getEnumByName(sistemaIngresado))) {

				
				SalidaCalipsoConsultaAcuerdoWS datos = null;
				
				if(!Validaciones.isNullOrEmpty(acuerdoIngresado)){
					
					EntradaCalipsoConsultaAcuerdoWS entrada = new EntradaCalipsoConsultaAcuerdoWS();
					
					entrada.setAcuerdoFacturacion(new BigInteger(acuerdoIngresado));
					
					datos = clienteCalipsoServicio.consultaAcuerdo(entrada);
					
					if (!Validaciones.isObjectNull(datos)) {
						Resultado resultado = datos.getResultado();
						if (!Validaciones.isObjectNull(resultado)) {
							if(TipoResultadoEnum.OK.toString().equals(resultado.getResultado())){
								numeroCliente = datos.getAcuerdo().getIdClienteLegado().toString();
							} else{
//								respuesta.setSuccess(false);
								acuerdoLegado.setMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInexistente"));
								return acuerdoLegado;
							} 
						} 
					}
				}
				
				if(!Validaciones.isNullOrEmpty(numeroCliente) && !listaIdClientesLegado.isEmpty()){
					contains = listaIdClientesLegado.contains(Utilidad.rellenarCerosIzquierda(numeroCliente,10)) || listaIdClientesLegado.contains(numeroCliente);
					acuerdoLegado.setIdClienteLegado(new Long(numeroCliente));
				}
				
				//acuerdoLegado.setMensaje solo estara seteado en caso de errores
				if(contains){
					acuerdoLegado.setEstado(datos.getAcuerdo().getEstado());
					return acuerdoLegado;
				}else{
					acuerdoLegado.setMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoNoRelacionado"));
					return acuerdoLegado;
				}
		}
		
		} catch (Throwable ex) {
			Traza.error(getClass(), ex.getMessage(), ex);
			if (ex instanceof JmsExcepcion) {
				acuerdoLegado.setMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.mic"));
				return acuerdoLegado;
			} else if (ex instanceof WebServiceExcepcion || ex instanceof WebServiceFormatoMensajeExcepcion) {
				acuerdoLegado.setMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.calipso"));
				return acuerdoLegado;
			} else {
				throw new NegocioExcepcion(ex.getMessage(), ex);
			}
		}
		return acuerdoLegado;
	}
	
	public AcuerdoLegado validarEstadoAcuerdoMic(Long numeroAcuerdo) throws NegocioExcepcion{
		
		AcuerdoLegado respuesta = new AcuerdoLegado();
		boolean tieneAcuerdoActivo = false;
		
        FacConsultaAcuerdoSalida salida = facJmsSyncServicio.consultarAcuerdo(numeroAcuerdo);
        EstadoAcuerdoFacturacionEnum estadoAcuerdo = null;
        
        if(!Validaciones.isObjectNull(salida)){
     	  estadoAcuerdo = salida.getPrimerAcuerdoFacturacion().getEstadoAcuerdo();
     	  if (estadoAcuerdo!=null) {
     		  
     		  if(EstadoAcuerdoFacturacionEnum.ACTIVO.codigo().equals(estadoAcuerdo.codigo())
	                   || EstadoAcuerdoFacturacionEnum.INCOMUNICADO.codigo().equals(estadoAcuerdo.codigo())){
     			 respuesta.setEstado(estadoAcuerdo);
     			tieneAcuerdoActivo = true;
	              }
     	  } else {
    		  Traza.auditoria(getClass(), " Nro Acuerdo: " + numeroAcuerdo + ", sin estadoAcuerdo");
    		  
//              respuesta.setSuccess(false);
              respuesta.setMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInexistente"));
              return respuesta;
           }
        } else {
     	  Traza.auditoria(getClass(), " Nro Acuerdo: " + numeroAcuerdo + ", respuesta vacia");
//     	  respuesta.setSuccess(false);
           respuesta.setMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInexistente"));
           return respuesta;
        }
        
//        respuesta.setSuccess(tieneAcuerdoActivo);	preguntar si tiene el estadoEnum que requiere (cuando se necesite este campo)
        
        //Tengo que limpiar la descripcion del error por si ya hubo un error previo
 	   respuesta.setMensaje(Utilidad.EMPTY_STRING);
 	   respuesta.setEstado(estadoAcuerdo);
 	   
        if (tieneAcuerdoActivo){
     	   Traza.auditoria(getClass(), " Nro Acuerdo: " + numeroAcuerdo + ", " + estadoAcuerdo.descripcion());
        } else {
     	   Traza.auditoria(getClass(), " Nro Acuerdo: " + numeroAcuerdo + ", INACTIVO");
     	   respuesta.setMensaje(Propiedades.MENSAJES_PROPIEDADES.getString("error.sistemaDestino.acuerdoInactivo"));
        }
		
		return respuesta;
	}

	/**
	 * Metodo que valida con que campos se puede interactuar en la edicion segun Estado y Usuario.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobroDto
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public String validarEdicionSegunEstadoMarca(DescobroDto descobroDto, UsuarioSesion userSesion) throws NegocioExcepcion{
		
		ShvCobDescobro descobroModelo;
		String resultadoFinal = "";
		Boolean edicionTotal = false;
		Boolean edicionErrorPrimerDescobro = false;
		Boolean edicionParcial = false;
		Boolean sinEdicion = false;
		MarcaEnum marca = null;
		List<MarcaEnum> marcasSimulacionOk = new ArrayList<MarcaEnum>();
		marcasSimulacionOk.add(MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO);
		marcasSimulacionOk.add(MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR);
		try {
			descobroModelo = descobroDao.buscarDescobro(descobroDto.getIdDescobro());

			ShvWfWorkflow workflow = descobroModelo.getWorkflow();
			ShvWfWorkflowEstado workflowEstado = workflow.getWorkflowEstado();
			Estado estado = workflowEstado.getEstado();
			// Recupero solo la ultima marca
			List<MarcaEnum> listaMarcas = this.obtenerUltimasMarcas(descobroDto.getIdDescobro(), descobroModelo, true);
			if (!listaMarcas.isEmpty()) {
				marca = listaMarcas.get(0);
				descobroDto.setMarca(marca);
			}
			
			switch (estado) {
			case DES_EN_EDICION:
				if (Validaciones.isObjectNull(marca)) {
					edicionTotal = true;
				} else if (marcasSimulacionOk.contains(marca)) {
					edicionTotal = true;
				} else if (MarcaEnum.SIMULACION_BATCH_EN_PROCESO.equals(marca)) {
					edicionParcial = true;
				}
				break;
			case DES_PENDIENTE:
				edicionParcial = true;
				break;
			case DES_DESCOBRO_PROCESO:
				edicionParcial = true;
				break;
			case DES_DESCOBRADO:
				edicionParcial = true;
				break;
			case DES_ERROR_EN_PRIMER_DESCOBRO:
				//TODO - para GUIDO de Fabio, vimos con Nadia que tambien se puede simular en el estado DES_ERROR_EN_PRIMER_DESCOBRO
				//asi que agregue la misma casuistica que en edicion, esto es asi porque se supone que la reversion tiro error sin descobrar nada
				//entonces se puede volver a simular
				//Joya dale caña!!
				if (Validaciones.isObjectNull(marca)) {
					edicionErrorPrimerDescobro = true;
				} else if (marcasSimulacionOk.contains(marca)) {
					edicionTotal = true;
				} else if (MarcaEnum.SIMULACION_BATCH_EN_PROCESO.equals(marca)) {
					edicionParcial = true;
				}
				break;
			case DES_EN_ERROR:
				edicionParcial = true;
				break;
			case DES_ANULADO:
				sinEdicion = true;
				break;
			case DES_PENDIENTE_CONFIRMACION_MANUAL:
				edicionParcial = true;
				break;
			default:
				break;
			}
			if (edicionTotal) {
				resultadoFinal = "";
			} else if(edicionParcial) {
				resultadoFinal = "edicionParcial";
			} else if(sinEdicion){
				resultadoFinal = "sinEdicion";
			} else if(edicionErrorPrimerDescobro){
				resultadoFinal = "edicionErrorPrimerDescobro";
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
	 * Metodo que valida habilitacion del boton simular.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param cobroDto
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public boolean validarBtnSimular(DescobroDto descobroDto, UsuarioSesion userSesion) throws NegocioExcepcion{
		
		ShvCobDescobro descobroModelo;
		Boolean habilitarBtnSimular = true;
		Boolean habilitarBtnSimular2 = true;
		Boolean habilitarBtnSimularFinal = true;
		MarcaEnum marca = null;
		try {
			Set<DescobroTransaccionDto> transacciones = descobroDto.getTransacciones();
			for (DescobroTransaccionDto transaccionDto : transacciones) {
				if (EstadoTransaccionEnum.CONFIRMADA.name().equals(transaccionDto.getEstadoTransaccion())){
					if(!Validaciones.isNullEmptyOrDash(transaccionDto.getSistemaAcuerdo())
							&& !Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoDestinoCargos())){
						if(Validaciones.isNullEmptyOrDash(transaccionDto.getSistemaAcuerdoFactDestinoContracargos())
								|| Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoFactDestinoContracargos())){
							transaccionDto.setHabilitarBtnSimular(false);
							habilitarBtnSimular = false;
							break;
						}else{
							transaccionDto.setHabilitarBtnSimular(true);
						}
					}else if(Validaciones.isNullEmptyOrDash(transaccionDto.getSistemaAcuerdo())
							&& Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoDestinoCargos()) &&
							Validaciones.isNullEmptyOrDash(transaccionDto.getSistemaAcuerdoFactDestinoContracargos())
							&& Validaciones.isNullEmptyOrDash(transaccionDto.getAcuerdoFactDestinoContracargos())){
						transaccionDto.setHabilitarBtnSimular(true);
					}
				}
			}
			descobroModelo = descobroDao.buscarDescobro(descobroDto.getIdDescobro());

			ShvWfWorkflow workflow = descobroModelo.getWorkflow();
			ShvWfWorkflowEstado workflowEstado = workflow.getWorkflowEstado();
			Estado estado = workflowEstado.getEstado();
			
			// Recupero solo la ultima marca
			List<MarcaEnum> listaMarcas = this.obtenerUltimasMarcas(descobroDto.getIdDescobro(), descobroModelo, true);
			if (!listaMarcas.isEmpty()) {
				marca = listaMarcas.get(0);
			}
			if( MarcaEnum.SIMULACION_BATCH_EN_PROCESO.equals(marca)){
				habilitarBtnSimular2 = false;
			}else{
				if(estado.equals(Estado.DES_EN_EDICION)){
					habilitarBtnSimular2 = true;
				}
			}

		}catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		if(habilitarBtnSimular && habilitarBtnSimular2){
			habilitarBtnSimularFinal = true;
		}else{
			habilitarBtnSimularFinal = false;
		}
		return habilitarBtnSimularFinal;
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
	public Long crear(DTO dto) throws NegocioExcepcion {
		return null;
	}
	
	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void eliminarTareaAnularDescobro(Long idDescobro, String usuarioModificacion) throws NegocioExcepcion {
		
		
		try {
			ShvCobDescobro descobroModelo = descobroDao.buscarDescobro(idDescobro);
			
			if(!Validaciones.isObjectNull(descobroModelo)){
				
				ShvWfWorkflow workflow = descobroModelo.getWorkflow();
				
				if(!Validaciones.isObjectNull(workflow)){
					
					//Elimino la tarea
					eliminarTareasSimulacion(null, descobroModelo, usuarioModificacion);
					
					//Anulo el descobro
					anularDescobro(workflow, usuarioModificacion);
					
				}
			}
			
	    } catch (NegocioExcepcion | PersistenciaExcepcion e) {
	        throw new NegocioExcepcion(e);
	    }
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param idDescobro
	 * @param usuarioModificacion
	 * @throws NegocioExcepcion
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void eliminarTareaErrorPrimerDescobroAnularDescobro(Long idDescobro, String usuarioModificacion) throws NegocioExcepcion {
		
		
		try {
			ShvCobDescobro descobroModelo = descobroDao.buscarDescobro(idDescobro);
			
			if(!Validaciones.isObjectNull(descobroModelo)){
				
				ShvWfWorkflow workflow = descobroModelo.getWorkflow();
				
				if(!Validaciones.isObjectNull(workflow)){
					
					//Elimino la tarea
					eliminarTareasErrorPrimerDescobro(null, descobroModelo, usuarioModificacion);
					
					//Anulo el descobro
					anularDescobro(workflow, usuarioModificacion);
					
				}
			}
			
		} catch (NegocioExcepcion | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param workflowDescobro
	 * @param usuarioModificacion
	 * @throws NegocioExcepcion
	 */
	private void anularDescobro(ShvWfWorkflow workflowDescobro, String usuarioModificacion) throws NegocioExcepcion{
		
		try {
				
			if(!Validaciones.isObjectNull(workflowDescobro)){
				
				// Anulo el descobro, verificando previamente en que estado estoy
				switch (workflowDescobro.getEstado()) {
				case DES_EN_EDICION:
					workflowDescobros.anularDescobroEnEdicion(workflowDescobro, "", usuarioModificacion);
					break;
				default: break;
				}
				
			}
			
	    } catch (WorkflowExcepcion e) {
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

	
	@Override

	public DescobroDto simularDescobro(DescobroDto descobroDto, List<ComprobanteDto> comprobantesAGuardar, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		DescobroDto descobroSalida = guardarDescobro(descobroDto, comprobantesAGuardar, userSesion);
		
		descobroSalida.setSimularDescobro(descobroBatchSimulacionServicio.simularDescobro(descobroSalida.getIdDescobro()));
		return descobroSalida; 
	}

	@Override
	public DescobroDto guardarDescobro(DescobroDto descobroDtoEntrada, List<ComprobanteDto> comprobantesAGuardar, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		DescobroDto descobroDtoSalida = descobroDtoEntrada;
		Date fecha = new Date();
		ComprobanteDto crearAdjuntoDescobro = new ComprobanteDto();
		List<ComprobanteDto> listaAdjuntos = new ArrayList<ComprobanteDto>();
		descobroDtoEntrada.setFechaUltimaModificacion(fecha);
		descobroDtoEntrada.setUsuarioModificacion(userSesion.getIdUsuario());
		
		if (Validaciones.isObjectNull(descobroDtoEntrada.getIdDescobro()) && Validaciones.isObjectNull(descobroDtoEntrada.getIdOperacionDescobro())) {
			descobroDtoEntrada.setFechaAlta(fecha);
			descobroDtoEntrada.setUsuarioAlta(userSesion.getIdUsuario());
			
			//Metodo que crea el descobro
			ShvCobDescobro desCobro = crearDescobroDto(descobroDtoEntrada, descobroDtoEntrada.getIdCobro(), userSesion);
			
			// Medicion de tiempo al inicio del procesamiento
			double fechaHoraInicioNanoTime = System.nanoTime();
			Date fechaHoraInicio = new Date();
			
			descobroDtoSalida = (DescobroDto) defaultMapeador.map(desCobro);
			
			Traza.loguearInfoProcesamiento(this.getClass(),"crearDescobroDto mapeo modelo a dto para actualizar pantalla", fechaHoraInicio, fechaHoraInicioNanoTime, 0);
			
			desCobro = modificarTransaccion(descobroDtoSalida, desCobro);
			
			descobroDtoSalida.setIdDescobro(desCobro.getIdDescobro());
			descobroDtoSalida.setIdOperacionDescobro(desCobro.getOperacion().getIdOperacion());
			
			//Elimino la tarea
			eliminarTareasErrorPrimerDescobro(descobroDtoSalida.getIdDescobro(), null, userSesion.getIdUsuario());
			
			if (!comprobantesAGuardar.isEmpty()) {
				for (ComprobanteDto  comprobante : comprobantesAGuardar) {
					crearAdjuntoDescobro = crearAdjuntoDescobro(descobroDtoSalida.getIdDescobro(), comprobante);
				}
			}
			descobroDtoSalida.setGuardadoOk(true);
			descobroDtoSalida.setPrimerDescobro(true);
			
		} else if (!Validaciones.isObjectNull(descobroDtoEntrada.getIdDescobro()) && !Validaciones.isObjectNull(descobroDtoEntrada.getIdOperacionDescobro())) {
				
			modificar(descobroDtoEntrada);
			
			//Elimino la tarea
			eliminarTareasErrorPrimerDescobro(descobroDtoEntrada.getIdDescobro(), null, userSesion.getIdUsuario());
			
			if(!comprobantesAGuardar.isEmpty()){
				for (ComprobanteDto  comprobante : comprobantesAGuardar) {
					Long idComprobante = comprobante.getIdComprobante();
					crearAdjuntoDescobro = crearAdjuntoDescobro(descobroDtoSalida.getIdDescobro(), comprobante);
					crearAdjuntoDescobro.setIdPantallaComprobante(idComprobante);
					listaAdjuntos.add(crearAdjuntoDescobro);
				}
			}
			
			descobroDtoSalida.setGuardadoOk(true);
			descobroDtoSalida.setPrimerDescobro(false);
			
		} else {
			//Notificar el error
			String mensaje = "El Id Descobro o el Id Operacion Descobro no tiene valor. "
					+ " Id Descobro: " + descobroDtoEntrada.getIdDescobro() 
					+ " Id Operacion Descobro: " + descobroDtoEntrada.getIdOperacionDescobro();
			Traza.error(getClass(), mensaje);
			throw new NegocioExcepcion(mensaje);
		}
		
		if(!listaAdjuntos.isEmpty()){
			descobroDtoSalida.setListaComprobantes(listaAdjuntos);
		}else{
			descobroDtoSalida.setListaComprobantes(comprobantesAGuardar);
		}
		return descobroDtoSalida;
	}
	
	
	@Override
	public void eliminarTareasSimulacion(Long idDescobro, ShvCobDescobro descobroModelo, String idUsuario) throws NegocioExcepcion {
		try {
			ShvWfWorkflow workflow = new ShvWfWorkflow();
			
			if(!Validaciones.isObjectNull(descobroModelo)){
				workflow = descobroModelo.getWorkflow();
			} else {
				if(!Validaciones.isObjectNull(idDescobro)){
					descobroModelo = descobroDao.buscarDescobro(idDescobro);
					workflow = descobroModelo.getWorkflow();
				}
			}
			
			List<ShvWfTarea> listaTareas = tareaServicio.buscarTareasPendientes(workflow.getIdWorkflow());
			
			if(Validaciones.isCollectionNotEmpty(listaTareas)){
				for (ShvWfTarea shvWfTarea : listaTareas) {
					switch (shvWfTarea.getTipoTarea()) {
					
					//si la tarea es de simulacion finalizada con exito o con error se elimina
					case DES_RES_SIM_OK :
						tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), workflow.getIdWorkflow(), new Date(), idUsuario, null);
						break;
					case DES_RES_SIM_ERR : 
						tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), workflow.getIdWorkflow(), new Date(), idUsuario, null);
						break;
					default:
						break;
					}
					
				}
				
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 */
	@Override
	public void eliminarTareasErrorPrimerDescobro(Long idDescobro, ShvCobDescobro descobroModelo, String idUsuario) throws NegocioExcepcion {
		try {
			ShvWfWorkflow workflow = new ShvWfWorkflow();
			
			if(!Validaciones.isObjectNull(descobroModelo)){
				workflow = descobroModelo.getWorkflow();
			} else {
				if(!Validaciones.isObjectNull(idDescobro)){
					descobroModelo = descobroDao.buscarDescobro(idDescobro);
					workflow = descobroModelo.getWorkflow();
				}
			}
			
			List<ShvWfTarea> listaTareas = tareaServicio.buscarTareasPendientes(workflow.getIdWorkflow());
			
			if(Validaciones.isCollectionNotEmpty(listaTareas)){
				for (ShvWfTarea shvWfTarea : listaTareas) {
					switch (shvWfTarea.getTipoTarea()) {
					case DES_IMP_1ER_ERR :
						if(!TipoTareaEstadoEnum.FINALIZADA.equals(shvWfTarea.getEstado())){
							tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), workflow.getIdWorkflow(), new Date(), idUsuario, null);
						}
						break;
					default:
						break;
					}
					
				}
				
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 */
	@Override
	public void eliminarTareasEnErrorDescobro(Long idDescobro, ShvCobDescobro descobroModelo, String idUsuario) throws NegocioExcepcion {
		try {
			ShvWfWorkflow workflow = new ShvWfWorkflow();
			
			if(!Validaciones.isObjectNull(descobroModelo)){
				workflow = descobroModelo.getWorkflow();
			} else {
				if(!Validaciones.isObjectNull(idDescobro)){
					descobroModelo = descobroDao.buscarDescobro(idDescobro);
					workflow = descobroModelo.getWorkflow();
				}
			}
			
			List<ShvWfTarea> listaTareas = tareaServicio.buscarTareasPendientes(workflow.getIdWorkflow());
			
			if(Validaciones.isCollectionNotEmpty(listaTareas)){
				for (ShvWfTarea shvWfTarea : listaTareas) {
					switch (shvWfTarea.getTipoTarea()) {
					case DES_IMP_ERR :
						if(!TipoTareaEstadoEnum.FINALIZADA.equals(shvWfTarea.getEstado())){
							tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), workflow.getIdWorkflow(), new Date(), idUsuario, null);
						}
						break;
					default:
						break;
					}
					
				}
				
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * 
	 * Retorna un descobro por el idOperacionCobro.
	 */
	public List<ShvCobDescobroOperacionRelacionada> buscarOperacionRelacionadaDescobroPorIdOperacionCobro(Long idOperacionCobro) throws NegocioExcepcion {
		try {
			List<ShvCobDescobroOperacionRelacionada> buscarOperacionRelacionadaDescobroPorIdOperacionCobro = descobroDao.buscarOperacionRelacionadaDescobroPorIdOperacionCobro(idOperacionCobro);

			return buscarOperacionRelacionadaDescobroPorIdOperacionCobro;
		} catch (Throwable e) {
			throw new NegocioExcepcion(e);
		}
	}

	/**
	 * @author u572487 Guido.Settecerze
	 * 
	 * Metodo que valida si la reversion esta en el Reversión En Edición, Reversión en Error o Error en Primera Reversión.
	 */
	public boolean validarEstadoIdReversionPadre(Long idOperacionDescobro) throws NegocioExcepcion {
		boolean estaEnEstosEstados = false;
		try {
			ShvCobDescobro descobro = descobroDao.buscarDescobroPorIdOperacion(idOperacionDescobro);
			Estado estado = descobro.getWorkflow().getWorkflowEstado().getEstado();
			if(estado.equals(Estado.DES_EN_EDICION) || estado.equals(Estado.DES_EN_ERROR) || estado.equals(Estado.DES_ERROR_EN_PRIMER_DESCOBRO)){
				estaEnEstosEstados = true;
			}

		} catch (Throwable e) {
			throw new NegocioExcepcion(e);
		}

		return estaEnEstosEstados;
	}

	/**
	 * @author u572487 Guido.Settecerze
	 * 
	 * Metodo que valida si la reversion existe.
	 */
	public boolean validarSiExisteElIdReversion(Long idOperacionDescobro) throws NegocioExcepcion {
		boolean existe = false;
		try {
			ShvCobDescobro descobro = descobroDao.buscarDescobroPorIdOperacion(idOperacionDescobro);
			if(!Validaciones.isObjectNull(descobro)){
				existe = true;
			}else{
				existe = false;
			}
		} catch (Throwable e) {
			throw new NegocioExcepcion(e);
		}

		return existe;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param cobroDto
	 * @param cobroModelo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String obtenerObseHistorial(DescobroDto descobroDto, ShvCobDescobro descobroModelo) throws NegocioExcepcion {
		StringBuffer str = new StringBuffer();
		try {
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			String usuarioNombreBatch = parametroServicio.getValorTexto(Constantes.USUARIO_NOMBRE_BATCH);

			if (Validaciones.isObjectNull(descobroModelo)) {
				descobroModelo = this.obtenerModeloDescobro(descobroDto.getIdDescobro());
			}
			List<ShvWfWorkflowEstadoHist> historial = descobroModelo.getWorkflow().getListaWorkflowEstadoHistOrdenadaPorFecha();

			boolean descobroProceso = false;
			for (ShvWfWorkflowEstadoHist historico : historial) {
				
				if (!Estado.DES_PEND_PROCESAR_MIC.equals(historico.getEstado())
						&& !Estado.DES_PENDIENTE_MIC.equals(historico.getEstado())){
						
						if (!Estado.DES_DESCOBRO_PROCESO.equals(historico.getEstado())){
							str.append(this.returnObservacion(historico, usuarioBatch, usuarioNombreBatch));
							
						} else
						if (Estado.DES_DESCOBRO_PROCESO.equals(historico.getEstado()) && !descobroProceso){
							str.append(this.returnObservacion(historico, usuarioBatch, usuarioNombreBatch));
							descobroProceso = true;
						}
						
					}
				str.append(this.returnObservacion(historico, usuarioBatch, usuarioNombreBatch));
			}


			if (!Validaciones.isObjectNull(descobroModelo.getWorkflow().getShvWfWorkflowEstado())) {
				if (descobroModelo.getWorkflow().getShvWfWorkflowEstado().iterator().hasNext()) {
//					if (str.length() > 0) {
//						str.append(Constantes.RETORNO_HTML);
//					}
					str.append(
						this.returnObservacion(
								descobroModelo.getWorkflow().getShvWfWorkflowEstado().iterator().next(),
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
	 * @author u572487 Guido.Settecerze
	 * @param datosModificados
	 * @param fecha
	 * @param usuarioModificacion
	 * @param usuarioBatch
	 * @param usuarioNombreBatch
	 * @return
	 * @throws LdapExcepcion
	 */
	private String returnObservacion(
		String datosModificados,
		Date fecha,
		String usuarioModificacion,
		String usuarioBatch,
		String usuarioNombreBatch
			) throws LdapExcepcion {
		StringBuffer str = new StringBuffer();
		boolean tieneObservacion = false;
		if(StringUtils.contains(datosModificados, "[Observaciones](Valor: ")){
			tieneObservacion = true;
			String replace = datosModificados.replace("[Observaciones](Valor: ", "");
			int lastIndexOf = replace.lastIndexOf(")");
			datosModificados =  replace.substring(0, lastIndexOf);
		}
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
				if (!Validaciones.isNullOrEmpty(datosModificados)) {
					if(tieneObservacion){
						String[] split = datosModificados.split("<br>");
						if(split.length>=2){
							for( int i = 1; i <= split.length - 1; i++)
							{
								str.append(split[i]);
							}
						}else{
							str.append(datosModificados);
						}
					}else{
						str.append(datosModificados);
					}
				}
			}
			str.append(Constantes.RETORNO_HTML);
		}
		return str.toString();
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param wfEstado
	 * @param usuarioBatch
	 * @param usuarioNombreBatch
	 * @return
	 * @throws LdapExcepcion
	 */
	private String returnObservacion(ShvWfWorkflowEstadoHist wfEstado,String usuarioBatch, String usuarioNombreBatch) throws LdapExcepcion {
		return this.returnObservacion(
			wfEstado.getDatosModificados(),
			wfEstado.getFechaModificacion(),
			wfEstado.getUsuarioModificacion(),
			usuarioBatch,
			usuarioNombreBatch
		);
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param wfEstado
	 * @param usuarioBatch
	 * @param usuarioNombreBatch
	 * @return
	 * @throws LdapExcepcion
	 */
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
	 * No uso public ShvCobDescobro pasarObservacionEnEstado(ShvCobDescobro descobro) throws NegocioExcepcion {
	 * Porque me duplica el estado Historico.
	 * TODO ABRIA QUE VER!!
	 * 
	 */
	public ShvCobDescobro pasarObservacionEnEstadoSinCrearHistorial(ShvCobDescobro descobro) throws NegocioExcepcion {
		ShvWfWorkflowEstado shvWorkflowEstado = descobro.getWorkflow().getWorkflowEstado();
		StringBuffer str = new StringBuffer();
		UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUid(descobro.getUsuarioUltimaModificacion());
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
		str.append(descobro.getObservacion());
		shvWorkflowEstado.setDatosModificados(
			 str.toString()
		);
		
		descobro.setObservacion(Utilidad.EMPTY_STRING);
		return descobro;
	}
	/**
	 * @author u572487 Guido.Settecerze
	 * @param cobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvCobDescobro pasarObservacionEnEstado(ShvCobDescobro descobro) throws NegocioExcepcion {
		ShvWfWorkflow workflow = descobro.getWorkflow();
		ShvWfWorkflowEstado shvWorkflowEstado = workflow.getWorkflowEstado();
		Estado estado = shvWorkflowEstado.getEstado();
		StringBuffer str = new StringBuffer();
		
		UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUid(descobro.getUsuarioUltimaModificacion());
		if (Validaciones.isNullOrEmpty(shvWorkflowEstado.getDatosModificados())) {
			str.append(Utilidad.EMPTY_STRING);
		} else {
//			str.append(shvWorkflowEstado.getDatosModificados());
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
		if(!Validaciones.isNullEmptyOrDash(descobro.getObservacion())){
			str.append("[Observaciones](Valor: ");
			str.append(descobro.getObservacion());
			str.append(") ");
		}
		boolean tieneEstosEstados = false;
			if(estado.equals(Estado.DES_PENDIENTE) ||
					estado.equals(Estado.DES_DESCOBRO_PROCESO) ||
					estado.equals(Estado.DES_DESCOBRADO) ||
					estado.equals(Estado.DES_EN_ERROR) ||
					estado.equals(Estado.DES_PENDIENTE_CONFIRMACION_MANUAL)){
				tieneEstosEstados = true;
		}
		if((shvWorkflowEstado.getEstado() == Estado.DES_EN_EDICION || shvWorkflowEstado.getEstado() == Estado.DES_ERROR_EN_PRIMER_DESCOBRO)){
			descobro.setObservacion(descobro.getObservacion());
		}else if (tieneEstosEstados){
			if(!Validaciones.isNullEmptyOrDash(descobro.getObservacion()) 
					&& !Validaciones.isNullOrEmpty(str.toString())){
				workflow = workflowService.actualizarWorkflowSinGuardar(workflow, str.toString(), descobro.getUsuarioUltimaModificacion());
				descobro.setObservacion(Utilidad.EMPTY_STRING);
			}
		}
		
		return descobro;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param idDescobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarMostrarObservacionAnterior(Long idDescobro) throws NegocioExcepcion {
		ShvCobDescobro descobro;
		boolean mostrarObAnterior = false;
		try {
			descobro = descobroDao.buscarDescobro(idDescobro);
			ShvWfWorkflowEstado shvWorkflowEstado = descobro.getWorkflow().getWorkflowEstado();
			Estado estado = shvWorkflowEstado.getEstado();
			if(estado.equals(Estado.DES_PENDIENTE) ||
					estado.equals(Estado.DES_DESCOBRO_PROCESO) ||
					estado.equals(Estado.DES_DESCOBRADO) ||
					estado.equals(Estado.DES_EN_ERROR)){
				mostrarObAnterior = true;
			} else if((estado == Estado.DES_EN_EDICION || estado == Estado.DES_ERROR_EN_PRIMER_DESCOBRO)){
				mostrarObAnterior = false;
			}
		}  catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
		return mostrarObAnterior;
	}
	/**
	 * @author u572487 Guido.Settecerze
	 * @param idAdjunto
	 * @throws NegocioExcepcion
	 */
	@Override
	public void eliminarAdjuntoDescobro(Long idAdjunto) throws NegocioExcepcion {
		try {
			ShvDocDocumentoAdjunto docAdjunto = documentoAdjuntoDao.buscarDocumentoAdjunto(idAdjunto);
			descobroDao.eliminarDocumentoAdjuntoDelDescobro(docAdjunto);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}

	public boolean verificaErrorEnMensajeTransaccion(Set<DescobroTransaccionDto> transacciones) {
		
		boolean transaccionesOK = true;
		
		for (DescobroTransaccionDto transaccion : transacciones) {
			if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(EstadoTransaccionEnum.getEnumByDescripcion(transaccion.getEstadoTransaccion()))){
				if(TipoMensajeEstadoEnum.ERR.name().equals(transaccion.getTipoMensajeTransaccion()) 
						||TipoMensajeEstadoEnum.ERR.name().equals(transaccion.getTipoMensajeCredito()) 
								|| TipoMensajeEstadoEnum.ERR.name().equals(transaccion.getTipoMensajeDebito())){
					transaccionesOK = false;
					return transaccionesOK;
				}
			}
		}
		
		return transaccionesOK;
	}
	
	public ShvCobDescobro blanquearMensajesTransacciones(ShvCobDescobro descobroModelo) {
		
		for (ShvCobTransaccion transaccion : descobroModelo.getOperacion().getTransacciones()) {
			
			if (!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccion.getEstadoProcesamiento())){
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
		}
		return descobroModelo;
	}
	
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public DescobroDto imputarDescobro(DescobroDto descobroDto, UsuarioSesion userSesion) throws NegocioExcepcion {
		ShvCobDescobro descobroModelo;
		try {
			String idUsuario = userSesion.getIdUsuario();
			
			//obtengo el descobro actualizado de la base
			descobroModelo = descobroDao.buscarDescobro(descobroDto.getIdDescobro());
			
			//blanqueo los mensajes que quedaron de la simulacion
			descobroModelo = blanquearMensajesTransacciones(descobroModelo);
			
			//elimino la tarea de revision de imputacion
			eliminarTareasImputacion(descobroModelo, userSesion);
			
			//actualizo los datos de imputacion
			descobroModelo.setUsuarioDerivacion(idUsuario);
			descobroModelo.setFechaDerivacion(new Date());
			descobroModelo.setUsuarioUltimaModificacion(idUsuario);
			descobroModelo.setFechaUltimaModificacion(new Date());
			
			ShvWfWorkflow workflow = descobroModelo.getWorkflow();
			
			if(Estado.DES_ERROR_EN_PRIMER_DESCOBRO.equals(workflow.getEstado())){
				descobroModelo = revivirDescobroConErrores(descobroModelo, userSesion);
			}else {
				// En esta transicion las observaciones presentes se persisten en el estado corriente
				workflow = workflowDescobros.solicitarDescobro(workflow, descobroModelo.getObservacion(), idUsuario);
			}			
			
			descobroModelo.setWorkflow(workflow);
			
			// y se eliminan del modelo
			descobroModelo.setObservacion(Utilidad.EMPTY_STRING);
			descobroModelo = descobroDao.modificar(descobroModelo);
			
			//seteo el nuevo estado en el dto para poder mostrarlo en pantalla
			descobroDto.setEstado(workflow.getEstado());
			
			return descobroDto;
		}catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public void eliminarTareasImputacion(ShvCobDescobro descobroModelo, UsuarioSesion usuarioModificacion) throws NegocioExcepcion {
		
			String idUsuario = usuarioModificacion.getIdUsuario();
			
			ShvWfWorkflow workflow = descobroModelo.getWorkflow();
			
			List<ShvWfTarea> tarea = tareaServicio.buscarTareasPendientes(workflow.getIdWorkflow());
//			TODO - a Fabio 1Reversion PENDIENTE IMPUTACION finalizar tarea de imputacion
			for (ShvWfTarea shvWfTarea : tarea) {
				switch (shvWfTarea.getTipoTarea()) {
				
				case DES_IMP_1ER_ERR:
				case DES_IMP_ERR: 
					tareaServicio.finalizarTareaCorrecto(shvWfTarea.getTipoTarea(), workflow.getIdWorkflow(), new Date(), idUsuario, null);
					break;
				default:
					break;
				}
			}
	}
	
	/**
	 * Metodo que informa a contabilidad, scard
	 * @param descobro
	 * @throws NegocioExcepcion
	 */
	public void informarAContabilidadYScard(ShvCobDescobro descobro) throws NegocioExcepcion {

		if(descobro.getWorkflow().getEstado().equals(Estado.DES_DESCOBRADO)){
			//Scard
			scardServicio.inicializarDocumentoOperacionDescobro(descobro.getOperacion());
			
			// Contabilidad
			
			//Lista de valores del cobro
			HashMap<Long,VistaSoporteResultadoBusquedaValor> listaValores = new HashMap<Long,VistaSoporteResultadoBusquedaValor>();
			
			for (ShvCobTransaccion transaccion : descobro.getOperacion().getTransaccionesSinDifCambio()) {

				//
				// Ahora solo enviamos a contabilizar las transacciones en estado Descobrada, dado que podemos tener
				// reversiones parciales.
				//
				if (EstadoTransaccionEnum.DESCOBRO.equals(transaccion.getEstadoProcesamiento())) {

					String transaccionFormateada = "";
					
					if (!transaccion.haySaldoAPagarOSaldoACobrar()){
						
						if (MonedaEnum.PES.equals(descobro.getMonedaOperacion())){
							
							List<ShvCobTransaccion> listaTransaccionesDC = null;
							if (!Validaciones.isObjectNull(transaccion.getFactura()) 
									&& transaccion.getFactura() instanceof ShvCobFacturaCalipso
									&& MonedaEnum.DOL.equals(((ShvCobFacturaCalipso)transaccion.getFactura()).getMoneda())){
								
								listaTransaccionesDC = descobro.getTransaccionesDC(transaccion.getNumeroTransaccion());
							}
							
							//Si hay transacciones por diferencia de cambio, contabilizo los documentos de dichas transacciones
							
							if (Validaciones.isCollectionNotEmpty(listaTransaccionesDC)){
								
								for (ShvCobTransaccion transaccionDC : listaTransaccionesDC) {
									transaccionFormateada = transaccionDC.getOperacionTransaccionFormateadoDCDescobros();
									contabilizarDocumentos(null, descobro, transaccionDC, listaValores, true, transaccionFormateada);
								}
							} else {
								//Sino contabilizo los documentos normalmente.
								transaccionFormateada = transaccion.getOperacionTransaccionFormateadoDescobro();
								contabilizarDocumentos(null, descobro, transaccion, listaValores, false, transaccionFormateada);
								
							}
						} else {
							//TODO: ESTE IF NO VA, PERO SI CONTABILIZARDOCUMENTOS
							if (!EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO.equals(transaccion.getEstadoProcesamiento())
									&& !EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM.equals(transaccion.getEstadoProcesamiento())){
								transaccionFormateada = transaccion.getOperacionTransaccionFormateadoDescobro();
								contabilizarDocumentos(null, descobro, transaccion, listaValores, false, transaccionFormateada);
							}
						}
					}
				}
			}
		}
		// Fin Contabilidad
	}
	
	private void contabilizarDocumentos(ShvCobCobro cobro, ShvCobDescobro descobro, ShvCobTransaccion transaccion, HashMap<Long, VistaSoporteResultadoBusquedaValor> listaValores, boolean esDiferenciaCambio, String transaccionFormateada)throws NegocioExcepcion {

		ShvCobFactura factura = null;
		
		BigDecimal tipoCambioFactura = null;
		BigDecimal tipoCambioMedioPago = null;
		BigDecimal importeTotalMedioPagoEnPesos = null;
		BigDecimal importeTotalFacturaEnPesos = null;
		
		if (!esDiferenciaCambio){
			factura = transaccion.getFactura();
			
			if (!Validaciones.isObjectNull(factura)){
				
				if (!(factura instanceof ShvCobFacturaUsuario)){
					if (MonedaEnum.DOL.equals(descobro.getMonedaOperacion())){
						//Si la moneda del cobro es en DOLARES, guardo el tipo de cambio de la factura
						tipoCambioFactura = ((ShvCobFacturaCalipso) factura).getTipoCambio();
						importeTotalFacturaEnPesos = factura.getImporteCobrar().multiply(tipoCambioFactura);
					}
				
					Traza.advertencia(getClass(), "contabilizando la factura " + factura.getIdFactura());
					cobroBatchSoporteImputacionContabilidadServicio.contabilizarFactura(factura, false, descobro.getWorkflow().getUsuarioAlta() ,Long.valueOf(descobro.getIdDescobro()), transaccionFormateada, descobro.getWorkflow().getUsuarioAlta());
				}
			}
		} else {
			factura = transaccion.getFacturaDC();
			
			if (!Validaciones.isObjectNull(factura)){
				Traza.advertencia(getClass(), "contabilizando la factura " + factura.getIdFactura());
				cobroBatchSoporteImputacionContabilidadServicio.contabilizarFacturaEnMonedaOrigen((ShvCobFacturaCalipso)factura, false,descobro.getWorkflow().getUsuarioAlta(),
						descobro.getIdDescobro(),String.valueOf(transaccion.getOperacionTransaccionFormateadoDCDescobros())
						, descobro.getWorkflow().getUsuarioAlta());
			}
		}
		
		
		for (ShvCobMedioPago mp : transaccion.getMediosPago()) {
			Traza.advertencia(getClass(), "contabilizando el medio de pago " + mp.getIdMedioPago());
			
			VistaSoporteResultadoBusquedaValor valor = null;
			
			//Si es un medio de pago Shiva
			if(mp instanceof ShvCobMedioPagoShiva){
				ShvCobMedioPagoShiva mpShiva = ((ShvCobMedioPagoShiva)mp);
				if(listaValores.containsKey(mpShiva.getIdValor())){
					//Si esta en la lista de valores lo saco de la lista
					valor=listaValores.get(mpShiva.getIdValor());
				} else {
					//Si no esta en la lista de valores lo busco en la base y lo agrego a la lista
					VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
					filtro.setIdValor(String.valueOf(mpShiva.getIdValor()));
					
					List<VistaSoporteResultadoBusquedaValor> lista = vistaSoporteServicio.buscarValores(filtro);
					if((Validaciones.isCollectionNotEmpty(lista))){
						valor = lista.get(0);
						listaValores.put(valor.getIdValor(),valor);
					}
					
				}
			}
			
			//En caso de tener medios de pago proveedores o liquido producto, como no tengo el documento cap en el descobro, busco el documento cap del cobro
//			ShvCobMedioPagoDocumentoCap docCap = null;
//			if (mp instanceof ShvCobMedioPagoCompensacionProveedor
//					|| mp instanceof ShvCobMedioPagoCompensacionLiquidoProducto){
//				try {
//					docCap = documentoCapDao.buscarDocumentoCapPorIdCobro(descobro.getIdCobro());
//				} catch (PersistenciaExcepcion e) {
//					throw new NegocioExcepcion(e.getMessage(), e);
//				}
//			}
			
			if (!Validaciones.isObjectNull(factura)){
				if (!(factura instanceof ShvCobFacturaUsuario)) {
					if (!esDiferenciaCambio){
						
						if (MonedaEnum.DOL.equals(descobro.getMonedaOperacion())){
							
							tipoCambioMedioPago = cobroBatchSoporteImputacionContabilidadServicio.calcularTipoCambioMedioPago(mp);
							
							if (Validaciones.isObjectNull(importeTotalMedioPagoEnPesos)){
								
								importeTotalMedioPagoEnPesos = new BigDecimal(0);
							}
							
							importeTotalMedioPagoEnPesos = importeTotalMedioPagoEnPesos.add(mp.getImporte().multiply(tipoCambioMedioPago));
						}
						
						cobroBatchSoporteImputacionContabilidadServicio.contabilizarMedioPagoAsociadoFactura(mp, false, descobro.getWorkflow().getUsuarioAlta(), descobro.getIdDescobro(), transaccionFormateada, valor,false);
					}else {
						cobroBatchSoporteImputacionContabilidadServicio.contabilizarMedioPagoAsociadoFactura(mp, false, descobro.getWorkflow().getUsuarioAlta(), descobro.getIdDescobro(), transaccionFormateada, valor,true);
					}
				} else {
					cobroBatchSoporteImputacionContabilidadServicio.contabilizarMedioPagoAsociadoFacturaUsuario(mp, false, descobro.getWorkflow().getUsuarioAlta(), null, transaccionFormateada, descobro.getIdDescobro(), valor);
				}
			} else {
				// transaccion sin factura
				if (!esDiferenciaCambio){
					cobroBatchSoporteImputacionContabilidadServicio.contabilizarMedioPagoAsociadoTratamiento(mp, false, descobro.getWorkflow().getUsuarioAlta(), cobro, transaccionFormateada, descobro.getIdDescobro(), valor);
				} else {
					cobroBatchSoporteImputacionContabilidadServicio.contabilizarMedioPagoAsociadoTratamiento(mp, false, descobro.getWorkflow().getUsuarioAlta(), cobro, transaccionFormateada, descobro.getIdDescobro(), valor);
				}
			}
		}
		
		//pregunto si los importes en pesos de factura y mp no son nulos y si son diferentes, debo hacer el ajuste contable
		if (!Validaciones.isObjectNull(importeTotalFacturaEnPesos) && !Validaciones.isObjectNull(importeTotalMedioPagoEnPesos)
				&& !importeTotalFacturaEnPesos.equals(importeTotalMedioPagoEnPesos)){
			
			BigDecimal diferenciaImporteEnPesos = new BigDecimal(0);
			
			//Si el importe de la factura en pesos es mayor al importe del medio de pago en pesos, diferenciaImporteEnPesos tiene que ser negativo.
			if (importeTotalFacturaEnPesos.compareTo(importeTotalMedioPagoEnPesos) > 0 ){
				diferenciaImporteEnPesos = importeTotalMedioPagoEnPesos.subtract(importeTotalFacturaEnPesos);
				diferenciaImporteEnPesos = diferenciaImporteEnPesos.setScale(2, RoundingMode.HALF_EVEN);
				
				cobroBatchSoporteImputacionContabilidadServicio.contabilizarDocumentoEnDolar(factura,diferenciaImporteEnPesos,
						descobro.getIdDescobro(),transaccionFormateada
						, descobro.getWorkflow().getUsuarioAlta(),false);
			
				//Si el importe del medio de pago en pesos es mayor al de la factura en pesos , diferenciaImporteEnPesos tiene que ser positivo.
			} else if (importeTotalMedioPagoEnPesos.compareTo(importeTotalFacturaEnPesos) > 0 ){
				
				diferenciaImporteEnPesos = importeTotalMedioPagoEnPesos.subtract(importeTotalFacturaEnPesos);
				diferenciaImporteEnPesos = diferenciaImporteEnPesos.setScale(2, RoundingMode.HALF_EVEN);
				
				cobroBatchSoporteImputacionContabilidadServicio.contabilizarDocumentoEnDolar(factura,diferenciaImporteEnPesos,
						descobro.getIdDescobro(),transaccionFormateada
						, descobro.getWorkflow().getUsuarioAlta(),false);
			}
			
		}
	}

	public List<DescobroTransaccionDto> transaccionesMapeo (List<CobroTransaccionDto> cobroTransacciones) throws NegocioExcepcion{

		List<DescobroTransaccionDto> listaTransacciones = new ArrayList<DescobroTransaccionDto>();
		
		for (CobroTransaccionDto cobroTransaccion : cobroTransacciones) {
			listaTransacciones.add((DescobroTransaccionDto) descobroTransaccionesMapeador.map(cobroTransaccion));
		}
		try {
			Utilidad.guionesNull(listaTransacciones);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return listaTransacciones;
	}
	
	@Override
	public Set <DescobroTransaccionDto> formatearImportesTransacciones(Set<DescobroTransaccionDto> transacciones, String monedaOperacion) {
		
		Set<DescobroTransaccionDto> listaTransacciones =  new HashSet<DescobroTransaccionDto>();
		
		for (DescobroTransaccionDto transaccion : transacciones) {
			
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
			if (!Validaciones.isNullEmptyOrDash(transaccion.getIntereses())){
				if(!Validaciones.isNullEmptyOrDash(transaccion.getTipoDocumento())) {
					transaccion.setIntereses(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(transaccion.getIntereses())),2,transaccion.getMoneda()));
				}else {
					transaccion.setIntereses(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(transaccion.getIntereses())),2, transaccion.getMonedaMedioPago()));
				}
			}
			
			if (!Validaciones.isNullEmptyOrDash(transaccion.getImporteABonificar())){
				if(!Validaciones.isNullEmptyOrDash(transaccion.getTipoDocumento())) {
					transaccion.setImporteABonificar(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(transaccion.getImporteABonificar())),2,transaccion.getMoneda()));
				}else {
					transaccion.setImporteABonificar(Utilidad.formatCurrency((Utilidad.stringToBigDecimal(transaccion.getImporteABonificar())),2,transaccion.getMonedaMedioPago()));
				}
			}
			
			if (!Validaciones.isNullEmptyOrDash(transaccion.getPorcABonificar())){
				transaccion.setPorcABonificar(transaccion.getPorcABonificar()+"%");
			}
		}
		listaTransacciones.addAll(transacciones);
		return listaTransacciones;
	}
	@Override
	public List<ShvParamMotivoDescobro> filtrarMotivoSegunPerfilYOrigenReversion(List<ShvParamMotivoDescobro> listaMotivos, UsuarioSesion userSesion, boolean reviertoDesdeBusqueda, String analista, String Copropietario) {
		
		List<ShvParamMotivoDescobro> motivosFiltrados = new ArrayList <ShvParamMotivoDescobro>();
		
		if (userSesion.getEsAnalistaLegajosChequesRechazados()) {
//			si vengo desde el legajo
			if (!reviertoDesdeBusqueda) {
				for(ShvParamMotivoDescobro motivos: listaMotivos) {
					if(MotivoDescobroEnum.CHEQUE_RECHAZADO.codigo().equals(motivos.getIdMotivoDescobro().toString())) {
						motivosFiltrados.add(motivos);
					}
				}
			} else {
				if (userSesion.getEsPerfilAnalistaCobranza()) {
					if (userSesion.getIdUsuario().equalsIgnoreCase(analista) || userSesion.getIdUsuario().equalsIgnoreCase(Copropietario)) {
						for(ShvParamMotivoDescobro motivos: listaMotivos) {
							if(!MotivoDescobroEnum.CHEQUE_RECHAZADO.codigo().equals(motivos.getIdMotivoDescobro().toString())) {
								motivosFiltrados.add(motivos);
							}
						}
					} else {
						for(ShvParamMotivoDescobro motivos: listaMotivos) {
							if(MotivoDescobroEnum.REVERSION_RELACIONADA.codigo().equals(motivos.getIdMotivoDescobro().toString())) {
								motivosFiltrados.add(motivos);
							}
						}
					}
				} else {
					for(ShvParamMotivoDescobro motivos: listaMotivos) {
						if (MotivoDescobroEnum.REVERSION_RELACIONADA.codigo().equals(motivos.getIdMotivoDescobro().toString())) {
							motivosFiltrados.add(motivos);
						}
					}
				}
			}
		} else {
			for(ShvParamMotivoDescobro motivos: listaMotivos) {
				if (!MotivoDescobroEnum.CHEQUE_RECHAZADO.codigo().equals(motivos.getIdMotivoDescobro().toString())) {
					motivosFiltrados.add(motivos);
				}
			}
		}
		
		return motivosFiltrados;
		
	}
	@Override
	public List<ShvParamMotivoDescobro> filtrarMotivoSegunPerfilYOrigenReversion(
		List<ShvParamMotivoDescobro> listaMotivos,
		UsuarioSesion userSesion,
		boolean reviertoDesdeBusqueda,
		String analista,
		String Copropietario,
		String idMotivoSelecionado
	) {
		List<ShvParamMotivoDescobro> motivosFiltrados = new ArrayList <ShvParamMotivoDescobro>();

		if (MotivoDescobroEnum.CHEQUE_RECHAZADO.codigo().equals(idMotivoSelecionado)) {
			for(ShvParamMotivoDescobro motivos: listaMotivos) {
				if(MotivoDescobroEnum.CHEQUE_RECHAZADO.codigo().equals(motivos.getIdMotivoDescobro().toString())) {
					motivosFiltrados.add(motivos);
				}
			}
		} else if (MotivoDescobroEnum.REVERSION_RELACIONADA.codigo().equals(idMotivoSelecionado)) {
			if (userSesion.getEsAnalistaLegajosChequesRechazados()) {
				for(ShvParamMotivoDescobro motivos: listaMotivos) {
					if(MotivoDescobroEnum.REVERSION_RELACIONADA.codigo().equals(motivos.getIdMotivoDescobro().toString())) {
						motivosFiltrados.add(motivos);
					}
				}
			} else {
				for(ShvParamMotivoDescobro motivos: listaMotivos) {
					if(!MotivoDescobroEnum.CHEQUE_RECHAZADO.codigo().equals(motivos.getIdMotivoDescobro().toString())) {
						motivosFiltrados.add(motivos);
					}
				}
			}
		} else {
			for(ShvParamMotivoDescobro motivos: listaMotivos) {
				if(!MotivoDescobroEnum.CHEQUE_RECHAZADO.codigo().equals(motivos.getIdMotivoDescobro().toString())) {
					motivosFiltrados.add(motivos);
				}
			}
		}

		return motivosFiltrados;
		
	}
	
	@Override
	public void crearCodigoOperacionExterna(ShvCobDescobro shvCobDescobro, List<CodigoOperacionExternaDto> codigosOperacionesExternasDto) {
		
		List<ShvCobDescobroCodigoOperacionExterna> listaOperacionesExternas = new ArrayList<ShvCobDescobroCodigoOperacionExterna>();
		for (CodigoOperacionExternaDto codigoOperacionExternaDto : codigosOperacionesExternasDto) {
			ShvCobDescobroCodigoOperacionExterna operacionExterna = new ShvCobDescobroCodigoOperacionExterna();
			operacionExterna.setCodigoOperacionExterna(codigoOperacionExternaDto.getCodigoOperacionExterna());
			operacionExterna.setDescobro(shvCobDescobro);
			
			listaOperacionesExternas.add(operacionExterna);
		}
		shvCobDescobro.getCodigosOperacionesExternas().addAll(listaOperacionesExternas);
	}
	
	@Override
	public void registrarDescobroPendienteConfirmacionManualAEnProceso(ShvCobDescobro descobroModelo, UsuarioSesion userSesion) throws NegocioExcepcion{
		try {
			ShvWfWorkflow wf = descobroModelo.getWorkflow();
			wf = workflowDescobros.registrarDescobroPendienteConfirmacionManualAEnProceso(wf, descobroModelo.getObservacion(), userSesion.getIdUsuario());
			
			descobroModelo.setObservacion(Constantes.EMPTY_STRING);
			descobroModelo.setWorkflow(wf);
		} catch ( WorkflowExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} 
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, NegocioExcepcion.class }, propagation = Propagation.REQUIRED)
	public void confirmaAplicacionManual(Long idDescobro, UsuarioSesion userSesion, String observacion, SociedadEnum sociedad, SistemaEnum sistema) throws NegocioExcepcion, PersistenciaExcepcion{
		List<ComprobanteDto> comprobantes = userSesion.getComprobantesAGuardar();
		List<CodigoOperacionExternaDto> codigosOperacionesExternas = userSesion.getCodigosOperacionesExternasAGuardar();
		ShvCobDescobro descobro;
		MarcaEnum marca = null;
		
		try {
			descobro = descobroDao.buscarDescobro(idDescobro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		if(Validaciones.isCollectionNotEmpty(comprobantes)){
			for (ComprobanteDto  comprobante : comprobantes) {
				crearAdjuntoDescobro(idDescobro, comprobante);
			}
			userSesion.setComprobantesAGuardar(new ArrayList<ComprobanteDto>());
		}

		if(Validaciones.isCollectionNotEmpty(codigosOperacionesExternas)){
			crearCodigoOperacionExterna(descobro, codigosOperacionesExternas);
			userSesion.setCodigosOperacionesExternasAGuardar(new ArrayList<CodigoOperacionExternaDto>());
		}
		
		if(!Validaciones.isNullEmptyOrDash(observacion)){
			descobro.setObservacion(observacion);
			//descobro = this.pasarObservacionEnEstado(descobro);
		}
		
		if (userSesion.getEsReferenteCaja()) {
			descobro.setFechaConfirmacionAplicacionManualRefCaja(new Date());
			descobro.setUsuarioAprobAplicacionManualRefCaja(userSesion.getIdUsuario());
			descobro.setNombreApellidoAprobAplicacionManualRefCaja(ldapServicio.buscarUsuarioPorUidEnMemoria(userSesion.getIdUsuario()).getNombreCompleto());
			marca = MarcaEnum.CONFIRMADO_POR_REFERENTE_CAJA;
		} else if (userSesion.getEsReferenteOperacionesExternas()) {
			descobro.setFechaAprobAplicacionManualRefOperacionesExternas(new Date());
			descobro.setUsuarioAprobAplicacionManualRefOperacionesExternas(userSesion.getIdUsuario());
			descobro.setNombreApellidoAprobAplicacionManualRefOperacionesExternas(ldapServicio.buscarUsuarioPorUidEnMemoria(userSesion.getIdUsuario()).getNombreCompleto());
			marca = MarcaEnum.CONFIRMADO_POR_REFERENTE_OPERACIONES_EXTERNAS;
		}
		
		if (Validaciones.isCollectionNotEmpty(comprobantes) || Validaciones.isCollectionNotEmpty(codigosOperacionesExternas)) {
			if (Validaciones.isObjectNull(tareaDao.buscarTareaPendienteConfManual(TipoTareaEnum.DES_CONFIRMA_APL_MAN, sociedad, sistema, descobro.getWorkflow().getIdWorkflow()))){
				registrarDescobroPendienteConfirmacionManualAEnProceso(descobro, userSesion);
			}
			descobroImputacionServicio.enviarMailyFinalizarTareaAplicacionManual(descobro, sociedad, sistema, userSesion);
			workflowService.agregarWorkflowMarca(descobro.getWorkflow(), userSesion.getIdUsuario(), marca);
		}
	}

	/*******************************************************************************************
	 * Getters & Setters
	 *******************************************************************************************/
	
	public IWorkflowDescobros getWorkflowDescobros() {
		return workflowDescobros;
	}

	public void setWorkflowDescobros(IWorkflowDescobros workflowDescobros) {
		this.workflowDescobros = workflowDescobros;
	}

	public IDescobroDao getDescobroDao() {
		return descobroDao;
	}

	public void setDescobroDao(IDescobroDao descobroDao) {
		this.descobroDao = descobroDao;
	}

	public ICobroDao getCobroDao() {
		return cobroDao;
	}

	public void setCobroDao(ICobroDao cobroDao) {
		this.cobroDao = cobroDao;
	}

	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public ICobroImputacionServicio getCobroServicio() {
		return cobroServicio;
	}

	public void setCobroServicio(ICobroImputacionServicio cobroServicio) {
		this.cobroServicio = cobroServicio;
	}

	public MailServicio getMailServicio() {
		return mailServicio;
	}

	public void setMailServicio(MailServicio mailServicio) {
		this.mailServicio = mailServicio;
	}
	
	public IDescobroSimulacionServicio getDescobroBatchSimulacionServicio() {
		return descobroBatchSimulacionServicio;
	}

	public void setDescobroBatchSimulacionServicio(
			IDescobroSimulacionServicio descobroBatchSimulacionServicio) {
		this.descobroBatchSimulacionServicio = descobroBatchSimulacionServicio;
	}

	public IExcelDescobroServicio getExcelDescobroServicio() {
		return excelDescobroServicio;
	}

	public void setExcelDescobroServicio(
			IExcelDescobroServicio excelDescobroServicio) {
		this.excelDescobroServicio = excelDescobroServicio;
	}


}