package ar.com.telecom.shiva.presentacion.bean.filtro;

public class ConfCobroDebitoFiltro extends Filtro {
	
	private static final long serialVersionUID = 1L;

	private String idsClientes;
	
	private String idsDoc;

	private String tipoDoc;
	
	private String nroDoc;
	
	private String sistema;
	
	private String moneda;
	
	private String refMIC;
	
	private String acuerdoFac;
	
	private String vencDesde;
	
	private String vencHasta;
	
	private String monedaImporteACobrar;
	
	private String idCobro;
	
	private String fechaTipoCambio;
	
	private String motivo;

	public ConfCobroDebitoFiltro() {
		super();
		this.idsClientes = "";
		this.idsDoc = "";
		this.tipoDoc = "";
		this.nroDoc = "";
		this.sistema = "";
		this.moneda = "";
		this.refMIC = "";
		this.acuerdoFac = "";
		this.vencDesde = "";
		this.vencHasta = "";
		this.monedaImporteACobrar="";
		this.idCobro = "";
		this.fechaTipoCambio="";
		this.motivo="";
	}
	
	public ConfCobroDebitoFiltro clone() {
		ConfCobroDebitoFiltro clone = new ConfCobroDebitoFiltro();
		clone.setTipoDoc(this.tipoDoc);
		clone.setNroDoc(this.nroDoc);
		clone.setSistema(this.sistema);
		clone.setMoneda(this.moneda);
		clone.setRefMIC(this.refMIC);
		clone.setAcuerdoFac(this.acuerdoFac);
		clone.setVencDesde(this.vencDesde);
		clone.setVencHasta(this.vencHasta);
		clone.setMonedaImporteACobrar(this.monedaImporteACobrar);
		clone.setIdCobro(this.idCobro);
		clone.setFechaTipoCambio(this.fechaTipoCambio);
		clone.setMotivo(this.motivo);
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

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
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

	public String getRefMIC() {
		return refMIC;
	}

	public void setRefMIC(String refMIC) {
		this.refMIC = refMIC;
	}

	public String getAcuerdoFac() {
		return acuerdoFac;
	}

	public void setAcuerdoFac(String acuerdoFac) {
		this.acuerdoFac = acuerdoFac;
	}

	public String getVencDesde() {
		return vencDesde;
	}

	public void setVencDesde(String vencDesde) {
		this.vencDesde = vencDesde;
	}

	public String getVencHasta() {
		return vencHasta;
	}

	public void setVencHasta(String vencHasta) {
		this.vencHasta = vencHasta;
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

	public String getFechaTipoCambio() {
		return fechaTipoCambio;
	}

	public void setFechaTipoCambio(String fechaTipoCambio) {
		this.fechaTipoCambio = fechaTipoCambio;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
