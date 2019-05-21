package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase que se utiliza para el DTO y la lista de valores DTO (lista de valores
 * creados)
 * 
 * @author nicolas.i.voget
 * 
 */
public class ValoresDto {

	private String idValorSelected;
	private String idComprobanteSelected;
	
	private ValorDto valorDto = new ValorDto();
	private ArrayList<ValorDto> listaValoresDto = new ArrayList<ValorDto>();
	private boolean duplicado =false;
	private String errorMensajeDuplicadoTabla;
	private boolean comprobanteVacio =false;
	private boolean comprobantePathVacio =false;
	private boolean comprobanteDescripcionVacio =false;
	private MultipartFile fileComprobante;
	private boolean generarConstancia;
	private String observacionMail;
	private boolean errorRecibo;
	private boolean errorListaVacia;
	private boolean errorValorDuplicado;
	private boolean mantenerComprobantesAdjuntos = false;
	private ArrayList<String> errorValorDuplicadoMensaje = new ArrayList<String>();

	public boolean isDuplicado() {
		return duplicado;
	}

	public void setDuplicado(boolean duplicado) {
		this.duplicado = duplicado;
	}

	public ValorDto getValorDto() {
		return valorDto;
	}

	public void setValorDto(ValorDto valorDto) {
		this.valorDto = valorDto;
	}

	public ArrayList<ValorDto> getListaValoresDto() {
		return listaValoresDto;
	}

	public void setListaValoresDto(ArrayList<ValorDto> listaValoresDto) {
		this.listaValoresDto = listaValoresDto;
	}

	public ValoresDto(ValorDto valorDto, ArrayList<ValorDto> listaValoresDto) {
		super();
		this.valorDto = valorDto;
		this.listaValoresDto = listaValoresDto;
	}

	public ValoresDto() {
	}

	public boolean isComprobanteVacio() {
		return comprobanteVacio;
	}

	public void setComprobanteVacio(boolean comprobanteVacio) {
		this.comprobanteVacio = comprobanteVacio;
	}

	public MultipartFile getFileComprobante() {
		return fileComprobante;
	}

	public void setFileComprobante(MultipartFile fileComprobante) {
		this.fileComprobante = fileComprobante;
	}

	public boolean isComprobantePathVacio() {
		return comprobantePathVacio;
	}

	public void setComprobantePathVacio(boolean comprobantePathVacio) {
		this.comprobantePathVacio = comprobantePathVacio;
	}

	public boolean isComprobanteDescripcionVacio() {
		return comprobanteDescripcionVacio;
	}

	public void setComprobanteDescripcionVacio(boolean comprobanteDescripcionVacio) {
		this.comprobanteDescripcionVacio = comprobanteDescripcionVacio;
	}

	public boolean isGenerarConstancia() {
		return generarConstancia;
	}

	public void setGenerarConstancia(boolean generarConstancia) {
		this.generarConstancia = generarConstancia;
	}

	public String getObservacionMail() {
		return observacionMail;
	}

	public void setObservacionMail(String observacionMail) {
		this.observacionMail = observacionMail;
	}

	public boolean isErrorListaVacia() {
		return errorListaVacia;
	}

	public void setErrorListaVacia(boolean errorListaVacia) {
		this.errorListaVacia = errorListaVacia;
	}

	public boolean isErrorValorDuplicado() {
		return errorValorDuplicado;
	}

	public void setErrorValorDuplicado(boolean errorValorDuplicado) {
		this.errorValorDuplicado = errorValorDuplicado;
	}

	public ArrayList<String> getErrorValorDuplicadoMensaje() {
		return errorValorDuplicadoMensaje;
	}

	public void setErrorValorDuplicadoMensaje(
			ArrayList<String> errorValorDuplicadoMensaje) {
		this.errorValorDuplicadoMensaje = errorValorDuplicadoMensaje;
	}

	/**
	 * @return the errorRecibo
	 */
	public boolean isErrorRecibo() {
		return errorRecibo;
	}

	/**
	 * @param errorRecibo the errorRecibo to set
	 */
	public void setErrorRecibo(boolean errorRecibo) {
		this.errorRecibo = errorRecibo;
	}

	public String getErrorMensajeDuplicadoTabla() {
		return errorMensajeDuplicadoTabla;
	}

	public void setErrorMensajeDuplicadoTabla(String errorMensajeDuplicadoTabla) {
		this.errorMensajeDuplicadoTabla = errorMensajeDuplicadoTabla;
	}

	public String getIdValorSelected() {
		return idValorSelected;
	}

	public void setIdValorSelected(String idValorSelected) {
		this.idValorSelected = idValorSelected;
	}

	public String getIdComprobanteSelected() {
		return idComprobanteSelected;
	}

	public void setIdComprobanteSelected(String idComprobanteSelected) {
		this.idComprobanteSelected = idComprobanteSelected;
	}
	
	public boolean isMantenerComprobantesAdjuntos() {
		return mantenerComprobantesAdjuntos;
	}

	public void setMantenerComprobantesAdjuntos(boolean mantenerComprobantesAdjuntos) {
		this.mantenerComprobantesAdjuntos = mantenerComprobantesAdjuntos;
	}

	
}
