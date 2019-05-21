package ar.com.telecom.shiva.negocio.semaforo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.enumeradores.CamposReglasGestionabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.ErroresCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SemaforoGestionabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoGestionableDTO;
import ar.com.telecom.shiva.presentacion.bean.dto.GestionabilidadDto;

public class SemaforoGestionabilidadCredito extends SemaforoGestionabilidad {

	public SemaforoGestionabilidadCredito() {
		this.setListaReglas(
			Arrays.asList(
				new PostReglaEnum[] { PostReglaEnum.R39, PostReglaEnum.R37, PostReglaEnum.R35,
					PostReglaEnum.R2_NOT_SHIVA, PostReglaEnum.R2_SHIVA,
					PostReglaEnum.R2_1, PostReglaEnum.R5, PostReglaEnum.R6,
					PostReglaEnum.R7,  PostReglaEnum.R8,  PostReglaEnum.R9,
					PostReglaEnum.R10, PostReglaEnum.R11, PostReglaEnum.R14,
					PostReglaEnum.R15, PostReglaEnum.R17, PostReglaEnum.R19,
					PostReglaEnum.R20, PostReglaEnum.R18, PostReglaEnum.R21,
					PostReglaEnum.R22, PostReglaEnum.R23, PostReglaEnum.R24,
					PostReglaEnum.R26, PostReglaEnum.R27, PostReglaEnum.R28,
					PostReglaEnum.R30
		}));
		Collections.sort(this.getListaReglas(), this.getComparador());
	}
	
	@Override
	protected boolean validar(DocumentoGestionableDTO dto) {
		CobroCreditoDto credito = (CobroCreditoDto) dto;
		List<Object> incluidos = Arrays.asList(
			new Object[] {
				SistemaEnum.CALIPSO,
				SistemaEnum.MIC,
				SistemaEnum.SHIVA
		});
		if (!incluidos.contains(credito.getSistemaOrigen())) {
			Traza.debug(getClass(), "DebitoDto con Origen != [CALIPSO, MIC, SHIVA]");
			return false;
		}
		incluidos = Arrays.asList(
			new Object[] {
				TipoComprobanteEnum.CRE,
				TipoComprobanteEnum.REM,
				TipoComprobanteEnum.CTA
		});
		if (SistemaEnum.SHIVA.equals(credito.getSistemaOrigen())) {
			return true;
		} 
		if (!incluidos.contains(credito.getTipoComprobanteEnum())) {
			Traza.debug(
				getClass(), 
				"CreditoDto con TipoDoc == [CRE, REM, CTA, AVISO_DE_PAGO, BOLETA_OFICINA, BOLETA_CLIENTE, BOLETA_CAJERO_PAGADOR]"
			);
			return false;
		}
		return true;
	}
	
	@Override
	protected GestionabilidadDto retornarSemaforoPorDefecto(DocumentoGestionableDTO dto) {
		CobroCreditoDto credito = (CobroCreditoDto) dto;
		GestionabilidadDto gestionabilidadDto = null;
		if (!MonedaEnum.PES.equals(credito.getMonedaEnum())) {
			if (SistemaEnum.CALIPSO.equals(credito.getSistemaOrigen())) {
				
				if (MonedaEnum.PES.getSigno2().equals(credito.getMonedaImporteUtilizar())
						&& TipoComprobanteEnum.CRE.equals(credito.getTipoComprobanteEnum())){
					gestionabilidadDto =
							new GestionabilidadDto(
								"No",
								SemaforoGestionabilidadEnum.ROJO.getDescripcion(),
								"Si moneda operacion es pesos y el credito es dolar retorna un semaforo Rojo",
								ErroresCreditoEnum.E_9000.codigo(),
								ErroresCreditoEnum.E_9000.descripcion()
						);
				}

			} else {
				gestionabilidadDto =
					new GestionabilidadDto(
						"No",
						SemaforoGestionabilidadEnum.ROJO.getDescripcion(),
						"Si moneda es dolar retorna un semaforo Rojo"
				);
			}
		}
		
		//DOLARES #24 .Se comenta para que se puedan habilitar CTAS EN DOLARES
//		if (TipoComprobanteEnum.CTA.equals(credito.getTipoComprobanteEnum())) {
//			gestionabilidadDto =
//				new GestionabilidadDto(
//					"No",
//					SemaforoGestionabilidadEnum.ROJO.getDescripcion(),
//					"Si moneda es dolar retorna un semaforo Rojo",
//					ErroresCreditoEnum.E_8512.codigo(),
//					ErroresCreditoEnum.E_8512.descripcion()
//			);
//		}
		
		return gestionabilidadDto;
	}

	@Override
	protected Object llamarGetter(CamposReglasGestionabilidadEnum key,DocumentoGestionableDTO dto) 
			throws NegocioExcepcion 
	{
		CobroCreditoDto credito = (CobroCreditoDto) dto;
		
		String nombreGetter = this.armarGetter(
			key,
			credito.getSistemaOrigen().getDescripcion(),
			(SistemaEnum.SHIVA.equals(credito.getSistemaOrigen()) ? (credito.getTipoComprobanteEnumShiva()!=null?credito.getTipoComprobanteEnumShiva().descripcion():"") : credito.getTipoComprobanteEnum().descripcion())
		);
		
		Object valueObject = null;
		try {
			Method method;
			method = credito.getClass().getMethod(nombreGetter);
			valueObject = method.invoke(credito, (Object[]) null);
			
			// Compar el valor del DebitoDto con los Valores de las
			// partes de la regla
			
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) 
		{
			throw new NegocioExcepcion(e.getMessage());
		}
		return valueObject;
	}

	@Override
	protected String armarGetter(CamposReglasGestionabilidadEnum key,String origen,String tipo) {
		
		StringBuffer strNombreGetter = new StringBuffer(SemaforoGestionabilidad.GETTER);
		strNombreGetter.append(Utilidad.primeraLetraMayuscula(key.atributo()));
		
		if (SistemaEnum.MIC.getDescripcion().equalsIgnoreCase(origen) &&
			TipoComprobanteEnum.REM.descripcion().equalsIgnoreCase(tipo)) 
		{
			if (CamposReglasGestionabilidadEnum.SUBTIPO.equals(key)) {
				return strNombreGetter.append("MicRem").toString();
			}
		} else if (SistemaEnum.SHIVA.getDescripcion().equalsIgnoreCase(origen)) {
			if (CamposReglasGestionabilidadEnum.TIPO.equals(key) ||
				CamposReglasGestionabilidadEnum.SUBTIPO.equals(key)) 
			{
				return strNombreGetter.append("Shiva").toString();
			}
		}
		return strNombreGetter.toString();
	}
	
	@Override
	protected void setearErroresGestionabilidad(PostReglaEnum posicion,DocumentoGestionableDTO dto,
												GestionabilidadDto semaforo) 
	{
		CobroCreditoDto credito = (CobroCreditoDto) dto;
		Map<CamposReglasGestionabilidadEnum, List<Object>> regla = this.obtenerReglaGestionabilidad(posicion);
		List<Object> errores = regla.get(CamposReglasGestionabilidadEnum.ERRORES_CREDITO);
		if (errores != null && !errores.isEmpty()) {
			ErroresCreditoEnum errorCredito = null;
			switch (posicion) {
			case R6:
				//Mic
				for (Object error : errores) {
					errorCredito = (ErroresCreditoEnum) error;
					if (errorCredito.objetoDiscriminador().equals(credito.getEstadoOrigenEnum())) {
						semaforo.setCodigoError(errorCredito.codigo());
						semaforo.setDescripcionError(errorCredito.descripcion());
						return;
					}
				}
				break;
			case R14:
				for (Object error : errores) {
					errorCredito = (ErroresCreditoEnum) error;
					if (errorCredito.objetoDiscriminador().equals(credito.getTipoFacturaMicRem())) {
						semaforo.setCodigoError(errorCredito.codigo());
						semaforo.setDescripcionError(errorCredito.descripcion());
						return;
					}
				}
			break;
			case R22:
				//Calipso
				for (Object error : errores) {
					errorCredito = (ErroresCreditoEnum) error;
					if (errorCredito.objetoDiscriminador().equals(credito.getEstadoOrigenEnum())) {
						semaforo.setCodigoError(errorCredito.codigo());
						semaforo.setDescripcionError(errorCredito.descripcion());
						return;
					}
				}
			break;
			case R26:
				for (Object error : errores) {
					errorCredito = (ErroresCreditoEnum) error;
					if (errorCredito.objetoDiscriminador().equals(credito.getMotivo())) {
						semaforo.setCodigoError(errorCredito.codigo());
						semaforo.setDescripcionError(errorCredito.descripcion());
						return;
					}
				}
			break;
			case R27:
				//SHIVA
				for (Object error : errores) {
					errorCredito = (ErroresCreditoEnum) error;
					if (errorCredito.objetoDiscriminador().equals(credito.getEstadoOrigenEnum())) {
						semaforo.setCodigoError(errorCredito.codigo());
						semaforo.setDescripcionError(errorCredito.descripcion());
						return;
					}
				}
			break;
			default:
				errorCredito = (ErroresCreditoEnum) errores.get(0);
				semaforo.setCodigoError(errorCredito.codigo());
				semaforo.setDescripcionError(errorCredito.descripcion());
			}
		}
	}
}