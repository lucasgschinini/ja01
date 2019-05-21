package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoRegistroOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.mapeadores.IOperacionMasivaRegistroMapeador;
import ar.com.telecom.shiva.base.registros.datos.entrada.MicOperacionMasivaEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaRegistroEntrada;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.OperacionMasivaRegistroVo;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaCobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaRegistroServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.dao.IRegistroOperacionMasivaDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroAplicarDeuda;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroGanancias;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroReintegro;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvMasOperacionMasivaSimplificadoWorkFlow;

public class OperacionMasivaRegistroServicioImpl implements IOperacionMasivaRegistroServicio {

	@Autowired 
	ICobroOnlineServicio cobroOnlineServicio;
	@Autowired
	private IParametroServicio parametroServicio;
	@Autowired
	IRegistroOperacionMasivaDao registroOperacionMasivaDao;
	@Autowired
	IOperacionMasivaRegistroMapeador micOperacionMasivaRegistroMapeador;
	
	@Autowired
	private IOperacionMasivaCobroServicio operacionMasivaCobroServicio;

	/**************************************************************************/
	/* Crear cobr                                                          */
	/**
	 * @throws NegocioExcepcion ************************************************************************/
	@Override
	public void generarCobroByOperacionMasivaProcesada(ShvMasOperacionMasivaSimplificadoWorkFlow shvMas) throws NegocioExcepcion {
		try {
			List<ShvMasRegistro> shvMasRegistros = registroOperacionMasivaDao.buscarRegistroByEstadoAndIdOperacionMasiva(
				EstadoRegistroOperacionMasivaEnum.PROCESADO,
				shvMas.getIdOperacionMasiva()
			);

			for(ShvMasRegistro re : shvMasRegistros) {
				OperacionMasivaRegistroVo vo = new OperacionMasivaRegistroVo();
				vo.setEmpresa(shvMas.getEmpresa());
				vo.setIdAnalista(shvMas.getIdAnalista());
				vo.setIdCopropietario(shvMas.getIdCopropietario());
				vo.setMotivo(shvMas.getMotivo());
				vo.setRegistro(re);
				vo.setIdOperacionMasiva(shvMas.getIdOperacionMasiva());
				vo.setSegmento(shvMas.getSegmento());
				vo.setTipoOperacionMasiva(shvMas.getTipoOperacionMasiva());
				vo.setUsuarioOperacionMasiva(shvMas.getIdAnalista());
				vo.setFechaAlta(shvMas.getFechaCreacion());
				vo.setFechaDerivacion(re.getFechaEntrada());
				vo.setMonedaDelCobro(MonedaEnum.PES);
				Long idCobro = operacionMasivaCobroServicio.crearCobro(vo);
				if (!Validaciones.isObjectNull(idCobro)) {
					operacionMasivaCobroServicio.generarCobroBatch(idCobro, re);
					re.setIdCobro(idCobro);
					//re.setFechaSalida(shvMas.get);
					registroOperacionMasivaDao.modificar(re);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**************************************************************************/
	/* Crear cobr                                                          */
	/**
	 * @throws NegocioExcepcion ************************************************************************/
	@Override
	public void generarCobroByListaShvMasRegistro(List<ShvMasRegistro> shvMasRegistros) throws NegocioExcepcion {
		try {
			
			for(ShvMasRegistro re : shvMasRegistros) {
				OperacionMasivaRegistroVo vo = new OperacionMasivaRegistroVo();
				vo.setEmpresa(re.getOperacionMasiva().getEmpresa());
				vo.setIdAnalista(re.getOperacionMasiva().getIdAnalista());
				vo.setIdCopropietario(re.getOperacionMasiva().getIdCopropietario());
				vo.setMotivo(re.getOperacionMasiva().getMotivo());
				vo.setRegistro(re);
				vo.setIdOperacionMasiva(re.getOperacionMasiva().getIdOperacionMasiva());
				vo.setSegmento(re.getOperacionMasiva().getSegmento());
				vo.setTipoOperacionMasiva(re.getOperacionMasiva().getTipoOperacionMasiva());
				vo.setUsuarioOperacionMasiva(re.getOperacionMasiva().getIdAnalista());
				vo.setFechaAlta(re.getOperacionMasiva().getFechaCreacion());
				vo.setFechaDerivacion(re.getFechaEntrada());
				vo.setMonedaDelCobro(MonedaEnum.PES);
				Traza.auditoria(getClass(), "Se genera el cobro de idOperacion: " + vo.getRegistro().getIdOperacion() + " y idOperacionMasiva: " + vo.getIdOperacionMasiva());
				Long idCobro = operacionMasivaCobroServicio.crearCobro(vo);
				if (!Validaciones.isObjectNull(idCobro)) {
					operacionMasivaCobroServicio.generarCobroBatch(idCobro, re);
					re.setIdCobro(idCobro);
					//re.setFechaSalida(shvMas.get);
					registroOperacionMasivaDao.modificar(re);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	/**************************************************************************/
	/* Volcar datos                                                           */
	/**************************************************************************/
	@Override
	public List<ShvMasRegistro> volcarDatosArchivoEntraMic(MicOperacionMasivaEntrada reg) throws NegocioExcepcion {
		Traza.auditoria(this.getClass(), "- Se vuelcan los datos a la base de datos");
		
		int index = 1;
		List<ShvMasRegistro> registrosPersistidos = new ArrayList<ShvMasRegistro>(); 
		for (MicOperacionMasivaRegistroEntrada registro: reg.getRegistros()) {
			
			Traza.auditoria(this.getClass(), "- idOperacion: " + registro.getParametrosGenerales().getIdOperacion() );
			Traza.auditoria(this.getClass(), "- idOperacion Masiva: " + registro.getParametrosGenerales().getIdOperacionMasiva());
			
			ShvMasRegistro modelo = null;
			try {
				modelo =  registroOperacionMasivaDao.buscarRegistroByEstadoAndIdOperacion(
					EstadoRegistroOperacionMasivaEnum.PROCESO_IMPUTACION,
					registro.getParametrosGenerales().getIdOperacion());
				
				if (modelo == null) {
					Traza.auditoria(this.getClass(), "- No existen registros correspondientes a la operacion: " + registro.getParametrosGenerales().getIdOperacion() + " para el vuelco de datos.");
					throw new NegocioExcepcion ("No existen registros correspondientes a la operacion: " + registro.getParametrosGenerales().getIdOperacion() + " para el vuelco de datos.");
					
				}
				
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion("Se ha producido un error en la operación: " + registro.getParametrosGenerales().getIdOperacion(), e);
			}
			
			modelo = micOperacionMasivaRegistroMapeador.mapRegistroEntrada(registro.getRegistro(), modelo);
			if (this.verficarResultado(modelo)) {
				modelo.setEstado(EstadoRegistroOperacionMasivaEnum.PROCESADO);
			} else {
				if (
					Validaciones.isObjectNull(modelo.getResultadoConsultaCredito()) &&
					Validaciones.isObjectNull(modelo.getResultadoConsultaDebito()) &&
					Validaciones.isObjectNull(modelo.getResultadoConsultaReintegro())
				) {
					throw new NegocioExcepcion("Registro numero: " + index + " La respuesta de mic esta vacia");
				} else { 
					modelo.setEstado(EstadoRegistroOperacionMasivaEnum.ERROR_MIC);
				}
			}
			
			try {
				modelo.setFechaModificacion(new Date());
				modelo = registroOperacionMasivaDao.modificar(modelo);
				if (EstadoRegistroOperacionMasivaEnum.PROCESADO.equals(modelo.getEstado())) {
					registrosPersistidos.add(modelo);
				}
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion("No se pudo actualizar el registro id " + modelo.getIdRegistro(), e);
			}
			index++;
		}
		return registrosPersistidos;
	}
	
	private boolean verficarResultado(ShvMasRegistro modelo) {
		boolean salida = false;
		
		if (modelo instanceof ShvMasRegistroGanancias) {
			if (
				!Validaciones.isObjectNull(modelo.getResultadoConsultaCredito()) &&
				(
					TipoResultadoEnum.OK.equals(modelo.getResultadoConsultaCredito()) ||
					TipoResultadoEnum.WRN.equals(modelo.getResultadoConsultaCredito())
				)
			) {
				salida = true;
			}
		} else if (modelo instanceof ShvMasRegistroAplicarDeuda) {
			if (
				!Validaciones.isObjectNull(modelo.getResultadoConsultaCredito()) && 
					(
						TipoResultadoEnum.OK.equals(modelo.getResultadoConsultaCredito())||
						TipoResultadoEnum.WRN.equals(modelo.getResultadoConsultaCredito())
					) &&
				!Validaciones.isObjectNull(modelo.getResultadoConsultaDebito()) && 
				(
					TipoResultadoEnum.OK.equals(modelo.getResultadoConsultaDebito()) ||
					TipoResultadoEnum.WRN.equals(modelo.getResultadoConsultaDebito())
				)
			) {
				salida = true;
			}
		} else if (modelo instanceof ShvMasRegistroDesistimiento) {
			if (!Validaciones.isObjectNull(modelo.getResultadoConsultaDebito()) && 
				(
					TipoResultadoEnum.OK.equals(modelo.getResultadoConsultaDebito()) ||
					TipoResultadoEnum.WRN.equals(modelo.getResultadoConsultaDebito())
				)
			) {
				salida = true;
			}
		} else if (modelo instanceof ShvMasRegistroReintegro) {
			if (
				!Validaciones.isObjectNull(modelo.getResultadoConsultaReintegro()) &&
				(TipoResultadoEnum.OK.equals(modelo.getResultadoConsultaReintegro()) ||
				TipoResultadoEnum.WRN.equals(modelo.getResultadoConsultaReintegro())		
				)&&
				!Validaciones.isObjectNull(modelo.getResultadoConsultaCredito()) &&
				(
					TipoResultadoEnum.WRN.equals(modelo.getResultadoConsultaCredito()) ||
					TipoResultadoEnum.OK.equals(modelo.getResultadoConsultaCredito())
				)
			) {
				salida = true;
			}
		}
		return salida;
	}
	
	/**************************************************************************/
	/* Gestionabilidad                                                        */ 
	/**************************************************************************/
	@Override
	public List<ShvMasRegistro> realizarGestionabiliadRegistrosOperacionMasiva(List<ShvMasRegistro> listaShvMasRegistro) throws NegocioExcepcion {
		for (ShvMasRegistro shvMasRegistro : listaShvMasRegistro) {
			shvMasRegistro = this.gestionabilidadDeRegistrosOperacionMasiva(shvMasRegistro);
		}
		return listaShvMasRegistro;
	}
	@Override
	public ShvMasRegistro gestionabilidadDeRegistrosOperacionMasiva(ShvMasRegistro shvMasRegistro) throws NegocioExcepcion {
		if (shvMasRegistro instanceof ShvMasRegistroAplicarDeuda) {
			shvMasRegistro = this.gestionabilidadDeRegistrosOperacionMasiva(
				(ShvMasRegistroAplicarDeuda) shvMasRegistro
			);
		} else if (shvMasRegistro instanceof ShvMasRegistroGanancias) {
			shvMasRegistro = this.gestionabilidadDeRegistrosOperacionMasiva(
				(ShvMasRegistroGanancias) shvMasRegistro
			);
		} else if (shvMasRegistro instanceof ShvMasRegistroReintegro) {
			shvMasRegistro = this.gestionabilidadDeRegistrosOperacionMasiva(
				(ShvMasRegistroReintegro) shvMasRegistro
			);
		} else if (shvMasRegistro instanceof ShvMasRegistroDesistimiento) {
			shvMasRegistro = this.gestionabilidadDeRegistrosOperacionMasiva(
				(ShvMasRegistroDesistimiento) shvMasRegistro
			);
		}
		
		return shvMasRegistro;
	}

	private ShvMasRegistro gestionabilidadDeRegistrosOperacionMasiva(ShvMasRegistroAplicarDeuda shvMasRegistro) throws NegocioExcepcion {
		boolean creditoGestionabilidad = false;
		boolean debitoGestionabilidad = false;
		List<String> detalleError = new ArrayList<String>();
		
		if (Validaciones.isObjectNull(shvMasRegistro.getNumeroReferenciaNC())) {
			// El credito es del tipo REMANENTE
			StringBuffer str = new StringBuffer();
			if (!Validaciones.isObjectNull(shvMasRegistro.getClientesSiebel())) {
				str.append(shvMasRegistro.getClientesSiebel().getClienteDuenoCredito() != null ? shvMasRegistro.getClientesSiebel().getClienteDuenoCredito().toString():"");
			}
			str.append(shvMasRegistro.getCuenta() != null ? shvMasRegistro.getCuenta().toString():"");
			str.append(shvMasRegistro.getTipoRemanente() != null ? shvMasRegistro.getTipoRemanente().getCodigo() : "");

			creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnCobrosPendienteProceso(str.toString());
			if (!creditoGestionabilidad) {
				creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnDescobrosPendienteProceso(str.toString());
				if (creditoGestionabilidad) {
					detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.reversion.pendiente.proceso"));
				}
			} else {
				detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.cobro.pndiente.proceso"));
			}
		} else {
			creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnCobrosPendienteProcesoByNumeroRefMic(
				Long.parseLong(shvMasRegistro.getNumeroReferenciaNC()
			));
			if (!creditoGestionabilidad) {
				creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnDescobrosPendienteProcesoByNumeroRefMic(
					Long.parseLong(shvMasRegistro.getNumeroReferenciaNC()
				));
				if (creditoGestionabilidad) {
					detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.reversion.pendiente.proceso"));
				}
			} else {
				detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.cobro.pndiente.proceso"));
			}
		}
		// Debito
		debitoGestionabilidad = cobroOnlineServicio.obtenerMarcaDebitoEnCobrosPendienteProcesoByNumeroRefMic(
			shvMasRegistro.getNumeroReferenciaFactura()
		);
		if (!debitoGestionabilidad) {
			debitoGestionabilidad = cobroOnlineServicio.obtenerMarcaDebitoEnCobrosPendienteProcesoByNumeroRefMic(
				shvMasRegistro.getNumeroReferenciaFactura()
			);
			if (debitoGestionabilidad) {
				detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.reversion.pendiente.proceso"));
			}
		} else {
			detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.cobro.pndiente.proceso"));
		}
		if (debitoGestionabilidad || creditoGestionabilidad) {
			shvMasRegistro.setEstado(EstadoRegistroOperacionMasivaEnum.ERROR_SHIVA);
			shvMasRegistro.setFechaModificacion(new Date());
			shvMasRegistro.setUsuarioModificacion(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			if (!detalleError.isEmpty()) {
				shvMasRegistro.setDescripcionErrorRegistro(StringUtils.join(detalleError.toArray(new String[detalleError.size()]), " - "));
			}
		} 
		return shvMasRegistro;
	}
	private ShvMasRegistro gestionabilidadDeRegistrosOperacionMasiva(ShvMasRegistroGanancias shvMasRegistro) throws NegocioExcepcion {
		boolean creditoGestionabilidad = false;
		List<String> detalleError = new ArrayList<String>();
		
		// Credito
		if (Validaciones.isObjectNull(shvMasRegistro.getNumeroReferenciaNC())) {
			// El credito es del tipo REMANENTE
			StringBuffer str = new StringBuffer();
			if (!Validaciones.isObjectNull(shvMasRegistro.getClientesSiebel())) {
				str.append(shvMasRegistro.getClientesSiebel().getClienteDuenoCredito() != null ? shvMasRegistro.getClientesSiebel().getClienteDuenoCredito().toString():"");
			}
			str.append(shvMasRegistro.getCuentaOrigen() != null ? shvMasRegistro.getCuentaOrigen().toString():"");
			str.append(shvMasRegistro.getTipoRemanente() != null ? shvMasRegistro.getTipoRemanente().getCodigo() : "");

			creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnCobrosPendienteProceso(str.toString());
			if (!creditoGestionabilidad) {
				creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnDescobrosPendienteProceso(str.toString());
				if (creditoGestionabilidad) {
					detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.reversion.pendiente.proceso"));
				}
			} else {
				detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.cobro.pndiente.proceso"));
			}
		} else {
			creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnCobrosPendienteProcesoByNumeroRefMic(
				shvMasRegistro.getNumeroReferenciaNC()
			);
			if (!creditoGestionabilidad) {
				creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnDescobrosPendienteProcesoByNumeroRefMic(
					shvMasRegistro.getNumeroReferenciaNC()
				);
				if (creditoGestionabilidad) {
					detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.reversion.pendiente.proceso"));
				}
			} else {
				detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.cobro.pndiente.proceso"));
			}
		}
		if (creditoGestionabilidad) {
			shvMasRegistro.setEstado(EstadoRegistroOperacionMasivaEnum.ERROR_SHIVA);
			shvMasRegistro.setFechaModificacion(new Date());
			shvMasRegistro.setUsuarioModificacion(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			if (!detalleError.isEmpty()) {
				shvMasRegistro.setDescripcionErrorRegistro(StringUtils.join(detalleError.toArray(new String[detalleError.size()]), " - "));
			}
		}
		return shvMasRegistro;
	}
	private ShvMasRegistro gestionabilidadDeRegistrosOperacionMasiva(ShvMasRegistroReintegro shvMasRegistro) throws NegocioExcepcion {
		boolean creditoGestionabilidad = false;
		List<String> detalleError = new ArrayList<String>();
		
		// Medio de pago
		if (Validaciones.isObjectNull(shvMasRegistro.getNumeroReferenciaNC())) {
			// El credito es del tipo REMANENTE
			StringBuffer str = new StringBuffer();
			if (!Validaciones.isObjectNull(shvMasRegistro.getClientesSiebel())) {
				str.append(shvMasRegistro.getClientesSiebel().getClienteDuenoCredito() != null ? shvMasRegistro.getClientesSiebel().getClienteDuenoCredito().toString():"");
			}
			str.append(shvMasRegistro.getCuenta() != null ? shvMasRegistro.getCuenta().toString():"");
			str.append(shvMasRegistro.getTipoRemanente() != null ? shvMasRegistro.getTipoRemanente().getCodigo() : "");

			creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnCobrosPendienteProceso(str.toString());
			if (!creditoGestionabilidad) {
				creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnDescobrosPendienteProceso(str.toString());
				if (creditoGestionabilidad) {
					detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.reversion.pendiente.proceso"));
				}
			} else {
				detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.cobro.pndiente.proceso"));
			}
		} else {
			creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnCobrosPendienteProcesoByNumeroRefMic(
				shvMasRegistro.getNumeroReferenciaNC()
			);
			if (!creditoGestionabilidad) {
				creditoGestionabilidad = cobroOnlineServicio.obtenerMarcaCreditoEnDescobrosPendienteProcesoByNumeroRefMic(
					shvMasRegistro.getNumeroReferenciaNC()
				);
				if (creditoGestionabilidad) {
					detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.reversion.pendiente.proceso"));
				}
			} else {
				detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.cobro.pndiente.proceso"));
			}
		}
		
		if (creditoGestionabilidad) {
			shvMasRegistro.setEstado(EstadoRegistroOperacionMasivaEnum.ERROR_SHIVA);
			shvMasRegistro.setFechaModificacion(new Date());
			shvMasRegistro.setUsuarioModificacion(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			if (!detalleError.isEmpty()) {
				shvMasRegistro.setDescripcionErrorRegistro(StringUtils.join(detalleError.toArray(new String[detalleError.size()]), " - "));
			}
		} 
		return shvMasRegistro;
	}
	private ShvMasRegistro gestionabilidadDeRegistrosOperacionMasiva(ShvMasRegistroDesistimiento shvMasRegistro) throws NegocioExcepcion {
		boolean debitoGestionabilidad = false;
		List<String> detalleError = new ArrayList<String>();
		
		// Debito
		debitoGestionabilidad = cobroOnlineServicio.obtenerMarcaDebitoEnCobrosPendienteProcesoByNumeroRefMic(
			shvMasRegistro.getNumeroReferenciaFactura()
		);
		if (!debitoGestionabilidad) {
			debitoGestionabilidad = cobroOnlineServicio.obtenerMarcaDebitoEnCobrosPendienteProcesoByNumeroRefMic(
				shvMasRegistro.getNumeroReferenciaFactura()
			);
			if (debitoGestionabilidad) {
				detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.reversion.pendiente.proceso"));
			}
		} else {
			detalleError.add(Propiedades.MENSAJES_PROPIEDADES.getString("operacionesMasivas.gestionabilidad.marca.cobro.pndiente.proceso"));
		}
		if (debitoGestionabilidad) {
			shvMasRegistro.setEstado(EstadoRegistroOperacionMasivaEnum.ERROR_SHIVA);
			shvMasRegistro.setFechaModificacion(new Date());
			shvMasRegistro.setUsuarioModificacion(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			if (!detalleError.isEmpty()) {
				shvMasRegistro.setDescripcionErrorRegistro(StringUtils.join(detalleError.toArray(new String[detalleError.size()]), " - "));
			}
		} 
		return shvMasRegistro;
	}
	public String test() {
		return "operacionMasivaRegistroServicio";
	}
	
	public String generarArchivoOperacionMasivaRespuesta(List<ShvMasRegistro> listaRegistros, TipoArchivoOperacionMasivaEnum tipoOperacion){
		String registrosProcesados = null;

		switch (tipoOperacion.getName()) {
		case "DEUDA":
			registrosProcesados = procesarRegistrosAplicarDeuda(listaRegistros);
			break;
		case "DSIST":
			registrosProcesados = procesarRegistrosDesestimiento(listaRegistros);
			break;
		case "GNCIA":
			registrosProcesados = procesarRegistrosGanancia(listaRegistros);
			break;
		case "REINT":
			registrosProcesados = procesarRegistrosReintegro(listaRegistros);
			break;
		}
		return registrosProcesados;
		
	}
	
	/**
	 * 
	 * @param listaRegistros
	 * @return
	 */
	private String procesarRegistrosAplicarDeuda(List<ShvMasRegistro> listaRegistros){
		StringBuffer registrosArchivo = new StringBuffer(); 
		String contenidoArchivo;
		boolean agregarGuion = false;

		for (ShvMasRegistro shvMasRegistro : listaRegistros) {
			ShvMasRegistroAplicarDeuda registrosAplicarDeuda = (ShvMasRegistroAplicarDeuda) shvMasRegistro;

			// Descripcion del tipo de Operacion (total o parcial)
			if (registrosAplicarDeuda.getTipoOperacion() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getTipoOperacion().getDescripcionCorta());
			} else {
				registrosArchivo.append("");
			}

			// Cliente dueño del debito
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getClientesSiebel() != null && registrosAplicarDeuda.getClientesSiebel().getClienteDuenoDebito() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getClientesSiebel().getClienteDuenoDebito().toString());
			} else {
				registrosArchivo.append("");
			}

			// Numero de referencia de factura
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getNumeroReferenciaFactura() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getNumeroReferenciaFactura().toString());
			} else {
				registrosArchivo.append("");
			}

			// Destransferir terceros
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getDestransferirTerceros() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getDestransferirTerceros().getDescripcionCorta());
			} else {
				registrosArchivo.append("");
			}

			// Deuda migrada
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getDeudaMigrada() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getDeudaMigrada().getDescripcionCorta());
			} else {
				registrosArchivo.append("");
			}

			// Importe
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getImporte() != null) {
				registrosArchivo.append(Utilidad.formatDecimalesACentavosComoTexto(registrosAplicarDeuda.getImporte()));
			} else {
				registrosArchivo.append("");
			}

			// Cliente dueño del credito
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getClientesSiebel() != null && registrosAplicarDeuda.getClientesSiebel().getClienteDuenoCredito() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getClientesSiebel().getClienteDuenoCredito().toString());
			} else {
				registrosArchivo.append("");
			}

			// Cuenta
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getCuenta() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getCuenta().toString());
			} else {
				registrosArchivo.append("");
			}

			// Tipo de Remanente
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getTipoRemanente() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getTipoRemanente().getDescripcion());
			} else {
				registrosArchivo.append("");
			}

			// Numero de referencia de nota de credito
			registrosArchivo.append(Constantes.SEMICOLON);
			if (!Validaciones.isNullOrEmpty(registrosAplicarDeuda.getNumeroReferenciaNC())) {
				registrosArchivo.append(registrosAplicarDeuda.getNumeroReferenciaNC().toString());
			} else {
				registrosArchivo.append("");
			}
			
			// Credito migrado
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getCreditoMigrado() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getCreditoMigrado().getDescripcionCorta());
			} else {
				registrosArchivo.append("");
			}

			// Accion sobre intereses
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getAccionSobreIntereses() != null && !Validaciones.isEmptyString(registrosAplicarDeuda.getAccionSobreIntereses().getCodigoMicApropiacion())) {
				registrosArchivo.append(registrosAplicarDeuda.getAccionSobreIntereses().getCodigoMicApropiacion());
			} else {
				registrosArchivo.append("");
			}
			
			// Porcentaje de bonificacion de intereses
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getPorcentajeBonificacionIntereses() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getPorcentajeBonificacionIntereses().toString());
			} else {
				registrosArchivo.append("");
			}
			
			// Importe de bonificacion de intereses
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getImporteBonificacionIntereses() != null) {
				if (
					!TratamientoInteresesEnum.BV.equals(registrosAplicarDeuda.getAccionSobreIntereses()) &&
					BigDecimal.ZERO.equals(registrosAplicarDeuda.getImporteBonificacionIntereses())
				) {
					registrosArchivo.append("");
				} else {
					registrosArchivo.append(Utilidad.formatDecimalesACentavosComoTexto(registrosAplicarDeuda.getImporteBonificacionIntereses()));
					
				}
				
			} else {
				registrosArchivo.append("");
			}
			
			// Cliente dueño del acuerdo de facturacion
			registrosArchivo.append(Constantes.SEMICOLON);
			if(registrosAplicarDeuda.getClientesSiebel() != null && registrosAplicarDeuda.getClientesSiebel().getClienteDuenoAcuerdo() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getClientesSiebel().getClienteDuenoAcuerdo().toString());
			} else {
				registrosArchivo.append("");
			}
			
			// Acuerdo de facturacion destino
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getAcuerdoFacturacionDestino() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getAcuerdoFacturacionDestino().toString());
			} else {
				registrosArchivo.append("");
			}
			
			// Registros agregados al descargar

			// Estado del registro de operacion masiva
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosAplicarDeuda.getEstado() != null) {
				registrosArchivo.append(registrosAplicarDeuda.getEstado().getDescripcion());
			} 

			// Id de Operacion de Cobro
			registrosArchivo.append(Constantes.SEMICOLON);
			if (
				!Validaciones.isObjectNull(registrosAplicarDeuda.getEstado()) &&
				(
					EstadoRegistroOperacionMasivaEnum.PROCESO_IMPUTACION.equals(registrosAplicarDeuda.getEstado()) ||
					EstadoRegistroOperacionMasivaEnum.PROCESADO.equals(registrosAplicarDeuda.getEstado())
				)
			) {
				if (registrosAplicarDeuda.getIdOperacion() != null){
					registrosArchivo.append(registrosAplicarDeuda.getIdOperacion().toString());
				}
			}

			// Codigo de respuesta provista por el cobrador
			registrosArchivo.append(Constantes.SEMICOLON);
			String codigoError = obtenerCodigoErrorGanancias(registrosAplicarDeuda);
			registrosArchivo.append(codigoError);

			// Descripcion de los errores provistos por el cobrador
			registrosArchivo.append(Constantes.SEMICOLON);

			if (!TipoResultadoEnum.OK.getDescripcionMic().equals(codigoError)) {
				if(registrosAplicarDeuda.getDescripcionErrorCredito() != null){
					registrosArchivo.append(registrosAplicarDeuda.getDescripcionErrorCredito().trim());
					agregarGuion=true;
				} 
	
				if (registrosAplicarDeuda.getDescripcionErrorDebito() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					} else {
						agregarGuion=true;
					}
					registrosArchivo.append(registrosAplicarDeuda.getDescripcionErrorDebito().trim());
				} 

				if (registrosAplicarDeuda.getDescripcionErrorRegistro()!= null){
					if (agregarGuion) {
					registrosArchivo.append(Constantes.SIGNO_MENOS);
					}else
					{
						agregarGuion=true;
					}
					registrosArchivo.append(registrosAplicarDeuda.getDescripcionErrorRegistro().trim());
					
				} 
				
				if (registrosAplicarDeuda.getDescripcionErrorReintegro() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					}
					registrosArchivo.append(registrosAplicarDeuda.getDescripcionErrorReintegro().trim());
				}
			}

			// Importe Intereses trasladados
			registrosArchivo.append(Constantes.SEMICOLON);
			BigDecimal interesesTrasladados = new BigDecimal(0);
			if (!Validaciones.isObjectNull(registrosAplicarDeuda.getEstado()) && EstadoRegistroOperacionMasivaEnum.PROCESADO.equals(registrosAplicarDeuda.getEstado())) {
				if (!Validaciones.isObjectNull(registrosAplicarDeuda.getCobranzaApropiacionInteresesTrasladadosRegulados())) {
					interesesTrasladados = interesesTrasladados.add(registrosAplicarDeuda.getCobranzaApropiacionInteresesTrasladadosRegulados());
				}
				if (!Validaciones.isObjectNull(registrosAplicarDeuda.getCobranzaApropiacionInteresesTrasladadosNoRegulados())) {
					interesesTrasladados = interesesTrasladados.add(registrosAplicarDeuda.getCobranzaApropiacionInteresesTrasladadosNoRegulados());
				}
				registrosArchivo.append(Utilidad.formatDecimalesACentavosComoTexto(interesesTrasladados));
			}

			// Importe Intereses bonificados
			registrosArchivo.append(Constantes.SEMICOLON);
			BigDecimal interesesBonificados = new BigDecimal(0);
			if (!Validaciones.isObjectNull(registrosAplicarDeuda.getEstado()) && EstadoRegistroOperacionMasivaEnum.PROCESADO.equals(registrosAplicarDeuda.getEstado())) {
				if (!Validaciones.isObjectNull(registrosAplicarDeuda.getCobranzaApropiacionInteresesBonificadosRegulados())) {
					interesesBonificados = interesesBonificados.add(registrosAplicarDeuda.getCobranzaApropiacionInteresesBonificadosRegulados());
				}
				if (!Validaciones.isObjectNull(registrosAplicarDeuda.getCobranzaApropiacionInteresesBonificadosNoRegulados())) {
					interesesBonificados = interesesBonificados.add(registrosAplicarDeuda.getCobranzaApropiacionInteresesBonificadosNoRegulados());
				}
				registrosArchivo.append(Utilidad.formatDecimalesACentavosComoTexto(interesesBonificados));
			}

			// Fecha valor
			registrosArchivo.append(Constantes.SEMICOLON);
			if (!Validaciones.isObjectNull(registrosAplicarDeuda.getEstado()) && EstadoRegistroOperacionMasivaEnum.PROCESADO.equals(registrosAplicarDeuda.getEstado())) {
				registrosArchivo.append(Utilidad.formatDateAAAAMMDD(registrosAplicarDeuda.getFechaValorCobranza()));
			}

			registrosArchivo.append(Constantes.RETORNO_WIN);
			agregarGuion = false;
		}

		contenidoArchivo = registrosArchivo.toString();
		return contenidoArchivo;
	}
	
	/**
	 *
	 * @param listaRegistros
	 * @return
	 */
	private String procesarRegistrosDesestimiento(List<ShvMasRegistro> listaRegistros) {
		StringBuffer registrosArchivo = new StringBuffer(); 
		String contenidoArchivo;
		boolean agregarGuion=false;

		for (ShvMasRegistro shvMasRegistro : listaRegistros) {
			
			ShvMasRegistroDesistimiento registrosDesestimiento = (ShvMasRegistroDesistimiento) shvMasRegistro;
			
			// Cliente dueño del debito
			if (registrosDesestimiento.getClientesSiebel().getClienteDuenoDebito() != null) {
				registrosArchivo.append(registrosDesestimiento.getClientesSiebel().getClienteDuenoDebito().toString());
			} else {
				registrosArchivo.append("");
			}
			registrosArchivo.append(Constantes.SEMICOLON);
			
			// Numero de referencia de Factura
			if (registrosDesestimiento.getNumeroReferenciaFactura() != null) {
				registrosArchivo.append(registrosDesestimiento.getNumeroReferenciaFactura().toString());
			} else {
				registrosArchivo.append("");
			}
			registrosArchivo.append(Constantes.SEMICOLON);
			
			// Importe
			if (registrosDesestimiento.getImporte() != null) {
				registrosArchivo.append(Utilidad.formatDecimalesACentavosComoTexto(registrosDesestimiento.getImporte()));
			} else {
				registrosArchivo.append("");
			}
			registrosArchivo.append(Constantes.SEMICOLON);
			
			// Deuda migrada
			if(registrosDesestimiento.getDeudaMigrada() != null){
				registrosArchivo.append(registrosDesestimiento.getDeudaMigrada().getDescripcionCorta());
			} else {
				registrosArchivo.append("");
			}
			registrosArchivo.append(Constantes.SEMICOLON);
			
			// Numero de acta de desistimiento
			if (!Validaciones.isEmptyString(registrosDesestimiento.getNumeroActaDesistimiento())) {
				registrosArchivo.append(registrosDesestimiento.getNumeroActaDesistimiento());
			} else {
				registrosArchivo.append("");
			}
			registrosArchivo.append(Constantes.SEMICOLON);

			// Fecha de acta de desistimiento
			if (registrosDesestimiento.getFechaActaDesistimiento() != null) {
				registrosArchivo.append(Utilidad.formatDateAAAAMMDD(registrosDesestimiento.getFechaActaDesistimiento()));
			} else {
				registrosArchivo.append("");
			}
			
			//Registros agregados al descargar
			
			// Estado del registro de operacion masiva
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosDesestimiento.getEstado() != null) {
				registrosArchivo.append(registrosDesestimiento.getEstado().getDescripcion());
			} 

			// Id de Operacion Masiva
			registrosArchivo.append(Constantes.SEMICOLON);
			if (
				!Validaciones.isObjectNull(registrosDesestimiento.getEstado()) &&
				(
					EstadoRegistroOperacionMasivaEnum.PROCESO_IMPUTACION.equals(registrosDesestimiento.getEstado()) ||
					EstadoRegistroOperacionMasivaEnum.PROCESADO.equals(registrosDesestimiento.getEstado())
				)
			) {
				if (registrosDesestimiento.getIdOperacion() != null){
					registrosArchivo.append(registrosDesestimiento.getIdOperacion().toString());
				}
			}
			
			// Codigo de respuesta provista por el proveedor
			registrosArchivo.append(Constantes.SEMICOLON);
			String codigoError = obtenerCodigoErrorGanancias(registrosDesestimiento);
			registrosArchivo.append(codigoError);
			
			// Descripcion del error provista por el proveedor
			registrosArchivo.append(Constantes.SEMICOLON);
			
			if (!TipoResultadoEnum.OK.getDescripcionMic().equals(codigoError)) {
				if (registrosDesestimiento.getDescripcionErrorCredito() != null) {
					registrosArchivo.append(registrosDesestimiento.getDescripcionErrorCredito().trim());
					agregarGuion=true;
				} 
	
				if (registrosDesestimiento.getDescripcionErrorDebito() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					} else {
						agregarGuion=true;
					}
					registrosArchivo.append(registrosDesestimiento.getDescripcionErrorDebito().trim());
				} 
				
				if (registrosDesestimiento.getDescripcionErrorRegistro() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					} else {
						agregarGuion=true;
					}
					registrosArchivo.append(registrosDesestimiento.getDescripcionErrorRegistro().trim());
				} 
				
				if (registrosDesestimiento.getDescripcionErrorReintegro() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					}
					registrosArchivo.append(registrosDesestimiento.getDescripcionErrorReintegro().trim());
				} 
			}
			
			// Retorno 
			registrosArchivo.append(Constantes.RETORNO_WIN);
			agregarGuion=false;			
		}

		contenidoArchivo = registrosArchivo.toString();
		return contenidoArchivo;
	}
	
	/**
	 * 
	 * @param listaRegistros
	 * @return
	 */
	private String procesarRegistrosReintegro(List<ShvMasRegistro> listaRegistros) {

		StringBuffer registrosArchivo = new StringBuffer(); 
		String contenidoArchivo;
		boolean agregarGuion=false;
		
		for (ShvMasRegistro shvMasRegistro : listaRegistros) {

			ShvMasRegistroReintegro registrosReintegros = (ShvMasRegistroReintegro) shvMasRegistro;

			// Cliente
			if (registrosReintegros.getClientesSiebel().getClienteDuenoCredito() != null) {
				registrosArchivo.append(registrosReintegros.getClientesSiebel().getClienteDuenoCredito().toString());
			} else {
				registrosArchivo.append("");
			}

			// Cuenta
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getCuenta() != null) {
				registrosArchivo.append(registrosReintegros.getCuenta().toString());
			} else {
				registrosArchivo.append("");
			}
			
			// Tipo de Remanente
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getTipoRemanente() != null) {
				registrosArchivo.append(registrosReintegros.getTipoRemanente().getDescripcion());
			} else {
				registrosArchivo.append("");
			}
			
			// Numero de Referencia Nota de Credito
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getNumeroReferenciaNC() != null) {
				registrosArchivo.append(registrosReintegros.getNumeroReferenciaNC().toString());
			} else {
				registrosArchivo.append("");
			}
			
			// Numero de documento
			registrosArchivo.append(Constantes.SEMICOLON);
			String documentoFormateado = Utilidad.getReferenciaNumeroDocumento(
					registrosReintegros.getClaseComprobante(), 
					registrosReintegros.getSucursalComprobante(), 
					registrosReintegros.getNumeroComprobante());
			registrosArchivo.append(documentoFormateado);

			// Credito migrado S/N
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getCreditoMigrado() != null) {
				registrosArchivo.append(registrosReintegros.getCreditoMigrado().getDescripcionCorta());
			} else {
				registrosArchivo.append("");
			}
			
			// Importe 
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getImporte() != null) {
				registrosArchivo.append(Utilidad.formatDecimalesACentavosComoTexto(registrosReintegros.getImporte()));
			} else {
				registrosArchivo.append("");
			}
			
			// Numero de tramite de reintegro
			registrosArchivo.append(Constantes.SEMICOLON);
			if(registrosReintegros.getTramiteReintegro() != null) {
				registrosArchivo.append(registrosReintegros.getTramiteReintegro().toString());
			} else {
				registrosArchivo.append("");
			}
			
			// Fecha de tramite del reintegro
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getFechaAltaTramiteReintegro() != null) {
				registrosArchivo.append(Utilidad.formatDateAAAAMMDD(registrosReintegros.getFechaAltaTramiteReintegro()));
			} else {
				registrosArchivo.append("");
			}
			
			// Tipo de Reintegro
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getTipoReintegro() != null) {
				registrosArchivo.append(registrosReintegros.getTipoReintegro().name());
			} else {
				registrosArchivo.append("");
			}
			
			// Cliente dueño del acuerdo del reintegro
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getClientesSiebel().getClienteDuenoAcuerdo() != null) {
				registrosArchivo.append(registrosReintegros.getClientesSiebel().getClienteDuenoAcuerdo().toString());
			} else {
				registrosArchivo.append("");
			}
			
			// Acuerdo de Facturación
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getAcuerdoFacturacionDestino() != null) {
				registrosArchivo.append(registrosReintegros.getAcuerdoFacturacionDestino().toString());
			} else {
				registrosArchivo.append("");
			}
			
			// Numero de Linea del acuerdo
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getLineaDestino() != null) {
				registrosArchivo.append(registrosReintegros.getLineaDestino().toString());
			} else {
				registrosArchivo.append("");
			}
			
			// Reintegra con intereses
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getReintegraConInteres() != null) {
				registrosArchivo.append(registrosReintegros.getReintegraConInteres().getDescripcionCorta());
			} else {
				registrosArchivo.append("");
			}
			
			// Registros agregados al descargar
			
			// Descripción del estado del procesamiento del registro 
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosReintegros.getEstado() != null) {
				registrosArchivo.append(registrosReintegros.getEstado().getDescripcion());
			} 
			
			// Id de Operacion
			registrosArchivo.append(Constantes.SEMICOLON);
			if (
				!Validaciones.isObjectNull(registrosReintegros.getEstado()) &&
				(
					EstadoRegistroOperacionMasivaEnum.PROCESO_IMPUTACION.equals(registrosReintegros.getEstado()) ||
					EstadoRegistroOperacionMasivaEnum.PROCESADO.equals(registrosReintegros.getEstado())
				)
			) {
				if (registrosReintegros.getIdOperacion() != null) {
					registrosArchivo.append(registrosReintegros.getIdOperacion().toString());
				}
			}
			
			// Código de error proveniente del procesamiento en el cobrador
			registrosArchivo.append(Constantes.SEMICOLON);

			String codigoError = obtenerCodigoErrorGanancias(registrosReintegros);
			registrosArchivo.append(codigoError);
			
			// Descripcion del error proveniente del cobrador
			registrosArchivo.append(Constantes.SEMICOLON);		
			
			if (!TipoResultadoEnum.OK.getDescripcionMic().equals(codigoError)) {
				if(registrosReintegros.getDescripcionErrorCredito()!= null){
					registrosArchivo.append(registrosReintegros.getDescripcionErrorCredito().trim());
					agregarGuion=true;
				} 
	
				if (registrosReintegros.getDescripcionErrorDebito() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					} else {
						agregarGuion=true;
					}
					registrosArchivo.append(registrosReintegros.getDescripcionErrorDebito().trim());
				} 
				
				if (registrosReintegros.getDescripcionErrorRegistro() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					} else {
						agregarGuion=true;
					}
					registrosArchivo.append(registrosReintegros.getDescripcionErrorRegistro().trim());
				} 
				
				if (registrosReintegros.getDescripcionErrorReintegro() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					}
					registrosArchivo.append(registrosReintegros.getDescripcionErrorReintegro().trim());
				} 
			}
				
			// Importe Intereses generados
			registrosArchivo.append(Constantes.SEMICOLON);
			BigDecimal intereses = new BigDecimal(0);
			if (!Validaciones.isObjectNull(registrosReintegros.getEstado()) && EstadoRegistroOperacionMasivaEnum.PROCESADO.equals(registrosReintegros.getEstado())) {
				
				if (!Validaciones.isObjectNull(registrosReintegros.getCobranzaCargoInteresesTrasladadosRegulados())) {
					intereses = intereses.add(registrosReintegros.getCobranzaCargoInteresesTrasladadosRegulados());
				}
				if (!Validaciones.isObjectNull(registrosReintegros.getCobranzaCargoInteresesTrasladadosNoRegulados())) {
					intereses = intereses.add(registrosReintegros.getCobranzaCargoInteresesTrasladadosNoRegulados());
				}
				if (!Validaciones.isObjectNull(registrosReintegros.getCobranzaCargoInteresesBonificadosRegulados())) {
					intereses = intereses.add(registrosReintegros.getCobranzaCargoInteresesBonificadosRegulados());
				}
				if (!Validaciones.isObjectNull(registrosReintegros.getCobranzaCargoInteresesBonificadosNoRegulados())) {
					intereses = intereses.add(registrosReintegros.getCobranzaCargoInteresesBonificadosNoRegulados());
				}
				
				registrosArchivo.append(Utilidad.formatDecimalesACentavosComoTexto(intereses));
			}
			
			// Retorno 
			registrosArchivo.append(Constantes.RETORNO_WIN);
			agregarGuion = false;

		}

		contenidoArchivo = registrosArchivo.toString();
		return contenidoArchivo;
	}
	
	/**
	 * 
	 * @param listaRegistros
	 * @return
	 */
	private String procesarRegistrosGanancia(List<ShvMasRegistro> listaRegistros) {

		StringBuffer registrosArchivo = new StringBuffer(); 
		String contenidoArchivo;
		boolean agregarGuion = false;
		String codigoError;

		for (ShvMasRegistro shvMasRegistro : listaRegistros) {
			
			ShvMasRegistroGanancias registrosGanancias = (ShvMasRegistroGanancias) shvMasRegistro;
			
			// Cliente dueño del Credito
			if (registrosGanancias.getClientesSiebel().getClienteDuenoCredito() != null) {
				registrosArchivo.append(registrosGanancias.getClientesSiebel().getClienteDuenoCredito().toString());
			} else {
				registrosArchivo.append("");
			}
			registrosArchivo.append(Constantes.SEMICOLON);
			
			// Cuenta
			if (registrosGanancias.getCuentaOrigen() != null) {
				registrosArchivo.append(registrosGanancias.getCuentaOrigen().toString());
			} else {
				registrosArchivo.append("");
			}
			registrosArchivo.append(Constantes.SEMICOLON);
			
			// Tipo de Remanente
			if (registrosGanancias.getTipoRemanente() != null) {
				registrosArchivo.append(registrosGanancias.getTipoRemanente().getDescripcion());
			} else {
				registrosArchivo.append("");
			}
			registrosArchivo.append(Constantes.SEMICOLON);
			
			// Numero de referencia de MIC
			if (registrosGanancias.getNumeroReferenciaNC() != null) {
				registrosArchivo.append(registrosGanancias.getNumeroReferenciaNC().toString());
			} else {
				registrosArchivo.append("");
			}
			registrosArchivo.append(Constantes.SEMICOLON);
			
			// Importe
			if (registrosGanancias.getImporte() != null) {
				registrosArchivo.append(Utilidad.formatDecimalesACentavosComoTexto(registrosGanancias.getImporte()));
			} else {
				registrosArchivo.append("");
			}
			registrosArchivo.append(Constantes.SEMICOLON);
			
			// Credito migrado
			if (registrosGanancias.getCreditoMigrado() != null) {
				registrosArchivo.append(registrosGanancias.getCreditoMigrado().getDescripcionCorta());
			} else {
				registrosArchivo.append("");
			}
			
			//Registros agregados al descargar
			
			// Estado registro de operacion masiva
			registrosArchivo.append(Constantes.SEMICOLON);
			if (registrosGanancias.getEstado() != null) {
				registrosArchivo.append(registrosGanancias.getEstado().getDescripcion());
			}
			
			// Id de Operacion Masiva
			registrosArchivo.append(Constantes.SEMICOLON);
			if (!Validaciones.isObjectNull(registrosGanancias.getEstado()) &&
				(EstadoRegistroOperacionMasivaEnum.PROCESO_IMPUTACION.equals(registrosGanancias.getEstado()) ||
					EstadoRegistroOperacionMasivaEnum.PROCESADO.equals(registrosGanancias.getEstado()))) {
				if (registrosGanancias.getIdOperacion() != null) {
					registrosArchivo.append(registrosGanancias.getIdOperacion().toString());
				}
			} 
			
			// Codigo de error proveniente del proveedor
			registrosArchivo.append(Constantes.SEMICOLON);
			codigoError="";
			codigoError = obtenerCodigoErrorGanancias(registrosGanancias);
			registrosArchivo.append(codigoError);
			
			// Descripcion del error proveniente del proveedor
			registrosArchivo.append(Constantes.SEMICOLON);
			
			if (!TipoResultadoEnum.OK.getDescripcionMic().equals(codigoError)) {
				if (registrosGanancias.getDescripcionErrorCredito() !=  null) {
					registrosArchivo.append(registrosGanancias.getDescripcionErrorCredito().trim());
					agregarGuion = true;
				} 
	
				if (registrosGanancias.getDescripcionErrorDebito() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					} else {
						agregarGuion=true;
					}
					registrosArchivo.append(registrosGanancias.getDescripcionErrorDebito().trim());
				} 
				
				if (registrosGanancias.getDescripcionErrorRegistro() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					} else {
						agregarGuion=true;
					}
					registrosArchivo.append(registrosGanancias.getDescripcionErrorRegistro().trim());
				} 
				
				if (registrosGanancias.getDescripcionErrorReintegro() != null) {
					if (agregarGuion) {
						registrosArchivo.append(Constantes.SIGNO_MENOS);
					}
					registrosArchivo.append(registrosGanancias.getDescripcionErrorReintegro().trim());
				} 
			}
				
			registrosArchivo.append(Constantes.RETORNO_WIN);
			agregarGuion=false;
		}

		contenidoArchivo = registrosArchivo.toString();
		return contenidoArchivo;
	}
	
	public String obtenerCodigoErrorGanancias(ShvMasRegistro registros){
		String codigoError="";

		if (registros.getResultadoConsultaCredito() != null) {
			if (!Validaciones.isNullOrEmpty(registros.getResultadoConsultaCredito().toString())) {
				if ("OK".equals(registros.getResultadoConsultaCredito().toString())) {
					codigoError="OK";
				}
			}
		}

		if (registros.getResultadoConsultaDebito() != null) {
			if (!Validaciones.isNullOrEmpty(registros.getResultadoConsultaDebito().toString())) {
				if ("OK".equals(registros.getResultadoConsultaDebito().toString())) {
					codigoError="OK";
				}
			}
		}

		if (registros.getResultadoConsultaReintegro() != null) {
			if (!Validaciones.isNullOrEmpty(registros.getResultadoConsultaReintegro().toString())) {
				if ("OK".equals(registros.getResultadoConsultaReintegro().toString())) {
					codigoError="OK";
				}
			}
		}

		if (registros.getResultadoConsultaCredito() != null) {
			if (!Validaciones.isNullOrEmpty(registros.getResultadoConsultaCredito().toString())) {
				if ("WRN".equals(registros.getResultadoConsultaCredito().toString())) {
					codigoError="WRN";
				}
			}
		}

		if (registros.getResultadoConsultaDebito() != null) {
			if (!Validaciones.isNullOrEmpty(registros.getResultadoConsultaDebito().toString())) {
				if ("WRN".equals(registros.getResultadoConsultaDebito().toString())) {
					codigoError="WRN";
				}
			}
		}

		if (registros.getResultadoConsultaReintegro() != null) {
			if (!Validaciones.isNullOrEmpty(registros.getResultadoConsultaReintegro().toString())) {
				if ("WRN".equals(registros.getResultadoConsultaReintegro().toString())) {
					codigoError="WRN";
				}
			}
		}

		if (registros.getResultadoConsultaCredito() != null) {
			if (!Validaciones.isNullOrEmpty(registros.getResultadoConsultaCredito().toString())) {
				if ("NOK".equals(registros.getResultadoConsultaCredito().toString())) {
					codigoError="NOK";
				}

			}
		}

		if (registros.getResultadoConsultaDebito()!=null) {
			if (!Validaciones.isNullOrEmpty(registros.getResultadoConsultaDebito().toString())) {
				if ("NOK".equals(registros.getResultadoConsultaDebito().toString())) {
					codigoError="NOK";
				}

			}
		}

		if (registros.getResultadoConsultaReintegro() != null) {
			if (!Validaciones.isNullOrEmpty(registros.getResultadoConsultaReintegro().toString())) {
				if ("NOK".equals(registros.getResultadoConsultaReintegro().toString())) {
					codigoError="NOK";
				}

			}
		}
		return codigoError;
	}
}
