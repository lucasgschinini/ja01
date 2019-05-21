package ar.com.telecom.shiva.negocio.bean;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

public class LegajoChequeRechazadoNotificacionPdf implements Serializable {
	private static final long serialVersionUID = 20170118L;

	private Long idLegajoChequeRechazado;
	private String numCheque;
	private String bancoOrigen;
	private String nroLinea;
	private Date fechaEmision;
	private Date fechaReembolso;
	private Date fechaCreacionCarta;
	private String importeCheque;
	private String motivoRechazo;
	private ClienteDireccionVo cliente = new ClienteDireccionVo();
	private String analistaFirmante;
	private String pathFirma = "";
	private URL urlFirma = null;
	private String detalleFirma;
	private String pathLogo = "";
	private URL urlLogo = null;
	
	
	public LegajoChequeRechazadoNotificacionPdf() {
		
	}

	/**
	 * @return the numCheque
	 */
	public String getNumCheque() {
		return numCheque;
	}

	/**
	 * @param numCheque the numCheque to set
	 */
	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}

	/**
	 * @return the bancoOrigen
	 */
	public String getBancoOrigen() {
		return bancoOrigen;
	}

	/**
	 * @param bancoOrigen the bancoOrigen to set
	 */
	public void setBancoOrigen(String bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the importeCheque
	 */
	public String getImporteCheque() {
		return importeCheque;
	}

	/**
	 * @param importeCheque the importeCheque to set
	 */
	public void setImporteCheque(String importeCheque) {
		this.importeCheque = importeCheque;
	}

	/**
	 * @return the motivoRechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * @param motivoRechazo the motivoRechazo to set
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	/**
	 * @return the fechaReembolso
	 */
	public Date getFechaReembolso() {
		return fechaReembolso;
	}

	/**
	 * @param fechaReembolso the fechaReembolso to set
	 */
	public void setFechaReembolso(Date fechaReembolso) {
		this.fechaReembolso = fechaReembolso;
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
	 * @return the pathFirma
	 */
	public String getPathFirma() {
		return pathFirma;
	}

	/**
	 * @param pathFirma the pathFirma to set
	 */
	public void setPathFirma(String pathFirma) {
		this.pathFirma = pathFirma;
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
	 * @return the fechaCreacionCarta
	 */
	public Date getFechaCreacionCarta() {
		return fechaCreacionCarta;
	}

	/**
	 * @param fechaCreacionCarta the fechaCreacionCarta to set
	 */
	public void setFechaCreacionCarta(Date fechaCreacionCarta) {
		this.fechaCreacionCarta = fechaCreacionCarta;
	}

	/**
	 * @return the idLegajoChequeRechazado
	 */
	public Long getIdLegajoChequeRechazado() {
		return idLegajoChequeRechazado;
	}

	/**
	 * @param idLegajoChequeRechazado the idLegajoChequeRechazado to set
	 */
	public void setIdLegajoChequeRechazado(Long idLegajoChequeRechazado) {
		this.idLegajoChequeRechazado = idLegajoChequeRechazado;
	}

	/**
	 * @return the analistaFirmante
	 */
	public String getAnalistaFirmante() {
		return analistaFirmante;
	}

	/**
	 * @param analistaFirmante the analistaFirmante to set
	 */
	public void setAnalistaFirmante(String analistaFirmante) {
		this.analistaFirmante = analistaFirmante;
	}

	/**
	 * @return the nroLinea
	 */
	public String getNroLinea() {
		return nroLinea;
	}

	/**
	 * @param nroLinea the nroLinea to set
	 */
	public void setNroLinea(String nroLinea) {
		this.nroLinea = nroLinea;
	}

	
}
