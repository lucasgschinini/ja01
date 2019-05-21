package ar.com.telecom.shiva.base.registros.datos.salida.agrupador;

import java.util.Date;

import ar.com.telecom.shiva.base.dto.REG;

@SuppressWarnings("serial")
public class MicOperacionMasivaGananciaSalida extends REG {
	
	private Date fechaValorGanancias;

	public Date getFechaValorGanancias() {
		return fechaValorGanancias;
	}

	public void setFechaValorGanancias(Date fechaValorGanancias) {
		this.fechaValorGanancias = fechaValorGanancias;
	}

}
