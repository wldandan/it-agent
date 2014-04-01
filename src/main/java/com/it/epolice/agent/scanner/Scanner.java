package com.it.epolice.agent.scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Scanner {
	@Value("${scanFolder}")
	private String folder;
	
	@Autowired
	private ScanFilter filter;
	
	public List<File> scan() {
		List<File> all = new ArrayList<File>();
		listFolder(all, new File(folder));
		return all;
	}
	
//	public String getFolder() {
//		return folder;
//	}
//
//	public ScanFilter getFilter() {
//		return filter;
//	}
//
//	public void setFolder(String folder) {
//		this.folder = folder;
//	}
//
//	public void setFilter(ScanFilter filter) {
//		this.filter = filter;
//	}

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
 