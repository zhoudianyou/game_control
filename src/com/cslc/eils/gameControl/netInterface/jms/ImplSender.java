package com.cslc.eils.gameControl.netInterface.jms;
import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 
 */

/**
 * @author tianhao
 *
 */
public class ImplSender implements ISender {

	private JmsTemplate jmsTemplate;
	
	@Override
	public void sendMessage(final Object obj,final int jmsType) {
		System.out.println("---------------�����߷�����Ϣ-----------------");  
        System.out.println("---------------�����߷���һ����Ϣ����Ϣ����Ϊ��" + jmsType); 
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objMsg = session.createObjectMessage();
				objMsg.setJMSType(String.valueOf(jmsType));
				objMsg.setObject((Serializable) obj);
				return objMsg;
			}
		});

	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
