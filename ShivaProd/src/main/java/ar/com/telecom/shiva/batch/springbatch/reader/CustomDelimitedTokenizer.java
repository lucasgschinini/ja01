/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.reader;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

/**
 * Esta clase fue generada con el objeto de sobrecargar el método 'isQuoteCharacter()' y asi evitar que se tomen
 * caracteres de escape por defecto, como puede ser la comilla
 * 
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class CustomDelimitedTokenizer extends DelimitedLineTokenizer {

	/**
	 * @see org.springframework.batch.item.file.transform.DelimitedLineTokenizer#isQuoteCharacter(char)
	 */
	@Override
	protected boolean isQuoteCharacter(char c) {
		return false;
	}
}
