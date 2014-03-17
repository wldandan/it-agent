package com.it.epolice.httpclient;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PutMethod;

import com.it.epolice.utility.io.IOUtils;


public class Put extends MethodBase {
    private byte[] body;

    /**
     * 
     * @param uri
     * @param header
     * @param body
     */
    public Put(String uri, Map<String, String> header, byte[] body) {
        super(uri, header, null);
        this.body = body;
    }

    /**
     * Need to update large content, using this method.
     * 
     * @param uri
     * @param header
     * @param body
     */
    public Put(String uri, Map<String, String> header, InputStream body) {
        super(uri, header, null);
        if (body != null) {
            this.body = IOUtils.toRawBytes(body, false);
        }
    }

    public Put(String uri, Map<String, String> header) {
        super(uri, header, null);
    }

    @Override
    public RestResponse apply() throws RemoteException {
        PutMethod method = null;

        try {
//            RemoteClient client = ClientScope.get().getValue();
            HttpController controller = new HttpController();
            method = new PutMethod(makeURL(controller, uri));
            if (header != null) {
                setHeaders(method, header);
            }

//            setContext(method, client);

            if (body != null) {
                method.setRequestEntity(new ByteArrayRequestEntity(body));
            }

            return executeMethod(method, controller);
        } catch (Exception e) {
            throw new RemoteException(e);
        } finally {
            release(method);
        }
    }
}
