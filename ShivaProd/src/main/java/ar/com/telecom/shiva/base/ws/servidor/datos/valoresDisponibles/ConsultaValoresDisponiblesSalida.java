package ar.com.telecom.shiva.base.ws.servidor.datos.valoresDisponibles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"listaRetencionesIIBB","listaRetencionesIVA","listaRetencionesGanancias",
		"listaRetencionesSegSoc","listaImpuestosSello","listaImpuestosSeguridadHigiene",
		"listaRetencionesIVARG3349","listaImpuestosTasasMunicipales","listaCheques",
		"listaChequesDiferidos","listaDepositosEfectivo","listaTransferencias",
		"listaInterdepositos","resultadoProcesamiento"})
@XmlRootElement
public class ConsultaValoresDisponiblesSalida {
	
	@XmlElement(name = "listaRetencionesIIBB", required = true)
	private ListaRetencionesIIBB listaRetencionesIIBB = new ListaRetencionesIIBB();
	
	@XmlElement(name = "listaRetencionesIVA", required = true)
	private ListaRetencionesIVA  listaRetencionesIVA  = new ListaRetencionesIVA();
	
	@XmlElement(name = "listaRetencionesGanancias", required = true)
	private ListaRetencionesGanancias  listaRetencionesGanancias  = new ListaRetencionesGanancias();
	
	@XmlElement(name = "listaRetencionesSegSoc", required = true)
	private ListaRetencionesSegSoc  listaRetencionesSegSoc  = new ListaRetencionesSegSoc();
	
	@XmlElement(name = "listaImpuestosSello", required = true)
	private ListaImpuestosSello  listaImpuestosSello  = new ListaImpuestosSello();
	
	@XmlElement(name = "listaImpuestosSeguridadHigiene", required = true)
	private ListaImpuestosSeguridadHigiene  listaImpuestosSeguridadHigiene  = new ListaImpuestosSeguridadHigiene();
	
	@XmlElement(name = "listaRetencionesIVARG3349", required = true)
	private ListaRetencionesIVARG3349  listaRetencionesIVARG3349  = new ListaRetencionesIVARG3349();
	
	@XmlElement(name = "listaImpuestosTasasMunicipales", required = true)
	private ListaImpuestosTasasMunicipales  listaImpuestosTasasMunicipales  = new ListaImpuestosTasasMunicipales();
	
	@XmlElement(name = "listaCheques", required = true)
	private ListaCheques listaCheques  = new ListaCheques();
	
	@XmlElement(name = "listaChequesDiferidos", required = true)
	private ListaChequesDiferidos  listaChequesDiferidos  = new ListaChequesDiferidos();
	
	@XmlElement(name = "listaDepositosEfectivo", required = true)
	private ListaDepositosEfectivo  listaDepositosEfectivo  = new ListaDepositosEfectivo();
	
	@XmlElement(name = "listaTransferencias", required = true)
	private ListaTransferencias  listaTransferencias  = new ListaTransferencias();
	
	@XmlElement(name = "listaInterdepositos", required = true)
	private ListaInterdepositos  listaInterdepositos  = new ListaInterdepositos();
	
	@XmlElement(name = "resultadoProcesamiento", required = true)
	private ResultadoProcesamiento  resultadoProcesamiento;
	
	public ListaRetencionesIIBB getListaRetencionesIIBB() {
		return listaRetencionesIIBB;
	}
	public void setListaRetencionesIIBB(ListaRetencionesIIBB listaRetencionesIIBB) {
		this.listaRetencionesIIBB = listaRetencionesIIBB;
	}
	
	public ListaRetencionesIVA getListaRetencionesIVA() {
		return listaRetencionesIVA;
	}

	public void setListaRetencionesIVA(ListaRetencionesIVA listaRetencionesIVA) {
		this.listaRetencionesIVA = listaRetencionesIVA;
	}

	public ListaRetencionesGanancias getListaRetencionesGanancias() {
		return listaRetencionesGanancias;
	}

	public void setListaRetencionesGanancias(ListaRetencionesGanancias listaRetencionesGanancias) {
		this.listaRetencionesGanancias = listaRetencionesGanancias;
	}

	public ListaRetencionesSegSoc getListaRetencionesSegSoc() {
		return listaRetencionesSegSoc;
	}

	public void setListaRetencionesSegSoc(ListaRetencionesSegSoc listaRetencionesSeguridadSocial) {
		this.listaRetencionesSegSoc = listaRetencionesSeguridadSocial;
	}

	public ListaImpuestosSello getListaImpuestosSello() {
		return listaImpuestosSello;
	}

	public void setListaImpuestosSello(ListaImpuestosSello listaImpuestoAlSello) {
		this.listaImpuestosSello = listaImpuestoAlSello;
	}

	public ListaImpuestosSeguridadHigiene getListaImpuestosSeguridadHigiene() {
		return listaImpuestosSeguridadHigiene;
	}
	public void setListaImpuestosSeguridadHigiene(
			ListaImpuestosSeguridadHigiene listaImpuestosSeguridadHigiene) {
		this.listaImpuestosSeguridadHigiene = listaImpuestosSeguridadHigiene;
	}

	public ListaRetencionesIVARG3349 getListaRetencionesIVARG3349() {
		return listaRetencionesIVARG3349;
	}
	public void setListaRetencionesIVARG3349(
			ListaRetencionesIVARG3349 listaRetencionesIVARG3349) {
		this.listaRetencionesIVARG3349 = listaRetencionesIVARG3349;
	}

	public ListaImpuestosTasasMunicipales getListaImpuestosTasasMunicipales() {
		return listaImpuestosTasasMunicipales;
	}
	public void setListaImpuestosTasasMunicipales(
			ListaImpuestosTasasMunicipales listaImpuestosTasasMunicipales) {
		this.listaImpuestosTasasMunicipales = listaImpuestosTasasMunicipales;
	}

	public ListaCheques getListaCheques() {
		return listaCheques;
	}
	public void setListaCheques(ListaCheques listaCheques) {
		this.listaCheques = listaCheques;
	}

	public ListaChequesDiferidos getListaChequesDiferidos() {
		return listaChequesDiferidos;
	}
	public void setListaChequesDiferidos(ListaChequesDiferidos listaChequesDiferidos) {
		this.listaChequesDiferidos = listaChequesDiferidos;
	}

	public ListaDepositosEfectivo getListaDepositosEfectivo() {
		return listaDepositosEfectivo;
	}
	public void setListaDepositosEfectivo(
			ListaDepositosEfectivo listaDepositosEfectivo) {
		this.listaDepositosEfectivo = listaDepositosEfectivo;
	}
	
	public ListaTransferencias getListaTransferencias() {
		return listaTransferencias;
	}
	public void setListaTransferencias(ListaTransferencias listaTransferencias) {
		this.listaTransferencias = listaTransferencias;
	}

	public ListaInterdepositos getListaInterdepositos() {
		return listaInterdepositos;
	}
	public void setListaInterdepositos(ListaInterdepositos listaInterdepositos) {
		this.listaInterdepositos = listaInterdepositos;
	}
	
	public ResultadoProcesamiento getResultadoProcesamiento() {
		return resultadoProcesamiento;
	}

	public void setResultadoProcesamiento(
			ResultadoProcesamiento resultadoProcesamiento) {
		this.resultadoProcesamiento = resultadoProcesamiento;
	}
}
