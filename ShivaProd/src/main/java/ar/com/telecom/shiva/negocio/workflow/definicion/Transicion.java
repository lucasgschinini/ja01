package ar.com.telecom.shiva.negocio.workflow.definicion;



public class Transicion {
	
	private String nombre;
	private String evento;
	private Estado origen;
	private Estado destino;
	
	public Transicion() {
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public Estado getOrigen() {
		return origen;
	}

	public void setOrigen(Estado origen) {
		this.origen = origen;
	}

	public Estado getDestino() {
		return destino;
	}

	public void setDestino(Estado destino) {
		this.destino = destino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
