package com.dics.order.jms;



import java.util.concurrent.atomic.AtomicReference;
import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSSender {
	
	@Autowired
	private Environment env;
	
	private static final Logger logger = LoggerFactory.getLogger(JMSSender.class);

	@Autowired
	private JmsTemplate jmsTemplate;
	
	

	public String send(String msgRequest,String rqUID) throws JMSException {
		final AtomicReference<Message> message = new AtomicReference<>();
		
		String destino=env.getProperty("jms.destinationName");
		logger.debug("destinationName ='{}' ", destino);
		
		jmsTemplate.convertAndSend(destino, msgRequest, messagePostProcessor -> {
			messagePostProcessor.setJMSCorrelationID(rqUID);
			messagePostProcessor.setJMSType("XML");
			message.set(messagePostProcessor);
			return messagePostProcessor;
		});
		String messageId = message.get().getJMSMessageID();
		logger.debug("sending Message ='{}' to JMS with MessageId='{}'", msgRequest, messageId);
		return messageId;
	}

}
