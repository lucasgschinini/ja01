package ar.com.telecom.shiva.negocio.conciliacion.definicion;


public class CampoCoincidente {

	private String nombre;

	private Class<?> claseBoletaOrigen;
	private String atributoBoleta;
	
	private Class<?> claseRegistroDestino;
	private String atributoRegistro;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAtributoBoleta() {
		return atributoBoleta;
	}
	public void setAtributoBoleta(String atributoBoleta) {
		this.atributoBoleta = atributoBoleta;
	}
	public String getAtributoRegistro() {
		return atributoRegistro;
	}
	public void setAtributoRegistro(String atributoRegistro) {
		this.atributoRegistro = atributoRegistro;
	}
	public Class<?> getClaseBoletaOrigen() {
		return claseBoletaOrigen;
	}
	public void setClaseBoletaOrigen(Class<?> claseBoletaOrigen) {
		this.claseBoletaOrigen = claseBoletaOrigen;
	}
	public Class<?> getClaseRegistroDestino() {
		return claseRegistroDestino;
	}
	public void setClaseRegistroDestino(Class<?> claseRegistroDestino) {
		this.claseRegistroDestino = claseRegistroDestino;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((atributoBoleta == null) ? 0 : atributoBoleta.hashCode());
		result = prime
				* result
				+ ((atributoRegistro == null) ? 0 : atributoRegistro.hashCode());
		result = prime
				* result
				+ ((claseBoletaOrigen == null) ? 0 : claseBoletaOrigen
						.hashCode());
		result = prime
				* result
				+ ((claseRegistroDestino == null) ? 0 : claseRegistroDestino
						.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CampoCoincidente other = (CampoCoincidente) obj;
		if (atributoBoleta == null) {
			if (other.atributoBoleta != null)
				return false;
		} else if (!atributoBoleta.equals(other.atributoBoleta))
			return false;
		if (atributoRegistro == null) {
			if (other.atributoRegistro != null)
				return false;
		} else if (!atributoRegistro.equals(other.atributoRegistro))
			return false;
		if (claseBoletaOrigen == null) {
			if (other.claseBoletaOrigen != null)
				return false;
		} else if (!claseBoletaOrigen.equals(other.claseBoletaOrigen))
			return false;
		if (claseRegistroDestino == null) {
			if (other.claseRegistroDestino != null)
				return false;
		} else if (!claseRegistroDestino.equals(other.claseRegistroDestino))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
