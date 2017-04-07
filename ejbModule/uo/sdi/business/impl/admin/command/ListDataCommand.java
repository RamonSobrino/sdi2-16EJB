package uo.sdi.business.impl.admin.command;

import java.util.ArrayList;
import java.util.List;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.command.Command;
import uo.sdi.dto.User;
import uo.sdi.dto.UserData;
import uo.sdi.persistence.Persistence;
import uo.sdi.persistence.UserDao;

public class ListDataCommand implements Command<List<UserData>> {

	@Override
	public List<UserData> execute() throws BusinessException {
		
		List<User> usuarios = Persistence.getUserDao().findAll();
		List<UserData> retorno = new ArrayList<UserData>();
		
		Long numTareasCompletadas;
		Long numTareasRetrasadas;
		Long numTareasPlanificadas;
		Long numTareasSinPlanificadas;
		
		for(User u: usuarios)
		{
			numTareasCompletadas = Persistence.getUserDao().
					numTareasCompletadas(u);
			numTareasRetrasadas = Persistence.getUserDao().
					numTareasRetrasadas(u);
			numTareasPlanificadas = Persistence.getUserDao().
					numTareasPlanificadas(u);
			numTareasSinPlanificadas = Persistence.getUserDao().
					numTareasSinPlanificadas(u);
			
			UserData ud = new UserData(u.getId(), u.getLogin(), 
					numTareasCompletadas, numTareasRetrasadas, 
					numTareasPlanificadas,
					numTareasSinPlanificadas);
			
			retorno.add(ud);
		}
		
		
		return retorno;
	}

}
