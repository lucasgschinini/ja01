package ar.com.telecom.shiva.base.jms.datos.entrada;

import ar.com.telecom.shiva.base.dto.JMS;

@SuppressWarnings("serial")
public class FacConsultaClienteEntrada 
	extends JMS {
	
	private String tipoTelefonia;
	private String tipoServicio; 
	private String numeroLinea;
	private String numeroCliente;
	private String numeroAcuerdo;
	
	private String custIntrnlNbr;
	private String apellCli;
	private String nomCli;
	private String nomCalle;
	private String nomCiudad;
	private String cuit;
	
	private String fechaEmision;
	private String cmnRtnInptCd;
	private String rtrvlCode;
	private String userOwnerInd;
	private String fstTimeLkupInd;
	
	private String natServNbr1;
	private String invArgtId1;
	private String prodBilgId;
	private String bilgElmtCd;
	private String cpiIdDsp;
	private String addlRecInd;
	
	
	public String toString() {
		String str = "[tipoServicio:"+String.valueOf(tipoServicio)+"],"
				+ "[numeroLinea:"+String.valueOf(numeroLinea)+"],"
				+ "[numeroCliente:"+String.valueOf(numeroCliente)+"],"
				+ "[numeroAcuerdo:"+String.valueOf(numeroAcuerdo)+"],"
				+ "[fechaEmision:"+String.valueOf(fechaEmision)+"],"
				+ "[cmnRtnInptCd:"+String.valueOf(cmnRtnInptCd)+"],"
				+ "[rtrvlCode:"+String.valueOf(rtrvlCode)+"],"
				+ "[userOwnerInd:"+String.valueOf(userOwnerInd)+"],"
				+ "[fstTimeLkupInd:"+String.valueOf(fstTimeLkupInd)+"]";
		return str;
	}

	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
	
	public String getTipoTelefonia() {
		return tipoTelefonia;
	}


	public void setTipoTelefonia(String tipoTelefonia) {
		this.tipoTelefonia = tipoTelefonia;
	}


	public String getTipoServicio() {
		return tipoServicio;
	}


	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}


	public String getNumeroLinea() {
		return numeroLinea;
	}


	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}


	public String getNumeroCliente() {
		return numeroCliente;
	}


	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}


	public String getNumeroAcuerdo() {
		return numeroAcuerdo;
	}


	public void setNumeroAcuerdo(String numeroAcuerdo) {
		this.numeroAcuerdo = numeroAcuerdo;
	}


	public String getCustIntrnlNbr() {
		return custIntrnlNbr;
	}


	public void setCustIntrnlNbr(String custIntrnlNbr) {
		this.custIntrnlNbr = custIntrnlNbr;
	}


	public String getApellCli() {
		return apellCli;
	}


	public void setApellCli(String apellCli) {
		this.apellCli = apellCli;
	}


	public String getNomCli() {
		return nomCli;
	}


	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}


	public String getNomCalle() {
		return nomCalle;
	}


	public void setNomCalle(String nomCalle) {
		this.nomCalle = nomCalle;
	}


	public String getNomCiudad() {
		return nomCiudad;
	}


	public void setNomCiudad(String nomCiudad) {
		this.nomCiudad = nomCiudad;
	}


	public String getCuit() {
		return cuit;
	}


	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


	public String getFechaEmision() {
		return fechaEmision;
	}


	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}


	public String getCmnRtnInptCd() {
		return cmnRtnInptCd;
	}


	public void setCmnRtnInptCd(String cmnRtnInptCd) {
		this.cmnRtnInptCd = cmnRtnInptCd;
	}


	public String getRtrvlCode() {
		return rtrvlCode;
	}


	public void setRtrvlCode(String rtrvlCode) {
		this.rtrvlCode = rtrvlCode;
	}


	public String getUserOwnerInd() {
		return userOwnerInd;
	}


	public void setUserOwnerInd(String userOwnerInd) {
		this.userOwnerInd = userOwnerInd;
	}


	public String getFstTimeLkupInd() {
		return fstTimeLkupInd;
	}


	public void setFstTimeLkupInd(String fstTimeLkupInd) {
		this.fstTimeLkupInd = fstTimeLkupInd;
	}


	public String getNatServNbr1() {
		return natServNbr1;
	}


	public void setNatServNbr1(String natServNbr1) {
		this.natServNbr1 = natServNbr1;
	}


	public String getInvArgtId1() {
		return invArgtId1;
	}


	public void setInvArgtId1(String invArgtId1) {
		this.invArgtId1 = invArgtId1;
	}


	public String getProdBilgId() {
		return prodBilgId;
	}


	public void setProdBilgId(String prodBilgId) {
		this.prodBilgId = prodBilgId;
	}


	public String getBilgElmtCd() {
		return bilgElmtCd;
	}


	public void setBilgElmtCd(String bilgElmtCd) {
		this.bilgElmtCd = bilgElmtCd;
	}


	public String getCpiIdDsp() {
		return cpiIdDsp;
	}


	public void setCpiIdDsp(String cpiIdDsp) {
		this.cpiIdDsp = cpiIdDsp;
	}


	public String getAddlRecInd() {
		return addlRecInd;
	}


	public void setAddlRecInd(String addlRecInd) {
		this.addlRecInd = addlRecInd;
	}
}
