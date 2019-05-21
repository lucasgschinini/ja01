package ar.com.telecom.shiva.batch.springbatch.processor;

import java.lang.reflect.InvocationTargetException;

import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Value;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataFile;
/**
 * 
 * @author u564030 Pablo M. Ibarrola
 *
 * Aqui un ejemplo de como implementar dentro de la lista de campos calculados
 * 
 * <bean class="ar.com.telecom.shiva.batch.springbatch.processor.ConfiguracionCalculoSecuencialReferencia" scope="step">
 *    <property name="stepExecution" value="#{stepExecution}" />
 *    <property name="ordenProcesamiento" value="1"/>
 *    <property name="idCampoDestino" value="referencia"/>
 * </bean> 
 *
 */
public class ConfiguracionCalculoSecuencialReferencia extends ConfiguracionCalculo implements IConfiguracionCalculo {

	@Value("#{stepExecution}")
	private StepExecution stepExecution;

	private String idCampoDestino;

	@Override
	public void calcular(Object objOrigen) throws NegocioExcepcion {

		try {
	        // Traigo los datos del archivo actual del contexto
	        GenericDataFile genericDataFile = (GenericDataFile) stepExecution.getJobExecution().getExecutionContext().get("genericDataFile");
	        
	        // Traigo la linea actual de procesamiento del contexto
			Long numerolineaActual = (Long) stepExecution.getJobExecution().getExecutionContext().get("lineaActual");
			
			// Genero la sencuencia
			String secuencialReferencia = Utilidad.formatDateAAMMDD(genericDataFile.getFechaProcesamiento())
										+ Utilidad.rellenarCerosIzquierda(genericDataFile.getSecuencialArchivo(), 2)
										+ Utilidad.rellenarCerosIzquierda(numerolineaActual.toString(), 4);
			
			// Seteo la secuencia en el registro
			setValorAtributo(objOrigen, idCampoDestino, secuencialReferencia);
			
		    Traza.auditoria(this.getClass(), "Resultado del calculo realizado");
		    Traza.auditoria(this.getClass(), "Entrada: fechaProcesamiento: '" + Utilidad.formatDateAAMMDD(genericDataFile.getFechaProcesamiento()) + "', secuencialArchivo: '" 
		    																  + Utilidad.rellenarCerosIzquierda(genericDataFile.getSecuencialArchivo(), 2) 
		    																  + "', numeroLineaActual: '" + Utilidad.rellenarCerosIzquierda(numerolineaActual.toString(), 4) + "'");
		    Traza.auditoria(this.getClass(), "Salida:  idCampoDestino: '" + idCampoDestino + "', valor asignado: '" + secuencialReferencia + "'");
		    Traza.auditoria(this.getClass(), "");
			
			
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();

			throw new NegocioExcepcion("Hubo un error desconocido al aplicar la logica de 'CalculoSecuencialPorReferencia': " + e.getMessage(), e);
		}
	}

	/**
	 * @return the idCampoDestino
	 */
	public String getIdCampoDestino() {
		return idCampoDestino;
	}

	/**
	 * @param idCampoDestino the idCampoDestino to set
	 */
	public void setIdCampoDestino(String idCampoDestino) {
		this.idCampoDestino = idCampoDestino;
	}

	/**
	 * @return the stepExecution
	 */
	public StepExecution getStepExecution() {
		return stepExecution;
	}

	/**
	 * @param stepExecution the stepExecution to set
	 */
	public void setStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}
}
