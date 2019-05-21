package ar.com.telecom.shiva.negocio.servicios.validacion;

import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;

public interface IBoletaSinValorValidacionServicio {

	void validacionEmpresa(String empresa) throws ValidacionExcepcion;
	void validacionSegmento(String segmento) throws ValidacionExcepcion;
	void validacionCodCliente(String codCliente) throws ValidacionExcepcion;
	void validacionRazonSocial(String razonSocial) throws ValidacionExcepcion;
	void validacionEmail(String email, boolean checkEmail) throws ValidacionExcepcion;
	void validacionAnalista(String analista) throws ValidacionExcepcion;
	void validacionCopropietario(String copropietario) throws ValidacionExcepcion;
	void validacionImporte(String importe) throws ValidacionExcepcion;
	void validacionAcuerdo(String acuerdo) throws ValidacionExcepcion;
	void validacionBancoDeposito(String bancoDeposito) throws ValidacionExcepcion;
	void validacionNroCuenta(String nroCuenta) throws ValidacionExcepcion;
	void validacionOperacionAsociada(String operacionAsociada) throws ValidacionExcepcion;
	void validacionOrigen(String origen) throws ValidacionExcepcion;
	void validacionObservaciones(String observaciones) throws ValidacionExcepcion;
	void validacionObservacionMail(String observacionMail, Boolean checkEnviarMailBoleta) throws ValidacionExcepcion;
	void validacionCheckRadioSeleccionado(Boolean checkImprimirBoleta, Boolean checkEnviarMailBoleta) throws ValidacionExcepcion;
	void validacionFechaAltaDesdeHasta(BoletaSinValorFiltro boletaFiltro) throws ValidacionExcepcion;
	void validacionImporteDesdeHasta(BoletaSinValorFiltro boletaFiltro) throws ValidacionExcepcion;
	void validacionObservacionMail(String observacionMail) throws ValidacionExcepcion;
	void validacionMotivo(String motivo) throws ValidacionExcepcion;
	void validacionCodClienteSiebel(String codClienteSiebel) throws ValidacionExcepcion;
	void validacionModificacion(BoletaSinValorDto boletaDto) throws ValidacionExcepcion;
	void validacionFechaDesde(String fechaDesde) throws ValidacionExcepcion;
	void validacionFechaHasta(String fechaHasta) throws ValidacionExcepcion;
}
