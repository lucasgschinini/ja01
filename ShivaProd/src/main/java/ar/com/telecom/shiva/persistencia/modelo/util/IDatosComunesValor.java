package ar.com.telecom.shiva.persistencia.modelo.util;

import java.math.BigDecimal;

public interface IDatosComunesValor {	
	
	public String getNumeroReferenciaDelValorFormateado();
	
	public String getNumeroValorFormateado();

	public String getClienteFormateado();

	public BigDecimal getImporte();

}
