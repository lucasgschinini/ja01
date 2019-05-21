package ar.com.telecom.shiva.presentacion.bean.filtro;

public class ConfCobroCreditoFiltro extends Filtro {
	
	private static final long serialVersionUID = 1L;

	private String idsClientes;
	
	private String idsDoc;

	private String tipoMedioPago;
	
	private String sistema;
	
	private String moneda;
	
	private String credDesde;
	
	private String credHasta;
	
	private String acuerdoFac;
	
	private String monedaImporteACobrar;
	
	private String idCobro;
	
	public ConfCobroCreditoFiltro() {
		super();
		this.idsClientes = "";
		this.idsDoc = "";
		this.tipoMedioPago = "";
		this.sistema = "";
		this.moneda = "";
		this.credDesde = "";
		this.credHasta = "";
		this.acuerdoFac = "";
		this.monedaImporteACobrar="";
		this.idCobro = "";
	}
	
	public ConfCobroCreditoFiltro clone() {
		ConfCobroCreditoFiltro clone = new ConfCobroCreditoFiltro();
		clone.setTipoMedioPago(this.tipoMedioPago);
		clone.setSistema(this.sistema);
		clone.setMoneda(this.moneda);
		clone.setCredDesde(this.credDesde);
		clone.setCredHasta(this.credHasta);
		clone.setAcuerdoFac(this.acuerdoFac);
		clone.setMonedaImporteACobrar(this.monedaImporteACobrar);
		clone.setIdCobro(this.idCobro);
		return clone;
	}

	public String getIdsClientes() {
		return idsClientes;
	}

	public void setIdsClientes(String idsClientes) {
		this.idsClientes = idsClientes;
	}

	public String getIdsDoc() {
		return idsDoc;
	}

	public void setIdsDoc(String idsDoc) {
		this.idsDoc = idsDoc;
	}

	public String getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(String tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCredDesde() {
		return credDesde;
	}

	public void setCredDesde(String credDesde) {
		this.credDesde = credDesde;
	}

	public String getCredHasta() {
		return credHasta;
	}

	public void setCredHasta(String credHasta) {
		this.credHasta = credHasta;
	}

	public String getAcuerdoFac() {
		return acuerdoFac;
	}

	public void setAcuerdoFac(String acuerdoFac) {
		this.acuerdoFac = acuerdoFac;
	}

	public String getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(String idCobro) {
		this.idCobro = idCobro;
	}

	public String getMonedaImporteACobrar() {
		return monedaImporteACobrar;
	}

	public void setMonedaImporteACobrar(String monedaImporteACobrar) {
		this.monedaImporteACobrar = monedaImporteACobrar;
	}
	
}
