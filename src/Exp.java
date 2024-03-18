import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Exp {
    public static void main(String[] args) throws IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy ',' HH:mm:ss");
        System.out.println(LocalDateTime.now().format(dateTimeFormatter));


//        User user1 = new User("cr7@mail.com", "123", "353535","as212.txt");
//        User user2 = new User("messi@mail.com", "123", "353535","as212.txt");
//        User user3 = new User("lsuarez@mail.com", "123", "353535","as212.txt");
//        ServiceImpl service = new ServiceImpl();
//        service.objectWrite(user1);
//        service.objectWrite(user2);
//        service.objectWrite(user3);
//        List<User> x = service.objectRead(3);
//        System.out.println(x.size());
//        for (int i = 0; i < x.size(); i++) {
//            System.out.println(x.get(i));
//        }

        String string = "cr7@mail.com";
        String string1 = string.replace("@mail.com", ".txt");
        System.out.println(string1);
        String replace = string1.replace("@", "sex");
        System.out.println(replace);


    }

    public List<User> objectRead() {
        ArrayList<User> list = new ArrayList<>();
        File file = new File("io/objects.txt");
        try (FileInputStream out = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(out)) {
            boolean permission = true;
            while (permission) {
                User user = (User) objectInputStream.readObject();
                if (user != null)
                    list.add(user);
                else permission = false;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public void objectWrite(User user) {
        File file = new File("io/objects.txt");
        try (FileOutputStream out = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(out)) {
            objectOutputStream.writeObject(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//
//    tedy_johns@gmail.com
//    jlkesh@gmail.com
//    din_walter@gmail.com
}
