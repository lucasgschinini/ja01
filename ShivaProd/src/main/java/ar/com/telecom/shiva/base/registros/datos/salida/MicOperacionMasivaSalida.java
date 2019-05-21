package ar.com.telecom.shiva.base.registros.datos.salida;

import java.util.ArrayList;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.registros.datos.MicOperacionMasivaCabeceraPie;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaRegistroSalida;

@SuppressWarnings("serial")
public class MicOperacionMasivaSalida extends REG {
	
	private MicOperacionMasivaCabeceraPie cabecera = new MicOperacionMasivaCabeceraPie();
	
	private ArrayList<MicOperacionMasivaRegistroSalida> registros = new ArrayList<MicOperacionMasivaRegistroSalida>(); 
	
	private MicOperacionMasivaCabeceraPie pie = new MicOperacionMasivaCabeceraPie();

	public MicOperacionMasivaCabeceraPie getCabecera() {
		return cabecera;
	}

	public void setCabecera(MicOperacionMasivaCabeceraPie cabecera) {
		this.cabecera = cabecera;
	}

	public MicOperacionMasivaCabeceraPie getPie() {
		return pie;
	}

	public void setPie(MicOperacionMasivaCabeceraPie pie) {
		this.pie = pie;
	}

	public ArrayList<MicOperacionMasivaRegistroSalida> getRegistros() {
		return registros;
	}

	public void setRegistros(ArrayList<MicOperacionMasivaRegistroSalida> registros) {
		this.registros = registros;
	}
}
