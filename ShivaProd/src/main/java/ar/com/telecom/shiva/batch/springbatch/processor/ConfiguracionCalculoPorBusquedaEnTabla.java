package ar.com.telecom.shiva.batch.springbatch.processor;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Value;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataFile;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 * Aqui un ejemplo de como implementar dentro de la lista de campos calculados
 * 
 *	<bean class="ar.com.telecom.shiva.batch.springbatch.processor.ConfiguracionCalculoPorBusquedaEnTabla" scope="step">
 *		<property name="stepExecution" value="#{stepExecution}" />
 *		<property name="ordenProcesamiento" value="2"/>
 *		<property name="configuracionConsultaHql">
 *	 		<bean class="ar.com.telecom.shiva.batch.springbatch.processor.ConfiguracionConsultaHql">
 *				<property name="consultaHql" value="select idClienteLegado from ShvParamBancoCliente where codigoOpBanco = {codigoOpBanco} and codigoOperacion = {codigoOperacion} and idAcuerdo = 98"/>
 *			</bean>
 *		</property>
 *		<property name="configuracionCampoDestino">
 *			<bean class="ar.com.telecom.shiva.batch.springbatch.processor.ConfiguracionCampo">
 *				<property name="posicionDeCorrespondencia" value="0"/>
 *				<property name="id" value="codigoCliente"/>
 *				<property name="nombre" value="Codigo de Cliente"/>
 *				<property name="configuracionTipoDato">
 *					<bean class="ar.com.telecom.shiva.batch.springbatch.processor.ConfiguracionTipoDato">
 *						<property name="tipoDato" value="TEXTO"/>
 *						<property name="longitud" value="11"/>
 *					</bean>
 *				</property>
 *				<property name="configuracionObligatoriedad">
 *					<bean class="ar.com.telecom.shiva.batch.springbatch.processor.ConfiguracionObligatoriedad">
 *						<property name="esObligatorio" value="SI"/>
 *					</bean>
 *				</property>
 *				<property name="configuracionMapeoModeloRelacionado">
 *					<bean class="ar.com.telecom.shiva.batch.springbatch.processor.ConfiguracionMapeoAtributoModeloRelacionado">
 *						<property name="atributoMapeoModeloRelacionado" value="codigoCliente"/>
 *					</bean>
 *				</property>
 *			</bean>
 *		</property>
 *	</bean>
 *
 */
public class ConfiguracionCalculoPorBusquedaEnTabla extends ConfiguracionCalculo implements IConfiguracionCalculo {

	private ConfiguracionConsultaHql	configuracionConsultaHql;
	private IConfiguracionCampo			configuracionCampoDestino;

	@Value("#{stepExecution}")
	private StepExecution stepExecution;

	@Override
	public void calcular(Object objOrigen) throws NegocioExcepcion {

		try {
			List<String> listaAtributos = (List<String>) Utilidad.getAtributos(configuracionConsultaHql.getConsultaHql());
			Object valorDeAsignacion = null;
			StringBuilder logProcesamientoCalculo = new StringBuilder();

			Collection<Object> resultadoConsultaDinamica = obtenerResultadoConsultaDinamica(objOrigen, configuracionConsultaHql.getConsultaHql(), listaAtributos);
			
			if (Validaciones.isCollectionNotEmpty(resultadoConsultaDinamica) && resultadoConsultaDinamica.size() == 1) {

				valorDeAsignacion = resultadoConsultaDinamica.iterator().next();
				setValorAtributo(objOrigen, configuracionCampoDestino.getId(), valorDeAsignacion);
			} else {
				
				// Armo un mensaje de error mas amigable para mostrar al usuario
		        // Traigo el bean del contexto
		        GenericDataFile genericFile = (GenericDataFile) stepExecution.getJobExecution().getExecutionContext().get("genericDataFile");

				logProcesamientoCalculo.append("No existe un valor definido para el campo '");
				logProcesamientoCalculo.append(configuracionCampoDestino.getNombre());
				logProcesamientoCalculo.append("' para el acuerdo '");
				logProcesamientoCalculo.append(genericFile.getIdAcuerdo());
				logProcesamientoCalculo.append("'");
				
				if (validarAtributosObtenidos(listaAtributos, objOrigen) && Validaciones.isCollectionNotEmpty(listaAtributos)) {
					for (String atributo : listaAtributos) {
						try {
						logProcesamientoCalculo.append(", ");
						logProcesamientoCalculo.append(atributo);
						logProcesamientoCalculo.append(" '");
						logProcesamientoCalculo.append(getValorAtributo(objOrigen, atributo));
						logProcesamientoCalculo.append("'");
						} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							throw new NegocioExcepcion(e);
						}
					}
				}
				
				logProcesamientoCalculo.append(Constantes.PUNTO);
				logProcesamientoCalculo.append(System.lineSeparator());
			}

		    Traza.auditoria(this.getClass(), "Resultado del calculo realizado");
		    Traza.auditoria(this.getClass(), "Entrada: Sql a Ejecutar: '" + configuracionConsultaHql.getConsultaHql());
		    Traza.auditoria(this.getClass(), "Salida:  idCampoDestino: '" + configuracionCampoDestino.getId() + "', valor asignado: '" + valorDeAsignacion + "'");
		    Traza.auditoria(this.getClass(), "");

			// Una vez seteado el resultado de la consulta en el destino, valido como ha quedado el resultado obtenido, validando el campo
			try {
				configuracionCampoDestino.validar(objOrigen, stepExecution.getJobExecution().getExecutionContext());
			} catch (NegocioExcepcion e) {
				
				String logProcesamiento = (String) stepExecution.getJobExecution().getExecutionContext().get("logProcesamiento");
				Long lineaActual = (Long) stepExecution.getJobExecution().getExecutionContext().get("lineaActual");

				if (Validaciones.isNullEmptyOrDash(logProcesamiento)) {
					logProcesamiento = Constantes.EMPTY_STRING;
				}
				
				// Debo verificar la obligatoridad del campo si deseo agregar el mensaje de error
				// de que no tuve resultados en la busqueda de registros antes realizada
				// No es lo mejor desde el punto de vista lógica, ya que estamos mezclando funcionalidad de bloques de configuracionCampo con configuracionCalculo
				// y repitiendo logica, pero dará un mensaje mas completo al usuario
				if (!Validaciones.isObjectNull(configuracionCampoDestino.getConfiguracionObligatoriedad())
						&& SiNoEnum.SI.equals(SiNoEnum.getEnumByName(configuracionCampoDestino.getConfiguracionObligatoriedad().getEsObligatorio()))) {
					
					if (Validaciones.isNullEmptyOrDash(String.valueOf(valorDeAsignacion))) {
						logProcesamiento += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.error.logProcesamiento"), 
																				lineaActual.toString(), logProcesamientoCalculo.toString());
						
//						logProcesamiento += "Línea número: " + lineaActual + " Causa: " + logProcesamientoCalculo;
					}
				}
				
//				logProcesamiento += "Línea número: " + lineaActual + " Causa: " + e.getMessage();
				logProcesamiento += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.error.logProcesamiento"),
																				lineaActual.toString(), e.getMessage());
				
				stepExecution.getJobExecution().getExecutionContext().put("logProcesamiento", logProcesamiento);
				Traza.error(ConfiguracionCalculoPorBusquedaEnTabla.class, e.getMessage());

				throw new ValidationException("Se han encontrado campos que no pasaron las validaciones en la línea: " + lineaActual, e);
			}
			
		} catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();

			throw new NegocioExcepcion("Hubo un error desconocido al aplicar la logica de 'CalculoPorBusquedaEnTabla': " + e.getMessage(), e);
		}
	}

	/**
	 * @return the configuracionConsultaHql
	 */
	public ConfiguracionConsultaHql getConfiguracionConsultaHql() {
		return configuracionConsultaHql;
	}

	/**
	 * @param configuracionConsultaHql
	 *            the configuracionConsultaHql to set
	 */
	public void setConfiguracionConsultaHql(ConfiguracionConsultaHql configuracionConsultaHql) {
		this.configuracionConsultaHql = configuracionConsultaHql;
	}

	/**
	 * @return the configuracionCampoDestino
	 */
	public IConfiguracionCampo getConfiguracionCampoDestino() {
		return configuracionCampoDestino;
	}

	/**
	 * @param configuracionCampoDestino the configuracionCampoDestino to set
	 */
	public void setConfiguracionCampoDestino(IConfiguracionCampo configuracionCampoDestino) {
		this.configuracionCampoDestino = configuracionCampoDestino;
	}

	/**
	 * @return the stepExecution
	 */
	public StepExecution getStepExecution() {
		return stepExecution;
	}

	/**
	 * @param stepExecution the stepExecution to set
	 */
	public void setStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}
}
