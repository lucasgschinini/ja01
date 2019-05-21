/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.validator;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Value;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.springbatch.listener.CustomSkipListener;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataItem;
import ar.com.telecom.shiva.batch.springbatch.processor.IConfiguracionCampo;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class ItemValidator implements Validator<GenericDataItem> {

	private List<IConfiguracionCampo> listaConfiguracionCampos;
	private boolean hayErrores;
	private String mensajeError;
	private NegocioExcepcion negExcepcion;
	private int lineaProcesada = 2;
	
	@Value("#{stepExecution}")
	private StepExecution stepExecution;

	
	@Override
	public void validate(GenericDataItem value) throws ValidationException {
		
		hayErrores = false;
		mensajeError = Constantes.EMPTY_STRING;
		
		String lineaYCausa = "Línea número : " + lineaProcesada + Constantes.WHITESPACE + "Causa: ";
		String logProcesamiento = (String) stepExecution.getJobExecution().getExecutionContext().get("logProcesamiento");
		
		if (Validaciones.isNullEmptyOrDash(logProcesamiento)) {
			logProcesamiento = Constantes.EMPTY_STRING;
		}
		
		for (IConfiguracionCampo configuracionCampo : listaConfiguracionCampos) {
			try {
				configuracionCampo.validar(value, stepExecution.getJobExecution().getExecutionContext());
			} catch (NegocioExcepcion e) {
				hayErrores = true;
				mensajeError += lineaYCausa + e.getMessage();
				logProcesamiento += lineaYCausa + e.getMessage();
				negExcepcion = e;
			}
		}
		if (this.hayErrores) {
			
			stepExecution.getJobExecution().getExecutionContext().put("logProcesamiento", logProcesamiento);
			
			Traza.error(CustomSkipListener.class, mensajeError);
			mensajeError = "Se han encontrado campos que no pasaron las validaciones en la linea " + lineaProcesada; 
			
			lineaProcesada++;
//			stepExecution.getJobExecution().getExecutionContext().put("tieneError", true);
			throw new ValidationException(mensajeError, negExcepcion);
		}
		
		lineaProcesada++;
	}

	/**
	 * @return the listaConfiguracionCampos
	 */
	public List<IConfiguracionCampo> getListaConfiguracionCampos() {
		return listaConfiguracionCampos;
	}

	/**
	 * @param listaConfiguracionCampos the listaConfiguracionCampos to set
	 */
	public void setListaConfiguracionCampos(
			List<IConfiguracionCampo> listaConfiguracionCampos) {
		this.listaConfiguracionCampos = listaConfiguracionCampos;
	}

	/**
	 * @return the hayErrores
	 */
	public boolean isHayErrores() {
		return hayErrores;
	}

	/**
	 * @param hayErrores the hayErrores to set
	 */
	public void setHayErrores(boolean hayCamposNulos) {
		this.hayErrores = hayCamposNulos;
	}

	
	
}
