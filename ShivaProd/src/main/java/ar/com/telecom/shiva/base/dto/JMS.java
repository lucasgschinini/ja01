package ar.com.telecom.shiva.base.dto;


/**
 * Objeto SOA JMS
 */
public class JMS extends SOA {

	private static final long serialVersionUID = 4130001118020957462L;
	
	//id mensajeria para depositar en la BD
	private Integer id;
	
	public JMS(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
}
