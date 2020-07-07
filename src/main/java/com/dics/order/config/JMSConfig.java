package com.dics.order.config;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;




@Configuration
@ConfigurationProperties("jms")
public class JMSConfig {

	private static final Logger log = LoggerFactory.getLogger(JMSConfig.class);

	// jms.brokerLocal
	private String brokerLocal;

	@PostConstruct
	public void init()
	{
		log.debug("brokerLocal {}",brokerLocal);
	}

	  @Bean
	  @ConditionalOnProperty(name="mode.development", havingValue="LOCAL")
	  public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
	    ActiveMQConnectionFactory activeMQConnectionFactory =
	        new ActiveMQConnectionFactory();
	    activeMQConnectionFactory.setBrokerURL(brokerLocal);

	    return activeMQConnectionFactory;
	  }

	  @Bean
	  @ConditionalOnProperty(name="mode.development", havingValue="LOCAL")
	  public CachingConnectionFactory cachingConnectionFactory() {
	    return new CachingConnectionFactory(
	        senderActiveMQConnectionFactory());
	  }

	  @Bean
	  @ConditionalOnProperty(name="mode.development", havingValue="LOCAL")
	  public JmsTemplate jmsTemplate() {
	    return new JmsTemplate(cachingConnectionFactory());
	  }

	public String getBrokerLocal() {
		return brokerLocal;
	}

	public void setBrokerLocal(String brokerLocal) {
		this.brokerLocal = brokerLocal;
	}
	
	

}
