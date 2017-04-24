package uo.sdi.business.impl.user;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.user.command.AddUserCommand;
import uo.sdi.business.impl.user.command.FindLoggableUSerCommand;
import uo.sdi.business.impl.user.command.FindUserCommand;
import uo.sdi.business.impl.user.command.RegisterUserCommand;
import uo.sdi.business.impl.user.command.UpdateUserDetailsCommand;
import uo.sdi.dto.User;

/**
 * Session Bean implementation class EjbUserService
 */
@Stateless
@WebService(name="userService")
public class EjbUserService implements RemoteUserService, LocalUserService {

	/**
	 * Default constructor.
	 */
	public EjbUserService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long registerUser(User user) throws BusinessException {
		return new RegisterUserCommand(user).execute();
	}

	@Override
	public void updateUserDetails(User user) throws BusinessException {
		new UpdateUserDetailsCommand(user).execute();
	}

	@Override
	public User findLoggableUser(final String login, final String password)
			throws BusinessException {
		return new FindLoggableUSerCommand<User>(login, password).execute();
	}

	@Override
	public User findUser(Long id) throws BusinessException {
		return new FindUserCommand<User>(id).execute();
	}

	@Override
	public Long addUser(User user) throws BusinessException {
		return new AddUserCommand(user).execute();
	}

}
