package uo.sdi.business.impl.login;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.login.command.LoginCommand;
import uo.sdi.dto.User;

/**
 * Session Bean implementation class EjbLoginService
 */
@Stateless
@WebService(name="loginService")
public class EjbLoginService implements RemoteLoginService, LocalLoginService {

    /**
     * Default constructor. 
     */
    public EjbLoginService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public User doLogin(String login, String password) throws BusinessException {
		return new LoginCommand(login, password).execute(); 
	}

}
