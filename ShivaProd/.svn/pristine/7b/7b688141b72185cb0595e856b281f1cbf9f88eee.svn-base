package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.Cliente;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.InformacionParaPaginadoCredito;

@SuppressWarnings("serial")
public class EntradaCalipsoCreditoWS extends EntradaWS {
	
	protected List<Cliente> listaClientes = new ArrayList<Cliente>();
	protected TipoMedioPagoEnum tipoMedioPago;
	protected Date 			fechaDesde;
	protected Date 			fechaHasta;
	protected BigInteger 	acuerdo;
	protected MonedaEnum 	moneda;
	
	protected InformacionParaPaginadoCredito informacionParaPaginado = new InformacionParaPaginadoCredito(); 

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public TipoMedioPagoEnum getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(TipoMedioPagoEnum tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
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

	public InformacionParaPaginadoCredito getInformacionParaPaginado() {
		return informacionParaPaginado;
	}

	public void setInformacionParaPaginado(
			InformacionParaPaginadoCredito informacionParaPaginado) {
		this.informacionParaPaginado = informacionParaPaginado;
	}
	
	
	
}
