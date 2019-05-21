package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenFechaPagoRegistroAvc;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IConciliacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IMotorProcesamientoConciliacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteMotorConciliacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class MotorProcesamientoConciliacionServicioImpl extends Servicio implements IMotorProcesamientoConciliacionServicio {

	@Autowired
	private IParametroServicio parametroServicio;
	@Autowired
	private IRegistroAVCServicio registroServicio;
	@Autowired
	private IBoletaSinValorServicio boletaSinValorServicio;
	@Autowired
	private IConciliacionServicio conciliacionServicio;
	@Autowired
	private IValorServicio valorServicio;
	
	@Autowired
	private IVistaSoporteServicio vistaSoporteServicio;

	
	/**
	 * Recorre y procesa los registros AVC y las boletas que esten en 
	 * estado "Pendiente de conciliar" y aplica las reglas de conciliacion para
	 * relacionar un registro AVC con un boleta formando una conciliacion Total o Sugerida.
	 * Realiza la conciliacion, sea de tipo Total o Sugerida, cambiando los estados del registro AVC
	 * y de la boleta. Ademas verifica si la boleta tiene un valor asociado y toma las acciones 
	 * que correspondan.
	 * 
	 * @param regla
	 * @param registroAvc
	 * @param listaBoletaSinValor
	 * @throws ShivaExcepcion 
	 * @throws NegocioExcepcion 
	 */
	public void ejecutarProcesoConciliacion(List<VistaSoporteMotorConciliacion> listaDeConciliaciones, HashMap<String, List<String>> cuerpoMailMap, Mail asuntoMail)  throws NegocioExcepcion{
		
		//Ordeno la lista de conciliaciones por fecha de pago
		Collections.sort(listaDeConciliaciones, new ComparatorOrdenFechaPagoRegistroAvc());
		//Lista de Boletas conciliadas
		List<Long> listaDeBoletasConciliadas = new ArrayList<Long>();
		
		for (VistaSoporteMotorConciliacion conciliacion : listaDeConciliaciones) {
			
			Traza.advertencia(MotorProcesamientoConciliacionServicioImpl.class,  "Procesando el registro avc deposito id: " + conciliacion.getRavcIdRegistroAvc() + 
					" conciliado con la boleta id: " + conciliacion.getBolIdBoleta() + 
					" por la regla: " + conciliacion.getRegla() + 
					" tipo: " + conciliacion.getTipo());
			Traza.advertencia(MotorProcesamientoConciliacionServicioImpl.class, "Datos del registro - id: " + conciliacion.getRavcIdRegistroAvc() + 
					" tipo Valor: " + prepararDatoParaMostrar(conciliacion.getRavcTipoValor()) +
					" número cliente: " + prepararDatoParaMostrar(conciliacion.getRavcCodigoCliente()) + 
					" importe: " + prepararDatoParaMostrar(String.valueOf(conciliacion.getRavcImporte())) + 
					" número boleta: " + prepararDatoParaMostrar(conciliacion.getRavcNumeroBoleta()) + 
					" acuerdo: " + prepararDatoParaMostrar(conciliacion.getRavcIdAcuerdo()) +
					" número cheque: " + prepararDatoParaMostrar(conciliacion.getRavcNumeroCheque()) +
					" estado: " + conciliacion.getRavcEstadoWorkflow()
					);
			if("BoletaSinValor".equals(conciliacion.getTipo()) && String.valueOf(TipoValorEnum.BOLETA_SIN_VALOR.getIdTipoValor()).equals(conciliacion.getRavcTipoValor())){
			Traza.auditoria(MotorProcesamientoConciliacionServicioImpl.class, "Datos de la boleta sin valor - id Boleta: "+ conciliacion.getBolIdBoleta() +
					" tipo Valor: "+ prepararDatoParaMostrar(TipoValorEnum.BOLETA_SIN_VALOR.getDescripcion()) +
					" número cliente: " + prepararDatoParaMostrar(conciliacion.getValClienteLegado()) + 
					" importe: " + prepararDatoParaMostrar(String.valueOf(conciliacion.getValImporte())) + 
					" número boleta: " + prepararDatoParaMostrar(conciliacion.getBolNumeroBoleta()) + 
					" acuerdo: " + prepararDatoParaMostrar(conciliacion.getBoletaIdAcuerdo()) + 
					" estado: " + conciliacion.getBoletaEstadoWorkflow());
			} else {
				Traza.auditoria(MotorProcesamientoConciliacionServicioImpl.class, "Datos de la boleta con valor - id Valor: "+ conciliacion.getValIdValor() +
						" id Boleta: " + conciliacion.getBolIdBoleta() + 
						" tipo Valor: "+ prepararDatoParaMostrar(conciliacion.getValTipoValor()) +
						" número cliente: " + prepararDatoParaMostrar(conciliacion.getValClienteLegado()) + 
						" importe: " + prepararDatoParaMostrar(String.valueOf(conciliacion.getValImporte())) + 
						" número boleta: " + prepararDatoParaMostrar(conciliacion.getBolNumeroBoleta()) + 
						" acuerdo: " + prepararDatoParaMostrar(conciliacion.getValAcuerdo()) + 
						" número cheque: " + prepararDatoParaMostrar(conciliacion.getValNumeroCheque()) +
						" estado: " + conciliacion.getValorEstadoWorkflow());
			}
					
			if(!listaDeBoletasConciliadas.contains(conciliacion.getBolIdBoleta())){
				conciliar(cuerpoMailMap, listaDeBoletasConciliadas,	conciliacion, asuntoMail);
			} else {
				Traza.advertencia(MotorProcesamientoConciliacionServicioImpl.class,  "La boleta con id: " 
						+ conciliacion.getBolIdBoleta() + " no se va a conciliar porque ya fue conciliada en esta corrida.");
			}
		}
	}

	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	private void conciliar(HashMap<String, List<String>> cuerpoMailMap, List<Long> listaDeBoletasConciliadas, VistaSoporteMotorConciliacion conciliacion, Mail asuntoMail) throws NegocioExcepcion {
		//////////////////////////////////////////////////////////////////////////////////////////
		//          CONCILIAR
		//////////////////////////////////////////////////////////////////////////////////////////
		String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
		
		DepositoDto registroAvc = crearRegistroAvc(conciliacion, usuarioBatch);
		BoletaSinValorDto boletaDto = crearBoletaDto(conciliacion, usuarioBatch);
		
		
		if("0".equals(conciliacion.getRegla())){
			// Conciliacion Total
			
			Long idValorAsociado = conciliacion.getValIdValor();
			if(Validaciones.isObjectNull(idValorAsociado)){
				// Si no tiene valor asociado lo creo
				ValorDto valorDto = conciliacionServicio.crearValorDtoApartirDeRegistroAvcYBoleta(registroAvc,boletaDto);
				valorDto.setUsuarioModificacion(usuarioBatch);
				ShvValValor valorCreado = valorServicio.crearValor(valorDto);
				valorServicio.asociarValorConBoleta(valorCreado,Long.valueOf(boletaDto.getIdBoleta().toString()));
				idValorAsociado = valorCreado.getIdValor();
			}
			
			// valor -> DISPONIBLE usando disponibilizarBoleta
			valorServicio.disponibilizarValorAsociadoBoleta(idValorAsociado,registroAvc.getFechaPago(),cuerpoMailMap, asuntoMail);
			
			// boleta -> CONCILIADO
			ShvBolBoleta boletaActualizada = boletaSinValorServicio.establecerRegistroConciliado(boletaDto);
			listaDeBoletasConciliadas.add(conciliacion.getBolIdBoleta());
			// registro -> CONCILIADO
			ShvAvcRegistroAvc registroActualizado = registroServicio.establecerRegistroConciliado(registroAvc);
			// Grabo en la tabla ShvAvcRegistroAvcBoleta
			conciliacionServicio.guardarAsociacionDeConciliacion(boletaActualizada,registroActualizado);
			
		}else{
			// Conciliacion Sugerida
			
			// registro -> CONCILIACION SUGERIDA
			ShvAvcRegistroAvc registroActualizado = registroServicio.establecerRegistroConciliacionSugerida(registroAvc);
			// boleta -> CONCILIACION SUGERIDA
			ShvBolBoleta boletaActualizada = boletaSinValorServicio.establecerRegistroConciliacionSugerida(boletaDto);
			listaDeBoletasConciliadas.add(conciliacion.getBolIdBoleta());
			// Grabo en la tabla ShvAvcRegistroAvcBoleta
			conciliacionServicio.guardarAsociacionDeConciliacion(boletaActualizada,registroActualizado);
		}
		
		
	}

	public String prepararDatoParaMostrar(String dato){
		return Validaciones.isNullOrEmpty(dato)?"-":dato;
	}
	
	/**
	 * 
	 * @param conciliacion
	 * @param usuarioModificacion
	 * @return
	 */
	private BoletaSinValorDto crearBoletaDto(VistaSoporteMotorConciliacion conciliacion, String usuarioModificacion) {
		BoletaSinValorDto boletaSinValor = new BoletaSinValorDto();
		boletaSinValor.setUsuarioModificacion(usuarioModificacion);
		boletaSinValor.setIdBoleta(conciliacion.getBolIdBoleta());
		boletaSinValor.setIdEmpresa(conciliacion.getValIdEmpresa());
		boletaSinValor.setIdSegmento(conciliacion.getValIdSegmento());
		boletaSinValor.setCodCliente(conciliacion.getValClienteLegado());
		boletaSinValor.setCodClienteAgrupador(conciliacion.getValClienteLegado());
		boletaSinValor.setRazonSocialClienteAgrupador(conciliacion.getValRazonSocialCliAgrup());
		boletaSinValor.setIdAnalista(conciliacion.getValIdAnalista());
		boletaSinValor.setCopropietario(conciliacion.getValIdCopropietario());
		boletaSinValor.setIdMotivo(conciliacion.getValIdMotivo());
		boletaSinValor.setOperacionAsociada(conciliacion.getValOperacionAsociada());
		
		boletaSinValor.setFechaUltimaModificacion(conciliacion.getBoletaFechaUltimaModificacion());
		boletaSinValor.setTimeStamp(boletaSinValor.getTimeStampDTO());
		return boletaSinValor;
	}

	/**
	 * 
	 * @param conciliacion
	 * @param usuarioModificacion
	 * @return
	 */
	private DepositoDto crearRegistroAvc(VistaSoporteMotorConciliacion conciliacion, String usuarioModificacion) {
		DepositoDto deposito = new DepositoDto();
		deposito.setIdRegistro(conciliacion.getRavcIdRegistroAvc());
		deposito.setUsuarioModificacion(usuarioModificacion);
		deposito.setTipoValor(conciliacion.getRavcTipoValor());
		deposito.setImporte(conciliacion.getRavcImporte());
		deposito.setIdAcuerdo(conciliacion.getRavcIdAcuerdo());
		deposito.setNroBoleta(Long.valueOf(conciliacion.getRavcNumeroBoleta()));
		
		if (!Validaciones.isObjectNull(conciliacion.getRavcFechaDePago())) {
			deposito.setFechaPago(conciliacion.getRavcFechaDePago());
		}
		if (!Validaciones.isObjectNull(conciliacion.getRavcCodigoBanco())) {
			deposito.setCodigoBanco(Long.valueOf(conciliacion.getRavcCodigoBanco()));
		}
		if (!Validaciones.isObjectNull(conciliacion.getRavcNumeroCheque())) {
			deposito.setNumeroCheque(Long.valueOf(conciliacion.getRavcNumeroCheque()));
		}
		if (!Validaciones.isObjectNull(conciliacion.getRavcFechaVencimiento())) {
			deposito.setFechaVencimiento(conciliacion.getRavcFechaVencimiento());
		}
		
		deposito.setFechaUltimaModificacion(conciliacion.getRavcFechaUltimaModificacion());
		deposito.setTimeStamp(deposito.getTimeStampDTO());
		return deposito;
	}
	
	/**
	 * 
	 */
	public List<VistaSoporteMotorConciliacion> listarRegistrosMotorConciliacionPorReglaMenor() throws NegocioExcepcion {
		return vistaSoporteServicio.listarRegistrosMotorConciliacionPorReglaMenor();
	}
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public IRegistroAVCServicio getRegistroServicio() {
		return registroServicio;
	}

	public void setRegistroServicio(IRegistroAVCServicio registroServicio) {
		this.registroServicio = registroServicio;
	}

	public IBoletaSinValorServicio getBoletaSinValorServicio() {
		return boletaSinValorServicio;
	}

	public void setBoletaSinValorServicio(
			IBoletaSinValorServicio boletaSinValorServicio) {
		this.boletaSinValorServicio = boletaSinValorServicio;
	}

	public IConciliacionServicio getConciliacionServicio() {
		return conciliacionServicio;
	}

	public void setConciliacionServicio(IConciliacionServicio conciliacionServicio) {
		this.conciliacionServicio = conciliacionServicio;
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	public IVistaSoporteServicio getVistaSoporteServicio() {
		return vistaSoporteServicio;
	}

	public void setVistaSoporteServicio(IVistaSoporteServicio vistaSoporteServicio) {
		this.vistaSoporteServicio = vistaSoporteServicio;
	}



}
