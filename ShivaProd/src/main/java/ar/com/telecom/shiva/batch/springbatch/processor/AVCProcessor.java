/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.processor;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataItem;

/**
 * @author u591368 F.N. Quispe
 *
 */
public class AVCProcessor implements ItemProcessor<GenericDataItem, GenericDataItem> {

	@Value("#{stepExecution}")
	private StepExecution stepExecution;

	private List<IConfiguracionCampo> listaConfiguracionCampos;
	private List<IConfiguracionCalculo> listaConfiguracionCamposCalculados;
	
	
	@Override
	public GenericDataItem process(GenericDataItem regAvc) throws Exception {
		
//		// Traigo un boolean del contexto para ver si el archivo contiene errores
//        boolean hayErrorEnArchivo = false; 
//        
//        if(!Validaciones.isObjectNull(stepExecution.getJobExecution().getExecutionContext().get("tieneError"))) {
//        	hayErrorEnArchivo = true;
//        };
		
		// Seteo Nro Acuerdo, trayendolo desde el context.
		setNumeroAcuerdo(regAvc);
		
//		if (!hayErrorEnArchivo) {
			for (IConfiguracionCalculo calculo : listaConfiguracionCamposCalculados) {
		    	Traza.auditoria(this.getClass(), "Voy a ejecutar la lógica de calculo de la clase: " + calculo.getClass().getSimpleName());
				calculo.calcular(regAvc);
//			}
		}
		
		return regAvc;
	}
	
	/**
	 * @return the listaConfiguracionCamposCalculados
	 */
	public List<IConfiguracionCalculo> getListaConfiguracionCamposCalculados() {
		return listaConfiguracionCamposCalculados;
	}

	/**
	 * @param listaConfiguracionCamposCalculados the listaConfiguracionCamposCalculados to set
	 */
	public void setListaConfiguracionCamposCalculados(List<IConfiguracionCalculo> listaConfiguracionCamposCalculados) {
		this.listaConfiguracionCamposCalculados = listaConfiguracionCamposCalculados;
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
	 * 
	 * @param regAvc
	 */
	public void setNumeroAcuerdo(GenericDataItem regAvc){
		
		String numeroDeAcuerdo = stepExecution.getJobExecution().getExecutionContext().getString("nroDeAcuerdo");
    	//Integer numAcuerdoInt = Integer.parseInt(numeroDeAcuerdo);
    	//Long numAcuerdoLong = (long) numAcuerdoInt;    	
    	regAvc.setIdAcuerdoStr(numeroDeAcuerdo);
		
	}
}
