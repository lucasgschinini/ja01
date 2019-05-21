package ar.com.telecom.shiva.base.mail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.MimeMessageHelper;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.MailExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.util.IDatosComunesValor;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class MailServicio {
	
	private MailConfiguracion mailConfiguracion;
	private Autenticacion autenticacion;
	
	@Autowired 
	ILdapServicio ldapServicio;
	
	/**
	 * Metodo publico que realiza el envio de correo
	 * @param subject
	 * @param content
	 * @throws ShivaExcepcion
	 */
	public void sendMail(Mail mail) {
		
		try {
			mail.setDebug(Boolean.FALSE);
			
			this.enviar(mail);
			
		} catch (MailExcepcion ex) {
			//No hace nada
		}
	}	
	/**
	 * @author u566205
	 * Agrupa los mails para enviar solo uno por cada key recibida. 
	 * La key se forma con el metodo formarKeyAgrupador()
	 * @param asunto, con almenos dos pipe para separar en el primer pipe el asunto para mail multiple
	 * el segundo pipe con un texto del cuerpo para mail multiples
	 * el tercer pipe con el asunto para el mail individual
	 * @param cuerpoMap
	 */
	public void enviarMailMasivo(String asunto, Map<String, List<String>> cuerpoMap){
		double fechaHoraInicioNanoTime = System.nanoTime();
		for (String key : cuerpoMap.keySet()){
			
			List<String> lista = cuerpoMap.get(key);
			Mail mail = new Mail();
			
			mail = armarDestinatarios(key, mail);
			
			String[] asuntoSplit = asunto.split("\\|"); 
			if(lista.size()==1){
				String[] datosCuerpo = lista.get(0).split("<br>");
				String[] datosValor = datosCuerpo[0].split("\\|");
				String asunto1 = "";
				/**
				 * @author u573005, sprint 3, se contempla los mails masivos
				 * de conciliacion sugerida que puede tener solo dos 
				 * asuntos posibles
				 */
				if(asuntoSplit.length >= 3){
					asunto1 = asuntoSplit[2] + " ";
				}
				
				/**
				 * @author u573005
				 * Sprint 3, defecto 53, se sobrescribe para el asunto caso de un unico aviso de pago
				 */
				if(asunto.contains(Constantes.AVISO_DE_PAGO) || asuntoSplit.length == 4){
					/**
					 * @author u573005
					 * Sprint 3, defecto 53, se sobrescribe para el asunto caso de un unico aviso de pago
					 */
					asunto1 += " " + Constantes.SEPARADOR_PIPE + " " + asuntoSplit[3];
					
				} else if (asunto.contains(Mensajes.ASUNTO_ANULAR_REGISTRO_AVC)){
//					No agrego nada
				} else if (asunto.contains("Conciliacion")) {
					String[] splitAux = asunto.split("Conciliacion");
					asunto1 = splitAux[1];

				} else {
					asunto1 += datosValor[0] + " " + Constantes.SEPARADOR_PIPE + " " + datosValor[1];
				}
				
				mail.setAsunto(asunto1);
				
				/**
				 * @author u562163 - DEF #50 - Sprint 3
				 */
				if (datosCuerpo.length == 1){
					StringBuffer cuerpo = new StringBuffer();
					cuerpo.append(datosCuerpo[0]);
					mail.setCuerpo(cuerpo);
				}else{
					StringBuffer cuerpo = new StringBuffer();
					for (String lineaCuerpo : datosCuerpo) {
						if(!Validaciones.isNullOrEmpty(lineaCuerpo.trim())){
							cuerpo.append(lineaCuerpo+"<br>");
						}
					}
					mail.setCuerpo(cuerpo);
				}
			} else if (lista.size() > 1 && asunto.contains("Conciliacion")) {
				
				StringBuffer cuerpo = new StringBuffer();
				mail.setAsunto(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.mail.asunto.valores.disponibles"));
				for (String datosCuerpo : lista) {
					/**
					 * @author u562163 - DEF #50 - Sprint 3
					 */
					if(!Validaciones.isNullOrEmpty(datosCuerpo.trim())){
						cuerpo.append(datosCuerpo + "<br><br>");
					}
				}
				mail.setCuerpo(cuerpo);
			}else {
				mail.setAsunto(asuntoSplit[0]);
				StringBuffer cuerpo = new StringBuffer();
				if(asuntoSplit.length >= 2){
					cuerpo.append(asuntoSplit[1]+"<br>");
				}
				for (String datosCuerpo : lista) {
					/**
					 * @author u562163 - DEF #50 - Sprint 3
					 */
					if(!Validaciones.isNullOrEmpty(datosCuerpo.trim())){
						cuerpo.append(datosCuerpo + "<br><br>");
					}
				}
				mail.setCuerpo(cuerpo);
			}
			sendMail(mail);
		}
		Utilidad.tracearTiempo(getClass(), "Tiempo en el metodo enviarMailMasivo ", fechaHoraInicioNanoTime);
	}
	/**
	 * @author u573005, sprint3, defecto 
	 * @param key con el destinatario para (Analista), cc (Copropietario) y cc team comercial
	 * @param mail
	 * El objetivo es controlar los destinatarios repetidos
	 */
	public Mail armarDestinatarios(String key, Mail mail) {
		
		String[] analistas = key.split("\\|");
		mail.setDestinatarioPara(obtenerMailUsuario(analistas[0]));
		
		if (analistas.length == 2){
			if(!Validaciones.isNullOrEmpty(analistas[1]) && !analistas[0].equalsIgnoreCase(analistas[1])){
				mail.setDestinatarioCC(obtenerMailUsuario(analistas[1]));				
			}
		}
		if (analistas.length == 3){
			if(!Validaciones.isNullOrEmpty(analistas[2])){		/*Verifico que el teamComercial no sea nulo*/
				if(!Validaciones.isNullOrEmpty(analistas[1])){	/*Verifico que el copropietario no sea nulo*/
					if(!analistas[2].equals(analistas[0])){ 	//Verifico que el teamComercial sea <> al propietario
						if(!analistas[2].equals(analistas[1])){ //Verifico que el teamComercial sea <> al copropietario
							mail.setDestinatarioCC(obtenerMailUsuario(analistas[1]) +";" +obtenerMailUsuario(analistas[2]));
						}else{
							mail.setDestinatarioCC(obtenerMailUsuario(analistas[2]));
						}
					}else{
						if(!analistas[1].equals(analistas[0])){ //Verifico que el propietario sea <> al copropietario
							mail.setDestinatarioCC(obtenerMailUsuario(analistas[1]));
						}
					}
				}else{
					if(!analistas[2].equals(analistas[0])){//Verifico que el teamComercial sea <> al propietario
						mail.setDestinatarioCC(obtenerMailUsuario(analistas[2]));
					}					
				}
			}else{
				if(!Validaciones.isNullOrEmpty(analistas[1])){	/*Verifico que el copropietario no sea nulo*/
					if(!analistas[1].equals(analistas[0])){ //Verifico que el propietario sea <> al copropietario
						mail.setDestinatarioCC(obtenerMailUsuario(analistas[1]));
					}
				}
			}
		}
		return mail;		
	}

	public String formarKeyAgrupador(String idAnalista, String idCopropietario, String idAnalistaTeamComercial) throws NegocioExcepcion {
		String key = idAnalista+"|"+idCopropietario+"|"+idAnalistaTeamComercial;
		return key;
	}
	
	public String obtenerMailUsuario(String idUsuario){
		if (!Validaciones.isNullOrEmpty(idUsuario)) {
			if (!idUsuario.contains("@")){
				UsuarioLdapDto usuarioLdapAnalista = null;
				try {
					usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(idUsuario);
				} catch (LdapExcepcion e) {
					Traza.error(getClass(), "Se ha producido error en el servicio de LDAP", e);
				}
				if(!Validaciones.isNullOrEmpty(String.valueOf(usuarioLdapAnalista)) && !Validaciones.isNullOrEmpty(usuarioLdapAnalista.getMail()) ){
					return usuarioLdapAnalista.getMail();
				}
			}
			return idUsuario;
		}
		return null;
	}
	
	/**
	 * Metodo publico que realiza el envio de correo
	 * @param subject
	 * @param content
	 * @throws MailExcepcion 
	 * @throws ShivaExcepcion
	 */
	public void enviarMail(Mail mail) throws MailExcepcion {
		mail.setDebug(Boolean.FALSE);
		this.enviar(mail);
	}
	
	/**
	 * Metodo que realiza prueba
	 * @throws ShivaExcepcion
	 */
	public void testMail() throws ShivaExcepcion {
		String asunto = "Shiva - Prueba Mail";
		StringBuffer cuerpo = new StringBuffer("Se ha probado exitosamente el correo desde la aplicación Shiva.");
		Mail mail = new Mail(mailConfiguracion.getTo(), asunto, cuerpo);
		
		try {
			mail.setDebug(Boolean.TRUE);
			
			this.enviar(mail);
			
		} catch (MailExcepcion ex) {
			
			// Mando el error a la pagina de mantenimiento
			throw new ShivaExcepcion(ex);
		}
	}
	
	/**
	 * Clase privada que envia mail
	 * @return
	 * @throws MailExcepcion
	 */
	private void enviar(Mail mail) throws MailExcepcion {
	    try {
	      Properties  props  = new Properties();
	      boolean     doAuth = !"none".equals(mailConfiguracion.getAuth());
	      boolean     useTLS = "tls".equals(mailConfiguracion.getAuth());
	      Session     session;
	 
	      props.put("mail.from", mailConfiguracion.getFrom());
	      props.put("mail.smtp.host", mailConfiguracion.getHost());
	      props.put("mail.smtp.port", mailConfiguracion.getPort());
	      props.put("mail.transport.protocol", "smtp");
	      props.put("mail.debug", mail.getDebug().toString());
	      
	      if (useTLS) {
	        props.put("mail.smtp.socketFactory.port",     mailConfiguracion.getPort());
	        props.put("mail.smtp.socketFactory.class",    "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false");
	        props.put("mail.smtp.starttls.enable",        "true");
	        props.put("mail.smtp.ssl",                    "true");
	      }
	 
	      if (doAuth) {
	        props.put("mail.user", autenticacion.getUsername());
	        props.put("mail.smtp.auth", "true");
	 
	        session = Session.getInstance(props, autenticacion);
	      } else {
	        session = Session.getInstance(props);
	      }  
	      
	      mail.setRemitente(mailConfiguracion.getFrom());
	      mail.setRemitenteNombre(mailConfiguracion.getFromName());
	      
	      MimeMessage   message = new MimeMessage(session);
	      MimeMessageHelper helper = new MimeMessageHelper(message, true, mailConfiguracion.getEncoding());
	      
	      if ((!Validaciones.isNullOrEmpty(mail.getRemitente()))
	    		  && (!Validaciones.isNullOrEmpty(mail.getRemitente()))) 
	      {
	    	  helper.setFrom(mail.getRemitente(), mail.getRemitenteNombre());
	      } else {
	    	  throw new MailExcepcion(Mensajes.MAIL_ERROR_REMITENTE);
	      }
	      
	      //Lista de Mail (Formato: email1;email2)
	      if (!Validaciones.isNullOrEmpty(mail.getDestinatarioPara())) {
		      String coleccion = mail.getDestinatarioPara();
			  Collection<String> destinatarios = Arrays.asList(coleccion.split(";"));
			  
		      for (Iterator<String> iterator = destinatarios.iterator(); iterator.hasNext();) {
		    	  String addressee = (String) iterator.next();
		    	  helper.addTo(new InternetAddress(addressee));
		      }
	      } else {
	    	  throw new MailExcepcion(Mensajes.MAIL_ERROR_DESTINATARIO_PARA);
	      }
	      
	      if (!Validaciones.isNullOrEmpty(mail.getDestinatarioCC())) {
		      String coleccion = mail.getDestinatarioCC();
			  Collection<String> destinatarios = Arrays.asList(coleccion.split(";"));
		      for (Iterator<String> iterator = destinatarios.iterator(); iterator.hasNext();) {
		    	  String addressee = (String) iterator.next();
		    	  helper.addCc(new InternetAddress(addressee));
		      }
	      }
	      
	      if (!Validaciones.isNullOrEmpty(mail.getDestinatarioBCC())) {
		      String coleccion = mail.getDestinatarioBCC();
			  Collection<String> destinatarios = Arrays.asList(coleccion.split(";"));
		      for (Iterator<String> iterator = destinatarios.iterator(); iterator.hasNext();) {
		    	  String addressee = (String) iterator.next();
		    	  helper.addBcc(new InternetAddress(addressee));
		      }
	      }
	      
	      helper.setSubject(mail.getAsunto());
	      
	      StringBuffer cuerpo = mail.getCuerpo();
	      cuerpo.append(getAsuntoNoResponder());
	      
	      //use the true flag to indicate the text included is HTML
	      helper.setText(cuerpo.toString(), true);
	      if(mail.getAdjuntos() != null){
		      for (Adjunto adjunto : mail.getAdjuntos()) {
			      final byte[] lista = adjunto.getAdjunto();
			      helper.addAttachment(adjunto.getNombreArchivoAdjuntar(), new InputStreamSource() {
			    	  @Override
			    	  public java.io.InputStream getInputStream() throws IOException {
				    	 ByteArrayInputStream is = new ByteArrayInputStream(lista);
				    	 return is;
			    	  }
			      });	    	  
		      }
	      }

	      //Mando a tracear antes del envio
	      traceoPreEnvioMail(mail);
	      
	      Transport.send(message);
	      
	      // OK
	      Traza.auditoria(getClass(), mail.getAsunto(), Mensajes.MAIL_EXITO);
	    
	    } catch (Throwable e) {
	    	// Cualquier error que se produzca
	    	Traza.error(getClass(), mail.getAsunto() + " - " + Mensajes.MAIL_ERROR , e);
	    	throw new MailExcepcion(e.getMessage(), e);
	    }		
	}

	/**
	 * Armo el asunto de no responder 
	 */
	private String getAsuntoNoResponder() {
		String asunto = "<br><br><br>";
		asunto += "<p><b><i><span lang='es-ar'><font color='#0000FF' size='2'>";
		asunto += Mensajes.MAIL_ASUNTO_NO_RESPONDER;
		asunto += "</font></span></i></b></p>";
		
		return asunto;
	}
	
	/**
	 * Envio a las trazas antes del envio de mail
	 * @param mail
	 */
	private void traceoPreEnvioMail(Mail mail) {
		Traza.auditoria(getClass(), mail.getAsunto(), "Se va a enviar el siguiente correo: ");
		Traza.auditoria(getClass(), mail.getAsunto(), "Remitente: "+ mail.getRemitenteNombre() + "(" + mail.getRemitente() +")");
		Traza.auditoria(getClass(), mail.getAsunto(), "Destinatario Para: " + mail.getDestinatarioPara());
		Traza.auditoria(getClass(), mail.getAsunto(), "Destinatario CC: " + mail.getDestinatarioCC());
		Traza.auditoria(getClass(), mail.getAsunto(), "Destinatario BCC: " + mail.getDestinatarioBCC());
		Traza.auditoria(getClass(), mail.getAsunto(), "Asunto: " + mail.getAsunto());
		Traza.auditoria(getClass(), mail.getAsunto(), "Cuerpo: " + mail.getCuerpo().toString());
	}
	
	/**
	 * @author u573005, sprint3, 
	 * se crea el metodo general para la creacion minima del cuerpo 
	 * de envio de mail masivos
	 * @param datosMail
	 * @return Si tiene cliente el string "Cliente: 97033 - Ejemplo Razon Social | Bco. Origen: Citi | Nro. Cheque: 1111 | Importe: $1.000,99"
	 * Si no, "Bco. Origen: Citi | Nro. Cheque: 1111 | Importe: $1.000,99"
	 */
	public String armarLineaCuerpoValor(IDatosComunesValor datosMail){
		String cuerpo = "";
		String cliente = datosMail.getClienteFormateado();
		if(!Validaciones.isNullOrEmpty(cliente)){
			cuerpo = cliente;
		}
		String numeroValor = datosMail.getNumeroValorFormateado();
		if(!Validaciones.isNullOrEmpty(numeroValor)){
			if(!Validaciones.isNullOrEmpty(cuerpo)){
				cuerpo += " " + Constantes.SEPARADOR_PIPE + " " + numeroValor;
			}else{
				cuerpo += numeroValor;
			}			
		}
		cuerpo += " " + Constantes.SEPARADOR_PIPE + " Importe: " + Utilidad.formatCurrency(datosMail.getImporte(), 2);
		return cuerpo;
	}
	
	public StringBuffer armarAsuntoValorStringBuffer (ShvValValor shvValValor) throws NegocioExcepcion {
		StringBuffer asunto = new StringBuffer();
		
		asunto.append(Propiedades.MENSAJES_PROPIEDADES.getString("mail.aviso.pago.valor.disponible"));  
		asunto.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.empresa"));
		asunto.append(Constantes.DOS_PUNTOS);
		asunto.append(Constantes.WHITESPACE);
		asunto.append(shvValValor.getEmpresa().getDescripcion());
		asunto.append(Constantes.WHITESPACE);
		asunto.append(Constantes.SEPARADOR_PIPE);
		
		

		if (!Validaciones.isObjectNull(shvValValor.getCuit())) {
			asunto.append(Constantes.WHITESPACE);
			asunto.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.cuit"));
			asunto.append(Constantes.DOS_PUNTOS);
			asunto.append(Constantes.WHITESPACE);
			asunto.append(shvValValor.getCuit().toString());
			asunto.append(Constantes.WHITESPACE);
			asunto.append(Constantes.SEPARADOR_PIPE);
			
			
		}
		
		if (!Validaciones.isObjectNull(shvValValor.getIdClienteLegado())) {
			asunto.append(Constantes.WHITESPACE); 
			asunto.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.aviso.pago.tabla.clientes.cliente"));
			asunto.append(Constantes.DOS_PUNTOS);
			asunto.append(Constantes.WHITESPACE); 
			asunto.append(shvValValor.getIdClienteLegado().toString());
		}
		
		
		if (!Validaciones.isNullOrEmpty(shvValValor.getRazonSocial())) {
			asunto.append(Constantes.WHITESPACE);
			asunto.append(Constantes.SEPARADOR_PIPE); 
			asunto.append(Constantes.WHITESPACE);
			asunto.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.aviso.pago.tabla.clientes.razonSocial"));
			asunto.append(Constantes.DOS_PUNTOS);
			asunto.append(Constantes.WHITESPACE);
			asunto.append(shvValValor.getRazonSocial());
			
		}

		
		if (!Validaciones.isNullOrEmpty(shvValValor.getNumeroReferenciaDelValorFormateado())) {
			if (!Validaciones.isObjectNull(asunto)) {
				asunto.append(Constantes.WHITESPACE);
				asunto.append(Constantes.SEPARADOR_PIPE);
				asunto.append(Constantes.WHITESPACE); 
				asunto.append(shvValValor.getNumeroReferenciaDelValorFormateado());
				
			} else {
				asunto.append(Constantes.WHITESPACE);
				asunto.append(shvValValor.getNumeroReferenciaDelValorFormateado());
			}			
		}
		return asunto;
	}
	
	public StringBuffer armarCuerpoValorStringBuffer (ShvValValor shvValValor) throws NegocioExcepcion {
		StringBuffer cuerpo = new StringBuffer();
		
		cuerpo.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.empresa"));
		cuerpo.append(Constantes.DOS_PUNTOS);
		cuerpo.append(Constantes.WHITESPACE);
		cuerpo.append(shvValValor.getEmpresa().getDescripcion());
		cuerpo.append(Constantes.WHITESPACE);
		cuerpo.append(Constantes.SEPARADOR_PIPE);
		
		if (!Validaciones.isObjectNull(shvValValor.getCuit())) {	
			cuerpo.append(Constantes.WHITESPACE);
			cuerpo.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.cuit"));
			cuerpo.append(Constantes.DOS_PUNTOS);
			cuerpo.append(Constantes.WHITESPACE);
			cuerpo.append(shvValValor.getCuit().toString());
			cuerpo.append(Constantes.WHITESPACE);
			cuerpo.append(Constantes.SEPARADOR_PIPE);
		}
		
		if (!Validaciones.isObjectNull(shvValValor.getIdClienteLegado())) {
			cuerpo.append(Constantes.WHITESPACE);
			cuerpo.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.aviso.pago.tabla.clientes.cliente"));
			cuerpo.append(Constantes.DOS_PUNTOS);
			cuerpo.append(Constantes.WHITESPACE); 
			cuerpo.append(shvValValor.getIdClienteLegado().toString());
		}
		
		cuerpo.append(Constantes.WHITESPACE);
		cuerpo.append(Constantes.SEPARADOR_PIPE);
		cuerpo.append(Constantes.WHITESPACE);
		cuerpo.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.aviso.pago.tabla.clientes.empresas.asociadas"));
		cuerpo.append(Constantes.DOS_PUNTOS);
		cuerpo.append(Constantes.WHITESPACE);
		cuerpo.append(empresasAsociadas(shvValValor));
		
		if (!Validaciones.isNullOrEmpty(shvValValor.getRazonSocial())) {
			cuerpo.append(Constantes.WHITESPACE);
			cuerpo.append(Constantes.SEPARADOR_PIPE); 
			cuerpo.append(Constantes.WHITESPACE);
			cuerpo.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.aviso.pago.tabla.clientes.razonSocial"));
			cuerpo.append(Constantes.DOS_PUNTOS);
			cuerpo.append(Constantes.WHITESPACE);
			cuerpo.append(shvValValor.getRazonSocial());
		}
		
		if (!Validaciones.isNullOrEmpty(shvValValor.getNumeroValor())) {
			cuerpo.append(Constantes.WHITESPACE); 
			cuerpo.append(Constantes.SEPARADOR_PIPE); 
			cuerpo.append(Constantes.WHITESPACE); 
			cuerpo.append(shvValValor.getNumeroValor());
		}
		
		cuerpo.append(Constantes.WHITESPACE); 
		cuerpo.append(Constantes.SEPARADOR_PIPE); 
		cuerpo.append(Constantes.WHITESPACE);
		cuerpo.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.importe"));
		cuerpo.append(Constantes.DOS_PUNTOS);
		cuerpo.append(Constantes.WHITESPACE);
		cuerpo.append(Utilidad.formatCurrency(shvValValor.getImporte(), 2));
		
		return cuerpo;
	}
	
	/**
	 * @author u591368 F.N. Quispe
	 * @param shvValValor
	 * @return Retorna las empresas asociadas con el cliente del valor
	 * @throws NegocioExcepcion
	 */
	public String empresasAsociadas(ShvValValor shvValValor) throws NegocioExcepcion {
		return Utilidad.armadoCampoEmpresasAsociadas(shvValValor.getPermiteUsoTA(), shvValValor.getPermiteUsoTP(), shvValValor.getPermiteUsoCV(),shvValValor.getPermiteUsoNX());
	}

	
	/**
	 * @author u573005, sprint 3, 
	 * se arma el asunto minimo para el envio de mail masivos
	 * de forma general
	 * @param datosMail
	 * @return Si tiene cliente el string "Cliente: 97033 - Ejemplo Razon Social | Nro. Cheque: 1111"
	 * Si no, "Nro. Cheque: 1111"
	 */
	public String armarAsuntoValor(IDatosComunesValor datosMail){
		String asunto = "";
		if(!Validaciones.isNullOrEmpty(datosMail.getClienteFormateado())){
			asunto = datosMail.getClienteFormateado();
		}
		if(!Validaciones.isNullOrEmpty(datosMail.getNumeroReferenciaDelValorFormateado())){
			if(!Validaciones.isNullOrEmpty(asunto)){
				asunto += " " + Constantes.SEPARADOR_PIPE + " " + datosMail.getNumeroReferenciaDelValorFormateado();
			}else{
				asunto += datosMail.getNumeroReferenciaDelValorFormateado();
			}			
		}
		return asunto;	
	}
	
	public MailConfiguracion getMailConfiguracion() {
		return mailConfiguracion;
	}

	public void setMailConfiguracion(MailConfiguracion mailConfiguracion) {
		this.mailConfiguracion = mailConfiguracion;
	}

	public Autenticacion getAutenticacion() {
		return autenticacion;
	}

	public void setAutenticacion(Autenticacion autenticacion) {
		this.autenticacion = autenticacion;
	}
}
