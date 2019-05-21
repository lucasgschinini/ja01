package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.Cliente;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.InformacionParaPaginadoDebito;

@SuppressWarnings("serial")
public class EntradaCalipsoDeudaWS extends EntradaWS {
	
	protected List<Cliente> listaClientes = new ArrayList<Cliente>();
	
	protected IdDocumento idDocumento;
	protected Date fechaVencimientoDesde;
	protected Date fechaVencimientoHasta;
	protected Date fechaCotizacion;
	protected BigInteger acuerdo;
	protected MonedaEnum moneda;
	
	protected InformacionParaPaginadoDebito informacionParaPaginado = new InformacionParaPaginadoDebito();
	
	/*******************************************************************
	 * Getters & Setters
	 ******************************************************************/

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Date getFechaVencimientoDesde() {
		return fechaVencimientoDesde;
	}

	public void setFechaVencimientoDesde(Date fechaVencimientoDesde) {
		this.fechaVencimientoDesde = fechaVencimientoDesde;
	}

	public Date getFechaVencimientoHasta() {
		return fechaVencimientoHasta;
	}

	public void setFechaVencimientoHasta(Date fechaVencimientoHasta) {
		this.fechaVencimientoHasta = fechaVencimientoHasta;
	}
	
	public Date getFechaCotizacion() {
		return fechaCotizacion;
	}

	public void setFechaCotizacion(Date fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	public BigInteger getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(BigInteger acuerdo) {
		this.acuerdo = acuerdo;
	}

	public MonedaEnum getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	public InformacionParaPaginadoDebito getInformacionParaPaginado() {
		return informacionParaPaginado;
	}

	public void setInformacionParaPaginado(
			InformacionParaPaginadoDebito informacionParaPaginado) {
		this.informacionParaPaginado = informacionParaPaginado;
	}

	public IdDocumento getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(IdDocumento idDocumento) {
		this.idDocumento = idDocumento;
	}
		
	
}
