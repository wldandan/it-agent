package com.it.epolice.agent.scanner;

import java.io.File;
import java.io.FileFilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ScanFilter implements FileFilter {
	@Value("${fileSuffix}")
	private String fileSuffix;
	@Value("${scanBuffer}")
	private int scanBuffer;

	@Override
	public boolean accept(File file) {
		return file.getName().toLowerCase().endsWith("." + fileSuffix) && 
				file.lastModified() < System.currentTimeMillis() - scanBuffer * 1000;
	}

//	public String getFileSuffix() {
//		return fileSuffix;
//	}
//
//	public void setFileSuffix(String fileSuffix) {
//		this.fileSuffix = fileSuffix;
//	}
//
//	public int getScanBuffer() {
//		return scanBuffer;
//	}
//
//	public void setScanBuffer(int scanBuffer) {
//		this.scanBuffer = scanBuffer;
//	}
	
}
