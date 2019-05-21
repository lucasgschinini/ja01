package ar.com.telecom.shiva.negocio.bean;

public class ImportarOperacionesMasivasAuxiliar extends ValidacionesAuxiliar {
	
	private StringBuffer validacionParcial = new StringBuffer();

	public ImportarOperacionesMasivasAuxiliar() {
	}

	/**
	 * @return the validacionParcial
	 */
	public StringBuffer getValidacionParcial() {
		return validacionParcial;
	}

	/**
	 * @param validacionParcial the validacionParcial to set
	 */
	public void setValidacionParcial(StringBuffer validacionParcial) {
		this.validacionParcial = validacionParcial;
	}

}
