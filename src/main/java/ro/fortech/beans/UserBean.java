package ro.fortech.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ro.fortech.model.User;
import ro.fortech.services.UserService;

@ManagedBean
@RequestScoped
public class UserBean {

	@Inject
	private UserService userService;

	private boolean isUsernameValid;
	private boolean isPasswordValid;
	private boolean validationComplete;

	public String checkIfUserPresent(String username, String password) {
		List<User> users = userService.getUsers();
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				this.isUsernameValid = true;
				if (user.getPassword().equals(password)) {
					this.isPasswordValid = true;
				}
			} else if (user.getPassword().equals(password)) {
				this.isPasswordValid = true;
				if (user.getUsername().equals(username)) {
					this.isUsernameValid = true;
				}
			}

		}

		if (!isUsernameValid) {
			FacesContext.getCurrentInstance().addMessage("myform:username",
					new FacesMessage("Invalid", "Invalid Username"));
		}
		if (!isPasswordValid) {
			FacesContext.getCurrentInstance().addMessage("myform:password",
					new FacesMessage("Invalid", "Invalid Password"));
		}

		if (isUsernameValid && isPasswordValid) {
			this.validationComplete = true;
			return "index";
		}

		return "login";
	}

	public boolean isUsernameValid() {
		return isUsernameValid;
	}

	public void setUsernameValid(boolean isUsernameValid) {
		this.isUsernameValid = isUsernameValid;
	}

	public boolean isPasswordValid() {
		return isPasswordValid;
	}

	public void setPasswordValid(boolean isPasswordValid) {
		this.isPasswordValid = isPasswordValid;
	}

	public boolean isValidationComplete() {
		return validationComplete;
	}

	public void setValidationComplete(boolean validationComplete) {
		this.validationComplete = validationComplete;
	}
}
