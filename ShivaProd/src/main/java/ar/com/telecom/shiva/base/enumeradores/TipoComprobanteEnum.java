package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.constantes.Constantes;

public enum TipoComprobanteEnum {

	FAC("Factura", "F", "FC","FAC"),
	DEB("Nota de Debito", "D", "ND","DEB"),
	CRE("Nota de Crédito", "C", "NC","CRE"),
	CTA("Pago a Cuenta", "T", "","CTA"),
	DUC("DUC", "", "","DUC"),
	CON("Convenio", "", "","CONVENIO"),
	REM("Remanente", "", "","REM"),
	TOD("Todos", "", "","TODOS"),
	REI("Reintegro", "", "", "REI"),
	GAN("Envío a Ganancia", "", "", "GAN"),
	SAP("Saldo a Pagar", "", "", "SAP"),
	APM("Aplicación Manual","","","APM"),
	// Fan4up
	C_C("Cuenta Corriente", "O","C/C","C/C"),
	CDD("Conjunto de Débitos", Constantes.WHITESPACE, "CDD", "CDD"),
	DE_("DE - Factura", "F", "DE", "DE"), 			// Documentos Sap (1),
	DD_("DD - Nota de Débito", "D", "DD", "DD"),		// Documentos Sap (1),
	DL_("DL - Líquido Producto", "O", "DL", "DL"),		// Documentos Sap (1),
	DR_("DR - Factura", "F", "DR", "DR"),		// Documentos Sap (1),
	DI_("DI - Documento interno", "O", "DI", "DI"),		// Documentos Sap (1),
	DH_("DH - Saldo a Cobrar", "O", "DH", "DH"),		// Documentos Sap (1),
		
	DB_("DB - Fac Cliente FE", "F", "DB", "DB"),	// Documentos Sap (2),
	DE2("DE - NC Cliente FE", "C", "DE2", "DE2"),	// Documentos Sap (2),
	DF_("DF - ND Cliente FE", "D", "DF", "DF"),	// Documentos Sap (2),
	DG_("DG - NC Cliente", "C", "DG", "DG"),	// Documentos Sap (2),
	DZ_("DZ - Recibo cliente", "O", "DZ", "DZ"),	// Documentos Sap (2),
	TE_("TE - Extracto bancario", "O", "TE", "TE"),	// Documentos Sap (2),
	
	YC_("YC - Nota de crédito", "C", "YC", "YC"),	// Documentos Sap (2),
	YD_("YD - Nota de débito", "D", "YD", "YD"),	// Documentos Sap (2),
	YF_("YF - Factura", "F", "YF", "YF"),	// Documentos Sap (2),
	YR_("YR - Recibo Cliente", "O", "YR", "YR"),	// Documentos Sap (2),
	
	EQU("Equipo", "F", "EQU", "EQU"),
	SER("Servicio", "F", "SER", "SER");

	String descripcion;
	String sigla;
	String siglaAux;
	String valor;

	private TipoComprobanteEnum(String descripcion, String sigla, String siglaAux, String valor) {
	    this.descripcion = descripcion;
	    this.sigla = sigla;
	    this.siglaAux = siglaAux;
	    this.valor = valor;
	}
	
	public String getValor() {
	    return this.valor;
	}
	
	//Para jsp
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	public String sigla() {
	    return this.sigla;
	}
	
	public String siglaAux() {
	    return this.siglaAux;
	}
	
	public static TipoComprobanteEnum getEnumByName(String name) {
		
		for (TipoComprobanteEnum tipoComprobante : TipoComprobanteEnum.values()) {
			if (tipoComprobante.name().equalsIgnoreCase(name)) {
				return tipoComprobante;
			}
		}
		        
		return null; 
	}
	
	public static TipoComprobanteEnum getEnumBySiglaAux(String name) {
		for (TipoComprobanteEnum tipoComprobante : TipoComprobanteEnum.values()) {
			if (tipoComprobante.siglaAux().equalsIgnoreCase(name)) {
				return tipoComprobante;
			}
		}
		        
		return null; 
	}

	public static TipoComprobanteEnum getEnumByDescripcion(String descripcion) {
		for (TipoComprobanteEnum tipoComprobante : TipoComprobanteEnum.values()) {
			if (tipoComprobante.descripcion().equalsIgnoreCase(descripcion)) {
				return tipoComprobante;
			}
		}
		return null; 
	}
	
	public static TipoComprobanteEnum getEnumByValor(String valor) {
		
		for (TipoComprobanteEnum tipoComprobante : TipoComprobanteEnum.values()) {
			if (tipoComprobante.getValor().equalsIgnoreCase(valor)) {
				return tipoComprobante;
			}
		}
		        
		return null; 
	}

	//------------------------------------------------------------------
	public static List<TipoComprobanteEnum> valoresParaDebito(){
		List<TipoComprobanteEnum> lista = new ArrayList<TipoComprobanteEnum>();
		lista.add(FAC);
		lista.add(DEB);
		lista.add(DUC);
		return lista;
	}
	
	public static List<TipoComprobanteEnum> valoresParaCredito(){
		List<TipoComprobanteEnum> lista = new ArrayList<TipoComprobanteEnum>();
		lista.add(CTA);
		lista.add(CRE);
		lista.add(REM);
		return lista;
	}
	public static List<TipoComprobanteEnum> valoresParaOtrosDebito(){
		List<TipoComprobanteEnum> lista = new ArrayList<TipoComprobanteEnum>();
		lista.add(FAC);
		lista.add(DEB);
		lista.add(C_C);
		lista.add(CDD);
		lista.add(EQU);
		lista.add(SER);
		// SAP
		lista.add(DE_);
		lista.add(DD_);
		lista.add(DL_);
		
		
	
		
		lista.add(DR_);		// Documentos Sap (1),
		lista.add(DI_);		// Documentos Sap (1),

		lista.add(DB_);	// Documentos Sap (2),
		lista.add(DE2);	// Documentos Sap (2),
		lista.add(DF_);	// Documentos Sap (2),
		lista.add(DG_);	// Documentos Sap (2),
		lista.add(DZ_);	// Documentos Sap (2),
		lista.add(TE_);	// Documentos Sap (2),
		lista.add(DH_);	// Documentos Sap (2),
		lista.add(YC_);	// Documentos Sap (2),
		lista.add(YD_);	// Documentos Sap (2),
		lista.add(YF_);	// Documentos Sap (2),
		lista.add(YR_);	// Documentos Sap (2),
		return lista;
	}
	//------------------------------------------------------------------
	public static TipoComprobanteEnum[] valoresParaDebitoCalipso() {
		TipoComprobanteEnum[] conjunto = new TipoComprobanteEnum[2];
		conjunto[0] = TipoComprobanteEnum.FAC;
		conjunto[1] = TipoComprobanteEnum.DEB;
		return conjunto;
	}
	
	public static TipoComprobanteEnum[] valoresParaCreditoCalipso() {
		TipoComprobanteEnum[] conjunto = new TipoComprobanteEnum[2];
		conjunto[0] = TipoComprobanteEnum.CTA;
		conjunto[1] = TipoComprobanteEnum.CRE;
		return conjunto;
	}
	
	public static TipoComprobanteEnum[] valoresParaDebitoMic() {
		TipoComprobanteEnum[] conjunto = new TipoComprobanteEnum[3];
		conjunto[0] = TipoComprobanteEnum.FAC;
		conjunto[1] = TipoComprobanteEnum.DEB;
		conjunto[2] = TipoComprobanteEnum.DUC;
		return conjunto;
	}
	
	public static TipoComprobanteEnum[] valoresParaCreditoMic() {
		TipoComprobanteEnum[] conjunto = new TipoComprobanteEnum[2];
		conjunto[0] = TipoComprobanteEnum.CRE;
		conjunto[1] = TipoComprobanteEnum.REM;
		return conjunto;
	}
	public static Map<String, Map<String, Object>> convertirAMap() {
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String,Object>>();

		for (TipoComprobanteEnum item : TipoComprobanteEnum.valoresParaOtrosDebito()) {
			Map<String, Object> des = new HashMap<String, Object>();
			des.put("descripcion", item.getDescripcion());
			map.put(item.name(), des);
		}
		return map;
	}
}
