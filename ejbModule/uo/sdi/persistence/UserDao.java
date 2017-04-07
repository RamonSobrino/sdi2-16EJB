package uo.sdi.persistence;

import uo.sdi.dto.User;
import uo.sdi.persistence.util.GenericDao;

public interface UserDao extends GenericDao<User, Long>{

	User findByLogin(String login);
	User findByLoginAndPassword(String login, String password);
	
	void deleteAllUsers();
	
	Long numTareasCompletadas(User u);
	Long numTareasRetrasadas(User u);
	Long numTareasPlanificadas(User u);
	Long numTareasSinPlanificadas(User u);

	
}
