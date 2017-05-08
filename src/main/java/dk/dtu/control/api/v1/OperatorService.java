package dk.dtu.control.api.v1;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dk.dtu.model.IDAL;
import dk.dtu.model.IDAL.DALException;
import dk.dtu.model.OperatorDAO;
import dk.dtu.model.Validation;
import dk.dtu.model.DTO.OperatorDTO;


@Path("/v1/operator")
public class OperatorService {
	
	private IDAL dao = new OperatorDAO();

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public OperatorDTO getOperator(@PathParam("id") String oprID) throws DALException {
		if (Validation.isPositiveInteger(oprID)) {
			return dao.getOperator(Integer.parseInt(oprID));
		} else {
			return null;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OperatorDTO> getOperatorList() throws DALException {
		return dao.getOperatorList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createOperator(OperatorDTO opr) throws DALException {
		return dao.createOperator(opr);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean updateOperator(OperatorDTO opr) throws DALException {
		return dao.updateOperator(opr);
	}
	
	@DELETE
	@Path("/{id}")
	public boolean deleteOperator(@PathParam("id") String oprID) throws DALException {
		if (Validation.isPositiveInteger(oprID)) {
			return dao.deleteOperator(Integer.parseInt(oprID));
		} else {
			return false;
		}
	}
}
