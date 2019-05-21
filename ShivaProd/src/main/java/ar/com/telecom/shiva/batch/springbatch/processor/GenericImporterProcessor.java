package ar.com.telecom.shiva.batch.springbatch.processor;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataItem;

public class GenericImporterProcessor implements ItemProcessor<GenericDataItem, GenericDataItem>{
	
	private List<ConfiguracionCalculoPorParseoPorPosicion> listaConfiguracionCamposCalculados;

//    private static final String NAME_MESSAGE = "el NOMBRE";
//    private static final String LAST_NAME_MESSAGE = "los APELLIDOS";
//
//    @Override
//    public Employee process(Employee readEmployee) throws Exception {
//
//        checkRequiredFields(readEmployee);
//
//        return readEmployee;
//    }
//
//    private void checkRequiredFields(Employee employee) {
//        Assert.hasLength(employee.getName(), requiredFieldError(NAME_MESSAGE, employee));
//        Assert.hasLength(employee.getLastName(), requiredFieldError(LAST_NAME_MESSAGE, employee));
//    }
//
//    private String requiredFieldError(String requiredFieldText, Employee employee) {
//        StringBuilder sb = new StringBuilder();
//
//        sb.append(": Es necesario %s en empleado. ");
//        sb.append(employee.toString());
//
//        return String.format(sb.toString(), requiredFieldText);
//    }
	

	@Override
	public GenericDataItem process(GenericDataItem item) throws Exception {
		
		System.out.println("Estoy en el GenericImporterProcessor con el registro: " + item.toString());
		
		for (ConfiguracionCalculoPorParseoPorPosicion calculo : listaConfiguracionCamposCalculados) {
			
			
			calculo.calcular(item);
			
			Traza.auditoria(this.getClass(), "Campo calculado:" + calculo.getOrdenProcesamiento());
		}
		
//		System.out.println("Acabo de pasar por el processor");
		return item;
	}

	/**
	 * @return the listaConfiguracionCamposCalculados
	 */
	public List<ConfiguracionCalculoPorParseoPorPosicion> getListaConfiguracionCamposCalculados() {
		return listaConfiguracionCamposCalculados;
	}

	/**
	 * @param listaConfiguracionCamposCalculados the listaConfiguracionCamposCalculados to set
	 */
	public void setListaConfiguracionCamposCalculados(
			List<ConfiguracionCalculoPorParseoPorPosicion> listaConfiguracionCamposCalculados) {
		this.listaConfiguracionCamposCalculados = listaConfiguracionCamposCalculados;
	}
}
