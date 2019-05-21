package ar.com.telecom.shiva.negocio.batch.bean.scard;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"nota1","nota2","nota3"})

public class Notas {

	private String nota1;
	private String nota2;
	private String nota3;
	
	
	@XmlElement
	public String getNota1() {
		return nota1;
	}
	public void setNota1(String nota1) {
		this.nota1 = nota1;
	}
	@XmlElement
	public String getNota2() {
		return nota2;
	}
	public void setNota2(String nota2) {
		this.nota2 = nota2;
	}
	@XmlElement
	public String getNota3() {
		return nota3;
	}
	public void setNota3(String nota3) {
		this.nota3 = nota3;
	}
	
	
}
