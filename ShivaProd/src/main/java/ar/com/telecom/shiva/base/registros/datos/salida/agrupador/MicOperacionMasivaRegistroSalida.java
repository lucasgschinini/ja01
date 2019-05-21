package ar.com.telecom.shiva.base.registros.datos.salida.agrupador;

import ar.com.telecom.shiva.base.dto.REG;

@SuppressWarnings("serial")
public class MicOperacionMasivaRegistroSalida extends REG {
	
	
	private MicOperacionMasivaParametrosSalida parametrosGenerales = new MicOperacionMasivaParametrosSalida();
	private MicOperacionMasivaDatosGralesSalida datosGenerales = new MicOperacionMasivaDatosGralesSalida();
	private MicOperacionMasivaDebitoSalida datosDebitos = new MicOperacionMasivaDebitoSalida(); 
	private MicOperacionMasivaMedioPagoSalida datosMedioPago = new MicOperacionMasivaMedioPagoSalida();
	private MicOperacionMasivaGananciaSalida datosGanancia = new MicOperacionMasivaGananciaSalida();
	private MicOperacionMasivaDesistimientoSalida datosDesistimiento = new MicOperacionMasivaDesistimientoSalida();
	private MicOperacionMasivaReintegroSalida datosReintegro = new MicOperacionMasivaReintegroSalida();
	private MicOperacionMasivaCargoProximaFacturaSalida datosCargoProximaFactura = new MicOperacionMasivaCargoProximaFacturaSalida();
	

	public MicOperacionMasivaParametrosSalida getParametrosGenerales() {
		return parametrosGenerales;
	}

	public void setParametrosGenerales(
			MicOperacionMasivaParametrosSalida parametrosGenerales) {
		this.parametrosGenerales = parametrosGenerales;
	}

	public MicOperacionMasivaDatosGralesSalida getDatosGenerales() {
		return datosGenerales;
	}

	public void setDatosGenerales(MicOperacionMasivaDatosGralesSalida datosGenerales) {
		this.datosGenerales = datosGenerales;
	}

	public MicOperacionMasivaDebitoSalida getDatosDebitos() {
		return datosDebitos;
	}

	public void setDatosDebitos(MicOperacionMasivaDebitoSalida datosDebitos) {
		this.datosDebitos = datosDebitos;
	}

	public MicOperacionMasivaMedioPagoSalida getDatosMedioPago() {
		return datosMedioPago;
	}

	public void setDatosMedioPago(MicOperacionMasivaMedioPagoSalida datosMedioPago) {
		this.datosMedioPago = datosMedioPago;
	}

	public MicOperacionMasivaGananciaSalida getDatosGanancia() {
		return datosGanancia;
	}

	public void setDatosGanancia(MicOperacionMasivaGananciaSalida datosGanancia) {
		this.datosGanancia = datosGanancia;
	}

	public MicOperacionMasivaDesistimientoSalida getDatosDesistimiento() {
		return datosDesistimiento;
	}

	public void setDatosDesistimiento(
			MicOperacionMasivaDesistimientoSalida datosDesistimiento) {
		this.datosDesistimiento = datosDesistimiento;
	}

	public MicOperacionMasivaReintegroSalida getDatosReintegro() {
		return datosReintegro;
	}

	public void setDatosReintegro(MicOperacionMasivaReintegroSalida datosReintegro) {
		this.datosReintegro = datosReintegro;
	}

	public MicOperacionMasivaCargoProximaFacturaSalida getDatosCargoProximaFactura() {
		return datosCargoProximaFactura;
	}

	public void setDatosCargoProximaFactura(
			MicOperacionMasivaCargoProximaFacturaSalida datosCargoProximaFactura) {
		this.datosCargoProximaFactura = datosCargoProximaFactura;
	}

}
