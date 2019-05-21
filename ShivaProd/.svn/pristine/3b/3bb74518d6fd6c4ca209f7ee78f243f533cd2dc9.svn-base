package ar.com.telecom.shiva.test.seguridad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Encriptador;

public class EncryptionDecryptionAES {
	
	public static void main(String[] args) throws Exception {
		String filepath = "C:\\Desarrollo\\foto.png";
		File file = new File(filepath);
		InputStream in = new FileInputStream(file);
		byte[] original = ControlArchivo.getBytesFromInputStream(in);
		
		byte[] encryptedBytes = Encriptador.encrypt(original);
		
		filepath = "C:\\Desarrollo\\fotoEncriptadoAES.png";
		File f = new File(filepath);
		FileOutputStream w;
		try {
			f.setWritable(true);
			w = new FileOutputStream(f);
			w.write(encryptedBytes);
			w.close();
			f.setReadOnly();
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		}
		
		byte[] plainBytes = Encriptador.decrypt(encryptedBytes); 
		filepath = "C:\\Desarrollo\\fotoDesencriptadoAES.png";
		f = new File(filepath);
		try {
			f.setWritable(true);
			w = new FileOutputStream(f);
			w.write(plainBytes);
			w.close();
			f.setReadOnly();
		} catch (IOException e) {
			throw new ShivaExcepcion(e);
		}
		
	}
}
	
