import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
//    static {
//        String file = Main.class.getClassLoader().getResource("logging.properties").getFile();
//        System.setProperty("java.util.logging.config.file", file);
//    }
//
//    private static Logger logger = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) throws IOException, InterruptedException {
        Service service = new ServiceImpl();
        Scanner scanner = new Scanner(System.in);
        List<User> userList = service.readUsers();
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println(userList.size());
        boolean permission1 = true;
        //boolean permission2 = true;
        while (permission1) {
            boolean permission3 = true;
            String a = "";
            while (permission3) {
                System.out.print("1) Authorization");
                System.out.println("   2)Login");
                System.out.print("here we go - ");
                a = scanner.nextLine();
                if ((Objects.equals(a, "1")) || (Objects.equals(a, "2")))
                    permission3 = false;
                else
                    System.out.println("wrong choice");
            }
            if (Objects.equals(a, "1")) {
                User user = service.authorization(userList);
                service.chat(user, userList);

            } else if (Objects.equals(a, "2")) {
                User user = service.login(userList);
                service.chat(user, userList);
            }
            permission1 = false;
        }
    }
}
