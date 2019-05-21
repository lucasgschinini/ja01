package ar.com.telecom.shiva.negocio.executor.rto.cobros;

import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;

public class ImputacionRespuestaRto {
	
	private int count;
	private Long idOperacion;
	private Long idOperacionDescobro;
	private TipoProcesoEnum tipoProceso;
	
	public ImputacionRespuestaRto(int count, Long idOperacion, Long idOperacionDescobro, TipoProcesoEnum tipoProceso) {
		this.count = count;
		this.idOperacion = idOperacion;
		this.idOperacionDescobro = idOperacionDescobro;
		this.tipoProceso = tipoProceso;
	}

	public int getCount() {
		return count;
	}

	public TipoProcesoEnum getTipoProceso() {
		return tipoProceso;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public Long getIdOperacionDescobro() {
		return idOperacionDescobro;
	}
}
