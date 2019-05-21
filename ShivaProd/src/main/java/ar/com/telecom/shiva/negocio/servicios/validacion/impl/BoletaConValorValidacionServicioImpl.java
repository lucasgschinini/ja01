package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.IBoletaConValorValidacionServicio;
import ar.com.telecom.shiva.persistencia.dao.IJurisdiccionDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamJurisdiccion;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaConValorFiltro;

public class BoletaConValorValidacionServicioImpl implements
		IBoletaConValorValidacionServicio {

	@Autowired
	IValorServicio boletaConValorServicio;
	
	@Autowired
	IJurisdiccionDao jurisdiccionDao;
	
	public void validacionEmpresa(String empresa) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(empresa)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	public void validacionSegmento(String segmento) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(segmento)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	public void validacionCodCliente(String codCliente) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(codCliente)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isNumeric(codCliente)) {
			throw new ValidacionExcepcion("", "error.numerico");
		}
	}
	
	public void validacionCodClienteEsNumerico(String codCliente) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(codCliente)) {
			if(!Validaciones.isNumeric(codCliente)){
				throw new ValidacionExcepcion("", "error.numerico");
			}			
		}
	}

	public void validacionRazonSocial(String razonSocial) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(razonSocial)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	public void validacionEmail(String email) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(email)) {
			if (!Validaciones.isNullOrEmail(email)) {
				throw new ValidacionExcepcion("", "error.formatoEmail");
			}
		}
	}

	public void validacionEmailCheck(String email) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(email)) {
			if (!Validaciones.isNullOrEmail(email)) {
				throw new ValidacionExcepcion("", "error.formatoEmail");
			}
		} else {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	public void validacionAnalista(String analista) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(analista)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}

	}

	public void validacionCopropietario(String copropietario) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(copropietario)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	public void validacionImporte(String importe) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(importe)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isImporteFormatoValor(importe)) {
			throw new ValidacionExcepcion("", "error.numeroConDosDigitos");
		}
	}

	public void validacionAcuerdo(String acuerdo) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(acuerdo)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	public void validacionBancoDeposito(String bancoDeposito) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(bancoDeposito)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}

	}

	public void validacionBancoOrigen(String bancoOrigen) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(bancoOrigen)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}

	}

	public void validacionNroCuenta(String nroCuenta) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(nroCuenta)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}

	}

	public void validacionOperacionAsociada(String operacionAsociada) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(operacionAsociada)) {
			if (!Validaciones.isNumeric(operacionAsociada)) {
				throw new ValidacionExcepcion("", "error.numerico");
			}
		}
	}

	public void validacionOperacionAsociadaObligatoria(String operacionAsociada) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(operacionAsociada)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isNumeric(operacionAsociada)) {
			throw new ValidacionExcepcion("", "error.numerico");
		}
	}

	public void validacionNumeroDeCheque(String numeroCheque) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(numeroCheque)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isNumeric(numeroCheque)) {
			throw new ValidacionExcepcion("", "error.numerico");
		}
	}

	public void validacionMotivo(String motivo) throws ValidacionExcepcion {
		// NO es obligatorio
	}

	public void validacionOrigen(String origen) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(origen)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	/**
	 * Valida que si el campo observacion contenga algo, lo valida que sea formato texto
	 */
	public void validacionObservaciones(String observaciones) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(observaciones)) {
			if (!Validaciones.esFormatoTexto(observaciones)) {
				throw new ValidacionExcepcion("", "error.formatoTexto");
			}
		}
	}

	public void validacionCheckRadioSeleccionado(Boolean checkImprimirBoleta, Boolean checkEnviarMailBoleta) throws ValidacionExcepcion {
		if (checkImprimirBoleta == false && checkEnviarMailBoleta == false) {
			throw new ValidacionExcepcion("", "error.radioObligatorio");
		}
	}

	public void validacionObservacionMail(String observacionMail,Boolean checkEnviarMailBoleta) throws ValidacionExcepcion {
		if (!Validaciones.isObjectNull(checkEnviarMailBoleta) && checkEnviarMailBoleta.equals(true)) {
			if (Validaciones.isNullOrEmpty(observacionMail)) {
				throw new ValidacionExcepcion("", "error.obligatorio");
			} else {
				if (!Validaciones.esFormatoTexto(observacionMail)) {
					throw new ValidacionExcepcion("", "error.formatoTexto");
				}
			}
		}
	}

	/**
	 * Valido la observacion mail sin check (para la modificacion)
	 * 
	 * @param observacionMail
	 * @throws ValidacionExcepcion
	 */
	public void validacionObservacionMail(String observacionMail) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(observacionMail)) {
			if (!Validaciones.esFormatoTexto(observacionMail)) {
				throw new ValidacionExcepcion("", "error.formatoTexto");
			}
		}
	}

	public void validacionImporteDesdeHasta(BoletaConValorFiltro boletaFiltro) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaFiltro.getImporteDesde())) {
			if (!Validaciones.isImporteFormato(boletaFiltro.getImporteDesde(),2)) {
				throw new ValidacionExcepcion("importeDesde","error.numeroConDosDigitos");
			}
			if (!Validaciones.isNullOrEmpty(boletaFiltro.getImporteHasta())) {
				if (!Validaciones.isImporteFormato(boletaFiltro.getImporteHasta(), 2)) {
					throw new ValidacionExcepcion("importeHasta","error.numeroConDosDigitos");
				} else {
					int resultado = Utilidad.stringToBigDecimal(boletaFiltro.getImporteDesde()).compareTo(
								Utilidad.stringToBigDecimal(boletaFiltro.getImporteHasta()));
					if (resultado > 0) {
						throw new ValidacionExcepcion("importeDesde","error.importeDesdeHastaIncorrecto");
					}
				}
			}
		} else {
			if (!Validaciones.isNullOrEmpty(boletaFiltro.getImporteHasta())) {
				if (!Validaciones.isImporteFormato(boletaFiltro.getImporteHasta(), 2)) {
					throw new ValidacionExcepcion("importeHasta","error.numeroConDosDigitos");
				}
			}
		}
	}

	public void validacionFechaAltaDesdeHasta(BoletaConValorFiltro boletaFiltro) throws ValidacionExcepcion {
		long seisMesesEnMs = 15778500000L;
		if (!Validaciones.isNullOrEmpty(boletaFiltro.getFechaDesde())) {
			if (Validaciones.validarFecha(boletaFiltro.getFechaDesde())) {
				if (!Validaciones.isNullOrEmpty(boletaFiltro.getFechaHasta())) {
					if (!Validaciones.validarFecha(boletaFiltro.getFechaHasta())) {
						throw new ValidacionExcepcion("fechaHasta","error.fechaIncorrectaHasta");
					} else {
						try {
							Date desde = Utilidad.deserializeAndFormatDate(boletaFiltro.getFechaDesde(),Utilidad.DATE_FORMAT_PICKER);
							Date hasta = Utilidad.deserializeAndFormatDate(boletaFiltro.getFechaHasta(),Utilidad.DATE_FORMAT_PICKER);
							if (hasta.before(desde)) {
								throw new ValidacionExcepcion("fechaDesde","error.fechaDesdeHastaIncorrecto");
							} else {
								if (desde.getTime() < (hasta.getTime() - seisMesesEnMs)) {
									throw new ValidacionExcepcion("fechaDesde","error.fechaDesdeHasta6MesesIncorrecto");
								}
							}
						} catch (ParseException e) {
							throw new ValidacionExcepcion(e.getMessage(), e);
						}
					}
				}
			} else {
				throw new ValidacionExcepcion("fechaDesde","error.fechaIncorrecta");
			}
		} else {
			if (!Validaciones.isNullOrEmpty(boletaFiltro.getFechaHasta())) {
				if (!Validaciones.validarFecha(boletaFiltro.getFechaHasta())) {
					throw new ValidacionExcepcion("fechaHasta","error.fechaIncorrecta");
				}
			}
		}
	}

	public void validacionTipoValor(String boletaFiltro)throws ValidacionExcepcion {

		if (Validaciones.isNullOrEmpty(boletaFiltro)) {
			throw new ValidacionExcepcion("", "error.obligatorio");

		}
	}

	public void validacionFechaVencimiento(String boletaFiltro)throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(boletaFiltro)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.validarFecha(boletaFiltro)) {
			throw new ValidacionExcepcion("fechaHasta","error.fechaIncorrecta");
		}
	}

	public void validacionFechaRecibo(String boletaFiltro)throws ValidacionExcepcion {
		validacionFechaRecibo(boletaFiltro, true);
	}

	public void validacionFechaRecibo(String boletaFiltro, boolean obligatorio)throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(boletaFiltro)) {
			if (obligatorio) {
				throw new ValidacionExcepcion("", "error.obligatorio");
			} else {
				return;
			}
		}
		if (!Validaciones.validarFechaConParse(boletaFiltro)) {
			throw new ValidacionExcepcion("","error.fechaIncorrecta");
		}
	}

	public void validacionCodClienteSiebel(String codClienteSiebel) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(codClienteSiebel)) {
			throw new ValidacionExcepcion("","boleta.alta.mensaje.siebel.no.validado");
		}
	}
	
	public void validacionCodClienteSiebelNoValidado(String codCliente, String codClienteSiebel)
			throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(codCliente)) {
			validacionCodClienteSiebel(codClienteSiebel);
		}
	}

	public void validacionModificacion(ValorDto boletaDto)throws ValidacionExcepcion {
		try {
			if (Validaciones.isNullOrEmpty(boletaConValorServicio.getDatosModificados(boletaDto))) {
				throw new ValidacionExcepcion("",Propiedades.MENSAJES_PROPIEDADES.getString("error.noHayModificacion"));
			}
		} catch (NegocioExcepcion e) {
			if (e instanceof ValidacionExcepcion) {
				throw new ValidacionExcepcion("", ((ValidacionExcepcion)e).getLeyenda());
			}
			
			Traza.error(getClass(), "Se ha producido un error de aplicacion en la validacion de un valor modificado", e);
			throw new ValidacionExcepcion("errorGeneral", "Se ha producido un error de aplicacion en la validacion de un valor modificado");
		}

	}

	public IValorServicio getboletaConValorServicio() {
		return boletaConValorServicio;
	}

	public void setboletaConValorServicio(IValorServicio boletaConValorServicio) {
		this.boletaConValorServicio = boletaConValorServicio;
	}





	// AVISOS
	@Override
	public void validacionNumeroDocumentoContable(String numeroDocumentoContable)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(numeroDocumentoContable)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isNumeric(numeroDocumentoContable)) {
			throw new ValidacionExcepcion("", "error.numerico");

		}
	}

	@Override
	public void validacionFechaDeposito(String boletaFiltro)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(boletaFiltro)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.validarFecha(boletaFiltro)) {
			throw new ValidacionExcepcion("fechaHasta",
					"error.fechaIncorrecta");
		}
	}

	@Override
	public void validacionCuit(String cuit) throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(cuit)) {
			//U562163 - Fusion - Paquete 01
//			if (!Validaciones.isFormatoCuit(cuit)) {
//				throw new ValidacionExcepcion("", "error.formatoCuit");
//			}
			Validaciones.validarCuit(cuit);
		}
	}
	
	@Override
	public void validacionCuitObligatorio(String cuit) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(cuit)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		//U562163 - Fusion - Paquete 01
//		if (!Validaciones.isFormatoCuit(cuit)) {
//			throw new ValidacionExcepcion("", "error.formatoCuit");
//		}
		Validaciones.validarCuit(cuit);
	}
	
	public void validacionCuitIIBB(String cuit) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(cuit)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		//U562163 - Fusion - Paquete 01
//		if (!Validaciones.isFormatoCuit(cuit)) {
//			throw new ValidacionExcepcion("", "error.formatoCuit");
//		}
		Validaciones.validarCuit(cuit);
	}

	@Override
	public void validacionProvincia(String provincia)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(provincia)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	public void validacionFechaEmisionPorProvincia(Date fechaEmision, String idProvincia)
			throws ValidacionExcepcion {
		Boolean validarMesesTope = Boolean.FALSE;
        Integer cantidadMesesTope = new Integer(0);

		try {
			ShvParamJurisdiccion paramJurisdiccion = jurisdiccionDao.listarProvinciasPorId(idProvincia);
			if(paramJurisdiccion.getTopeMesesFechaEmision()!= null){
				cantidadMesesTope = paramJurisdiccion.getTopeMesesFechaEmision();
				validarMesesTope = Boolean.TRUE;
			}
		} catch (PersistenciaExcepcion e) {
			Traza.advertencia(getClass(), "No se ha encontrado la jurisdiccion de la provincia: " + idProvincia , e);
		}
        if(validarMesesTope){
    		Calendar calendarTope = new GregorianCalendar();
            calendarTope.setTimeInMillis(new Date().getTime());
            calendarTope.add(Calendar.MONTH, -cantidadMesesTope);
            calendarTope.set(Calendar.HOUR, 0);
            calendarTope.set(Calendar.MINUTE, 0);
            calendarTope.set(Calendar.SECOND, 0);
            calendarTope.set(Calendar.MILLISECOND, 0);
            Date fechaTope = calendarTope.getTime();
    		
            if (fechaEmision.compareTo(fechaTope) != 0 && fechaEmision.before(fechaTope)) {
    			throw new ValidacionExcepcion("", "error.fechaEmision.provincia.meses", new Object[] {cantidadMesesTope.toString()});
    		}
        }
	}

	@Override
	public void validacionLetraComprobante(String letraComprobante)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(letraComprobante)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}
	
	public void validacionNumeroLegalComprobante(String numeroLegalComprobante)
			throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(numeroLegalComprobante)) {
			if(!Validaciones.isFormatoRecibo(numeroLegalComprobante)){
				throw new ValidacionExcepcion("", "error.formatoRecibo");
			}
		}
	}
	
	public void validacionNumeroLegalComprobanteObligatorio(String numeroLegalComprobante)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(numeroLegalComprobante)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isFormatoRecibo(numeroLegalComprobante)) {
			throw new ValidacionExcepcion("", "error.formatoRecibo");
		}
	}

	public void validacionFechaTransferencia(String fechaTransferencia)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(fechaTransferencia)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.validarFecha(fechaTransferencia)) {
			throw new ValidacionExcepcion("fechaHasta",
					"error.fechaIncorrecta");
		}
	}

	@Override
	public void validacionNumeroReferencia(String numeroReferencia)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(numeroReferencia)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isNumeric(numeroReferencia)) {
			throw new ValidacionExcepcion("", "error.numerico");
		}
	}

	@Override
	public void validacionTipoImpuesto(String tipoImpuesto)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(tipoImpuesto)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}

	@Override
	public void validacionNumeroConstancia(String nroConstancia)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(nroConstancia)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isNumeric(nroConstancia)) {
			throw new ValidacionExcepcion("", "error.numerico");
		}
	}

	public void validacionFechaEmision(String fechaEmision)	throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(fechaEmision)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		} else if (!Validaciones.validarFecha(fechaEmision)) {
			throw new ValidacionExcepcion("fechaHasta", "error.fechaIncorrecta");
		} else {
			try {
				Date desde = Utilidad.deserializeAndFormatDate(fechaEmision, Utilidad.DATE_FORMAT_PICKER);
				Date hasta = new Date();
				if (desde.after(hasta)) {
					throw new ValidacionExcepcion("fechaEmision", "error.fechaEmisionIncorrecto");
				}
			} catch (ParseException e) {
				throw new ValidacionExcepcion(e.getMessage(), e);
			}
		}
	}
	public void validacionFechaEmisionCheque(String fechaEmision)	throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(fechaEmision)) {
			if (!Validaciones.validarFecha(fechaEmision)) {
				throw new ValidacionExcepcion("fechaEmision", "error.fechaIncorrecta");
			} else {
				try {
					Date desde = Utilidad.deserializeAndFormatDate(fechaEmision, Utilidad.DATE_FORMAT_PICKER);
					Date hasta = new Date();
					if (desde.after(hasta)) {
						throw new ValidacionExcepcion("fechaEmision", "error.fechaEmisionChequeIncorrecto");
					}
				} catch (ParseException e) {
					throw new ValidacionExcepcion(e.getMessage(), e);
				}
			}
		}
	}
	@Override
	public void validacionNumeroBoleta(String numeroBoleta)
			throws ValidacionExcepcion {
		validacionNumeroBoletaObligatorio(numeroBoleta);
		validacionNumeroBoletaNumerico(numeroBoleta);
	}
	
	private void validacionNumeroBoletaObligatorio(String numeroBoleta)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(numeroBoleta)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
	}
	
	public void validacionNumeroBoletaNumerico(String numeroBoleta)
			throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(numeroBoleta)) {
			if (!Validaciones.isNumeric(numeroBoleta)) {
				throw new ValidacionExcepcion("", "error.numerico");
			}
		}
	}

	@Override
	public void validacionListaComprobantes(
			List<ComprobanteDto> listaComprobantes) throws ValidacionExcepcion {
		if (!Validaciones.isCollectionNotEmpty(listaComprobantes)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		
	}

	@Override
	public void validacionReciboPreImpreso(String reciboPreImpreso)
			throws ValidacionExcepcion {
		if (!Validaciones.isNullOrEmpty(reciboPreImpreso)) {
			if(!Validaciones.isFormatoRecibo(reciboPreImpreso)){
				throw new ValidacionExcepcion("", "error.formatoRecibo");
			}
		}
	}

	// buscar valores

	public void validacionFechaDesde(String boletaFiltro)
			throws ValidacionExcepcion {

		if (!Validaciones.isNullOrEmpty(boletaFiltro)) {
			if (!Validaciones.validarFecha(boletaFiltro)) {
				throw new ValidacionExcepcion("fechaDesde",
						"error.fechaIncorrecta");
			}

		}

	}

	public void validacionFechaHasta(String boletaFiltro)
			throws ValidacionExcepcion {

		if (!Validaciones.isNullOrEmpty(boletaFiltro)) {

			if (!Validaciones.validarFecha(boletaFiltro)) {
				throw new ValidacionExcepcion("fechaHasta",
						"error.fechaIncorrecta");
			}
		}
	}

	public void validacionFechaDesdeHasta(String fechaDesde, String FechaHasta)
			throws ValidacionExcepcion {

		if (!Validaciones.isNullOrEmpty(fechaDesde)
				&& !Validaciones.isNullOrEmpty(FechaHasta)) {

			if (Validaciones.validarFecha(fechaDesde)
					&& Validaciones.validarFecha(FechaHasta)) {

				if (!Validaciones
						.validarFechaDesdeHasta(fechaDesde, FechaHasta)) {
					throw new ValidacionExcepcion("fechaHasta",
							"error.fechaDesdeHastaIncorrecto");

				}

			}
		}
	}

	public void validacionImporteDesdeHastaFormato(String importe)
			throws ValidacionExcepcion {

		if (!Validaciones.isNullOrEmpty(importe)){
			if (!Validaciones.isImporteFormato(importe, 2)) {
				throw new ValidacionExcepcion("", "error.numeroConDosDigitos");
			}
			
		}
		
		
	}

	public void validacionImporteDesdeHasta(String importeDesde, String importeHasta) throws ValidacionExcepcion {

		if (!Validaciones.isNullOrEmpty(importeDesde)
				&& !Validaciones.isNullOrEmpty(importeHasta)) {

			if (Validaciones.isImporteFormato(importeDesde, 2)	&& Validaciones.isImporteFormato(importeHasta, 2)) {
			//if (Validaciones.isImporteFormatoValor(importeDesde) && Validaciones.isImporteFormatoValor(importeHasta)) {
				if (!Validaciones.validacionImporteDesdeHasta(importeDesde,	importeHasta)) {
					throw new ValidacionExcepcion("importeDesde",
							"error.importeDesdeHastaIncorrecto");
				}

			}
		}
	}

	@Override
	public void validacionFechaInterdeposito(String fechaInterdeposito)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(fechaInterdeposito)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.validarFecha(fechaInterdeposito)) {
			throw new ValidacionExcepcion("fechaHasta",
					"error.fechaIncorrecta");
		}
	}

	@Override
	public void validacionNumeroInterdeposito(String numeroInterdepositoCdif)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(numeroInterdepositoCdif)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isNumeric(numeroInterdepositoCdif)) {
			throw new ValidacionExcepcion("", "error.numerico");
		}
	}

	@Override
	public void validacionCodOrganismo(String codOrganismo)
			throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(codOrganismo)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isNumeric(codOrganismo)) {
			throw new ValidacionExcepcion("", "error.numerico");
		}
	}

	@Override
	public void validacionObservacionConfirmacion(String observacionConfirmacion, boolean esObligatorio)
			throws ValidacionExcepcion {
		
		if (esObligatorio && Validaciones.isNullOrEmpty(observacionConfirmacion)) {
			throw new ValidacionExcepcion("", "error.obligatorio");
		}
		if (!Validaciones.isNullOrEmpty(observacionConfirmacion)) {
			if (!Validaciones.esFormatoTexto(observacionConfirmacion)) {
				throw new ValidacionExcepcion("", "error.formatoTexto");
			}
		}
	}
	
	@Override
	public void validarNumeroBoletaFiltro(String numero) throws ValidacionExcepcion{
		
		if(!Validaciones.isNullOrEmpty(numero)){
			if (!Validaciones.isNumeric(numero)) {
				throw new ValidacionExcepcion("", "error.numerico");
			}
			if (numero.length()>10) {
				throw new ValidacionExcepcion("", "error.tamañoMayorADiez");
			}
		}		
	}
	
	@Override
	public void validarReferenciaValorFiltro(String numero) throws ValidacionExcepcion{
		if(!Validaciones.isNullOrEmpty(numero)){
			if (!Validaciones.isAlphaNumeric(numero)) {
				throw new ValidacionExcepcion("", "error.formatoTexto");
			}
		}	
	}
	
	@Override
	public void validarIdValorFiltro(String numero) throws ValidacionExcepcion{
		if(!Validaciones.isNullOrEmpty(numero)){
			if (!Validaciones.isNumeric(numero)) {
				throw new ValidacionExcepcion("", "error.numerico");
			}
		}	
	}
	
	@Override
	public void validarIdCobroShivaFiltro(String operacionTransaccion) throws ValidacionExcepcion{
		
		if(!Validaciones.isNullOrEmpty(operacionTransaccion)){
			if (!Validaciones.isNumeric(operacionTransaccion)) {
				throw new ValidacionExcepcion("", "error.numerico");
			}
		}	
	}
	
	@Override
	public void validarReciboPreImpreso(String reciboPreImpreso) throws ValidacionExcepcion {

		if(!Validaciones.isNullOrEmpty(reciboPreImpreso)) {
			if(!Validaciones.isFormatoRecibo(reciboPreImpreso)) {
				throw new ValidacionExcepcion("", "error.formatoRecibo");
			}
		}
	}

	@Override
	public void validarFechaIngresoRecibo(String fechaIngresoRecibo) throws ValidacionExcepcion {

		if(!Validaciones.isNullOrEmpty(fechaIngresoRecibo)) {
			if(!Validaciones.validarFecha(fechaIngresoRecibo)) {
				throw new ValidacionExcepcion("", "error.fechaIncorrecta");
			}
		}
	}
	
	public IValorServicio getBoletaConValorServicio() {
		return boletaConValorServicio;
	}

	public void setBoletaConValorServicio(IValorServicio boletaConValorServicio) {
		this.boletaConValorServicio = boletaConValorServicio;
	}

	public IJurisdiccionDao getJurisdiccionDao() {
		return jurisdiccionDao;
	}

	public void setJurisdiccionDao(IJurisdiccionDao jurisdiccionDao) {
		this.jurisdiccionDao = jurisdiccionDao;
	}

}
