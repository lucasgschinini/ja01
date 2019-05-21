package ar.com.telecom.shiva.base.jms.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.IControlJms;
import ar.com.telecom.shiva.base.jms.datos.entrada.FacConsultaAcuerdoEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.FacConsultaClienteEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida.AcuerdoFacturacion;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaClienteSalida;
import ar.com.telecom.shiva.base.jms.servicios.IFacJmsSyncServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.AcuerdoLegado;

public class FacJmsSyncServicioImpl implements IFacJmsSyncServicio {

	@Autowired
	IControlJms facConsultasControlMQ;
	
	@Autowired 
	MapeadorJMS facConsultaAcuerdoMapeoJMS;

	@Autowired 
	MapeadorJMS facConsultaAcuerdoxClienteMapeoJMS;
	
	@Autowired 
	MapeadorJMS facConsultaClienteMapeoJMS;
	
	@Override
	public FacConsultaAcuerdoSalida consultarAcuerdo(Long numeroAcuerdo) throws JmsExcepcion {
		
		try {
			FacConsultaAcuerdoEntrada jms = new FacConsultaAcuerdoEntrada();
			jms.setTipoTelefonia("");
			jms.setTipoServicio("A");
			jms.setNumeroLinea("");
			jms.setNumeroCliente("0");
			jms.setNumeroAcuerdo(numeroAcuerdo.toString());
			
			jms.setCustIntrnlNbr("");
			jms.setApellCli("");
			jms.setNomCli("");
			jms.setNomCalle("");
			jms.setNomCiudad("");
			jms.setCuit("0");
			
			jms.setFechaEmision(Utilidad.obtenerFechaJuliana());
			jms.setCmnRtnInptCd("I");
			jms.setRtrvlCode("3");
			jms.setUserOwnerInd("");
			jms.setFstTimeLkupInd("Y");
			
			jms.setNatServNbr1("");
			jms.setInvArgtId1("0");
			jms.setProdBilgId("");
			jms.setBilgElmtCd("");
			jms.setCpiIdDsp("0");
			jms.setAddlRecInd("");
			
			// Serializar el objeto a mensaje
			String mensajeAEnviar = facConsultaAcuerdoMapeoJMS.serializar(jms, true);
				
			//Envio datos
			String mensajeRecibido = 
					facConsultasControlMQ.consultar(mensajeAEnviar,  
							Propiedades.SHIVA_PROPIEDADES.getString(
									"mq.fac.consultas.contrato"));
			
			if (mensajeRecibido != null) {
				FacConsultaAcuerdoSalida salida = 
						(FacConsultaAcuerdoSalida) facConsultaAcuerdoMapeoJMS.deserializar(mensajeRecibido, true, true);
				
				if ("Y".equalsIgnoreCase(salida.getRetorno().getIndicadorError())) {
					
					if ("9980".equalsIgnoreCase(salida.getRetorno().getCodigoError()) 
							&& "0".equalsIgnoreCase(salida.getRetorno().getCantRegistrosRetornados())) {
						
						//Sin Datos o acuerdo no encontrado
						return null;
					} else {
						Traza.error(getClass(), "Se ha retornado el mensaje con error: " + salida.getRetorno().toString());
						throw new JmsExcepcion("Se ha retornado el mensaje con error: " + salida.getRetorno().getCodigoError());
					}
				}
				
				return salida;
				
			} else {
				throw new JmsExcepcion("Tiempo agotado: No se ha podido recibir la respuesta");
			}
			
		} catch (Exception e) {
			throw new JmsExcepcion("Se ha producido un error del servicio: " + e.getMessage());
		}
	}

	@Override
	public FacConsultaAcuerdoSalida consultarAcuerdoxLinea(String numeroLinea)
			throws JmsExcepcion {
		
		try {
			FacConsultaAcuerdoEntrada jms = new FacConsultaAcuerdoEntrada();
			jms.setTipoTelefonia("");
			jms.setTipoServicio("A");
			jms.setNumeroLinea(numeroLinea);
			jms.setNumeroCliente("0");
			jms.setNumeroAcuerdo("0");
			
			jms.setCustIntrnlNbr("0");
			jms.setApellCli("");
			jms.setNomCli("");
			jms.setNomCalle("");
			jms.setNomCiudad("");
			jms.setCuit("0");
			
			jms.setFechaEmision(Utilidad.obtenerFechaJuliana());
			jms.setCmnRtnInptCd("A");
			jms.setRtrvlCode("3");
			jms.setUserOwnerInd("O");
			jms.setFstTimeLkupInd("Y");
			
			jms.setNatServNbr1("");
			jms.setInvArgtId1("0");
			jms.setProdBilgId("");
			jms.setBilgElmtCd("");
			jms.setCpiIdDsp("0");
			jms.setAddlRecInd("");
			
			// Serializar el objeto a mensaje
			String mensajeAEnviar = facConsultaAcuerdoMapeoJMS.serializar(jms, true);
				
			//Envio datos
			String mensajeRecibido = 
					facConsultasControlMQ.consultar(mensajeAEnviar,  
							Propiedades.SHIVA_PROPIEDADES.getString(
									"mq.fac.consultas.contrato"));
			
			if (mensajeRecibido != null) {
				FacConsultaAcuerdoSalida salida = 
						(FacConsultaAcuerdoSalida) facConsultaAcuerdoMapeoJMS.deserializar(mensajeRecibido, true, true);
				
				if ("Y".equalsIgnoreCase(salida.getRetorno().getIndicadorError())) {
					if ("9980".equalsIgnoreCase(salida.getRetorno().getCodigoError()) 
							&& "0".equalsIgnoreCase(salida.getRetorno().getCantRegistrosRetornados())) {
						
						//Sin Datos o acuerdo no encontrado
						return null;
					} else {
						Traza.error(getClass(), "Se ha retornado el mensaje con error: " + salida.getRetorno().toString());
						throw new JmsExcepcion("Se ha retornado el mensaje con error: " + salida.getRetorno().getCodigoError());
					}
				}
				
				return salida;
				
			} else {
				throw new JmsExcepcion("Tiempo agotado: No se ha podido recibir la respuesta");
			}
			
		} catch (Exception e) {
			throw new JmsExcepcion("Se ha producido un error del servicio: " + e.getMessage());
		}
	}

	@Override
	public FacConsultaAcuerdoSalida consultarAcuerdoxCliente(Long numeroCliente)
			throws JmsExcepcion {
		try {
			FacConsultaAcuerdoEntrada jms = new FacConsultaAcuerdoEntrada();
			jms.setTipoTelefonia("");
			jms.setTipoServicio("A");
			jms.setNumeroLinea("");
			jms.setNumeroCliente(numeroCliente.toString());
			jms.setNumeroAcuerdo("0");
			
			jms.setCustIntrnlNbr("");
			jms.setApellCli("");
			jms.setNomCli("");
			jms.setNomCalle("");
			jms.setNomCiudad("");
			jms.setCuit("0");
			
			jms.setFechaEmision(Utilidad.obtenerFechaJuliana());
			jms.setCmnRtnInptCd("B");
			jms.setRtrvlCode("3");
			jms.setUserOwnerInd("");
			jms.setFstTimeLkupInd("Y");
			
			jms.setNatServNbr1("");
			jms.setInvArgtId1("0");
			jms.setProdBilgId("");
			jms.setBilgElmtCd("");
			jms.setCpiIdDsp("0");
			jms.setAddlRecInd("");
			
			// Serializar el objeto a mensaje
			String mensajeAEnviar = facConsultaAcuerdoxClienteMapeoJMS.serializar(jms, true);
				
			//Envio datos
			String mensajeRecibido = 
					facConsultasControlMQ.consultar(mensajeAEnviar,  
							Propiedades.SHIVA_PROPIEDADES.getString(
									"mq.fac.consultas.contrato"));
			
			if (mensajeRecibido != null) {
				FacConsultaAcuerdoSalida salida = 
						(FacConsultaAcuerdoSalida) facConsultaAcuerdoxClienteMapeoJMS.deserializar(mensajeRecibido, true, true);
				
				if ("Y".equalsIgnoreCase(salida.getRetorno().getIndicadorError())) {
					
					if ("9980".equalsIgnoreCase(salida.getRetorno().getCodigoError()) 
							&& "0".equalsIgnoreCase(salida.getRetorno().getCantRegistrosRetornados())) {
						
						//Sin Datos o acuerdo no encontrado
						return null;
					} else {
						Traza.error(getClass(), "Se ha retornado el mensaje con error: " + salida.getRetorno().toString());
						throw new JmsExcepcion("Se ha retornado el mensaje con error: " + salida.getRetorno().getCodigoError());
					}
				}
				
				return salida;
				
			} else {
				throw new JmsExcepcion("Tiempo agotado: No se ha podido recibir la respuesta");
			}
			
		} catch (Exception e) {
			throw new JmsExcepcion("Se ha producido un error del servicio: " + e.getMessage());
		}
	}

	@Override
	public FacConsultaClienteSalida consultarClientexAcuerdo(
			String numeroAcuerdo) throws JmsExcepcion {
		try {
			FacConsultaClienteEntrada jms = new FacConsultaClienteEntrada();
			jms.setTipoTelefonia("");
			jms.setTipoServicio("A");
			jms.setNumeroLinea("");
			jms.setNumeroCliente("0");
			jms.setNumeroAcuerdo(numeroAcuerdo.toString());
			
			jms.setCustIntrnlNbr("");
			jms.setApellCli("");
			jms.setNomCli("");
			jms.setNomCalle("");
			jms.setNomCiudad("");
			jms.setCuit("0");
			
			jms.setFechaEmision(Utilidad.obtenerFechaJuliana());
			jms.setCmnRtnInptCd("I");
			jms.setRtrvlCode("1");
			jms.setUserOwnerInd("");
			jms.setFstTimeLkupInd("Y");
			
			jms.setNatServNbr1("");
			jms.setInvArgtId1("0");
			jms.setProdBilgId("");
			jms.setBilgElmtCd("");
			jms.setCpiIdDsp("0");
			jms.setAddlRecInd("");
			
			// Serializar el objeto a mensaje
			String mensajeAEnviar = facConsultaClienteMapeoJMS.serializar(jms, true);
				
			//Envio datos
			String mensajeRecibido = 
					facConsultasControlMQ.consultar(mensajeAEnviar,  
							Propiedades.SHIVA_PROPIEDADES.getString(
									"mq.fac.consultas.contrato"));
			
			if (mensajeRecibido != null) {
				FacConsultaClienteSalida salida = 
						(FacConsultaClienteSalida) facConsultaClienteMapeoJMS.deserializar(mensajeRecibido, true, true);
				
				if ("Y".equalsIgnoreCase(salida.getRetorno().getIndicadorError())) {
					
					if ("9980".equalsIgnoreCase(salida.getRetorno().getCodigoError()) 
							&& "0".equalsIgnoreCase(salida.getRetorno().getCantRegistrosRetornados())) {
						
						//Sin Datos o acuerdo no encontrado
						return null;
					
					} else {
						Traza.error(getClass(), "Se ha retornado el mensaje con error: " + salida.getRetorno().toString());
						throw new JmsExcepcion("Se ha retornado el mensaje con error: " + salida.getRetorno().getCodigoError());
					}
				}
				
				return salida;
				
			} else {
				throw new JmsExcepcion("Tiempo agotado: No se ha podido recibir la respuesta");
			}
			
		} catch (Exception e) {
			throw new JmsExcepcion("Se ha producido un error del servicio: " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param idClienteLegado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public AcuerdoLegado buscarPrimerAcuerdoActivo(Long idClienteLegado) throws JmsExcepcion {
		
		AcuerdoLegado acuerdoLegado = null;
		
		try {
			// Busco los acuerdos de un cliente
			FacConsultaAcuerdoSalida consultarAcuerdoxCliente = consultarAcuerdoxCliente(idClienteLegado);
			
			if (!Validaciones.isObjectNull(consultarAcuerdoxCliente)) {
				
				List<AcuerdoFacturacion> listaAcuerdos = consultarAcuerdoxCliente.getListaAcuerdoFacturacion();
	
				if (Validaciones.isCollectionNotEmpty(listaAcuerdos)) {
	
					// Recorro el/los acuerdos para validar su estado y quedarme con el primero que cumpla
					for (AcuerdoFacturacion acuerdo : listaAcuerdos) {
						if (!Validaciones.isObjectNull(acuerdo)) {
							
							// Mejora Performance
							// Si estos dos cumplen con este criterio, puedo ir a consultar por el acuerdo (no borrar los espacios)
							if (!Validaciones.isNullOrEmpty(acuerdo.getFechaFinalizacionAcuerdoFactura())
									&& !Validaciones.isNullOrEmpty(acuerdo.getFechaUltimaFactura())
									&& Constantes.JULIANO_ULTIMO_DIA.equalsIgnoreCase(acuerdo.getFechaFinalizacionAcuerdoFactura().trim())
									&& !Constantes.JULIANO_PRIMER_DIA.equalsIgnoreCase(acuerdo.getFechaUltimaFactura().trim())) {
							
								FacConsultaAcuerdoSalida salida = consultarAcuerdo(acuerdo.getNumeroAcuerdo());
							    EstadoAcuerdoFacturacionEnum estadoAcuerdo = null;
							       
							    if (!Validaciones.isObjectNull(salida)) {
							    	estadoAcuerdo = salida.getPrimerAcuerdoFacturacion().getEstadoAcuerdo();
							    	
							    	if (!Validaciones.isObjectNull(estadoAcuerdo)) {
							    		if (EstadoAcuerdoFacturacionEnum.ACTIVO.codigo().equals(estadoAcuerdo.codigo())
							    				|| EstadoAcuerdoFacturacionEnum.INCOMUNICADO.codigo().equals(estadoAcuerdo.codigo())) {
							                     
							    			acuerdoLegado = new AcuerdoLegado();
							    			  
							    			acuerdoLegado.setNumero(acuerdo.getNumeroAcuerdo());
							    			acuerdoLegado.setEstado(EstadoAcuerdoFacturacionEnum.getEnumByCodigo(estadoAcuerdo.codigo()));
							    			acuerdoLegado.setSistema(SistemaEnum.MIC);
							    			acuerdoLegado.setIdClienteLegado(idClienteLegado);
							    			
							    			break;
							    		}
							    	}
							    }
							}
						}
					}
				}
			}
		}
		catch (JmsException e) {
			throw e;
		}

		return acuerdoLegado;		
	}
	
	
	/************************************************************************
	 * Getters/Setters
	 ***********************************************************************/
	public IControlJms getFacConsultasControlMQ() {
		return facConsultasControlMQ;
	}

	public void setFacConsultasControlMQ(
			IControlJms facConsultasControlMQ) {
		this.facConsultasControlMQ = facConsultasControlMQ;
	}

	public MapeadorJMS getFacConsultaAcuerdoMapeoJMS() {
		return facConsultaAcuerdoMapeoJMS;
	}

	public void setFacConsultaAcuerdoMapeoJMS(MapeadorJMS facConsultaAcuerdoMapeoJMS) {
		this.facConsultaAcuerdoMapeoJMS = facConsultaAcuerdoMapeoJMS;
	}

	public MapeadorJMS getFacConsultaClienteMapeoJMS() {
		return facConsultaClienteMapeoJMS;
	}

	public void setFacConsultaClienteMapeoJMS(MapeadorJMS facConsultaClienteMapeoJMS) {
		this.facConsultaClienteMapeoJMS = facConsultaClienteMapeoJMS;
	}

	public MapeadorJMS getFacConsultaAcuerdoxClienteMapeoJMS() {
		return facConsultaAcuerdoxClienteMapeoJMS;
	}

	public void setFacConsultaAcuerdoxClienteMapeoJMS(
			MapeadorJMS facConsultaAcuerdoxClienteMapeoJMS) {
		this.facConsultaAcuerdoxClienteMapeoJMS = facConsultaAcuerdoxClienteMapeoJMS;
	}	
}
