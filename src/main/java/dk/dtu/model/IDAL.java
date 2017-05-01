package dk.dtu.model;

import dk.dtu.model.DTO.OperatorDTO;

import java.util.ArrayList;

public interface IDAL {

	OperatorDTO getOperator(int userId) throws DALException;

	ArrayList<OperatorDTO> getOperatorList() throws DALException;

	boolean createOperator(OperatorDTO user) throws DALException;

	boolean updateOperator(OperatorDTO user) throws DALException;

	boolean deleteOperator(int userId) throws DALException;
    
	class DALException extends Exception {
  		
  		private static final long serialVersionUID = 7355418246336739229L;

  		public DALException(String msg) {
  			super(msg);
  		}
  	}
}
