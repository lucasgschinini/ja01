package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoAcuerdoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcChequeDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcDeposito;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcEfectivo;

public class RegistroAVCDepositoMapeador extends Mapeador {

	@Autowired
	private RegistroAVCMapeador registroAVCMapeador;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvAvcRegistroAvc modelo = (ShvAvcRegistroAvc) vo;
		DepositoDto depositoDto = new DepositoDto(String.valueOf(modelo.getAcuerdo().getIdAcuerdo()));
		Estado estado = modelo.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado();
		depositoDto.setIdRegistro(modelo.getIdRegistroAvc());
		depositoDto.setAcuerdo(modelo.getAcuerdo().getDescripcion());
		depositoDto.setIdAcuerdo(String.valueOf(modelo.getAcuerdo().getIdAcuerdo()));
		depositoDto.setBancoDeposito(modelo.getAcuerdo().getBancoCuenta().getBanco().getDescripcion());
		depositoDto.setNumeroCuenta(modelo.getAcuerdo().getBancoCuenta().getCuentaBancaria());
		depositoDto.setImporte(modelo.getImporte());
		depositoDto.setImporteParaComparar(modelo.getImporte());
		depositoDto.setTipoValor(String.valueOf(modelo.getTipoValor().getIdTipoValor()));
		depositoDto.setFechaUltimaModificacion(modelo.getWorkFlow().getFechaUltimaModificacion());
		depositoDto.setTimeStamp(String.valueOf(modelo.getWorkFlow().getFechaUltimaModificacion().getTime()));
		depositoDto.setCodigoCliente(String.valueOf(modelo.getCodigoCliente()));
		depositoDto.setEstadoFormateado(estado.descripcion());
		depositoDto.setTipoValorFormateado(modelo.getTipoValor().getDescripcion());
		depositoDto.setEsEstadoPendConfirmar(estado.equals(Estado.AVC_PENDIENTE_CONFIRMAR_ALTA_VALOR));
		depositoDto.setNombreArchivo(modelo.getArchivoAvc().getNombreArchivo());
		
		depositoDto.setIdRecInstrumento(modelo.getDeposito().getIdRecInstrumento());
		depositoDto.setRend(modelo.getDeposito().getRend());
		depositoDto.setFechaPago(modelo.getDeposito().getFechaPago());
		depositoDto.setEstadoAcreditacion(modelo.getDeposito().getEstadoAcreditacion());
		depositoDto.setFechaAcreditacion(modelo.getDeposito().getFechaAcreditacion());
		depositoDto.setFormaPago(modelo.getDeposito().getFormaPago());
		depositoDto.setNroBoleta(modelo.getDeposito().getNroBoleta());
		depositoDto.setSucursalDeposito(modelo.getDeposito().getSucursalDeposito());
		depositoDto.setGrupoAcreedor(modelo.getDeposito().getGrupoAcreedor());
		depositoDto.setNombreCliente(modelo.getDeposito().getNombreCliente());
		depositoDto.setCodigoRechazo(modelo.getDeposito().getCodigoRechazo());
		
		/*OBSERVACIONES*/
		depositoDto.setObservacionAnulacion(modelo.getObservacionAnulacion());
		depositoDto.setObservacionConfirmarAnulacion(modelo.getObservacionConfirmarAnulacion());
		depositoDto.setObservaciones(modelo.getObservaciones());
		
		// Mapeo el efectivo, cheque o cheque diferido
		if(!Validaciones.isObjectNull(modelo.getDeposito().getDepositoCheque())){
			//Cheque
			depositoDto.setCodigoBanco(modelo.getDeposito().getDepositoCheque().getCodigoBanco());
			depositoDto.setSucursal(modelo.getDeposito().getDepositoCheque().getSucursal());
			depositoDto.setCodigoPostal(modelo.getDeposito().getDepositoCheque().getCodigoPostal());
			depositoDto.setNumeroCheque(modelo.getDeposito().getDepositoCheque().getNumeroCheque());
			depositoDto.setCuentaEmisora(modelo.getDeposito().getDepositoCheque().getCuentaEmisora());
		}else{
			if(!Validaciones.isObjectNull(modelo.getDeposito().getDepositoChequeDiferido())){
				//Cheque Diferido
				depositoDto.setCodigoBanco(modelo.getDeposito().getDepositoChequeDiferido().getCodigoBanco());
				depositoDto.setSucursal(modelo.getDeposito().getDepositoChequeDiferido().getSucursal());
				depositoDto.setCodigoPostal(modelo.getDeposito().getDepositoChequeDiferido().getCodigoPostal());
				depositoDto.setNumeroCheque(modelo.getDeposito().getDepositoChequeDiferido().getNumeroCheque());
				depositoDto.setCuentaEmisora(modelo.getDeposito().getDepositoChequeDiferido().getCuentaEmisora());
				depositoDto.setFechaVencimiento(modelo.getDeposito().getDepositoChequeDiferido().getFechaVencimiento());
			}
		}
		
		return depositoDto;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		DepositoDto deposito = (DepositoDto) dto;

		try {
			
			// Mapeo el registro AVC
			ShvAvcRegistroAvc registroAVCModelo = (ShvAvcRegistroAvc) registroAVCMapeador.map(deposito, null);
			
			// Mapeo el deposito
			ShvAvcRegistroAvcDeposito registroAVCDepositoModelo = new ShvAvcRegistroAvcDeposito();
			registroAVCDepositoModelo.setIdRecInstrumento(deposito.getIdRecInstrumento());
			registroAVCDepositoModelo.setRend(deposito.getRend());
			registroAVCDepositoModelo.setFechaPago(deposito.getFechaPago());
			registroAVCDepositoModelo.setEstadoAcreditacion((deposito.getEstadoAcreditacion()==null)?"Acreditado":deposito.getEstadoAcreditacion());
			registroAVCDepositoModelo.setFechaAcreditacion(deposito.getFechaAcreditacion());
			
			if(!Validaciones.isNullOrEmpty(deposito.getFormaPago())){
				registroAVCDepositoModelo.setFormaPago(deposito.getFormaPago());
			}
			if(!Validaciones.isObjectNull(deposito.getNroBoleta())){
				registroAVCDepositoModelo.setNroBoleta(deposito.getNroBoleta());
			}
			if(!Validaciones.isObjectNull(deposito.getSucursalDeposito())){
				registroAVCDepositoModelo.setSucursalDeposito(deposito.getSucursalDeposito());
			}
			if(!Validaciones.isObjectNull(deposito.getGrupoAcreedor())){
				registroAVCDepositoModelo.setGrupoAcreedor(deposito.getGrupoAcreedor());
			}
			if(!Validaciones.isObjectNull(deposito.getNombreCliente())){
				registroAVCDepositoModelo.setNombreCliente(deposito.getNombreCliente());
			}
			if(!Validaciones.isObjectNull(deposito.getCodigoRechazo())){
				registroAVCDepositoModelo.setCodigoRechazo(deposito.getCodigoRechazo());
			}
			
			/*OBSERVACIONES*/
			registroAVCModelo.setObservacionAnulacion(deposito.getObservacionAnulacion());
			registroAVCModelo.setObservacionConfirmarAnulacion(deposito.getObservacionConfirmarAnulacion());
			
			/*Cliente agrupador*/
			registroAVCModelo.setRazonSocialClientePerfil(deposito.getRazonSocialClientePerfil());
			if (!Validaciones.isObjectNull(deposito.getIdClientePerfil())){
				registroAVCModelo.setIdClientePerfil(Long.valueOf(deposito.getIdClientePerfil()));
			}
			
			registroAVCDepositoModelo.setRegistroAvc(registroAVCModelo);
			registroAVCModelo.setDeposito(registroAVCDepositoModelo);
			
			
			
			// Mapeo el efectivo, cheque o cheque diferido
			if(Validaciones.isObjectNull(deposito.getCodigoBanco())){
				//Efectivo
				ShvAvcRegistroAvcEfectivo registroAVCEfectivoModelo = new ShvAvcRegistroAvcEfectivo();
				registroAVCDepositoModelo.setDepositoEfectivo(registroAVCEfectivoModelo);
				registroAVCEfectivoModelo.setDeposito(registroAVCDepositoModelo);
			}else{
				if(TipoAcuerdoEnum.ACUERDO_34.descripcion().equals(deposito.getAcuerdo())){
					//Cheque Diferido
					ShvAvcRegistroAvcChequeDiferido registroAVCChequeDiferidoModelo = new ShvAvcRegistroAvcChequeDiferido();
					registroAVCChequeDiferidoModelo.setCodigoBanco(deposito.getCodigoBanco());
					registroAVCChequeDiferidoModelo.setSucursal(deposito.getSucursal());
					registroAVCChequeDiferidoModelo.setCodigoPostal(deposito.getCodigoPostal());
					registroAVCChequeDiferidoModelo.setNumeroCheque(deposito.getNumeroCheque());
					registroAVCChequeDiferidoModelo.setCuentaEmisora(deposito.getCuentaEmisora());
					registroAVCChequeDiferidoModelo.setFechaVencimiento(deposito.getFechaVencimiento());
					registroAVCDepositoModelo.setDepositoChequeDiferido(registroAVCChequeDiferidoModelo);
					registroAVCChequeDiferidoModelo.setDeposito(registroAVCDepositoModelo);
				}else{
					// Cheque
					ShvAvcRegistroAvcCheque registroAVCChequeModelo = new ShvAvcRegistroAvcCheque();
					registroAVCChequeModelo.setCodigoBanco(deposito.getCodigoBanco());
					registroAVCChequeModelo.setSucursal(deposito.getSucursal());
					registroAVCChequeModelo.setCodigoPostal(deposito.getCodigoPostal());
					registroAVCChequeModelo.setNumeroCheque(deposito.getNumeroCheque());
					registroAVCChequeModelo.setCuentaEmisora(deposito.getCuentaEmisora());
					registroAVCDepositoModelo.setDepositoCheque(registroAVCChequeModelo);
					registroAVCChequeModelo.setDeposito(registroAVCDepositoModelo);
				}
			}
			
			
			return registroAVCModelo;
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}

	public RegistroAVCMapeador getRegistroAVCMapeador() {
		return registroAVCMapeador;
	}

	public void setRegistroAVCMapeador(RegistroAVCMapeador registroAVCMapeador) {
		this.registroAVCMapeador = registroAVCMapeador;
	}

}
