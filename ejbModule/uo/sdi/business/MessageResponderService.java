package uo.sdi.business;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import uo.sdi.dto.Task;

public interface MessageResponderService {
	
	public void sendError(Message msg, String tipoError) throws JMSException;
	public void sendOk(MapMessage msg) throws JMSException;
	public void sendList(MapMessage msg, List<Task> lista) throws JMSException;


}
