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

/**
 * 
 */
public class RemoteException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 6531350649091273621L;

    /**
     * 
     */
    public RemoteException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public RemoteException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public RemoteException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public RemoteException(Throwable cause) {
        super(cause);
    }

}
