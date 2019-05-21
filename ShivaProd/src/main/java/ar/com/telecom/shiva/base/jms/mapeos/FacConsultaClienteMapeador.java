package ar.com.telecom.shiva.base.jms.mapeos;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.FacConsultaClienteEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaClienteSalida;
import ar.com.telecom.shiva.base.jms.util.JmsRetorno;
import ar.com.telecom.shiva.base.jms.util.JmsUtilidad;
import ar.com.telecom.shiva.base.jms.util.definicion.Campo;
import ar.com.telecom.shiva.base.jms.util.definicion.CampoAEnviarJMS;
import ar.com.telecom.shiva.base.jms.util.definicion.TipoDatoEnum;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public class FacConsultaClienteMapeador 
	extends MapeadorJMS {

	@Override
	public String serializar(JMS jms, boolean esSincronico) throws NegocioExcepcion {
		FacConsultaClienteEntrada datosAEnviar = (FacConsultaClienteEntrada) jms;
		
		List<CampoAEnviarJMS> listaCamposAEnviar = new ArrayList<CampoAEnviarJMS>();
		listaCamposAEnviar.add(new CampoAEnviarJMS("TIPO-TELEFONIA", null, null, datosAEnviar.getTipoTelefonia()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("NTWK-SERV-TYPE-CD", null, null, datosAEnviar.getTipoServicio()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("NAT-SER-NBR", null, null, datosAEnviar.getNumeroLinea()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("CIDN-DSP", null, null, datosAEnviar.getNumeroCliente()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("INVC-ARGT-ID-DSP", null, null, datosAEnviar.getNumeroAcuerdo()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("CUST-INTRNL-NBR", null, null, datosAEnviar.getCustIntrnlNbr()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("APELL-CLI", null, null, datosAEnviar.getApellCli()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("NOM-CLI", null, null, datosAEnviar.getNomCli()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("NOM-CALLE", null, null, datosAEnviar.getNomCalle()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("NOM-CIUDAD", null, null, datosAEnviar.getNomCiudad()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("CUIT", null, null, datosAEnviar.getCuit()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("EFF-STA-DT", null, null, datosAEnviar.getFechaEmision()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("CMN-RTN-INPT-CD", null, null, datosAEnviar.getCmnRtnInptCd()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("RTRVL-CODE", null, null, datosAEnviar.getRtrvlCode()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("USER-OWNER-IND", null, null, datosAEnviar.getUserOwnerInd()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("FST-TIME-LKUP-IND", null, null, datosAEnviar.getFstTimeLkupInd()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("NAT-SERV-NBR-1", null, null, datosAEnviar.getNatServNbr1()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("INV-ARGT-ID-1", null, null, datosAEnviar.getInvArgtId1()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("PROD-BILG-ID", null, null, datosAEnviar.getProdBilgId()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("BILG-ELMT-CD", null, null, datosAEnviar.getBilgElmtCd()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("CPI-ID-DSP", null, null, datosAEnviar.getCpiIdDsp()));
		listaCamposAEnviar.add(new CampoAEnviarJMS("ADDL-REC-IND", null, null, datosAEnviar.getAddlRecInd()));
		
		String datosCabecera = Propiedades.SHIVA_PROPIEDADES.getString("mq.fac.consultas.servicio");
		
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
		
		boolean esMensajeDeError = false;
		
		if (getDefaultFormatoMensajeJMS().getLontitudFijaMensajeDeserializable() == mensajeRecibido.length()) {
			esMensajeDeError = false;
		} else {
			if (getDefaultFormatoMensajeJMS().getLontitudFijaMensajeDeserializableError() == mensajeRecibido.length()) {
				esMensajeDeError = true;
			} else {
				String mensajeError = "Su longitud fija es incorrecto - Mensaje Length:" + mensajeRecibido.length();
		    	throw new NegocioExcepcion(mensajeError);
			}
	    }
		
		String msg = mensajeRecibido;
		
		FacConsultaClienteSalida datosRecibidos = new FacConsultaClienteSalida();
		
		List<Campo> camposEntrada = getDefaultFormatoMensajeJMS().getCamposDeserializableOrdenada();
		int posInicial = 0;
		int posFinal = 0;
	    for (Campo campo: camposEntrada){
	    	String nombreCampo = campo.getNombre();
	    	int longitud = campo.getLongitud();
	    	
	    	//Si no es un mensaje de error, puedo mapear los demas campos
	    	if (!esMensajeDeError) {
		    	if (campo.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
		    		//SALTO-1 = Serie de campos que no los necesito por eso saltamos posiciones
		    		if ("SALTO-1".equalsIgnoreCase(nombreCampo)) {
		    			posFinal = posInicial + longitud;
		    		    posInicial += longitud;
		    		}
		    		
		    	} 
		    	
		    	if (campo.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
		    		if ("RJ79-CIDN-DSP".equalsIgnoreCase(nombreCampo)) {
		    			
		    			try {
		    				posFinal = posInicial + longitud;
		    		    	Long valor = Long.valueOf(msg.substring(posInicial, posFinal));
		    		    	datosRecibidos.setNumeroCliente(valor);
			    			posInicial += longitud;
		    		    } catch(Exception e){
		    				String desc = "Error en el campo RJ79-CIDN-DSP";
		    				Traza.error(getClass(), desc, e);
		    				throw new NegocioExcepcion(desc,e);
		    			}
		    		}
		    	}
	    	}
	    	
	    	if (campo.getTipoDato().equals(TipoDatoEnum.AGRUPADOR)) {
	    		if ("retorno".equalsIgnoreCase(nombreCampo)) {
	    			JmsRetorno retorno = new JmsRetorno();
					
					for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    					String nombreCampoAgrupador = campoAgrupador.getNombre();
    					int longitudAgrupador = campoAgrupador.getLongitud();
    					
    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
    						if ("ERR-IND".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String valor=msg.substring(posInicial, posFinal);
    			    		    		retorno.setIndicadorError(valor.trim());
    			    		    	}	
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "Error en el campo ERR-IND";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("ERR-CD".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String valor=msg.substring(posInicial, posFinal);
    			    		    		retorno.setCodigoError(valor.trim());
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "Error en el campo ERR-CD";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("ADDL-REC-FOUND-IND".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String valor=msg.substring(posInicial, posFinal);
    			    		    		retorno.setIndicadorRegAdicionalesEncontrados(valor.trim());
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "Error en el campo ADDL-REC-FOUND-IND";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    						if ("ADDL-REC-IND".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					String valor=msg.substring(posInicial, posFinal);
    			    		    		retorno.setRegistrosAdicionales(valor.trim());
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "Error en el campo ADDL-REC-IND";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    			    	}
    					
    					if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
    						if ("RTRND-RECS-CNT".equalsIgnoreCase(nombreCampoAgrupador)) {
    							try {
    			    				posFinal = posInicial + longitudAgrupador;
    			    				if (camposSeteables) {
    			    					Long msgLength=Long.valueOf(msg.substring(posInicial, posFinal));
    			    		    		retorno.setCantRegistrosRetornados(msgLength.toString());
    			    		    	}
    				    			posInicial += longitudAgrupador;
    			    		    } catch(Exception e){
    			    				String desc = "Error en el campo RTRND-RECS-CNT";
    			    				Traza.error(getClass(), desc, e);
    			    				throw new NegocioExcepcion(desc,e);
    			    			}
    						}
    			    	}
    					
    				} // Fin - for
    				
    				datosRecibidos.setRetorno(retorno);
				}
    		}
		}
		return datosRecibidos;
	}
	
	public boolean verificarServicio(String msj, boolean esSincronico) throws NegocioExcepcion {
		//Si quiero validar el servicio en la cabecera, lo hago
		if (getDefaultFormatoMensajeJMS().getLontitudFijaCabeceraDeserializableSync() > 0) {
			String servicio = Propiedades.SHIVA_PROPIEDADES.getString("mq.fac.consultas.servicio");
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
