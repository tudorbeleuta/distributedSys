package model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {

	@Id
	private String id;
	private String username;
	// TODO: encryption!
	private String password;
	private UserRole role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role.toString();
	}

	public void setRole(String role) throws Exception {

		this.role = UserRole.valueOf(role);
	}

}
