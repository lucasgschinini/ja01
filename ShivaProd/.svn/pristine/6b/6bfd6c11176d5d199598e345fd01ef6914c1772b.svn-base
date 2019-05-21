package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.ValorPorReversionDto;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IOrigenDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorPorReversion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class ValorPorReversionMapeador extends Mapeador {
	
	private static final String ID_ORIGEN_REVERSION = "7";

	@Autowired
	IAcuerdoDao acuerdoDao;
	
	@Autowired
	IOrigenDao origenDao;

	@Autowired
	IBancoDao bancoDao;
	
	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvValValorPorReversion valorReversion = (ShvValValorPorReversion) vo;
		ValorPorReversionDto dto = new ValorPorReversionDto();
		dto.setIdRegistro(valorReversion.getIdValorPorReversion());
		dto.setTipoValorFormateado(TipoValorEnum.getEnumByIdTipoValor(valorReversion.getIdTipoValor()).getDescripcion());
		dto.setIdTipoValor(String.valueOf(valorReversion.getIdTipoValor()));
		try {
			if (valorReversion.getIdAcuerdo() != null){
				dto.setAcuerdo(acuerdoDao.buscarAcuerdo(String.valueOf(valorReversion.getIdAcuerdo())).getDescripcion());
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		Estado estado = valorReversion.getWorkflow().getShvWfWorkflowEstado().iterator().next().getEstado();
		
		dto.setImporte(valorReversion.getImporte());
		dto.setSaldoDisponible(valorReversion.getSaldoDisponible());
		dto.setNumeroCheque(String.valueOf(valorReversion.getNumeroCheque()));
		dto.setIdBancoOrigen(valorReversion.getIdBancoOrigen());
		if(valorReversion.getFechaDeposito()!=null)
		dto.setFechaDeposito(valorReversion.getFechaDeposito().toString());
		dto.setFechaVencimiento(valorReversion.getFechaVencimiento());
		dto.setNumeroReferencia(String.valueOf(valorReversion.getNumeroReferencia()));
		dto.setCodigoOrganismo(valorReversion.getCodigoOrganismo());
		dto.setNumeroBoleta((valorReversion.getNumeroBoleta()!=null)?String.valueOf(valorReversion.getNumeroBoleta()):null);
		dto.setCodigoInterdeposito((valorReversion.getCodigoInterdeposito() != null)?String.valueOf(valorReversion.getCodigoInterdeposito()):null);
		dto.setEstadoFormateado(valorReversion.getWorkflow().getEstado().descripcion());
		dto.setEsEstadoPendConfirmar(estado.equals(Estado.REV_PENDIENTE_CONFIRMAR));
		return dto;
	}
	
	public ValorDto mapValorDto(ShvValValorPorReversion valorReversion) throws NegocioExcepcion {
		ValorDto dto = new ValorDto();
		dto.setIdTipoValor(String.valueOf(valorReversion.getIdTipoValor()));
		dto.setTipoValor(TipoValorEnum.getEnumByIdTipoValor(valorReversion.getIdTipoValor()).getDescripcion());
		try {
			if (valorReversion.getIdAcuerdo() != null){
				ShvParamAcuerdo acuerdo = acuerdoDao.buscarAcuerdo(String.valueOf(valorReversion.getIdAcuerdo()));
				if (!Validaciones.isObjectNull(acuerdo)){
					dto.setAcuerdo(acuerdo.getDescripcion());
					dto.setBancoDeposito(acuerdo.getBancoCuenta().getBanco().getDescripcion());
					dto.setNumeroCuenta(acuerdo.getBancoCuenta().getCuentaBancaria());
				}
			}
			ShvParamOrigen origen = origenDao.buscarOrigen(ID_ORIGEN_REVERSION);
			dto.setOrigen(origen.getDescripcion());
			dto.setIdOrigen(String.valueOf(origen.getIdOrigen()));
			if(!Validaciones.isNullOrEmpty(valorReversion.getIdBancoOrigen())){
				ShvParamBanco bancoOrigen = bancoDao.buscarBanco(Utilidad.rellenarCerosIzquierda(valorReversion.getIdBancoOrigen(),4));
				if (!Validaciones.isObjectNull(bancoOrigen)){
					dto.setIdBancoOrigen(bancoOrigen.getIdBanco());
					dto.setBancoOrigen(bancoOrigen.getDescripcion());
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		dto.setIdAcuerdo(String.valueOf(valorReversion.getIdAcuerdo()));
		dto.setImporte(Utilidad.formatCurrency(valorReversion.getImporte(),2));
		dto.setSaldoDisponible(Utilidad.formatCurrency(valorReversion.getSaldoDisponible(),2));
		dto.setNumeroCheque(String.valueOf(valorReversion.getNumeroCheque()));
		if (valorReversion.getIdTipoValor().equals(Long.valueOf(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor()))){
			dto.setFechaDeposito(Utilidad.formatDatePicker(valorReversion.getFechaVencimiento()));
		} else {
			dto.setFechaDeposito(Utilidad.formatDatePicker(valorReversion.getFechaDeposito()));
		}
		dto.setFechaTransferencia(dto.getFechaDeposito());
		dto.setFechaInterdeposito(dto.getFechaDeposito());
		dto.setFechaVencimiento(Utilidad.formatDatePicker(valorReversion.getFechaVencimiento()));
		dto.setNumeroReferencia(String.valueOf(valorReversion.getNumeroReferencia()));
		dto.setCodOrganismo(valorReversion.getCodigoOrganismo());
		dto.setNumeroBoleta((valorReversion.getNumeroBoleta()!=null)?String.valueOf(valorReversion.getNumeroBoleta()):null);
		dto.setNumeroInterdepositoCdif(String.valueOf(valorReversion.getCodigoInterdeposito()));
		dto.setEstadoRegistroAvc(valorReversion.getWorkflow().getEstado().descripcion());
		dto.setTimeStamp(String.valueOf(valorReversion.getWorkflow().getFechaUltimaModificacion().getTime()));
		return dto;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		return null;
	}

	public IAcuerdoDao getAcuerdoDao() {
		return acuerdoDao;
	}

	public void setAcuerdoDao(IAcuerdoDao acuerdoDao) {
		this.acuerdoDao = acuerdoDao;
	}

	public IOrigenDao getOrigenDao() {
		return origenDao;
	}

	public void setOrigenDao(IOrigenDao origenDao) {
		this.origenDao = origenDao;
	}
	
	

}
