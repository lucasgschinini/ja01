
package ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import ar.com.telecom.shiva.base.soa.xmlAdapters.BigIntegerXmlAdapter;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Pin_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "pin");
    private final static QName _Tipo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipo");
    private final static QName _CodigoLocalidad_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "codigoLocalidad");
    private final static QName _SearchingKey_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "searchingKey");
    private final static QName _UserID_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "userID");
    private final static QName _FechaHoraEnRuta_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraEnRuta");
    private final static QName _Estado_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "estado");
    private final static QName _AtenuacionUP_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "atenuacionUP");
    private final static QName _FechaHoraDespacho_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraDespacho");
    private final static QName _MargenSenalRuidoObjetivoDown_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "margenSenalRuidoObjetivoDown");
    private final static QName _Localizacion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "localizacion");
    private final static QName _FechaCumplCx_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaCumplCx");
    private final static QName _Ack_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ack");
    private final static QName _PotenciaDeTransmisionUp_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "potenciaDeTransmisionUp");
    private final static QName _IdentificadorEntidadProveedor_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "identificadorEntidadProveedor");
    private final static QName _NormaOperativa_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "normaOperativa");
    private final static QName _ProviderCode_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "providerCode");
    private final static QName _TipoVinculo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoVinculo");
    private final static QName _NsiCabina_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nsiCabina");
    private final static QName _ComentarioCita_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "comentarioCita");
    private final static QName _Bastidor_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "bastidor");
    private final static QName _UsuarioRecomendado_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "usuarioRecomendado");
    private final static QName _VelocidadADSLTerminal_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "velocidadADSLTerminal");
    private final static QName _TecnologiaADSL_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tecnologiaADSL");
    private final static QName _UnidadesTransmitidasUp_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "UnidadesTransmitidasUp");
    private final static QName _ReclamoFechaIngresoRed_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "reclamoFechaIngresoRed");
    private final static QName _FechaVtoIndicador_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaVtoIndicador");
    private final static QName _Path_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "path");
    private final static QName _Resultado_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "resultado");
    private final static QName _PerfilCRM_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "perfilCRM");
    private final static QName _NumeroSolicitud_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "numeroSolicitud");
    private final static QName _Af2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "af2");
    private final static QName _Predecesor_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "predecesor");
    private final static QName _Af1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "af1");
    private final static QName _State_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "state");
    private final static QName _NombreCalle_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nombreCalle");
    private final static QName _Ocupados_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ocupados");
    private final static QName _Denycode_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "denycode");
    private final static QName _SolicitudAsociadaPM_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "solicitudAsociadaPM");
    private final static QName _CodigoServicioIntegral_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "codigoServicioIntegral");
    private final static QName _Tarea_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tarea");
    private final static QName _NroLinea_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nroLinea");
    private final static QName _SendJndiName_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sendJndiName");
    private final static QName _ParPredecesor_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "parPredecesor");
    private final static QName _Vacante_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "vacante");
    private final static QName _ServiceCode_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "serviceCode");
    private final static QName _NombreLocalidad_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nombreLocalidad");
    private final static QName _PrimerFechaHoraIngresoRed_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "primerFechaHoraIngresoRed");
    private final static QName _RepartidorGeneral_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "repartidorGeneral");
    private final static QName _Th2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "th2");
    private final static QName _Th3_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "th3");
    private final static QName _Grupo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "grupo");
    private final static QName _Th1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "th1");
    private final static QName _FechaReporteSus_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaReporteSus");
    private final static QName _CodigoOrigen_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "codigoOrigen");
    private final static QName _Scoring_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "scoring");
    private final static QName _FechaHoraUltimaPruebaDSL_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraUltimaPruebaDSL");
    private final static QName _TotalVinculos_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "totalVinculos");
    private final static QName _Codigo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "codigo");
    private final static QName _OficinaGestion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "oficinaGestion");
    private final static QName _Level_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "level");
    private final static QName _Ln1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ln1");
    private final static QName _Unidad_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "unidad");
    private final static QName _CapacidadMaximaParUp_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "capacidadMaximaParUp");
    private final static QName _ReclamoMarcaRecomendado_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "reclamoMarcaRecomendado");
    private final static QName _Nivel4_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nivel4");
    private final static QName _GrupoAseguramiento_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "grupoAseguramiento");
    private final static QName _Nivel5_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nivel5");
    private final static QName _CodigoAnomalia_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "codigoAnomalia");
    private final static QName _Location_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "location");
    private final static QName _Nivel2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nivel2");
    private final static QName _Nivel3_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nivel3");
    private final static QName _Ln3_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ln3");
    private final static QName _AnyElement_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "AnyElement");
    private final static QName _Proyeccion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "proyeccion");
    private final static QName _Ln2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ln2");
    private final static QName _EstadoSolicitud_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "estadoSolicitud");
    private final static QName _Abajo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "abajo");
    private final static QName _EndTime_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "endTime");
    private final static QName _Nessap_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nessap");
    private final static QName _Indisponibles_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "indisponibles");
    private final static QName _Causa_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "causa");
    private final static QName _NsiAparato_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nsiAparato");
    private final static QName _SupplierErrorCode_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "supplierErrorCode");
    private final static QName _TipoSolicitudPendienteSur_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoSolicitudPendienteSur");
    private final static QName _Equipo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "equipo");
    private final static QName _UsuarioMovilAsignado_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "usuarioMovilAsignado");
    private final static QName _TargetNPI_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "targetNPI");
    private final static QName _ErrorCode_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "errorCode");
    private final static QName _FechaHoraCreacion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraCreacion");
    private final static QName _Extremo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "extremo");
    private final static QName _SumaryCode6_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sumaryCode6");
    private final static QName _Fecha_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fecha");
    private final static QName _UltimoCambioEstadoDelPort_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ultimoCambioEstadoDelPort");
    private final static QName _ViolacionDeCodigoUp_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "violacionDeCodigoUp");
    private final static QName _SumaryCode2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sumaryCode2");
    private final static QName _FechaPagoDUC_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaPagoDUC");
    private final static QName _IdentificadorOT_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "identificadorOT");
    private final static QName _SumaryCode3_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sumaryCode3");
    private final static QName _SumaryCode4_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sumaryCode4");
    private final static QName _SumaryCode5_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sumaryCode5");
    private final static QName _VelocidadLineaRealDown_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "velocidadLineaRealDown");
    private final static QName _SumaryCode1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sumaryCode1");
    private final static QName _Topologia_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "topologia");
    private final static QName _EstadoLinea_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "estadoLinea");
    private final static QName _Telefono_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "telefono");
    private final static QName _Descripcion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "descripcion");
    private final static QName _CondicionIVA_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "condicionIVA");
    private final static QName _Nzgnse_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nzgnse");
    private final static QName _Sintoma_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sintoma");
    private final static QName _Nombre_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nombre");
    private final static QName _FechaInstalacionTemporaria_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaInstalacionTemporaria");
    private final static QName _VelocidadADSLZona_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "velocidadADSLZona");
    private final static QName _EquipmentSupplier_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "equipmentSupplier");
    private final static QName _FechaHoraCancelacion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraCancelacion");
    private final static QName _CentralConmutacion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "centralConmutacion");
    private final static QName _TargetTON_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "targetTON");
    private final static QName _Sk10_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk10");
    private final static QName _SolicitudProvision_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "solicitudProvision");
    private final static QName _TransactionId_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "transactionId");
    private final static QName _Sk13_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk13");
    private final static QName _FechaHoraEnSitio_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraEnSitio");
    private final static QName _Sk11_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk11");
    private final static QName _Sk12_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk12");
    private final static QName _Problema_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "problema");
    private final static QName _FechaCumplISP_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaCumplISP");
    private final static QName _SupplierErrorDescription_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "supplierErrorDescription");
    private final static QName _FechaCumplAptoTP_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaCumplAptoTP");
    private final static QName _Ac6_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ac6");
    private final static QName _EstadoOperativo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "estadoOperativo");
    private final static QName _Ac5_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ac5");
    private final static QName _Ac4_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ac4");
    private final static QName _Ac3_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ac3");
    private final static QName _VelocidadLineaConfiguradaUp_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "velocidadLineaConfiguradaUp");
    private final static QName _Ac2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ac2");
    private final static QName _Comment_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "comment");
    private final static QName _Tratamiento_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tratamiento");
    private final static QName _Ebos_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ebos");
    private final static QName _CodigoCalle_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "codigoCalle");
    private final static QName _MotivoLlamado_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "motivoLlamado");
    private final static QName _ModoDeOperacion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "modoDeOperacion");
    private final static QName _Manzana_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "manzana");
    private final static QName _ViolacionDeCodigoDown_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "violacionDeCodigoDown");
    private final static QName _Ac1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ac1");
    private final static QName _Arriba_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "arriba");
    private final static QName _TargetAddress_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "targetAddress");
    private final static QName _MotivoProblema_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "motivoProblema");
    private final static QName _Puerto_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "puerto");
    private final static QName _OutputMessage_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "outputMessage");
    private final static QName _ConversationID_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "conversationID");
    private final static QName _IdLoteFact_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "id_lote_fact");
    private final static QName _CategoriaCliente_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "categoriaCliente");
    private final static QName _AnyXMLElement_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "AnyXMLElement");
    private final static QName _VelocidadADSLMedido_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "velocidadADSLMedido");
    private final static QName _UfUsuario_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ufUsuario");
    private final static QName _StartTime_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "startTime");
    private final static QName _Operation_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "operation");
    private final static QName _Central_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "central");
    private final static QName _ReclamoMarcaVip_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "reclamoMarcaVip");
    private final static QName _ReservadosVarios_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "reservadosVarios");
    private final static QName _CodigoCierre_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "codigoCierre");
    private final static QName _ServiceProviderCode_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "serviceProviderCode");
    private final static QName _EstadoCable_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "estadoCable");
    private final static QName _NroSolicitudPendienteSur_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nroSolicitudPendienteSur");
    private final static QName _MotivoRecupero_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "motivoRecupero");
    private final static QName _ComentarioCD_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "comentarioCD");
    private final static QName _Libres_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "libres");
    private final static QName _ConfirmadosDaniados_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "confirmadosDaniados");
    private final static QName _CablePredecesor_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "cablePredecesor");
    private final static QName _CentralNoAtendida_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "centralNoAtendida");
    private final static QName _Identificador_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "identificador");
    private final static QName _NumeroRegistroFalla_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "numeroRegistroFalla");
    private final static QName _Destino_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "destino");
    private final static QName _Hora_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "hora");
    private final static QName _TonoTerminal_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tonoTerminal");
    private final static QName _CoordenadaPredecesor_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "coordenadaPredecesor");
    private final static QName _Ct1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ct1");
    private final static QName _Ct2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ct2");
    private final static QName _TransmisionCeldasDown_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "transmisionCeldasDown");
    private final static QName _ComentarioInstalacion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "comentarioInstalacion");
    private final static QName _Cable_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "cable");
    private final static QName _RelacionSenalRuidoUp_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "relacionSenalRuidoUp");
    private final static QName _IdLoteAfip_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "id_lote_afip");
    private final static QName _Marca_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "marca");
    private final static QName _TipoPM_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoPM");
    private final static QName _CategoriaLinea_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "categoriaLinea");
    private final static QName _CategoriaTerminalDispersion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "categoriaTerminalDispersion");
    private final static QName _Par_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "par");
    private final static QName _Categoria_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "categoria");
    private final static QName _HistoricoPrueba_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "historicoPrueba");
    private final static QName _Cierre_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "cierre");
    private final static QName _MessageID_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "messageID");
    private final static QName _AgenciaGestionaria_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "agenciaGestionaria");
    private final static QName _Lc2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "lc2");
    private final static QName _Nescab_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nescab");
    private final static QName _Lc1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "lc1");
    private final static QName _FechaHora_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHora");
    private final static QName _EquipoNivel2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "equipoNivel2");
    private final static QName _Escalera_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "escalera");
    private final static QName _Result_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "result");
    private final static QName _EquipoNivel1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "equipoNivel1");
    private final static QName _EquipoNivel0_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "equipoNivel0");
    private final static QName _Utilizador_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "utilizador");
    private final static QName _FechaHoraSuspension_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraSuspension");
    private final static QName _VelocidadLineaConfiguradaDown_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "velocidadLineaConfiguradaDown");
    private final static QName _NombreCalleElementoRed_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nombreCalleElementoRed");
    private final static QName _Dc2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dc2");
    private final static QName _Dc1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dc1");
    private final static QName _ParentServiceCode_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "parentServiceCode");
    private final static QName _ParentMessageId_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "parentMessageId");
    private final static QName _Sign_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sign");
    private final static QName _FEcuit_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "FEcuit");
    private final static QName _Dc5_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dc5");
    private final static QName _Nzrae_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nzrae");
    private final static QName _Dc4_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dc4");
    private final static QName _Dc3_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dc3");
    private final static QName _FiguraEnGuia_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "figuraEnGuia");
    private final static QName _Nzrab_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nzrab");
    private final static QName _Entidad_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "entidad");
    private final static QName _Nse_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nse");
    private final static QName _Nzvab_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nzvab");
    private final static QName _Coordenada_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "coordenada");
    private final static QName _TipoDomicilio_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoDomicilio");
    private final static QName _NumeroReferenciaPM_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "numeroReferenciaPM");
    private final static QName _TipoPrueba_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoPrueba");
    private final static QName _Nzvae_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nzvae");
    private final static QName _Text_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "text");
    private final static QName _Nzrav_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nzrav");
    private final static QName _CapacidadMaximaParDown_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "capacidadMaximaParDown");
    private final static QName _RelacionSenalRuidoDown_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "relacionSenalRuidoDown");
    private final static QName _VacantesSecundarios_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "vacantesSecundarios");
    private final static QName _AturVendorId_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "aturVendorId");
    private final static QName _Prioridad_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "prioridad");
    private final static QName _ReclamoTipoRetencion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "reclamoTipoRetencion");
    private final static QName _TotalConexiones_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "totalConexiones");
    private final static QName _RegistradosDaniados_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "registradosDaniados");
    private final static QName _Description_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "description");
    private final static QName _Cantidad_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "cantidad");
    private final static QName _TotalConexUtilizadas_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "totalConexUtilizadas");
    private final static QName _MargenSenalRuidoObjetivoUp_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "margenSenalRuidoObjetivoUp");
    private final static QName _Nzrbe_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nzrbe");
    private final static QName _TipoSolicitud_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoSolicitud");
    private final static QName _FechaCumplLinea_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaCumplLinea");
    private final static QName _Nzrba_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nzrba");
    private final static QName _FechaHoraMaxima_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraMaxima");
    private final static QName _ReclamoOrigenGirafe_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "reclamoOrigenGirafe");
    private final static QName _TipoSolicitudRelacionada_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoSolicitudRelacionada");
    private final static QName _FechaHoraHasta_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraHasta");
    private final static QName _IdTerminalDispersion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "idTerminalDispersion");
    private final static QName _Ntsac5_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ntsac5");
    private final static QName _Ntsac6_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ntsac6");
    private final static QName _LoteFact_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "loteFact");
    private final static QName _Ntsac3_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ntsac3");
    private final static QName _Ntsac4_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ntsac4");
    private final static QName _ComentarioRegHistIntervencionesAnt_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ComentarioRegHistIntervencionesAnt");
    private final static QName _Ntsac1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ntsac1");
    private final static QName _AcumuladoPendienteCliente_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "acumuladoPendienteCliente");
    private final static QName _Nzvbe_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nzvbe");
    private final static QName _Apellido_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "apellido");
    private final static QName _Vertical_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "vertical");
    private final static QName _Nzrbv_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nzrbv");
    private final static QName _Ntsac2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ntsac2");
    private final static QName _NumeroSerie_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "numeroSerie");
    private final static QName _UsuarioPrueba_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "usuarioPrueba");
    private final static QName _SupplierErrorExplanation_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "supplierErrorExplanation");
    private final static QName _VelocidadLineaRealUp_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "velocidadLineaRealUp");
    private final static QName _TipoMI_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoMI");
    private final static QName _FechaCumplCabinaTP_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaCumplCabinaTP");
    private final static QName _FechaAltaLinea_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaAltaLinea");
    private final static QName _Observaciones_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "observaciones");
    private final static QName _TipoCita_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoCita");
    private final static QName _Sk2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk2");
    private final static QName _Sk3_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk3");
    private final static QName _Sk1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk1");
    private final static QName _IdCbte_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "id_cbte");
    private final static QName _Sk6_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk6");
    private final static QName _Sk7_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk7");
    private final static QName _Sk4_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk4");
    private final static QName _Sk5_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk5");
    private final static QName _Rg3_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "rg3");
    private final static QName _ConAdsl_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "conAdsl");
    private final static QName _Rg1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "rg1");
    private final static QName _Origen_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "origen");
    private final static QName _Rg2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "rg2");
    private final static QName _ConnectionFactoryJndiName_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "connectionFactoryJndiName");
    private final static QName _ReservaSucesor_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "reservaSucesor");
    private final static QName _CantidadIntentos_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "cantidadIntentos");
    private final static QName _Exists_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "exists");
    private final static QName _FechaHoraCierre_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraCierre");
    private final static QName _CodigoDemora_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "codigoDemora");
    private final static QName _Zona_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "zona");
    private final static QName _MarcaVIP_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "marcaVIP");
    private final static QName _FormaPago_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "formaPago");
    private final static QName _ReservadosSolicitudes_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "reservadosSolicitudes");
    private final static QName _Tecnologia_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tecnologia");
    private final static QName _Importe_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "importe");
    private final static QName _UnidadesTransmitidasDown_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "UnidadesTransmitidasDown");
    private final static QName _Accion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "accion");
    private final static QName _EquipoId_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "equipoId");
    private final static QName _TipoConstruccion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoConstruccion");
    private final static QName _Token_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "token");
    private final static QName _ManzanaElementoRed_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "manzanaElementoRed");
    private final static QName _Cnc_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "cnc");
    private final static QName _Simcard_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "simcard");
    private final static QName _SistemaOrigen_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sistemaOrigen");
    private final static QName _ReclamoMarcaRetencion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "reclamoMarcaRetencion");
    private final static QName _TrasladorCobro_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "trasladorCobro");
    private final static QName _AlturaElementoRed_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "alturaElementoRed");
    private final static QName _Entrantes_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "entrantes");
    private final static QName _ParHasta_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "parHasta");
    private final static QName _ActionID_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "actionID");
    private final static QName _Precalificacion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "precalificacion");
    private final static QName _GponSuscriberId_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "gponSuscriberId");
    private final static QName _TipoTicketRelacionado_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoTicketRelacionado");
    private final static QName _VacantesPrimarios_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "vacantesPrimarios");
    private final static QName _IpFija_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "ipFija");
    private final static QName _Sk9_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk9");
    private final static QName _Utilidad_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "utilidad");
    private final static QName _Sk8_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "sk8");
    private final static QName _ParesLibres_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "paresLibres");
    private final static QName _Piso_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "piso");
    private final static QName _CabinaAptoNuevo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "cabinaAptoNuevo");
    private final static QName _SummaryCodes_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "summaryCodes");
    private final static QName _TipoLinea_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoLinea");
    private final static QName _ConsumerCode_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "consumerCode");
    private final static QName _DespliegueDeTecnologia_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "despliegueDeTecnologia");
    private final static QName _ComentarioInstCabina_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "comentarioInstCabina");
    private final static QName _Modelo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "modelo");
    private final static QName _FechaCumplRg_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaCumplRg");
    private final static QName _Dr1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dr1");
    private final static QName _GponRed_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "gponRed");
    private final static QName _DslamActualElementoRed_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dslamActualElementoRed");
    private final static QName _Dr3_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dr3");
    private final static QName _Dr2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dr2");
    private final static QName _FechaCierreHistIntervencionesAnt_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "FechaCierreHistIntervencionesAnt");
    private final static QName _Dr5_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dr5");
    private final static QName _Host_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "host");
    private final static QName _Dr4_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dr4");
    private final static QName _NombreEdificio_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nombreEdificio");
    private final static QName _Altura_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "altura");
    private final static QName _Disponible_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "disponible");
    private final static QName _Comentario_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "comentario");
    private final static QName _PerfilDeFrecuencia_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "perfilDeFrecuencia");
    private final static QName _EstadoAdministrativo_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "estadoAdministrativo");
    private final static QName _Profesion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "profesion");
    private final static QName _CodigoProvincia_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "codigoProvincia");
    private final static QName _Bl2_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "bl2");
    private final static QName _ObservacionesSBT_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "observacionesSBT");
    private final static QName _TipoInstalacion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "tipoInstalacion");
    private final static QName _LineNumberID_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "lineNumberID");
    private final static QName _FechaHoraDevolucion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraDevolucion");
    private final static QName _Latitud_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "latitud");
    private final static QName _FechaHoraDesde_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraDesde");
    private final static QName _InputMessage_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "inputMessage");
    private final static QName _Bl1_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "bl1");
    private final static QName _NumeroEmpalme_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "numeroEmpalme");
    private final static QName _VerCode_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "verCode");
    private final static QName _Longitud_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "longitud");
    private final static QName _PerfilVelocidad_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "perfilVelocidad");
    private final static QName _TransmisionCeldasUp_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "transmisionCeldasUp");
    private final static QName _ComentarioInstAparato_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "comentarioInstAparato");
    private final static QName _PotenciaDeTransmisionDown_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "potenciaDeTransmisionDown");
    private final static QName _Cuit_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "cuit");
    private final static QName _DiagnosticoUltimaPruebaActor_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "diagnosticoUltimaPruebaActor");
    private final static QName _FechaHoraAceptacion_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "fechaHoraAceptacion");
    private final static QName _Dominio_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "dominio");
    private final static QName _ReclamoTipoOrigenGirafe_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "reclamoTipoOrigenGirafe");
    private final static QName _AtenuacionDown_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "atenuacionDown");
    private final static QName _NombreEdificioElementoRed_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "nombreEdificioElementoRed");
    private final static QName _ParDesde_QNAME = new QName("http://www.telecom.com.ar/XMLSchema/bios/common", "parDesde");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AcuerdosPlanesDescuento }
     * 
     */
    public AcuerdosPlanesDescuento createAcuerdosPlanesDescuento() {
        return new AcuerdosPlanesDescuento();
    }

    /**
     * Create an instance of {@link DetalleServicio }
     * 
     */
    public DetalleServicio createDetalleServicio() {
        return new DetalleServicio();
    }

    /**
     * Create an instance of {@link Materiales }
     * 
     */
    public Materiales createMateriales() {
        return new Materiales();
    }

    /**
     * Create an instance of {@link ProductosComerciales }
     * 
     */
    public ProductosComerciales createProductosComerciales() {
        return new ProductosComerciales();
    }

    /**
     * Create an instance of {@link ServiciosAdicionales }
     * 
     */
    public ServiciosAdicionales createServiciosAdicionales() {
        return new ServiciosAdicionales();
    }

    /**
     * Create an instance of {@link Devoluciones }
     * 
     */
    public Devoluciones createDevoluciones() {
        return new Devoluciones();
    }

    /**
     * Create an instance of {@link ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Propiedades }
     * 
     */
    public ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Propiedades createPropiedades() {
        return new ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Propiedades();
    }

    /**
     * Create an instance of {@link ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Producto }
     * 
     */
    public ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Producto createProducto() {
        return new ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Producto();
    }

    /**
     * Create an instance of {@link Vinculos }
     * 
     */
    public Vinculos createVinculos() {
        return new Vinculos();
    }

    /**
     * Create an instance of {@link ServiciosSuplementarios }
     * 
     */
    public ServiciosSuplementarios createServiciosSuplementarios() {
        return new ServiciosSuplementarios();
    }

    /**
     * Create an instance of {@link Equipos }
     * 
     */
    public Equipos createEquipos() {
        return new Equipos();
    }

    /**
     * Create an instance of {@link DatosComercialesParque }
     * 
     */
    public DatosComercialesParque createDatosComercialesParque() {
        return new DatosComercialesParque();
    }

    /**
     * Create an instance of {@link DomiciliosFinca }
     * 
     */
    public DomiciliosFinca createDomiciliosFinca() {
        return new DomiciliosFinca();
    }

    /**
     * Create an instance of {@link Movimientos }
     * 
     */
    public Movimientos createMovimientos() {
        return new Movimientos();
    }

    /**
     * Create an instance of {@link Aparatos }
     * 
     */
    public Aparatos createAparatos() {
        return new Aparatos();
    }

    /**
     * Create an instance of {@link DetallesPlanDescuento }
     * 
     */
    public DetallesPlanDescuento createDetallesPlanDescuento() {
        return new DetallesPlanDescuento();
    }

    /**
     * Create an instance of {@link DomiciliosFincaFijo }
     * 
     */
    public DomiciliosFincaFijo createDomiciliosFincaFijo() {
        return new DomiciliosFincaFijo();
    }

    /**
     * Create an instance of {@link PromocionesComerciales }
     * 
     */
    public PromocionesComerciales createPromocionesComerciales() {
        return new PromocionesComerciales();
    }

    /**
     * Create an instance of {@link Secciones }
     * 
     */
    public Secciones createSecciones() {
        return new Secciones();
    }

    /**
     * Create an instance of {@link Domicilios }
     * 
     */
    public Domicilios createDomicilios() {
        return new Domicilios();
    }

    /**
     * Create an instance of {@link CodigosConceptos }
     * 
     */
    public CodigosConceptos createCodigosConceptos() {
        return new CodigosConceptos();
    }

    /**
     * Create an instance of {@link DomiciliosFinca.DomicilioFinca }
     * 
     */
    public DomiciliosFinca.DomicilioFinca createDomiciliosFincaDomicilioFinca() {
        return new DomiciliosFinca.DomicilioFinca();
    }

    /**
     * Create an instance of {@link DomiciliosFinca.DomicilioFinca.Propiedades }
     * 
     */
    public DomiciliosFinca.DomicilioFinca.Propiedades createDomiciliosFincaDomicilioFincaPropiedades() {
        return new DomiciliosFinca.DomicilioFinca.Propiedades();
    }

    /**
     * Create an instance of {@link DatosComercialesParque.Rehabilitaciones }
     * 
     */
    public DatosComercialesParque.Rehabilitaciones createDatosComercialesParqueRehabilitaciones() {
        return new DatosComercialesParque.Rehabilitaciones();
    }

    /**
     * Create an instance of {@link DatosComercialesParque.Incomunicaciones }
     * 
     */
    public DatosComercialesParque.Incomunicaciones createDatosComercialesParqueIncomunicaciones() {
        return new DatosComercialesParque.Incomunicaciones();
    }

    /**
     * Create an instance of {@link Vinculos.Vinculo }
     * 
     */
    public Vinculos.Vinculo createVinculosVinculo() {
        return new Vinculos.Vinculo();
    }

    /**
     * Create an instance of {@link Vinculos.Vinculo.Abajo }
     * 
     */
    public Vinculos.Vinculo.Abajo createVinculosVinculoAbajo() {
        return new Vinculos.Vinculo.Abajo();
    }

    /**
     * Create an instance of {@link Vinculos.Vinculo.Arriba }
     * 
     */
    public Vinculos.Vinculo.Arriba createVinculosVinculoArriba() {
        return new Vinculos.Vinculo.Arriba();
    }

    /**
     * Create an instance of {@link ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Producto.Servicios }
     * 
     */
    public ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Producto.Servicios createProductoServicios() {
        return new ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Producto.Servicios();
    }

    /**
     * Create an instance of {@link DetalleServicio.Propiedades }
     * 
     */
    public DetalleServicio.Propiedades createDetalleServicioPropiedades() {
        return new DetalleServicio.Propiedades();
    }

    /**
     * Create an instance of {@link AcuerdosPlanesDescuento.AcuerdoPlanDescuento }
     * 
     */
    public AcuerdosPlanesDescuento.AcuerdoPlanDescuento createAcuerdosPlanesDescuentoAcuerdoPlanDescuento() {
        return new AcuerdosPlanesDescuento.AcuerdoPlanDescuento();
    }

    /**
     * Create an instance of {@link ClienteCRM }
     * 
     */
    public ClienteCRM createClienteCRM() {
        return new ClienteCRM();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta }
     * 
     */
    public ClienteCRM.Cuenta createClienteCRMCuenta() {
        return new ClienteCRM.Cuenta();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta.Actividades }
     * 
     */
    public ClienteCRM.Cuenta.Actividades createClienteCRMCuentaActividades() {
        return new ClienteCRM.Cuenta.Actividades();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta.JurisdiccionesIIBB }
     * 
     */
    public ClienteCRM.Cuenta.JurisdiccionesIIBB createClienteCRMCuentaJurisdiccionesIIBB() {
        return new ClienteCRM.Cuenta.JurisdiccionesIIBB();
    }

    /**
     * Create an instance of {@link Cliente }
     * 
     */
    public Cliente createCliente() {
        return new Cliente();
    }

    /**
     * Create an instance of {@link Documento }
     * 
     */
    public Documento createDocumento() {
        return new Documento();
    }

    /**
     * Create an instance of {@link FELoteFACTType }
     * 
     */
    public FELoteFACTType createFELoteFACTType() {
        return new FELoteFACTType();
    }

    /**
     * Create an instance of {@link Materiales.Material }
     * 
     */
    public Materiales.Material createMaterialesMaterial() {
        return new Materiales.Material();
    }

    /**
     * Create an instance of {@link Usuario }
     * 
     */
    public Usuario createUsuario() {
        return new Usuario();
    }

    /**
     * Create an instance of {@link FacturaDeudaActual }
     * 
     */
    public FacturaDeudaActual createFacturaDeudaActual() {
        return new FacturaDeudaActual();
    }

    /**
     * Create an instance of {@link ProductosComerciales.Producto }
     * 
     */
    public ProductosComerciales.Producto createProductosComercialesProducto() {
        return new ProductosComerciales.Producto();
    }

    /**
     * Create an instance of {@link ServiciosAdicionales.ServicioAdicional }
     * 
     */
    public ServiciosAdicionales.ServicioAdicional createServiciosAdicionalesServicioAdicional() {
        return new ServiciosAdicionales.ServicioAdicional();
    }

    /**
     * Create an instance of {@link Devoluciones.Devolucion }
     * 
     */
    public Devoluciones.Devolucion createDevolucionesDevolucion() {
        return new Devoluciones.Devolucion();
    }

    /**
     * Create an instance of {@link Factura }
     * 
     */
    public Factura createFactura() {
        return new Factura();
    }

    /**
     * Create an instance of {@link ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Propiedades.Propiedad }
     * 
     */
    public ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Propiedades.Propiedad createPropiedadesPropiedad() {
        return new ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Propiedades.Propiedad();
    }

    /**
     * Create an instance of {@link Cumplimiento }
     * 
     */
    public Cumplimiento createCumplimiento() {
        return new Cumplimiento();
    }

    /**
     * Create an instance of {@link ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Servicio }
     * 
     */
    public ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Servicio createServicio() {
        return new ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Servicio();
    }

    /**
     * Create an instance of {@link CategoriaAbono }
     * 
     */
    public CategoriaAbono createCategoriaAbono() {
        return new CategoriaAbono();
    }

    /**
     * Create an instance of {@link ServiciosSuplementarios.ServicioSuplementario }
     * 
     */
    public ServiciosSuplementarios.ServicioSuplementario createServiciosSuplementariosServicioSuplementario() {
        return new ServiciosSuplementarios.ServicioSuplementario();
    }

    /**
     * Create an instance of {@link Equipos.Equipo }
     * 
     */
    public Equipos.Equipo createEquiposEquipo() {
        return new Equipos.Equipo();
    }

    /**
     * Create an instance of {@link RegistroComercio }
     * 
     */
    public RegistroComercio createRegistroComercio() {
        return new RegistroComercio();
    }

    /**
     * Create an instance of {@link Movimientos.Movimiento }
     * 
     */
    public Movimientos.Movimiento createMovimientosMovimiento() {
        return new Movimientos.Movimiento();
    }

    /**
     * Create an instance of {@link Instalacion }
     * 
     */
    public Instalacion createInstalacion() {
        return new Instalacion();
    }

    /**
     * Create an instance of {@link JmsQueueConfig }
     * 
     */
    public JmsQueueConfig createJmsQueueConfig() {
        return new JmsQueueConfig();
    }

    /**
     * Create an instance of {@link Aparatos.Aparato }
     * 
     */
    public Aparatos.Aparato createAparatosAparato() {
        return new Aparatos.Aparato();
    }

    /**
     * Create an instance of {@link Contacto }
     * 
     */
    public Contacto createContacto() {
        return new Contacto();
    }

    /**
     * Create an instance of {@link Modem }
     * 
     */
    public Modem createModem() {
        return new Modem();
    }

    /**
     * Create an instance of {@link DetallesPlanDescuento.DetallePlanDescuento }
     * 
     */
    public DetallesPlanDescuento.DetallePlanDescuento createDetallesPlanDescuentoDetallePlanDescuento() {
        return new DetallesPlanDescuento.DetallePlanDescuento();
    }

    /**
     * Create an instance of {@link DatosParque }
     * 
     */
    public DatosParque createDatosParque() {
        return new DatosParque();
    }

    /**
     * Create an instance of {@link DomiciliosFincaFijo.DomicilioFincaFijo }
     * 
     */
    public DomiciliosFincaFijo.DomicilioFincaFijo createDomiciliosFincaFijoDomicilioFincaFijo() {
        return new DomiciliosFincaFijo.DomicilioFincaFijo();
    }

    /**
     * Create an instance of {@link DatosRed }
     * 
     */
    public DatosRed createDatosRed() {
        return new DatosRed();
    }

    /**
     * Create an instance of {@link Transaction }
     * 
     */
    public Transaction createTransaction() {
        return new Transaction();
    }

    /**
     * Create an instance of {@link PromocionesComerciales.Promo }
     * 
     */
    public PromocionesComerciales.Promo createPromocionesComercialesPromo() {
        return new PromocionesComerciales.Promo();
    }

    /**
     * Create an instance of {@link Secciones.Seccion }
     * 
     */
    public Secciones.Seccion createSeccionesSeccion() {
        return new Secciones.Seccion();
    }

    /**
     * Create an instance of {@link Domicilios.Domicilio }
     * 
     */
    public Domicilios.Domicilio createDomiciliosDomicilio() {
        return new Domicilios.Domicilio();
    }

    /**
     * Create an instance of {@link CodigosConceptos.CodigoConcepto }
     * 
     */
    public CodigosConceptos.CodigoConcepto createCodigosConceptosCodigoConcepto() {
        return new CodigosConceptos.CodigoConcepto();
    }

    /**
     * Create an instance of {@link FEcredentialsType }
     * 
     */
    public FEcredentialsType createFEcredentialsType() {
        return new FEcredentialsType();
    }

    /**
     * Create an instance of {@link DomiciliosFinca.DomicilioFinca.Provincia }
     * 
     */
    public DomiciliosFinca.DomicilioFinca.Provincia createDomiciliosFincaDomicilioFincaProvincia() {
        return new DomiciliosFinca.DomicilioFinca.Provincia();
    }

    /**
     * Create an instance of {@link DomiciliosFinca.DomicilioFinca.Localidad }
     * 
     */
    public DomiciliosFinca.DomicilioFinca.Localidad createDomiciliosFincaDomicilioFincaLocalidad() {
        return new DomiciliosFinca.DomicilioFinca.Localidad();
    }

    /**
     * Create an instance of {@link DomiciliosFinca.DomicilioFinca.Zona }
     * 
     */
    public DomiciliosFinca.DomicilioFinca.Zona createDomiciliosFincaDomicilioFincaZona() {
        return new DomiciliosFinca.DomicilioFinca.Zona();
    }

    /**
     * Create an instance of {@link DomiciliosFinca.DomicilioFinca.Propiedades.Propiedad }
     * 
     */
    public DomiciliosFinca.DomicilioFinca.Propiedades.Propiedad createDomiciliosFincaDomicilioFincaPropiedadesPropiedad() {
        return new DomiciliosFinca.DomicilioFinca.Propiedades.Propiedad();
    }

    /**
     * Create an instance of {@link DatosComercialesParque.Rehabilitaciones.Rehabilitacion }
     * 
     */
    public DatosComercialesParque.Rehabilitaciones.Rehabilitacion createDatosComercialesParqueRehabilitacionesRehabilitacion() {
        return new DatosComercialesParque.Rehabilitaciones.Rehabilitacion();
    }

    /**
     * Create an instance of {@link DatosComercialesParque.Incomunicaciones.Incomunicacion }
     * 
     */
    public DatosComercialesParque.Incomunicaciones.Incomunicacion createDatosComercialesParqueIncomunicacionesIncomunicacion() {
        return new DatosComercialesParque.Incomunicaciones.Incomunicacion();
    }

    /**
     * Create an instance of {@link Vinculos.Vinculo.Abajo.Extremo }
     * 
     */
    public Vinculos.Vinculo.Abajo.Extremo createVinculosVinculoAbajoExtremo() {
        return new Vinculos.Vinculo.Abajo.Extremo();
    }

    /**
     * Create an instance of {@link Vinculos.Vinculo.Arriba.Extremo }
     * 
     */
    public Vinculos.Vinculo.Arriba.Extremo createVinculosVinculoArribaExtremo() {
        return new Vinculos.Vinculo.Arriba.Extremo();
    }

    /**
     * Create an instance of {@link ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Producto.Servicios.Servicio }
     * 
     */
    public ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Producto.Servicios.Servicio createProductoServiciosServicio() {
        return new ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.commons.Producto.Servicios.Servicio();
    }

    /**
     * Create an instance of {@link DetalleServicio.Propiedades.Propiedad }
     * 
     */
    public DetalleServicio.Propiedades.Propiedad createDetalleServicioPropiedadesPropiedad() {
        return new DetalleServicio.Propiedades.Propiedad();
    }

    /**
     * Create an instance of {@link AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Acuerdo }
     * 
     */
    public AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Acuerdo createAcuerdosPlanesDescuentoAcuerdoPlanDescuentoAcuerdo() {
        return new AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Acuerdo();
    }

    /**
     * Create an instance of {@link AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Plan }
     * 
     */
    public AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Plan createAcuerdosPlanesDescuentoAcuerdoPlanDescuentoPlan() {
        return new AcuerdosPlanesDescuento.AcuerdoPlanDescuento.Plan();
    }

    /**
     * Create an instance of {@link ClienteCRM.Perfil }
     * 
     */
    public ClienteCRM.Perfil createClienteCRMPerfil() {
        return new ClienteCRM.Perfil();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta.Actividades.Actividad }
     * 
     */
    public ClienteCRM.Cuenta.Actividades.Actividad createClienteCRMCuentaActividadesActividad() {
        return new ClienteCRM.Cuenta.Actividades.Actividad();
    }

    /**
     * Create an instance of {@link ClienteCRM.Cuenta.JurisdiccionesIIBB.JurisdiccionIIBB }
     * 
     */
    public ClienteCRM.Cuenta.JurisdiccionesIIBB.JurisdiccionIIBB createClienteCRMCuentaJurisdiccionesIIBBJurisdiccionIIBB() {
        return new ClienteCRM.Cuenta.JurisdiccionesIIBB.JurisdiccionIIBB();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "pin")
    public JAXBElement<String> createPin(String value) {
        return new JAXBElement<String>(_Pin_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipo")
    public JAXBElement<String> createTipo(String value) {
        return new JAXBElement<String>(_Tipo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "codigoLocalidad")
    public JAXBElement<String> createCodigoLocalidad(String value) {
        return new JAXBElement<String>(_CodigoLocalidad_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "searchingKey")
    public JAXBElement<String> createSearchingKey(String value) {
        return new JAXBElement<String>(_SearchingKey_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "userID")
    public JAXBElement<String> createUserID(String value) {
        return new JAXBElement<String>(_UserID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraEnRuta")
    public JAXBElement<String> createFechaHoraEnRuta(String value) {
        return new JAXBElement<String>(_FechaHoraEnRuta_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "estado")
    public JAXBElement<String> createEstado(String value) {
        return new JAXBElement<String>(_Estado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "atenuacionUP")
    public JAXBElement<String> createAtenuacionUP(String value) {
        return new JAXBElement<String>(_AtenuacionUP_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraDespacho")
    public JAXBElement<String> createFechaHoraDespacho(String value) {
        return new JAXBElement<String>(_FechaHoraDespacho_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "margenSenalRuidoObjetivoDown")
    public JAXBElement<String> createMargenSenalRuidoObjetivoDown(String value) {
        return new JAXBElement<String>(_MargenSenalRuidoObjetivoDown_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "localizacion")
    public JAXBElement<String> createLocalizacion(String value) {
        return new JAXBElement<String>(_Localizacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaCumplCx")
    public JAXBElement<String> createFechaCumplCx(String value) {
        return new JAXBElement<String>(_FechaCumplCx_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ack")
    public JAXBElement<Object> createAck(Object value) {
        return new JAXBElement<Object>(_Ack_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "potenciaDeTransmisionUp")
    public JAXBElement<String> createPotenciaDeTransmisionUp(String value) {
        return new JAXBElement<String>(_PotenciaDeTransmisionUp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "identificadorEntidadProveedor")
    public JAXBElement<String> createIdentificadorEntidadProveedor(String value) {
        return new JAXBElement<String>(_IdentificadorEntidadProveedor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "normaOperativa")
    public JAXBElement<String> createNormaOperativa(String value) {
        return new JAXBElement<String>(_NormaOperativa_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "providerCode")
    public JAXBElement<String> createProviderCode(String value) {
        return new JAXBElement<String>(_ProviderCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoVinculo")
    public JAXBElement<String> createTipoVinculo(String value) {
        return new JAXBElement<String>(_TipoVinculo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nsiCabina")
    public JAXBElement<String> createNsiCabina(String value) {
        return new JAXBElement<String>(_NsiCabina_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "comentarioCita")
    public JAXBElement<String> createComentarioCita(String value) {
        return new JAXBElement<String>(_ComentarioCita_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "bastidor")
    public JAXBElement<String> createBastidor(String value) {
        return new JAXBElement<String>(_Bastidor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "usuarioRecomendado")
    public JAXBElement<String> createUsuarioRecomendado(String value) {
        return new JAXBElement<String>(_UsuarioRecomendado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "velocidadADSLTerminal")
    public JAXBElement<String> createVelocidadADSLTerminal(String value) {
        return new JAXBElement<String>(_VelocidadADSLTerminal_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tecnologiaADSL")
    public JAXBElement<String> createTecnologiaADSL(String value) {
        return new JAXBElement<String>(_TecnologiaADSL_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "UnidadesTransmitidasUp")
    public JAXBElement<String> createUnidadesTransmitidasUp(String value) {
        return new JAXBElement<String>(_UnidadesTransmitidasUp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "reclamoFechaIngresoRed")
    public JAXBElement<String> createReclamoFechaIngresoRed(String value) {
        return new JAXBElement<String>(_ReclamoFechaIngresoRed_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaVtoIndicador")
    public JAXBElement<String> createFechaVtoIndicador(String value) {
        return new JAXBElement<String>(_FechaVtoIndicador_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "path")
    public JAXBElement<String> createPath(String value) {
        return new JAXBElement<String>(_Path_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "resultado")
    public JAXBElement<String> createResultado(String value) {
        return new JAXBElement<String>(_Resultado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "perfilCRM")
    public JAXBElement<String> createPerfilCRM(String value) {
        return new JAXBElement<String>(_PerfilCRM_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "numeroSolicitud")
    public JAXBElement<String> createNumeroSolicitud(String value) {
        return new JAXBElement<String>(_NumeroSolicitud_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "af2")
    public JAXBElement<String> createAf2(String value) {
        return new JAXBElement<String>(_Af2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "predecesor")
    public JAXBElement<String> createPredecesor(String value) {
        return new JAXBElement<String>(_Predecesor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "af1")
    public JAXBElement<String> createAf1(String value) {
        return new JAXBElement<String>(_Af1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "state")
    public JAXBElement<String> createState(String value) {
        return new JAXBElement<String>(_State_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nombreCalle")
    public JAXBElement<String> createNombreCalle(String value) {
        return new JAXBElement<String>(_NombreCalle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ocupados")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createOcupados(BigInteger value) {
        return new JAXBElement<BigInteger>(_Ocupados_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "denycode")
    public JAXBElement<String> createDenycode(String value) {
        return new JAXBElement<String>(_Denycode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "solicitudAsociadaPM")
    public JAXBElement<String> createSolicitudAsociadaPM(String value) {
        return new JAXBElement<String>(_SolicitudAsociadaPM_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "codigoServicioIntegral")
    public JAXBElement<String> createCodigoServicioIntegral(String value) {
        return new JAXBElement<String>(_CodigoServicioIntegral_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tarea")
    public JAXBElement<String> createTarea(String value) {
        return new JAXBElement<String>(_Tarea_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nroLinea")
    public JAXBElement<String> createNroLinea(String value) {
        return new JAXBElement<String>(_NroLinea_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sendJndiName")
    public JAXBElement<String> createSendJndiName(String value) {
        return new JAXBElement<String>(_SendJndiName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "parPredecesor")
    public JAXBElement<String> createParPredecesor(String value) {
        return new JAXBElement<String>(_ParPredecesor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "vacante")
    public JAXBElement<String> createVacante(String value) {
        return new JAXBElement<String>(_Vacante_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "serviceCode")
    public JAXBElement<String> createServiceCode(String value) {
        return new JAXBElement<String>(_ServiceCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nombreLocalidad")
    public JAXBElement<String> createNombreLocalidad(String value) {
        return new JAXBElement<String>(_NombreLocalidad_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "primerFechaHoraIngresoRed")
    public JAXBElement<String> createPrimerFechaHoraIngresoRed(String value) {
        return new JAXBElement<String>(_PrimerFechaHoraIngresoRed_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "repartidorGeneral")
    public JAXBElement<String> createRepartidorGeneral(String value) {
        return new JAXBElement<String>(_RepartidorGeneral_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "th2")
    public JAXBElement<String> createTh2(String value) {
        return new JAXBElement<String>(_Th2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "th3")
    public JAXBElement<String> createTh3(String value) {
        return new JAXBElement<String>(_Th3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "grupo")
    public JAXBElement<String> createGrupo(String value) {
        return new JAXBElement<String>(_Grupo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "th1")
    public JAXBElement<String> createTh1(String value) {
        return new JAXBElement<String>(_Th1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaReporteSus")
    public JAXBElement<String> createFechaReporteSus(String value) {
        return new JAXBElement<String>(_FechaReporteSus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "codigoOrigen")
    public JAXBElement<String> createCodigoOrigen(String value) {
        return new JAXBElement<String>(_CodigoOrigen_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "scoring")
    public JAXBElement<String> createScoring(String value) {
        return new JAXBElement<String>(_Scoring_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraUltimaPruebaDSL")
    public JAXBElement<String> createFechaHoraUltimaPruebaDSL(String value) {
        return new JAXBElement<String>(_FechaHoraUltimaPruebaDSL_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "totalVinculos")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createTotalVinculos(BigInteger value) {
        return new JAXBElement<BigInteger>(_TotalVinculos_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "codigo")
    public JAXBElement<String> createCodigo(String value) {
        return new JAXBElement<String>(_Codigo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "oficinaGestion")
    public JAXBElement<String> createOficinaGestion(String value) {
        return new JAXBElement<String>(_OficinaGestion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "level")
    public JAXBElement<String> createLevel(String value) {
        return new JAXBElement<String>(_Level_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ln1")
    public JAXBElement<String> createLn1(String value) {
        return new JAXBElement<String>(_Ln1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "unidad")
    public JAXBElement<String> createUnidad(String value) {
        return new JAXBElement<String>(_Unidad_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "capacidadMaximaParUp")
    public JAXBElement<String> createCapacidadMaximaParUp(String value) {
        return new JAXBElement<String>(_CapacidadMaximaParUp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "reclamoMarcaRecomendado")
    public JAXBElement<String> createReclamoMarcaRecomendado(String value) {
        return new JAXBElement<String>(_ReclamoMarcaRecomendado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nivel4")
    public JAXBElement<String> createNivel4(String value) {
        return new JAXBElement<String>(_Nivel4_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "grupoAseguramiento")
    public JAXBElement<String> createGrupoAseguramiento(String value) {
        return new JAXBElement<String>(_GrupoAseguramiento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nivel5")
    public JAXBElement<String> createNivel5(String value) {
        return new JAXBElement<String>(_Nivel5_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "codigoAnomalia")
    public JAXBElement<String> createCodigoAnomalia(String value) {
        return new JAXBElement<String>(_CodigoAnomalia_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "location")
    public JAXBElement<String> createLocation(String value) {
        return new JAXBElement<String>(_Location_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nivel2")
    public JAXBElement<String> createNivel2(String value) {
        return new JAXBElement<String>(_Nivel2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nivel3")
    public JAXBElement<String> createNivel3(String value) {
        return new JAXBElement<String>(_Nivel3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ln3")
    public JAXBElement<String> createLn3(String value) {
        return new JAXBElement<String>(_Ln3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "AnyElement")
    public JAXBElement<String> createAnyElement(String value) {
        return new JAXBElement<String>(_AnyElement_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "proyeccion")
    public JAXBElement<String> createProyeccion(String value) {
        return new JAXBElement<String>(_Proyeccion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ln2")
    public JAXBElement<String> createLn2(String value) {
        return new JAXBElement<String>(_Ln2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "estadoSolicitud")
    public JAXBElement<String> createEstadoSolicitud(String value) {
        return new JAXBElement<String>(_EstadoSolicitud_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "abajo")
    public JAXBElement<String> createAbajo(String value) {
        return new JAXBElement<String>(_Abajo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "endTime")
    public JAXBElement<XMLGregorianCalendar> createEndTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_EndTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nessap")
    public JAXBElement<String> createNessap(String value) {
        return new JAXBElement<String>(_Nessap_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "indisponibles")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createIndisponibles(BigInteger value) {
        return new JAXBElement<BigInteger>(_Indisponibles_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "causa")
    public JAXBElement<String> createCausa(String value) {
        return new JAXBElement<String>(_Causa_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nsiAparato")
    public JAXBElement<String> createNsiAparato(String value) {
        return new JAXBElement<String>(_NsiAparato_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "supplierErrorCode")
    public JAXBElement<String> createSupplierErrorCode(String value) {
        return new JAXBElement<String>(_SupplierErrorCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoSolicitudPendienteSur")
    public JAXBElement<String> createTipoSolicitudPendienteSur(String value) {
        return new JAXBElement<String>(_TipoSolicitudPendienteSur_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "equipo")
    public JAXBElement<String> createEquipo(String value) {
        return new JAXBElement<String>(_Equipo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "usuarioMovilAsignado")
    public JAXBElement<String> createUsuarioMovilAsignado(String value) {
        return new JAXBElement<String>(_UsuarioMovilAsignado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "targetNPI")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createTargetNPI(BigInteger value) {
        return new JAXBElement<BigInteger>(_TargetNPI_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "errorCode")
    public JAXBElement<String> createErrorCode(String value) {
        return new JAXBElement<String>(_ErrorCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraCreacion")
    public JAXBElement<String> createFechaHoraCreacion(String value) {
        return new JAXBElement<String>(_FechaHoraCreacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "extremo")
    public JAXBElement<Object> createExtremo(Object value) {
        return new JAXBElement<Object>(_Extremo_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sumaryCode6")
    public JAXBElement<String> createSumaryCode6(String value) {
        return new JAXBElement<String>(_SumaryCode6_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fecha")
    public JAXBElement<String> createFecha(String value) {
        return new JAXBElement<String>(_Fecha_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ultimoCambioEstadoDelPort")
    public JAXBElement<String> createUltimoCambioEstadoDelPort(String value) {
        return new JAXBElement<String>(_UltimoCambioEstadoDelPort_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "violacionDeCodigoUp")
    public JAXBElement<String> createViolacionDeCodigoUp(String value) {
        return new JAXBElement<String>(_ViolacionDeCodigoUp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sumaryCode2")
    public JAXBElement<String> createSumaryCode2(String value) {
        return new JAXBElement<String>(_SumaryCode2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaPagoDUC")
    public JAXBElement<String> createFechaPagoDUC(String value) {
        return new JAXBElement<String>(_FechaPagoDUC_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "identificadorOT")
    public JAXBElement<String> createIdentificadorOT(String value) {
        return new JAXBElement<String>(_IdentificadorOT_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sumaryCode3")
    public JAXBElement<String> createSumaryCode3(String value) {
        return new JAXBElement<String>(_SumaryCode3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sumaryCode4")
    public JAXBElement<String> createSumaryCode4(String value) {
        return new JAXBElement<String>(_SumaryCode4_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sumaryCode5")
    public JAXBElement<String> createSumaryCode5(String value) {
        return new JAXBElement<String>(_SumaryCode5_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "velocidadLineaRealDown")
    public JAXBElement<String> createVelocidadLineaRealDown(String value) {
        return new JAXBElement<String>(_VelocidadLineaRealDown_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sumaryCode1")
    public JAXBElement<String> createSumaryCode1(String value) {
        return new JAXBElement<String>(_SumaryCode1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "topologia")
    public JAXBElement<String> createTopologia(String value) {
        return new JAXBElement<String>(_Topologia_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "estadoLinea")
    public JAXBElement<String> createEstadoLinea(String value) {
        return new JAXBElement<String>(_EstadoLinea_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "telefono")
    public JAXBElement<String> createTelefono(String value) {
        return new JAXBElement<String>(_Telefono_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "descripcion")
    public JAXBElement<String> createDescripcion(String value) {
        return new JAXBElement<String>(_Descripcion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "condicionIVA")
    public JAXBElement<String> createCondicionIVA(String value) {
        return new JAXBElement<String>(_CondicionIVA_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nzgnse")
    public JAXBElement<String> createNzgnse(String value) {
        return new JAXBElement<String>(_Nzgnse_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sintoma")
    public JAXBElement<String> createSintoma(String value) {
        return new JAXBElement<String>(_Sintoma_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nombre")
    public JAXBElement<String> createNombre(String value) {
        return new JAXBElement<String>(_Nombre_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaInstalacionTemporaria")
    public JAXBElement<String> createFechaInstalacionTemporaria(String value) {
        return new JAXBElement<String>(_FechaInstalacionTemporaria_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "velocidadADSLZona")
    public JAXBElement<String> createVelocidadADSLZona(String value) {
        return new JAXBElement<String>(_VelocidadADSLZona_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "equipmentSupplier")
    public JAXBElement<String> createEquipmentSupplier(String value) {
        return new JAXBElement<String>(_EquipmentSupplier_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraCancelacion")
    public JAXBElement<String> createFechaHoraCancelacion(String value) {
        return new JAXBElement<String>(_FechaHoraCancelacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "centralConmutacion")
    public JAXBElement<String> createCentralConmutacion(String value) {
        return new JAXBElement<String>(_CentralConmutacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "targetTON")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createTargetTON(BigInteger value) {
        return new JAXBElement<BigInteger>(_TargetTON_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk10")
    public JAXBElement<String> createSk10(String value) {
        return new JAXBElement<String>(_Sk10_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "solicitudProvision")
    public JAXBElement<String> createSolicitudProvision(String value) {
        return new JAXBElement<String>(_SolicitudProvision_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "transactionId")
    public JAXBElement<String> createTransactionId(String value) {
        return new JAXBElement<String>(_TransactionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk13")
    public JAXBElement<String> createSk13(String value) {
        return new JAXBElement<String>(_Sk13_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraEnSitio")
    public JAXBElement<String> createFechaHoraEnSitio(String value) {
        return new JAXBElement<String>(_FechaHoraEnSitio_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk11")
    public JAXBElement<String> createSk11(String value) {
        return new JAXBElement<String>(_Sk11_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk12")
    public JAXBElement<String> createSk12(String value) {
        return new JAXBElement<String>(_Sk12_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "problema")
    public JAXBElement<String> createProblema(String value) {
        return new JAXBElement<String>(_Problema_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaCumplISP")
    public JAXBElement<String> createFechaCumplISP(String value) {
        return new JAXBElement<String>(_FechaCumplISP_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "supplierErrorDescription")
    public JAXBElement<String> createSupplierErrorDescription(String value) {
        return new JAXBElement<String>(_SupplierErrorDescription_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaCumplAptoTP")
    public JAXBElement<String> createFechaCumplAptoTP(String value) {
        return new JAXBElement<String>(_FechaCumplAptoTP_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ac6")
    public JAXBElement<String> createAc6(String value) {
        return new JAXBElement<String>(_Ac6_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "estadoOperativo")
    public JAXBElement<String> createEstadoOperativo(String value) {
        return new JAXBElement<String>(_EstadoOperativo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ac5")
    public JAXBElement<String> createAc5(String value) {
        return new JAXBElement<String>(_Ac5_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ac4")
    public JAXBElement<String> createAc4(String value) {
        return new JAXBElement<String>(_Ac4_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ac3")
    public JAXBElement<String> createAc3(String value) {
        return new JAXBElement<String>(_Ac3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "velocidadLineaConfiguradaUp")
    public JAXBElement<String> createVelocidadLineaConfiguradaUp(String value) {
        return new JAXBElement<String>(_VelocidadLineaConfiguradaUp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ac2")
    public JAXBElement<String> createAc2(String value) {
        return new JAXBElement<String>(_Ac2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "comment")
    public JAXBElement<String> createComment(String value) {
        return new JAXBElement<String>(_Comment_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tratamiento")
    public JAXBElement<String> createTratamiento(String value) {
        return new JAXBElement<String>(_Tratamiento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ebos")
    public JAXBElement<String> createEbos(String value) {
        return new JAXBElement<String>(_Ebos_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "codigoCalle")
    public JAXBElement<String> createCodigoCalle(String value) {
        return new JAXBElement<String>(_CodigoCalle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "motivoLlamado")
    public JAXBElement<String> createMotivoLlamado(String value) {
        return new JAXBElement<String>(_MotivoLlamado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "modoDeOperacion")
    public JAXBElement<String> createModoDeOperacion(String value) {
        return new JAXBElement<String>(_ModoDeOperacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "manzana")
    public JAXBElement<String> createManzana(String value) {
        return new JAXBElement<String>(_Manzana_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "violacionDeCodigoDown")
    public JAXBElement<String> createViolacionDeCodigoDown(String value) {
        return new JAXBElement<String>(_ViolacionDeCodigoDown_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ac1")
    public JAXBElement<String> createAc1(String value) {
        return new JAXBElement<String>(_Ac1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "arriba")
    public JAXBElement<String> createArriba(String value) {
        return new JAXBElement<String>(_Arriba_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "targetAddress")
    public JAXBElement<String> createTargetAddress(String value) {
        return new JAXBElement<String>(_TargetAddress_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "motivoProblema")
    public JAXBElement<String> createMotivoProblema(String value) {
        return new JAXBElement<String>(_MotivoProblema_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "puerto")
    public JAXBElement<String> createPuerto(String value) {
        return new JAXBElement<String>(_Puerto_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "outputMessage")
    public JAXBElement<String> createOutputMessage(String value) {
        return new JAXBElement<String>(_OutputMessage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "conversationID")
    public JAXBElement<String> createConversationID(String value) {
        return new JAXBElement<String>(_ConversationID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "id_lote_fact")
    public JAXBElement<Long> createIdLoteFact(Long value) {
        return new JAXBElement<Long>(_IdLoteFact_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "categoriaCliente")
    public JAXBElement<String> createCategoriaCliente(String value) {
        return new JAXBElement<String>(_CategoriaCliente_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "AnyXMLElement")
    public JAXBElement<Object> createAnyXMLElement(Object value) {
        return new JAXBElement<Object>(_AnyXMLElement_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "velocidadADSLMedido")
    public JAXBElement<String> createVelocidadADSLMedido(String value) {
        return new JAXBElement<String>(_VelocidadADSLMedido_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ufUsuario")
    public JAXBElement<String> createUfUsuario(String value) {
        return new JAXBElement<String>(_UfUsuario_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "startTime")
    public JAXBElement<XMLGregorianCalendar> createStartTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StartTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "operation")
    public JAXBElement<String> createOperation(String value) {
        return new JAXBElement<String>(_Operation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "central")
    public JAXBElement<String> createCentral(String value) {
        return new JAXBElement<String>(_Central_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "reclamoMarcaVip")
    public JAXBElement<String> createReclamoMarcaVip(String value) {
        return new JAXBElement<String>(_ReclamoMarcaVip_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "reservadosVarios")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createReservadosVarios(BigInteger value) {
        return new JAXBElement<BigInteger>(_ReservadosVarios_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "codigoCierre")
    public JAXBElement<String> createCodigoCierre(String value) {
        return new JAXBElement<String>(_CodigoCierre_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "serviceProviderCode")
    public JAXBElement<String> createServiceProviderCode(String value) {
        return new JAXBElement<String>(_ServiceProviderCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "estadoCable")
    public JAXBElement<String> createEstadoCable(String value) {
        return new JAXBElement<String>(_EstadoCable_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nroSolicitudPendienteSur")
    public JAXBElement<String> createNroSolicitudPendienteSur(String value) {
        return new JAXBElement<String>(_NroSolicitudPendienteSur_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "motivoRecupero")
    public JAXBElement<String> createMotivoRecupero(String value) {
        return new JAXBElement<String>(_MotivoRecupero_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "comentarioCD")
    public JAXBElement<String> createComentarioCD(String value) {
        return new JAXBElement<String>(_ComentarioCD_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "libres")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createLibres(BigInteger value) {
        return new JAXBElement<BigInteger>(_Libres_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "confirmadosDaniados")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createConfirmadosDaniados(BigInteger value) {
        return new JAXBElement<BigInteger>(_ConfirmadosDaniados_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "cablePredecesor")
    public JAXBElement<String> createCablePredecesor(String value) {
        return new JAXBElement<String>(_CablePredecesor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "centralNoAtendida")
    public JAXBElement<String> createCentralNoAtendida(String value) {
        return new JAXBElement<String>(_CentralNoAtendida_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "identificador")
    public JAXBElement<String> createIdentificador(String value) {
        return new JAXBElement<String>(_Identificador_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "numeroRegistroFalla")
    public JAXBElement<String> createNumeroRegistroFalla(String value) {
        return new JAXBElement<String>(_NumeroRegistroFalla_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "destino")
    public JAXBElement<String> createDestino(String value) {
        return new JAXBElement<String>(_Destino_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "hora")
    public JAXBElement<String> createHora(String value) {
        return new JAXBElement<String>(_Hora_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tonoTerminal")
    public JAXBElement<String> createTonoTerminal(String value) {
        return new JAXBElement<String>(_TonoTerminal_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "coordenadaPredecesor")
    public JAXBElement<String> createCoordenadaPredecesor(String value) {
        return new JAXBElement<String>(_CoordenadaPredecesor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ct1")
    public JAXBElement<String> createCt1(String value) {
        return new JAXBElement<String>(_Ct1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ct2")
    public JAXBElement<String> createCt2(String value) {
        return new JAXBElement<String>(_Ct2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "transmisionCeldasDown")
    public JAXBElement<String> createTransmisionCeldasDown(String value) {
        return new JAXBElement<String>(_TransmisionCeldasDown_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "comentarioInstalacion")
    public JAXBElement<String> createComentarioInstalacion(String value) {
        return new JAXBElement<String>(_ComentarioInstalacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "cable")
    public JAXBElement<String> createCable(String value) {
        return new JAXBElement<String>(_Cable_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "relacionSenalRuidoUp")
    public JAXBElement<String> createRelacionSenalRuidoUp(String value) {
        return new JAXBElement<String>(_RelacionSenalRuidoUp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "id_lote_afip")
    public JAXBElement<Long> createIdLoteAfip(Long value) {
        return new JAXBElement<Long>(_IdLoteAfip_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "marca")
    public JAXBElement<String> createMarca(String value) {
        return new JAXBElement<String>(_Marca_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoPM")
    public JAXBElement<String> createTipoPM(String value) {
        return new JAXBElement<String>(_TipoPM_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "categoriaLinea")
    public JAXBElement<String> createCategoriaLinea(String value) {
        return new JAXBElement<String>(_CategoriaLinea_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "categoriaTerminalDispersion")
    public JAXBElement<String> createCategoriaTerminalDispersion(String value) {
        return new JAXBElement<String>(_CategoriaTerminalDispersion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "par")
    public JAXBElement<String> createPar(String value) {
        return new JAXBElement<String>(_Par_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "categoria")
    public JAXBElement<String> createCategoria(String value) {
        return new JAXBElement<String>(_Categoria_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "historicoPrueba")
    public JAXBElement<String> createHistoricoPrueba(String value) {
        return new JAXBElement<String>(_HistoricoPrueba_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "cierre")
    public JAXBElement<String> createCierre(String value) {
        return new JAXBElement<String>(_Cierre_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "messageID")
    public JAXBElement<String> createMessageID(String value) {
        return new JAXBElement<String>(_MessageID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "agenciaGestionaria")
    public JAXBElement<String> createAgenciaGestionaria(String value) {
        return new JAXBElement<String>(_AgenciaGestionaria_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "lc2")
    public JAXBElement<String> createLc2(String value) {
        return new JAXBElement<String>(_Lc2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nescab")
    public JAXBElement<String> createNescab(String value) {
        return new JAXBElement<String>(_Nescab_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "lc1")
    public JAXBElement<String> createLc1(String value) {
        return new JAXBElement<String>(_Lc1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHora")
    public JAXBElement<String> createFechaHora(String value) {
        return new JAXBElement<String>(_FechaHora_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "equipoNivel2")
    public JAXBElement<String> createEquipoNivel2(String value) {
        return new JAXBElement<String>(_EquipoNivel2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "escalera")
    public JAXBElement<String> createEscalera(String value) {
        return new JAXBElement<String>(_Escalera_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "result")
    public JAXBElement<String> createResult(String value) {
        return new JAXBElement<String>(_Result_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "equipoNivel1")
    public JAXBElement<String> createEquipoNivel1(String value) {
        return new JAXBElement<String>(_EquipoNivel1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "equipoNivel0")
    public JAXBElement<String> createEquipoNivel0(String value) {
        return new JAXBElement<String>(_EquipoNivel0_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "utilizador")
    public JAXBElement<String> createUtilizador(String value) {
        return new JAXBElement<String>(_Utilizador_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraSuspension")
    public JAXBElement<String> createFechaHoraSuspension(String value) {
        return new JAXBElement<String>(_FechaHoraSuspension_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "velocidadLineaConfiguradaDown")
    public JAXBElement<String> createVelocidadLineaConfiguradaDown(String value) {
        return new JAXBElement<String>(_VelocidadLineaConfiguradaDown_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nombreCalleElementoRed")
    public JAXBElement<String> createNombreCalleElementoRed(String value) {
        return new JAXBElement<String>(_NombreCalleElementoRed_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dc2")
    public JAXBElement<String> createDc2(String value) {
        return new JAXBElement<String>(_Dc2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dc1")
    public JAXBElement<String> createDc1(String value) {
        return new JAXBElement<String>(_Dc1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "parentServiceCode")
    public JAXBElement<String> createParentServiceCode(String value) {
        return new JAXBElement<String>(_ParentServiceCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "parentMessageId")
    public JAXBElement<String> createParentMessageId(String value) {
        return new JAXBElement<String>(_ParentMessageId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sign")
    public JAXBElement<String> createSign(String value) {
        return new JAXBElement<String>(_Sign_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "FEcuit")
    public JAXBElement<Long> createFEcuit(Long value) {
        return new JAXBElement<Long>(_FEcuit_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dc5")
    public JAXBElement<String> createDc5(String value) {
        return new JAXBElement<String>(_Dc5_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nzrae")
    public JAXBElement<String> createNzrae(String value) {
        return new JAXBElement<String>(_Nzrae_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dc4")
    public JAXBElement<String> createDc4(String value) {
        return new JAXBElement<String>(_Dc4_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dc3")
    public JAXBElement<String> createDc3(String value) {
        return new JAXBElement<String>(_Dc3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "figuraEnGuia")
    public JAXBElement<String> createFiguraEnGuia(String value) {
        return new JAXBElement<String>(_FiguraEnGuia_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nzrab")
    public JAXBElement<String> createNzrab(String value) {
        return new JAXBElement<String>(_Nzrab_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "entidad")
    public JAXBElement<String> createEntidad(String value) {
        return new JAXBElement<String>(_Entidad_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nse")
    public JAXBElement<String> createNse(String value) {
        return new JAXBElement<String>(_Nse_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nzvab")
    public JAXBElement<String> createNzvab(String value) {
        return new JAXBElement<String>(_Nzvab_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "coordenada")
    public JAXBElement<String> createCoordenada(String value) {
        return new JAXBElement<String>(_Coordenada_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoDomicilio")
    public JAXBElement<String> createTipoDomicilio(String value) {
        return new JAXBElement<String>(_TipoDomicilio_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "numeroReferenciaPM")
    public JAXBElement<String> createNumeroReferenciaPM(String value) {
        return new JAXBElement<String>(_NumeroReferenciaPM_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoPrueba")
    public JAXBElement<String> createTipoPrueba(String value) {
        return new JAXBElement<String>(_TipoPrueba_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nzvae")
    public JAXBElement<String> createNzvae(String value) {
        return new JAXBElement<String>(_Nzvae_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "text")
    public JAXBElement<String> createText(String value) {
        return new JAXBElement<String>(_Text_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nzrav")
    public JAXBElement<String> createNzrav(String value) {
        return new JAXBElement<String>(_Nzrav_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "capacidadMaximaParDown")
    public JAXBElement<String> createCapacidadMaximaParDown(String value) {
        return new JAXBElement<String>(_CapacidadMaximaParDown_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "relacionSenalRuidoDown")
    public JAXBElement<String> createRelacionSenalRuidoDown(String value) {
        return new JAXBElement<String>(_RelacionSenalRuidoDown_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "vacantesSecundarios")
    public JAXBElement<String> createVacantesSecundarios(String value) {
        return new JAXBElement<String>(_VacantesSecundarios_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "aturVendorId")
    public JAXBElement<String> createAturVendorId(String value) {
        return new JAXBElement<String>(_AturVendorId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "prioridad")
    public JAXBElement<String> createPrioridad(String value) {
        return new JAXBElement<String>(_Prioridad_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "reclamoTipoRetencion")
    public JAXBElement<String> createReclamoTipoRetencion(String value) {
        return new JAXBElement<String>(_ReclamoTipoRetencion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "totalConexiones")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createTotalConexiones(BigInteger value) {
        return new JAXBElement<BigInteger>(_TotalConexiones_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "registradosDaniados")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createRegistradosDaniados(BigInteger value) {
        return new JAXBElement<BigInteger>(_RegistradosDaniados_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "description")
    public JAXBElement<String> createDescription(String value) {
        return new JAXBElement<String>(_Description_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "cantidad")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createCantidad(BigInteger value) {
        return new JAXBElement<BigInteger>(_Cantidad_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "totalConexUtilizadas")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createTotalConexUtilizadas(BigInteger value) {
        return new JAXBElement<BigInteger>(_TotalConexUtilizadas_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "margenSenalRuidoObjetivoUp")
    public JAXBElement<String> createMargenSenalRuidoObjetivoUp(String value) {
        return new JAXBElement<String>(_MargenSenalRuidoObjetivoUp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nzrbe")
    public JAXBElement<String> createNzrbe(String value) {
        return new JAXBElement<String>(_Nzrbe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoSolicitud")
    public JAXBElement<String> createTipoSolicitud(String value) {
        return new JAXBElement<String>(_TipoSolicitud_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaCumplLinea")
    public JAXBElement<String> createFechaCumplLinea(String value) {
        return new JAXBElement<String>(_FechaCumplLinea_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nzrba")
    public JAXBElement<String> createNzrba(String value) {
        return new JAXBElement<String>(_Nzrba_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraMaxima")
    public JAXBElement<String> createFechaHoraMaxima(String value) {
        return new JAXBElement<String>(_FechaHoraMaxima_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "reclamoOrigenGirafe")
    public JAXBElement<String> createReclamoOrigenGirafe(String value) {
        return new JAXBElement<String>(_ReclamoOrigenGirafe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoSolicitudRelacionada")
    public JAXBElement<String> createTipoSolicitudRelacionada(String value) {
        return new JAXBElement<String>(_TipoSolicitudRelacionada_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraHasta")
    public JAXBElement<String> createFechaHoraHasta(String value) {
        return new JAXBElement<String>(_FechaHoraHasta_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "idTerminalDispersion")
    public JAXBElement<String> createIdTerminalDispersion(String value) {
        return new JAXBElement<String>(_IdTerminalDispersion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ntsac5")
    public JAXBElement<String> createNtsac5(String value) {
        return new JAXBElement<String>(_Ntsac5_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ntsac6")
    public JAXBElement<String> createNtsac6(String value) {
        return new JAXBElement<String>(_Ntsac6_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FELoteFACTType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "loteFact")
    public JAXBElement<FELoteFACTType> createLoteFact(FELoteFACTType value) {
        return new JAXBElement<FELoteFACTType>(_LoteFact_QNAME, FELoteFACTType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ntsac3")
    public JAXBElement<String> createNtsac3(String value) {
        return new JAXBElement<String>(_Ntsac3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ntsac4")
    public JAXBElement<String> createNtsac4(String value) {
        return new JAXBElement<String>(_Ntsac4_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ComentarioRegHistIntervencionesAnt")
    public JAXBElement<String> createComentarioRegHistIntervencionesAnt(String value) {
        return new JAXBElement<String>(_ComentarioRegHistIntervencionesAnt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ntsac1")
    public JAXBElement<String> createNtsac1(String value) {
        return new JAXBElement<String>(_Ntsac1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "acumuladoPendienteCliente")
    public JAXBElement<String> createAcumuladoPendienteCliente(String value) {
        return new JAXBElement<String>(_AcumuladoPendienteCliente_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nzvbe")
    public JAXBElement<String> createNzvbe(String value) {
        return new JAXBElement<String>(_Nzvbe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "apellido")
    public JAXBElement<String> createApellido(String value) {
        return new JAXBElement<String>(_Apellido_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "vertical")
    public JAXBElement<String> createVertical(String value) {
        return new JAXBElement<String>(_Vertical_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nzrbv")
    public JAXBElement<String> createNzrbv(String value) {
        return new JAXBElement<String>(_Nzrbv_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ntsac2")
    public JAXBElement<String> createNtsac2(String value) {
        return new JAXBElement<String>(_Ntsac2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "numeroSerie")
    public JAXBElement<String> createNumeroSerie(String value) {
        return new JAXBElement<String>(_NumeroSerie_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "usuarioPrueba")
    public JAXBElement<String> createUsuarioPrueba(String value) {
        return new JAXBElement<String>(_UsuarioPrueba_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "supplierErrorExplanation")
    public JAXBElement<String> createSupplierErrorExplanation(String value) {
        return new JAXBElement<String>(_SupplierErrorExplanation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "velocidadLineaRealUp")
    public JAXBElement<String> createVelocidadLineaRealUp(String value) {
        return new JAXBElement<String>(_VelocidadLineaRealUp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoMI")
    public JAXBElement<String> createTipoMI(String value) {
        return new JAXBElement<String>(_TipoMI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaCumplCabinaTP")
    public JAXBElement<String> createFechaCumplCabinaTP(String value) {
        return new JAXBElement<String>(_FechaCumplCabinaTP_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaAltaLinea")
    public JAXBElement<String> createFechaAltaLinea(String value) {
        return new JAXBElement<String>(_FechaAltaLinea_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "observaciones")
    public JAXBElement<String> createObservaciones(String value) {
        return new JAXBElement<String>(_Observaciones_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoCita")
    public JAXBElement<String> createTipoCita(String value) {
        return new JAXBElement<String>(_TipoCita_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk2")
    public JAXBElement<String> createSk2(String value) {
        return new JAXBElement<String>(_Sk2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk3")
    public JAXBElement<String> createSk3(String value) {
        return new JAXBElement<String>(_Sk3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk1")
    public JAXBElement<String> createSk1(String value) {
        return new JAXBElement<String>(_Sk1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "id_cbte")
    public JAXBElement<Long> createIdCbte(Long value) {
        return new JAXBElement<Long>(_IdCbte_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk6")
    public JAXBElement<String> createSk6(String value) {
        return new JAXBElement<String>(_Sk6_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk7")
    public JAXBElement<String> createSk7(String value) {
        return new JAXBElement<String>(_Sk7_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk4")
    public JAXBElement<String> createSk4(String value) {
        return new JAXBElement<String>(_Sk4_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk5")
    public JAXBElement<String> createSk5(String value) {
        return new JAXBElement<String>(_Sk5_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "rg3")
    public JAXBElement<String> createRg3(String value) {
        return new JAXBElement<String>(_Rg3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "conAdsl")
    public JAXBElement<String> createConAdsl(String value) {
        return new JAXBElement<String>(_ConAdsl_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "rg1")
    public JAXBElement<String> createRg1(String value) {
        return new JAXBElement<String>(_Rg1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "origen")
    public JAXBElement<String> createOrigen(String value) {
        return new JAXBElement<String>(_Origen_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "rg2")
    public JAXBElement<String> createRg2(String value) {
        return new JAXBElement<String>(_Rg2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "connectionFactoryJndiName")
    public JAXBElement<String> createConnectionFactoryJndiName(String value) {
        return new JAXBElement<String>(_ConnectionFactoryJndiName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "reservaSucesor")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createReservaSucesor(BigInteger value) {
        return new JAXBElement<BigInteger>(_ReservaSucesor_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "cantidadIntentos")
    public JAXBElement<String> createCantidadIntentos(String value) {
        return new JAXBElement<String>(_CantidadIntentos_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "exists")
    public JAXBElement<Boolean> createExists(Boolean value) {
        return new JAXBElement<Boolean>(_Exists_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraCierre")
    public JAXBElement<String> createFechaHoraCierre(String value) {
        return new JAXBElement<String>(_FechaHoraCierre_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "codigoDemora")
    public JAXBElement<String> createCodigoDemora(String value) {
        return new JAXBElement<String>(_CodigoDemora_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "zona")
    public JAXBElement<String> createZona(String value) {
        return new JAXBElement<String>(_Zona_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "marcaVIP")
    public JAXBElement<String> createMarcaVIP(String value) {
        return new JAXBElement<String>(_MarcaVIP_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "formaPago")
    public JAXBElement<String> createFormaPago(String value) {
        return new JAXBElement<String>(_FormaPago_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "reservadosSolicitudes")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createReservadosSolicitudes(BigInteger value) {
        return new JAXBElement<BigInteger>(_ReservadosSolicitudes_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tecnologia")
    public JAXBElement<String> createTecnologia(String value) {
        return new JAXBElement<String>(_Tecnologia_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "importe")
    public JAXBElement<String> createImporte(String value) {
        return new JAXBElement<String>(_Importe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "UnidadesTransmitidasDown")
    public JAXBElement<String> createUnidadesTransmitidasDown(String value) {
        return new JAXBElement<String>(_UnidadesTransmitidasDown_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "accion")
    public JAXBElement<String> createAccion(String value) {
        return new JAXBElement<String>(_Accion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "equipoId")
    public JAXBElement<String> createEquipoId(String value) {
        return new JAXBElement<String>(_EquipoId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoConstruccion")
    public JAXBElement<String> createTipoConstruccion(String value) {
        return new JAXBElement<String>(_TipoConstruccion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "token")
    public JAXBElement<String> createToken(String value) {
        return new JAXBElement<String>(_Token_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "manzanaElementoRed")
    public JAXBElement<String> createManzanaElementoRed(String value) {
        return new JAXBElement<String>(_ManzanaElementoRed_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "cnc")
    public JAXBElement<Boolean> createCnc(Boolean value) {
        return new JAXBElement<Boolean>(_Cnc_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "simcard")
    public JAXBElement<String> createSimcard(String value) {
        return new JAXBElement<String>(_Simcard_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sistemaOrigen")
    public JAXBElement<String> createSistemaOrigen(String value) {
        return new JAXBElement<String>(_SistemaOrigen_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "reclamoMarcaRetencion")
    public JAXBElement<String> createReclamoMarcaRetencion(String value) {
        return new JAXBElement<String>(_ReclamoMarcaRetencion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "trasladorCobro")
    public JAXBElement<String> createTrasladorCobro(String value) {
        return new JAXBElement<String>(_TrasladorCobro_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "alturaElementoRed")
    public JAXBElement<String> createAlturaElementoRed(String value) {
        return new JAXBElement<String>(_AlturaElementoRed_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "entrantes")
    @XmlJavaTypeAdapter(BigIntegerXmlAdapter.class)
    public JAXBElement<BigInteger> createEntrantes(BigInteger value) {
        return new JAXBElement<BigInteger>(_Entrantes_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "parHasta")
    public JAXBElement<String> createParHasta(String value) {
        return new JAXBElement<String>(_ParHasta_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "actionID")
    public JAXBElement<String> createActionID(String value) {
        return new JAXBElement<String>(_ActionID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "precalificacion")
    public JAXBElement<String> createPrecalificacion(String value) {
        return new JAXBElement<String>(_Precalificacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "gponSuscriberId")
    public JAXBElement<String> createGponSuscriberId(String value) {
        return new JAXBElement<String>(_GponSuscriberId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoTicketRelacionado")
    public JAXBElement<String> createTipoTicketRelacionado(String value) {
        return new JAXBElement<String>(_TipoTicketRelacionado_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "vacantesPrimarios")
    public JAXBElement<String> createVacantesPrimarios(String value) {
        return new JAXBElement<String>(_VacantesPrimarios_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "ipFija")
    public JAXBElement<String> createIpFija(String value) {
        return new JAXBElement<String>(_IpFija_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk9")
    public JAXBElement<String> createSk9(String value) {
        return new JAXBElement<String>(_Sk9_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "utilidad")
    public JAXBElement<String> createUtilidad(String value) {
        return new JAXBElement<String>(_Utilidad_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "sk8")
    public JAXBElement<String> createSk8(String value) {
        return new JAXBElement<String>(_Sk8_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "paresLibres")
    public JAXBElement<String> createParesLibres(String value) {
        return new JAXBElement<String>(_ParesLibres_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "piso")
    public JAXBElement<String> createPiso(String value) {
        return new JAXBElement<String>(_Piso_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "cabinaAptoNuevo")
    public JAXBElement<String> createCabinaAptoNuevo(String value) {
        return new JAXBElement<String>(_CabinaAptoNuevo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "summaryCodes")
    public JAXBElement<String> createSummaryCodes(String value) {
        return new JAXBElement<String>(_SummaryCodes_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoLinea")
    public JAXBElement<String> createTipoLinea(String value) {
        return new JAXBElement<String>(_TipoLinea_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "consumerCode")
    public JAXBElement<String> createConsumerCode(String value) {
        return new JAXBElement<String>(_ConsumerCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "despliegueDeTecnologia")
    public JAXBElement<String> createDespliegueDeTecnologia(String value) {
        return new JAXBElement<String>(_DespliegueDeTecnologia_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "comentarioInstCabina")
    public JAXBElement<String> createComentarioInstCabina(String value) {
        return new JAXBElement<String>(_ComentarioInstCabina_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "modelo")
    public JAXBElement<String> createModelo(String value) {
        return new JAXBElement<String>(_Modelo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaCumplRg")
    public JAXBElement<String> createFechaCumplRg(String value) {
        return new JAXBElement<String>(_FechaCumplRg_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dr1")
    public JAXBElement<String> createDr1(String value) {
        return new JAXBElement<String>(_Dr1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "gponRed")
    public JAXBElement<String> createGponRed(String value) {
        return new JAXBElement<String>(_GponRed_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dslamActualElementoRed")
    public JAXBElement<String> createDslamActualElementoRed(String value) {
        return new JAXBElement<String>(_DslamActualElementoRed_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dr3")
    public JAXBElement<String> createDr3(String value) {
        return new JAXBElement<String>(_Dr3_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dr2")
    public JAXBElement<String> createDr2(String value) {
        return new JAXBElement<String>(_Dr2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "FechaCierreHistIntervencionesAnt")
    public JAXBElement<String> createFechaCierreHistIntervencionesAnt(String value) {
        return new JAXBElement<String>(_FechaCierreHistIntervencionesAnt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dr5")
    public JAXBElement<String> createDr5(String value) {
        return new JAXBElement<String>(_Dr5_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "host")
    public JAXBElement<String> createHost(String value) {
        return new JAXBElement<String>(_Host_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dr4")
    public JAXBElement<String> createDr4(String value) {
        return new JAXBElement<String>(_Dr4_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nombreEdificio")
    public JAXBElement<String> createNombreEdificio(String value) {
        return new JAXBElement<String>(_NombreEdificio_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "altura")
    public JAXBElement<String> createAltura(String value) {
        return new JAXBElement<String>(_Altura_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "disponible")
    public JAXBElement<Boolean> createDisponible(Boolean value) {
        return new JAXBElement<Boolean>(_Disponible_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "comentario")
    public JAXBElement<String> createComentario(String value) {
        return new JAXBElement<String>(_Comentario_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "perfilDeFrecuencia")
    public JAXBElement<String> createPerfilDeFrecuencia(String value) {
        return new JAXBElement<String>(_PerfilDeFrecuencia_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "estadoAdministrativo")
    public JAXBElement<String> createEstadoAdministrativo(String value) {
        return new JAXBElement<String>(_EstadoAdministrativo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "profesion")
    public JAXBElement<String> createProfesion(String value) {
        return new JAXBElement<String>(_Profesion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "codigoProvincia")
    public JAXBElement<String> createCodigoProvincia(String value) {
        return new JAXBElement<String>(_CodigoProvincia_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "bl2")
    public JAXBElement<String> createBl2(String value) {
        return new JAXBElement<String>(_Bl2_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "observacionesSBT")
    public JAXBElement<String> createObservacionesSBT(String value) {
        return new JAXBElement<String>(_ObservacionesSBT_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "tipoInstalacion")
    public JAXBElement<String> createTipoInstalacion(String value) {
        return new JAXBElement<String>(_TipoInstalacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "lineNumberID")
    public JAXBElement<String> createLineNumberID(String value) {
        return new JAXBElement<String>(_LineNumberID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraDevolucion")
    public JAXBElement<String> createFechaHoraDevolucion(String value) {
        return new JAXBElement<String>(_FechaHoraDevolucion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "latitud")
    public JAXBElement<BigDecimal> createLatitud(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Latitud_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraDesde")
    public JAXBElement<String> createFechaHoraDesde(String value) {
        return new JAXBElement<String>(_FechaHoraDesde_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "inputMessage")
    public JAXBElement<String> createInputMessage(String value) {
        return new JAXBElement<String>(_InputMessage_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "bl1")
    public JAXBElement<String> createBl1(String value) {
        return new JAXBElement<String>(_Bl1_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "numeroEmpalme")
    public JAXBElement<String> createNumeroEmpalme(String value) {
        return new JAXBElement<String>(_NumeroEmpalme_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "verCode")
    public JAXBElement<String> createVerCode(String value) {
        return new JAXBElement<String>(_VerCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "longitud")
    public JAXBElement<BigDecimal> createLongitud(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Longitud_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "perfilVelocidad")
    public JAXBElement<String> createPerfilVelocidad(String value) {
        return new JAXBElement<String>(_PerfilVelocidad_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "transmisionCeldasUp")
    public JAXBElement<String> createTransmisionCeldasUp(String value) {
        return new JAXBElement<String>(_TransmisionCeldasUp_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "comentarioInstAparato")
    public JAXBElement<String> createComentarioInstAparato(String value) {
        return new JAXBElement<String>(_ComentarioInstAparato_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "potenciaDeTransmisionDown")
    public JAXBElement<String> createPotenciaDeTransmisionDown(String value) {
        return new JAXBElement<String>(_PotenciaDeTransmisionDown_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "cuit")
    public JAXBElement<String> createCuit(String value) {
        return new JAXBElement<String>(_Cuit_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "diagnosticoUltimaPruebaActor")
    public JAXBElement<String> createDiagnosticoUltimaPruebaActor(String value) {
        return new JAXBElement<String>(_DiagnosticoUltimaPruebaActor_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "fechaHoraAceptacion")
    public JAXBElement<String> createFechaHoraAceptacion(String value) {
        return new JAXBElement<String>(_FechaHoraAceptacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "dominio")
    public JAXBElement<String> createDominio(String value) {
        return new JAXBElement<String>(_Dominio_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "reclamoTipoOrigenGirafe")
    public JAXBElement<String> createReclamoTipoOrigenGirafe(String value) {
        return new JAXBElement<String>(_ReclamoTipoOrigenGirafe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "atenuacionDown")
    public JAXBElement<String> createAtenuacionDown(String value) {
        return new JAXBElement<String>(_AtenuacionDown_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "nombreEdificioElementoRed")
    public JAXBElement<String> createNombreEdificioElementoRed(String value) {
        return new JAXBElement<String>(_NombreEdificioElementoRed_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.telecom.com.ar/XMLSchema/bios/common", name = "parDesde")
    public JAXBElement<String> createParDesde(String value) {
        return new JAXBElement<String>(_ParDesde_QNAME, String.class, null, value);
    }

}
