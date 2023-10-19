package com.anthem.enterprise.client.http;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anthem.common.PMFResourceUtils;
import com.anthem.util.Constants;

public final class HttpClientPool {
	private static Properties webConfig = null;
	private static final Logger logger = LogManager.getLogger(HttpClientPool.class); 

	// Single-element enum to implement Singleton.
	private static enum Singleton {
		// Just one of me so constructor will be called once.
		Client;
		// The thread-safe client.
		private final CloseableHttpClient threadSafeClient;

		// The constructor creates it - thus late
		@SuppressWarnings("deprecation")
		private Singleton() {
			webConfig = PMFResourceUtils.getProperties();

			HttpClientBuilder builder = HttpClientBuilder.create();
			 
		    // setup a Trust Strategy that allows all certificates.		    
		    SSLContext sslContext = null;
			try {
				sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				    public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				        return true;
				    }
				}).build();
			} catch (KeyManagementException e) {
				logger.error("Exception in connection pooling :: " + e);
			} catch (NoSuchAlgorithmException e) {
				logger.error("Exception in connection pooling :: " + e);
			} catch (KeyStoreException e) {
				logger.error("Exception in connection pooling :: " + e);
			}
		    builder.setSslcontext( sslContext);
		 
		    
		    HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();		 
		    
		    SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
		    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
		            .register("http", PlainConnectionSocketFactory.getSocketFactory())
		            .register("https", sslSocketFactory)
		            .build();
		    logger.debug("Registry set :: ");
		    // now, we create connection-manager using our Registry.
		    //      -- allows multi-threaded use
		    PoolingHttpClientConnectionManager cManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		    cManager.setMaxTotal(Integer.parseInt(webConfig.getProperty(Constants.PLM_CONNECTION_POOL_TOTAL_MAX_ROUTES)));
			cManager.setDefaultMaxPerRoute(Integer.parseInt(webConfig
					.getProperty(Constants.PLM_CONNECTION_POOL_MAX_PER_ROUTE)));
		    builder.setConnectionManager( cManager);
			
			threadSafeClient = builder.build();
		}

		public CloseableHttpClient get() {
			return threadSafeClient;
		}
	}

	public static CloseableHttpClient getClient() {
		// The thread safe client is held by the singleton.
		return Singleton.Client.get();
	}
}