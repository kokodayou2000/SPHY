package atguigu.netty.sql;

public class User {
    private int userId;
    private int PASSWORD;
    private String NAME;
    private String message;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", PASSWORD=" + PASSWORD +
                ", NAME='" + NAME + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(int PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String  getNAME() {
        return NAME;
    }

    public User() {
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public User(int userId, int PASSWORD, String NAME) {
        this.userId = userId;
        this.PASSWORD = PASSWORD;
        this.NAME = NAME;
    }
}
