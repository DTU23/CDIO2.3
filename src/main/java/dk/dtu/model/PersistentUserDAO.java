package dk.dtu.model;


import dk.dtu.model.DTO.OperatorDTO;
import dk.dtu.model.storage.IDataStorage;

import java.io.IOException;
import java.util.ArrayList;

public class PersistentUserDAO implements IDAL{

	private IDataStorage storage;
	private ArrayList<OperatorDTO> users;

	public PersistentUserDAO(IDataStorage storage) {
		this.storage = storage;
		users = new ArrayList<>();
	}

	public OperatorDTO getUser(int userId) {
		for (OperatorDTO user : users) {
			if (user.getOprId() == userId) {
				return user;
			}
		}
		return null;
	}

	public ArrayList<OperatorDTO> getUserList() throws DALException {
		if (users == null) {
			throw new DALException("Userlist not instantiated");
		} else {
			return users;
		}
	}
	
	public boolean isUserListEmpty(){
        return users.isEmpty();
    }

	public void createUser(OperatorDTO user) throws DALException {
		users.add(user);

		try {
			storage.write(users);
		}catch (IOException e){
			throw new DALException("IOException", e);
		}
	}

	public void updateUser(OperatorDTO user) throws DALException {
		deleteUser(user.getOprId());
		createUser(user);

		try {
			storage.write(users);
		}catch (IOException e){
			throw new DALException("IOException", e);
		}
	}

	public void deleteUser(int userId) throws DALException {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getOprId() == userId) {
				users.remove(users.get(i));
			}
		}

		try {
			storage.write(users);
		}catch (IOException e){
			throw new DALException("IOException", e);
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
		try {
			users = storage.read();
		} catch (ClassNotFoundException e){
			throw new DALException("ClassNotFoundException", e);
		} catch (IOException e){
			throw new DALException("IOException", e);
		}
	}
}