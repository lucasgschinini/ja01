package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.MailExcepcion;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.GeneradorArchivoPdf;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.mapeos.OperacionTruncaMapeador;
import ar.com.telecom.shiva.negocio.servicios.IOperacionesTruncasServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.IOperacionTruncaDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.presentacion.bean.dto.LineaRegistroDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.OperacionTruncaFiltro;

import com.itextpdf.text.DocumentException;

public class OperacionesTruncasServicioImpl extends Servicio implements IOperacionesTruncasServicio {
	
	public static String EXT_PDF  = ".pdf";
	public static String GUION_BAJO= "_";
	
	public static String NOMBRE_ARCHIVO = "SHIVA_REPORTE_DESCOBROS_TRUNCOS";
	public static String ASUNTO_MAIL = "Telecom Argentina - Descobros manuales SHIVA con operaciones pendientes";
	
	private Date fecha=  Calendar.getInstance().getTime();
	
	private ByteArrayOutputStream doc;

	@Autowired
	private IDescobroDao descobroDao;
	@Autowired
	private IParametroServicio parametroServicio;
	@Autowired
	private IOperacionTruncaDao operacionTruncaDao;
	@Autowired
	private OperacionTruncaMapeador operacionTruncaMapeador;
	@Autowired 
	private MailServicio mailServicio;

	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		return null;
	}

	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		return null;
	}

	@Override
	public Long crear(DTO dto) throws NegocioExcepcion {
		return null;
	}

	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
	}

	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
	}

	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}

	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp) throws NegocioExcepcion {
	}

	@Override
	public void generarArchivoOperacionesTruncas(String fechaHasta) throws NegocioExcepcion {

		try {
			OperacionTruncaFiltro opeTruncaFiltro = new OperacionTruncaFiltro();

			LineaRegistroDto lineaRegistroDto = null;
			List<LineaRegistroDto> listLineaRegistroDto = new ArrayList<LineaRegistroDto>();
			opeTruncaFiltro.setFechaHasta(fechaHasta);
			
			Collection<ShvCobCobro> listaOperacionesTruncas = operacionTruncaDao.buscarOperacionesTruncas(opeTruncaFiltro);
			if (Validaciones.isCollectionNotEmpty(listaOperacionesTruncas)) {

				for (ShvCobCobro cobro : listaOperacionesTruncas) {
					
					ShvCobDescobro descobro = descobroDao.buscarDescobroPorIdCobro(cobro.getIdCobro());
					lineaRegistroDto = (LineaRegistroDto) operacionTruncaMapeador.map(descobro);
					listLineaRegistroDto.add(lineaRegistroDto);
				}

				try {
					GeneradorArchivoPdf archivoPdf = new GeneradorArchivoPdf();
					String carpetaDestino = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.operacionesTruncas");
					String nombreArchivo = carpetaDestino + NOMBRE_ARCHIVO + GUION_BAJO + Utilidad.formatDateAAAAMMDD_HHmmss(fecha) + EXT_PDF;
					doc = archivoPdf.GeneraArchivoPdf(listLineaRegistroDto, nombreArchivo);
					enviarMail(Mensajes.ASUNTO_MAIL_OPERACIONES_TRUNCAS,new StringBuffer(Mensajes.CUERPO_MAIL_OPERACIONES_TRUNCAS));
				} catch (DocumentException | IOException | SQLException e) {
					throw new NegocioExcepcion(e.getMessage(),e);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

	}

	private void enviarMail(String asunto, StringBuffer cuerpo) throws NegocioExcepcion, MailExcepcion {
		Mail mail = new Mail(parametroServicio.getValorTexto("destinatarios.mail.operaciones.truncas"), asunto, cuerpo);
		
		if(!Validaciones.isObjectNull(doc)){
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			listaAdjuntos.add(new Adjunto(doc.toByteArray(), NOMBRE_ARCHIVO, Constantes.EXTENSION_ARCHIVO_ADJUNTO));
			mail.setAdjuntos(listaAdjuntos);
		}

		mailServicio.enviarMail(mail);
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public IOperacionTruncaDao getOperacionTruncaDao() {
		return operacionTruncaDao;
	}

	public void setOperacionTruncaDao(IOperacionTruncaDao operacionTruncaDao) {
		this.operacionTruncaDao = operacionTruncaDao;
	}

	public OperacionTruncaMapeador getOperacionTruncaMapeador() {
		return operacionTruncaMapeador;
	}

	public void setOperacionTruncaMapeador(
			OperacionTruncaMapeador operacionTruncaMapeador) {
		this.operacionTruncaMapeador = operacionTruncaMapeador;
	}

	public ByteArrayOutputStream getDoc() {
		return doc;
	}

	public void setDoc(ByteArrayOutputStream doc) {
		this.doc = doc;
	}

}
