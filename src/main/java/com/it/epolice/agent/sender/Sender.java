package com.it.epolice.agent.sender;

import java.io.File;
import java.io.FileInputStream;

import com.it.epolice.agent.exception.SendFileException;
import com.it.epolice.httpclient.FileApi;

public class Sender {
	private FileApi fileApi;
	
	public void send(File file) throws SendFileException  {
		try {
			FileInputStream is = new FileInputStream(file);
			getFileApi().sendFile(file.getName(), getContentType(file.getName()), is);
		} catch (Exception e) {
			throw new SendFileException(e);
		}
	}

	public FileApi getFileApi() {
		return fileApi;
	}

	public void setFileApi(FileApi fileApi) {
		this.fileApi = fileApi;
	}
	
	private String getContentType(String fileName) {
		if(fileName.toLowerCase().endsWith(".jpg")) return "image/JPEG";
		else return "application/binary";
	}
}
