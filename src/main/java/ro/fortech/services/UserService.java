package ro.fortech.services;

import java.util.List;

import ro.fortech.model.User;

/**
 * This service is used in order to perform CRUD operations on the user objects.
 *
 */
public interface UserService {

	/**
	 * Method used in order to get all the users that are present in the
	 * database.
	 * 
	 * @return - a list of users that are present in the database
	 */
	List<User> getUsers();

	/**
	 * Method used to get a user from the database based on the user id
	 * 
	 * @param idUser
	 *            - the id of the user that will be obtained from the database
	 * @return - the user that has the id as the parameter provided for the
	 *         method
	 */
	User getUser(int idUser);

	/**
	 * Method used to delete an user from the database based on the user id
	 * 
	 * @param idUser
	 *            - the id of the user that will be deleted from the database
	 */
	void delete(int idUser);

	/**
	 * Method used to update an user that is present in the database
	 * 
	 * @param user
	 *            - the object that haves the new parameters and will be put in
	 *            the database in order to update an existing one
	 */
	void update(User user);

	/**
	 * Method used to insert a user in the database.
	 * 
	 * @param user
	 *            - the user that ill be inserted in the database.
	 */
	void insert(User user);

}
