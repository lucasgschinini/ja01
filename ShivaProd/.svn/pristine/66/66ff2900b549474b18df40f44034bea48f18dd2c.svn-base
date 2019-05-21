package ar.com.telecom.shiva.base.ws.cliente;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCargoEntradaCargosWs;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cargos.DetCargo;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cargos.RespuestaCargo;
import ar.com.telecom.shiva.presentacion.wsdl.calipso.cargos.WebServiceCalipsoCargosPortType;

public class CalipsoCargosWS {
	
	@Autowired
	WebServiceCalipsoCargosPortType webServiceCalipsoCargosPortType;
	
	private final String MENSAJE = "WS CalipsoCargos";
	
	/**
	 * Objetivo: Permite al consumidor del servicio generar cargos a proxima factura
	 * Proveedor: Calipso
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion 
	 */
	public SalidaCalipsoCargosWS generarCargoCalipso(EntradaCalipsoCargosWS entrada) 
			throws NegocioExcepcion {
		
		try {
			
			String idOperacionShiva = entrada.getIdOperacion();
			String numeroTransaccionShiva = entrada.getNumeroTransaccion();
			String usuarioCobrador = entrada.getUsuarioCobrador();
			String tipoOperacion = entrada.getTipoOperacion()!=null?entrada.getTipoOperacion().name():null;
			String modoOperacion = entrada.getModoOperacion()!=null?entrada.getModoOperacion().getDescripcionCorta():null;
			
			DetCargo detalleCargo = null;
			DetalleCargoEntradaCargosWs detalleCargoEntrada = entrada.getDetalleCargo();
			
			if(!Validaciones.isObjectNull(detalleCargoEntrada)){
				detalleCargo = new DetCargo();
			
				detalleCargo.setAcuerdo(
						!Validaciones.isNullOrEmpty(detalleCargoEntrada.getAcuerdoFacturacion())
							?detalleCargoEntrada.getAcuerdoFacturacion():"");
				detalleCargo.setImporte(
						detalleCargoEntrada.getImporte()!=null
							?new BigDecimal(detalleCargoEntrada.getImporte().toString().replace(",", ".")):new BigDecimal("0"));
				detalleCargo.setFechaDesde(
						!Validaciones.isObjectNull(detalleCargoEntrada.getFechaDesde())
							?Utilidad.formatDateAAAAMMDDconBarra(detalleCargoEntrada.getFechaDesde()):"");
				detalleCargo.setFechaHasta(
						!Validaciones.isObjectNull(detalleCargoEntrada.getFechaHasta())
							?Utilidad.formatDateAAAAMMDDconBarra(detalleCargoEntrada.getFechaHasta()):"");
				detalleCargo.setTipoMora(
						!Validaciones.isObjectNull(detalleCargoEntrada.getTipoMora())
							?detalleCargoEntrada.getTipoMora().name():"");
				detalleCargo.setAlgoritmoMora(
						!Validaciones.isObjectNull(detalleCargoEntrada.getAlgoritmoMora())
							?detalleCargoEntrada.getAlgoritmoMora().name():"");
				detalleCargo.setImporteBonificacionIntereses(
						detalleCargoEntrada.getImporteBonificacionIntereses()!=null
							?new BigDecimal(detalleCargoEntrada.getImporteBonificacionIntereses().toString().replace(",", ".")):new BigDecimal("0"));
				detalleCargo.setOrigenCargo(
						!Validaciones.isObjectNull(detalleCargoEntrada.getOrigenCargo())
							?detalleCargoEntrada.getOrigenCargo().codigo():"");
				detalleCargo.setLeyendaFacturaCargo(
						!Validaciones.isNullOrEmpty(detalleCargoEntrada.getLeyendaFacturaCargo())
							?detalleCargoEntrada.getLeyendaFacturaCargo():"");
				detalleCargo.setLeyendaFacturaInteres(
						!Validaciones.isNullOrEmpty(detalleCargoEntrada.getLeyendaFacturaInteres())
							?detalleCargoEntrada.getLeyendaFacturaInteres():"");
				detalleCargo.setMonedaCargo(
						!Validaciones.isObjectNull(detalleCargoEntrada.getMonedaCargo())
						?detalleCargoEntrada.getMonedaCargo().getSigno2():"");
			}
			
			RespuestaCargo respuesta = new RespuestaCargo();
			
			respuesta = webServiceCalipsoCargosPortType.generacionCargo(idOperacionShiva, numeroTransaccionShiva, usuarioCobrador, tipoOperacion, modoOperacion, detalleCargo);
			
			if (!Validaciones.isObjectNull(respuesta)) {
				SalidaCalipsoCargosWS salida = new SalidaCalipsoCargosWS();
				salida.setIdOperacionTransaccion(respuesta.getIdTransaccionShiva());
				salida.setIdMovMer(respuesta.getIdMovMer());
				salida.setMontoCalculadoMora(respuesta.getMontoCalculadoMora());
				salida.setMontoACuenta(respuesta.getMontoACuenta());
				salida.setInteresesBonificados(respuesta.getInteresesBonificados());
				salida.setMonedaIntereses(!Validaciones.isNullEmptyOrDash(respuesta.getMonedaIntereses())?MonedaEnum.getEnumBySigno2(respuesta.getMonedaIntereses()):null);
				
				if(!Validaciones.isObjectNull(respuesta.getResultado())){
					Resultado salidaResultado = new Resultado();
					salidaResultado.setCodigoError(respuesta.getResultado().getCodigoerror());
					salidaResultado.setDescripcionError(respuesta.getResultado().getDescripcionerror());
					salidaResultado.setResultado(respuesta.getResultado().getResultado());
					salida.setResultado(salidaResultado);
				}
				
				return salida;
			}else{
				throw new WebServiceExcepcion(MENSAJE + ": Se ha producido un error en el webservice");
			}	
			
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion(MENSAJE + " - Error de formato: " + e);	
		} catch (RemoteAccessException e) {
			throw new WebServiceExcepcion(MENSAJE + ": Falla de conexion: " + e);
		}
	}
	
}
