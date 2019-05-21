package ar.com.telecom.shiva.base.enumeradores;

public enum EstadoCajaEnum {

	HABILITADA("Caja habilitada"),
	INHABILITADA("Caja inhabilitada");
	
	String descripcion;
	
	private EstadoCajaEnum(String descripcion)
	{
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public static  EstadoCajaEnum getEnumtByName(String name)
	{
		for (EstadoCajaEnum enumCaja : EstadoCajaEnum.values())
		{
			if (enumCaja.name().equalsIgnoreCase(name))
			{
				return enumCaja;
			}
		}
		return null;
	}
}
