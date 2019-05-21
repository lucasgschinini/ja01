/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.processor;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.springframework.batch.item.ExecutionContext;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.util.definicion.TipoDatoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;


/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class ConfiguracionCampo extends ConfiguracionCalculo implements IConfiguracionCampo {
	
	private String posicionDeCorrespondencia;
	private String id;
	private String nombre;
	private ConfiguracionTipoDato configuracionTipoDato;
	private ConfiguracionObligatoriedad configuracionObligatoriedad;
//	private ConfiguracionValidacion configuracionValidacion;
	private ConfiguracionMapeoAtributoModeloRelacionado configuracionMapeoModeloRelacionado;
	private ConfiguracionRechazarArchivo configuracionRechazarArchivo;
	
	/**
	 * @return the posicionDeCorrespondencia
	 */
	public String getPosicionDeCorrespondencia() {
		return posicionDeCorrespondencia;
	}
	
	/**
	 * @param posicionDeCorrespondencia the posicionDeCorrespondencia to set
	 */
	public void setPosicionDeCorrespondencia(String posicionDeCorrespondencia) {
		this.posicionDeCorrespondencia = posicionDeCorrespondencia;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @return the configuracionTipoDato
	 */
	public ConfiguracionTipoDato getConfiguracionTipoDato() {
		return configuracionTipoDato;
	}
	
	/**
	 * @param configuracionTipoDato the configuracionTipoDato to set
	 */
	public void setConfiguracionTipoDato(ConfiguracionTipoDato configuracionTipoDato) {
		this.configuracionTipoDato = configuracionTipoDato;
	}
	
	/**
	 * @return the configuracionObligatoriedad
	 */
	public ConfiguracionObligatoriedad getConfiguracionObligatoriedad() {
		return configuracionObligatoriedad;
	}

	/**
	 * @param configuracionObligatoriedad the configuracionObligatoriedad to set
	 */
	public void setConfiguracionObligatoriedad(ConfiguracionObligatoriedad configuracionObligatoriedad) {
		this.configuracionObligatoriedad = configuracionObligatoriedad;
	}
	
	/**
	 * @return the configuracionMapeoModeloRelacionado
	 */
	public ConfiguracionMapeoAtributoModeloRelacionado getConfiguracionMapeoModeloRelacionado() {
		return configuracionMapeoModeloRelacionado;
	}

	/**
	 * @param configuracionMapeoModeloRelacionado the configuracionMapeoModeloRelacionado to set
	 */
	public void setConfiguracionMapeoModeloRelacionado(
			ConfiguracionMapeoAtributoModeloRelacionado configuracionMapeoModeloAvcRelacionado) {
		this.configuracionMapeoModeloRelacionado = configuracionMapeoModeloAvcRelacionado;
	}

	
	
	/**
	 * @return the configuracionRechazarArchivo
	 */
	public ConfiguracionRechazarArchivo getConfiguracionRechazarArchivo() {
		return configuracionRechazarArchivo;
	}

	/**
	 * @param configuracionRechazarArchivo the configuracionRechazarArchivo to set
	 */
	public void setConfiguracionRechazarArchivo(ConfiguracionRechazarArchivo configuracionRechazarArchivo) {
		this.configuracionRechazarArchivo = configuracionRechazarArchivo;
	}

	@Override
	public void validar(Object objOrigen, ExecutionContext context) throws NegocioExcepcion {
		
		StringBuilder mensaje = new StringBuilder();
		Object value = null;
		try {
			value = getValorAtributo(objOrigen, id);
			
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
			throw new NegocioExcepcion("Ocurrió un error al obtener el valor del campo " + nombre,e);
		}
		
		validarObligatoriedad(value, mensaje, context);
		validarLongitud(value, mensaje, context);
		validarTipoDato(value, mensaje, context);
	}

	/**
	 * 
	 * @param value
	 * @param mensaje
	 * @param context 
	 * @throws NegocioExcepcion
	 */
	private void validarTipoDato(Object value, StringBuilder mensaje, ExecutionContext context) throws NegocioExcepcion {
		if (!Validaciones.isNullEmptyOrDash(configuracionTipoDato.getTipoDato())) {
			String tipoDato = configuracionTipoDato.getTipoDato();
			
			if (!Validaciones.isNullEmptyOrDash(configuracionTipoDato.getFormato()) && TipoDatoEnum.FECHA.equals(TipoDatoEnum.getEnumByName(tipoDato))) {
				try {
					Date fecha = Utilidad.parseDateStringCustomFormat(value.toString(), configuracionTipoDato.getFormato());
				} catch (ParseException e) {
					mensaje.append(" '" + nombre + "'")
						   .append(" posee un formato incorrecto. ")
						   .append("El formato esperado es '" + configuracionTipoDato.getFormato() + "'")
						   .append(System.lineSeparator());
					if (!Validaciones.isObjectNull(configuracionRechazarArchivo)) {
						if (SiNoEnum.SI.equals(SiNoEnum.getEnumByName(configuracionRechazarArchivo.getSeRechazaArchivo()))) {
							context.put("tieneError", true);
						}
					}
					throw new NegocioExcepcion(mensaje.toString(),e);
				}
			} else if (TipoDatoEnum.NUMERO.equals(TipoDatoEnum.getEnumByName(tipoDato))) {
				try {
					int campoNumerico = Integer.parseInt(value.toString());
					if (campoNumerico < Constantes.CERO) {
						mensaje.append("'" + nombre + "'")
					   	   .append(" debe ser mayor a 0 (Cero) ")
					   	   .append(System.lineSeparator());
						throw new NegocioExcepcion(mensaje.toString());
					}
				} catch (NumberFormatException e) {
					mensaje.append("'" + nombre + "'")
					   	   .append(" debe ser ")
					   	   .append(tipoDato)
					   	   .append(System.lineSeparator());
					if (!Validaciones.isObjectNull(configuracionRechazarArchivo)) {
						if (SiNoEnum.SI.equals(SiNoEnum.getEnumByName(configuracionRechazarArchivo.getSeRechazaArchivo()))) {
							context.put("tieneError", true);
						}
					}
					throw new NegocioExcepcion(mensaje.toString(),e);
				}
			} else if (TipoDatoEnum.DECIMAL.equals(TipoDatoEnum.getEnumByName(tipoDato))) {
				try {
					BigDecimal campoDecimal = new BigDecimal(value.toString());
					if (campoDecimal.compareTo(BigDecimal.ZERO) < 0 ) {
						mensaje.append("'" + nombre + "'")
					   	   .append(" debe ser mayor a 0 (Cero) ")
					   	   .append(System.lineSeparator());
						throw new NegocioExcepcion(mensaje.toString());
					}
				} catch (NumberFormatException e) {
					mensaje.append("'" + nombre + "'")
					   	   .append(" debe ser ")
					   	   .append(tipoDato)
					   	   .append(System.lineSeparator());
					if (!Validaciones.isObjectNull(configuracionRechazarArchivo)) {
						if (SiNoEnum.SI.equals(SiNoEnum.getEnumByName(configuracionRechazarArchivo.getSeRechazaArchivo()))) {
							context.put("tieneError", true);
						}
					}
					throw new NegocioExcepcion(mensaje.toString(),e);
				}
			}
		}
	}

	/**
	 * 
	 * @param value
	 * @param mensaje
	 * @param context 
	 * @throws NegocioExcepcion
	 */
	private void validarObligatoriedad(Object value, StringBuilder mensaje, ExecutionContext context) throws NegocioExcepcion {
		
		
		if ((Validaciones.isObjectNull(value) || Constantes.EMPTY_STRING.equals(value)) && SiNoEnum.SI.equals(SiNoEnum.getEnumByName(configuracionObligatoriedad.getEsObligatorio()))) {
			mensaje.append("'" + nombre + "'")
				   .append(" es Obligatorio, se descarta el registro.")
				   .append(System.lineSeparator());
			if (!Validaciones.isObjectNull(configuracionRechazarArchivo)) {
				if (SiNoEnum.SI.equals(SiNoEnum.getEnumByName(configuracionRechazarArchivo.getSeRechazaArchivo()))) {
					context.put("tieneError", true);
				}
			}
			throw new NegocioExcepcion(mensaje.toString());
		}
	}
	
	/**
	 * 
	 * @param value
	 * @param mensaje
	 * @param context 
	 * @throws NegocioExcepcion
	 */
	private void validarLongitud(Object value, StringBuilder mensaje, ExecutionContext context) throws NegocioExcepcion {
		
		if (!Validaciones.isNullEmptyOrDash(configuracionTipoDato.getLongitud())) {
			
			int	longitudEsperada = Integer.parseInt(configuracionTipoDato.getLongitud());
			
			if(value.toString().length() > longitudEsperada ) {
				mensaje.append("'" + nombre + "' ")
						.append("tiene una Longitud incorrecta(Longitud Esperada: ")
						.append(configuracionTipoDato.getLongitud()) 
						.append(" | Longitud Obtenida: " )
						.append(value.toString().length())
						.append(Constantes.CLOSE_PARENTESIS)
						.append(System.lineSeparator());
				if (!Validaciones.isObjectNull(configuracionRechazarArchivo)) {
					if (SiNoEnum.SI.equals(SiNoEnum.getEnumByName(configuracionRechazarArchivo.getSeRechazaArchivo()))) {
						context.put("tieneError", true);
					}
				}
				throw new NegocioExcepcion(mensaje.toString());
			}
		}
	}
}