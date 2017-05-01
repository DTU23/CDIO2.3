package dk.dtu.model.storage;

import dk.dtu.model.DTO.OperatorDTO;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public interface IDataStorage {
    /**
     * Updates memory to persistent data. Returns false on error
     * @param users array-list of userDTO objects
     */
    void write(ArrayList<OperatorDTO> users) throws IOException;

    /**
     * Reads data input to memory
     * @return ArrayList<UserDTO>
     */
    ArrayList<OperatorDTO> read() throws IOException, ClassNotFoundException;
}
