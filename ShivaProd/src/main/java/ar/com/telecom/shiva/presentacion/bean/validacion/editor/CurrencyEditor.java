package ar.com.telecom.shiva.presentacion.bean.validacion.editor;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.otros.NumeroExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class CurrencyEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) {
		if (!Validaciones.isNullOrEmpty(text)) {
			String texto = text.replace("$","");
			texto = texto.replace(".", "");
			texto = texto.replace(',','.');
			texto = texto.replace(" ","");
			texto = texto.trim();
			try {
				BigDecimal type = new BigDecimal(texto);
				setValue(type);
			} catch (Exception e) {
				throw new NumeroExcepcion(Mensajes.ERROR_NUMERICO);
			}
		} else {
			setValue(new BigDecimal(0));
		}
    }
	
	@Override
	public String getAsText() {
		String value = "";
		if (this.getValue() instanceof BigDecimal) {
		    value = Utilidad.getCurrencyFromBDToString((BigDecimal)this.getValue(), 2);
		} else {
			value = "" + this.getValue();
		}
		if (Validaciones.isNullOrEmpty(value)) {
			value = "0";
		}
		
		return "$"+value.replace('.', ',');
	}
}
