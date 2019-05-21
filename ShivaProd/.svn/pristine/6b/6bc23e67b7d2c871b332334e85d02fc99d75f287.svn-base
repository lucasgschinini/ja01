package ar.com.telecom.shiva.base.jms.mapeos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaDeudaSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDebito;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.jms.util.definicion.Campo;
import ar.com.telecom.shiva.base.jms.util.definicion.CampoAEnviarJMS;
import ar.com.telecom.shiva.base.jms.util.definicion.TipoDatoEnum;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class MicConsultaDeudaMapeador 
	extends MapeadorJMS {

	@Override
	public String serializar(JMS jms, boolean esSincronico) throws NegocioExcepcion {
		MicConsultaDeudaEntrada datosAEnviar = (MicConsultaDeudaEntrada) jms;
		
		List<CampoAEnviarJMS> listaCamposAEnviar = new ArrayList<CampoAEnviarJMS>();
		
		int j = 1;
		for (BigInteger codigoCliente: datosAEnviar.getListaClientes()) 
		{
			Integer contador = Integer.valueOf(j);
			listaCamposAEnviar.add(new CampoAEnviarJMS("listaClientes", "cliente", contador, codigoCliente.toString()));
			j++;
		}
		
		//Informacion de facturas
		listaCamposAEnviar.add(new CampoAEnviarJMS("tipoComprobante", null, null, 
				datosAEnviar.getInformacionFactura().getTipoComprobante()!=null ? String.valueOf(datosAEnviar.getInformacionFactura().getTipoComprobante().getValor()) : ""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("claseComprobante", null, null, 
				datosAEnviar.getInformacionFactura().getClaseComprobante()!=null ? String.valueOf(datosAEnviar.getInformacionFactura().getClaseComprobante()) : ""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("sucursalComprobante", null, null, datosAEnviar.getInformacionFactura().getSucursalComprobante()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("numeroComprobante", null, null, datosAEnviar.getInformacionFactura().getNumeroComprobante()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("fechaVencimientoDesde", null, null, 
				Utilidad.formatDateAAAAMMDD(datosAEnviar.getInformacionFactura().getFechaVencimientoDesde())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("fechaVencimientoHasta", null, null, 
				Utilidad.formatDateAAAAMMDD(datosAEnviar.getInformacionFactura().getFechaVencimientoHasta())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("numeroReferenciaMIC", null, null,
				datosAEnviar.getInformacionFactura().getNumeroReferenciaMICFormateado()!=null ? datosAEnviar.getInformacionFactura().getNumeroReferenciaMICFormateado() : "")); 
		listaCamposAEnviar.add(new CampoAEnviarJMS("acuerdo", null, null,
				datosAEnviar.getInformacionFactura().getAcuerdo()!=null ? String.valueOf(datosAEnviar.getInformacionFactura().getAcuerdo()) : ""));
		
		//Informacion de convenios
		listaCamposAEnviar.add(new CampoAEnviarJMS("secuencialDeCuenta", null, null,
				datosAEnviar.getInformacionConvenios().getSecuencialDeCuenta()!=null ? String.valueOf(datosAEnviar.getInformacionConvenios().getSecuencialDeCuenta()) : ""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("secuencialDeConvenio", null, null,
				datosAEnviar.getInformacionConvenios().getSecuencialDeConvenio()!=null ? String.valueOf(datosAEnviar.getInformacionConvenios().getSecuencialDeConvenio()) : ""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("numeroDeCuota", null, null,
				datosAEnviar.getInformacionConvenios().getNumeroDeCuota()!=null ? String.valueOf(datosAEnviar.getInformacionConvenios().getNumeroDeCuota()) : ""));
		
		//Informacion para paginados
		listaCamposAEnviar.add(new CampoAEnviarJMS("cantidadRegistrosARetornar", null, null,
					datosAEnviar.getInformacionPaginado().getCantidadRegistrosARetornar()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getCantidadRegistrosARetornar()):""));
		//
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimoNumeroDUC", null, null, datosAEnviar.getInformacionPaginado().getUltimoNumeroDUC()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimaFechaDUC", null, null,
				Utilidad.formatDateAAAAMMDD(datosAEnviar.getInformacionPaginado().getUltimaFechaDUC())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimoClienteDUC", null, null,
				datosAEnviar.getInformacionPaginado().getUltimoClienteDUC()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimoClienteDUC()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimaCuentaDUC", null, null,
				datosAEnviar.getInformacionPaginado().getUltimaCuentaDUC()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimaCuentaDUC()):""));
		//
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimoNumeroReferenciaMIC", null, null,
				datosAEnviar.getInformacionPaginado().getUltimoNumeroReferenciaMIC()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimoNumeroReferenciaMIC()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimaFechaFactura", null, null,
				Utilidad.formatDateAAAAMMDD(datosAEnviar.getInformacionPaginado().getUltimaFechaFactura())));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimoClienteFactura", null, null,
				datosAEnviar.getInformacionPaginado().getUltimoClienteFactura()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimoClienteFactura()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimaCuentaFactura", null, null,
				datosAEnviar.getInformacionPaginado().getUltimaCuentaFactura()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimaCuentaFactura()):""));
		//
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimaSecuencialCuenta", null, null,
				datosAEnviar.getInformacionPaginado().getUltimaSecuencialCuenta()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimaSecuencialCuenta()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimaSecuencialConvenio", null, null,
				datosAEnviar.getInformacionPaginado().getUltimaSecuencialConvenio()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimaSecuencialConvenio()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimoNumeroCuota", null, null,
				datosAEnviar.getInformacionPaginado().getUltimoNumeroCuota()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimoNumeroCuota()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimoClienteConvenio", null, null,
				datosAEnviar.getInformacionPaginado().getUltimoClienteConvenio()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimoClienteConvenio()):""));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ultimaCuentaConvenio", null, null,
				datosAEnviar.getInformacionPaginado().getUltimaCuentaConvenio()!=null ? String.valueOf(datosAEnviar.getInformacionPaginado().getUltimaCuentaConvenio()):""));
		
		
		String datosCabecera = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.consulta.deuda.servicio");
		
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
		
		
		//Sacar la cabecera del mensaje (201)
		String msg = mensajeRecibido;
		if (getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync() > 0) {
			msg = mensajeRecibido.substring(getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync(), msg.length());
		}
		Traza.auditoria(getClass(), "Mensaje sin cabecera: " + msg);
		
		MicConsultaDeudaSalida datosRecibidos = new MicConsultaDeudaSalida();
		
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
				
				if ("listaDebitos".equalsIgnoreCase(nombreCampo)) {
					
					for (int contador=0; campo.getLongitud() > contador ; contador++) {
						MicDebito debito = new MicDebito();
						
						for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
	    					String nombreCampoAgrupador = campoAgrupador.getNombre();
	    					int longitudAgrupador = campoAgrupador.getLongitud();
	    					
	    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
	    						
	    						if ("tipoComprobante".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	TipoComprobanteEnum enumerador = TipoComprobanteEnum.getEnumByName(valor.trim());
	    			    		    	debito.setTipoComprobante(enumerador);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo tipoComprobante";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("descripcionTipoDuc".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	debito.setDescripcionTipoDuc(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo descripcionTipoDuc";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("codigoEstadoDuc".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	debito.setCodigoEstadoDuc(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoEstadoDuc";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("descripcionEstadoDuc".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	debito.setDescripcionEstadoDuc(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo descripcionEstadoDuc";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("acuerdo".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	debito.setAcuerdo(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo acuerdo";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("claseComprobante".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	TipoClaseComprobanteEnum enumerador = TipoClaseComprobanteEnum.getEnumByName(valor.trim());
	    			    		    	debito.setClaseComprobante(enumerador);
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
	    			    		    	debito.setSucursalComprobante(valor.trim());
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
	    			    		    	debito.setNumeroComprobante(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo numeroComprobante";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("descripcionEstadoAcuerdoFacturacion".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	debito.setDescripcionEstadoAcuerdoFacturacion(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo descripcionEstadoAcuerdoFacturacion";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("estadoConceptosTerceros".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	EstadoConceptoTercerosEnum enumerador = EstadoConceptoTercerosEnum.getEnumByCodigoMic(valor.trim());
	    			    		    	debito.setEstadoConceptosTerceros(enumerador);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo estadoConceptosTerceros";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("posibilidadDestransferencia".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	SiNoEnum enumerador = SiNoEnum.getEnumByDescripcionCorta(valor.trim());
	    			    		    	debito.setPosibilidadDestransferencia(enumerador);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo posibilidadDestransferencia";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("codigoEstadoFactura".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	debito.setCodigoEstadoFactura(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoEstadoFactura";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("descripcionEstadoFactura".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	debito.setDescripcionEstadoFactura(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo descripcionoEstadoFactura";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("codigoMarcaReclamo".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	debito.setCodigoMarcaReclamo(valor.trim());
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
	    			    		    	debito.setDescripcionMarcaReclamo(valor.trim());
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
	    			    		    	debito.setCodigoMarcaCyQ(valor); //--> no ponemos un trim
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
	    			    		    	debito.setDescripcionMarcaCyQ(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo descripcionMarcaCyQ";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("razonSocialCliente".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	String valor=msg.substring(posInicial, posFinal);
	    			    		    	debito.getInformacionAdicionalTagetik().setRazonSocialCliente(valor.trim());
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
	    			    		    	debito.getInformacionAdicionalTagetik().setTipoCliente(valor.trim());
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
	    			    		    	debito.getInformacionAdicionalTagetik().setCuit(valor.trim());
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
	    			    		    	debito.getInformacionAdicionalTagetik().setMarketingCustomerGroup(valor.trim());
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
	    			    		    	debito.getInformacionAdicionalTagetik().setDescripcionTipoFactura(valor.trim());
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
	    			    		    	debito.getInformacionAdicionalDacota().setIndicadorPeticionCorte(valor.trim());
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
	    			    		    	debito.getInformacionAdicionalDacota().setCodigoTarifa(valor.trim());
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoTarifa";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("numeroReferenciaMIC".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor=msg.substring(posInicial, posFinal);

	    			    				// Hacemos un .trim() para eliminar los espacios al final en caso de que retorne referencia de DUC 'U723-00000003  '
	    			    				debito.setNumeroReferenciaMic(valor.trim());  

	    			    				posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo numeroReferenciaMIC";
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
	    				    			debito.setCodigoCliente(valor);
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
	    				    			debito.setCuenta(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo cuenta";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("codigoTipoDuc".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Integer valor = new Integer(msg.substring(posInicial, posFinal));
	    				    			debito.setCodigoTipoDuc(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoTipoDuc";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("fechaVencimiento".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Date valor=Utilidad.deserializeAndFormatDate(msg.substring(posInicial, posFinal));
	    			    		    	debito.setFechaVencimiento(( valor== null)?null:DateUtils.truncate(valor,Calendar.DAY_OF_MONTH));
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo fechaVencimiento";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("importeAl1erVencimiento".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    		    					debito.setImporteAl1erVencimiento(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo importeAl1erVencimiento";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("importeAl2doVencimiento".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    		    					debito.setImporteAl2doVencimiento(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo importeAl2doVencimiento";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("saldoAl1erVencimiento".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    		    					debito.setSaldoAl1erVencimiento(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo saldoAl1erVencimiento";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("codigoEstadoAcuerdoFacturacion".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Integer valor = new Integer(msg.substring(posInicial, posFinal));
	    				    			debito.setCodigoEstadoAcuerdoFacturacion(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo codigoEstadoAcuerdoFacturacion";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("importe3erosTransferidos".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    		    					debito.setImporte3erosTransferidos(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo importe3erosTransferidos";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("importe1erVencimientoCon3eros".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    		    					debito.setImporte1erVencimientoCon3eros(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo importe1erVencimientoCon3eros";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("importe2erVencimientoCon3eros".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    		    					debito.setImporte2erVencimientoCon3eros(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo importe2erVencimientoCon3eros";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("fechaEmision".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Date valor=Utilidad.deserializeAndFormatDate(msg.substring(posInicial, posFinal));
	    			    		    	debito.setFechaEmision(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo fechaEmision";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("numeroConvenio".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Integer valor = new Integer(msg.substring(posInicial, posFinal));
	    				    			debito.setNumeroConvenio(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo numeroConvenio";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("cuota".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Integer valor = new Integer(msg.substring(posInicial, posFinal));
	    				    			debito.setCuota(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo cuota";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("fechaUltimoPagoParcial".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Date valor=Utilidad.deserializeAndFormatDate(msg.substring(posInicial, posFinal));
	    			    		    	debito.setFechaUltimoPagoParcial(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo fechaUltimoPagoParcial";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("fechaPuestaAlCobro".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Date valor=Utilidad.deserializeAndFormatDate(msg.substring(posInicial, posFinal));
	    			    		    	debito.setFechaPuestaAlCobro(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo fechaPuestaAlCobro";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("cicloFacturacion".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				Integer valor = new Integer(msg.substring(posInicial, posFinal));
	    				    			debito.getInformacionAdicionalTagetik().setCicloFacturacion(valor);
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
	    				    			debito.getInformacionAdicionalTagetik().setTipoFactura(valor);
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo tipoFactura";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("fechaVencimientoMora".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    		    	Date valor=Utilidad.deserializeAndFormatDate(msg.substring(posInicial, posFinal));
	    			    		    	debito.getInformacionAdicionalDacota().setFechaVencimientoMora(( valor== null)?null:DateUtils.truncate(valor,Calendar.DAY_OF_MONTH));
	    				    			posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo fechaVencimientoMora";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("saldoTerceroFinanciableNOTransferible".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    		    					debito.getInformacionAdicionalSaldosTerceros().setSaldoTerceroFinanciableNOTransferible(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo saldoTerceroFinanciableNOTransferible";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("saldoTerceroFinanciableTransferible".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    			    				debito.getInformacionAdicionalSaldosTerceros().setSaldoTerceroFinanciableTransferible(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo saldoTerceroFinanciableTransferible";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    						if ("saldoTerceroNOFinanciableTransferible".equalsIgnoreCase(nombreCampoAgrupador)) {
	    							try {
	    			    				posFinal = posInicial + longitudAgrupador;
	    			    				String valor = msg.substring(posInicial, posFinal);
	    			    				BigDecimal val = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(valor.trim()), 2);
	    			    				debito.getInformacionAdicionalSaldosTerceros().setSaldoTerceroNOFinanciableTransferible(val);
	    			    		    	posInicial += longitudAgrupador;
	    			    		    } catch(Exception e){
	    			    				String desc = "Error en el campo saldoTerceroNOFinanciableTransferible";
	    			    				Traza.error(getClass(), desc, e);
	    			    				throw new NegocioExcepcion(desc,e);
	    			    			}
	    						}
	    			    	}
	    				} // Fin - for
						
						//Por estar vacio no se agrega debito
						if (!BigInteger.ZERO.equals(debito.getCodigoCliente())) {
							datosRecibidos.getListaDebitos().add(debito);
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
			String servicio = Propiedades.SHIVA_PROPIEDADES.getString("mq.mic.consulta.deuda.servicio");
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
