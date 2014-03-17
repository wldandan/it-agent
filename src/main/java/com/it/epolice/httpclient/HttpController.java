package com.it.epolice.httpclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

public class HttpController {

    HttpClient client;
    String server;
    int port;
    int count;
    boolean secureConnection;
    String rootContext;

    /**
     * 
     */
    public HttpController() {
        createHttpClient();
    }

    /**
     * @param server
     * @param port
     * @param remoteClient
     */
    public HttpController(String server, int port, int count) {
        this.server = server;
        this.port = port;
        this.count = count;
        createHttpClient();
    }

    private void createHttpClient() {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(count);
        connectionManager.getParams().setMaxTotalConnections(count * 4);
        this.client = new HttpClient(connectionManager);
        this.rootContext = "";
    }

    /**
     * Use to enable secure connection (https).
     * 
     * @param secure
     *            Set to true to use https
     */
    public void useSecureConnection(boolean secure) {
        secureConnection = secure;
    }

    /**
     * Determine whether or not to use a secure connection.
     * 
     * @return True for https
     */
    public boolean isSecureConnection() {
        return secureConnection;
    }

    /**
     * Set the root context for running within an app server.
     * 
     * @param rootContext
     *            The context that is prepended before the REST URI
     */
    public void setRootContext(String rootContext) {
        if (rootContext != null) {
            this.rootContext = rootContext;
        }
    }

    /**
     * Return the root context for running within an app server.
     * 
     * @return The context that is prepended before the REST URI
     */
    public String getRootContext() {
        return rootContext;
    }

    /**
     * @param f
     * @return RestResponse
     * @throws HttpException
     * @throws IOException
     */
	public RestResponse send(final Call f) throws RemoteException {
		RestResponse r = f.apply();
		// if authorization failed because of invalid session token, we can
		// retry
//		if (r.getCode() == HttpStatus.SC_UNAUTHORIZED) {
//			if (remoteClient.getContext() != null) {
//				remoteClient.refresh();
//				r = f.apply();
//
//				if (r.getCode() == HttpStatus.SC_UNAUTHORIZED) {
//					throw new RuntimeException("SECURITY_AUTHENTICATION_FAIL");
//				}
//				remoteClient.extractRequestToken(r);
//			} else {
//				throw new RuntimeException("SECURITY_AUTHENTICATION_FAIL");
//			}
//		}
//		remoteClient.extractReplyNonce(r);
		return r;
	}

    /**
     * @param headers
     * @return Map<String, String>
     */
    Map<String, String> extractHeaders(Header[] headers) {
        if (headers == null) {
            return null;
        }
        Map<String, String> result = new HashMap<String, String>();
        for (Header header : headers) {
            result.put(header.getName(), header.getValue());
        }
        return result;
    }

}
