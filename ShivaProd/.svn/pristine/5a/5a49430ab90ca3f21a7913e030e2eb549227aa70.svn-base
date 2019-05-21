package ar.com.telecom.shiva.persistencia.modelo.simple;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * @author u564030 Pablo M. Ibarrola
 * 
 * Req 70868 Mejora de Performance en Talonarios.
 * Se ha detectado que al levantar datos de constancia (y solo de constancia) se está levantando 
 * el objeto valor asociado a la constancia de manera innecesaria.
 * Se crea una nueva PK sin el valor asociado.
 */
@Entity
@Table(name = "SHV_VAL_CONSTANCIA_RECEP_VALOR")
public class ShvValConstanciaRecepcionValorSimplificado extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
    private ShvValConstanciaRecepcionValorSimplificadoPk pk;

	/**
	 * @return the pk
	 */
	public ShvValConstanciaRecepcionValorSimplificadoPk getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(ShvValConstanciaRecepcionValorSimplificadoPk pk) {
		this.pk = pk;
	}
}
