package com.it.epolice.httpclient;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

class RestResponse {
    private int code;
    private Map<String, String> headers;
    private byte[] content;
    private InputStream contentStream;

    /**
     * @param code
     * @param content
     */
    public RestResponse(int code, byte[] content) {
        this.code = code;
        this.content = content;
    }

    /**
     * @param code
     * @param content
     * @param headers
     */
    public RestResponse(int code, byte[] content, Map<String, String> headers) {
        this.code = code;
        this.content = content;
        this.headers = headers;
    }
    
    /**
     * @param code
     * @param content
     * @param headers
     */
    public RestResponse(int code, InputStream content, Map<String, String> headers) {
        this.code = code;
        this.contentStream = content;
        this.headers = headers;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the content
     */
    public byte[] getContent() {
        return content;
    }
    
    /**
     * @return the content
     */
    public InputStream getContentAsSream() {
        return this.contentStream;
    }

    /**
     * @return the content
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * @return the content
     */
    public String getHeader(String name) {
        return headers == null ? null : headers.get(name);
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        try {
            return "Http: " + code + "\nHeaders: " + headers.toString() + "\nContent:\n" + new String(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // ignore the unsupported encoding that won't be thrown
        }
        return "Http: " + code + "\nHeaders: " + headers.toString() + "\nContent:\n" + new String(content);
    }

}
