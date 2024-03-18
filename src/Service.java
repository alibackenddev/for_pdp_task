import java.io.IOException;
import java.util.List;
import java.util.Objects;

public interface Service {
    User authorization(List<User> userList) throws IOException, InterruptedException;
    User login(List<User> userList) throws IOException, InterruptedException;
    void writeMessage(User user, List<User> userList) throws IOException;
    void readMessage(User user) throws IOException;
    List<User> readUsers() throws IOException;
    User writeToFile(String chatLink) throws IOException;
    void chat(User user, List<User> userList) throws IOException;
}
