package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenModificacionCobroTransaccionDto;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenModificacionShvCobTransaccion;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRenglonSapEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.jms.servicios.IFacJmsSyncServicio;
import ar.com.telecom.shiva.base.utils.GenerarCartaPdf;
import ar.com.telecom.shiva.base.utils.GenerarResumenPdf;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.bean.ClienteDireccionVo;
import ar.com.telecom.shiva.negocio.bean.CobroCompensacionPdfCap;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineTransaccionesMapeador;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionCalipsoServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaCapPdfCap;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteConsultaDeudaPdfCap;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoCapDao;
import ar.com.telecom.shiva.persistencia.dao.impl.TareaDaoImpl;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDocumentoCap;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvWfTareaSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoCTASinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobMedioPagoSinOperacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion.ShvCobTransaccionSinOperacion;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.TareaFiltro;



public class CobroBatchServicioImpl extends Servicio implements ICobroBatchServicio {

	@Autowired private ICobroDao cobroDao;	
	@Autowired private CobroOnlineTransaccionesMapeador cobroOnlineTransaccionesMapeador;
	@Autowired private IFacJmsSyncServicio facJmsSyncServicio;

	@Autowired private IDocumentoCapDao documentoCapDao;
	@Autowired private IVistaSoporteServicio vistaSoporteServicio;
	@Autowired private ICobroOnlineDao cobroOnlineDao;
	@Autowired private IParametroServicio parametroServicio;
	@Autowired private IClienteServicio clienteServicio;
	
	@Autowired private IWorkflowCobros workflowCobros;
	@Autowired private ICobroOnlineServicio cobroOnlineServicio;
	@Autowired private ICobroBatchSoporteImputacionServicio cobroBatchSoporteImputacionServicio;
	
	@Autowired private TareaDaoImpl tareaDaoImpl;

	@Autowired private ICobroBatchSoporteImputacionCalipsoServicio cobroBatchSoporteImputacionCalipsoServicio;


	@Override
	public void modificarTransaccionesConIntereses(CobroDto cobroDto) throws NegocioExcepcion {
		try {
			
			ShvCobCobro cobro = cobroDao.buscarCobro(cobroDto.getIdCobro());
			
			//pregunta si ya existe el cobro en las tablas originales, si existe quiere decir
			//que ya se simulo al menos una vez entonces guardo los posibles cambios en las transacciones
			if(!Validaciones.isObjectNull(cobro)){
				Set<ShvCobTransaccion> setDeTransaccionesCobro = cobro.getOperacion().getTransaccionesSinDifCambio();
				Set<CobroTransaccionDto> setDeTransaccionesDto = cobroDto.getTransaccionesSinDifCambio();
				
				//paso las colecciones a un treeset seteandole un comparator para que las ordene
				//de manera que sea mas facil recorrerlas y actualizarlas
				Set<ShvCobTransaccion> setOrdenadoTransaccionesCobro = new TreeSet<ShvCobTransaccion>(new ComparatorOrdenModificacionShvCobTransaccion());
				Set<CobroTransaccionDto> setOrdenadoTransaccionesDto = new TreeSet<CobroTransaccionDto>(new ComparatorOrdenModificacionCobroTransaccionDto());
				setOrdenadoTransaccionesCobro.addAll(setDeTransaccionesCobro);
				setOrdenadoTransaccionesDto.addAll(setDeTransaccionesDto);
				
				if(Validaciones.isCollectionNotEmpty(setOrdenadoTransaccionesCobro) 
						&& Validaciones.isCollectionNotEmpty(setOrdenadoTransaccionesDto)){
					Iterator<ShvCobTransaccion> itCobro = setOrdenadoTransaccionesCobro.iterator();
					
					//creo un set nuevo para actualizarlo y pisar el viejo
					Set<ShvCobTransaccion> listaTransaccionesCobroActualizado = new HashSet<ShvCobTransaccion>();
					
					//recorro todas las transacciones como vienen ordendas de la base
					//aunque solo voy a actualizar los debitos que no sean "reintegros No a proxima factura"
					//y envio a ganancias y el medio de pago Debito a proxima factura
					while(itCobro.hasNext()){
						ShvCobTransaccion transaccionCobro = itCobro.next();
						ShvCobTransaccion modeloActualizado = transaccionCobro;
						
						Iterator<CobroTransaccionDto> itCobroDto = setOrdenadoTransaccionesDto.iterator();
						
						//recorro las transacciones de la grilla en pantalla mientras que quede alguna
						while(itCobroDto.hasNext()) {
							CobroTransaccionDto transaccionDto = itCobroDto.next();
							
							if(!transaccionCobro.getNumeroTransaccion().toString().equals(transaccionDto.getNumeroTransaccion())){
								break;
							}
							//Primero valido que regitro de la transaccion de la pantalla tenga intereses, si no tiene intereses lo salto
							if(!Validaciones.isNullEmptyOrDash(transaccionDto.getIntereses())){
								modeloActualizado = cobroOnlineTransaccionesMapeador.map(transaccionDto, transaccionCobro);
							}
							
							//borro el registro de la transaccion de la pantalla, para no recorrerla de nuevo
							itCobroDto.remove();
						}
						
						//me guardo el modelo actualizado en la lista nueva
						listaTransaccionesCobroActualizado.add(modeloActualizado);
					}
					
					//actualizo el modelo
					cobro.getOperacion().getTransacciones().removeAll(setDeTransaccionesCobro);
					cobro.getOperacion().getTransacciones().addAll(listaTransaccionesCobroActualizado);
					
					// actualizo el tema de intereses
					actualizarTratamientoIntereses(cobro);
					
					//lo impacto en la base
					cobroDao.modificar(cobro);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param cobro
	 */
	public void actualizarTratamientoIntereses(ShvCobCobro cobro) {
		
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			actualizarTratamientoInteresesTransaccion(transaccion);
		}
	}
	
	/**
	 * 
	 * @param transaccion
	 */
	public void actualizarTratamientoInteresesTransaccion(ShvCobTransaccion transaccion) {
	
		boolean medioPagoGeneraIntereses = true;
		for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
			 
			if (SiNoEnum.NO.equals(medioPago.getTipoMedioPago().getGeneraIntereses())) {
				medioPagoGeneraIntereses = false;
			}
	
			//
			// Actualizar tratamiento de intereses para debito a proxima
			//

			if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaMic) {
				
				ShvCobMedioPagoDebitoProximaFacturaMic medioPagoProxFact = (ShvCobMedioPagoDebitoProximaFacturaMic) medioPago;
				
				TratamientoInteresesEnum queHacerConIntereses = calcularTratamientoIntereses(
																	null,
																	null,
																	null,
																	SiNoEnum.NO,
																	medioPagoProxFact.getTrasladarIntereses(),
																	medioPagoProxFact.getPorcentajeBonifIntereses(),
																	medioPagoProxFact.getCobradorInteresesGenerados(),
																	medioPagoGeneraIntereses);
				
				medioPagoProxFact.setQueHacerConIntereses(queHacerConIntereses);
				
				if (!Validaciones.isObjectNull(medioPagoProxFact.getImporteBonificacionIntereses()) &&
					!Validaciones.isObjectNull(medioPagoProxFact.getCobradorInteresesGenerados())
				) {
					medioPagoProxFact.setCobradorInteresesTrasladados(
							medioPagoProxFact.getCobradorInteresesGenerados().subtract(medioPagoProxFact.getImporteBonificacionIntereses()));
				}
	
			} else if (medioPago instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso) {
				
				ShvCobMedioPagoDebitoProximaFacturaCalipso medioPagoProxFact = (ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPago;
				
				TratamientoInteresesEnum queHacerConIntereses = calcularTratamientoIntereses(
																	null,
																	null,
																	medioPagoProxFact.getFechaVencimientoFactura(),
																	SiNoEnum.NO,
																	medioPagoProxFact.getTrasladarIntereses(),
																	medioPagoProxFact.getPorcentajeBonifIntereses(),
																	medioPagoProxFact.getCobradorIntereseGenerados(),
																	medioPagoGeneraIntereses);
				
				medioPagoProxFact.setQueHacerConIntereses(queHacerConIntereses);

				if (!Validaciones.isObjectNull(medioPagoProxFact.getImporteBonificacionIntereses()) &&
					!Validaciones.isObjectNull(medioPagoProxFact.getCobradorIntereseGenerados())
				) {
					medioPagoProxFact.setCobradorInteresesTrasladados(
							medioPagoProxFact.getCobradorIntereseGenerados().subtract(medioPagoProxFact.getImporteBonificacionIntereses()));
				}
			}
		}
		
		//
		// Actualizar tratamiento de intereses para facturas
		//
		if (!Validaciones.isObjectNull(transaccion.getFactura())) {
			
			TratamientoInteresesEnum queHacerConIntereses = null;
			
			if (transaccion.getFactura() instanceof ShvCobFacturaMic) {
				
				ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) transaccion.getFactura();
				
				queHacerConIntereses = calcularTratamientoIntereses(
											facturaMic.getFechaVencimiento(),
											facturaMic.getFechaSegundoVencimiento(),
											facturaMic.getFechaValor(),
											facturaMic.getCobrarSegundoVencimiento(),
											facturaMic.getTrasladarIntereses(),
											facturaMic.getPorcentajeBonifIntereses(),
											facturaMic.getCobradorInteresesGenerados(),
											medioPagoGeneraIntereses);
			
			} else if (transaccion.getFactura() instanceof ShvCobFacturaCalipso){
				
				ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) transaccion.getFactura();
				
				queHacerConIntereses = calcularTratamientoIntereses(
											facturaCalipso.getFechaVencimiento(),
											null,
											facturaCalipso.getFechaValor(),
											SiNoEnum.NO,
											facturaCalipso.getTrasladarIntereses(),
											facturaCalipso.getPorcentajeBonifIntereses(),
											facturaCalipso.getCobradorInteresesGenerados(),
											medioPagoGeneraIntereses);
			}
			
			transaccion.getFactura().setQueHacerConIntereses(queHacerConIntereses);
			
			if (
				!Validaciones.isObjectNull(transaccion.getFactura().getImporteBonificacionIntereses()) &&
				!Validaciones.isObjectNull(transaccion.getFactura().getCobradorInteresesGenerados())
			) {
				transaccion.getFactura().setCobradorInteresesTrasladados(
						transaccion.getFactura().getCobradorInteresesGenerados().subtract(transaccion.getFactura().getImporteBonificacionIntereses()));
			}

		}
	
		//
		// Actualizar tratamiento de intereses para credito a proxima
		//
		if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())) {
	
			if (TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
					|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
				
				TratamientoInteresesEnum queHacerConIntereses = calcularTratamientoIntereses(
																	null,
																	null,
																	transaccion.getTratamientoDiferencia().getFechaTramiteReintegro(),
																	SiNoEnum.NO,
																	transaccion.getTratamientoDiferencia().getTrasladarIntereses(),
																	transaccion.getTratamientoDiferencia().getPorcentajeBonifIntereses(),
																	transaccion.getTratamientoDiferencia().getCobradorInteresesGenerados(),
																	medioPagoGeneraIntereses);
				
				transaccion.getTratamientoDiferencia().setQueHacerConIntereses(queHacerConIntereses);

				if (
					!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia().getImporteBonificacionIntereses()) &&
					!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia().getCobradorInteresesGenerados())
				) {
					transaccion.getTratamientoDiferencia().setCobradorInteresesTrasladados(
							transaccion.getTratamientoDiferencia().getCobradorInteresesGenerados().subtract(transaccion.getTratamientoDiferencia().getImporteBonificacionIntereses()));
				}
				
			}
		}
	}	

	/**
	 * Metodo que calcula el tratamiento de intereses para una cobranza
	 * El calculo de tratamiento de intereses toma como valores por defecto aquellos que corresponden a MIC, independientemente
	 * el tipo de factura.
	 * El valor del tratamiento de intereses corresponde a un Enum, el cuál internamente traduce los valores de apropiacion 
	 * para MIC y Calipso, y las posibles combinaciones que se pueden usar en simulacion.
	 * 
	 * @param checkCobrar2doVencimiento
	 * @param checkTrasladarIntereses
	 * @param porcentajeBonifIntereses
	 * @param medioPagoGeneraIntereses
	 * @return
	 */	
	private TratamientoInteresesEnum calcularTratamientoIntereses(
												Date fecha1erVencimiento,
												Date fecha2doVencimiento,
												Date fechaImputacionCobro,
												SiNoEnum checkCobrar2doVencimiento, 
												SiNoEnum checkTrasladarIntereses, 
												Integer porcentajeBonifIntereses,
												BigDecimal interesesGenerados,
												boolean medioPagoGeneraIntereses) {
	
		Integer PORCENTAJE_BONIF_INTERESES_CIEN = new Integer(100);
		
		TratamientoInteresesEnum tratamientoIntereses = null;
		
		// Si no tengo fecha de 1er vencimiento, entonces el calculo es para tratamiento
		// de diferencia
		if (Validaciones.isObjectNull(fecha1erVencimiento)) {
			
			if (SiNoEnum.SI.equals(checkTrasladarIntereses)) {
				tratamientoIntereses = TratamientoInteresesEnum.TM;
			} else {
				tratamientoIntereses = TratamientoInteresesEnum.BM;
			}
			
		} else {
			// Si tengo fecha de 1er vencimiento, entonces el calculo es para facturas
			
			//
			// Calculo de intereses con fecha de cobro antes del vencimiento
			// o medio de pago que no genera intereses
			tratamientoIntereses = TratamientoInteresesEnum.SC;
			
			if (medioPagoGeneraIntereses) {
					
				//
				// El cobro se realiza sin seleccionar el check de cobrar a 2do vencimiento
				//
				if (SiNoEnum.NO.equals(checkCobrar2doVencimiento)) { 
	
					// 
					// Calculo de intereses con fecha de cobro luego del primer vencimiento, y antes del segundo si aplicara
					//
					if ((fechaImputacionCobro.compareTo(fecha1erVencimiento) > 0) 
							&& (Validaciones.isObjectNull(fecha2doVencimiento) 
									|| (!Validaciones.isObjectNull(fecha2doVencimiento) && fechaImputacionCobro.compareTo(fecha2doVencimiento) <= 0))) {
						
						if (SiNoEnum.SI.equals(checkTrasladarIntereses)) {
							tratamientoIntereses = TratamientoInteresesEnum.TV;
						} else {
							if (PORCENTAJE_BONIF_INTERESES_CIEN.equals(porcentajeBonifIntereses)) {
								tratamientoIntereses = TratamientoInteresesEnum.BV;
							} else {
								if (Validaciones.isObjectNull(interesesGenerados) || BigDecimal.ZERO.equals(interesesGenerados)) {
									tratamientoIntereses = TratamientoInteresesEnum.TV;
								} else {
									tratamientoIntereses = TratamientoInteresesEnum.TC;
								}
							}
						}
					}
						
					//
					// Calculo de intereses con fecha de cobro posterior a la fecha de 2do vencimiento, si aplicara
					//
					if (!Validaciones.isObjectNull(fecha2doVencimiento) && fechaImputacionCobro.compareTo(fecha2doVencimiento) > 0) {
		
						if (SiNoEnum.SI.equals(checkTrasladarIntereses)) {
							tratamientoIntereses = TratamientoInteresesEnum.TV;
						} else {
							if (PORCENTAJE_BONIF_INTERESES_CIEN.equals(porcentajeBonifIntereses)) {
								tratamientoIntereses = TratamientoInteresesEnum.BV;
							} else {
								if (Validaciones.isObjectNull(interesesGenerados) || BigDecimal.ZERO.equals(interesesGenerados)) {
									tratamientoIntereses = TratamientoInteresesEnum.TV;
								} else {
									tratamientoIntereses = TratamientoInteresesEnum.TC;
								}
							}
						}
					}
	
				} else {
					//
					// El cobro se realiza seleccionando el check de cobrar a 2do vencimiento
					// Aqui solo debieran caer documentos a cobrar de MIC
					//
					if (fechaImputacionCobro.compareTo(fecha2doVencimiento) > 0) {
						
						if (SiNoEnum.SI.equals(checkTrasladarIntereses)) {
							tratamientoIntereses = TratamientoInteresesEnum.TM;
						} else {
							tratamientoIntereses = TratamientoInteresesEnum.BM;
						}
					} else {
						tratamientoIntereses = TratamientoInteresesEnum.TM;
					}
				}
			}
		}
		
		return tratamientoIntereses;
	}
	
	@Override
	public CobroCompensacionPdfCap completarDatosParaInformesCapPdf(long idCobro, boolean esCarta) throws NegocioExcepcion {
		try {
			CobroCompensacionPdfCap cobroVo = new CobroCompensacionPdfCap();
			ShvCobEdCobro shvCobEdCobro = cobroOnlineDao.buscarCobro(idCobro);

			if (
				!Validaciones.isObjectNull(shvCobEdCobro.getMediosPagos()) &&
				!shvCobEdCobro.getMediosPagos().isEmpty()
			) {
				ShvCobEdOtrosMedioPago shvCobEdOtrosMedioPago  = shvCobEdCobro.getMediosPagos().iterator().next();
				TipoCreditoEnum tipoMedioPago = TipoCreditoEnum.getEnumByValor(shvCobEdOtrosMedioPago.getTipoMedioPago().getIdTipoMedioPago());
				if (TipoCreditoEnum.PROVEEDORES.equals(tipoMedioPago) || TipoCreditoEnum.LIQUIDOPRODUCTO.equals(tipoMedioPago)) {
//					if (!esCarta) {
//						for (ShvCobEdMedioPagoCliente shvCobEdMedioPagoCliente : shvCobEdOtrosMedioPago.getListaMedioPagoCliente()) {
//							cobroVo.getAcuACompensar().setKey(Long.parseLong(shvCobEdMedioPagoCliente.getIdClienteLegado()));
//						}
//					}
					boolean primero = true;
					for (ShvCobEdDocumentoCap shvCobEdDocumentoCap : shvCobEdOtrosMedioPago.getDocumentosCap()) {
						if (!TipoRenglonSapEnum.AGRUPADOR.equals(shvCobEdDocumentoCap.getTipoRenglon())) {
							if (primero && esCarta) {
								cobroVo.getCliente().setIdClienteLegal(shvCobEdDocumentoCap.getIdClienteLegado());
								cobroVo.getCliente().setIdProveedor(shvCobEdDocumentoCap.getIdProveedor());
								this.completarDatosCliente(cobroVo.getCliente());
								primero = false;
								break;
							} else if (!esCarta) {
								if (primero) {
									cobroVo.getCliente().setIdProveedor(shvCobEdDocumentoCap.getIdProveedor());
									primero = false;
								}
//								if (SiNoEnum.SI.equals(shvCobEdDocumentoCap.getCheckSinDiferenciaCambio())) {
//									cobroVo.getAcuACompensar().aAcumular(shvCobEdDocumentoCap.getIdClienteLegado(), shvCobEdDocumentoCap.getImporteDoc());
//								} else {
//									cobroVo.getAcuACompensar().aAcumular(shvCobEdDocumentoCap.getIdClienteLegado(), shvCobEdDocumentoCap.getImpDocPES());
//								}
							}
						}
					}
					
				} else {
					throw new NegocioExcepcion("El cobro no posee otros medios de pago LIQUIDO PRODUCTO o PROVEEDORES");
				}
			} else {
				throw new NegocioExcepcion("El cobro no posee otros medios de pago");
			}

			//cobroVo.setListaDeuda(vistaSoporteServicio.obtenerRegistrosDeudaPdf(shvCobEdCobro.getIdOperacion()));

			// obtengo el numero Interno sap
			ShvCobMedioPagoDocumentoCap shvCobMedioPagoDocumentoCap = documentoCapDao.buscarDocumentoCapPorIdCobro(idCobro);

			if (!Validaciones.isObjectNull(shvCobMedioPagoDocumentoCap) && !Validaciones.isObjectNull(shvCobMedioPagoDocumentoCap.getIdDocumento())) {
				cobroVo.setIdInternoSAP(shvCobMedioPagoDocumentoCap.getIdDocumento().toString());
			}
			
			if (!Validaciones.isObjectNull( vistaSoporteServicio.obtenerRegistrosDeudaPdf(shvCobEdCobro.getIdOperacion()))) {
				cobroVo.setListaDeuda( vistaSoporteServicio.obtenerRegistrosDeudaPdf(shvCobEdCobro.getIdOperacion()));
			}
			
			
			if (!esCarta) {
				cobroVo.setListaCaps(vistaSoporteServicio.obtenerRegistrosCapPdf(shvCobEdCobro.getIdCobro()));
				if (Validaciones.isObjectNull(shvCobMedioPagoDocumentoCap.getFechaDerivacionCap())) {
					cobroVo.setFecha(new Date());
				} else {
					cobroVo.setFecha(shvCobMedioPagoDocumentoCap.getFechaDerivacionCap());
				}
				if (!Validaciones.isObjectNull(shvCobMedioPagoDocumentoCap.getAnioFiscal())) {
					cobroVo.setOrden(shvCobMedioPagoDocumentoCap.getAnioFiscal(), shvCobMedioPagoDocumentoCap.getOrden());
				}
				
				
				for (VistaSoporteConsultaDeudaPdfCap deuda : cobroVo.getListaDeuda()) {
					cobroVo.getAcuACompensar().aAcumular(Long.parseLong(deuda.getCliente()), deuda.getImporteACobrar());
				}
				cobroVo.getAcuACompensar().ordernar();
				cobroVo.getCliente().setIdClienteLegal(cobroVo.getAcuACompensar().getPrimeroKey());
				this.completarDatosCliente(cobroVo.getCliente(), shvCobEdCobro);
				List<Long> keys = cobroVo.getAcuACompensar().getKeys().subList(1, cobroVo.getAcuACompensar().getTree().size());
				for(Long key : keys) {
					cobroVo.getClientesAdicionales().add(key.toString());
				}
				List<VistaSoporteConsultaCapPdfCap> caps = new ArrayList<VistaSoporteConsultaCapPdfCap>();
				for (VistaSoporteConsultaCapPdfCap cap : cobroVo.getListaCapsOriginales()) {
					Boolean existe = false;
					for (VistaSoporteConsultaCapPdfCap vista : caps) {
						if(cap.getNumeroDocumentoSap().equals(vista.getNumeroDocumentoSap()) &&  cap.getFechaEmision().equals(vista.getFechaEmision())) {
							vista.setaCompensarEnPesos(vista.getaCompensarEnPesos().add(cap.getaCompensarEnPesos()));
							vista.setMontoReclamado(vista.getMontoReclamado().add(cap.getaCompensarEnPesos()));
							existe=true;
							break;
						}
					}
					if(!existe) {
						caps.add(cap);
					}
				}
				cobroVo.setListaCaps(caps);
			} else {
				cobroVo.setFecha(shvCobMedioPagoDocumentoCap.getFechaValorCompensacion());
			}
			// TODO PARA PI y PRE PI!!!!!!!!!!!!
			//shvCobEdCobro.setUsuarioAprobadorSuperOperacionesEspeciales("U157495");
			cobroVo.setUsuarioVerificador(shvCobEdCobro.getUsuarioAprobadorSuperOperacionesEspeciales());
			cobroVo.setDetalleFirma(parametroServicio.getValorTexto(
					//"cobros.compensacion.pdf.detalleFirma." + cobroVo.getUsuarioVerificador()
				Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.FIRMANTE_DOCUMENTO_DETALLEFIRMA),
					cobroVo.getUsuarioVerificador()
			)));
			cobroVo.setSectorSolicitante(parametroServicio.getValorTexto(
				//"cobros.compensacion.pdf.sector.solicitante." + cobroVo.getUsuarioVerificador()
				Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.FIRMANTE_DOCUMENTO_SECTORSOLICITANTE),
					cobroVo.getUsuarioVerificador()
			)));
			cobroVo.setMoneda(shvCobEdCobro.getMonedaOperacion());
			cobroVo.setIdOperacion(shvCobEdCobro.getIdOperacion());
			return cobroVo;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage());
		}
	}
	private ClienteDireccionVo completarDatosCliente(ClienteDireccionVo cliente, ShvCobEdCobro shvCobEdCobro) throws NegocioExcepcion {
		for (ShvCobEdCliente shvCobEdCliente : shvCobEdCobro.getClientes()) {
			if (cliente.getIdClienteLegal().equals(shvCobEdCliente.getIdClienteLegado())) {
				cliente.setCuit(shvCobEdCliente.getCuit());
				cliente.setRazonSocial(shvCobEdCliente.getRazonSocial());
				break;
			}
		}
	
		return cliente;
	}
	private ClienteDireccionVo completarDatosCliente(ClienteDireccionVo cliente) throws NegocioExcepcion {
		ClienteBean clienteBean = this.clienteServicio.consultarCliente(cliente.getIdClienteLegal().toString());

		if (!Validaciones.isObjectNull(clienteBean)) {
			cliente.setCuit(clienteBean.getCuit());
			cliente.setRazonSocial(clienteBean.getRazonSocialClienteAgrupador());
			// Datos de domicilio!!
			if (!Validaciones.isObjectNull(clienteBean.getDomicilio())) {
				cliente.setAltura(clienteBean.getDomicilio().getAltura());
				cliente.setCodigoCalle(clienteBean.getDomicilio().getCodigoCalle());
				cliente.setCodigoCiudad(clienteBean.getDomicilio().getCodigoCiudad());
				cliente.setCodigoPostal(clienteBean.getDomicilio().getCodigoPostal());
				cliente.setDescLocalidad(clienteBean.getDomicilio().getDescLocalidad());
				cliente.setDescProvincia(clienteBean.getDomicilio().getDescProvincia());
				cliente.setEscalera(clienteBean.getDomicilio().getEscalera());
				cliente.setNombreCalle(clienteBean.getDomicilio().getNombreCalle());
				cliente.setNombreEdificio(clienteBean.getDomicilio().getNombreEdificio());
				cliente.setPiso(clienteBean.getDomicilio().getPiso());
				cliente.setPuerta(clienteBean.getDomicilio().getPuerta());
			}
		}
		return cliente;
	}
	
	/**
	 * 
	 */
	public ArchivoByteArray generarPdfCartaCompensacion(Long idCobro) throws NegocioExcepcion {
		CobroCompensacionPdfCap cobroCompensacionPdfCapVo = this.completarDatosParaInformesCapPdf(idCobro, true);
		ArchivoByteArray archivo = new ArchivoByteArray();

		cobroCompensacionPdfCapVo.setUrlFirma(getClass().getResource("/firma." + cobroCompensacionPdfCapVo.getUsuarioVerificador() + ".png"));

		archivo.setByteArray(
			GenerarCartaPdf.generarCartaCompensacion(cobroCompensacionPdfCapVo)
		);
		archivo.setNombreArchivo(
			Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("imputacion.cobro.cap.carta"),
					String.valueOf(cobroCompensacionPdfCapVo.getIdOperacion()),
					String.valueOf(cobroCompensacionPdfCapVo.getIdInternoSAP())
		));
		return archivo;
	}
	
	/**
	 * 
	 */
	public ArchivoByteArray generarPdfResumenCompensacion(Long idCobro) throws NegocioExcepcion {
		CobroCompensacionPdfCap cobroCompensacionPdfCapVo = this.completarDatosParaInformesCapPdf(idCobro, false);
			new CobroCompensacionPdfCap();

		ArchivoByteArray archivo = new ArchivoByteArray();
		cobroCompensacionPdfCapVo.setUrlLogo(getClass().getResource("/logo.png"));
		
		cobroCompensacionPdfCapVo.setUrlFirma(getClass().getResource("/firma." + cobroCompensacionPdfCapVo.getUsuarioVerificador() + ".png"));
		
		archivo.setByteArray(
			GenerarResumenPdf.generarResumenCompensacion(cobroCompensacionPdfCapVo)
		);
		
		archivo.setNombreArchivo(
			Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("imputacion.cobro.cap.resumen"),
					String.valueOf(cobroCompensacionPdfCapVo.getIdOperacion()),
					String.valueOf(cobroCompensacionPdfCapVo.getIdInternoSAP())
		));
		return archivo;
	}

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ShvCobCobro> buscarCobrosPendienteSimulacionBatch() throws NegocioExcepcion {
		try {
			
			List<ShvCobCobro> cobrosPendienteSimulacionBatch = cobroDao.buscarCobrosSimulacionEnProceso();
			return cobrosPendienteSimulacionBatch;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
	/**
	 * Cambia el estado del workflow del cobro y lo guarda en la base
	 * @param cobro
	 * @param cobErrorCobro
	 * @throws NegocioExcepcion 
	 */
	@Override
	public void cambiarEstadoCobro(ShvCobCobro cobro, Estado estado, String usuario) throws NegocioExcepcion {
		try{
			if (Estado.COB_PROCESO.equals(cobro.getWorkflow().getEstado())){
				if(Estado.COB_ERROR_COBRO.equals(estado)){
					ShvWfWorkflow workflowActualizado = workflowCobros.registrarErrorCobro(cobro.getWorkflow(), "", usuario);
					cobro.setWorkflow(workflowActualizado);
					cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
					cobro = cobroDao.modificar(cobro);
				}else{
					if(Estado.COB_ERROR_APROPIACION.equals(estado)){
						ShvWfWorkflow workflowActualizado = workflowCobros.registrarErrorApropiacion(cobro.getWorkflow(), "", usuario);
						cobro.setWorkflow(workflowActualizado);
						cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
						cobro = cobroDao.modificar(cobro);
					}else{
						if(Estado.COB_ERROR_CONFIRMACION.equals(estado)){
							ShvWfWorkflow workflowActualizado = workflowCobros.registrarErrorConfirmacion(cobro.getWorkflow(), "", usuario);
							cobro.setWorkflow(workflowActualizado);
							//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
							cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
							cobro = cobroDao.modificar(cobro);
						} else {
							if(Estado.COB_COBRADO.equals(estado)){
								ShvWfWorkflow workflowActualizado = workflowCobros.cobrarCobro(cobro.getWorkflow(), "", usuario);
								cobro.setWorkflow(workflowActualizado);
								//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
								cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),true);
								cobro = cobroDao.modificar(cobro);
							} else {
								if(Estado.COB_ERROR_DESAPROPIACION.equals(estado)){
									ShvWfWorkflow workflowActualizado = workflowCobros.registrarErrorDesapropiacion(cobro.getWorkflow(), "", usuario);
									cobro.setWorkflow(workflowActualizado);
									//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
									cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
									cobro = cobroDao.modificar(cobro);
								} else {
									if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)){
										ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroEnProcesoAPendienteDeConfirmacionManual(cobro.getWorkflow(), "", usuario);
										cobro.setWorkflow(workflowActualizado);
										//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
										cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
										cobro = cobroDao.modificar(cobro);
									} else {
										if (Estado.COB_COBRADO_PARCIALMENTE.equals(estado)){
											ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroCobradoParcialmente(cobro.getWorkflow(), "", usuario);
											cobro.setWorkflow(workflowActualizado);
											//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
											cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),true);
											//TODO:
											/**
											 * Cuando el proceso de imputación de cobros deba modificar el estado a un estado final de tipo parcial, 
											 * se debe actualizar el importe parcial imputado del cobro.El valor con el cuál se debe actualizar 
											 * el importe parcial es el siguiente cálculo: El importe parcial del cobro será la suma de los importes 
											 * de los medios de pago de las transacciones que se encuentren apropiadas correctamente, 
											 * más el importe del tratamiento de la diferencia de tipo “débito a próxima factura” de aquellas transacciones 
											 * que se encuentren correctamente apropiadas.
											 * Para ello también será necesario agregar una nueva columna IMPORTE_PARCIAL en la tabla SHV_COB_ED_COBRO 
											 * a fin de poder almacenar 
											 */
											cobro = cobroDao.modificar(cobro);
										} else {
											if (Estado.COB_ERROR_CONFIRMACION_PARCIAL.equals(estado)){
												//TODO:VER
											} else {
												if (Estado.COB_ERROR_DESAPROPIACION_PARCIAL.equals(estado)){
													//TODO:VER
												} else {
													if (Estado.COB_PEND_DESAPROPIACION_MANUAL_EXTERNA.equals(estado)) {
														ShvWfWorkflow workflowActualizado = workflowCobros.registrarEnProcesoACobroPendienteDesapropiacionManualExterna(cobro.getWorkflow(), "", usuario);
														cobro.setWorkflow(workflowActualizado);
														//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
														cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),true);
														cobro = cobroDao.modificar(cobro);
													} else {
														if (Estado.COB_PARCIALMENTE_EN_ERROR.equals(estado)) {
															ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroEnProcesoACobroParcialmenteEnError(cobro.getWorkflow(), "", usuario);
															cobro.setWorkflow(workflowActualizado);
															//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
															cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),true);
															cobro = cobroDao.modificar(cobro);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} else {
				if (Estado.COB_ERROR_CONFIRMACION.equals(cobro.getWorkflow().getEstado())){
					if(Estado.COB_CONFIRMADO_CON_ERROR.equals(estado)){
						ShvWfWorkflow workflowActualizado = workflowCobros.reintentarConfirmacion(cobro.getWorkflow(), "", usuario);
						cobro.setWorkflow(workflowActualizado);
						//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
						cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),true);
						cobro = cobroDao.modificar(cobro);
					}
				} else if (Estado.COB_ERROR_DESAPROPIACION.equals(cobro.getWorkflow().getEstado())) {
					if (Estado.COB_PROCESO.equals(estado)) {
						ShvWfWorkflow workflowActualizado = workflowCobros.solicitarEnviarDesapropiacion(cobro.getWorkflow(), "", usuario);
						cobro.setWorkflow(workflowActualizado);
						//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
						cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
						cobro = cobroDao.modificar(cobro);
					} else if (Estado.COB_PROCESO_APLICACION_EXTERNA.equals(estado)) {
						ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroDeErrorEnDesapropiacionAEnProcesoDeAplicacionExterna(cobro.getWorkflow(), "", usuario);
						cobro.setWorkflow(workflowActualizado);
						//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
						cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
						cobro = cobroDao.modificar(cobro);
					} else if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL_Y_PROCESO_APLICACION_EXTERNA.equals(estado)) {
						ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroDeErrorEnDesapropiacionAPendienteConfirmacionManualYEnProcesoDeAplicacionExterna(cobro.getWorkflow(), "", usuario);
						cobro.setWorkflow(workflowActualizado);
						//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
						cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
						cobro = cobroDao.modificar(cobro);
					}

				}else if (Estado.COB_PARCIALMENTE_EN_ERROR.equals(cobro.getWorkflow().getEstado())){
					if (Estado.COB_COBRADO_PARCIALMENTE.equals(estado)) {
						ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroParcialmenteEnErrorACobroParcialmenteImputado(cobro.getWorkflow(), "", usuario);
						cobro.setWorkflow(workflowActualizado);
						//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
						cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
						cobro = cobroDao.modificar(cobro);
					} else{
						
						if (Estado.COB_REEDITADO_PARCIAL.equals(estado)) {
							ShvWfWorkflow workflowActualizado = workflowCobros.registrarCobroParcialmenteEnErrorACobroParcialmenteReeditado(cobro.getWorkflow(), "", usuario);
							cobro.setWorkflow(workflowActualizado);
							//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
							cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
							cobro = cobroDao.modificar(cobro);
						}
					}
					
				} else  if (Estado.COB_ERROR_COBRO.equals(cobro.getWorkflow().getEstado())){
					if (Estado.COB_ANULADO.equals(estado)) {
						ShvWfWorkflow workflowActualizado = workflowCobros.anularCobroErrorEnCobro(cobro.getWorkflow(), "", usuario);
						cobro.setWorkflow(workflowActualizado);
						//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
						cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
						cobro = cobroDao.modificar(cobro);
					} else {
						if (Estado.COB_REEDITADO_PARCIAL.equals(estado)) {
							ShvWfWorkflow workflowActualizado = workflowCobros.reeditarParcialmenteCobroErrorEnCobro(cobro.getWorkflow(), "", usuario);
							cobro.setWorkflow(workflowActualizado);
							//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
							cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
							cobro = cobroDao.modificar(cobro);
						} else {
							if (Estado.COB_REEDITADO.equals(estado)) {
								ShvWfWorkflow workflowActualizado = workflowCobros.reeditarCobroErrorEnCobro(cobro.getWorkflow(), "", usuario);
								cobro.setWorkflow(workflowActualizado);
								//setear la fecha de imputacion y ultima modificacion en SHV_COB_ED_COBRO
								cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
								cobro = cobroDao.modificar(cobro);
							}
						}
					}
				}
				else {
					Traza.advertencia(getClass(), "Se quizo avanzar en el workflow de cobros a un estado incorrecto. Id cobro: " + cobro.getOperacion().getIdOperacion() + "pero el cobro no esta en estado 'En Proceso'. ");
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}	
	
//	/**
//	 * Cambia el estado del workflow del cobro y lo guarda en la base
//	 * @param cobro
//	 * @param cobErrorCobro
//	 * @throws NegocioExcepcion 
//	 */
//	@Override
//	public void cambiarEstadoCobro(ShvCobCobro cobro, Estado estadoDestino, String usuario) throws NegocioExcepcion {
//		try {
//			ShvWfWorkflow workflowActualizado = null;
//			Estado estadoOrigen = cobro.getWorkflow().getEstado();
//			
//			if (Estado.COB_PROCESO.equals(estadoOrigen)){
//				
//				if(Estado.COB_ERROR_COBRO.equals(estadoDestino)){
//					workflowActualizado = workflowCobros.registrarErrorCobro(cobro.getWorkflow(), "", usuario);
//				}else{
//					if(Estado.COB_ERROR_APROPIACION.equals(estadoDestino)){
//						workflowActualizado = workflowCobros.registrarErrorApropiacion(cobro.getWorkflow(), "", usuario);
//					}else{
//						if(Estado.COB_ERROR_CONFIRMACION.equals(estadoDestino)){
//							workflowActualizado = workflowCobros.registrarErrorConfirmacion(cobro.getWorkflow(), "", usuario);
//						} else {
//							if(Estado.COB_COBRADO.equals(estadoDestino)){
//								workflowActualizado = workflowCobros.cobrarCobro(cobro.getWorkflow(), "", usuario);
//							} else {
//								if(Estado.COB_ERROR_DESAPROPIACION.equals(estadoDestino)){
//									workflowActualizado = workflowCobros.registrarErrorDesapropiacion(cobro.getWorkflow(), "", usuario);
//								} else {
//									if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estadoDestino)){
//										workflowActualizado = workflowCobros.registrarCobroEnProcesoAPendienteDeConfirmacionManual(cobro.getWorkflow(), "", usuario);
//									} else {
//										if (Estado.COB_COBRADO_PARCIALMENTE.equals(estadoDestino)){
//											workflowActualizado = workflowCobros.registrarCobroCobradoParcialmente(cobro.getWorkflow(), "", usuario);
//											//TODO:
//											/**
//											 * Cuando el proceso de imputación de cobros deba modificar el estado a un estado final de tipo parcial, 
//											 * se debe actualizar el importe parcial imputado del cobro.El valor con el cuál se debe actualizar 
//											 * el importe parcial es el siguiente cálculo: El importe parcial del cobro será la suma de los importes 
//											 * de los medios de pago de las transacciones que se encuentren apropiadas correctamente, 
//											 * más el importe del tratamiento de la diferencia de tipo “débito a próxima factura” de aquellas transacciones 
//											 * que se encuentren correctamente apropiadas.
//											 * Para ello también será necesario agregar una nueva columna IMPORTE_PARCIAL en la tabla SHV_COB_ED_COBRO 
//											 * a fin de poder almacenar 
//											 */
//										} else {
//											if (Estado.COB_ERROR_CONFIRMACION_PARCIAL.equals(estadoDestino)){
//												//TODO:VER
//												workflowActualizado = workflowCobros.registrarCobroEnErrorConfirmacionParcial(cobro.getWorkflow(), "", usuario);
//											} else {
//												if (Estado.COB_ERROR_DESAPROPIACION_PARCIAL.equals(estadoDestino)){
//													//TODO:VER
//													workflowActualizado = workflowCobros.registrarCobroEnErrorEnDesapropiacionParcial(cobro.getWorkflow(), "", usuario);
//												} else {
//													if (Estado.COB_PEND_DESAPROPIACION_MANUAL_EXTERNA.equals(estadoDestino)) {
//														workflowActualizado = workflowCobros.registrarEnProcesoACobroPendienteDesapropiacionManualExterna(cobro.getWorkflow(), "", usuario);
//													} else {
//														if (Estado.COB_PARCIALMENTE_EN_ERROR.equals(estadoDestino)) {
//															workflowActualizado = workflowCobros.registrarCobroEnProcesoACobroParcialmenteEnError(cobro.getWorkflow(), "", usuario);
//														}
//													}
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			} else if (Estado.COB_ERROR_CONFIRMACION.equals(estadoOrigen)){
//				if(Estado.COB_CONFIRMADO_CON_ERROR.equals(estadoDestino)){
//					workflowActualizado = workflowCobros.reintentarConfirmacion(cobro.getWorkflow(), "", usuario);
//				}
//
//			} else if (Estado.COB_ERROR_DESAPROPIACION.equals(estadoOrigen)) {
//				if (Estado.COB_PROCESO.equals(estadoDestino)) {
//					workflowActualizado = workflowCobros.solicitarEnviarDesapropiacion(cobro.getWorkflow(), "", usuario);
//				}
//			
//			} else if (Estado.COB_PARCIALMENTE_EN_ERROR.equals(estadoOrigen)){
//				if (Estado.COB_COBRADO_PARCIALMENTE.equals(estadoDestino)) {
//					workflowActualizado = workflowCobros.registrarCobroParcialmenteEnErrorACobroParcialmenteImputado(cobro.getWorkflow(), "", usuario);
//				} else{
//					
//					if (Estado.COB_REEDITADO_PARCIAL.equals(estadoDestino)) {
//						workflowActualizado = workflowCobros.registrarCobroParcialmenteEnErrorACobroParcialmenteReeditado(cobro.getWorkflow(), "", usuario);
//					}
//				}
//				
//			} else  if (Estado.COB_ERROR_COBRO.equals(estadoOrigen)){
//				if (Estado.COB_ANULADO.equals(estadoDestino)) {
//					workflowActualizado = workflowCobros.anularCobroErrorEnCobro(cobro.getWorkflow(), "", usuario);
//				} else {
//					if (Estado.COB_REEDITADO_PARCIAL.equals(estadoDestino)) {
//						workflowActualizado = workflowCobros.reeditarParcialmenteCobroErrorEnCobro(cobro.getWorkflow(), "", usuario);
//					} else {
//						if (Estado.COB_REEDITADO.equals(estadoDestino)) {
//							workflowActualizado = workflowCobros.reeditarCobroErrorEnCobro(cobro.getWorkflow(), "", usuario);
//						}
//					}
//				}
//			}
//			
//			if (!Validaciones.isObjectNull(workflowActualizado)) {
//				cobro.setWorkflow(workflowActualizado);
//				cobro = cobroDao.modificar(cobro);
//
//				//setear la fecha de imputación y última modificación en SHV_COB_ED_COBRO
//				cobroOnlineServicio.setearUsuarioYFechaImputacionYUltimaMdoficiacion(cobro.getOperacion().getIdOperacion(),workflowActualizado.getFechaUltimaModificacion(),false);
//			} else {
//				StringBuffer mensajeWarning = new StringBuffer();
//				mensajeWarning.append("Warning!: No existe una transicion contemplada dentro del metodo 'cambiarEstadoCobro()' para el ");
//				mensajeWarning.append("estado origen '");
//				mensajeWarning.append(estadoOrigen.descripcion());
//				mensajeWarning.append(" (");
//				mensajeWarning.append(estadoOrigen.name());
//				mensajeWarning.append(")' estado destino '");
//				mensajeWarning.append(estadoDestino.descripcion());
//				mensajeWarning.append(" (");
//				mensajeWarning.append(estadoDestino.name());
//				mensajeWarning.append(")' para la Operación de Cobro '");
//				mensajeWarning.append(cobro.getOperacion().getIdOperacion());
//				mensajeWarning.append("'.");
//
//				Traza.advertencia(getClass(),mensajeWarning.toString());
//			}
//
//		} catch (PersistenciaExcepcion e) {
//			throw new NegocioExcepcion(e.getMessage(),e);
//		}
//	}	
	
	@Override
	public void cambiarEstadoSegunEstadoDeTareas(Long idCobro, String usuario) throws NegocioExcepcion {
	
		try {
			ShvCobCobro cobro = cobroDao.buscarCobro(idCobro);
			cambiarEstadoSegunEstadoDeTareas(cobro, usuario);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * 
	 * @param cobro
	 * @param estadoCobroAAvanzar
	 */
	public void cambiarEstadoSegunEstadoDeTareas(ShvCobCobro cobro, String usuario) throws NegocioExcepcion{
		
		try {
			
			List<ShvWfTareaSimplificado> listaTareas = new ArrayList<ShvWfTareaSimplificado>();
			TareaFiltro filtro = new TareaFiltro();
			filtro.setIdWorkflow(cobro.getWorkflow().getIdWorkflow());
			Estado estado = null;
			
			listaTareas = tareaDaoImpl.listarTareasParaEstadoFinalDeCobro(filtro);
			
			boolean hayTareasPendientes = false;
			boolean hayTareasReeditadas = false;
			boolean todasAnuladas = false;
			boolean todasReeditadas = false;
			boolean existenTransaccionesConfirmadas = false;
			
			int cantidadDeTareas = listaTareas.size();
			int cantidadAnulados = 0;
			int cantidadReeditados = 0;
			
			for (ShvWfTareaSimplificado shvWfTarea : listaTareas) {
				
				if(TipoTareaEstadoEnum.PENDIENTE.equals(shvWfTarea.getEstado())){
					hayTareasPendientes = true;
					break;
				} else {
					
					switch (shvWfTarea.getTipoAccion()){
					
						case ANULADA:
							cantidadAnulados++;
							break;
						
						case REEDICION_COMPLETADA:
							cantidadReeditados++;
							hayTareasReeditadas = true;
							break;
					default:
						break;
					
					}
				}
			}
			
			if (!hayTareasPendientes){
				
				existenTransaccionesConfirmadas = hayTransaccionesConfirmadas(cobro);
				
				if(cantidadDeTareas == cantidadAnulados){
					todasAnuladas = true;
					
				} else if(cantidadDeTareas == cantidadReeditados){
					todasReeditadas = true;
				}
				
				if (existenTransaccionesConfirmadas){
					//Si todas las tareas están anuladas y existe alguna transaccion confirmada
					if(todasAnuladas){
						estado = Estado.COB_COBRADO_PARCIALMENTE;
					
					} else if(todasReeditadas || hayTareasReeditadas){
						estado = Estado.COB_REEDITADO_PARCIAL;
					} 
					
				} else {
					if(todasAnuladas){
						estado = Estado.COB_ANULADO;
						
					} else if(todasReeditadas){
						estado = Estado.COB_REEDITADO;
						
					} else if (hayTareasReeditadas){
						estado = Estado.COB_REEDITADO_PARCIAL;
					}
				}
				
				Traza.advertencia(this.getClass(), "El cobro " + cobro.getIdCobro()+ " que esta en estado "+ cobro.getWorkflow().getEstado() + " pasara al estado " + estado);
				
				cambiarEstadoCobro(cobro, estado, usuario);
				Traza.advertencia(this.getClass(), "El cobro " + cobro.getIdCobro()+ " se encuentra en el estado " + cobro.getWorkflow().getEstado());
				}
			
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	
	public boolean verificarTareasParaAnularCobro(Long idCobro, String usuario, Long idTarea) throws NegocioExcepcion{
		try {
			ShvCobCobro cobro = cobroDao.buscarCobro(idCobro);
			List<ShvWfTareaSimplificado> listaTareas = new ArrayList<ShvWfTareaSimplificado>();
			TareaFiltro filtro = new TareaFiltro();
			filtro.setIdWorkflow(cobro.getWorkflow().getIdWorkflow());

			listaTareas = tareaDaoImpl.listarTareasParaEstadoFinalDeCobro(filtro);
			
			
			int cantidadReeditados = 0;
			int cantidadPendientes = 0;
			int cantidadAnulados = 0;
			
			Long idTareaPendiente = 0l;

			for (ShvWfTareaSimplificado shvWfTarea : listaTareas) {
				
				if(TipoTareaEstadoEnum.PENDIENTE.equals(shvWfTarea.getEstado())){
					idTareaPendiente = shvWfTarea.getIdTarea();
					cantidadPendientes++;
					break;
				} else {
					switch (shvWfTarea.getTipoAccion()){
						case ANULADA:
							cantidadAnulados++;
							break;
						case REEDICION_COMPLETADA:
							cantidadReeditados++;
							break;
					default:
						break;
					
					}
				}
			}
			
//			APROBADA("Aprobada"),
//			RECHAZADA("Rechazada"),
//			CONFIRMADA("Confirmada"),
//			ANULADA("Anulada"),
//			REEDICION_COMPLETADA("Reedicion completada");
			boolean salida = false;
			if (
				(cantidadReeditados == 0) &&
				(cantidadAnulados == listaTareas.size() -1) &&
				cantidadPendientes == 1 &&
				idTareaPendiente.equals(idTarea)
			) {
				salida = true;
			} else {
				salida = false;
			}
			return salida;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	/**
	 * 
	 * @param idOperacion
	 * @param sistemaDestino
	 * @param sociedad
	 * @throws NegocioExcepcion
	 */
	@Override
	public void actualizarReferenciaMediosDePagoCTA(Long idCobro, SistemaEnum sistemaDestino, SociedadEnum sociedad, Long grupo) throws NegocioExcepcion {
		
		// si es un cobro nuevo con otros debitos.
		List<ShvCobTransaccionSinOperacion> listaTransacciones = new ArrayList<ShvCobTransaccionSinOperacion>();
		

		try {
			ShvCobEdCobroSimplificado cobro = cobroOnlineDao.buscarCobroSimplificadoPorIdCobro(idCobro);

			if(Validaciones.isObjectNull(grupo)){
				listaTransacciones = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYSistemaYSociedad(cobro.getIdOperacion(), sistemaDestino, sociedad);
				
			} else if (grupo == 0){
				listaTransacciones = cobroDao.buscarTransaccionSinOperacionPorIdOperacionYGrupo(cobro.getIdOperacion(), grupo);
			}
		
			//Primero hago esto, para que los cambios queden impactados en base
			for (ShvCobTransaccionSinOperacion transaccion : listaTransacciones) {
				
				//listo los mp CALIPSO CTA. En caso de haber, debemos modificar el numero de cta para las transacciones dependientes
				Set<ShvCobMedioPagoSinOperacion> listaMpCalipso = transaccion.getMediosPagoCta();
				
				cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(transaccion.getIdOperacion(), transaccion, false);
				
				//En esta lista voy a guardar los cta que contienen el dato a reemplazar
				List<ShvCobMedioPagoCTASinOperacion> listaMPCtaOriginal = new ArrayList<ShvCobMedioPagoCTASinOperacion>();
				
				//En esta lista voy a guardar los cta que tengo que reemplazar, o sea que actualmente tienen el valor del cta nuevo
				List<ShvCobMedioPagoCTASinOperacion> listaMPCtaNuevoValor = new ArrayList<ShvCobMedioPagoCTASinOperacion>();
				
				for (ShvCobMedioPagoSinOperacion shvCobMedioPagoCalipsoSinOperacion : listaMpCalipso) {
				
					if(shvCobMedioPagoCalipsoSinOperacion instanceof ShvCobMedioPagoCTASinOperacion){
						
						//si el mp es un cta y está en estado nuevo, entonces es el mp que tengo que buscar en las demás transacciones, para reemplazar
						// numero, clase, y sucursal del comprobante, por el valor anterior(original)
						//X-0780-00000077
						if(EstadoFacturaMedioPagoEnum.NUEVO.equals(shvCobMedioPagoCalipsoSinOperacion.getEstado())){
							if(!listaMPCtaNuevoValor.contains((ShvCobMedioPagoCTASinOperacion)shvCobMedioPagoCalipsoSinOperacion)){
								listaMPCtaNuevoValor.add((ShvCobMedioPagoCTASinOperacion)shvCobMedioPagoCalipsoSinOperacion);
							}
						} else {
							//Si el mp es un cta y no está en el estado nuevo, entonces es el mp que tengo que tomar la sucursal, numero y clase
							//para actualizar
							if(!listaMPCtaOriginal.contains((ShvCobMedioPagoCTASinOperacion)shvCobMedioPagoCalipsoSinOperacion)){
								listaMPCtaOriginal.add((ShvCobMedioPagoCTASinOperacion)shvCobMedioPagoCalipsoSinOperacion);
							}
						}
					}
							
				}
				
				for (ShvCobMedioPagoCTASinOperacion mpCtaNuevo:listaMPCtaNuevoValor){
					
					for (ShvCobMedioPagoCTASinOperacion mpCtaOriginal:listaMPCtaOriginal){
					
						if (mpCtaOriginal.getIdMedioPago().equals(mpCtaNuevo.getMedioPagoPadre().getIdMedioPago())){
	
							cobroBatchSoporteImputacionCalipsoServicio.setearIdCalipsoALosDemasMediosPago
								(transaccion.getIdOperacion(), transaccion.getNumeroTransaccion(), mpCtaNuevo, mpCtaOriginal);
						}
					}
				}
				
				cobroBatchSoporteImputacionServicio.tracearDatosProcesamientoTransaccion(transaccion.getIdOperacion(), transaccion, false);
				
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Si hay alguna transaccion confirmada, retorna true sino false.
	 * @param cobro
	 * @return
	 */
	public Boolean hayTransaccionesConfirmadas(ShvCobCobro cobro) {
		for (ShvCobTransaccion transaccion : cobro.getTransaccionesOrdenadas()) {
			if(EstadoTransaccionEnum.CONFIRMADA.equals(transaccion.getEstadoProcesamiento())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public CobroOnlineTransaccionesMapeador getCobroOnlineTransaccionesMapeador() {
		return cobroOnlineTransaccionesMapeador;
	}

	/**
	 * 
	 * @param cobroOnlineTransaccionesMapeador
	 */
	public void setCobroOnlineTransaccionesMapeador(CobroOnlineTransaccionesMapeador cobroOnlineTransaccionesMapeador) {
		this.cobroOnlineTransaccionesMapeador = cobroOnlineTransaccionesMapeador;
	}

	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long crear(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @return
	 */
	public ICobroDao getCobroDao() {
		return cobroDao;
	}

	/**
	 * 
	 * @param cobroDao
	 */
	public void setCobroDao(ICobroDao cobroDao) {
		this.cobroDao = cobroDao;
	}
}
