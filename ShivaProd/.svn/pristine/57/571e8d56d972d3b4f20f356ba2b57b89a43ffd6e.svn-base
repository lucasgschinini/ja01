package ar.com.telecom.shiva.negocio.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ar.com.telecom.shiva.base.comparador.ComparatorVistaSoporteConsultaCapPdfCap;
import ar.com.telecom.shiva.base.comparador.ComparatorVistaSoporteConsultaDeudaPdfCap;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaCapPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaDeudaPdfCap;

public class CobroCompensacionPdfCap implements Serializable {
	private static final long serialVersionUID = 20170118L;

	
	private MonedaEnum moneda;
	private Date fecha;
	private String detalleFirma;
	private String usuarioVerificador;
	private Long idOperacion;
	private String idInternoSAP;
	private ClienteDireccionVo cliente = new ClienteDireccionVo();
	private List<String> clientesAdicionales = new ArrayList<String>();
	private String pathFirma = "";
	private URL urlFirma = null;
	private String pathLogo = "";
	private URL urlLogo = null;
	// Dato del resumen
	private Long orden;
	private Long yearFiscal;
	private String sectorSolicitante;
	
	// Auxilianres
	private Acumulador<Long> acuACompensar = new Acumulador<Long>();
	
	
	private List<VistaSoporteConsultaDeudaPdfCap> listaDeuda = new ArrayList<VistaSoporteConsultaDeudaPdfCap>();
	private List<VistaSoporteConsultaCapPdfCap> listaCaps = new ArrayList<VistaSoporteConsultaCapPdfCap>();
	
	
	public BigDecimal getImporteDeuda() {
		
		BigDecimal total = new BigDecimal(0);
		
		for (VistaSoporteConsultaDeudaPdfCap documento : this.listaDeuda) {
			
			total = total.add(documento.getaCompensarEnPesos());
		}
		return total;
	}
	
	public BigDecimal getImporteCaps() {
		
		BigDecimal total = new BigDecimal(0);
		
		for (VistaSoporteConsultaCapPdfCap documento : this.listaCaps) {
			
			total = total.add(documento.getaCompensarEnPesos());
		}
		return total;
	}
	
	
	public CobroCompensacionPdfCap() {
		
	}
	/**
	 * @return the idOperacion
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}
	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	/**
	 * @return the listaDeuda
	 */
	public Set<VistaSoporteConsultaDeudaPdfCap> getListaDeuda() {
		Set<VistaSoporteConsultaDeudaPdfCap> documentosOrdenados = new TreeSet<VistaSoporteConsultaDeudaPdfCap>(new ComparatorVistaSoporteConsultaDeudaPdfCap());
		

		
		
		if (Validaciones.isCollectionNotEmpty(this.listaDeuda)) {
			
			documentosOrdenados.addAll(this.listaDeuda);
		}
		
		
		return documentosOrdenados;
	}
	
	public void setListaDeuda(List<VistaSoporteConsultaDeudaPdfCap> listaDeuda) {
		this.listaDeuda = listaDeuda;
	}

	/**
	 * @return the listaCaps
	 */
	
	public List<VistaSoporteConsultaCapPdfCap> getListaCapsOriginales() {
		return this.listaCaps;
	}
	
	public Set<VistaSoporteConsultaCapPdfCap> getListaCaps() {

//		if (Validaciones.isCollectionNotEmpty(this.listaCaps)) {
//			
//			documentosOrdenados.addAll(this.listaCaps);
//		}
		
	Set<VistaSoporteConsultaCapPdfCap> documentosOrdenados = new TreeSet<VistaSoporteConsultaCapPdfCap>(new ComparatorVistaSoporteConsultaCapPdfCap());
	
//	List<VistaSoporteConsultaCapPdfCap> caps = new ArrayList<VistaSoporteConsultaCapPdfCap>();
//	for (VistaSoporteConsultaCapPdfCap cap : this.listaCaps) {
//		Boolean existe = false;
//		for (VistaSoporteConsultaCapPdfCap vista : caps) {
//			if(cap.getNumeroDocumentoSap().equals(vista.getNumeroDocumentoSap()) &&  cap.getFechaEmision().equals(vista.getFechaEmision())) {
//				vista.setaCompensarEnPesos(vista.getaCompensarEnPesos().add(cap.getaCompensarEnPesos()));
//				existe=true;
//				break;
//			}
//		}
//		if(!existe) {
//			caps.add(cap);
//		}
//	}
	if (Validaciones.isCollectionNotEmpty(this.listaCaps)) {

	documentosOrdenados.addAll(this.listaCaps);
	}

	return documentosOrdenados;
}
			
	/**
	 * @param listaCaps the listaCaps to set
	 */
	public void setListaCaps(List<VistaSoporteConsultaCapPdfCap> listaCaps) {
		this.listaCaps = listaCaps;
	}
	/**
	 * @return the cliente
	 */
	public ClienteDireccionVo getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(ClienteDireccionVo cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the clientesAdicionales
	 */
	public List<String> getClientesAdicionales() {
		return clientesAdicionales;
	}
	/**
	 * @param clientesAdicionales the clientesAdicionales to set
	 */
	public void setClientesAdicionales(List<String> clientesAdicionales) {
		this.clientesAdicionales = clientesAdicionales;
	}
	
	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}
	/**
	 * @return the detalleFirma
	 */
	public String getDetalleFirma() {
		return detalleFirma;
	}
	/**
	 * @param detalleFirma the detalleFirma to set
	 */
	public void setDetalleFirma(String detalleFirma) {
		this.detalleFirma = detalleFirma;
	}
	/**
	 * @return the idInternoSAP
	 */
	public String getIdInternoSAP() {
		return idInternoSAP;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the usuarioVerificador
	 */
	public String getUsuarioVerificador() {
		return usuarioVerificador;
	}
	/**
	 * @param usuarioVerificador the usuarioVerificador to set
	 */
	public void setUsuarioVerificador(String usuarioVerificador) {
		this.usuarioVerificador = usuarioVerificador;
	}
	/**
	 * @param idInternoSAP the idInternoSAP to set
	 */
	public void setIdInternoSAP(String idInternoSAP) {
		this.idInternoSAP = idInternoSAP;
	}
	// Metodos!!!String clienteDireccion = "AV. CORRIENTES 655 PISO 6º";
	public String getClienteDireccion() {
		StringBuilder str = new StringBuilder();
		if (Validaciones.isNullEmptyOrDash(this.getCliente().getNombreCalle())) {
			str.append(" ");
		} else {
			str.append(this.getCliente().getNombreCalle());
		
		str.append(" ");
		if (!Validaciones.isNullEmptyOrDash(this.getCliente().getAltura())) {
			str.append(this.getCliente().getAltura());// Hay calles sin altura pero...	
		} else {
			str.append("S/N");
		}
		str.append(" ");
		if (!Validaciones.isNullEmptyOrDash(this.getCliente().getPiso())) {
			str.append("PISO");
			str.append(this.getCliente().getPiso());
			str.append(" ");
		}
		if (!Validaciones.isNullEmptyOrDash(this.getCliente().getPuerta())) {
			str.append("OFICINA");
			str.append(this.getCliente().getPuerta());
			str.append(" ");
		}}
		
		return str.toString();
	}
	public String getClienteLocalidad() {
		StringBuilder str = new StringBuilder();
		if (("Capital Federal").equalsIgnoreCase(this.getCliente().getDescProvincia())) {
			str.append(this.getCliente().getDescProvincia());
		} else {
			if (!Validaciones.isObjectNull(this.getCliente().getDescLocalidad())) {
				str.append(this.getCliente().getDescLocalidad());
			}
			if (!Validaciones.isObjectNull(this.getCliente().getDescProvincia())) {
				str.append("(" + this.getCliente().getDescProvincia() + ")");
			}
		}
		return str.toString();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CobroCompensacionPdfCapVo [moneda=" + moneda + ", \nfecha="
				+ fecha + ", \ndetalleFirma=" + detalleFirma
				+ ", \nusuarioVerificador=" + usuarioVerificador
				+ ", \nidOperacion=" + idOperacion + ", \nidInternoSAP="
				+ idInternoSAP + ", \ncliente=" + cliente
				+ ", \nclientesAdicionales=" + clientesAdicionales
				+ "    \nDireccion " + this.getClienteDireccion()
				+ "    \nLocalicad " + this.getClienteLocalidad() + "]";
	}
	/**
	 * @return the path
	 */
	public String getPathFirma() {
		return pathFirma;
	}
	/**
	 * @param path the path to set
	 */
	public void setPathFirma(String path) {
		this.pathFirma = path;
	}
	/**
	 * @return the acuACompensar
	 */
	public Acumulador<Long> getAcuACompensar() {
		return acuACompensar;
	}
	/**
	 * @return the pathLogo
	 */
	public String getPathLogo() {
		return pathLogo;
	}
	/**
	 * @param pathLogo the pathLogo to set
	 */
	public void setPathLogo(String pathLogo) {
		this.pathLogo = pathLogo;
	}


	/**
	 * @return the urlFirma
	 */
	public URL getUrlFirma() {
		return urlFirma;
	}


	/**
	 * @param urlFirma the urlFirma to set
	 */
	public void setUrlFirma(URL urlFirma) {
		this.urlFirma = urlFirma;
	}


	/**
	 * @return the urlLogo
	 */
	public URL getUrlLogo() {
		return urlLogo;
	}


	/**
	 * @param urlLogo the urlLogo to set
	 */
	public void setUrlLogo(URL urlLogo) {
		this.urlLogo = urlLogo;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Long fiscalYear, Long orden) {
		this.orden = orden;
		this.yearFiscal = fiscalYear;
	}
	
	public String getOrden() {
		if (Validaciones.isObjectNull(this.yearFiscal)) {
			return "";
		}
		return Utilidad.rellenarCerosIzquierda(orden.toString(), 2).concat("-").concat(yearFiscal.toString());
	}

	/**
	 * @return the sectorSolicitante
	 */
	public String getSectorSolicitante() {
		return sectorSolicitante;
	}

	/**
	 * @param sectorSolicitante the sectorSolicitante to set
	 */
	public void setSectorSolicitante(String sectorSolicitante) {
		this.sectorSolicitante = sectorSolicitante;
	}
}
