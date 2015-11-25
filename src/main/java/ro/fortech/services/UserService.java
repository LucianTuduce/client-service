package ro.fortech.services;

import java.util.List;

import ro.fortech.model.User;

public interface UserService {
	public List<User> getUsers();

	public User getCar(int idUser);

	public void delete(int idUser);

	public void update(User car);

	public void insert(User car);

}
