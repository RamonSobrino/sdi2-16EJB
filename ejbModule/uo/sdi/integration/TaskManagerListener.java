package uo.sdi.integration;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import uo.sdi.business.LoginService;
import uo.sdi.business.MessageResponderService;
import uo.sdi.business.TaskService;
import uo.sdi.business.UserService;
import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.login.LocalLoginService;
import uo.sdi.business.impl.responder.EjbMessageResponderService;
import uo.sdi.business.impl.task.LocalTaskService;
import uo.sdi.business.impl.user.LocalUserService;
import uo.sdi.dto.Task;
import uo.sdi.dto.User;

@MessageDriven(
		activationConfig = {
				@ActivationConfigProperty(
						propertyName = "destination",
						propertyValue = "queue/TaskManager")
		})
public class TaskManagerListener implements MessageListener {

	private User user;
	
	@EJB(beanInterface = LocalTaskService.class)
	private TaskService taskService;
	
	@EJB(beanInterface = LocalLoginService.class)
	private LoginService loginService;
	
	@EJB
	private MessageResponderService responder;
	
	@Override
	public void onMessage(Message msg) {
		try {
			process(msg);
		} catch (JMSException jex) {
			// here we should log the exception
			//jex.printStackTrace();
			try {
				responder.sendError(msg, "Ha ocurrido un error en el envio");
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			try {
				responder.sendError(msg, e.getMessage());
			} catch (JMSException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}

	private void process(Message m) throws JMSException, BusinessException {
		if (!messageOfExpectedType(m)) { 
			System.out.println("Not of expected type " + m);
			return;
		}
		MapMessage msg = (MapMessage) m;
		if(!doComprobarUser(msg))
		{
			responder.sendError(msg, "Usuario incorrecto");
			return;
		}
		String cmd = msg.getString("command");
		switch(cmd)
		{
			case "TareasHoy":
				doTareasHoy(msg);
				break;
			case "TareasRetrasadas":
				doTareasRetrasadas(msg);
				break;
			case "FinalizarTarea":
				doFinalizarTarea(msg);
				break;
			case "NuevaTarea":
				doNuevaTarea(msg);
				break;
		}
	}

	private boolean doComprobarUser(MapMessage msg) 
			throws JMSException, BusinessException {
		String user = msg.getString("user");
		String password = msg.getString("password");
		
		this.user = loginService.doLogin(user, password);
		
		if(this.user==null)
		{
			return false;
		}else{
			return true;
		}
	}

	private void doNuevaTarea(MapMessage msg) 
			throws JMSException, BusinessException {
		Task tarea = new Task();

		String title = msg.getString("title");
		
		if(!msg.getString("idCat").equals("null"))
		{
			Long idCat = Long.valueOf(msg.getString("idCat"));
			tarea.setCategoryId(idCat);
		}	
		String comment = msg.getString("comment");
		
		tarea.setTitle(title);
		tarea.setComments(comment);
		tarea.setUserId(this.user.getId());
				
		this.taskService.createTask(tarea);
		
		this.responder.sendOk(msg);
	}

	private void doFinalizarTarea(MapMessage msg) 
			throws NumberFormatException, JMSException, BusinessException {
		Long id = Long.valueOf(msg.getString("id"));
		
		this.taskService.markTaskAsFinished(id);
		
		this.responder.sendOk(msg);
	}

	private void doTareasRetrasadas(MapMessage msg) 
			throws BusinessException, JMSException {
		List<Task> lista = this.taskService.
				findUnfinishedTasksByUserId(this.user.getId());
		this.responder.sendList(msg, lista);
		}

	private void doTareasHoy(MapMessage msg) 
			throws BusinessException, JMSException {
		List<Task> lista = this.taskService.
				findTodayTasksByUserId(this.user.getId());
		this.responder.sendList(msg, lista);
	}

	private boolean messageOfExpectedType(Message m) {
		return m instanceof MapMessage;
	}

}
