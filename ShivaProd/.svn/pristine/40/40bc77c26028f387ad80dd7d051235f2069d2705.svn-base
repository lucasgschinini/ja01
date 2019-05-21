/**
 * 
 */
package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SemaforoGestionabilidadEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;

/**
 * @author u564030
 *
 */
public abstract class DocumentoGestionableDTO extends DTO {

	private static final long serialVersionUID = 1L;
	
	private GestionabilidadDto semaforo;

	/**
	 * @return the semaforo
	 */
	public GestionabilidadDto getSemaforo() {
		return semaforo;
	}

	/**
	 * @param semaforo the semaforo to set
	 */
	public void setSemaforo(GestionabilidadDto semaforo) {
		this.semaforo = semaforo;
	}

	public int obtenerSemaforoPrioridad() {
		if (Validaciones.isObjectNull(this.getSemaforo())) {
			return -1;
		}
		return this.getSemaforo().obtenerSemaforoPrioridad();
	}
	
	
	public String obtenerSemaforoColor() {
		if (Validaciones.isObjectNull(this.semaforo)) {
			return null;
		}
		return this.semaforo.getSemaforo();
	}
	public String getClassSemaforo() {
		String retorno = null;
		if (SemaforoGestionabilidadEnum.ROJO.getDescripcion().equals(this.getSemaforo().getSemaforo())) {
			retorno = new String ("semaforo-rojo");
		} else if (SemaforoGestionabilidadEnum.VERDE.getDescripcion().equals(this.getSemaforo().getSemaforo())) {
			retorno = new String ("semaforo-verde");
		} else if (SemaforoGestionabilidadEnum.AMARILLO.getDescripcion().equals(this.getSemaforo().getSemaforo())) {
			retorno = new String ("semaforo-amarillo");
		} else {
			retorno = new String ("semaforo-naranja");
		}
		return retorno;
	}
}
