package com.it.epolice.agent.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scanner {
	private final String folder;
	private final ScanFilter filter;
	public Scanner(String folder) {
		super();
		this.folder = folder;
		this.filter = new ScanFilter();
	}
	
	public List<File> scan() {
		List<File> all = new ArrayList<File>();
		listFolder(all, new File(folder));
		return all;
	}
	
	private void listFolder(List<File> all, File folder) {
		File[] children = folder.listFiles(this.filter);
		if(null != children && children.length > 0) {
			for(File child : children) {
				if(child.isFile()) all.add(child);
				else listFolder(all, child);
			}
		}
		
	}
}
 