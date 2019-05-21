package ar.com.telecom.shiva.test.seguridad;

import java.security.Provider;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class EncriptacionListaAlgoritmosRunner {
    public static void main(String[] args) {
    	Security.addProvider(new BouncyCastleProvider());
    	
        for (Provider provider : Security.getProviders()) {
            System.out.println("Provider: " + provider.getName() + " version: " + provider.getVersion());
            for (Provider.Service service : provider.getServices()) {
                System.out.printf("  Type : %-30s  Algorithm: %-30s\n", service.getType(), service.getAlgorithm());
            }
        }
    }
}

