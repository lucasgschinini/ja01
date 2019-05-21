package ar.com.telecom.shiva.negocio.mapeos;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.IMapeadorUtil;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.presentacion.bean.dto.ConciliacionSugeridaDto;

public class ConciliacionSugeridaMapeador extends Mapeador implements IMapeadorUtil {

	private Map<Long, VistaSoporteResultadoBusquedaValor> listaValoresPorIdsBoletas = new HashMap<Long, VistaSoporteResultadoBusquedaValor>(0);
	
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvAvcRegistroAvcBoleta modelo = (ShvAvcRegistroAvcBoleta) vo;
		ConciliacionSugeridaDto conciliacionDto = new ConciliacionSugeridaDto();
		
		try {
			// Registro AVC
			ShvAvcRegistroAvc registroAvc = modelo.getShvAvcRegistroAvcBoletaPk().getRegistroAvc();
			conciliacionDto.setFechaUltimaModificacion(modelo.getShvAvcRegistroAvcBoletaPk().getBoleta().getWorkFlow().getFechaUltimaModificacion());
			conciliacionDto.setTimeStamp(conciliacionDto.getTimeStampDTO());
			
			conciliacionDto.setRegistroId(String.valueOf(registroAvc.getIdRegistroAvc()));
			conciliacionDto.setRegistroNroAcuerdo(registroAvc.getAcuerdo().getIdAcuerdo().toString());
			if (!Validaciones.isObjectNull(registroAvc.getCodigoCliente())){
				conciliacionDto.setRegistroNroCliente(registroAvc.getCodigoCliente());				
			} else {
				conciliacionDto.setRegistroNroCliente("-");
			}
			conciliacionDto.setRegistroNroBoleta(registroAvc.getDeposito().getNroBoleta().toString());


			if (!Validaciones.isObjectNull(registroAvc.getDeposito().getDepositoCheque())) {
				conciliacionDto.setRegistroNroCheque(registroAvc.getDeposito().getDepositoCheque().getNumeroCheque().toString());
			}else{
				if (!Validaciones.isObjectNull(registroAvc.getDeposito().getDepositoChequeDiferido())) {
					conciliacionDto.setRegistroNroCheque(registroAvc.getDeposito().getDepositoChequeDiferido().getNumeroCheque().toString());
				}else{
					conciliacionDto.setRegistroNroCheque("-");
				}
			}
				
			conciliacionDto.setRegistroImporte(registroAvc.getImporte().toString());
			
			
			// Boleta
			ShvBolBoleta boleta = modelo.getShvAvcRegistroAvcBoletaPk().getBoleta();
			
			conciliacionDto.setBoletaNroBoleta(String.valueOf(boleta.getNumeroBoleta()));
			conciliacionDto.setNombreArchivo(registroAvc.getArchivoAvc().getNombreArchivo());
			conciliacionDto.setBoletaId(String.valueOf(boleta.getIdBoleta()));

			// Boletas sin valor no tiene nro cheque. Sin embargo Boletas con valor si tiene nro de cheque
			if(!Validaciones.isObjectNull(boleta.getBoletaSinValor())){
				conciliacionDto.setBoletaNroAcuerdo(String.valueOf(boleta.getBoletaSinValor().getAcuerdo().getIdAcuerdo()));
				conciliacionDto.setBoletaNroCliente(String.valueOf(boleta.getBoletaSinValor().getIdClienteLegado()));
				conciliacionDto.setBoletaImporte(boleta.getBoletaSinValor().getImporte().toString());
				conciliacionDto.setBoletaNroCheque("-");
				
			}else if(!Validaciones.isObjectNull(boleta.getBoletaConValor())){

				VistaSoporteResultadoBusquedaValor valor = listaValoresPorIdsBoletas.get(boleta.getIdBoleta());
				if(!Validaciones.isObjectNull(valor)){		
					String nroCheque = valor.getReferenciaValor();
					String idTipoValor = valor.getIdTipoValor();
					String idAcuerdo = valor.getIdAcuerdo();
					String nroCliente = valor.getIdClienteLegado();
					BigDecimal importe = valor.getImporte();
					String importeStr = "";
					if(!Validaciones.isObjectNull(importe)){
						importeStr = importe.toString();
					}
					
					if(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor() == Integer.valueOf(idTipoValor)){
						conciliacionDto.setBoletaNroCheque("-");
					}else{
						if(!Validaciones.isNullOrEmpty(nroCheque)){
							conciliacionDto.setBoletaNroCheque(nroCheque);
						}
					}
					if(!Validaciones.isNullOrEmpty(idAcuerdo)){
						conciliacionDto.setBoletaNroAcuerdo(idAcuerdo);
					}
					if(!Validaciones.isNullOrEmpty(nroCliente)){
						conciliacionDto.setBoletaNroCliente(nroCliente);
					}
					if(!Validaciones.isNullOrEmpty(importeStr)){
						conciliacionDto.setBoletaImporte(importeStr);
					}
					
				}
			}
			
			// Diferencias
			conciliacionDto.setDiferenciaNroAcuerdo(!conciliacionDto.getRegistroNroAcuerdo()
					.equalsIgnoreCase(conciliacionDto.getBoletaNroAcuerdo()));
			conciliacionDto.setDiferenciaNroCliente(!conciliacionDto.getRegistroNroCliente()
					.equalsIgnoreCase(conciliacionDto.getBoletaNroCliente()));
			conciliacionDto.setDiferenciaNroBoleta(!conciliacionDto.getRegistroNroBoleta()
					.equalsIgnoreCase(conciliacionDto.getBoletaNroBoleta()));
			conciliacionDto.setDiferenciaNroCheque(!conciliacionDto.getRegistroNroCheque()
					.equalsIgnoreCase(conciliacionDto.getBoletaNroCheque()));
			conciliacionDto.setDiferenciaImporte(!conciliacionDto.getRegistroImporte()
					.equalsIgnoreCase(conciliacionDto.getBoletaImporte()));
			
			
			//fecha ordenamiento
			if(TipoValorEnum.INTERDEPÓSITO.equals(registroAvc.getTipoValor())){
				conciliacionDto.setFechaOrdenamiento(Utilidad.formatDateAndTimeFull(registroAvc.getInterdeposito().getFechaIngreso()));
			}else if(TipoValorEnum.TRANSFERENCIA.equals(registroAvc.getTipoValor())){
				conciliacionDto.setFechaOrdenamiento(Utilidad.formatDateAndTimeFull(registroAvc.getTransferencia().getFechaIngreso()));
			}else{
				//deposito
				conciliacionDto.setFechaOrdenamiento(Utilidad.formatDateAndTimeFull(registroAvc.getDeposito().getFechaPago()));
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return conciliacionDto;
	}

	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		return vo;
//		ArchivoAVCDto archivoAVC = (ArchivoAVCDto) dto;
//		
//		ShvAvcArchivoAvc archivosAvcModelo = (ShvAvcArchivoAvc)
//				(vo != null ? vo : new ShvAvcArchivoAvc());
//		try {
//			archivosAvcModelo.setAcuerdo(acuerdoDao.buscarAcuerdo(archivoAVC.getNroAcuerdo()));
//			archivosAvcModelo.setFechaProcesamiento(archivoAVC.getFechaProcesamiento());
//			archivosAvcModelo.setLogProcesamiento(archivoAVC.getLogProcesamiento());
//			archivosAvcModelo.setNombreArchivo(archivoAVC.getNombreArchivo());
//			archivosAvcModelo.setUsuarioProcesamiento(archivoAVC.getUsuarioProcesamiento());
//			
//		} catch (Exception e) {
//			throw new NegocioExcepcion(e.getMessage(), e);
//		}
//		return archivosAvcModelo;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void setObjeto(Object cualquierAtributo) throws NegocioExcepcion {
		listaValoresPorIdsBoletas = (Map<Long, VistaSoporteResultadoBusquedaValor>) cualquierAtributo;
	}



	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/

//	public IValorDao getValorDao() {
//		return valorDao;
//	}
//
//
//	public void setValorDao(IValorDao valorDao) {
//		this.valorDao = valorDao;
//	}
	

}
