package ar.com.telecom.shiva.base.jms.mapeos;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicDescobroEntrada;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.jms.util.definicion.CampoAEnviarJMS;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class MicDescobroMapeador extends MapeadorJMS {

	@Override
	public String serializar(JMS jms, boolean esSincronico) throws NegocioExcepcion {
		MicDescobroEntrada datosAEnviar = (MicDescobroEntrada) jms;
		
		List<CampoAEnviarJMS> listaCamposAEnviar = new ArrayList<CampoAEnviarJMS>();
		listaCamposAEnviar.add(new CampoAEnviarJMS("tipoInvocacion", null, null, String.valueOf(datosAEnviar.getTipoInvocacion().getValor())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("modoEjecucion", null, null,String.valueOf(datosAEnviar.getModoEjecucion().getDescripcionCorta())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("idOperacion", null, null, String.valueOf(datosAEnviar.getIdOperacion())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("idSecuencia", null, null, 
				datosAEnviar.getIdSecuencia()!=null?String.valueOf(datosAEnviar.getIdSecuencia()):null));
		listaCamposAEnviar.add(new CampoAEnviarJMS("usuarioCobrador", null, null, String.valueOf(datosAEnviar.getUsuarioCobrador())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("facturarContracargoFactura", null, null, String.valueOf(datosAEnviar.getFacturarContracargoFactura())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("acuerdoFacturacionContracargoFactura", null, null,
				datosAEnviar.getAcuerdoFacturacionContracargoFactura()!=null ? String.valueOf(datosAEnviar.getAcuerdoFacturacionContracargoFactura()) : ""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("facturarContracargoCargo", null, null, String.valueOf(datosAEnviar.getFacturarContracargoCargo())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("acuerdoFacturacionContracargoCargo", null, null,
				datosAEnviar.getAcuerdoFacturacionContracargoCargo()!=null ? String.valueOf(datosAEnviar.getAcuerdoFacturacionContracargoCargo()) : ""));

		// Si es imputacion sete este servicio
		String datosCabecera = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.descobro.servicio");
		
		// Si es simulacion uso el servicio nuevo
		if (!Validaciones.isObjectNull(datosAEnviar.getModoEjecucion())) {
			if (SiNoEnum.SI.equals(datosAEnviar.getModoEjecucion())) {
				datosCabecera = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.simulacion.descobro.servicio");
			}
		}
		
		String mensaje = JmsUtilidad.generarMensaje(
				getDefaultFormatoMensajeJMS(), listaCamposAEnviar, datosCabecera);
		
		JmsUtilidad.verificarLongitudTotalMensaje(getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraSerializable(),
				getDefaultFormatoMensajeJMS().getLontitudFijaMensajeSerializable(), mensaje);
	    
		return mensaje;
	}

	@Override
	public boolean verificarLongitudMsjRecibida(String msj, boolean esSincronico) throws NegocioExcepcion {
		throw new NegocioExcepcion("Se debe utilizar el mapeador MicRespuestaRecepcionMapeador para deserializar");
	}
	
	@Override
	public JMS deserializar(String mensajeRecibido, boolean camposSeteables, boolean esSincronico) throws NegocioExcepcion {
		throw new NegocioExcepcion("Se debe utilizar el mapeador MicRespuestaRecepcionMapeador para deserializar");
	}

	@Override
	public boolean verificarServicio(String msj, boolean esSincronico) throws NegocioExcepcion {
		throw new NegocioExcepcion("Se debe utilizar el mapeador MicRespuestaRecepcionMapeador para deserializar");
	}
}
