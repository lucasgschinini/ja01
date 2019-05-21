package ar.com.telecom.shiva.base.enumeradores;

/**
 * @author u578936 M.A.Uehara
 *
 */
public enum ConfCampoEnum {

	SOCIEDAD("sociedad"),
	NONEDA_OPERACION("moneda"),
	SISTEMA("sistema"),
	TIPO_DEBITO("Tipo debito"),
	NUMERO_DOCUMENTO("numeroDocumento");
	
	
	String descripcion;
	
	private ConfCampoEnum(String descripcion) {
		this.descripcion = descripcion;
	}
}
