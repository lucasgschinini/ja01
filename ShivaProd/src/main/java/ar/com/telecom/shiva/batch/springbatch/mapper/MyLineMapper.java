package ar.com.telecom.shiva.batch.springbatch.mapper;

import org.springframework.batch.item.file.mapping.DefaultLineMapper;

public class MyLineMapper extends DefaultLineMapper<Object>  {

	/**
	 * @see org.springframework.batch.item.file.mapping.DefaultLineMapper#mapLine(java.lang.String, int)
	 */
	@Override
	public Object mapLine(String line, int lineNumber) throws Exception {
		return super.mapLine(line, lineNumber);
	}
}
