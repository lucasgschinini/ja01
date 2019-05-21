package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

// Tipos de documentos a compensar
public enum TipoDocumentoCapEnum {
	KB ("KB", "Factura Proveed Nac"),
	DP ("DP", "Fact Ret/Liq a Pag"),
	KC ("KC", "N Crédito Prov Nac"),
	KD ("KD", "N Débito Prov Nac"),
	KK ("KK", "Fact Compras Menores"),
	KL ("KL", "N Créd Emit Telecom"),
	KM ("KM", "N Deb Emit Telecom"),
	KN ("KN", "ND Emit Telec Multas"),
	KQ ("KQ", "Otr Docum RG 1415"),
	KR ("KR", "Otr Docum RG 3419"),
	KT ("KT", "Factura Ticket"),
	K2 ("K2", " Documento Interno"),
//	
//	K_I ("K!", ""),
//	K_II ("K¡", ""),
//	K_00 ("K=", ""),
//	SA ("SA", ""),
// NO GESTIONABLES!!!!
	DQ ("DQ", "Liq a Pagar WS"),
	K$ ("K$", "Compensación SHIVA"),
	AD ("K-", "Rendición G Menores"),
	K0 ("K0", "Antic. de gastos FF"),
	K1 ("K1", "Docum Financieros"),
	K3 ("K3", "Rendición Fondo Fijo"),
	K4 ("K4", "Tasas Impuestos IMP"),
	K5 ("K5", "Tasas Impuestos CAP"),
	K6 ("K6", "Comision recaudacion"),
	K7 ("K7", "Doc. anticipos Comex"),
	K8 ("K8", "Liquido Producto"),
	K9 ("K9", "Recarga T. Monedero"),
	KA ("KA", "Doc.Anul.Fact.Prov."),
	KE ("KE", "Factura Prov Exterio"),
	KF ("KF", "N Crédito Prov Exter"),
	KG ("KG", "N Débito Prov Exteri"),
	KH ("KH", "Factura Serv Public"),
	KI ("KI", "N Crédito Serv Publi"),

	KJ ("KJ", "Fact. de Telecentros"),
	KO ("KO", "Despacho Aduana"),
	KP ("KP", "Otr Comprob Serv Ext"),
	KS ("KS", "Pago PM o Compensac"),
	KU ("KU", "Provisiones acumulab"),
	KV ("KV", "Provis c/OC p/Gasto"),
	KW ("KW", "Provis c/OC p/FI-AA"),
	KX ("KX", "Provisiones sin OC"),
	KY ("KY", "Comisiones bancarias"),
	EX ("K!", "NC_Dif. de Cambio"),
	EQ ("K=", "FC_Dif. de Cambio"),
	QU ("K?", "NC_Dif. de Cambio"),
	KZ ("KZ", "Pago a Proveedor"),
	XE ("K¡", "ND_Dif. de Cambio"),
	SA ("SA", "Doc. Contable Manual"),
// NeedIT 71029 - Nuevos tipos de bloqueo y tipos de documentos SAP
	AB ("AB", "Doc.Anulac.Contab."),
	CC ("CC", "Conv - Not Créd Prov"),
	CD ("CD", "Conv - ND emitida"),
	CE ("CE", "Conv - NC emitida"),
	CF ("CF", "Conv - Factura Prov"),
	CI ("CI", "Conv - Docum intern"),
	DJ ("DJ", "RC Cliente Canje."),
	HR ("HR", "Módulo HR"),
	HS ("HS", "Sueldos Confidencial"),
	
	DT ("K.", "Doc_LiqTarj Corp"),
		
	MS ("K+", "Rendición Tarj Corp"),
	
	N1 ("N1", "Sueld Ajustes y Comp"),
	N2 ("N2", "Sueld Carg Sociales"),
	N3 ("N3", "Sueld Embargos"),
	N4 ("N4", "Sueldos Anticipos"),
	N5 ("N5", "Comp Sueldos a Pagar"),
	N6 ("N6", "Liquidacion Sueldos"),
	N7 ("N7", "Documento Interno"),
	N8 ("N8", "Sueldo Imp.Pciales"),
	P1 ("P1", "Aj canje Incr.CF"),
	P7 ("P7", "Dc. Gen. Prov.CANJE."),
	PE ("PE", "Prov. Exterior Canje"),
	PJ ("PJ", "Factura de Canje"),
	PK ("PK", "ND de Canje"),
	PL ("PL", "NC de Canje"),
	PY ("PY", "Compensación Canje"),
	YP ("YP", "FC Canje"),
	YQ ("YQ", "NC Canje"),
	ZA ("ZA", "Doc.Anul.Fact.Prov."),
	ZP ("ZP", "Pagos automáticos");
	
	
	
	
	
	
	
	
	
	

	String codigo;
	String descripcion;

	public String getCodigo() {
		return this.codigo;
	}
	public String getDescripcion() {
		return this.descripcion;
	}
	private TipoDocumentoCapEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	public static TipoDocumentoCapEnum getEnumByName(String name) {
		for (TipoDocumentoCapEnum emumerado : TipoDocumentoCapEnum.values()) {
			if (emumerado.name().equalsIgnoreCase(name)) {
				return emumerado;
			}
		}
		return null;
	}
	public static TipoDocumentoCapEnum getEnumByCodigo(String codigo) {
		for (TipoDocumentoCapEnum emumerado : TipoDocumentoCapEnum.values()) {
			if (emumerado.getCodigo().equalsIgnoreCase(codigo)) {
				return emumerado;
			}
		}
		return null;
	}
	public static List<TipoDocumentoCapEnum> getEnums() {
		List<TipoDocumentoCapEnum> lista = new ArrayList<TipoDocumentoCapEnum>();
		for (TipoDocumentoCapEnum enumerado : TipoDocumentoCapEnum.values()) {
			lista.add(enumerado);
		}
		return lista;
	}
	public static List<TipoDocumentoCapEnum> obtenerEnumParaCombo(){
		List<TipoDocumentoCapEnum> lista = new ArrayList<TipoDocumentoCapEnum>();
		lista.add(KB);
		lista.add(DP);
		lista.add(KC);
		lista.add(KD);
		lista.add(KK);
		lista.add(KL);
		lista.add(KM);
		lista.add(KN);
		lista.add(KQ);
		lista.add(KR);
		lista.add(KT);
		lista.add(K2);
		return lista;
	}
}
