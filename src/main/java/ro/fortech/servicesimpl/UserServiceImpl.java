package ro.fortech.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import ro.fortech.model.User;
import ro.fortech.services.UserService;

@Stateless
public class UserServiceImpl implements UserService{

	private List<User> users = new ArrayList<>();

	public List<User> getUsers() {
		initUserList();
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	private void initUserList(){
		User user = new User();
		user.setCountry("Romania");
		user.setPassword("admin");
		user.setUsername("admin");
		
		User user1 = new User();
		user1.setCountry("Romania");
		user1.setPassword("pass");
		user1.setUsername("lucian");
		
		User user2 = new User();
		user2.setCountry("Romania");
		user2.setPassword("pass");
		user2.setUsername("dani");
		
		users.add(user);
		users.add(user1);
		users.add(user2);
	}

	@Override
	public User getCar(int idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		
	}
	
}
