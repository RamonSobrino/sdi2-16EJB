package uo.sdi.dto;

public class UserData {
	
	private Long id;

	private String login;
	
	private Long numTareasCompletadas;
	private Long numTareasRetrasadas;
	private Long numTareasPlanificadas;
	private Long numTareasSinPlanificadas;
	
	public UserData(Long id, String login, Long numTareasCompletadas,
			Long numTareasRetrasadas, Long numTareasPlanificadas,
			Long numTareasSinPlanificadas) {
		super();
		this.id = id;
		this.login = login;
		this.numTareasCompletadas = numTareasCompletadas;
		this.numTareasRetrasadas = numTareasRetrasadas;
		this.numTareasPlanificadas = numTareasPlanificadas;
		this.numTareasSinPlanificadas = numTareasSinPlanificadas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getNumTareasCompletadas() {
		return numTareasCompletadas;
	}

	public void setNumTareasCompletadas(Long numTareasCompletadas) {
		this.numTareasCompletadas = numTareasCompletadas;
	}

	public Long getNumTareasRetrasadas() {
		return numTareasRetrasadas;
	}

	public void setNumTareasRetrasadas(Long numTareasRetrasadas) {
		this.numTareasRetrasadas = numTareasRetrasadas;
	}

	public Long getNumTareasPlanificadas() {
		return numTareasPlanificadas;
	}

	public void setNumTareasPlanificadas(Long numTareasPlanificadas) {
		this.numTareasPlanificadas = numTareasPlanificadas;
	}

	public Long getNumTareasSinPlanificadas() {
		return numTareasSinPlanificadas;
	}

	public void setNumTareasSinPlanificadas(Long numTareasSinPlanificadas) {
		this.numTareasSinPlanificadas = numTareasSinPlanificadas;
	}

	
}
