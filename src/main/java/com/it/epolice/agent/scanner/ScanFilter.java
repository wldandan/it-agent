package com.it.epolice.agent.scanner;

import java.io.File;
import java.io.FileFilter;

public class ScanFilter implements FileFilter {
	private final static String FILE_SUFFIX = ".jpg";
	private final static int CREATION_BUFFER_IN_SECOND = 60;

	@Override
	public boolean accept(File file) {
		return file.getName().toLowerCase().endsWith(FILE_SUFFIX) && 
				file.lastModified() < System.currentTimeMillis() - CREATION_BUFFER_IN_SECOND * 1000;
	}

}
