package ar.com.telecom.shiva.negocio.batch.bean.scard;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Documentos {

	private List<Documento> documento = new ArrayList<Documento>();

	@XmlElement
	public List<Documento> getDocumento() {
		return documento;
	}

	public void setDocumento(List<Documento> documento) {
		this.documento = documento;
	}
	
	
}
