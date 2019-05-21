package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;


public enum EmpresaEnum {

	TA("Telecom"),
	TP("Personal"),
	CV("Cablevision"),
	FT("Fibertel"),
	NX("Nextel");
	
	String descripcion;
	
	private EmpresaEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	public static EmpresaEnum getEnumByName(String name) {
		
		for (EmpresaEnum tipoComprobante : EmpresaEnum.values()) {
			if (tipoComprobante.name().equalsIgnoreCase(name)) {
				return tipoComprobante;
			}
		}
		        
		return null; 
	}

	public static List<EmpresaEnum> obtenerListaDeEmpresas(String empresa) {
		
		List<EmpresaEnum> lista = new ArrayList<EmpresaEnum>();
		String[] empresasString = empresa.split(";");
		
		for (String empresaString : empresasString) {
			for (EmpresaEnum empresaEnum : EmpresaEnum.values()) {
				if (empresaEnum.name().equalsIgnoreCase(empresaString)) {
					lista.add(empresaEnum);
					break;
				}
			}
		}
		
		return lista;
	}
	
}
