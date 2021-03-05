package goygovs.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String taskProject;
	private String usersTask;
	private String firstName;
	private String lastName;

	protected User() {
	}

	public User(String taskProject, String usersTask, String firstName, String lastName) {
		this.taskProject = taskProject;
		this.usersTask = usersTask;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public String getTaskProject(){
		return taskProject;
	}

	public void setTaskProject(String taskProject){
		this.taskProject = taskProject;
	}

	public String getUsersTask(){
		return usersTask;
	}

	public void setUsersTask(String usersTask){
		this.usersTask = usersTask;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, taskProject='%s', usersTask='%s', firstName='%s', lastName='%s']", id, taskProject, usersTask,
				firstName, lastName);
	}

}