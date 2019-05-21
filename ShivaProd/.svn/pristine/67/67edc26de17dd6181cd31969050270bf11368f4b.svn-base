package ar.com.telecom.shiva.base.registros.util;

import java.util.List;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.registros.util.definicion.Campo;
import ar.com.telecom.shiva.base.registros.util.definicion.CampoAEnviarREG;
import ar.com.telecom.shiva.base.registros.util.definicion.FormatoRegistros;
import ar.com.telecom.shiva.base.registros.util.definicion.TipoDatoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;

public class RegistroUtilidad {

//	/**
//	 * Verifico que si la longitud del mensaje es correcta de acuerdo a lo configurado
//	 * @param formatoMensaje
//	 * @param mensaje
//	 * @throws NegocioExcepcion
//	 */
//	public static void verificarLongitudTotalMensaje(int longitudCabecera, int longitudCuerpo, String mensaje) 
//			throws NegocioExcepcion
//	{
//		if ((longitudCabecera+longitudCuerpo) != mensaje.length()) {
//	    	String mensajeError = "Su longitud fija es incorrecto - Mensaje Length:" + mensaje.length();
//	    	throw new JmsExcepcion(mensajeError, null, mensajeError);
//	    }
//	}
	
	/**
	 * Genero una linea
	 * @param formatoMensaje
	 * @param listaCamposAEnviar
	 * @return
	 */
	public static String generarLinea(FormatoRegistros formatoRegistro, 
			List<CampoAEnviarREG> listaCamposAEnviar, String nombreAgrupador) 
	{
		String mensaje = "";
		
		List<Campo> camposSalida = formatoRegistro.getCamposSerializableOrdenada();
		for (Campo campo: camposSalida) {
			if (campo.getTipoDato().equals(TipoDatoEnum.AGRUPADOR)
		    		&& campo.getNombre().equalsIgnoreCase(nombreAgrupador)) {
				
				int contador = 0;
				int cantCamposAgrupador = campo.getAgrupadorOrdenada().size();
				
				for (Campo campoAgrupador: campo.getAgrupadorOrdenada()){
    				
					String nombreCampoAgrupador = campoAgrupador.getNombre();
    				int longitudAgrupador = campoAgrupador.getLongitud();
    				String valorCampoAgrupador = "";
    				
    				for (CampoAEnviarREG campoAEnviar: listaCamposAEnviar){
    					
    					if (campoAEnviar.getNombreCampo().equals(nombreCampoAgrupador)) {
    						valorCampoAgrupador = campoAEnviar.getValorCampo();
    						
    						if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.ALFANUMERICO)) {
		    		    		mensaje += Utilidad.rellenarEspaciosDerecha(valorCampoAgrupador, longitudAgrupador).substring(0, longitudAgrupador);
		    		    		contador++;
		    		    	} 
		    		    	if (campoAgrupador.getTipoDato().equals(TipoDatoEnum.NUMERICO)) {
		    		    		mensaje += Utilidad.rellenarCerosIzquierda(valorCampoAgrupador, longitudAgrupador).substring(0, longitudAgrupador);
		    		    		contador++;
		    		    	}
		    		    	
		    		    	if (contador < cantCamposAgrupador) {
		    		    		mensaje += Constantes.SEPARADOR_PIPE;
		    		    	} else {
		    		    		mensaje += "\r\n";
		    		    		return mensaje;
		    		    	}
    					}    					
    				}
				}
			}
		}
		
		return "";
	}
}
