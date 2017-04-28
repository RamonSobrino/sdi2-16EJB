package uo.sdi.business.impl.responder;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import uo.sdi.business.MessageResponderService;
import uo.sdi.dto.Task;

@Stateless
public class EjbMessageResponderService implements MessageResponderService{

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;
	
	@Resource private SessionContext ctx;
	private Destination destino;
	
	
	private Connection con  ;
	private Session session;
	
	@Override
	public void sendError(Message msg, String tipoError) throws JMSException {
		this.destino =  msg.getJMSReplyTo();
		
		con = factory.createConnection();
		this.session = con.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		MessageProducer sender = session.createProducer(destino);
		
		TextMessage mensaje = this.session.createTextMessage();
		mensaje.setText("Un Error se ha producido "+ tipoError);
		
		sender.send(mensaje);
	}

	@Override
	public void sendOk(MapMessage msg) throws JMSException {
		this.destino =  msg.getJMSReplyTo();
		
		con = factory.createConnection();
		this.session = con.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		MessageProducer sender = session.createProducer(destino);
		
		TextMessage mensaje = this.session.createTextMessage();
		mensaje.setText("Todo ha ido bien");
		
		sender.send(mensaje);

	}

	@Override
	public void sendList(MapMessage msg, List<Task> lista) throws JMSException {
		this.destino =  msg.getJMSReplyTo();
		
		con = factory.createConnection();
		this.session = con.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		MessageProducer sender = session.createProducer(destino);
		
		ObjectMessage mensaje = 
				this.session.createObjectMessage((Serializable) lista);
		
		sender.send(mensaje);

	}

}
