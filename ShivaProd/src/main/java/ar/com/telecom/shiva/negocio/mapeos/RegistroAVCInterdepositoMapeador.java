package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.InterdepositoDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.IOrganismoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcInterdeposito;

public class RegistroAVCInterdepositoMapeador extends Mapeador {

	@Autowired
	private RegistroAVCMapeador registroAVCMapeador;
	@Autowired
	private IOrganismoDao organismoDao;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvAvcRegistroAvc modelo = (ShvAvcRegistroAvc) vo;
		InterdepositoDto interdepositoDto = new InterdepositoDto(String.valueOf(modelo.getAcuerdo().getIdAcuerdo()));
		Estado estado = modelo.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado();
		interdepositoDto.setIdRegistro(modelo.getIdRegistroAvc());
		interdepositoDto.setAcuerdo(modelo.getAcuerdo().getDescripcion());
		interdepositoDto.setIdAcuerdo(String.valueOf(modelo.getAcuerdo().getIdAcuerdo()));
		interdepositoDto.setBancoDeposito(modelo.getAcuerdo().getBancoCuenta().getBanco().getDescripcion());
		interdepositoDto.setNumeroCuenta(modelo.getAcuerdo().getBancoCuenta().getCuentaBancaria());
		interdepositoDto.setImporte(modelo.getImporte());
		interdepositoDto.setTipoValor(String.valueOf(modelo.getTipoValor().getIdTipoValor()));
		interdepositoDto.setCodigoCliente(String.valueOf(modelo.getCodigoCliente()));
		interdepositoDto.setEstadoFormateado(estado.descripcion());
		interdepositoDto.setTipoValorFormateado(modelo.getTipoValor().getDescripcion());
		interdepositoDto.setEsEstadoPendConfirmar(estado.equals(Estado.AVC_PENDIENTE_CONFIRMAR_ALTA_VALOR));
		interdepositoDto.setNombreArchivo(modelo.getArchivoAvc().getNombreArchivo());
		interdepositoDto.setTimeStamp(String.valueOf(modelo.getWorkFlow().getFechaUltimaModificacion().getTime()));
		
		interdepositoDto.setFechaValor(modelo.getInterdeposito().getFechaValor());
		interdepositoDto.setFechaIngreso(modelo.getInterdeposito().getFechaIngreso());
		interdepositoDto.setConcepto(modelo.getInterdeposito().getConcepto());
		interdepositoDto.setCodigoOperacion(modelo.getInterdeposito().getCodigoOperacion());
		interdepositoDto.setComprobante(modelo.getInterdeposito().getComprobante());
		interdepositoDto.setCodigoOrganismo(modelo.getInterdeposito().getCodigoOrganismo());
		interdepositoDto.setCodigoInterdeposito(String.valueOf(modelo.getInterdeposito().getCodigoInterdeposito()));
		interdepositoDto.setDeposito(modelo.getInterdeposito().getDeposito());
		interdepositoDto.setSucursal(modelo.getInterdeposito().getSucursal());
		interdepositoDto.setCodOpBanco(modelo.getInterdeposito().getCodOpBanco());
		interdepositoDto.setPcc(modelo.getInterdeposito().getPcc());
		
		/*OBSERVACIONES*/
		interdepositoDto.setObservacionAnulacion(modelo.getObservacionAnulacion());
		interdepositoDto.setObservacionConfirmarAnulacion(modelo.getObservacionConfirmarAnulacion());
		interdepositoDto.setObservaciones(modelo.getObservaciones());
		return interdepositoDto;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		InterdepositoDto interdeposito = (InterdepositoDto) dto;

		try {
			// Mapeo el registro AVC
			ShvAvcRegistroAvc registroAVCModelo = (ShvAvcRegistroAvc) registroAVCMapeador.map((RegistroAVCDto)interdeposito, null);
			
			
			// Mapeo el deposito
			ShvAvcRegistroAvcInterdeposito registroAVCInterdepositoModelo = new ShvAvcRegistroAvcInterdeposito();
			registroAVCInterdepositoModelo.setFechaValor(interdeposito.getFechaValor());
			registroAVCInterdepositoModelo.setFechaIngreso(interdeposito.getFechaIngreso());
			registroAVCInterdepositoModelo.setConcepto(interdeposito.getConcepto());
			registroAVCInterdepositoModelo.setCodigoOperacion(!Validaciones.isNullOrEmpty(interdeposito.getCodigoOperacion())?String.valueOf(interdeposito.getCodigoOperacion()):null);
			registroAVCInterdepositoModelo.setComprobante(interdeposito.getComprobante());
			registroAVCInterdepositoModelo.setCodigoOrganismo(interdeposito.getCodigoOrganismo());
			registroAVCInterdepositoModelo.setCodigoInterdeposito(Long.valueOf(interdeposito.getCodigoInterdeposito()));
			registroAVCInterdepositoModelo.setDeposito(interdeposito.getDeposito());
			registroAVCInterdepositoModelo.setSucursal(interdeposito.getSucursal());
			registroAVCInterdepositoModelo.setCodOpBanco(interdeposito.getCodOpBanco());
			registroAVCInterdepositoModelo.setPcc(interdeposito.getPcc());
			
			/*OBSERVACIONES*/
			registroAVCModelo.setObservacionAnulacion(interdeposito.getObservacionAnulacion());
			registroAVCModelo.setObservacionConfirmarAnulacion(interdeposito.getObservacionConfirmarAnulacion());
			
			registroAVCModelo.setInterdeposito(registroAVCInterdepositoModelo);
			registroAVCInterdepositoModelo.setRegistroAvc(registroAVCModelo);
						
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

	public IOrganismoDao getOrganismoDao() {
		return organismoDao;
	}

	public void setOrganismoDao(IOrganismoDao organismoDao) {
		this.organismoDao = organismoDao;
	}
}
