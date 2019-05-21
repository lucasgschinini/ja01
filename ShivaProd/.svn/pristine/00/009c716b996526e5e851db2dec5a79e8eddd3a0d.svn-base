package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.IBoletaSinValorValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;

public class BoletaSinValorValidacionServicioImpl implements IBoletaSinValorValidacionServicio {

	@Autowired IBoletaSinValorServicio boletaSinValorServicio;
	
	public void validacionEmpresa(String empresa) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(empresa)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}
	}

	public void validacionSegmento(String segmento) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(segmento)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}
	}

	public void validacionCodCliente(String codCliente)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(codCliente)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}
		if (!Validaciones.isNumeric(codCliente)) {
			throw new ValidacionExcepcion("","error.numerico");
		}
	}

	public void validacionRazonSocial(String razonSocial)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(razonSocial)) {
			throw new ValidacionExcepcion("","boleta.alta.mensaje.siebel.no.validado");
		}
	}

	public void validacionEmail(String email,boolean checkEmail) throws ValidacionExcepcion {
		if(!Validaciones.isNullOrEmpty(email)){
			if (!Validaciones.isNullOrEmail(email)) {
				throw new ValidacionExcepcion("","error.formatoEmail");
			}
		}else{
			if (checkEmail){
				throw new ValidacionExcepcion("","error.obligatorio");
			}
		}
	}

	public void validacionAnalista(String analista) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(analista)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}

	}

	public void validacionCopropietario(String copropietario) throws ValidacionExcepcion {
		// NO es obligatorio
	}

	public void validacionImporte(String importe) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(importe)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}
		if (!Validaciones.isImporteFormato(importe,2)) {
			throw new ValidacionExcepcion("","error.numeroConDosDigitos");
		}
	}

	public void validacionAcuerdo(String acuerdo) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(acuerdo)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}
	}

	public void validacionBancoDeposito(String bancoDeposito)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(bancoDeposito)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}

	}

	public void validacionNroCuenta(String nroCuenta)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(nroCuenta)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}

	}

	public void validacionOperacionAsociada(String operacionAsociada) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(operacionAsociada)) {
			if (!Validaciones.isNumeric(operacionAsociada)) {
				throw new ValidacionExcepcion("","error.numerico");
			}
		}
	}
	public void validacionMotivo(String motivo) throws ValidacionExcepcion {
		// NO es obligatorio
	}
	
	public void validacionOrigen(String origen) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(origen)) {
			throw new ValidacionExcepcion("","error.obligatorio");
		}
	}

	public void validacionObservaciones(String observaciones) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(observaciones)) {
			if (!Validaciones.esFormatoTexto(observaciones)) {
				throw new ValidacionExcepcion("","error.formatoTexto");
			}
		}
	}


	public void validacionCheckRadioSeleccionado(Boolean checkImprimirBoleta, Boolean checkEnviarMailBoleta) throws ValidacionExcepcion {
		if(checkImprimirBoleta==false && checkEnviarMailBoleta==false){
			throw new ValidacionExcepcion("","error.radioObligatorio");
		}
	}
	
	
	public void validacionObservacionMail(String observacionMail, Boolean checkEnviarMailBoleta)
			throws ValidacionExcepcion {
		if(!Validaciones.isObjectNull(checkEnviarMailBoleta) && checkEnviarMailBoleta.equals(true)){
			if (Validaciones.isNullOrEmpty(observacionMail)) {
				throw new ValidacionExcepcion("","error.obligatorio");
			}else{
				if (!Validaciones.esFormatoTexto(observacionMail)) {
					throw new ValidacionExcepcion("","error.formatoTexto");
				}
			}
		}
	}
	
	/**
	 * Valido la observacion mail sin check (para la modificacion)
	 * @param observacionMail
	 * @throws ValidacionExcepcion
	 */
	public void validacionObservacionMail(String observacionMail)
		throws ValidacionExcepcion {
		if(!Validaciones.isNullOrEmpty(observacionMail)){
			if (!Validaciones.esFormatoTexto(observacionMail)) {
				throw new ValidacionExcepcion("","error.formatoTexto");
			}
		}
	}
	

	public void validacionImporteDesdeHasta(BoletaSinValorFiltro boletaFiltro) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaFiltro.getImporteDesde())) {
			if (!Validaciones.isImporteFormato(boletaFiltro.getImporteDesde(),2)) {
				throw new ValidacionExcepcion("importeDesde","error.numeroConDosDigitos");
			}
			if(!Validaciones.isNullOrEmpty(boletaFiltro.getImporteHasta())){
				if (!Validaciones.isImporteFormato(boletaFiltro.getImporteHasta(),2)) {
					throw new ValidacionExcepcion("importeHasta","error.numeroConDosDigitos");
				}else{
					int resultado = Utilidad.stringToBigDecimal(boletaFiltro.getImporteDesde()).compareTo(Utilidad.stringToBigDecimal(boletaFiltro.getImporteHasta()));
					if(resultado > 0){
						throw new ValidacionExcepcion("importeDesde","error.desdeHasta");
					}
				}
			}
		}else{
			if(!Validaciones.isNullOrEmpty(boletaFiltro.getImporteHasta())){
				if (!Validaciones.isImporteFormato(boletaFiltro.getImporteHasta(),2)) {
					throw new ValidacionExcepcion("importeHasta","error.numeroConDosDigitos");
				}
			}
		}
	}

	public void validacionFechaAltaDesdeHasta(BoletaSinValorFiltro boletaFiltro) throws ValidacionExcepcion {
		long seisMesesEnMs = Constantes.$6_MESES_MS;
		if (!Validaciones.isNullOrEmpty(boletaFiltro.getFechaDesde())) {
			if (Validaciones.validarFecha(boletaFiltro.getFechaDesde())) {
				if (!Validaciones.isNullOrEmpty(boletaFiltro.getFechaHasta())) {
					if (!Validaciones.validarFecha(boletaFiltro.getFechaHasta())) {
						throw new ValidacionExcepcion("fechaHasta","error.fechaIncorrecta");
					}else{
						try {
							Date desde = Utilidad.deserializeAndFormatDate(boletaFiltro.getFechaDesde(),Utilidad.DATE_FORMAT_PICKER);
							Date hasta = Utilidad.deserializeAndFormatDate(boletaFiltro.getFechaHasta(),Utilidad.DATE_FORMAT_PICKER);
							if(hasta.before(desde)){
								throw new ValidacionExcepcion("fechaDesde","error.desdeHasta");
							}else{
								if (desde.getTime()<(hasta.getTime() - seisMesesEnMs)){
									throw new ValidacionExcepcion("fechaDesde","error.fechaDesdeHasta6MesesIncorrecto");
								}
							}
						} catch (ParseException e) {
							throw new ValidacionExcepcion(e.getMessage(),e);
						}
					}
				}
			}else{
				throw new ValidacionExcepcion("fechaDesde","error.fechaIncorrecta");
			}
		}else{
			if (!Validaciones.isNullOrEmpty(boletaFiltro.getFechaHasta())) {
				if (!Validaciones.validarFecha(boletaFiltro.getFechaHasta())) {
					throw new ValidacionExcepcion("fechaHasta","error.fechaIncorrecta");
				}
			}
		}
	}

	public void validacionFechaDesde(String desde) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(desde)){
			throw new ValidacionExcepcion("fechaDesde","error.obligatorio");
		}
	}

	public void validacionFechaHasta(String hasta) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(hasta)){
			throw new ValidacionExcepcion("fechaHasta","error.obligatorio");
		}
	}

	public void validacionCodClienteSiebel(String codClienteSiebel) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(codClienteSiebel)) {
			throw new ValidacionExcepcion("","boleta.alta.mensaje.siebel.no.validado");
		}
	}

	public void validacionModificacion(BoletaSinValorDto boletaDto) throws ValidacionExcepcion {
		try {
			if(Validaciones.isNullOrEmpty(boletaSinValorServicio.getDatosModificados(boletaDto))){
				throw new ValidacionExcepcion("", Propiedades.MENSAJES_PROPIEDADES.getString("error.noHayModificacion"));
			}
		} catch (NegocioExcepcion e) {
			throw new ValidacionExcepcion("", Propiedades.MENSAJES_PROPIEDADES.getString("error.noHayModificacion"));
		}
		
	}

	public IBoletaSinValorServicio getBoletaSinValorServicio() {
		return boletaSinValorServicio;
	}

	public void setBoletaSinValorServicio(
			IBoletaSinValorServicio boletaSinValorServicio) {
		this.boletaSinValorServicio = boletaSinValorServicio;
	}

}
