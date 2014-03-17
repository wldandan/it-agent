/**
 * IBM Confidential
 * 
 * OCO Source Materials
 * 
 * IBM SPSS Products: Analytic Server
 * (c) Copyright IBM Corp. 2012
 * 
 * The source code for this program is not published or otherwise divested of
 * its trade secrets, irrespective of what has been deposited with the U.S.
 * Copyright Office.
 */

package com.it.epolice.httpclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;

/**
 * 
 */
public abstract class MethodBase implements Call {
    protected String uri;
    protected String params;
    protected Map<String, String> header;
    private boolean isStream;

    public MethodBase(String uri, Map<String, String> header, String params) {
        super();
        this.uri = uri;
        this.header = header;
        this.params = params;
    }

    /**
     * @param method
     * @param header
     */
    protected void setHeaders(HttpMethod method, Map<String, String> header) {
        Iterator<Map.Entry<String, String>> it = header.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            String headerValue = e.getValue();
            if (headerValue != null) {
                try {
                    headerValue = URLEncoder.encode(headerValue, "UTF-8");
                } catch (UnsupportedEncodingException uee) {
                    // DO NOTHING
                }
            }
            method.addRequestHeader(e.getKey(), headerValue);
        }
    }

//    /**
//     * @param method
//     * @param client
//     */
//    protected void setContext(HttpMethod method, RemoteClient client) {
//        String requestToken = client.getRequestToken();
//        if (requestToken != null) {
//            method.addRequestHeader("request-token", requestToken);
//            if (client.getReplyNonce() != null) {
//                method.addRequestHeader("reply-nonce", client.getReplyNonce());
//            }
//        } else {
//            setHeaders(method, client.getAuthenticationHeaders());
//        }
//    }

    /**
     * Sets responseBody content as stream.
     */
    protected void setStreamMode() {
        this.isStream = true;
    }

    /**
     * Indicates if response body is stream or byte[].  True if it is stream.
     * 
     * @return
     */
    protected boolean isStreamMode() {
        return isStream;
    }

    /**
     * To release connection back to the HTTP connection manager.
     * 
     * @param method
     */
    protected void release(HttpMethod method) {
        if (method != null) {
            method.releaseConnection();
            method = null;
        }
    }

    /**
     * Makes a full URL, including secure connection, server, port, root context, and the provided URI.
     * 
     * @param controller
     *            The HttpController
     * @param uri
     *            The AE REST URI
     * @return A full URL for contacting the AE server
     */
    protected String makeURL(HttpController controller, String uri) {
        String protocol = controller.isSecureConnection() ? "https" : "http";
        String rootContext = controller.getRootContext();

        String prefixedUri = uri.startsWith("/") ? uri : "/" + uri;
        prefixedUri = rootContext + prefixedUri;
        if (prefixedUri.startsWith("/")) {
            prefixedUri = prefixedUri.substring(1);
        }
        try {
            prefixedUri = URLEncoder.encode(prefixedUri, "UTF-8");
            if (params != null) {
                prefixedUri += params;
            }
        } catch (UnsupportedEncodingException e) {
            // DO Nothing
        }
        return protocol + "://" + controller.server + ":" + controller.port + "/" + prefixedUri;
    }

    /**
     * Execute a method.
     * 
     * @param method
     *            The method to execute
     * @param controller
     *            HttpController
     * @return A RestResponse
     * @throws IOException
     * @throws HttpException
     */
    protected RestResponse executeMethod(HttpMethodBase method, HttpController controller) throws IOException, HttpException {
        int status = controller.client.executeMethod(method);
        if (status != HttpStatus.SC_OK && status != HttpStatus.SC_CREATED) {
            Map<String, String> extractHeaders = controller.extractHeaders(method.getResponseHeaders());
            if (status == HttpStatus.SC_UNAUTHORIZED) {
                return new RestResponse(status, method.getResponseBody(), extractHeaders);
            }
            String msg = method.getResponseBodyAsString();
            if (msg == null || msg.length() == 0) {
                msg = "Http error " + status;
                throw new HttpException(msg);
            }
            String contentType = extractHeaders.get("Content-Type");
            if (contentType.equalsIgnoreCase("application/json; charset=utf-8")) {
                return new RestResponse(method.getStatusCode(), method.getResponseBody(), extractHeaders);
            }
            throw new HttpException(msg);
        }
        if (isStream) {
            return new RestResponse(method.getStatusCode(),
                method.getResponseBodyAsStream(),
                controller.extractHeaders(method.getResponseHeaders()));
        } else {
            return new RestResponse(method.getStatusCode(),
                method.getResponseBody(),
                controller.extractHeaders(method.getResponseHeaders()));
        }
    }
}
