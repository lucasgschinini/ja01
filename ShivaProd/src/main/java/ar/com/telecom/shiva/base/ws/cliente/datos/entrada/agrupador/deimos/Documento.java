package ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.EmpresaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumentoMic;

public class Documento {

	protected EmpresaEnum empresa;
	protected SistemaEnum sistema;
	protected BigDecimal importe;
	protected InfoAdicionalMedPagNoComisionables infoAdicionalMedPagNoComisionables;
	protected IdDocumentoMic idDocumentoMic;
	protected IdDocumento idDocumentoCalipso;
	
	/*************************************************
	 * Getters & Setters
	 *************************************************/
	
	public EmpresaEnum getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaEnum empresa) {
		this.empresa = empresa;
	}
	public SistemaEnum getSistema() {
		return sistema;
	}
	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public InfoAdicionalMedPagNoComisionables getInfoAdicionalMedPagNoComisionables() {
		return infoAdicionalMedPagNoComisionables;
	}
	public void setInfoAdicionalMedPagNoComisionables(
			InfoAdicionalMedPagNoComisionables infoAdicionalMedPagNoComisionables) {
		this.infoAdicionalMedPagNoComisionables = infoAdicionalMedPagNoComisionables;
	}
	public IdDocumentoMic getIdDocumentoMic() {
		return idDocumentoMic;
	}
	public void setIdDocumentoMic(IdDocumentoMic idDocumentoMic) {
		this.idDocumentoMic = idDocumentoMic;
	}
	public IdDocumento getIdDocumentoCalipso() {
		return idDocumentoCalipso;
	}
	public void setIdDocumentoCalipso(IdDocumento idDocumentoCalipso) {
		this.idDocumentoCalipso = idDocumentoCalipso;
	}	
}