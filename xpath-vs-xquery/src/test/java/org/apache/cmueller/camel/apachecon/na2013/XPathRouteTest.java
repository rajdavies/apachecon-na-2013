package org.apache.cmueller.camel.apachecon.na2013;

import java.io.FileInputStream;

import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.apache.camel.util.StopWatch;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XPathRouteTest extends CamelSpringTestSupport {
	
	private int repeatCounter = 10000;
	
	@Test
	public void measureXPathExecution() throws Exception {
		template.setDefaultEndpointUri("direct:xpath");
		String paylaod = IOUtils.toString(new FileInputStream("src/test/data/10K_buyStocks.xml"), "UTF-8");

		// warm up
		for (int i = 0; i < repeatCounter; i++) {
			template.sendBody(paylaod);
		}

		getMockEndpoint("mock:xpath").reset();
		getMockEndpoint("mock:xpath").expectedMessageCount(repeatCounter);
		
		StopWatch watch = new StopWatch();
		for (int i = 0; i < repeatCounter; i++) {
			template.sendBody(paylaod);
		}
		watch.stop();
		System.out.println("measureXPathExecution duration: " + watch.taken() + "ms");
		
		assertMockEndpointsSatisfied();
	}
	
	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/bundle-context-xpath.xml");
	}
}
