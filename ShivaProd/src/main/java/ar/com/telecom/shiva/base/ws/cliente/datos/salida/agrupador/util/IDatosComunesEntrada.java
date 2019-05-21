package ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;

/**
 * 
 * @author u573005, sprint 4
 * 
 *Estas clases se usan en pantalla para no tener que discriminar entre creditos y debitos
 */
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
	@JsonSubTypes({
		@Type(value = CobroDebitoDto.class, name = "CobroDebitoDto"),
		@Type(value = CobroCreditoDto.class, name = "CobroCreditoDto"),
		@Type(value = DocumentoCapDto.class, name = "DocumentoCapDto"),
	})
public interface IDatosComunesEntrada {
	
	public IdDocumento getIdDocumentoCalipso();
	
	public String getNumeroReferenciaMic();
	
	public SistemaEnum getSistemaOrigen();

//	se utiliza para manejar los registros en la pantalla
	public String getIdPantalla();
	
	public String getClaseString();
}
