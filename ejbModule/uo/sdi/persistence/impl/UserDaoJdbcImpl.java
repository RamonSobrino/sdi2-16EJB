package uo.sdi.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import uo.sdi.dto.User;
import uo.sdi.dto.types.UserStatus;
import uo.sdi.persistence.UserDao;
import uo.sdi.persistence.util.JdbcTemplate;
import uo.sdi.persistence.util.RowMapper;

import uo.sdi.persistence.impl.TaskDaoJdbcImpl;

public class UserDaoJdbcImpl implements UserDao {

	public class UserMapper implements RowMapper<User> {
		@Override
		public User toObject(ResultSet rs) throws SQLException {
			return new User()
				.setId(  		rs.getLong("id") )
				.setLogin(  	rs.getString("login") )
				.setPassword(  	rs.getString("password") )
				.setEmail(  	rs.getString("email") )
				.setIsAdmin( 	rs.getBoolean("isAdmin") )
				.setStatus(  	UserStatus.valueOf( rs.getString("status") ));
		}
	}
	
	private	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public Long save(User dto) {
		jdbcTemplate.execute("USER_INSERT", 
				dto.getLogin(), 
				dto.getPassword(), 
				dto.getEmail(),
				dto.getIsAdmin(),
				toStringOrNull( dto.getStatus() )
			);
		return jdbcTemplate.getGeneratedKey();
	}

	private String toStringOrNull(Object obj) {
		return obj == null ? null : obj.toString();
	}

	@Override
	public int update(User dto) {
		return jdbcTemplate.execute("USER_UPDATE", 
				dto.getLogin(), 
				dto.getPassword(), 
				dto.getEmail(),
				dto.getIsAdmin(),
				toStringOrNull( dto.getStatus() ),
				dto.getId()
			);
	}

	@Override
	public int delete(Long id) {
		return jdbcTemplate.execute("USER_DELETE", id);
	}

	@Override
	public User findById(Long id) {
		return jdbcTemplate.queryForObject(
				"USER_FIND_BY_ID", 
				new UserMapper(), 
				id
			);
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.queryForList("USER_FIND_ALL", new UserMapper());
	}

	@Override
	public User findByLogin(String login) {
		return jdbcTemplate.queryForObject(
				"USER_FIND_BY_LOGIN", 
				new UserMapper(), 
				login
			);
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		return jdbcTemplate.queryForObject(
				"USER_FIND_BY_LOGIN_AND_PASSWORD", 
				new UserMapper(), 
				login, password
			);
	}

	@Override
	public void deleteAllUsers() {
		jdbcTemplate.execute("USER_DELETE_ALL");
	}

	@Override
	public Long numTareasCompletadas(User u) {
		return (long) jdbcTemplate.executeQuery("NUMBER_TASKS_FINISHED_BY_USER", u.getId());
	}

	@Override
	public Long numTareasRetrasadas(User u) {
		return  (long) jdbcTemplate.executeQuery("NUMBER_TASKS_UNFINISHED_BY_USER", u.getId());
	}

	@Override
	public Long numTareasPlanificadas(User u) {
		return (long) jdbcTemplate.executeQuery("NUMBER_TASKS_PLANNED_BY_USER", u.getId());
	}

	@Override
	public Long numTareasSinPlanificadas(User u) {
		return (long) jdbcTemplate.executeQuery("NUMBER_TASKS_UNPLANNED_BY_USER", u.getId());
	}

}
