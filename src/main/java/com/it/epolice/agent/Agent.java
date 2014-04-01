package com.it.epolice.agent;

import java.io.File;
import java.util.List;

import com.it.epolice.agent.exception.SendFileException;
import com.it.epolice.agent.scanner.Scanner;
import com.it.epolice.agent.sender.Sender;

public class Agent implements Runnable {
	private Sender sender;
	private Scanner scanner;
	
	@Override
	public void run() {
		while(true) {
			List<File> files = getScanner().scan();
			if(files.size() == 0) {
				try {
					System.out.println("Sleeping 5s...");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				for(File f : files) {
					try {
						getSender().send(f);
						f.renameTo(new File(f.getAbsolutePath() + ".sent"));
					} catch (SendFileException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	
	
}
