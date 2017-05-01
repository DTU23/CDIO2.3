package dk.dtu.control.api.v1;

import model.IDAL;
import model.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This controller class handles communication with the data-layer.
 * It also has methods for generating and verifying passwords
 */
public class Ctrl {
	private IDAL dao;

	public Ctrl(IDAL dao){
		this.dao = dao;
	}

	/**
	 * Returns user object from hashmap-key ID
	 * @param hashMap user-details organized in a hashmap
	 * @return User object
	 */
	public UserDTO getUser(HashMap<String, Object> hashMap) throws IDAL.DALException {
		return this.dao.getUser((int) hashMap.get("ID"));
	}

	/**
	 * Gets all users from DAO
	 * @return array list of user objects
	 */
	public ArrayList<UserDTO> getUserList() throws IDAL.DALException {
		return dao.getUserList();
	}

	/**
	 * Checks if any users exist
	 * @return true if user list is empty, false if not.
	 */
	public boolean isUserListEmpty(){
		return dao.isUserListEmpty();
	}

	/**
	 * Creates new user
	 * @param hashMap user-details organized in a hashmap
	 * @throws IDAL.DALException exception from data-layer
	 * @throws UserDTO.DTOException exception from userobject interactions
	 */
	public void createUser(HashMap<String, Object> hashMap) throws UserDTO.DTOException, IDAL.DALException {
		this.dao.createUser(new UserDTO(hashMap));
	}

	/**
	 * Deletes a user from the data persistence
	 * @param hashMap user-details organized in a hashmap
	 * @throws IDAL.DALException exception from data-layer
	 */
	public void deleteUser(HashMap<String, Object> hashMap) throws IDAL.DALException {
		this.dao.deleteUser((int) hashMap.get("ID"));
	}

	/**
	 * Edits a user. Takes ID for user being edited and a hashmap for which key to update.
	 * @param hashMap user-details organized in a hashmap
	 * @throws IDAL.DALException exception raised at data-layer
	 */
	@SuppressWarnings("unchecked")
	public void editUser(HashMap<String, Object> hashMap) throws IDAL.DALException {
		UserDTO user = this.dao.getUser((int) hashMap.get("ID"));
		if(hashMap.containsKey("userName")){
			user.setUserName(hashMap.get("userName").toString());
		}
		if(hashMap.containsKey("ini")){
			user.setIni(hashMap.get("ini").toString());
		}
		if(hashMap.containsKey("cpr")){
			user.setCpr(hashMap.get("cpr").toString());
		}
		if(hashMap.containsKey("password")) {
			user.setPassword(hashMap.get("password").toString());
		}
		if(hashMap.containsKey("roles")){
			user.setRoles((ArrayList<String>) hashMap.get("roles"));
		}
		this.dao.updateUser(user);
	}

	/**
	 * Checks if user exists
	 * @param hashMap user-details organized in a hashmap
	 * @return boolean: true if user exists, false if not.
	 */
	public boolean exists(HashMap<String, Object> hashMap){
		return this.dao.userExists((int) hashMap.get("ID"));
	}

	/**
	 * Initializes datapersistence/DAO.
	 * @throws IDAL.DALException exception from data-layer
	 */
	public void initStorage() throws IDAL.DALException {
		this.dao.init();
	}
}
