package com.it.epolice.agent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AgentMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-agent-context.xml");
		Agent agent = (Agent) context.getBean("agent");
		new Thread(agent).start();
	}

}
