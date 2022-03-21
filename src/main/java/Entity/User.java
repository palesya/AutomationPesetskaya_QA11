package Entity;

public class User {
    private String userName;
    private String password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class userBuilder {
        User user;

        public userBuilder() {
            user = new User();
        }

        public userBuilder withUsername(String userName) {
            user.userName = userName;
            return this;
        }

        public userBuilder withPassword(String password) {
            user.password = password;
            return this;
        }

        public User build() {
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
