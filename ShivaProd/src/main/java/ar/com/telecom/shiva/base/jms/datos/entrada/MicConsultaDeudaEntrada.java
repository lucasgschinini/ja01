package ar.com.telecom.shiva.base.jms.datos.entrada;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionConvenios;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionFactura;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionPaginadoDebito;

@SuppressWarnings("serial")
public class MicConsultaDeudaEntrada 
	extends JMS {
	
	protected List<BigInteger> 			listaClientes = new ArrayList<BigInteger>();
	protected MicInformacionFactura 	informacionFactura = new MicInformacionFactura();
	protected MicInformacionConvenios 	informacionConvenios = new MicInformacionConvenios(); 
	protected MicInformacionPaginadoDebito 	informacionPaginado = new MicInformacionPaginadoDebito();
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
  	
	public List<BigInteger> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(List<BigInteger> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public MicInformacionFactura getInformacionFactura() {
		return informacionFactura;
	}
	public void setInformacionFactura(MicInformacionFactura informacionFactura) {
		this.informacionFactura = informacionFactura;
	}
	public MicInformacionConvenios getInformacionConvenios() {
		return informacionConvenios;
	}
	public void setInformacionConvenios(MicInformacionConvenios informacionConvenios) {
		this.informacionConvenios = informacionConvenios;
	}
	public MicInformacionPaginadoDebito getInformacionPaginado() {
		return informacionPaginado;
	}
	public void setInformacionPaginado(MicInformacionPaginadoDebito informacionPaginado) {
		this.informacionPaginado = informacionPaginado;
	}
}
