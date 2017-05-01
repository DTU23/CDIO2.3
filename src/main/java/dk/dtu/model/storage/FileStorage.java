package dk.dtu.model.storage;

import dk.dtu.model.DTO.OperatorDTO;

import java.io.*;
import java.util.ArrayList;

public class FileStorage implements IDataStorage {

    private String path;

    public FileStorage() {
        this(System.getProperty("user.dir")+"/src/model/storage/data.txt");
    }

    public FileStorage(String path) {
        this.path = path;
    }

    @Override
    public void write(ArrayList<OperatorDTO> users) throws IOException {
        FileOutputStream fOS = null;
        ObjectOutputStream oOS = null;

        try {
            // Serialize the collection of UserDTO's
            fOS = new FileOutputStream(path);
            oOS = new ObjectOutputStream(fOS);

            // Write every object to the file
            for (int i = 0; i < users.size(); i++) {
                oOS.writeObject(users.get(i));
            }
        } finally {
            oOS.close();
        }
    }

    @Override
    public ArrayList<OperatorDTO> read() throws IOException, ClassNotFoundException {
        if(!fileExists()) {
            createFile();
        }
        ArrayList<OperatorDTO> userList = new ArrayList<>();
        FileInputStream fIS = null;
        ObjectInputStream oIS = null;
        try {
            // Deserialize the file back into the collection
            fIS = new FileInputStream(path);
            oIS = new ObjectInputStream(fIS);

            // Pull every object into the ArrayList
            try {
                while (true) {
                    OperatorDTO user = (OperatorDTO) oIS.readObject();
                    userList.add(user);
                }
            } catch (EOFException e) {
                // No problem - no more objects to import
            }

        } finally {
            if (oIS != null){
                oIS.close();
            }
        }
        return userList;
    }

    public boolean fileExists() {
        return new File(path).exists();
    }

    public void createFile() throws IOException {
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(path, "UTF-8");
            writer.println("");
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }

}
