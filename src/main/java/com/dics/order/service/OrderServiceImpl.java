package com.dics.order.service;

import java.util.UUID;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dics.order.jms.RetryQueueService;
import com.dics.order.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	RetryQueueService rqService;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Override
	public Order ingresarOrder(Order order) throws JMSException, JsonProcessingException {
		
		String rqUID=getRqId();
		order.setRqUID(rqUID);
		rqService.sendQueue(javaSerializedToXmlStr(order), rqUID);
		logger.debug("rqUID {}",rqUID);
		
		
		
		return order;
	}
	
	
	public String javaSerializedToXmlStr(Order order) throws JsonProcessingException {
	    XmlMapper xmlMapper = new XmlMapper();
	    String xml = xmlMapper.writeValueAsString(order);
	   
	    return xml;
	}
	
	private String getRqId() {
		String rqId = "";

		UUID uuid = UUID.randomUUID();
		rqId = uuid.toString();

		return rqId;
	}
	

}
