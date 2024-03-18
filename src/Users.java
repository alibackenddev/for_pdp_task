public enum Users {
    USER_ONE("jack@gmail.com", "123qwerty", "+998931234567"),
    USER_TWO("tedy_johns@gmail.com", "456qwerty", "+998947777777");

    public final String userName;
    public final String passWord;
    public final String phone;


    Users(String userName, String passWord, String phone) {
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
    }
}
