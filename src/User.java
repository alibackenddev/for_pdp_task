public class User {
    private String email;
    private String password;
    private String phone;
    private String chatLink;
    public User(String email, String password, String phone, String chatLink) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.chatLink = chatLink;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getChatLink() {
        return chatLink;
    }

    public void setChatLink(String chatLink) {
        this.chatLink = chatLink;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", chatLink='" + chatLink + '\'' +
                '}';
    }
}
