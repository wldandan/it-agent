package com.it.epolice.httpclient;

interface Call {
    /**
     * @return RestResponse
     */
    RestResponse apply() throws RemoteException;
}
