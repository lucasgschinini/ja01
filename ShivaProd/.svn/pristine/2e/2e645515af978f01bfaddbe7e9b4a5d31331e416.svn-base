package ar.com.telecom.shiva.base.constantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Propiedades {
	
	/** mensajes.properties en el war*/
    public static final ResourceBundle MENSAJES_PROPIEDADES = ResourceBundle.getBundle("mensajes");
    
    /** shiva.properties en el war*/
    public static final ResourceBundle SHIVA_VERSION = ResourceBundle.getBundle("shiva_version");
    
    /** shiva_*.properties ruta fisica*/
    public static final ResourceBundle SHIVA_PROPIEDADES = ResourceBundle.getBundle("shiva_"+System.getProperty(Constantes.PROPIEDAD_ENTORNO)+"_interno");
    
    /** validaciones.properties **/
    public static final ResourceBundle VALIDACIONES_PROPIEDADES = ResourceBundle.getBundle("validaciones");
    
    /**
     * Apuntando a cualquier archivo para obtener propiedades
     */
    public static ResourceBundle archivoPropiedades(String urlFile) {

    	ResourceBundle bundle = null;
        InputStreamReader reader = null;
        FileInputStream fis = null;
        try {

            File file = new File(urlFile);

            // Also checks for existance
            if (file.isFile()) { 
                fis = new FileInputStream(file);
			    reader = new InputStreamReader(fis);
           		bundle = new PropertyResourceBundle(reader);
            }
        } catch (FileNotFoundException e) {
			return null;
        } catch (IOException e) {
			return null;
		} finally {
            try {
            	if (reader!=null) reader.close();
				if (fis!=null) fis.close();
			} catch (IOException e) {}
        }
        return bundle;
    }
    
}
