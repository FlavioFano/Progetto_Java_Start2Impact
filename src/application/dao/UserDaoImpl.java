package application.dao;

import application.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final String CSV_FILE_PATH="src/File/utenti.csv";
    @Override
    public User createUser(User user) {
        user.setUserId(getLastUserId()+1);
        try {
            Writer writer = new FileWriter(CSV_FILE_PATH,true);
            writer.write("\n"+
                    user.getUserId()+";"+user.getNome()+";"+user.getCognome()+
                            ";"+user.getDataDiNascita()+";"+user.getIndirizzo()+";"+user.getIdDocumento());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (BufferedReader userReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            skipFirstLine(userReader);
            String line;

            while ((line = userReader.readLine()) != null) {
                users.add(createUserFromLine(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    private User createUserFromLine(String line) {
        User user= new User();
        String[] words = line.split(";");

        user.setUserId(Integer.valueOf(words[0]));
        user.setNome(words[1]);
        user.setCognome(words[2]);
        user.setDataDiNascita(words[3]);
        user.setIndirizzo(words[4]);
        user.setIdDocumento(words[5]);

        return user;
    }

    private void skipFirstLine(BufferedReader userReader) throws IOException {
        userReader.readLine();
    }

    public Integer getLastUserId(){
        List<User> usersList=getAll();
        Integer lastId=0;
        for(User user:usersList){
            lastId=user.getUserId();
        }
        return lastId;
    }
}
