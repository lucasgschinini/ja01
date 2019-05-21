package ar.com.telecom.shiva.base.ws.cliente;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoCargoGeneradoEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCreditoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleNotaCreditoDebitoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleNotaCreditoDebitoGeneradoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleNotaCreditoDebitoOriginalDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleCargoGeneradoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleFacturaDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleMedioPagoDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.DetalleOperacionRelacionadaDescobro;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.Detallecargo;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.Detallecontracargocar;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.Detallecontracargofac;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.Detallefacturas;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.ListaCtaCrereversiones;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.ListaCtaCrereversiones.Detalle;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.Listacreditosydebitos.Detallecreditosydebitos;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.Listadetallemediospago.DetMedioPago;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.Listadetalleoperacionesrelacionadas.OperacionRelacionado;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.RespuestaReversion;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.descobros.WebServiceReversionCobrosPortType;

public class CalipsoDescobrosWS {
	
	private static final String MENSAJE_ERROR = "WS ReversionCobros ";
	
	@Autowired 
	WebServiceReversionCobrosPortType webServiceReversionCobrosPortType;
	
	/**
	 * Objetivo: Permite al consumidor del servicio realizar las siguientes operaciones
	 * 				Imputar Descobro
	 * Proveedor: Calipso
	 * 
	 * @param entrada
	 * @return
	 * @throws WebServiceExcepcion
	 * @throws WebServiceFormatoMensajeExcepcion 
	 */
	public SalidaCalipsoDescobroWS descobrarCobro(EntradaCalipsoDescobroWS entrada) 
			throws WebServiceExcepcion, WebServiceFormatoMensajeExcepcion {
		try {
			String idOperacionShiva = null;
			if(!Validaciones.isObjectNull(entrada.getIdOperacion())){
				idOperacionShiva = Utilidad.rellenarCerosIzquierda(entrada.getIdOperacion().toString(), 7);
			}
			String numeroTransaccion = null;
			if(!Validaciones.isObjectNull(entrada.getNumeroTransaccion())){
				numeroTransaccion = Utilidad.rellenarCerosIzquierda(entrada.getNumeroTransaccion().toString(), 5);
			}
			String usuario = Constantes.SHIVA_APLICACION.toUpperCase();
			String tipoOperacion = Utilidad.rellenarCerosIzquierda(Utilidad.generarSalidaConValorOVacio(entrada.getTipoOperacion().getValor()),2);
			String modoOperacion = !Validaciones.isObjectNull(entrada.getModoOperacion())?entrada.getModoOperacion().getDescripcionCorta():null;
			
			//Detalle de Factura (Agrupador)
			Detallefacturas detalleFacturaEntrada = new Detallefacturas();
			if (!Validaciones.isObjectNull(entrada.getIdCobranza())) {
				detalleFacturaEntrada.setIdCobranza(entrada.getIdCobranza());
			}
			
			//Detalle de Cargo (Agrupador)
			Detallecargo detalleCargoEntrada = new Detallecargo();
			if (!Validaciones.isObjectNull(entrada.getIdMovMer())) {
				detalleCargoEntrada.setIdMovMer(entrada.getIdMovMer());
			}
			
			//Lista de CTA o Notas de Credito a revertir (Lista)
			ListaCtaCrereversiones listaCtaCreEntrada = new ListaCtaCrereversiones();
			//Detalle de CTA o Nota de Crédito (Agrupador)
			List<ListaCtaCrereversiones.Detalle> detalles = new ArrayList<ListaCtaCrereversiones.Detalle>();
			for (DetalleCTAoNotaCreditoDescobro ctaCre: entrada.getListaCtaONotaCredito()) {
				ListaCtaCrereversiones.Detalle detalleCtaCreEntrada = new Detalle();
				detalleCtaCreEntrada.setIdCobranza(ctaCre.getIdCobranza());
				detalles.add(detalleCtaCreEntrada);				
			}
			listaCtaCreEntrada.getDetalle().addAll(detalles);
			
			//Detalle del Contracargo a Generar por Factura (Agrupador)
			Detallecontracargofac detalleContracargoFacturaEntrada = new Detallecontracargofac();
			if(!Validaciones.isObjectNull(entrada.getFacturarContracargoFactura())){
				detalleContracargoFacturaEntrada.setFacturarContracargo(entrada.getFacturarContracargoFactura().getDescripcionCorta());
			}
			if(!Validaciones.isNullOrEmpty(entrada.getAcuerdoFacturacionContracargoFactura())){
				detalleContracargoFacturaEntrada.setAcuerdo(entrada.getAcuerdoFacturacionContracargoFactura());
			} else{
				detalleContracargoFacturaEntrada.setAcuerdo("");
			}
			
			//Detalle del Contracargo a Generar por Cargo (Agrupador)
			Detallecontracargocar detalleContracargoCargoEntrada = new Detallecontracargocar();
			if(!Validaciones.isObjectNull(entrada.getFacturarContracargoCargo())){
				detalleContracargoCargoEntrada.setFacturarContracargo(entrada.getFacturarContracargoCargo().getDescripcionCorta());
			}
			if(!Validaciones.isNullOrEmpty(entrada.getAcuerdoFacturacionContracargoCargo())){
				detalleContracargoCargoEntrada.setAcuerdo(entrada.getAcuerdoFacturacionContracargoCargo());
			}else{
				detalleContracargoCargoEntrada.setAcuerdo("");
			}
			
			//TODO 
			RespuestaReversion respuesta = webServiceReversionCobrosPortType.reversion(idOperacionShiva, numeroTransaccion, usuario, tipoOperacion, modoOperacion, detalleFacturaEntrada, detalleCargoEntrada, listaCtaCreEntrada, detalleContracargoFacturaEntrada, detalleContracargoCargoEntrada);
			
			//Salida>>>>
			
			if (!Validaciones.isObjectNull(respuesta)) {
				
				SalidaCalipsoDescobroWS datos = new SalidaCalipsoDescobroWS();
				datos.setIdOperacionTransaccion(respuesta.getIdTransaccionShiva());
				if(!Validaciones.isObjectNull(respuesta.getTipoOperacion())){
					datos.setTipoOperacion(TipoInvocacionEnum.getEnumById(new Long(respuesta.getTipoOperacion())));
				}
				//Detalle Resultado de la llamada al servicio (Agrupador)
				if (!Validaciones.isObjectNull(respuesta.getDetResultadoServicio())) {
					Resultado resultadoLlamadaServicio = new Resultado();
					resultadoLlamadaServicio.setCodigoError(respuesta.getDetResultadoServicio().getCodigoerror());
					resultadoLlamadaServicio.setDescripcionError(respuesta.getDetResultadoServicio().getDescripcionerror());
					resultadoLlamadaServicio.setResultado(respuesta.getDetResultadoServicio().getResultado());
					datos.setResultadoLlamadaServicio(resultadoLlamadaServicio);
				}
				
				//Detalle de Factura (Agrupador)
				if (!Validaciones.isObjectNull(respuesta.getDetFactura())
						&& !Validaciones.isObjectNull(respuesta.getDetFactura().getDetResultadoFactura())) {
					
					DetalleFacturaDescobro detalleFacturaSalida = new DetalleFacturaDescobro();
					
					//Detalle de Resultado de Reversión de Factura (Agrupador)
					if (!Validaciones.isObjectNull(respuesta.getDetFactura().getDetResultadoFactura())) {
						Resultado resultadoFactura = new Resultado();
						resultadoFactura.setCodigoError(respuesta.getDetFactura().getDetResultadoFactura().getCodigoerror());
						resultadoFactura.setDescripcionError(respuesta.getDetFactura().getDetResultadoFactura().getDescripcionerror());
						resultadoFactura.setResultado(respuesta.getDetFactura().getDetResultadoFactura().getResultado());
						detalleFacturaSalida.setResultadoDescobroFactura(resultadoFactura);
					}
					
					//Detalle de Cargo Generado Factura (Agrupador)
					if (!Validaciones.isObjectNull(respuesta.getDetFactura().getDetAcuerdoContracargo())) {
						if(!Validaciones.isObjectNull(respuesta.getDetFactura().getDetAcuerdoContracargo().getEstadoAcuerdo())){
							detalleFacturaSalida.setEstadoAcuerdoFacturacion(EstadoAcuerdoFacturacionEnum.getEnumByName(respuesta.getDetFactura().getDetAcuerdoContracargo().getEstadoAcuerdo()));
						}
						if(!Validaciones.isObjectNull(respuesta.getDetFactura().getDetAcuerdoContracargo().getEstadoCargo())){
							detalleFacturaSalida.setEstadoCargoGenerado(EstadoCargoGeneradoEnum.getEnumByNameConEspacios(respuesta.getDetFactura().getDetAcuerdoContracargo().getEstadoCargo()));
						}
					}
					
					datos.setDetalleFacturaDescobro(detalleFacturaSalida);
				}
				
				//Lista Detalle de Medios de Pago  (Lista)
				if (!Validaciones.isObjectNull(respuesta.getListaDetMediosPago())
						&& Validaciones.isCollectionNotEmpty(respuesta.getListaDetMediosPago().getDetMedioPago())) {
					
					List<DetalleMedioPagoDescobro> listaDetalleMedioPagoDescobro = new ArrayList<DetalleMedioPagoDescobro>();
					
					//Detalle de Medio de Pago (Agrupador)
					for (DetMedioPago detalle : respuesta.getListaDetMediosPago().getDetMedioPago()) {
						
						DetalleMedioPagoDescobro detalleMedioPagoSalida = new DetalleMedioPagoDescobro();
						
						if(!Validaciones.isObjectNull(detalle.getIdMedioPago())){
							IdDocumento idMedioPago = new IdDocumento();
							if(!Validaciones.isObjectNull(detalle.getIdMedioPago().getClaseComprobante())){
								idMedioPago.setTipoComprobante(TipoComprobanteEnum.getEnumByName(detalle.getIdMedioPago().getTipoComprobante()));
							}
							if(!Validaciones.isObjectNull(detalle.getIdMedioPago().getClaseComprobante())){
								idMedioPago.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(detalle.getIdMedioPago().getClaseComprobante()));
							}
							idMedioPago.setSucursalComprobante(detalle.getIdMedioPago().getSucursalComprobante());
							idMedioPago.setNumeroComprobante(detalle.getIdMedioPago().getNumeroComprobante());
							
							//Id de Medio de Pago (Agrupador)
							detalleMedioPagoSalida.setIdMedioPago(idMedioPago);
						}
						
						detalleMedioPagoSalida.setIdDocCtasCob(detalle.getIdDocumentoCuentasCobranza());
						
						//Detalle de Resultado Reversión de Medio de Pago (Agrupador)
						if(!Validaciones.isObjectNull(detalle.getDetResultadoMedioPago())){
							Resultado resultadoMedioPagoSalida = new Resultado();
							resultadoMedioPagoSalida.setCodigoError(detalle.getDetResultadoMedioPago().getCodigoerror());
							resultadoMedioPagoSalida.setDescripcionError(detalle.getDetResultadoMedioPago().getDescripcionerror());
							resultadoMedioPagoSalida.setResultado(detalle.getDetResultadoMedioPago().getResultado());							
							detalleMedioPagoSalida.setResultadoDescobroMedioPago(resultadoMedioPagoSalida);
						}
						
						listaDetalleMedioPagoDescobro.add(detalleMedioPagoSalida);
					}
					
					datos.setListaDetalleMedioPagoDescobro(listaDetalleMedioPagoDescobro);
				}
				
				
				//Detalle de Cargo Generado (Agrupador)
				if (!Validaciones.isObjectNull(respuesta.getDetCargo())
						&& !Validaciones.isObjectNull(respuesta.getDetCargo().getDetResultadoCargo())) {
					
					DetalleCargoGeneradoDescobro detalleCargoGeneradoSalida = new DetalleCargoGeneradoDescobro();
					
					if (!Validaciones.isObjectNull(respuesta.getDetCargo())) {
						if(!Validaciones.isObjectNull(respuesta.getDetCargo().getEstadoAcuerdo())){
							detalleCargoGeneradoSalida.setEstadoAcuerdoFacturacion(EstadoAcuerdoFacturacionEnum.getEnumByName(respuesta.getDetCargo().getEstadoAcuerdo()));
						}
						if(!Validaciones.isObjectNull(respuesta.getDetCargo().getEstadoCargo())){
							detalleCargoGeneradoSalida.setEstadoCargoGenerado(EstadoCargoGeneradoEnum.getEnumByNameConEspacios(respuesta.getDetCargo().getEstadoCargo()));
						}
						detalleCargoGeneradoSalida.setInteresesRealesTrasladados(respuesta.getDetCargo().getInteresesTrasladados());
					}
					
					//Detalle de Resultado de Contracargos Generados (Agrupador)
					if (!Validaciones.isObjectNull(respuesta.getDetCargo().getDetResultadoCargo())) {
						Resultado resultadoCargoGenerado = new Resultado();
						resultadoCargoGenerado.setCodigoError(respuesta.getDetCargo().getDetResultadoCargo().getCodigoerror());
						resultadoCargoGenerado.setDescripcionError(respuesta.getDetCargo().getDetResultadoCargo().getDescripcionerror());
						resultadoCargoGenerado.setResultado(respuesta.getDetCargo().getDetResultadoCargo().getResultado());
						detalleCargoGeneradoSalida.setResultadoDescobroCargoGenerado(resultadoCargoGenerado);
					}
					
					datos.setDetalleCargoGeneradoDescobro(detalleCargoGeneradoSalida);
				}
				
				//Lista Detalle de Operaciones Posteriores Relacionadas (Lista)
				if (!Validaciones.isObjectNull(respuesta.getListaDetOpRelacionadas())
						&& Validaciones.isCollectionNotEmpty(respuesta.getListaDetOpRelacionadas().getOperacionRelacionado())) {
					
					List<DetalleOperacionRelacionadaDescobro> listaOperacionesRelacionadas = new ArrayList<DetalleOperacionRelacionadaDescobro>();
					
					//Detalle de Operaciones Posteriores Relacionadas (Agrupador)
					for (OperacionRelacionado detalle : respuesta.getListaDetOpRelacionadas().getOperacionRelacionado()) {
						
						DetalleOperacionRelacionadaDescobro detalleOperacionRelacionadaSalida = new DetalleOperacionRelacionadaDescobro();
						if(!Validaciones.isNullOrEmpty(detalle.getSistema())){
							detalleOperacionRelacionadaSalida.setSistema(SistemaEnum.getEnumByName(detalle.getSistema()));							
						}
						
						//Detalle de Operación Shiva (Agrupador)
						if(!Validaciones.isObjectNull(detalle.getDetOperacionRelacionadaShiva())){
							if(!Validaciones.isObjectNull(detalle.getDetOperacionRelacionadaShiva().getIdOperacionShiva())){
								detalleOperacionRelacionadaSalida.setIdOperacion(new Long(detalle.getDetOperacionRelacionadaShiva().getIdOperacionShiva()));
							}
							if(!Validaciones.isNullEmptyOrDash(detalle.getDetOperacionRelacionadaShiva().getIdTransaccionShiva())){
								detalleOperacionRelacionadaSalida.setNumeroTransaccion(new Integer(detalle.getDetOperacionRelacionadaShiva().getIdTransaccionShiva()));
							}
						}
						
						//Detalle de Operación Legado (Agrupador)
						if(!Validaciones.isObjectNull(detalle.getDetOperacionRelacionadaLegado()) 
								&& !Validaciones.isObjectNull(detalle.getDetOperacionRelacionadaLegado().getIdCobranza())){
							detalleOperacionRelacionadaSalida.setIdCobranza(detalle.getDetOperacionRelacionadaLegado().getIdCobranza().longValue());
						}
						
						//Detalle de documento (Agrupador)
						if(!Validaciones.isObjectNull(detalle.getDetalleDocumento())){
							
							//Id de Documento (Agrupador)
							if(!Validaciones.isObjectNull(detalle.getDetalleDocumento().getIdDocumento())){
								IdDocumento detalleDocumento = new IdDocumento();
								if(!Validaciones.isNullOrEmpty(detalle.getDetalleDocumento().getIdDocumento().getTipoComprobante())){
									detalleDocumento.setTipoComprobante(TipoComprobanteEnum.getEnumByName(detalle.getDetalleDocumento().getIdDocumento().getTipoComprobante()));
								}
								if(!Validaciones.isNullOrEmpty(detalle.getDetalleDocumento().getIdDocumento().getClaseComprobante())){
									detalleDocumento.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(detalle.getDetalleDocumento().getIdDocumento().getClaseComprobante()));
								}
								detalleDocumento.setSucursalComprobante(detalle.getDetalleDocumento().getIdDocumento().getSucursalComprobante());
								detalleDocumento.setNumeroComprobante(detalle.getDetalleDocumento().getIdDocumento().getNumeroComprobante());
								
								detalleOperacionRelacionadaSalida.setDetalleDocumento(detalleDocumento);
							}
							detalleOperacionRelacionadaSalida.setIdDocCtasCob(detalle.getDetalleDocumento().getIdDocumentoCuentasCobranza());
							detalleOperacionRelacionadaSalida.setCodigoCliente(detalle.getDetalleDocumento().getIdClienteLegado());
							detalleOperacionRelacionadaSalida.setImporteCobrado(detalle.getDetalleDocumento().getImporte());
							if(!Validaciones.isNullOrEmpty(detalle.getDetalleDocumento().getFechaCob())){
								try {
									detalleOperacionRelacionadaSalida.setFechaCobranza(Utilidad.parseDateFullFormatBarra(detalle.getDetalleDocumento().getFechaCob()));
								} catch (ParseException e) {
									Traza.error(getClass(), e.getMessage());
									throw new WebServiceExcepcion(e);
								}
							}
							
						}
						
						listaOperacionesRelacionadas.add(detalleOperacionRelacionadaSalida);
					}
					
					datos.setListaOperacionesRelacionadas(listaOperacionesRelacionadas);
				}
				
				//Lista de Notas de Crédito o Débito generadas por la reversión (Lista)
				if (!Validaciones.isObjectNull(respuesta.getListaCreDeb())
						&& Validaciones.isCollectionNotEmpty(respuesta.getListaCreDeb().getDetallecreditosydebitos())
						) {
					
					List<DetalleNotaCreditoDebitoDescobro> listaNotasCreditoDebitoSalida = new ArrayList<DetalleNotaCreditoDebitoDescobro>();
					
					for (Detallecreditosydebitos detalleNotaCreditoDebito : respuesta.getListaCreDeb().getDetallecreditosydebitos()) {
						
						DetalleNotaCreditoDebitoDescobro detalleNotaCreditoDebitoSalida = new DetalleNotaCreditoDebitoDescobro();
						
						//Detalle de Notas de Crédito o Débito Generada (Agrupador)
						DetalleNotaCreditoDebitoGeneradoDescobro notaCreDebGeneradoSalida = new DetalleNotaCreditoDebitoGeneradoDescobro();
						
						notaCreDebGeneradoSalida.setIdCobranza(detalleNotaCreditoDebito.getDetalleGen().getIdCobranza());
						notaCreDebGeneradoSalida.setImporte(detalleNotaCreditoDebito.getDetalleGen().getImporte());
						notaCreDebGeneradoSalida.setImporteCapital(detalleNotaCreditoDebito.getDetalleGen().getImporteCapital());
						notaCreDebGeneradoSalida.setImporteImpuestos(detalleNotaCreditoDebito.getDetalleGen().getImporteImpuestos());
						
						if(!Validaciones.isObjectNull(detalleNotaCreditoDebito.getDetalleGen().getIdDocumento())){
							
							IdDocumento documento = new IdDocumento();
							if(!Validaciones.isNullOrEmpty((detalleNotaCreditoDebito.getDetalleGen().getIdDocumento().getTipo()))){
								documento.setTipoComprobante(TipoComprobanteEnum.getEnumByValor(detalleNotaCreditoDebito.getDetalleGen().getIdDocumento().getTipo()));	
							}
							if(!Validaciones.isNullOrEmpty((detalleNotaCreditoDebito.getDetalleGen().getIdDocumento().getClase()))){
								documento.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(detalleNotaCreditoDebito.getDetalleGen().getIdDocumento().getClase()));
							}
							documento.setSucursalComprobante(detalleNotaCreditoDebito.getDetalleGen().getIdDocumento().getSucursal());
							documento.setNumeroComprobante(detalleNotaCreditoDebito.getDetalleGen().getIdDocumento().getNumero());
							
							notaCreDebGeneradoSalida.setDocumentoGenerado(documento);
						}
						
						notaCreDebGeneradoSalida.setIdDocumentoCuentasCobranza(detalleNotaCreditoDebito.getDetalleGen().getIdDocumentoCuentasCobranza());
						
						if(!Validaciones.isNullOrEmpty(detalleNotaCreditoDebito.getDetalleGen().getVencimiento())){
							try {
								notaCreDebGeneradoSalida.setFechaVencimiento(Utilidad.parseFechaBarraString(detalleNotaCreditoDebito.getDetalleGen().getVencimiento()));
							} catch (ParseException e) {
								Traza.error(getClass(), e.getMessage());
								throw new WebServiceExcepcion(e);
							}
						}
						
						if(!Validaciones.isNullOrEmpty(detalleNotaCreditoDebito.getDetalleGen().getFechaImputacion())){
							try {
								notaCreDebGeneradoSalida.setFechaImputacion(Utilidad.parseFechaBarraString(detalleNotaCreditoDebito.getDetalleGen().getFechaImputacion()));
							} catch (ParseException e) {
								Traza.error(getClass(), e.getMessage());
								throw new WebServiceExcepcion(e);
							}
						}
						
						notaCreDebGeneradoSalida.setImporteAplicado(detalleNotaCreditoDebito.getDetalleGen().getImporteAplicado());
						
						if(!Validaciones.isObjectNull(detalleNotaCreditoDebito.getDetalleGen().getOrigenDelDocumento())){
							notaCreDebGeneradoSalida.setOrigenDelDocumento(OrigenDocumentoEnum.getEnumByName(detalleNotaCreditoDebito.getDetalleGen().getOrigenDelDocumento()));
						}
						
						//Detalle de Notas de Crédito o Débito Original (Agrupador)
						DetalleNotaCreditoDebitoOriginalDescobro notaCreDebOriginalSalida = new DetalleNotaCreditoDebitoOriginalDescobro();


						if(!Validaciones.isObjectNull(detalleNotaCreditoDebito.getDetalleOri().getIdDocumento())){
							
							IdDocumento documento = new IdDocumento();
							if(!Validaciones.isNullOrEmpty((detalleNotaCreditoDebito.getDetalleOri().getIdDocumento().getTipo()))){
								documento.setTipoComprobante(TipoComprobanteEnum.getEnumByValor(detalleNotaCreditoDebito.getDetalleOri().getIdDocumento().getTipo()));	
							}
							if(!Validaciones.isNullOrEmpty((detalleNotaCreditoDebito.getDetalleOri().getIdDocumento().getClase()))){
								documento.setClaseComprobante(TipoClaseComprobanteEnum.getEnumByName(detalleNotaCreditoDebito.getDetalleOri().getIdDocumento().getClase()));
							}
							documento.setSucursalComprobante(detalleNotaCreditoDebito.getDetalleOri().getIdDocumento().getSucursal());
							documento.setNumeroComprobante(detalleNotaCreditoDebito.getDetalleOri().getIdDocumento().getNumero());
							
							notaCreDebOriginalSalida.setDocumentoGenerado(documento);
						}
						
						notaCreDebOriginalSalida.setIdDocumentoCuentasCobranza(detalleNotaCreditoDebito.getDetalleOri().getIdDocumentoCuentasCobranza());
						
						detalleNotaCreditoDebitoSalida.setNotaCreDebGenerado(notaCreDebGeneradoSalida);
						detalleNotaCreditoDebitoSalida.setNotaCreDebOriginal(notaCreDebOriginalSalida);
						
						listaNotasCreditoDebitoSalida.add(detalleNotaCreditoDebitoSalida);
					}
					
					datos.setListaNotasCreditoDebitoDescobro(listaNotasCreditoDebitoSalida);
				}
				
				return datos;
			}
			throw new WebServiceExcepcion(MENSAJE_ERROR + ": Se ha producido un error en el webservice");
			
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion(MENSAJE_ERROR + " - Error de formato: " + e);	
		} catch (RemoteAccessException e) {
			throw new WebServiceExcepcion(MENSAJE_ERROR + ": Falla de conexion: " + e);
		}
	}
	
	/*****************************************************************************
	 * Getters & Setters
	 *****************************************************************************/
	
	public WebServiceReversionCobrosPortType getWebServiceReversionCobrosPortType() {
		return webServiceReversionCobrosPortType;
	}
	
	public void setWebServiceReversionCobrosPortType(
			WebServiceReversionCobrosPortType webServiceReversionCobrosPortType) {
		this.webServiceReversionCobrosPortType = webServiceReversionCobrosPortType;
	}

}
