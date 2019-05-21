package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoNotificacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoNotificacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.GenerarCartaLegajoPdf;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.bean.ClienteDireccionVo;
import ar.com.telecom.shiva.negocio.bean.LegajoChequeRechazadoNotificacionPdf;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoNotificacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.ILegajoChequeRechazadoNotificacionDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacion;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjNotificacionSimplificado;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class LegajoChequeRechazadoNotificacionServicioImpl extends Servicio implements ILegajoChequeRechazadoNotificacionServicio {
	@Autowired
	private ILegajoChequeRechazadoNotificacionDao legajoChequeRechazadoNotificacionDao;
	@Autowired
	private IParametroServicio parametroServicio;
	@Autowired
	private ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio;
	
	
	
	@Override
	public ShvLgjNotificacionSimplificado guardar(ShvLgjNotificacionSimplificado shvLgjNotificacionSimplificado) throws NegocioExcepcion {
		Date now = new Date();

		if (Validaciones.isObjectNull(shvLgjNotificacionSimplificado.getIdNotificacion())) {
			shvLgjNotificacionSimplificado.setFechaCreacion(now);
			if (!Validaciones.isObjectNull(shvLgjNotificacionSimplificado.getCarta())) {
				shvLgjNotificacionSimplificado.getCarta().setFecha(now);
			}
		}
		try {
			shvLgjNotificacionSimplificado = legajoChequeRechazadoNotificacionDao.guardar(shvLgjNotificacionSimplificado);
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return shvLgjNotificacionSimplificado;
	}
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void anular(Long idNotificacion) throws NegocioExcepcion {
		try {
			legajoChequeRechazadoNotificacionDao.cambiarEstado(idNotificacion, new Date(), EstadoNotificacionEnum.ELIMINADA);
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return;
	}
	@Override
	public ShvLgjNotificacion buscar(Long idNotificacion) throws NegocioExcepcion {
		try {
			return legajoChequeRechazadoNotificacionDao.buscar(idNotificacion);
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public List<ShvLgjNotificacionSimplificado> listar(Long idLegajoChequeRechazado) throws NegocioExcepcion {
		try {
			return legajoChequeRechazadoNotificacionDao.lista(idLegajoChequeRechazado);
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	
	@Override
	public ArchivoByteArray generarCartaLegajoPdf(Long id) throws NegocioExcepcion{
		LegajoChequeRechazadoNotificacionPdf legajoNotificacionPdfVo = completarDatosParaNotificacionLegajoPdf(id);
		ArchivoByteArray archivo = new ArchivoByteArray();

		legajoNotificacionPdfVo.setUrlFirma(getClass().getResource("/firma." + legajoNotificacionPdfVo.getAnalistaFirmante() + ".png"));

		archivo.setByteArray(
			GenerarCartaLegajoPdf.generarCartaLegajo(legajoNotificacionPdfVo)
		);
		archivo.setNombreArchivo(
			Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.carta"),
					String.valueOf(legajoNotificacionPdfVo.getIdLegajoChequeRechazado()),
					String.valueOf(legajoNotificacionPdfVo.getNumCheque())
		));
		return archivo;
	}
	
	private LegajoChequeRechazadoNotificacionPdf completarDatosParaNotificacionLegajoPdf(Long id) throws NegocioExcepcion{
		LegajoChequeRechazadoNotificacionPdf legajoVo = new LegajoChequeRechazadoNotificacionPdf();
		ShvLgjNotificacion shvLgjNotificacion = buscar(id);
		
		if(!Validaciones.isObjectNull(shvLgjNotificacion)){
		
			legajoVo.setIdLegajoChequeRechazado(shvLgjNotificacion.getLegajoChequeRechazado().getIdLegajoChequeRechazado());
			legajoVo.setBancoOrigen(shvLgjNotificacion.getLegajoChequeRechazado().getBancoOrigen().getDescripcion());
			
			if(!Validaciones.isObjectNull(shvLgjNotificacion.getCarta())){
				ClienteDireccionVo clienteVo = new ClienteDireccionVo();
				clienteVo.setNombreCalle(shvLgjNotificacion.getCarta().getCalle());
				clienteVo.setAltura(shvLgjNotificacion.getCarta().getNumero());
				clienteVo.setCodigoPostal(shvLgjNotificacion.getCarta().getCodigoPostal());
				clienteVo.setPuerta((Validaciones.isObjectNull(shvLgjNotificacion.getCarta().getDepartamento()) ? "" : shvLgjNotificacion.getCarta().getDepartamento()));
				clienteVo.setDescLocalidad(shvLgjNotificacion.getCarta().getLocalidad());
				clienteVo.setPiso((Validaciones.isObjectNull(shvLgjNotificacion.getCarta().getPiso()) ? "" : shvLgjNotificacion.getCarta().getPiso()));
				if(!Validaciones.isObjectNull(shvLgjNotificacion.getCarta().getProvincia())){
					clienteVo.setDescProvincia(shvLgjNotificacion.getCarta().getProvincia().getDescripcion());
				}
				clienteVo.setRazonSocial(shvLgjNotificacion.getCarta().getNombreDestinatario());
				legajoVo.setCliente(clienteVo);

				legajoVo.setFechaCreacionCarta(shvLgjNotificacion.getCarta().getFecha());
				legajoVo.setFechaReembolso(shvLgjNotificacion.getCarta().getFechaReembolso());
				legajoVo.setAnalistaFirmante(shvLgjNotificacion.getCarta().getAnalistaFirmante());
				if (!Validaciones.isNullEmptyOrDash(legajoVo.getAnalistaFirmante())) {
					legajoVo.setDetalleFirma(parametroServicio.getValorTexto(
						Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.FIRMANTE_DOCUMENTO_DETALLEFIRMA),
							legajoVo.getAnalistaFirmante()
					)));
				}
			}
			
			if( !Validaciones.isObjectNull(shvLgjNotificacion.getLegajoChequeRechazado().getFechaEmision()) ) {
				legajoVo.setFechaEmision(shvLgjNotificacion.getLegajoChequeRechazado().getFechaEmision());
			}
				
			legajoVo.setImporteCheque(Utilidad.formatCurrency(shvLgjNotificacion.getLegajoChequeRechazado().getImporte(),false,false,2).replace(',', '.'));
			legajoVo.setMotivoRechazo(shvLgjNotificacion.getLegajoChequeRechazado().getMotivoLegajo().getDescripcion().toUpperCase());
			legajoVo.setNumCheque(shvLgjNotificacion.getLegajoChequeRechazado().getNumeroCheque().toString());	
			legajoVo.setNroLinea(shvLgjNotificacion.getCarta().getNroLinea());
		}
		
		return legajoVo;
	}
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public ShvLgjNotificacionSimplificado crear(ShvLgjNotificacionSimplificado shvLgjNotificacionSimplificado, Estado estadoWorkFlowLegajo) throws NegocioExcepcion, ValidationException {
		Date now = new Date();

		if (!this.validarTipoNotificacion(shvLgjNotificacionSimplificado.getTipoNotificacion(), estadoWorkFlowLegajo)) {
			throw new ValidacionExcepcion(
				Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(
						"legajo.cheques.rechazados.notificacion.validacion.tipo"),
						shvLgjNotificacionSimplificado.getTipoNotificacion().getDescripcion(),
						estadoWorkFlowLegajo.descripcion()
			));
		}
		if (Validaciones.isObjectNull(shvLgjNotificacionSimplificado.getIdNotificacion())) {
			shvLgjNotificacionSimplificado.setFechaCreacion(now);
			shvLgjNotificacionSimplificado.setFechaModificacion(now);
			if (!Validaciones.isObjectNull(shvLgjNotificacionSimplificado.getCarta())) {
				shvLgjNotificacionSimplificado.getCarta().setFecha(now);
			}
		}
		if (TipoNotificacionEnum.ENTREGA_CHEQUE.equals(shvLgjNotificacionSimplificado.getTipoNotificacion())) {
			legajoChequeRechazadoServicio.entregarCheque(
				shvLgjNotificacionSimplificado.getIdLegajoChequeRechazado(),
				shvLgjNotificacionSimplificado.getUsuarioAlta()
			);
		}
		shvLgjNotificacionSimplificado.setEstado(EstadoNotificacionEnum.ACTIVA);
		
		try {
			shvLgjNotificacionSimplificado = legajoChequeRechazadoNotificacionDao.guardar(shvLgjNotificacionSimplificado);
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return shvLgjNotificacionSimplificado;
	}
	
	private boolean validarTipoNotificacion(TipoNotificacionEnum tipoNotificacion, Estado estadoLegajo) {
		boolean valido = false;

		if (!TipoNotificacionEnum.ENTREGA_CHEQUE.equals(tipoNotificacion)) {
			valido = true;
		} else if (
			Estado.LGJ_EN_PROCESO_REEMBOLSO_CON_CHEQUE_PEND_ENTREGA.equals(estadoLegajo) ||
			Estado.LGJ_REEMBOLSADO_CON_CHEQUE_PEND_ENTREGA.equals(estadoLegajo) ||
			Estado.LGJ_CERRADO_CON_CHEQUE_PEND_ENTREGA.equals(estadoLegajo) ||
			Estado.LGJ_ABIERTO.equals(estadoLegajo)
		) {
			valido = true;
		}
		return valido;
	}
}
