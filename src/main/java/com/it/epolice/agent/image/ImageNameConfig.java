package com.it.epolice.agent.image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageNameConfig {
	private final static String Key_pattern = "pattern";
	private final static String Key_cameraId = "cameraId";
	private final static String Key_lane = "lane";
	private final static String Key_time = "time";
	private final static String Key_timePattern = "timePattern";
	private final static String Key_carNumber = "carNumber";
	private final static String Key_regulationDescription = "regulationDescription";
	private final static String Key_regulationType = "regulationType";
	
	private final Properties config;
	private final Pattern pattern;
	
	private static ImageNameConfig instance;
	private ImageNameConfig(Properties config) {
		this.config = config;
		this.pattern = Pattern.compile(this.config.getProperty(Key_pattern));
	}
	
	public static ImageNameConfig load() {
		if(null == instance) {
			Properties p = new Properties();
			try {
				p.load(new InputStreamReader(new FileInputStream("src/main/resources/picture_rule_config.properties"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			instance = new ImageNameConfig(p);
		}
		return instance;
	}
	
	public ImageInfo parse(String name) {
		Matcher matcher = pattern.matcher(name);
		if(matcher.matches()) {
			ImageInfo info = new ImageInfo();
			info.setPicName(name);
			info.setCamId(getStringValue(matcher, Key_cameraId));
			info.setCarNumber(getStringValue(matcher, Key_carNumber));
			info.setLane(getStringValue(matcher, Key_lane));
			info.setRegulationDesc(getStringValue(matcher, Key_regulationDescription));
			info.setRegulationType(getStringValue(matcher, Key_regulationType));
			String timeValue = getStringValue(matcher, Key_time);
			String timePatternValue = (String) config.get(Key_timePattern);
			if(null != timeValue && null != timePatternValue) {
				try {
					info.setTime(new SimpleDateFormat(timePatternValue).parse(timeValue));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return info;
		}
		return null;
	}
	
	private String getStringValue(Matcher matcher, String key) {
		String value = (String)this.config.get(key);
		return null == value ? null : matcher.group(Integer.parseInt(value));
	}
	
}
