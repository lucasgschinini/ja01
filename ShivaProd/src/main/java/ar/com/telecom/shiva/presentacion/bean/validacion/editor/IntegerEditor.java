package ar.com.telecom.shiva.presentacion.bean.validacion.editor;

import java.beans.PropertyEditorSupport;

import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.otros.NumeroExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class IntegerEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) {
		if (!Validaciones.isNullOrEmpty(text)) {
			try {
				Integer type = new Integer(text);
				setValue(type);
			} catch (Exception e) {
				throw new NumeroExcepcion(Mensajes.ERROR_NUMERICO);
			}
		} else {
			setValue(null);
		}
    }
	
	@Override
	public String getAsText() {
		if (!Validaciones.isObjectNull(this.getValue())) {
			if (this.getValue() instanceof Integer) {
			    return ((Integer)this.getValue()).toString();
			} else {
				return "" + this.getValue();
			}
		}
		return null;
	}
}
