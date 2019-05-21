/**
 * 
 */
package ar.com.telecom.shiva.test.soa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import ar.com.telecom.shiva.base.enumeradores.TipoRenglonSapEnum;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

/**
 * @author u586743
 *
 */
public class TestMaxi extends SoporteContextoSpringTest {

	@Test
	public void test() {


		Set<String> claves = new TreeSet<String>();

		List<DocumentoCapDto> listaDocumentosCap = new ArrayList<DocumentoCapDto>();

		List<DocumentoCapDto> listaDocABorrar = new ArrayList<DocumentoCapDto>();
		
		final Long numero = 3600005287l;
		
		for(int i = 1; i < 10; i=i+2 ) {
			claves.add("M650"+String.valueOf(numero+i));
		}
				
		for (int i = 0; i<10;i++)
		{
			DocumentoCapDto nuevoDoc = new DocumentoCapDto();
			nuevoDoc.setCodigoSociedad("M650");
			nuevoDoc.setTipoRenglon(TipoRenglonSapEnum.REF);
			nuevoDoc.setNumeroDocSAPVinculado(String.valueOf(numero+i));
			listaDocumentosCap.add(nuevoDoc);
		}
		for (int i = 0; i < 5;i++)
		{
			DocumentoCapDto nuevoDoc = new DocumentoCapDto();
			
			nuevoDoc.setCodigoSociedad("M650");
			nuevoDoc.setTipoRenglon(TipoRenglonSapEnum.REF);
			nuevoDoc.setNumeroDocSAPVinculado(String.valueOf(numero+i));
			listaDocumentosCap.add(nuevoDoc);
		}


		
		
		//-------------------------------------------------

		for (DocumentoCapDto doc : listaDocumentosCap) {
			if (!claves.contains(doc.getIdPantalla())){
				listaDocABorrar.add(doc);
			}
		}

		for (DocumentoCapDto doc : listaDocABorrar) {
			listaDocumentosCap.remove(doc);
		}

		listaDocABorrar = null ;

	}

}


