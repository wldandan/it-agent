package com.it.epolice.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileApi {

    public void sendFile(String uri, String contentType, InputStream content) throws Exception {
        try {
            Map<String, String> header = new HashMap<String, String>();
            header.put("Content-Type", contentType);
            header.put("Accept-Language", "zh-CN");
            int maxSize = 10 * 1024 * 1024;
            byte[] bytes = extractRawBytes(content, maxSize);
            header.put("Content-Length", Long.toString(bytes.length));
            boolean moreData = content.available() > 0;
            header.put("hasMoreData", moreData ? "T" : "F");

            HttpController http = new HttpController("localhost", 8080, 1);
            String restUri = "/image/syncBinary";
            http.send(new Put(restUri, header, bytes));
//            if (response.getCode() != HttpStatus.SC_OK && response.getCode() != HttpStatus.SC_CREATED) {
//                String message = RemoteHelpers.getResponseMsg(response);
//                LocMsg msg = RemoteHelpers.getLocMsg(response);
//                throw new FileApiException(msg, message, null);
//            }

            // Send the remainder of the InputStream, if chunks are needed
            header.remove("overwrite");
            header.remove("blockSize");
            while (content.available() > 0) {
                bytes = extractRawBytes(content, maxSize);
                header.put("Content-Length", Long.toString(bytes.length));
                moreData = content.available() > 0;
                header.put("hasMoreData", moreData ? "T" : "F");
                String rstUri = "/fs/append/file/";
                http.send(new Put(rstUri + uri, header, bytes));
//                if (response.getCode() != HttpStatus.SC_OK) {
//                    String message = RemoteHelpers.getResponseMsg(response);
//                    LocMsg msg = RemoteHelpers.getLocMsg(response);
//                    throw new FileApiException(msg, message, null);
//                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            content.close();
        }
    }

    private byte[] extractRawBytes(InputStream content, int maxSize) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int buffSize = 32768;
            byte[] buf = new byte[buffSize];

            int bytesToRead = maxSize;
            int size = -1;
            while ((size = content.read(buf, 0, bytesToRead > buffSize ? buffSize : bytesToRead)) > 0) {
                bos.write(buf, 0, size);
                bytesToRead -= size;
            }
            return bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
}
