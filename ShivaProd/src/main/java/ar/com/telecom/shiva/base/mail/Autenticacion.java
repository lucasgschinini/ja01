package ar.com.telecom.shiva.base.mail;

import javax.mail.PasswordAuthentication;


public class Autenticacion extends javax.mail.Authenticator {
	private String username;
	private String password;
	 
	public PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(username, password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}