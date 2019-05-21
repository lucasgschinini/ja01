package ar.com.telecom.shiva.negocio.semaforo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.enumeradores.CamposReglasGestionabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.ErroresDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDebitoImportadoEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenDebitoEnum;
import ar.com.telecom.shiva.base.enumeradores.SemaforoGestionabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoGestionableDTO;
import ar.com.telecom.shiva.presentacion.bean.dto.GestionabilidadDto;

public class SemaforoGestionabilidadDebito extends SemaforoGestionabilidad {

	public SemaforoGestionabilidadDebito() {
		this.setListaReglas(
			Arrays.asList(
				new PostReglaEnum[] {PostReglaEnum.R38, PostReglaEnum.R36, PostReglaEnum.R35,
					PostReglaEnum.R2,PostReglaEnum.R2_1, PostReglaEnum.R5, PostReglaEnum.R6,
					PostReglaEnum.R7, PostReglaEnum.R9, PostReglaEnum.R10,
					PostReglaEnum.R8, PostReglaEnum.R11, PostReglaEnum.R32,
					PostReglaEnum.R12, PostReglaEnum.R13, PostReglaEnum.R17,
					PostReglaEnum.R19, PostReglaEnum.R20, PostReglaEnum.R18,
					PostReglaEnum.R21, PostReglaEnum.R22, PostReglaEnum.R23
		}));
		Collections.sort(this.getListaReglas(), this.getComparador());
	}

	@Override
	protected boolean validar(DocumentoGestionableDTO dto) {
		CobroDebitoDto debito = (CobroDebitoDto) dto;
		List<Object> incluidos = Arrays.asList(
			new Object[] {
				SistemaEnum.CALIPSO,
				SistemaEnum.MIC
		});
		if (!incluidos.contains(debito.getSistemaOrigen())) {
			Traza.debug(getClass(), "DebitoDto con Origen != [CALIPSO, MIC]");
			return false;
		}
		incluidos = Arrays.asList(
			new Object[] {
				TipoComprobanteEnum.FAC,
				TipoComprobanteEnum.DEB,
				TipoComprobanteEnum.DUC
		});
		if (!incluidos.contains(debito.getTipoComprobanteEnum())) {
			Traza.debug(getClass(), "DebitoDto con TipoDoc != [FAC, DEB, DUC]");
			return false;
		}
		return true;
	}

	@Override
	protected GestionabilidadDto retornarSemaforoPorDefecto(DocumentoGestionableDTO dto) {
		CobroDebitoDto debito = (CobroDebitoDto) dto;

		GestionabilidadDto gestionabilidadDto = null;
		if (
			OrigenDebitoEnum.IMPORTACION.equals(debito.getOrigen()) &&
			EstadoDebitoImportadoEnum.PENDIENTE.equals(debito.getResultadoValidacionEstado())
		) {
			gestionabilidadDto = new GestionabilidadDto(
				"Si",
				SemaforoGestionabilidadEnum.VERDE.getDescripcion(),
				"Debito importado por excel."
			);
		}
		
		//
		// El siguiente codigo se agrega solo a efectos de inhibir temporalmente el uso de documentos en dolares
		// hasta que se apruebe se uso cuando se finalicen las pruebas
		//

//		if (MonedaEnum.DOL.getSigno2().equals(debito.getMoneda())) {
//			if (SistemaEnum.CALIPSO.equals(debito.getSistemaOrigen())) {
//				gestionabilidadDto =
//					new GestionabilidadDto(
//						"No",
//						SemaforoGestionabilidadEnum.ROJO.getDescripcion(),
//						"Si moneda es dolar retorna un semaforo Rojo",
//						ErroresDebitoEnum.E_8513.codigo(),
//						ErroresDebitoEnum.E_8513.descripcion()
//				);
//
//			} else {
//				gestionabilidadDto =
//					new GestionabilidadDto(
//						"No",
//						SemaforoGestionabilidadEnum.ROJO.getDescripcion(),
//						"Si moneda es dolar retorna un semaforo Rojo"
//				);
//			}
//		}		
		
		return gestionabilidadDto;
	}

	@Override
	protected Object llamarGetter(CamposReglasGestionabilidadEnum key,DocumentoGestionableDTO dto) throws NegocioExcepcion {
		
		CobroDebitoDto debito = (CobroDebitoDto) dto;
		String nombreGetter = this.armarGetter(key,debito.getSistemaOrigen().getDescripcion(),debito.getTipoComprobanteEnum().descripcion());
		
		Object valueObject = null;
		try {
			Method method;
			method = debito.getClass().getMethod(nombreGetter);
			valueObject = method.invoke(debito,(Object[]) null);
			
			// Compar el valor del DebitoDto con los Valores de las
			// partes de la regla
		} catch (
			NoSuchMethodException | SecurityException
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

		return strNombreGetter.toString();
	}

	@Override
	protected void setearErroresGestionabilidad(PostReglaEnum posicion,DocumentoGestionableDTO dto,GestionabilidadDto semaforo) 
	{
		CobroDebitoDto debito = (CobroDebitoDto) dto;
		Map<CamposReglasGestionabilidadEnum, List<Object>> regla = this.obtenerReglaGestionabilidad(posicion);
		List<Object> errores = regla.get(CamposReglasGestionabilidadEnum.ERRORES_DEBITO);
		if (errores != null && !errores.isEmpty()) {
			ErroresDebitoEnum errorDebito = null;
			switch (posicion) {
			case R6:
				for (Object error : errores) {
					errorDebito = (ErroresDebitoEnum) error;
					if (errorDebito.objetoDiscriminador().equals(debito.getEstadoOrigenEnum())) {
						semaforo.setCodigoError(errorDebito.codigo());
						semaforo.setDescripcionError(errorDebito.descripcion());
						return;
					}
				}
				break;
			case R22:
				for (Object error : errores) {
					errorDebito = (ErroresDebitoEnum) error;
					if (errorDebito.objetoDiscriminador().equals(debito.getEstadoOrigenEnum())) {
						semaforo.setCodigoError(errorDebito.codigo());
						semaforo.setDescripcionError(errorDebito.descripcion());
						return;
					}
				}
			break;
			case R32:
				for (Object error : errores) {
					errorDebito = (ErroresDebitoEnum) error;
					if (errorDebito.objetoDiscriminador().equals(debito.getEstadoOrigenEnum())) {
						semaforo.setCodigoError(errorDebito.codigo());
						semaforo.setDescripcionError(errorDebito.descripcion());
						return;
					}
				}
				break;
			case R17:
				for (Object error : errores) {
					errorDebito = (ErroresDebitoEnum) error;
					if (errorDebito.objetoDiscriminador().equals(debito.getEstadoOrigenEnum())) {
						semaforo.setCodigoError(errorDebito.codigo());
						semaforo.setDescripcionError(errorDebito.descripcion());
						return;
					}
				}
			break;
			default:
				errorDebito = (ErroresDebitoEnum) errores.get(0);
				semaforo.setCodigoError(errorDebito.codigo());
				semaforo.setDescripcionError(errorDebito.descripcion());
			}
		}
	}
}