package ar.com.telecom.shiva.base.jms.mapeos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaCreditoEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaCreditoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicCredito;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.jms.util.definicion.Campo;
import ar.com.telecom.shiva.base.jms.util.definicion.CampoAEnviarJMS;
import ar.com.telecom.shiva.base.jms.util.definicion.TipoDatoEnum;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class MicConsultaCreditoMapeador 
	extends MapeadorJMS {

	@Override
	public String serializar(JMS jms, boolean esSincronico) throws NegocioExcepcion {
		MicConsultaCreditoEntrada datosAEnviar = (MicConsultaCreditoEntrada) jms;
		
		List<CampoAEnviarJMS> listaCamposAEnviar = new ArrayList<CampoAEnviarJMS>();
		
		int j = 1;
		for (BigInteger codigoCliente: datosAEnviar.getListaClientes()) 
		{
			Integer contador = Integer.valueOf(j);
			listaCamposAEnviar.add(new CampoAEnviarJMS("listaClientes", "cliente", contador, codigoCliente.toString()));
			j++;
		}
		
		//Informacion de facturas
		listaCamposAEnviar.add(new CampoAEnviarJMS("tipoMedioPago", null, null, 
				datosAEnviar.getTipoMedioPago()!=null ? String.valueOf(datosAEnviar.getTipoMedioPago()) : ""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("fechaDesde", null, null, 
				Utilidad.formatDateAAAAMMDD(datosAEnviar.getFechaDesde())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("fechaHasta", null, null, 
				Utilidad.formatDateAAAAMMDD(datosAEnviar.getFechaHasta())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("acuerdo", null, null,
				datosAEnviar.getAcuerdo()!=null ? String.valueOf(datosAEnviar.getAcuerdo()) : ""));
		
		//Informacion para paginados
		listaCamposAEnviar.add(new CampoAEnviarJMS("cantidadRegistrosARetornar", null, null,
					datosAEnviar.getInformacionPaginado().getCantidadRegistrosARetornar()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getCantidadRegistrosARetornar()):""));
		//
		listaCamposAEnviar.add(new CampoAEnviarJMS("codigoCliente", null, null,
				datosAEnviar.getInformacionPaginado().getUltimoCodigoClienteRemanente()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimoCodigoClienteRemanente()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("cuenta", null, null,
				datosAEnviar.getInformacionPaginado().getUltimaCuentaRemanente()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimaCuentaRemanente()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("codigoTipoRemanente", null, null,
				datosAEnviar.getInformacionPaginado().getUltimoCodigoTipoRemanente()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimoCodigoTipoRemanente()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimaFechaRemanente", null, null,
				Utilidad.formatDateAAAAMMDD(datosAEnviar.getInformacionPaginado().getUltimaFechaRemanente())));
		//
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimoNumeroReferenciaMicNC", null, null,
				datosAEnviar.getInformacionPaginado().getUltimoNumeroReferenciaMicNC()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimoNumeroReferenciaMicNC()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimaFechaNotaCredito", null, null,
				Utilidad.formatDateAAAAMMDD(datosAEnviar.getInformacionPaginado().getUltimaFechaNotaCredito())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimoClienteNotaCredito", null, null,
				datosAEnviar.getInformacionPaginado().getUltimoClienteNotaCredito()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimoClienteNotaCredito()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimaCuentaNotaCredito", null, null,
				datosAEnviar.getInformacionPaginado().getUltimaCuentaNotaCredito()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimaCuentaNotaCredito()):""));
		
		String datosCabecera = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.consulta.credito.servicio");
		
		String mensaje = JmsUtilidad.generarMensaje(
				getDefaultFormatoMensajeJMS(), listaCamposAEnviar, datosCabecera);
		
		JmsUtilidad.verificarLongitudTotalMensaje(getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraSerializable(),
				getDefaultFormatoMensajeJMS().getLontitudFijaMensajeSerializable(), mensaje);
	    
		return mensaje;
	}

	@Override
	public boolean verificarLongitudMsjRecibida(String msj, boolean esSincronico)
			throws NegocioExcepcion {
		
		int total = getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync() 
				+ getDefaultFormatoMensajeJMS().getLontitudFijaMensajeDeserializable();
		
		if (total != msj.length()) {
	    	return false;
	    } else {
	    	return true;
	    } 
		
	}
	
	@Override
	public JMS deserializar(String mensajeRecibido, boolean camposSeteables, boolean esSincronico) throws NegocioExcepcion {
		
		JmsUtilidad.verificarLongitudTotalMensaje(
				getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync(), 
				getDefaultFormatoMensajeJMS().getLontitudFijaMensajeDeserializable(), 
				mensajeRecibido);
		
		
		//Sacar la cabecera del mensaje
		String msg = mensajeRecibido;
		if (getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync() > 0) {
			msg = mensajeRecibido.substring(getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync(), msg.length());
		}
		Traza.auditoria(getClass(), "Mensaje sin cabecera: " + msg);
		
		MicConsultaCreditoSalida datosRecibidos = new MicConsultaCreditoSalida();
		
		List<Campo> camposEntrada = getDefaultFormatoMensajeJMS().getCamposDeserializableOrdenada();
		int posInicial = 0;
		int posFinal = 0;
				
	    for (Campo campo: camposEntrada){
	    	String nombreCampo = campo.getNombre();
	    	int longitud = campo.getLongitud();
	    	
	    	if (campo.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
	    		if ("cantidadRegistrosRetornados".equalsIgnoreCase(nombreCampo)) {
	    			try {
	    				posFinal = posInicial + longitud;
	    		    	Long valor=Long.valueOf(msg.substring(posInicial, posFinal));
		    			datosRecibidos.getControlPaginado().setCantidadRegistrosRetornados(valor);
		    			posInicial += longitud;
	    		    } catch(Exception e){
	    				String desc = "Error en el campo cantidadRegistrosRetornados";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}
	    		
	    		if ("cantidadRegistrosTotales".equalsIgnoreCase(nombreCampo)) {
	    			try {
	    				posFinal = posInicial + longitud;
	    		    	Long valor=Long.valueOf(msg.substring(posInicial, posFinal));
	    		    	datosRecibidos.getControlPaginado().setCantidadRegistrosTotales(valor);
		    			posInicial += longitud;
	    		    } catch(Exception e){
	    				String desc = "Error en el campo cantidadRegistrosTotales";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}
	    	} 
	    	
	    	if (campo.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
	    		if ("resultadoConsulta".equalsIgnoreCase(nombreCampo)) {
	    			try {
	    				posFinal = posInicial + longitud;
	    		    	String valor = msg.substring(posInicial, posFinal);
	    		    	OkNokEnum enumerador = OkNokEnum.getEnumByDescripcion(valor.trim()); 
	    		    	datosRecibidos.getResultadoConsulta().setResultadoConsulta(enumerador);
		    			posInicial += longitud;
	    		    } catch(Exception e){
	    				String desc = "Error en el campo resultadoConsulta";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}
	    		if ("codigoError".equalsIgnoreCase(nombreCampo)) {
	    			try {
	    				posFinal = posInicial + longitud;
	    		    	String valor = msg.substring(posInicial, posFinal);
	    		    	datosRecibidos.getResultadoConsulta().setCodigoError(valor.trim());
		    			posInicial += longitud;
	    		    } catch(Exception e){
	    				String desc = "Error en el campo codigoError";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}
	    		if ("descripcionError".equalsIgnoreCase(nombreCampo)) {
	    			try {
	    				posFinal = posInicial + longitud;
	    		    	String valor = msg.substring(posInicial, posFinal);
	    		    	datosRecibidos.getResultadoConsulta().setDescripcionError(valor.trim());
		    			posInicial += longitud;
	    		    } catch(Exception e){
	    				String desc = "Error en el campo descripcionError";
	    				Traza.error(getClass(), desc, e);
	    				throw new NegocioExcepcion(desc,e);
	    			}
	    		}	
	    	}
	    	
			if (campo.getTipoDato().equals(TipoDatoEnum.AGRUPADOR)) {
				if ("listaCreditos".equalsIgnoreCase(nombreCampo)) {
										
					for (int contador=0; campo.getLongitud() > contador ; contador++) {
						MicCredito credito = new MicCredito();
						
						for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
	    					String nombreCampoAgrupador = campoAgrupador.getNombre();
	    					int longitudAgrupador = campoAgrupador.getLongitud();
	    					
	    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
	    						if ("tipoCredito".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	TipoCreditoEnum enumerador = TipoCreditoEnum.getEnumByValor(valor.trim());
	    			    		    	credito.setTipoCredito(enumerador);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo tipoCredito";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("tipoComprobante".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	TipoComprobanteEnum enumerador = TipoComprobanteEnum.getEnumByName(valor.trim());
	    			    		    	credito.getInformacionNotaCredito().setTipoComprobante(enumerador);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo tipoComprobante";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("claseComprobante".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	TipoClaseComprobanteEnum enumerador = TipoClaseComprobanteEnum.getEnumByName(valor.trim());
	    			    		    	credito.getInformacionNotaCredito().setClaseComprobante(enumerador);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo claseComprobante";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("sucursalComprobante".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionNotaCredito().setSucursalComprobante(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo sucursalComprobante";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("numeroComprobante".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionNotaCredito().setNumeroComprobante(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo numeroComprobante";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("descripcionTipoRemanente".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionRemanente().setDescripcionTipoRemanente(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo descripcionTipoRemanente";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("codigoMarcaReclamo".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionGeneral().setCodigoMarcaReclamo(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoMarcaReclamo";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("descripcionMarcaReclamo".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionGeneral().setDescripcionMarcaReclamo(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo descripcionMarcaReclamo";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("codigoMarcaCyQ".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionGeneral().setCodigoMarcaCyQ(valor); //--> no ponemos un trim
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoMarcaCyQ";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("descripcionMarcaCyQ".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionGeneral().setDescripcionMarcaCyQ(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo descripcionMarcaCyQ";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("codigoEstadoCredito".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionGeneral().setCodigoEstadoCredito(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoEstadoCredito";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("descripcionEstadoCredito".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionGeneral().setDescripcionEstadoCredito(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo descripcionEstadoCredito";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						
	    						if ("razonSocialCliente".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionAdicionalTagetikNotaCreditoMic().setRazonSocialCliente(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo razonSocialCliente";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("tipoCliente".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionAdicionalTagetikNotaCreditoMic().setTipoCliente(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo tipoCliente";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("cuit".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionAdicionalTagetikNotaCreditoMic().setCuit(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo cuit";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("marketingCustomerGroup".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionAdicionalTagetikNotaCreditoMic().setMarketingCustomerGroup(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo marketingCustomerGroup";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("descripcionTipoFactura".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionAdicionalTagetikNotaCreditoMic().setDescripcionTipoFactura(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo descripcionTipoFactura";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("indicadorPeticionCorte".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionAdicionalDacota().setIndicadorPeticionCorte(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo indicadorPeticionCorte";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("codigoTarifa".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	credito.getInformacionAdicionalDacota().setCodigoTarifa(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoTarifa";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    			    	}
	    					
	    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
	    						if ("codigoCliente".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	BigInteger valor = new BigInteger(msg.substring(posInicial, posFinal));
	    				    			credito.setCodigoCliente(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoCliente";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("cuenta".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Integer valor = new Integer(msg.substring(posInicial, posFinal));
	    				    			credito.setCuenta(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo cuenta";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("numeroReferenciaMIC".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				Long valor = new Long(msg.substring(posInicial, posFinal));
	    			    		    	credito.getInformacionNotaCredito().setNumeroReferenciaMic(valor.toString());
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo numeroReferenciaMIC";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("codigoTipoRemanente".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    								posFinal = posInicial + longitudAgrupador;
	    			    		    	Integer valor = new Integer(msg.substring(posInicial, posFinal));
	    			    		    	credito.getInformacionRemanente().setCodigoTipoRemanente(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoTipoRemanente";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("importe".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    		    					credito.getInformacionGeneral().setImporte(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo importe";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("saldo".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    		    					credito.getInformacionGeneral().setSaldo(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo saldo";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("fechaAlta".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Date valor=Utilidad.deserializeAndFormatDate(msg.substring(posInicial, posFinal));
	    			    		    	credito.getInformacionGeneral().setFechaAlta(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo fechaAlta";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("fechaEmision".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Date valor=Utilidad.deserializeAndFormatDate(msg.substring(posInicial, posFinal));
	    			    		    	credito.getInformacionGeneral().setFechaEmision(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo fechaEmision";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("fechaVencimiento".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Date valor=Utilidad.deserializeAndFormatDate(msg.substring(posInicial, posFinal));
	    			    		    	credito.getInformacionGeneral().setFechaVencimiento(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo fechaVencimiento";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("fechaUltimoMovimientoCobroPP".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Date valor=Utilidad.deserializeAndFormatDate(msg.substring(posInicial, posFinal));
	    			    		    	credito.getInformacionGeneral().setFechaUltimoMovimientoCobroPP(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo fechaUltimoMovimientoCobroPP";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("cicloFacturacion".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				Integer valor = new Integer(msg.substring(posInicial, posFinal));
	    			    				credito.getInformacionAdicionalTagetikNotaCreditoMic().setCicloFacturacion(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo cicloFacturacion";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("tipoFactura".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				Integer valor = new Integer(msg.substring(posInicial, posFinal));
	    			    				credito.getInformacionAdicionalTagetikNotaCreditoMic().setTipoFactura(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo tipoFactura";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("importeOriginal".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    			    				credito.getInformacionAdicionalTagetikNotaCreditoMic().setImporteOriginal(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo importeOriginal";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("fechaVencimientoMora".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Date valor=Utilidad.deserializeAndFormatDate(msg.substring(posInicial, posFinal));
	    			    		    	credito.getInformacionAdicionalDacota().setFechaVencimientoMora(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo fechaVencimientoMora";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    			    	}
	    				} // Fin - for
	    				
						//Por estar vacio no se agrega credito
						if (!BigInteger.ZERO.equals(credito.getCodigoCliente())) {
							datosRecibidos.getListaCreditos().add(credito);
						}
	    				
					} // fin - if longitud
    				
				}
    		}
		}
		return datosRecibidos;
	}

	@Override
	public boolean verificarServicio(String msj, boolean esSincronico) throws NegocioExcepcion {
		//Si quiero validar el servicio en la cabecera, lo hago
		if (getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync() > 0) {
			String servicio = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.consulta.credito.servicio");
			String msg = msj.substring(0, getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync());
			if (servicio.equalsIgnoreCase(msg.trim())) {
				return true;
			} else {
				return false;
			}
		}
				
		return true;
	}
}
