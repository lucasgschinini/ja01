package ar.com.telecom.shiva.base.jms.datos.entrada;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicInformacionPaginadoCredito;

@SuppressWarnings("serial")
public class MicConsultaCreditoEntrada 
	extends JMS {
	
	protected List<BigInteger> 			listaClientes = new ArrayList<BigInteger>();
	protected TipoMedioPagoEnum			tipoMedioPago;
	protected Date						fechaDesde;
	protected Date						fechaHasta;
	protected BigInteger				acuerdo;
	
	protected MicInformacionPaginadoCredito 	informacionPaginado = new MicInformacionPaginadoCredito();

	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
	
	public List<BigInteger> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<BigInteger> listaClientes) {
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

	public MicInformacionPaginadoCredito getInformacionPaginado() {
		return informacionPaginado;
	}

	public void setInformacionPaginado(
			MicInformacionPaginadoCredito informacionPaginado) {
		this.informacionPaginado = informacionPaginado;
	}
	
}
