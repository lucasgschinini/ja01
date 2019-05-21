package ar.com.telecom.shiva.batch.springbatch.listener;

import java.util.regex.PatternSyntaxException;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Value;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataItem;

public class CustomItemProcessListener implements ItemProcessListener<GenericDataItem, GenericDataItem>{

	private long lineasProcesadas = 2;
	private long contadorLineasProcesadasOk = Constantes.CERO;
	private long contadorLineasErroneas = Constantes.CERO;
	
	private String listaDeCaracteresARemover;

	@Value("#{stepExecution}")
	private StepExecution stepExecution;

	@Override
	public void afterProcess(GenericDataItem arg0, GenericDataItem arg1) {
		
		contadorLineasProcesadasOk++;
		stepExecution.getJobExecution().getExecutionContext().put("contadorLineasProcesadasOk", contadorLineasProcesadasOk);
		
		Traza.auditoria(CustomItemProcessListener.class, "[PROCESSOR] Linea Procesada OK: " + lineasProcesadas);
		lineasProcesadas++;
	}

	@Override
	public void beforeProcess(GenericDataItem arg0) {
		Traza.auditoria(CustomItemProcessListener.class, " ");
		Traza.auditoria(CustomItemProcessListener.class, "[PROCESSOR] Linea: " + lineasProcesadas);
		
		if (!Validaciones.isNullEmptyOrDash(listaDeCaracteresARemover)) {

			for (String caracterARemover : listaDeCaracteresARemover.split("\\" + Constantes.SEPARADOR_PIPE)) {
				Traza.auditoria(CustomItemProcessListener.class, "Procedo a remover el caracter '" + caracterARemover + "' del registro antes de su procesamiento.");

				try {
					arg0.setFechaValor(arg0.getFechaValor().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setFechaIngreso(arg0.getFechaIngreso().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setConcepto(arg0.getConcepto().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setCodigoOperacion(arg0.getCodigoOperacion().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setComprobante(arg0.getComprobante().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setDeposito(arg0.getDeposito().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setSucursal(arg0.getSucursal().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setImporte(arg0.getImporte().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setDescripcion(arg0.getDescripcion().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setCodigoOpBanco(arg0.getCodigoOpBanco().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setPcc(arg0.getPcc().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
					arg0.setColumnaSiempreVacia(arg0.getColumnaSiempreVacia().replaceAll(caracterARemover, Constantes.EMPTY_STRING));
				} catch (PatternSyntaxException e) {
					Traza.auditoria(CustomItemProcessListener.class, "No se pudo remover el caracter '" + caracterARemover + "'. Se genera una excepcion: ");
					e.printStackTrace();
					throw e;
				}
			}
		}
		
		Object contadorLineasErroneasObject = stepExecution.getJobExecution().getExecutionContext().get("contadorLineasErroneas"); 
		
		if (!Validaciones.isObjectNull(contadorLineasErroneasObject)) {
			contadorLineasErroneas = (Long) contadorLineasErroneasObject; 
		}
		stepExecution.getJobExecution().getExecutionContext().put("lineaActual", lineasProcesadas);
	}

	@Override
	public void onProcessError(GenericDataItem value, Exception exception) {
		
		contadorLineasErroneas++;
		stepExecution.getJobExecution().getExecutionContext().put("contadorLineasErroneas", contadorLineasErroneas);
		lineasProcesadas++;
	}

	/**
	 * @return the listaDeCaracteresARemover
	 */
	public String getListaDeCaracteresARemover() {
		return listaDeCaracteresARemover;
	}

	/**
	 * @param listaDeCaracteresARemover the listaDeCaracteresARemover to set
	 */
	public void setListaDeCaracteresARemover(String caracteresARemover) {
		this.listaDeCaracteresARemover = caracteresARemover;
	}
}
