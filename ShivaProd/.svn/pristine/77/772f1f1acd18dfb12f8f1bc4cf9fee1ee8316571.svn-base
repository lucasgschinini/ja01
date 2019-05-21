package ar.com.telecom.shiva.presentacion.bean.validacion.editor;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;

import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.excepciones.otros.NumeroExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class LongEditor extends PropertyEditorSupport {
	
	@Override
	public void setAsText(String text) {
		if (!Validaciones.isNullOrEmpty(text)) {
			try {
				BigDecimal type = new BigDecimal(text);
				setValue(type);
			} catch (Exception e) {
				throw new NumeroExcepcion(Mensajes.ERROR_NUMERICO);
			}
		} else {
			setValue(new BigDecimal(0));
		}
    }
}
