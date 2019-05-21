package ar.com.telecom.shiva.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.FileSystemException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.logs.Traza;

public final class UnicaInstanciaFileKey {
 
	/**
     * Instantiates a new app lock.
     */
    private UnicaInstanciaFileKey() {
    }
 
    /** The lockFile. */
    private File lockFile = null;
 
    /** The lock. */
    private FileLock lock = null;
 
    /** The lockChannel. */
    private FileChannel lockChannel = null;
 
    /** The lockStream. */
    private FileOutputStream lockStream = null;
 
     /**
     * Instantiates a new app lock.
     *
     * @param key Unique application key
     * @throws IOException 
     * @throws FileSystemException 
     * @throws Exception The exception
     */
    private UnicaInstanciaFileKey(String key) 
    	throws IOException, ShivaExcepcion 
    {
        String tmpDir = System.getProperty("java.io.tmpdir");
        if (!tmpDir.endsWith(System.getProperty("file.separator"))) {
        	tmpDir += System.getProperty("file.separator");
        }
 
        // Acquire MD5
        try {
        	MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.reset();
            String hashText = new java.math.BigInteger(1, md.digest(key.getBytes())).toString(16);
                
            // Hash string has no leading zeros
            // Adding zeros to the beginnig of has string
            while (hashText.length() < 32) {
            	hashText = "0" + hashText;
            }
            lockFile = new File(tmpDir + hashText + ".app_lock");
        } catch (NoSuchAlgorithmException ex) {
            Traza.error(getClass(), "AppLock.AppLock() file fail");
        }
 
        // MD5 acquire fail
        if (lockFile == null) {
        	lockFile = new File(tmpDir + key + ".app_lock");
        }
 
        lockStream = new FileOutputStream(lockFile);
 
        String fContent = "Java AppLock Object" + Constantes.RETORNO_WIN + "Locked by key: " + key + Constantes.RETORNO_WIN;
        lockStream.write(fContent.getBytes());
 
        lockChannel = lockStream.getChannel();
 
        lock = lockChannel.tryLock();
 
        if (lock == null) {
        	throw new ShivaExcepcion("Can't create Lock file");
        }
    }
 
    /**
     * Release Lock.
     * Now another application instance can gain lock.
     * @throws IOException 
     *
     */
    private void release() throws IOException {
        if (lock.isValid()) {
                lock.release();
        }
        if (lockStream != null) {
                lockStream.close();
        }
        if (lockChannel.isOpen()) {
                lockChannel.close();
        }
        if (lockFile.exists()) {
                lockFile.delete();
        }
    }
 
    @Override
    protected void finalize() throws Throwable {
    	this.release();
        super.finalize();
    }
 
    /** The instance. */
    private static UnicaInstanciaFileKey instance;
 
    /**
     * Set application lock.
     * Method can be run only one time per application.
     * All next calls will be ignored.
     *
     * @param key Unique application lock key
     * @return true, if successful
     */
     public static boolean setLock(String key) {
    	 if (instance != null) {
    		 return true;
    	 }
 
         try {
        	 instance = new UnicaInstanciaFileKey(key);
         } catch (Exception ex) {
             instance = null;
             Traza.advertencia(UnicaInstanciaFileKey.class, "Fail to set AppLoc", ex);
             return false;
         }
 
         Runtime.getRuntime().addShutdownHook(new Thread() {
             @Override
             public void run() {
            	 UnicaInstanciaFileKey.releaseLock();
             }
         });
         return true;
     }
 
    /**
     * Trying to release Lock.
     * After release you can not user AppLock again in this application.
     * @throws NoSuchFieldException 
     */
    public static void releaseLock() {
        try {
            if (instance == null) {
            	throw new NoSuchFieldException("INSTANCE IS NULL");
            }
            instance.release();
        } catch (Exception ex){
        	 Traza.advertencia(UnicaInstanciaFileKey.class, "Fail to set AppLoc", ex);
        }
    }
}