package com.it.epolice.agent;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Test;

import com.it.epolice.agent.image.ImageInfo;
import com.it.epolice.agent.image.ImageNameConfig;

public class ImageInfoTest {

	@Test
	public void testSimpleGoodPassOfParse() {
		String picName = "机号017车道B12014年03月13日01时30分30秒RX09D1H1驶向北至南违章超速时速61公里_S";
		ImageInfo info = ImageNameConfig.load().parse(picName);
		assertEquals("017", info.getCamId());
		assertEquals("B1", info.getLane());
		assertEquals("2014-03-13 01:30:30", new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(info.getTime()));
		assertEquals("RX09D1H1", info.getCarNumber());
		assertEquals("驶向北至南违章超速时速61公里", info.getRegulationDesc());
		assertEquals("S", info.getRegulationType());
	}

}
