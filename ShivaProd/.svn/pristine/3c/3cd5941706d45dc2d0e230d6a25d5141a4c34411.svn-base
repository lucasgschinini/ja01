package ar.com.telecom.shiva.presentacion.bean.validacion.editor;

import org.springframework.beans.PropertyAccessException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import ar.com.telecom.shiva.base.excepciones.otros.NumeroExcepcion;


public class CustomBindingErrorProcessor implements BindingErrorProcessor {
	
	/**
	 * Error code that a missing field error (i.e. a required field not
	 * found in the list of property values) will be registered with:
	 * "required".
	 */
	public static final String MISSING_FIELD_ERROR_CODE = "required";


	public void processMissingFieldError(String missingField, BindingResult bindingResult) {
		// Create field error with code "required".
		String fixedField = bindingResult.getNestedPath() + missingField;
		String[] codes = bindingResult.resolveMessageCodes(MISSING_FIELD_ERROR_CODE, missingField);
		Object[] arguments = getArgumentsForBindError(bindingResult.getObjectName(), fixedField);
		bindingResult.addError(new FieldError(
				bindingResult.getObjectName(), fixedField, "", true,
				codes, arguments, "Field '" + fixedField + "' is required"));
	}

	public void processPropertyAccessException(PropertyAccessException ex, BindingResult bindingResult) {
		// Create field error with the exceptions's code, e.g. "typeMismatch".
		String field = ex.getPropertyName();
		String[] codes = bindingResult.resolveMessageCodes(ex.getErrorCode(), field);
		Object[] arguments = getArgumentsForBindError(bindingResult.getObjectName(), field);
		Object rejectedValue = ex.getValue();
		if (rejectedValue != null && rejectedValue.getClass().isArray()) {
			rejectedValue = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(rejectedValue));
		}
		
		// Ahi se agrega la excepcion NumeroExcepicion
		if(ex.getCause() instanceof NumeroExcepcion) {
			bindingResult.addError(new FieldError(
				bindingResult.getObjectName(), field, rejectedValue, true,
				codes, arguments, ex.getCause().getLocalizedMessage()));
		} else {
			bindingResult.addError(new FieldError(
					bindingResult.getObjectName(), field, rejectedValue, true,
					codes, arguments, ex.getLocalizedMessage()));
		}
		
	}

	/**
	 * Return FieldError arguments for a binding error on the given field.
	 * Invoked for each missing required field and each type mismatch.
	 * <p>The default implementation returns a single argument indicating the field name
	 * (of type DefaultMessageSourceResolvable, with "objectName.field" and "field" as codes).
	 * @param objectName the name of the target object
	 * @param field the field that caused the binding error
	 * @return the Object array that represents the FieldError arguments
	 * @see org.springframework.validation.FieldError#getArguments
	 * @see org.springframework.context.support.DefaultMessageSourceResolvable
	 */
	protected Object[] getArgumentsForBindError(String objectName, String field) {
		String[] codes = new String[] {objectName + Errors.NESTED_PATH_SEPARATOR + field, field};
		return new Object[] {new DefaultMessageSourceResolvable(codes, field)};
	}
}
