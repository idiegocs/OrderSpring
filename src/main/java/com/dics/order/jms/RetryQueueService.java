package com.dics.order.jms;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;



@Component
public class RetryQueueService {
	
	   private static final Logger log = LoggerFactory.getLogger(RetryQueueService.class);

	    @Autowired
	    private JMSSender sender;
	    
	    /**
	     * Metodo que realiza el PUT hacia un recurso JMS. Este metodo contiene
	     * configuración de reintentos 3 reintentos cada 3 segundos. En caso de
	     * generarse la Excepción. Se ira por el metodo recovery
	     * 
	     * @param content
	     * @return String
	     * @throws Exception
	     */
	    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(3000))
	    public String sendQueue(String content, String rqUID) throws JMSException {
			
			    log.info("Se realizara el PUT a la JMS");
			    sender.send(content,rqUID);
			    return "Ok";
			
	    }

	    /**
	     * Metodo recover que se encarga de dejar un log mencionando que el registro
	     * debera procesarse manualmente.
	     * 
	     * @param t
	     * @param content
	     * @return String
	     */
	    @Recover
	    public String sendQueueRecovery(Exception t, String content) {
		String recovery = (String.format("Sistema no Disponible. Se debe reintentar(manualmente):  %s", content));
		log.info(recovery);
		return recovery;
	    }

}
