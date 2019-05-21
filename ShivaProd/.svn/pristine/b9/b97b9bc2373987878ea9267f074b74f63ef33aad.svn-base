package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

// Subtipos Compensación en el EA
public enum SubTipoCompensacionEnum {
	LIQ_PROD("LIQ_PROD", "Líquido Producto"),
	RET_LIQ_PROD("RET_LIQ_PROD", "Retribución Líquido Producto"),
	CPP_INTERNAC("CPP_INTERNAC", "CPP Internacional");

	String codigo;
	String descripcion;
	
	private SubTipoCompensacionEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	
	public static SubTipoCompensacionEnum getEnumByCodigo(String codigo) {
		for (SubTipoCompensacionEnum elem : SubTipoCompensacionEnum.values()) {
			if (elem.getCodigo().equalsIgnoreCase(codigo)) {
				return elem;
			}
		}
		return null; 
	}

	public static SubTipoCompensacionEnum getEnumByName(String name) {
		for (SubTipoCompensacionEnum enumerado : SubTipoCompensacionEnum.values()) {
			if (enumerado.name().equalsIgnoreCase(name)) {
				return enumerado;
			}
		}
		return null; 
	}

	public static List<SubTipoCompensacionEnum> getEnums() {
		List<SubTipoCompensacionEnum> lista = new ArrayList<SubTipoCompensacionEnum>();

		for (SubTipoCompensacionEnum enumerado : SubTipoCompensacionEnum.values()) {
			lista.add(enumerado);
		}
		return lista;
	}
}
