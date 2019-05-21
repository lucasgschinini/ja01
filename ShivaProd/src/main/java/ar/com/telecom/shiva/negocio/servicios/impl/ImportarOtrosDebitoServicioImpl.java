package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.jms.util.runnable.ConsultaDebitosACobradoresThread;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.ws.cliente.SapConsultaPartidasWS;
import ar.com.telecom.shiva.base.ws.cliente.SapS4ConsultaProveedoresWS;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteDeimosServicio;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineClienteMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineCreditoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineDebitoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineDocumentoAdjuntoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineHistorialMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobroOnlineTransaccionesMapeador;
import ar.com.telecom.shiva.negocio.mapeos.CobrosOnlineVistaMapeador;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICombosServicio;
import ar.com.telecom.shiva.negocio.servicios.IExcelServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IReglaCobroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.validacion.ICobroOnlineValidacionServicio;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineClienteDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineCreditoDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDebitoDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineMedioPagoDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineOtrosDebitoDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoAdjuntoDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IMedioPagoDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoDao;
import ar.com.telecom.shiva.persistencia.dao.ITareaDao;
import ar.com.telecom.shiva.persistencia.dao.IWorkflowDao;
import ar.com.telecom.shiva.persistencia.dao.impl.CobroOnlineDaoImpl;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;

public class ImportarOtrosDebitoServicioImpl  {
	
	@Autowired ICobroOnlineDao cobroOnlineDao;
	@Autowired ICobroOnlineClienteDao cobroClienteDao;
	@Autowired ICobroOnlineDebitoDao cobroDebitoDao;
	@Autowired ICobroOnlineOtrosDebitoDao cobroOtrosDebitoDao;
	@Autowired ICobroOnlineCreditoDao cobroCreditoDao;
	@Autowired ICobroOnlineMedioPagoDao cobroMedioPagoDao;
	@Autowired IWorkflowCobros workflowCobros;
	@Autowired IWorkflowService workflowService;
	@Autowired IWorkflowDao workflowDao;
	@Autowired IClienteSiebelServicio clienteSiebelServicio;
	@Autowired IGenericoDao genericoDao;
	@Autowired IDocumentoAdjuntoDao documentoAdjuntoDao;
	@Autowired MailServicio mailServicio;
	@Autowired ITareaServicio tareaServicio;
	@Autowired ITareaDao tareaDao;
	@Autowired IClienteDeimosServicio clienteDeimosServicio;
	@Autowired ICobroOnlineValidacionServicio cobroOnlineValidacionServicio;
	@Autowired IExcelServicio exportacionDetalleCobro;
	@Autowired ICombosServicio combosServicio;	
	@Autowired ILdapServicio ldapServicio;
	@Autowired IMotivoDao motivoDao;	
	@Autowired ICobroDao cobroDao;
	@Autowired IMedioPagoDao medioPagoDao;
	@Autowired IVistaSoporteServicio vistaSoporteServicio;	
	@Autowired ICobroBatchSimulacionServicio cobroBatchSimulacionServicio;
	@Autowired ICobroBatchServicio cobroBatchServicio;
	@Autowired IParametroServicio parametroServicio;
	@Autowired CobrosOnlineVistaMapeador cobroOnlineVistaMapeo;	
	@Autowired CobroOnlineDebitoMapeador debitoMapeador;
	@Autowired CobroOnlineCreditoMapeador creditoMapeador;
	@Autowired CobroOnlineClienteMapeador clienteMapeador;
	@Autowired CobroOnlineHistorialMapeador cobroOnlineHistorialMapeo;
	@Autowired CobroOnlineTransaccionesMapeador cobroOnlineTransaccionesMapeador;
	@Autowired CobroOnlineDocumentoAdjuntoMapeador adjuntoMapeador;
	@Autowired IDescobroDao descobroDao;
	@Autowired ITeamComercialServicio teamComercialServicio;	
	@Autowired IOperacionMasivaServicio operacionMasivaServicio;
	@Autowired IClienteCalipsoServicio clienteCalipsoServicio;
	@Autowired IMicJmsSyncServicio  micJmsSyncServicio;
	@Autowired IReglaCobroServicio reglaCobroServicio;
	
	@Autowired ICobroBatchSoporteServicio cobroBatchSoporteServicio;
	@Autowired
	SapS4ConsultaProveedoresWS sapS4ConsultaProveedoresWS;
	@Autowired
	SapConsultaPartidasWS sapConsultaPartidasWS;
	@Autowired
	private IClienteServicio clienteServicio;
	@Autowired
	private CobroOnlineDaoImpl cobroDaoImpl;
	
	
	public static final String XLS_CONTENT_TYPE = "application/vnd.ms-excel";
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String ATTACHMENT_FILENAME = "attachment; filename=";
	
	//LISTAS PARA CONSULTAR A LOS COBRADORES MIC, CALIPSO
	public final int LIMITE_MIC=30;
	public final int LIMITE_CALIPSO = 15;
	public List<ShvCobEdDebito> listaDebitosOriginal;
	public List<ShvCobEdDebito> listaDebitosMic = new ArrayList<ShvCobEdDebito>();
	public List<ShvCobEdDebito> listaDebitosCalipso = new ArrayList<ShvCobEdDebito>();
	public List<ConsultaDebitosACobradoresThread> listaThreads = new ArrayList<ConsultaDebitosACobradoresThread>();
}
