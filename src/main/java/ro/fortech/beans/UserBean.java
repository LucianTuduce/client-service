package ro.fortech.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import javax.inject.Inject;

import ro.fortech.model.User;
import ro.fortech.services.UserService;

@ManagedBean
@RequestScoped
public class UserBean {

	@Inject
	private UserService userService;
	
	public boolean checkIfUserPresent(String name){
		List<User> users = userService.getUsers();
		for (User user: users){
			if(user.getUsername().equals(name)){
				return true;
			}
		}
		return false;
	}
	
}
