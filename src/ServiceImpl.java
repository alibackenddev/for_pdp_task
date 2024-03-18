import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ServiceImpl implements Service {

    static {
        String file = Main.class.getClassLoader().getResource("logging.properties").getFile();
        System.setProperty("java.util.logging.config.file", file);
    }

    private static Logger logger = Logger.getLogger(Main.class.getSimpleName());
    private final static Pattern compile1 = Pattern.compile("(\\w+)@(\\w{4,5})\\.(\\w{2,3})");
    private final static Pattern compile2 = Pattern.compile("(\\+998)((9[01349])(\\d{7}))");
    private final static Pattern compile3 = Pattern.compile("(?=.*[a-z]+)(?=.*\\d+).{8,32}");

    private static String emailAddress = "";
    private static String phoneNumber = "";
    private static String password = "";

    public User authorization(List<User> userList) throws IOException, InterruptedException {
        boolean permission = true;
        Scanner scanner = new Scanner(System.in);
        while (permission) {
            System.out.print("Email address - ");
            emailAddress = scanner.nextLine();

            System.out.print("Password - ");
            password = scanner.nextLine();

            System.out.print("Phone number - ");
            phoneNumber = scanner.nextLine();
            if (compile1.matcher(emailAddress).matches()
                    && compile3.matcher(password).matches()
                    && compile2.matcher(phoneNumber).matches()) {
                for (User user : userList) {
                    if (user.getEmail().equals(emailAddress) &&
                            user.getPassword().equals(password) &&
                            user.getPhone().equals(phoneNumber)) {
                        logger.info("You are already exist our system, welcome your account");
                        System.out.println("You are already exist our system, welcome your account");
                        return user;
                    }
                }
                String chatLink = emailAddress.replace("@gmail.com", ".txt");
                Files.createFile(Path.of("io/chats/" + chatLink));
                User user = writeToFile(chatLink);
                userList.add(user);

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(" dd-MM-yyyy ',' HH:mm:ss");
                String format = LocalDateTime.now().format(dateTimeFormatter);
                Files.writeString(Path.of("io/registered_time.txt"), user + ", Registered time [" + format + "]" + "\n", StandardOpenOption.APPEND);
                logger.info("Successful authorization!!!");
                TimeUnit.MILLISECONDS.sleep(50);
                return user;
            } else {
                System.out.println("invalid type info");
                boolean perm = true;
                while (perm) {
                    System.out.println("1) Exit" + "   2) Continue");
                    String d = scanner.nextLine();
                    if (Objects.equals(d, "1")) {
                        perm = false;
                        permission = false;
                    } else if (Objects.equals(d, "2"))
                        perm = false;
                }
            }
        }
        return null;
    }

    public User writeToFile(String chatLink) throws IOException {
        Files.writeString(Path.of("io/usernames/username.txt"), "\n" + emailAddress , StandardOpenOption.APPEND);
        Files.writeString(Path.of("io/passwords/passwords.txt"), "\n" +password, StandardOpenOption.APPEND);
        Files.writeString(Path.of("io/phone/phone.txt"), "\n" + phoneNumber, StandardOpenOption.APPEND);
        Files.writeString(Path.of("io/chatlinks/chatlinks.txt"), "\n" + chatLink, StandardOpenOption.APPEND);
        return new User(emailAddress,password,phoneNumber,chatLink);
    }

    public User login(List<User> userList) throws IOException, InterruptedException {
        boolean permission = true;
        Scanner scanner = new Scanner(System.in);
        while (permission) {
            System.out.print("Email address - ");
            emailAddress = scanner.nextLine();

            System.out.print("Password - ");
            password = scanner.nextLine();

            if (compile1.matcher(emailAddress).matches() && compile3.matcher(password).matches()) {
                List<User> list = readUsers();

                for (User user : list) {
                    if (user.getEmail().equals(emailAddress) &&
                            user.getPassword().equals(password)) {
                        logger.info("welcome your account");
                        TimeUnit.MILLISECONDS.sleep(50);
                        return user;
                    }
                }
            } else {
                logger.info("invalid type info or no exist info");
                TimeUnit.MILLISECONDS.sleep(50);
                boolean perm = true;
                while (perm) {
                    System.out.println("1) Exit" + "  2) Continue");
                    String d = scanner.nextLine();
                    if (Objects.equals(d, "1")) {
                        perm = false;
                        permission = false;
                    } else if (Objects.equals(d, "2"))
                        perm = false;
                }
            }
        }
        return null;
    }

    @Override
    public List<User> readUsers() throws IOException {
        List<User> userList = new ArrayList<>();
        List<String> username = Files.readAllLines(Path.of("io/usernames/username.txt"));
        List<String> password = Files.readAllLines(Path.of("io/passwords/passwords.txt"));
        List<String> phones = Files.readAllLines(Path.of("io/phone/phone.txt"));
        List<String> chat_link = Files.readAllLines(Path.of("io/chatlinks/chatlinks.txt"));

        for (int i = 0; i < username.size(); i++) {
            User user = new User();
            user.setEmail(username.get(i));
            user.setPassword(password.get(i));
            user.setPhone(phones.get(i));
            user.setChatLink(chat_link.get(i));
            userList.add(user);
        }
        return userList;
    }

    public void writeMessage(User user, List<User> userList) throws IOException {
        Scanner scanner = new Scanner(System.in);
        for (int j = 0; j < userList.size(); j++) {
            if (!Objects.equals(user.getEmail(), userList.get(j).getEmail()))
                System.out.println(j + ")" + userList.get(j).getEmail());
        }

        System.out.print("choose - ");
        int l = scanner.nextInt();
        System.out.print("Enter your message - ");
        String message = new Scanner(System.in).nextLine();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(" dd-MM-yyyy ',' HH:mm:ss");
        String format = LocalDateTime.now().format(dateTimeFormatter);
        Files.writeString(Path.of("io/chats/" + userList.get(l).getChatLink()),"                     " + user.getEmail() + " ["+ format +"] " + " -> " +"\n" + message + "\n", StandardOpenOption.APPEND);
    }

    @Override
    public void readMessage(User user) throws IOException {
        System.out.println(Files.readString(Path.of("io/chats/" + user.getChatLink())));
    }

    public void chat(User user, List<User> userList) throws IOException {
        Scanner scanner = new Scanner(System.in);
        if (user != null) {
            boolean perm = true;
            while (perm) {
                System.out.println("1) Read message    " + "2) Write message");
                System.out.print("-> = ");
                String i = scanner.nextLine();
                if (Objects.equals(i, "2"))
                    writeMessage(user, userList);

                else if (Objects.equals(i, "1")) {
                    readMessage(user);
                }
                boolean perm2 = true;
                while (perm2) {
                    System.out.println("1) Exit" + "  2) Continue");
                    String d = scanner.nextLine();
                    if (Objects.equals(d, "1")) {
                        perm = false;
                        perm2 = false;
                    } else if (Objects.equals(d, "2"))
                        perm2 = false;
                }
            }
        }
    }
}
