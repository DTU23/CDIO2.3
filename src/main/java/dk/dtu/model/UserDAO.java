package dk.dtu.model;

import dk.dtu.model.DTO.OperatorDTO;

import java.util.ArrayList;

public class UserDAO implements IDAL{

	private ArrayList<OperatorDTO> users;

	public UserDAO() {
		users = new ArrayList<>();
	}

	public OperatorDTO getUser(int userId){
		for (OperatorDTO user : users) {
			if (user.getOprId() == userId) {
				return user;
			}
		}
		return null;
	}

	public ArrayList<OperatorDTO> getUserList() throws DALException {
		if(users == null){
			throw new DALException("Userlist not instantiated!");
		}else {
			return users;
		}
	}

	public boolean isUserListEmpty(){
		return users.isEmpty();
	}

	public void createUser(OperatorDTO user) throws DALException {
		if(users.add(user)){

		} else {
			throw new DALException("User couldn't be created");
		}
	}

	public void updateUser(OperatorDTO user) throws DALException {
		deleteUser(user.getOprId());
		createUser(user);
	}

	public void deleteUser(int userId) throws DALException {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getOprId() == userId) {
				if(users.remove(users.get(i))) {

				} else {
					throw new DALException("User couldn't be removed");
				}
			}
		}
	}

	public boolean userExists(int userId){
		for (OperatorDTO user : users) {
			if (user.getOprId() == userId) {
				return true;
			}
		}
		return false;
	}
	
	public void init() throws DALException {

	}
}