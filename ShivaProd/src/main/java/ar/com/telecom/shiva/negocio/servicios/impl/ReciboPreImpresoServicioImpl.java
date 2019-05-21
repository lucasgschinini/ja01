package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.IReciboPreImpresoServicio;
import ar.com.telecom.shiva.negocio.servicios.ITalonarioServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRecibosPreImpresos;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaReciboPreimpreso;
import ar.com.telecom.shiva.persistencia.dao.IReciboPreImpresoDao;
import ar.com.telecom.shiva.persistencia.dao.ITalonarioDao;
import ar.com.telecom.shiva.persistencia.dao.IVistaSoporteDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalReciboPreImpreso;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalTalonario;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvTalCompensacionSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvTalReciboPreImpresoCompensacionSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvTalReciboPreImpresoSimplificado;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ReciboPreImpresoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TalonarioDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaReciboPreImpresoFiltro;

public class ReciboPreImpresoServicioImpl extends Servicio implements IReciboPreImpresoServicio {
	

	@Autowired
	IReciboPreImpresoDao reciboPreImpresoDao;
	
	@Autowired
	ITalonarioDao talonarioDao;
	
	@Autowired 
	IWorkflowRecibosPreImpresos workflowRecibosPreImpresos;
	
	@Autowired 
	ITalonarioServicio talonarioServicio;
	
	@Autowired 
	IVistaSoporteDao vistaSoporteDao;
	
	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		try {
			return (ReciboPreImpresoDto) defaultMapeador.map(reciboPreImpresoDao.buscarRecibo(id));
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e); 
		}
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

	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
		ReciboPreImpresoDto reciboDto = (ReciboPreImpresoDto) dto;
		try {
			
			ShvTalReciboPreImpreso reciboModelo = 
					reciboPreImpresoDao.buscarRecibo(Integer.valueOf(reciboDto.getId().toString()));
			Date fechaAnulacion = new Date();
			String nombreUsuario = reciboDto.getNombreUsuarioModificacion();
			String datosModificados ="["+Constantes.DATOS_MODIFICADOS_USUARIO_ANULACION+"]("+Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL+": ) ("+Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO+": "+nombreUsuario+") ["+Constantes.DATOS_MODIFICADOS_FECHA_ANULACION+"]("+Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL+": ) ("+Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO+": "+Utilidad.formatDateAndTime(fechaAnulacion)+")";
			
			workflowRecibosPreImpresos.anularRecibo(reciboModelo.getWorkflow(), datosModificados, reciboDto.getUsuarioModificacion());
			reciboModelo.setUsuarioAnulacion(reciboDto.getUsuarioModificacion());
			reciboModelo.setFechaAnulacion(fechaAnulacion);
			
			reciboPreImpresoDao.actualizarRecibo(reciboModelo);
			
		} catch (NumberFormatException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}
	
	/**
	 * 
	 * @param listaIdRecibos
	 * @param idTalonario
	 * @param timeStamp
	 * @throws NegocioExcepcion
	 */
	public void verificarConcurrenciaLista (String[] listaIdRecibos, String idTalonario, Long timeStamp) throws NegocioExcepcion {
		String listaIdConcurrentes = "";
		ShvTalTalonario talonarioModelo;
		try {
				talonarioModelo = talonarioDao.buscarTalonario(Integer.valueOf(idTalonario));
			Iterator<ShvTalReciboPreImpreso> iterador = talonarioModelo.getShvTalReciboPreImpreso().iterator();
			for (int i = 0; i < listaIdRecibos.length; i++) {
				while (iterador.hasNext()){
					ShvTalReciboPreImpreso next = iterador.next();
					if (next.getIdReciboPreimpreso().compareTo(Integer.valueOf(listaIdRecibos[i])) == 0){
						if (next.getWorkflow().getFechaUltimaModificacion().getTime() > timeStamp.longValue()){
							listaIdConcurrentes += next.getNumeroRecibo()+",";
						}
					}
				}
			}
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		if (!listaIdConcurrentes.equals("")){
			listaIdConcurrentes = listaIdConcurrentes.substring(0,listaIdConcurrentes.length()-1);
			throw new ConcurrenciaExcepcion(listaIdConcurrentes);
		}
	}
	
	
	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp) throws NegocioExcepcion {
		try {
			ShvTalReciboPreImpreso reciboModelo = reciboPreImpresoDao.buscarRecibo(Integer.valueOf(id.toString()));
			if (reciboModelo.getWorkflow().getFechaUltimaModificacion().getTime() > timeStamp.longValue()){
				throw new ConcurrenciaExcepcion();
			} else {
				if (reciboModelo.getTalonario().getFechaAsignacion().getTime() < timeStamp.longValue()){
					if (reciboModelo.getTalonario().getWorkflow().getFechaUltimaModificacion().getTime()  > timeStamp.longValue()){
						throw new ConcurrenciaExcepcion();
					}
				}
			}
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	public void verificarConcurrenciaSimplificado(String nroRecibo, Long timeStamp) throws NegocioExcepcion {
		try {
			ShvTalReciboPreImpresoSimplificado reciboModelo = reciboPreImpresoDao.buscarSimplificadoPorNumeroRecibo(nroRecibo);
			if (reciboModelo.getWorkflow().getFechaUltimaModificacion().getTime() > timeStamp.longValue()){
				throw new ConcurrenciaExcepcion();
			} else {
				if (reciboModelo.getTalonario().getFechaAsignacion().getTime() < timeStamp.longValue()){
					if (reciboModelo.getTalonario().getWorkflow().getFechaUltimaModificacion().getTime()  > timeStamp.longValue()){
						throw new ConcurrenciaExcepcion();
					}
				}
			}
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void anularRecibos(String listaIdRecibos, UsuarioSesion usuarioLogueado, String idTalonario, String timeStamp) throws NegocioExcepcion {
		
		String[] idRecibos = listaIdRecibos.split("\\|");
		verificarConcurrenciaLista(idRecibos,idTalonario,Long.valueOf(timeStamp));
		for (String idRec : idRecibos) {
			ReciboPreImpresoDto dto = new ReciboPreImpresoDto();
			dto.setId(idRec);
			dto.setUsuarioModificacion(usuarioLogueado.getIdUsuario());
			dto.setNombreUsuarioModificacion(usuarioLogueado.getNombreCompleto());
			dto.setTimeStamp(timeStamp);
			anular(dto);
		}
		
		TalonarioDto talonarioDto = new TalonarioDto();
		talonarioDto.setIdTalonario(idTalonario);
		talonarioDto.setUsuarioModificacion(usuarioLogueado.getIdUsuario());
		talonarioServicio.anular(talonarioDto);
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void guardarCompensaciones(ReciboPreImpresoDto reciboDto, String usuarioModificacion) throws NegocioExcepcion {
		try {
			ShvTalReciboPreImpresoCompensacionSimplificado baseModeloSimplificado = reciboPreImpresoDao.buscarReciboCompensacionSimplificado(Integer.valueOf(reciboDto.getId().toString()));
			baseModeloSimplificado.getFechaIngreso();
			Set<ShvTalCompensacionSimplificado> listaCompensaciones = new HashSet<ShvTalCompensacionSimplificado>();
			verificarConcurrenciaSimplificado(reciboDto.getNroRecibo(), Long.valueOf(reciboDto.getTimeStamp()));
			String datosModificados = "";
			String fechaIngreso = new String();
			boolean fechaIngresoModificada = false;
			if (baseModeloSimplificado.getFechaIngreso() != null){
				if (!Utilidad.formatDatePicker(baseModeloSimplificado.getFechaIngreso()).equals(reciboDto.getFechaIngreso())){
					fechaIngreso = Utilidad.formatDatePicker(baseModeloSimplificado.getFechaIngreso());
					fechaIngresoModificada = true;
				}
			} else {
				if (!Validaciones.isNullOrEmpty(reciboDto.getFechaIngreso())){
					fechaIngreso = "";
					fechaIngresoModificada = true;
				}
			}
			ShvTalCompensacionSimplificado next;
			ShvWfWorkflow wfActualizado = null;
			Iterator<ShvTalCompensacionSimplificado> compIterador;
			if (!Validaciones.isNullOrEmpty(reciboDto.getListaCompensaciones())){
				String[] compensaciones = reciboDto.getListaCompensaciones().split("\\|");
				for (String compensacion : compensaciones) {
					//divide el string
					String[] comp = compensacion.split("_");
					ShvTalCompensacionSimplificado compModelo = new ShvTalCompensacionSimplificado();
					compModelo.setReferencia(comp[0]);
					compModelo.setImporte(Utilidad.stringToBigDecimal(comp[1]));
					compModelo.setIdReciboPreImpreso(Integer.valueOf(reciboDto.getId().toString()));
					listaCompensaciones.add(compModelo);
				}
				// Comparo las compensaciones recibidas con las de la base de datos
				// para armar datosModificados
				List<String[]> recLista = new ArrayList<String[]>();
				Iterator<ShvTalCompensacionSimplificado> recIterador=listaCompensaciones.iterator();
				while (recIterador.hasNext()){
					
					//Arma una lista con las compensaciones recibidas
					String[] agregar = new String[] {"","",""};
					next = recIterador.next();
					
					agregar[0] = next.getReferencia();
					agregar[1] = String.valueOf(next.getImporte());
					recLista.add(agregar);
				}
				if (baseModeloSimplificado.getShvTalCompensacionSimplificado().iterator().hasNext()){
					List<String[]> compLista = new ArrayList<String[]>();
					String bajas = "";
					compIterador=baseModeloSimplificado.getShvTalCompensacionSimplificado().iterator();
					while (compIterador.hasNext()){
						//Arma una lista con las compensaciones de la base de datos
						String[] agregar = new String[] {"","",""};
						next = compIterador.next();
							
						agregar[0] = next.getReferencia();
						agregar[1] = String.valueOf(next.getImporte());
						compLista.add(agregar);
					}
					for (String[] comp : compLista) {
						boolean flag = false;
						//Compara las listas	
						for (String[] rec : recLista) {
							if (rec[0].equalsIgnoreCase(comp[0]) && rec[1].equalsIgnoreCase(comp[1]) && Validaciones.isNullOrEmpty(rec[2])){
								flag = true;
								rec[2] = "-"; //se descarta
								break;
							}
						}
						//Si no encuentra coincidencia guarda los datos
						if ( !flag){
							bajas += Constantes.DATOS_MODIFICADOS_REFERENCIA+": "+comp[0]+" - "+Constantes.DATOS_MODIFICADOS_IMPORTE+": "+comp[1]+"|";
						}
					}
					if (bajas.length()>1){
						bajas = bajas.substring(0, bajas.length()-1);
					}
					if (!bajas.equals("")){
						datosModificados += "["+Constantes.DATOS_MODIFICADOS_COMPENSACIONES+"] ("+Constantes.DATOS_MODIFICADOS_ELIMINADOS+": "+bajas+")";
					}
				}
				String altas ="";
				for (String[] rec : recLista) {
					if (Validaciones.isNullOrEmpty(rec[2])){
						altas +=Constantes.DATOS_MODIFICADOS_REFERENCIA+": "+rec[0]+" - "+Constantes.DATOS_MODIFICADOS_IMPORTE+": "+rec[1]+"|";
					}
				}
				if ( altas.length()>1){
					altas = altas.substring(0, altas.length()-1);
				}
				
				if (baseModeloSimplificado.getImporte() == null){
					baseModeloSimplificado.setImporte(BigDecimal.ZERO);
				}
				if (!altas.equals("")){
					if (datosModificados.equals("")){
						datosModificados += "["+Constantes.DATOS_MODIFICADOS_COMPENSACIONES+"]";
					}
					datosModificados += "("+Constantes.DATOS_MODIFICADOS_AGREGADOS+": "+ altas+")";
				}
				reciboPreImpresoDao.actualizarCompensaciones(listaCompensaciones);
				baseModeloSimplificado.getShvTalCompensacionSimplificado().clear();
				if (!datosModificados.equals("")){
					datosModificados +=  "["+Constantes.DATOS_MODIFICADOS_IMPORTE+"] ("+Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL+": "+baseModeloSimplificado.getImporte()+")";
				}
				
				VistaSoporteBusquedaReciboPreImpresoFiltro reciboFiltro = new VistaSoporteBusquedaReciboPreImpresoFiltro();
				reciboFiltro.setIdRecibo(reciboDto.getId().toString());
				List<VistaSoporteResultadoBusquedaReciboPreimpreso> listaValores = vistaSoporteDao.buscarRecibos(reciboFiltro);
				
				baseModeloSimplificado.setImporte(calcularImporte(listaValores, listaCompensaciones));
				
				if (!datosModificados.equals("")){
					datosModificados +=  " ("+Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO+": "+baseModeloSimplificado.getImporte()+")";
				}
				if (fechaIngresoModificada){
					datosModificados += "["+Constantes.DATOS_MODIFICADOS_FECHA_INGRESO+"] ("+Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL+": "+
						fechaIngreso+") ("+Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO+": "+reciboDto.getFechaIngreso()+")";
				}
				if (Estado.REC_PENDIENTE.equals (baseModeloSimplificado.getWorkflow().getEstado())) {
					// Solo tomo el camino 'Asociar compensacion' si no tiene compensaciones ni valores, caso contrario me guio por el estado
					// de los valores que posea asociados
					wfActualizado = workflowRecibosPreImpresos.asociarCompensacion(baseModeloSimplificado.getWorkflow(), datosModificados, usuarioModificacion);
				}else{
					wfActualizado = workflowRecibosPreImpresos.actualizarWorkflow(baseModeloSimplificado.getWorkflow(), datosModificados, usuarioModificacion);
				}
			}else{
				boolean eliminarCompensaciones = false;
				if (!baseModeloSimplificado.getWorkflow().getShvWfWorkflowEstado().iterator().next().getEstado().equals(Estado.REC_PENDIENTE)){
					compIterador = baseModeloSimplificado.getShvTalCompensacionSimplificado().iterator();
					String bajas="";
					while (compIterador.hasNext()){
						next = compIterador.next();
						bajas += Constantes.DATOS_MODIFICADOS_REFERENCIA+": "+next.getReferencia()+" - "+Constantes.DATOS_MODIFICADOS_IMPORTE+": "+next.getImporte()+"|";
					}
					if (bajas.length()>1){
						bajas = bajas.substring(0, bajas.length()-1);
					}
					datosModificados += "["+Constantes.DATOS_MODIFICADOS_COMPENSACIONES+"]("+Constantes.DATOS_MODIFICADOS_ELIMINADOS+": "+bajas+") ["+Constantes.DATOS_MODIFICADOS_IMPORTE+"] ("+Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL+": "+baseModeloSimplificado.getImporte()+")";
					reciboPreImpresoDao.actualizarCompensaciones(listaCompensaciones);
					baseModeloSimplificado.getShvTalCompensacionSimplificado().clear();
					
					VistaSoporteBusquedaReciboPreImpresoFiltro reciboFiltro = new VistaSoporteBusquedaReciboPreImpresoFiltro();
					reciboFiltro.setIdRecibo(reciboDto.getId().toString());
					List<VistaSoporteResultadoBusquedaReciboPreimpreso> resultadoVista = vistaSoporteDao.buscarRecibos(reciboFiltro);
					
					baseModeloSimplificado.setImporte(calcularImporte(resultadoVista,listaCompensaciones));
					
					datosModificados += "("+Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO+": "+baseModeloSimplificado.getImporte()+")";
					if (baseModeloSimplificado.getImporte().compareTo(BigDecimal.ZERO)==0){
						baseModeloSimplificado.setImporte(null);
					}
					eliminarCompensaciones = true;
				}
				if (fechaIngresoModificada){
					datosModificados += "["+Constantes.DATOS_MODIFICADOS_FECHA_INGRESO+"] ("+Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL+": "+
						fechaIngreso+") ("+Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO+": "+reciboDto.getFechaIngreso()+")";
				}
				if (eliminarCompensaciones && Estado.REC_COMPENSACION.equals(baseModeloSimplificado.getWorkflow().getEstado())) {
					// Solo tomo el camino 'Eliminar compensacion' si solo tiene compensaciones, caso contrario me guio por el estado
					// de los valores que posea asociados
					wfActualizado = workflowRecibosPreImpresos.eliminarCompensacion(baseModeloSimplificado.getWorkflow(), datosModificados, usuarioModificacion);
				} else {
					wfActualizado = workflowRecibosPreImpresos.actualizarWorkflow(baseModeloSimplificado.getWorkflow(), datosModificados, usuarioModificacion);
				}
			}
			baseModeloSimplificado.setFechaIngreso(Utilidad.parseDatePickerString(reciboDto.getFechaIngreso()));

			baseModeloSimplificado.setWorkflow(wfActualizado);
			baseModeloSimplificado.getShvTalCompensacionSimplificado().addAll(listaCompensaciones);
			reciboPreImpresoDao.actualizarReciboSimplificado(baseModeloSimplificado);
		} catch (NumberFormatException | PersistenciaExcepcion | ParseException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Actualiza el saldo o estado de un recibo al que se le asigna o desasigna un valor.
	 * El parametro suma determina si el importe del valor debe sumarse (true) o restarse; es decir asignación o desasignación del importe del recibo.
	 */
	public void actualizacionEstado(List<List<ShvValValor>> matriz, String numeroRecibo, String usuarioModificacion) throws NegocioExcepcion{
		try {
			ShvTalReciboPreImpresoCompensacionSimplificado reciboModelo = reciboPreImpresoDao.buscarSimplificadoConCompensacionPorNumeroRecibo(numeroRecibo);
			String datosModificados = "["+Constantes.DATOS_MODIFICADOS_IMPORTE+"] ("+Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL+": "+reciboModelo.getImporte()+")";
			reciboModelo.setImporte(calcularImporte(matriz, reciboModelo));

			datosModificados += " ("+Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO+": "+reciboModelo.getImporte()+")";
			boolean pendienteConciliacion = false;
			boolean usadoODisponible = false;
			for (List<ShvValValor> lista : matriz) {
				for (ShvValValor valor : lista) {

					if (valor.getWorkFlow().getEstado().equals(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION)
							||valor.getWorkFlow().getEstado().equals(Estado.VAL_BOLETA_PENDIENTE_AUTORIZAR)
							||valor.getWorkFlow().getEstado().equals(Estado.VAL_AVISO_PAGO_PENDIENTE_CONFIRMAR)
							||valor.getWorkFlow().getEstado().equals(Estado.VAL_NO_DISPONIBLE)){
						pendienteConciliacion = true;
						break;
					} else if (valor.getWorkFlow().getEstado().equals(Estado.VAL_DISPONIBLE)
							|| valor.getWorkFlow().getEstado().equals(Estado.VAL_USADO)){
						usadoODisponible = true;
					}
				}
			}

			if (pendienteConciliacion){
				switch (reciboModelo.getWorkflow().getEstado()) {
				case REC_COMPENSACION:
					reciboModelo.setWorkflow(workflowRecibosPreImpresos.actCompensacionesPendiente(reciboModelo.getWorkflow(), datosModificados, usuarioModificacion));
					break;
				case REC_VALORES_CONCILIADOS:
					reciboModelo.setWorkflow(workflowRecibosPreImpresos.actConciliadoPendiente(reciboModelo.getWorkflow(), datosModificados, usuarioModificacion));
					break;
				case REC_PENDIENTE:
					reciboModelo.setWorkflow(workflowRecibosPreImpresos.establecerReciboPendiente(reciboModelo.getWorkflow(), datosModificados, usuarioModificacion));
					break;
				default:
					//no cambia de estado
				}
			} else if (usadoODisponible){
				switch (reciboModelo.getWorkflow().getEstado()) {
				case REC_COMPENSACION:
					reciboModelo.setWorkflow(workflowRecibosPreImpresos.actCompensacionesConciliado(reciboModelo.getWorkflow(), datosModificados, usuarioModificacion));
					break;
				case REC_VALORES_PENDIENTE:
					reciboModelo.setWorkflow(workflowRecibosPreImpresos.eliminarPendienteConciliado(reciboModelo.getWorkflow(), datosModificados, usuarioModificacion));
					break;
				case REC_PENDIENTE:
					reciboModelo.setWorkflow(workflowRecibosPreImpresos.establecerReciboConciliado(reciboModelo.getWorkflow(), datosModificados, usuarioModificacion));
					break;
				default:
					//no cambia de estado
				}
			} else if (!reciboModelo.getShvTalCompensacionSimplificado().isEmpty()) {
				switch (reciboModelo.getWorkflow().getEstado()) {
				case REC_VALORES_PENDIENTE:
					reciboModelo.setWorkflow(workflowRecibosPreImpresos.eliminarPendienteCompensaciones(reciboModelo.getWorkflow(), datosModificados, usuarioModificacion));
					break;
				case REC_VALORES_CONCILIADOS:
					reciboModelo.setWorkflow(workflowRecibosPreImpresos.eliminarConciliadoCompensaciones(reciboModelo.getWorkflow(), datosModificados, usuarioModificacion));
					break;
				default:
					//no cambia de estado
				}
			} else {
				switch (reciboModelo.getWorkflow().getEstado()) {
				case REC_VALORES_PENDIENTE:
					reciboModelo.setWorkflow(workflowRecibosPreImpresos.eliminarPendiente(reciboModelo.getWorkflow(), datosModificados, usuarioModificacion));
					break;
				case REC_VALORES_CONCILIADOS:
					reciboModelo.setWorkflow(workflowRecibosPreImpresos.eliminarConciliado(reciboModelo.getWorkflow(), datosModificados, usuarioModificacion));
					break;
				default:
					//no cambia de estado
				}
			}
			if (reciboModelo.getImporte().equals(BigDecimal.ZERO)){
				reciboModelo.setImporte(null);
			}
			reciboPreImpresoDao.actualizarReciboSimplificado(reciboModelo);
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	

	/**
	 * @see ar.com.telecom.shiva.negocio.servicios.IReciboPreImpresoServicio#validarFechaIngreso(ar.com.telecom.shiva.presentacion.bean.dto.ReciboPreImpresoDto)
	 * Se agrega el tipo de valor y el importe
	 */
	public String validarFechaIngreso (ReciboPreImpresoDto reciboDto) throws NegocioExcepcion {
		try {
			String advertencia = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("recibo.fechaIngreso.mensaje.diferencia.ok"), 
					reciboDto.getNroRecibo(), reciboDto.getFechaIngreso());
			
			VistaSoporteBusquedaReciboPreImpresoFiltro reciboFiltro = new VistaSoporteBusquedaReciboPreImpresoFiltro();
			reciboFiltro.setIdRecibo(reciboDto.getId().toString());
			List<VistaSoporteResultadoBusquedaReciboPreimpreso> resultadoVista = vistaSoporteDao.buscarRecibos(reciboFiltro);
			
			boolean error = false;
			for (VistaSoporteResultadoBusquedaReciboPreimpreso reciboVista : resultadoVista) {
				if (!Validaciones.isObjectNull(reciboVista.getListaValores())){
					for (ValorDto valor : reciboVista.getListaValores()) {
						
						String fechaIngresoReciboDelValor = "-";
						String fechaIngresoDelRecibo = "-";
						
						if (!Validaciones.isNullOrEmpty(valor.getFechaIngresoRecibo())) {
							fechaIngresoReciboDelValor = valor.getFechaIngresoRecibo();
						}
						
						if (!Validaciones.isNullOrEmail(reciboDto.getFechaIngreso())) {
							fechaIngresoDelRecibo = reciboDto.getFechaIngreso();
						}
						
						if (!fechaIngresoReciboDelValor.equals(fechaIngresoDelRecibo)) {
							advertencia +=  "<br><br>" + "Valor - Tipo Valor: " + TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valor.getIdTipoValor())).getDescripcion() 
										+ " | " + " Importe: " + valor.getImporte()  
										+ " | " + valor.getNumeroValor().replace("<br>", "|")
										+ " - " + Propiedades.MENSAJES_PROPIEDADES.getString("valor.fechaIngresoRecibo") + ": " + fechaIngresoReciboDelValor;
							error = true;
						}
					}
				}
			}
			if (error) {
				return advertencia;
			} else {
				return null;
			}
		} catch (NumberFormatException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Este método 'calcularImporte' se usa unicamente cuando se almacenan compensaciones (agrega o quitar) 
	 * 
	 * La lista de valores que recibe este método es la lista de valores completa, al igual que la lista de compensaciones
	 * que se arman en base a lo que existe en pantalla.
	 * 
	 * @param recibosVista
	 * @param listaCompensaciones
	 * @return
	 */
	private static BigDecimal calcularImporte(List<VistaSoporteResultadoBusquedaReciboPreimpreso> recibosVista, Set<ShvTalCompensacionSimplificado> listaCompensaciones) {
		BigDecimal importe = new BigDecimal(0);
		if(!Validaciones.isObjectNull(recibosVista) && Validaciones.isCollectionNotEmpty(recibosVista)){
			if (!Validaciones.isObjectNull(recibosVista.get(0).getListaValores())){
				for (ValorDto valor : recibosVista.get(0).getListaValores()){
						importe = importe.add(Utilidad.stringToBigDecimal(Utilidad.formatCurrency(valor.getImporte(),false, false)));
				}
			}
		}
		
		if (!Validaciones.isObjectNull(listaCompensaciones)){
			for (ShvTalCompensacionSimplificado compensacion : listaCompensaciones){
				importe = importe.add(compensacion.getImporte());
			}
		}
		return importe;
	}
	
	/**
	 * Este método 'calcularImporte' se usa unicamente en el actualizarEstado de recibos, que a su vez es invocado desde 
	 * el metodo actualizarEstadoRecibo en el servicio de Valores.
	 * 
	 * La lista de valores que recibe este método es la lista de valores completa, que contempla tanto los valores actualmente asociados
	 * como aquellos que se están agregando
	 * 
	 * @param matriz
	 * @param reciboModelo
	 * @return
	 */
	private static BigDecimal calcularImporte(List<List<ShvValValor>> matriz, ShvTalReciboPreImpresoCompensacionSimplificado reciboModelo) {
		BigDecimal importe = new BigDecimal(0);
		Iterator<ShvValValor> valIterador;

		for (int indice = 0; indice < 7; indice ++) {
			if (!matriz.get(indice).isEmpty()) {
				valIterador = matriz.get(indice).iterator();

				while (valIterador.hasNext()) {
					ShvValValor valor = valIterador.next();
					if (!valor.getWorkFlow().getEstado().equals(Estado.VAL_ANULADO)) {
						importe = importe.add(valor.getImporte());
					}
				}
			}
		}

		if (reciboModelo.getShvTalCompensacionSimplificado() != null){
			Iterator<ShvTalCompensacionSimplificado> compIterador = reciboModelo.getShvTalCompensacionSimplificado().iterator();
			while (compIterador.hasNext()){
				importe = importe.add(compIterador.next().getImporte());
			}
		}
		
		return importe;
	}

	/**
	 * Devuelve un mensaje de error si el recibo no es valido para la asignacion de un valor,
	 * teniendo en cuenta:
	 *   . estado del talonario (rendicion completada o asignado a administrador)
	 *   . estado del recibo (anulado)
	 *   . segmento del talonario
	 *   . fecha ingreso del recibo
	 *   
	 */
	@Override
	public String validarRecibo(ValorDto valorDto) throws NegocioExcepcion {
		String mensajeError = "";
		try {
			ShvTalReciboPreImpresoSimplificado reciboModelo = reciboPreImpresoDao.buscarSimplificadoPorNumeroRecibo(valorDto.getReciboPreImpreso());
			if (reciboModelo != null){
				if (!reciboModelo.getWorkflow().getEstado().equals(Estado.REC_ANULADO)){
					
					if (reciboModelo.getTalonario().getWorkflow().getEstado().equals(Estado.TAL_RENDICION_COMPLETADA) ||
							reciboModelo.getTalonario().getWorkflow().getEstado().equals(Estado.TAL_ASIGNADO_ADMINISTRADOR)){
						mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("recibo.asociarValor.error.estadoInvalido"), reciboModelo.getTalonario().getWorkflow().getEstado().descripcion(), valorDto.getReciboPreImpreso());
						
					} else {
						
						String mensajeErrorValor = "";
						String mensajeErrorRecibo = "";
						
						// SEGMENTO
						
						if (!reciboModelo.getTalonario().getSegmento().getIdSegmento().equals(valorDto.getIdSegmento())){
							valorDto.formarNumeroValor();
							mensajeError = "Existen valores cuyos datos no coinciden con los datos del recibo:"+"<br>";
							mensajeErrorValor = valorDto.getNumeroValor()+"<br>"
														+"-Segmento: "+valorDto.getSegmento()+"<br>";
							mensajeErrorRecibo = "Recibo "+reciboModelo.getNumeroRecibo()
														+"<br>-Segmento: "+reciboModelo.getTalonario().getSegmento().getDescripcion();
						}
						
						//FECHA INGRESO RECIBO
						
						if (!Validaciones.isNullOrEmpty(valorDto.getFechaIngresoRecibo()) && (!Validaciones.isObjectNull(reciboModelo.getFechaIngreso()) ? valorDto.getFechaIngresoRecibo().compareTo(Utilidad.formatDatePicker(reciboModelo.getFechaIngreso()))!=0 : false)){
							
							valorDto.formarNumeroValor();
							if (mensajeError == ""){
								mensajeError = "Existen valores cuyos datos no coinciden con los datos del recibo:"+"<br>";
								mensajeErrorValor = valorDto.getNumeroValor()+"<br>"
														+"-Fecha Ingreso Recibo: "+valorDto.getFechaIngresoRecibo()+"<br>";
								mensajeErrorRecibo = "Recibo "+reciboModelo.getNumeroRecibo()
										+"<br>-Fecha Ingreso: "+Utilidad.cambiarFormatoFechaDDMMAAAA(Utilidad.formatDateAAAAMMDD(reciboModelo.getFechaIngreso()));
							} else {
								
								mensajeErrorValor += "-Fecha Ingreso Recibo: "+valorDto.getFechaIngresoRecibo()+"<br>";
								mensajeErrorRecibo += "<br>-Fecha Ingreso: "+Utilidad.cambiarFormatoFechaDDMMAAAA(Utilidad.formatDateAAAAMMDD(reciboModelo.getFechaIngreso()));
							}
						}
						mensajeError += mensajeErrorValor + mensajeErrorRecibo;
					}
				} else {
					// RECIBO ANULADO
					if (reciboModelo.getTalonario().getWorkflow().getEstado().equals(Estado.TAL_RENDICION_COMPLETADA)
							|| reciboModelo.getTalonario().getWorkflow().getEstado().equals(Estado.TAL_ASIGNADO_ADMINISTRADOR)){
						
						mensajeError += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("recibo.asociarValor.error.reciboAnuladoTalonarioRendCompletaAsignadoAdmin"),
								valorDto.getReciboPreImpreso(),reciboModelo.getWorkflow().getEstado().descripcion(),
									reciboModelo.getTalonario().getWorkflow().getEstado().descripcion());
								
					} else {
						mensajeError += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("recibo.asociarValor.error.reciboAnulado"),
								valorDto.getReciboPreImpreso(),reciboModelo.getWorkflow().getEstado().descripcion());
					}
				}
				
			} else {
				mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("recibo.asociarValor.error.reciboInexistente"), valorDto.getReciboPreImpreso());
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
		return mensajeError;
	}

	/**
	 * 
	 * @return
	 */
	public IReciboPreImpresoDao getReciboPreImpresoDao() {
		return reciboPreImpresoDao;
	}

	/**
	 * 
	 * @param reciboPreImpresoDao
	 */
	public void setReciboPreImpresoDao(IReciboPreImpresoDao reciboPreImpresoDao) {
		this.reciboPreImpresoDao = reciboPreImpresoDao;
	}
}
