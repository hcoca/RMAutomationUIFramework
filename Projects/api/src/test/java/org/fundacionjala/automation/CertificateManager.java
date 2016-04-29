package org.fundacionjala.automation;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Hashtable;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.axis.components.net.JSSESocketFactory;
import org.apache.axis.components.net.SecureSocketFactory;

public class CertificateManager extends JSSESocketFactory implements SecureSocketFactory {

	public CertificateManager(Hashtable attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	private static String MY_KEYSTORE_PASSWORD = "Control123!";
	
	private static String RESOURCE_PATH_TO_KEYSTORE = "keystore.jks";

	/**
	 * Read the keystore, init the SSL socket factory
	 *
	 * This overrides the parent class to provide our SocketFactory
	 * implementation.
	 *
	 * @throws IOException
	 */
	
	protected void initFactory() throws IOException {
		try {
			SSLContext context = getContext();
			sslFactory = context.getSocketFactory();
		} catch (Exception e) {
			if (e instanceof IOException) {
				throw (IOException) e;
			}
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * Gets a custom SSL Context. This is the main working of this class. The
	 * following are the steps that make up our custom configuration:
	 *
	 * 1. Open our keystore file using the password provided 
	 * 2. Create a KeyManagerFactory and TrustManagerFactory using this file
	 * 3. Initialise a SSLContext using these factories
	 */
	
	protected SSLContext getContext() {
	char[] keystorepass = MY_KEYSTORE_PASSWORD.toCharArray();
	InputStream keystoreFile = this.getClass().getClassLoader().getResourceAsStream(RESOURCE_PATH_TO_KEYSTORE);
	
	try {
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(keystoreFile, keystorepass);
		KeyManagerFactory kmf = KeyManagerFactory
		.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(keyStore, keystorepass);
		TrustManagerFactory tmf = TrustManagerFactory
		.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(keyStore);
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(),
		new SecureRandom());
		return sslContext;
	} catch (Exception e) {
		
	}
	return null;
	}
}
