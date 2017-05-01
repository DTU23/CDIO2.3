package dk.dtu.model;


import java.io.IOException;
import java.util.ArrayList;

import dk.dtu.model.DTO.OperatorDTO;
import dk.dtu.model.storage.IDataStorage;

public class PersistentOperatorDAO implements IDAL{

	private IDataStorage storage;
	private ArrayList<OperatorDTO> operatorList;

	public PersistentOperatorDAO(IDataStorage storage) {
		this.storage = storage;
		operatorList = new ArrayList<>();
		try {
			init();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OperatorDTO getOperator(int oprId) throws DALException {
		for(OperatorDTO operator : operatorList) {
			if(operator.getOprId() == oprId) {
				return operator;
			}
		}
		throw new DALException("Operator doesn't exist");
	}

	@Override
	public ArrayList<OperatorDTO> getOperatorList() throws DALException {
		if(operatorList == null) {
			throw new DALException("There is a problem with the storage");
		} else if(operatorList.isEmpty()) {
			throw new DALException("There are no users in the system");
		} else {
			return operatorList;
		}
	}

	@Override
	public boolean createOperator(OperatorDTO opr) throws DALException {
		operatorList.add(opr);
		try {
			storage.write(operatorList);
			return true;
		}catch (IOException e){
			throw new DALException("IOException");
		}
	}

	@Override
	public boolean updateOperator(OperatorDTO opr) throws DALException {
		operatorList.remove(getOperator(opr.getOprId()));
		operatorList.add(opr);
		try {
			storage.write(operatorList);
			return true;
		}catch (IOException e){
			throw new DALException("IOException");
		}
	}

	@Override
	public boolean deleteOperator(int oprId) throws DALException {
		operatorList.remove(getOperator(oprId));
		try {
			storage.write(operatorList);
			return true;
		}catch (IOException e){
			throw new DALException("IOException");
		}
	}

	public void init() throws DALException {
		try {
			operatorList = storage.read();
		} catch (ClassNotFoundException e){
			throw new DALException("ClassNotFoundException");
		} catch (IOException e){
			throw new DALException("IOException");
		}
	}
}