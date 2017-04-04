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
import uo.sdi.business.impl.command.Command;
import uo.sdi.business.impl.command.CommandExecutor;
import uo.sdi.dto.User;
import uo.sdi.persistence.Persistence;

/**
 * Session Bean implementation class EjbAdminService
 */
@Stateless
@WebService(name="adminService")
public class EjbAdminService implements RemoteAdminService, LocalAdminService {

    /**
     * Default constructor. 
     */
    public EjbAdminService() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public void deepDeleteUser(Long id) throws BusinessException {
		new CommandExecutor<Void>().execute( new DeepDeleteUserCommand( id ) );
	}

	@Override
	public void disableUser(Long id) throws BusinessException {
		new CommandExecutor<Void>().execute( new DisableUserCommand( id ) );
	}

	@Override
	public void enableUser(Long id) throws BusinessException {
		new CommandExecutor<Void>().execute( new EnableUserCommand( id ) );
	}

	@Override
	public List<User> findAllUsers() throws BusinessException {
		return new CommandExecutor<List<User>>().execute(new FindAllUsersCommand());
	}

	@Override
	public User findUserById(final Long id) throws BusinessException {
		return new CommandExecutor<User>().execute( new Command<User>() {
			@Override public User execute() throws BusinessException {
				return Persistence.getUserDao().findById(id);
			}
		});
	}

	@Override
	public void initDataBase() throws BusinessException {
		new CommandExecutor<Void>().execute( new InitDataBaseCommand());		
	}
    
}
