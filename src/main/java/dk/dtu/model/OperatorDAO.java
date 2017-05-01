package dk.dtu.model;

import dk.dtu.model.DTO.OperatorDTO;

import java.util.ArrayList;

public class OperatorDAO implements IDAL{

	private ArrayList<OperatorDTO> operatorList;

	public OperatorDAO() {
		operatorList = new ArrayList<>();
	}

	@Override
	public OperatorDTO getOperator(int oprId) throws DALException{
		for (OperatorDTO operator : operatorList) {
			if (operator.getOprId() == oprId) {
				return operator;
			}
		}
		throw new DALException("Operator doesn't exist");
	}

	@Override
	public ArrayList<OperatorDTO> getOperatorList() throws DALException {
		if(!operatorList.isEmpty())
			return operatorList;
		else { throw new DALException("There are no users in the system"); }
	}

	@Override
	public boolean createOperator(OperatorDTO opr) throws DALException {
		try {
			getOperator(opr.getOprId());
			throw new DALException("OperatorID already used");
		} catch (DALException e) {
			return operatorList.add(opr);
		}
	}

	@Override
	public boolean updateOperator(OperatorDTO opr) throws DALException {
		operatorList.remove(getOperator(opr.getOprId()));
		return operatorList.add(opr);
	}

	@Override
	public boolean deleteOperator(int oprId) throws DALException {
		return operatorList.remove(getOperator(oprId));
	}
}