package com.it.epolice.agent.scanner;

import java.io.File;
import java.io.FileFilter;

public class ScanFilter implements FileFilter {
	private String fileSuffix;
	private int scanBuffer;

	@Override
	public boolean accept(File file) {
		return file.getName().toLowerCase().endsWith("." + fileSuffix) && 
				file.lastModified() < System.currentTimeMillis() - scanBuffer * 1000;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public int getScanBuffer() {
		return scanBuffer;
	}

	public void setScanBuffer(int scanBuffer) {
		this.scanBuffer = scanBuffer;
	}
	
}
