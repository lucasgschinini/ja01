package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.ConceptoDocumentoScardEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoValorMotivoSuspensionEnum;
import ar.com.telecom.shiva.base.enumeradores.LeyendaPiePaginaDocumentoScardEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoGeneradoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.ControlXml;
import ar.com.telecom.shiva.base.utils.NumerosALetras;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.batch.bean.scard.Cabecera;
import ar.com.telecom.shiva.negocio.batch.bean.scard.Cheque;
import ar.com.telecom.shiva.negocio.batch.bean.scard.ChequeRechazado;
import ar.com.telecom.shiva.negocio.batch.bean.scard.Detalle;
import ar.com.telecom.shiva.negocio.batch.bean.scard.Documento;
import ar.com.telecom.shiva.negocio.batch.bean.scard.Documentos;
import ar.com.telecom.shiva.negocio.batch.bean.scard.Factura;
import ar.com.telecom.shiva.negocio.batch.bean.scard.Facturas;
import ar.com.telecom.shiva.negocio.batch.bean.scard.Medio;
import ar.com.telecom.shiva.negocio.batch.bean.scard.MedioDePago;
import ar.com.telecom.shiva.negocio.batch.bean.scard.Notas;
import ar.com.telecom.shiva.negocio.batch.bean.scard.PieDePagina;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IScardServicio;
import ar.com.telecom.shiva.negocio.servicios.ITransaccionSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDocumentoScard;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.dao.ICobroCodigoOperacionExternaSimplificadoDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineClienteDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroCodigoOperacionExternaSimplificadoDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IParametroDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoComprobanteDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDocumento;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionCesionCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionIIBB;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionIntercompany;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionLiquidoProducto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionOtras;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionProveedor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoPlanDePago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoRemanente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobOperacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimpleSinWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroCodigoOperacionExternaSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroSimpleSinWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDocumentoSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCodigoOperacionExternaSimplificado;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;

public class ScardServicioImpl implements IScardServicio {
	
	private static final String NOMBRE_ARCHIVO = ".DocumentosDeCobro.";
	private static final String EXTENSION = ".xml";
	private static final String ESPACIO = " ";
	private static final String SCARD_PROCESADO = "PROCESADO";
	private static final String SCARD_PENDIENTE = "PENDIENTE";
	private static final String PUNTOS = "...";
	private static final int TRUNCAR = 12;
	
	private static final String REF_REINTEGRO_DESISTIMIENTO = "DESIST"; 
	private static final String REF_REINTEGRO_PLAN_PAGO = "PLAN PAGO"; 
	private static final String REF_REINTEGRO_COMP_INTERCOMPANY = "COMP IC"; 
	private static final String REF_REINTEGRO_COMP_IIBB = "COMP IIBB"; 
	private static final String REF_REINTEGRO_COMP_CESION_CREDITOS = "COMP CC"; 
	private static final String REF_REINTEGRO_COMP_OTRAS = "OTRAS COMP"; 
	
	private static final String NOTA_PIE_ASTERISCOS_POR_TRES = Constantes.OPEN_PARENTESIS 
															 + Constantes.ASTERISK 
															 + Constantes.ASTERISK 
															 + Constantes.ASTERISK 
															 + Constantes.CLOSE_PARENTESIS;
	
	private static final String NOTA_PIE_ASTERISCOS_POR_CUATRO = Constantes.OPEN_PARENTESIS 
			                                                 + Constantes.ASTERISK 
			                                                 + Constantes.ASTERISK 
			                                                 + Constantes.ASTERISK 
			                                                 + Constantes.ASTERISK
			                                                 + Constantes.CLOSE_PARENTESIS;

	@Autowired private IGenericoDao genericoDao;
	@Autowired private IDocumentoDao documentoDao;
	@Autowired private IParametroDao parametroDao;
	@Autowired private ICobroImputacionServicio cobroServicio;
	@Autowired private IDescobroServicio descobroServicio;
	@Autowired private IVistaSoporteServicio vistaSoporteServicio;
	@Autowired private ICobroOnlineDao cobroOnlineDao;
	@Autowired private ICobroOnlineClienteDao cobroOnlineClienteDao;
	@Autowired private ICobroDao cobroDao;
	@Autowired private IDescobroDao descobroDao;
	@Autowired private ITransaccionSoporteServicio transaccionSoporteServicio;
	@Autowired private ITipoMedioPagoDao tipoMedioPagoDao;
	@Autowired private ITipoComprobanteDao tipoComprobanteDao;
	@Autowired private ICobroCodigoOperacionExternaSimplificadoDao cobroCodigoOperacionExternaSimplificadoDao;
	@Autowired private IDescobroCodigoOperacionExternaSimplificadoDao descobroCodigoOperacionExternaSimplificadoDao;

	
	// Para recibos
	private final String LETRA_INICIAL_RECIBO = "R";
		
	// Para notas de reembolso
	private final String LETRA_INICIAL_NOTA_REEMBOLSO = "N";

	/**
	 * Recorre la lista de facturas, para verificar si hay una llamada al pie de pagina
	 * @param notas
	 * @param detalle
	 * @return
	 */
	private Notas agregarNotaAlPieDePagina(Notas notas, Detalle detalle) {
		double fechaHoraInicioNanoTime = System.nanoTime();
		Traza.advertencia(getClass(), "Comienzo del metodo 'agregarNotaAlPieDePagina'");
		
		boolean existenDocumentosGeneradosPorDiferenciaCambio = false;
		boolean existenInteresesATrasladarEnProximaFacturaUsuario = false;
		
		for (Factura factura : detalle.getFacturas().getFactura()){
			if (LeyendaPiePaginaDocumentoScardEnum.DOCUMENTO_GENERADO_POR_DIF_CAMBIO.equals(factura.getLeyendaPiePagina())) {
				existenDocumentosGeneradosPorDiferenciaCambio = true;

			} else if (LeyendaPiePaginaDocumentoScardEnum.EXISTEN_INTERESES_TRASLADAS_PROXIMA_FACTURA.equals(factura.getLeyendaPiePagina())) {
				existenInteresesATrasladarEnProximaFacturaUsuario = true;
			}
		}

		if (Validaciones.isNullOrEmpty(notas.getNota1())){
			for (Medio medio : detalle.getMediosDePago().getMedio()){
				if (LeyendaPiePaginaDocumentoScardEnum.DOCUMENTO_GENERADO_POR_DIF_CAMBIO.equals(medio.getLeyendaPiePagina())) {
					existenDocumentosGeneradosPorDiferenciaCambio = true;
				}
			}
		}

		if (existenDocumentosGeneradosPorDiferenciaCambio && existenInteresesATrasladarEnProximaFacturaUsuario) {
			StringBuffer strNota1 = new StringBuffer();
			strNota1.append(NOTA_PIE_ASTERISCOS_POR_TRES);
			strNota1.append(Constantes.WHITESPACE);
			strNota1.append(LeyendaPiePaginaDocumentoScardEnum.DOCUMENTO_GENERADO_POR_DIF_CAMBIO.getLeyendaPiePagina());
			notas.setNota1(strNota1.toString());
			
			StringBuffer strNota2 = new StringBuffer();
			strNota2.append(NOTA_PIE_ASTERISCOS_POR_CUATRO);
			strNota2.append(Constantes.WHITESPACE);
			strNota2.append(LeyendaPiePaginaDocumentoScardEnum.EXISTEN_INTERESES_TRASLADAS_PROXIMA_FACTURA.getLeyendaPiePagina());
			notas.setNota2(strNota2.toString());
		} else {
			if (existenDocumentosGeneradosPorDiferenciaCambio) {
				StringBuffer strNota1 = new StringBuffer();
				strNota1.append(NOTA_PIE_ASTERISCOS_POR_TRES);
				strNota1.append(Constantes.WHITESPACE);
				strNota1.append(LeyendaPiePaginaDocumentoScardEnum.DOCUMENTO_GENERADO_POR_DIF_CAMBIO.getLeyendaPiePagina());
				notas.setNota1(strNota1.toString());
				
			} else if (existenInteresesATrasladarEnProximaFacturaUsuario) {
				StringBuffer strNota1 = new StringBuffer();
				strNota1.append(NOTA_PIE_ASTERISCOS_POR_TRES);
				strNota1.append(Constantes.WHITESPACE);
				strNota1.append(LeyendaPiePaginaDocumentoScardEnum.EXISTEN_INTERESES_TRASLADAS_PROXIMA_FACTURA.getLeyendaPiePagina());
				notas.setNota1(strNota1.toString());
			}
		}
		
		Utilidad.tracearTiempo(getClass(), "Finalizo el metodo 'agregarNotaAlPieDePagina' con una duracion de: ", fechaHoraInicioNanoTime);
		return notas;
	}
	
	/**
	 * Se hace un control sobre las facturas y notas de crédito de Calipso de una lista de transacciones, a fin de verificar si existen o no 
	 * documentos generados por diferencia de cambio.
	 * 
	 * @param listaTransacciones
	 * @return
	 */
	private boolean existenDocumentosGeneradosPorDiferenciaCambio(Collection<ShvCobTransaccion> listaTransacciones) {
		
		boolean existenDocumentosGeneradosPorDiferenciaCambio = 				
				existenDocumentosDebitosGeneradosPorDiferenciaCambio(listaTransacciones) 
					|| existenDocumentosCreditosGeneradosPorDiferenciaCambio(listaTransacciones);
		
		return existenDocumentosGeneradosPorDiferenciaCambio;
	}
	
	/**
	 * Se hace un control sobre las facturas de Calipso de una lista de transacciones, a fin de verificar si existen o no 
	 * documentos generados por diferencia de cambio.
	 * 
	 * @param listaTransacciones
	 * @return
	 */
	private boolean existenDocumentosDebitosGeneradosPorDiferenciaCambio(Collection<ShvCobTransaccion> listaTransacciones) {
		
		boolean existenDocumentosGeneradosPorDiferenciaCambio = false;
		
		for (ShvCobTransaccion transaccion : listaTransacciones) {
			
			ShvCobFactura factura = transaccion.getFacturaTransaccion();
			
			if (esDocumentoGeneradoPorDiferenciaCambio(factura)) {
				existenDocumentosGeneradosPorDiferenciaCambio = true;
				break;
			}
		}
		
		return existenDocumentosGeneradosPorDiferenciaCambio;
	}
	
	/**
	 * Se hace un control sobre la factura de Calipso, a fin de verificar si es un 
	 * documento generado por diferencia de cambio.
	 * 
	 * @param listaTransacciones
	 * @return
	 */
	private boolean esDocumentoGeneradoPorDiferenciaCambio(ShvCobFactura factura) {
		
		boolean existenDocumentosGeneradosPorDiferenciaCambio = false;
		
		if (!Validaciones.isObjectNull(factura)) {
			if (factura instanceof ShvCobFacturaCalipso){
				if (OrigenDocumentoEnum.DC.equals(((ShvCobFacturaCalipso) factura).getOrigenDocumento())){
					existenDocumentosGeneradosPorDiferenciaCambio = true;
				}
			}
		}
		
		return existenDocumentosGeneradosPorDiferenciaCambio;
	}	
	
	/**
	 * Se hace un control sobre las facturas de Calipso de una lista de transacciones, a fin de verificar si existen o no 
	 * documentos generados por diferencia de cambio.
	 * 
 	 * En caso afirmativo, como nota al pie deberá aparecer la leyenda 
	 * 	"Documento generado por diferencia de cambio"
	 * 
	 * con los asteriscos a nivel de factura cuando corresponda
	 * 
	 * @param listaTransacciones
	 * @return
	 */
	private boolean existenDocumentosCreditosGeneradosPorDiferenciaCambio(Collection<ShvCobTransaccion> listaTransacciones) {
		
		boolean existenDocumentosGeneradosPorDiferenciaCambio = false;
		
		for (ShvCobTransaccion transaccion : listaTransacciones) {
			for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
				if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
					if (OrigenDocumentoEnum.DC.equals(((ShvCobMedioPagoNotaCreditoCalipso)medioPago).getOrigenDocumento())){
						existenDocumentosGeneradosPorDiferenciaCambio = true;
						break;
					}
				}
			}
		}
		
		return existenDocumentosGeneradosPorDiferenciaCambio;
	}	
	
	/**
	 * Se hace un control sobre la factura de Calipso, a fin de verificar si es un 
	 * documento generado por diferencia de cambio.
	 * 
 	 * En caso afirmativo, como nota al pie deberá aparecer la leyenda 
	 * 	"Documento generado por diferencia de cambio"
	 * 
	 * con los asteriscos a nivel de factura cuando corresponda
	 * 
	 * @param listaTransacciones
	 * @return
	 */
	private boolean esDocumentoGeneradoPorDiferenciaCambio(ShvCobMedioPago medioPago) {
		
		boolean existenDocumentosGeneradosPorDiferenciaCambio = false;
		
		if (!Validaciones.isObjectNull(medioPago)) {
			if (medioPago instanceof ShvCobMedioPagoCalipso){
				if (OrigenDocumentoEnum.DC.equals(((ShvCobMedioPagoNotaCreditoCalipso)medioPago).getOrigenDocumento())){
					existenDocumentosGeneradosPorDiferenciaCambio = true;
				}
			}
		}
		
		return existenDocumentosGeneradosPorDiferenciaCambio;
	}	
	
	/**
	 * Se hace un control sobre las facturas de usuario (otros débitos) de una lista de transacciones fin de poder verificar
	 * si existen intereses a trasladar en alguna factura. Estas facturas no se simulan o cobran de manera automática contra los cobradores
	 * por lo que nunca vamos a saber el monto de intereses.
	 * Si podemos verificar teniendo en cuenta las siguientes reglas, si podemos asumir que se han calculado intereses	
	 * 
	 * 	• Verifico si la factura genera interesas contra la tabla paramétrica (shv_param_tipo_comprobante_uso) 
	 *	• Verifico si los medios de pago que acompañan a la factura generan intereses. Aquí 'medio pago mata factura' 
	 *	  cuando el "generar intereses = no" (shv_param_tipo_medio_pago)  
	 *	• Para las facturas de MIC y Calipso, se asume que los intereses se pueden calcular, ya que tenemos  
	 *    un cobrador que nos calcula los intereses. Para el resto de los documentos de usuario, no vamos a saber los intereses generados.
	 *	• Se asume que se generan por la marca de “genera intereses = si” en la tabla paramétrica.
	 *  • Debemos tener en cuenta la fecha de cobro vs la fecha de vencimiento para verificar si podemos asumir que hubo intereses.
	 * 
	 * @param listaTransacciones
	 * @return
	 */
	private boolean existenInteresesATrasladarEnProximaFacturaUsuario(Collection<ShvCobTransaccion> listaTransacciones) {
		
		boolean existenInteresesATrasladarEnProximaFacturaUsuario = false;
		
		for (ShvCobTransaccion transaccion : listaTransacciones) {
			ShvCobFactura factura = transaccion.getFacturaTransaccion();
			
			if (existenInteresesATrasladarEnProximaFacturaUsuario(factura)) {
				existenInteresesATrasladarEnProximaFacturaUsuario = true;
				break;
			}
		}
		
		return existenInteresesATrasladarEnProximaFacturaUsuario;
	}

	/**
	 * Se hace un control sobre una factura de usuario (otros débitos) a fin de poder verificar
	 * si existen intereses a trasladar. Esta factura no se simulan o cobran de manera automática contra los cobradores
	 * por lo que nunca vamos a saber el monto de intereses.
	 * Si podemos verificar teniendo en cuenta las siguientes reglas, si podemos asumir que se han calculado intereses	
	 * 
	 * 	• Verifico si la factura genera interesas contra la tabla paramétrica (shv_param_tipo_comprobante_uso) 
	 *	• Verifico si los medios de pago que acompañan a la factura generan intereses. Aquí 'medio pago mata factura' 
	 *	  cuando el "generar intereses = no" (shv_param_tipo_medio_pago)  
	 *	• Para las facturas de MIC y Calipso, se asume que los intereses se pueden calcular, ya que tenemos  
	 *    un cobrador que nos calcula los intereses. Para el resto de los documentos de usuario, no vamos a saber los intereses generados.
	 *	• Se asume que se generan por la marca de “genera intereses = si” en la tabla paramétrica.
	 *  • Debemos tener en cuenta la fecha de cobro vs la fecha de vencimiento para verificar si podemos asumir que hubo intereses.
	 *  • Adicionalmente, debemos tener en cuenta la acción del usuario sobre los intereses.
	 *	  Es posible que la factura posea intereses, pero el usuario ha decidido no trasladarlos, 
	 *	  en todo caso, no deberá estar presente la nueva leyenda al pie.
	 * 
	 * @param listaTransacciones
	 * @return
	 */
	private boolean existenInteresesATrasladarEnProximaFacturaUsuario(ShvCobFactura factura) {
		
		double fechaHoraInicioNanoTime = System.nanoTime();
		Traza.advertencia(getClass(), "Comienzo del metodo 'existenInteresesATrasladarEnProximaFacturaUsuario'");
		
		boolean tipoComprobanteGeneraIntereses = false;
		boolean mediosPagoAsociadosGeneranIntereses = true;
		boolean existenInteresesPorPagoFueraFecha = false;
		boolean usuarioDeseaTrasladarIntereses = false;
		
		boolean existenInteresesATrasladarEnProximaFacturaUsuario = false;
		
		if (!Validaciones.isObjectNull(factura)) {
			if (factura instanceof ShvCobFacturaUsuario) {
				if (SiNoEnum.SI.equals(factura.getUsoPorTipoComprobante().getGeneraIntereses())) {

					// Actualizo el booleano, hasta aqui puedo asumir que hay intereses
					tipoComprobanteGeneraIntereses = true;
					
					// Verifico si los medios de pago que acompañan a la factura que genera intereses. 
					// Aquí “medio pago mata factura” si la factura indica que NO genera interes
					for (ShvCobMedioPago medioPago : factura.getTransaccion().getMediosPago()) {
						if (SiNoEnum.NO.equals(medioPago.getTipoMedioPago().getGeneraIntereses())) {
							mediosPagoAsociadosGeneranIntereses = false;
							break;
						}
					}
						
					//
					// Si la fecha de vencimiento no existe, el control de "fecha vencida" no se hace 
					// Luego verifico si el pago se hizo posterior a la fecha de vencimiento, caso contrario
					// no hay posibilidad de generar intereses
					//
					if (!Validaciones.isObjectNull(factura.getFechaVencimiento())) { 
						existenInteresesPorPagoFueraFecha = factura.getFechaValor().after(factura.getFechaVencimiento());
					} else {
						existenInteresesPorPagoFueraFecha = true;
					}
					//
					// Adicionalmente, debemos tener en cuenta la acción del usuario sobre los intereses.
					// Es posible que la factura posea intereses, pero el usuario ha decidido no trasladarlos, 
					// en todo caso, no deberá estar presente la nueva leyenda al pie.
					if (!Validaciones.isObjectNull(factura.getQueHacerConIntereses())) {
						
						if (TratamientoInteresesEnum.TV.equals(factura.getQueHacerConIntereses())
							|| TratamientoInteresesEnum.TC.equals(factura.getQueHacerConIntereses())
							|| TratamientoInteresesEnum.TM.equals(factura.getQueHacerConIntereses())) {
							
							usuarioDeseaTrasladarIntereses = true;
						}
					}
				}
			}
		}
		
		existenInteresesATrasladarEnProximaFacturaUsuario = tipoComprobanteGeneraIntereses 
														 && mediosPagoAsociadosGeneranIntereses 
														 && existenInteresesPorPagoFueraFecha
														 && usuarioDeseaTrasladarIntereses;
		
		Utilidad.tracearTiempo(getClass(), "Finalizo el metodo 'existenInteresesATrasladarEnProximaFacturaUsuario' con una duracion de: ", fechaHoraInicioNanoTime);
		return existenInteresesATrasladarEnProximaFacturaUsuario;
	}
	
	/**
	 * 
	 * @param listaTransacciones
	 * @param tipoCobro
	 * @return
	 * @throws NegocioExcepcion
	 */
	private Detalle mapearDetalleTransaccionDebito(
			Collection<ShvCobTransaccion> listaTransacciones, Collection<ShvCobTransaccion> listaTransaccionesPadres, boolean aplicarTratamientoChequesRechazados) throws NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		Traza.advertencia(getClass(), "Comienzo del metodo 'mapearDetalleTransaccionDebito'");
		Detalle tagDetalle = new Detalle();
		Facturas tagFacturas = new Facturas();
		ChequeRechazado tagChequeRechazado = new ChequeRechazado();
		MedioDePago tagMedioDePago = new MedioDePago();
		BigDecimal importeTotal = new BigDecimal(0);
		List <String> listaReferencias = new ArrayList<String>();
		
		boolean existenDocumentosGeneradosPorDiferenciaCambio = existenDocumentosGeneradosPorDiferenciaCambio(listaTransacciones);

		Traza.advertencia(getClass(), "~ Recorro la lista de transacciones");

		for (ShvCobTransaccion transaccion : listaTransacciones) {
			
			Traza.advertencia(getClass(), "~ idOperacion: " + transaccion.getOperacion().getIdOperacion() + " ~ idTransaccion: " + transaccion.getIdTransaccion());
			
			ShvCobFactura factura = transaccion.getFacturaTransaccion();
			Traza.advertencia(getClass(), "~   Armo los datos de la factura(" + factura.getIdFactura() +") en el tag <factura>"); 
			
			Factura tagFactura = new Factura();
			
			tagFactura.setClase((factura.getClaseComprobante() != null) ? (factura.getClaseComprobante().equals(TipoClaseComprobanteEnum.S) || factura.getClaseComprobante().equals(TipoClaseComprobanteEnum.D)) ? ESPACIO:String.valueOf(factura.getClaseComprobante()) : ESPACIO);
			
			String idTipoComprobante = factura.getTipoComprobante().getIdTipoComprobante();
			
			if (!Validaciones.isObjectNull(idTipoComprobante)) {
				tagFactura.setComprobante(TipoComprobanteEnum.getEnumByName(idTipoComprobante).getValor());
			} else {
				tagFactura.setComprobante(ESPACIO);
			}
			
			tagFactura.setNumero((factura.getNumeroComprobante() != null) ? Utilidad.rellenarCerosIzquierda(factura.getNumeroComprobante(), 8) : ESPACIO);
			tagFactura.setSucursal((factura.getSucursalComprobante() != null) ? Utilidad.rellenarCerosIzquierda(factura.getSucursalComprobante(), 4) : ESPACIO);

			// Tratamiento de datos particulares de acuerdo al tipo de factura
			if (factura instanceof ShvCobFacturaCalipso){
				tagFactura.setOrigen(Constantes.CALIPSO_CAL);
				tagFactura.setReferencia(ESPACIO);
				
				MonedaEnum monedaFactura = ((ShvCobFacturaCalipso) factura).getMoneda();
				MonedaEnum monedaOperacion = factura.getTransaccion().getOperacion().getMonedaOperacion();
				
				tagFactura.setMonedaOrigen(monedaFactura.getSigno2());
					
				// Solo informamos "tipo de cambio" e "importe moneda" origen cuando hay diferencia entre las monedas de cobro y documento
				if (!monedaFactura.equals(monedaOperacion)) {

					tagFactura.setTipoCambio(((ShvCobFacturaCalipso)factura).getTipoCambio() != null ? formatCurrencyTipoCambio(((ShvCobFacturaCalipso)factura).getTipoCambio()): ESPACIO);
					tagFactura.setImporteMonedaOrigen((((ShvCobFacturaCalipso) factura).getImporteAplicadoAFechaEmisionMonedaOrigen() != null) ? Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(((ShvCobFacturaCalipso) factura).getImporteAplicadoAFechaEmisionMonedaOrigen(), 2)): ESPACIO);

					tagFactura.setSaldoAnterior((factura.getSaldoActual() != null) ? Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(((ShvCobFacturaCalipso) factura).getSaldoActualMonedaOrigen(), 2)): ESPACIO);
					tagFactura.setImporteVencimiento((factura.getImporteOriginal() != null) ? Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(((ShvCobFacturaCalipso) factura).getImporteOriginalMonedaOrigen(), 2)): ESPACIO);
				
				} else {
					
					tagFactura.setTipoCambio(ESPACIO);
					tagFactura.setImporteMonedaOrigen(ESPACIO);

					tagFactura.setSaldoAnterior((factura.getSaldoActual() != null) ? Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(factura.getSaldoActual(),2)):ESPACIO);
					tagFactura.setImporteVencimiento((factura.getImporteOriginal() != null) ? Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(factura.getImporteOriginal(),2)):ESPACIO);
				}

				if (esDocumentoGeneradoPorDiferenciaCambio(factura)) {
					// Seteamos por defecto los tres asteriscos ya que la nota de pie de documentos generados por diferencia de cambio
					// tiene la priodidad por sobre el tema de intereses
					tagFactura.setLlamadaPiePagina(NOTA_PIE_ASTERISCOS_POR_TRES);
					tagFactura.setLeyendaPiePagina(LeyendaPiePaginaDocumentoScardEnum.DOCUMENTO_GENERADO_POR_DIF_CAMBIO);
				} else { 
					tagFactura.setLlamadaPiePagina(ESPACIO);
				}

			} else if (factura instanceof ShvCobFacturaMic) {
				tagFactura.setOrigen(Constantes.MIC);
				
				if (TipoComprobanteEnum.DUC.equals(((ShvCobFacturaMic)factura).getTipoComprobante().getIdTipoComprobante())) {
					tagFactura.setReferencia(((ShvCobFacturaMic)factura).getIdReferenciaFactura());
				} else {
					tagFactura.setReferencia(Utilidad.rellenarCerosIzquierda(((ShvCobFacturaMic)factura).getIdReferenciaFactura(), 15));
				}
				
				if (factura instanceof ShvCobFacturaMic) {
					tagFactura.setMonedaOrigen(MonedaEnum.PES.getSigno2());
				} else {
					tagFactura.setMonedaOrigen(((ShvCobFacturaCalipso)factura).getMoneda().getSigno2());
				}
				
				tagFactura.setTipoCambio(ESPACIO);
				tagFactura.setSaldoAnterior((factura.getSaldoActual() != null) ? Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(factura.getSaldoActual(), 2)) : ESPACIO);
				tagFactura.setImporteVencimiento((factura.getImporteOriginal() != null)?Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(factura.getImporteOriginal(), 2)) : ESPACIO);
				tagFactura.setImporteMonedaOrigen(ESPACIO);
				tagFactura.setLlamadaPiePagina(ESPACIO);

			} else if (factura instanceof ShvCobFacturaUsuario) {
			
				if (existenInteresesATrasladarEnProximaFacturaUsuario(factura)) {
					if (existenDocumentosGeneradosPorDiferenciaCambio) {
						tagFactura.setLlamadaPiePagina(NOTA_PIE_ASTERISCOS_POR_CUATRO);
					} else {
						tagFactura.setLlamadaPiePagina(NOTA_PIE_ASTERISCOS_POR_TRES);
					}
					tagFactura.setLeyendaPiePagina(LeyendaPiePaginaDocumentoScardEnum.EXISTEN_INTERESES_TRASLADAS_PROXIMA_FACTURA);
				} else { 
					tagFactura.setLlamadaPiePagina(ESPACIO);
				}

				ShvCobFacturaUsuario facturaUsuario = (ShvCobFacturaUsuario) factura;

				//
				// Seteo id Tipo de Medio Pago y Codigo Cliente Legado
				//
				if (SistemaEnum.HUAWEI.equals(facturaUsuario.getSistemaOrigen())) {
					if (TipoComprobanteEnum.C_C.name().equals(facturaUsuario.getTipoComprobante().getIdTipoComprobante())) {
						// Cuenta corriente Huawei | Numero de documento: N/A | Referencia: Referente de pago
						tagFactura.setClase(ESPACIO);
						tagFactura.setComprobante(ESPACIO);
						tagFactura.setNumero(ESPACIO);
						tagFactura.setSucursal(ESPACIO);
						tagFactura.setReferencia(Validaciones.isObjectNull(facturaUsuario.getReferencia()) ? ESPACIO : facturaUsuario.getReferencia().trim());

					} else {
						tagFactura.setReferencia(ESPACIO);
					}

				} else if (SistemaEnum.NEGOCIO_NET.equals(facturaUsuario.getSistemaOrigen())) {
					if (TipoComprobanteEnum.C_C.name().equals(facturaUsuario.getTipoComprobante().getIdTipoComprobante())) {
						// Cuenta corriente Negocio.Net | Numero de documento: N/A | Referencia: Referente de pago
						tagFactura.setClase(ESPACIO);
						tagFactura.setComprobante(ESPACIO);
						tagFactura.setNumero(ESPACIO);
						tagFactura.setSucursal(ESPACIO);
						tagFactura.setReferencia(Validaciones.isObjectNull(facturaUsuario.getReferencia()) ? ESPACIO : facturaUsuario.getReferencia().trim());

					} else {
						tagFactura.setReferencia(ESPACIO);
					}

				} else if (SistemaEnum.SAP.equals(facturaUsuario.getSistemaOrigen())) {
					if (TipoComprobanteEnum.C_C.name().equals(facturaUsuario.getTipoComprobante().getIdTipoComprobante())) {
						// Cuenta corriente SAP | Numero de documento: N/A | Referencia: Cliente
						tagFactura.setClase(ESPACIO);
						tagFactura.setComprobante(ESPACIO);
						tagFactura.setNumero(ESPACIO);
						tagFactura.setSucursal(ESPACIO);
						tagFactura.setReferencia(facturaUsuario.getIdClienteLegado().toString());

					} else {
						tagFactura.setReferencia(ESPACIO);
					}
					
				} else if (SistemaEnum.OPEN.equals(facturaUsuario.getSistemaOrigen())) {
					if (TipoComprobanteEnum.C_C.name().equals(facturaUsuario.getTipoComprobante().getIdTipoComprobante())) {
						// Cuenta corriente Open | Numero de documento: N/A | Referencia: Referente de pago
						tagFactura.setClase(ESPACIO);
						tagFactura.setComprobante(ESPACIO);
						tagFactura.setNumero(ESPACIO);
						tagFactura.setSucursal(ESPACIO);
						tagFactura.setReferencia(Validaciones.isObjectNull(facturaUsuario.getReferencia()) ? ESPACIO : facturaUsuario.getReferencia().trim());

					} else {
						tagFactura.setReferencia(ESPACIO);
					}
					
				} else if (SistemaEnum.NEXUS.equals(facturaUsuario.getSistemaOrigen())) {
						// Equipo/Servicio Nexus | Numero de documento: Letra-Sucursal-Comprobante | Referencia: N/A
						tagFactura.setReferencia(ESPACIO);
				} 
					
				if (TipoComprobanteEnum.DE2.name().equals(facturaUsuario.getTipoComprobante().getIdTipoComprobante())) {
					// Para el caso del tipo de comprobante 'DE2', hacemos un tratamiento especial para poder eliminar el '2' de la descripción
					// que se está utilizando.
					tagFactura.setComprobante(tagFactura.getComprobante().replace("2", ""));
				}
				
				tagFactura.setOrigen(facturaUsuario.getSistemaOrigen().getDescripcionCorta());
				tagFactura.setMonedaOrigen(facturaUsuario.getMoneda().getSigno2());
				
				if (!Validaciones.isObjectNull(facturaUsuario.getTipoCambio())) {
					tagFactura.setTipoCambio(formatCurrencyTipoCambio(facturaUsuario.getTipoCambio()));
					tagFactura.setImporteMonedaOrigen(
							(Validaciones.isObjectNull(facturaUsuario.getImporteAplicadoAFechaEmision())) ? ESPACIO : 
								Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(facturaUsuario.getImporteAplicadoAFechaEmision(), 2)));
				} else {
					tagFactura.setTipoCambio(ESPACIO);
					tagFactura.setImporteMonedaOrigen(ESPACIO);
				}
	
				tagFactura.setSaldoAnterior(ESPACIO);
				tagFactura.setImporteVencimiento(ESPACIO);
			}	

			BigDecimal cobradorInteresesTrasladados = null;
			BigDecimal cobradorInteresesBonificados = null;
			String acuerdoTrasladoCargo = null;
			
			if (EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO.equals(transaccion.getEstadoProcesamiento()) 
					|| EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM.equals(transaccion.getEstadoProcesamiento())) {

				Traza.advertencia(getClass(), "~   Calculo los intereses para facturas de transacciones con padres (son diferencia de cambio), para ello busco al padre de mi transaccion, tomo su factura y recupero los intereses");
				
				// Calculo los intereses para facturas de transacciones con padres (son diferencia de cambio)
				// para ello busco al padre de mi transaccion, tomo su factura y recupero los intereses
				for (ShvCobTransaccion transaccionPadre : listaTransaccionesPadres) {

					// Verifico si la transaccion actual es hija de..
					if (transaccion.getNumeroTransaccionPadre().equals(transaccionPadre.getNumeroTransaccion())) {
						
						// Tomo los datos del padre solo si el documento a presentar no es uno generado por cobro 
						// (por ejemplo, Nota de Débito por dif. decambio)
						if (Validaciones.isObjectNull(((ShvCobFacturaCalipso)transaccion.getFacturaTransaccion()).getOrigenDocumento())) {
							cobradorInteresesTrasladados = transaccionPadre.getFacturaTransaccion().getCobradorInteresesTrasladados();
							cobradorInteresesBonificados = transaccionPadre.getFacturaTransaccion().getImporteBonificacionIntereses();
							acuerdoTrasladoCargo = transaccionPadre.getFacturaTransaccion().getAcuerdoTrasladoCargo();
							break;
						}
					}
				}
				
				Traza.advertencia(getClass(), "~   Fin Calculo los intereses para facturas de transacciones con padres");
				
			} else {
				// Calculo los intereses para facturas de transacciones sin padres (no son diferencias de cambio)
				
				cobradorInteresesTrasladados = factura.getCobradorInteresesTrasladados();
				cobradorInteresesBonificados = factura.getImporteBonificacionIntereses();
				acuerdoTrasladoCargo = factura.getAcuerdoTrasladoCargo();
			}

			// Calculo los intereses general
			if (!Validaciones.isObjectNull(cobradorInteresesTrasladados) && cobradorInteresesTrasladados.compareTo(BigDecimal.ZERO) > 0) {
				tagFactura.setIntereses(Utilidad.formatCurrency(cobradorInteresesTrasladados, false, false));
	
				if (!Validaciones.isObjectNull(acuerdoTrasladoCargo)) {
					tagFactura.setAcuerdo(String.valueOf(acuerdoTrasladoCargo));
				} else {
					tagFactura.setAcuerdo(ESPACIO);
				}
	
			} else {
				tagFactura.setIntereses(ESPACIO);
				tagFactura.setAcuerdo(ESPACIO);
			}
	
			if (!Validaciones.isObjectNull(cobradorInteresesBonificados) && cobradorInteresesBonificados.compareTo(BigDecimal.ZERO) > 0) {
				tagFactura.setInteresesBonificados(Utilidad.formatCurrency(cobradorInteresesBonificados, false, false));				
			} else {
				tagFactura.setInteresesBonificados(ESPACIO);
			}
				
			// Otros datos
			tagFactura.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(factura.getImporteCobrar(), 2)));
			tagFactura.setVencimiento((factura.getFechaVencimiento() != null) ? Utilidad.formatDatePicker(factura.getFechaVencimiento()) : ESPACIO);

			// Cuando existe más de una transacción con la misma factura, y los tratamientos de intereses pueden variar, por ejemplo por usar 
			// en una transacción medios de pago que calculan intereses y en otra no, no se puede asumir un valor de tratamiento de intereses “correcto” 
			// al agrupar los pagos parciales de una factura. Por esto se definió que este campo viajará siempre en blanco, independientemente 
			// si la factura a cobrar se encuentra o no en mas de una transacción dentro del mismo cobro
			// tagFactura.setOperacion((factura.getQueHacerConIntereses() != null) ? factura.getQueHacerConIntereses().name() : ESPACIO);
			tagFactura.setOperacion(ESPACIO);
			
			tagFactura.setConsecuenciaCobro((factura.getGeneradoPorCobro() != null) ? 
					(SiNoEnum.SI.equals(factura.getGeneradoPorCobro()) ? String.valueOf(SiNoEnum.SI.getDescripcionCorta()): ESPACIO): ESPACIO);
			
			tagFactura.setPago((factura.getFechaValor() != null) ? Utilidad.formatDatePicker(factura.getFechaValor()):ESPACIO);

			//
			// Recorro la listas tagFactura actual a fin de verificar si la factura ya existe, de manera de poder agrupar en caso afirmativo
			//
			boolean facturaNoExisteEnListaFactura = true; 
			
			Traza.advertencia(getClass(), "~   Recorro la listas tagFactura actual a fin de verificar si la factura ya existe, de manera de poder agrupar en caso afirmativo"); 
			
			for (Factura tagFacturaEnLista : tagFacturas.getFactura()) {
				
				if (compararFacturaEnTagsFactura(tagFacturaEnLista, tagFactura)) {
						
					// Importe agrupado de factura

					BigDecimal importeFacturaEnLista  = BigDecimal.ZERO;
					BigDecimal importeAgrupadoFactura = BigDecimal.ZERO;

					if (!Validaciones.isObjectNull(tagFacturaEnLista.getImporte())) {
						importeFacturaEnLista = Utilidad.stringToBigDecimal(tagFacturaEnLista.getImporte());
					}
					
					importeAgrupadoFactura = importeFacturaEnLista.add(factura.getImporteCobrar());
					tagFacturaEnLista.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(importeAgrupadoFactura, 2)));

					// Intereses trasladados agrupados de factura

					BigDecimal importeInteresesTrasladadosFacturaEnLista  = BigDecimal.ZERO;
					BigDecimal importeAgrupadoInteresesTrasladadosFactura = BigDecimal.ZERO;

					if (!Validaciones.isObjectNull(tagFacturaEnLista.getIntereses())) {
						importeInteresesTrasladadosFacturaEnLista = Utilidad.stringToBigDecimal(tagFacturaEnLista.getIntereses());
					}

					if (!Validaciones.isObjectNull(factura.getCobradorInteresesTrasladados())) {
						importeAgrupadoInteresesTrasladadosFactura = importeInteresesTrasladadosFacturaEnLista.add(factura.getCobradorInteresesTrasladados());
						if (importeAgrupadoInteresesTrasladadosFactura.compareTo(BigDecimal.ZERO) > 0) {
							tagFacturaEnLista.setIntereses(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(importeAgrupadoInteresesTrasladadosFactura, 2)));
						}
					}

					// Intereses trasladados agrupados de factura

					BigDecimal importeInteresesBonificadosFacturaEnLista  = BigDecimal.ZERO;
					BigDecimal importeAgrupadoInteresesBonificadosFactura = BigDecimal.ZERO;

					if (!Validaciones.isObjectNull(tagFacturaEnLista.getInteresesBonificados())) {
						importeInteresesBonificadosFacturaEnLista = Utilidad.stringToBigDecimal(tagFacturaEnLista.getInteresesBonificados()); 
					}

					if (!Validaciones.isObjectNull(factura.getImporteBonificacionIntereses())) {
						importeAgrupadoInteresesBonificadosFactura = importeInteresesBonificadosFacturaEnLista.add(factura.getImporteBonificacionIntereses());
						if (importeAgrupadoInteresesBonificadosFactura.compareTo(BigDecimal.ZERO) > 0) {
							tagFacturaEnLista.setInteresesBonificados(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(importeAgrupadoInteresesBonificadosFactura, 2)));
						}
					}

					facturaNoExisteEnListaFactura = false;
				}
			}
			
			if (facturaNoExisteEnListaFactura) {
				tagFacturas.getFactura().add(tagFactura);
				String strReferencia ="";
				
				if (Constantes.CALIPSO_CAL.equals(tagFactura.getOrigen())) {
					// Facturas de Calipso
					strReferencia = tagFactura.getClase() + tagFactura.getComprobante() + tagFactura.getNumero() + tagFactura.getSucursal();
				} else if (Constantes.MIC.equals(tagFactura.getOrigen())) {
					// Facturas de MIC
					strReferencia = tagFactura.getReferencia();
				} else {
					// Otros Débitos
					strReferencia = tagFactura.getClase() + tagFactura.getComprobante() + tagFactura.getNumero() + tagFactura.getSucursal() + tagFactura.getReferencia();
				}

				if (!listaReferencias.contains(strReferencia)) {
					listaReferencias.add(strReferencia);
					tagFacturas.setCantidadFacturas(tagFacturas.getCantidadFacturas() + 1);
				}
			}			
			
			importeTotal = importeTotal.add(factura.getImporteCobrar());

			Traza.advertencia(getClass(), "~   Recorro la lista de medios de pago de la transaccion para armar la info de medios de pago en el tag <medio>");
			
			for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {

				Traza.advertencia(getClass(), "~   Armo los datos del medio de pago (" + medioPago.getIdMedioPago() +") en el tag <factura>, siempre que sea diferente de estado 'NUEVO'"); 

				if (!EstadoFacturaMedioPagoEnum.NUEVO.equals(medioPago.getEstado())) {

					boolean medioPagoEsUnChequeRechazado = false;
					
					Medio tagMedioPago = new Medio();
					Cheque tagCheque = new Cheque();

					tagMedioPago.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(medioPago.getImporte(), 2)));
					tagMedioPago.setMonedaOrigen(medioPago.getMoneda().getSigno2());
					
					tagMedioPago.setReferencia(ESPACIO);
					tagMedioPago.setComprobante(ESPACIO);
					tagMedioPago.setClase(ESPACIO);
					tagMedioPago.setSucursal(ESPACIO);
					tagMedioPago.setNumero(ESPACIO);
					tagMedioPago.setVencimiento(ESPACIO);

					tagMedioPago.setImporteMonedaOrigen(ESPACIO);
					tagMedioPago.setTipoCambio(ESPACIO);
					tagMedioPago.setConsecuenciaCobro(ESPACIO);
					tagMedioPago.setLlamadaPiePagina(ESPACIO);
					
					if (medioPago instanceof ShvCobMedioPagoShiva) {
						
						tagMedioPago.setOrigen(Constantes.SHIVA);
						
						ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva) medioPago);
						
						VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
						filtro.setIdValor(String.valueOf(medioPagoShiva.getIdValor()));
						List<VistaSoporteResultadoBusquedaValor> listaValores = vistaSoporteServicio.buscarValores(filtro);
						
						if (Validaciones.isCollectionNotEmpty(listaValores)) {
							VistaSoporteResultadoBusquedaValor vistaValor = listaValores.get(0);
							
							tagMedioPago.setDescripcion((vistaValor.getTipoValor() != null) ? Utilidad.capitalizarCadena(vistaValor.getTipoValor()) : ESPACIO);
							String[] referencias = Utilidad.armarReferencias(vistaValor.getNroValor().split("<br>"));
							tagMedioPago.setReferencia1(referencias[0]);
							tagMedioPago.setReferencia2(referencias[1]);
							tagMedioPago.setReferencia3(referencias[2]);
							
							if (aplicarTratamientoChequesRechazados) {
								if ((vistaValor.getEstadoValor().equals(Estado.VAL_SUSPENDIDO.descripcion()) || vistaValor.getEstadoValor().equals(Estado.VAL_ANULADO.descripcion())) 
										&& EstadoValorMotivoSuspensionEnum.VAL_MOTIVO_SUSPENSION_CHEQUE_RECHAZADO.descripcion().equals(vistaValor.getMotivoSuspension())) {
	
									tagCheque.setDescripcion((vistaValor.getTipoValor() != null) ? Utilidad.capitalizarCadena(vistaValor.getTipoValor()) : ESPACIO);
									tagCheque.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(medioPagoShiva.getImporte(), 2)));
									tagCheque.setReferencia1(referencias[0]);
									tagCheque.setReferencia2(referencias[1]);
									tagCheque.setReferencia3(referencias[2]);
									tagCheque.setOrigen(Constantes.SHIVA);
									tagCheque.setMonedaOrigen(MonedaEnum.PES.getSigno2());
									tagCheque.setImporteMonedaOrigen(ESPACIO);
									tagCheque.setTipoCambio(ESPACIO);
									importeTotal = importeTotal.subtract(medioPagoShiva.getImporte());
									
									//
									// Recorro la listas tagChequeRechazado actual a fin de verificar si el cheque ya existe, de manera de poder agrupar 
									// (en realidad de no agregar) en caso afirmativo
									//
									boolean chequeRechazadoNoExisteEnListaChequeRechazado = true; 
									for (Cheque tagChequeRechazadoEnLista : tagChequeRechazado.getCheque()) {
										
										if (compararTagsChequeRechazado(tagChequeRechazadoEnLista, tagCheque)) {
											
											BigDecimal importeChequeRechazadoEnLista = Utilidad.stringToBigDecimal(tagChequeRechazadoEnLista.getImporte()); 
											BigDecimal importeAgrupadoChequeRechazado = importeChequeRechazadoEnLista.add(medioPagoShiva.getImporte());
											
											tagChequeRechazadoEnLista.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(importeAgrupadoChequeRechazado, 2)));
											chequeRechazadoNoExisteEnListaChequeRechazado = false;
										}
									}
									
									if (chequeRechazadoNoExisteEnListaChequeRechazado) {
										tagChequeRechazado.getCheque().add(tagCheque);
									}
									
									medioPagoEsUnChequeRechazado = true;
								}
							}
						}

					} else {

						tagMedioPago.setDescripcion((medioPago.getTipoMedioPago() != null) ? Utilidad.capitalizarCadena(medioPago.getTipoMedioPago().getDescripcion()).replace("iibb", "IIBB") : ESPACIO);
						tagMedioPago.setReferencia1(ESPACIO);

						if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {
							
							tagMedioPago.setOrigen(Constantes.MIC);
							
							ShvCobMedioPagoNotaCreditoMic ncMic = (ShvCobMedioPagoNotaCreditoMic) medioPago;
							if (!Validaciones.isObjectNull(ncMic.getClaseComprobante())
									&& !Validaciones.isNullOrEmpty(ncMic.getSucursalComprobante())
									&& !Validaciones.isNullOrEmpty(ncMic.getNroComprobante())) {
								
								StringBuffer strReferenciaNotaCreditoMic = new StringBuffer("");
								
								strReferenciaNotaCreditoMic.append("Nro. Documento: ");
								strReferenciaNotaCreditoMic.append(Utilidad.parsearNroDocNoDuc(ncMic.getClaseComprobante(), ncMic.getSucursalComprobante(), ncMic.getNroComprobante()));
								strReferenciaNotaCreditoMic.append(" | ");
								strReferenciaNotaCreditoMic.append("Nro. Referencia: ");
								strReferenciaNotaCreditoMic.append(ncMic.getNumeroNotaCredito());
								
								String[] referencias = Utilidad.armarReferencias(strReferenciaNotaCreditoMic.toString().split("<br>"));
								tagMedioPago.setReferencia1(referencias[0]);
								tagMedioPago.setReferencia2(referencias[1]);
								tagMedioPago.setReferencia3(referencias[2]);
								
								tagMedioPago.setComprobante(!Validaciones.isObjectNull(ncMic.getTipoComprobante()) ? ncMic.getTipoComprobante().name() : ESPACIO);
								tagMedioPago.setClase(!Validaciones.isObjectNull(ncMic.getClaseComprobante()) ? (ncMic.getClaseComprobante().equals(TipoClaseComprobanteEnum.S) || ncMic.getClaseComprobante().equals(TipoClaseComprobanteEnum.D)) ? ESPACIO:String.valueOf(ncMic.getClaseComprobante()) : ESPACIO);
								tagMedioPago.setNumero(!Validaciones.isObjectNull(ncMic.getNroComprobante()) ? Utilidad.rellenarCerosIzquierda(ncMic.getNroComprobante(), 8) : ESPACIO);
								tagMedioPago.setSucursal(!Validaciones.isObjectNull(ncMic.getSucursalComprobante()) ? Utilidad.rellenarCerosIzquierda(ncMic.getSucursalComprobante(), 4) : ESPACIO);
								tagMedioPago.setVencimiento(!Validaciones.isObjectNull(ncMic.getFechaVencimiento()) ? Utilidad.formatDatePicker(ncMic.getFechaVencimiento()) : ESPACIO);
							}
							
						} else if (medioPago instanceof ShvCobMedioPagoRemanente) {
							
							tagMedioPago.setOrigen(Constantes.MIC);

							ShvCobMedioPagoRemanente remMic = (ShvCobMedioPagoRemanente) medioPago;
							StringBuffer str = new StringBuffer();
							String nroDoc = remMic.getCuentaRemanente() != null ? (new Formatter().format("%013d", new Long(remMic.getCuentaRemanente()))).toString():"";
							str.append(nroDoc);

							if (TipoRemanenteEnum.TRANSFERIBLE_ACTUALIZABLE.equals(remMic.getTipoRemanente())) {
								str.append("-");
								str.append(Utilidad.formatDateAAMMDD(remMic.getFechaAlta()));
							}
							tagMedioPago.setReferencia1("Cuenta: " + str.toString());
							
						} else if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
							
							tagMedioPago.setOrigen(Constantes.MIC);

							ShvCobMedioPagoDebitoProximaFacturaMic debProxMic = (ShvCobMedioPagoDebitoProximaFacturaMic) medioPago;
							StringBuffer str = new StringBuffer();
							str.append(debProxMic.getAcuerdoTrasladoCargo() != null ? debProxMic.getAcuerdoTrasladoCargo().toString() : "");
							tagMedioPago.setReferencia1("Acuerdo de facturación: " + str.toString());
						}
						
						if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
							
							tagMedioPago.setOrigen(Constantes.CALIPSO_CAL);

							ShvCobMedioPagoNotaCreditoCalipso ncCalipso = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;
							String nroDoc = "";
							
							MonedaEnum monedaOperacionNc = ncCalipso.getTransaccion().getOperacion().getMonedaOperacion();
							
							if (!Validaciones.isObjectNull(ncCalipso.getClaseComprobante())
									&& !Validaciones.isNullOrEmpty(ncCalipso.getSucursalComprobante())
									&& !Validaciones.isNullOrEmpty(ncCalipso.getNroComprobante())) {
								
								nroDoc=Utilidad.parsearNroDocNoDuc(ncCalipso.getClaseComprobante(), ncCalipso.getSucursalComprobante(), ncCalipso.getNroComprobante());
								tagMedioPago.setReferencia1("Nro. Documento: " + nroDoc);
								
								tagMedioPago.setComprobante(!Validaciones.isObjectNull(ncCalipso.getTipoComprobante()) ? ncCalipso.getTipoComprobante().name() : ESPACIO);
								tagMedioPago.setClase(!Validaciones.isObjectNull(ncCalipso.getClaseComprobante()) ? 
											(ncCalipso.getClaseComprobante().equals(TipoClaseComprobanteEnum.S) 
														|| ncCalipso.getClaseComprobante().equals(TipoClaseComprobanteEnum.D)) ? ESPACIO:String.valueOf(ncCalipso.getClaseComprobante()) : ESPACIO);
								
								tagMedioPago.setNumero(!Validaciones.isObjectNull(ncCalipso.getNroComprobante()) ? Utilidad.rellenarCerosIzquierda(ncCalipso.getNroComprobante(), 8) : ESPACIO);
								tagMedioPago.setSucursal(!Validaciones.isObjectNull(ncCalipso.getSucursalComprobante()) ? Utilidad.rellenarCerosIzquierda(ncCalipso.getSucursalComprobante(), 4) : ESPACIO);
								tagMedioPago.setVencimiento(!Validaciones.isObjectNull(ncCalipso.getFechaVencimiento()) ? Utilidad.formatDatePicker(ncCalipso.getFechaVencimiento()) : ESPACIO);
							}

							if (SiNoEnum.SI.equals(ncCalipso.getGeneradoPorCobro())) {
								tagMedioPago.setConsecuenciaCobro(String.valueOf(SiNoEnum.SI.getDescripcionCorta()));
							}

							if (esDocumentoGeneradoPorDiferenciaCambio(ncCalipso)) {
								// Seteamos por defecto los tres asteriscos ya que la nota de pie de documentos generados por diferencia de cambio
								// tiene la priodidad por sobre el tema de intereses
								tagMedioPago.setLlamadaPiePagina(NOTA_PIE_ASTERISCOS_POR_TRES);
								tagMedioPago.setLeyendaPiePagina(LeyendaPiePaginaDocumentoScardEnum.DOCUMENTO_GENERADO_POR_DIF_CAMBIO);
							}
							
							if (!ncCalipso.getMoneda().equals(monedaOperacionNc)) {
								tagMedioPago.setImporteMonedaOrigen(
										!Validaciones.isObjectNull(ncCalipso.getImporteAplicadoAFechaEmisionMonedaOrigen()) ?
												Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(ncCalipso.getImporteAplicadoAFechaEmisionMonedaOrigen(), 2)) : ESPACIO);

								tagMedioPago.setTipoCambio(
										!Validaciones.isObjectNull(ncCalipso.getTipoDeCambioFechaEmision()) ? 
												formatCurrencyTipoCambio(ncCalipso.getTipoDeCambioFechaEmision()): ESPACIO);
							}

						} else if (medioPago instanceof ShvCobMedioPagoCTA){

							tagMedioPago.setOrigen(Constantes.CALIPSO_CAL);

							ShvCobMedioPagoCTA ctaCalipso = (ShvCobMedioPagoCTA)medioPago;
							String nroDoc ="";
							
							MonedaEnum monedaOperacionCta = ctaCalipso.getTransaccion().getOperacion().getMonedaOperacion();
							
							if(!Validaciones.isObjectNull(ctaCalipso.getClaseComprobante())
									&& !Validaciones.isNullOrEmpty(ctaCalipso.getSucursalComprobante())
									&& !Validaciones.isNullOrEmpty(ctaCalipso.getNroComprobante())){
								
								nroDoc=Utilidad.parsearNroDocNoDuc(ctaCalipso.getClaseComprobante(), ctaCalipso.getSucursalComprobante(), ctaCalipso.getNroComprobante());
								tagMedioPago.setReferencia1(nroDoc);
								
								tagMedioPago.setComprobante(!Validaciones.isObjectNull(ctaCalipso.getTipoComprobante()) ? ctaCalipso.getTipoComprobante().name() : ESPACIO);
								tagMedioPago.setClase(!Validaciones.isObjectNull(ctaCalipso.getClaseComprobante()) ? 
										(ctaCalipso.getClaseComprobante().equals(TipoClaseComprobanteEnum.S) 
												|| ctaCalipso.getClaseComprobante().equals(TipoClaseComprobanteEnum.D)) ? ESPACIO:String.valueOf(ctaCalipso.getClaseComprobante()) : ESPACIO);

								tagMedioPago.setNumero(!Validaciones.isObjectNull(ctaCalipso.getNroComprobante()) ? Utilidad.rellenarCerosIzquierda(ctaCalipso.getNroComprobante(), 8) : ESPACIO);
								tagMedioPago.setSucursal(!Validaciones.isObjectNull(ctaCalipso.getSucursalComprobante()) ? Utilidad.rellenarCerosIzquierda(ctaCalipso.getSucursalComprobante(), 4) : ESPACIO);
							}
							
							if (!ctaCalipso.getMoneda().equals(monedaOperacionCta)) {
								tagMedioPago.setImporteMonedaOrigen(
										!Validaciones.isObjectNull(ctaCalipso.getImporteAplicadoAFechaEmisionMonedaOrigen()) ?
												Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(ctaCalipso.getImporteAplicadoAFechaEmisionMonedaOrigen(), 2)) : ESPACIO);

								tagMedioPago.setTipoCambio(
										!Validaciones.isObjectNull(ctaCalipso.getTipoDeCambioFechaCobro()) ? 
												formatCurrencyTipoCambio(ctaCalipso.getTipoDeCambioFechaCobro()): ESPACIO);
							} else {

								tagMedioPago.setTipoCambio(ESPACIO);
							}
							
						} else if (medioPago instanceof ShvCobMedioPagoDesistimiento
									|| medioPago instanceof ShvCobMedioPagoCompensacionOtras
									|| medioPago instanceof ShvCobMedioPagoCompensacionCesionCredito
									|| medioPago instanceof ShvCobMedioPagoCompensacionIntercompany) {
						
							tagMedioPago.setOrigen(Constantes.SHIVA);
							tagMedioPago.setReferencia1("Referencia: " + medioPago.getReferencia());
						
						} else if (medioPago instanceof ShvCobMedioPagoCompensacionIIBB) {
							
							ShvCobMedioPagoCompensacionIIBB compensacionIIBB = (ShvCobMedioPagoCompensacionIIBB) medioPago;
							
							tagMedioPago.setOrigen(Constantes.SHIVA);
							tagMedioPago.setReferencia1("Referencia: " + medioPago.getReferencia() + " | Provincia: " + compensacionIIBB.getProvincia().getDescripcion());
							
						} else if (medioPago instanceof ShvCobMedioPagoCompensacionLiquidoProducto) {
							
							ShvCobMedioPagoCompensacionLiquidoProducto compensacionLiqProd = (ShvCobMedioPagoCompensacionLiquidoProducto) medioPago;
							
							StringBuffer strReferenciaCompensacionLiqProd = new StringBuffer("");
							strReferenciaCompensacionLiqProd.append("Referencia: ");
							strReferenciaCompensacionLiqProd.append(medioPago.getReferencia());
							strReferenciaCompensacionLiqProd.append(" | ");
							strReferenciaCompensacionLiqProd.append("Subtipo: ");
							strReferenciaCompensacionLiqProd.append(compensacionLiqProd.getSubTipo().getDescripcion());
							strReferenciaCompensacionLiqProd.append(" | ");
							strReferenciaCompensacionLiqProd.append("Nro Doc Interno: ");
							strReferenciaCompensacionLiqProd.append(compensacionLiqProd.getDocumentoCap().getIdDocumento());
							
							String[] referencia = Utilidad.armarReferencias(strReferenciaCompensacionLiqProd.toString().split("<br>"));
							tagMedioPago.setOrigen(Constantes.SHIVA);
							
							tagMedioPago.setReferencia1(referencia[0]);
							tagMedioPago.setReferencia2(referencia[1]);
							tagMedioPago.setReferencia3(referencia[2]);
							
						} else if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso){
							
							tagMedioPago.setOrigen(Constantes.CALIPSO_CAL);

							ShvCobMedioPagoDebitoProximaFacturaCalipso debProxClp = (ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago;
							StringBuffer str = new StringBuffer();
							str.append(debProxClp.getAcuerdoTrasladoCargo() != null ? debProxClp.getAcuerdoTrasladoCargo().toString() : "");
							tagMedioPago.setReferencia1("Acuerdo de facturación: " + str.toString());
							
						}  else if (medioPago instanceof ShvCobMedioPagoCompensacionProveedor) {
							
							ShvCobMedioPagoCompensacionProveedor compensacionProveed = (ShvCobMedioPagoCompensacionProveedor) medioPago;
							
							StringBuffer strReferenciaCompensacionProveed = new StringBuffer("");
							strReferenciaCompensacionProveed.append("Referencia: ");
							strReferenciaCompensacionProveed.append(medioPago.getReferencia());
							strReferenciaCompensacionProveed.append(" | ");
							strReferenciaCompensacionProveed.append("Nro Doc Interno: ");
							strReferenciaCompensacionProveed.append(compensacionProveed.getDocumentoCap().getIdDocumento());
							
							String[] referencia = Utilidad.armarReferencias(strReferenciaCompensacionProveed.toString().split("<br>"));
							tagMedioPago.setOrigen(Constantes.SHIVA);

							tagMedioPago.setReferencia1(referencia[0]);
							tagMedioPago.setReferencia2(referencia[1]);
							tagMedioPago.setReferencia3(referencia[2]);
						}
						
						if (Validaciones.isObjectNull(tagMedioPago.getReferencia2())) {
							tagMedioPago.setReferencia2(ESPACIO);
						}

						if (Validaciones.isObjectNull(tagMedioPago.getReferencia3())) {
							tagMedioPago.setReferencia3(ESPACIO);
						}
					}
					
					//
					// Solo voy a agregar los medios de pago a la lista, si no es un cheque rechazado
					//
					if (!medioPagoEsUnChequeRechazado) {
					
						Traza.advertencia(getClass(), "~   Recorro la listas tagMedioPago actual a fin de verificar si el medio de pago ya existe, de manera de poder agrupar en caso afirmativo"); 

						//
						// Recorro la listas tagMedioPago actual a fin de verificar si el medio de pago ya existe, de manera de poder agrupar en caso afirmativo
						//
						boolean medioPagoNoExisteEnListaMedioPago = true; 
						for (Medio tagMedioPagoEnLista : tagMedioDePago.getMedio()) {
							
							if (compararMedioPagoEnTagsMedioPago(tagMedioPagoEnLista, tagMedioPago)) {
								
								BigDecimal importeMedioPagoEnLista = Utilidad.stringToBigDecimal(tagMedioPagoEnLista.getImporte()); 
								BigDecimal importeAgrupadoMedioPago = importeMedioPagoEnLista.add(medioPago.getImporte());
								
								tagMedioPagoEnLista.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(importeAgrupadoMedioPago, 2)));
								medioPagoNoExisteEnListaMedioPago = false;
							}
						}
						
						if (medioPagoNoExisteEnListaMedioPago) {
							tagMedioDePago.getMedio().add(tagMedioPago);
						}
					}
				}
			}
		}

		tagFacturas.setImporteTotal(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(importeTotal, 2)));

		tagDetalle.setFacturas(tagFacturas);
		tagDetalle.setMediosDePago(tagMedioDePago);
		tagDetalle.setChequesRechazados(tagChequeRechazado);

		if (!tagChequeRechazado.getCheque().isEmpty()) {
			tagDetalle.setChequesRechazados(tagChequeRechazado);
		}
		
		Utilidad.tracearTiempo(getClass(), "Finalizo el metodo 'mapearDetalleTransaccionDebito' con una duracion de: ", fechaHoraInicioNanoTime);
		return tagDetalle;
	}
	
	/**
	 * Realiza la comparacion entre dos tags de factura.
	 * Retornará verdadero en caso de que sean iguales.
	 * 
	 * @param tagFacturaEnLista
	 * @param tagFactura
	 * @return
	 */
	private boolean compararFacturaEnTagsFactura(Factura tagFacturaEnLista, Factura tagFactura) {
		
		StringBuffer claveUnicaComparacionTagFacturaEnLista = new StringBuffer(); 
			// Origen
			claveUnicaComparacionTagFacturaEnLista.append(tagFacturaEnLista.getOrigen());
			claveUnicaComparacionTagFacturaEnLista.append(Constantes.SEPARADOR_PIPE);

			// Documento legal
			claveUnicaComparacionTagFacturaEnLista.append(tagFacturaEnLista.getComprobante());
			claveUnicaComparacionTagFacturaEnLista.append(Constantes.SEPARADOR_PIPE);
			claveUnicaComparacionTagFacturaEnLista.append(tagFacturaEnLista.getClase());
			claveUnicaComparacionTagFacturaEnLista.append(Constantes.SEPARADOR_PIPE);
			claveUnicaComparacionTagFacturaEnLista.append(tagFacturaEnLista.getSucursal());
			claveUnicaComparacionTagFacturaEnLista.append(Constantes.SEPARADOR_PIPE);
			claveUnicaComparacionTagFacturaEnLista.append(tagFacturaEnLista.getNumero());
			claveUnicaComparacionTagFacturaEnLista.append(Constantes.SEPARADOR_PIPE);

			// Referencia (repito el comprobante ya que me ayuda para formar una clave para los DUCs)
			claveUnicaComparacionTagFacturaEnLista.append(tagFacturaEnLista.getComprobante());
			claveUnicaComparacionTagFacturaEnLista.append(Constantes.SEPARADOR_PIPE);
			claveUnicaComparacionTagFacturaEnLista.append(tagFacturaEnLista.getReferencia());
			claveUnicaComparacionTagFacturaEnLista.append(Constantes.SEPARADOR_PIPE);

			// Fecha de Imputacion
			claveUnicaComparacionTagFacturaEnLista.append(tagFacturaEnLista.getPago());
	
			StringBuffer claveUnicaComparacionTagFactura = new StringBuffer(); 
			// Origen
			claveUnicaComparacionTagFactura.append(tagFactura.getOrigen());
			claveUnicaComparacionTagFactura.append(Constantes.SEPARADOR_PIPE);

			// Documento legal
			claveUnicaComparacionTagFactura.append(tagFactura.getComprobante());
			claveUnicaComparacionTagFactura.append(Constantes.SEPARADOR_PIPE);
			claveUnicaComparacionTagFactura.append(tagFactura.getClase());
			claveUnicaComparacionTagFactura.append(Constantes.SEPARADOR_PIPE);
			claveUnicaComparacionTagFactura.append(tagFactura.getSucursal());
			claveUnicaComparacionTagFactura.append(Constantes.SEPARADOR_PIPE);
			claveUnicaComparacionTagFactura.append(tagFactura.getNumero());
			claveUnicaComparacionTagFactura.append(Constantes.SEPARADOR_PIPE);

			// Referencia (repito el comprobante ya que me ayuda para formar una clave para los DUCs)
			claveUnicaComparacionTagFactura.append(tagFactura.getComprobante());
			claveUnicaComparacionTagFactura.append(Constantes.SEPARADOR_PIPE);
			claveUnicaComparacionTagFactura.append(tagFactura.getReferencia());
			claveUnicaComparacionTagFactura.append(Constantes.SEPARADOR_PIPE);

			// Fecha de Imputacion
			claveUnicaComparacionTagFactura.append(tagFactura.getPago());		
				
		return claveUnicaComparacionTagFacturaEnLista.toString().equals(claveUnicaComparacionTagFactura.toString());
	}
	
	/**
	 * Realiza la comparacion entre dos tags de medio de pago conteniendo información de medios de pago.
	 * Retornará verdadero en caso de que sean iguales.
	 * 
	 * @param tagMedioPagoEnLista
	 * @param tagMedioPago
	 * @return
	 */
	private boolean compararMedioPagoEnTagsMedioPago(Medio tagMedioPagoEnLista, Medio tagMedioPago) {
		
		StringBuffer referenciaTagMedioPagoEnLista = new StringBuffer();
			referenciaTagMedioPagoEnLista.append(tagMedioPagoEnLista.getDescripcion());
			referenciaTagMedioPagoEnLista.append(tagMedioPagoEnLista.getReferencia1());			
			referenciaTagMedioPagoEnLista.append(tagMedioPagoEnLista.getReferencia2());
			referenciaTagMedioPagoEnLista.append(tagMedioPagoEnLista.getReferencia3());
		
		StringBuffer referenciaTagMedioPago = new StringBuffer(); 
			referenciaTagMedioPago.append(tagMedioPago.getDescripcion());	
			referenciaTagMedioPago.append(tagMedioPago.getReferencia1());
			referenciaTagMedioPago.append(tagMedioPago.getReferencia2()); 
			referenciaTagMedioPago.append(tagMedioPago.getReferencia3());

		return referenciaTagMedioPagoEnLista.toString().equals(referenciaTagMedioPago.toString());
	}
	
	/**
	 * Realiza la comparacion entre dos tags de factura conteniendo información de medios de pago.
	 * Retornará verdadero en caso de que sean iguales.
	 * 
	 * @param tagFacturaEnLista
	 * @param tagFactura
	 * @return
	 */
	private boolean compararMedioPagoEnTagsFactura(Factura tagFacturaEnLista, Factura tagFactura) {
		
		StringBuffer referenciaTagFacturaEnLista = new StringBuffer();
			referenciaTagFacturaEnLista.append(tagFacturaEnLista.getOrigen());
			referenciaTagFacturaEnLista.append(tagFacturaEnLista.getReferencia());			
		
		StringBuffer referenciaTagFactura = new StringBuffer(); 
			referenciaTagFactura.append(tagFactura.getOrigen());	
			referenciaTagFactura.append(tagFactura.getReferencia());

		return referenciaTagFacturaEnLista.toString().equals(referenciaTagFactura.toString());
	}

	/**
	 * Realiza la compararción entre dos tags de cheque
	 * Retornará verdadero en caso de ambos sean iguales
	 * 
	 * @param tagChequeRechazadoEnLista
	 * @param tagChequeRechazado
	 * @return
	 */
	private boolean compararTagsChequeRechazado(Cheque tagChequeRechazadoEnLista, Cheque tagChequeRechazado) {
		
		StringBuffer referenciaTagChequeRechazadoEnLista = new StringBuffer();
			referenciaTagChequeRechazadoEnLista.append(tagChequeRechazadoEnLista.getDescripcion());
			referenciaTagChequeRechazadoEnLista.append(tagChequeRechazadoEnLista.getReferencia1());
			referenciaTagChequeRechazadoEnLista.append(tagChequeRechazadoEnLista.getReferencia2());
			referenciaTagChequeRechazadoEnLista.append(tagChequeRechazadoEnLista.getReferencia3());
		
		StringBuffer referenciaTagChequeRechazado = new StringBuffer();
			referenciaTagChequeRechazado.append(tagChequeRechazado.getDescripcion());
			referenciaTagChequeRechazado.append(tagChequeRechazado.getReferencia1());
			referenciaTagChequeRechazado.append(tagChequeRechazado.getReferencia2());
			referenciaTagChequeRechazado.append(tagChequeRechazado.getReferencia3());
		
		return referenciaTagChequeRechazadoEnLista.toString().equals(referenciaTagChequeRechazado.toString());
	}
	
	/**
 	 * Realiza la comparacion entre dos tags de medio de pago utilizados para contener
 	 * información de tratamientos de la diferencia, aplicaciones manuales, o conjuntos de débitos.
	 * Retornará verdadero en caso de que sean iguales.
	 *
	 * @param tagMedioPagoEnLista
	 * @param tagMedioPago
	 * @return
	 */
	private boolean compararReintegroEnTagsMedioPago(Medio tagMedioPagoEnLista, Medio tagMedioPago) {

		StringBuffer referenciaTagReintegroEnLista = new StringBuffer();
			referenciaTagReintegroEnLista.append(tagMedioPagoEnLista.getOrigen());
			referenciaTagReintegroEnLista.append(tagMedioPagoEnLista.getDescripcion());
		
		StringBuffer referenciaTagReintegro = new StringBuffer();
			referenciaTagReintegro.append(tagMedioPago.getOrigen());
			referenciaTagReintegro.append(tagMedioPago.getDescripcion());
		
		return referenciaTagReintegroEnLista.toString().equals(referenciaTagReintegro.toString());
	}
	
	/**
	 * @param listaTransacciones
	 * en el medio de pago completar info del reintegro / conjunto de debitos / aplicaciones manuales
	 * 		importe 		--> importe del reintegro
	 * 		descripcion		--> descripcion del tipo de reintegro
	 * 		origen			--> SHV
	 * 
	 * en la factura completar los datos del medio de pago
	 * 		importe total	--> importe de los medios de pago
	 * 		cantidad de facturas --> cantidad de medios de pago 
	 * 		Referencia
	 * 			referencia del medio de pago usada en la grilla de transacciones, menos NC y CTA
	 * 		Para NC y CTA usar comprobante - clase sucursal y numero
	 * 
	 * 		moneda --> $
	 * 		importe: importe del medio de pago
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	private Detalle mapearDetalleTransaccionReintegro(Collection<ShvCobTransaccion> listaTransacciones, TipoOperacionEnum tipoOperacion) throws NegocioExcepcion {
		double fechaHoraInicioNanoTime = System.nanoTime();
		Traza.advertencia(getClass(), "Comienzo del metodo 'mapearDetalleTransaccionReintegro'");
		Facturas tagFacturas = new Facturas();
		MedioDePago tagMediosDePago = new MedioDePago();

		BigDecimal importeTotal = BigDecimal.ZERO;
		BigDecimal importeAgrupadoDocumento = BigDecimal.ZERO;
		
		String codigosOperacionExterno = this.obtenerCodigosOperacionExterno(tipoOperacion, listaTransacciones); 
		
		Traza.advertencia(getClass(), "~ Recorro la lista de transacciones");
		for (ShvCobTransaccion transaccion : listaTransacciones) {
			
			Traza.advertencia(getClass(), "~ idOperacion: " + transaccion.getOperacion().getIdOperacion() + " ~ idTransaccion: " + transaccion.getIdTransaccion());
			Medio tagMedio = new Medio();
			
			if (esTransaccionConConjuntoDeDebitos(transaccion)) {
				
				//
				// Mapeo el conjunto de débitos en la sección de medios de pago
				//
				ShvCobFacturaUsuario facturaUsuario = (ShvCobFacturaUsuario) transaccion.getFactura();
				Traza.advertencia(getClass(), "~   Armo los datos de la factura(" + facturaUsuario.getIdFactura() +") en el tag <medio>"); 
				
				String vArmarRefencia[] = Utilidad.armarReferencias(
						facturaUsuario.getSistemaOrigen().getDescripcion(),
						codigosOperacionExterno
					);

				tagMedio.setDescripcion(Utilidad.capitalizarCadena("Aplicación Conjunto Débitos"));
				tagMedio.setReferencia1(vArmarRefencia[0]);
				tagMedio.setReferencia2(vArmarRefencia[1]);
				tagMedio.setReferencia3(vArmarRefencia[2]);
				
				tagMedio.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(facturaUsuario.getImporteCobrar(), 2)));
				tagMedio.setMonedaOrigen(facturaUsuario.getMoneda().getSigno2());
				tagMedio.setOrigen(facturaUsuario.getSistemaOrigen().getDescripcion());
				
				importeAgrupadoDocumento = facturaUsuario.getImporteCobrar();
				
			} else { 
			
				//
				// Mapeo el tratamiento de la diferencia en la sección de medios de pago
				//
				ShvCobTratamientoDiferencia tratamientoDiferencia = transaccion.getTratamientoDiferencia();
				Traza.advertencia(getClass(), "~   Armo los datos del tratamiento de la diferencia (" + tratamientoDiferencia.getIdTratamientoDiferencia() +") en el tag <medio>"); 

				if (esTransaccionConAplicacionManual(transaccion)) {
				
					String vArmarRefencia[] = Utilidad.armarReferencias(
						tratamientoDiferencia.getSistemaOrigen().getDescripcion(),
						tratamientoDiferencia.getReferencia(),
						codigosOperacionExterno
					);
					tagMedio.setDescripcion(Utilidad.capitalizarCadena(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.getDescripcion()));
					tagMedio.setReferencia1(vArmarRefencia[0]);
					tagMedio.setReferencia2(vArmarRefencia[1]);
					tagMedio.setReferencia3(vArmarRefencia[2]);
	
				} else if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(tratamientoDiferencia.getTipoTratamientoDiferencia()) 
						 	|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(tratamientoDiferencia.getTipoTratamientoDiferencia())) {
	
					String vrefencia[] = new String[1];
	
					if (!Validaciones.isNullEmptyOrDash(tratamientoDiferencia.getAcuerdoTrasladoCargo())) {
						vrefencia[0] = "Sistema: " + tratamientoDiferencia.getSistemaOrigen().getDescripcion() + " | Acuerdo: " + tratamientoDiferencia.getAcuerdoTrasladoCargo();
					} else {
						vrefencia[0] = "Sistema: " + tratamientoDiferencia.getSistemaOrigen().getDescripcion() + " | Acuerdo: ";	
					}
					String vArmarRefencia[] = Utilidad.armarReferencias(vrefencia);
					tagMedio.setDescripcion(Utilidad.capitalizarCadena(tratamientoDiferencia.getTipoTratamientoDiferencia().getDescripcion()));
					tagMedio.setReferencia1(vArmarRefencia[0]);
					tagMedio.setReferencia2(vArmarRefencia[1]);
					tagMedio.setReferencia3(vArmarRefencia[2]);
					
				} else {
					tagMedio.setDescripcion(Utilidad.capitalizarCadena(tratamientoDiferencia.getTipoTratamientoDiferencia().getDescripcion()));
					tagMedio.setReferencia1(ESPACIO);
					tagMedio.setReferencia2(ESPACIO);
					tagMedio.setReferencia3(ESPACIO);
				}

				tagMedio.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(tratamientoDiferencia.getImporte(), 2)));
				tagMedio.setMonedaOrigen(tratamientoDiferencia.getMoneda().getSigno2());
				tagMedio.setOrigen(Constantes.SHIVA);

				importeAgrupadoDocumento = tratamientoDiferencia.getImporte();
			}
			
			tagMedio.setReferencia(ESPACIO);
			tagMedio.setComprobante(ESPACIO);
			tagMedio.setClase(ESPACIO);
			tagMedio.setSucursal(ESPACIO);
			tagMedio.setNumero(ESPACIO);
			tagMedio.setVencimiento(ESPACIO);
			tagMedio.setImporteMonedaOrigen(ESPACIO);
			tagMedio.setTipoCambio(ESPACIO);
			tagMedio.setConsecuenciaCobro(ESPACIO);
			tagMedio.setLlamadaPiePagina(ESPACIO);

			//
			// Recorro la listas tagMedio actual a fin de verificar si el tratamiento o el conjunto de débitos ya existe, 
			// de manera de poder agrupar en caso afirmativo
			//
			boolean documentoNoExisteEnListaMedio = true; 
			
			Traza.advertencia(getClass(), "~   Recorro la listas tagMedio actual a fin de verificar si el tratamiento o el conjunto de débitos ya existe de manera de poder agrupar en caso afirmativo"); 
			
			for (Medio tagMedioEnLista : tagMediosDePago.getMedio()) {
				
				if (compararReintegroEnTagsMedioPago(tagMedioEnLista, tagMedio)) {
						
					// Importe agrupado de tratamiento

					BigDecimal importeEnLista 					  = BigDecimal.ZERO;
					BigDecimal importeAgrupado 					  = BigDecimal.ZERO;

					if (!Validaciones.isObjectNull(tagMedioEnLista.getImporte())) {
						importeEnLista = Utilidad.stringToBigDecimal(tagMedioEnLista.getImporte());
					}
					
					importeAgrupado = importeEnLista.add(importeAgrupadoDocumento);
					tagMedioEnLista.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(importeAgrupado, 2)));

					documentoNoExisteEnListaMedio = false;
				}
			}
			
			if (documentoNoExisteEnListaMedio) {
				tagMediosDePago.getMedio().add(tagMedio);
			}			
			
			Traza.advertencia(getClass(), "~   Recorro la lista de medios de pago de la transaccion para armar la info de medios de pago en el tag <factura>");
			//
			// Mapeo los medios de pago en la sección de facturas
			//
			for (ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()) {
			
				Traza.advertencia(getClass(), "~   Armo los datos del medio de pago (" + medioPago.getIdMedioPago() +") en el tag <factura>, siempre que sea diferente de estado 'NUEVO'"); 
				
				if (!EstadoFacturaMedioPagoEnum.NUEVO.equals(medioPago.getEstado())) {

					// Para datos generales de tagFacturas 
					importeTotal = importeTotal.add(medioPago.getImporte());
	
					// TagFactura
					Factura tagFactura = new Factura();
					
					// Importe y moneda
					tagFactura.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(medioPago.getImporte(), 2)));
					tagFactura.setMonedaOrigen(medioPago.getMoneda().getSigno2());
					
					tagFactura.setComprobante(ESPACIO);
					tagFactura.setClase(ESPACIO);
					tagFactura.setSucursal(ESPACIO);
					tagFactura.setNumero(ESPACIO);
	
					if (medioPago instanceof ShvCobMedioPagoNotaCreditoMic) {
						ShvCobMedioPagoNotaCreditoMic medioPagoNotaCreditoMic = (ShvCobMedioPagoNotaCreditoMic) medioPago;
						
						tagFactura.setComprobante(medioPagoNotaCreditoMic.getTipoComprobante().getValor());
						tagFactura.setClase(obtenerClaseComprobanteFormateado(medioPagoNotaCreditoMic.getClaseComprobante()));
						tagFactura.setSucursal(obtenerSucursalComprobanteFormateado(medioPagoNotaCreditoMic.getSucursalComprobante()));
						tagFactura.setNumero(obtenerNumeroComprobanteFormateado(medioPagoNotaCreditoMic.getNroComprobante()));
						
					} else if (medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso) {
						ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;
						
						tagFactura.setComprobante(medioPagoNotaCreditoCalipso.getTipoComprobante().getValor());
						tagFactura.setClase(obtenerClaseComprobanteFormateado(medioPagoNotaCreditoCalipso.getClaseComprobante()));
						tagFactura.setSucursal(obtenerSucursalComprobanteFormateado(medioPagoNotaCreditoCalipso.getSucursalComprobante()));
						tagFactura.setNumero(obtenerNumeroComprobanteFormateado(medioPagoNotaCreditoCalipso.getNroComprobante()));

					} else if (medioPago instanceof ShvCobMedioPagoCTA) {
						ShvCobMedioPagoCTA medioPagoCTA = (ShvCobMedioPagoCTA) medioPago;
						
						tagFactura.setComprobante(medioPagoCTA.getTipoComprobante().getValor());
						tagFactura.setClase(obtenerClaseComprobanteFormateado(medioPagoCTA.getClaseComprobante()));
						tagFactura.setSucursal(obtenerSucursalComprobanteFormateado(medioPagoCTA.getSucursalComprobante()));
						tagFactura.setNumero(obtenerNumeroComprobanteFormateado(medioPagoCTA.getNroComprobante()));
	
					} else if (medioPago instanceof ShvCobMedioPagoDesistimiento) {
						tagFactura.setReferencia(REF_REINTEGRO_DESISTIMIENTO);

					} else if (medioPago instanceof ShvCobMedioPagoPlanDePago) {
						tagFactura.setReferencia(REF_REINTEGRO_PLAN_PAGO);
					
					} else if (medioPago instanceof ShvCobMedioPagoCompensacionIntercompany) {
						tagFactura.setReferencia(REF_REINTEGRO_COMP_INTERCOMPANY);
					
					} else if (medioPago instanceof ShvCobMedioPagoCompensacionIIBB) {
						tagFactura.setReferencia(REF_REINTEGRO_COMP_IIBB);
					
					} else if (medioPago instanceof ShvCobMedioPagoCompensacionCesionCredito) {
						tagFactura.setReferencia(REF_REINTEGRO_COMP_CESION_CREDITOS);
					
					} else if (medioPago instanceof ShvCobMedioPagoCompensacionOtras) {
						tagFactura.setReferencia(REF_REINTEGRO_COMP_OTRAS);
					
					} else {
						tagFactura.setReferencia(obtenerAbreviaturaMedioPagoParaReintegros(medioPago) + medioPago.getReferencia());
					}
					
					// Campos vacios
					tagFactura.setOrigen(medioPago.getSistemaOrigen().getDescripcionCorta());
					tagFactura.setVencimiento(ESPACIO);

					tagFactura.setImporteVencimiento(ESPACIO);
					tagFactura.setSaldoAnterior(ESPACIO);
					tagFactura.setPago(ESPACIO);
					tagFactura.setImporteMonedaOrigen(ESPACIO);
					tagFactura.setTipoCambio(ESPACIO);
					tagFactura.setAcuerdo(ESPACIO);
					tagFactura.setOperacion(ESPACIO);
					tagFactura.setIntereses(ESPACIO);
					tagFactura.setInteresesBonificados(ESPACIO);
					tagFactura.setConsecuenciaCobro(ESPACIO);
					tagFactura.setLlamadaPiePagina(ESPACIO);

					//
					// Recorro la listas tagFacturas actual a fin de verificar si el medio de pago ya existe, de manera de poder agrupar en caso afirmativo
					//

					Traza.advertencia(getClass(), "~   Recorro la listas tagFacturas a fin de verificar si el medio de pago ya existe de manera de poder agrupar en caso afirmativo"); 
					
					boolean documentoNoExisteEnLista = true; 
					for (Factura tagFacturaEnLista : tagFacturas.getFactura()) {
						
						if (compararMedioPagoEnTagsFactura(tagFacturaEnLista, tagFactura)) {
							
							BigDecimal importeDocumentoEnLista = Utilidad.stringToBigDecimal(tagFacturaEnLista.getImporte()); 
							BigDecimal importeAgrupado = importeDocumentoEnLista.add(medioPago.getImporte());
							
							tagFacturaEnLista.setImporte(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(importeAgrupado, 2)));
							documentoNoExisteEnLista = false;
						}
					}
					
					if (documentoNoExisteEnLista) {
						tagFacturas.getFactura().add(tagFactura);
					}
				}
			}
			
			// Datos generales
			tagFacturas.setImporteTotal(Utilidad.formatCurrencySacarPesos(Utilidad.formatCurrency(importeTotal, 2)));
			tagFacturas.setCantidadFacturas(tagFacturas.getFactura().size());
		}
		
		Detalle tagDetalle = new Detalle();
		tagDetalle.setFacturas(tagFacturas);
		tagDetalle.setMediosDePago(tagMediosDePago);

		Utilidad.tracearTiempo(getClass(), "Finalizo el metodo 'mapearDetalleTransaccionReintegro' con una duracion de: ", fechaHoraInicioNanoTime);
		return tagDetalle;		
	}
	
	/**
	 * Retorna la abreviatura correspondiente al medio de pago dado, siempre y cuando el mismo posea una.
	 * El formato a retornar será "CHQ: "
	 *
	 * @param medioPago
	 * @return
	 */
	private String obtenerAbreviaturaMedioPagoParaReintegros(ShvCobMedioPago medioPago) {
		
		StringBuffer abreviatura = new StringBuffer();
		
		if (!Validaciones.isEmptyString(TipoMedioPagoEnum.getEnumByIdTipoMedioPago(medioPago.getTipoMedioPago().getIdTipoMedioPago()).getAbreviaturaTipoMedioPago())) {
			abreviatura.append(TipoMedioPagoEnum.getEnumByIdTipoMedioPago(medioPago.getTipoMedioPago().getIdTipoMedioPago()).getAbreviaturaTipoMedioPago());
			abreviatura.append(Constantes.DOS_PUNTOS);
			abreviatura.append(Constantes.WHITESPACE);
		}
		
		return abreviatura.toString();
	}
	
	/**
	 * 
	 * @param tipoClaseComprobante
	 * @return
	 */
	private String obtenerClaseComprobanteFormateado(TipoClaseComprobanteEnum tipoClaseComprobante) {
		
		String strClaseComprobante = ESPACIO; 
		
		if (!Validaciones.isObjectNull(tipoClaseComprobante)) {
			// Si el comprobante es "D" o "S", el mismo no se envía ya que para el usuario es un blanco.
			if (!TipoClaseComprobanteEnum.D.equals(tipoClaseComprobante) && !TipoClaseComprobanteEnum.S.equals(tipoClaseComprobante)) {
				strClaseComprobante = tipoClaseComprobante.name();
			}		
		}
		
		return strClaseComprobante;
	}
	
	/**
	 * 
	 * @param sucursalComprobante
	 * @return
	 */
	private String obtenerSucursalComprobanteFormateado(String sucursalComprobante) {

		String strSucursalComprobante = ESPACIO;
		
		if (!Validaciones.isObjectNull(sucursalComprobante)) {
			strSucursalComprobante = Utilidad.rellenarCerosIzquierda(sucursalComprobante, 4);
		}
		
		return strSucursalComprobante;
	}
	
	/**
	 * 
	 * @param numeroComprobante
	 * @return
	 */
	private String obtenerNumeroComprobanteFormateado(String numeroComprobante) {
		
		String strNumeroComprobante = ESPACIO;
		
		if (!Validaciones.isObjectNull(numeroComprobante)) {
			strNumeroComprobante = Utilidad.rellenarCerosIzquierda(numeroComprobante, 8); 
		}
		
		return strNumeroComprobante;
	}

	/**
	 * 
	 * @param idClienteLegado
	 * @param rasonSocialClienteLegado
	 * @param cuit
	 * @param analista
	 * @param fechaDocumento
	 * @param idOperacion
	 * @param tipoOperacion
	 * @param tipoDocumento
	 * @param esTransaccionConDUC
	 * @param esTransaccionConAplicacionManual
	 * @return
	 * @throws NegocioExcepcion
	 */
	private Cabecera mapearCabecera(
				Long idClienteLegado,
				String rasonSocialClienteLegado,
				String cuit,
				String analista,
				Date fechaDocumento,
				Long idOperacion,
				TipoOperacionEnum tipoOperacion,
				TipoDocumentoGeneradoEnum tipoDocumento,
				boolean esTransaccionConDUC,
				boolean esTransaccionConEnvioAGanancias,
				boolean esTransaccionConAplicacionManual,
				boolean esTransaccionConConjuntoDeDebitos,
				Long idOperacionMasiva,
				MonedaEnum monedaOperacion) throws NegocioExcepcion {
		
		double fechaHoraInicioNanoTime = System.nanoTime();
		Traza.advertencia(getClass(), "Comienzo del metodo 'mapearCabecera'");
		
		String strOperacionFormateado = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);
		
		Cabecera cabecera = new Cabecera();
		cabecera.setCliente(Utilidad.rellenarCerosIzquierda(String.valueOf(idClienteLegado), 11));
		cabecera.setRazonSocial(rasonSocialClienteLegado != null ? rasonSocialClienteLegado : ESPACIO);
		cabecera.setCuit(cuit != null ? cuit : ESPACIO);

		cabecera.setConcepto(
				obtenerLeyendaConcepto(
						obtenerConcepto(idOperacion, tipoOperacion, tipoDocumento, 
								esTransaccionConDUC,
								esTransaccionConEnvioAGanancias,
								esTransaccionConAplicacionManual, 
								esTransaccionConConjuntoDeDebitos), idOperacion));
		
		cabecera.setComprobante(obtenerNumeroDeComprobante(tipoOperacion, tipoDocumento));
		
		cabecera.setDomicilio(ESPACIO);

		cabecera.setFecha(Utilidad.formatDatePicker(fechaDocumento));
		cabecera.setHora(Utilidad.formatDateHHMMSSTH2(fechaDocumento));
		cabecera.setIdOperacion(strOperacionFormateado);
		
		if (!Validaciones.isObjectNull(idOperacionMasiva)) {
			cabecera.setIdOperacionMasiva(idOperacionMasiva.toString());
		} else {
			cabecera.setIdOperacionMasiva(ESPACIO);
		}
		
		cabecera.setMonedaOperacion(monedaOperacion.getSigno2());
		cabecera.setResponsableIVA(ESPACIO);
		cabecera.setTipo(tipoDocumento.getTipoDocumentoGeneradoScard());
		cabecera.setAnalista(analista);

		Utilidad.tracearTiempo(getClass(), "Finalizo el metodo 'mapearCabecera' con una duracion de: ", fechaHoraInicioNanoTime);
		return cabecera;
	}
	
	/**
	 * 
	 * @param tipoOperacion
	 * @param tipoDocumento
	 * @return
	 * @throws NegocioExcepcion
	 */
	private String obtenerNumeroDeComprobante(
					TipoOperacionEnum tipoOperacion,
					TipoDocumentoGeneradoEnum tipoDocumento) throws NegocioExcepcion {
		
		String numeroDeComprobante = null;
		
		// Armo el numero de comprobante del documento
		try {
			String puntoDeVenta = parametroDao.getValorTexto("recibo.scard.puntoDeVenta");
			Long secuenciaDeCoprobante = null;
				
			if (TipoOperacionEnum.COBRO.equals(tipoOperacion)){
	
				if (TipoDocumentoGeneradoEnum.RECIBO.equals(tipoDocumento) ){
					secuenciaDeCoprobante = obtenerNumeroDeComprobanteRecibo();
					numeroDeComprobante = LETRA_INICIAL_RECIBO + puntoDeVenta;
	
				} else {
					secuenciaDeCoprobante = obtenerNumeroDeComprobanteNotaDeReembolso();
					numeroDeComprobante = LETRA_INICIAL_NOTA_REEMBOLSO + puntoDeVenta;
				}
	
			} else if (TipoOperacionEnum.DESCOBRO.equals(tipoOperacion)) {
	
				if (TipoDocumentoGeneradoEnum.RECIBO.equals(tipoDocumento) ) {
					secuenciaDeCoprobante = obtenerNumeroDeComprobanteRecibo();
					numeroDeComprobante = LETRA_INICIAL_RECIBO + puntoDeVenta;
	
				} else {
					secuenciaDeCoprobante =  obtenerNumeroDeComprobanteNotaDeReembolso();
					numeroDeComprobante = LETRA_INICIAL_NOTA_REEMBOLSO + puntoDeVenta;
				}
			}
			numeroDeComprobante += "-" + Utilidad.rellenarCerosIzquierda(secuenciaDeCoprobante.toString(), 8);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return numeroDeComprobante;
	}
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	private Long obtenerNumeroDeComprobanteRecibo() throws NegocioExcepcion {
		try {
			return genericoDao.proximoValorSecuencia("SEQ_SHV_SCARD_RECIBO");
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);		
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	private Long obtenerNumeroDeComprobanteNotaDeReembolso() throws NegocioExcepcion {
		try {
			return genericoDao.proximoValorSecuencia("SEQ_SHV_SCARD_NOTA_REEMBOLSO");
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);		
		}
	}	

	/**
	 * 
	 * @param idOperacion
	 * @param tipoOperacion
	 * @param tipoDocumento
	 * @return
	 */
	private ConceptoDocumentoScardEnum obtenerConcepto(
					Long idOperacion,
					TipoOperacionEnum tipoOperacion,
					TipoDocumentoGeneradoEnum tipoDocumento,
					boolean esTransaccionConDUC,
					boolean esTransaccionConEnvioAGanancias,
					boolean esTransaccionConAplicacionManual,
					boolean esTransaccionConConjuntoDeDebitos) {
		
		double fechaHoraInicioNanoTime = System.nanoTime();
		Traza.advertencia(getClass(), "Comienzo del metodo 'obtenerConcepto'");

		ConceptoDocumentoScardEnum concepto = null;

		if (TipoOperacionEnum.COBRO.equals(tipoOperacion)) {

			if (TipoDocumentoGeneradoEnum.RECIBO.equals(tipoDocumento) ){
				if (esTransaccionConDUC) {
					concepto = ConceptoDocumentoScardEnum.COBRO_DE_DUC;
				} else {
					concepto = ConceptoDocumentoScardEnum.COBRO_DE_FACTURA;
				}
				
			} else {
				if (esTransaccionConAplicacionManual) {
					concepto = ConceptoDocumentoScardEnum.SALIDA_APLICACION_MANUAL;
				} else if (esTransaccionConConjuntoDeDebitos) {
					concepto = ConceptoDocumentoScardEnum.SALIDA_APLICACION_MANUAL_CONJUNTO_DE_DEBITOS;
				} else if (esTransaccionConEnvioAGanancias) {
					concepto = ConceptoDocumentoScardEnum.REINTEGRO;
				} else {
					concepto = ConceptoDocumentoScardEnum.REINTEGRO;
				}
			}

		} else if (TipoOperacionEnum.DESCOBRO.equals(tipoOperacion)){

			if (TipoDocumentoGeneradoEnum.NOTA_REEMBOLSO.equals(tipoDocumento)){
				if (esTransaccionConDUC) {
					concepto = ConceptoDocumentoScardEnum.REVERSION_DE_COBRO_DE_DUC;
				} else {
					concepto = ConceptoDocumentoScardEnum.REVERSION_DE_COBRO_DE_FACTURA;
				}

			} else {
				if (esTransaccionConAplicacionManual) {
					concepto = ConceptoDocumentoScardEnum.REVERSION_DE_SALIDA_APLICACION_MANUAL;
				} else if (esTransaccionConConjuntoDeDebitos) {
					concepto = ConceptoDocumentoScardEnum.REVERSION_DE_SALIDA_APLICACION_MANUAL_CONJUNTO_DE_DEBITOS;
				} else if (esTransaccionConEnvioAGanancias) {
					concepto = ConceptoDocumentoScardEnum.REVERSION_DE_REINTEGRO;
				} else {
					concepto = ConceptoDocumentoScardEnum.REVERSION_DE_REINTEGRO;
				}
			}
		}
		
		Utilidad.tracearTiempo(getClass(), "Finalizo el metodo 'obtenerConcepto' con una duracion de: ", fechaHoraInicioNanoTime);
		return concepto;
	}
	
	/**
	 * 
	 * @param concepto
	 * @param idOperacion
	 * @return
	 */
	private String obtenerLeyendaConcepto(ConceptoDocumentoScardEnum concepto, Long idOperacion) { 
	
		String strOperacionFormateado = Utilidad.rellenarCerosIzquierda(idOperacion.toString(), 7);
		return Utilidad.reemplazarMensajes(concepto.descripcion(), strOperacionFormateado);
	}
	
	/**
	 * 
	 * @param transaccion
	 * @param tipoOperacion
	 * @return
	 */
	private TipoDocumentoGeneradoEnum obtenerTipoDocumento(ShvCobTransaccion transaccion, TipoOperacionEnum tipoOperacion) {
		
		TipoDocumentoGeneradoEnum tipoDocumento = null;

		if (!Validaciones.isObjectNull(transaccion.getFacturaTransaccion())) {
			if (TipoComprobanteEnum.CDD.name().equals(transaccion.getFacturaTransaccion().getTipoComprobante().getIdTipoComprobante())) {
				// Los comprobantes de tipo "Conjunto de Débitos" se tratan en Notas de Reembolso en el Cobro
				tipoDocumento = TipoOperacionEnum.COBRO.equals(tipoOperacion) ? 
						TipoDocumentoGeneradoEnum.NOTA_REEMBOLSO : TipoDocumentoGeneradoEnum.NOTA_REEMBOLSO;
			} else {
				tipoDocumento = TipoOperacionEnum.COBRO.equals(tipoOperacion) ? 
						TipoDocumentoGeneradoEnum.RECIBO : TipoDocumentoGeneradoEnum.NOTA_REEMBOLSO;
			}
		} else if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {
			tipoDocumento = TipoOperacionEnum.COBRO.equals(tipoOperacion) ? 
					TipoDocumentoGeneradoEnum.NOTA_REEMBOLSO : TipoDocumentoGeneradoEnum.RECIBO;
		}	
		
		return tipoDocumento;
	}
	
	public void inicializarDocumentoOperacionCobro(ShvCobOperacion operacion) throws NegocioExcepcion {
		inicializarDocumento(operacion, TipoOperacionEnum.COBRO);
	}

	public void inicializarDocumentoOperacionDescobro(ShvCobOperacion operacion) throws NegocioExcepcion {
		inicializarDocumento(operacion, TipoOperacionEnum.DESCOBRO);
	}
	
	
	/**
	 * Cuando un cobro o un descobro llegan a un estado final correcto, se debe invocar a este metodo para 
	 * dejar pendiente los registros que darán lugar luego a la generación de los documentos contra SCARD.
	 * Este metodo genera un registro en la tabla SHV_COB_DOCUMENTO para combinación posible de
	 * 
	 * 		- Id de Operacion
	 * 		- Cliente
	 * 		- Tipo de documento (Recibo o Nota de Reembolso)
	 * 
	 *  en estado 'PENDIENTE'
	 *  
	 *  Las transacciones de saldo a cobrar o saldo a pagar no se tienen en cuenta.
	 *  Con las modificaciones relacionadas al req 72562, Sap 4Up, ahora los cobros pueden cobrarse parcialmente, 
	 *  por lo tanto las transacciones que debemos tomar son aquellas que se encuentren en estado CONFIRMADA
	 * 
	 * @param operacion
	 * @param tipoOperacion
	 * @throws NegocioExcepcion
	 */
	private void inicializarDocumento(ShvCobOperacion operacion, TipoOperacionEnum tipoOperacion) throws NegocioExcepcion {
		
		double fechaHoraInicioNanoTime = System.nanoTime();

		// Generar un registro en la tabla de documentos por cada juego de 
		// Operacion - Cliente - Tipo de documento
		
		Traza.advertencia(getClass(), " ");
		Traza.advertencia(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Traza.advertencia(getClass(), "~ Comienzo del metodo 'inicializarDocumento'");
		Traza.advertencia(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Traza.advertencia(getClass(), "Aqui vamos a generar un registro en la tabla de documentos por cada juego");
		Traza.advertencia(getClass(), "'Operacion|Cliente|Tipo de Documento (Recibo o Nota de Reembolso)' que exista.");
		Traza.advertencia(getClass(), " ");
		
		Collection<ShvCobDocumento> listaDocumentos = new ArrayList<ShvCobDocumento>();
		
		for (ShvCobTransaccion transaccion : operacion.getTransacciones()) {
			
			if (!transaccion.haySaldoAPagarOSaldoACobrar() 
					&& (EstadoTransaccionEnum.CONFIRMADA.equals(transaccion.getEstadoProcesamiento())
							|| EstadoTransaccionEnum.DESCOBRO.equals(transaccion.getEstadoProcesamiento()))) {
				
				if (!EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO.equals(transaccion.getEstadoProcesamiento())
						&& !EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO_SIM.equals(transaccion.getEstadoProcesamiento())) {
					
					Long idClienteLegado = transaccionSoporteServicio.obtenerClienteLegadoTransaccion(transaccion);
					TipoDocumentoGeneradoEnum tipoDocumento = obtenerTipoDocumento(transaccion, tipoOperacion);
					
					ShvCobDocumento documento = new ShvCobDocumento();
					documento.setOperacion(operacion);
					documento.setIdClienteLegado(idClienteLegado);
					documento.setTipoDocumento(tipoDocumento);
					documento.setTipoOperacion(tipoOperacion);

					documento.setConcepto(
							obtenerConcepto(operacion.getIdOperacion(), 
									tipoOperacion, tipoDocumento, 
									esTransaccionConDUC(transaccion), 
									esTransaccionConEnvioAGanancias(transaccion),
									esTransaccionConAplicacionManual(transaccion), 
									esTransaccionConConjuntoDeDebitos(transaccion)));							

					documento.setEstado(SCARD_PENDIENTE);
					
					Traza.advertencia(getClass(), "Procesamiento de Transaccion: " + transaccion.getNumeroTransaccion() 
												+ ". Juego generado. Operacion: '" + operacion.getIdOperacion() + "'"
												+ " | Id Cliente Legado: " + idClienteLegado + "'" 
												+ " | Tipo Documento: " + tipoDocumento + "'"
												+ " | Tipo Operacion: " + tipoOperacion + "'");
					
					if (!listaDocumentos.contains(documento)) {
						listaDocumentos.add(documento);
						
						Traza.advertencia(getClass(), "Juego agregado a la lista de 'documentos'");
					}
				}
			}
		}

		// Una vez armada la lista de clientes con cada tipo de documento que debo generar, 
		// los almaceno en la base de datos
		for (ShvCobDocumento documento : listaDocumentos) {
		
			try {
				documento.setFechaCreacion(new Date());
				documentoDao.insertarActualizar(documento);

			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		}
		
		Traza.advertencia(getClass(), " ");
		Traza.advertencia(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Utilidad.tracearTiempo(getClass(), "~ Fin del metodo 'inicializarDocumento' con una duracion de: ", fechaHoraInicioNanoTime);
		Traza.advertencia(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Traza.advertencia(getClass(), " ");
	}

	/**
	 * Este método es el encargado de levantar los registros en estado 'PENDIENTE' de la tabla 'SHV_COB_DOCUMENTO' 
	 * 
	 * 
	 * @param fechaHasta con formato DD/MM/YYYY
	 * @throws NegocioExcepcion
	 */
	public void generarDocumentos(String fechaHasta) throws NegocioExcepcion {

		double fechaHoraInicioNanoTime = System.nanoTime();

		Traza.advertencia(getClass(), " ");
		Traza.advertencia(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Traza.advertencia(getClass(), "~ Comienzo del metodo 'generarDocumentos'");
		Traza.advertencia(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Traza.advertencia(getClass(), " ");
		Traza.advertencia(getClass(), "Fecha ingresada: '" + fechaHasta + "'");
		
		// Levantar los registros pendientes de la base de datos
	  		// - Id de  Operacion / Cliente / Tipo de documento (Recibo o Nota de Reembolso)
		
		// Por cada registro pendiente, debo ir a buscar sus datos en el cobro

		try {
			Documentos contenedorDocumentosScard = new Documentos();

			Filtro scardFiltro = new Filtro();
			scardFiltro.setFechaHasta(fechaHasta);
			Date fechaParametro = Utilidad.parseDatePickerString(fechaHasta);
			
			//Variable para ir consultando la operacion del cobro
			ShvCobOperacion operacion	= null;
			
			Date fechaDocumento = null;
			
			Long idOperacionMasiva 		= null;
			MonedaEnum monedaOperacion  = null; 
			
			List<ShvCobDocumentoSimplificado> documentosScard = (List<ShvCobDocumentoSimplificado>) documentoDao.listarPendientes(SCARD_PENDIENTE, scardFiltro);
			Traza.advertencia(getClass(), "Se encontraron '" + documentosScard.size() + "' registros en estado " + SCARD_PENDIENTE);
			Traza.auditoria(getClass(), "Se procesa el registro..... --> " + ControlMemoriaCPU.getInformacionMemoria());
			
			for (ShvCobDocumentoSimplificado registroDocumento : documentosScard) {
				Traza.advertencia(getClass(), "~");
				Traza.advertencia(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				Traza.advertencia(getClass(), "~ Se procesara el registro Scard con idDocumento: '" + registroDocumento.getIdDocumento() + "'"
						+ ". Datos: Operacion: '" + registroDocumento.getIdOperacion() + "'"
						+ " | Id Cliente Legado: '" + registroDocumento.getIdClienteLegado() + "'"
						+ " | Tipo Documento: '" + registroDocumento.getTipoDocumento() + "'" 
						+ " | Tipo Operacion: '" + registroDocumento.getTipoOperacion() + "'"
						+ " | Concepto: '" + registroDocumento.getConcepto() + "'");
				Traza.advertencia(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				
				ResultadoBusquedaDocumentoScard resultadoBusqueda=null;
				
				Collection<ShvCobTransaccion> listaTransaccionesParaDocumentoScard = new ArrayList<ShvCobTransaccion>();
				Collection<ShvCobTransaccion> listaTransaccionesPadresParaDocumentoScard = new ArrayList<ShvCobTransaccion>();
				
				//Realizo un corte de control con la Operacion para buscarla una sola vez
				if((Validaciones.isObjectNull(operacion)) || (!Validaciones.isObjectNull(operacion) && !registroDocumento.getIdOperacion().equals(operacion.getIdOperacion()))){
					double busquedaOperacionNanoTime = System.nanoTime();
					operacion = cobroDao.buscarOperacionPorIdOperacion(registroDocumento.getIdOperacion());
					Utilidad.tracearTiempo(getClass(), "Duracion en buscar la operacion con id: " + registroDocumento.getIdOperacion(), busquedaOperacionNanoTime);
				}
			
				if (TipoOperacionEnum.COBRO.equals(registroDocumento.getTipoOperacion())) {
					resultadoBusqueda = cobroDao.obtenerDocumentoScardCobroPorIdDocumento(registroDocumento.getIdDocumento());
					
				} else if(TipoOperacionEnum.DESCOBRO.equals(registroDocumento.getTipoOperacion())) {
					resultadoBusqueda = cobroDao.obtenerDocumentoScardDescobroPorIdDocumento(registroDocumento.getIdDocumento());		
				}

				fechaDocumento = resultadoBusqueda.getFechaUltimaModificacion();
				
				idOperacionMasiva = resultadoBusqueda.getIdOperacionMasiva();
				if (!Validaciones.isObjectNull(resultadoBusqueda.getMonedaOperacion())){
					monedaOperacion = resultadoBusqueda.getMonedaOperacion();
				} else {
					monedaOperacion = MonedaEnum.PES;
				}
				// Recorre las transacciones del cobro o descobro
				for (ShvCobTransaccion transaccion : operacion.getTransacciones()) {
					if(!transaccion.haySaldoAPagarOSaldoACobrar()
							&& (EstadoTransaccionEnum.CONFIRMADA.equals(transaccion.getEstadoProcesamiento())
								|| EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO.equals(transaccion.getEstadoProcesamiento())
								|| EstadoTransaccionEnum.DESCOBRO.equals(transaccion.getEstadoProcesamiento()))) {
						
						boolean conceptosIguales = false;

						//
						// En el siguiente apartado se busca el concepto de la transacción, para luego junto con el cliente,
						// el tipo de documento y el tipo de operacion realizar un macheo contra el documento Scard que se está procesado.
						// Puede darse el caso de que el concepto del documento Scard que se está procesando sea nulo,
						// lo cuál se puede deber a registros viejos. En ese caso, no voy a usar el concepto para poder machear
						// transaccion con documento Scard en curso.
						//
						if (!Validaciones.isObjectNull(registroDocumento.getConcepto())) {
							ConceptoDocumentoScardEnum conceptoTransaccion = obtenerConcepto(
																		operacion.getIdOperacion(), 
																		registroDocumento.getTipoOperacion(), 
																		obtenerTipoDocumento(transaccion, registroDocumento.getTipoOperacion()), 
																		esTransaccionConDUC(transaccion), 
																		esTransaccionConEnvioAGanancias(transaccion),
																		esTransaccionConAplicacionManual(transaccion), 
																		esTransaccionConConjuntoDeDebitos(transaccion));
							
							conceptosIguales = conceptoTransaccion.equals(registroDocumento.getConcepto());
							
						} else {
							Traza.advertencia(getClass(), " ");
							Traza.advertencia(getClass(), "Warning!: Ojo que el concepto en el documento es 'nulo', lo cuál puede deberse a que el mismo ha sido inicializado");
							Traza.advertencia(getClass(), "con una versión del proceso de Scard anterior al Req 72562 Shiva 4Up - Proceso de cobranza convergente.");
							Traza.advertencia(getClass(), "Se recomienda regenerar o en su defecto actualizar el registro y volver a ejecutar el proceso batch de Scard.");
							Traza.advertencia(getClass(), " ");

							// Quedan en 'true' por defecto al ser nulo el concepto en el documento a procesar, de otro modo
							// es posible que no se van a encontrar transacciones para procesar, ya que el concepto de la transaccion
							// se calcula en tiempo de ejecución
							conceptosIguales = true;
						}
						
						Traza.auditoria(getClass(), "Se procesara la transaccion: Numero: '" + transaccion.getNumeroTransaccion() + "' con Id: '" + transaccion.getIdTransaccion() + "'");
						
						if (TipoOperacionEnum.COBRO.equals(registroDocumento.getTipoOperacion())) {
							if (TipoDocumentoGeneradoEnum.RECIBO.equals(registroDocumento.getTipoDocumento())) {

								Traza.advertencia(getClass(), "   Grupo COBRO RECIBO Evaluo la transaccion '" + transaccion.getNumeroTransaccion() + "' con IdClienteLegado: '" + transaccionSoporteServicio.obtenerClienteLegadoTransaccionDebito(transaccion) + "'");
								if (registroDocumento.getIdClienteLegado().equals(
										transaccionSoporteServicio.obtenerClienteLegadoTransaccionDebito(transaccion)) && conceptosIguales) {

									boolean laTransaccionEsPadre = false;
									// Antes de agregar a la lista de transacciones de Scard, verifico si la transaccion no es padre de alguna otra
									// existente en el cobro. De ser así, no puedo agregarla.. mas adelante me quedaré con el hijo directamente
									// Si es padre, la agrego en una segunda lista, para tenerla a mano para el manejo de intereses
									for (ShvCobTransaccion posibleTransaccionHija : operacion.getTransacciones()) {
										if (!Validaciones.isObjectNull(posibleTransaccionHija.getNumeroTransaccionPadre())) {
											
											// Verifico si la transaccion actual es padre de..
											if (transaccion.getNumeroTransaccion().equals(posibleTransaccionHija.getNumeroTransaccionPadre())) {
												listaTransaccionesPadresParaDocumentoScard.add(transaccion);
												laTransaccionEsPadre = true;
												break;
											}
										}
									}
									
									if (!laTransaccionEsPadre) {
										listaTransaccionesParaDocumentoScard.add(transaccion);
										Traza.advertencia(getClass(), "   Grupo COBRO RECIBO Agrego la transaccion '" + transaccion.getNumeroTransaccion() + "' a la lista para este cliente y tipo de operación");
									}
								}
	
							} else if (TipoDocumentoGeneradoEnum.NOTA_REEMBOLSO.equals(registroDocumento.getTipoDocumento())) {
								
								Traza.advertencia(getClass(), "   Grupo COBRO NOTA_REEMBOLSO Evaluo la transaccion '" + transaccion.getNumeroTransaccion() + "' con IdClienteLegado: '" + transaccionSoporteServicio.obtenerClienteLegadoTransaccionReintegro(transaccion) + "'");
								if (registroDocumento.getIdClienteLegado().equals(
										transaccionSoporteServicio.obtenerClienteLegadoTransaccionReintegro(transaccion)) && conceptosIguales) {

									listaTransaccionesParaDocumentoScard.add(transaccion);
									Traza.advertencia(getClass(), "   Grupo COBRO NOTA_REEMBOLSO Agrego la transaccion '" + transaccion.getNumeroTransaccion() + "' a la lista para este cliente y tipo de operación");
								} 
							}
							
						} else if (TipoOperacionEnum.DESCOBRO.equals(registroDocumento.getTipoOperacion())) {
							if (TipoDocumentoGeneradoEnum.RECIBO.equals(registroDocumento.getTipoDocumento())) {
								
								Traza.advertencia(getClass(), "   Grupo DESCOBRO RECIBO Evaluo la transaccion '" + transaccion.getNumeroTransaccion() + "' con IdClienteLegado: '" + transaccionSoporteServicio.obtenerClienteLegadoTransaccionReintegro(transaccion) + "'");
								if (registroDocumento.getIdClienteLegado().equals(
										transaccionSoporteServicio.obtenerClienteLegadoTransaccionReintegro(transaccion)) && conceptosIguales) {

									listaTransaccionesParaDocumentoScard.add(transaccion);
									Traza.advertencia(getClass(), "   Grupo DESCOBRO RECIBO Agrego la transaccion '" + transaccion.getNumeroTransaccion() + "' a la lista para este cliente y tipo de operación");
								}
	
							} else if (TipoDocumentoGeneradoEnum.NOTA_REEMBOLSO.equals(registroDocumento.getTipoDocumento())) {
								
								Traza.advertencia(getClass(), "   Grupo DESCOBRO NOTA_REEMBOLSO Evaluo la transaccion '" + transaccion.getNumeroTransaccion() + "' con IdClienteLegado: '" + transaccionSoporteServicio.obtenerClienteLegadoTransaccionDebito(transaccion) + "'");
								if (registroDocumento.getIdClienteLegado().equals(
										transaccionSoporteServicio.obtenerClienteLegadoTransaccionDebito(transaccion)) && conceptosIguales) {

									boolean laTransaccionEsPadre = false;
									// Antes de agregar a la lista de transacciones de Scard, verifico si la transaccion no es padre de alguna otra
									// existente en el cobro. De ser así, no puedo agregarla.. mas adelante me quedaré con el hijo directamente
									// Si es padre, la agrego en una segunda lista, para tenerla a mano para el manejo de intereses
									for (ShvCobTransaccion posibleTransaccionHija : operacion.getTransacciones()) {
										if (!Validaciones.isObjectNull(posibleTransaccionHija.getNumeroTransaccionPadre())) {
											
											// Verifico si la transaccion actual es padre de..
											if (transaccion.getNumeroTransaccion().equals(posibleTransaccionHija.getNumeroTransaccionPadre())) {
												listaTransaccionesPadresParaDocumentoScard.add(transaccion);
												laTransaccionEsPadre = true;
												break;
											}
										}
									}
									
									if (!laTransaccionEsPadre) {
										listaTransaccionesParaDocumentoScard.add(transaccion);
										Traza.advertencia(getClass(), "   Grupo DESCOBRO NOTA_REEMBOLSO Agrego la transaccion '" + transaccion.getNumeroTransaccion() + "' a la lista para este cliente y tipo de operación");
									}
								}
							}
						}
						Traza.auditoria(getClass(), "Se termino de procesar la transaccion: "+transaccion.getIdTransaccion() + "---> " + ControlMemoriaCPU.getInformacionMemoria());
					}
				}
				//FIN - recorrer las transacciones
				
				Traza.advertencia(getClass(), " ");
				Traza.advertencia(getClass(), "Comienzo con los mapeos a XML...");
				Traza.advertencia(getClass(), " ");
				
				NumerosALetras numerosALetras = new NumerosALetras();

				Detalle detalle = null;
				
				double mapeoDetalleNanoTime = System.nanoTime();
				Traza.auditoria(getClass(), "Se comienza a mapear las transacciones");
				if (TipoOperacionEnum.COBRO.equals(registroDocumento.getTipoOperacion())) {
					if (TipoDocumentoGeneradoEnum.RECIBO.equals(registroDocumento.getTipoDocumento())) {
						detalle = mapearDetalleTransaccionDebito(listaTransaccionesParaDocumentoScard, listaTransaccionesPadresParaDocumentoScard, false);

					} else if (TipoDocumentoGeneradoEnum.NOTA_REEMBOLSO.equals(registroDocumento.getTipoDocumento())) {
						detalle = mapearDetalleTransaccionReintegro(listaTransaccionesParaDocumentoScard, registroDocumento.getTipoOperacion());
					}
					
				} else if (TipoOperacionEnum.DESCOBRO.equals(registroDocumento.getTipoOperacion())) {
					if (TipoDocumentoGeneradoEnum.RECIBO.equals(registroDocumento.getTipoDocumento())) {
						detalle = mapearDetalleTransaccionReintegro(listaTransaccionesParaDocumentoScard, registroDocumento.getTipoOperacion());

					} else if (TipoDocumentoGeneradoEnum.NOTA_REEMBOLSO.equals(registroDocumento.getTipoDocumento())) {
						detalle = mapearDetalleTransaccionDebito(listaTransaccionesParaDocumentoScard, listaTransaccionesPadresParaDocumentoScard, true);
					}
				}
				Utilidad.tracearTiempo(getClass(), "Duracion en mapear el detalle del registro "+registroDocumento.getIdDocumento()+": ", mapeoDetalleNanoTime);
				
				Cabecera cabecera;

				ShvCobTransaccion nextTransaccion = listaTransaccionesParaDocumentoScard.iterator().next(); 
				 									// operacion.getTransacciones().iterator().next();

				if (!Validaciones.isObjectNull(resultadoBusqueda)){
					cabecera = mapearCabecera(
							resultadoBusqueda.getIdClienteLegado(),
							resultadoBusqueda.getRazonSocial(),
							resultadoBusqueda.getCuit(),
							resultadoBusqueda.getIdAnalista(),
							fechaDocumento,
							registroDocumento.getIdOperacion(),
							registroDocumento.getTipoOperacion(),
							registroDocumento.getTipoDocumento(),
							esTransaccionConDUC(nextTransaccion),
							esTransaccionConEnvioAGanancias(nextTransaccion),
							esTransaccionConAplicacionManual(nextTransaccion),
							esTransaccionConConjuntoDeDebitos(nextTransaccion),
							idOperacionMasiva,
							monedaOperacion
							);								
				} else {
					
					// Asumo que el descobro es pre-shiva
					ShvCobFactura factura = null;
					String razonSocial = null;
					String cuit = null;
					Long idClienteLegado = null;
					
//					ShvCobTransaccion nextTransaccion = listaTransaccionesParaDocumentoScard.iterator().next();
//														// operacion.getTransacciones().iterator().next();

					for (ShvCobFactura fact : listaTransaccionesParaDocumentoScard.iterator().next().getShvCobFactura()) { 
												   // operacion.getTransacciones().iterator().next().getShvCobFactura()){
						if (!Validaciones.isObjectNull(fact)) {
							if (fact.getGeneradoPorCobro().equals(SiNoEnum.NO)) {
								factura = fact;
							}
						}
					}
					if (!Validaciones.isObjectNull(factura)) {
						idClienteLegado = factura.getIdClienteLegado();
						cuit = (factura.getCuit() != null)?factura.getCuit():ESPACIO;
						if ( factura instanceof ShvCobFacturaCalipso) {
							razonSocial = (((ShvCobFacturaCalipso)factura).getRazonSocialClienteLegado()!= null)?((ShvCobFacturaCalipso)factura).getRazonSocialClienteLegado().toUpperCase():ESPACIO;
						} else {
							razonSocial = (((ShvCobFacturaMic)factura).getRazonSocialClienteLegado() != null)?((ShvCobFacturaMic)factura).getRazonSocialClienteLegado().toUpperCase():ESPACIO;
						}
					}
					
					cabecera = mapearCabecera(
							idClienteLegado,
							razonSocial,
							cuit,
							resultadoBusqueda.getIdAnalista(),
							fechaDocumento,
							registroDocumento.getIdOperacion(),
							registroDocumento.getTipoOperacion(),
							registroDocumento.getTipoDocumento(),
							esTransaccionConDUC(nextTransaccion),
							esTransaccionConEnvioAGanancias(nextTransaccion),
							esTransaccionConAplicacionManual(nextTransaccion),
							esTransaccionConConjuntoDeDebitos(nextTransaccion),
							idOperacionMasiva,
							monedaOperacion);
				}
				
				cabecera.setTotal(detalle.getFacturas().getImporteTotal());
				cabecera.setDescripcion(numerosALetras.Convertir(Utilidad.formatCurrency(cabecera.getTotal(), false, false), true));

				PieDePagina pieDePagina = new PieDePagina();
				Notas notas = new Notas();
				notas = agregarNotaAlPieDePagina(notas, detalle);
				pieDePagina.setNotas(notas);

				Documento nuevoDocumentoScard = new Documento();
				nuevoDocumentoScard.setCabecera(cabecera);
				nuevoDocumentoScard.setDetalle(detalle);
				nuevoDocumentoScard.setPie(pieDePagina);
				
				//
				// Genero el XML propio del registro actual
				//				
				StringWriter detalleDocumentoXmlStringWriter = new StringWriter();
				double MarshalNanoTime = System.nanoTime();
				try {
					Traza.auditoria(getClass(), "Inicio del armado del XML del registro " + registroDocumento.getIdDocumento() + "..... --> " + ControlMemoriaCPU.getInformacionMemoria());
					JAXBContext jaxbContext = JAXBContext.newInstance(Documento.class);
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
					jaxbMarshaller.marshal(nuevoDocumentoScard, detalleDocumentoXmlStringWriter);
					Traza.auditoria(getClass(), "Fin del armado del XML del registro " + registroDocumento.getIdDocumento() + "..... --> " + ControlMemoriaCPU.getInformacionMemoria());
				} catch (JAXBException e) {
					throw new NegocioExcepcion(e);
				}

				registroDocumento.setNumeroRecibo(cabecera.getComprobante());
				registroDocumento.setDetalleDocumentoXml(detalleDocumentoXmlStringWriter.toString());
				Traza.auditoria(getClass(), "Tamaño del detalleDocumentoXmlStringWriter para el registro: " + registroDocumento.getIdDocumento() + " tamaño: "
				+ detalleDocumentoXmlStringWriter.toString().length());

				// 
				// Agrego el documento generado a la lista de documentos a enviar a SCARD
				// 
				contenedorDocumentosScard.getDocumento().add(nuevoDocumentoScard);
				Traza.auditoria(getClass(), "Cantidad de documentos en el contenedor de documentos de Scard: "+contenedorDocumentosScard.getDocumento().size());
				Utilidad.tracearTiempo(getClass(), "Duracion en generar el XML del registro "+registroDocumento.getIdDocumento(), MarshalNanoTime);
			}

			// Armo el archivo
			String carpetaDestino = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.scard");
			Traza.auditoria(getClass(), "Carpeta destino: "+carpetaDestino);
			String nombreArchivo = Constantes.SHIVA_APLICACION.toUpperCase() + NOMBRE_ARCHIVO + Utilidad.formatDateAAAAMMDD(fechaParametro) + EXTENSION;
			Traza.auditoria(getClass(), "Nombre del archivo: "+nombreArchivo);
			
			Traza.auditoria(getClass(), "Inicio del metodo marshal..... --> " + ControlMemoriaCPU.getInformacionMemoria());
			double marshalNanoTime = System.nanoTime();
			@SuppressWarnings("unused")
			String resultado = ControlXml.marshal(Documentos.class, contenedorDocumentosScard, carpetaDestino, nombreArchivo);
			Date fechaProcesamiento = new Date();
			Utilidad.tracearTiempo(getClass(), "Tienmpo en ejecutar metodo ControlXml.marshal", marshalNanoTime);
			Traza.auditoria(getClass(), "Fin del metodo marshal..... --> " + ControlMemoriaCPU.getInformacionMemoria());
			
			// Actualizo los registros pendientes a procesados
			for (ShvCobDocumentoSimplificado registroDocumento : documentosScard) {
				registroDocumento.setEstado(SCARD_PROCESADO);
				registroDocumento.setFechaProcesamiento(fechaProcesamiento);
				
				double insertarActualizarNanoTime = System.nanoTime();
				documentoDao.insertarActualizarDocumentoSimplificado(registroDocumento);
				Utilidad.tracearTiempo(getClass(), "Duracion en insertar en la base de datos el registro: "+registroDocumento.getIdDocumento(), insertarActualizarNanoTime);
			}

			Utilidad.tracearTiempo(getClass(), "Finalizo el metodo 'generarDocumentos' con una duracion de: ", fechaHoraInicioNanoTime);
		} catch (PersistenciaExcepcion | ShivaExcepcion | ParseException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		Traza.advertencia(getClass(), " ");
		Traza.advertencia(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Traza.advertencia(getClass(), "~ Fin del metodo 'generarDocumentos'");
		Traza.advertencia(getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Traza.advertencia(getClass(), " ");
	}
	
	/**
	 * Este metodo se encarga de determinar si un cobro posee o no DUCs, en base a la evaluacion de una transaccion.
	 * Sin inconvenientes, se podrá recibir la primera transaccion que posee un cobro, ya que cuando
	 * se tienen DUCs en un cobro, es lo unico que se puede tener, es decir, si existen DUcs,
	 * no vamos a encontrar nunca facturas, notas de debito o tratamientos de diferencia
	 * 
	 * @param transaccion
	 * @return
	 */
	private boolean esTransaccionConDUC(ShvCobTransaccion transaccion) {
		
		boolean esTransaccionConDUC = false;
		
		if (!Validaciones.isObjectNull(transaccion.getFacturaTransaccion())) {
			if (TipoComprobanteEnum.DUC.equals(transaccion.getFacturaTransaccion().getTipoComprobante().getIdTipoComprobante())) {
				esTransaccionConDUC = true;
			}
		}
		
		return esTransaccionConDUC;
	}
	
	/**
	 * Este metodo se encarga de determinar si un cobro posee o no Aplicacion Manual a nivel tratamiento de la diferencia.
	 * Es para cobros que van a quedar obsoletos, ya que luego de la implementación del 
	 * requerimiento "72562 Shiva 4Up - Proceso de cobranza convergente", estos casos ya no debieran generarse nuevamente. 
	 * 
	 * @author u587496 Guido.Settecerze
	 * @param transaccion
	 * @return
	 */
	private boolean esTransaccionConAplicacionManual(ShvCobTransaccion transaccion) {
		
		boolean esTransaccionConAplicacionManual = false;
		
		if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {
			if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia()) 
				 ||TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
				 ||TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
				 ||TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
				 ||TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
				esTransaccionConAplicacionManual = true;
			}
		}
		
		return esTransaccionConAplicacionManual;
	}
	
	/**
	 * Este metodo se encarga de determinar si un cobro posee "Envío a Ganancias" a nivel tratamiento de la diferencia.
	 *  
	 * @author u587496 Guido.Settecerze
	 * @param transaccion
	 * @return
	 */
	private boolean esTransaccionConEnvioAGanancias(ShvCobTransaccion transaccion) {
		
		boolean esTransaccionConEnvioAGanancias = false;
		
		if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {
			if (TipoTratamientoDiferenciaEnum.ENVIO_A_GANANCIAS.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
				esTransaccionConEnvioAGanancias = true;
			}
		}
		
		return esTransaccionConEnvioAGanancias;
	}

	/**
	 * Este metodo se encarga de determinar si un cobro posee o no Conjunto de Débitos.
	 * 
	 * @param transaccion
	 * @return
	 */
	private boolean esTransaccionConConjuntoDeDebitos(ShvCobTransaccion transaccion) {
	
		boolean esTransaccionConConjuntoDeDebitos = false;
		
		if (!Validaciones.isObjectNull(transaccion.getFactura())) {
			if (TipoComprobanteEnum.CDD.name().equals(transaccion.getFacturaTransaccion().getTipoComprobante().getIdTipoComprobante())) {
				esTransaccionConConjuntoDeDebitos = true;
			}
		}
		return esTransaccionConConjuntoDeDebitos;
	}
	
    /**
     * Este método retorna el tipo de cambio formateado, respetando los 4 decimales que retorna Calipso
     * para el tipo de cambio a fecha de emisión
     * 
     * Dado que en Utilidad no hay nada que pueda soportar esto (porque todo redondea a 2 decimales), se presenta
     * este método particular dentro de SCARD.
     *  
     * @param d
     * @return
     */
    private String formatCurrencyTipoCambio(BigDecimal d) {
    	
    	if (!Validaciones.isObjectNull(d)) {
    		
    		String ret = new DecimalFormat("#,##0.0000").format(d);
    		
    		// Como es muy raro que el formateo se convierta en 999.999.999,99 en forma local
    		// por eso si en otros servidores salen como 999,999,999.99  
    		if (".".equals(ret.substring((ret.length()-5),(ret.length()-4)))) {
    			//999|999|999.99
    			//999|999|999,99
        		//999.999.999,99

    			String formato = ret.replace(",", "|").replace(".", ",").replace("|", ".");
    			ret = formato;
    		}
    		
			ret = Utilidad.formatCurrencySacarPunto(ret);
    		return ret;
    	} else {
    		return Utilidad.EMPTY_STRING;
    	}
    }    

    /**
     * 
     * @param tipoOperacion
     * @param listaTransacciones
     * @return
     * @throws NegocioExcepcion
     */
    private String obtenerCodigosOperacionExterno(TipoOperacionEnum tipoOperacion, Collection<ShvCobTransaccion> listaTransacciones) throws NegocioExcepcion {

		double fechaHoraInicioNanoTime = System.nanoTime();
		Traza.advertencia(getClass(), "Comienzo del metodo 'obtenerCodigosOperacionExterno'");
    	
    	List<String> listaCodigo = new ArrayList<String>();
    	boolean esOperacionManual = false;
    	Long idOperacion = null;
    	
    	for (ShvCobTransaccion transaccion : listaTransacciones) {
    		Traza.advertencia(getClass(), "~ Verifico si existen operaciones manuales dentro de la transaccion: " + transaccion.getIdTransaccion());
    		if (esTransaccionConAplicacionManual(transaccion) || esTransaccionConConjuntoDeDebitos(transaccion)) {
    			esOperacionManual = true;
    			idOperacion = transaccion.getOperacion().getIdOperacion();
        		Traza.advertencia(getClass(), "~ es operacion manual!");
    			break;
    		} 
    	}
    	if (esOperacionManual) {
			if (!TipoOperacionEnum.COBRO.equals(tipoOperacion)) {

				Traza.advertencia(getClass(), "~ Es un Descobro, busco los codigos de operacion externa");
				try {
					ShvCobDescobroSimpleSinWorkflow descobro = descobroDao.buscarDescobroSimpleSinWorkflowPorIdOperacion(idOperacion);
					List<ShvCobDescobroCodigoOperacionExternaSimplificado> listaCodigoOperacionesExternas = descobroCodigoOperacionExternaSimplificadoDao.listarPorIdDescobro(descobro.getIdDescobro());
					for (ShvCobDescobroCodigoOperacionExternaSimplificado registroCodigoOperacionExterna : listaCodigoOperacionesExternas) {
						if (!Validaciones.isNullEmptyOrDash(registroCodigoOperacionExterna.getCodigoOperacionExterna())) {
							listaCodigo.add(Utilidad.truncarCadena(
									registroCodigoOperacionExterna.getCodigoOperacionExterna().trim(),
								TRUNCAR,
								PUNTOS
							));
						}
					}
				} catch (PersistenciaExcepcion e) {
					Traza.error(getClass(), e.getMessage());
					throw new NegocioExcepcion(e.getMessage(), e);
				}
			} else {

				Traza.advertencia(getClass(), "~ Es un Cobro, busco los codigos de operacion externa");
				try {
					ShvCobCobroSimpleSinWorkflow cobro = cobroDao.buscarCobroSimpleSinWorkflowPorIdOperacion(idOperacion);
					List<ShvCobEdCodigoOperacionExternaSimplificado> listaCodigoOperacionesExternas = cobroCodigoOperacionExternaSimplificadoDao.listarPorIdCobro(cobro.getIdCobro());
					for (ShvCobEdCodigoOperacionExternaSimplificado registroCodigoOperacionExterna : listaCodigoOperacionesExternas) {
						if (!Validaciones.isNullEmptyOrDash(registroCodigoOperacionExterna.getCodigoOperacionExterna())) {
							listaCodigo.add(Utilidad.truncarCadena(
								registroCodigoOperacionExterna.getCodigoOperacionExterna().trim(),
								TRUNCAR,
								PUNTOS
							));
						}
					}
					Traza.advertencia(getClass(), "~ Busqueda finalizada.");

				} catch (PersistenciaExcepcion e) {
					Traza.error(getClass(), e.getMessage());
					throw new NegocioExcepcion(e.getMessage(), e);
				}
			}
			Collections.sort(listaCodigo);

			if (!listaCodigo.isEmpty()) {
				
				Utilidad.tracearTiempo(getClass(), "Finalizo el metodo 'obtenerCodigosOperacionExterno' con una duracion de: ", fechaHoraInicioNanoTime);
				return StringUtils.join(
					listaCodigo.toArray(new String[listaCodigo.size()]),
					","
				);
			}
    	}

    	Utilidad.tracearTiempo(getClass(), "Finalizo el metodo 'obtenerCodigosOperacionExterno' con una duracion de: ", fechaHoraInicioNanoTime);
    	return "";
    }
	/**
	 * @return the genericoDao
	 */
	public IGenericoDao getGenericoDao() {
		return genericoDao;
	}

	/**
	 * @param genericoDao the genericoDao to set
	 */
	public void setGenericoDao(IGenericoDao genericoDao) {
		this.genericoDao = genericoDao;
	}

	/**
	 * @return the documentoDao
	 */
	public IDocumentoDao getDocumentoDao() {
		return documentoDao;
	}

	/**
	 * @param documentoDao the documentoDao to set
	 */
	public void setDocumentoDao(IDocumentoDao documentoDao) {
		this.documentoDao = documentoDao;
	}

	/**
	 * @return the parametroDao
	 */
	public IParametroDao getParametroDao() {
		return parametroDao;
	}

	/**
	 * @param parametroDao the parametroDao to set
	 */
	public void setParametroDao(IParametroDao parametroDao) {
		this.parametroDao = parametroDao;
	}

	/**
	 * @return the cobroServicio
	 */
	public ICobroImputacionServicio getCobroServicio() {
		return cobroServicio;
	}

	/**
	 * @param cobroServicio the cobroServicio to set
	 */
	public void setCobroServicio(ICobroImputacionServicio cobroServicio) {
		this.cobroServicio = cobroServicio;
	}

	/**
	 * @return the descobroServicio
	 */
	public IDescobroServicio getDescobroServicio() {
		return descobroServicio;
	}

	/**
	 * @param descobroServicio the descobroServicio to set
	 */
	public void setDescobroServicio(IDescobroServicio descobroServicio) {
		this.descobroServicio = descobroServicio;
	}

	/**
	 * @return the vistaSoporteServicio
	 */
	public IVistaSoporteServicio getVistaSoporteServicio() {
		return vistaSoporteServicio;
	}

	/**
	 * @param vistaSoporteServicio the vistaSoporteServicio to set
	 */
	public void setVistaSoporteServicio(IVistaSoporteServicio vistaSoporteServicio) {
		this.vistaSoporteServicio = vistaSoporteServicio;
	}

	/**
	 * @return the cobroOnlineDao
	 */
	public ICobroOnlineDao getCobroOnlineDao() {
		return cobroOnlineDao;
	}

	/**
	 * @param cobroOnlineDao the cobroOnlineDao to set
	 */
	public void setCobroOnlineDao(ICobroOnlineDao cobroOnlineDao) {
		this.cobroOnlineDao = cobroOnlineDao;
	}

	/**
	 * @return the cobroOnlineClienteDao
	 */
	public ICobroOnlineClienteDao getCobroOnlineClienteDao() {
		return cobroOnlineClienteDao;
	}

	/**
	 * @param cobroOnlineClienteDao the cobroOnlineClienteDao to set
	 */
	public void setCobroOnlineClienteDao(
			ICobroOnlineClienteDao cobroOnlineClienteDao) {
		this.cobroOnlineClienteDao = cobroOnlineClienteDao;
	}

	public ICobroDao getCobroDao() {
		return cobroDao;
	}

	public void setCobroDao(ICobroDao cobroDao) {
		this.cobroDao = cobroDao;
	}
	
	
}
