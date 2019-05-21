package ar.com.telecom.shiva.negocio.mapeos;

import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.IJurisdiccionDao;
import ar.com.telecom.shiva.persistencia.dao.IReciboPreImpresoDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoComprobanteDao;
import ar.com.telecom.shiva.persistencia.dao.IretencionImpuesto;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorRetencion;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class ValorRetencionMapeador extends ValorMapeador {

	@Autowired
	IJurisdiccionDao jurisdiccionDao;
	@Autowired
	ITipoComprobanteDao tipoCompronteDao;
	@Autowired
	IReciboPreImpresoDao reciboDao;

	@Autowired
	IretencionImpuesto retencionImpuestoDao;

	private static final String RETENCION_IIBB = "1";

	public DTO map(Modelo vo) throws NegocioExcepcion {
		return null;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		super.map(dto, vo);
		
		ValorDto valorDto = (ValorDto) dto;

		ShvValValorRetencion retencion = (ShvValValorRetencion) (vo != null ? vo
				: new ShvValValorRetencion());

		try {

			retencion.setParamTipoRetencionImpuesto(retencionImpuestoDao
					.buscarRetencionImpuesto(valorDto.getIdTipoImpuesto()));
			retencion.setNroConstanciaRetencion(valorDto.getNroConstancia());
			retencion.setFechaEmision((Utilidad.parseDatePickerString(valorDto
					.getFechaEmision())));

			if (!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo())) {
				retencion.setFechaRecibo((Utilidad.parseDatePickerString(valorDto.getFechaIngresoRecibo())));
			}

			if (!Validaciones.isNullOrEmpty(valorDto.getReciboPreImpreso())) {
				retencion.setReciboPreImpreso(reciboDao.buscarPorNumeroRecibo(valorDto.getReciboPreImpreso()));
			}

			if (RETENCION_IIBB.equals(valorDto.getIdTipoImpuesto())) {

				String cuitSinGuion = valorDto.getCuitIbb().replace("-", "");

				retencion.setCuit(cuitSinGuion);
				retencion.setParamJurisdiccion(jurisdiccionDao
						.listarProvinciasPorId(valorDto.getIdProvincia()));

				if (!Validaciones
						.isNullOrEmpty(valorDto.getIdTipoComprobante())) {
					retencion.setParamTipoComprobante(tipoCompronteDao
							.listarTipoComprobanteClase(valorDto
									.getIdTipoComprobante()));

				}
				if (!Validaciones.isNullOrEmpty(valorDto
						.getIdLetraComprobante())) {
					retencion.setParamTipoLetraComprobante(tipoCompronteDao
							.listarTipoLetraComprobanteClase(valorDto
									.getIdLetraComprobante()));

				}

				if (!Validaciones.isNullOrEmpty(valorDto
						.getNumeroLegalComprobante())) {

					StringTokenizer token = new StringTokenizer(
							valorDto.getNumeroLegalComprobante(), "-");

					retencion.setSucursalComprobante(token.nextToken());
					retencion.setNumeroComprobante(token.nextToken());
				}

			}

		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return retencion;
	}

	public IJurisdiccionDao getJurisdiccionDao() {
		return jurisdiccionDao;
	}

	public void setJurisdiccionDao(IJurisdiccionDao jurisdiccionDao) {
		this.jurisdiccionDao = jurisdiccionDao;
	}

	public IretencionImpuesto getRetencionImpuestoDao() {
		return retencionImpuestoDao;
	}

	public void setRetencionImpuestoDao(IretencionImpuesto retencionImpuestoDao) {
		this.retencionImpuestoDao = retencionImpuestoDao;
	}

}
