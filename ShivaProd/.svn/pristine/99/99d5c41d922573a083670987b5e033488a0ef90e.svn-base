package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.ControlHilosCobrosBatchRunner;
import ar.com.telecom.shiva.negocio.bean.ControlDeHilosCobros;
import ar.com.telecom.shiva.negocio.servicios.IControlHilosCobrosServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

/**
 * 
 * @author Lucas Schinini
 *
 */
@Service
public class ControlHilosCobrosServicioImpl implements IControlHilosCobrosServicio {

	@Autowired ICobroDao cobroDao;
	@Autowired
	private MailServicio mailServicio;
	
	public ICobroDao getCobroDao() {
		return cobroDao;
	}

	public void setCobroDao(ICobroDao cobroDao) {
		this.cobroDao = cobroDao;
	}
	
	@Override
	public void iniciarProcesoDeControlDeHilos(Integer tiempoLimite,String estadosCobro, String destinatario, String destinatarioCC, String horaEnvioMailCobrosPendientes) throws NegocioExcepcion {
		
		List<ControlDeHilosCobros> listaCobros = new ArrayList<ControlDeHilosCobros>();
		List<ControlDeHilosCobros> listaHilos = new ArrayList<ControlDeHilosCobros>();
		
		Traza.advertencia(ControlHilosCobrosBatchRunner.class, "---- Se inicia la busqueda de hilos que llevan más de " + tiempoLimite +" hora/s de procesamiento");
		
		//Busco los hilos
		listaHilos = buscarHilosEnProceso(tiempoLimite);
		
		//Envio mail
		enviarMail(listaHilos,null,destinatario,destinatarioCC,tiempoLimite);
		
		String[] horaEnvioMails = horaEnvioMailCobrosPendientes.split(",");
		
		Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        
		for (String string : horaEnvioMails) {
			int hora = Integer.valueOf(string);
			if (hora == fechaActual.get(Calendar.HOUR_OF_DAY)){
				
				Traza.advertencia(ControlHilosCobrosBatchRunner.class, "---- Se inicia la busqueda de cobros en proceso ");
				
				//Busco los cobros
				listaCobros = buscarCobrosEnProceso(estadosCobro);
				
				//Envio mail
				enviarMail(null,listaCobros,destinatario,destinatarioCC,tiempoLimite);
				
			}
		}
		
		
	}

	private void enviarMail(List<ControlDeHilosCobros> listaHilos, List<ControlDeHilosCobros> listaCobros, String destinatario, String destinatarioCC, Integer tiempoLimite) {
		
		Mail mail = null; 
		String asunto ="";
		boolean enviarMail = false;
		Date fechaActual= new Date();
		String tiempoSinProcesar ="";
		long diferenciaEnMilisegundos = 0;
		StringBuffer cuerpo= new StringBuffer();
		
		if(Validaciones.isCollectionNotEmpty(listaHilos)){
			
			Traza.advertencia(ControlHilosCobrosBatchRunner.class, "---- Se encontraron [" + listaHilos.size() + "]");
			//Asunto: Shiva - Control de hilos
			asunto = Propiedades.MENSAJES_PROPIEDADES.getString("titulo.nombre.app") + " - " + Propiedades.MENSAJES_PROPIEDADES.getString("mail.asunto.control.hilos");
			
			cuerpo.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cuerpo.control.hilos") + " <br> <br>", tiempoLimite.toString()));
			
			for (ControlDeHilosCobros hilo : listaHilos) {
				
				diferenciaEnMilisegundos = fechaActual.getTime() - hilo.getFechaInicioHilo().getTime();
				
				hilo.setDiferenciaEnMilisegundos(diferenciaEnMilisegundos);
			}
			
			listaHilos = ordenarLista(listaHilos);
			
			for (ControlDeHilosCobros hilo : listaHilos) {
				
				tiempoSinProcesar = calcularTiempoSinProcesar(hilo.getDiferenciaEnMilisegundos());
				
				cuerpo.append("<b>[ID OPERACION]:</b> " + hilo.getIdOperacion() + "<br><b> [FECHA INICIO DEL HILO]: </b>" + hilo.getFechaInicioHilo()
						+ "<br><b>[TIEMPO DE INACTIVIDAD DEL HILO]: </b>" + tiempoSinProcesar + " <br><br>");
				Traza.advertencia(ControlHilosCobrosBatchRunner.class, "----[ID OPERACION]:" + hilo.getIdOperacion() + " [FECHA INICIO DEL HILO]: " + hilo.getFechaInicioHilo()
						+ "[TIEMPO DE INACTIVIDAD DEL HILO]: " + tiempoSinProcesar);
						
			}
			
			enviarMail = true;
		}

		if(Validaciones.isCollectionNotEmpty(listaCobros)){

			fechaActual= new Date();
			diferenciaEnMilisegundos = 0;

			asunto = Propiedades.MENSAJES_PROPIEDADES.getString("titulo.nombre.app") + " - " + Propiedades.MENSAJES_PROPIEDADES.getString("mail.asunto.control.hilos.estados.cobros");
			
			for (ControlDeHilosCobros cobro : listaCobros) {
				
				diferenciaEnMilisegundos = fechaActual.getTime() - cobro.getFechaUltimaModificacionWF().getTime();
				
				cobro.setDiferenciaEnMilisegundos(diferenciaEnMilisegundos);
				
			}
			
			listaCobros = ordenarLista(listaCobros);
			
			for (ControlDeHilosCobros cobro : listaCobros) {
				System.out.println("[ID OPERACION]: "+ cobro.getIdOperacion() + " TIEMPO EN MILISEGUNDOS: " + cobro.getDiferenciaEnMilisegundos());
				tiempoSinProcesar = calcularTiempoSinProcesar(cobro.getDiferenciaEnMilisegundos());

				cuerpo.append("<b>[ID OPERACION]:</b> " + cobro.getIdOperacion() + "<br><b> [ESTADO]:</b> " +cobro.getEstadoCobro() + "<br> <b>[FECHA ULTIMO ESTADO DE COBRO]:</b> " + cobro.getFechaUltimaModificacionWF() 
						+ "<br><b>[TIEMPO DE INACTIVIDAD DEL COBRO]: </b>" + tiempoSinProcesar + " <br><br> ");
				Traza.advertencia(ControlHilosCobrosBatchRunner.class, "----[ID OPERACION]:" + cobro.getIdOperacion() + " [FECHA ULTIMO ESTADO DE COBRO]: " 
						+ cobro.getFechaUltimaModificacionWF());
				System.out.println(cuerpo);
				enviarMail = true;
			}
			
		}
		
		if (enviarMail){
			mail = new Mail(destinatario,destinatarioCC,asunto,new StringBuffer(cuerpo));
			mailServicio.sendMail(mail);
		}
		
	}
	
	private List<ControlDeHilosCobros> ordenarLista(List<ControlDeHilosCobros> listaControlCobros) {
		
		Collections.sort(listaControlCobros, new Comparator<ControlDeHilosCobros>() {
			public int compare(ControlDeHilosCobros o1, ControlDeHilosCobros o2) {
				return (o2.getDiferenciaEnMilisegundos()).compareTo(o1.getDiferenciaEnMilisegundos());
			}
		});
		
		return listaControlCobros;
	}

	/**
	 * 
	 * @param differenceInMillis
	 * @return
	 */
	private String calcularTiempoSinProcesar(long differenceInMillis) {
		
		int dias = 0;
		String tiempoSinProcesar = Propiedades.MENSAJES_PROPIEDADES.getString("mail.cuerpo.control.hilos.cobros.tiempo.sin.procesar");
		
		long horas = 0;
		long minutos = 0;
		long segundos = differenceInMillis/1000;
		
		while (segundos > Constantes.$1_MIN_EN_SEGUNDOS){
			if(segundos/Constantes.$1_MIN_EN_SEGUNDOS==0){
				minutos+=segundos/Constantes.$1_MIN_EN_SEGUNDOS;
			} else {
				minutos+=segundos/Constantes.$1_MIN_EN_SEGUNDOS;
				segundos = segundos%Constantes.$1_MIN_EN_SEGUNDOS;
			}
			
		}
		
		while(minutos > Constantes.$1_HORA_EN_MINUTOS){
			
			if(minutos/Constantes.$1_HORA_EN_MINUTOS==0){
				horas+=minutos/Constantes.$1_HORA_EN_MINUTOS;
			} else {
				horas+=minutos/Constantes.$1_HORA_EN_MINUTOS;
				minutos = minutos%Constantes.$1_HORA_EN_MINUTOS;
			}
		}
		
		while(horas > Constantes.$1_DIA_EN_HORAS){
			if(horas/Constantes.$1_DIA_EN_HORAS==0){
				dias+=horas/Constantes.$1_DIA_EN_HORAS;
			} else {
				dias+=horas/Constantes.$1_DIA_EN_HORAS;
				horas = horas%Constantes.$1_DIA_EN_HORAS;
			}
		}
		
		if (dias > Constantes.CERO){
			tiempoSinProcesar = dias + " dia/s " + tiempoSinProcesar;
		}
		
		return Utilidad.reemplazarMensajes(tiempoSinProcesar,String.valueOf(horas),String.valueOf(minutos),String.valueOf(segundos));
		
	}

	public List<ControlDeHilosCobros> buscarHilosEnProceso(Integer tiempoLimite) throws NegocioExcepcion {
		
		List<ControlDeHilosCobros> listaHilos = new ArrayList<ControlDeHilosCobros>();
		
		try {
			listaHilos = cobroDao.buscarHilosEnProceso(tiempoLimite);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return listaHilos;
	}
	
	public List<ControlDeHilosCobros> buscarCobrosEnProceso(String estadosCobro) throws NegocioExcepcion {
		
		List<ControlDeHilosCobros> listaCobros = new ArrayList<ControlDeHilosCobros>();
		String[] estados = estadosCobro.split(",");
		List<Estado> listaEstadosEnum = new ArrayList<Estado>();

		for (String string : estados) {
			Estado estado = Estado.getEnumByName(string);
			if(!Validaciones.isObjectNull(estado)){
				listaEstadosEnum.add(estado);
			}
		}
			
		try {
			listaCobros = cobroDao.buscarCobrosEnProceso(listaEstadosEnum);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return listaCobros;
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
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {
		// TODO Auto-generated method stub
		
	}
}