package com.it.epolice.agent;

import java.io.FileInputStream;

import com.it.epolice.httpclient.FileApi;

public class Sender {
	public static void main(String args[]) throws Exception {
		String file = "Sample/flower.JPG";
		FileInputStream is = new FileInputStream(file);
		new FileApi().sendFile("1.jpg", "image/JPEG", is);
	}
}
