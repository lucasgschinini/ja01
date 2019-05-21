package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public enum TipoValorEnum {

	BOLETA_SIN_VALOR(1,"Boleta Sin Valor"),
	BOLETA_DEPOSITO_CHEQUE(2,"Bol. Depósito p/Cheque"),
	BOLETA_DEPOSITO_CHEQUE_DIFERIDO(3,"Bol. Depósito p/Cheque Diferido"),
	BOLETA_DEPOSITO_EFECTIVO(4,"Bol. Depósito p/Efectivo"),
	CHEQUE(5,"Cheque"),
	CHEQUE_DIFERIDO(6,"Cheque Diferido"),
	EFECTIVO(7,"Efectivo"),
	TRANSFERENCIA(8,"Transferencia"),
	INTERDEPÓSITO(9,"Interdepósito"),
	RETENCIÓN_IMPUESTO(10,"Retención/Impuesto");
	
	int idTipoValor;
	String descripcion;
	
	/**
	 * 
	 * @param idTipoValor
	 * @param descripcion
	 */
	private TipoValorEnum(int idTipoValor, String descripcion) {
	    this.descripcion = descripcion;
	    this.idTipoValor = idTipoValor;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getIdTipoValorString() {
		return String.valueOf(this.idTipoValor);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getIdTipoValor(){
		return this.idTipoValor;
	}
	
	/**
	 * 
	 * @param idTipoValor
	 * @return
	 */
	public static TipoValorEnum getEnumByIdTipoValor(Long idTipoValor) {
		
		for (TipoValorEnum tipoValor : TipoValorEnum.values()) {
			if (tipoValor.getIdTipoValor() == idTipoValor) {
				return tipoValor;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static TipoValorEnum getEnumByDescripcion(String descripcion) {
		
		for (TipoValorEnum tipoValor : TipoValorEnum.values()) {
			if (tipoValor.getDescripcion().equalsIgnoreCase(descripcion)) {
				return tipoValor;
			}
		}
		return null; 
	}
	
	public static boolean esTipoAvisoDePago(TipoValorEnum valorEnum) {
		if (
			CHEQUE.equals(valorEnum) ||
			CHEQUE_DIFERIDO.equals(valorEnum) ||
			EFECTIVO.equals(valorEnum) ||
			TRANSFERENCIA.equals(valorEnum) ||
			INTERDEPÓSITO.equals(valorEnum) ||
			RETENCIÓN_IMPUESTO.equals(valorEnum)
		) {
			return true;
		}
		return false;
	}

	public static boolean esTipoBoletaConValor(TipoValorEnum valorEnum) {
		if (
			BOLETA_DEPOSITO_CHEQUE.equals(valorEnum) ||
			BOLETA_DEPOSITO_CHEQUE_DIFERIDO.equals(valorEnum) ||
			BOLETA_DEPOSITO_EFECTIVO.equals(valorEnum)
		) {
			return true;
		}
		return false;
	}

	public static boolean esTipoBoletaSinValor(TipoValorEnum valorEnum) {
		if (BOLETA_SIN_VALOR.equals(valorEnum)) {
			return true;
		}
		return false;
	}


	public static List<TipoValorEnum> obtenerListaDeTipoDeValores(String tipoDeValor) {
		
		List<TipoValorEnum> lista = new ArrayList<TipoValorEnum>();
		String[] tipoDeValoresString = tipoDeValor.split(";");
		
		for (String tipoDeValorString : tipoDeValoresString) {
			for (TipoValorEnum tipoValorEnum : TipoValorEnum.values()) {
				if (tipoValorEnum.getIdTipoValorString().equalsIgnoreCase(tipoDeValorString)) {
					lista.add(tipoValorEnum);
					break;
				}
			}
		}
		
		return lista;

	}
	public static Map<String, Map<String, Object>> convertirAMap() {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();

		for (TipoValorEnum item : TipoValorEnum.values()) {
			Map<String, Object> des = new HashMap<String, Object>();
			des.put("idTipoValor", item.getIdTipoValor());
			des.put("descripcion", item.getDescripcion());
			map.put(item.name(), des);
		}
		return map;
	}
		
}
