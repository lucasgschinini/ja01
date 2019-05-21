package ar.com.telecom.shiva.base.jms.mapeos;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicGeneracionCargoEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleGeneracionCargos;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.jms.util.definicion.CampoAEnviarJMS;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class MicGeneracionCargoMapeador extends MapeadorJMS {

	@Override
	public String serializar(JMS jms, boolean esSincronico) throws NegocioExcepcion {
		MicGeneracionCargoEntrada datosAEnviar = (MicGeneracionCargoEntrada) jms;
		
		List<CampoAEnviarJMS> listaCamposAEnviar = new ArrayList<CampoAEnviarJMS>();
		listaCamposAEnviar.add(new CampoAEnviarJMS("tipoInvocacion", null, null, String.valueOf(datosAEnviar.getTipoInvocacion().getValor())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("modoEjecucion", null, null,String.valueOf(datosAEnviar.getModoEjecucion().getDescripcionCorta())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("idOperacion", null, null, String.valueOf(datosAEnviar.getIdOperacion())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("idSecuencia", null, null, 
				datosAEnviar.getIdSecuencia()!=null?String.valueOf(datosAEnviar.getIdSecuencia()):null));
		listaCamposAEnviar.add(new CampoAEnviarJMS("usuarioCobrador", null, null, String.valueOf(datosAEnviar.getUsuarioCobrador())));
		
		MicDetalleGeneracionCargos detalle = datosAEnviar.getDetalleGeneracionCargos();
		
		if(!Validaciones.isObjectNull(detalle)){

			//Detalle
			listaCamposAEnviar.add(new CampoAEnviarJMS("acuerdoFacturacion", null, null,
					detalle.getAcuerdoFacturacion()!=null ? detalle.getAcuerdoFacturacion():""));
			String importeNoReguladoFormateado = Utilidad.formatCurrencySacarPunto(Utilidad.formatCurrencyBDAgregandoDecimales(detalle.getImporteNoRegulado(), 2).toString());
			listaCamposAEnviar.add(new CampoAEnviarJMS("importeNoRegulado", null, null, importeNoReguladoFormateado));
			String importeReguladoFormateado = Utilidad.formatCurrencySacarPunto(Utilidad.formatCurrencyBDAgregandoDecimales(detalle.getImporteRegulado(), 2).toString());
			listaCamposAEnviar.add(new CampoAEnviarJMS("importeRegulado", null, null, importeReguladoFormateado));
			listaCamposAEnviar.add(new CampoAEnviarJMS("fechaDesde", null, null, 
					!Validaciones.isNullOrEmpty(detalle.getFechaDesde())?detalle.getFechaDesde():"00000000"
					));
			listaCamposAEnviar.add(new CampoAEnviarJMS("fechaHasta", null, null,
					!Validaciones.isNullOrEmpty(detalle.getFechaHasta())?detalle.getFechaHasta():"00000000"
					));
			listaCamposAEnviar.add(new CampoAEnviarJMS("calcularFechaHasta", null, null,
					detalle.getCalcularFechaHasta()!=null ? detalle.getCalcularFechaHasta().getDescripcionCorta():""));
			listaCamposAEnviar.add(new CampoAEnviarJMS("queHacerConLosIntereses", null, null,
					detalle.getQueHacerConLosIntereses()!=null ? detalle.getQueHacerConLosIntereses().name():""));
			String importeBonificacionInteresesNoReguladoFormateado = Utilidad.formatCurrencySacarPunto(Utilidad.formatCurrencyBDAgregandoDecimales(detalle.getImporteBonificacionInteresesNoRegulado(), 2).toString());
			listaCamposAEnviar.add(new CampoAEnviarJMS("importeBonificacionInteresesNoRegulado", null, null, importeBonificacionInteresesNoReguladoFormateado));
			String importeBonificacionInteresesReguladoFormateado = Utilidad.formatCurrencySacarPunto(Utilidad.formatCurrencyBDAgregandoDecimales(detalle.getImporteBonificacionInteresesRegulado(), 2).toString());
			listaCamposAEnviar.add(new CampoAEnviarJMS("importeBonificacionInteresesRegulado", null, null, importeBonificacionInteresesReguladoFormateado));
			listaCamposAEnviar.add(new CampoAEnviarJMS("tipoCliente", null, null,
					detalle.getTipoCliente()!=null ? detalle.getTipoCliente().codigo():""));
			listaCamposAEnviar.add(new CampoAEnviarJMS("tipoIva", null, null,
					detalle.getTipoIva()!=null ? detalle.getTipoIva().name():""));
			listaCamposAEnviar.add(new CampoAEnviarJMS("origenCargo", null, null,
					detalle.getOrigenCargo()!=null ? detalle.getOrigenCargo().codigo():""));
			listaCamposAEnviar.add(new CampoAEnviarJMS("leyendaFacturaCargo", null, null, detalle.getLeyendaFacturaCargo()));
			listaCamposAEnviar.add(new CampoAEnviarJMS("leyendaFacturaInteres", null, null, detalle.getLeyendaFacturaInteres()));
				
		}	
		
		// Si es imputacion sete este servicio
		String servicio = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.cargo.servicio");
		// Si es simulacion uso el servicio nuevo
		if (!Validaciones.isObjectNull(datosAEnviar.getModoEjecucion())) {
			if (SiNoEnum.SI.equals(datosAEnviar.getModoEjecucion())) {
				servicio = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.simulacion.cargo.servicio");
			}
		}		
		String mensaje = JmsUtilidad.generarMensaje(
				getDefaultFormatoMensajeJMS(), listaCamposAEnviar, servicio);
		
		JmsUtilidad.verificarLongitudTotalMensaje(getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraSerializable(),
				getDefaultFormatoMensajeJMS().getLontitudFijaMensajeSerializable(), mensaje);
	    
		return mensaje;
	}

	@Override
	public boolean verificarLongitudMsjRecibida(String msj, boolean esSincronico) throws NegocioExcepcion {
		throw new NegocioExcepcion("Se debe utilizar el mapeador MicRecepcionMapeador para deserializar");
	}
	
	@Override
	public JMS deserializar(String mensajeRecibido, boolean camposSeteables, boolean esSincronico) throws NegocioExcepcion {
		throw new NegocioExcepcion("Se debe utilizar el mapeador MicRecepcionMapeador para deserializar");
	}

	@Override
	public boolean verificarServicio(String msj, boolean esSincronico) throws NegocioExcepcion {
		throw new NegocioExcepcion("Se debe utilizar el mapeador MicRecepcionMapeador para deserializar");
	}
}
