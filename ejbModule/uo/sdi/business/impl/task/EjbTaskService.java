package uo.sdi.business.impl.task;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.task.command.CreateCategoryCommand;
import uo.sdi.business.impl.task.command.CreateTaskCommand;
import uo.sdi.business.impl.task.command.DeleteCategoryCommand;
import uo.sdi.business.impl.task.command.DuplicateCategoryCommand;
import uo.sdi.business.impl.task.command.MarkTaskAsFinishedCommand;
import uo.sdi.business.impl.task.command.UpdateCategoryCommand;
import uo.sdi.business.impl.task.command.UpdateTaskCommand;
import uo.sdi.dto.Category;
import uo.sdi.dto.Task;
import uo.sdi.persistence.Persistence;

/**
 * Session Bean implementation class EjbTaskService
 */
@Stateless
@WebService(name="taskService")
public class EjbTaskService implements RemoteTaskService, LocalTaskService {

	/**
	 * Default constructor.
	 */
	public EjbTaskService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long createCategory(Category category) throws BusinessException {
		return new CreateCategoryCommand(category).execute();

	}

	@Override
	public Long duplicateCategory(Long id) throws BusinessException {
		return new DuplicateCategoryCommand(id).execute();
	}

	@Override
	public void updateCategory(Category category) throws BusinessException {
		new UpdateCategoryCommand(category).execute();
	}

	@Override
	public void deleteCategory(Long catId) throws BusinessException {
		new DeleteCategoryCommand(catId).execute();
	}

	@Override
	public Category findCategoryById(final Long id) throws BusinessException {
		return Persistence.getCategoryDao().findById(id);
	}

	@Override
	public List<Category> findCategoriesByUserId(final Long id)
			throws BusinessException {
		return Persistence.getCategoryDao().findByUserId(id);
	}

	@Override
	public Long createTask(Task task) throws BusinessException {
		return new CreateTaskCommand(task).execute();
	}

	@Override
	public void deleteTask(final Long id) throws BusinessException {
		Persistence.getTaskDao().delete(id);
	}

	@Override
	public void markTaskAsFinished(Long id) throws BusinessException {
		new MarkTaskAsFinishedCommand(id).execute();
	}

	@Override
	public void updateTask(Task task) throws BusinessException {
		new UpdateTaskCommand(task).execute();
	}

	@Override
	public Task findTaskById(final Long id) throws BusinessException {
		return Persistence.getTaskDao().findById(id);
	}

	@Override
	public List<Task> findInboxTasksByUserId(final Long id)
			throws BusinessException {
		return Persistence.getTaskDao().findInboxTasksByUserId(id);
	}

	@Override
	public List<Task> findWeekTasksByUserId(final Long id)
			throws BusinessException {
		return Persistence.getTaskDao().findWeekTasksByUserId(id);
	}

	@Override
	public List<Task> findTodayTasksByUserId(final Long id)
			throws BusinessException {
		return Persistence.getTaskDao().findTodayTasksByUserId(id);
	}

	@Override
	public List<Task> findTasksByCategoryId(final Long id)
			throws BusinessException {
		return Persistence.getTaskDao().findTasksByCategoryId(id);
	}

	@Override
	public List<Task> findFinishedTasksByCategoryId(final Long id)
			throws BusinessException {
		return Persistence.getTaskDao().findFinishedTasksByCategoryId(id);
	}

	@Override
	public List<Task> findFinishedInboxTasksByUserId(final Long id) {
		return Persistence.getTaskDao().findFinishedTasksInboxByUserId(id);
	}

	@Override
	public List<Task> findUnfinishedTasksByUserId(final Long userId)
			throws BusinessException {
		return Persistence.getTaskDao().findUnfinishedTasksByUserId(userId);
	}

	
}
