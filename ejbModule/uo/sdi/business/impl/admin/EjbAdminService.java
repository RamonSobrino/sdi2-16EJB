package uo.sdi.business.impl.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.admin.command.DeepDeleteUserCommand;
import uo.sdi.business.impl.admin.command.DisableUserCommand;
import uo.sdi.business.impl.admin.command.EnableUserCommand;
import uo.sdi.business.impl.admin.command.FindAllUsersCommand;
import uo.sdi.business.impl.admin.command.InitDataBaseCommand;
import uo.sdi.business.impl.admin.command.ListDataCommand;
import uo.sdi.dto.User;
import uo.sdi.dto.UserData;
import uo.sdi.persistence.Persistence;

/**
 * Session Bean implementation class EjbAdminService
 */
@Stateless
@WebService(name = "adminService")
public class EjbAdminService implements RemoteAdminService, LocalAdminService {

	/**
	 * Default constructor.
	 */
	public EjbAdminService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deepDeleteUser(Long id) throws BusinessException {
		new DeepDeleteUserCommand(id).execute();
	}

	@Override
	public void disableUser(Long id) throws BusinessException {
		new DisableUserCommand(id).execute();
	}

	@Override
	public void enableUser(Long id) throws BusinessException {
		new EnableUserCommand(id).execute();
	}

	@Override
	public List<User> findAllUsers() throws BusinessException {
		return new FindAllUsersCommand().execute();
	}

	@Override
	public User findUserById(final Long id) throws BusinessException {
		return Persistence.getUserDao().findById(id);
	}

	@Override
	public void initDataBase() throws BusinessException {
		new InitDataBaseCommand().execute();
	}

	@Override
	public List<UserData> listData() throws BusinessException {
		return new ListDataCommand().execute();
	}

}
