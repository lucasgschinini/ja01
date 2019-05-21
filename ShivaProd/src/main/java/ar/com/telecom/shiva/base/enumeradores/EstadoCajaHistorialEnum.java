package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoCajaHistorialEnum {

//	HABILITADA ("Caja habilitada"),
//	INHABILITADA ("Caja inhabilidatada");
	
	ABIERTA ("Caja abierta"),
	CERRADA ("Caja cerrada");
	
	String descripcion;
	
	private EstadoCajaHistorialEnum(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	public String getDescripcion()
	{
		return this.descripcion;
	}
	
	public static EstadoCajaHistorialEnum getEnumByName (String name)
	{
		for (EstadoCajaHistorialEnum cajaHistorial : EstadoCajaHistorialEnum.values())
		{
			if (cajaHistorial.name().equalsIgnoreCase(name))
			{
				return cajaHistorial;
			}
		}
		return null;
	}
}
