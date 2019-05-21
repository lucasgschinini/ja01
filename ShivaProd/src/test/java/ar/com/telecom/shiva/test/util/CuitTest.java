package ar.com.telecom.shiva.test.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class CuitTest {
	public static void main(String[] args) throws IOException{
		 String cuit;
		 //Direccion del archivo donde se encontrara la lista de CUITS
		 FileReader fr = new FileReader("C:\\Users\\u585863\\Desktop\\Lista de Cuits.txt");
		//Direccion del archivo donde se guardara la validacion de los CUITS
		 FileWriter fw = new FileWriter("C:\\Users\\u585863\\Desktop\\listaCuitsValidados.log");
	     BufferedReader br = new BufferedReader(fr);
	     boolean resultadoValidacion = false;
	     
	     while((cuit = br.readLine())!=null) {
	     	try {
	     		resultadoValidacion = false;
	     		resultadoValidacion = Validaciones.validarCuit(cuit);
	     		fw.write("Voy a validar el CUIT: " + cuit + " Resultado: " + resultadoValidacion + "\n");
	     		fw.flush();
	     	} catch (ValidacionExcepcion e) {
	     		fw.write("Voy a validar el CUIT: " + cuit + " Resultado: " + resultadoValidacion + "\n");
	     		fw.flush();
	        }
		}
	     br.close();
	     fw.close();
	}
}
