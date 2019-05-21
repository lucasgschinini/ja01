/**
 * 
 */
package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.enumeradores.SemaforoGestionabilidadEnum;

/**
 * @author u578936 MA.Uehara
 *
 */
public class GestionabilidadDto {

	private String cobrarDeuda;
	private String semaforo;
	private String observacion;
	private String codigoError;
	private String descripcionError;

	public GestionabilidadDto() {
	}

	public GestionabilidadDto(
		String cobrarDeuda,
		String semaforo,
		String observacion
	) {
		this.cobrarDeuda = cobrarDeuda;
		this.semaforo = semaforo;
		this.observacion = observacion;
	}
	public GestionabilidadDto(
		String cobrarDeuda,
		String semaforo,
		String observacion,
		String codigoError,
		String descripcionError
	) {
		this.cobrarDeuda = cobrarDeuda;
		this.semaforo = semaforo;
		this.observacion = observacion;
		this.codigoError = codigoError;
		this.descripcionError = descripcionError;
	}

	public String getCobrarDeuda() {
		return cobrarDeuda;
	}
	public void setCobrarDeuda(String cobrarDeuda) {
		this.cobrarDeuda = cobrarDeuda;
	}
	public String getSemaforo() {
		return semaforo;
	}
	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cobrarDeuda == null) ? 0 : cobrarDeuda.hashCode());
		result = prime * result
				+ ((codigoError == null) ? 0 : codigoError.hashCode());
		result = prime
				* result
				+ ((descripcionError == null) ? 0 : descripcionError.hashCode());
		result = prime * result
				+ ((observacion == null) ? 0 : observacion.hashCode());
		result = prime * result
				+ ((semaforo == null) ? 0 : semaforo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GestionabilidadDto other = (GestionabilidadDto) obj;
		if (cobrarDeuda == null) {
			if (other.cobrarDeuda != null)
				return false;
		} else if (!cobrarDeuda.equals(other.cobrarDeuda))
			return false;
		if (codigoError == null) {
			if (other.codigoError != null)
				return false;
		} else if (!codigoError.equals(other.codigoError))
			return false;
		if (descripcionError == null) {
			if (other.descripcionError != null)
				return false;
		} else if (!descripcionError.equals(other.descripcionError))
			return false;
		if (observacion == null) {
			if (other.observacion != null)
				return false;
		} else if (!observacion.equals(other.observacion))
			return false;
		if (semaforo == null) {
			if (other.semaforo != null)
				return false;
		} else if (!semaforo.equals(other.semaforo))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "GestionabilidadDto [cobrarDeuda=" + cobrarDeuda + ", semaforo="
				+ semaforo + ", observacion=" + observacion + ", codigoError="
				+ codigoError + ", descripcionError=" + descripcionError + "]";
	}
	public int obtenerSemaforoPrioridad() {
		int salida = -1;
		if (this.semaforo != null && !"".equals(this.semaforo)) {
			if (SemaforoGestionabilidadEnum.VERDE.getDescripcion().equals(this.semaforo)) {
				salida = 0;
			} else if (SemaforoGestionabilidadEnum.AMARILLO.getDescripcion().equals(this.semaforo)) {
				salida = 1;
			} else if (SemaforoGestionabilidadEnum.NARANJA.getDescripcion().equals(this.semaforo)) {
				salida = 2;
			} else if (SemaforoGestionabilidadEnum.ROJO.getDescripcion().equals(this.semaforo)) {
				salida = 3;
			}
		}
		return salida;
	}
	public static String esGestinonle(String semaforo) {
		String salida = "";
		if (SemaforoGestionabilidadEnum.VERDE.getDescripcion().equals(semaforo)) {
			salida = "SI";
		} else if (SemaforoGestionabilidadEnum.AMARILLO.getDescripcion().equals(semaforo)) {
			salida = "SI";
		} else if (SemaforoGestionabilidadEnum.NARANJA.getDescripcion().equals(semaforo)) {
			salida = "NO";
		} else if (SemaforoGestionabilidadEnum.ROJO.getDescripcion().equals(semaforo)) {
			salida = "NO";
		}
		return salida;
	}
}