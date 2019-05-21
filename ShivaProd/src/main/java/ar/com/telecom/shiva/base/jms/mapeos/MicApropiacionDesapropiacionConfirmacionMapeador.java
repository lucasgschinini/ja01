package ar.com.telecom.shiva.base.jms.mapeos;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicApropiacionDesapropiacionConfirmacionEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleMedioPago;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.jms.util.definicion.CampoAEnviarJMS;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class MicApropiacionDesapropiacionConfirmacionMapeador extends MapeadorJMS {

	@Override
	public String serializar(JMS jms, boolean esSincronico) throws NegocioExcepcion {
		MicApropiacionDesapropiacionConfirmacionEntrada datosAEnviar = (MicApropiacionDesapropiacionConfirmacionEntrada) jms;
		
		List<CampoAEnviarJMS> listaCamposAEnviar = new ArrayList<CampoAEnviarJMS>();
		listaCamposAEnviar.add(new CampoAEnviarJMS("tipoInvocacion", null, null, String.valueOf(datosAEnviar.getTipoInvocacion().getValor())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("modoEjecucion", null, null,String.valueOf(datosAEnviar.getModoEjecucion().getDescripcionCorta())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("idOperacion", null, null, String.valueOf(datosAEnviar.getIdOperacion())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("idSecuencia", null, null, 
				datosAEnviar.getIdSecuencia()!=null?String.valueOf(datosAEnviar.getIdSecuencia()):null));
		listaCamposAEnviar.add(new CampoAEnviarJMS("usuarioCobrador", null, null, String.valueOf(datosAEnviar.getUsuarioCobrador())));
		
		if(!Validaciones.isObjectNull(datosAEnviar.getDetalleFactura())){
			//Detalle Factura
			if(TipoInvocacionEnum.$01.equals(datosAEnviar.getTipoInvocacion())
					|| TipoInvocacionEnum.$03.equals(datosAEnviar.getTipoInvocacion())) 
			{
				
				listaCamposAEnviar.add(new CampoAEnviarJMS("tipoOperacion", null, null,
						datosAEnviar.getDetalleFactura().getTipoOperacion()!=null ? String.valueOf(datosAEnviar.getDetalleFactura().getTipoOperacion().getDescripcionCorta()):""));
	//			sprint 5
				listaCamposAEnviar.add(new CampoAEnviarJMS("tipoDocumento", null, null,
						datosAEnviar.getDetalleFactura().getTipoDocumento()!=null ? String.valueOf(datosAEnviar.getDetalleFactura().getTipoDocumento().codigo()):""));

				// Este campo envia el numero de referencia de un documento MIC, que posee formato numerico (el cual debemos completar con 0 a la izquierda si correspondiera)
				// Tambien se usa para enviar el ID de referencia de un documento de tipo DUC, que tiene formato U723-00000031, el cuál debemos tratar como texto y alinearlo a la izquierda
				listaCamposAEnviar.add(new CampoAEnviarJMS("referenciaFactura", null, null, 
						(Validaciones.isNumeric(datosAEnviar.getDetalleFactura().getReferenciaFactura()) ? 
								Utilidad.rellenarCerosIzquierda(datosAEnviar.getDetalleFactura().getReferenciaFactura(), 15) : datosAEnviar.getDetalleFactura().getReferenciaFactura())) 
				);
				listaCamposAEnviar.add(new CampoAEnviarJMS("fechaValor", null, null, datosAEnviar.getDetalleFactura().getFechaValor()));
				listaCamposAEnviar.add(new CampoAEnviarJMS("acuerdoFacturacionIntereses", null, null, datosAEnviar.getDetalleFactura().getAcuerdoFacturacionIntereses()));
				
				String importeFormateado = Utilidad.formatCurrencySacarPunto(Utilidad.formatCurrencyBDAgregandoDecimales(datosAEnviar.getDetalleFactura().getImporte(), 2).toString());
				listaCamposAEnviar.add(new CampoAEnviarJMS("importe", null, null, importeFormateado));
				listaCamposAEnviar.add(new CampoAEnviarJMS("queHacerConLosIntereses", null, null,
						datosAEnviar.getDetalleFactura().getQueHacerConLosIntereses()!=null ? String.valueOf(datosAEnviar.getDetalleFactura().getQueHacerConLosIntereses()):""));
//				sprint 5 cambio de nombre y tipo de dato
				String importeBonificacionInteresesFormateado = Utilidad.formatCurrencySacarPunto(Utilidad.formatCurrencyBDAgregandoDecimales(datosAEnviar.getDetalleFactura().getImporteBonificacionIntereses(), 2).toString());
				listaCamposAEnviar.add(new CampoAEnviarJMS("importeBonificacionIntereses", null, null, importeBonificacionInteresesFormateado));
				listaCamposAEnviar.add(new CampoAEnviarJMS("queHacerConLosTerceros", null, null,
						datosAEnviar.getDetalleFactura().getQueHacerConLosTerceros()!=null?String.valueOf(datosAEnviar.getDetalleFactura().getQueHacerConLosTerceros().getDescripcionCorta()):""));
//				sprint 5 				
				String montoAcumuladoSimulacionFormateado = Utilidad.formatCurrencySacarPunto(Utilidad.formatCurrencyBDAgregandoDecimales(datosAEnviar.getDetalleFactura().getMontoAcumuladoSimulacion(), 2).toString());
				listaCamposAEnviar.add(new CampoAEnviarJMS("montoAcumuladoSimulacion", null, null, montoAcumuladoSimulacionFormateado));
//				sprint 5
				listaCamposAEnviar.add(new CampoAEnviarJMS("fechaCobranzaAcumuladaSimulacion", null, null, datosAEnviar.getDetalleFactura().getFechaCobranzaAcumuladaSimulacion()));
				
			} else {
				listaCamposAEnviar.add(new CampoAEnviarJMS("fechaValor", null, null, datosAEnviar.getDetalleFactura().getFechaValor()));
			}
		}	
		//Detalle Medio Pago Agrupador
		if(TipoInvocacionEnum.$02.equals(datosAEnviar.getTipoInvocacion())
				|| TipoInvocacionEnum.$03.equals(datosAEnviar.getTipoInvocacion())) {
		
			int j = 1;
			for (MicDetalleMedioPago mp: datosAEnviar.getListaMediosPago()) 
			{
				Integer contador = Integer.valueOf(j);
				listaCamposAEnviar.add(new CampoAEnviarJMS("detalleMedioPago", "tipoMedioPago", contador, 
						mp.getTipoMedioPago()!=null?String.valueOf(mp.getTipoMedioPago().name()):""));
				String importeFormateado = 
						Utilidad.formatCurrencySacarPunto(Utilidad.formatCurrencyBDAgregandoDecimales(mp.getImporteMedioPago(), 2).toString());
				listaCamposAEnviar.add(new CampoAEnviarJMS("detalleMedioPago", "importeMedioPago", contador, importeFormateado));
				listaCamposAEnviar.add(new CampoAEnviarJMS("detalleMedioPago", "cuentaRemanente", contador, 
						String.valueOf(mp.getCuentaRemanente())));
				listaCamposAEnviar.add(new CampoAEnviarJMS("detalleMedioPago", "tipoRemanente", contador,
						mp.getTipoRemanente()!=null?String.valueOf(mp.getTipoRemanente().codigo()):""));
				listaCamposAEnviar.add(new CampoAEnviarJMS("detalleMedioPago", "numeroNC" , contador, 
						String.valueOf(mp.getNumeroNC())));
//				sprint 5 
				String montoAcumuladoSimulacionFormateado = Utilidad.formatCurrencySacarPunto(Utilidad.formatCurrencyBDAgregandoDecimales(datosAEnviar.getDetalleFactura().getMontoAcumuladoSimulacion(), 2).toString());
				listaCamposAEnviar.add(new CampoAEnviarJMS("detalleMedioPago", "montoAcumuladoSimulacion", contador, montoAcumuladoSimulacionFormateado));

				j++;
			}
		}
		
		// Si es imputacion sete este servicio
		String servicio = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.imputacion.adc.servicio");
		// Si es simulacion uso el servicio nuevo
		if (!Validaciones.isObjectNull(datosAEnviar.getModoEjecucion())) {
			if (SiNoEnum.SI.equals(datosAEnviar.getModoEjecucion())) {
				servicio = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.simulacion.adc.servicio");
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
