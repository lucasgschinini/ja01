package ar.com.telecom.shiva.base.utils;

import java.security.Key;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")

/**
 * Para los passwords con AES-128 - Usamos para encriptar los passwords
 * NOTA: No modificar los keys hasta el aviso
 * 
 * Para los bytes con AES-256 - Otra opcion para no usar el framework bouncycastle
 * There are many problems when you try encrypting a string such password, credit card nos, phone no. etc ie
 * 1. which algorithm to use.
 * 2. how to store the generated Key in the database.
 * 3. should i use MD5, AES etc.
 * 
 * Here is the question to all your answers. After spending sometime on this i finally got the best algorithm 
 * that a person can use to encrypt and decrypt data while he/she also wants to store those encrypted strings 
 * and later on want to decrypt it while retrieving the data.
 * 
 * Many people face problem while decrypting the encrypted data as the KEY used for encryption if stored as 
 * String in database then it becomes very tough to use that string as the KEY. So below is the code where 
 * you only need to store the encrypted code and not the  key. The decryption will take place as an when wanted.
 * 
 * For encryption we must use a secret key along with an algorithm. In the following example we use an algorithm 
 * called AES 128 and the bytes of the word "TheBestSecretKey" as the secret key 
 * (the best secret key we found in this world). 
 * AES algorithm can use a key of 128 bits (16 bytes * 8); so we selected that key.
 *
 * Usando jasypt, bouncy Castle
 * Antes: PBEWithMD5AndDES con TheBestSecretKey
 * Ahora: PBEWITHSHA256AND256BITAES-CBC-BC con SHIVASHIVASHIVASHIVASHIVASHIVA14
 * 
 * Sobre el algoritmo PBEWITHSHA256AND256BITAES-CBC-BC 
 * http://security.stackexchange.com/questions/10049/explaining-security-vs-performace-to-a-non-tech-superior
 *
 ***/

public class Encriptador {
	
	public static final Date actualDate = new Date();
	
	private static final String PROVIDER = "AES";
	
	// 128 bits -- No tocar por ahora
	public static final String key128 = "TheBestSecretKey"; 
	public static final byte[] iv128 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static final byte[] key128Value = key128.getBytes();
	
	// 256 bits
	public static final String key256 = "SHIVASHIVASHIVASHIVASHIVASHIVA14"; 
	public static final byte[] iv256 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static final byte[] key256Value = key256.getBytes();
	

	/**
	 * Clave
	 */
    private static Key generateKey(int bits) throws Exception {
    	if (bits == 128) {
    		Key key = new SecretKeySpec(key128Value, PROVIDER);
    		return key;
    	}
    	if (bits == 256) {
    		Key key = new SecretKeySpec(key256Value, PROVIDER);
    		return key;
    	}
    	return null;
    }
    
    /**
     * Encriptar los passwords o los textos
     * @param originalData
     * @return
     * @throws Exception
     */
	public static String encrypt(String originalData) throws Exception {
        Key key = generateKey(128);
        Cipher c = Cipher.getInstance(PROVIDER);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(originalData.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
	
	/**
	 * Desencriptar los passwords o los textos
	 * @param encryptedData
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey(128);
        Cipher c = Cipher.getInstance(PROVIDER);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    
	/**
	 * Encriptar los bytes o archivos
	 * @param originalByte
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] originalByte) throws Exception {
//		String keySHIVA = "SHIVA";
//		char[] password = key256.toCharArray();
//		byte[] salt = keySHIVA.getBytes();
//		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//		KeySpec keySpec = new PBEKeySpec(password, salt, 1, 256);
//		SecretKey secretKey = factory.generateSecret(keySpec);
//		SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), PROVIDER);
//		Cipher cipher = Cipher.getInstance(PROVIDER);
//		cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(iv256));
		
		Key secret = generateKey(256);
		Cipher cipher = Cipher.getInstance(PROVIDER);
		cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(iv256));
		byte[] encryptedByte = cipher.doFinal(originalByte);
		return Base64.encodeBase64(encryptedByte);
	}
	
	/**
	 * Desencriptar los bytes o archivos
	 * @param encryptedByte
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] encryptedByte) throws Exception {
//		String keySHIVA = "SHIVA";
//		char[] password = key256.toCharArray();
//		byte[] salt = keySHIVA.getBytes();
//		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//		KeySpec keySpec = new PBEKeySpec(password, salt, 1, 256);
//		SecretKey tmp = factory.generateSecret(keySpec);
//		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), PROVIDER);
//		Cipher cipher = Cipher.getInstance(PROVIDER);
//		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv256));
		
		Key secret = generateKey(256);
		Cipher cipher = Cipher.getInstance(PROVIDER);
		byte[] decodedBytes = Base64.decodeBase64(encryptedByte);
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv256));
		byte[] decryptedByte = cipher.doFinal(decodedBytes);
		
		return decryptedByte;
	}

}
