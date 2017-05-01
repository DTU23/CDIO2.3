package dk.dtu.model;

import dk.dtu.model.DTO.OperatorDTO;

import java.util.ArrayList;

public interface IDAL {

	OperatorDTO getUser(int userId);

	ArrayList<OperatorDTO> getUserList() throws DALException;
	
	boolean isUserListEmpty();

	void createUser(OperatorDTO user) throws DALException;

	void updateUser(OperatorDTO user) throws DALException;

	void deleteUser(int userId) throws DALException;

	boolean userExists(int userId);
	
	void init() throws DALException;
    
	class DALException extends Exception {
  		
  		private static final long serialVersionUID = 7355418246336739229L;

  		public DALException(String msg, Throwable e) {
  			super(msg,e);
  		}

  		public DALException(String msg) {
  			super(msg);
  		}
  	}
}
