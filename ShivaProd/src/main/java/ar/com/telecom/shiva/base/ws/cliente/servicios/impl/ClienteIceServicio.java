package ar.com.telecom.shiva.base.ws.cliente.servicios.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.CodigoEmpresaIceEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionIceEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.IceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaIceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaIceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteIceServicio;

public class ClienteIceServicio implements IClienteIceServicio {
	
	@Autowired
	IceConsultaChequesWS iceConsultaChequesWS;
	
	/**
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaIceConsultaChequesWS consultarChequesIce (EntradaIceConsultaChequesWS entrada) throws NegocioExcepcion {
		
		entrada.setTipoInvocacion(TipoInvocacionIceEnum.$01);
		entrada.setCodigoEmpresaReceptora(CodigoEmpresaIceEnum.$0004);
		
		if (Validaciones.isObjectNull(entrada.getCantidadDeRegistrosARetornar())) {
			entrada.setCantidadDeRegistrosARetornar(new BigInteger("10000")); // TODO: armar una constantes para limite superior, ver si ya no existe
		}
		
		if (Validaciones.isObjectNull(entrada.getUltimoRegistroProcesado())) {
			entrada.setUltimoRegistroProcesado(new Long(-1));	
		}
		
		SalidaIceConsultaChequesWS salidaChequeWS = iceConsultaChequesWS.consultarChequesIce(entrada);
		
		return salidaChequeWS;
	}
	
	/**
	 * 
	 * @param entrada
	 * @return
	 * @throws NegocioExcepcion
	 */
	public SalidaIceConsultaChequesWS consultarDetalleCobranzaChequeIce (EntradaIceConsultaChequesWS entrada) throws NegocioExcepcion {

		entrada.setTipoInvocacion(TipoInvocacionIceEnum.$02);
		entrada.setCodigoEmpresaReceptora(CodigoEmpresaIceEnum.$0004);
		
		if (Validaciones.isObjectNull(entrada.getCantidadDeRegistrosARetornar())) {
			entrada.setCantidadDeRegistrosARetornar(new BigInteger("10000")); // TODO: armar una constantes para limite superior, ver si ya no existe
		}
		
		if (Validaciones.isObjectNull(entrada.getUltimoRegistroProcesado())) {
			entrada.setUltimoRegistroProcesado(new Long(-1));	
		}
		
		SalidaIceConsultaChequesWS salidaChequeWS = iceConsultaChequesWS.consultarChequesIce(entrada);
		
		return salidaChequeWS;
	}

}
