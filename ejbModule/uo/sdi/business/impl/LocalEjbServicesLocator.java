package uo.sdi.business.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uo.sdi.business.AdminService;
import uo.sdi.business.LoginService;
import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TaskService;
import uo.sdi.business.UserService;

public class LocalEjbServicesLocator implements ServicesFactory {

	private static final String ADMIN_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi2-16TODO/" + "sdi2-16EJB/" + "EjbAdminService!"
			+ "uo.sdi.business.impl.admin.LocalAdminService";
	private static final String USER_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi2-16TODO/" + "sdi2-16EJB/" + "EjbUserService!"
			+ "uo.sdi.business.impl.user.LocalUserService";
	private static final String TASK_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi2-16TODO/" + "sdi2-16EJB/" + "EjbTaskService!"
			+ "uo.sdi.business.impl.task.LocalTaskService";
	private static final String LOGIN_SERVICE_JNDI_KEY = "java:global/"
			+ "sdi2-16TODO/" + "sdi2-16EJB/" + "EjbLoginService!"
			+ "uo.sdi.business.impl.login.LocalLoginService";

	@Override
	public AdminService getAdminService() {
		try {
			Context ctx = new InitialContext();
			return (AdminService) ctx.lookup(ADMIN_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public UserService getUserService() {
		try {
			Context ctx = new InitialContext();
			return (UserService) ctx.lookup(USER_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public TaskService getTaskService() {
		try {
			Context ctx = new InitialContext();
			return (TaskService) ctx.lookup(TASK_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public LoginService getLoginService() {
		try {
			Context ctx = new InitialContext();
			return (LoginService) ctx.lookup(LOGIN_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

}
