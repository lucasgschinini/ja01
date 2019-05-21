package ar.com.telecom.shiva.presentacion.bean.validacion;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BaseValidacion implements Validator {
	
	public boolean supports(Class<?> clazz) {
		//return LoginUsuarioSearch.class.isAssignableFrom(clazz);
		return true;
	}

	public void validate(Object target, Errors errors) {
		//LoginUsuarioSearch usuario = (LoginUsuarioSearch) target;
	}	
}
