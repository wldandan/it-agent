package com.it.epolice.agent;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Test;

import com.it.epolice.agent.image.ImageInfo;
import com.it.epolice.agent.image.ImageNameConfig;

public class ImageInfoTest {

	@Test
	public void testSimpleGoodPassOfParse() {
		String picName = "����017����B12014��03��13��01ʱ30��30��RX09D1H1ʻ������Υ�³���ʱ��61����_S";
		ImageInfo info = ImageNameConfig.load().parse(picName);
		assertEquals("017", info.getCamId());
		assertEquals("B1", info.getLane());
		assertEquals("2014-03-13 01:30:30", new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(info.getTime()));
		assertEquals("RX09D1H1", info.getCarNumber());
		assertEquals("ʻ������Υ�³���ʱ��61����", info.getRegulationDesc());
		assertEquals("S", info.getRegulationType());
	}

}
