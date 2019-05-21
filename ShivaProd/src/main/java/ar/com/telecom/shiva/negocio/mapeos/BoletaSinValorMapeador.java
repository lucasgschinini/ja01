package ar.com.telecom.shiva.negocio.mapeos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.IBoletaDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoDao;
import ar.com.telecom.shiva.persistencia.dao.IOrigenDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoletaSinValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class BoletaSinValorMapeador extends Mapeador {
	
	private IEmpresaDao empresaDao;
	private ISegmentoDao segmentoDao;
	private IAcuerdoDao acuerdoDao;
	private IMotivoDao motivoDao;
	private IOrigenDao origenDao;
	private IBoletaDao boletaDao;
	
	@Autowired 
	private ILdapServicio ldapServicio;

	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvBolBoletaSinValor modelo = (ShvBolBoletaSinValor) vo;
		
		BoletaSinValorDto boletaDto = new BoletaSinValorDto(modelo.getBoleta().getIdBoleta());
		
		boletaDto.setId(modelo.getIdBoleta());
		boletaDto.setIdBoleta(modelo.getIdBoleta());
		//Para la concurrencia
		boletaDto.setFechaUltimaModificacion(modelo.getBoleta().getWorkFlow().getFechaUltimaModificacion());
		boletaDto.setTimeStamp(boletaDto.getTimeStampDTO());
		
		boletaDto.setTipoValor(String.valueOf(TipoValorEnum.BOLETA_SIN_VALOR.getIdTipoValor()));
		boletaDto.setIdEmpresa(modelo.getEmpresa().getIdEmpresa());
		boletaDto.setEmpresa(modelo.getEmpresa().getDescripcion());
		boletaDto.setIdSegmento(modelo.getSegmento().getIdSegmento());
		boletaDto.setSegmento(modelo.getSegmento().getDescripcion());
		boletaDto.setCodCliente(String.valueOf(modelo.getIdClienteLegado()));
		boletaDto.setCodClienteAgrupador(String.valueOf(modelo.getBoleta().getBoletaSinValor().getIdClienteSiebel()));
		boletaDto.setRazonSocialClienteAgrupador(!Validaciones.isNullOrEmpty(modelo.getRazonSocial())?modelo.getRazonSocial():"");
		boletaDto.setEmail(!Validaciones.isNullOrEmpty(modelo.getBoleta().getEmail())?modelo.getBoleta().getEmail():"");
		
		/* Analista */
		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdAnalista());
		boletaDto.setIdAnalista(modelo.getIdAnalista());
		boletaDto.setNombreAnalista(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		boletaDto.setMailAnalista(usuarioLdap != null?usuarioLdap.getMail():"");
		
		/* Copropietario */
		usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdCopropietario());
		boletaDto.setIdCopropietario(usuarioLdap!=null?modelo.getIdCopropietario():"");
		boletaDto.setCopropietario(usuarioLdap!=null?usuarioLdap.getNombreCompleto():"");
		
		boletaDto.setImporte(Utilidad.formatCurrency(modelo.getImporte(), 2) );
		boletaDto.setImporteParaComparar(modelo.getImporte());
		boletaDto.setIdMotivo(!Validaciones.isObjectNull(modelo.getMotivo())?String.valueOf(modelo.getMotivo().getIdMotivo()):"");
		boletaDto.setMotivo(!Validaciones.isObjectNull(modelo.getMotivo())?modelo.getMotivo().getDescripcion():"");
		boletaDto.setOperacionAsociada(!Validaciones.isNullOrEmpty(String.valueOf(modelo.getOperacionAsociada()))?String.valueOf(modelo.getOperacionAsociada()):"");
		boletaDto.setIdOrigen(!Validaciones.isObjectNull(modelo.getBoleta().getBoletaSinValor().getOrigen())?String.valueOf(modelo.getBoleta().getBoletaSinValor().getOrigen().getIdOrigen()):"");
		boletaDto.setOrigen(!Validaciones.isObjectNull(modelo.getBoleta().getBoletaSinValor().getOrigen())?modelo.getBoleta().getBoletaSinValor().getOrigen().getDescripcion():"");
		boletaDto.setIdAcuerdo(String.valueOf(modelo.getAcuerdo().getIdAcuerdo()));
		boletaDto.setAcuerdo(modelo.getAcuerdo().getDescripcion());
		boletaDto.setBancoDeposito(modelo.getAcuerdo().getBancoCuenta().getBanco().getDescripcion());
		boletaDto.setNroCuenta(modelo.getAcuerdo().getBancoCuenta().getCuentaBancaria());
		boletaDto.setNumeroBoleta(String.valueOf(modelo.getBoleta().getNumeroBoleta()));
		boletaDto.setFechaAlta(modelo.getBoleta().getFechaAlta());
		boletaDto.setBoletaEnviadaMailEstado(modelo.getBoleta().getEmailEnvioEstado());
		boletaDto.setBoletaImpresaEstado(modelo.getBoleta().getImpresionEstado());
		boletaDto.setObservaciones(!Validaciones.isObjectNull(modelo.getObservaciones())?modelo.getObservaciones():"");
		boletaDto.setObservacionMail(!Validaciones.isObjectNull(modelo.getBoleta().getEmailObservaciones())?modelo.getBoleta().getEmailObservaciones():"");
		boletaDto.setCodEstado(modelo.getBoleta().getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().name());
		boletaDto.setEstado(modelo.getBoleta().getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().descripcion());
		
		return boletaDto;
	}
	
	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		//PropertyTranslator.translate(dto);
		BoletaSinValorDto boletaDto = (BoletaSinValorDto) dto;
		
		ShvBolBoletaSinValor boletaModelo = (ShvBolBoletaSinValor)
				(vo != null ? vo : new ShvBolBoletaSinValor());
		try{
			boletaModelo.setIdClienteSiebel(Long.valueOf(boletaDto.getCodClienteAgrupador()));
			boletaModelo.setIdClienteLegado(Long.valueOf(boletaDto.getCodCliente()));
			boletaModelo.setRazonSocial(boletaDto.getRazonSocialClienteAgrupador());
			boletaModelo.setIdAnalista(boletaDto.getIdAnalista());
			boletaModelo.setImporte(Utilidad.stringToBigDecimal(boletaDto.getImporte()));
			boletaModelo.setObservaciones(boletaDto.getObservaciones());
			
			boletaModelo.setEmpresa(empresaDao.buscar(boletaDto.getIdEmpresa()));
			boletaModelo.setSegmento(segmentoDao.buscarSegmento(boletaDto.getIdSegmento()));
			boletaModelo.setAcuerdo(acuerdoDao.buscarAcuerdo(boletaDto.getIdAcuerdo()));
			boletaModelo.setOrigen(origenDao.buscarOrigen(boletaDto.getIdOrigen()));
			
			if(!Validaciones.isNullOrEmpty(boletaDto.getIdMotivo())){
				boletaModelo.setMotivo(motivoDao.buscarMotivo(boletaDto.getIdMotivo()));
			} else {
				boletaModelo.setMotivo(null);
			}
			if(!Validaciones.isNullOrEmpty(boletaDto.getIdCopropietario())){
				boletaModelo.setIdCopropietario(boletaDto.getIdCopropietario());
			} else {
				boletaModelo.setIdCopropietario(null);
			}
			if(!Validaciones.isNullOrEmpty(boletaDto.getOperacionAsociada())){
				boletaModelo.setOperacionAsociada(Integer.valueOf(boletaDto.getOperacionAsociada()));
			} else {
				boletaModelo.setOperacionAsociada(null);
			}
			
		}catch(Exception e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return boletaModelo;
	}

	/**
	 * Mapea de ShvValValor a boletaSinValorDto
	 * @param valores
	 * @return
	 */
	public List<BoletaSinValorDto> mapearBoletaConValor(List<ShvValValor> valores) throws NegocioExcepcion{
		List<BoletaSinValorDto> boletasDto = new ArrayList<BoletaSinValorDto>();
		for (ShvValValor valor : valores) {
			BoletaSinValorDto boletaDto = new BoletaSinValorDto();
			boletaDto.setTipoValor(String.valueOf(valor.getTipoValor().getIdTipoValor()));
			boletaDto.setCodCliente(String.valueOf(valor.getIdClienteLegado()));
			boletaDto.setImporteParaComparar(valor.getImporte());
			boletaDto.setAcuerdo(String.valueOf(valor.getAcuerdo().getIdAcuerdo()));
			
			switch (TipoValorEnum.getEnumByIdTipoValor(valor.getTipoValor().getIdTipoValor())) {
		    case BOLETA_DEPOSITO_CHEQUE:
		    	boletaDto.setNumeroBoleta(String.valueOf(valor.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta()));
		    	boletaDto.setNumeroCheque(String.valueOf(valor.getValorBoletaDepositoCheque().getNumeroCheque()));
		    	boletaDto.setFechaUltimaModificacion(valor.getValorBoletaDepositoCheque().getBoleta().getWorkFlow().getFechaUltimaModificacion());
				boletaDto.setId(valor.getValorBoletaDepositoCheque().getBoleta().getIdBoleta());
				boletaDto.setIdBoleta(valor.getValorBoletaDepositoCheque().getBoleta().getIdBoleta());
				boletaDto.setEstado(valor.getValorBoletaDepositoCheque().getBoleta().getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().descripcion());
		    	break;
		    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
		    	boletaDto.setNumeroBoleta(String.valueOf(valor.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta()));
		    	boletaDto.setNumeroCheque(String.valueOf(valor.getValorBoletaDepositoChequePD().getNumeroCheque()));
		    	boletaDto.setFechaUltimaModificacion(valor.getValorBoletaDepositoChequePD().getBoleta().getWorkFlow().getFechaUltimaModificacion());
				boletaDto.setId(valor.getValorBoletaDepositoChequePD().getBoleta().getIdBoleta());
				boletaDto.setIdBoleta(valor.getValorBoletaDepositoChequePD().getBoleta().getIdBoleta());
				boletaDto.setEstado(valor.getValorBoletaDepositoChequePD().getBoleta().getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().descripcion());
		    	break;
		    case BOLETA_DEPOSITO_EFECTIVO:
		    	boletaDto.setNumeroBoleta(String.valueOf(valor.getValorBoletaEfectivo().getBoleta().getNumeroBoleta()));
		    	boletaDto.setFechaUltimaModificacion(valor.getValorBoletaEfectivo().getBoleta().getWorkFlow().getFechaUltimaModificacion());
				boletaDto.setId(valor.getValorBoletaEfectivo().getBoleta().getIdBoleta());
				boletaDto.setIdBoleta(valor.getValorBoletaEfectivo().getBoleta().getIdBoleta());
				boletaDto.setEstado(valor.getValorBoletaEfectivo().getBoleta().getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().descripcion());
		    	break;
		    default:
			}
			
			//Para la concurrencia
			boletaDto.setTimeStamp(boletaDto.getTimeStampDTO());
			
			boletasDto.add(boletaDto);
		}
		return boletasDto;
	}
	
	/**
	 * Mapea de ShvValValor a boletaSinValorDto
	 * @param valores
	 * @return
	 */
	public BoletaSinValorDto mapearBoletaConValor(ShvValValor valor) throws NegocioExcepcion{
		BoletaSinValorDto boletaDto = new BoletaSinValorDto();
		boletaDto.setTipoValor(String.valueOf(valor.getTipoValor().getIdTipoValor()));
		boletaDto.setCodCliente(String.valueOf(valor.getIdClienteLegado()));
		boletaDto.setImporteParaComparar(valor.getImporte());
		boletaDto.setAcuerdo(String.valueOf(valor.getAcuerdo().getIdAcuerdo()));
		boletaDto.setIdAcuerdo(String.valueOf(valor.getAcuerdo().getIdAcuerdo()));
		
		switch (TipoValorEnum.getEnumByIdTipoValor(valor.getTipoValor().getIdTipoValor())) {
	    case BOLETA_DEPOSITO_CHEQUE:
	    	boletaDto.setNumeroBoleta(String.valueOf(valor.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta()));
	    	boletaDto.setNumeroCheque(String.valueOf(valor.getValorBoletaDepositoCheque().getNumeroCheque()));
	    	boletaDto.setFechaUltimaModificacion(valor.getValorBoletaDepositoCheque().getBoleta().getWorkFlow().getFechaUltimaModificacion());
	    	boletaDto.setId(valor.getValorBoletaDepositoCheque().getBoleta().getIdBoleta());
	    	boletaDto.setIdBoleta(valor.getValorBoletaDepositoCheque().getBoleta().getIdBoleta());
	    	break;
	    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
	    	boletaDto.setNumeroBoleta(String.valueOf(valor.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta()));
	    	boletaDto.setNumeroCheque(String.valueOf(valor.getValorBoletaDepositoChequePD().getNumeroCheque()));
	    	boletaDto.setFechaUltimaModificacion(valor.getValorBoletaDepositoChequePD().getBoleta().getWorkFlow().getFechaUltimaModificacion());
	    	boletaDto.setId(valor.getValorBoletaDepositoChequePD().getBoleta().getIdBoleta());
	    	boletaDto.setIdBoleta(valor.getValorBoletaDepositoChequePD().getBoleta().getIdBoleta());
	    	break;
	    case BOLETA_DEPOSITO_EFECTIVO:
	    	boletaDto.setNumeroBoleta(String.valueOf(valor.getValorBoletaEfectivo().getBoleta().getNumeroBoleta()));
	    	boletaDto.setFechaUltimaModificacion(valor.getValorBoletaEfectivo().getBoleta().getWorkFlow().getFechaUltimaModificacion());
	    	boletaDto.setId(valor.getValorBoletaEfectivo().getBoleta().getIdBoleta());
	    	boletaDto.setIdBoleta(valor.getValorBoletaEfectivo().getBoleta().getIdBoleta());
	    	break;
	    default:
		}
		
		//Para la concurrencia
		boletaDto.setTimeStamp(boletaDto.getTimeStampDTO());
		
		return boletaDto;
	}
	
	/**********************************************
	 *             Getters & Setters              *
	 **********************************************/
	
	public IEmpresaDao getEmpresaDao() {
		return empresaDao;
	}


	public void setEmpresaDao(IEmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}


	public ISegmentoDao getSegmentoDao() {
		return segmentoDao;
	}


	public void setSegmentoDao(ISegmentoDao segmentoDao) {
		this.segmentoDao = segmentoDao;
	}


	public IAcuerdoDao getAcuerdoDao() {
		return acuerdoDao;
	}


	public void setAcuerdoDao(IAcuerdoDao acuerdoDao) {
		this.acuerdoDao = acuerdoDao;
	}

	public IMotivoDao getMotivoDao() {
		return motivoDao;
	}

	public void setMotivoDao(IMotivoDao motivoDao) {
		this.motivoDao = motivoDao;
	}
	
	public IOrigenDao getOrigenDao() {
		return origenDao;
	}
	
	public void setOrigenDao(IOrigenDao origenDao) {
		this.origenDao = origenDao;
	}

	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}


	public IBoletaDao getBoletaDao() {
		return boletaDao;
	}


	public void setBoletaDao(IBoletaDao boletaDao) {
		this.boletaDao = boletaDao;
	}

}
