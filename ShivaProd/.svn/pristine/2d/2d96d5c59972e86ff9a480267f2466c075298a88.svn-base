package ar.com.telecom.shiva.negocio.semaforo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.enumeradores.CamposReglasGestionabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.ErroresDocumentosCapEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoBloqueoSapEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoGestionableDTO;
import ar.com.telecom.shiva.presentacion.bean.dto.GestionabilidadDto;

public class SemaforoGestionabilidadDocumentoCap extends SemaforoGestionabilidad {

	public SemaforoGestionabilidadDocumentoCap() {
		this.setListaReglas(
				Arrays.asList(
					new PostReglaEnum[] {PostReglaEnum.R2, PostReglaEnum.R40, PostReglaEnum.R40_1, PostReglaEnum.R41, PostReglaEnum.R42, PostReglaEnum.R43,
						PostReglaEnum.R44, PostReglaEnum.R45, PostReglaEnum.R46, PostReglaEnum.R47
			}));
			Collections.sort(this.getListaReglas(), this.getComparador());
	}

	
	@Override
	protected boolean validar(DocumentoGestionableDTO dto) {
		DocumentoCapDto doc = (DocumentoCapDto) dto;
		List<Object> incluidos = Arrays.asList(
			new Object[] {
				SistemaEnum.SAP
		});
		if (!incluidos.contains(doc.getSistemaOrigen())) {
			Traza.debug(getClass(), "DocumentoCapDto con Origen != [SAP]");
			return false;
		}
		
		return true;
	}
	@Override
	protected GestionabilidadDto retornarSemaforoPorDefecto(
			DocumentoGestionableDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String armarGetter(CamposReglasGestionabilidadEnum key, String origen, String tipo) {
		
		StringBuffer strNombreGetter = new StringBuffer(SemaforoGestionabilidad.GETTER);
		strNombreGetter.append(Utilidad.primeraLetraMayuscula(key.atributo()));

		return strNombreGetter.toString();
	}

	@Override
	protected Object llamarGetter(CamposReglasGestionabilidadEnum key,
			DocumentoGestionableDTO dto) throws NegocioExcepcion {
		
		DocumentoCapDto cap = (DocumentoCapDto) dto;
		String nombreGetter = "";
		
		nombreGetter= this.armarGetter(key,cap.getSistemaOrigen().getDescripcion(),"");
		
		Object valueObject = null;
		try {
			Method method;
			method = cap.getClass().getMethod(nombreGetter);
			valueObject = method.invoke(cap,(Object[]) null);
			
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
	protected void setearErroresGestionabilidad(PostReglaEnum posicion,
			DocumentoGestionableDTO dto, GestionabilidadDto semaforo) {

		DocumentoCapDto cap = (DocumentoCapDto) dto;
		Map<CamposReglasGestionabilidadEnum, List<Object>> regla = this.obtenerReglaGestionabilidad(posicion);
		List<Object> errores = regla.get(CamposReglasGestionabilidadEnum.ERRORES_CAP);
		if (errores != null && !errores.isEmpty()) {
			ErroresDocumentosCapEnum errorCap = null;
			switch (posicion) {
			case R40: // CASO DIFERENTE // 37 del exc el
				for (Object error : errores) {
					errorCap = (ErroresDocumentosCapEnum) error;
					EstadoBloqueoSapEnum estado = (EstadoBloqueoSapEnum) errorCap.objetoDiscriminador();
					if (Validaciones.isObjectNull(estado)) {
						List<String> bloqueos = new ArrayList<String>();
						bloqueos.add("B");
						bloqueos.add("C");
						bloqueos.add("0");
						bloqueos.add("1");
						bloqueos.add("2");
						bloqueos.add("3");
						bloqueos.add("4");
						bloqueos.add("5");
						bloqueos.add("6");
						
						if (!bloqueos.contains(cap.getProveedorInhabilitadoCodigo())) {
							semaforo.setCodigoError(errorCap.codigo());
							semaforo.setDescripcionError(errorCap.descripcion());
							return;
						}
					} else {
						if (estado.getCodigo().equals(cap.getProveedorInhabilitadoCodigo())) {
							semaforo.setCodigoError(errorCap.codigo());
							semaforo.setDescripcionError(errorCap.descripcion());
							return;
						}
					}
					
				}
			break;
			case R41: //"R41 - 38 del execl
				for (Object error : errores) {
					errorCap = (ErroresDocumentosCapEnum) error;
					if (errorCap.objetoDiscriminador().equals(cap.getTipoDocumento())) {
						semaforo.setCodigoError(errorCap.codigo());
						semaforo.setDescripcionError(errorCap.descripcion());
						return;
					}
				}
			break;
			case R43:
				for (Object error : errores) {
					errorCap = (ErroresDocumentosCapEnum) error;
					if (errorCap.objetoDiscriminador().equals(cap.getEstadoBloqueoSapEnum())) {
						semaforo.setCodigoError(errorCap.codigo());
						semaforo.setDescripcionError(errorCap.descripcion());
						return;
					}
				}
				break;
			case R45:
				for (Object error : errores) {
					errorCap = (ErroresDocumentosCapEnum) error;
					if (errorCap.objetoDiscriminador().equals(cap.getEstadoBloqueoSapEnum())) {
						semaforo.setCodigoError(errorCap.codigo());
						semaforo.setDescripcionError(errorCap.descripcion());
						return;
					}
				}
			break;
			default:
				errorCap = (ErroresDocumentosCapEnum) errores.get(0);
				semaforo.setCodigoError(errorCap.codigo());
				semaforo.setDescripcionError(errorCap.descripcion());
			}
		}
	
		
	}
}
