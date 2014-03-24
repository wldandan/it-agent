package com.it.epolice.agent.image;

import java.util.Properties;

public class ImageNameConfig {
	private final static String Key_pattern = "pattern";
	private final static String Key_cameraId = "cameraId";
	private final static String Key_lane = "lane";
	private final static String Key_time = "time";
	private final static String Key_carNumber = "carNumber";
	private final static String Key_regulationDescription = "regulationDescription";
	private final static String regulationType = "regulationType";
	
	private final Properties config;
	
	private static ImageNameConfig instance;
	private ImageNameConfig() {
		
	}
	
	public static ImageNameConfig load() {
		if(null == instance) {
			instance = new ImageNameConfig();
			instance.config = new Properties();
			instance.config.load();
		}
	}
}
